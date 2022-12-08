/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.banhang;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.GheDao;
import com.org.app.controller.LoaiVeDao;
import com.org.app.controller.PhimDao;
import com.org.app.controller.PhongChieuDao;
import com.org.app.controller.SuatChieuDao;
import com.org.app.entity.Ghe;
import com.org.app.entity.Phim;
import com.org.app.entity.PhongChieu;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.DateHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.ui.component.GheLabel;
import com.org.app.ui.component.GheMapPanel;
import com.org.app.ui.component.VeDatList;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.CustomGridLayOut;
import com.org.app.util.LabelWrapperInterface;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toMap;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author intfs
 */
public class DatVeFrame extends javax.swing.JFrame implements SubFrame<DatVeFrame>, SubPanelCreatorInterfaces<SubFrame>, LabelWrapperInterface {
    SubPanelCreator<DatVeFrame> subPanel;
    GheMapPanel gheP;
    SuatChieuDao scDao;
    PhongChieuDao phongDao;
    PhimDao phimDao;
    LoaiVeDao loaiVeDao;
    public boolean isEdit = false;
    public boolean isNotInThreeDay = false;
//    GheDao gheDao;
    DatMenuFrame menuFrame = null;
    Integer limitLuongVe = 0;
    List<SuatChieu> scIncoming;
    CardLayout layout;
    public VeDatList veSelectedList;
    HashMap<String, Phim> pIncoming;
    HashMap<String,SuatChieu> scm;
    HashMap<String, PhongChieu> pcm;
    HashMap<String, Ghe> ghem;
    //test
//    static Date CURRENT_DATE = DateHelper.toDate("2022-04-18",DateHelper.YYYYMMDD_FORMAT);
//    static Time CURRENT_TIME = TimeHelper.toTime(7,0,0);
    static Date CURRENT_DATE = DateHelper.now();
    static Time CURRENT_TIME = TimeHelper.now();
    
    /**
     * Creates new form DatVeFrame
     */
    
    //truong hop khong con phim sap chieu
    public DatVeFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        scDao = new SuatChieuDao();
        phongDao = new PhongChieuDao();
        phimDao = new PhimDao();
        loaiVeDao = new LoaiVeDao();
        scIncoming = new ArrayList<>();
        pIncoming = new HashMap<>();
        scm = new HashMap<>();
        pcm = new HashMap<>();
        ghem = new HashMap<>();
        veSelectedList = new VeDatList(getLoaiVeMap());
        layout = (CardLayout)pnlGheMap.getLayout();
        gheP = new GheMapPanel(this, pnlGhe.getWidth(), pnlGhe.getHeight());
//        gheDao = new GheDao();
         setUp();

    }
    
    public DatVeFrame(DatMenuFrame menuF) {
       this();
       menuFrame = menuF;
    }
    
    private void setUp() {
        System.out.println(CURRENT_DATE);
        System.out.println(CURRENT_TIME);
        for (int i = 0; i < 11; i++) {
            cboVeNguoiLon.addItem(i);
            cboVeTreEm.addItem(i);
        }
        pnlGheMap.add("empty",pnlEmpty);
        pnlGheMap.add("ghe",pnlGhe);
    }

    @Override
    public JPanel getContentPanelFor(JComponent panel) {
        return subPanel.createPanelFor(panel);
    }

    @Override
    public SubPanelCreator createSubPanel(SubFrame f) {
        return new SubPanelCreator<>(f) {
            @Override
            public void render() {
                renderFrame();
            }
        };
    }

    private void renderFrame() {
         try {
            ExecutorService service = Executors.newFixedThreadPool(2);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
//                    System.out.println("load tt in render frame");
                    if(isNotInThreeDay) {
//                        System.out.println("Not in three day");
                    }else loadTT();
                    return null;
                }
            }));
            service.invokeAll(calls);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(!isEdit) setLuongVe() ;
        System.out.println("done render frame dat ve");
    }
    
    private void loadVeDatList() {
        cboPhim.setSelectedItem(veSelectedList.getPhim());
            cboSuat.setSelectedItem(veSelectedList.getSc());
            cboVeNguoiLon.setSelectedItem(veSelectedList.getAdult_limit());
            cboVeTreEm.setSelectedItem(veSelectedList.getChild_limit());
    }
    
    private void setLuongVe() {
        cboVeNguoiLon.setSelectedIndex(1);
        cboVeTreEm.setSelectedIndex(0);
    }
    
    private void loadTT() {
        scIncoming.clear();
        if(!isNotInThreeDay) {
            //update time
            updateDateTime();
        }
        try{

        ExecutorService service = Executors.newFixedThreadPool(10);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
                    scIncoming = scDao.selectTTDatVeByDate(CURRENT_DATE, CURRENT_TIME, 1);
                    setMap();
                    loadCboPhim();
//                    showGheMap();
                    return null;
                }
            }));
            service.invokeAll(calls);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private Map<String, Double> getLoaiVeMap() {
        try {
            return loaiVeDao.getMapLoaiVe();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    private void setMap() {
        Map<String, SuatChieu> map = scIncoming.stream().collect(toMap( s -> s.getId(),  Function.identity()));
        scm = new HashMap<>(map);
    }
    private void loadCboPhim() {
        try {
            cboPhim.removeAllItems();
            if(scIncoming.size() == 0) return;
            System.out.println(scm.keySet());
            List<Phim> p = phimDao.selectBySuatChieu(scm.keySet().toArray(String[]::new));
            System.out.println(p.size());
            updateMapPhim(p);
            for(Phim ps : pIncoming.values())
                cboPhim.addItem(ps);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void updateMapPhim(List<Phim> lp) {
        pIncoming.clear();
       for(Phim p : lp) {
           pIncoming.put(p.getId(), p);
       }
    }


    @Override
    public SubPanelCreator getSubPanelCreator() {
        return subPanel;
    }


    void loadLich(String id) {
        List<SuatChieu> scop = findbyPhim(id);
        cboSuat.removeAllItems();
        for(SuatChieu sc : scop) {
            cboSuat.addItem(sc);
        }
        
    }
    
    void loadPhong(List<SuatChieu> scPhim) {
        try {
            pcm.clear();
            List<String> phong = scPhim.stream().map(sc -> sc.getPhong()).collect(Collectors.toList());
            System.out.println(phong);
            List<PhongChieu> pcs = phongDao.selectbyMulIds(phong.toArray(String[]::new));
            Map<String, PhongChieu> map = pcs.stream()
                .collect(Collectors.toMap(PhongChieu::getId, Function.identity()));
            pcm.putAll(map);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    } 
    
   private  List<SuatChieu> findbyPhim(String idp) {
        List<SuatChieu> scOfPhim = scIncoming.stream().filter(sc -> sc.getPhim().equals(idp)).collect(Collectors.toList());
        System.out.println("scOfPhim "+ scOfPhim);
        return scOfPhim;
    }
   
   
    void selectedSuatChieu(SuatChieu sc) {
        System.out.println("insides set selected sc");
        Phim phim = (Phim)cboPhim.getSelectedItem();
        System.out.println(sc.getId());
        loadPhong(findbyPhim(phim.getId()));
        

        setRelatedLabel(sc);
        System.out.println("isEdit = "+ isEdit);
        if(!isEdit) veSelectedList.clear();
        updateVeListInfo();
        updateDetailPanelLabels();
        showGheMap();
        System.out.println("whu");
    }
    
    public void setDatVeFor(SuatChieu sc) {
        String phim = sc.getPhim();
        
        Phim p =  pIncoming.get(phim);
        cboPhim.setSelectedItem(p);
        cboSuat.setSelectedItem(sc);
    }
    
    public void setDatVeForLongTerm(SuatChieu sc) {
        CURRENT_DATE = sc.getNgayChieu();
        CURRENT_TIME = TimeHelper.minus(sc.getGioBatDau(), 5);
//        System.out.println("current = "+ CURRENT_DATE.toString() + "\n time = "+ CURRENT_TIME.toString());
        loadTT();
        setDatVeFor(sc);
        System.out.println("long term");
        
    }
    
    
    void selectedPhim() {
        Phim phim = (Phim)cboPhim.getSelectedItem();
        loadLich(phim.getId());
        setLabelPhimChon(phim.getTen());
        
    }
    
    void setLabelPhimChon(String ten) {
        lblPhim.setText(getText(ten));
    }
    
    private void setRelatedLabel(SuatChieu sc) {
        lblNgayChieu.setText(DateHelper.toString(sc.getNgayChieu(),DateHelper.DDMMYYY_SLASH_FORMAT));
        lblSuatChieu.setText(TimeHelper.toStringWithHourMintues(sc.getGioBatDau()));
        lblPhong.setText(sc.getPhongNum());
        lblGheChon.setText("");
    }
    
   
    
    private String getText(String txt) {
        return  String.format("<html>%s</html>", txt).toString();
    }
    
     private void showGheMap() {
        if(scIncoming.isEmpty()) {
            layout.show(pnlGheMap, "empty");
            btnThanhToan.setEnabled(false);
        }else {
            System.out.println("\nshow pnlGhe\n");
            updatePanelGhe();
            layout.show(pnlGheMap, "ghe");
        }
    }
     
     private void updateVeListInfo() {
         veSelectedList.setSuatAndPhim((Phim)cboPhim.getSelectedItem(), getSelectedSuatChieu());
     }
    
    private void updatePanelGhe(){
        System.out.println("update panel ghe");
        SuatChieu sc;
        gheP.setMapOf(getSelectedSuatChieu(), getPhongChieuCboItem());
        JPanel p = gheP.getPanel();
        pnlGhe.removeAll();
        p .setVisible(true);
        pnlGhe.add(p,BorderLayout.CENTER);
    }
    
    private void setLuongVe(boolean isVeTreEm) {
        int tongVe = (Integer)cboVeNguoiLon.getSelectedItem() + (Integer) cboVeNguoiLon.getSelectedItem();
        Object newLimit = isVeTreEm? cboVeTreEm.getSelectedItem() : cboVeNguoiLon.getSelectedItem();
//        System.out.println("newLimit = "+ newLimit);
        veSelectedList.setLimit(isVeTreEm, (Integer)newLimit);
        
//        System.out.println("getlimit = "  + veSelectedList.geListtLimit() );
        //if(veSelectedList.getSize() > 0)
        updateDetailPanelLabels();
    }
    
    public void updateDetailPanelLabels() {
        Integer[] expected = veSelectedList.getLuongVeExpectedInfo();
        Integer[] current =  veSelectedList.getLuongVeCurrentInfo();
        JLabel[] lbls = {lblSoVeNLSelected,lblSoVeTESelected, lblSoVeSelected};
        int veVip = veSelectedList.countVeVip();
        for (int i = 0; i < expected.length; i++) {
            String txt = detailStringFormat(current[i], expected[i]);
            lbls[i].setText(txt);
        }
        lblSoVeVip.setText(String.valueOf(veVip));
    }
    
    private String detailStringFormat(int current, int expected) {
        if(expected == 0) {
            return "0";
        }
        return String.format("%d/%d", current, expected);

    }
    
    public Integer getLuongVe() {
        return limitLuongVe;
    }
    
    public Integer getLimitNL() {
        return (Integer)cboVeNguoiLon.getSelectedItem();
    }
    
    public Integer getLimitTE() {
        return (Integer)cboVeTreEm.getSelectedItem();
    } 
    
    private PhongChieu getPhongChieuCboItem() {
        String pc_id = getSelectedSuatChieu().getPhong();
        return pcm.get(pc_id);
    }
    
    private SuatChieu getSelectedSuatChieu() {
        return (SuatChieu) cboSuat.getSelectedItem();
    }
    
    private void getGheMap() {
        SuatChieu sc = (SuatChieu) cboSuat.getSelectedItem();
        PhongChieu pc = pcm.get(sc.getPhong());
    }
    
    
    
    public void showListGheChon() {
        List<GheLabel> gList = veSelectedList.getListVe();
        
        String sq = gList.stream().map((t) -> {
            String id = t.getGhe().getId();
            String loai = t.toStringLoaiVe();
            return String.format("#%s_<b>%s</b>", loai, id.substring(id.length()-3, id.length()));
           
        }).collect(Collectors.joining(", "));
        
        wrap(lblGheChon, sq);
    }
    
    
    public void check() {
        if(veSelectedList.isBlank()) {
            MessageHelper.message(this, "Vui lòng chọn số lượng vé và ghế.");
        }else if(!veSelectedList.isFull()) {
            try {
                veSelectedList.checkIsAllSelected();
            }catch (RuntimeException ex) {
                MessageHelper.message(this, ex.getMessage());
            }
        }else {
            menuFrame.setDonThanhToanAsVe(this);
        }
    }
    
    private void updateDateTime() {
        CURRENT_DATE = DateHelper.now();
        CURRENT_TIME = TimeHelper.now();
    }
       

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContent = new javax.swing.JPanel();
        pnlLichRap = new javax.swing.JPanel();
        pnlSuatPhong = new javax.swing.JPanel();
        cboSuat = new javax.swing.JComboBox<>();
        pnlPhim = new javax.swing.JPanel();
        cboPhim = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        pnlPhong = new javax.swing.JPanel();
        lblManHinh = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlChuThichGhe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlGheMap = new javax.swing.JPanel();
        pnlGhe = new javax.swing.JPanel();
        pnlEmpty = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        pnlGheChon = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboVeTreEm = new javax.swing.JComboBox<>();
        cboVeNguoiLon = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        pnlThongTin = new javax.swing.JPanel();
        pnlTenPhim = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lblPhim = new javax.swing.JLabel();
        pnlNgayChieu = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblNgayChieu = new javax.swing.JLabel();
        pnlSuat = new javax.swing.JPanel();
        lblSuat = new javax.swing.JLabel();
        lblSuatChieu = new javax.swing.JLabel();
        pnlSuat1 = new javax.swing.JPanel();
        lblSuat1 = new javax.swing.JLabel();
        lblPhong = new javax.swing.JLabel();
        pnlSelectedGhe = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblGheChon = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        pnlDetail = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblSoVeTESelected = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblSoVeNLSelected = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        lblSoVeVip = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblSoVeSelected = new javax.swing.JLabel();
        bntQuayLai = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 650));
        setMinimumSize(new java.awt.Dimension(700, 650));

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlContent.setPreferredSize(new java.awt.Dimension(700, 650));

        pnlLichRap.setBackground(new java.awt.Color(214, 229, 217));
        pnlLichRap.setMaximumSize(new java.awt.Dimension(500, 70));
        pnlLichRap.setPreferredSize(new java.awt.Dimension(500, 70));
        pnlLichRap.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlSuatPhong.setBackground(new java.awt.Color(198, 217, 201));
        pnlSuatPhong.setMaximumSize(new java.awt.Dimension(227, 44));
        pnlSuatPhong.setOpaque(false);

        cboSuat.setMaximumSize(new java.awt.Dimension(207, 22));
        cboSuat.setMinimumSize(new java.awt.Dimension(207, 22));
        cboSuat.setPreferredSize(new java.awt.Dimension(207, 22));
        cboSuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSuatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSuatPhongLayout = new javax.swing.GroupLayout(pnlSuatPhong);
        pnlSuatPhong.setLayout(pnlSuatPhongLayout);
        pnlSuatPhongLayout.setHorizontalGroup(
            pnlSuatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSuatPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboSuat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlSuatPhongLayout.setVerticalGroup(
            pnlSuatPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuatPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboSuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlLichRap.add(pnlSuatPhong, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 13, -1, 44));

        pnlPhim.setBackground(new java.awt.Color(198, 217, 201));
        pnlPhim.setMaximumSize(new java.awt.Dimension(214, 44));
        pnlPhim.setOpaque(false);

        cboPhim.setMaximumSize(new java.awt.Dimension(194, 22));
        cboPhim.setMinimumSize(new java.awt.Dimension(194, 22));
        cboPhim.setPreferredSize(new java.awt.Dimension(194, 22));
        cboPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPhimLayout = new javax.swing.GroupLayout(pnlPhim);
        pnlPhim.setLayout(pnlPhimLayout);
        pnlPhimLayout.setHorizontalGroup(
            pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPhim, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlPhimLayout.setVerticalGroup(
            pnlPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlLichRap.add(pnlPhim, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, -1, 44));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setMaximumSize(new java.awt.Dimension(10, 48));
        jSeparator1.setPreferredSize(new java.awt.Dimension(10, 48));
        pnlLichRap.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(236, 11, -1, -1));

        pnlPhong.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhong.setMaximumSize(new java.awt.Dimension(480, 590));
        pnlPhong.setPreferredSize(new java.awt.Dimension(480, 590));
        pnlPhong.setLayout(null);

        lblManHinh.setBackground(new java.awt.Color(51, 51, 51));
        lblManHinh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblManHinh.setForeground(new java.awt.Color(255, 255, 255));
        lblManHinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblManHinh.setText("Màn hình");
        lblManHinh.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204)));
        lblManHinh.setMaximumSize(new java.awt.Dimension(480, 21));
        lblManHinh.setMinimumSize(new java.awt.Dimension(480, 21));
        lblManHinh.setOpaque(true);
        lblManHinh.setPreferredSize(new java.awt.Dimension(480, 21));
        pnlPhong.add(lblManHinh);
        lblManHinh.setBounds(0, 40, 480, 29);

        jPanel1.setBackground(new java.awt.Color(236, 237, 229));
        jPanel1.setMaximumSize(new java.awt.Dimension(480, 24));
        jPanel1.setPreferredSize(new java.awt.Dimension(480, 24));

        pnlChuThichGhe.setOpaque(false);
        pnlChuThichGhe.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        jLabel1.setBackground(new java.awt.Color(236, 237, 229));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/seat_available.png"))); // NOI18N
        jLabel1.setText("Ghế trống");
        jLabel1.setIconTextGap(10);
        jLabel1.setOpaque(true);
        pnlChuThichGhe.add(jLabel1);

        jLabel3.setBackground(new java.awt.Color(236, 237, 229));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/seat_selected.png"))); // NOI18N
        jLabel3.setText("Ghế được chọn");
        jLabel3.setOpaque(true);
        pnlChuThichGhe.add(jLabel3);

        jLabel2.setBackground(new java.awt.Color(236, 237, 229));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/seat_unavailable.png"))); // NOI18N
        jLabel2.setText("Ghế đã đặt");
        jLabel2.setOpaque(true);
        pnlChuThichGhe.add(jLabel2);

        jLabel4.setBackground(new java.awt.Color(236, 237, 229));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/seat_vp_available.png"))); // NOI18N
        jLabel4.setText("Ghế VIP");
        jLabel4.setOpaque(true);
        pnlChuThichGhe.add(jLabel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(pnlChuThichGhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlChuThichGhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlPhong.add(jPanel1);
        jPanel1.setBounds(0, 80, 480, 24);

        pnlGheMap.setMaximumSize(new java.awt.Dimension(480, 410));
        pnlGheMap.setPreferredSize(new java.awt.Dimension(480, 410));
        pnlGheMap.setLayout(new java.awt.CardLayout());

        pnlGhe.setBackground(new java.awt.Color(153, 204, 255));
        pnlGhe.setMaximumSize(new java.awt.Dimension(480, 400));
        pnlGhe.setPreferredSize(new java.awt.Dimension(480, 400));
        pnlGhe.setLayout(new java.awt.BorderLayout());
        pnlGheMap.add(pnlGhe, "card2");

        pnlEmpty.setBackground(new java.awt.Color(255, 153, 153));
        pnlEmpty.setMaximumSize(new java.awt.Dimension(480, 450));
        pnlEmpty.setPreferredSize(new java.awt.Dimension(480, 450));

        jLabel15.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Không có suất chiếu kế tiếp");

        javax.swing.GroupLayout pnlEmptyLayout = new javax.swing.GroupLayout(pnlEmpty);
        pnlEmpty.setLayout(pnlEmptyLayout);
        pnlEmptyLayout.setHorizontalGroup(
            pnlEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmptyLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        pnlEmptyLayout.setVerticalGroup(
            pnlEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmptyLayout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pnlGheMap.add(pnlEmpty, "card3");

        pnlPhong.add(pnlGheMap);
        pnlGheMap.setBounds(0, 110, 480, 410);

        pnlGheChon.setBackground(new java.awt.Color(229, 236, 213));
        pnlGheChon.setMaximumSize(new java.awt.Dimension(202, 650));
        pnlGheChon.setPreferredSize(new java.awt.Dimension(202, 650));

        jPanel2.setBackground(new java.awt.Color(229, 234, 218));
        jPanel2.setMaximumSize(new java.awt.Dimension(181, 103));

        jLabel5.setText("Người lớn: ");
        jLabel5.setMaximumSize(new java.awt.Dimension(77, 31));
        jLabel5.setMinimumSize(new java.awt.Dimension(54, 31));
        jLabel5.setPreferredSize(new java.awt.Dimension(77, 31));

        jLabel6.setText("Trẻ em:");
        jLabel6.setMaximumSize(new java.awt.Dimension(56, 31));
        jLabel6.setMinimumSize(new java.awt.Dimension(56, 31));
        jLabel6.setPreferredSize(new java.awt.Dimension(56, 31));

        cboVeTreEm.setMaximumSize(new java.awt.Dimension(80, 22));
        cboVeTreEm.setMinimumSize(new java.awt.Dimension(80, 22));
        cboVeTreEm.setPreferredSize(new java.awt.Dimension(80, 22));
        cboVeTreEm.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVeTreEmItemStateChanged(evt);
            }
        });
        cboVeTreEm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVeTreEmActionPerformed(evt);
            }
        });

        cboVeNguoiLon.setMaximumSize(new java.awt.Dimension(80, 22));
        cboVeNguoiLon.setMinimumSize(new java.awt.Dimension(80, 22));
        cboVeNguoiLon.setPreferredSize(new java.awt.Dimension(80, 22));
        cboVeNguoiLon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboVeNguoiLonItemStateChanged(evt);
            }
        });
        cboVeNguoiLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVeNguoiLonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboVeNguoiLon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(cboVeTreEm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVeTreEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVeNguoiLon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel7.setText("Thông tin ghế chọn");
        jLabel7.setPreferredSize(new java.awt.Dimension(158, 42));

        pnlThongTin.setBackground(new java.awt.Color(229, 234, 218));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        pnlThongTin.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

        pnlTenPhim.setBackground(new java.awt.Color(210, 230, 211));
        pnlTenPhim.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel8.setText("Phim:");

        lblPhim.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhim.setPreferredSize(new java.awt.Dimension(126, 38));

        javax.swing.GroupLayout pnlTenPhimLayout = new javax.swing.GroupLayout(pnlTenPhim);
        pnlTenPhim.setLayout(pnlTenPhimLayout);
        pnlTenPhimLayout.setHorizontalGroup(
            pnlTenPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTenPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTenPhimLayout.setVerticalGroup(
            pnlTenPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlThongTin.add(pnlTenPhim);

        pnlNgayChieu.setBackground(new java.awt.Color(210, 230, 211));
        pnlNgayChieu.setPreferredSize(new java.awt.Dimension(118, 30));

        jLabel11.setText("Ngày:");

        lblNgayChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblNgayChieu.setForeground(new java.awt.Color(51, 51, 255));
        lblNgayChieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayChieu.setMaximumSize(new java.awt.Dimension(104, 38));
        lblNgayChieu.setPreferredSize(new java.awt.Dimension(104, 38));

        javax.swing.GroupLayout pnlNgayChieuLayout = new javax.swing.GroupLayout(pnlNgayChieu);
        pnlNgayChieu.setLayout(pnlNgayChieuLayout);
        pnlNgayChieuLayout.setHorizontalGroup(
            pnlNgayChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayChieuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlNgayChieuLayout.setVerticalGroup(
            pnlNgayChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNgayChieuLayout.createSequentialGroup()
                .addGroup(pnlNgayChieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThongTin.add(pnlNgayChieu);

        pnlSuat.setBackground(new java.awt.Color(210, 230, 211));
        pnlSuat.setPreferredSize(new java.awt.Dimension(118, 30));

        lblSuat.setText("Suất chiếu:");

        lblSuatChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblSuatChieu.setForeground(new java.awt.Color(51, 51, 255));
        lblSuatChieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSuatChieu.setPreferredSize(new java.awt.Dimension(97, 38));

        javax.swing.GroupLayout pnlSuatLayout = new javax.swing.GroupLayout(pnlSuat);
        pnlSuat.setLayout(pnlSuatLayout);
        pnlSuatLayout.setHorizontalGroup(
            pnlSuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSuat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSuatLayout.setVerticalGroup(
            pnlSuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuatLayout.createSequentialGroup()
                .addGroup(pnlSuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblSuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSuatChieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThongTin.add(pnlSuat);

        pnlSuat1.setBackground(new java.awt.Color(210, 230, 211));
        pnlSuat1.setPreferredSize(new java.awt.Dimension(118, 30));

        lblSuat1.setText("Phòng Chiếu");

        lblPhong.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblPhong.setForeground(new java.awt.Color(51, 51, 255));
        lblPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhong.setPreferredSize(new java.awt.Dimension(91, 38));

        javax.swing.GroupLayout pnlSuat1Layout = new javax.swing.GroupLayout(pnlSuat1);
        pnlSuat1.setLayout(pnlSuat1Layout);
        pnlSuat1Layout.setHorizontalGroup(
            pnlSuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuat1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSuat1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSuat1Layout.setVerticalGroup(
            pnlSuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuat1Layout.createSequentialGroup()
                .addGroup(pnlSuat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSuat1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThongTin.add(pnlSuat1);

        pnlSelectedGhe.setBackground(new java.awt.Color(229, 234, 218));
        pnlSelectedGhe.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        pnlSelectedGhe.setMaximumSize(new java.awt.Dimension(181, 109));

        jLabel9.setText("Ghế đã chọn:");
        jLabel9.setMaximumSize(new java.awt.Dimension(94, 17));
        jLabel9.setPreferredSize(new java.awt.Dimension(94, 17));

        lblGheChon.setBackground(new java.awt.Color(51, 148, 204));
        lblGheChon.setForeground(new java.awt.Color(51, 148, 204));
        lblGheChon.setMaximumSize(new java.awt.Dimension(161, 63));
        lblGheChon.setPreferredSize(new java.awt.Dimension(161, 63));

        javax.swing.GroupLayout pnlSelectedGheLayout = new javax.swing.GroupLayout(pnlSelectedGhe);
        pnlSelectedGhe.setLayout(pnlSelectedGheLayout);
        pnlSelectedGheLayout.setHorizontalGroup(
            pnlSelectedGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectedGheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSelectedGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGheChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlSelectedGheLayout.setVerticalGroup(
            pnlSelectedGheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSelectedGheLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGheChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        pnlDetail.setBackground(new java.awt.Color(229, 234, 218));
        pnlDetail.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        pnlDetail.setMaximumSize(new java.awt.Dimension(181, 130));
        pnlDetail.setMinimumSize(new java.awt.Dimension(181, 130));
        pnlDetail.setPreferredSize(new java.awt.Dimension(181, 130));
        pnlDetail.setLayout(new java.awt.GridLayout(4, 1, 0, 8));

        jPanel6.setBackground(new java.awt.Color(234, 230, 198));

        jLabel10.setText("Vé trẻ em:");
        jLabel10.setMaximumSize(new java.awt.Dimension(73, 26));
        jLabel10.setMinimumSize(new java.awt.Dimension(73, 26));
        jLabel10.setPreferredSize(new java.awt.Dimension(73, 26));

        lblSoVeTESelected.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblSoVeTESelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoVeTESelected.setMaximumSize(new java.awt.Dimension(91, 26));
        lblSoVeTESelected.setPreferredSize(new java.awt.Dimension(91, 26));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoVeTESelected, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSoVeTESelected, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlDetail.add(jPanel6);

        jPanel5.setBackground(new java.awt.Color(234, 230, 198));

        jLabel12.setText("Vé người lớn:");
        jLabel12.setMaximumSize(new java.awt.Dimension(73, 26));
        jLabel12.setPreferredSize(new java.awt.Dimension(73, 26));

        lblSoVeNLSelected.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblSoVeNLSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoVeNLSelected.setMaximumSize(new java.awt.Dimension(91, 26));
        lblSoVeNLSelected.setPreferredSize(new java.awt.Dimension(91, 26));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoVeNLSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSoVeNLSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel12, lblSoVeNLSelected});

        pnlDetail.add(jPanel5);

        jPanel3.setBackground(new java.awt.Color(234, 230, 198));

        jLabel14.setText("Vé VIP:");
        jLabel14.setMaximumSize(new java.awt.Dimension(73, 26));
        jLabel14.setMinimumSize(new java.awt.Dimension(73, 26));
        jLabel14.setPreferredSize(new java.awt.Dimension(73, 26));

        lblSoVeVip.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblSoVeVip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoVeVip.setMaximumSize(new java.awt.Dimension(91, 26));
        lblSoVeVip.setPreferredSize(new java.awt.Dimension(91, 26));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoVeVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSoVeVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel14, lblSoVeVip});

        pnlDetail.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(234, 230, 198));

        jLabel13.setText("Tổng số vé:");
        jLabel13.setMaximumSize(new java.awt.Dimension(73, 26));
        jLabel13.setPreferredSize(new java.awt.Dimension(73, 26));

        lblSoVeSelected.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblSoVeSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSoVeSelected.setMaximumSize(new java.awt.Dimension(91, 26));
        lblSoVeSelected.setPreferredSize(new java.awt.Dimension(91, 26));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSoVeSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblSoVeSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, lblSoVeSelected});

        pnlDetail.add(jPanel4);

        bntQuayLai.setText("Quay lại");
        bntQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGheChonLayout = new javax.swing.GroupLayout(pnlGheChon);
        pnlGheChon.setLayout(pnlGheChonLayout);
        pnlGheChonLayout.setHorizontalGroup(
            pnlGheChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGheChonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGheChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGheChonLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(bntQuayLai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhToan))
                    .addGroup(pnlGheChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(pnlThongTin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlGheChonLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlSelectedGhe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pnlGheChonLayout.setVerticalGroup(
            pnlGheChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGheChonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSelectedGhe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(pnlDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlGheChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(bntQuayLai))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlLichRap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlGheChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlContentLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGheChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                        .addComponent(pnlLichRap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhimActionPerformed
        if(cboPhim.getItemCount() == 0) {
            showGheMap();
            return;
        }
        selectedPhim();
    }//GEN-LAST:event_cboPhimActionPerformed

    private void cboSuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSuatActionPerformed
        if(cboSuat.getItemCount() == 0) return;
//        System.out.println("\tcbo action perfomr\n");
        selectedSuatChieu((SuatChieu)(cboSuat.getSelectedItem()));
    }//GEN-LAST:event_cboSuatActionPerformed

    private void bntQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntQuayLaiActionPerformed
        if(isNotInThreeDay) isNotInThreeDay = false;
        menuFrame.showCard(DatMenuFrame.GuiOf.HOME);
//        menuFrame.mainFrame.showCard(DatMenuFrame.CARD_NAME_MAIN);
    }//GEN-LAST:event_bntQuayLaiActionPerformed

    private void cboVeTreEmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVeTreEmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboVeTreEmActionPerformed

    private void cboVeTreEmItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVeTreEmItemStateChanged
       if(evt.getStateChange() == ItemEvent.SELECTED) {
           setLuongVe(true);
       }
    }//GEN-LAST:event_cboVeTreEmItemStateChanged

    private void cboVeNguoiLonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboVeNguoiLonItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED) {
           setLuongVe(false);
       }
    }//GEN-LAST:event_cboVeNguoiLonItemStateChanged

    private void cboVeNguoiLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVeNguoiLonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboVeNguoiLonActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        check();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatVeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatVeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatVeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatVeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatVeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntQuayLai;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<Phim> cboPhim;
    private javax.swing.JComboBox<SuatChieu> cboSuat;
    private javax.swing.JComboBox<Integer> cboVeNguoiLon;
    private javax.swing.JComboBox<Integer> cboVeTreEm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGheChon;
    private javax.swing.JLabel lblManHinh;
    private javax.swing.JLabel lblNgayChieu;
    private javax.swing.JLabel lblPhim;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblSoVeNLSelected;
    private javax.swing.JLabel lblSoVeSelected;
    private javax.swing.JLabel lblSoVeTESelected;
    private javax.swing.JLabel lblSoVeVip;
    private javax.swing.JLabel lblSuat;
    private javax.swing.JLabel lblSuat1;
    private javax.swing.JLabel lblSuatChieu;
    private javax.swing.JPanel pnlChuThichGhe;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDetail;
    private javax.swing.JPanel pnlEmpty;
    private javax.swing.JPanel pnlGhe;
    private javax.swing.JPanel pnlGheChon;
    private javax.swing.JPanel pnlGheMap;
    private javax.swing.JPanel pnlLichRap;
    private javax.swing.JPanel pnlNgayChieu;
    private javax.swing.JPanel pnlPhim;
    private javax.swing.JPanel pnlPhong;
    private javax.swing.JPanel pnlSelectedGhe;
    private javax.swing.JPanel pnlSuat;
    private javax.swing.JPanel pnlSuat1;
    private javax.swing.JPanel pnlSuatPhong;
    private javax.swing.JPanel pnlTenPhim;
    private javax.swing.JPanel pnlThongTin;
    // End of variables declaration//GEN-END:variables

}

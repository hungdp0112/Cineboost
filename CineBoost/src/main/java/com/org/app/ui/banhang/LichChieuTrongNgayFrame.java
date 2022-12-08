/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.banhang;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.DonThanhToanDao;
import com.org.app.controller.PhimDao;
import com.org.app.controller.PhongChieuDao;
import com.org.app.controller.SuatChieuDao;
import com.org.app.controller.VeDatDao;
import com.org.app.entity.Phim;
import com.org.app.entity.PhongChieu;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.DateHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.ui.component.PhimPanel;
import com.org.app.ui.component.PhimPanelModel;
import com.org.app.ui.component.PhongPanel;
import com.org.app.ui.main.MainFrame;
import com.org.app.ui.quanly.*;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.CustomGridLayOut;
import com.org.app.util.DinhDangTienTe;
import com.org.app.util.FixedSizeCoatingPanel;
import com.org.app.util.LabelWrapperInterface;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JViewport;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author intfs
 */
public class LichChieuTrongNgayFrame extends javax.swing.JFrame implements SubFrame<LichChieuTrongNgayFrame>, SubPanelCreatorInterfaces<SubFrame>, LabelWrapperInterface {
    private static String PANEL_PHONG_CARD = "phongchieu";
    private static String PANEL_PHIM_CARD   = "lichchieu";
    private static String TITLE_TEXT = "Lịch Chiếu trong ngày";
    SubPanelCreator<LichChieuTrongNgayFrame> subPanel;
    CardLayout l;
    LocalDateTime now;
    MainFrame mainFrame;
    SuatChieuDao scDao;
    PhongChieuDao pcDao;
    DonThanhToanDao ttdao = new DonThanhToanDao();
    PhimDao phimDao;
    VeDatDao veDatDao;
    List<SuatChieu> scs;
    List<PhongChieu> phongs;
    HashMap<SuatChieu, Integer> luongVeSuatMap;
    List<Phim> phims;
    HashMap<String, Phim> mapPhim;
    Timer timer;
    Timer backgroundTimer;
    Instant n;
    java.util.Date nowDate = DateHelper.now();
    public static String CARD_NAME_MAIN = "dashboard";
    /**
     * Creates new form QLProfile
     */
    final int COL_NUM = 2;

    public LichChieuTrongNgayFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        l = (CardLayout) pnlDash.getLayout();
        scDao = new SuatChieuDao();
        pcDao = new PhongChieuDao();
        scs = new ArrayList<>();
        phimDao = new PhimDao();
        veDatDao = new VeDatDao();
        phims = new ArrayList<>();
        phongs = new ArrayList<>();
        mapPhim = new HashMap<>();
        luongVeSuatMap = new HashMap<>();
        
    }
    
    public LichChieuTrongNgayFrame(MainFrame mainF) {
        this();
        mainFrame = mainF;
        now = LocalDateTime.now();
        
        //test only 
//        now = LocalDateTime.of(2022, java.time.Month.APRIL, 20,16, 58, 50);
        lblTitle.setText(TITLE_TEXT+ " "+ DateHelper.toString(now, DateHelper.DDMMYYY_SLASH_FORMAT));
        setUp();
    }
    
    private void loading() {
        try {
            ExecutorService service = Executors.newFixedThreadPool(5);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((() -> {loadLichChieu(); addPhong(); addPhim();return null;}));
            service.invokeAll(calls);
            loadLuongVeBan(); loadDoanhThu();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   
    private boolean isCurrent() {
        Class s = mainFrame.getCurrentFrame().getClass();
        boolean isCurrent = s.equals(this.getClass());
        System.out.println("\t Current f = "+s+"\n");
       return isCurrent;
    }
       private void runInBackGround() {
           System.out.println("in bacground");
        if(backgroundTimer != null) backgroundTimer.stop();
       
        backgroundTimer = new Timer(1000, (e) -> {
            System.out.println("\ntimer " + (timer== null));
            System.out.println("isNoscComing = " + isNoComingSuat());
            if(!isCurrent()) {
                timer.stop();
                timer = null;
                backgroundTimer.stop();
                backgroundTimer = null;
            }else if(timer == null && !isNoComingSuat()) {
//            System.out.println("not null");
            if(isCurrent()) {
//                System.out.println("start count down");
                countdown();
            }
        }
       });
//           System.out.println("start");
       backgroundTimer.start();
       
      
   }
    
    
    private void loadLuongVeBan() throws Exception {
//       Integer lv = veDatDao.getLuongVeBanNgay(LocalDate().now);
       Integer lv = veDatDao.getLuongVeBanNgay(now.toLocalDate());
        System.out.println("lv = "+ lv);
       lblLuongVe.setText(String.format("%s%d", lv<10 && lv >0? "0" : "", lv));
    }
    private void loadDoanhThu() throws SQLException {
//        Double tong = ttdao.getDoanhThuNgay(LocalDate.now());
        Double tong = ttdao.getDoanhThuNgay(now.toLocalDate());
        System.out.println("\ttong "+ tong);
        lblDoanhThu.setText(DinhDangTienTe.chuyenThanhTienVN(tong));
    }
    private void loadLichChieu() {
        try {
            scs.clear();
//            LocalDateTime n = LocalDateTime.now();
            //test
//            now = LocalDateTime.of(2022, Month.MARCH, 18, 10, 40, 0);

            //needed
            now = LocalDateTime.now();
            scs = scDao.selectInComingSuatChieus(now);
            if(!isNoComingSuat()) {
                loadListPhim();
                setLuongVeSuatMap();
            }
            pnlDash.repaint();
            pnlDash.revalidate();
//            System.out.println("\tincoming sc = "+scs.size());
            System.out.println(phims);
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageHelper.message(this, ex.toString(),ColorAndIconBank.Icon.ERROR);
        }
    }

        private Instant futureTime;
    
    private void countdown() {
        System.out.println("runcountdown");
        if(isNoComingSuat())return;
        SuatChieu nextSC = scs.get(0);
        setLblPhim(scs.get(0).getPhim());
        //get ngay, gio suat
        LocalDate date = DateHelper.convertToLocalDate(nextSC.getNgayChieu());
        LocalTime time = TimeHelper.toLocalTime(nextSC.getGioBatDau());
        futureTime = LocalDateTime.of(date,time)
                        .atZone(ZoneId.systemDefault()).toInstant();
         //test only
       // LocalDateTime now = LocalDateTime.of(2022, java.time.Month.APRIL, 16,22, 59, 30);
         n = now.atZone(ZoneId.systemDefault()).toInstant();
        if (timer != null) {
                timer.stop();
            }
            lblCountDown.setText("---");
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Duration duration = Duration.between(Instant.now(), futureTime);
                    //test
                    // n = n.plus(1, ChronoUnit.SECONDS);
                    //Duration duration = Duration.between(n, futureTime);
                    if (duration.isNegative()) {
                        timer.stop();
                        timer = null;
                        lblCountDown.setText("00:00:00");
//                        setNowTest();
                        //keep
                        now = LocalDateTime.now();
                        loading();
                    } else {
                        String formatted = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
                        lblCountDown.setText(formatted);
                    }
                }
            });
            timer.start();
        }
    
    private void setNowTest() {
        now = LocalDateTime.of(2022, java.time.Month.APRIL, 20, 5, 01, 30);
    }
    
    private void setLblPhim(String idPhim) {
        String tenPhim = mapPhim.get(idPhim).getTen();
        wrap(lblPhim, tenPhim);
    }

    private void setLuongVeSuatMap() throws Exception {
        luongVeSuatMap.clear();
        for(SuatChieu s : scs) {
            int lv = veDatDao.getLuongVeOf(s.getId());
            luongVeSuatMap.put(s, lv);
        }
        System.out.println("luongvesuat: "+ luongVeSuatMap.toString());
    }
    

    
     private void addPhong() {
        try {
            phongs =  pcDao.selectAll();
            CustomGridLayOut layout = new CustomGridLayOut(phongs.size(), 2, 15, 30);
            pnlPhong1.setLayout(layout.getLayOut());
               
            updatePhongPanel();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     
    private void updatePhongPanel() throws Exception {
//        java.sql.Date date = DateHelper.now();
//        java.sql.Date date = DateHelper.toDate("18/03/2022",DateHelper.DDMMYYY_SLASH_FORMAT);
//        now = LocalDateTime.now(); 
//        java.sql.Time time = TimeHelper.now();
//        test
        java.sql.Date date = DateHelper.toDate(now.toLocalDate());
        java.sql.Time time = TimeHelper.toSQLTime(now.toLocalTime());
        pnlPhong1.removeAll();
        List<List<Object[]>> top3List = getListTop3(date, time);
        List<Integer[]> luongSuat = getLuongSuat(date, time);
        System.out.println("top3List : "+ top3List.size() );
        List<Object[]> top3;
        Integer[] ls ;
        for (int i = 0; i < phongs.size(); i++) {
           top3 = top3List.get(i);
           ls = luongSuat.get(i);
                String id_phong = phongs.get(i).getId();
                PhongPanel phongP = new PhongPanel(id_phong, mainFrame);
                phongP.setLuongSuatChieu(ls, top3);
                pnlPhong1.add(FixedSizeCoatingPanel.getCoatingJPanel(phongP));
        }
        pnlPhong1.repaint();
        pnlPhong1.revalidate();
        pnlPhong.repaint();
        pnlPhong.revalidate();
    }
    
    private List<List<Object[]>>  getListTop3( java.sql.Date date, java.sql.Time time) throws Exception {
        List<List<Object[]>> list = new  ArrayList<>();
        for(PhongChieu pc : phongs) {
            list.add(scDao.selectTopSuatCuaPhong(pc.getId(), date, time));
        }
        return list;
    }
    
    private List<Integer[]> getLuongSuat(java.sql.Date date, java.sql.Time time) throws Exception {
        List<Integer[]> list = new ArrayList<>();
        for(PhongChieu pc : phongs) {
            list.add(scDao.selectSuatDaChieuCuaPhong(pc.getId(), date, time));
        }
        return list;
    }

    void addPhim() {
        
        System.out.println("run addPhim");
        try{
            pnlPhims1.removeAll();
            if(isNoComingSuat()) {
                pnlPhims1.setVisible(!isNoComingSuat());
                lblEmptyPhim.setVisible(isNoComingSuat());
                return ;
            }else {
                System.out.println("iscOMING = FLASE");
                lblEmptyPhim.setVisible(isNoComingSuat());
                System.out.println("lblEmpty = "+ lblEmptyPhim);
                pnlPhims1.setVisible(!isNoComingSuat());
            }
            updatePhim();
        }catch(Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, e.getMessage(), 0);
        }
    }
    
    private void updatePhim() {
        System.out.println("");
        pnlPhims1.removeAll();
        Integer num = scs.size();
        CustomGridLayOut layout = new CustomGridLayOut(num, COL_NUM, 10, 15);
        pnlPhims1.setLayout(layout.getLayOut());
        
        for (int i = 0; i < num; i++) {
            System.out.println("add phim ");
            SuatChieu suat = scs.get(i);
            String scp = suat.getPhim();
            PhimPanelModel model = new PhimPanelModel(mapPhim.get(scp), suat, getLuongVeOf(suat));
            PhimPanel pp = new PhimPanel(mainFrame,model);
            pnlPhims1.add(FixedSizeCoatingPanel.getCoatingJPanel(pp));
            pp.setPoster();
        }
        pnlPhims.repaint();;
        pnlPhims.revalidate();
       
    }
    
    private int getLuongVeOf(SuatChieu sc) {
        return luongVeSuatMap.get(sc);
    }
    
    private void loadListPhim() throws Exception {
        phims.clear();
        Set<String> ids = getListIDPhim();
//        Set<String> setPhim = new HashSet<>(ids);
        System.out.println("ids = "+ids.toString());
        phims = phimDao.selectByIds(ids.toArray(String[]::new));
        addToHashMap(phims);
    }
    
    private void addToHashMap(List<Phim> pl) {
        mapPhim.clear();
        for(Phim p : pl) {
            mapPhim.put(p.getId(), p);
        }
    }
    
    private Set<String> getListIDPhim() {
        Set<String> idList = new HashSet<>();
        for(SuatChieu sc : scs) {
            idList.add(sc.getPhim());
        }
        return idList;
    }
    
    private boolean isNoComingSuat() {
        return scs.isEmpty();
    }
    
    private void showCard(boolean b) {
        if(b) l.show(pnlDash, PANEL_PHIM_CARD);
        else l.show(pnlDash, PANEL_PHONG_CARD);
    }
    
        private void setUp() {
        lblEmptyPhim.setVisible(false);
        sp.setBorder(BorderFactory.createEmptyBorder());
        sp.getViewport().setOpaque(false);
        sp.setViewportBorder(null);
        spp.getViewport().setOpaque(false);
        spp.setViewportBorder(null);
        spp.setViewportBorder(null);
        sp.setOpaque(false);
        spp.setOpaque(false);
        //add card name
        pnlDash.add(PANEL_PHIM_CARD, sp);
        pnlDash.add(PANEL_PHONG_CARD, spp);
//        pnl.isRounedBorder = 1;
//        pnl.repaint();
        
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
        jPanel1 = new com.org.app.customui.GradientPanel("#D3E2E2", "#F3F6D4", 145);
        jLabel3 = new javax.swing.JLabel();
        lblCountDown = new javax.swing.JLabel();
        lblPhim = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblLuongVe = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblDoanhThu = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        tglState = new javax.swing.JToggleButton();
        pnlDash = new javax.swing.JPanel();
        spp = new javax.swing.JScrollPane();
        pnlPhong = new javax.swing.JPanel();
        pnlPhong1 = new javax.swing.JPanel();
        sp = new javax.swing.JScrollPane();
        pnlPhims = new javax.swing.JPanel();
        lblEmptyPhim = new javax.swing.JLabel();
        pnlPhims1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 650));

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setPreferredSize(new java.awt.Dimension(700, 650));

        jPanel1.setBackground(new java.awt.Color(239, 224, 120));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204)));
        jPanel1.setMaximumSize(new java.awt.Dimension(200, 87));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 87));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Suất chiếu kế tiếp");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel3.setMaximumSize(new java.awt.Dimension(200, 23));
        jLabel3.setMinimumSize(new java.awt.Dimension(200, 23));
        jLabel3.setPreferredSize(new java.awt.Dimension(200, 23));

        lblCountDown.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        lblCountDown.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCountDown.setText("--------");

        lblPhim.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCountDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(lblPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCountDown)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(236, 188, 127));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204)));

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Số vé đã bán ");

        lblLuongVe.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblLuongVe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLuongVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_ticket_30px_2.png"))); // NOI18N
        lblLuongVe.setText("305");
        lblLuongVe.setMaximumSize(new java.awt.Dimension(182, 67));
        lblLuongVe.setMinimumSize(new java.awt.Dimension(182, 67));
        lblLuongVe.setPreferredSize(new java.awt.Dimension(182, 67));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLuongVe, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(202, 227, 190));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204)));

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Doanh thu");

        lblDoanhThu.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        lblDoanhThu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDoanhThu.setText("-------");
        lblDoanhThu.setMaximumSize(new java.awt.Dimension(182, 67));
        lblDoanhThu.setMinimumSize(new java.awt.Dimension(182, 67));
        lblDoanhThu.setPreferredSize(new java.awt.Dimension(182, 67));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel4.setBackground(new java.awt.Color(245, 244, 244));
        jPanel4.setPreferredSize(new java.awt.Dimension(231, 35));

        lblTitle.setBackground(new java.awt.Color(245, 244, 244));
        lblTitle.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_film_reel_24px.png"))); // NOI18N
        lblTitle.setText("Lịch Chiếu trong ngày");
        lblTitle.setOpaque(true);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tglState.setSelected(true);
        tglState.setText("Hiện lịch theo phim");
        tglState.setFocusable(false);
        tglState.setMaximumSize(new java.awt.Dimension(127, 30));
        tglState.setMinimumSize(new java.awt.Dimension(127, 30));
        tglState.setPreferredSize(new java.awt.Dimension(127, 30));
        tglState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglStateActionPerformed(evt);
            }
        });

        pnlDash.setBackground(new java.awt.Color(237, 237, 226));
        pnlDash.setOpaque(false);
        pnlDash.setLayout(new java.awt.CardLayout());

        spp.setBackground(new java.awt.Color(153, 153, 0));
        spp.setBorder(null);
        spp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spp.setMaximumSize(new java.awt.Dimension(700, 650));
        spp.setPreferredSize(new java.awt.Dimension(700, 650));

        pnlPhong.setBackground(new java.awt.Color(153, 153, 0));
        pnlPhong.setMaximumSize(null);
        pnlPhong.setOpaque(false);

        pnlPhong1.setMaximumSize(null);
        pnlPhong1.setOpaque(false);
        pnlPhong1.setPreferredSize(new java.awt.Dimension(661, 441));

        javax.swing.GroupLayout pnlPhong1Layout = new javax.swing.GroupLayout(pnlPhong1);
        pnlPhong1.setLayout(pnlPhong1Layout);
        pnlPhong1Layout.setHorizontalGroup(
            pnlPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );
        pnlPhong1Layout.setVerticalGroup(
            pnlPhong1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPhongLayout = new javax.swing.GroupLayout(pnlPhong);
        pnlPhong.setLayout(pnlPhongLayout);
        pnlPhongLayout.setHorizontalGroup(
            pnlPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPhong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        pnlPhongLayout.setVerticalGroup(
            pnlPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPhongLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(pnlPhong1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        spp.setViewportView(pnlPhong);
        pnlPhong.getAccessibleContext().setAccessibleName("");

        pnlDash.add(spp, "card4");

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setMaximumSize(null);
        sp.setPreferredSize(new java.awt.Dimension(700, 650));

        pnlPhims.setMaximumSize(null);
        pnlPhims.setOpaque(false);

        lblEmptyPhim.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblEmptyPhim.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmptyPhim.setText("Không có suất chiếu");

        pnlPhims1.setBackground(new java.awt.Color(102, 255, 102));
        pnlPhims1.setOpaque(false);
        pnlPhims1.setPreferredSize(new java.awt.Dimension(650, 441));

        javax.swing.GroupLayout pnlPhims1Layout = new javax.swing.GroupLayout(pnlPhims1);
        pnlPhims1.setLayout(pnlPhims1Layout);
        pnlPhims1Layout.setHorizontalGroup(
            pnlPhims1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 650, Short.MAX_VALUE)
        );
        pnlPhims1Layout.setVerticalGroup(
            pnlPhims1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlPhimsLayout = new javax.swing.GroupLayout(pnlPhims);
        pnlPhims.setLayout(pnlPhimsLayout);
        pnlPhimsLayout.setHorizontalGroup(
            pnlPhimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPhims1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlPhimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlPhimsLayout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(lblEmptyPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(199, Short.MAX_VALUE)))
        );
        pnlPhimsLayout.setVerticalGroup(
            pnlPhimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPhims1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlPhimsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlPhimsLayout.createSequentialGroup()
                    .addGap(194, 194, 194)
                    .addComponent(lblEmptyPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(194, Short.MAX_VALUE)))
        );

        sp.setViewportView(pnlPhims);

        pnlDash.add(sp, "card2");

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 10, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContentLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tglState, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pnlDash, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnlContentLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel2, jPanel3});

        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tglState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlDash, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tglStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglStateActionPerformed
        tglState.setText(tglState.isSelected()? "Hiển thị theo phim" : "Hiển thị theo phòng");
        showCard(tglState.isSelected());
    }//GEN-LAST:event_tglStateActionPerformed

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
            java.util.logging.Logger.getLogger(LichChieuTrongNgayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LichChieuTrongNgayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LichChieuTrongNgayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LichChieuTrongNgayFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainFrame mf = null;
                LichChieuTrongNgayFrame lcf = new LichChieuTrongNgayFrame(mf);
                lcf.renderFrame();lcf.setVisible(true);
            }
        });
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
    
   @Override
    public SubPanelCreator getSubPanelCreator() {
        return subPanel;
    }
    
    private void renderFrame() {
        try {
         ExecutorService service = Executors.newFixedThreadPool(2);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
                    System.out.println("renderFrame of dashboard");
                    loading();
                    runInBackGround();
                    return null;
                }
                
            }));
            service.invokeAll(calls);
            System.out.println("finsish renderframe");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCountDown;
    private javax.swing.JLabel lblDoanhThu;
    private javax.swing.JLabel lblEmptyPhim;
    private javax.swing.JLabel lblLuongVe;
    private javax.swing.JLabel lblPhim;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDash;
    private javax.swing.JPanel pnlPhims;
    private javax.swing.JPanel pnlPhims1;
    private javax.swing.JPanel pnlPhong;
    private javax.swing.JPanel pnlPhong1;
    private javax.swing.JScrollPane sp;
    private javax.swing.JScrollPane spp;
    private javax.swing.JToggleButton tglState;
    // End of variables declaration//GEN-END:variables


}

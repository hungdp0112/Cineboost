/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.org.app.ui.quanly;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.PhimDao;
import com.org.app.controller.PhongChieuDao;
import com.org.app.controller.SuatChieuDao;
import com.org.app.entity.Phim;
import com.org.app.entity.PhongChieu;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.DateHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.ui.component.LichPhimPanel;
import com.org.app.ui.component.LichPhimPanelComponent;
import com.org.app.ui.main.MainFrame;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.LabelWrapperInterface;
import com.org.app.util.PhimComboRender;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author lenovo
 */
public class TraCuuLichChieuFrame extends javax.swing.JFrame implements SubFrame<TraCuuLichChieuFrame>,SubPanelCreatorInterfaces<SubFrame>, LabelWrapperInterface {
SubPanelCreator<TraCuuLichChieuFrame> subPanel;
    private PhimDao phimDao;
    private List<Phim> listPhim;
    private SuatChieuDao scDao;
    private PhongChieuDao phongDao;
    private List<Vector<Object>> listToTable;
    private List<PhongChieu> phongs;
    private CardLayout l;
    private LichPhimPanel lichpnl;
    private MainFrame mainF;
    private DefaultTableModel model;
    private TableRendererUtil tblRender;
    private int index = -1;
     
    public static final int TAB_THEO_PHIM = 1;
    public static final int TAB_TUY_CHON = 0;
    public static final String TG_BTN_FILTER_PHIM = "Hiện theo phim";
    public static final String TG_BTN_FILTER_TATCA = "Hiện toàn bộ";
    public static final String LICHPANEL_SC_CARD = "lich";
    public static final String LICHPANEL_EMPTY_CARD = "empty";
    public static final String CARD_NAME_MAIN = "tralich";
    private int index_cboPhim = -1;
    
    /** Creates new form DialogTraCuuLichChieu */
    public TraCuuLichChieuFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        l = (CardLayout) pnlDsLich.getLayout();
        lichpnl = new LichPhimPanel(pnlLichPhim);
        phimDao = new PhimDao();
        phongDao = new PhongChieuDao();
        scDao = new SuatChieuDao();
        listPhim = new ArrayList<>();
        listToTable = new ArrayList<>();
        phongs = new ArrayList<>();
        model = (DefaultTableModel) tblTraCuuTuyChon.getModel();
        tabsTracuulichchieu.setSelectedIndex(TAB_TUY_CHON);
        tblRender = new TableRendererUtil(tblTraCuuTuyChon);
        tblRender.changeHeaderStyle(Color.decode("#B3D7D2"));
    }
    
    public TraCuuLichChieuFrame(MainFrame mainFrame) {
        this();
        this.mainF = mainFrame;

    }
    
    public void setFilterPhong(String idPhong, java.util.Date datebd,java.util.Date kt) {
        for(PhongChieu pc : phongs) {
            if(pc.getId().equals(idPhong)) {
                cboPhongChieu.setSelectedItem(pc);
                chkTraTheoPhongChieu.setSelected(true);
                chkTrongKhoang.setSelected(true);
                dcNgayBatDau.setDate(datebd);
                dcNgayKetThuc.setDate(kt);
                btnLoc.doClick();
                return;
            }
        }
    }
    
    private void setUp() {
        chkTraTheoPhongChieu.setSelected(false);
        chkTheoTinhTrang.setSelected(false);
        chkTrongKhoang.setSelected(false);
        syncDateChooserWithCheckBox();
        
        pnlDsLich.add(LICHPANEL_SC_CARD, pnlLichPhim);
        pnlDsLich.add(LICHPANEL_EMPTY_CARD, pnlPhimEmpty);
        
        lockJDCEditor(dcNgayBatDau);
        lockJDCEditor(dcNgayChieu);

        dcNgayBatDau.setDate(DateHelper.now());
        dcNgayKetThuc.setDate(DateHelper.addDays(DateHelper.now(), 1));
        
        dcNgayChieu.setMaxSelectableDate(DateHelper.addYears(DateHelper.now(), 1));
        btnFilterPhim.setSelected(true);
        showCboPhimInTuyChon(btnFilterPhim.isSelected());
        dcNgayChieu.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
//                System.out.println("dc = " + dcNgaySinh.getDate().toString());
                java.util.Date d = dcNgayChieu.getDate();
                
                JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dcNgayChieu.getDateEditor();
                if(txtFld.getForeground().equals(Color.RED)) {
                    System.out.println("false date = null");
                    btnTim.setEnabled(false);
                    btnUpdateByDate.setEnabled(false);
                }else {
                    wrap(lblNgayChieu, DateHelper.toString(d));
                    btnTim.setEnabled(true);
                    btnUpdateByDate.setEnabled(true);
                    showLichOfPhim();
                }
                
            }
        });
        
        dcNgayBatDau.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                java.util.Date bd = dcNgayBatDau.getDate();
                dcNgayKetThuc.setMinSelectableDate(dcNgayBatDau.getDate());
                if(dcNgayKetThuc.getDate().compareTo(bd) < 0) {
                    System.out.println("change");
                    dcNgayKetThuc.setDate(DateHelper.addDays(bd, 1));
                }
        };
        });
        
  
        btnFilterPhim.setUI(new MetalToggleButtonUI() {
                             @Override
                protected Color getSelectColor() {
                return new Color(223,235,232);
        }
    });
    }
    
    private void lockJDCEditor(JDateChooser dc) {
        JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dc.getDateEditor();
        txtFld.setEditable(false);
    }
    
    private boolean isTuyChonAllMode() {
        return btnFilterPhim.isSelected();
    }
    
    private Object[] getFilter() {
        //phim, ngaybd, ngaykt, phong
        String phim = null, phong = null; 
        java.sql.Date bd = null, kt = null;
        Integer tt = !chkTheoTinhTrang.isSelected()? -1 : rdoNgungChieu.isSelected()? 1 : 0;
        Object arr[] = {phim, bd, kt, phong,tt};  
        if(!isTuyChonAllMode()) {
            arr[0] = getSelectedPhim(true).getId();
        }
        if(chkTrongKhoang.isSelected()) {
            arr[1] = DateHelper.toDate(dcNgayBatDau.getDate());
            arr[2] = DateHelper.toDate(dcNgayKetThuc.getDate());
        }
        
        if(chkTraTheoPhongChieu.isSelected()) {
            arr[3] = ((PhongChieu)cboPhongChieu.getSelectedItem()).getId();
        }
        System.out.println("arr = "+java.util.Arrays.toString(arr));
        return arr;
    }
   public void showTable() {
       System.out.println("show table");
       Vector<String> COLUMN_NAME = new Vector(List.of("STT","NGÀY CHIẾU", "TÊN PHIM", "PHÒNG", "THỜI LƯỢNG", "GIỜ BẮT ĐẦU", "GIỜ KẾT THÚC","ID SUẤT"));
       listToTable.clear();
       model.setRowCount(0);
       tblTraCuuTuyChon.clearSelection();
       Object[] arr = getFilter();

       try {
               listToTable = scDao.selectByAll(arr);
           System.out.println("list size = "+ listToTable.size());
           System.out.println(listToTable);
           model.setColumnIdentifiers(COLUMN_NAME);
               for (int i = 0; i < listToTable.size(); i++) {
                   listToTable.get(i).add(0, String.valueOf(i+1));
                   listToTable.get(i).set(1, DateHelper.toString((Date)listToTable.get(i).get(1)));
                   model.addRow(listToTable.get(i));
           }
               tblRender.setColumnAlignment(new int[] {0,1,2,3,4,5,6}, SwingConstants.CENTER);
            TableColumnModel tcm = tblTraCuuTuyChon.getColumnModel();
            tcm.removeColumn( tcm.getColumn(7) );
            index = -1;
       } catch (Exception ex) {
           ex.printStackTrace();
       }
   }
    
    public void showTab(int index) {
        tabsTracuulichchieu.setSelectedIndex(index);
    }
    
    private void loadCboPhim() {
            listPhim.clear();
            DefaultComboBoxModel modelPhimF = (DefaultComboBoxModel) cboPhimTuyChon.getModel();
//            DefaultComboBoxModel modelPhim = (DefaultComboBoxModel) cboPhim.getModel();
            DefaultComboBoxModel modelPhims = (DefaultComboBoxModel) cboPhims.getModel();
            modelPhimF.removeAllElements();
//            modelPhim.removeAllElements();
            modelPhims.removeAllElements();
        try {
            listPhim = phimDao.selectAll();
//            loadListToModel(modelPhim, listPhim);
            loadListToModel(modelPhimF, listPhim);
            loadListToModel(modelPhims, listPhim);
            
            cboPhimTuyChon.setRenderer(new PhimComboRender());
            if(!listPhim.isEmpty() && index_cboPhim == -1) cboPhims.getModel().setSelectedItem(listPhim.get(0));
        } catch (Exception ex) {
            MessageHelper.message(this, ex.getMessage(), 0);
        }
    }
    
 
    private void loadListToModel(DefaultComboBoxModel m, List list) {
        for (int i = 0; i < list.size(); i++) {
            m.addElement(list.get(i));
        }
    }
    
    
       @Override
    public JPanel getContentPanelFor(JComponent panel) {
        return subPanel.createPanelFor(panel);
    }

       @Override
    public SubPanelCreator getSubPanelCreator() {
        return subPanel;
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
    
    private void renderFrame(){
        setUp();
        setDatButtonState();
        loadComboPhong();
        loadCboPhim();
        dcNgayChieu.setDate(DateHelper.now());
        btnTim.doClick();
        
        showTable();
    }
    
    private void showCboPhimInTuyChon(boolean isSelected) {
        btnFilterPhim.setText(isSelected?  TG_BTN_FILTER_TATCA : TG_BTN_FILTER_PHIM );
        pnlFilterByPhim.setVisible(!isSelected);
        showTable();
        
    }
    
    
    
    private void showSelectePhim() {
    try {
        if(index_cboPhim == -1) return;
        
//        Phim p = (Phim)cboPhim.getSelectedItem();
        Phim p = getSelectedPhim(false);
//        System.out.println((JCombobox)cboPhims.getModel().getSelectedItem());
        loadPoster(p);
        showLichOfPhim();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    
    private java.sql.Date getSelectedDateInPhim() {
        return DateHelper.toDate(dcNgayChieu.getDate());
    }
    
    private void showLichOfPhim() {
    try {
        pnlLichPhim.removeAll();
        Phim phim = getSelectedPhim(false);
        List<SuatChieu> scs = scDao.selectByPhimNgay(getSelectedDateInPhim(), phim.getId());
        System.out.println("scs size = "+ scs.size());
        if(scs.isEmpty()) {
            setTextOfEmptyLichPanel(phim, rootPaneCheckingEnabled);
            l.show(pnlDsLich, LICHPANEL_EMPTY_CARD);
        }else {
            l.show(pnlDsLich, LICHPANEL_SC_CARD);
            lichpnl.setLichPhimList(scs);
            addListenerForLichComponent(lichpnl.getListComponents());
            pnlLichPhim.add(lichpnl, BorderLayout.CENTER);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    }
    
    private void addListenerForLichComponent(List<LichPhimPanelComponent> list) {
        for(LichPhimPanelComponent lp : list) {
            lp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                 setLichPanelComponentListener(lp);
                 if(e.getClickCount() == 2) {
                     System.out.println("double click");
                     SuatChieu sc = lp.getSuatChieu();
                     if(lp.isDaChieu()) {
                         MessageHelper.message(mainF, "Suất đã chiếu không thể đặt");
                     }else moveToDatVe(sc);
                }
            }
           });
        }
    }
    
    private void selectLichPhong(LichPhimPanelComponent lp) {
        if(!lp.isDaChieu()) {
            SuatChieu sc = lp.getSuatChieu();
            moveToDatVe(sc);
        }else {
            MessageHelper.message(this, "Suất đã chiếu không thể đặt vé");
        }
    }
    
    private void setLichPanelComponentListener(LichPhimPanelComponent lichComp) {
            List<LichPhimPanelComponent> lcs = lichpnl.getListComponents();
            for(LichPhimPanelComponent l : lcs) l.setUnselect();
            
            index = lichComp.getIndex();
            System.out.println("change index = "+ index);
            lichComp.setSelected();
    }
    
    private void setTextOfEmptyLichPanel(Phim phim, boolean isNoSuatChieu) {
        boolean isPhimScreeening = phim.getTrangThai();
        String txt = "Phim đã ngừng chiếu.";
        if( isPhimScreeening && isNoSuatChieu) {
            txt = "Phim chưa có lịch chiếu ngày " + lblNgayChieu.getText();
        }
        wrap(jLabel1, txt);
    }
    
    private void loadPoster(Phim p) {
        SettingIconHelper.setPosterFor(lblPoster, p.getPoster());
    }
    
    private void loadComboPhong() {
    try {
        cboPhongChieu.removeAllItems();
        phongs.clear();
        phongs = phongDao.selectAll();
        for(PhongChieu p : phongs)
        cboPhongChieu.addItem(p);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
        
    }
    
    private PhongChieu getSelectedPhong() {
        return (PhongChieu) cboPhongChieu.getSelectedItem();
    }
    
    private Phim getSelectedPhim(boolean isTuyChonCbo) {
        
        return isTuyChonCbo? (Phim) cboPhimTuyChon.getSelectedItem() :(Phim) cboPhims.getModel().getSelectedItem();
    }
    
    
    
    private void syncDateChooserWithCheckBox() {
        dcNgayBatDau.setEnabled(chkTrongKhoang.isSelected());
        dcNgayKetThuc.setEnabled(chkTrongKhoang.isSelected());
        
        cboPhongChieu.setEnabled(chkTraTheoPhongChieu.isSelected());
        
        rdoDangChieu.setEnabled(chkTheoTinhTrang.isSelected());
        rdoNgungChieu.setEnabled(chkTheoTinhTrang.isSelected());
       
    }
    
    private void getLichInMonth() {
        chkTrongKhoang.setSelected(true);
        syncDateChooserWithCheckBox();
        dcNgayBatDau.setDate(DateHelper.getBeginningOfMonth());
        dcNgayKetThuc.setDate(DateHelper.getLastDayOfMonth());
        
        btnFilterPhim.setSelected(true);btnFilterPhim.doClick(); //showCboPhimInTuyChon(btnFilterPhim.isSelected());
        Phim p = getSelectedPhim(false);
        cboPhimTuyChon.setSelectedItem(p);
        btnLoc.doClick();
        showTab(TAB_TUY_CHON);
    }
    
   
    private void setDatButtonState() {
        if(tblTraCuuTuyChon.getSelectedRow() == -1) {
            btnDatVe.setEnabled(false);
        }else {
            SuatChieu s = getSuatChieuFromSelectedRow();
//            LocalDateTime now = LocalDateTime.now();
            LocalDateTime now = LocalDateTime.of(2022, java.time.Month.APRIL, 17 ,07, 00, 50);
            btnDatVe.setEnabled(!s.isSuatDaChieu(now));
        }
//        btnDatVe.setEnabled(tblTraCuuTuyChon.getSelectedRow() != -1);
    }
    
    private SuatChieu getSuatChieuFromSelectedRow() {
        String idsc = (String)tblTraCuuTuyChon.getModel().getValueAt(index, 7);
            Time gbd = (Time)tblTraCuuTuyChon.getModel().getValueAt(index, 5);
            java.sql.Date nc = DateHelper.toDate((String)tblTraCuuTuyChon.getModel().getValueAt(index, 1), DateHelper.DDMMYYY_SLASH_FORMAT);
            System.out.println("nc = "+ nc.toString());
            return new SuatChieu(idsc, nc, gbd);
    }
    
    private void moveToDatVe() throws Exception {
        SuatChieu sc = scDao.selectById(getSuatChieuFromSelectedRow().getId());
//        if(sc.getNgayChieu().after(DateHelper.addDays(DateHelper.now(), 2))) {
//            mainF.showLongTerm(sc);
//        }else mainF.showBooking(sc);
          moveToDatVe(sc);
    }
    
    private void moveToDatVe(SuatChieu sc) {
        if(sc.getNgayChieu().after(DateHelper.addDays(DateHelper.now(), 2))) {
            mainF.showLongTerm(sc);
        }else mainF.showBooking(sc);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgTinhTrang = new javax.swing.ButtonGroup();
        pnlTraCuuLichChieu = new javax.swing.JPanel();
        lblTracuulichchieu = new javax.swing.JLabel();
        tabsTracuulichchieu = new javax.swing.JTabbedPane();
        pnlTuyChon = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTraCuuTuyChon = new javax.swing.JTable();
        buttonGradient1 = new com.org.app.customui.ButtonGradient(true);
        jPanel11 = new javax.swing.JPanel();
        lblLocThongTin = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        pnlByDayRange = new javax.swing.JLayeredPane();
        chkTrongKhoang = new javax.swing.JCheckBox();
        pnlDateOp = new javax.swing.JPanel();
        lblTuNgay = new javax.swing.JLabel();
        lblDenNgay = new javax.swing.JLabel();
        dcNgayBatDau = new com.toedter.calendar.JDateChooser();
        dcNgayKetThuc = new com.toedter.calendar.JDateChooser();
        pnlByPhong = new javax.swing.JLayeredPane();
        chkTraTheoPhongChieu = new javax.swing.JCheckBox();
        pnlPhongOp = new javax.swing.JPanel();
        lblPhongChieu = new javax.swing.JLabel();
        cboPhongChieu = new javax.swing.JComboBox<>();
        pnlByPhimState = new javax.swing.JLayeredPane();
        chkTheoTinhTrang = new javax.swing.JCheckBox();
        pnlPhimStateOp = new javax.swing.JPanel();
        rdoDangChieu = new javax.swing.JRadioButton();
        rdoNgungChieu = new javax.swing.JRadioButton();
        btnFilterPhim = new javax.swing.JToggleButton();
        pnlFilterByPhim = new javax.swing.JPanel();
        cboPhimTuyChon = new javax.swing.JComboBox<>();
        btnDatVe = new com.org.app.customui.ButtonGradient(true);
        pnlTheoPhim = new javax.swing.JPanel();
        pnlDssuatchieu = new javax.swing.JPanel();
        lblPoster = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jSeparator1 = new javax.swing.JSeparator();
        pnlDsLich = new javax.swing.JPanel();
        pnlPhimEmpty = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlLichPhim = new javax.swing.JPanel();
        lblTenNgayChieu = new javax.swing.JLabel();
        lblNgayChieu = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnTim = new com.org.app.customui.ButtonGradient();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jlabel2 = new javax.swing.JLabel();
        dcNgayChieu = new com.toedter.calendar.JDateChooser();
        lblTenphim = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        btnLichInMonth = new com.org.app.customui.ButtonGradient();
        buttonGradient3 = new com.org.app.customui.ButtonGradient();
        jSeparator3 = new javax.swing.JSeparator();
        cboPhims = new com.org.app.customui.ComboBoxSuggestion<>();
        buttonGradient4 = new com.org.app.customui.ButtonGradient();
        btnUpdateByDate = new com.org.app.customui.ButtonGradient();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTraCuuLichChieu.setBackground(new java.awt.Color(255, 255, 255));
        pnlTraCuuLichChieu.setLayout(new java.awt.BorderLayout());

        lblTracuulichchieu.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        lblTracuulichchieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTracuulichchieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_search_48px.png"))); // NOI18N
        lblTracuulichchieu.setText("Tra cứu lịch chiếu");
        pnlTraCuuLichChieu.add(lblTracuulichchieu, java.awt.BorderLayout.NORTH);

        pnlTuyChon.setBackground(new java.awt.Color(255, 255, 255));
        pnlTuyChon.setOpaque(false);

        tblTraCuuTuyChon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã suất chiếu", "Tên phim", "Phòng chiếu", "Giờ chiếu", "Trạng thái", "Ngày chiếu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTraCuuTuyChon.setFocusable(false);
        tblTraCuuTuyChon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTraCuuTuyChonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTraCuuTuyChon);
        if (tblTraCuuTuyChon.getColumnModel().getColumnCount() > 0) {
            tblTraCuuTuyChon.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        buttonGradient1.setBackground(new java.awt.Color(0, 153, 153));
        buttonGradient1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_schedule_30px.png"))); // NOI18N
        buttonGradient1.setText("Cập nhật lịch chiếu");
        buttonGradient1.setColor1(new java.awt.Color(176, 197, 207));
        buttonGradient1.setColor2(new java.awt.Color(150, 202, 218));
        buttonGradient1.setColorHover1(new java.awt.Color(51,51,51,120));
        buttonGradient1.setColorHover2(new java.awt.Color(102,102,102));
        buttonGradient1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_schedule_30px_1.png"))); // NOI18N
        buttonGradient1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient1ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel11.setForeground(new java.awt.Color(204, 204, 204));
        jPanel11.setMaximumSize(new java.awt.Dimension(218, 521));

        lblLocThongTin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblLocThongTin.setText("Lọc thông tin");

        btnLoc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLoc.setFocusable(false);
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        chkTrongKhoang.setBackground(new java.awt.Color(255, 255, 255));
        chkTrongKhoang.setText("Trong khoảng: ");
        chkTrongKhoang.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        chkTrongKhoang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTrongKhoangActionPerformed(evt);
            }
        });

        pnlDateOp.setBackground(new java.awt.Color(223, 235, 232));

        lblTuNgay.setText("Từ ngày: ");

        lblDenNgay.setText("Đến ngày: ");

        dcNgayBatDau.setDateFormatString("dd/MM/yy");

        dcNgayKetThuc.setDateFormatString("dd/MM/yy");

        javax.swing.GroupLayout pnlDateOpLayout = new javax.swing.GroupLayout(pnlDateOp);
        pnlDateOp.setLayout(pnlDateOpLayout);
        pnlDateOpLayout.setHorizontalGroup(
            pnlDateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDateOpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDenNgay)
                    .addComponent(lblTuNgay))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dcNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlDateOpLayout.setVerticalGroup(
            pnlDateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDateOpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTuNgay)
                    .addComponent(dcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(pnlDateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDenNgay, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlByDayRange.setLayer(chkTrongKhoang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlByDayRange.setLayer(pnlDateOp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlByDayRangeLayout = new javax.swing.GroupLayout(pnlByDayRange);
        pnlByDayRange.setLayout(pnlByDayRangeLayout);
        pnlByDayRangeLayout.setHorizontalGroup(
            pnlByDayRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlByDayRangeLayout.createSequentialGroup()
                .addComponent(chkTrongKhoang)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlByDayRangeLayout.createSequentialGroup()
                .addComponent(pnlDateOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlByDayRangeLayout.setVerticalGroup(
            pnlByDayRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlByDayRangeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkTrongKhoang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDateOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        chkTraTheoPhongChieu.setBackground(new java.awt.Color(255, 255, 255));
        chkTraTheoPhongChieu.setText("Tra theo phòng chiếu");
        chkTraTheoPhongChieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTraTheoPhongChieuActionPerformed(evt);
            }
        });

        pnlPhongOp.setBackground(new java.awt.Color(223, 235, 232));

        lblPhongChieu.setText("Phòng chiếu");

        javax.swing.GroupLayout pnlPhongOpLayout = new javax.swing.GroupLayout(pnlPhongOp);
        pnlPhongOp.setLayout(pnlPhongOpLayout);
        pnlPhongOpLayout.setHorizontalGroup(
            pnlPhongOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongOpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPhongOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPhongOpLayout.createSequentialGroup()
                        .addComponent(lblPhongChieu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cboPhongChieu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlPhongOpLayout.setVerticalGroup(
            pnlPhongOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhongOpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPhongChieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboPhongChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlByPhong.setLayer(chkTraTheoPhongChieu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlByPhong.setLayer(pnlPhongOp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlByPhongLayout = new javax.swing.GroupLayout(pnlByPhong);
        pnlByPhong.setLayout(pnlByPhongLayout);
        pnlByPhongLayout.setHorizontalGroup(
            pnlByPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlByPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlByPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPhongOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlByPhongLayout.createSequentialGroup()
                        .addComponent(chkTraTheoPhongChieu)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlByPhongLayout.setVerticalGroup(
            pnlByPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlByPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkTraTheoPhongChieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlPhongOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        chkTheoTinhTrang.setBackground(new java.awt.Color(255, 255, 255));
        chkTheoTinhTrang.setText("Theo tình trạng");
        chkTheoTinhTrang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTheoTinhTrangActionPerformed(evt);
            }
        });

        pnlPhimStateOp.setBackground(new java.awt.Color(223, 235, 232));

        btgTinhTrang.add(rdoDangChieu);
        rdoDangChieu.setSelected(true);
        rdoDangChieu.setText("Đã chiếu");

        btgTinhTrang.add(rdoNgungChieu);
        rdoNgungChieu.setText("Chưa chiếu");

        javax.swing.GroupLayout pnlPhimStateOpLayout = new javax.swing.GroupLayout(pnlPhimStateOp);
        pnlPhimStateOp.setLayout(pnlPhimStateOpLayout);
        pnlPhimStateOpLayout.setHorizontalGroup(
            pnlPhimStateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimStateOpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPhimStateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoDangChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNgungChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPhimStateOpLayout.setVerticalGroup(
            pnlPhimStateOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimStateOpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rdoDangChieu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rdoNgungChieu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlByPhimState.setLayer(chkTheoTinhTrang, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlByPhimState.setLayer(pnlPhimStateOp, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlByPhimStateLayout = new javax.swing.GroupLayout(pnlByPhimState);
        pnlByPhimState.setLayout(pnlByPhimStateLayout);
        pnlByPhimStateLayout.setHorizontalGroup(
            pnlByPhimStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlByPhimStateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlByPhimStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPhimStateOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlByPhimStateLayout.createSequentialGroup()
                        .addComponent(chkTheoTinhTrang)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlByPhimStateLayout.setVerticalGroup(
            pnlByPhimStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlByPhimStateLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkTheoTinhTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlPhimStateOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnFilterPhim.setBackground(new java.awt.Color(208, 224, 229));
        btnFilterPhim.setSelected(true);
        btnFilterPhim.setBorderPainted(false);
        btnFilterPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFilterPhim.setFocusable(false);
        btnFilterPhim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilterPhimActionPerformed(evt);
            }
        });

        cboPhimTuyChon.setMaximumSize(new java.awt.Dimension(174, 22));
        cboPhimTuyChon.setMinimumSize(new java.awt.Dimension(174, 22));
        cboPhimTuyChon.setPreferredSize(new java.awt.Dimension(174, 22));
        cboPhimTuyChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhimTuyChonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFilterByPhimLayout = new javax.swing.GroupLayout(pnlFilterByPhim);
        pnlFilterByPhim.setLayout(pnlFilterByPhimLayout);
        pnlFilterByPhimLayout.setHorizontalGroup(
            pnlFilterByPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterByPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPhimTuyChon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFilterByPhimLayout.setVerticalGroup(
            pnlFilterByPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFilterByPhimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboPhimTuyChon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlByPhong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlByPhimState, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlByDayRange, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnFilterPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlFilterByPhim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblLocThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(lblLocThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFilterPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlFilterByPhim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(pnlByDayRange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlByPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlByPhimState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDatVe.setBackground(new java.awt.Color(255, 255, 255));
        btnDatVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_ticket_26px.png"))); // NOI18N
        btnDatVe.setText("Đặt vé");
        btnDatVe.setColor1(new java.awt.Color(247, 214, 122));
        btnDatVe.setColor2(new java.awt.Color(234, 203, 153));
        btnDatVe.setColorHover1(new java.awt.Color(255, 255, 255));
        btnDatVe.setColorHover2(null);
        btnDatVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTuyChonLayout = new javax.swing.GroupLayout(pnlTuyChon);
        pnlTuyChon.setLayout(pnlTuyChonLayout);
        pnlTuyChonLayout.setHorizontalGroup(
            pnlTuyChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTuyChonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTuyChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTuyChonLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTuyChonLayout.setVerticalGroup(
            pnlTuyChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTuyChonLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTuyChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTuyChonLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTuyChonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonGradient1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDatVe, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsTracuulichchieu.addTab("Tra cứu tùy chọn", pnlTuyChon);

        pnlTheoPhim.setOpaque(false);

        pnlDssuatchieu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDssuatchieu.setOpaque(false);

        lblPoster.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        pnlDsLich.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlDsLich.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_planner_24px.png"))); // NOI18N

        javax.swing.GroupLayout pnlPhimEmptyLayout = new javax.swing.GroupLayout(pnlPhimEmpty);
        pnlPhimEmpty.setLayout(pnlPhimEmptyLayout);
        pnlPhimEmptyLayout.setHorizontalGroup(
            pnlPhimEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimEmptyLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
        );
        pnlPhimEmptyLayout.setVerticalGroup(
            pnlPhimEmptyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPhimEmptyLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pnlDsLich.add(pnlPhimEmpty, "card3");

        pnlLichPhim.setLayout(new java.awt.BorderLayout());
        pnlDsLich.add(pnlLichPhim, "card2");

        lblTenNgayChieu.setBackground(new java.awt.Color(95,167,109,150));
        lblTenNgayChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        lblTenNgayChieu.setForeground(new java.awt.Color(255, 255, 255));
        lblTenNgayChieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTenNgayChieu.setText("Suất chiếu ngày");
        lblTenNgayChieu.setOpaque(true);

        lblNgayChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        lblNgayChieu.setForeground(new java.awt.Color(51, 51, 51));
        lblNgayChieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNgayChieu.setFocusable(false);

        javax.swing.GroupLayout pnlDssuatchieuLayout = new javax.swing.GroupLayout(pnlDssuatchieu);
        pnlDssuatchieu.setLayout(pnlDssuatchieuLayout);
        pnlDssuatchieuLayout.setHorizontalGroup(
            pnlDssuatchieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDssuatchieuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblPoster, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDsLich, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlDssuatchieuLayout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(lblTenNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDssuatchieuLayout.setVerticalGroup(
            pnlDssuatchieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDssuatchieuLayout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDssuatchieuLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlDssuatchieuLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(lblPoster, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDssuatchieuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDssuatchieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgayChieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDsLich, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), new java.awt.Color(204, 204, 204)));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setMaximumSize(new java.awt.Dimension(662, 30));
        jPanel2.setPreferredSize(new java.awt.Dimension(662, 40));

        btnTim.setText("Tìm");
        btnTim.setColor1(new java.awt.Color(160, 189, 187));
        btnTim.setColor2(new java.awt.Color(181, 208, 188));
        btnTim.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        btnTim.setMaximumSize(new java.awt.Dimension(127, 25));
        btnTim.setMinimumSize(new java.awt.Dimension(127, 25));
        btnTim.setPreferredSize(new java.awt.Dimension(127, 25));
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jLayeredPane1.setMaximumSize(new java.awt.Dimension(652, 90));
        jLayeredPane1.setOpaque(true);
        jLayeredPane1.setPreferredSize(new java.awt.Dimension(652, 90));

        jlabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jlabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_calendar_20px.png"))); // NOI18N
        jlabel2.setText("Ngày chiếu");

        dcNgayChieu.setToolTipText("Lọc lịch trong vòng 1 năm");
        dcNgayChieu.setDateFormatString("dd/MM/yyyy");
        dcNgayChieu.setMaxSelectableDate(new java.util.Date(1672509663000L));
        dcNgayChieu.setMaximumSize(null);
        dcNgayChieu.setMinSelectableDate(new java.util.Date(1325354463000L));
        dcNgayChieu.setPreferredSize(new java.awt.Dimension(30, 22));

        lblTenphim.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        lblTenphim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_movie_20px.png"))); // NOI18N
        lblTenphim.setText("Tên phim: ");

        jPanel1.setOpaque(false);

        btnLichInMonth.setText("Lịch chiếu của phim trong tháng");
        btnLichInMonth.setColor1(new java.awt.Color(201, 211, 169));
        btnLichInMonth.setColor2(new java.awt.Color(139, 178, 119));
        btnLichInMonth.setFont(new java.awt.Font("Leelawadee UI", 0, 11)); // NOI18N
        btnLichInMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLichInMonthActionPerformed(evt);
            }
        });

        buttonGradient3.setText("Lịch chiếu chi tiết");
        buttonGradient3.setColor1(new java.awt.Color(201, 211, 169));
        buttonGradient3.setColor2(new java.awt.Color(139, 178, 119));
        buttonGradient3.setColorHover1(null);
        buttonGradient3.setColorHover2(new java.awt.Color(255, 255, 255));
        buttonGradient3.setFont(new java.awt.Font("Leelawadee UI", 0, 11)); // NOI18N
        buttonGradient3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLichInMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnLichInMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(buttonGradient3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnLichInMonth, buttonGradient3});

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cboPhims.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhimsActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jlabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(dcNgayChieu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lblTenphim, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jSeparator2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jSeparator3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cboPhims, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenphim, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(jlabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dcNgayChieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboPhims, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(208, 208, 208)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(271, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenphim)
                                    .addComponent(cboPhims, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jlabel2)
                                    .addComponent(dcNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(42, 42, 42)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(46, Short.MAX_VALUE)))
        );

        jPanel3.add(jLayeredPane1, java.awt.BorderLayout.LINE_START);

        buttonGradient4.setBackground(new java.awt.Color(211, 225, 184));
        buttonGradient4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_documentary_30px.png"))); // NOI18N
        buttonGradient4.setText("Cập nhật trạng thái chiếu");
        buttonGradient4.setColor1(new java.awt.Color(226, 226, 176));
        buttonGradient4.setColor2(new java.awt.Color(217, 219, 163));
        buttonGradient4.setColorHover1(new java.awt.Color(230, 230, 230));
        buttonGradient4.setColorHover2(new java.awt.Color(204, 204, 204));
        buttonGradient4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_documentary_30px.png"))); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        btnUpdateByDate.setBackground(new java.awt.Color(0, 153, 153));
        btnUpdateByDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_schedule_30px.png"))); // NOI18N
        btnUpdateByDate.setText("Cập nhật lịch chiếu trong ngày");
        btnUpdateByDate.setColor1(new java.awt.Color(176, 215, 199));
        btnUpdateByDate.setColor2(new java.awt.Color(155, 205, 186));
        btnUpdateByDate.setColorHover1(new java.awt.Color(51,51,51,120));
        btnUpdateByDate.setColorHover2(new java.awt.Color(102,102,102));
        btnUpdateByDate.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_schedule_30px_1.png"))); // NOI18N
        btnUpdateByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateByDateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTheoPhimLayout = new javax.swing.GroupLayout(pnlTheoPhim);
        pnlTheoPhim.setLayout(pnlTheoPhimLayout);
        pnlTheoPhimLayout.setHorizontalGroup(
            pnlTheoPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheoPhimLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(pnlTheoPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(pnlDssuatchieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTheoPhimLayout.createSequentialGroup()
                        .addComponent(btnUpdateByDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnlTheoPhimLayout.setVerticalGroup(
            pnlTheoPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTheoPhimLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDssuatchieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTheoPhimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateByDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsTracuulichchieu.addTab("Tra cứu theo phim", pnlTheoPhim);

        pnlTraCuuLichChieu.add(tabsTracuulichchieu, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTraCuuLichChieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTraCuuLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboPhimTuyChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhimTuyChonActionPerformed
        if(cboPhimTuyChon.getSelectedIndex() == -1) return;
        showTable();
    }//GEN-LAST:event_cboPhimTuyChonActionPerformed

    private void btnFilterPhimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilterPhimActionPerformed
        System.out.println("is selected = "+ btnFilterPhim.isSelected());
        showCboPhimInTuyChon(btnFilterPhim.isSelected());
    }//GEN-LAST:event_btnFilterPhimActionPerformed

    private void buttonGradient3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient3ActionPerformed
        btnFilterPhim.setSelected(true);
        cboPhimTuyChon.setSelectedItem(getSelectedPhim(false));
        btnFilterPhim.doClick();
        showTab(TAB_TUY_CHON);
    }//GEN-LAST:event_buttonGradient3ActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
       showLichOfPhim();
    }//GEN-LAST:event_btnTimActionPerformed

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
       this.mainF.showCard(QLPhimFrame.CARD_NAME_MAIN);
       ((QLPhimFrame)mainF.getCurrentFrame()).setSelectedPhim((Phim)cboPhims.getSelectedItem());
    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void chkTheoTinhTrangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTheoTinhTrangActionPerformed
//       if( chkTheoTinhTrang.isSelected() && chkTrongKhoang.isSelected()) chkTrongKhoang.setSelected(false);
        syncDateChooserWithCheckBox();
    }//GEN-LAST:event_chkTheoTinhTrangActionPerformed

    private void chkTraTheoPhongChieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTraTheoPhongChieuActionPerformed
        syncDateChooserWithCheckBox();
//        showTable();
    }//GEN-LAST:event_chkTraTheoPhongChieuActionPerformed

    private void chkTrongKhoangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTrongKhoangActionPerformed
//        if( chkTrongKhoang.isSelected() && chkTheoTinhTrang.isSelected()) chkTheoTinhTrang.setSelected(false);
        syncDateChooserWithCheckBox();
//        showTable();
    }//GEN-LAST:event_chkTrongKhoangActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        showTable();
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnLichInMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLichInMonthActionPerformed
        getLichInMonth();
    }//GEN-LAST:event_btnLichInMonthActionPerformed

    private void buttonGradient1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient1ActionPerformed
        index = tblTraCuuTuyChon.getSelectedRow(); 
        mainF.showCard(QLSuatChieuFrame.CARD_NAME_MAIN);
        QLSuatChieuFrame ql = (QLSuatChieuFrame) mainF.getCurrentFrame();
        ql.setSelecteSuat(getSuatChieuFromSelectedRow());
//        ((QLSuatChieuFrame)mainF.getFrame(QLSuatChieuFrame.CARD_NAME_MAIN)).;
        //mainF.showCard(QLSuatChieuFrame.CARD_NAME_MAIN);
    }//GEN-LAST:event_buttonGradient1ActionPerformed

    private void tblTraCuuTuyChonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTraCuuTuyChonMouseClicked
        index = tblTraCuuTuyChon.getSelectedRow();
        setDatButtonState();
        if(SwingUtilities.isRightMouseButton(evt)) {
            SuatChieu s = getSuatChieuFromSelectedRow();
            LocalDateTime now = LocalDateTime.of(2022, java.time.Month.APRIL, 17 ,07, 00, 50);
            MessageHelper.message(this,s.isSuatDaChieu(now)? "da": "chua");
        }
    }//GEN-LAST:event_tblTraCuuTuyChonMouseClicked

    private void cboPhimsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhimsActionPerformed
        index_cboPhim = cboPhims.getSelectedIndex();
        showSelectePhim();
    }//GEN-LAST:event_cboPhimsActionPerformed

    private void btnDatVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVeActionPerformed
    try {
        setDatButtonState();
        moveToDatVe();
    } catch (Exception ex) {
        ex.printStackTrace();
    }
       
    }//GEN-LAST:event_btnDatVeActionPerformed

    private void btnUpdateByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateByDateActionPerformed
        Date date = dcNgayChieu.getDate();
        mainF.showSuatNgay(date);
    }//GEN-LAST:event_btnUpdateByDateActionPerformed

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
            java.util.logging.Logger.getLogger(TraCuuLichChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TraCuuLichChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TraCuuLichChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TraCuuLichChieuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TraCuuLichChieuFrame tc = new TraCuuLichChieuFrame();
                tc.renderFrame();tc.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTinhTrang;
    private com.org.app.customui.ButtonGradient btnDatVe;
    private javax.swing.JToggleButton btnFilterPhim;
    private com.org.app.customui.ButtonGradient btnLichInMonth;
    private javax.swing.JButton btnLoc;
    private com.org.app.customui.ButtonGradient btnTim;
    private com.org.app.customui.ButtonGradient btnUpdateByDate;
    private com.org.app.customui.ButtonGradient buttonGradient1;
    private com.org.app.customui.ButtonGradient buttonGradient3;
    private com.org.app.customui.ButtonGradient buttonGradient4;
    private javax.swing.JComboBox<String> cboPhimTuyChon;
    private com.org.app.customui.ComboBoxSuggestion<Phim> cboPhims;
    private javax.swing.JComboBox<PhongChieu> cboPhongChieu;
    private javax.swing.JCheckBox chkTheoTinhTrang;
    private javax.swing.JCheckBox chkTraTheoPhongChieu;
    private javax.swing.JCheckBox chkTrongKhoang;
    private com.toedter.calendar.JDateChooser dcNgayBatDau;
    private com.toedter.calendar.JDateChooser dcNgayChieu;
    private com.toedter.calendar.JDateChooser dcNgayKetThuc;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel jlabel2;
    private javax.swing.JLabel lblDenNgay;
    private javax.swing.JLabel lblLocThongTin;
    private javax.swing.JLabel lblNgayChieu;
    private javax.swing.JLabel lblPhongChieu;
    private javax.swing.JLabel lblPoster;
    private javax.swing.JLabel lblTenNgayChieu;
    private javax.swing.JLabel lblTenphim;
    private javax.swing.JLabel lblTracuulichchieu;
    private javax.swing.JLabel lblTuNgay;
    private javax.swing.JLayeredPane pnlByDayRange;
    private javax.swing.JLayeredPane pnlByPhimState;
    private javax.swing.JLayeredPane pnlByPhong;
    private javax.swing.JPanel pnlDateOp;
    private javax.swing.JPanel pnlDsLich;
    private javax.swing.JPanel pnlDssuatchieu;
    private javax.swing.JPanel pnlFilterByPhim;
    private javax.swing.JPanel pnlLichPhim;
    private javax.swing.JPanel pnlPhimEmpty;
    private javax.swing.JPanel pnlPhimStateOp;
    private javax.swing.JPanel pnlPhongOp;
    private javax.swing.JPanel pnlTheoPhim;
    private javax.swing.JPanel pnlTraCuuLichChieu;
    private javax.swing.JPanel pnlTuyChon;
    private javax.swing.JRadioButton rdoDangChieu;
    private javax.swing.JRadioButton rdoNgungChieu;
    private javax.swing.JTabbedPane tabsTracuulichchieu;
    private javax.swing.JTable tblTraCuuTuyChon;
    // End of variables declaration//GEN-END:variables

   

}

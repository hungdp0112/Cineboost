 
package com.org.app.ui.banhang;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.DoAnChiTietDao;
import com.org.app.controller.DonThanhToanDao;
import com.org.app.controller.VeDatDao;
import com.org.app.entity.DoAnChiTiet;
import com.org.app.entity.DonThanhToan;
import com.org.app.entity.KichCoDoAn;
import com.org.app.entity.NhanVien;
import com.org.app.entity.Phim;
import com.org.app.entity.SuatChieu;
import com.org.app.entity.ThanhVien;
import com.org.app.entity.VeDat;
import com.org.app.helper.Authenticator;
import com.org.app.helper.DateHelper;
import com.org.app.helper.InputValidlHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.ui.component.DADatList;
import com.org.app.ui.component.DetailVeFromThanhToan;
import com.org.app.ui.component.GiamGiaDieuKien;
import com.org.app.ui.component.MenuDoAnPanel;
import com.org.app.ui.component.VeDatList;
import com.org.app.ui.main.MainFrame;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.DinhDangTienTe;
import com.org.app.util.JLabelWrapper;
import com.org.app.util.LabelWrapperInterface;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ThanhToanFrame extends javax.swing.JFrame implements SubFrame<ThanhToanFrame>,SubPanelCreatorInterfaces<SubFrame>, LabelWrapperInterface{
    SubPanelCreator<ThanhToanFrame> subPanel;
    DatMenuFrame menuFrame;
    MenuDoAnPanel menuDoAn;
    DatVeFrame veFrame;
    VeDatList veList;
    VeDatDao veDao;
    DoAnChiTietDao dactDao;
    DADatList doAnList;
    DonThanhToanDao donttDao;
    ThanhToanFrame self;
    ThanhVienCapNhat addTVDialog;
    DetailVeFromThanhToan detailDialog = new DetailVeFromThanhToan();
    ThanhVien tv = null;
    
    public static boolean isNewDon ;
    public static boolean isDoAnOnly = false;
    public static final Integer soluongDon = 5;
    public static boolean isBoth = false;
    public static final int VE_ONLY = 0;
    public static final int DO_AN_ONLY = 1;
    public static final int BOTH = 2;
    
    public enum TYPE {
        VE_ONLY(0),
        DO_AN_ONLY(1),
        BOTH(2);
        private int index;
        
        private TYPE(Integer index) {
            this.index = index;
        }
        
        private Integer getType(){return index;}
    }
    
    
    public ThanhToanFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        this.getContentPane().setBackground(java.awt.Color.white);
        subPanel = createSubPanel(this);
        menuDoAn = new MenuDoAnPanel(this);
        menuDoAn.setVisible(true);
        veDao = new VeDatDao();       
        donttDao = new DonThanhToanDao();
        dactDao = new DoAnChiTietDao();
        doAnList = new DADatList();
        addTVDialog = new ThanhVienCapNhat(this);
        
        ///test
//        Authenticator.USER = new NhanVien("QL00006", "Trần Vân", DateHelper.toDate("23/02/1998"), true, "0329436123", "vantht@gmail.com", true, "user1.png", "vantht", "P12345");
        setUp();
//        pack();
    }
    
    public boolean isDoAnDatListEmpty() {
        return doAnList == null || doAnList.getMp() == null || doAnList.getSoLuong() == 0;
    }
  
    public void menuThanhToanOf(ThanhToanFrame.TYPE type, Object frame) {
        clearPnlTongTien();
        System.out.println("both = " + isBoth);
        switch (type) {
            case VE_ONLY :{
                veFrame = ((DatVeFrame) frame);
                isDoAnOnly = false;
//                renderFrameForVe();
                break;
            }
            case DO_AN_ONLY : {
                isDoAnOnly = true;
                renderFrameForDoAn();
                break;
            }
            case BOTH : {
                veFrame = (DatVeFrame) frame;
                isBoth = true;
                isDoAnOnly = false;
                pnlDonVe.setVisible(true);
                renderFrame();
            }
        }
    }
    

    public ThanhToanFrame(DatVeFrame datVeFrame) {
        this();
        
        veFrame = datVeFrame;
        menuFrame = veFrame.menuFrame;
        
        renderFrame();
    }
    
    public ThanhToanFrame(DatMenuFrame menuF) {
        this();
        menuFrame = menuF;
        isDoAnOnly = true;
        
//        renderFrame();
    }
    
    public boolean isDatThemVe() {
        return isDoAnOnly && doAnList.getSoLuong() > 0;
    }
    
    // Truyền cả 2
    public ThanhToanFrame(KichCoDoAn kcda) {
        this();; // Thêm
        
    }
    
    private void setUp(){
        System.out.println("finish thanhtoan setup");
        pnlChonDoAn.add(menuDoAn, BorderLayout.CENTER); 
        pnlDoAn.setBackground(ColorAndIconBank.REP_COLOR_PART );
        pnlDonVe.setBackground(ColorAndIconBank.REP_COLOR_PART );
        pnlTongTien.setBackground(ColorAndIconBank.REP_COLOR_PART);
        pnlThanhTien.setBackground(new Color(255,255,255,0));
        //set foregournd cho lbl in pnlDonVe
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
    
    public void renderFrame() {
        System.out.println("render Frame method");
        if(isDoAnOnly) {
            renderFrameForDoAn();
            
        }else if(isBoth) {
            renderBoth();
            detailDialog.setList(veList);
        }
        else {
            renderFrameForVe();
            txtTienKD.setEnabled(true);
            detailDialog.setList(veList);
        }
        
    }
    public void renderBoth() {
        veList = veFrame.veSelectedList;
        setDetailPnlVe();
        updateDADatList();
    }
    
    public void renderFrameForVe() {
        pnlDonVe.setVisible(true);
        System.out.println("render ve only");
        veList = veFrame.veSelectedList;
        System.out.println("ve list from thanh toan = "+veList);
        clearStatePnlDoAn();
        setDetailPnlVe();
        updatePnlTongTien();
//        setDetailPnlVe();
System.out.println("don setting");
        pnlDonVe.setVisible(true);
    }
     
    private void setDetailPnlVe() {
        Phim phim = veList.getPhim();
        SuatChieu sc = veList.getSc();
        wrap(lblTenPhim, phim.getTen());
       
        setLabelSuatChieuInDon(sc);
        setGia();
    }
    
    private void setGia() {
        Double[] totalVeArray = veList.getDetailPrice();
        int nl = veList.getLuongVeNL();
        int te = veList.getLuongVeTE();
        lblVeTreEm.setText(te + " x " + DinhDangTienTe.chuyenThanhTienVN(totalVeArray[0]));
        lblVeNguoiLon.setText(nl + " x " + DinhDangTienTe.chuyenThanhTienVN(totalVeArray[1]));
        lblPhuThu.setText(DinhDangTienTe.chuyenThanhTienVN(veList.getTongPhuThu()));
        lblTamTinhVe.setText(DinhDangTienTe.chuyenThanhTienVN(totalVeArray[2]));
        
    }
    
    private void setLabelSuatChieuInDon(SuatChieu sc) {
        String date = DateHelper.toString(sc.getNgayChieu());
        String gbd = TimeHelper.toStringWithHourMintues(sc.getGioBatDau());
        lblSuatChieu.setText(gbd+" - "+date);
    }
    
    public void clearStatePnlDoAn() {
        JLabel[] lbls = {lblTongTienTA, lblTongTienDU, lblTamTinhDA};
//        if(isPlainVe) {
            for (int i = 0; i < lbls.length; i++) {
               lbls[i].setText(DinhDangTienTe.chuyenThanhTienVN(0));
            }
//        }
    }
    
    private void setStatePnlVe(boolean isPlainDA) {
        
        JLabel[] lbls = {lblTenPhim, lblSuatChieu, lblVeTreEm, lblVeNguoiLon, lblTamTinhVe};
        if(isPlainDA) {
            for(JLabel lbl : lbls) {
                lbl.setText("");
            }
        }
        pnlDonVe.setVisible(!isPlainDA);
    }
    
    public void clearPnlDoAn_isDA(){
        clearStatePnlDoAn();
        if (isDoAnOnly) {
            clearPnlTongTien();
//            menuDoAn.clear();
//            menuDoAn.datList.clear();
        }else {
            isBoth = false;
            updatePnlTongTien();
        }     
    }
    
    public void updatePnlTongTien() {
        getMaTV();
        Double ve = 0.0;
        Double da = 0.0;
        Double s =  0.0;
        if (!isDoAnOnly) {
            ve = DinhDangTienTe.chuyenChuoiThanhDouble(lblTamTinhVe.getText());
            da = lblTamTinhDA.getText().equals("0")? 0.0 : DinhDangTienTe.chuyenChuoiThanhDouble(lblTamTinhDA.getText());
        }else if (isDoAnOnly) {
//            da = Double.parseDouble(lblTamTinhDA.getText());
            da = DinhDangTienTe.chuyenChuoiThanhDouble(lblTamTinhDA.getText());
        }     
        s = ve + da;
        lblTongTien.setText(DinhDangTienTe.chuyenThanhTienVN(s));
        updateThanhTien();
    }
      
    public void clearPnlTongTien() {
        txtIDThanhVien.setText("");
        txtTienKD.setText("");
        lblGiamGia.setText("0%");
        
        lblTongTien.setText(DinhDangTienTe.chuyenThanhTienVN(0));
        lblThanhTien.setText(DinhDangTienTe.chuyenThanhTienVN(0));
        lblTienThoiLai.setText(DinhDangTienTe.chuyenThanhTienVN(0));
    }
    
// DO AN
//-------------------------------------------------------------------------------------         
    public void renderFrameForDoAn() {
        System.out.println("render frame do an");
        doAnList = menuDoAn.getDADatList();
        setStatePnlVe(true);
        chonMonListener();
        clearStatePnlDoAn();
//        updatePnlTongTien();
//        updatePnlTongTienDA();
    }
    
    
    public void updateDADatList(){
        doAnList = menuDoAn.getDADatList();
        if(!isDoAnOnly) isBoth = true;
        chonMonListener();
        updateTongTienDA();
        updatePnlTongTien();
    }
    
    private void updateTongTienDA(){
        Double tongTienTA = doAnList.getTongTienTA();
        Double tongTienDU = doAnList.getTongTienDU();
        Double tongTienDA = tongTienTA + tongTienDU;
        lblTongTienTA.setText(DinhDangTienTe.chuyenThanhTienVN(tongTienTA));
        lblTongTienDU.setText(DinhDangTienTe.chuyenThanhTienVN(tongTienDU));
        lblTamTinhDA.setText(DinhDangTienTe.chuyenThanhTienVN(tongTienDA));
    }
    
    private List<DoAnChiTiet> getToDACT() {
        return doAnList.toDACT();
    }
    
//-------------------------------------------------------------------------------------
    public void reset() {
        if(!isDoAnOnly) {
            veFrame = null;
            veList.clear();
        }
    }
    
    public void showVeDat() {
        List<VeDat> vedal;
        try {
            vedal = veDao.selectAll();
        System.out.println("vdl = "+ vedal.size());
        } catch (Exception ex) {
            ex.printStackTrace();
       }
    }
    
    private void move() {
        if(isDoAnOnly) {
            System.out.println("is do an only");
           menuFrame.showCard(DatMenuFrame.GuiOf.HOME);
        }else {
            veFrame.isEdit = true;
            menuFrame.showCard(DatMenuFrame.GuiOf.DAT_VE);
        }
    }
    private List<VeDat> getToVeDat() {
        return veList.toVeDat();
    }
    
    private void updateThanhTien() {
        lblGiamGia.setText(String.format("%d%%", getMaGiam()));
//        Double tamTinh =  Double.parseDouble(lblTongTien.getText());
        Double tamTinh =  DinhDangTienTe.chuyenChuoiThanhDouble(lblTongTien.getText());
        int percent = Integer.parseInt(lblGiamGia.getText().replace("%", ""));
        
        long tt = Math.round((tamTinh * ((double)(100-percent) * 0.01)) * 1000);
        Double round =  tt/1000.0;
        lblThanhTien.setText(DinhDangTienTe.chuyenThanhTienVN(round));
    }
    
    private void addVeToDonVe(String id_donve) throws Exception {
        List<VeDat> list = getToVeDat();
        for (VeDat vd : list) {
            vd.setDonVe(id_donve);
            veDao.insert(vd);
        }
    }
//--------------------------------------------------------------------------------    
    private void addDAToDonDA(String id_donda) throws Exception {
        List<DoAnChiTiet> list = getToDACT();
        for (DoAnChiTiet dact : list) {
            dact.setDonDA(id_donda);
            dactDao.insert(dact);
        }
    }
//-------------------------------------------------------------------------------- 
    
    private void thanhToan() {
        if(!isBoth && !isDoAnOnly) {
            try {
//                DonThanhToan don = donttDao.addDonThanhToan(VE_ONLY, getMaTV(), Authenticator.USER.getId());
                DonThanhToan don = donttDao.addDonThanhToan(VE_ONLY, getMaTV(), Authenticator.USER.getId());
                System.out.println("don = " + don);
                addVeToDonVe(don.getDonVe());
                if(isGiamGia()) {
                    donttDao.updateTongTien(don.getId(), DinhDangTienTe.chuyenChuoiThanhDouble(lblThanhTien.getText()));
                }
                else {
                    donttDao.updateTongTien(don.getId());
                }
                MessageHelper.message(this, "Thanh toán thành công");
                menuFrame.showCard(DatMenuFrame.GuiOf.HOME);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (isDoAnOnly) {
            thanhToanDoAn();
        }else {
            thanhToanDonTong();
        }
        resetLoaiDon();
        setDonMoi();
    }
    
    private void thanhToanDoAn(){    
            try {
                System.out.println("List da : "+ doAnList);
//                DonThanhToan don = donttDao.addDonThanhToan(VE_ONLY, getMaTV(), Authenticator.USER.getId());
                DonThanhToan don = donttDao.addDonThanhToan(DO_AN_ONLY, getMaTV(), Authenticator.USER.getId());
                System.out.println("don = " + don);
//                addVeToDonVe(don.getDonVe());
                addDAToDonDA(don.getDonDoAn());
                if(isGiamGia()) {
                    donttDao.updateTongTien(don.getId(), DinhDangTienTe.chuyenChuoiThanhDouble(lblThanhTien.getText()));
                }
                else {
                    donttDao.updateTongTien(don.getId());
                }
                MessageHelper.message(this, "Đặt thành công");
                menuFrame.showCard(DatMenuFrame.GuiOf.HOME);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    
    private void thanhToanDonTong() {
        System.out.println("isBoth = ");   
        try {
            DonThanhToan don = donttDao.addDonThanhToan(BOTH, getMaTV(), Authenticator.USER.getId());
            System.out.println("don "+ don);
            addVeToDonVe(don.getDonVe());
            addDAToDonDA(don.getDonDoAn());
            if(isGiamGia()) {
                    donttDao.updateTongTien(don.getId(), DinhDangTienTe.chuyenChuoiThanhDouble(lblThanhTien.getText()));
                }
                else {
                    donttDao.updateTongTien(don.getId());
                }MessageHelper.message(this, "Đặt thành công");
                menuFrame.showCard(DatMenuFrame.GuiOf.HOME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    private void setDonMoi() {
        clearStatePnlDoAn();
        menuDoAn.clear();
        clearPnlTongTien();
    }
    private boolean isGiamGia() {
        return getMaGiam() > 0;
    }
   
    
    private String getMaTV() {     
        if(txtIDThanhVien.getText().isEmpty()) {
            return null;
        }
        
        return tv.getID();
    }
    
    private int getMaGiam() {
        if (!isDoAnOnly) {
            int slVe = veList.getSoLuong();
            int slDA = doAnList.getTongSoLuong();
            System.out.println("slDa "+ slDA);
            return GiamGiaDieuKien.getGiamGia(slVe, slDA);
//        return GiamGiaDieuKien.getGiamGia(slVe, slDA);
        }else {
            int slVe = 0;
            int slDA = doAnList.getTongSoLuong();
            
            return GiamGiaDieuKien.getGiamGia(slVe, slDA);
        }

    }
    private void setGiamGia() {
        lblGiamGia.setText(String.format("%d%%", getMaGiam()));
    }
    public void chonMonListener(){
        btnThanhToan.setEnabled(false);
        txtTienKD.setEnabled(doAnList.getSoLuong() > 0);
    }
    
    private boolean checkTienKD(){
        String tienKD = txtTienKD.getText();
        if (!tienKD.isBlank()) {
            return false;
        }
        
        return true;
    }
    
    private void tienThoiLai(){
        double tienThoi = 0;
        double tienKD = DinhDangTienTe.chuyenChuoiThanhDouble(txtTienKD.getText());
        double thanhTien = DinhDangTienTe.chuyenChuoiThanhDouble(lblThanhTien.getText());
        if (tienKD >= thanhTien) {
            tienThoi = tienKD - thanhTien;
        }
        lblTienThoiLai.setText(DinhDangTienTe.chuyenThanhTienVN(tienThoi));
    }
    private void showDialog(){
        
        addTVDialog.setVisible(true);
        tv =  addTVDialog.getThanhVien();
        setTVTextId();
    }
    
    private void setTVTextId() {
        txtIDThanhVien.setText(tv == null? "" : tv.getMakh());
    }
    
    public void resetLoaiDon() {
        isDoAnOnly = true;
        isBoth = false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        pnlContent = new javax.swing.JPanel();
        gradientPanel1 = new com.org.app.customui.GradientPanel();
        pnlThanhTien = new javax.swing.JPanel();
        pnlButton = new javax.swing.JPanel();
        btnThanhVien = new com.org.app.customui.ButtonGradient();
        btnThanhToan = new com.org.app.customui.ButtonGradient();
        pnlDetail = new javax.swing.JPanel();
        pnlDonVe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTenPhim = new javax.swing.JLabel();
        lblSuatChieu = new javax.swing.JLabel();
        lblVeNguoiLon = new javax.swing.JLabel();
        lblVeTreEm = new javax.swing.JLabel();
        lblTamTinhVe = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPhuThu = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btnDetailVe = new javax.swing.JButton();
        pnlDoAn = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTongTienTA = new javax.swing.JLabel();
        lblTongTienDU = new javax.swing.JLabel();
        lblTamTinhDA = new javax.swing.JLabel();
        pnlTongTien = new javax.swing.JPanel();
        pnlMaKH = new javax.swing.JPanel();
        txtIDThanhVien = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlTamTinh = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        pnlGiamGia = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblGiamGia = new javax.swing.JLabel();
        pnlTong = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lblThanhTien = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTienKD = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        lblTienThoiLai = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlChonDoAn = new javax.swing.JPanel();
        btnQuayLai = new com.org.app.customui.ButtonGradient();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContent.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlContent.setPreferredSize(new java.awt.Dimension(700, 650));
        pnlContent.setLayout(new java.awt.BorderLayout());

        pnlThanhTien.setBackground(new java.awt.Color(255, 255, 255));
        pnlThanhTien.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thành Tiền", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Leelawadee UI", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlThanhTien.setMaximumSize(new java.awt.Dimension(300, 600));
        pnlThanhTien.setOpaque(false);
        pnlThanhTien.setPreferredSize(new java.awt.Dimension(300, 600));
        pnlThanhTien.setLayout(new java.awt.BorderLayout());

        pnlButton.setMaximumSize(new java.awt.Dimension(278, 39));
        pnlButton.setOpaque(false);
        pnlButton.setPreferredSize(new java.awt.Dimension(278, 39));

        btnThanhVien.setText("Thành viên");
        btnThanhVien.setColor1(new java.awt.Color(242, 234, 158));
        btnThanhVien.setColor2(new java.awt.Color(238, 241, 177));
        btnThanhVien.setColorHover1(new java.awt.Color(243, 231, 180));
        btnThanhVien.setColorHover2(new java.awt.Color(246, 249, 187));
        btnThanhVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhVienActionPerformed(evt);
            }
        });

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setColor1(new java.awt.Color(245, 244, 244));
        btnThanhToan.setColor2(new java.awt.Color(245, 244, 244));
        btnThanhToan.setColorHover1(new java.awt.Color(153, 255, 51));
        btnThanhToan.setColorHover2(new java.awt.Color(156, 236, 183));
        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlButtonLayout = new javax.swing.GroupLayout(pnlButton);
        pnlButton.setLayout(pnlButtonLayout);
        pnlButtonLayout.setHorizontalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlButtonLayout.setVerticalGroup(
            pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlButtonLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlThanhTien.add(pnlButton, java.awt.BorderLayout.PAGE_END);

        pnlDetail.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetail.setPreferredSize(new java.awt.Dimension(283, 525));

        pnlDonVe.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn vé", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(102, 0, 51))); // NOI18N
        pnlDonVe.setMaximumSize(new java.awt.Dimension(296, 200));
        pnlDonVe.setMinimumSize(new java.awt.Dimension(296, 200));
        pnlDonVe.setOpaque(false);
        pnlDonVe.setPreferredSize(new java.awt.Dimension(296, 200));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel1.setText("Tên phim:");

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel2.setText("Suất chiếu:");

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel4.setText("Vé người lớn:");

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel5.setText("Vé trẻ em:");

        lblTenPhim.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTenPhim.setForeground(new java.awt.Color(204, 102, 0));
        lblTenPhim.setText("Sóng Gió Truyền Kiếp");
        lblTenPhim.setPreferredSize(new java.awt.Dimension(121, 30));

        lblSuatChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblSuatChieu.setText("20:00 09/03/2022");

        lblVeNguoiLon.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblVeNguoiLon.setText("4 x 80.000 VND");

        lblVeTreEm.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblVeTreEm.setText("2 x 60.000");

        lblTamTinhVe.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTamTinhVe.setText("200.000");

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel3.setText("Tạm tính:");

        lblPhuThu.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhuThu.setText("0");
        lblPhuThu.setMaximumSize(new java.awt.Dimension(59, 16));
        lblPhuThu.setPreferredSize(new java.awt.Dimension(59, 16));

        jLabel15.setFont(new java.awt.Font("Leelawadee UI", 0, 12)); // NOI18N
        jLabel15.setText("Phụ thu:");

        btnDetailVe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_two_tickets_20px.png"))); // NOI18N
        btnDetailVe.setToolTipText("Chi tiết vé");
        btnDetailVe.setFocusable(false);
        btnDetailVe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailVeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDonVeLayout = new javax.swing.GroupLayout(pnlDonVe);
        pnlDonVe.setLayout(pnlDonVeLayout);
        pnlDonVeLayout.setHorizontalGroup(
            pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonVeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonVeLayout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(btnDetailVe))
                    .addGroup(pnlDonVeLayout.createSequentialGroup()
                        .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel15)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonVeLayout.createSequentialGroup()
                                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSuatChieu)
                                    .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblVeTreEm)
                                        .addComponent(lblPhuThu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblVeNguoiLon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTamTinhVe, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDonVeLayout.createSequentialGroup()
                                .addComponent(lblTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))))
                .addContainerGap())
        );
        pnlDonVeLayout.setVerticalGroup(
            pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDonVeLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblSuatChieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblVeNguoiLon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDonVeLayout.createSequentialGroup()
                        .addComponent(lblVeTreEm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDonVeLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDonVeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTamTinhVe)
                    .addComponent(jLabel3))
                .addGap(1, 1, 1)
                .addComponent(btnDetailVe, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlDoAn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn đồ ăn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14), new java.awt.Color(102, 0, 51))); // NOI18N
        pnlDoAn.setMaximumSize(new java.awt.Dimension(296, 131));
        pnlDoAn.setMinimumSize(new java.awt.Dimension(296, 131));
        pnlDoAn.setOpaque(false);
        pnlDoAn.setPreferredSize(new java.awt.Dimension(296, 131));

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setText("Tổng tiền đồ ăn:");

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setText("Tổng tiền nước:");

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setText("Tạm tính:");

        lblTongTienTA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTongTienTA.setText("60.000");

        lblTongTienDU.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTongTienDU.setText("100.000");

        lblTamTinhDA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTamTinhDA.setText("160.000");

        javax.swing.GroupLayout pnlDoAnLayout = new javax.swing.GroupLayout(pnlDoAn);
        pnlDoAn.setLayout(pnlDoAnLayout);
        pnlDoAnLayout.setHorizontalGroup(
            pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoAnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTongTienTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongTienDU, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(lblTamTinhDA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDoAnLayout.setVerticalGroup(
            pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoAnLayout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTongTienTA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTongTienDU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTamTinhDA)
                    .addComponent(jLabel8))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pnlTongTien.setMaximumSize(new java.awt.Dimension(296, 189));
        pnlTongTien.setOpaque(false);
        pnlTongTien.setPreferredSize(new java.awt.Dimension(296, 189));
        pnlTongTien.setLayout(new java.awt.GridLayout(6, 1));

        pnlMaKH.setOpaque(false);
        pnlMaKH.setPreferredSize(new java.awt.Dimension(51, 51));

        txtIDThanhVien.setEditable(false);
        txtIDThanhVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDThanhVienActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel14.setText("Mã thành viên:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Delete_20px.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusable(false);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Delete_20px_roll.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMaKHLayout = new javax.swing.GroupLayout(pnlMaKH);
        pnlMaKH.setLayout(pnlMaKHLayout);
        pnlMaKHLayout.setHorizontalGroup(
            pnlMaKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaKHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(txtIDThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlMaKHLayout.setVerticalGroup(
            pnlMaKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlMaKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtIDThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlTongTien.add(pnlMaKH);

        pnlTamTinh.setMaximumSize(new java.awt.Dimension(291, 31));
        pnlTamTinh.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel10.setText("Tổng tiền:");

        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongTien.setText("0");

        javax.swing.GroupLayout pnlTamTinhLayout = new javax.swing.GroupLayout(pnlTamTinh);
        pnlTamTinh.setLayout(pnlTamTinhLayout);
        pnlTamTinhLayout.setHorizontalGroup(
            pnlTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTamTinhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        pnlTamTinhLayout.setVerticalGroup(
            pnlTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTamTinhLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlTamTinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(lblTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlTongTien.add(pnlTamTinh);

        pnlGiamGia.setOpaque(false);

        jLabel9.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel9.setText("Giảm giá:");

        lblGiamGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGiamGia.setForeground(new java.awt.Color(255, 51, 51));
        lblGiamGia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGiamGia.setText("0%");

        javax.swing.GroupLayout pnlGiamGiaLayout = new javax.swing.GroupLayout(pnlGiamGia);
        pnlGiamGia.setLayout(pnlGiamGiaLayout);
        pnlGiamGiaLayout.setHorizontalGroup(
            pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiamGiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(56, 56, 56)
                .addComponent(lblGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlGiamGiaLayout.setVerticalGroup(
            pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGiamGiaLayout.createSequentialGroup()
                .addGroup(pnlGiamGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiamGia))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTongTien.add(pnlGiamGia);

        pnlTong.setMaximumSize(new java.awt.Dimension(291, 31));
        pnlTong.setOpaque(false);

        jLabel13.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel13.setText("Tổng hóa đơn:");
        jLabel13.setMaximumSize(new java.awt.Dimension(63, 31));
        jLabel13.setMinimumSize(new java.awt.Dimension(63, 31));
        jLabel13.setPreferredSize(new java.awt.Dimension(63, 31));

        lblThanhTien.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblThanhTien.setForeground(new java.awt.Color(102, 153, 0));
        lblThanhTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThanhTien.setText("0");
        lblThanhTien.setPreferredSize(new java.awt.Dimension(144, 29));

        javax.swing.GroupLayout pnlTongLayout = new javax.swing.GroupLayout(pnlTong);
        pnlTong.setLayout(pnlTongLayout);
        pnlTongLayout.setHorizontalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlTongLayout.setVerticalGroup(
            pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlTongTien.add(pnlTong);

        jPanel7.setOpaque(false);

        jLabel11.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel11.setText("Tiền thanh toán:");
        jLabel11.setMaximumSize(new java.awt.Dimension(102, 31));
        jLabel11.setMinimumSize(new java.awt.Dimension(102, 31));
        jLabel11.setPreferredSize(new java.awt.Dimension(102, 31));

        txtTienKD.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtTienKD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTienKD.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 153, 153)));
        txtTienKD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKDKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTienKD, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienKD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlTongTien.add(jPanel7);

        jPanel8.setOpaque(false);

        lblTienThoiLai.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienThoiLai.setForeground(new java.awt.Color(255, 153, 0));
        lblTienThoiLai.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTienThoiLai.setText("0");
        lblTienThoiLai.setMaximumSize(new java.awt.Dimension(94, 31));
        lblTienThoiLai.setMinimumSize(new java.awt.Dimension(94, 31));
        lblTienThoiLai.setPreferredSize(new java.awt.Dimension(94, 31));

        jLabel12.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel12.setText("Tiền thối lại:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(33, 33, 33)
                .addComponent(lblTienThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(lblTienThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTongTien.add(jPanel8);

        javax.swing.GroupLayout pnlDetailLayout = new javax.swing.GroupLayout(pnlDetail);
        pnlDetail.setLayout(pnlDetailLayout);
        pnlDetailLayout.setHorizontalGroup(
            pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailLayout.createSequentialGroup()
                .addGroup(pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pnlDoAn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDonVe, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTongTien, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDetailLayout.setVerticalGroup(
            pnlDetailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetailLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(pnlDonVe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        pnlThanhTien.add(pnlDetail, java.awt.BorderLayout.NORTH);

        pnlChonDoAn.setBackground(new java.awt.Color(102, 102, 102));
        pnlChonDoAn.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(153, 153, 153), new java.awt.Color(51, 51, 51)), "Chọn Đồ Ăn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Leelawadee UI", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        pnlChonDoAn.setMaximumSize(new java.awt.Dimension(350, 600));
        pnlChonDoAn.setMinimumSize(new java.awt.Dimension(350, 600));
        pnlChonDoAn.setOpaque(false);
        pnlChonDoAn.setPreferredSize(new java.awt.Dimension(350, 600));
        pnlChonDoAn.setLayout(new java.awt.BorderLayout());

        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_return_20px.png"))); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.setColor1(new java.awt.Color(204, 204, 204));
        btnQuayLai.setColor2(new java.awt.Color(210, 210, 210));
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(gradientPanel1Layout.createSequentialGroup()
                        .addComponent(pnlThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlChonDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlChonDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlContent.add(gradientPanel1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDThanhVienActionPerformed
        getMaTV();
    }//GEN-LAST:event_txtIDThanhVienActionPerformed

    private void txtTienKDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKDKeyReleased
        if(txtTienKD.getText().length()>=10) {  
                 evt.consume();
                 return;
        }
        if (!checkTienKD()) {
            double tienKD = DinhDangTienTe.chuyenChuoiThanhDouble(txtTienKD.getText());
            double thanhTien = DinhDangTienTe.chuyenChuoiThanhDouble(lblThanhTien.getText());
            btnThanhToan.setEnabled(tienKD >= thanhTien);
            
            tienThoiLai();
        }
    }//GEN-LAST:event_txtTienKDKeyReleased

    private void btnDetailVeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailVeActionPerformed
        Point pt = MouseInfo.getPointerInfo().getLocation();
        detailDialog.setLocation(pt.x -10, pt.y + 40);
        detailDialog.setList(veList);
        detailDialog.setVisible(true);
    }//GEN-LAST:event_btnDetailVeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tv = null;
        setTVTextId();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnThanhVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhVienActionPerformed
       showDialog();
    }//GEN-LAST:event_btnThanhVienActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        thanhToan();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        move();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

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
            java.util.logging.Logger.getLogger(ThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThanhToanFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetailVe;
    private com.org.app.customui.ButtonGradient btnQuayLai;
    private com.org.app.customui.ButtonGradient btnThanhToan;
    private com.org.app.customui.ButtonGradient btnThanhVien;
    private com.org.app.customui.GradientPanel gradientPanel1;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblPhuThu;
    private javax.swing.JLabel lblSuatChieu;
    private javax.swing.JLabel lblTamTinhDA;
    private javax.swing.JLabel lblTamTinhVe;
    private javax.swing.JLabel lblTenPhim;
    private javax.swing.JLabel lblThanhTien;
    private javax.swing.JLabel lblTienThoiLai;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienDU;
    private javax.swing.JLabel lblTongTienTA;
    private javax.swing.JLabel lblVeNguoiLon;
    private javax.swing.JLabel lblVeTreEm;
    private javax.swing.JPanel pnlButton;
    private javax.swing.JPanel pnlChonDoAn;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDetail;
    private javax.swing.JPanel pnlDoAn;
    private javax.swing.JPanel pnlDonVe;
    private javax.swing.JPanel pnlGiamGia;
    private javax.swing.JPanel pnlMaKH;
    private javax.swing.JPanel pnlTamTinh;
    private javax.swing.JPanel pnlThanhTien;
    private javax.swing.JPanel pnlTong;
    private javax.swing.JPanel pnlTongTien;
    private javax.swing.JTextField txtIDThanhVien;
    private javax.swing.JTextField txtTienKD;
    // End of variables declaration//GEN-END:variables

  
}

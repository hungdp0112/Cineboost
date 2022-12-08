
package com.org.app.ui.banhang;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.DonDoAnDao;
import com.org.app.controller.DonVeDao;
import com.org.app.controller.LoaiGheDao;
import com.org.app.util.DinhDangTienTe;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class HoaDonChiTietFrame extends javax.swing.JDialog {
    SubPanelCreator<LichChieuTrongNgayFrame> subPanel;
    DonVeDao daoDV = new DonVeDao();
    DonDoAnDao daoDDA = new DonDoAnDao();
    LoaiGheDao daoLG = new LoaiGheDao();
    int id_dontt;
    int soVeNL = 0;
    int soVeTE = 0;
    double tienVeNL = 0;
    double tienVeTE = 0;
    double tongHoaDon = 0;
    
    public HoaDonChiTietFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        dinhDangBang();
    }
    
    public HoaDonChiTietFrame(int id_dontt) {
        this();
        this.id_dontt = id_dontt;
    }
    
    void fillTableDonVe() throws Exception {
        DefaultTableModel model = (DefaultTableModel) tblDonVe.getModel();
        model.setRowCount(0);
        List<Object[]> list = daoDV.getThongTinDonVe(id_dontt);
        int stt = 1;
        for (Object[] row : list) {          
            String loaiVe = row[2].equals("TE")?"Trẻ em":"Người lớn";
            Double tienVe = (Double) row[3];
            model.addRow(new Object[]{stt++, row[0], row[1], loaiVe, DinhDangTienTe.chuyenThanhTienVN(tienVe)});
        }
    }
    
    void fillTableDoAn() throws Exception{
        DefaultTableModel model = (DefaultTableModel) tblDonDoAn.getModel();
        model.setRowCount(0);
        List<Object[]> list = daoDDA.getThongTinDonDA(id_dontt);
        for (Object[] row : list) {
            Double tienDoAn = (Double) row[3];
            model.addRow(new Object[]{row[0], row[1], row[2], DinhDangTienTe.chuyenThanhTienVN(tienDoAn)});
        }
    }
    int soVeNL_VP = 0;
    int soVeTE_VP = 0;
    double tienVeNL_VP = 0;
    double tienVeTE_VP = 0;
    double tongTienVe = 0;
    double tongTienDA = 0;
    void fillDataToLabel() throws Exception{
        lblSoGheDC.setText(String.valueOf(tblDonVe.getRowCount()));
        
        // tiền vé 
        for (int row = 0; row < tblDonVe.getRowCount(); row++) {
            double tienVe = DinhDangTienTe.chuyenChuoiThanhDouble((String) tblDonVe.getValueAt(row, 4));
            if (tblDonVe.getValueAt(row, 3).equals("Người lớn")) {
                if (tienVe == 120) {
                    soVeNL_VP++;
                    tienVeNL_VP = DinhDangTienTe.chuyenChuoiThanhDouble((String) tblDonVe.getValueAt(row, 4));
                    
                    tongTienVe += tienVeNL_VP;
                    tongHoaDon += tienVeNL_VP;
                }else {
                    soVeNL++;
                    tienVeNL = DinhDangTienTe.chuyenChuoiThanhDouble((String) tblDonVe.getValueAt(row, 4));
                    
                    tongTienVe += tienVeNL;
                    tongHoaDon += tienVeNL;
                }
            }else {
                if (tienVe == 100) {
                    soVeTE_VP++;
                    tienVeTE_VP = DinhDangTienTe.chuyenChuoiThanhDouble((String) tblDonVe.getValueAt(row, 4));
                    
                    tongTienVe += tienVeTE_VP;
                    tongHoaDon += tienVeTE_VP;
                }else {
                    soVeTE++;
                    tienVeTE = DinhDangTienTe.chuyenChuoiThanhDouble((String) tblDonVe.getValueAt(row, 4));
                    
                    tongTienVe += tienVeTE;
                    tongHoaDon += tienVeTE;
                }
            }
        }
                
        // tiền đồ ăn     
        for (int row = 0; row < tblDonDoAn.getRowCount(); row++) {
            double tienDoAn = DinhDangTienTe.chuyenChuoiThanhDouble((String) tblDonDoAn.getValueAt(row, 3));
            tongTienDA += tienDoAn;
            tongHoaDon += tienDoAn;
        }
        lblTongTA.setText(String.valueOf(daoDDA.getSoLuongTABySQL(id_dontt)));
        lblTongDU.setText(String.valueOf(daoDDA.getSoLuongDUBySQL(id_dontt)));
        lblTongTienDA.setText(DinhDangTienTe.chuyenThanhTienVN(tongTienDA));
        
        // load Dữ liệu lên Lable
        if (daoLG.getSoGheVPBySQL(id_dontt) > 0 && soVeNL != 0 && soVeTE != 0) {
            // có ghế thường lẫn ghế vip
            System.out.println("TH1");
            lblTienVeNL.setText(soVeNL + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeNL) + " - " + soVeNL_VP + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeNL_VP));
            lblTienVeTE.setText(soVeTE + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeTE) + " - " + soVeTE_VP + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeTE_VP));
            if (soVeNL_VP == 0 && soVeNL == 0) {
                lblTienVeNL.setText("0");
            }else if (soVeTE_VP == 0 && soVeTE == 0){
                lblTienVeTE.setText("0");
            }
        }else if(daoLG.getSoGheVPBySQL(id_dontt) > 0 && soVeNL == 0 && soVeTE == 0){
            // chỉ có ghế vip
            System.out.println("TH2");
            lblTienVeNL.setText(soVeNL_VP + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeNL_VP));
            lblTienVeTE.setText(soVeTE_VP + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeTE_VP));
            if (soVeNL_VP == 0 && soVeTE_VP == 0){
                lblTienVeNL.setText("0");
                lblTienVeTE.setText("0");
            }else if (soVeNL_VP == 0) {
                lblTienVeNL.setText("0");
            }else if (soVeTE_VP == 0) {
                lblTienVeTE.setText("0");
            }
        }else {
            // chỉ có ghế thường
            System.out.println("TH3");
            lblTienVeNL.setText(soVeNL + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeNL));
            lblTienVeTE.setText(soVeTE + " x " + DinhDangTienTe.chuyenThanhTienVN(tienVeTE));
            if (soVeNL == 0 && soVeTE == 0){
                lblTienVeNL.setText("0");
                lblTienVeTE.setText("0");
            }else if (soVeNL == 0) {
                lblTienVeNL.setText("0");
            }else if (soVeTE == 0){
                lblTienVeTE.setText("0");
            }
        }
        lblSoGheVip.setText(String.valueOf(daoLG.getSoGheVPBySQL(id_dontt)));
        
        lblTongTienVe.setText(DinhDangTienTe.chuyenThanhTienVN(tongTienVe));
        
        lblTongTienHoaDon.setText(DinhDangTienTe.chuyenThanhTienVN(tongHoaDon));
    }
    
    private void dinhDangBang(){
        // tbl Don Do An
        TableRendererUtil tblDA = new TableRendererUtil(tblDonDoAn);
        tblDA.changeHeaderStyle(Color.decode("#BB9981"));
        tblDA.setColoumnWidthByPersent(1, 10);
        tblDA.setRowHeightByPresent(40);
        tblDonDoAn.setGridColor(Color.decode("#464E2E"));
        tblDonDoAn.setFocusable(false);
        tblDonDoAn.setInheritsPopupMenu(true);
        tblDonDoAn.setShowGrid(true);
        tblDonDoAn.setSelectionBackground(Color.decode("#694E2E"));
        tblDonDoAn.setSelectionForeground(Color.decode("#FFFFFF"));
        for (int i = 0; i < tblDonDoAn.getColumnCount(); i++) {
            tblDA.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        
        tblDA.setColoumnWidthByPersent(1, 20);
        tblDA.setColoumnWidthByPersent(2, 18);
        
        
        // tbl Don Ve
        TableRendererUtil tblDV = new TableRendererUtil(tblDonVe);        
        tblDV.changeHeaderStyle(Color.decode("#E0A2FF"));
        tblDV.setColoumnWidthByPersent(1, 10);
        tblDV.setRowHeightByPresent(40);
        tblDonVe.setGridColor(Color.decode("#3E497A"));
        tblDonVe.setFocusable(false);
        tblDonVe.setInheritsPopupMenu(true);
        tblDonVe.setShowGrid(true);
        tblDonVe.setSelectionBackground(Color.decode("#3E497A"));
        tblDonVe.setSelectionForeground(Color.decode("#FFFFFF"));
        for (int i = 0; i < tblDonVe.getColumnCount(); i++) {
            tblDV.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        
        tblDV.setColoumnWidthByPersent(0, 5);
        tblDV.setColoumnWidthByPersent(1, 30);
        tblDV.setColoumnWidthByPersent(2, 30);
        tblDV.setColoumnWidthByPersent(3, 15);
        tblDV.setColoumnWidthByPersent(4, 15);
    }
        
    private void setUp(){
        dinhDangBang();
    }
    
    private void renderFrame() { 
        try {
            fillTableDoAn();
            fillTableDonVe();
            fillDataToLabel();
        } catch (Exception ex) {
            ex.printStackTrace();
            
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        btnQuayLai = new javax.swing.JButton();
        pnlChonDoAn = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonDoAn = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblTongTA = new javax.swing.JLabel();
        lblTongDU = new javax.swing.JLabel();
        lblTongTienDA = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDonVe = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSoGheDC = new javax.swing.JLabel();
        lblTienVeNL = new javax.swing.JLabel();
        lblTienVeTE = new javax.swing.JLabel();
        lblSoGheVip = new javax.swing.JLabel();
        lblTongTienHoaDon = new javax.swing.JLabel();
        lblTongTienVe = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnQuayLai.setBackground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnQuayLai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_return_20px.png"))); // NOI18N
        btnQuayLai.setText("Quay lại");
        btnQuayLai.setFocusable(false);
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        pnlChonDoAn.setBackground(new java.awt.Color(193, 219, 166));
        pnlChonDoAn.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đồ ăn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        pnlChonDoAn.setForeground(new java.awt.Color(255, 255, 255));

        tblDonDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tên đồ ăn", "Số lượng", "Size", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonDoAn.setRowHeight(30);
        jScrollPane1.setViewportView(tblDonDoAn);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setText("Tổng đồ ăn:");

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setText("Tổng đồ uống:");

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setText("Tổng tiền đồ ăn");

        lblTongTA.setBackground(new java.awt.Color(255, 255, 255));
        lblTongTA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongTA.setText("0");

        lblTongDU.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTongDU.setText("0");

        lblTongTienDA.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTongTienDA.setForeground(new java.awt.Color(69, 191, 71));
        lblTongTienDA.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTongTA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongDU, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongTienDA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lblTongTA))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTongDU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblTongTienDA))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout pnlChonDoAnLayout = new javax.swing.GroupLayout(pnlChonDoAn);
        pnlChonDoAn.setLayout(pnlChonDoAnLayout);
        pnlChonDoAnLayout.setHorizontalGroup(
            pnlChonDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlChonDoAnLayout.setVerticalGroup(
            pnlChonDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChonDoAnLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setFont(new java.awt.Font("Corbel", 0, 24)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_billing_machine_48px.png"))); // NOI18N
        jLabel13.setText("Hóa Đơn Chi Tiết");
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel13.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin vé", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tblDonVe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên phim", "Suất chiếu", "Loại vé", "Tiền vé"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonVe.setRowHeight(30);
        jScrollPane2.setViewportView(tblDonVe);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 204, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin giá vé", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setText("Số ghế đã chọn:");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel2.setText("Vé người lớn:");

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Vé trẻ em:");

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Số ghế VIP:");

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 18)); // NOI18N
        jLabel5.setText("Tổng hóa đơn:");

        lblSoGheDC.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblSoGheDC.setText("0");

        lblTienVeNL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienVeNL.setText("0");

        lblTienVeTE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTienVeTE.setText("0");

        lblSoGheVip.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblSoGheVip.setText("0");

        lblTongTienHoaDon.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTongTienHoaDon.setForeground(new java.awt.Color(245, 71, 71));
        lblTongTienHoaDon.setText("0");

        lblTongTienVe.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lblTongTienVe.setForeground(new java.awt.Color(69, 191, 71));
        lblTongTienVe.setText("0");

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setText("Tổng tiền vé");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTienVeTE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSoGheVip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongTienVe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTongTienHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTienVeNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblSoGheDC, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblSoGheDC))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblTienVeNL))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTienVeTE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoGheVip)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTongTienVe)
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblTongTienHoaDon))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 724, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pnlChonDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(btnQuayLai)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel13)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnQuayLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(pnlChonDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        renderFrame();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HoaDonChiTietFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HoaDonChiTietFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSoGheDC;
    private javax.swing.JLabel lblSoGheVip;
    private javax.swing.JLabel lblTienVeNL;
    private javax.swing.JLabel lblTienVeTE;
    private javax.swing.JLabel lblTongDU;
    private javax.swing.JLabel lblTongTA;
    private javax.swing.JLabel lblTongTienDA;
    private javax.swing.JLabel lblTongTienHoaDon;
    private javax.swing.JLabel lblTongTienVe;
    private javax.swing.JPanel pnlChonDoAn;
    private javax.swing.JTable tblDonDoAn;
    private javax.swing.JTable tblDonVe;
    // End of variables declaration//GEN-END:variables
}

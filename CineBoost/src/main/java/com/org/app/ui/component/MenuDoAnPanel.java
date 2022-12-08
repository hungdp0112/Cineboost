/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.controller.DoAnDao;
import com.org.app.controller.KichCoDoAnDao;
import com.org.app.entity.DoAn;
import com.org.app.entity.DoAnChiTiet;
import com.org.app.entity.KichCoDoAn;
import com.org.app.helper.MessageHelper;
import com.org.app.ui.banhang.ThanhToanFrame;
import com.org.app.util.CustomGridLayOut;
import com.org.app.util.DinhDangTienTe;
import com.org.app.util.FixedSizeCoatingPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class MenuDoAnPanel extends javax.swing.JPanel {       
    KichCoDoAnDao kcdaDao;;
    List<KichCoDoAn> kcda;
    //DATDOANLIST LUU VAO SOLUONG DOAN VA MAP DOAN CHON DE TINH TONG BO VAO THANHTOANFRAME
    //BEN THANHTOANFRAME SE CO DATDOANLIST, KHI NHAN XAC NHAN UPDATE DATDOANLIST BEN THANHTOANFRAME
    //DATDOANLIST SE CO PHUONG THUC TINH TONG
    ThanhToanFrame ttFrame;
    
//    LinkedHashMap<Integer, Integer> selectedDoAns = new LinkedHashMap<>(); // id, số lượng đã chọn
//    HashMap<Integer, KichCoDoAn> mp = new HashMap<>();
    
    public DADatList datList;
    
    int row = -1;
    boolean trangThai = true;
    /**
     * Creates new form MenuDoAnPanel
     */
    
    final int COL_NUM = 3;
    public MenuDoAnPanel() {
        initComponents();
        kcdaDao = new KichCoDoAnDao();
        kcda = new ArrayList<>();
        datList = new DADatList(new LinkedHashMap<>(), new HashMap<>());
        
        Dimension expectedDimension = new Dimension(345,600);
        this.setPreferredSize(expectedDimension);
        this.setMaximumSize(expectedDimension);
        this.setMinimumSize(expectedDimension);
        setUp();
        renderFrame();
        
    }
    
    public MenuDoAnPanel(ThanhToanFrame ttF) {
        this();
        this.ttFrame = ttF;
    }
    
    private void setUp() {
        scrollDoAn.setBorder(BorderFactory.createEmptyBorder());
        scrollDoAn.getViewport().setOpaque(false);
        scrollDoAn.setViewportBorder(null);
        scrollDoUong.setBorder(BorderFactory.createEmptyBorder());
        scrollDoUong.getViewport().setOpaque(false);
        scrollDoUong.setViewportBorder(null);
        clear();
    }
    
    
    public void loadPanelMenuDU() throws Exception{
        String loaiDA_Anh = "DU";
        kcda.clear();
        kcda = kcdaDao.selectMenu(loaiDA_Anh);
        CustomGridLayOut layout = new CustomGridLayOut(kcda.size(), COL_NUM, 0, 15);
        pnlMenuDU.setLayout(layout.getLayOut());

        for (KichCoDoAn kcda : kcda) {
            DoAnPanelModel model = new DoAnPanelModel(kcda.getId(), 
                                       kcda.getDoAn().getTen(), 
                                       kcda.getDoAn().getLoaiDoAn().getId(), 
                                       kcda.getKichco().getId(), 
                                       kcda.getGia());
            DoAnPanel pnlDA = new DoAnPanel(this, model);
            pnlDA.setAnh();
            pnlMenuDU.add(pnlDA);
        }
            
    }
    
    public void loadPanelMenuTA() throws Exception{
        String loaiDA_Anh = "TA";
        kcda.clear();
        kcda = kcdaDao.selectMenu(loaiDA_Anh);
        CustomGridLayOut layout = new CustomGridLayOut(kcda.size(), COL_NUM, 0, 15);
        pnlMenuTA.setLayout(layout.getLayOut());

        for (KichCoDoAn kcda : kcda) {
            DoAnPanelModel model = new DoAnPanelModel(kcda.getId(), 
                                       kcda.getDoAn().getTen(), 
                                       kcda.getDoAn().getLoaiDoAn().getId(), 
                                       kcda.getKichco().getId(), 
                                       kcda.getGia());
//            System.out.println("kcda.getGia " + kcda.getGia());
            DoAnPanel pnlDA = new DoAnPanel(this, model);
            pnlDA.setAnh();
            pnlMenuTA.add(pnlDA);
        }           
//          System.out.println(kcda = kcdaDao.selectMenu("DU"));
    }
    
    public void fillToTable() {
        DefaultTableModel model = (DefaultTableModel) tblDoAn.getModel();
        model.setRowCount(0);
        Set<Integer> keySet = datList.getIdKCDAs();
        for (Integer key: keySet) {
            model.addRow(new Object[]{datList.getTenOf(key), datList.getKichCoOf(key), DinhDangTienTe.chuyenThanhTienVN(datList.getGiaOf(key)), datList.getSoLuongOf(key)});
        }
        tongTien();
        if (tblDoAn.getRowCount() > 0) {
            row = datList.getSoLuong()-1;
            tblDoAn.setRowSelectionInterval(row, row);       
            btnXacNhan.setEnabled(true);
        }
    }
    
    public void addItem(Integer kcda_id){
        if (trangThai) {
            try {
            // Throws lỗi kiểm tra số lượng
            KichCoDoAn kcda = kcdaDao.selectById(kcda_id);
            datList.add(kcda);
            fillToTable();
            row = datList.getSoLuong() - 1;
            tblDoAn.setRowSelectionInterval(row, row);
            tblDoAn.scrollRectToVisible(new java.awt.Rectangle(tblDoAn.getCellRect(row, 0, true)));
            setForm(row);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void updateItem() throws Exception{
        String tenDA = (String) tblDoAn.getValueAt(row, 0);
        String kichCo = (String) cboSize.getSelectedItem();
        String kichCoBD = (String) tblDoAn.getValueAt(row, 1);
        Integer soLuong = (Integer) spnSoLuong.getValue();
        KichCoDoAn kcda = kcdaDao.selectByTenDA_KC(tenDA, kichCo);        
        KichCoDoAn kcdaBD = kcdaDao.selectByTenDA_KC(tenDA, kichCoBD);
        
        datList.update(kcda, soLuong, kichCoBD, kcdaBD);
    }
    
    public void removeItem() throws Exception {
        String tenDA = (String) tblDoAn.getValueAt(row, 0);
        String kichCo = (String) tblDoAn.getValueAt(row, 1);
        KichCoDoAn kcda = kcdaDao.selectByTenDA_KC(tenDA, kichCo);
        datList.remove(kcda);
    }
    
    public DADatList getDADatList(){
        return datList;
    }
    
    public void tongTien(){
        double tongtienDA = datList.getTamTinh();
        lblTongTien.setText(DinhDangTienTe.chuyenThanhTienVN(tongtienDA));
    }
    
    public void setForm(int row){
        String tenDA = (String) tblDoAn.getValueAt(row, 0);
        String kichCo = (String) tblDoAn.getValueAt(row, 1);
        Integer soLuong = (Integer) tblDoAn.getValueAt(row, 3);
        lblTenDoAn.setText(tenDA);
        spnSoLuong.setValue(soLuong);
        cboSize.setSelectedItem(kichCo);
    }
    
    
    public void renderFrame(){
        try {
            loadPanelMenuDU();
            loadPanelMenuTA();
            spnSoLuong.setEnabled(false);
            cboSize.setEnabled(false);
            btnCapNhatDA.setEnabled(false);
            btnChonLai.setEnabled(false);
            btnXacNhan.setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();
        }     
    }
    
    private void trangThaiMo(){
        trangThai = true;
        jTabbedPane1.setEnabled(trangThai);
        pnlMenuDU.setEnabled(trangThai);
        pnlMenuTA.setEnabled(trangThai);
        tblDoAn.setEnabled(trangThai);       
        btnXacNhan.setEnabled(trangThai);
        btnXoaDA.setEnabled(trangThai);
        btnChonLai.setEnabled(false);
        this.getRootPane().setBackground(Color.decode("#F2E8C3"));
    }
    
    private void trangThaiDong(){
        trangThai = false;
        jTabbedPane1.setEnabled(trangThai);
        pnlMenuDU.setEnabled(trangThai);
        pnlMenuTA.setEnabled(trangThai);
        tblDoAn.setEnabled(trangThai);       
        spnSoLuong.setEnabled(trangThai);
        btnCapNhatDA.setEnabled(trangThai);
        cboSize.setEnabled(trangThai);
        btnXacNhan.setEnabled(trangThai);
        btnChonLai.setEnabled(true);
        btnXoaDA.setEnabled(trangThai);
        tblDoAn.clearSelection();
        this.getRootPane().setBackground(Color.decode("#F2FBB4"));
    }
    
    public void clear(){
        lblTenDoAn.setText("");
        spnSoLuong.setValue(1);
        cboSize.setSelectedItem("S");
        lblTongTien.setText("");
        datList.clear();
        fillToTable();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoAn = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlDoUong = new javax.swing.JPanel();
        scrollDoUong = new javax.swing.JScrollPane();
        pnlMenuDU = new javax.swing.JPanel();
        pnlDoAn = new javax.swing.JPanel();
        scrollDoAn = new javax.swing.JScrollPane();
        pnlMenuTA = new javax.swing.JPanel();
        pnlEdit = new javax.swing.JPanel();
        lblTenDoAn = new javax.swing.JLabel();
        cboSize = new javax.swing.JComboBox<>();
        spnSoLuong = new javax.swing.JSpinner();
        btnCapNhatDA = new javax.swing.JButton();
        btnXoaDA = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnBoQua = new javax.swing.JButton();
        btnChonLai = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
        pnlTongTien = new javax.swing.JPanel();
        lblTongTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(345, 600));
        setMinimumSize(new java.awt.Dimension(345, 600));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(345, 600));

        pnlContent.setBackground(new java.awt.Color(102, 102, 102));
        pnlContent.setMaximumSize(new java.awt.Dimension(345, 600));
        pnlContent.setPreferredSize(new java.awt.Dimension(345, 600));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Menu đồ ăn - uống:");

        tblDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên món", "Size", "Giá", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoAn);

        jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTabbedPane1.setOpaque(true);
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        pnlMenuDU.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlMenuDULayout = new javax.swing.GroupLayout(pnlMenuDU);
        pnlMenuDU.setLayout(pnlMenuDULayout);
        pnlMenuDULayout.setHorizontalGroup(
            pnlMenuDULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        pnlMenuDULayout.setVerticalGroup(
            pnlMenuDULayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 132, Short.MAX_VALUE)
        );

        scrollDoUong.setViewportView(pnlMenuDU);

        javax.swing.GroupLayout pnlDoUongLayout = new javax.swing.GroupLayout(pnlDoUong);
        pnlDoUong.setLayout(pnlDoUongLayout);
        pnlDoUongLayout.setHorizontalGroup(
            pnlDoUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDoUong)
        );
        pnlDoUongLayout.setVerticalGroup(
            pnlDoUongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDoUong)
        );

        jTabbedPane1.addTab("Đồ uống", pnlDoUong);

        pnlMenuTA.setBackground(new java.awt.Color(230, 231, 226));

        javax.swing.GroupLayout pnlMenuTALayout = new javax.swing.GroupLayout(pnlMenuTA);
        pnlMenuTA.setLayout(pnlMenuTALayout);
        pnlMenuTALayout.setHorizontalGroup(
            pnlMenuTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        pnlMenuTALayout.setVerticalGroup(
            pnlMenuTALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 132, Short.MAX_VALUE)
        );

        scrollDoAn.setViewportView(pnlMenuTA);

        javax.swing.GroupLayout pnlDoAnLayout = new javax.swing.GroupLayout(pnlDoAn);
        pnlDoAn.setLayout(pnlDoAnLayout);
        pnlDoAnLayout.setHorizontalGroup(
            pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDoAn)
        );
        pnlDoAnLayout.setVerticalGroup(
            pnlDoAnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollDoAn)
        );

        jTabbedPane1.addTab("Đồ ăn", pnlDoAn);

        pnlEdit.setBackground(new java.awt.Color(243, 244, 193));
        pnlEdit.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel", 1, 14))); // NOI18N
        pnlEdit.setMaximumSize(new java.awt.Dimension(134, 122));

        lblTenDoAn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTenDoAn.setForeground(new java.awt.Color(255, 102, 51));
        lblTenDoAn.setText("Bắp");
        lblTenDoAn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "S", "M", "L" }));
        cboSize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        spnSoLuong.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spnSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        spnSoLuong.setMaximumSize(new java.awt.Dimension(31, 20));

        btnCapNhatDA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCapNhatDA.setText("Cập nhật");
        btnCapNhatDA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCapNhatDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatDAActionPerformed(evt);
            }
        });

        btnXoaDA.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        btnXoaDA.setText("X");
        btnXoaDA.setToolTipText("Xóa đồ ăn");
        btnXoaDA.setBorderPainted(false);
        btnXoaDA.setContentAreaFilled(false);
        btnXoaDA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoaDA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnXoaDAMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnXoaDAMouseExited(evt);
            }
        });
        btnXoaDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDAActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Chỉnh sửa");

        javax.swing.GroupLayout pnlEditLayout = new javax.swing.GroupLayout(pnlEdit);
        pnlEdit.setLayout(pnlEditLayout);
        pnlEditLayout.setHorizontalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXoaDA))
                    .addGroup(pnlEditLayout.createSequentialGroup()
                        .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboSize, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnCapNhatDA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pnlEditLayout.setVerticalGroup(
            pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaDA, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(pnlEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCapNhatDA, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnBoQua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBoQua.setText("Bỏ qua");
        btnBoQua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBoQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBoQuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnBoQua);

        btnChonLai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnChonLai.setText("Chọn lại");
        btnChonLai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnChonLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonLaiActionPerformed(evt);
            }
        });
        jPanel1.add(btnChonLai);

        btnXacNhan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });
        jPanel1.add(btnXacNhan);

        pnlTongTien.setBackground(new java.awt.Color(215, 221, 225));

        lblTongTien.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblTongTien.setForeground(new java.awt.Color(204, 51, 0));
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTongTien.setText("0");

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setText("Tổng tiền:");

        javax.swing.GroupLayout pnlTongTienLayout = new javax.swing.GroupLayout(pnlTongTien);
        pnlTongTien.setLayout(pnlTongTienLayout);
        pnlTongTienLayout.setHorizontalGroup(
            pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTongTienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTongTienLayout.setVerticalGroup(
            pnlTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongTienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongTien)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(pnlTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatDAActionPerformed
        // TODO add your handling code here:        
        if (row != -1) {
            cboSize.setEnabled(true);
            try {                
                updateItem();
                fillToTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnCapNhatDAActionPerformed

    private void tblDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMouseClicked
        // TODO add your handling code here:      
        try {
            row = tblDoAn.getSelectedRow();
            setForm(row);
            cboSize.setEnabled(true);
            spnSoLuong.setEnabled(true);
            btnCapNhatDA.setEnabled(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblDoAnMouseClicked

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        trangThaiDong();
        ttFrame.updateDADatList();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void btnChonLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonLaiActionPerformed
        // TODO add your handling code here:
        trangThaiMo();
        btnBoQua.setEnabled(true);
    }//GEN-LAST:event_btnChonLaiActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
       jTabbedPane1.repaint();
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void btnXoaDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDAActionPerformed
        // TODO add your handling code here:
        if (tblDoAn.getRowCount() <= 0) {
            MessageHelper.message(this, "Bạn chưa chọn đồ ăn nào trong bảng"); 
        }else {
            if (row != -1) {            
                try {
                    removeItem();
                    fillToTable();
//                    row = -1;
                    spnSoLuong.setEnabled(false);
                    cboSize.setEnabled(false);
                    btnCapNhatDA.setEnabled(false);                   
                    if (tblDoAn.getRowCount() <= 0) {
                        btnXacNhan.setEnabled(false);
                        ttFrame.clearStatePnlDoAn();
                        ttFrame.updatePnlTongTien();
//                        ttFrame.clearPnlTongTien();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }               
            }else {
                MessageHelper.message(this, "Cần chọn 1 dòng trong bảng để thực hiện xóa");
            }
        }
    }//GEN-LAST:event_btnXoaDAActionPerformed

    private void btnXoaDAMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaDAMouseEntered
        // TODO add your handling code here:
        btnXoaDA.setForeground(Color.RED);
    }//GEN-LAST:event_btnXoaDAMouseEntered

    private void btnXoaDAMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaDAMouseExited
        // TODO add your handling code here:
        btnXoaDA.setForeground(Color.BLACK);
    }//GEN-LAST:event_btnXoaDAMouseExited

    private void btnBoQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBoQuaActionPerformed
        // TODO add your handling code here:
        System.out.println("isDoAnOnly " + ThanhToanFrame.isDoAnOnly);
//        System.out.println("isBoth "+ ThanhToanFrame.is);
        trangThaiDong();
        clear();
        ttFrame.clearPnlDoAn_isDA();
        btnBoQua.setEnabled(false);
    }//GEN-LAST:event_btnBoQuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBoQua;
    private javax.swing.JButton btnCapNhatDA;
    private javax.swing.JButton btnChonLai;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoaDA;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblTenDoAn;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDoAn;
    private javax.swing.JPanel pnlDoUong;
    private javax.swing.JPanel pnlEdit;
    private javax.swing.JPanel pnlMenuDU;
    private javax.swing.JPanel pnlMenuTA;
    private javax.swing.JPanel pnlTongTien;
    private javax.swing.JScrollPane scrollDoAn;
    private javax.swing.JScrollPane scrollDoUong;
    private javax.swing.JSpinner spnSoLuong;
    private javax.swing.JTable tblDoAn;
    // End of variables declaration//GEN-END:variables
}

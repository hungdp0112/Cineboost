/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.quanly;

import com.org.app.ui.main.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.DonThanhToanDao;
import com.org.app.helper.DateHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.message.MessageDialog;
import com.org.app.ui.banhang.HoaDonChiTietFrame;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.DinhDangTienTe;
import com.org.app.util.LockDateChooser;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author intfs
 */
public class QLDonThanhToanFrame extends javax.swing.JFrame implements SubFrame<QLNhanVienFrame>,SubPanelCreatorInterfaces<SubFrame> {
    public static final String CARD_NAME_MAIN = "donthanhtoan";
    
    SubPanelCreator<QLDonThanhToanFrame> subPanel;
    DefaultTableModel model;
    DonThanhToanDao dao = new DonThanhToanDao();
    TableRendererUtil tbl;
    private List<Vector<Object>> listToTable;
    int row = -1;
    
    private final String HIEN_TAT_CA_TEXT = "Hiển thị tất cả";
    private final String HIEN_THEO_BO_LOC_TEXT = "Hiển thị theo bộ lọc";
    
    /**
     * Creates new form mainFrame
     */
    public QLDonThanhToanFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        listToTable = new ArrayList<>();
        model = (DefaultTableModel) tblDonThanhToan.getModel();
        dcNgayBD.getDateEditor().getUiComponent().setName("txtNgayBD");
        dcNgayKT.getDateEditor().getUiComponent().setName("txtNgayKT");
        setUp();
    }

    private boolean isKhachHang(){
        return cboLoaiKH.getSelectedIndex()== 1;
    }
    
    private Integer getSelectedThang(){
        String thang = (String) cboTheoThang.getSelectedItem();
        return Integer.parseInt(thang.replace("Tháng ", ""));
    }
    
    private Object[] getFilter() {
        //ngaybd, ngaykt, thang, nam
        Integer thang = null, nam = null; 
        java.sql.Date bd = null, kt = null;
        Integer kh = !chkLoaiKH.isSelected()? -1 : isKhachHang() ? 1 : 0;
        String kw = null;
        Object arr[] = {bd, kt, thang, nam, kh, kw};  
        if(chkNgay.isSelected()) {
            arr[0] = DateHelper.toDate(dcNgayBD.getDate());
            arr[1] = DateHelper.toDate(dcNgayKT.getDate());
        }
        if(chkThang.isSelected()) {
            arr[2] = getSelectedThang();
            arr[3] = cboTheoNamOfThang.getSelectedItem();
        }
        
        if(chkNam.isSelected()) {
            arr[3] = cboTheoNam.getSelectedItem();
        }
        
        if (!txtTim.getText().isBlank()) {
            arr[5] = txtTim.getText();
        }
        System.out.println("arr = "+java.util.Arrays.toString(arr));
        return arr;
    }
    
    
    void fillTable() {        
//        Vector<String> COLUMN_NAME = new Vector(List.of("Mã đơn","Ngày mua", "Loại khách hàng", "Mã thành viên", "Tổng tiền"));
        Vector<String> COLUMN_NAME = new Vector(List.of("Mã đơn","Ngày mua", "Loại khách hàng", "Tổng tiền"));
        listToTable.clear();
        model.setRowCount(0);
        Object[] arr = getFilter();
        
        try {
            listToTable = dao.selectByAll(arr);
            System.out.println("list size = "+ listToTable.size());
            model.setColumnIdentifiers(COLUMN_NAME);
            for (int i = 0; i < listToTable.size(); i++) {
                listToTable.get(i).set(1, DateHelper.toString((java.util.Date)listToTable.get(i).get(1)));
                model.addRow(listToTable.get(i));
            }
            for (int i = 0; i < tblDonThanhToan.getColumnCount(); i++) {
            tbl.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        
        tbl.setColoumnWidthByPersent(0, 5);
        tbl.setColoumnWidthByPersent(3, 25);
        for (int i = 1; i < tblDonThanhToan.getColumnCount(); i++) {
            tbl.setColoumnWidthByPersent(i, 20);
            if (i == 3) i++;
        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public void deleteDonThanhToan(){
       int id_DonTT = (int) (tblDonThanhToan.getValueAt(row, 0));
        try {
            dao.delete(id_DonTT);
            this.fillTable();
            MessageHelper.message(this, "Xóa đơn thành công!", 2);
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Xóa thất bại!", 0);
        } 
    }
    
    public void setForm(){
        if (row != -1) {
            lblMaDon.setText(String.valueOf(tblDonThanhToan.getValueAt(row, 0)));
            lblNgayMua.setText(String.valueOf(tblDonThanhToan.getValueAt(row, 1)));
            lblLoaiKH.setText(String.valueOf(tblDonThanhToan.getValueAt(row, 2)));
            lblTongTien.setText(String.valueOf(tblDonThanhToan.getValueAt(row, 3)));
        }
    }
    
    public void search(){
        this.fillTable();
    }
    
    public void buttonStatusOpen(){
        chkNgay.setEnabled(true);
        chkThang.setEnabled(true);
        chkNam.setEnabled(true);
        chkLoaiKH.setEnabled(true);
    }
//    
    public void buttonStatusClose(){
        chkNgay.setEnabled(false);
        dcNgayBD.setEnabled(false);
        dcNgayKT.setEnabled(false);
        chkNgay.setSelected(false);
        
        chkThang.setEnabled(false);
        cboTheoThang.setEnabled(false);
        cboTheoNamOfThang.setEnabled(false);
        chkThang.setSelected(false);
        
        chkNam.setEnabled(false);
        cboTheoNam.setEnabled(false);
        chkNam.setSelected(false);
        
        chkLoaiKH.setEnabled(false);
        cboLoaiKH.setEnabled(false);
        chkLoaiKH.setSelected(false);
    }
    
    public void mouseReleased(MouseEvent mouseEvent) {
//        if (SwingUtilities.isRightMouseButton(mouseEvent)) {
//            if (mouseEvent.getClickCount() == 2) {
                HoaDonChiTietFrame hdct = new HoaDonChiTietFrame((int) tblDonThanhToan.getValueAt(row, 0));
                hdct.setVisible(true);
//            } 
//        }
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
    
    private void cboNam(){
        DefaultComboBoxModel nam = (DefaultComboBoxModel) cboTheoNam.getModel();
        DefaultComboBoxModel namOfThang = (DefaultComboBoxModel) cboTheoNamOfThang.getModel();
        try {
            List<Integer> list = dao.getNamThanhToan();
            loadListToModel(nam, list);
            loadListToModel(namOfThang, list);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadListToModel(DefaultComboBoxModel m, List list) {
        for (int i = 0; i < list.size(); i++) {
            m.addElement(list.get(i));
        }
    }
    
    private void setUp() {
        dinhDangBang();
        
        dcNgayBD.setDate(DateHelper.now());
        dcNgayKT.setDate(DateHelper.addDays(DateHelper.now(), 1));
        LockDateChooser.lock(dcNgayBD, dcNgayKT);
        cboNam();
        dcNgayBD.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                    java.util.Date d = dcNgayBD.getDate();    
                    dcNgayKT.setMinSelectableDate(d);
                    if(dcNgayKT.getDate().compareTo(d) < 0) {
                        System.out.println("change");
                        dcNgayKT.setDate(DateHelper.addDays(d, 1));
                    }
                    fillTable();
                }
            });
        
        dcNgayKT.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                    fillTable();
                }
            });

        btnShowAll.setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return Color.white;
            }
        });
    }
    
    public void renderFrame() {
        fillTable();
        this.row = -1;
        buttonStatusClose();
        btnXoa.setEnabled(false);
    }
    
    private void dinhDangBang(){
        tbl = new TableRendererUtil(tblDonThanhToan);
        tbl.changeHeaderStyle(Color.decode("#F3E9DD"));
        tbl.setColoumnWidthByPersent(1, 10);
        tbl.setRowHeightByPresent(40);
        tblDonThanhToan.setGridColor(Color.decode("#00A264"));
        tblDonThanhToan.setFocusable(false);
        tblDonThanhToan.setInheritsPopupMenu(true);
        tblDonThanhToan.setShowGrid(true);
        tblDonThanhToan.setSelectionBackground(Color.decode("#00A264"));
        tblDonThanhToan.setSelectionForeground(Color.decode("#FFFFFF"));

        
        for (int i = 0; i < tblDonThanhToan.getColumnCount(); i++) {
            tbl.setColumnAlignment(1, (int) CENTER_ALIGNMENT);
        }
        
        tbl.setColoumnWidthByPersent(0, 5);
        tbl.setColoumnWidthByPersent(3, 25);
        for (int i = 1; i < tblDonThanhToan.getColumnCount(); i++) {
            tbl.setColoumnWidthByPersent(i, 20);
            if (i == 3) i++;
        }
    }
    
    private void trangThai(){
        if (chkNgay.isSelected()) {
            dcNgayBD.setEnabled(true);
            dcNgayKT.setEnabled(true);

            cboTheoThang.setEnabled(false);
            cboTheoNamOfThang.setEnabled(false);
            cboTheoNam.setEnabled(false);
            cboLoaiKH.setEnabled(false);
        }else if (chkThang.isSelected()) {
            cboTheoThang.setEnabled(true);
            cboTheoNamOfThang.setEnabled(true);

            dcNgayBD.setEnabled(false);
            dcNgayKT.setEnabled(false);
            cboTheoNam.setEnabled(false);
            cboLoaiKH.setEnabled(false);
        }else if (chkNam.isSelected()) {
            cboTheoNam.setEnabled(true);

            dcNgayBD.setEnabled(false);
            dcNgayKT.setEnabled(false);
            cboTheoThang.setEnabled(false);
            cboTheoNamOfThang.setEnabled(false);
            cboLoaiKH.setEnabled(false);
        }

        if (chkLoaiKH.isSelected()) {
            cboLoaiKH.setEnabled(true);
        }else {
            cboLoaiKH.setEnabled(false);
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

        btgLoc = new javax.swing.ButtonGroup();
        btgLocNgay = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDonThanhToan = new javax.swing.JTable();
        pnlThongTinDTT = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblMaDon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblSuatChieu = new javax.swing.JLabel();
        lblNgayMua = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblLoaiKH = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        pnlBoLoc = new javax.swing.JPanel();
        btnShowAll = new javax.swing.JToggleButton();
        jPanel7 = new javax.swing.JPanel();
        cboLoaiKH = new javax.swing.JComboBox<>();
        chkNgay = new javax.swing.JCheckBox();
        chkThang = new javax.swing.JCheckBox();
        chkNam = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        cboTheoThang = new javax.swing.JComboBox<>();
        cboTheoNamOfThang = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        dcNgayKT = new com.toedter.calendar.JDateChooser();
        dcNgayBD = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        cboTheoNam = new javax.swing.JComboBox<>();
        chkLoaiKH = new javax.swing.JCheckBox();
        btnXoa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mainframe");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(690, 0));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_bill_48px_1.png"))); // NOI18N
        jLabel1.setText("Quản Lý Đơn Thanh Toán");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jPanel4.setBackground(new java.awt.Color(77, 172, 150));
        jPanel4.setMaximumSize(new java.awt.Dimension(480, 587));

        tblDonThanhToan.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        tblDonThanhToan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã đơn", "Ngày mua", "Loại khách hàng", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDonThanhToan.setToolTipText("Chọn và click 2 lần để xem chi tiết");
        tblDonThanhToan.setName("tblDonThanhToan"); // NOI18N
        tblDonThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDonThanhToanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDonThanhToan);

        pnlThongTinDTT.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_bill_18px.png"))); // NOI18N
        jLabel2.setText("Mã đơn:");
        jLabel2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblMaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaDon.setName("lblMaDon"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_timeline_week_18px.png"))); // NOI18N
        jLabel5.setText("Ngày mua:");
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblSuatChieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSuatChieu.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        lblNgayMua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNgayMua.setName("lblNgayMua"); // NOI18N

        jLabel8.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_user_18px.png"))); // NOI18N
        jLabel8.setText("Loại khách hàng:");
        jLabel8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblLoaiKH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLoaiKH.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblLoaiKH.setName("lblLoaiKH"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_money_18px.png"))); // NOI18N
        jLabel10.setText("Tổng tiền:");
        jLabel10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        lblTongTien.setBackground(new java.awt.Color(255, 51, 51));
        lblTongTien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTongTien.setName("lblTongTien"); // NOI18N

        javax.swing.GroupLayout pnlThongTinDTTLayout = new javax.swing.GroupLayout(pnlThongTinDTT);
        pnlThongTinDTT.setLayout(pnlThongTinDTTLayout);
        pnlThongTinDTTLayout.setHorizontalGroup(
            pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinDTTLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinDTTLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinDTTLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSuatChieu, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(lblMaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlThongTinDTTLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinDTTLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlThongTinDTTLayout.setVerticalGroup(
            pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinDTTLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(lblMaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblLoaiKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(lblSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(pnlThongTinDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblNgayMua, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(lblTongTien))
                .addGap(22, 22, 22))
        );

        txtTim.setToolTipText("Nhập số điện thoại hoặc tên khách hàng để tìm");
        txtTim.setName("txtTim"); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setPreferredSize(new java.awt.Dimension(424, 6));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 6, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Tìm kiếm:");

        jButton1.setText("Tìm");
        jButton1.setFocusable(false);
        jButton1.setName("btnTim"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlThongTinDTT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlThongTinDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBoLoc.setBackground(new java.awt.Color(77, 172, 150));
        pnlBoLoc.setMaximumSize(new java.awt.Dimension(202, 587));
        pnlBoLoc.setMinimumSize(new java.awt.Dimension(202, 587));
        pnlBoLoc.setPreferredSize(new java.awt.Dimension(202, 587));

        btnShowAll.setBackground(new java.awt.Color(223, 235, 232));
        btnShowAll.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnShowAll.setText("Hiển thị tất cả");
        btnShowAll.setFocusable(false);
        btnShowAll.setName("btnShowAll"); // NOI18N
        btnShowAll.setOpaque(true);
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setOpaque(false);

        cboLoaiKH.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cboLoaiKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Thành viên", "Tự do" }));
        cboLoaiKH.setName("cboLoaiKH"); // NOI18N
        cboLoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiKHActionPerformed(evt);
            }
        });

        btgLocNgay.add(chkNgay);
        chkNgay.setText("Theo ngày");
        chkNgay.setFocusable(false);
        chkNgay.setName("chkNgay"); // NOI18N
        chkNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNgayActionPerformed(evt);
            }
        });

        btgLocNgay.add(chkThang);
        chkThang.setText("Theo tháng");
        chkThang.setFocusable(false);
        chkThang.setName("chkThang"); // NOI18N
        chkThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkThangActionPerformed(evt);
            }
        });

        btgLocNgay.add(chkNam);
        chkNam.setText("Theo năm");
        chkNam.setFocusable(false);
        chkNam.setName("chkNam"); // NOI18N
        chkNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNamActionPerformed(evt);
            }
        });

        jPanel1.setOpaque(false);

        cboTheoThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12" }));
        cboTheoThang.setName("cboTheoThang"); // NOI18N
        cboTheoThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoThangActionPerformed(evt);
            }
        });

        cboTheoNamOfThang.setName("cboTheoNamOfThang"); // NOI18N
        cboTheoNamOfThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNamOfThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboTheoNamOfThang, 0, 84, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cboTheoThang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cboTheoNamOfThang, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setOpaque(false);

        dcNgayKT.setDateFormatString("dd/MM/yyyy");
        dcNgayKT.setName("dcNgayKT"); // NOI18N

        dcNgayBD.setDateFormatString("dd/MM/yyyy");
        dcNgayBD.setMaxSelectableDate(new java.util.Date(1956506510000L));
        dcNgayBD.setMinSelectableDate(new java.util.Date(-62135791090000L));
        dcNgayBD.setName("dcNgayBD"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dcNgayKT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dcNgayBD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(dcNgayBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(dcNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setOpaque(false);

        cboTheoNam.setName("cboTheoNam"); // NOI18N
        cboTheoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTheoNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(cboTheoNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cboTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        chkLoaiKH.setText("Khách");
        chkLoaiKH.setFocusable(false);
        chkLoaiKH.setName("chkLoaiKH"); // NOI18N
        chkLoaiKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLoaiKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboLoaiKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkNam, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(chkThang, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkNgay)
                            .addComponent(chkLoaiKH))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(chkNgay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkThang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkLoaiKH)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboLoaiKH, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_30px_2.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setFocusable(false);
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBoLocLayout = new javax.swing.GroupLayout(pnlBoLoc);
        pnlBoLoc.setLayout(pnlBoLocLayout);
        pnlBoLocLayout.setHorizontalGroup(
            pnlBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBoLocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShowAll, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlBoLocLayout.setVerticalGroup(
            pnlBoLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoLocLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnShowAll, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlBoLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        // TODO add your handling code here:
        if (btnShowAll.isSelected()) {
            btnShowAll.setText(HIEN_TAT_CA_TEXT);
            buttonStatusOpen();
        }else{
            btnShowAll.setText(HIEN_THEO_BO_LOC_TEXT);
            buttonStatusClose();
            fillTable();
        }
    }//GEN-LAST:event_btnShowAllActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        if (row != -1) {
            deleteDonThanhToan();
            tblDonThanhToan.clearSelection();
            btnXoa.setEnabled(false);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblDonThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDonThanhToanMouseClicked
        // TODO add your handling code here:
        row = tblDonThanhToan.getSelectedRow(); if(row == -1) return;
        if(evt.getClickCount() == 2) {
            mouseReleased(evt);
        }else if(evt.getClickCount() == 1) {
            row = tblDonThanhToan.getSelectedRow();
            setForm();
            btnXoa.setEnabled(true);
        }
    }//GEN-LAST:event_tblDonThanhToanMouseClicked

    private void cboLoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiKHActionPerformed
        // TODO add your handling code here:
        fillTable();
    }//GEN-LAST:event_cboLoaiKHActionPerformed

    private void cboTheoThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoThangActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_cboTheoThangActionPerformed

    private void cboTheoNamOfThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNamOfThangActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_cboTheoNamOfThangActionPerformed

    private void cboTheoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTheoNamActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_cboTheoNamActionPerformed

    private void chkNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNgayActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_chkNgayActionPerformed

    private void chkThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkThangActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_chkThangActionPerformed

    private void chkNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNamActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_chkNamActionPerformed

    private void chkLoaiKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLoaiKHActionPerformed
        // TODO add your handling code here:
        fillTable();
        trangThai();
    }//GEN-LAST:event_chkLoaiKHActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QLDonThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDonThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDonThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDonThanhToanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLDonThanhToanFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgLoc;
    private javax.swing.ButtonGroup btgLocNgay;
    private javax.swing.JToggleButton btnShowAll;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiKH;
    private javax.swing.JComboBox<String> cboTheoNam;
    private javax.swing.JComboBox<String> cboTheoNamOfThang;
    private javax.swing.JComboBox<String> cboTheoThang;
    private javax.swing.JCheckBox chkLoaiKH;
    private javax.swing.JCheckBox chkNam;
    private javax.swing.JCheckBox chkNgay;
    private javax.swing.JCheckBox chkThang;
    private com.toedter.calendar.JDateChooser dcNgayBD;
    private com.toedter.calendar.JDateChooser dcNgayKT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLoaiKH;
    private javax.swing.JLabel lblMaDon;
    private javax.swing.JLabel lblNgayMua;
    private javax.swing.JLabel lblSuatChieu;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JPanel pnlBoLoc;
    private javax.swing.JPanel pnlThongTinDTT;
    private javax.swing.JTable tblDonThanhToan;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

   
}

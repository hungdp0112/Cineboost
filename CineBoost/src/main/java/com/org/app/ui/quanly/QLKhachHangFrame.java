/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.quanly;

import com.org.app.ui.main.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.ThanhVienDao;
import com.org.app.entity.ThanhVien;
import com.org.app.helper.DateHelper;
import com.org.app.helper.InputValidlHelper;
import com.org.app.helper.JDBCHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.message.MessageDialog;
import com.org.app.ui.banhang.LichChieuTrongNgayFrame;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.InputFocusGUIUtil;
import com.org.app.util.KhachHangValidator;
import com.org.app.util.LockDateChooser;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.org.app.util.ValidationHelper;
import com.org.app.util.Validator;
import com.org.app.util.ValidatorException;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author intfs
 */
public class QLKhachHangFrame extends javax.swing.JFrame implements SubFrame<QLKhachHangFrame>, SubPanelCreatorInterfaces<SubFrame> {
    public static final String CARD_NAME_MAIN = "thanhvien";
    
    SubPanelCreator<QLKhachHangFrame> subPanel;
    DefaultTableModel model;
    private int index;
    ThanhVienDao dao = new ThanhVienDao();
    Validator<ThanhVien> validator =  ValidationHelper.getThanhVienChecker();
    ThanhVien tv = null;
    List<ThanhVien> list;

    private String BTN_SUA_NAME = "Sửa";
    private String BTN_XOA_NAME = "Xóa";
    private String BTN_THEM_NAME = "Thêm";
    private String BTN_MOI_NAME = "Mới";

    private String BTN_SUA_ICON = "icons8_refresh_24px_1.png";
    private String BTN_SUA_ICON_ROLL = "icons8_refresh_24px_2.png";
    private String BTN_XOA_ICON = "icons8_remove_24px_1.png";
    private String BTN_XOA_ICON_ROLL = "icons8_remove_24px_2.png";
    private String BTN_THEM_ICON = "icons8_add_24px_1.png";
    private String BTN_THEM_ICON_ROLL = "icons8_add_24px.png";
    private String BTN_MOI_ICON = "icons8_new_copy_24px_1.png";
    private String BTN_MOI_ICON_ROLL = "icons8_new_copy_24px_2.png";

    /**
     * Creates new form mainFrame
     */
    public QLKhachHangFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        setUp();
        dinhDangBang();
        pnlBackGround.requestFocus();
        setUpInputFocusListener();
        setSelectedRow();
    }

    public void fillTable() {
        model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTim.getText();
            TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
            tblKhachHang.setRowSorter(trs);
            trs.setRowFilter(RowFilter.regexFilter(keyword));
            list = dao.selectAll();
            for (ThanhVien tv : list) {
                Object[] row = {
                    tv.getID(),
                    tv.getHoTen(),
                    DateHelper.toString(tv.getNgaySinh()),
                    tv.getGioiTinh() ? "Nam" : "Nữ",
                    tv.getSoDT(),
                    tv.getEmail(),
                    tv.getMakh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Lỗi truy vấn dữ liệu!", 0);
        }
    }

    public void insert() {
        try {
            ThanhVien model = getForm();
            check(model);
            checkLoi(model);
            dao.insert(model);
            this.fillTable();
            MessageHelper.message(this, "Thêm mới thành công!", 2);
            index = findMatch(model);
            setSelectedRow();
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, e.getMessage(), 3);
        }
    }

    public void update() {
        try {
            ThanhVien model = getForm();
            check(model);
            checkLoi(tv);
            dao.update(model);
            this.fillTable();
            MessageHelper.message(this, "Cập nhật thành công!", 2);
            index = list.indexOf(model);
            setSelectedRow();
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, e.getMessage(), 3);
        }
    }

    public void delete() {
        String makh = lblIDKhachHang.getText();
        try {
            dao.delete(makh);
            this.fillTable();
            MessageHelper.message(this, "Xóa thành công!", 2);
            if (index != list.size()) {
                if (index == -1) {
                    clear();
                    return;
                }else{
                tblKhachHang.setRowSelectionInterval(index, index);
                }
            }else{
                index = list.size() - 1;
                if (index == -1) {
                    clear();
                    return;
                }else{
                    tblKhachHang.setRowSelectionInterval(index, index); 
                }
                
            }
            setForm(list.get(index));
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Xóa thất bại!", 0);
        }
    }

    private void setSelectedRow() {
        if (index == -1 || index > list.size() - 1) {
            index = -1;
            return;
        }
        tblKhachHang.setRowSelectionInterval(index, index);
        tblKhachHang.scrollRectToVisible(new java.awt.Rectangle(tblKhachHang.getCellRect(index, 0, true)));

        updateControlState();
        btnTrangThai();
        setForm(list.get(index));
        tv = list.get(index);
    }

    private void updateControlState() {
        boolean first = index <= 0;
        boolean last = index == list.size() - 1;

        btnFirst.setEnabled(!first);
        btnLast.setEnabled(!last);
    }

    public void setUp() {
        JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dcsNgaySinh.getDateEditor();
        txtFld.setBackground(pnlBackGround.getBackground());
        txtFld.setHorizontalAlignment(SwingConstants.CENTER);
        txtFld.setBorder(txtHoTen.getBorder());
        txtSoDT.setBorder(txtHoTen.getBorder());
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnFirst, btnLast, btnNext, btnPre);
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnThem, btnSua, btnXoa, btnMoi);
        setButtonState();
        LockDateChooser.lock(dcsNgaySinh);
    }

    private void setButtonState() {

        String btnSuaText = BTN_SUA_NAME;
        String btnSuaIcon = BTN_SUA_ICON;
        String btnSuaIconRoll = BTN_SUA_ICON_ROLL;

        String btnXoaText = BTN_XOA_NAME;
        String btnXoaIcon = BTN_XOA_ICON;
        String btnXoaIconRoll = BTN_XOA_ICON_ROLL;

        String btnThemText = BTN_THEM_NAME;
        String btnThemIcon = BTN_THEM_ICON;
        String btnThemIconRoll = BTN_THEM_ICON_ROLL;

        String btnMoiText = BTN_MOI_NAME;
        String btnMoiIcon = BTN_MOI_ICON;
        String btnMoiIconRoll = BTN_MOI_ICON_ROLL;

        btnSua.setText(btnSuaText);
        btnSua.setIcon(ScaleImageIconGenerator.getImageFromResources(btnSuaIcon));
        btnSua.setRolloverIcon(ScaleImageIconGenerator.getImageFromResources(btnSuaIconRoll));

        btnXoa.setText(btnXoaText);
        btnXoa.setIcon(ScaleImageIconGenerator.getImageFromResources(btnXoaIcon));
        btnXoa.setRolloverIcon(ScaleImageIconGenerator.getImageFromResources(btnXoaIconRoll));

        btnThem.setText(btnThemText);
        btnThem.setIcon(ScaleImageIconGenerator.getImageFromResources(btnThemIcon));
        btnThem.setRolloverIcon(ScaleImageIconGenerator.getImageFromResources(btnThemIconRoll));

        btnMoi.setText(btnMoiText);
        btnMoi.setIcon(ScaleImageIconGenerator.getImageFromResources(btnMoiIcon));
        btnMoi.setRolloverIcon(ScaleImageIconGenerator.getImageFromResources(btnMoiIconRoll));

        btnFirst.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_skip_to_start_26px_1.png")));
        btnNext.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_right_26px.png")));
        btnPre.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_left_26px_2.png")));
        btnLast.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_end_26px_2.png")));

        index = -1;
        clear();

    }

    private void setForm(ThanhVien tv) {
        lblIDKhachHang.setText(tv.getID());
        txtHoTen.setText(tv.getHoTen());
        dcsNgaySinh.setDate(tv.getNgaySinh());
        if (tv.getGioiTinh()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtSoDT.setText(tv.getSoDT());
        txtEmail.setText(tv.getEmail());

    }

    private ThanhVien getForm() {
        ThanhVien tv = new ThanhVien();
        tv.setID(lblIDKhachHang.getText());
        tv.setHoTen(txtHoTen.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dcsNgaySinh.getDate());
        tv.setNgaySinh(DateHelper.toDate(date, 3));
        tv.setGioiTinh(rdoNam.isSelected());
        tv.setSoDT(txtSoDT.getText().replace(" ", ""));
        tv.setEmail(txtEmail.getText());
        return tv;
    }

    private void clear() {
        lblIDKhachHang.setText("");
        txtHoTen.setText("");
        dcsNgaySinh.setDate(DateHelper.now());
        btgGioiTinh.clearSelection();
        txtSoDT.setText("");
        txtEmail.setText("");
        rdoNam.setSelected(true);
        this.index = -1;
        tblKhachHang.clearSelection();
        btnTrangThai();
    }

    public void search() {
        this.fillTable();
        this.clear();
//        this.index = -1;
    }

    private void edit() {
        if (index == -1) {
            return;
        }else{
        try {
            String idtv = (String) tblKhachHang.getValueAt(index, 0);
            tv = dao.selectById(idtv);
            this.setForm(tv);
        } catch (Exception ex) {
            System.out.println("ex "+ ex.getMessage());
        }
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
    
    public void renderFrame() { 
        this.index = -1;
        setButtonState();
        fillTable();
        
        btnTrangThai();
        if (list.size() != 0 && index == -1) {
            index = 0; setSelectedRow();
        }
    }
    
    private void dinhDangBang(){
        TableRendererUtil tbl = new TableRendererUtil(tblKhachHang);
        tbl.changeHeaderStyle(Color.decode("#99FF99"));
        tbl.setColoumnWidthByPersent(1, 10);
        tbl.setRowHeightByPresent(40);
        tblKhachHang.setGridColor(Color.decode("#019267"));
        tblKhachHang.setFocusable(false);
        tblKhachHang.setInheritsPopupMenu(true);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.setSelectionBackground(Color.decode("#019267"));
        tblKhachHang.setSelectionForeground(Color.decode("#FFFFFF"));
        for (int i = 0; i < tblKhachHang.getColumnCount(); i++) {
            tbl.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        tbl.setColoumnWidthByPersent(0, 10);
        tbl.setColoumnWidthByPersent(1, 25);
        tbl.setColoumnWidthByPersent(3, 10);
        
    }
    
    private void setUpInputFocusListener() {
        Color focusColor = ColorAndIconBank.INPUT_COLOR_FOCUS;
        Color unfocusColor = new Color(0, 0, 0, 0);
        InputFocusGUIUtil.setFocusEffect(focusColor, unfocusColor, new JTextField[]{txtHoTen, txtEmail}, jLabel3, jLabel8);

    }

    private void check(ThanhVien tv) throws ValidatorException {
        Validator<ThanhVien> checker = ValidationHelper.getThanhVienChecker();
        checker.check(tv);
    }
    
    private void checkLoi(ThanhVien tv) throws ValidatorException{
        ((KhachHangValidator)validator).checkSoDT(list, tv, txtSoDT.getText().replace(" ", ""));
    }
    
    private boolean checkForm() {
        boolean rong = InputValidlHelper.isEmpty(txtHoTen, txtEmail, txtSoDT);
        if (rong) {
            MessageHelper.message(this, "Ô nhập không được để trống", 1);
            return false;
        }
        return true;
    }
    
    public void btnTrangThai(){
        if (index == -1) {
            btnThem.setEnabled(true); 
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }else{
            btnThem.setEnabled(false); 
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
        }
    }
    
    private int findMatch(ThanhVien tv) {
        ThanhVien m = list.stream().filter(e -> (e.getHoTen().equals(tv.getHoTen())
                && e.getEmail().equals(tv.getEmail())
                && e.getSoDT().equals(tv.getSoDT())
                )).collect(Collectors.toList()).get(0);
        return list.indexOf(m);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGioiTinh = new javax.swing.ButtonGroup();
        pnlBackGround = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        dcsNgaySinh = new com.toedter.calendar.JDateChooser();
        txtHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        lblIDKhachHang = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        txtSoDT = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        txtTim = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mainframe");

        pnlBackGround.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackGround.setPreferredSize(new java.awt.Dimension(690, 0));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-customer-48.png"))); // NOI18N
        jLabel1.setText("Quản Lý Thành Viên");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Mã khách hàng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setFocusable(false);
        tblKhachHang.setGridColor(new java.awt.Color(169, 197, 80));
        tblKhachHang.setSelectionBackground(new java.awt.Color(61, 144, 106));
        tblKhachHang.setSelectionForeground(new java.awt.Color(244, 244, 244));
        tblKhachHang.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        tblKhachHang.setShowGrid(true);
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel", 1, 14))); // NOI18N
        jPanel1.setOpaque(false);

        dcsNgaySinh.setDateFormatString("dd/MM/yyyy");
        dcsNgaySinh.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));

        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txtHoTen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtHoTen.setActionCommand("<Not Set>");
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(74, 140, 74)));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel2.setText("ID");

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Họ tên");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Ngày sinh");

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setText("Giới tính");

        jLabel6.setBackground(new java.awt.Color(204, 204, 204));
        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setText("Số điện thoại");

        btgGioiTinh.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        rdoNam.setText("Nam");
        rdoNam.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        btgGioiTinh.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        rdoNu.setText("Nữ");
        rdoNu.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        lblIDKhachHang.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jLabel8.setBackground(new java.awt.Color(204, 204, 204));
        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setText("Email");

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(74, 140, 74)));

        jPanel5.setBackground(new java.awt.Color(0, 102, 153));
        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        btnThem.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_24px_1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.setMaximumSize(new java.awt.Dimension(153, 44));
        btnThem.setMinimumSize(new java.awt.Dimension(153, 44));
        btnThem.setPreferredSize(new java.awt.Dimension(153, 44));
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem);

        btnSua.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_24px_1.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSua.setMaximumSize(new java.awt.Dimension(153, 44));
        btnSua.setMinimumSize(new java.awt.Dimension(153, 44));
        btnSua.setPreferredSize(new java.awt.Dimension(153, 44));
        btnSua.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel5.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_24px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoa.setMaximumSize(new java.awt.Dimension(153, 44));
        btnXoa.setMinimumSize(new java.awt.Dimension(153, 44));
        btnXoa.setPreferredSize(new java.awt.Dimension(153, 44));
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa);

        btnMoi.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_new_copy_24px_1.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMoi.setMaximumSize(new java.awt.Dimension(153, 44));
        btnMoi.setMinimumSize(new java.awt.Dimension(153, 44));
        btnMoi.setPreferredSize(new java.awt.Dimension(153, 44));
        btnMoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel5.add(btnMoi);

        txtSoDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(74, 140, 74)));
        try {
            txtSoDT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#### ### ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSoDT.setToolTipText("");
        txtSoDT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(lblIDKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(dcsNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel5)
                        .addGap(37, 37, 37)
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(dcsNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblIDKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel5)
                        .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdoNu)
                    .addComponent(rdoNam))
                .addGap(27, 27, 27)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dcsNgaySinh, txtEmail, txtHoTen});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel", 1, 14))); // NOI18N
        jPanel3.setOpaque(false);

        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txtTim.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 204, 153)));
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTim)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setOpaque(false);

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_skip_to_start_26px.png"))); // NOI18N
        btnFirst.setPreferredSize(new java.awt.Dimension(80, 38));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel4.add(btnFirst);

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_left_26px_5.png"))); // NOI18N
        btnPre.setPreferredSize(new java.awt.Dimension(80, 38));
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        jPanel4.add(btnPre);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_right_26px_2.png"))); // NOI18N
        btnNext.setPreferredSize(new java.awt.Dimension(80, 38));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_end_26px.png"))); // NOI18N
        btnLast.setPreferredSize(new java.awt.Dimension(80, 38));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel4.add(btnLast);

        javax.swing.GroupLayout pnlBackGroundLayout = new javax.swing.GroupLayout(pnlBackGround);
        pnlBackGround.setLayout(pnlBackGroundLayout);
        pnlBackGroundLayout.setHorizontalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 20, Short.MAX_VALUE))))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        this.index = tblKhachHang.getSelectedRow();
        btnTrangThai();
        this.edit();
    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_txtTimKeyReleased

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        index = 0;
        setSelectedRow();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        // TODO add your handling code here:
        if (index > 0) {
            index--;
        }
        setSelectedRow();
    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (index < list.size() - 1) {
            index++;
        }
        setSelectedRow();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        index = list.size() - 1;
        setSelectedRow();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (!checkForm()) return;
        else insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if (!checkForm()) return;
        else update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
        tv = null;
    }//GEN-LAST:event_btnMoiActionPerformed

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
            java.util.logging.Logger.getLogger(QLKhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLKhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLKhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLKhachHangFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLKhachHangFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGioiTinh;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private com.toedter.calendar.JDateChooser dcsNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIDKhachHang;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JFormattedTextField txtSoDT;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables

}

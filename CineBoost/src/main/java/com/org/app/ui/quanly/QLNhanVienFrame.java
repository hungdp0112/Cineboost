/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.quanly;

import com.org.app.ui.main.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.NhanVienDao;
import com.org.app.entity.NhanVien;
import com.org.app.helper.DateHelper;
import com.org.app.helper.ImageUtil;
import com.org.app.helper.InputValidlHelper;
import com.org.app.helper.JDBCHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.message.MessageDialog;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.InputFocusGUIUtil;
import com.org.app.util.LockDateChooser;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.NhanVienValidator;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.ShowPasswordInterfaceUtil;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.org.app.util.ValidationHelper;
import com.org.app.util.Validator;
import com.org.app.util.ValidatorException;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author intfs
 */
public class QLNhanVienFrame extends javax.swing.JFrame implements SubFrame<QLNhanVienFrame>, SubPanelCreatorInterfaces<SubFrame>, ShowPasswordInterfaceUtil {
    public static final String CARD_NAME_MAIN = "nhanvien";
    
    SubPanelCreator<QLNhanVienFrame> subPanel;
    NhanVienDao dao = new NhanVienDao();
    Validator<NhanVien> validator =  ValidationHelper.getNhanVienChecker();
    String path = "src/main/resources/images/avatars/user1.png";
    File file = new File(path);
    List<NhanVien> list;
    NhanVien nv = null;
    private int index;
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
    
    private String THONG_BAO = "Thông báo";
    private String BAO_LOI = "Báo lỗi";
    private String MSG_COLOR = "#6699FF";
    private String TEXT_BTN = "Xác nhận";
    
    /**
     * Creates new form mainFrame
     */
    public QLNhanVienFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        pnlBackGround.requestFocus();
        setUp();
    }

    public void renderFrame() {
        fillTable();
        btnTrangThai();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            list = dao.selectAll();
            for (NhanVien tv : list) {
                Object[] row = {
                    tv.getId(),
                    tv.getHoten(),
                    DateHelper.toString(tv.getNgaysinh()),
                    tv.getGioitinh() ? "Nữ" : "Nam",
                    tv.getSodt(),
                    tv.getEmail(),
                    tv.getTentk(),
                    tv.getAnh(),
                    tv.getVaitro() ? "Quản lý" : "Nhân viên"
                };
                model.addRow(row);
            }
            if (list.size() != 0 && index == -1) {
                index = 0; setSelectedRow();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Lỗi truy vấn dữ liệu!", 0);
        }
    }
    
    public void insert() {
        file = new File(path);
        ImageUtil.saveImage("src/main/resources/images/avatars", file.getName(), file);
        try {
            NhanVien model = getForm();
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

    public void update(){
        file = new File(path);
        ImageUtil.saveImage("src/main/resources/images/avatars", file.getName(), file);
        try {
            NhanVien model = getForm();
            check(model);
            checkLoi(nv);
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
        String makh = lblIDNhanVien.getText();
        try {
            dao.delete(makh);
            this.fillTable();
            this.clear();
            MessageHelper.message(this, "Xóa thành công!", 2);
            if(index >= list.size() - 1) index--;
            setSelectedRow();
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Xóa thất bại", 0);

        }
    }

    private void setSelectedRow() {
        if (index == -1 || index > list.size() - 1) {
            index = -1;
            return;
        }
        tblNhanVien.setRowSelectionInterval(index, index);
        tblNhanVien.scrollRectToVisible(new java.awt.Rectangle(tblNhanVien.getCellRect(index, 0, true)));

        updateControlState();
        btnTrangThai();
        setForm(list.get(index));
        nv = list.get(index);
    }

    private void updateControlState() {
        boolean first = index <= 0;
        boolean last = index == list.size() - 1;

        btnFirst.setEnabled(!first);
        btnLast.setEnabled(!last);
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

    private void setForm(NhanVien tv) {
        lblIDNhanVien.setText(tv.getId());
        txtHoTen.setText(tv.getHoten());
        dcsNgaySinh.setDate(tv.getNgaysinh());
        cboGioiTinh.setSelectedItem(tv.getGioitinh() == true ? "Nữ" : "Nam");
        txtSoDT.setText(tv.getSodt());
        txtEmail.setText(tv.getEmail());
        txtTenTK.setText(tv.getTentk());
        txtMatKhau.setText(tv.getMatkhau());
        txtXacNhanMK.setText(tv.getMatkhau());
        //lblAnh.setIcon(new ImageIcon("user1.png"));
        //
        if (tv.getAnh() != null) {
            lblAnh.setToolTipText(tv.getAnh());
//            System.out.println(ImageUtil.class.getResource("/images/avatars/" + tv.getAnh()));
            lblAnh.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getAvtImage(tv.getAnh()), lblAnh));
        } else {
            lblAnh.setToolTipText("user1.png");
            lblAnh.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getAvtImage("user1.png"), lblAnh));
        }
        //
        cboVaiTro.setSelectedItem(tv.getVaitro() == true ? "Quản lý" : "Nhân viên");
    }

    private NhanVien getForm() {
        NhanVien tv = new NhanVien();
        tv.setId(lblIDNhanVien.getText());
        tv.setHoten(txtHoTen.getText());
        //tv.setNgaysinh((Date) dcsNgaySinh.getDate());
        tv.setGioitinh(cboGioiTinh.getSelectedItem().equals("Nam") ? false : true);
        tv.setSodt(txtSoDT.getText().replace(" ", ""));
        tv.setEmail(txtEmail.getText());
        tv.setTentk(txtTenTK.getText());
        tv.setMatkhau(String.valueOf(txtMatKhau.getPassword()));
        //tv.setAnh("user1.png");
        //
        tv.setAnh(lblAnh.getToolTipText());
        //
        tv.setVaitro(cboVaiTro.getSelectedItem().equals("Nhân viên") ? false : true);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dcsNgaySinh.getDate());
        tv.setNgaysinh(DateHelper.toDate(date, 3));
        return tv;

    }

    private void clear() {        
        lblIDNhanVien.setText("");
        txtHoTen.setText("");
        dcsNgaySinh.setDate(DateHelper.now());
        cboVaiTro.setSelectedIndex(0);
        txtSoDT.setText("");
        txtEmail.setText("");
        txtTenTK.setText("");
        txtMatKhau.setText("");
        txtXacNhanMK.setText("");
        cboVaiTro.setSelectedIndex(0);
        lblAnh.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getAvtImage("user1.png"), lblAnh));
        index = -1;
        tblNhanVien.clearSelection();
        btnTrangThai();
    }
    
    
    private void edit() {
        try {
            String idtv = (String) tblNhanVien.getValueAt(index, 0);
            nv = dao.selectById(idtv);
            this.setForm(nv);
            btnTrangThai();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //
    void chonAnh() {
        Path current = Paths.get(System.getProperty("user.dir"), "src","main", "resources", "images", "avatars");
        JFileChooser chooser = new JFileChooser(current.toFile());
        chooser.setFileFilter(new FileNameExtensionFilter("PNG,JPG", "png", "jpg"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            ImageIcon poster = new ImageIcon(file.getAbsolutePath());
            SettingIconHelper.setIconFor(lblAnh, poster);
            lblAnh.setToolTipText(file.getName());
        }
    }

    //
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

    private void setUpInputFocusListener() {
        Color focusColor = ColorAndIconBank.INPUT_COLOR_FOCUS;
        Color unfocusColor = new Color(0,0,0,0);
        // Color focusLabelColor = new Color(255, 255, 255, 255);
        //InputFocusGUIUtil.setFocusEffect(focusColor, unfocusColor, new JTextField[]{txtHoTen, txtSoDT, txtEmail, txtTenTK, txtMatKhau, txtXacNhanMK}, focusLabelColor, jLabel4, jLabel8, jLabel7, jLabel15, jLabel13, jLabel14);
        InputFocusGUIUtil.setFocusEffect(focusColor, unfocusColor, new JTextField[]{txtHoTen, txtEmail, txtTenTK, txtMatKhau, txtXacNhanMK}, jLabel4, jLabel7, jLabel15, jLabel13, jLabel14);

    }
    
    private void check(NhanVien nv) throws ValidatorException {
        Validator<NhanVien> checker = ValidationHelper.getNhanVienChecker();
        checker.check(nv);
    }

    private void checkLoi(NhanVien nv) throws ValidatorException{
        ((NhanVienValidator)validator).checkGmail(list, nv, txtEmail.getText());
        ((NhanVienValidator)validator).checkMatKhau(String.valueOf(txtMatKhau.getPassword()).trim(), String.valueOf(txtXacNhanMK.getPassword()).trim());
        ((NhanVienValidator)validator).checkTenTK(list, nv, txtTenTK.getText());
    }
    
    private boolean checkForm() {
        boolean rong = InputValidlHelper.isEmptyPass(txtHoTen, txtEmail, txtSoDT, txtTenTK, txtMatKhau, txtXacNhanMK);
        if (rong) {
            MessageHelper.message(this, "Ô nhập không được để trống", 1);
            return false;
        }
        return true;
    }
    
    private void dinhDangBang(){
        TableRendererUtil tbl = new TableRendererUtil(tblNhanVien);
        tbl.changeHeaderStyle(Color.decode("#6699FF"));
        tbl.setColoumnWidthByPersent(1, 10);
        tbl.setRowHeightByPresent(40);
        tblNhanVien.setGridColor(Color.decode("#0093AB"));
        tblNhanVien.setFocusable(false);
        tblNhanVien.setInheritsPopupMenu(true);
        tblNhanVien.setShowGrid(true);
        tblNhanVien.setSelectionBackground(Color.decode("#0093AB"));
        tblNhanVien.setSelectionForeground(Color.decode("#FFFFFF"));
        for (int i = 0; i < tblNhanVien.getColumnCount(); i++) {
            tbl.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        tbl.setColoumnWidthByPersent(0, 10);
        tbl.setColoumnWidthByPersent(1, 20);
        tbl.setColoumnWidthByPersent(3, 9);
        tbl.setColoumnWidthByPersent(2, 12);
        tbl.setColoumnWidthByPersent(4, 14);
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

    private void setUp() {
        dinhDangBang();
        lblHover.setVisible(false);
        JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dcsNgaySinh.getDateEditor();
        txtFld.setBackground(pnlBackGround.getBackground());
        txtFld.setHorizontalAlignment(SwingConstants.CENTER);
        txtFld.setBorder(txtHoTen.getBorder());
        txtSoDT.setBorder(txtHoTen.getBorder());
        
        SettingIconHelper.setIconSourceFor(lblHide, "eye_disable.png");
        SettingIconHelper.setIconSourceFor(lblHide_XN, "eye_disable.png");
        //
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnFirst, btnLast, btnNext, btnPre);
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnThem, btnSua, btnXoa, btnMoi);
        //
        
        LockDateChooser.lock(dcsNgaySinh);
        setButtonState();
        setUpInputFocusListener();
    }
    

    private int findMatch(NhanVien nv) {
        NhanVien m = list.stream().filter(e -> (e.getHoten().equals(nv.getHoten())
                && e.getEmail().equals(nv.getEmail())
                && e.getTentk().equals(nv.getTentk())
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

        jProgressBar1 = new javax.swing.JProgressBar();
        fileChooser = new javax.swing.JFileChooser();
        jTextField1 = new javax.swing.JTextField();
        pnlBackGround = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblIDNhanVien = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        dcsNgaySinh = new com.toedter.calendar.JDateChooser();
        txtHoTen = new javax.swing.JTextField();
        txtSoDT = new javax.swing.JFormattedTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenTK = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboVaiTro = new javax.swing.JComboBox<>();
        lblHide = new javax.swing.JLabel();
        lblHide_XN = new javax.swing.JLabel();
        txtMatKhau = new com.org.app.customui.PasswordTextField();
        txtXacNhanMK = new com.org.app.customui.PasswordTextField();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        pnlHinh = new javax.swing.JLayeredPane();
        lblHover = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mainframe");

        pnlBackGround.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackGround.setPreferredSize(new java.awt.Dimension(690, 0));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Quản Lý Nhân Viên");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        tblNhanVien.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID NV", "Họ tên", "Ngày sinh", "Giới tính", "Số điện thoại", "Email", "Tài khoản", "Ảnh", "Vai trò"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setFocusable(false);
        tblNhanVien.setGridColor(new java.awt.Color(169, 197, 80));
        tblNhanVien.setName("tblNhanVien"); // NOI18N
        tblNhanVien.setSelectionBackground(new java.awt.Color(61, 144, 106));
        tblNhanVien.setSelectionForeground(new java.awt.Color(244, 244, 244));
        tblNhanVien.setShowGrid(true);
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel1.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 16)); // NOI18N
        jLabel3.setText("ID:");

        lblIDNhanVien.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblIDNhanVien.setForeground(new java.awt.Color(102, 153, 255));
        lblIDNhanVien.setName("lblIDNhanVien"); // NOI18N
        lblIDNhanVien.setPreferredSize(new java.awt.Dimension(67, 29));

        jPanel5.setBackground(new java.awt.Color(150, 209, 226));
        jPanel5.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Họ tên");

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setText("Ngày sinh");

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setText("Điện thoại");

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setText("Email");

        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txtEmail.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(102, 153, 255)));

        dcsNgaySinh.setDateFormatString("dd/MM/yyyy");
        dcsNgaySinh.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N

        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txtHoTen.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtHoTen.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(102, 153, 255)));
        txtHoTen.setName("txtHoTen"); // NOI18N

        txtSoDT.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(102, 153, 255)));
        try {
            txtSoDT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#### ### ###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtSoDT.setToolTipText("");
        txtSoDT.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel4)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcsNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoDT)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dcsNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSoDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(150, 209, 226));
        jPanel6.setOpaque(false);

        jLabel15.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel15.setText("Tài khoản");

        jLabel13.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel13.setText("Mật khẩu");

        jLabel14.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel14.setText("Xác nhận");

        txtTenTK.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 0, 0)));
        txtTenTK.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtTenTK.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(102, 153, 255)));

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setText("Giới tính");

        cboGioiTinh.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.setFocusable(false);

        jLabel11.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel11.setText("Vai trò");

        cboVaiTro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cboVaiTro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));
        cboVaiTro.setFocusable(false);

        lblHide.setToolTipText("Nhấn giữ để hiện Password");
        lblHide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHideMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblHideMouseReleased(evt);
            }
        });

        lblHide_XN.setToolTipText("Nhấn giữ để hiện Password");
        lblHide_XN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHide_XN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHide_XNMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblHide_XNMouseReleased(evt);
            }
        });

        txtMatKhau.setUnderlineColor(new java.awt.Color(102, 153, 255));

        txtXacNhanMK.setUnderlineColor(new java.awt.Color(102, 153, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cboVaiTro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(8, 8, 8)
                                        .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addGap(9, 9, 9)
                                        .addComponent(txtXacNhanMK, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblHide, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHide_XN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblHide, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtXacNhanMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblHide_XN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVaiTro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        jPanel3.setBackground(new java.awt.Color(0, 102, 153));
        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        btnThem.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_24px_1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setFocusable(false);
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.setMaximumSize(new java.awt.Dimension(153, 44));
        btnThem.setPreferredSize(new java.awt.Dimension(153, 44));
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel3.add(btnThem);

        btnSua.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_24px_1.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setFocusable(false);
        btnSua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSua.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel3.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_24px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setFocusable(false);
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel3.add(btnXoa);

        btnMoi.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_new_copy_24px_1.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setFocusable(false);
        btnMoi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel3.add(btnMoi);

        lblHover.setForeground(new java.awt.Color(255, 255, 255));
        lblHover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHoverMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHoverMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHoverMouseExited(evt);
            }
        });

        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAnhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAnhMouseExited(evt);
            }
        });

        pnlHinh.setLayer(lblHover, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pnlHinh.setLayer(lblAnh, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout pnlHinhLayout = new javax.swing.GroupLayout(pnlHinh);
        pnlHinh.setLayout(pnlHinhLayout);
        pnlHinhLayout.setHorizontalGroup(
            pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
            .addGroup(pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlHinhLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblHover, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlHinhLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        pnlHinhLayout.setVerticalGroup(
            pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 142, Short.MAX_VALUE)
            .addGroup(pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlHinhLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblHover, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(pnlHinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlHinhLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlHinh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblIDNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlHinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setOpaque(false);

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_skip_to_start_26px.png"))); // NOI18N
        btnFirst.setFocusable(false);
        btnFirst.setPreferredSize(new java.awt.Dimension(80, 38));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        jPanel4.add(btnFirst);

        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_left_26px_5.png"))); // NOI18N
        btnPre.setFocusable(false);
        btnPre.setPreferredSize(new java.awt.Dimension(80, 38));
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        jPanel4.add(btnPre);

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_right_26px_2.png"))); // NOI18N
        btnNext.setFocusable(false);
        btnNext.setPreferredSize(new java.awt.Dimension(80, 38));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext);

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_end_26px.png"))); // NOI18N
        btnLast.setFocusable(false);
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
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)))
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(192, 192, 192))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 702, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 654, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:
        this.index = tblNhanVien.getSelectedRow();
        this.edit();
    }//GEN-LAST:event_tblNhanVienMouseClicked

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

    private void lblHideMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMousePressed
        SettingIconHelper.setIconSourceFor(lblHide, "eye_enable.png");
        showPassword(txtMatKhau);
    }//GEN-LAST:event_lblHideMousePressed

    private void lblHideMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMouseReleased
        SettingIconHelper.setIconSourceFor(lblHide, "eye_disable.png");
        hidePassword(txtMatKhau);
    }//GEN-LAST:event_lblHideMouseReleased

    private void lblHide_XNMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHide_XNMousePressed
        // TODO add your handling code here:
        SettingIconHelper.setIconSourceFor(lblHide_XN, "eye_enable.png");
        showPassword(txtXacNhanMK);
    }//GEN-LAST:event_lblHide_XNMousePressed

    private void lblHide_XNMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHide_XNMouseReleased
        // TODO add your handling code here:
        SettingIconHelper.setIconSourceFor(lblHide_XN, "eye_disable.png");
        hidePassword(txtXacNhanMK);
    }//GEN-LAST:event_lblHide_XNMouseReleased

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
        file = null;
        nv = null;
    }//GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        if(!checkForm()) return;
        else update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if(!checkForm()) return;
        else insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void lblHoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblHoverMouseClicked

    private void lblHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseEntered
        // TODO add your handling code here:
        lblHover.setVisible(true);
        lblHover.setOpaque(true);
        lblHover.setText("<html> Double click<br>để chọn ảnh</html>");
        lblHover.setBackground(new Color(0, 0, 0, 205));
    }//GEN-LAST:event_lblHoverMouseEntered

    private void lblHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseExited
        // TODO add your handling code here:
        lblHover.setVisible(false);
    }//GEN-LAST:event_lblHoverMouseExited

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked
        // TODO add your handling code here:
//        if (evt.getClickCount() == 2) {
//            chonAnh();
//        }
    }//GEN-LAST:event_lblAnhMouseClicked

    private void lblAnhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseEntered
        // TODO add your handling code here:
        lblHover.setVisible(true);
        lblHover.setOpaque(true);

        lblHover.setBackground(new Color(0, 0, 0, 50));
    }//GEN-LAST:event_lblAnhMouseEntered

    private void lblAnhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseExited
        // TODO add your handling code here:
        lblHover.setVisible(false);
    }//GEN-LAST:event_lblAnhMouseExited

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
            java.util.logging.Logger.getLogger(QLNhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLNhanVienFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLNhanVienFrame nvf = new QLNhanVienFrame();
                nvf.renderFrame(); nvf.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<String> cboVaiTro;
    private com.toedter.calendar.JDateChooser dcsNgaySinh;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblHide;
    private javax.swing.JLabel lblHide_XN;
    private javax.swing.JLabel lblHover;
    private javax.swing.JLabel lblIDNhanVien;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JLayeredPane pnlHinh;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private com.org.app.customui.PasswordTextField txtMatKhau;
    private javax.swing.JFormattedTextField txtSoDT;
    private javax.swing.JTextField txtTenTK;
    private com.org.app.customui.PasswordTextField txtXacNhanMK;
    // End of variables declaration//GEN-END:variables

    @Override
    public SubPanelCreator getSubPanelCreator() {
        return this.subPanel;
    }

}

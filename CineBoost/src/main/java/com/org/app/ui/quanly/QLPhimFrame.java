/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.quanly;

import com.org.app.ui.main.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.PhimDao;
import com.org.app.controller.SuatChieuDao;
import com.org.app.entity.NhanVien;
import com.org.app.entity.Phim;
import com.org.app.helper.Authenticator;
import com.org.app.helper.DateHelper;
import com.org.app.helper.ImageUtil;
import com.org.app.helper.InputValidlHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.message.MessageDialog;
import com.org.app.ui.banhang.LichChieuTrongNgayFrame;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.InputFocusGUIUtil;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.org.app.util.ValidationHelper;
import com.org.app.util.Validator;
import com.org.app.util.ValidatorException;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author intfs
 */
public class QLPhimFrame extends javax.swing.JFrame implements SubFrame<QLPhimFrame>, SubPanelCreatorInterfaces<SubFrame> {
    public static final String CARD_NAME_MAIN = "phim";
    
    SubPanelCreator<QLPhimFrame> subPanel;
    DefaultTableModel model;
    PhimDao dao = new PhimDao();
    int row = -1;
    String path = "src/main/resources/images/posters/poster1.png";
    File file = new File(path);
    
    List<Phim> list;

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

    //
    public QLPhimFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        dinhDangBang();
       
//        renderFrame();
        pnlBackGround.requestFocus();   
        dcsNgayChieu.getDateEditor().getUiComponent().setName("txtNgayChieu");
    }
        
    public void fillTable() {
        model = (DefaultTableModel) tblPhim.getModel();
        model.setRowCount(0);
        try {
            list = dao.selectAll();
            for (Phim p : list) {
                Object[] row = {
                    p.getId(),
                    p.getTen(),
                    p.getThoiLuong(),
                    DateHelper.toString(p.getNgayKhoiChieu()),
                    p.getTheloai(),
                    p.getNgonNgu(),
                    p.getTrangThai() ? "Đang chiếu" : "Dừng chiếu",
                    p.getTomTat(),};
                model.addRow(row);
            }
//            if (list.size() != 0 &row == -1) row = 0; edit();
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Lỗi truy vấn dữ liệu!");
        }
    }

    public void setUp() {
        System.out.println("setUp");
        lblHover.setVisible(false);
        
        JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dcsNgayChieu.getDateEditor();
        txtFld.setBackground(pnlBackGround.getBackground());
        txtFld.setHorizontalAlignment(SwingConstants.CENTER);
        txtFld.setBorder(txtTenPhim.getBorder());
        MouseHoverEffect
                .mouseOverEffectButton(Color.decode("#F2B35F"), btnThem, btnSua, btnXoa, btnMoi);
        setButtonState();
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

        clear();
        //changeFormButtonState();
    }
    
    //
    public void insert() {
        file = new File(path);
        ImageUtil.saveImage("src/main/resources/images/posters", file.getName(), file);

        try {
            Phim model = getForm();
            check(model);
            dao.insert(model);
            this.fillTable();
            int index = list.size() -1;
            MessageHelper.message(this, "Thêm mới thành công!", 2);
            setSelectedPhim(list.get(index));
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, e.getMessage(), 0);
        }
    }
    
    public void update() {
        file = new File(path);
        ImageUtil.saveImage("src/main/resources/images/posters", file.getName(), file);
        try {
            Phim model = getForm();
            check(model);
            if(isPhimHasSuat(model, model.getId())) {
                
                MessageHelper.message(this, "Phim đã có suất chiếu không thể chỉnh thời lượng ", MessageHelper.ERROR_MESSAGE);
                return;
            }
            dao.update(model);
            this.fillTable();
            MessageHelper.message(this, "Cập nhật thành công!", 2);
            setSelectedPhim(model);
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, e.getMessage(), 3);
        }
    }
    
    public boolean isPhimHasSuat(Phim moi, String idPhim) throws Exception {
        SuatChieuDao scDao = new SuatChieuDao();
            int luongSuat = scDao.getLuongSuatOfPhim(idPhim);
        if((moi.getThoiLuong().compareTo(list.get(list.indexOf(moi)).getThoiLuong())) != 0) {
            return luongSuat > 0;
        }
        return false;
    }
    public void delete() {
        String id_phim = String.valueOf(tblPhim.getValueAt(row, 0));
        int pre = row;
        try {
            dao.delete(id_phim);
            this.fillTable();
            this.clear();
            MessageHelper.message(this, "Xóa thành công!", 2);   
            if(pre > list.size()-1) pre--;
            if(pre != -1) {
                System.out.println("set row "+ row);
                row = pre;
                tblPhim.setRowSelectionInterval(row, row);
                edit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Xóa thất bại!", 0);
        }
    }

    public void search() {
        String keyword = txtTim.getText();
        TableRowSorter<DefaultTableModel> trs = new TableRowSorter<>(model);
        tblPhim.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter(keyword));
        this.fillTable();
        this.clear();
        this.row = -1;
    }

    private void setForm(Phim p) {
        txtTenPhim.setText(p.getTen());
        spnGio.setValue(p.getThoiLuong() / 60);
        spnPhut.setValue(p.getThoiLuong() % 60);
        dcsNgayChieu.setDate(p.getNgayKhoiChieu());
//        System.out.println("Trang thai " + p.getTrangThai());
        if (p.getTrangThai() == true) {
            rdoDangChieu.setSelected(true);
        } else {
            rdoDungChieu.setSelected(true);
        }
        cboTheLoai.setSelectedItem(p.getTheloai());
        cboNgonNgu.setSelectedItem(p.getNgonNgu());
        txtTomTat.setText(p.getTomTat());
        if (p.getPoster() != null) {
            lblPoster.setToolTipText(p.getPoster());
            lblPoster.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getPosterImage(p.getPoster()), lblPoster));
        } else {
            lblPoster.setToolTipText("poster1.png");
            lblPoster.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getPosterImage("poster1.png"), lblPoster));
        }
    }

    private Phim getForm() {
        Phim p = new Phim();
        if (row != -1) {
            String idPhim = (String) tblPhim.getValueAt(row, 0);
            p.setId(idPhim);
        }
        p.setTen(txtTenPhim.getText());
        int h = (int) spnGio.getValue();
        int m = (int) spnPhut.getValue();
        int thoiluong = (h * 60) + m;
        p.setThoiLuong(thoiluong);
        p.setNgayKhoiChieu(DateHelper.toDate(dcsNgayChieu.getDate()));
        p.setTrangThai(rdoDangChieu.isSelected() ? true : false);
        p.setTheloai(String.valueOf(cboTheLoai.getSelectedItem()));
        p.setNgonNgu(String.valueOf(cboNgonNgu.getSelectedItem()));
        p.setTomTat(txtTomTat.getText());
        p.setPoster(lblPoster.getToolTipText());
        p.setNv(Authenticator.USER.getId());
//        p.setNv("NV00001");
        return p;
    }

    private void clear() {
        txtTenPhim.setText("");
        dcsNgayChieu.setDate(DateHelper.now());
        spnGio.setValue(0);
        spnPhut.setValue(0);
        rdoDangChieu.setSelected(true);
        cboTheLoai.setSelectedIndex(0);
        cboNgonNgu.setSelectedIndex(0);
        txtTomTat.setText("");
        lblPoster.setToolTipText("poster1.png");
        lblPoster.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getPosterImage("poster1.png"), lblPoster));
        tblPhim.clearSelection();
        this.row = -1;
        btnTrangThai();
    }

    private void edit() {
        try {
            String idPhim = (String) tblPhim.getValueAt(row, 0);
            Phim p = dao.selectById(idPhim);

            tblPhim.setRowSelectionInterval(row, row);
            tblPhim.scrollRectToVisible(new java.awt.Rectangle(tblPhim.getCellRect(row, 0, true)));
            btnTrangThai();
            setForm(p);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setSelectedPhim(Phim p) {
//        System.out.println("list size = "+ list.size());
        int i = list.indexOf(p);
//        System.out.println("i in phim = " + i);
        
        if(i == -1) return;
        
        row = i;
        tblPhim.setRowSelectionInterval(row, row);
        edit();
        
    }
    void chonAnh() {
        Path current = Paths.get(System.getProperty("user.dir"), "src","main", "resources", "images", "posters");
        fileChooser = new JFileChooser(current.toFile());
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            fileChooser.setFileFilter(new FileNameExtensionFilter("PNG,JPG", "png", "jpg"));
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            file = fileChooser.getSelectedFile();
            ImageIcon poster = new ImageIcon(file.getAbsolutePath());
            SettingIconHelper.setIconFor(lblPoster, poster);
            lblPoster.setToolTipText(file.getName());
//            JOptionPane.showMessageDialog(this, lblPoster.getToolTipText());
        }
    }

    private void check(Phim p) throws ValidatorException {
        Validator<Phim> checker = ValidationHelper.getPhimChecker();
        checker.check(p);
        
    }
    
    private boolean checkForm() {
        boolean rong = InputValidlHelper.isEmpty(txtTenPhim);
        if (rong) {
            MessageHelper.message(this, "Ô nhập không được để trống", 3);
            return false;
        }
        
        int gio = (int) spnGio.getValue();
        int phut = (int) spnPhut.getValue();
        int thoiluong = (gio*60) + phut;
        if (gio == 0 && phut == 0) {
            MessageHelper.message(this, "Bạn chưa chọn thời lượng", 1);
            return false;
        }
        
        return true;
    }

    @Override
    public SubPanelCreator getSubPanelCreator() {
        return subPanel;
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
                setUp();
                renderFrame();
            }
        };
    }

    private void setUpInputFocusListener() {
        Color focusColor = ColorAndIconBank.INPUT_COLOR_FOCUS;
        Color unfocusColor = new Color(0, 0, 0, 0);
        InputFocusGUIUtil.setFocusEffect(focusColor, unfocusColor, new JTextField[]{txtTenPhim, txtTim}, jLabel3, jLabel8);
//        InputFocusGUIUtil.setFocusEfsfect(focusColor, unfocusColor , txtUserName,jLabel1);
    }

    public void renderFrame() {      
        row = -1;
        setUpInputFocusListener();
        rdoDangChieu.setSelected(true);
        // Poster mặc định
        lblPoster.setToolTipText("poster1.png");
        lblPoster.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getPosterImage("poster1.png"), lblPoster));
        setButtonState();
        txtTomTat.setLineWrap(true);
        txtTomTat.setWrapStyleWord(true);
        this.fillTable();
        if (list.size() > 0 && row == -1) {
            row = 0; edit();
        }  
    }
    
    private void dinhDangBang(){
        TableRendererUtil tbl = new TableRendererUtil(tblPhim);
        tbl.changeHeaderStyle(Color.decode("#FFCC99"));
        tbl.setColoumnWidthByPersent(1, 10);
        tbl.setRowHeightByPresent(40);
        tblPhim.setGridColor(Color.decode("#E2703A"));
        tblPhim.setFocusable(false);
        tblPhim.setInheritsPopupMenu(true);
        tblPhim.setShowGrid(true);
        tblPhim.setSelectionBackground(Color.decode("#E2703A"));
        tblPhim.setSelectionForeground(Color.decode("#FFFFFF"));
        for (int i = 0; i < tblPhim.getColumnCount(); i++) {
            tbl.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        tbl.setColoumnWidthByPersent(0, 8);
        tbl.setColoumnWidthByPersent(1, 20);
    }
    
        public void btnTrangThai(){
        if (row == -1) {
            btnThem.setEnabled(true); 
            btnSua.setEnabled(false);
            btnXoa.setEnabled(false);
        }else{
            btnThem.setEnabled(false); 
            btnSua.setEnabled(true);
            btnXoa.setEnabled(true);
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

        btgTrangThai = new javax.swing.ButtonGroup();
        fileChooser = new javax.swing.JFileChooser();
        jSpinner1 = new javax.swing.JSpinner();
        pnlBackGround = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhim = new javax.swing.JTable();
        txtTim = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        rdoDangChieu = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTomTat = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        dcsNgayChieu = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtTenPhim = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        spnGio = new javax.swing.JSpinner();
        rdoDungChieu = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        spnPhut = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cboTheLoai = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboNgonNgu = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        pnlPoster = new javax.swing.JLayeredPane();
        lblHover = new javax.swing.JLabel();
        lblPoster = new javax.swing.JLabel();

        fileChooser.setName("fileChooser"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mainframe");

        pnlBackGround.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackGround.setPreferredSize(new java.awt.Dimension(690, 0));

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_movie_beginning_48px_1.png"))); // NOI18N
        jLabel1.setText("Quản Lý Phim");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        tblPhim.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Phim", "Tên phim", "Thời lượng", "Ngày chiếu", "Thể loại", "Ngôn ngữ", "Trạng thái", "Tóm tắt"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhim.setFocusable(false);
        tblPhim.setGridColor(new java.awt.Color(169, 197, 80));
        tblPhim.setInheritsPopupMenu(true);
        tblPhim.setName("tblPhim"); // NOI18N
        tblPhim.setSelectionBackground(new java.awt.Color(61, 144, 106));
        tblPhim.setSelectionForeground(new java.awt.Color(244, 244, 244));
        tblPhim.setShowGrid(true);
        tblPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhimMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhim);

        txtTim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 204, 153)));
        txtTim.setName("txtTim"); // NOI18N
        txtTim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel8.setText("Tìm kiếm");

        jPanel3.setOpaque(false);

        btgTrangThai.add(rdoDangChieu);
        rdoDangChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        rdoDangChieu.setText("Đang chiếu");
        rdoDangChieu.setName("rdoDangChieu"); // NOI18N

        txtTomTat.setColumns(20);
        txtTomTat.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtTomTat.setRows(5);
        txtTomTat.setName("txtTomTat"); // NOI18N
        jScrollPane2.setViewportView(txtTomTat);

        jLabel4.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel4.setText("Thời lượng");

        dcsNgayChieu.setDateFormatString("dd/MM/yyyy");
        dcsNgayChieu.setName("dcsNgayChieu"); // NOI18N

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setText("Tóm tắt");

        txtTenPhim.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtTenPhim.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 204, 153)));
        txtTenPhim.setName("txtTenPhim"); // NOI18N

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setText("Trạng thái");

        spnGio.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        spnGio.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spnGio.setToolTipText("Giờ");
        spnGio.setName("spnGio"); // NOI18N

        btgTrangThai.add(rdoDungChieu);
        rdoDungChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        rdoDungChieu.setText("Dừng chiếu");
        rdoDungChieu.setName("rdoDungChieu"); // NOI18N

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setText("Ngày chiếu");

        spnPhut.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        spnPhut.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));
        spnPhut.setToolTipText("Phút");
        spnPhut.setName("spnPhut"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("Tên phim");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel2.setText(":");

        jLabel9.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel9.setText("Thể loại");

        cboTheLoai.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cboTheLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hành động", "Kinh dị", "Tâm lý", "Hài Hước", "Hoạt hình", "Khoa học viễn tưởng", "Phiêu lưu", "Tình cảm" }));
        cboTheLoai.setName("cboTheLoai"); // NOI18N

        jLabel10.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel10.setText("Ngôn ngữ");

        cboNgonNgu.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cboNgonNgu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiếng Việt", "Tiếng Anh", "Tiếng Nhật", "Tiếng Pháp", "Tiếng Nga" }));
        cboNgonNgu.setName("cboNgonNgu"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoDangChieu)
                                .addGap(24, 24, 24)
                                .addComponent(rdoDungChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenPhim, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(spnGio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spnPhut, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcsNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addComponent(txtTenPhim))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(spnGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnPhut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(dcsNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(rdoDangChieu))
                    .addComponent(rdoDungChieu))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboTheLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cboNgonNgu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(0, 102, 153));
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(360, 44));
        jPanel5.setLayout(new java.awt.GridLayout(1, 0, 3, 0));

        btnThem.setBackground(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_24px_1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnThem.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnThem.setMaximumSize(new java.awt.Dimension(153, 44));
        btnThem.setName("btnThem"); // NOI18N
        btnThem.setPreferredSize(new java.awt.Dimension(153, 44));
        btnThem.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem);

        btnSua.setBackground(new java.awt.Color(255, 255, 255));
        btnSua.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_24px_1.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSua.setName("btnSua"); // NOI18N
        btnSua.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel5.add(btnSua);

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_24px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnXoa.setName("btnXoa"); // NOI18N
        btnXoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa);

        btnMoi.setBackground(new java.awt.Color(255, 255, 255));
        btnMoi.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_new_copy_24px_1.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnMoi.setName("btnMoi"); // NOI18N
        btnMoi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        jPanel5.add(btnMoi);

        pnlPoster.setBackground(new java.awt.Color(204, 204, 204));
        pnlPoster.setOpaque(true);

        lblHover.setForeground(new java.awt.Color(255, 255, 255));
        lblHover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHover.setText("Double click để chọn poster");
        lblHover.setName("lblHover"); // NOI18N
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
        pnlPoster.add(lblHover);
        lblHover.setBounds(0, 0, 190, 233);

        lblPoster.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPosterMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblPosterMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblPosterMouseExited(evt);
            }
        });
        pnlPoster.add(lblPoster);
        lblPoster.setBounds(0, 0, 190, 233);

        javax.swing.GroupLayout pnlBackGroundLayout = new javax.swing.GroupLayout(pnlBackGround);
        pnlBackGround.setLayout(pnlBackGroundLayout);
        pnlBackGroundLayout.setHorizontalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(pnlPoster, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBackGroundLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlBackGroundLayout.setVerticalGroup(
            pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBackGroundLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlPoster, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBackGround, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBackGround, javax.swing.GroupLayout.PREFERRED_SIZE, 651, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblPhimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhimMouseClicked
        // TODO add your handling code here:
        this.row = tblPhim.getSelectedRow();
        this.edit();
    }//GEN-LAST:event_tblPhimMouseClicked

    private void txtTimKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKeyReleased
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_txtTimKeyReleased

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if(!checkForm()) return;
        else insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if(!checkForm()) return;
        else update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
            delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
        file = null;
    }//GEN-LAST:event_btnMoiActionPerformed

    private void lblPosterMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPosterMouseEntered
        // TODO add your handling code here:
        lblHover.setVisible(true);
        lblHover.setOpaque(true);

        lblHover.setBackground(new Color(0, 0, 0, 50));
    }//GEN-LAST:event_lblPosterMouseEntered

    private void lblPosterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPosterMouseExited
        // TODO add your handling code here:
        lblHover.setVisible(false);
    }//GEN-LAST:event_lblPosterMouseExited

    private void lblPosterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPosterMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblPosterMouseClicked

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

        lblHover.setBackground(new Color(0, 0, 0, 205));
    }//GEN-LAST:event_lblHoverMouseEntered

    private void lblHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseExited
        // TODO add your handling code here:
        lblHover.setVisible(false);
    }//GEN-LAST:event_lblHoverMouseExited

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
            java.util.logging.Logger.getLogger(QLPhimFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLPhimFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLPhimFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLPhimFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLPhimFrame pf = new QLPhimFrame();
                pf.setUp();
                pf.renderFrame();
                pf.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgTrangThai;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboNgonNgu;
    private javax.swing.JComboBox<String> cboTheLoai;
    private com.toedter.calendar.JDateChooser dcsNgayChieu;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JLabel lblHover;
    private javax.swing.JLabel lblPoster;
    private javax.swing.JPanel pnlBackGround;
    private javax.swing.JLayeredPane pnlPoster;
    private javax.swing.JRadioButton rdoDangChieu;
    private javax.swing.JRadioButton rdoDungChieu;
    private javax.swing.JSpinner spnGio;
    private javax.swing.JSpinner spnPhut;
    private javax.swing.JTable tblPhim;
    private javax.swing.JTextField txtTenPhim;
    private javax.swing.JTextField txtTim;
    private javax.swing.JTextArea txtTomTat;
    // End of variables declaration//GEN-END:variables
}

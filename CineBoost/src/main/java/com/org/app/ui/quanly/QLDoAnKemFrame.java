package com.org.app.ui.quanly;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.DoAnDao;
import com.org.app.controller.KichCoDoAnDao;
import com.org.app.entity.DoAn;
import com.org.app.entity.KichCo;
import com.org.app.entity.KichCoDoAn;
import com.org.app.entity.LoaiDoAn;
import com.org.app.helper.ImageUtil;
import com.org.app.helper.InputValidlHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.message.MessageDialog;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.DinhDangTienTe;
import com.org.app.util.InputFocusGUIUtil;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.QLDoAnKemValidator;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.TableRendererUtil;
import com.org.app.util.ValidationHelper;
import com.org.app.util.Validator;
import com.org.app.util.ValidatorException;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class QLDoAnKemFrame extends javax.swing.JFrame implements SubFrame<QLDoAnKemFrame>, SubPanelCreatorInterfaces<SubFrame> {
    public static final String CARD_NAME_MAIN = "doan";
    
    SubPanelCreator<QLDoAnKemFrame> subPanel;
    KichCoDoAnDao dao = new KichCoDoAnDao();
    DoAnDao daoDA = new DoAnDao();
    Validator<KichCoDoAn> validator =  ValidationHelper.getKichCoDoAnChecker();
    KichCoDoAn kcda = null;
    int id_KCDA;

    private List<KichCoDoAn> list;
    private int index;
    
    CardLayout layout;
    String current;
    boolean isUpdate = false;
    private String UPDATE_PANEL = "Cập nhật món";
    private String ADD_PANEL = "Thêm món";
    
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
    
    
    public QLDoAnKemFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        pnlMain.requestFocus();
        setUp();
//        renderFrame();
    }

    void fillTable(String locDuLieu) {
        DefaultTableModel model = (DefaultTableModel) tblDoAn.getModel();
        model.setRowCount(0);
        int i = 1;
        try {
            if (locDuLieu.equals("Tất cả")) {
                list = dao.selectDoAnAll();              
            } else if (locDuLieu.equals("Đồ uống")) {
                list = dao.selectDoAn_DU();
            } else {
                list = dao.selectDoAn_TA();
            }
            for (KichCoDoAn kcda : list) {
                    Object[] row = {
                        i++,
                        kcda.getDoAn().getId(),
                        kcda.getDoAn().getTen(),
                        kcda.getKichco().getId(),
                        DinhDangTienTe.chuyenThanhTienVN(kcda.getGia())
                    };
                    model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Lỗi truy vấn dữ liệu!", 0);
        }
    }

    private void setSelectedRow() {
        if (index == -1 || index > list.size() - 1) {
            index = -1;
            return;
        }
        tblDoAn.setRowSelectionInterval(index, index);
        tblDoAn.scrollRectToVisible(new java.awt.Rectangle(tblDoAn.getCellRect(index, 0, true)));

        updateControlState();
        btnTrangThai();
        setForm(list.get(index));
        kcda = list.get(index);
    }

    private void updateControlState() {
        boolean first = index <= 0;
        boolean last = index == list.size() - 1;

        btnFirst.setEnabled(!first);
        btnLast.setEnabled(!last);
    }

    public void setUp() {
        dinhDangBang();
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnFirst, btnLast, btnNext, btnPre);
        MouseHoverEffect.mouseOverEffectButton(ColorAndIconBank.ROLLOVER_BUTTON, btnThem, btnSua, btnXoa, btnMoi);
        setButtonState();
        layout = (CardLayout) pnlTenDA.getLayout();
        pnlTenDA.add("Thêm món", pnlAddF);
        pnlTenDA.add("Cập nhật món", pnlUpdateF);
        current = ADD_PANEL;
        layout.show(pnlTenDA, current);
        lblTenMon.setText(current);
        cboTenDA();
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

    private void edit() {
        if (index == -1) {
            return;
        }else{
        try {
            String id_doan = (String) tblDoAn.getValueAt(index, 2);
            String id_kichco = (String) tblDoAn.getValueAt(index, 3);
            kcda = dao.selectByTenDA_KC(id_doan, id_kichco);
            this.setForm(kcda);
            id_KCDA = kcda.getId();
            btnTrangThai();
        } catch (Exception ex) {
            Logger.getLogger(QLKhachHangFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (!isUpdate) {
            current = ADD_PANEL;
            layout.show(pnlTenDA, current);
            lblTenMon.setText(current);
        }
        }
    }
    
    private void checkLoi(KichCoDoAn kcda) throws ValidatorException{
        String tendaMoi = txtTenMon.getText();
        String sizeMoi = cboSize.getItemAt(cboSize.getSelectedIndex());
        System.out.println("checkLoi kcda " + kcda);
        System.out.println("tenMoi " + tendaMoi);
        System.out.println("sizeMoi " + sizeMoi);
        ((QLDoAnKemValidator)validator).checkDoAn(list, kcda, tendaMoi, sizeMoi);
    }
    
    public void insert() {
            KichCoDoAn model = getForm();
            try {
                check(model);
                checkLoi(model);
                daoDA.insert_SP(model.getDoAn().getTen(), model.getDoAn().getLoaiDoAn().getId(), model.getKichco().getId(), model.getGia());
                this.fillTable((String) cboLocDoAn.getSelectedItem());
                MessageHelper.message(this, "Thêm mới thành công!", 2);
                index = findMatch(model);
                setSelectedRow();
            } catch (Exception e) {
                e.printStackTrace();
                MessageHelper.message(this, e.getMessage(), 0);
            }
    }
    
    public void update() {
            try {
                KichCoDoAn model = getForm();
                check(model);
                checkLoi(kcda);
                
                System.out.println("model " + model.getDoAn().getTen() + " " + model.getDoAn().getLoaiDoAn().getId() + " " + model.getKichco().getId() + " " + model.getGia() + " " + model.getDoAn().getId() + " " + model.getId());
                daoDA.update_SP(model.getDoAn().getTen(), model.getDoAn().getLoaiDoAn().getId(), model.getKichco().getId(), model.getGia(), model.getDoAn().getId(), id_KCDA);
                this.fillTable((String) cboLocDoAn.getSelectedItem());
                setForm(model);
                MessageHelper.message(this, "Cập nhật thành công!", 2);
                index = list.indexOf(model);
                System.out.println("index " + index);
                setSelectedRow();
            } catch (Exception e) {
                e.printStackTrace();
                MessageHelper.message(this, e.getMessage(), 0);
            }
    }

    public void delete() {
        try {
            String id_doan = (String) tblDoAn.getValueAt(index, 2);
            String id_kichco = (String) tblDoAn.getValueAt(index, 3);
            KichCoDoAn kcda = dao.selectByTenDA_KC(id_doan, id_kichco);
            dao.delete(kcda.getId());
            this.fillTable((String) cboLocDoAn.getSelectedItem());
            if (index != list.size()) {
                if (index == -1) {
                    clear();
                    return;
                }else{
                tblDoAn.setRowSelectionInterval(index, index);
                }
            }else{
                index = list.size() - 1;
                if (index == -1) {
                    clear();
                    return;
                }else{
                    tblDoAn.setRowSelectionInterval(index, index); 
                }
                
            }
            setForm(list.get(index));
            MessageHelper.message(this, "Xóa thành công!", 2);
        } catch (Exception e) {
            e.printStackTrace();
            MessageHelper.message(this, "Xóa thất bại!", 0);
        }
    }

    private void setForm(KichCoDoAn kcda) {
        lblID_DoAn.setText(kcda.getDoAn().getId());
        txtTenMon.setText(kcda.getDoAn().getTen());
        cboLoaiDoAn.setSelectedItem(kcda.getDoAn().getLoaiDoAn().getId().equals("DU") ? "Đồ uống" : "Đồ ăn");
        cboSize.setSelectedItem("Size " + kcda.getKichco().getId());
        txtGia.setText(DinhDangTienTe.chuyenThanhTienVN(kcda.getGia()));
        cboTenDA.setSelectedItem(kcda.getDoAn().getTen());
        if (kcda.getDoAn().getLoaiDoAn().getId().equals("DU")) {
            lblAnhDoAn.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getDoAnImage("douong_loai_icon.png"), lblAnhDoAn));
        } else {
            lblAnhDoAn.setIcon(ScaleImageIconGenerator.getScaledIconOf(ImageUtil.getDoAnImage("doan_loai_icon.png"), lblAnhDoAn));
        }
    }

    private KichCoDoAn getForm() {
        KichCoDoAn kcda = new KichCoDoAn();
        DoAn da = new DoAn();
        LoaiDoAn lda = new LoaiDoAn();
        KichCo kc = new KichCo();

        lda.setId(cboLoaiDoAn.getSelectedItem().equals("Đồ ăn") ? "TA" : "DU");

        da.setId(lblID_DoAn.getText());
        if (!isUpdate) {
            System.out.println("TH1");
            da.setTen(txtTenMon.getText());
        }else{
            da.setTen((String) cboTenDA.getSelectedItem());
        }
        
        da.setLoaiDoAn(lda);

        String kc_id = (String) cboSize.getSelectedItem();
        if (kc_id.equals("Size S")) {
            kc.setId("S");
        } else if (kc_id.equals("Size M")) {
            kc.setId("M");
        } else {
            kc.setId("L");
        }

        kcda.setId(id_KCDA);
        kcda.setDoAn(da);
        kcda.setKichco(kc);
        kcda.setGia(DinhDangTienTe.chuyenChuoiThanhDouble(txtGia.getText()));
//        System.out.println("kcdaGia " + kcda.getGia());
//        System.out.println("kcda: " + kcda.getDoAn().getTen() + " " + kcda.getDoAn().getLoaiDoAn().getId() + " " + kcda.getKichco().getId() + " " + kcda.getGia());
        return kcda;
    }

    public void clear() {
        kcda = null;
        id_KCDA = 0;
        lblID_DoAn.setText("");
        lblAnhDoAn.setText("");
        txtGia.setText("");
        txtTenMon.setText("");
        cboLoaiDoAn.setSelectedIndex(0);
        cboSize.setSelectedIndex(0);
        index = -1;
        tblDoAn.clearSelection();
        btnTrangThai();
    }

    private boolean checkForm() {
        boolean rong = false;
        if (!isUpdate) {
            System.out.println("1");
            rong = InputValidlHelper.isEmpty(txtGia);
        }else {
            System.out.println("2");
            rong = InputValidlHelper.isEmpty(txtTenMon,txtGia);
        }               
        if (rong) {
            MessageHelper.message(this, "Ô nhập không được để trống", 1);
            return false;
        }
        
        try {
            double gia = Double.parseDouble(txtGia.getText());
        } catch (Exception e) {          
            MessageHelper.message(this, "Giá phải là số!", 1);
            txtGia.requestFocus();
            return false;
        }
        return true;
    }

    private void check(KichCoDoAn kcda) throws ValidatorException{
        Validator<KichCoDoAn> checker = ValidationHelper.getKichCoDoAnChecker();
        checker.check(kcda);
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
                renderFrame();
            }
        };
    }

    private void setUpInputFocusListener() {
        Color focusColor = ColorAndIconBank.INPUT_COLOR_FOCUS;
        Color unfocusColor = new Color(0, 0, 0, 0);
        InputFocusGUIUtil.setFocusEffect(focusColor, unfocusColor, new JTextField[]{txtTenMon, txtGia}, lblTenMon, jLabel5);
    }

    public void renderFrame() {
        this.index = -1;
        setUpInputFocusListener();
        setButtonState();

        fillTable((String) cboLocDoAn.getSelectedItem());
        btnTrangThai();
        if (list.size() != 0 && index == -1) {
                index = 0; setSelectedRow();
        }
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
    
    private void cboTenDA(){
        DefaultComboBoxModel tenDA = (DefaultComboBoxModel) cboTenDA.getModel();
        tenDA.removeAllElements();
        try {
            List<String> list = daoDA.getTenDA();
            loadListToModel(tenDA, list);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void loadListToModel(DefaultComboBoxModel m, List list) {
        for (int i = 0; i < list.size(); i++) {
            m.addElement(list.get(i));
        }
    }
    
    private void setIdDA(){
        String tenDA = (String) cboTenDA.getSelectedItem();
        String id_DA = daoDA.getIdDoAn(tenDA);
        lblID_DoAn.setText(id_DA);
    }
    
    private void setLoaiDA(){
        String id_DA = lblID_DoAn.getText();
        if (id_DA != null) {
            try {
                String loaiDoAn = daoDA.getLoaiDoAn(id_DA);
                cboLoaiDoAn.setSelectedItem(loaiDoAn.equals("Đồ uống")?"Đồ uống" : "Đồ ăn");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
    
    private void dinhDangBang(){
        TableRendererUtil tbl = new TableRendererUtil(tblDoAn);
        tbl.changeHeaderStyle(Color.decode("#FF6666"));
        tbl.setColoumnWidthByPersent(1, 10);
        tbl.setRowHeightByPresent(40);
        tblDoAn.setGridColor(Color.decode("#FE8F8F"));
        tblDoAn.setSelectionBackground(Color.decode("#FE8F8F"));
        tblDoAn.setSelectionForeground(Color.decode("#FFFFFF"));
        for (int i = 0; i < tblDoAn.getColumnCount(); i++) {
            tbl.setColumnAlignment(i, (int) CENTER_ALIGNMENT);
        }
        
        tbl.setColoumnWidthByPersent(0, 5);
        for (int i = 1; i < tblDoAn.getColumnCount(); i++) {
            tbl.setColoumnWidthByPersent(i, 20);
        }
    }
    
    private int findMatch(KichCoDoAn kcda) {
        KichCoDoAn m = list.stream().filter(e -> (e.getKichco().getId().equals(kcda.getKichco().getId())
                && e.getDoAn().getTen().equals(kcda.getDoAn().getTen())
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

        pnlMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDoAn = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        pnlThongTin = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblID_DoAn = new javax.swing.JLabel();
        lblTenMon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboSize = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cboLoaiDoAn = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        lblAnhDoAn = new javax.swing.JLabel();
        cboLocDoAn = new javax.swing.JComboBox<>();
        btnTenMon = new javax.swing.JButton();
        pnlTenDA = new javax.swing.JPanel();
        pnlAddF = new javax.swing.JPanel();
        txtTenMon = new javax.swing.JTextField();
        pnlUpdateF = new javax.swing.JPanel();
        cboTenDA = new javax.swing.JComboBox<>();
        pnlDiChuyen = new javax.swing.JPanel();
        btnFirst = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        pnlThaoTac = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setPreferredSize(new java.awt.Dimension(690, 0));

        tblDoAn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDoAn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Tên món", "Size", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDoAn.setFocusable(false);
        tblDoAn.setGridColor(new java.awt.Color(169, 197, 80));
        tblDoAn.setName("tblDoAn"); // NOI18N
        tblDoAn.setSelectionBackground(new java.awt.Color(61, 144, 106));
        tblDoAn.setSelectionForeground(new java.awt.Color(244, 244, 244));
        tblDoAn.setShowGrid(true);
        tblDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDoAnMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDoAn);

        jLabel1.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_street_food_48px.png"))); // NOI18N
        jLabel1.setText("Quản Lý Đồ Ăn Kèm");
        jLabel1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        pnlThongTin.setBackground(new java.awt.Color(227, 255, 255));
        pnlThongTin.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đồ ăn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Corbel", 1, 18))); // NOI18N
        pnlThongTin.setMaximumSize(new java.awt.Dimension(460, 230));
        pnlThongTin.setMinimumSize(new java.awt.Dimension(460, 230));
        pnlThongTin.setPreferredSize(new java.awt.Dimension(460, 230));

        jLabel3.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel3.setText("ID");

        lblID_DoAn.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lblID_DoAn.setName("lblid"); // NOI18N

        lblTenMon.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lblTenMon.setText("Tên món");

        jLabel6.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel6.setText("Kích cỡ");

        cboSize.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        cboSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Size S", "Size M", "Size L" }));

        jLabel5.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel5.setText("Giá");

        txtGia.setBackground(new java.awt.Color(227, 255, 255));
        txtGia.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtGia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 102)));

        jLabel7.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel7.setText("Loại đồ ăn");

        cboLoaiDoAn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cboLoaiDoAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đồ ăn", "Đồ uống" }));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setOpaque(false);

        lblAnhDoAn.setPreferredSize(new java.awt.Dimension(118, 119));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblAnhDoAn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        cboLocDoAn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồ uống", "Đồ ăn" }));
        cboLocDoAn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboLocDoAn.setName("cboLocDoAn"); // NOI18N
        cboLocDoAn.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocDoAnItemStateChanged(evt);
            }
        });

        btnTenMon.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnTenMon.setText("+");
        btnTenMon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTenMonActionPerformed(evt);
            }
        });

        pnlTenDA.setOpaque(false);
        pnlTenDA.setPreferredSize(new java.awt.Dimension(50, 25));
        pnlTenDA.setLayout(new java.awt.CardLayout());

        pnlAddF.setOpaque(false);

        txtTenMon.setBackground(new java.awt.Color(227, 255, 255));
        txtTenMon.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txtTenMon.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 102, 102)));
        txtTenMon.setPreferredSize(new java.awt.Dimension(69, 25));

        javax.swing.GroupLayout pnlAddFLayout = new javax.swing.GroupLayout(pnlAddF);
        pnlAddF.setLayout(pnlAddFLayout);
        pnlAddFLayout.setHorizontalGroup(
            pnlAddFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
            .addGroup(pnlAddFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtTenMon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
        );
        pnlAddFLayout.setVerticalGroup(
            pnlAddFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
            .addGroup(pnlAddFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(txtTenMon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );

        pnlTenDA.add(pnlAddF, "card3");

        pnlUpdateF.setOpaque(false);

        cboTenDA.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cboTenDA.setLightWeightPopupEnabled(false);
        cboTenDA.setMaximumSize(new java.awt.Dimension(65, 25));
        cboTenDA.setMinimumSize(new java.awt.Dimension(65, 25));
        cboTenDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTenDAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUpdateFLayout = new javax.swing.GroupLayout(pnlUpdateF);
        pnlUpdateF.setLayout(pnlUpdateFLayout);
        pnlUpdateFLayout.setHorizontalGroup(
            pnlUpdateFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cboTenDA, 0, 138, Short.MAX_VALUE)
        );
        pnlUpdateFLayout.setVerticalGroup(
            pnlUpdateFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateFLayout.createSequentialGroup()
                .addComponent(cboTenDA, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlTenDA.add(pnlUpdateF, "card2");

        javax.swing.GroupLayout pnlThongTinLayout = new javax.swing.GroupLayout(pnlThongTin);
        pnlThongTin.setLayout(pnlThongTinLayout);
        pnlThongTinLayout.setHorizontalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(98, 98, 98))
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)))
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGia)
                            .addComponent(cboSize, 0, 183, Short.MAX_VALUE)
                            .addComponent(lblID_DoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addComponent(pnlTenDA, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboLoaiDoAn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboLocDoAn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlThongTinLayout.setVerticalGroup(
            pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinLayout.createSequentialGroup()
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblID_DoAn, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboLocDoAn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinLayout.createSequentialGroup()
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlThongTinLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblTenMon))
                            .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnTenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pnlTenDA, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboLoaiDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlThongTinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pnlDiChuyen.setBackground(new java.awt.Color(255, 255, 255));
        pnlDiChuyen.setOpaque(false);
        pnlDiChuyen.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

        btnFirst.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_skip_to_start_26px.png"))); // NOI18N
        btnFirst.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });
        pnlDiChuyen.add(btnFirst);

        btnPre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_left_26px_5.png"))); // NOI18N
        btnPre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });
        pnlDiChuyen.add(btnPre);

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_sort_right_26px_2.png"))); // NOI18N
        btnNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlDiChuyen.add(btnNext);

        btnLast.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_end_26px.png"))); // NOI18N
        btnLast.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        pnlDiChuyen.add(btnLast);

        pnlThaoTac.setBackground(new java.awt.Color(255, 255, 255));
        pnlThaoTac.setOpaque(false);
        pnlThaoTac.setPreferredSize(new java.awt.Dimension(458, 39));
        pnlThaoTac.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_add_24px_1.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        pnlThaoTac.add(btnThem);

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_24px_1.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        pnlThaoTac.add(btnSua);

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_remove_24px_1.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlThaoTac.add(btnXoa);

        btnMoi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnMoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_new_copy_24px_1.png"))); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnMoi.setPreferredSize(new java.awt.Dimension(80, 30));
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });
        pnlThaoTac.add(btnMoi);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(pnlDiChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(pnlThaoTac, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(150, Short.MAX_VALUE))
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlMainLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(345, 345, 345))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 87, Short.MAX_VALUE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(pnlDiChuyen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlThongTin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlThaoTac, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.PREFERRED_SIZE, 650, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDoAnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDoAnMouseClicked
        // TODO add your handling code here:
        this.index = tblDoAn.getSelectedRow();
        this.edit();
        isUpdate = false;
        btnTenMon.setEnabled(false);
    }//GEN-LAST:event_tblDoAnMouseClicked

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
        // TODO add your handling code here:
        if (!checkForm()) return;
        else insert();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        if (!checkForm()) return;
        else update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        delete();
        cboTenDA();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoiActionPerformed
        // TODO add your handling code here:
        clear();
        current = ADD_PANEL;
        layout.show(pnlTenDA, current);
        lblTenMon.setText(current);
        btnTenMon.setEnabled(true);
    }//GEN-LAST:event_btnMoiActionPerformed

    private void cboLocDoAnItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocDoAnItemStateChanged
        // TODO add your handling code here:
        this.fillTable((String) cboLocDoAn.getSelectedItem());
    }//GEN-LAST:event_cboLocDoAnItemStateChanged

    private void btnTenMonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTenMonActionPerformed
        // TODO add your handling code here:
        isUpdate = !isUpdate;
        System.out.println("isU " + isUpdate);
        if (current.equals(UPDATE_PANEL)) {
            pnlUpdateF.setVisible(true);
            current = ADD_PANEL;
            btnSua.setEnabled(false);
            lblID_DoAn.setText("");
            txtTenMon.setText("");           
        }else {
            current = UPDATE_PANEL;
            
            cboTenDA();
            pnlUpdateF.setVisible(false);
            tblDoAn.clearSelection();
            btnXoa.setEnabled(false);
            if (!list.isEmpty()) {
                cboTenDA.setSelectedIndex(0);
            }else {
                MessageHelper.message(this, "Không có món nào trong menu bạn cần thêm món", 1);       
            }      
        }
        layout.show(pnlTenDA, current);
        lblTenMon.setText(current);
    }//GEN-LAST:event_btnTenMonActionPerformed

    private void cboTenDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTenDAActionPerformed
        // TODO add your handling code here:
        setIdDA();
        setLoaiDA();
        txtTenMon.setText((String) cboTenDA.getSelectedItem());
    }//GEN-LAST:event_cboTenDAActionPerformed

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
            java.util.logging.Logger.getLogger(QLDoAnKemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDoAnKemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDoAnKemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDoAnKemFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLDoAnKemFrame f = new QLDoAnKemFrame();
                f.renderFrame();
                        f.setVisible(true);
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
    private javax.swing.JButton btnTenMon;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiDoAn;
    private javax.swing.JComboBox<String> cboLocDoAn;
    private javax.swing.JComboBox<String> cboSize;
    private javax.swing.JComboBox<String> cboTenDA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhDoAn;
    private javax.swing.JLabel lblID_DoAn;
    private javax.swing.JLabel lblTenMon;
    private javax.swing.JPanel pnlAddF;
    private javax.swing.JPanel pnlDiChuyen;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlTenDA;
    private javax.swing.JPanel pnlThaoTac;
    private javax.swing.JPanel pnlThongTin;
    private javax.swing.JPanel pnlUpdateF;
    private javax.swing.JTable tblDoAn;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtTenMon;
    // End of variables declaration//GEN-END:variables
}

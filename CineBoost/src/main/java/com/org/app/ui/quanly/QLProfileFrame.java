/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.quanly;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.formdev.flatlaf.ui.FlatRoundBorder;
import com.org.app.controller.NhanVienDao;
import com.org.app.entity.NhanVien;
import com.org.app.helper.Authenticator;
import com.org.app.helper.DateHelper;
import com.org.app.helper.ImageUtil;
import com.org.app.helper.InputValidlHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.InputFocusGUIUtil;
import com.org.app.util.LabelWrapperInterface;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.NhanVienValidator;
import com.org.app.util.ShowPasswordInterfaceUtil;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import com.org.app.util.ValidationHelper;
import com.org.app.util.Validator;
import com.org.app.util.ValidatorException;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;
import com.org.app.ui.main.MainFrame;

/**
 *
 * @author intfs
 */
public class QLProfileFrame extends javax.swing.JFrame implements SubFrame<QLProfileFrame>,SubPanelCreatorInterfaces<SubFrame>, ShowPasswordInterfaceUtil, LabelWrapperInterface{
    public static final String CARD_NAME_MAIN = "profile";
    private final String MK_Text = "Mật khẩu cũ";
    private final String MK_Moi_Text = "Mật khẩu mới";
    private final String MK_Xac_Nhan = "Xác nhận mật khẩu";
    private MainFrame mainF;
    SubPanelCreator<QLProfileFrame> subPanel;
    NhanVienDao nvDao;
    Validator<NhanVien> validator =  ValidationHelper.getNhanVienChecker();
    NhanVienValidator checker = ((NhanVienValidator)validator);
    
    File file;
    boolean isLoading;
    /**
     * Creates new form QLProfile
     */
    public QLProfileFrame(){
        FlatIntelliJLaf.setup();
        initComponents();
        subPanel = createSubPanel(this);
        //test
       // Authenticator.USER = new NhanVien("QL00006","Trần Vân",DateHelper.toDate("23/02/1998"),true,"0329436123","vantht@gmail.com",true,"user2.png","vantht","Ps12345");
        nvDao = new NhanVienDao();
        showPassword(txtMatKhauCu);
        showPassword(txtMatKhauMoi);
        showPassword(txtXacNhanMatKhau);  
        txtTenTK.setName("txtTenTK");
        System.out.println( "d: "+ txtTenTK.getName());;   
   }
    
//    public QLProfileFrame() {
//        
//    }
    
    public QLProfileFrame(MainFrame main) {
        this();
        mainF = main;
    }
    
    public void setUp() {
        isLoading = true;
        InputFocusGUIUtil.setFocusEffect(txtTen.getFocus(), txtTen.getOrigin(), txtPhone, null);
        lblHover.setVisible(false);
         JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dcNgaySinh.getDateEditor();
         txtFld.setBackground(pnlUpdateProfile.getBackground());
         txtFld.setHorizontalAlignment(SwingConstants.CENTER);
         txtFld.setBorder(txtTen.getBorderO());
        dcNgaySinh.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                System.out.println("dc = " + dcNgaySinh.getDate().toString());
                java.util.Date d = dcNgaySinh.getDate();
                wrap(lblNgaySinh, DateHelper.toString(d));
                try {
                lblNgaySinh.setForeground(Color.black);
                checker.checkNgaySinh(DateHelper.toDate(d));
            } catch (ValidatorException ex) {
                lblNgaySinh.setForeground(Color.red);
            }
            }
        });
        SettingIconHelper.setIconSourceFor(lblHideMK, "eye_disable.png");
        SettingIconHelper.setIconSourceFor(lblHideMKMoi, "eye_disable.png");
        SettingIconHelper.setIconSourceFor(lblHideXacNhan, "eye_disable.png");
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
                setUp();
                renderFrame();
            }
        };
    }
    
    public void renderFrame(){
        System.out.println("render frame run");
        fillProfileForm();
        
    }
    
    private void fillProfileForm() {
        NhanVien nv = Authenticator.USER;
        txtTenTK.setText(nv.getTentk());
        txtTen.setText(nv.getHoten());
        dcNgaySinh.setDate(DateHelper.toDate(nv.getNgaysinh()));

        txtEmail.setText(nv.getEmail());
        txtPhone.setText(nv.getSodt());
        
        rdoNam.setSelected(!nv.getGioitinh());
        rdoNu.setSelected(nv.getGioitinh());
        
        SettingIconHelper.setProfilePic(lblHinh, nv.getAnh());
        SettingIconHelper.setProfilePic(lblHinh1, nv.getAnh());
        fillComfirm(nv);
        
        lockOnInvalid();
    }
   
    private void fillComfirm(NhanVien nv) {
        wrap(lblTenTK, nv.getTentk());
        wrap(lblTen, nv.getHoten());
        wrap(lblNgaySinh, DateHelper.toString(dcNgaySinh.getDate()));
        wrap(lblEmail, nv.getEmail());
        wrap(lblPhone, txtPhone.getText());
        lblGioiTinh.setText(nv.getGioitinh()? "Nữ": "Nam");
    }
    
    public void reset() {
        fillProfileForm();
        
    }
    
    private void update() {
        try {
            NhanVien newNv = getForm();
            List<NhanVien> nvList = nvDao.selectAll();
            check(newNv);
            
            checker.checkTenTK(nvList, Authenticator.USER, getText(txtTenTK));
//            file = new File(path);
            ImageUtil.saveImage("src/main/resources/images/avatars", file.getName(), file);
            nvDao.update(newNv);
            MessageHelper.message(this, "Cập nhật thành công");
            Authenticator.USER = newNv;
            mainF.setProfile();
            mainF.repaint(); mainF.revalidate();
            fillProfileForm();
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageHelper.message(this, ex.getMessage()+"\n Cập nhật thất bại.", MessageHelper.FORMAT_ERROR_MESSAGE);
        }
    }
    
    private void check(NhanVien nv) throws ValidatorException {
        validator.check(nv);
    }
       void chonAnh() {
        Path current = Paths.get(System.getProperty("user.dir"), "src","main", "resources", "images", "avatars");
        JFileChooser chooser = new JFileChooser(current.toFile());
        chooser.setFileFilter(new FileNameExtensionFilter("PNG,JPG", "png", "jpg"));
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            ImageIcon pic = new ImageIcon(file.getAbsolutePath());
            SettingIconHelper.setIconFor(lblHinh, pic);
            SettingIconHelper.setIconFor(lblHinh1, pic);
            lblHinh.setToolTipText(String.format("%s", file.getName(), "Nhấn giữ để chọn hình"));
        }
    }
       
    private NhanVien getForm() {
        NhanVien nvOld = Authenticator.USER;
        NhanVien nv = new NhanVien(nvOld);
        
        nv.setTentk(txtTenTK.getText().trim());
        nv.setHoten(txtTen.getText().trim());
        nv.setEmail(txtEmail.getText().trim());
        nv.setNgaysinh(DateHelper.toDate(dcNgaySinh.getDate()));
        
        System.out.println("phong: "+ txtPhone.getText());
        String phone = txtPhone.getText().replaceAll("(-)|(\\s+)", "").trim();
        nv.setSodt(phone);
        nv.setGioitinh(rdoNu.isSelected());
        
        if(file != null) {
            nv.setAnh(file.getName());
            System.out.println("file " + getName());
        }
        System.out.println("nhanvien update: "+ nv.toString());
        
        return nv;
    }
    
    private void clear() {
        lblErrorMess.setText("");
        InputValidlHelper.clear(txtMatKhauCu, txtMatKhauMoi, txtXacNhanMatKhau);
    } 
   
    private void setUpdatePassBtn(boolean state, String mess) {
        if(!state){
            btnUpdatePass.setToolTipText(String.format("<html>%s</html>", mess));
        }
        
        btnUpdatePass.setEnabled(state);
        lblErrorMess.setText(mess);
    }
    
    private void checkEmpty() {
        
    }
    private void lockOnInvalid() {
        boolean unlock = true;
        unlock = InputValidlHelper.isEmptyPass(txtMatKhauCu, txtMatKhauMoi, txtXacNhanMatKhau);
        
        if(isLoading) return;
        if(unlock) {
            setUpdatePassBtn(!unlock, "Không để trống ô nhập");
        }else {
            try {
                ((NhanVienValidator)validator).checkMatKhau(Authenticator.USER, getText(txtMatKhauCu).trim(), getText(txtMatKhauMoi), getText(txtXacNhanMatKhau).trim());
                setUpdatePassBtn(true, "");
            } catch (ValidatorException ex) {
                System.out.println("invalid");
                System.out.println(ex.getMessage());
                setUpdatePassBtn(false, ex.getMessage());
                
            }
        }
    }
    
    private void setInputVerify(JTextComponent txt) {
        InputVerifier  verify = new InputVerifier(){
            @Override
            public boolean verify(JComponent input) {
                input.setBackground(Color.WHITE);
                try {
                if(txt.getText().isEmpty()) throw new ValidatorException("O nhap trong");
                ((NhanVienValidator)validator).checkMatKhau(Authenticator.USER, getText(txtMatKhauCu), getText(txtMatKhauMoi), getText(txtXacNhanMatKhau));
                setUpdatePassBtn(true, "");
                
                return true;
            } catch (ValidatorException ex) {
                
                String mess =ex.getMessage();
                if(mess.equals(NhanVienValidator.NhanVienValidatorError.KHOP.getMess())) {
                    txtMatKhauMoi.setBackground(Color.RED);
                    txtXacNhanMatKhau.setBackground(Color.RED);
                }else if(mess.equals(NhanVienValidator.NhanVienValidatorError.DINH_DANG.getMess())){
                    txtMatKhauMoi.setBackground(Color.red);
                }
                setUpdatePassBtn(false,mess);
                return false;
            }
            }
           
        };
        txt.setInputVerifier(verify);
    }
    private String getText(JTextComponent c) {
        if(c instanceof JPasswordField) return String.valueOf(((JPasswordField)c).getPassword());
        return c.getText();
    }
    
    private void updatePassword() {
        try {
            NhanVien n = Authenticator.USER;
            n.setMatkhau(getText(txtMatKhauMoi));
            nvDao.update(n);
            MessageHelper.message(this, "Đổi mật khẩu thành công");
            clear();
        } catch (Exception ex) {
            ex.printStackTrace();
            MessageHelper.message(this, ex.getMessage());
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

        bgrGioiTinh = new javax.swing.ButtonGroup();
        pnlContent = new javax.swing.JPanel();
        pnlUpdateProfile = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JFormattedTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        dcNgaySinh = new com.toedter.calendar.JDateChooser();
        pnlHinh = new javax.swing.JLayeredPane();
        lblHover = new javax.swing.JLabel();
        lblHinh = new javax.swing.JLabel();
        txtTen = new com.org.app.customui.InputTextField();
        txtEmail = new com.org.app.customui.InputTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenTK = new com.org.app.customui.InputTextField();
        btnRefresh = new javax.swing.JButton();
        pnlProfile = new javax.swing.JPanel();
        lblHinh1 = new javax.swing.JLabel();
        btnUpdate = new com.org.app.customui.ButtonGradient();
        jPanel3 = new javax.swing.JPanel();
        lblTenTK = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblTen = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        pnlChangePassword = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        bntResetPass = new com.org.app.customui.ButtonGradient();
        btnUpdatePass = new com.org.app.customui.ButtonGradient();
        jPanel2 = new javax.swing.JPanel();
        lblHideMK = new javax.swing.JLabel();
        lblNoteMK = new javax.swing.JLabel();
        lblHideXacNhan = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblHideMKMoi = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMatKhauMoi = new com.org.app.customui.PasswordTextField();
        txtXacNhanMatKhau = new com.org.app.customui.PasswordTextField();
        txtMatKhauCu = new com.org.app.customui.PasswordTextField();
        lblErrorMess = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContent.setBackground(new java.awt.Color(255, 255, 255));
        pnlContent.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlContent.setPreferredSize(new java.awt.Dimension(700, 650));

        pnlUpdateProfile.setBackground(new java.awt.Color(227, 237, 219));
        pnlUpdateProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlUpdateProfileMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(3, 85, 79));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Họ tên:");

        jLabel2.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thông tin cá nhân");

        jLabel3.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(3, 85, 79));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Điện thoại:");

        jLabel4.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(3, 85, 79));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Email:");

        jLabel5.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(3, 85, 79));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Ngày sinh:");

        jLabel6.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(3, 85, 79));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Giới tính: ");

        txtPhone.setBackground(new java.awt.Color(227, 237, 219));
        txtPhone.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 102)));
        try {
            txtPhone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtPhone.setFocusLostBehavior(javax.swing.JFormattedTextField.PERSIST);
        txtPhone.setName("txtPhone"); // NOI18N
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPhoneKeyReleased(evt);
            }
        });

        rdoNam.setBackground(new java.awt.Color(227, 237, 219));
        bgrGioiTinh.add(rdoNam);
        rdoNam.setText("Nam");
        rdoNam.setName("nam"); // NOI18N
        rdoNam.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdoNamItemStateChanged(evt);
            }
        });

        rdoNu.setBackground(new java.awt.Color(227, 237, 219));
        bgrGioiTinh.add(rdoNu);
        rdoNu.setText("Nữ");
        rdoNu.setName("nu"); // NOI18N

        dcNgaySinh.setBackground(new java.awt.Color(255, 102, 51));
        dcNgaySinh.setDateFormatString("dd/MM/yyyy");
        dcNgaySinh.setMaximumSize(new java.awt.Dimension(192, 20));
        dcNgaySinh.setMinSelectableDate(new java.util.Date(-62135791113000L));
        dcNgaySinh.setName("dobDateChooser"); // NOI18N
        dcNgaySinh.setOpaque(false);
        dcNgaySinh.setPreferredSize(new java.awt.Dimension(192, 20));
        dcNgaySinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcNgaySinhPropertyChange(evt);
            }
        });

        lblHover.setBackground(new java.awt.Color(204, 204, 204));
        lblHover.setForeground(new java.awt.Color(255, 255, 255));
        lblHover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHover.setText("Double click chọn hình");
        lblHover.setToolTipText("");
        lblHover.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
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
        pnlHinh.add(lblHover);
        lblHover.setBounds(10, 0, 170, 160);

        lblHinh.setToolTipText("");
        lblHinh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblHinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHinhMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHinhMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHinhMouseExited(evt);
            }
        });
        pnlHinh.add(lblHinh);
        lblHinh.setBounds(10, 0, 170, 160);

        txtTen.setFocus(new java.awt.Color(215, 220, 219));
        txtTen.setLineThick(2);
        txtTen.setName("txtTen"); // NOI18N
        txtTen.setOrigin(new java.awt.Color(227, 237, 219));
        txtTen.setUnderlineColor(new java.awt.Color(0, 102, 102));
        txtTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKeyReleased(evt);
            }
        });

        txtEmail.setFocus(new java.awt.Color(215, 220, 219));
        txtEmail.setLineThick(2);
        txtEmail.setName("email"); // NOI18N
        txtEmail.setOrigin(new java.awt.Color(227, 237, 219));
        txtEmail.setUnderlineColor(new java.awt.Color(0, 102, 102));
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(3, 85, 79));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Tên tài khoản: ");

        txtTenTK.setFocus(new java.awt.Color(215, 220, 219));
        txtTenTK.setLineThick(2);
        txtTenTK.setOrigin(new java.awt.Color(227, 237, 219));
        txtTenTK.setUnderlineColor(new java.awt.Color(0, 102, 102));
        txtTenTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtTenTKMouseReleased(evt);
            }
        });
        txtTenTK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenTKKeyReleased(evt);
            }
        });

        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_30px.png"))); // NOI18N
        btnRefresh.setBorderPainted(false);
        btnRefresh.setContentAreaFilled(false);
        btnRefresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRefresh.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_refresh_30px_roll.png"))); // NOI18N
        btnRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRefreshMouseClicked(evt);
            }
        });
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUpdateProfileLayout = new javax.swing.GroupLayout(pnlUpdateProfile);
        pnlUpdateProfile.setLayout(pnlUpdateProfileLayout);
        pnlUpdateProfileLayout.setHorizontalGroup(
            pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                        .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pnlHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(26, 26, 26)
                                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                    .addComponent(txtPhone)))
                            .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlUpdateProfileLayout.setVerticalGroup(
            pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlHinh, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlUpdateProfileLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPhone)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlUpdateProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addContainerGap(136, Short.MAX_VALUE))
        );

        pnlUpdateProfileLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, rdoNam, rdoNu});

        pnlUpdateProfileLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtEmail, txtPhone});

        pnlUpdateProfileLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {dcNgaySinh, txtTen});

        rdoNu.getAccessibleContext().setAccessibleDescription("");

        pnlProfile.setBackground(new java.awt.Color(246, 247, 235));
        pnlProfile.setMaximumSize(new java.awt.Dimension(350, 309));
        pnlProfile.setPreferredSize(new java.awt.Dimension(350, 309));

        lblHinh1.setBackground(new java.awt.Color(204, 204, 204));
        lblHinh1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(153, 153, 153), new java.awt.Color(204, 204, 204)));

        btnUpdate.setText("Cập nhật");
        btnUpdate.setColor1(new java.awt.Color(110, 203, 175));
        btnUpdate.setColor2(new java.awt.Color(0, 153, 153));
        btnUpdate.setName("btnUpdate"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jPanel3.setMaximumSize(new java.awt.Dimension(237, 229));
        jPanel3.setOpaque(false);

        lblTenTK.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTenTK.setText("vanthtfafafasdfas@gmail.com");
        lblTenTK.setPreferredSize(new java.awt.Dimension(140, 16));

        jLabel8.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(3, 85, 79));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Họ tên:");

        lblTen.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTen.setText("vanthtfafafasdfas@gmail.com");
        lblTen.setPreferredSize(new java.awt.Dimension(140, 28));

        jLabel11.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(3, 85, 79));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Điện thoại:");

        lblNgaySinh.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblNgaySinh.setText("vanthtfafafasdfas@gmail.com");
        lblNgaySinh.setMaximumSize(new java.awt.Dimension(171, 28));
        lblNgaySinh.setMinimumSize(new java.awt.Dimension(171, 28));
        lblNgaySinh.setPreferredSize(new java.awt.Dimension(171, 28));

        jLabel10.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(3, 85, 79));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Email:");

        lblGioiTinh.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGioiTinh.setText("vanthtfafafasdfas@gmail.com");
        lblGioiTinh.setMaximumSize(new java.awt.Dimension(140, 28));
        lblGioiTinh.setMinimumSize(new java.awt.Dimension(140, 28));
        lblGioiTinh.setPreferredSize(new java.awt.Dimension(140, 28));

        lblEmail.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblEmail.setText("vanthtfafafasdfas@gmail.com");
        lblEmail.setMaximumSize(new java.awt.Dimension(140, 28));
        lblEmail.setMinimumSize(new java.awt.Dimension(140, 28));
        lblEmail.setPreferredSize(new java.awt.Dimension(140, 28));

        jLabel13.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(3, 85, 79));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Tài khoản:");

        lblPhone.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblPhone.setText("vanthtfafafasdfas@gmail.com");
        lblPhone.setMaximumSize(new java.awt.Dimension(140, 28));
        lblPhone.setMinimumSize(new java.awt.Dimension(140, 28));
        lblPhone.setPreferredSize(new java.awt.Dimension(140, 28));

        jLabel9.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(3, 85, 79));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Ngày sinh:");

        jLabel12.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(3, 85, 79));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Giới tính: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13, jLabel8, jLabel9});

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblHinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProfileLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProfileLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProfileLayout.createSequentialGroup()
                        .addComponent(lblHinh1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)))
                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlChangePassword.setBackground(new java.awt.Color(203, 221, 229));
        pnlChangePassword.setMaximumSize(new java.awt.Dimension(356, 312));
        pnlChangePassword.setPreferredSize(new java.awt.Dimension(356, 312));

        jPanel1.setMaximumSize(new java.awt.Dimension(212, 48));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(212, 48));
        jPanel1.setLayout(null);

        jLabel18.setBackground(new java.awt.Color(0, 102, 102));
        jLabel18.setFont(new java.awt.Font("Corbel", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 102, 102));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_secure_24px.png"))); // NOI18N
        jLabel18.setText("Đổi mật khẩu");
        jLabel18.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel18);
        jLabel18.setBounds(10, 0, 183, 40);

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_secure_24px.png"))); // NOI18N
        jPanel1.add(jLabel17);
        jLabel17.setBounds(180, 0, 24, 30);

        bntResetPass.setForeground(new java.awt.Color(102, 102, 102));
        bntResetPass.setText("Reset");
        bntResetPass.setColor1(new java.awt.Color(120, 163, 197));
        bntResetPass.setColor2(new java.awt.Color(160, 200, 223));
        bntResetPass.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        bntResetPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntResetPassActionPerformed(evt);
            }
        });

        btnUpdatePass.setForeground(new java.awt.Color(102, 102, 102));
        btnUpdatePass.setText("Đổi mật khẩu");
        btnUpdatePass.setColor1(new java.awt.Color(89, 168, 174));
        btnUpdatePass.setColor2(new java.awt.Color(79, 210, 179));
        btnUpdatePass.setColorHover1(new java.awt.Color(242, 242, 242));
        btnUpdatePass.setColorHover2(new java.awt.Color(243, 240, 240));
        btnUpdatePass.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        btnUpdatePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePassActionPerformed(evt);
            }
        });

        jPanel2.setOpaque(false);

        lblHideMK.setToolTipText("Nhấn giữ để hiện Password");
        lblHideMK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHideMK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHideMKMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblHideMKMouseReleased(evt);
            }
        });

        lblNoteMK.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        lblNoteMK.setForeground(new java.awt.Color(3, 85, 79));
        lblNoteMK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNoteMK.setText("Mật khẩu cũ:");

        lblHideXacNhan.setToolTipText("Nhấn giữ để hiện Password");
        lblHideXacNhan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHideXacNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHideXacNhanMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblHideXacNhanMouseReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(3, 85, 79));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Mật khẩu mới:");

        lblHideMKMoi.setToolTipText("Nhấn giữ để hiện Password");
        lblHideMKMoi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHideMKMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblHideMKMoiMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblHideMKMoiMouseReleased(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(3, 85, 79));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Xác nhận mật khẩu:");

        txtMatKhauMoi.setFocus(new java.awt.Color(216, 233, 233));
        txtMatKhauMoi.setOrigin(new java.awt.Color(203, 221, 229));
        txtMatKhauMoi.setUnderlineColor(new java.awt.Color(102, 102, 102));
        txtMatKhauMoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauMoiKeyReleased(evt);
            }
        });

        txtXacNhanMatKhau.setFocus(new java.awt.Color(216, 233, 233));
        txtXacNhanMatKhau.setOrigin(new java.awt.Color(203, 221, 229));
        txtXacNhanMatKhau.setUnderlineColor(new java.awt.Color(102, 102, 102));
        txtXacNhanMatKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtXacNhanMatKhauKeyReleased(evt);
            }
        });

        txtMatKhauCu.setFocus(new java.awt.Color(216, 233, 233));
        txtMatKhauCu.setOrigin(new java.awt.Color(203, 221, 229));
        txtMatKhauCu.setUnderlineColor(new java.awt.Color(102, 102, 102));
        txtMatKhauCu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMatKhauCuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHideXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNoteMK, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHideMK, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHideMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblNoteMK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHideMK, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHideMKMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtXacNhanMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHideXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );

        lblErrorMess.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblErrorMess.setForeground(new java.awt.Color(255, 51, 102));

        javax.swing.GroupLayout pnlChangePasswordLayout = new javax.swing.GroupLayout(pnlChangePassword);
        pnlChangePassword.setLayout(pnlChangePasswordLayout);
        pnlChangePasswordLayout.setHorizontalGroup(
            pnlChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangePasswordLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChangePasswordLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChangePasswordLayout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlChangePasswordLayout.createSequentialGroup()
                                .addComponent(bntResetPass, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btnUpdatePass, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))
                    .addGroup(pnlChangePasswordLayout.createSequentialGroup()
                        .addGroup(pnlChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblErrorMess, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(38, Short.MAX_VALUE))))
        );
        pnlChangePasswordLayout.setVerticalGroup(
            pnlChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlChangePasswordLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblErrorMess, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlChangePasswordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntResetPass, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdatePass, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlUpdateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlChangePassword, 352, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlUpdateProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(pnlProfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblHideMKMoiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMKMoiMousePressed
        SettingIconHelper.setIconSourceFor(lblHideMKMoi, "eye_enable.png");
        showPassword(txtMatKhauMoi);
    }//GEN-LAST:event_lblHideMKMoiMousePressed

    private void lblHideMKMoiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMKMoiMouseReleased

        SettingIconHelper.setIconSourceFor(lblHideMKMoi, "eye_disable.png");
        hidePassword(txtMatKhauMoi);
    }//GEN-LAST:event_lblHideMKMoiMouseReleased

    private void lblHideMKMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMKMousePressed

        SettingIconHelper.setIconSourceFor(lblHideMK, "eye_enable.png");
        showPassword(txtMatKhauCu);
    }//GEN-LAST:event_lblHideMKMousePressed

    private void lblHideMKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideMKMouseReleased
        SettingIconHelper.setIconSourceFor(lblHideMK, "eye_disable.png");
        hidePassword(txtMatKhauCu);
    }//GEN-LAST:event_lblHideMKMouseReleased

    private void lblHideXacNhanMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideXacNhanMousePressed
        SettingIconHelper.setIconSourceFor(lblHideXacNhan, "eye_enable.png");
        showPassword(txtXacNhanMatKhau);
    }//GEN-LAST:event_lblHideXacNhanMousePressed

    private void lblHideXacNhanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHideXacNhanMouseReleased
        SettingIconHelper.setIconSourceFor(lblHideXacNhan, "eye_disable.png");
        hidePassword(txtXacNhanMatKhau);
    }//GEN-LAST:event_lblHideXacNhanMouseReleased

    private void dcNgaySinhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcNgaySinhPropertyChange
//        wrap(lblNgaySinh, DateHelper.toString(dcNgaySinh.getDate()));
    }//GEN-LAST:event_dcNgaySinhPropertyChange

    private void rdoNamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdoNamItemStateChanged
         lblGioiTinh.setText(rdoNu.isSelected()? "Nữ": "Nam");
    }//GEN-LAST:event_rdoNamItemStateChanged

    private void pnlUpdateProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlUpdateProfileMouseClicked
       ((JPanel)evt.getSource()).requestFocus();
    }//GEN-LAST:event_pnlUpdateProfileMouseClicked

    private void txtPhoneKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyReleased
        String phone = txtPhone.getText().replaceAll("\\s+", "").trim();
        phone = phone.replaceAll("(.+?)(-)$", "$1");
        try {
            System.out.println("phone = "+phone);
            lblPhone.setText(phone);
            lblPhone.setForeground(Color.black);
            checker.checkDienThoai(phone);
        } catch (ValidatorException ex) {
            lblPhone.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtPhoneKeyReleased

    private void lblHoverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseClicked
        if (evt.getClickCount() == 2) {
            
            chonAnh();
        }                    
    }//GEN-LAST:event_lblHoverMouseClicked

    private void lblHoverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseEntered
        lblHover.setVisible(true);
        lblHover.setOpaque(true);

        lblHover.setBackground(new Color(0, 0, 0, 205));
    }//GEN-LAST:event_lblHoverMouseEntered

    private void lblHoverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHoverMouseExited
        lblHover.setVisible(false);
    }//GEN-LAST:event_lblHoverMouseExited

    private void lblHinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseClicked
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lblHinhMouseClicked

    private void lblHinhMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseEntered
        lblHover.setVisible(true);
        lblHover.setOpaque(true);

        lblHover.setBackground(new Color(0, 0, 0, 50));
        //         System.out.println("enter");
    }//GEN-LAST:event_lblHinhMouseEntered

    private void lblHinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHinhMouseExited
        lblHover.setVisible(false);
    }//GEN-LAST:event_lblHinhMouseExited

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        System.out.println("click update");
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void bntResetPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntResetPassActionPerformed
        isLoading = false;
        clear();
    }//GEN-LAST:event_bntResetPassActionPerformed

    private void btnUpdatePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePassActionPerformed
        lockOnInvalid();
        updatePassword();
    }//GEN-LAST:event_btnUpdatePassActionPerformed

    private void txtMatKhauMoiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauMoiKeyReleased
        isLoading = false;
        lockOnInvalid();
    }//GEN-LAST:event_txtMatKhauMoiKeyReleased

    private void txtXacNhanMatKhauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtXacNhanMatKhauKeyReleased
         isLoading = false;
        lockOnInvalid();
    }//GEN-LAST:event_txtXacNhanMatKhauKeyReleased

    private void txtMatKhauCuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMatKhauCuKeyReleased
        isLoading = false;
        lockOnInvalid();
    }//GEN-LAST:event_txtMatKhauCuKeyReleased

    private void txtTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKeyReleased
       lblTen.setText(txtTen.getText().trim());
       try {
            lblTen.setForeground(Color.black);
            checker.checkHoten(txtTen.getText().trim());
        } catch (ValidatorException ex) {
            lblTen.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtTenKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        lblEmail.setText(txtEmail.getText().trim());
        try {
            lblEmail.setForeground(Color.black);
            checker.checkEmail(txtEmail.getText().trim());
        } catch (ValidatorException ex) {
            lblEmail.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtTenTKMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTenTKMouseReleased
        
    }//GEN-LAST:event_txtTenTKMouseReleased

    private void btnRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRefreshMouseClicked
        reset();
    }//GEN-LAST:event_btnRefreshMouseClicked

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtTenTKKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenTKKeyReleased
       lblTenTK.setText(txtTenTK.getText().trim());
        try {
            lblTenTK.setForeground(Color.black);
            checker.checkFormatTenTK(txtTenTK.getText().trim());
        } catch (ValidatorException ex) {
            lblTenTK.setForeground(Color.red);
        }
    }//GEN-LAST:event_txtTenTKKeyReleased

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
            java.util.logging.Logger.getLogger(QLProfileFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLProfileFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLProfileFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLProfileFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QLProfileFrame pf = new QLProfileFrame();
                 pf.renderFrame(); pf.setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgrGioiTinh;
    private com.org.app.customui.ButtonGradient bntResetPass;
    private javax.swing.JButton btnRefresh;
    private com.org.app.customui.ButtonGradient btnUpdate;
    private com.org.app.customui.ButtonGradient btnUpdatePass;
    private com.toedter.calendar.JDateChooser dcNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrorMess;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHideMK;
    private javax.swing.JLabel lblHideMKMoi;
    private javax.swing.JLabel lblHideXacNhan;
    private javax.swing.JLabel lblHinh;
    private javax.swing.JLabel lblHinh1;
    private javax.swing.JLabel lblHover;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNoteMK;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblTen;
    private javax.swing.JLabel lblTenTK;
    private javax.swing.JPanel pnlChangePassword;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JLayeredPane pnlHinh;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlUpdateProfile;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private com.org.app.customui.InputTextField txtEmail;
    private com.org.app.customui.PasswordTextField txtMatKhauCu;
    private com.org.app.customui.PasswordTextField txtMatKhauMoi;
    private javax.swing.JFormattedTextField txtPhone;
    private com.org.app.customui.InputTextField txtTen;
    private com.org.app.customui.InputTextField txtTenTK;
    private com.org.app.customui.PasswordTextField txtXacNhanMatKhau;
    // End of variables declaration//GEN-END:variables


}

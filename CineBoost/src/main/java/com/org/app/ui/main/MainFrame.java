/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.main;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.controller.ThongKeDAO;
import com.org.app.entity.NhanVien;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.Authenticator;
import com.org.app.helper.DateHelper;
import com.org.app.helper.MessageHelper;
import com.org.app.helper.SettingIconHelper;
import com.org.app.ui.banhang.DatMenuFrame;
import com.org.app.ui.banhang.LichChieuTrongNgayFrame;
import com.org.app.ui.login.LoginFrame;
import com.org.app.ui.quanly.QLDoAnKemFrame;
import com.org.app.ui.quanly.QLDonThanhToanFrame;
import com.org.app.ui.quanly.QLKhachHangFrame;
import com.org.app.ui.quanly.QLNhanVienFrame;
import com.org.app.ui.quanly.QLPhimFrame;
import com.org.app.ui.quanly.QLProfileFrame;
import com.org.app.ui.quanly.QLSuatChieuFrame;
import com.org.app.ui.quanly.TraCuuLichChieuFrame;
import com.org.app.ui.thongke.TongHopThongKeFrame;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.MouseHoverEffect;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.helper.ImageUtil;
import com.org.app.ui.banhang.ThanhToanFrame;
import com.org.app.util.CalendarAndClock;
import com.org.app.util.LabelWrapperInterface;
import com.org.app.util.SideMenuButton;
import com.org.app.util.SubFrame;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author intfs
 */
public class MainFrame extends javax.swing.JFrame implements LabelWrapperInterface{
    HashMap<JLabel, SideMenuButton> sideMenuButtonList;
    HashMap<JLabel, String> buttonAndFrame;
    HashMap<String, ButtonSubFrameCard> cards;
    private String currentFrame = "";
    CardLayout l;
    int xy, xx;
    public static boolean isDone = false;

    /**
     * Creates new form mainFrame
     */
    public MainFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        l = (CardLayout) pnlRight.getLayout();
//        setTitleSideMenuFont();
        sideMenuButtonList = new HashMap<>();
        this.setIconImage(new ImageIcon(MainFrame.class.getResource("/icon/boot_noSologan.png")).getImage()); //this.setIconImage(XImage.getAppIcon());
//        Authenticator.USER = new NhanVien("QL00006", "Trần Vân", DateHelper.toDate("23/02/1998"), true, "0329436123", "vantht@gmail.com", true, "user1.png", "vantht", "P12345");
        setUpSideMenu();
        setProfile();
        buttonAndFrame = new HashMap<>();
        cards = new HashMap<>();
        setLocationRelativeTo(null);
        
            loading();            
            this.pack();
        // Giờ
        try {
            launchClock();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Title
        lblTitle.setText("  Cineboost");
        currentFrame = LichChieuTrongNgayFrame.CARD_NAME_MAIN;
        showCard(LichChieuTrongNgayFrame.CARD_NAME_MAIN);
    }

    private void loading() {
        try {
            
            ExecutorService service = Executors.newFixedThreadPool(5);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
                    createSubFrame();
                    return null;
                }
            }));
            service.invokeAll(calls);
            isDone = true;
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    private void lockOnRole() {
        boolean isql = Authenticator.isQuanLy();
            btnQLThongKe.setEnabled(isql);
            btnQLNhanVien.setEnabled(isql);
    }

    private void createSubFrame() {
        createSubFrameAndButtonBinding(QLSuatChieuFrame.CARD_NAME_MAIN, new QLSuatChieuFrame(), btnQLSuatChieu);
        createSubFrameAndButtonBinding(LichChieuTrongNgayFrame.CARD_NAME_MAIN, new LichChieuTrongNgayFrame(this), btnLichChieu);
        createSubFrameAndButtonBinding(QLDoAnKemFrame.CARD_NAME_MAIN, new QLDoAnKemFrame(), btnQLDoAn);
        createSubFrameAndButtonBinding(QLKhachHangFrame.CARD_NAME_MAIN, new QLKhachHangFrame(), btnQLThanhVien);
        createSubFrameAndButtonBinding(QLPhimFrame.CARD_NAME_MAIN, new QLPhimFrame(), btnQLPhim);
        createSubFrameAndButtonBinding(TraCuuLichChieuFrame.CARD_NAME_MAIN, new TraCuuLichChieuFrame(this), btnTraLichChieu);
        createSubFrameAndButtonBinding(QLDonThanhToanFrame.CARD_NAME_MAIN, new QLDonThanhToanFrame(), btnQLThanhToan);
        createSubFrameAndButtonBinding(QLProfileFrame.CARD_NAME_MAIN, new QLProfileFrame(this), lblProfile);
        createSubFrameAndButtonBinding(DatMenuFrame.CARD_NAME_MAIN, new DatMenuFrame(this), btnDat);
        createSubFrameAndButtonBinding(AboutFrame.CARD_NAME_MAIN, new AboutFrame(), btnAbout);

        if(Authenticator.isQuanLy()) {
            createSubFrameAndButtonBinding(TongHopThongKeFrame.CARD_NAME_MAIN, new TongHopThongKeFrame(), btnQLThongKe);
            createSubFrameAndButtonBinding(QLNhanVienFrame.CARD_NAME_MAIN, new QLNhanVienFrame(), btnQLNhanVien);
        }

        addPanels();
    }

    public void addSubPanelToMain(String cardName, SubFrame subFrame) {
        pnlRight.add(cardName, subFrame.getContentPanelFor(pnlRight));
    }

    private void createSubFrameAndButtonBinding(String cardName, SubFrame sf, JLabel btn) {
        cards.put(cardName, getButtonSubFrameOf(cardName, sf, btn));
    }

    private ButtonSubFrameCard getButtonSubFrameOf(String name, SubFrame f, JLabel l) {
        return new ButtonSubFrameCard(name, f, l);
    }

    private void addPanels() {
        for (String k : cards.keySet()) {
            ButtonSubFrameCard bf = cards.get(k);
            pnlRight.add(k, bf.getFrame().getContentPanelFor(pnlRight));
        }
    }

    public void setProfile() {
        wrap(lblTenTK, Authenticator.USER.getTentk());
        String pic = Authenticator.USER.getAnh();
        SettingIconHelper.setProfilePic(lblProfile,pic);
        lblProfile.repaint();
        lblProfile.revalidate();
    }

    private void setUpSideMenu() {
        SettingIconHelper.setIconSourceFor(lblSideBck, "sideBar_bckg.png");
        JComponent sideBtns[] = {btnLichChieu, btnTraLichChieu, btnDat, btnQLPhim, btnQLSuatChieu, btnQLDoAn, btnQLNhanVien, btnQLThanhVien, btnQLThanhToan, btnQLThongKe, btnExit, btnAbout};
        JComponent layeredPnls[] = {lpnl2, lpnl1, lpnl3, lpnl4, lpnl10, lpnl5, lpnl6, lpnl7, lpnl8, lpnl9, lpnl11, lpnl12};
        String rollIcons[] = {"icons8_dashboard_26px_1_roll.png", "icons8_content_26px_roll.png", "icons8_ticket_26px_roll.png", 
            "icons8_movie_26px_roll.png", "icons8_schedule_26px_roll.png", "icons8_Street_Food_26px_roll.png","icons8_name_tag_26px_roll.png","icons8_member_26px_roll.png", 
            "icons8_receipt_26px_roll.png","icons8_report_file_26px_roll.png","icons8_Logout_26px_roll.png","icons8_settings_26px_roll.png"};
        for (int i = 0; i < sideBtns.length; i++) {
            SideMenuButton sbtn = new SideMenuButton((JLabel) sideBtns[i], layeredPnls[i], ColorAndIconBank.SIDEMENU_COLOR_HOVERS, true);
            sbtn.setRollOverIcon(ScaleImageIconGenerator.getImageFromResources(rollIcons[i]));
            sideMenuButtonList.put((JLabel)sideBtns[i], sbtn);
            if (sbtn.getButton().equals(btnExit)) {
                sideMenuButtonList.remove(i);
            }
        }

        addSideMenuButtonEffect(layeredPnls, sideBtns);
        lockOnRole();
    }

    private void addSideMenuButtonEffect(JComponent layeredPnls[], JComponent sideBtns[]) {
        lblBanHang.setBackground(ColorAndIconBank.SIDEMENU_TEXT_TITLED);
        lblQuanLy.setBackground(ColorAndIconBank.SIDEMENU_TEXT_TITLED);
    }

    private SideMenuButton getSelectedButton(JLabel lbl) {
        return sideMenuButtonList.get(lbl);
    }

    private void clearButtonSelected(SideMenuButton btn) {
        for (SideMenuButton b : sideMenuButtonList.values()) {
            b.setIsSelected(false);
        }
        btn.setIsSelected(true);;
    }

    public void setClickedListener(MouseEvent evt) {
        SideMenuButton btn = getSelectedButton((JLabel) evt.getSource());
        if (!btn.getButton().isEnabled()) {
            lockOnMaintance("Chức năng chỉ dành cho quản lý.");
        }
        if (btn.isIsSelected() || !btn.getButton().isEnabled()) {
            return;
        }
        clearButtonSelected(btn);
        showCard(btn.getButton());
    }

    public void clickButton(JLabel lbl) {
        SideMenuButton btn = sideMenuButtonList.get(lbl);
        if (!btn.getButton().isEnabled()) {
            lockOnMaintance("Chức năng chỉ dành cho quản lý.");
        }
        if (btn.isIsSelected() || !btn.getButton().isEnabled()) {
            return;
        }
        clearButtonSelected(btn);
        showCard(btn.getButton());
    }
//    public void set
    private void lockOnMaintance(String mess) {
        MessageHelper.message(this, mess);
    }

   private void showCard(JLabel lbl) {
//         pnlRight.removeAll();
         String cardName = buttonAndFrame.get(lbl);
         if(!lbl.isEnabled()) {
             return;
         }else if(getCurrentFrame().getClass().equals(DatMenuFrame.class)) {
             DatMenuFrame datMenu = (DatMenuFrame)getCurrentFrame();
             boolean passing = checkingDatDon();
             System.out.println("oh shit");
             if(!passing) {
                 datMenu.showCard(DatMenuFrame.GuiOf.THANHTOAN); return;
             }else {
                datMenu.getDatVe().isNotInThreeDay = false;
                if(DatMenuFrame.currentFrame.equals(DatMenuFrame.VE_CARD)) {
                   datMenu.showCard(DatMenuFrame.GuiOf.HOME);
                }else {
                    System.out.println("render tai thanh toan");
                    datMenu.renderFrame();
                }
             }
             
         }
         currentFrame = cardName;
         cards.get(cardName).getFrame().getSubPanelCreator().render();
         System.out.println(pnlRight.getComponentCount());
         l.show(pnlRight,cardName);
         pnlRight.repaint();
         pnlRight.revalidate();
    }
   
   private boolean checkingDatDon() {
        DatMenuFrame datMenu = (DatMenuFrame)getCurrentFrame();
        boolean isNotHavingDon = datMenu.getTTAndDA().isDoAnDatListEmpty();
        if((datMenu.isHome && !isNotHavingDon)) {
            return MessageHelper.confirm(this, "Đơn chưa thanh toán, bạn có muốn hủy?");
        }else if(DatMenuFrame.currentFrame.equals(DatMenuFrame.THANHTOAN_CARD)) {
            if(!ThanhToanFrame.isDoAnOnly || ThanhToanFrame.isBoth || !isNotHavingDon) {
                 return MessageHelper.confirm(this, "Đơn chưa thanh toán, bạn có muốn hủy?");
            }
        }
        return true;
   }
        
   

    public void showCard(String cardName) {
        System.out.println("showCard() " + cardName);
        pnlRight.repaint();
        pnlRight.revalidate();
        
        clickButton(cards.get(cardName).getBtn());
    }
    
    
     
    public void showBooking(SuatChieu sc) {
        System.out.println("show datVeFrame from main");
        showCard(DatMenuFrame.CARD_NAME_MAIN);
        DatMenuFrame menu = (DatMenuFrame)cards.get(DatMenuFrame.CARD_NAME_MAIN).getFrame();
        menu.showDatVeFrameWith(sc);
    }
    
    public void showLongTerm(SuatChieu sc) {
          ((DatMenuFrame)cards.get(DatMenuFrame.CARD_NAME_MAIN).getFrame())
            .getDatVe().isNotInThreeDay = true;
        showCard(DatMenuFrame.CARD_NAME_MAIN);
          DatMenuFrame menu = (DatMenuFrame)cards.get(DatMenuFrame.CARD_NAME_MAIN).getFrame();
        menu.showVeFrameLongTerm(sc);
    }
    
    public void showSuatNgay(java.util.Date date) {
        showCard(QLSuatChieuFrame.CARD_NAME_MAIN);
        QLSuatChieuFrame frame = (QLSuatChieuFrame) cards.get(QLSuatChieuFrame.CARD_NAME_MAIN).getFrame();
        frame.setSuatNgay(date);
    }
    
    public SubFrame getCurrentFrame() {
       return cards.get(currentFrame).getFrame();
    }
    
    public SubFrame getFrame(String name) {
        return cards.get(name).getFrame();
    }
     
    class ButtonSubFrameCard {
         JLabel btn;
         SubFrame frame;
         String cardName;
         ButtonSubFrameCard(String name, SubFrame frame, JLabel btn) {
             this.btn = btn;
             this.frame  = frame;
             this.cardName = name;
             buttonAndFrame.put(btn,name);
         }


         
        public JLabel getBtn() {
            return btn;
        }

        public void setBtn(JLabel btn) {
            this.btn = btn;
        }

        public SubFrame getFrame() {
            return frame;
        }

        public void setFrame(SubFrame frame) {
            this.frame = frame;
        }

    }

    public void dangXuat() {
        Authenticator.logOut();
        LoginFrame login = new LoginFrame();
        login.setVisible(true);
        this.dispose();
    }

    private void launchClock() throws IOException {
        new java.util.Timer().scheduleAtFixedRate(new CalendarAndClock() {
            @Override
            public void run() {
                String time = CalendarAndClock.Clock.getTime();
                String date = CalendarAndClock.Calendar.getDate();
//                lblClock.setText(CalendarAndClock.Clock.getTime());
//                lblDate.setText(CalendarAndClock.Calendar.getDate());
//                lblA.setText(CalendarAndClock.Clock.getAMPM());
                lblClock.setText( date + " " + time + " ");
            }
        }, 0, 1000);
    }

    public void setColorEntered_Minimize() {
        pnlMinimize.setBackground(Color.decode("#999999"));
    }

    public void setColorEntered_Maximize() {
        pnlMaximize.setBackground(Color.decode("#999999"));
    }

    public void setColorEntered_Close() {
        pnlClose.setBackground(Color.decode("#999999"));
    }

    public void setColorExit_Minimize() {
        pnlMinimize.setBackground(Color.decode("#000000"));
    }

    public void setColorExit__Maximize() {
        pnlMaximize.setBackground(Color.decode("#000000"));
    }

    public void setColorExit__Close() {
        pnlClose.setBackground(Color.decode("#000000"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLeft = new javax.swing.JPanel();
        pnlQuanLy = new javax.swing.JPanel();
        lpnl4 = new javax.swing.JLayeredPane();
        btnQLPhim = new javax.swing.JLabel();
        lpnl10 = new javax.swing.JLayeredPane();
        btnQLSuatChieu = new javax.swing.JLabel();
        lpnl5 = new javax.swing.JLayeredPane();
        btnQLDoAn = new javax.swing.JLabel();
        lpnl6 = new javax.swing.JLayeredPane();
        btnQLNhanVien = new javax.swing.JLabel();
        lpnl7 = new javax.swing.JLayeredPane();
        btnQLThanhVien = new javax.swing.JLabel();
        lpnl8 = new javax.swing.JLayeredPane();
        btnQLThanhToan = new javax.swing.JLabel();
        lpnl9 = new javax.swing.JLayeredPane();
        btnQLThongKe = new javax.swing.JLabel();
        pnlBanHang = new javax.swing.JPanel();
        lpnl2 = new javax.swing.JLayeredPane();
        btnLichChieu = new javax.swing.JLabel();
        lpnl1 = new javax.swing.JLayeredPane();
        btnTraLichChieu = new javax.swing.JLabel();
        lpnl3 = new javax.swing.JLayeredPane();
        btnDat = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblQuanLy = new javax.swing.JLabel();
        lblBanHang = new javax.swing.JLabel();
        pnlProfile = new javax.swing.JPanel();
        lblProfile = new javax.swing.JLabel();
        lblTenTK = new javax.swing.JLabel();
        lpnl11 = new javax.swing.JLayeredPane();
        btnExit = new javax.swing.JLabel();
        lpnl12 = new javax.swing.JLayeredPane();
        btnAbout = new javax.swing.JLabel();
        lblSideBck = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblClock = new javax.swing.JLabel();
        pnlHome = new javax.swing.JPanel();
        pnlActions = new javax.swing.JPanel();
        pnlMinimize = new javax.swing.JPanel();
        lblMinimize = new javax.swing.JLabel();
        pnlMaximize = new javax.swing.JPanel();
        lblMaximize = new javax.swing.JLabel();
        pnlClose = new javax.swing.JPanel();
        lblClose = new javax.swing.JLabel();
        pnlTitle = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mainframe");
        setMaximumSize(new java.awt.Dimension(890, 700));
        setMinimumSize(new java.awt.Dimension(890, 700));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(890, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setPreferredSize(new java.awt.Dimension(190, 715));
        pnlLeft.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlQuanLy.setOpaque(false);
        pnlQuanLy.setLayout(new java.awt.GridLayout(7, 0));

        lpnl4.setPreferredSize(new java.awt.Dimension(190, 40));

        btnQLPhim.setBackground(new java.awt.Color(240, 239, 239));
        btnQLPhim.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLPhim.setForeground(new java.awt.Color(51, 51, 51));
        btnQLPhim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLPhim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_movie_26px.png"))); // NOI18N
        btnQLPhim.setText("Phim");
        btnQLPhim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLPhim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnQLPhim.setIconTextGap(8);
        btnQLPhim.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLPhim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLPhimMouseReleased(evt);
            }
        });

        lpnl4.setLayer(btnQLPhim, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl4Layout = new javax.swing.GroupLayout(lpnl4);
        lpnl4.setLayout(lpnl4Layout);
        lpnl4Layout.setHorizontalGroup(
            lpnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnQLPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lpnl4Layout.setVerticalGroup(
            lpnl4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQLPhim, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pnlQuanLy.add(lpnl4);

        lpnl10.setPreferredSize(new java.awt.Dimension(190, 40));

        btnQLSuatChieu.setBackground(new java.awt.Color(240, 239, 239));
        btnQLSuatChieu.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLSuatChieu.setForeground(new java.awt.Color(51, 51, 51));
        btnQLSuatChieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLSuatChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_schedule_26px.png"))); // NOI18N
        btnQLSuatChieu.setText("Suất chiếu");
        btnQLSuatChieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLSuatChieu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnQLSuatChieu.setIconTextGap(8);
        btnQLSuatChieu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLSuatChieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLSuatChieuMouseReleased(evt);
            }
        });

        lpnl10.setLayer(btnQLSuatChieu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl10Layout = new javax.swing.GroupLayout(lpnl10);
        lpnl10.setLayout(lpnl10Layout);
        lpnl10Layout.setHorizontalGroup(
            lpnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl10Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(btnQLSuatChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lpnl10Layout.setVerticalGroup(
            lpnl10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQLSuatChieu, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pnlQuanLy.add(lpnl10);

        lpnl5.setPreferredSize(new java.awt.Dimension(190, 40));

        btnQLDoAn.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLDoAn.setForeground(new java.awt.Color(51, 51, 51));
        btnQLDoAn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLDoAn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Street_Food_26px.png"))); // NOI18N
        btnQLDoAn.setText("Đồ ăn");
        btnQLDoAn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLDoAn.setIconTextGap(10);
        btnQLDoAn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLDoAn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLDoAnMouseReleased(evt);
            }
        });

        lpnl5.setLayer(btnQLDoAn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl5Layout = new javax.swing.GroupLayout(lpnl5);
        lpnl5.setLayout(lpnl5Layout);
        lpnl5Layout.setHorizontalGroup(
            lpnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl5Layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(btnQLDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        lpnl5Layout.setVerticalGroup(
            lpnl5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQLDoAn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlQuanLy.add(lpnl5);

        lpnl6.setBackground(new java.awt.Color(72, 143, 177));

        btnQLNhanVien.setBackground(new java.awt.Color(102, 102, 102));
        btnQLNhanVien.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLNhanVien.setForeground(new java.awt.Color(51, 51, 51));
        btnQLNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_name_tag_26px.png"))); // NOI18N
        btnQLNhanVien.setText("Nhân viên");
        btnQLNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLNhanVien.setIconTextGap(10);
        btnQLNhanVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLNhanVienMouseReleased(evt);
            }
        });

        lpnl6.setLayer(btnQLNhanVien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl6Layout = new javax.swing.GroupLayout(lpnl6);
        lpnl6.setLayout(lpnl6Layout);
        lpnl6Layout.setHorizontalGroup(
            lpnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl6Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lpnl6Layout.setVerticalGroup(
            lpnl6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl6Layout.createSequentialGroup()
                .addComponent(btnQLNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlQuanLy.add(lpnl6);

        lpnl7.setPreferredSize(new java.awt.Dimension(190, 40));

        btnQLThanhVien.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLThanhVien.setForeground(new java.awt.Color(51, 51, 51));
        btnQLThanhVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLThanhVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_member_26px.png"))); // NOI18N
        btnQLThanhVien.setText("Thành viên");
        btnQLThanhVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLThanhVien.setIconTextGap(10);
        btnQLThanhVien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLThanhVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLThanhVienMouseReleased(evt);
            }
        });

        lpnl7.setLayer(btnQLThanhVien, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl7Layout = new javax.swing.GroupLayout(lpnl7);
        lpnl7.setLayout(lpnl7Layout);
        lpnl7Layout.setHorizontalGroup(
            lpnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl7Layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addComponent(btnQLThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        lpnl7Layout.setVerticalGroup(
            lpnl7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl7Layout.createSequentialGroup()
                .addComponent(btnQLThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlQuanLy.add(lpnl7);

        lpnl8.setBackground(new java.awt.Color(0, 102, 102));
        lpnl8.setPreferredSize(new java.awt.Dimension(190, 40));

        btnQLThanhToan.setBackground(new java.awt.Color(0, 153, 153));
        btnQLThanhToan.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLThanhToan.setForeground(new java.awt.Color(51, 51, 51));
        btnQLThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLThanhToan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_receipt_26px.png"))); // NOI18N
        btnQLThanhToan.setText("Đơn thanh toán");
        btnQLThanhToan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLThanhToan.setIconTextGap(10);
        btnQLThanhToan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLThanhToanMouseReleased(evt);
            }
        });

        lpnl8.setLayer(btnQLThanhToan, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl8Layout = new javax.swing.GroupLayout(lpnl8);
        lpnl8.setLayout(lpnl8Layout);
        lpnl8Layout.setHorizontalGroup(
            lpnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnl8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnQLThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lpnl8Layout.setVerticalGroup(
            lpnl8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnQLThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlQuanLy.add(lpnl8);

        btnQLThongKe.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnQLThongKe.setForeground(new java.awt.Color(51, 51, 51));
        btnQLThongKe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnQLThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_report_file_26px.png"))); // NOI18N
        btnQLThongKe.setText("Thống kê");
        btnQLThongKe.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQLThongKe.setIconTextGap(10);
        btnQLThongKe.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnQLThongKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnQLThongKeMouseReleased(evt);
            }
        });

        lpnl9.setLayer(btnQLThongKe, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl9Layout = new javax.swing.GroupLayout(lpnl9);
        lpnl9.setLayout(lpnl9Layout);
        lpnl9Layout.setHorizontalGroup(
            lpnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl9Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnQLThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lpnl9Layout.setVerticalGroup(
            lpnl9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl9Layout.createSequentialGroup()
                .addComponent(btnQLThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlQuanLy.add(lpnl9);

        pnlLeft.add(pnlQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 190, 270));

        pnlBanHang.setOpaque(false);
        pnlBanHang.setLayout(new java.awt.GridLayout(3, 1, 0, 2));

        lpnl2.setPreferredSize(new java.awt.Dimension(190, 30));
        lpnl2.setRequestFocusEnabled(false);

        btnLichChieu.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnLichChieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnLichChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_dashboard_26px_1.png"))); // NOI18N
        btnLichChieu.setText("DashBoard");
        btnLichChieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLichChieu.setIconTextGap(10);
        btnLichChieu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLichChieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnLichChieuMouseReleased(evt);
            }
        });

        lpnl2.setLayer(btnLichChieu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl2Layout = new javax.swing.GroupLayout(lpnl2);
        lpnl2.setLayout(lpnl2Layout);
        lpnl2Layout.setHorizontalGroup(
            lpnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnl2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lpnl2Layout.setVerticalGroup(
            lpnl2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl2Layout.createSequentialGroup()
                .addComponent(btnLichChieu, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pnlBanHang.add(lpnl2);

        lpnl1.setPreferredSize(new java.awt.Dimension(190, 40));

        btnTraLichChieu.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnTraLichChieu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTraLichChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_content_26px.png"))); // NOI18N
        btnTraLichChieu.setText("Tra lịch chiếu");
        btnTraLichChieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTraLichChieu.setIconTextGap(10);
        btnTraLichChieu.setPreferredSize(new java.awt.Dimension(113, 40));
        btnTraLichChieu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTraLichChieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnTraLichChieuMouseReleased(evt);
            }
        });

        lpnl1.setLayer(btnTraLichChieu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl1Layout = new javax.swing.GroupLayout(lpnl1);
        lpnl1.setLayout(lpnl1Layout);
        lpnl1Layout.setHorizontalGroup(
            lpnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnl1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnTraLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lpnl1Layout.setVerticalGroup(
            lpnl1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnl1Layout.createSequentialGroup()
                .addComponent(btnTraLichChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlBanHang.add(lpnl1);

        lpnl3.setPreferredSize(new java.awt.Dimension(190, 40));

        btnDat.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnDat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_ticket_26px.png"))); // NOI18N
        btnDat.setText("Đặt vé - đồ ăn");
        btnDat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDat.setIconTextGap(10);
        btnDat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnDatMouseReleased(evt);
            }
        });

        lpnl3.setLayer(btnDat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl3Layout = new javax.swing.GroupLayout(lpnl3);
        lpnl3.setLayout(lpnl3Layout);
        lpnl3Layout.setHorizontalGroup(
            lpnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnl3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lpnl3Layout.setVerticalGroup(
            lpnl3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDat, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlBanHang.add(lpnl3);

        pnlLeft.add(pnlBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 190, 120));

        jLabel1.setFont(new java.awt.Font("Leelawadee UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_documentary_48px.png"))); // NOI18N
        jLabel1.setText("CineBoost");
        pnlLeft.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 190, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));
        pnlLeft.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, 10));

        lblQuanLy.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblQuanLy.setForeground(new java.awt.Color(51, 51, 51));
        lblQuanLy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQuanLy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/double_arrow.png"))); // NOI18N
        lblQuanLy.setText("Quản Lý");
        lblQuanLy.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(0, 102, 102)));
        lblQuanLy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblQuanLy.setIconTextGap(10);
        lblQuanLy.setOpaque(true);
        lblQuanLy.setPreferredSize(new java.awt.Dimension(40, 17));
        lblQuanLy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblQuanLyMouseReleased(evt);
            }
        });
        pnlLeft.add(lblQuanLy, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 30));

        lblBanHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblBanHang.setForeground(new java.awt.Color(51, 51, 51));
        lblBanHang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/double_arrow.png"))); // NOI18N
        lblBanHang.setText("Bán hàng");
        lblBanHang.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(98, 107, 150)));
        lblBanHang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBanHang.setIconTextGap(10);
        lblBanHang.setOpaque(true);
        lblBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lblBanHangMouseReleased(evt);
            }
        });
        pnlLeft.add(lblBanHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 190, 30));

        pnlProfile.setOpaque(false);
        pnlProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlProfileMouseClicked(evt);
            }
        });

        lblProfile.setToolTipText("Double click để chỉnh sửa thông tin");
        lblProfile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblProfile.setPreferredSize(new java.awt.Dimension(40, 40));
        lblProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblProfileMouseClicked(evt);
            }
        });

        lblTenTK.setFont(new java.awt.Font("Courier New", 1, 14)); // NOI18N
        lblTenTK.setText("TenTK");
        lblTenTK.setToolTipText("Double click để chỉnh sửa thông tin");
        lblTenTK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTenTK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTenTKMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProfileLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTenTK, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlProfileLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenTK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pnlLeft.add(pnlProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 190, 60));

        lpnl11.setMaximumSize(new java.awt.Dimension(190, 40));
        lpnl11.setPreferredSize(new java.awt.Dimension(190, 40));

        btnExit.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_Logout_26px.png"))); // NOI18N
        btnExit.setText("Đăng xuất");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnExitMouseReleased(evt);
            }
        });

        lpnl11.setLayer(btnExit, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl11Layout = new javax.swing.GroupLayout(lpnl11);
        lpnl11.setLayout(lpnl11Layout);
        lpnl11Layout.setHorizontalGroup(
            lpnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl11Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lpnl11Layout.setVerticalGroup(
            lpnl11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnl11Layout.createSequentialGroup()
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlLeft.add(lpnl11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 190, 40));

        btnAbout.setFont(new java.awt.Font("Corbel", 1, 14)); // NOI18N
        btnAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_settings_26px.png"))); // NOI18N
        btnAbout.setText("About");
        btnAbout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAboutMouseReleased(evt);
            }
        });

        lpnl12.setLayer(btnAbout, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnl12Layout = new javax.swing.GroupLayout(lpnl12);
        lpnl12.setLayout(lpnl12Layout);
        lpnl12Layout.setHorizontalGroup(
            lpnl12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnl12Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(btnAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        lpnl12Layout.setVerticalGroup(
            lpnl12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAbout, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnlLeft.add(lpnl12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 590, 190, 40));
        pnlLeft.add(lblSideBck, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, 680));

        getContentPane().add(pnlLeft);
        pnlLeft.setBounds(0, 25, 190, 681);

        pnlRight.setBackground(new java.awt.Color(204, 255, 255));
        pnlRight.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlRight.setOpaque(false);
        pnlRight.setPreferredSize(new java.awt.Dimension(700, 650));
        pnlRight.setLayout(new java.awt.CardLayout());
        getContentPane().add(pnlRight);
        pnlRight.setBounds(190, 25, 700, 650);

        jPanel1.setBackground(new java.awt.Color(193, 210, 202));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 25));

        lblClock.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblClock.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblClock.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(474, Short.MAX_VALUE)
                .addComponent(lblClock, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(190, 675, 700, 25);

        pnlHome.setBackground(new java.awt.Color(51, 51, 51));
        pnlHome.setMaximumSize(new java.awt.Dimension(890, 25));
        pnlHome.setMinimumSize(new java.awt.Dimension(890, 25));
        pnlHome.setPreferredSize(new java.awt.Dimension(890, 30));
        pnlHome.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnlHomeMouseDragged(evt);
            }
        });
        pnlHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlHomeMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlHomeMousePressed(evt);
            }
        });
        pnlHome.setLayout(new java.awt.BorderLayout());

        pnlActions.setBackground(new java.awt.Color(51, 51, 51));
        pnlActions.setMinimumSize(new java.awt.Dimension(90, 28));
        pnlActions.setPreferredSize(new java.awt.Dimension(90, 28));
        pnlActions.setLayout(new java.awt.GridLayout(1, 0));

        pnlMinimize.setBackground(new java.awt.Color(51, 51, 51));

        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_minus_18px_1.png"))); // NOI18N
        lblMinimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblMinimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMinimizeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMinimizeMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlMinimizeLayout = new javax.swing.GroupLayout(pnlMinimize);
        pnlMinimize.setLayout(pnlMinimizeLayout);
        pnlMinimizeLayout.setHorizontalGroup(
            pnlMinimizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pnlMinimizeLayout.setVerticalGroup(
            pnlMinimizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMinimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pnlActions.add(pnlMinimize);

        pnlMaximize.setBackground(new java.awt.Color(51, 51, 51));

        lblMaximize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_rectangle_stroked_18px.png"))); // NOI18N
        lblMaximize.setEnabled(false);

        javax.swing.GroupLayout pnlMaximizeLayout = new javax.swing.GroupLayout(pnlMaximize);
        pnlMaximize.setLayout(pnlMaximizeLayout);
        pnlMaximizeLayout.setHorizontalGroup(
            pnlMaximizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaximize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pnlMaximizeLayout.setVerticalGroup(
            pnlMaximizeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMaximize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pnlActions.add(pnlMaximize);

        pnlClose.setBackground(new java.awt.Color(51, 51, 51));

        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_multiply_18px_1.png"))); // NOI18N
        lblClose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblCloseMousePressed(evt);
            }
        });

        javax.swing.GroupLayout pnlCloseLayout = new javax.swing.GroupLayout(pnlClose);
        pnlClose.setLayout(pnlCloseLayout);
        pnlCloseLayout.setHorizontalGroup(
            pnlCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pnlCloseLayout.setVerticalGroup(
            pnlCloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        pnlActions.add(pnlClose);

        pnlHome.add(pnlActions, java.awt.BorderLayout.LINE_END);

        pnlTitle.setBackground(new java.awt.Color(102, 102, 102));
        pnlTitle.setOpaque(false);
        pnlTitle.setPreferredSize(new java.awt.Dimension(200, 30));

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("  Title");
        lblTitle.setPreferredSize(new java.awt.Dimension(200, 16));
        pnlTitle.add(lblTitle);

        pnlHome.add(pnlTitle, java.awt.BorderLayout.LINE_START);

        getContentPane().add(pnlHome);
        pnlHome.setBounds(0, 0, 890, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExitMouseReleased
        boolean luaChon = MessageHelper.confirm(this, "Bạn có muốn đăng xuất không");
        if (luaChon) {
            dangXuat();
        } else {
            JOptionPane.getRootFrame().dispose();
        }
    }//GEN-LAST:event_btnExitMouseReleased

    private void btnDatMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDatMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnDatMouseReleased

    private void btnQLNhanVienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLNhanVienMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLNhanVienMouseReleased

    private void lblBanHangMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBanHangMouseReleased

    }//GEN-LAST:event_lblBanHangMouseReleased

    private void btnLichChieuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLichChieuMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnLichChieuMouseReleased

    private void btnTraLichChieuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTraLichChieuMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnTraLichChieuMouseReleased

    private void lblQuanLyMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuanLyMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lblQuanLyMouseReleased

    private void btnQLPhimMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLPhimMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLPhimMouseReleased

    private void btnQLDoAnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLDoAnMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLDoAnMouseReleased

    private void btnQLThanhToanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLThanhToanMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLThanhToanMouseReleased

    private void btnQLThanhVienMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLThanhVienMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLThanhVienMouseReleased

    private void btnQLThongKeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLThongKeMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLThongKeMouseReleased

    private void lblProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblProfileMouseClicked
        if (evt.getClickCount() == 2) {
            showCard(lblProfile);
        }
    }//GEN-LAST:event_lblProfileMouseClicked

    private void btnAboutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAboutMouseReleased
        // TODO add your handling code here:
        setClickedListener(evt);

    }//GEN-LAST:event_btnAboutMouseReleased

    private void btnQLSuatChieuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnQLSuatChieuMouseReleased
        setClickedListener(evt);
    }//GEN-LAST:event_btnQLSuatChieuMouseReleased

    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseEntered
        // TODO add your handling code here:
        setColorEntered_Minimize();
    }//GEN-LAST:event_lblMinimizeMouseEntered

    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMouseExited
        // TODO add your handling code here:
        setColorExit_Minimize();
    }//GEN-LAST:event_lblMinimizeMouseExited

    private void lblMinimizeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMinimizeMousePressed
        MainFrame.this.setState(Frame.ICONIFIED);
    }//GEN-LAST:event_lblMinimizeMousePressed

    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseEntered
        // TODO add your handling code here:
        setColorEntered_Close();
    }//GEN-LAST:event_lblCloseMouseEntered

    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMouseExited
        // TODO add your handling code here:
        setColorExit__Close();
    }//GEN-LAST:event_lblCloseMouseExited

    private void lblCloseMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMousePressed
        System.exit(0);
    }//GEN-LAST:event_lblCloseMousePressed

    private void pnlHomeMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHomeMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_pnlHomeMouseDragged

    private void pnlHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHomeMouseClicked
//        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
//            if (MainFrame.this.getExtendedState() == MAXIMIZED_BOTH) {
//                MainFrame.this.setExtendedState(JFrame.NORMAL);
//            } else {
//                MainFrame.this.setExtendedState(MAXIMIZED_BOTH);
//            }
//        }
    }//GEN-LAST:event_pnlHomeMouseClicked

    private void pnlHomeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlHomeMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_pnlHomeMousePressed

    private void lblTenTKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTenTKMouseClicked
        if (evt.getClickCount() == 2) {
            showCard(lblProfile);
        }
    }//GEN-LAST:event_lblTenTKMouseClicked

    private void pnlProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlProfileMouseClicked
        if (evt.getClickCount() == 2) {
            showCard(lblProfile);
        }
    }//GEN-LAST:event_pnlProfileMouseClicked

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAbout;
    private javax.swing.JLabel btnDat;
    private javax.swing.JLabel btnExit;
    private javax.swing.JLabel btnLichChieu;
    private javax.swing.JLabel btnQLDoAn;
    private javax.swing.JLabel btnQLNhanVien;
    private javax.swing.JLabel btnQLPhim;
    private javax.swing.JLabel btnQLSuatChieu;
    private javax.swing.JLabel btnQLThanhToan;
    private javax.swing.JLabel btnQLThanhVien;
    private javax.swing.JLabel btnQLThongKe;
    private javax.swing.JLabel btnTraLichChieu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblBanHang;
    private javax.swing.JLabel lblClock;
    private javax.swing.JLabel lblClose;
    private javax.swing.JLabel lblMaximize;
    private javax.swing.JLabel lblMinimize;
    private javax.swing.JLabel lblProfile;
    private javax.swing.JLabel lblQuanLy;
    private javax.swing.JLabel lblSideBck;
    private javax.swing.JLabel lblTenTK;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLayeredPane lpnl1;
    private javax.swing.JLayeredPane lpnl10;
    private javax.swing.JLayeredPane lpnl11;
    private javax.swing.JLayeredPane lpnl12;
    private javax.swing.JLayeredPane lpnl2;
    private javax.swing.JLayeredPane lpnl3;
    private javax.swing.JLayeredPane lpnl4;
    private javax.swing.JLayeredPane lpnl5;
    private javax.swing.JLayeredPane lpnl6;
    private javax.swing.JLayeredPane lpnl7;
    private javax.swing.JLayeredPane lpnl8;
    private javax.swing.JLayeredPane lpnl9;
    private javax.swing.JPanel pnlActions;
    private javax.swing.JPanel pnlBanHang;
    private javax.swing.JPanel pnlClose;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMaximize;
    private javax.swing.JPanel pnlMinimize;
    private javax.swing.JPanel pnlProfile;
    private javax.swing.JPanel pnlQuanLy;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlTitle;
    // End of variables declaration//GEN-END:variables

}

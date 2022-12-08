/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.banhang;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.MessageHelper;
import com.org.app.ui.main.MainFrame;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import java.awt.CardLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author intfs
 */
public class DatMenuFrame extends javax.swing.JFrame implements SubFrame<DatMenuFrame>,SubPanelCreatorInterfaces<SubFrame>{
    SubPanelCreator<DatMenuFrame> subPanel;
    MainFrame mainFrame;
    DatVeFrame dv;
    ThanhToanFrame da;
    ThanhToanFrame tt;
    CardLayout l;
    
    HashMap<String, SubFrame> cards;
    public static final String VE_CARD = "ve";
    public static final String DOAN_CARD = "doan";
    public static final String HOME_CARD = "home";
    public static final String THANHTOAN_CARD = "thanhtoan";
    public static final String CARD_NAME_MAIN = "datmenu";
    public boolean isHome = true;
    public static String currentFrame = HOME_CARD;

    @Override
    public SubPanelCreator getSubPanelCreator() {
        return subPanel;
    }
    
    public enum GuiOf{
        DAT_VE(VE_CARD),
        DAT_DO_AN(DOAN_CARD),
        THANHTOAN(THANHTOAN_CARD),
        HOME(HOME_CARD);
        
        private String name;
        GuiOf(String name) {
            this.name = name;
        }
        
        @Override
        public String toString() {
            return name;
        }
    }
    
    /**
     * Creates new form DatMenuFrame
     */
    
    public DatMenuFrame(MainFrame mainF) {
        this();
        this.mainFrame = mainF;
    }
    public DatMenuFrame() {
        FlatIntelliJLaf.setup();
        initComponents();
        l = (CardLayout) pnlContent.getLayout();
        subPanel = createSubPanel(this);
        cards = new HashMap<>();
        setUp();
        
    }
    
    private void setUp() {
        addPanelToCard();
        addCards();
//        showCard(HOME_CARD);
    }
    
    private void addPanelToCard() {
        dv = new DatVeFrame(this);
        tt = new ThanhToanFrame(this);
//        tt = new ThanhToanFrame();
        pnlDatVe.add(dv.getContentPanelFor(pnlContent));
//        pnlDatDoAn.add(tt.getContentPanelFor(pnlContent));
        pnlThanhToan.add(tt.getContentPanelFor(pnlContent));
       ;
        
    }
    
    private void addCards() {
        addCardsAndMap(VE_CARD, dv, pnlDatVe);
//        addCardsAndMap(DOAN_CARD, da, pnlDatDoAn);
        addCardsAndMap(THANHTOAN_CARD, tt, pnlThanhToan);
        
        cards.put(HOME_CARD, this);
        pnlContent.add(HOME_CARD, pnlHome);
        
    }
    
    private void addCardsAndMap(String name, SubFrame f , JPanel child) {
        cards.put(name, f);
        pnlContent.add(name,child);
    }
    
    public DatVeFrame getDatVe() {
        return dv;
    }
    
    public ThanhToanFrame getTTAndDA() {
        return tt;
    }
    
    private void showCard(String name) {
        System.out.println("show card: "+name);
        currentFrame = name;
        l.show(pnlContent, name);
        System.out.println("id edit = "+ dv.isEdit);
        
        if(name.equals(HOME_CARD)) {
            System.out.println("show home");
            isHome = true;
            System.out.println("set edit = ");
            dv.isEdit = false;
//            dv.isNotInThreeDay = false;
            System.out.println("id edit = "+ dv.isEdit);
            dv.veSelectedList.clear();
//            dv.subPanel.render();
            if(tt.isDoAnDatListEmpty()) {
                System.out.println("render lai do an");
                setDonThanhToanAsDoAn();
            }
            System.out.println("finish show home");
            System.out.println("id edit = "+ dv.isEdit);
            System.out.println("---------------------");
            return;
        }else if(name.equals(THANHTOAN_CARD)) {
            if(tt.doAnList != null && tt.doAnList.getMp() != null && !tt.isDoAnDatListEmpty() && ThanhToanFrame.isDoAnOnly) return; 
        }else if(name.equals(VE_CARD)) {
            if(dv.isEdit) {
                isHome = false;
                System.out.println("edit true");
                return;
            }
    }
    
        isHome = false;
        cards.get(name).getSubPanelCreator().render();
        System.out.println("render and show inner");
    }
    
    public void showCard(GuiOf go) {
        String card = go.toString();
        showCard(card);
        
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
    
    public void renderFrame(){
        try {
            ExecutorService service = Executors.newFixedThreadPool(2);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
//                    System.out.println("render frame menudoan");
                    showCard(GuiOf.HOME);
                    return null;
                }
                
            }));
            service.invokeAll(calls);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        

    }
    
    
    public void setDonThanhToanAsVe(Object frame) {
        System.out.println("set don thanh toan as VE");
        if(ThanhToanFrame.isDoAnOnly && tt.isDatThemVe()) {
            System.out.println("is both");
            ThanhToanFrame.isDoAnOnly = false;
            ThanhToanFrame.isBoth = true;
            tt.menuThanhToanOf(ThanhToanFrame.TYPE.BOTH, frame);
            
        }else {
            System.out.println("ve only");
            ThanhToanFrame.isDoAnOnly = false;
            ThanhToanFrame.isBoth = false;
            tt.menuThanhToanOf(ThanhToanFrame.TYPE.VE_ONLY, frame);
            
        }
        showCard(THANHTOAN_CARD);
//        MessageHelper.message(this, "show thanh toan");
    }
    
    public void setDonThanhToanAsDoAn(){
        tt.menuThanhToanOf(ThanhToanFrame.TYPE.DO_AN_ONLY, null);
        
    }
    
    public void setDonThanhToanAsBoth() {
        
    }
    

    public void showDatVeFrameWith(SuatChieu suat) {
        showCard(GuiOf.DAT_VE);
        System.out.println("suat dat = "+ suat);
        dv.setDatVeFor(suat);
    }
    
    public void showVeFrameLongTerm(SuatChieu suat) {
        showCard(GuiOf.DAT_VE);
        System.out.println("suat dat = "+ suat);
        dv.setDatVeForLongTerm(suat);
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
        pnlHome = new com.org.app.customui.GradientPanel("#DAD299", "#B0DAB9", 145);
        btnDatVe2 = new com.org.app.customui.ButtonGradient();
        btnDatDoAn2 = new com.org.app.customui.ButtonGradient();
        pnlDatVe = new javax.swing.JPanel();
        pnlDatDoAn = new javax.swing.JPanel();
        pnlThanhToan = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContent.setBackground(new java.awt.Color(218, 237, 237));
        pnlContent.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlContent.setLayout(new java.awt.CardLayout());

        pnlHome.setBackground(new java.awt.Color(217, 229, 235));
        pnlHome.setLayout(null);

        btnDatVe2.setForeground(new java.awt.Color(51, 51, 51));
        btnDatVe2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ticket_menu_icon.png"))); // NOI18N
        btnDatVe2.setText("Đặt vé");
        btnDatVe2.setColor1(new java.awt.Color(238, 235, 128));
        btnDatVe2.setColor2(new java.awt.Color(240, 226, 180));
        btnDatVe2.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        btnDatVe2.setGradientFocus(145);
        btnDatVe2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDatVe2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDatVe2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatVe2ActionPerformed(evt);
            }
        });
        pnlHome.add(btnDatVe2);
        btnDatVe2.setBounds(30, 190, 300, 230);

        btnDatDoAn2.setForeground(new java.awt.Color(51, 51, 51));
        btnDatDoAn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/food_menu_icon_100.png"))); // NOI18N
        btnDatDoAn2.setText("Đặt đồ ăn");
        btnDatDoAn2.setColor1(new java.awt.Color(147, 220, 219));
        btnDatDoAn2.setColor2(new java.awt.Color(108, 179, 184));
        btnDatDoAn2.setFont(new java.awt.Font("Leelawadee UI", 1, 24)); // NOI18N
        btnDatDoAn2.setGradientFocus(145);
        btnDatDoAn2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDatDoAn2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDatDoAn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatDoAn2ActionPerformed(evt);
            }
        });
        pnlHome.add(btnDatDoAn2);
        btnDatDoAn2.setBounds(370, 190, 300, 230);

        pnlContent.add(pnlHome, "card2");

        pnlDatVe.setMaximumSize(new java.awt.Dimension(700, 650));
        pnlDatVe.setMinimumSize(new java.awt.Dimension(700, 650));
        pnlDatVe.setOpaque(false);
        pnlDatVe.setLayout(new java.awt.BorderLayout());
        pnlContent.add(pnlDatVe, "card3");

        pnlDatDoAn.setLayout(new java.awt.BorderLayout());
        pnlContent.add(pnlDatDoAn, "card4");

        pnlThanhToan.setLayout(new java.awt.BorderLayout());
        pnlContent.add(pnlThanhToan, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlContent, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatVe2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatVe2ActionPerformed

        showCard(GuiOf.DAT_VE);
    }//GEN-LAST:event_btnDatVe2ActionPerformed

    private void btnDatDoAn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatDoAn2ActionPerformed
        showCard(GuiOf.THANHTOAN);
    }//GEN-LAST:event_btnDatDoAn2ActionPerformed

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
            java.util.logging.Logger.getLogger(DatMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatMenuFrame mnf = new DatMenuFrame();
                mnf.renderFrame();
                mnf.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.org.app.customui.ButtonGradient btnDatDoAn2;
    private com.org.app.customui.ButtonGradient btnDatVe2;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlDatDoAn;
    private javax.swing.JPanel pnlDatVe;
    private javax.swing.JPanel pnlHome;
    private javax.swing.JPanel pnlThanhToan;
    // End of variables declaration//GEN-END:variables
}

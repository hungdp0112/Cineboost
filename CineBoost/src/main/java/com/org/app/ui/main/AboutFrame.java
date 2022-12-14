/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.main;

import com.org.app.helper.ImageUtil;
import com.org.app.ui.quanly.QLProfileFrame;
import com.org.app.util.ScaleImageIconGenerator;
import com.org.app.util.SubFrame;
import com.org.app.util.SubPanelCreator;
import com.org.app.util.SubPanelCreatorInterfaces;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class AboutFrame extends javax.swing.JFrame implements SubFrame<QLProfileFrame>, SubPanelCreatorInterfaces<SubFrame> {
    public static final String CARD_NAME_MAIN = "about";
    
    SubPanelCreator<AboutFrame> subPanel;

    /**
     * Creates new form AboutFrame
     */
    public AboutFrame() {
        initComponents();
        subPanel = createSubPanel(this); 
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtText = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtText.setEditable(false);
        txtText.setFont(new java.awt.Font("Corbel", 0, 14)); // NOI18N
        txtText.setText("Trong các rạp chiếu phim hiện nay, việc quản lý và tổ chức chiếu phim, bán vé luôn là một vấn đề được quan tâm. Người quản lý luôn gặp khó khăn trong vấn đề quản lý quá trình hoạt động của từng bộ phân như: phim, lịch chiếu,… đặc biệt là quá trình bán vé. Khách hàng phải xếp hàng chờ đợi để mua được vé. Chính vì lý do đó mà số lượng khách hàng đi xem phim cũng ít hơn, thu nhập của rạp cũng bị ảnh hưởng. vì vậy, tạo ra một phần mềm quản lý rạp chiếu phim là một nhu cầu tất yếu.\nHệ thống quản lý rạp chiếu phim được xây dựng trên những nhu cầu thực tế của khách hàng và nhà quản lý nhằm giải quyết những khó khăn gặp phải, giảm rủi ro trong quá trình quản lý rạp. hệ thống hướng tới đối tượng khách hàng và những nhân viên quản lý rạp.\nCác chức năng chính bao gồm:\n\t- Quản lý phim.\n\t- Quản lý suất chiếu.\n\t- Quản lý phòng chiếu.\n\t- Quản lý vé, đặt vé.\n\t- Quản lý đồ ăn.\n\t- Quản lý hóa đơn.\n\t- Quản lý thông tin khách hàng.\n\t- Quản lý nhân viên.\n\t- Chỉnh sửa thông tin tài khoản cá nhân, đổi mật khẩu\n\t- Thống kê báo cáo.\nCác chức năng này giúp người quản lý dễ dàng điều khiển quá trình hoạt động của rạp và rát thuận tiện đẻ khách hàng có thể mua được vé.\n");
        txtText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTextMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtText);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/poster.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTextMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_txtTextMouseClicked

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
            java.util.logging.Logger.getLogger(AboutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AboutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AboutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AboutFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AboutFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane txtText;
    // End of variables declaration//GEN-END:variables

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

            }
        };
    }
}

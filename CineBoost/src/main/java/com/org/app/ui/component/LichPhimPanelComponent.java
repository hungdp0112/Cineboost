/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.entity.SuatChieu;
import com.org.app.helper.DateHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.util.LabelWrapperInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author intfs
 */
public class LichPhimPanelComponent extends javax.swing.JPanel implements LabelWrapperInterface{
    SuatChieu sc;
    int index;
    
    /**
     * Creates new form LichPhimPanelComponent
     */
    public LichPhimPanelComponent() {
        initComponents();
        sc = new SuatChieu();
//        Dimension d = new Dimension();
//        this.setPreferredSize(d);
//        this.setMaximumSize(d);
//        this.setMinimumSize(d);
    }
    
    public LichPhimPanelComponent(SuatChieu sc, int index) {
        this();
        this.sc = sc;
        this.index = index;
        setLabel();
    }
    
    public void setLabel() {
        wrap(lblPhong,getPhongName(sc.getPhong()));
        wrap(lblGio, String.format("%s (%s)",TimeHelper.toStringWithHourMintues(sc.getGioBatDau()), isSuatDaChieu()? "ĐC" : "SC"));
        this.setToolTipText(isDaChieu()? "Suất đã chiếu" : "<html>Suất sắp chiếu<br>Nhấn 2 lần để đặt vé<html>");
        System.out.println("isDachieu = "+ isSuatDaChieu());
    }
    
    private boolean isSuatDaChieu() {
        java.sql.Date d = sc.getNgayChieu();
        java.sql.Time t = sc.getGioBatDau();
        LocalDateTime suat = LocalDateTime.of(DateHelper.convertToLocalDate(d),TimeHelper.toLocalTime(t));
        return DateHelper.compare(suat, LocalDateTime.now()) <= 0;
    }
    
    public boolean isDaChieu() {
        return isSuatDaChieu();
    }
    private String getPhongName(String idPhong) {
        return "Phòng "+ Integer.parseInt(idPhong.substring(1, idPhong.length()));
    }
    
    public void setSelected() {
        gradientPanel1.setColor1(Color.decode("#E3E3B3"));
        gradientPanel1.setColor2(new Color(236,241,234));
    }
    
    public void setUnselect() {
        gradientPanel1.setColor1(new Color(152,217,204));
        gradientPanel1.setColor2(new Color(236,241,234));
    }

    public SuatChieu getSuatChieu() {
        return sc;
    }
    
    public int getIndex() {
        return index;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gradientPanel1 = new com.org.app.customui.GradientPanel();
        lblPhong = new javax.swing.JLabel();
        lblGio = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(165, 70));
        setPreferredSize(new java.awt.Dimension(165, 76));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        gradientPanel1.setColor1(new java.awt.Color(152, 217, 204));
        gradientPanel1.setColor2(new java.awt.Color(236, 241, 234));
        gradientPanel1.setMaximumSize(new java.awt.Dimension(165, 76));
        gradientPanel1.setPreferredSize(new java.awt.Dimension(165, 76));

        lblPhong.setFont(new java.awt.Font("Leelawadee UI", 1, 14)); // NOI18N
        lblPhong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhong.setText("Phong");
        lblPhong.setMaximumSize(new java.awt.Dimension(165, 25));
        lblPhong.setMinimumSize(new java.awt.Dimension(165, 25));
        lblPhong.setPreferredSize(new java.awt.Dimension(165, 25));

        lblGio.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGio.setForeground(new java.awt.Color(51, 51, 51));
        lblGio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_clock_16px.png"))); // NOI18N
        lblGio.setText("Gio");
        lblGio.setMaximumSize(new java.awt.Dimension(165, 25));
        lblGio.setMinimumSize(new java.awt.Dimension(165, 25));
        lblGio.setPreferredSize(new java.awt.Dimension(165, 25));

        javax.swing.GroupLayout gradientPanel1Layout = new javax.swing.GroupLayout(gradientPanel1);
        gradientPanel1.setLayout(gradientPanel1Layout);
        gradientPanel1Layout.setHorizontalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblGio, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gradientPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        gradientPanel1Layout.setVerticalGroup(
            gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gradientPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblGio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(gradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(gradientPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
        );

        add(gradientPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.org.app.customui.GradientPanel gradientPanel1;
    private javax.swing.JLabel lblGio;
    private javax.swing.JLabel lblPhong;
    // End of variables declaration//GEN-END:variables
}
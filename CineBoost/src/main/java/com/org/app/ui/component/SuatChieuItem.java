/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.entity.Phim;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.DateHelper;
import com.org.app.helper.TimeHelper;
import com.org.app.util.LabelWrapperInterface;
import java.awt.Color;
import java.util.Objects;

/**
 *
 * @author intfs
 */
public class SuatChieuItem extends javax.swing.JPanel implements LabelWrapperInterface{
     private int suatChieuIndex;
    private SuatChieu suatChieu;
    private String idSuat;
    private Phim phim;
    private int luongve = 0;
    /**
     * Creates new form SuatChieuItem
     */
    public SuatChieuItem() {
        initComponents();
    }
    public SuatChieuItem(SuatChieu suatChieu,  Phim phim, int suatChieuIndex) {
        this();        
        this.suatChieuIndex = suatChieuIndex;
        this.suatChieu = suatChieu;
        this.phim = phim;
        this.idSuat = suatChieu.getId();
        setLabels();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        lblGioBatDau.setText(sdf.format(suatChieu.getGioBatDau()));
//        lblGioKetThuc.setText(sdf.format(suatChieu.getGioKetThuc()));
//        lblTenPhim.setText("<html>" + suatChieu.getPhim().getTen() + "</html>");
    }
    
    private void setLabels() {
        lblGioBatDau.setText(TimeHelper.toString(getGioBatDau()));
        lblGioKetThuc.setText(TimeHelper.toString(getGioKetThuc()));
        wrap(lblTenPhim, getPhim().getTen());
        lblTenPhim.setText(getPhim().getTen());
        wrap(lblID, getIdSuat());
        lblNgayChieu.setText(DateHelper.toString(getNgayChieu()));
        lblPhong.setText(getPhong());
        setLabelLuongVe();
    }
    
    private void setLabelLuongVe() {
        lblLuongveban.setText(String.format("Lượng vé bán: %s%d", luongve<10 && luongve > 0 ? 0 : "",luongve));
    }
    
    public void setLuongVe(int luongVe) {
        this.luongve = luongVe;
        setLabelLuongVe();
        
    }
    
    public int getLuongVe() {
        return this.luongve;
    }

    public java.sql.Time getGioBatDau() {
        return suatChieu.getGioBatDau();
    }
    
    public java.sql.Time getGioKetThuc() {
        return suatChieu.getGioKetThuc();
    }
    
    public void setGioBatDau(java.sql.Time gioBd) {
        this.suatChieu.setGioBatDau(gioBd);
    }
    
    public void setGioKetThuc(java.sql.Time gioKt) {
        this.suatChieu.setGioKetThuc(gioKt);
    }
    
    
    public SuatChieu getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(SuatChieu suatChieu) {
        this.suatChieu = suatChieu;
    }

    public String getPhong() {
        return suatChieu.getPhong();
    }
    
    public java.sql.Date getNgayChieu() {
        return suatChieu.getNgayChieu();
    }
    
    public void setNgayChieu(java.sql.Date nc) {
        suatChieu.setNgayChieu(nc);
    }
    
    public String getIdSuatChieu() {
        return suatChieu.getId();
    }

    public void setPhong(String p) {
       suatChieu.setPhong(p);
    }
    public Phim getPhim() {
        return phim;
    }
    public void setPhim(Phim p) {
        this.phim = p;
    }
    public int getSuatChieuIndex() {
        return suatChieuIndex;
    }

    public void setSuatChieuIndex(int suatChieuIndex) {
        this.suatChieuIndex = suatChieuIndex;
    }

    public void setItemSelected(){
        this.setBackground(Color.decode("#D9EDD4"));
        this.setBackground(Color.decode("#EACD68"));
    }
    
    public void setItemUnSelected(){
        this.setBackground(Color.decode("#EAF1E1"));
    }
    
    public int getThoiLuong() {
        return phim.getThoiLuong();
    }

    public String getIdSuat() {
        return idSuat;
    }

    public void setIdSuat(String idSuat) {
        this.idSuat = idSuat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.suatChieu);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SuatChieuItem other = (SuatChieuItem) obj;
        if (!Objects.equals(this.suatChieu, other.suatChieu)) {
            return false;
        }
        return true;
    }

    
    
   
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        lblGioBatDau = new javax.swing.JLabel();
        lblGioKetThuc = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblTenPhim = new javax.swing.JLabel();
        lblNgayChieu = new javax.swing.JLabel();
        lblPhong = new javax.swing.JLabel();
        lblLuongveban = new javax.swing.JLabel();

        setBackground(new java.awt.Color(234, 241, 225));
        setMaximumSize(new java.awt.Dimension(270, 112));
        setPreferredSize(new java.awt.Dimension(282, 112));

        jPanel2.setMaximumSize(new java.awt.Dimension(87, 112));
        jPanel2.setMinimumSize(new java.awt.Dimension(87, 112));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(87, 112));

        jPanel1.setBackground(new java.awt.Color(153, 204, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(28, 12));
        jPanel1.setMinimumSize(new java.awt.Dimension(28, 12));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(28, 12));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(125, 124, 54));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 6, 0, 0, new java.awt.Color(102, 0, 0)));
        jSeparator1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jSeparator1.setMaximumSize(new java.awt.Dimension(50, 12));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 12));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 2;
        gridBagConstraints.weightx = 4.0;
        gridBagConstraints.weighty = 4.0;
        jPanel1.add(jSeparator1, gridBagConstraints);

        lblGioBatDau.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGioBatDau.setForeground(new java.awt.Color(204, 102, 0));
        lblGioBatDau.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGioBatDau.setText("00:00:00");
        lblGioBatDau.setMaximumSize(new java.awt.Dimension(67, 16));
        lblGioBatDau.setMinimumSize(new java.awt.Dimension(67, 16));
        lblGioBatDau.setPreferredSize(new java.awt.Dimension(67, 16));

        lblGioKetThuc.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblGioKetThuc.setForeground(new java.awt.Color(204, 102, 0));
        lblGioKetThuc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGioKetThuc.setText("00:00:00");
        lblGioKetThuc.setMaximumSize(new java.awt.Dimension(67, 16));
        lblGioKetThuc.setMinimumSize(new java.awt.Dimension(67, 16));
        lblGioKetThuc.setPreferredSize(new java.awt.Dimension(67, 16));

        lblID.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblID.setForeground(new java.awt.Color(153, 153, 153));
        lblID.setMaximumSize(new java.awt.Dimension(65, 11));
        lblID.setPreferredSize(new java.awt.Dimension(65, 11));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGioBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblGioKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(lblGioBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGioKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel3.setMaximumSize(new java.awt.Dimension(189, 112));
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(189, 112));

        lblTenPhim.setFont(new java.awt.Font("Leelawadee UI", 1, 12)); // NOI18N
        lblTenPhim.setForeground(new java.awt.Color(95, 96, 57));
        lblTenPhim.setText("das");
        lblTenPhim.setMaximumSize(new java.awt.Dimension(169, 31));
        lblTenPhim.setMinimumSize(new java.awt.Dimension(169, 31));
        lblTenPhim.setPreferredSize(new java.awt.Dimension(169, 26));

        lblNgayChieu.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        lblNgayChieu.setForeground(new java.awt.Color(102, 102, 102));
        lblNgayChieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_calendar_6_20px.png"))); // NOI18N
        lblNgayChieu.setText("dd/MM/yyyy");
        lblNgayChieu.setMaximumSize(new java.awt.Dimension(102, 26));
        lblNgayChieu.setMinimumSize(new java.awt.Dimension(102, 26));
        lblNgayChieu.setPreferredSize(new java.awt.Dimension(102, 26));

        lblPhong.setFont(new java.awt.Font("Leelawadee UI", 1, 11)); // NOI18N
        lblPhong.setForeground(new java.awt.Color(102, 102, 102));
        lblPhong.setText("P01");
        lblPhong.setMaximumSize(new java.awt.Dimension(38, 26));
        lblPhong.setMinimumSize(new java.awt.Dimension(38, 26));
        lblPhong.setPreferredSize(new java.awt.Dimension(38, 26));

        lblLuongveban.setFont(new java.awt.Font("Leelawadee UI", 1, 10)); // NOI18N
        lblLuongveban.setForeground(new java.awt.Color(153, 51, 0));
        lblLuongveban.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_ticket_16px_1_1.png"))); // NOI18N
        lblLuongveban.setText("Lượng vé bán: 40");
        lblLuongveban.setToolTipText("Lượng vé đã bán");
        lblLuongveban.setMaximumSize(new java.awt.Dimension(138, 16));
        lblLuongveban.setMinimumSize(new java.awt.Dimension(138, 16));
        lblLuongveban.setPreferredSize(new java.awt.Dimension(138, 16));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblLuongveban, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTenPhim, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayChieu, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLuongveban, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblGioBatDau;
    private javax.swing.JLabel lblGioKetThuc;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLuongveban;
    private javax.swing.JLabel lblNgayChieu;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblTenPhim;
    // End of variables declaration//GEN-END:variables
}
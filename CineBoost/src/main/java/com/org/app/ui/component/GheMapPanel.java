/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.controller.GheDao;
import com.org.app.entity.Ghe;
import com.org.app.entity.PhongChieu;
import com.org.app.entity.SuatChieu;
import com.org.app.ui.banhang.DatVeFrame;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author intfs
 */
public class GheMapPanel extends javax.swing.JPanel {

    PhongChieu pc;
    SuatChieu sc;
    DatVeFrame veFrame;
    LinkedHashMap<String, Ghe> gheMap;
    List<Ghe> gheDat;
    int limit = 0;
    int limitVeNL = 0;
    int limitVeTE = 0;
    public VeDatList selectedList;
    GheDao gheDao;

    /**
     * Creates new form GheMapPanel
     */

    public GheMapPanel(DatVeFrame dvf, int width, int height) {
        this();
        Dimension expectedDimension = new Dimension(width, height);
        this.setPreferredSize(expectedDimension);
        this.setMaximumSize(expectedDimension);
        this.setMinimumSize(expectedDimension);
        veFrame = dvf;
        this.selectedList = veFrame.veSelectedList;
    }

    public GheMapPanel() {
        initComponents();
        pc = new PhongChieu();
        sc = new SuatChieu();
        gheMap = new LinkedHashMap<>();
        gheDat = new ArrayList<>();
        gheDao = new GheDao();
        selectedList = new VeDatList();
        System.out.println("where setupghe");
    }

    public void setMapOf(SuatChieu sc, PhongChieu pc) {
        System.out.println("inside gheMapPanel");
        this.sc = sc;
        this.pc = pc;
        gheMap.clear();
        selectedList.clear();
        render();
    }

    public int getLimit() {
        return veFrame.getLuongVe();
    }

    public boolean isFull() {
        return getSelectedGhe().size() == getLimit();
    }

    public void showSelectedGheList() {
//        selectedList.put(ghe);
        System.out.println("selectes list = " + selectedList);
        veFrame.showListGheChon();
    }

    public void unselectedGhe(GheLabel ghe) {
//        selectedList.remove(ghe);
        System.out.println("selectes list = " + selectedList);
        veFrame.showListGheChon();
    }

    public List<Ghe> getSelectedGhe() {
        List<Ghe> list = new ArrayList<>();
        return list;
    }

    public void loadPhongMap() throws Exception {
        System.out.println("id phong = " + pc.getId());
        Map<String, Ghe> map = gheDao.getMapGheOfPhong(pc.getId());
        gheMap.putAll(map);
        gheDat = gheDao.selectGheDaDat(sc.getId());

//        System.out.println("gheDat - " + gheDat.size());
    }

    public void setUpGhe(int cols, int rows) {
        this.removeAll();
        int i = 0;
        GridLayout layout = new GridLayout(rows + 1, cols + 1);
        this.setLayout(layout);

        for (int j = 0; j < cols + 1; j++) {
            JLabel lbl = new JLabel("  " + (j));
            lbl.setVerticalAlignment(SwingConstants.CENTER);
            if (j == 0) {
                lbl.setText("");
            }
            this.add(lbl);
        }
        int row = 0;
        Ghe[] ghes = gheMap.values().toArray(Ghe[]::new);
        for (int j = 0; j < ghes.length; j++) {
            if (j % (cols) == 0) {
                String text = "" + (char) ((int) 'A' + row);
                JLabel l = new JLabel(text, SwingConstants.CENTER);
                l.setVerticalAlignment(SwingConstants.TOP);
                this.add(l);
                row++;
            }

            GheLabel lblg = new GheLabel(ghes[j], this);
            setBookedGhe(ghes[j], lblg);
            lblg.getLbl().setVerticalAlignment(SwingConstants.TOP);
            this.add(lblg.getLbl());
        }
        System.out.println("DONE SET GHE\n" + "gheLimit default = " + selectedList.geListtLimit());
    }

    
    public void render() {
        try {
        ExecutorService serverice = Executors.newFixedThreadPool(4);
            List<Callable<Object>> calls = new ArrayList<>();
            calls.add((new Callable<Object>(){
                @Override
                public Object call() throws Exception {
                    loadPhongMap();
                    setUpGhe(pc.getSoLuongCot(), pc.getSoLuongDay());
                    return null;
                }
            }));
            serverice.invokeAll(calls);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    int i = 0;
    private void setBookedGhe(Ghe g, GheLabel glbl) {
//        System.out.println("set Booked ghe");
        if(!gheDat.isEmpty()) {
        for(Ghe gd : gheDat) {
            if(gd.getId().equals(g.getId())) {
//                System.out.println("ghe booked set "+ i);
                glbl.setIsBooked(true);
                
            }
        }
        }
    }
    
    public JPanel getPanel() {return this;}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

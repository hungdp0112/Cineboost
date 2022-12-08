/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.entity.Ghe;
import com.org.app.util.ScaleImageIconGenerator;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author intfs
 */
 public class GheLabel{
    private JLabel lbl;
    private Ghe ghe;
    private String pic;
    private boolean isBooked;
    private boolean isSelected;
    private GheMapPanel panel;
    private boolean loaiVe;
    
    public static boolean ADULT = true;
    public static boolean CHILDREN = false;
    //#FFEFB46D hover - selected #FFECE46C - availiable #FFA1EC6C
    enum IconGhe {
        AVAILABLE("seat_available.png"),
        UNAVAILABLE("seat_unavailable.png"),
        AVAILABLE_VIP("seat_vp_available.png"),
        UNAVAILABLE_VIP("seat_vp_unavailable.png"),
        AVAILABLE_SELECTED("seat_selected.png"),
        AVAILABLE_SELECTED_VIP("seat_vp_selected.png"),
        HOVER("seat_hover.png"),
        HOVER_VIP("seat_vp_hover.png");

        private String iconName;
        IconGhe(String iconName) {
            this.iconName = iconName;
        }
        
        public String toString() {
            return iconName;
        }
        
        
    }
    public GheLabel() {
        lbl = new JLabel();
        pic = "";
        lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    public GheLabel(Ghe ghe, GheMapPanel panel) {
        this();
        this.ghe = ghe;
        this.isBooked = false;
        this.isSelected = false;
        this.panel = panel;
        this.loaiVe = ADULT;
        setIconGhe();
        updateIcon();
        setToolTip();
        setMouseListener();
    }

    public boolean getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(boolean type) {
        this.loaiVe = type;
    }
    
    
    
    public void setPanel(GheMapPanel p) {
        panel = p;
    }
    private void setToolTip() {
        lbl.setToolTipText(ghe.getId());
    }
   
    private void updateIcon() {
        ImageIcon i = ScaleImageIconGenerator.getImageFromResources(pic);
        lbl.setIcon(i);
    }
    
    private void setIconGhe() {
        if(ghe.isVip()) {
            setIconVP();
        }else setIconTH();
    }
    
    private void setIconVP() {
        if(isBooked) setPic(IconGhe.UNAVAILABLE_VIP.toString());
        else if(isSelected) setPic(IconGhe.AVAILABLE_SELECTED_VIP.toString());
        else setPic(IconGhe.AVAILABLE_VIP.toString());
    }
    
    private void setIconTH() {
        if(isBooked) setPic(IconGhe.UNAVAILABLE.toString());
        else if(isSelected) setPic(IconGhe.AVAILABLE_SELECTED.toString());
        else setPic(IconGhe.AVAILABLE.toString());
    }
    
    private void setIconMouseEnter() {
        if(isSelected || isBooked) return;
        if(ghe.isVip()) setPic(IconGhe.HOVER_VIP.toString());
        else setPic(IconGhe.HOVER.toString());
    }

    public JLabel getLbl() {
        return lbl;
    }

    public void setLbl(JLabel lbl) {
        this.lbl = lbl;
    }

    public Ghe getGhe() {
        return ghe;
    }

    public void setGhe(Ghe ghe) {
        this.ghe = ghe;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
        setIconGhe();
        updateIcon();
        
    }
    
    

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
        System.out.println("this ghe is selected "+ isSelected);
//        if(panel.isFull())
//        if(isSelected) this.panel.addSelectedGhe(this);
//        else this.panel.unselectedGhe(this);
        setIconGhe();
        updateIcon();
        this.panel.showSelectedGheList();
    }
    
    
    
    private JLabel getLabel() {
        return lbl;
    }
    
    private void setMouseListener() {
        lbl.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                setMouseClicked();
                updateIcon();
            }

            public void mouseEntered(MouseEvent e) {
                setIconMouseEnter();
                updateIcon();
            }

            public void mouseExited(MouseEvent e) {
                if(isSelected) return;
                setIconGhe();
                updateIcon();
            }
        });
    }
    
    private void setMouseClicked() {
        if(isBooked) return;
        if(!isSelected) 
            panel.selectedList.put(this);
        else panel.selectedList.remove(this);
        
        panel.veFrame.updateDetailPanelLabels();
            //-----let see
//            setIsSelected(!isSelected);
//            System.out.println("change to selected");
//
//            setIconGhe();
    }

    @Override
    public String toString() {
        return "GheLabel{" + "ghe=" + ghe +  "loaiVe: " + toStringLoaiVe() + '}';
    }
    
    public String toStringLoaiVe() {
        return loaiVe? "NL" : "TE";
    }
    
    public static void main(String[] args) {
       ImageIcon d = ScaleImageIconGenerator.getImageFromResources("seat_available.png");
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final GheLabel other = (GheLabel) obj;
        if (!Objects.equals(this.ghe, other.ghe)) {
            return false;
        }
        return true;
    }
  
    
}

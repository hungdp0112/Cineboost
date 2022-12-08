/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.util.ColorAndIconBank.Icon;
import static com.org.app.util.MouseHoverEffect.setBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author intfs
 */
public class SideMenuButton  extends MouseHoverEffect{
    JLabel lbl;
    JComponent binding;
    boolean isSelected;
    boolean isEnable;
    
    javax.swing.Icon[] icons = new javax.swing.Icon[2];
    
    
    public SideMenuButton(JLabel lbl, JComponent binding) {
       this.lbl = lbl;
       this.binding = binding;
       isSelected = false;       
    }
    
    public SideMenuButton(JLabel lbl, JComponent binding,Color c,boolean border) {
       this(lbl,binding);
       mouseOverEffect(c,border);
    }
    
    public void setRollOverIcon(ImageIcon rollIcon) {
        icons[0] = lbl.getIcon();
        icons[1] = ((javax.swing.Icon) rollIcon);
    }
    
    
    
    public JLabel getButton() {
        return lbl;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean selected) {
        
        this.isSelected = selected;
        if(!this.isSelected || !this.lbl.isEnabled()) {
            clearState();
        }else {
                binding.setBackground(ColorAndIconBank.SIDEMENU_COLOR_SELECTED);
                binding.repaint();
                binding.validate();
                binding.setOpaque(true);
                if(icons[0] != null)lbl.setIcon(icons[1]);
                setBorder(true, binding);

                lbl.repaint();
                lbl.validate();
                lbl.setForeground(ColorAndIconBank.SIDEMENU_TEXT_SELECTED);
        } 
        
    }
    public void clearState() {
    binding.repaint();
    binding.validate();
    binding.setOpaque(false);
     setBorder(false, binding);
     if(icons[0] != null) lbl.setIcon(icons[0]);
    lbl.validate();
    lbl.repaint();
    lbl.setForeground(ColorAndIconBank.SIDEMENU_TEXT_DEFAULT);
    }
   
    
   
   
     public void mouseOverEffect(Color c, boolean needLeftBorder) {
        binding.repaint();
        binding.validate();
        lbl.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(isSelected) return;
                binding.setBackground(c);
                binding.repaint();
                binding.validate();
                binding.setOpaque(true);
                if(needLeftBorder) setBorder(true, binding);

                lbl.setForeground(ColorAndIconBank.SIDEMENU_TEXT_SELECTED);
                lbl.setIcon(icons[1]);
                lbl.validate();
                lbl.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if(isSelected) return;
                binding.repaint();
                binding.validate();
                
                binding.setOpaque(false);
                binding.setBackground(new Color(0,0,0,0));
                if(needLeftBorder) setBorder(false, binding);
                
//                lbl.validate();
//                lbl.repaint();
                lbl.setIcon(icons[0]);
                lbl.setForeground(ColorAndIconBank.SIDEMENU_TEXT_DEFAULT);
                lbl.validate();
                lbl.repaint();
            }
        }
        );
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.lbl);
        hash = 97 * hash + Objects.hashCode(this.binding);
        hash = 97 * hash + (this.isSelected ? 1 : 0);
        hash = 97 * hash + Arrays.deepHashCode(this.icons);
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
        final SideMenuButton other = (SideMenuButton) obj;
        if (this.isSelected != other.isSelected) {
            return false;
        }
        if (!Objects.equals(this.lbl, other.lbl)) {
            return false;
        }
        if (!Objects.equals(this.binding, other.binding)) {
            return false;
        }
        if (!Arrays.deepEquals(this.icons, other.icons)) {
            return false;
        }
        return true;
    }
     
     
    
    
}

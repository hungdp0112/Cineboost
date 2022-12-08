/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.customui;

import com.org.app.util.ColorAndIconBank.Icon;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author intfs
 */


public class CustomeButton extends JButton{
    
    
    public Color hoverBackground = Color.LIGHT_GRAY;
    
    private JButton getBtn() {return this;}
        public  void buttonMouseOverBoldEffect() {
        javax.swing.Icon orIcon = this.getIcon();
        if(orIcon == null || this.getRolloverIcon() == null) return;
        
        Color or = this.getBackground();
        setRolloverIcon(this, orIcon);
        
        this.addMouseListener(
            new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                getBtn().setBackground(hoverBackground);
//                boldText(true, getBtn());
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                getBtn().setBackground(or);
//                boldText(false, getBtn());
            }
        }
        );
    }
        
    private static void setRolloverIcon(JButton btn, javax.swing.Icon icon) {
        if(icon != null)  btn.setRolloverIcon(icon);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.helper.SettingIconHelper;
import com.org.app.util.ColorAndIconBank.Icon;
import static com.org.app.util.MouseHoverEffect.boldText;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author intfs
 */
public class HoverLabel extends JLabel{
    
    private Icon hoverIcon;
    private Icon orgIcon;
   
    JLabel lbl;
    
    
    public HoverLabel() {
        super();
    }
    
    private JLabel getLabel() {
        return this;
    }
    private void hover() {
        this.addMouseListener(new MouseAdapter() {
        
            @Override
            public void mouseEntered(MouseEvent e) {
                SettingIconHelper.setIconFor(getLabel(), hoverIcon.getIcon());
            }

            @Override
            public void mouseExited(MouseEvent e) {
               SettingIconHelper.setIconFor(getLabel(), orgIcon.getIcon());
            }
        });
    }
    
}

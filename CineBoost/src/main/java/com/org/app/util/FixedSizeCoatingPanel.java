/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

/**
 *
 * @author intfs
 */
public class FixedSizeCoatingPanel {
    
    public static JPanel getCoatingJPanel(JPanel panel) {
        JPanel p = new JPanel(new GridBagLayout());
        p.setOpaque(false);
//        p.setBorder(new MatteBorder(1,1,1,1,Color.red));
        p.add(panel);
        return p;
    } 
    
}

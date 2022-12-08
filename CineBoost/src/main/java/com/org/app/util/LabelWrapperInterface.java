/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Color;
import javax.swing.JLabel;

/**
 *
 * @author intfs
 */
public interface LabelWrapperInterface {
    default public void wrap(JLabel lbl, String text) {
        JLabelWrapper.wrap(lbl, text);
    }
    default public void setForeground(Color color,JLabel lbl) {
        lbl.setForeground(color);
    }
    
    default public void setForeground(Color color, JLabel...lbls) {
        for(JLabel lbl : lbls)
            setForeground(color, lbl);
    }
    
}

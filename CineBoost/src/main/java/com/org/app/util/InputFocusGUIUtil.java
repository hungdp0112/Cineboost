/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author intfs
 */
public class InputFocusGUIUtil {
    
    static Color lblO;
    
    public static void setFocusEffect(Color focusColor, Color unFocusColor, JTextField[] texts, JLabel...lbl) {
        for (int i = 0; i < texts.length; i++) {
            setFocusEffect(focusColor, unFocusColor, texts[i], lbl[i]);
            
        }
    }
    
    public static void setFocusEffect(Color focusColor, Color unFocusColor, JTextField[] texts, Color focusLabelColor ,JLabel...lbl) {
        for (int i = 0; i < texts.length; i++) {
            setFocusEffect(focusColor, unFocusColor, texts[i], lbl[i],focusLabelColor);
            
        }
    }
    
    
    public static void setFocusEffect(Color focusColor, Color unFocusColor, JTextField textField, JLabel lbl) {
        lblO = lbl == null? null : lbl.getForeground();
        textField.addFocusListener(new FocusAdapter() {
            @Override
             public void focusGained(FocusEvent e) {
                textField.setBackground(focusColor);
                setForegroundLabel(Color.BLACK, lbl);
                
             }

            @Override
             public void focusLost(FocusEvent e) {
                textField.setBackground(unFocusColor);
                setForegroundLabel(lblO, lbl);
             }
    
        });
        
    }
    
        public static void setFocusEffect(Color focusColor, Color unFocusColor, JTextField textField, JLabel lbl, Color lblForFocus) {
        lblO = lbl == null? null : lbl.getForeground();
        textField.addFocusListener(new FocusAdapter() {
            @Override
             public void focusGained(FocusEvent e) {
                textField.setBackground(focusColor);
                setForegroundLabel(lblForFocus, lbl);
                
             }

            @Override
             public void focusLost(FocusEvent e) {
                textField.setBackground(unFocusColor);
                setForegroundLabel(lblO, lbl);
             }
    
        });
        
    }
      
    public static void setForegroundLabel( Color or, JLabel lbl) {
            if(lbl == null) return;
            lbl.setForeground(or);
        
        
    }
    
}

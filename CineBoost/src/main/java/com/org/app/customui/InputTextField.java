/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.customui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.text.JTextComponent;

/**
 *
 * @author intfs
 */
public class InputTextField extends JTextField{
    Color focus = Color.decode("#d6d6d6");
    Color origin = Color.decode("#e4ede7");
    Color underlineColor = Color.decode("#4f4f4f");
    Color lblForFocus = Color.decode("#4a4f9f");
    int lineThick = 3;
    MatteBorder borderO = new MatteBorder(0,0,lineThick,0,underlineColor);
    JLabel lblFor = null;

    public int getLineThick() {
        return lineThick;
    }

    public void setLineThick(int lineThick) {
        this.lineThick = lineThick;
        setBorderO(new MatteBorder(0,0,this.lineThick,0, underlineColor));
    }
    
    public Color getLblForFocus() {
        return lblForFocus;
    }

    public void setLblForFocus(Color lblForFocus) {
        this.lblForFocus = lblForFocus;
        setFocusEffect(this);
    }
    

    public Color getFocus() {
        return focus;
    }

    public void setFocus(Color focus) {
        this.focus = focus;
        setFocusEffect(this);
    }

    public Color getOrigin() {
        return origin;
    }

    public MatteBorder getBorderO() {
        return borderO;
    }

    public void setBorderO(MatteBorder border) {
        this.borderO = border;
        this.setBorder(borderO);
    }

    
    public void setOrigin(Color origin) {
        this.origin = origin;
        this.setBackground(getOrigin());
    }

    public Color getUnderlineColor() {
        return underlineColor;
    }

    public void setUnderlineColor(Color underlineColor) {
        this.underlineColor = underlineColor;
        setBorderO(new MatteBorder(0,0,lineThick,0,this.underlineColor));
    }

    public JLabel getLblFor() {
        return lblFor;
    }

    public void setLblFor(JLabel lblFor) {
        this.lblFor = lblFor;
        setFocusEffect(this);
    }

    public InputTextField() {
        super();
        this.setPreferredSize(new Dimension(180,25));
        this.setBackground(origin);
        this.setBorder(borderO);
        setFocusEffect(this);

    }
    private Color getOrForegoundOfLabelFor() {
        if(lblFor == null) return null; 
        return lblFor.getForeground();
    }
            
    public void setFocusEffect(JTextField textField) {
        
        textField.addFocusListener(new FocusAdapter() {
            @Override
             public void focusGained(FocusEvent e) {
                textField.setBackground(getFocus());
                setForegroundLabel(getLblForFocus(), getLblFor());
                
             }

            @Override
             public void focusLost(FocusEvent e) {
                textField.setBackground(getOrigin());
                setForegroundLabel(getOrForegoundOfLabelFor(), getLblFor());
             }
    
        });
    }
     public static void setForegroundLabel( Color or, JLabel lbl) {
         if(lbl == null) return;   
         lbl.setForeground(or);
        
        
    }
    
}

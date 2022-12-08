/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.formdev.flatlaf.ui.FlatBorder;
import com.formdev.flatlaf.ui.FlatLineBorder;
import com.org.app.util.ColorAndIconBank.Icon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicBorders;

/**
 *
 * @author intfs
 */
public class MouseHoverEffect {

    static Color or;

    public static void buttonMouseOverBoldEffect(Color c, JButton btn) {
        
        Color or = btn.getBackground();
        btn.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(c);
                boldText(true, btn);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(or);
                boldText(false, btn);
            }
        }
        );
    }
    
    public static void buttonMouseOverBoldEffect(Color c, JButton btn, ImageIcon orIcon, ImageIcon rolloverIcon) {
        Color or = btn.getBackground();
        if(orIcon != null) btn.setIcon(orIcon);
        setRolloverIcon(btn, orIcon);
        btn.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setBackground(c);
                boldText(true, btn);
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn.setBackground(or);
                boldText(false, btn);
            }
        }
        );
    }
    
    public static void buttonMouseOverBoldEffect(Color c, JButton btn, ImageIcon rolloverIcon) {
        buttonMouseOverBoldEffect(c, btn, null, rolloverIcon);
    }
    
    
    
    private static void setRolloverIcon(JButton btn, javax.swing.Icon icon) {
        if(icon != null)  btn.setRolloverIcon(icon);
    }

    public static void boldText(boolean bold, JButton btn) {
        Font f = btn.getFont();
        if (bold) {
            btn.setFont(new Font(f.getName(), Font.BOLD, f.getSize()));
        } else {
            btn.setFont(new Font(f.getName(), Font.PLAIN, f.getSize()));
        }
    }

    public static void buttonMouseOverEffect(Color c, JButton btn, boolean textOnly) {
        or = btn.getBackground();
        if (textOnly) {
            or = btn.getForeground();
            btn.addMouseListener(
                    new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setForeground(c);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setForeground(or);
                }
            }
            );
        } else {
            mouseOverEffect(c, btn);
        }

    }
    
     public static void lblMouseOverEffect(Color c, JLabel btn, boolean textOnly) {
        or = btn.getBackground();
        if (textOnly) {
            or = btn.getForeground();
            btn.addMouseListener(
                    new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setForeground(c);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setForeground(or);
                }
            }
            );
        } else {
            mouseOverEffect(c, btn);
        }

    }
    
    public static void mouseOverEffectButton(Color c, JButton... lbls) {
        System.out.println("run mouse oveer button set up");
        for (int i = 0; i < lbls.length; i++) {
            buttonMouseOverBoldEffect(c, lbls[i]);
        }
    }

    public static void mouseOverEffect(Color c, JComponent lbl) {
        Color or = lbl.getBackground();
        lbl.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lbl.setBackground(c);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                lbl.setBackground(or);
            }

        }
        );
    }

    public static void mouseOverEffect(Color c, JComponent... lbls) {
        for (int i = 0; i < lbls.length; i++) {
            mouseOverEffect(c, lbls[i]);
        }
    }

    public static void mouseOverEffect(Color c, JComponent[] parents, JComponent[] children, boolean needLeftBorder) {
        if (parents.length != children.length) {
            throw new RuntimeException("Arrays of Component not matched");
        } else {
            for (int i = 0; i < parents.length; i++) {
                mouseOverEffect(c, parents[i], children[i],needLeftBorder);

            }
        }
    }

    
    public static void mouseOverEffect(Color c, JComponent parent, JComponent child, boolean needLeftBorder) {
        parent.repaint();
        parent.validate();
        child.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                parent.setBackground(c);
                parent.repaint();
                parent.validate();
                parent.setOpaque(true);
                if(needLeftBorder) setBorder(true, parent);

                child.setForeground(ColorAndIconBank.SIDEMENU_TEXT_SELECTED);
                child.validate();
                child.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                parent.repaint();
                parent.validate();
                parent.setOpaque(false);
                if(needLeftBorder) setBorder(false, parent);
                
                child.validate();
                child.repaint();
                child.setForeground(ColorAndIconBank.SIDEMENU_TEXT_DEFAULT);
            }

        }
        );
    }
    
    public static void mouseOverEffect_DA(Color c, JComponent parent, JComponent child) {
        parent.repaint();
        parent.validate();
        child.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                parent.setBackground(c);
                parent.repaint();
                parent.validate();
                setBorderRU(true, parent);
                child.validate();
                child.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                parent.setBackground(Color.decode("#F2F2F2"));
                parent.repaint();
                parent.validate();
                setBorderRU(false, parent);
                               
                child.validate();
                child.repaint();
            }

        }
        );
    }
    
    
      
    public static void setBorder(boolean border, JComponent c){
        if(border)
        c.setBorder(new MatteBorder(new Insets(0,5,0,0),ColorAndIconBank.SIDEMENU_TEXT_BORDER));
        else c.setBorder(null);
        
    }
    
    public static void setBorderRU(boolean border, JComponent c){
        if(border)
        c.setBorder(new MatteBorder(new Insets(0,0,1,1), Color.BLACK));
        else c.setBorder(null);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.customui;

import java.awt.Color;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author intfs
 */
public abstract class GradientComponent extends JButton {
    
//    public Color startColor = Color.decode("#488FB1");
//    public Color endColor = Color.decode("#4FD3C4");
//    public boolean transparentControls = true;
//    public int gradientFocus = 200;
//
//    public Color getStartColor() {
//        return startColor;
//    }
//
//    public void setkStartColor(Color startColor) {
//        this.startColor = startColor;
//    }
//
//    public Color getEndColor() {
//        return endColor;
//    }
//
//    public void setendColor(Color endColor) {
//        this.endColor = endColor;
//    }
//
//    public boolean isTransparentControls() {
//        return transparentControls;
//    }
//
//    public void settransparentControls(boolean transparentControls) {
//        this.transparentControls = transparentControls;
//    }
//
//    public int getGradientFocus() {
//        return gradientFocus;
//    }
//
//    public void setkGradientFocus(int kGradientFocus) {
//        this.gradientFocus = kGradientFocus;
//    }
//  
// 
//    
//    
//
//    public GradientComponent() {
//
//        if (transparentControls) {
//            setBg(true);
//        } else {
//            setBg(false);
//        }
//
//    }
//    
//    public GradientComponent(String startColor, String focusColor, int range) {
//        setkStartColor(Color.decode(startColor));
//        setendColor(Color.decode(focusColor));
//        setkGradientFocus(range);
//    }
//
//    @Override
//    public synchronized void addMouseMotionListener(MouseMotionListener l) {
//        super.addMouseMotionListener(l); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        int w = getWidth();
//        int h = getHeight();
//
//        GradientPaint gp = new GradientPaint(0, 0, kStartColor, kGradientFocus, h, endColor);;
//
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
//        //g2d.dispose();
//    }
//
//    private void setBg(boolean isOpaque) {
//        Component[] components = this.getComponents();
//        for (Component component : components) {
//
//            ((JLabel) component).setOpaque(isOpaque);
//            ((JCheckBox) component).setOpaque(isOpaque);
//            ((JTextField) component).setOpaque(isOpaque);
//            ((JPasswordField) component).setOpaque(isOpaque);
//            ((JFormattedTextField) component).setOpaque(isOpaque);
//            ((JToolBar) component).setOpaque(isOpaque);
//            ((JRadioButton) component).setOpaque(isOpaque);
//
//        }
//    }
    
}

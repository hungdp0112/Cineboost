/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.customui;

/**
 *
 * @author DELL
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;


/**
 *
 * @author oXCToo
 */
public class RoundedGradientPanel extends JPanel {

    public Color color1 = Color.decode("#488FB1");
    public Color color2 = Color.decode("#4FD3C4");
    public boolean transparentControls = true;
    public int gradientFocus = 200;
    public float borderRadius = 0.4f;

    
    public float getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius; repaint();
    }

    
    
    public Color getColor1() {
        return color1;
    }

    public void setColor1(Color kStartColor) {
        this.color1 = kStartColor;
    }

    public Color getColor2() {
        return color2;
    }

    public void setColor2(Color kEndColor) {
        this.color2 = kEndColor;
    }

    public boolean isTransparentControls() {
        return transparentControls;
    }

    public void setkTransparentControls(boolean kTransparentControls) {
        this.transparentControls = kTransparentControls;
    }

    public int getGradientFocus() {
        return gradientFocus;
    }

    public void setGradientFocus(int kGradientFocus) {
        this.gradientFocus = kGradientFocus;
    }
  
 
    
    

    public RoundedGradientPanel() {
        
        if (transparentControls) {
            setBg(true);
        } else {
            setBg(false);
        }
        
    }
    
  
    
    public RoundedGradientPanel(String startColor, String focusColor, int range) {
        setColor1(Color.decode(startColor));
        setColor2(Color.decode(focusColor));
        setGradientFocus(range);
        
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener l) {
        super.addMouseMotionListener(l); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void paintComponent(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        

        GradientPaint gp = new GradientPaint(0, 0, color1, gradientFocus, h, color2);
        g2d.setPaint(gp);
        
         ((JPanel)this).setBorder(null);
         g2d.fillRoundRect(0, 0, w, h,(int) (h*getBorderRadius()),(int) (h*getBorderRadius()));
            
        
    }

    private void setBg(boolean isOpaque) {
        Component[] components = this.getComponents();
        for (Component component : components) {

            ((JLabel) component).setOpaque(isOpaque);
            ((JCheckBox) component).setOpaque(isOpaque);
            ((JTextField) component).setOpaque(isOpaque);
            ((JPasswordField) component).setOpaque(isOpaque);
            ((JFormattedTextField) component).setOpaque(isOpaque);
            ((JToolBar) component).setOpaque(isOpaque);
            ((JRadioButton) component).setOpaque(isOpaque);

        }
    }

}

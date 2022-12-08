/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author intfs
 */
public abstract class SubPanelCreator<E extends SubFrame> {
    
    E frame;
    JPanel contentPanel;
    
    

    public JPanel createPanelFor(JComponent comp) {
        resize(comp.getWidth(), comp.getHeight());
        return contentPanel;
    }

    public SubPanelCreator (E frame) {
        this.frame = frame;
        contentPanel = getContentPanel();
    }
    
    public JPanel getContentPanel() {
        
        return (JPanel) frame.getContentPane();
    }
    
    public void resize(int width, int height) {
        contentPanel.setMaximumSize(new Dimension(width,height));
        contentPanel.setMinimumSize(new Dimension(width,height));
        contentPanel.setPreferredSize(new Dimension(width,height));
    }
    public abstract void render();
    
    public SubPanelCreator getSubPanel() {return this;};
}

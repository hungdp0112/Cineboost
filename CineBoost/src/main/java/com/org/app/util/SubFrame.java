/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Container;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author intfs
 */
public interface SubFrame<F extends JFrame> extends SubPanelCreatorInterfaces<SubFrame>{
    
    default public Container getContentPane() {
        return ((JFrame)this).getContentPane();
    }
    
    public javax.swing.JPanel getContentPanelFor(JComponent panel);
    
    public SubPanelCreator getSubPanelCreator();
    
   
    
}

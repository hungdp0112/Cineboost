/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author intfs
 */
public interface SubPanelCreatorInterfaces<E extends SubFrame>{
    
    public SubPanelCreator createSubPanel(E f);
    
   
}

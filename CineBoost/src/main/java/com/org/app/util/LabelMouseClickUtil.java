/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

/**
 *
 * @author intfs
 */
public class LabelMouseClickUtil{

    
    public static void addMouseListenerFor(JLabel lbl, MouseClickListener listener) {
        lbl.addMouseListener(listener);
    }


    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import javax.swing.JLabel;

/**
 *
 * @author intfs
 */
public class JLabelWrapper {

    public static void wrap(JLabel lbl, String text) {
       lbl.setText(String.format("<html>%s</html>", text));
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

/**
 *
 * @author DELL
 */
public class LockDateChooser {
    public static void lock(JDateChooser dc){
        JTextFieldDateEditor txtFld = (JTextFieldDateEditor) dc.getDateEditor();
        txtFld.setEditable(false);
    }
    
    public static void lock(JDateChooser... dcs){
        for (int i = 0; i < dcs.length; i++) {
            lock(dcs[i]);
        }
    }
}

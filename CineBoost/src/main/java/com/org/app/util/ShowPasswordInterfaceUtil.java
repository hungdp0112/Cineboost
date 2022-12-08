/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import javax.swing.JPasswordField;

/**
 *
 * @author intfs
 */
public interface ShowPasswordInterfaceUtil {
    
    
    
    default public void showPassword(JPasswordField p) {
        
        p.setEchoChar('\u0000');
    }
    
    default public void hidePassword(JPasswordField p) {
        
        p.setEchoChar('\u26ab');
    }
    
}

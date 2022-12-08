/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.helper;

import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author intfs
 */
public class InputValidlHelper {
    public static boolean isEmpty( JTextComponent... txt) {
        for(int i = 0; i<txt.length; i++) {
            if(txt[i].getText().isBlank()) {
                txt[i].requestFocus();
                return true;}
        }
        return false;
    }
    
    public static boolean isEmptyPass( JTextComponent... txt) {
        for(int i = 0; i<txt.length; i++) {
            if(txt[i].getText().isBlank()) {
                return true;}
        }
        return false;
    }
    
 
    
    public static void clear(JTextComponent txt) {
        txt.setText("");
    }
    
    public static void clear(JTextComponent... txts) {
        for(int i = 0; i<txts.length; i++) {
            clear(txts[i]);
        }
    }
    
    public static boolean isValidEmail(String input){
        return input.matches("^([\\w\\-\\.]+)\\@(\\w+)(\\.[a-z]{2,10}){1,2}$");
    }
    

}

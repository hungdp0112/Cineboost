/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.helper;


import com.org.app.message.ConfirmDialog;
import com.org.app.message.MessageDialog;
import com.org.app.util.ColorAndIconBank;
import com.org.app.util.ScaleImageIconGenerator;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author intfs
 */
public class MessageHelper {
    public static ImageIcon SuccessIcon = ColorAndIconBank.Icon.SUCCESS.getIcon();
    public static ImageIcon ErrorIcon = ColorAndIconBank.Icon.ERROR.getIcon();
    public static ImageIcon InfoIcon = ColorAndIconBank.Icon.INFO.getIcon();
    public static ImageIcon ValidErrorIcon = ColorAndIconBank.Icon.VALID_ERROR.getIcon();

    public static final int INFORMATION_MESSAGE = 1;
    public static final int ERROR_MESSAGE = 0;
    public static final int SUCCESS_MESSAGE = 2;
    public static final int FORMAT_ERROR_MESSAGE = 3;
    private static MessageDialog msg;
    private static ConfirmDialog cfm;
    
    public static void message(Component parent, String message) {
        msg = new MessageDialog((JFrame) parent);
        msg.showMessage("Thông báo", message, InfoIcon);
        if (msg.getMessageType() == MessageDialog.MessageType.OK) msg.dispose();
    }
    
    public static ImageIcon getIconByType(int messageType) {
        switch  (messageType) {
            case 0 : return ErrorIcon;
            case 1 : return InfoIcon;
            case 2 : return SuccessIcon;
            case 3: return ValidErrorIcon;
        }
        return InfoIcon;
        
    }
        
    
    public static void message(Component parent, String message, int messageType) {
        msg = new MessageDialog((JFrame) parent);
        if (messageType == 1) {
            msg.showMessage("Thông báo", message, InfoIcon);
        }else if (messageType == 0) {
            msg.showMessage("Báo lỗi", message, ErrorIcon);
        }else if (messageType == 3){
            msg.showMessage("Lỗi định dạng", message, ValidErrorIcon);
        }else if (messageType == 2){
            msg.showMessage("Thông báo", message, SuccessIcon);
        }
        if (msg.getMessageType() == MessageDialog.MessageType.OK) msg.dispose();
    }
        
    public static void message(Component parent, String message, ColorAndIconBank.Icon icon) {
        msg = new MessageDialog((JFrame) parent);
        msg.showMessage("Thông báo", message, icon.getIcon());
        if (msg.getMessageType() == MessageDialog.MessageType.OK) msg.dispose();
//        JOptionPane.showMessageDialog(parent, message, "Thông báo", 1,icon.getIcon());
    }
    
    public static void message(Component parent, String title, String message, int messageType) {
//        ImageIcon icon = messageType == INFORMATION_MESSAGE ? SuccessIcon : ErrorIcon;
        ImageIcon icon = getIconByType(messageType);
        msg = new MessageDialog((JFrame) parent);
        msg.showMessage(title, message, icon);
        if (msg.getMessageType() == MessageDialog.MessageType.OK) msg.dispose();
//        JOptionPane.showMessageDialog(parent, message,title, messageType, icon);
    }

    public static boolean confirm(Component parent, String message) {
        cfm = new ConfirmDialog((JFrame) parent);
        cfm.showMessage("Thông báo", message, InfoIcon);
        if (cfm.getMessageType() == ConfirmDialog.MessageType.OK) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean confirm(Component parent, String title, String message) {
        cfm = new ConfirmDialog((JFrame) parent);
        cfm.showMessage(title, message, InfoIcon);
        if (cfm.getMessageType() == ConfirmDialog.MessageType.OK) {
            return true;
        } else {
            return false;
        }
    }
    
}

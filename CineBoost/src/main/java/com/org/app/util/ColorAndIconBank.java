/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/**
 *
 * @author intfs
 */
public class ColorAndIconBank {
    public static String path = "";
    public static ImageIcon CONTENT_PANEL_BACKGROUND = new ImageIcon("resources/icon/pic.jpg");
    public static ImageIcon SIDE_PANEL_BACKGROUND = new ImageIcon(ColorAndIconBank.class.getResource("/icon/sideBar_bckg.png"));
    public static final Color SIDEMENU_COLOR_HOVER = Color.decode("#05595B");
    
    public static final Color SIDEMENU_COLOR_SELECTED = new Color(0,102,102);
    public static final Color SIDEMENU_COLOR_HOVERS =  new Color(72,143,177);
    
    public static final Color SIDEMENU_TEXT_DEFAULT = new Color(51,51,51);
    public static final Color SIDEMENU_TEXT_SELECTED = new Color(255,255,255);
    public static final Color SIDEMENU_TEXT_TITLED = new Color(251,240,155,250);
    public static final Color SIDEMENU_TEXT_BORDER = new Color(251,248,192,255);
    
    public static final Color INPUT_COLOR_FOCUS = new Color(199,199,199,100);
    
    public static final Color FILTER_BAR_BACKGROUND = new Color(234,238,208);
    public static final Color FILTER_BAR_BORDER_TEXT = Color.RED;
    
//    public static final Color REP_COLOR_PART = new Color(223,237,186, 150);
    public static final Color REP_COLOR_PART = Color.decode("#D2E3D2");
    
//    public static final Color ROLLOVER_BUTTON = new Color(149,191,191);
    public static final Color ROLLOVER_BUTTON = Color.decode("#B3BDBE");
    
    public static final Color TOOLBAR_BACKGROUND_COLOR_DEFAULT = Color.decode("#345368");
    public static final Color TOOLBAR_BACKGROUND_COLOR_SELECTED = Color.decode("#FFFFFF");
    public static final Color TOOLBAR_BACKGROUND_COLOR_ENTERED = Color.decode("#4E6B7E");
    
    
    public static final Color TOOLBAR_FOREGROUND_COLOR_DEFAULT = Color.decode("#FFFFFF");
    public static final Color TOOLBAR_FOREGROUND_COLOR_SELECTED = Color.decode("#345368");
    
    public static final Color SEAT_COLOR_SELECTED = Color.decode("#F4AA24");
    public static final Color SEAT_FOREGROUND_COLOR_SELECTED = Color.WHITE;
    
    public static final Color NORMAL_SEAT_COLOR = Color.decode("#DDDDDD");
    public static final Color NORMAL_SEAT_FOREGROUND_COLOR = Color.BLACK;
    
    public static final Color VIP_SEAT_COLOR = Color.decode("#4D648D");
    public static final Color VIP_SEAT_FOREGROUND_COLOR = Color.WHITE;
    
    public static final Color RESERVED_SEAT_COLOR = Color.decode("#A52B24");
    public static final Color RESERVED_SEAT_FOREGROUND_COLOR = Color.WHITE;
    
//    public static final Color MENU_COLOR_SELECTED = Color.decode("");
//    public static final Color MENU_COLOR_HOVERS = Color.decode("");
    
    public enum Icon { 
    ADD(1),
    SAVE(2),
    DELETE(3),
    UPDATE(5),
    SUCCESS(6),
    SEARCH(7),
    EXIT(8),
    OPEN(9),
    INFO(11),
    LOGIN(12),
    LOADING(13),
    ERROR(-1),
    VALID_ERROR(-2);
    
    Map<Integer,String> iconWithMess = new HashMap<>();
    private int index;
    
    Icon(int index) {
        this.index = index;
        createMap();
    }
    
    public ImageIcon getIcon() {
        return new ImageIcon(ColorAndIconBank.class.getResource(iconWithMess.get(index)));
    }
    
    public boolean isError() {
        return index < 0;
    }
    
    private void createMap() {
        String packageName = "/icon/";
        String extension = ".png";
        iconWithMess.put(1, packageName + "add" + extension);
        iconWithMess.put(2, packageName + "save" +extension);
        iconWithMess.put(3, packageName + "trash_can" + extension);
        iconWithMess.put(5, packageName + "update_32" + extension);
        iconWithMess.put(6, packageName + "success_icon" +extension);
        iconWithMess.put(7, packageName + "search" +extension);
        iconWithMess.put(8, packageName + "exit" +extension);
        iconWithMess.put(9, packageName + "open" +extension);
        iconWithMess.put(11, packageName + "info_icon" +extension);
        iconWithMess.put(12, packageName + "login_success" +extension);
        iconWithMess.put(13, packageName + "loading.gif");
        iconWithMess.put(-1, packageName + "error_icon" + extension);
        iconWithMess.put(-2, packageName + "valid_error"+extension);
        
    }
    }
}

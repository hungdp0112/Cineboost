/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.helper;

import com.org.app.util.ScaleImageIconGenerator;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author intfs
 */
public class SettingIconHelper {
    public static void setIconFor(JLabel comp, ImageIcon image) {
        comp.setIcon(ScaleImageIconGenerator.getScaledIconOf(image, comp));
    }
    
    public static void setPosterFor(JLabel comp, String name) {
        ImageIcon poster = ImageUtil.getPosterImage(name);
        System.out.println("pso = "+poster.toString());
        setIconFor(comp, poster);
    }
    
    public static void setProfilePic(JLabel comp, String name) {
        ImageIcon pic = null;
        try {
            pic = ImageUtil.getAvtImage(name);
        }catch(Exception e) {
            System.out.println(" catch profile pic not found");
            pic = ImageUtil.getAvtImage("/imgaes/avatars/user1.png");
            Image img = pic.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH);
//         comp.setIcon(new ImageIcon(img));
        }
        setIconFor(comp, pic);
    } 
    
    public static void setPosterFor(JLabel comp, int w, int h, String name) {
        ImageIcon poster = null;
        try {
         poster = ImageUtil.getPosterImage(name);
         Image img = poster.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
//          setIconFor(comp, new ImageIcon(img));
         comp.setIcon(new ImageIcon(img));
            
        }catch(Exception e){
            e.printStackTrace();
        poster = ImageUtil.getPosterImage("poster1.png");
        Image img = poster.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
         comp.setIcon(new ImageIcon(img));
            
        }
    }
    
    public static void setIconFor(JLabel comp, int w, int h, String name){
        ImageIcon icon = ImageUtil.getDoAnImage(name);
        Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        comp.setIcon(new ImageIcon(img));
    }
    
    public static void setIconSourceFor(JLabel comp, ImageIcon image) {
        
    }
    
    public static void setIconSourceFor(JLabel comp, String name) {
        comp.setIcon(ScaleImageIconGenerator.getScaledSourcesIcon(name, comp));
    }
    
    public static void setIconFor(JLabel comp, String name) {
        comp.setIcon(ScaleImageIconGenerator.getScaledIconOf(name, comp));
    }
    
    
}

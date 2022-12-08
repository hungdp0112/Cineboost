/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 *
 * @author intfs
 */
public class ScaleImageIconGenerator {
    public static ImageIcon getScaledSourcesIcon(String name, JComponent comp) {
        ImageIcon i = getImageFromResources(name);
        Image img = i.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    public static ImageIcon getScaledIconOf(ImageIcon icon, JComponent comp) {
        Image img = icon.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    
    public static ImageIcon getImageFromResources(String name) {
        return new ImageIcon(ScaleImageIconGenerator.class.getResource("/icon/"+name));
    }
    
    public static ImageIcon getScaledIconOf(String name, JComponent comp) {
        System.out.println("name: " + name);
        ImageIcon i = new ImageIcon(ScaleImageIconGenerator.class.getResource(name));
        Image img = i.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }
    public static void main(String[] args) {
//       ImageIcon d = new ImageIcon(ScaleImageIconGenerator.class.getResource("pic.jpg"));
        System.out.println(ScaleImageIconGenerator.class.getResource("/com/org/app/resources/").toExternalForm());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

/**
 *
 * @author intfs
 */
public class GiamGiaDieuKien {
   public static int ggCombo = 10; 

   
   public static int ggDonVeTren4 = 5;
   public static int ggKHDauTien = 10;
   
   public static int ggDoAnTren3 = 5;
   
   public static int getGiamGiaDauTien(int slVe, int slDa) {
       int giam = ggKHDauTien;
       if(slVe >= 4) {
           giam+= ggDonVeTren4;
       }else if(isDonCombo(slVe, slDa)) {
           giam += ggCombo;
       }
       return giam;
   }
   public static int getGiamGia(int slVe, int slDa) {
       if(isDonCombo(slVe, slDa)) return ggCombo;
       if(slVe >= 4) return ggDonVeTren4;
       if (slDa >= 3) return ggDoAnTren3;
       
       return 0;
   }
      
   public static boolean isDonCombo(int slVe, int slDA) {
       return (slVe == slDA) && slVe >= 2;
   }
   
   public static int getTestGG() {
       return 10;
   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class DinhDangTienTe {
    public static String chuyenThanhTienVN(Integer tien){
        Locale locale = new Locale("vi", "VN");
        String pattern = "##,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);
        return dcf.format(tien);
    }
    
    public static String chuyenThanhTienVN(Double tien){
        Locale locale = new Locale("vi", "VN");
        String pattern = "##,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);
        String tienDoi = String.valueOf(tien*100);
        int tienCD = Integer.parseInt(tienDoi.replace(".", ""));
        return dcf.format(tienCD);
    }
    
    public static String chuyenThanhTienVN(BigDecimal tien){
        Locale locale = new Locale("vi", "VN");
        String pattern = "##,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);
        int tienXoaCham = Integer.parseInt(dcf.format(tien).replace(".", ""));
        int tienCD = tienXoaCham * 1000;
        return dcf.format(tienCD);
    }
    
    public static String chuyenThanhTienVN(String tien){
        Locale locale = new Locale("vi", "VN");
        String pattern = "##,###,###,###";
        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        dcf.applyPattern(pattern);
        int tienXoaCham = Integer.parseInt(tien.replace(".", ""));
        int tienCD = tienXoaCham;
        return dcf.format(tienCD);
    }
    
    public static Integer chuyenChuoiThanhInt(String tien){
        int tienCD = Integer.parseInt(tien.replace(".", ""));
        return tienCD;
    }
    
    public static Double chuyenChuoiThanhDouble(String tien){
        double tienCD = (Double.parseDouble(tien.replace(".", ""))/1000);
        return tienCD;
    }
    
//    public static void main(String[] args) {
//        String tien = "1.280";
//        
//        Locale locale = new Locale("vi", "VN");
//        String pattern = "##,###,###,###";
//        DecimalFormat dcf = (DecimalFormat) NumberFormat.getNumberInstance(locale);
//        dcf.applyPattern(pattern);  
//        int tien2 = Integer.parseInt(tien.replace(".", ""));
//        int tien3 = (int) (tien2*Math.pow(10, 3));
//        System.out.println("tien " + tien2 + " tien sau " + dcf.format(tien3));
//
//        BigDecimal decimal = new BigDecimal("20.00");
//        System.out.println("big decimal " + decimal);
//        System.out.println("dinh dang " + chuyenThanhTienVN(decimal));
//        String tien = "20.000";
//        System.out.println("dinh dang 2 " + chuyenChuoiThanhInt(tien));
//        System.out.println("string_double " + string_double);
//        double douBle = 1000.0;
//        System.out.println("double " + String.valueOf(douBle*10).replace(".", ""));
//        BigDecimal decimal = new BigDecimal(1000.00);
//        String deci = String.valueOf(decimal);
//        int decid = Integer.parseInt(deci) * 100;
//        System.out.println("big decimal " + decid);
//    }
}

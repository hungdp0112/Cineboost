/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.helper;

import com.org.app.entity.NhanVien;

/**
 *
 * @author intfs
 */
public class Authenticator {
        public static NhanVien USER;


    public static void logOut() {
        Authenticator.USER = null;
    }

 
    public static boolean isLogin() {
        return Authenticator.USER != null;
    }
    
    public static boolean isQuanLy() {
        return USER.getVaitro();
    }
    
}

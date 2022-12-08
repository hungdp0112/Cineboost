/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.KichCoDoAn;
import com.org.app.entity.NhanVien;
import com.org.app.entity.Phim;
import com.org.app.entity.ThanhVien;

/**
 *
 * @author intfs
 */
public class ValidationHelper {
    
 
    public static Validator<NhanVien> getNhanVienChecker() {
        return new NhanVienValidator();
    }
    
    public static Validator<ThanhVien> getThanhVienChecker() {
        return new KhachHangValidator();
    }
    
    public static Validator<KichCoDoAn> getKichCoDoAnChecker() {
        return new QLDoAnKemValidator();
    }
    
    public static Validator<Phim> getPhimChecker() {
        return new PhimValidator();
    }
    
}

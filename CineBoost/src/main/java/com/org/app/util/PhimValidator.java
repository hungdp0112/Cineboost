/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.Phim;
import com.org.app.helper.DateHelper;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class PhimValidator extends Validator<Phim>{
    private Phim p;
    
    public enum PhimValidatorError {
        DINH_DANG_NGAY("Sai định dạng ngày: dd/MM/yyyy"),
        DINH_DANG_THOILUONG("Thời lượng không được quá 3 giờ");
        
        private String mess; 
        
        PhimValidatorError(String mess) {
            this.mess = mess;
        }
        
        public String getMess() {return mess;}
    }
    
    public PhimValidator (Phim p){
        this.p = p;
    }
    PhimValidator(){        
    }
    
    @Override
    public void check(Phim p) throws ValidatorException {
        this.p = p;
//        checkTenPhim();
        checkNgay();
        checkThoiLuong();
    }
    
    public void checkTenPhim() throws ValidatorException {
        if(p.getTen().length() < 3 || p.getTen().length() > 50) 
            throw new ValidatorException("Tên phim phải từ 3 - 50 ký tự");
        else if(!p.getTen().matches("[\\p{L}]+(\\s\\p{L}+)*")) {
            throw new ValidatorException("Tên phim phải là chữ");
        }
    }
    
    public void checkNgay() throws ValidatorException {
        try {
            new SimpleDateFormat("dd/MM/yyyy").parse(DateHelper.toString(p.getNgayKhoiChieu()));
        } catch (Exception e) {
            throw new ValidatorException(PhimValidatorError.DINH_DANG_NGAY.getMess());
        }
    }
    
    public void checkThoiLuong() throws ValidatorException {
        int gio = p.getThoiLuong()/60;
        if (gio >= 4) {
            throw new ValidatorException(PhimValidatorError.DINH_DANG_THOILUONG.getMess());
        }
    }
    
    
}

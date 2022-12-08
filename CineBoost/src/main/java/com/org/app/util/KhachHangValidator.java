/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.ThanhVien;
import com.org.app.helper.DateHelper;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhachHangValidator extends Validator<ThanhVien>{
    private ThanhVien tv;
    
    public enum KhachHangValidatorError {
        SODT_TRUNG("Số điện thoại này đã tồn tại"),
        EMAIL_TRUNG("Email này đã tồn tại");
        
        private String mess; 
        
        KhachHangValidatorError(String mess) {
            this.mess = mess;
        }
        
        public String getMess() {return mess;}
    }
    
    public KhachHangValidator (ThanhVien tv){
        this.tv = tv;
    }
    KhachHangValidator(){
        
    }
    @Override
    public void check(ThanhVien tv) throws ValidatorException {
        this.tv = tv;
        checkHoTen();
        checkNgaySinh();
        checkDienThoai();
        checkEmail();
    }
    
    public void checkHoTen() throws ValidatorException {
        if(tv.getHoTen().length() < 3 || tv.getHoTen().length() > 50) 
            throw new ValidatorException("Họ tên phải từ 3 - 50 ký tự");
        else if(!tv.getHoTen().matches("[\\p{L}]+(\\s\\p{L}+)*")) {
            throw new ValidatorException("Họ tên phải là chữ");
        }
    }
    
    public void checkNgaySinh() throws ValidatorException {      
        int max = LocalDate.now().getYear() - 18; 
        int y = DateHelper.toDate(tv.getNgaySinh()).toLocalDate().getYear();
        System.out.println(y);
        if( y > max) throw new ValidatorException(String.format("Khách hàng phải từ 18 tuổi trở lên (%d)", max));
        if(DateHelper.compare(DateHelper.now(), tv.getNgaySinh()) > 0) {
            
        }
    }
    
    public void checkSoDT(List<ThanhVien> list, ThanhVien current, String soDTMoi) throws ValidatorException {
        checkDienThoai(soDTMoi);
        String currentID = current.getID();
        System.out.println("curID " + currentID);
        boolean isSameSoDT = (currentID != "") && current.getSoDT().equals(soDTMoi);
        System.out.println("isSame " + isSameSoDT);
        if(currentID == "" && !isSameSoDT) {
            for(ThanhVien n : list){
                if(n.getSoDT().equals(soDTMoi)) throw new ValidatorException(KhachHangValidatorError.SODT_TRUNG.getMess());
            }
        }else if (!isSameSoDT){
            for(ThanhVien n : list){
                System.out.println("getSDT " + n.getSoDT());
                System.out.println("soDTMoi " + soDTMoi);
                if(n.getSoDT().equals(soDTMoi)) throw new ValidatorException(KhachHangValidatorError.SODT_TRUNG.getMess());
            }
        }
    }
    
    public void checkEmail(String email) throws ValidatorException {
        if(!ValidationUtil.isValidEmail(email)) throw new ValidatorException("Email không đúng định dạng");
    }
    
    public void checkEmail() throws ValidatorException {
        checkEmail(tv.getEmail());
    }
    
     public void checkDienThoai(String soDT) throws ValidatorException {
        if(!ValidationUtil.dienThoai(soDT)) throw new ValidatorException("Điện thoại phải từ 9-12 số");
    }
     
     public void checkDienThoai() throws ValidatorException {
         checkDienThoai(tv.getSoDT());
     }
}

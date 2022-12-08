/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.NhanVien;
import com.org.app.helper.DateHelper;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 *
 * @author intfs
 */
public class NhanVienValidator extends Validator<NhanVien> {
    
    private NhanVien nv;
    
    public enum NhanVienValidatorError {
        ACCOUNT_TRUNG("Tên tài khoản trùng"),
        ACCOUNT_DINH_DANG("Tên tài khoản không chứa ký tự in hoa và ký tự đặc biệt hoặc khoảng cách"),
        ACCOUNT_DO_DAI("Tên tài khoản có độ dài từ 6-15 ký tự"),
        MAT_KHAU_CU_SAI("Mật khẩu cũ không đúng"),
        DO_DAI("Mật khẩu có độ dài từ 6-10 ký tự"),
        DINH_DANG("Mật khẩu ít nhất 1 chữ viết hoa, 1 chữ thường và số"),
        KHOP("Xác nhận mật khẩu không trùng khớp"),
        EMAIL_TRUNG("Email này đã tồn tại");
        
        private String mess; 
        
        NhanVienValidatorError(String mess) {
            this.mess = mess;
        }
        
        public String getMess() {return mess;}
    }

    public NhanVienValidator(NhanVien nv) {
        this.nv = nv;
    }

    public NhanVienValidator getValidator() {return this;}
    
    public NhanVienValidator() {
    }
    
    @Override
    public void check(NhanVien nv) throws ValidatorException{
        this.nv = nv;
        checkHoten();
        checkNgaySinh();
        checkEmail();
        checkDienThoai();
        
    }
    
    public void checkTenTK(List<NhanVien> list, NhanVien current, String tenTKmoi) throws ValidatorException {
        checkFormatTenTK(tenTKmoi);
        String currentID = current.getId();
        boolean isSameUser = (currentID != "") && current.getTentk().equals(tenTKmoi);
        if(currentID == "" && !isSameUser) {
            for(NhanVien n : list){
                if(n.getTentk().equals(tenTKmoi)) throw new ValidatorException(NhanVienValidatorError.ACCOUNT_TRUNG.getMess());
            }
        }else if (!isSameUser){
            for(NhanVien n : list){
                if(n.getTentk().equals(tenTKmoi)) throw new ValidatorException(NhanVienValidatorError.ACCOUNT_TRUNG.getMess());
            }
        }
    }
    
    public void checkGmail(List<NhanVien> list, NhanVien current, String emailMoi) throws ValidatorException {
        checkEmail(emailMoi);
        String currentID = current.getId();
        boolean isSameEmail = (currentID != "") && current.getEmail().equals(emailMoi);
        if(currentID == "" && !isSameEmail) {
            for(NhanVien n : list){
                if(n.getEmail().equals(emailMoi)) throw new ValidatorException(NhanVienValidatorError.EMAIL_TRUNG.getMess());
            }
        }else if (!isSameEmail){
            for(NhanVien n : list){
                if(n.getEmail().equals(emailMoi)) throw new ValidatorException(NhanVienValidatorError.EMAIL_TRUNG.getMess());
            }
        }
    }
    
    public void checkFormatTenTK(String account) throws ValidatorException {
        if(account.length() < 6 || account.length() > 15) {
            throw new ValidatorException(NhanVienValidatorError.ACCOUNT_DO_DAI.getMess());
        }else if(!account.matches("^([a-z0-9]+)$")) {
            throw new ValidatorException(NhanVienValidatorError.ACCOUNT_DINH_DANG.getMess());
        }
    }
    public void checkMatKhau(NhanVien user, String mkCu, String matKhauMoi, String xacNhan) throws ValidatorException {
      if(!user.getMatkhau().equals(mkCu)) throw new ValidatorException(NhanVienValidatorError.MAT_KHAU_CU_SAI.getMess());
      

        if(matKhauMoi.length() < 6 || matKhauMoi.length() > 10) {
          throw new ValidatorException(NhanVienValidatorError.DO_DAI.getMess());
        } else if(!matKhauMoi.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]+)$")) throw new ValidatorException(NhanVienValidatorError.DINH_DANG.getMess());
        else if(!matKhauMoi.equals(xacNhan)) throw new ValidatorException(NhanVienValidatorError.KHOP.getMess());
    }
    
    // Cho ql nhan vien frame
    public void checkMatKhau(String matKhau, String xacNhan) throws ValidatorException {
        if(matKhau.length() < 6 || matKhau.length() > 10) {
            throw new ValidatorException(NhanVienValidatorError.DO_DAI.getMess());
        } 
        else if(!matKhau.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])([a-zA-Z0-9]+)$")) throw new ValidatorException(NhanVienValidatorError.DINH_DANG.getMess());
        else if(!matKhau.equals(xacNhan)) throw new ValidatorException(NhanVienValidatorError.KHOP.getMess());
    }
    public void checkEmail(String email) throws ValidatorException {
        if(!ValidationUtil.isValidEmail(email)) throw new ValidatorException("Email không đúng định dạng");
    }
    
    public void checkEmail() throws ValidatorException {
        checkEmail(nv.getEmail());
    }
    
    public void checkDienThoai(String dt) throws ValidatorException {
        dt = dt.replaceAll("[^0-9]+", "");
        if(!ValidationUtil.dienThoai(dt))  throw new ValidatorException("Điện thoại phải từ 9-10 số");
    }
    
    public void checkDienThoai() throws ValidatorException {
           checkDienThoai(nv.getSodt());
    }
    
    //Do tuoi tu 16 - 65
    public void checkNgaySinh(java.sql.Date ngaySinh) throws ValidatorException {
         int max = LocalDate.now().getYear() - 16;
        int min = LocalDate.now().getYear() - 65;
        
        int y = DateHelper.toDate(ngaySinh).toLocalDate().getYear();
        if(y < min || y > max) throw new ValidatorException(String.format("Nhân viên phải có độ tuổi từ 16-65 (%d-%d)", min, max));
    }
    public void checkNgaySinh() throws ValidatorException {
        checkNgaySinh(nv.getNgaysinh());        
    }
    public void checkHoten() throws ValidatorException {
        checkHoten(nv.getHoten());
    }
    
    public void checkHoten(String hoten) throws ValidatorException {
        if(hoten.length() < 3 || hoten.length() > 50) 
            throw new ValidatorException("Họ tên phải từ 3 - 50 ký tự");
        else if(!hoten.matches("[\\p{L}]+(\\s\\p{L}+)*")) {
            throw new ValidatorException("Họ tên phải là chữ");
        }
    }
}

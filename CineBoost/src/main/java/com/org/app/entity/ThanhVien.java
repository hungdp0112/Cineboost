/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

import java.sql.Date;

/**
 *
 * @author intfs
 */
public class ThanhVien {
    private String ID = null;
    private String hoTen;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String soDT;
    private String email;
    private String makh;

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public ThanhVien(String ID, String hoTen, Date ngaySinh, boolean gioiTinh, String soDT, String email, String makh) {
        this.ID = ID;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
        this.makh = makh;
    }

    public ThanhVien() {
    }
    
    public ThanhVien(String hoTen, Date ngaySinh, boolean gioiTinh, String soDT, String email) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.soDT = soDT;
        this.email = email;
    }
    
     public ThanhVien(String ID, String hoTen, Date ngaySinh, boolean gioiTinh, String soDT, String email) {
        this(hoTen,ngaySinh,gioiTinh,soDT,email);
        this.ID = ID;   
    }

    public ThanhVien(String id_ThanhVien) {
        this.ID = id_ThanhVien;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

@Override
    public String toString() {
        return hoTen;
    }    


}

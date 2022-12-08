/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author intfs
 */
public class NhanVien {
    private String id;
    private String hoten;
    private Date ngaysinh;
    private boolean gioitinh;
    private String sodt;
    private String email;
    private boolean vaitro;
    private String anh;
    private String tentk;
    private String matkhau;
   


    public NhanVien(String id) {
        this.id = id;
    }

    public NhanVien() {
    }

    public NhanVien(String hoten, Date ngaysinh, boolean gioitinh, String sodt, String email, boolean vaitro, String anh,String tentk, String matkhau) {
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sodt = sodt;
        this.email = email;
        this.vaitro = vaitro;
        this.anh = anh;
        this.tentk = tentk;
        this.matkhau = matkhau;
    }
    
    public NhanVien(String id, String hoten, Date ngaysinh, boolean gioitinh, String sodt, String email, boolean vaitro,String anh, String tentk, String matkhau) {
        this(hoten,ngaysinh,gioitinh,sodt,email,vaitro,anh,tentk,matkhau);
        this.id = id;
    }

    
    public NhanVien(NhanVien n) {
        this(n.getId(), n.getHoten(), n.getNgaysinh(), n.getGioiTinh(), n.getSodt(), n.getEmail(), n.getVaitro(), n.getAnh(), n.getTentk(), n.getMatkhau());
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public boolean getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getSodt() {
        return sodt;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getVaitro() {
        return vaitro;
    }

    public void setVaitro(boolean vaitro) {
        this.vaitro = vaitro;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTentk() {
        return tentk;
    }

    public void setTentk(String tentk) {
        this.tentk = tentk;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public boolean getGioiTinh() {
        return this.gioitinh;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", hoten=" + hoten + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh + ", sodt=" + sodt + ", email=" + email + ", vaitro=" + vaitro + ", anh=" + anh + ", tentk=" + tentk + ", matkhau=" + matkhau + '}';
    }

   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhanVien other = (NhanVien) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
    
    
    
    
    
            
            
}

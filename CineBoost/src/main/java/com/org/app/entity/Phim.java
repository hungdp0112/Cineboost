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
public class Phim {
    private String id;
    private String ten;
    private String poster;
    private Integer thoiLuong;
    private String theloai;
    private String ngonNgu;
    private Date ngayKhoiChieu;
    private String tomTat;
    private boolean trangThai;
    private String nv;

    public Phim(String id, String ten, String poster, Integer thoiLuong, String theloai, String ngonNgu, Date ngayKhoiChieu, String tomTat, boolean trangThai, String nv) {
        this(ten,poster,thoiLuong,theloai,ngonNgu,ngayKhoiChieu,tomTat,trangThai,nv);
        this.id = id;
    }

    public Phim(String ten, String poster, Integer thoiLuong, String theloai, String ngonNgu, Date ngayKhoiChieu, String tomTat, boolean trangThai, String nv) {
        this.ten = ten;
        this.poster = poster;
        this.thoiLuong = thoiLuong;
        this.theloai = theloai;
        this.ngonNgu = ngonNgu;
        this.ngayKhoiChieu = ngayKhoiChieu;
        this.tomTat = tomTat;
        this.trangThai = trangThai;
        this.nv = nv;
    }

    public Phim() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(Integer thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

   

    public String getTheloai() {
        return theloai;
    }

    public void setTheloai(String theloai) {
        this.theloai = theloai;
    }

    public String getNgonNgu() {
        return ngonNgu;
    }

    public void setNgonNgu(String ngonNgu) {
        this.ngonNgu = ngonNgu;
    }

    public Date getNgayKhoiChieu() {
        return ngayKhoiChieu;
    }

    public void setNgayKhoiChieu(Date ngayKhoiChieu) {
        this.ngayKhoiChieu = ngayKhoiChieu;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    @Override
    public String toString() {
        //return "Phim{" + "id=" + id + ", ten=" + ten + ", poster=" + poster + ", thoiLuong=" + thoiLuong + ", theloai=" + theloai + ", ngonNgu=" + ngonNgu + ", ngayKhoiChieu=" + ngayKhoiChieu + ", tomTat=" + tomTat + ", trangThai=" + trangThai + ", nv=" + nv + '}';
        return ten;
    }
    
    public String toTen() {
        return ten;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Phim other = (Phim) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    

}

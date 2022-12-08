/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

import com.org.app.helper.DateHelper;
import com.org.app.helper.TimeHelper;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

/**
 *
 * @author intfs
 */
public class SuatChieu {
    private String id;
    private Date ngayChieu;
    private String phong;
    private String phim;
    private Time gioBatDau;
    private Time gioKetThuc;
    private String nv;

    public SuatChieu(String id, Date ngayChieu, String phong, String phim, Time gioBatDau, Time gioKetThuc, String nv) {
        this(ngayChieu, phong,phim,gioBatDau,gioKetThuc,nv);
        this.id = id;
    }

    public SuatChieu(Date ngayChieu, String phong, String phim, Time gioBatDau, Time gioKetThuc, String nv) {
        this.ngayChieu = ngayChieu;
        this.phong = phong;
        this.phim = phim;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.nv = nv;
    }

    public SuatChieu() {
    }

    public SuatChieu(String idSuat, Date ngayChieu, Time gioBatDau) {
        this.id = idSuat;
        this.ngayChieu = ngayChieu;
        this.gioBatDau = gioBatDau;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getPhim() {
        return phim;
    }

    public void setPhim(String phim) {
        this.phim = phim;
    }

    public Time getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(Time gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    @Override
    public String toString() {
        //return "SuatChieu{" + "id=" + id + ", ngayChieu=" + ngayChieu + ", phong=" + phong + ", phim=" + phim + ", gioBatDau=" + gioBatDau + ", gioKetThuc=" + gioKetThuc + ", nv=" + nv + '}';
        return DateHelper.toString(ngayChieu,DateHelper.DDMMYYY_SLASH_FORMAT) + "-" +TimeHelper.toStringWithHourMintues(gioBatDau) + "-"+getPhongString();
    }
    
    public String getPhongString() {
        String p = getPhong();
        return "P" + getPhong().substring(p.length()-2,p.length());
    }
    public String toFullString() {
         return "SuatChieu{" + "id=" + id + ", ngayChieu=" + ngayChieu + ", phong=" + phong + ", phim=" + phim + ", gioBatDau=" + gioBatDau + ", gioKetThuc=" + gioKetThuc + ", nv=" + nv + '}';
    }
    
    public String getPhongNum() {
        return (Integer.parseInt(getPhong().substring(1))+"");
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SuatChieu other = (SuatChieu) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public boolean isSuatDaChieu(java.time.LocalDateTime dateTime) {
        java.time.LocalDateTime scTime = DateHelper.toLocalDateTime(ngayChieu, gioBatDau);
        System.out.println("ngay "+ ngayChieu);
        System.out.println("now " + dateTime.toLocalDate().toString());
        int cp = DateHelper.compare(scTime, dateTime);
        System.out.println("cp = "+cp);
        return cp <= 0;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}

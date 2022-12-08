/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.entity.Phim;
import com.org.app.entity.SuatChieu;
import com.org.app.helper.TimeHelper;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author intfs
 */
public class PhimPanelModel {
    private Phim phim;
    private String tenPhim;
    private String poster;
    private String phongChieu;
    private String gioChieu;
    private Integer luongVe;
    private SuatChieu sc;

    public PhimPanelModel() {
        tenPhim = "Unknown";
        poster = "Unknown";
        phongChieu = "Phòng Unknown";
        gioChieu = "00:00";
        luongVe = 0;
        sc = null;
    }

    
    public Integer getLuongVe() {
        return luongVe;
    }

    public void setLuongVe(Integer luongVe) {
        this.luongVe = luongVe;
    }
    
    

    public PhimPanelModel(Phim phim, String poster, String idSuat, String phongChieu, Time gioChieu, int luongVe) {
        this.phim = phim;
        this.poster = poster;
        this.phongChieu = phongChieu;
        this.gioChieu = TimeHelper.toStringWithHourMintues(gioChieu);
        this.luongVe = luongVe;
    }
    
    public PhimPanelModel(Phim phim, String idSuat, String phongChieu, Time gioChieu) {
        this(phim, phim.getPoster(), idSuat, phongChieu, TimeHelper.now(),0);
    }
    
    public PhimPanelModel(Phim p, SuatChieu sc, int luongve) {
        this(p, p.getPoster(), sc.getId() ,sc.getPhong(),sc.getGioBatDau(),luongve);
        this.sc = sc;
    }
    
    public String convertGioChieu(Date gio) {
        
        return null;
    }

    public String getTenPhim() {
        return phim.getTen();
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getPoster() {
        return phim.getPoster();
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPhongChieu() {
        return sc.getPhong();
    }
    
    public String getNgonNgu() {
        return phim.getNgonNgu();
    }
    
    public String getTheLoai() {
        return phim.getTheloai();
    }
    
    public java.sql.Date getNgayKC() {
        return phim.getNgayKhoiChieu();
    }
    
    public int getThoiLuong() {
        return phim.getThoiLuong();
    }
    
    public String getTrangThai() {
        return phim.getTrangThai()? "Đang chiếu" : "Dừng chiếu";
    }
 
    public void setPhongChieu(String phongChieu) {
        this.sc.setPhong(phongChieu);
    }

    public String getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(String gioChieu) {
        this.gioChieu = gioChieu;
    }

    public SuatChieu getSc() {
        return sc;
    }

    public void setSc(SuatChieu sc) {
        this.sc = sc;
    }
    
}

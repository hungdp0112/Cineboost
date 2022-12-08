/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

/**
 *
 * @author intfs
 */
public class VeDat {
    private Integer id;
    private String loaiVe;
    private String ghe;
    private String donVe;
    private String suatChieu;

    public VeDat(Integer id, String loaiVe, String ghe, String donVe, String suatChieu) {
        this(loaiVe,ghe,donVe,suatChieu);
        this.id = id;
        
    }

    public VeDat(String loaiVe, String ghe, String donVe, String suatChieu) {
        this.id = null;
        this.loaiVe = loaiVe;
        this.ghe = ghe;
        this.donVe = donVe;
        this.suatChieu = suatChieu;
    }

    public VeDat() {
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoaiVe() {
        return loaiVe;
    }

    public void setLoaiVe(String loaiVe) {
        this.loaiVe = loaiVe;
    }

    public String getGhe() {
        return ghe;
    }

    public void setGhe(String ghe) {
        this.ghe = ghe;
    }

    public String getDonVe() {
        return donVe;
    }

    public void setDonVe(String donVe) {
        this.donVe = donVe;
    }

    public String getSuatChieu() {
        return suatChieu;
    }

    public void setSuatChieu(String suatChieu) {
        this.suatChieu = suatChieu;
    }    

    @Override
    public String toString() {
        return "VeDat{" + "id=" + id + ", loaiVe=" + loaiVe + ", ghe=" + ghe + ", donVe=" + donVe + ", suatChieu=" + suatChieu + '}';
    }
    
    
}

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
public class Ghe {
    private String id;
    private String viTriDay;
    private String viTriCot;
    private PhongChieu phong;
    private LoaiGhe loaiGhe;
    public static double phuThuVip;

    public Ghe(String id, String viTriDay, String viTriCot, PhongChieu phong, LoaiGhe loaiGhe) {
        this.id = id;
        this.viTriDay = viTriDay;
        this.viTriCot = viTriCot;
        this.phong = phong;
        this.loaiGhe = loaiGhe;
        phuThuVip = getPhuThuVip();
    }

    public double getPhuThuVip() {
        return this.loaiGhe.getGia();
    }
    public Ghe() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getViTriDay() {
        return viTriDay;
    }

    public void setViTriDay(String viTriDay) {
        this.viTriDay = viTriDay;
    }

    public String getViTriCot() {
        return viTriCot;
    }

    public void setViTriCot(String viTriCot) {
        this.viTriCot = viTriCot;
    }

    public PhongChieu getPhong() {
        return phong;
    }

    public void setPhong(PhongChieu phong) {
        this.phong = phong;
    }

    public LoaiGhe getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(LoaiGhe loaiGhe) {
        this.loaiGhe = loaiGhe;
    }
    
    public boolean isVip() {
        return loaiGhe.getId().equals("VP");
    }

    @Override
    public String toString() {
        return "Ghe{" + "id=" + id + '}';
    }
    
    
}

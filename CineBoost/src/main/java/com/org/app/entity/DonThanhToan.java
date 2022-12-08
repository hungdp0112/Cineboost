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
public class DonThanhToan {
    private Integer id;
    private String donDoAn;
    private String donVe;
    private Double tong;
    private String nv;
    private String tv;
    private Date ngayDat;

    public DonThanhToan(Integer id, String donDoAn, String donVe, Double tong, String nv, String tv, Date ngayDat) {
        this.id = id;
        this.donDoAn = donDoAn;
        this.donVe = donVe;
        this.tong = tong;
        this.nv = nv;
        this.tv = tv;
        this.ngayDat = ngayDat;
    }

    public DonThanhToan(String donDoAn, String donVe, Double tong, String nv, String tv, Date ngayDat) {
        this.donDoAn = donDoAn;
        this.donVe = donVe;
        this.tong = tong;
        this.nv = nv;
        this.tv = tv;
        this.ngayDat = ngayDat;
    }

    public DonThanhToan(String nv, String tv) {
        this.nv = nv;
        this.tv = tv;
        this.ngayDat = null;
        this.donVe = null;
        this.donDoAn = null;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDonDoAn() {
        return donDoAn;
    }

    public void setDonDoAn(String donDoAn) {
        this.donDoAn = donDoAn;
    }

    public String getDonVe() {
        return donVe;
    }

    public void setDonVe(String donVe) {
        this.donVe = donVe;
    }

   


    public Double getTong() {
        return tong;
    }

    public void setTong(Double tong) {
        this.tong = tong;
    }

    public String getNv() {
        return nv;
    }

    public void setNv(String nv) {
        this.nv = nv;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }


    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }
    
    
    
    
    
    
}

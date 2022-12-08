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
public class DoAnChiTiet {
    private Integer id;
    private String donDA;
    private int soLuong;
    private Integer doAn;
    
    public DoAnChiTiet(Integer id, String donDA, int soLuong, Integer doAn) {
        this.id = id;
        this.donDA = donDA;
        this.soLuong = soLuong;
        this.doAn = doAn;
    }

    public DoAnChiTiet(String donDA, int soLuong, Integer doAn) {
        this.donDA = donDA;
        this.soLuong = soLuong;
        this.doAn = doAn;
    }
    
    public DoAnChiTiet(Integer doAn, Integer soluong){
        this.id = null;
        this.donDA = null;
        this.doAn = doAn;
        this.soLuong = soluong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDonDA() {
        return donDA;
    }

    public void setDonDA(String donDA) {
        this.donDA = donDA;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDoAn() {
        return doAn;
    }

    public void setDoAn(Integer doAn) {
        this.doAn = doAn;
    }
    
    
    
}

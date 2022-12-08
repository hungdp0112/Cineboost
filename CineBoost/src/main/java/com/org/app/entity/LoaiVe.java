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
public class LoaiVe {
    private String id;
    private String ten;
    private Double gia;

    public LoaiVe(String id, String ten, Double gia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
    }

    public String getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public Double getGia() {
        return gia;
    }
    
    
}

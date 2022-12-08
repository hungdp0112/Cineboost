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
public class DoAn {
    private String id;
    private String ten;
    private LoaiDoAn loaiDoAn;

    public DoAn(String id, String ten, LoaiDoAn loaiDoAn) {
        this(ten,loaiDoAn);
        this.id = id;
    }

    public DoAn(String ten, LoaiDoAn loaiDoAn) {
        this.ten = ten;
        this.loaiDoAn = loaiDoAn;
    }

    public DoAn() {
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

    public LoaiDoAn getLoaiDoAn() {
        return loaiDoAn;
    }

    public void setLoaiDoAn(LoaiDoAn loaiDoAn) {
        this.loaiDoAn = loaiDoAn;
    }
    
 
    
    
    
}

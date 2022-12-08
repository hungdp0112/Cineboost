/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

import java.util.HashSet;

/**
 *
 * @author intfs
 */
public class DonDoAn {   
    private String id;
    private Integer soluong;
    private Double tamTinh;
    private HashSet<DoAnChiTiet> doAns;

    public DonDoAn(String id, Integer soluong, Double tamTinh, HashSet<DoAnChiTiet> doAns) {
        this.id = id;
        this.soluong = soluong;
        this.tamTinh = tamTinh;
        this.doAns = doAns;
    }

    public DonDoAn(String id, Integer soluong, Double tamTinh) {
        this.id = id;
        this.soluong = soluong;
        this.tamTinh = tamTinh;
        doAns = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSoluong() {
        return soluong;
    }

    public void setSoluong(Integer soluong) {
        this.soluong = soluong;
    }

    public Double getTamTinh() {
        return tamTinh;
    }

    public void setTamTinh(Double tamTinh) {
        this.tamTinh = tamTinh;
    }
    
    
    
}

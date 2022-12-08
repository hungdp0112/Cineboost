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
public class DonVe {
    private String id;
    private Integer tongGhe;
    private Double tamTinh;
    private HashSet<VeDat> veDats;

    public DonVe(String id, Integer tongGhe, Double tamTinh) {
        this.id = id;
        this.tongGhe = tongGhe;
        this.tamTinh = tamTinh;
        veDats = new HashSet<>();
    }

    public DonVe(String id, Integer tongGhe, Double tamTinh, HashSet<VeDat> veDats) {
        this.id = id;
        this.tongGhe = tongGhe;
        this.tamTinh = tamTinh;
        this.veDats = veDats;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTongGhe() {
        return tongGhe;
    }

    public void setTongGhe(Integer tongGhe) {
        this.tongGhe = tongGhe;
    }

    public Double getTamTinh() {
        return tamTinh;
    }

    public void setTamTinh(Double tamTinh) {
        this.tamTinh = tamTinh;
    }

    public HashSet<VeDat> getVeDats() {
        return veDats;
    }

    public void setVeDats(HashSet<VeDat> veDats) {
        this.veDats = veDats;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

import java.util.Objects;

/**
 *
 * @author intfs
 */
public class KichCoDoAn {
    Integer id;
    DoAn doAn;
    KichCo kichco;
    Double gia;

    public KichCoDoAn(Integer id, KichCo kichco, Double gia) {
        this.id = id;
        this.kichco = kichco;
        this.gia = gia;
    }
    
    public KichCoDoAn(Integer id, DoAn doAn, KichCo kichco, Double gia) {
        this.id = id;
        this.doAn = doAn;
        this.kichco = kichco;
        this.gia = gia;
    }

    public KichCoDoAn() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DoAn getDoAn() {
        return doAn;
    }

    public void setDoAn(DoAn doAn) {
        this.doAn = doAn;
    }

    public KichCo getKichco() {
        return kichco;
    }

    public void setKichco(KichCo kichco) {
        this.kichco = kichco;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final KichCoDoAn other = (KichCoDoAn) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
}

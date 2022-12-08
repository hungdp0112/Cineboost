/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author intfs
 */
public class PhongChieu {
     private String id;
     private int soLuongDay;
     private int soLuongCot;
     private Set<SuatChieu> suatChieus = new HashSet<SuatChieu>(0);
     private Set<Ghe> gheNgois = new HashSet<Ghe>(0);

    public PhongChieu(String id, int soLuongDay, int soLuongCot) {
        this.id = id;
        this.soLuongDay = soLuongDay;
        this.soLuongCot = soLuongCot;
    }

    public PhongChieu() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSoLuongDay() {
        return soLuongDay;
    }

    public void setSoLuongDay(int soLuongDay) {
        this.soLuongDay = soLuongDay;
    }

    public int getSoLuongCot() {
        return soLuongCot;
    }

    public void setSoLuongCot(int soLuongCot) {
        this.soLuongCot = soLuongCot;
    }

    public Set<SuatChieu> getSuatChieus() {
        return suatChieus;
    }

    public void setSuatChieus(Set<SuatChieu> suatChieus) {
        this.suatChieus = suatChieus;
    }

    public Set<Ghe> getGheNgois() {
        return gheNgois;
    }

    public void setGheNgois(Set<Ghe> gheNgois) {
        this.gheNgois = gheNgois;
    }

    @Override
    public String toString() {
        return Integer.valueOf(id.substring(1,id.length())) + "";
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final PhongChieu other = (PhongChieu) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
     
     
    
}

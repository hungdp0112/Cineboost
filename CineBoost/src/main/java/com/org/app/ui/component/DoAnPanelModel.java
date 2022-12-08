/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

/**
 *
 * @author DELL
 */
public class DoAnPanelModel {
    private Integer id_kcda;;
    private String tenDA;
    private String id_loaiDA;
    private String anh;
    private String kichCo;
    private Double gia;
    
    public DoAnPanelModel() {
        tenDA = "DA00000";
        anh = "Unknown";
        kichCo = "Unknown";
        gia = 0.0;
        setAnh();
    }

    public DoAnPanelModel(Integer id_kcda, String tenDA, String id_loaiDA, String kichCo, Double gia) {
        this.id_kcda = id_kcda;
        this.tenDA = tenDA;
        this.id_loaiDA = id_loaiDA;
        this.kichCo = kichCo;
        this.gia = gia;
        setAnh();
    }
    
    public Integer getId_kcda() {
        return id_kcda;
    }

    public void setId_kcda(Integer id_kcda) {
        this.id_kcda = id_kcda;
    }

    public String getId_loaiDA() {
        return id_loaiDA;
    }

    public void setId_loaiDA(String id_loaiDA) {
        this.id_loaiDA = id_loaiDA;
    }

    public String getTenDA() {
        return tenDA;
    }

    public void setTenDA(String tenDA) {
        this.tenDA = tenDA;
    }

    public String getAnh() {
        return anh;
    }
    
    public void setAnh() {
        if (id_loaiDA.equals("DU")) {
            this.anh = "douong_loai_icon.png";
        }else{
            this.anh = "doan_loai_icon.png";
        }
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }
    
    
}

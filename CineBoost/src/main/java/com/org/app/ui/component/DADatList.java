/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.controller.KichCoDoAnDao;
import com.org.app.entity.DoAnChiTiet;
import com.org.app.entity.KichCoDoAn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author DELL
 */
public class DADatList {
    LinkedHashMap<Integer, Integer> selectedDoAns; // id, số lượng đã chọn
    HashMap<Integer, KichCoDoAn> mp;
    KichCoDoAnDao kcdaDao = new KichCoDoAnDao();
    
    public Integer getIdOf(Integer id){
        return mp.get(id).getId();
    }
    
    public String getTenOf(Integer id){
        return mp.get(id).getDoAn().getTen();
    }
    
    public String getKichCoOf(Integer id){
        return mp.get(id).getKichco().getId();
    }
    
    public Double getGiaOf(Integer id){
        return mp.get(id).getGia();
    }
    
    public String getLoaiDAOf(Integer id){
        return mp.get(id).getDoAn().getLoaiDoAn().getId();
    }
       
    public Integer getSoLuongOf(Integer id_kcda){
        return selectedDoAns.get(id_kcda);
    }
    
    public DADatList() {
    }

    public DADatList(LinkedHashMap<Integer, Integer> selectedDoAns, HashMap<Integer, KichCoDoAn> mp) {
        this.selectedDoAns = selectedDoAns;
        this.mp = mp;
    }

    public LinkedHashMap<Integer, Integer> getSelectedDoAns() {
        return selectedDoAns;
    }

    public void setSelectedDoAns(LinkedHashMap<Integer, Integer> selectedDoAns) {
        this.selectedDoAns = selectedDoAns;
    }

    public HashMap<Integer, KichCoDoAn> getMp() {
        return mp;
    }

    public void setMp(HashMap<Integer, KichCoDoAn> mp) {
        this.mp = mp;
    }
    
    public void putKCDA(Integer id_kcda, KichCoDoAn kcda){
        mp.put(id_kcda, kcda);
    }
    
    public void putSeletedList(Integer id_kcda, Integer soLuongDADC){
        selectedDoAns.put(id_kcda, soLuongDADC);
    }
    
    public Set<Integer> getIdKCDAs(){
        return selectedDoAns.keySet();
    }
    
    public int getSoLuong(){
        return mp.size();
//        return tong;
    }
    
    public int getTongSoLuong() {
        int tong = 0;
        if(mp.size() == 0)return 0;
        for(Integer sl : selectedDoAns.values()) {
            tong += sl;
        }
        return tong;
    }
    
    // check đồ ăn đã có trong vỏ chưa.
    public void addDoAn(KichCoDoAn kcda, Integer soLuongDA){
        int id_kcda = kcda.getId();
        
        if (mp.containsKey(id_kcda)) {
            soLuongDA = getSoLuongOf(id_kcda) + 1;
            putSeletedList(id_kcda, soLuongDA);
        }else{
            putKCDA(id_kcda, kcda);
            putSeletedList(id_kcda, (soLuongDA + 1));
        }
    }
    
    public void add(KichCoDoAn kcda){
        int soLuongDADC = 0;
        addDoAn(kcda, soLuongDADC);
    }
     
    // update Đồ ăn
    public void update(KichCoDoAn kcda, Integer soLuongChanged, String kichCoChanged, KichCoDoAn kcdaBD){
        int id_kcda = kcda.getId();
        int tongSLMoi = soLuongChanged;
        System.out.println("idKCDA moi " + id_kcda);
        System.out.println("idKCDA cu "+ kcdaBD.getId());
       if(kichCoChanged.equals(kcdaBD.getKichco().getId()) && Integer.compare(kcda.getId(), kcdaBD.getId()) != 0) {
           System.out.println("change sl only");
           int slCu = selectedDoAns.get(kcda.getId());
           System.out.println("slCu "+ slCu);
           tongSLMoi += slCu;
           System.out.println("tongSL moi: "+ tongSLMoi);
           System.out.println("kcdda " + kcda.getId());
//           selectedDoAns.put(id_kcda, tongSLMoi);
           selectedDoAns.replace(id_kcda, tongSLMoi);
           remove(kcdaBD);
       }else {
            remove(kcdaBD);
            putKCDA(id_kcda, kcda);
            putSeletedList(id_kcda, soLuongChanged);
       }
        System.out.println("soLuongChaged " + soLuongChanged);
//        mp.put(soLuongChanged, kcda)
//        selectedDoAns.put(kcda.getId(), soLuongChanged);
    }
    
    // remove Đò ăn
    public void remove(KichCoDoAn kcda){
        int id_kcda = kcda.getId();
        // xóa đồ ăn
        mp.remove(id_kcda);
        // xóa số lượng
        selectedDoAns.remove(id_kcda);
    }
    
    private Double getGia(KichCoDoAn kcda) {
        return kcda.getGia();
    }
        
    private int getSoLuong(KichCoDoAn kcda) {
        return selectedDoAns.get(kcda.getId());
    }
    
    // clear mp, selectedDAs
    public void clear(){
        mp.clear();
        selectedDoAns.clear();
    }
    
    // tinh tổng tiền đồ ăn
    public double getTongTienTA(){
        double tongtien = 0.0;
        
        Set<Integer> keySet = getIdKCDAs();
        for (Integer key: keySet) {
            KichCoDoAn kcda = mp.get(key);
            int id_kcda = kcda.getId();
            System.out.println("id " + id_kcda);
            if (getLoaiDAOf(id_kcda).equals("TA")) {
//                tongtien = getSoLuongOf(id_kcda) * getGiaOf(id_kcda);
                tongtien += ( getSoLuongOf(id_kcda) * getGiaOf(id_kcda));
            }
        }
        return tongtien;
    }
    // tinh tổng tiền đồ uống
    public double getTongTienDU(){
        double tongtien = 0.0;
        
        Set<Integer> keySet = getIdKCDAs();
        for (Integer key: keySet) {
            KichCoDoAn kcda = mp.get(key);
            int id_kcda = kcda.getId();
            System.out.println("id " + id_kcda);
            if (getLoaiDAOf(id_kcda).equals("DU")) {
                tongtien += ( getSoLuongOf(id_kcda) * getGiaOf(id_kcda));
            }
        }
        return tongtien;    
    }
    
    // tính tổng tiền đồ ăn
    public double getTamTinh(){
        double tongtien = 0.0;
                
        Set<Integer> keySet = getIdKCDAs();
        for (Integer key: keySet) {
            KichCoDoAn kcda = mp.get(key);
            int id_kcda = kcda.getId();
            tongtien += ( getSoLuongOf(id_kcda) * getGiaOf(id_kcda));
        }
        return tongtien; 
    }
    
    private DoAnChiTiet getDADat(DoAnChiTiet dact) {
        int id_kcda = dact.getDoAn();
        String id_donda = dact.getDonDA();
        int soluong = dact.getSoLuong();
        //loaive, ghe, donve, suatchieu
        return new DoAnChiTiet(id_donda, soluong, id_kcda);
    }
    
    public List<DoAnChiTiet> toDACT() {
        List<DoAnChiTiet> dactlist = new ArrayList<>();
        Set<Integer> keySet = getIdKCDAs();
        for (Integer key: keySet) {
            String id_donda = "";
            int id_kcda = getIdOf(key);
            int soluong = getSoLuongOf(key);
            dactlist.add(getDADat(new DoAnChiTiet(id_donda, soluong, id_kcda)));
        }
        return dactlist;
    }
}

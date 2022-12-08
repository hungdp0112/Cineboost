/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.ui.component;

import com.org.app.entity.Ghe;
import com.org.app.entity.Phim;
import com.org.app.entity.SuatChieu;
import com.org.app.entity.VeDat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 *
 * @author intfs
 */
public class VeDatList {
    private Phim phim;
    private SuatChieu sc;
    private int size;
    private int adult_limit;
    private int child_limit;
    
    private int curr_adult;
    private int curr_child;
    
    private double phuThu = 0.0;
    List<GheLabel> listVe; 
    Stack<GheLabel> stackGheTE;
    Stack<GheLabel> stackGheNL;
    Map<String, Double> loaiVeMap;
    
    public final String LOAI_VE_TE = "TE";
    public final String LOAI_VE_NL = "NL";
    public double GIA_GHE_TE = 0.0;
    public double GIA_GHE_NL = 0.0;
    
    public VeDatList() {
        listVe = new ArrayList<>();
        stackGheNL = new Stack<>();
        stackGheTE = new Stack<>();
        loaiVeMap = new HashMap<>();
    }
    
    public VeDatList(Map<String, Double> vdmap) {
        this();
        loaiVeMap = vdmap;
    }
   
    public VeDatList(Phim p, SuatChieu sc) {
        this();
        phim = p;
        this.sc = sc;
    }
    
    public void setSuatAndPhim(Phim p, SuatChieu sc) {
        phim = p;
        this.sc = sc;
    }

    public VeDatList(int adult_limit, int child_limit, int curr_adult, int curr_child) {
        this();
        this.adult_limit = adult_limit;
        this.child_limit = child_limit;
        
        this.curr_adult = curr_adult;
        this.curr_child = curr_child;
    }
    
    
    public boolean isBlank() {
        return adult_limit == 0 && child_limit == 0;
    }
    
    
    public void put(GheLabel ghe) {
        addAs(ghe);
        
        System.out.println("change to selected\ngheList in vedatList = "+ this.getListVe());
        System.out.println("current : "+ geListtLimit());
    }
    
    public void addAs(GheLabel ghe) {
        if(isFull()) return;
        if(adult_limit > 0 && curr_adult < adult_limit) {
            ghe.setLoaiVe(GheLabel.ADULT);
        }else if(child_limit > 0 && curr_child < child_limit) {
            ghe.setLoaiVe(GheLabel.CHILDREN);
        }
        System.out.println("adding...");
        increase(ghe.getLoaiVe());
        stackPut(ghe);
        listVe.add(ghe);
        ghe.setIsSelected(!ghe.isIsSelected());
    }
    
    public void stackPut(GheLabel ghe) {
        if(Boolean.valueOf(ghe.getLoaiVe()).equals(GheLabel.ADULT)) {
            System.out.println("add adult stack");
            stackGheNL.push(ghe);
        }else{
            System.out.println("add adult stack");
            stackGheTE.push(ghe);
        }
        
        System.out.println("stack "+"tre :" + stackGheTE.toString() +" nl: "+ stackGheNL);
    }
    
    
    
    public void remove(GheLabel ghe) {
        listVe.remove(ghe);
        decrease(ghe.getLoaiVe());
        ghe.setIsSelected(!ghe.isIsSelected());
    }
    
    

    public int getSize() {
        this.size = this.size = adult_limit + child_limit;
        return size;
    }

    public void increase(boolean isNL) {
        if(isNL) curr_adult++;
        else curr_child++;
    }
    
    private void decrease(boolean isNL) {
        if(isNL) curr_adult--;
        else curr_child--;
    }
   
    public boolean isFull() {
        return getCurrentSize() == getSize();
    }
    
    public int getCurrentSize() {
        return curr_adult + curr_child;
    }

    public int getAdult_limit() {
        return adult_limit;
    }
    public void setLimit(boolean isVeTreEm, int newLimit) {
        if(isVeTreEm) setChild_limit(newLimit);
        else setAdult_limit(newLimit);
    }
    public void setAdult_limit(int newAdultLimit) {
        System.out.println("set new ADULT LIMT");
        if(this.adult_limit > newAdultLimit) {
            System.out.println("must reduce NL");
            reduceLimit(true, newAdultLimit);
        } 
        this.adult_limit = newAdultLimit;
        
    }

    
    public int getChild_limit() {
        return child_limit;
    }

    public void setChild_limit(int newChildLimit) {
        System.out.println("set new ADULT LIMT");
         if(this.child_limit > newChildLimit) {
            System.out.println("must reduce TE");
            reduceLimit(false, newChildLimit);
        } 
        
        this.child_limit = newChildLimit;
      
    }

    public void reduceLimit(boolean isNL, int newLimit) {
        int range = isNL? (curr_adult - newLimit) : (curr_child - newLimit);
        Stack<GheLabel> stack = isNL? stackGheNL : stackGheTE;
        
        releaseStack(stack, range);

    }
    
    private void releaseStack(Stack<GheLabel> s, int times) {
        System.out.println("range = "+ times);
        for(int i = 0; i < times; i++) {
            GheLabel g = s.pop();
            System.out.println("ghe pop = "+g);
            remove(g);
        }
    }
    
    public int getLuongVeNL() {
        return getAdult_limit();
    }
    public int getLuongVeTE() {
        return getCurr_child();
    }
    
    private int getCurr_adult() {
        return curr_adult;
    }

    private void setCurr_adult(int curr_adult) {
        this.curr_adult = curr_adult;
    }

    private int getCurr_child() {
        return curr_child;
    }

    public void increaseCurrentChild() {
        this.curr_child++;
    }
    
    public void increaseCurrentAdult() {
        this.curr_adult++;
    }
    
    
    private void setCurr_child(int curr_child) {
        this.curr_child = curr_child;
    }

    public List<GheLabel> getListVe() {
        return listVe;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public SuatChieu getSc() {
        return sc;
    }

    public void setSc(SuatChieu sc) {
        this.sc = sc;
    }
    
    
    
    public String geListtLimit() {
        return String.format("Size: %d - adult: %d - child: %d\nCurrent : %d",
                getSize(), getAdult_limit(), getChild_limit(),getCurrentSize());
    }
    
    public Integer[] getLuongVeExpectedInfo() {
       return new Integer[] {getAdult_limit(), getChild_limit(), getSize()}; 
    }
    
    public Integer[] getLuongVeCurrentInfo() {
        return new Integer[] {getCurr_adult(), getCurr_child(), getCurrentSize()}; 
    }
    
    public int countVeVip() {
        return Integer.parseInt(listVe.stream().filter(g -> g.getGhe().isVip()).count()+"");
    }
    public void clear() {
//        adult_limit = 0;
//        child_limit = 0;
        
        curr_child = 0;
        curr_adult = 0;

        
        stackGheNL.clear();
        stackGheTE.clear();
        listVe.clear();
    }
    
    public void checkIsAllSelected() throws RuntimeException{
        if((curr_adult != adult_limit) && (curr_child != child_limit)) throw new RuntimeException("Chưa chọn đủ số vé");
        if(curr_adult != adult_limit) throw new RuntimeException("Chưa chọn đủ số vé người lớn");
        if(curr_child!= child_limit) throw new RuntimeException("Chưa chọn đủ số vé người trẻ em");
    }
    
    private VeDat getVeDat(GheLabel ghe) {
        String lv = ghe.toStringLoaiVe();
        String g = ghe.getGhe().getId();
        String dv = "";
        String sc = this.sc.getId();
        //loaive, ghe, donve, suatchieu
        return new VeDat(lv, g, dv, sc);
    }
    
    public List<GheLabel> toListGheLabel(Stack<GheLabel> stack) {
        return stack.stream().collect(Collectors.toList());
    }
    
   
    public List<VeDat> toVeDat() {
        List<VeDat> vdl = new ArrayList<>();
        System.out.println("to Ve dat");
        for(GheLabel gl : listVe) {
            vdl.add(getVeDat(gl));
//            gl.toString();
            System.out.println("-------------------");
        }
        return vdl;
    }
    
    public int getSoLuong() {
        return getSize();
    }
    public Double[] getDetailPrice() {
        Double totals[] = new Double[3];
        //tong 
        totals[2] = getToTalPrice(true) + getToTalPrice(false);
        //tinh tong ve tre em
        totals[0] = GIA_GHE_TE;
        //tinh tong ve nguoi lon
        totals[1] = GIA_GHE_NL;
        return totals;
    }
    
    
    
    public double getToTalOf(boolean isVeTreEm) {
        double total = getToTalPrice(isVeTreEm);
        return total;
    }
    
    public void setGiaGhe(boolean isChildren, double gia) {
        if(isChildren) GIA_GHE_TE = gia;
        else GIA_GHE_NL = gia;
    }
    
    private void addPhuThu(double v) {
        phuThu += v;
    }
    public double getTongPhuThu() {
        return phuThu;
    }
    
    public List<GheLabel> getListGheTE() {
        return toListGheLabel(stackGheTE);
    }
    
    public List<GheLabel> getListGheNL() {
        return toListGheLabel(stackGheNL);
    }
    
    public double getToTalPrice (boolean isChildren) {
        double tong = 0;
        List<GheLabel> glbs = isChildren? toListGheLabel(stackGheTE) : toListGheLabel(stackGheNL);
        if(glbs.isEmpty()) return tong; 
        double gia = isChildren? loaiVeMap.get(LOAI_VE_TE) : loaiVeMap.get(LOAI_VE_NL);
        System.out.println("gia = " + gia);
        setGiaGhe(isChildren, gia);
             for (GheLabel g : glbs) {
                 Ghe ghe = g.getGhe();
                 tong = tong + gia;
                 if(ghe.isVip()) {
                    double pt = ghe.getPhuThuVip();
                    tong += pt;
                    addPhuThu(pt);
                 }
        }

        return tong;
    }
    
  
    
    
}

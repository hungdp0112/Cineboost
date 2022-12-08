/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.GridLayout;

/**
 *
 * @author intfs
 */
public class CustomGridLayOut {
    GridLayout layout;
    public CustomGridLayOut(int total, int defaultColNum) {
        layout = new GridLayout(getRowNum(total, defaultColNum),defaultColNum);
    }
    
    public CustomGridLayOut(int total, int defaultColNum, int hGap, int vGap) {
        this(total,defaultColNum);
        getLayOut().setVgap(vGap);
        getLayOut().setHgap(hGap);
    }
    
    private int getRowNum(int total, int defaultCol) {
        if( total % defaultCol > 0) return total/defaultCol + 1;
        return total/defaultCol;
    }

    
    
    
    
    public GridLayout getLayOut() {     
        return layout;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import com.org.app.entity.Phim;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author intfs
 */
public class PhimComboRender extends DefaultListCellRenderer {

//  protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
//  private final static Dimension preferredSize = new Dimension(0, 20);

      protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
  public Component getListCellRendererComponent(JList list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
      
      Object item = value;
    if (item instanceof Phim) {
        Phim p =  (Phim) value;
      item = p.getTen() + "("+ (p.getTrangThai()? "ĐC" : "NC") +")";
    }
//    renderer.setPreferredSize(preferredSize);
      return super.getListCellRendererComponent( list, item, index, isSelected, cellHasFocus);
  }
}


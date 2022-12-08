/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author intfs
 */
public abstract class MouseClickListener extends MouseAdapter{
    @Override
    public abstract void mouseClicked(MouseEvent e);
}

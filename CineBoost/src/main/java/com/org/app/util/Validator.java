/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.util;

/**
 *
 * @author intfs
 */
public abstract class Validator<E> {
    public abstract void check(E e) throws ValidatorException;
    
    
}

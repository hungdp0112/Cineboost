/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author intfs
 */
public abstract class Dao<K,E> {
    public abstract void insert(E entity) throws Exception;
    public abstract void update(E entity) throws Exception;
    public abstract void delete(K value) throws Exception;
    public abstract E selectById(K value) throws Exception;
    public abstract List<E> selectAll() throws Exception;
    public abstract List<E> selectBySql(String sql, Object...args) throws Exception;
    public abstract E objectFromRs(ResultSet rs) throws Exception;
}

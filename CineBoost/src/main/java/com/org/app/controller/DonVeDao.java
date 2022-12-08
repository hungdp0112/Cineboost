/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.DonVe;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author intfs
 */
public class DonVeDao extends Dao<String, DonVe>{
    String INSERT_SQL = "INSERT INTO DONVE (ID_DONVE) VALUES (?)";
    String UPDATE_SQL = "UPDATE DONVE SET ID_DONVE = ? WHERE ID_DONVE = ?";
    String DELETE_SQL = "DELETE FROM DONVE WHERE ID_DONVE = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DONVE";
    String SELECT_BY_ID_SQL = "SELECT * FROM DONVE WHERE ID_DONVE = ?";

    @Override
    public void insert(DonVe entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getId());
    }

    @Override
    public void update(DonVe entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getId(), entity.getId());
        
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public DonVe selectById(String value) throws Exception {
        List<DonVe> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonVe> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<DonVe> selectBySql(String sql, Object... args) throws Exception {
        List<DonVe> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while (rs.next()) {
                list.add(objectFromRs(rs));
            }
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Lỗi truy suất dữ liệu");
        }finally {
            if(rs != null) {
                rs.getStatement().getConnection().close();
                if(!rs.isClosed()) {
                   rs.getStatement().close();
                   rs.close();
               }
            } 
        }
        return list;
    }

    @Override
    public DonVe objectFromRs(ResultSet rs) throws Exception {
        return new DonVe(rs.getString("ID_DONVE"),
                rs.getInt("TONGGHE"),
                rs.getDouble("TAMTINH")
        );
    }
    
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args) throws Exception{
        ResultSet rs = null;
        try {
            List<Object[]> list = new ArrayList<>();
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while (rs.next()) {                
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            return list;
        }catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Lỗi truy suất dữ liệu");
        }finally {
            if(rs != null) {
                rs.getStatement().getConnection().close();
                if(!rs.isClosed()) {
                   rs.getStatement().close();
                   rs.close();
               }
            } 
        }
    }
    
    public List<Object[]> getThongTinDonVe(int id_dontt) throws Exception{
        String sql = "{CALL sp_donVe(?)}";
        String[] cols = {"TEN", "SUATCHIEU", "ID_LOAIVE", "GIA"};
        return this.getListOfArray(sql, cols, id_dontt);
    }
    
}
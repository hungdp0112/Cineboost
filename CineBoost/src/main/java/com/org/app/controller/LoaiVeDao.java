/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.LoaiVe;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author intfs
 */
public class LoaiVeDao extends Dao<String, LoaiVe> {
    private String selectAll = "SELECT * FROM LOAIVE";
    private String selecById = "SELECT * FROM LOAIVE WHERE ID_LOAIVE = ?";
    private String INSERT = "";
    private String UPDATE = "";
    private String DELETE = "";
    
    @Override
    public void insert(LoaiVe entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(LoaiVe entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(String value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public LoaiVe selectById(String value) throws Exception {
         List<LoaiVe> l =  selectBySql(selecById, value);
        return l.size() == 0? null : l.get(0);
    }
    
    @Override
    public List<LoaiVe> selectAll() throws Exception {
        return selectBySql(selectAll);
    }
    
    @Override
    public List<LoaiVe> selectBySql(String sql, Object... args) throws Exception {
        List<LoaiVe> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while (rs.next()) {
                list.add(objectFromRs(rs));
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
    
    @Override
    public LoaiVe objectFromRs(ResultSet rs) throws Exception {
        return new LoaiVe(rs.getString("ID_LOAIVE"),
                rs.getString("TEN"),
                rs.getDouble("GIA"));
    }
    
    public Map<String, Double> getMapLoaiVe() throws Exception {
        List<LoaiVe> l = selectAll();
        return l.stream().collect(toMap(LoaiVe::getId, LoaiVe::getGia));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;
import com.org.app.entity.KichCo;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author intfs
 */
public class KichCoDao extends Dao<String, KichCo>{
    String INSERT_SQL = "INSERT INTO KICHCO (ID) VALUES (?)";
    String UPDATE_SQL = "UPDATE KICHCO SET ID =? WHERE ID=?";
    String DELETE_SQL = "DELETE FROM KICHCO WHERE ID = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KICHCO";
    String SELECT_BY_ID_SQL = "SELECT * FROM KICHCO WHERE ID = ?";

    @Override
    public void insert(KichCo entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getId());
    }

    @Override
    public void update(KichCo entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getId(), entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public KichCo selectById(String value) throws Exception {
        List<KichCo> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KichCo> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<KichCo> selectBySql(String sql, Object... args) throws Exception {
        List<KichCo> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while (rs.next()) {
                list.add(objectFromRs(rs));
            }
//            rs.getStatement().close();
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
    public KichCo objectFromRs(ResultSet rs) throws Exception {
        return new KichCo(rs.getString("ID"));
    }
    
}

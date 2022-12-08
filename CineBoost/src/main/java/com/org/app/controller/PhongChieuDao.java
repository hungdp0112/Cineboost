/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.PhongChieu;
import com.org.app.helper.JDBCHelper;
import com.org.app.util.ValueOfInQuerryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author intfs
 */
public class PhongChieuDao extends Dao<String, PhongChieu> {

    String INSERT_SQL = "INSERT INTO PHONGCHIEU (SOLUONGDAY, SOLUONGCOT) VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE PHONGCHIEU SET SOLUONGDAY = ?, SOLUONGCOT = ? WHERE ID_PHONG = ?";
    String DELETE_SQL = "DELETE FROM PHONGCHIEU WHERE ID_PHONG = ?";
    String SELECT_ALL_SQL = "SELECT * FROM PHONGCHIEU ORDER BY ID_PHONG";
    String SELECT_BY_ID_SQL = "SELECT * FROM PHONGCHIEU WHERE ID_PHONG = ?";
    String SELECT_BY_MUL_ID = "SELECT * FROM PHONGCHIEU WHERE ID_PHONG IN (?)";

    @Override
    public void insert(PhongChieu entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getId(), entity.getSoLuongDay(), entity.getSoLuongCot());
    }

    @Override
    public void update(PhongChieu entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getSoLuongDay(), entity.getSoLuongCot());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);

    }

    @Override
    public PhongChieu selectById(String value) throws Exception {
        List<PhongChieu> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<PhongChieu> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    public Map<String, PhongChieu> selectToMap(List<PhongChieu> phongs) throws Exception {
        LinkedHashMap<String, PhongChieu> map = new LinkedHashMap<>();
        for(PhongChieu p : phongs) {
            map.put(p.getId(), p);
        }
        return map;
    }
    
    @Override
    public List<PhongChieu> selectBySql(String sql, Object... args) throws Exception {
        List<PhongChieu> list = new ArrayList<PhongChieu>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while (rs.next()) {           
                list.add(objectFromRs(rs));
            }
//            rs.getStatement().close();
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
    public PhongChieu objectFromRs(ResultSet rs) throws Exception {
        return new PhongChieu(
                rs.getString("ID_PHONG"),
                rs.getInt("soLuongDay"),
                rs.getInt("soLuongCot")
        );
    }
    
    public List<PhongChieu> selectbyMulIds(String...args) throws Exception {
        List<PhongChieu> l = new ArrayList<>();
        String sql = ValueOfInQuerryBuilder.any(SELECT_BY_MUL_ID, args.length);
        return selectBySql(sql, args);
    }

}

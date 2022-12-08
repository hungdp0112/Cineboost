/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.ThanhVien;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.parser.Entity;

/**
 *
 * @author DELL
 */
public class ThanhVienDao extends Dao<String, ThanhVien>{
    String INSERT_SQL = "INSERT INTO THANHVIEN (HOTEN, NGAYSINH, GIOITINH, SODT, EMAIL) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE THANHVIEN SET HOTEN = ?, NGAYSINH = ?, GIOITINH = ?, SODT = ?, EMAIL = ? WHERE ID_TV = ?";
    String DELETE_SQL = "DELETE FROM THANHVIEN WHERE ID_TV = ?";
    String SELECT_ALL_SQL = "SELECT * FROM THANHVIEN";
    String SELECT_BY_ID_SQL = "SELECT * FROM THANHVIEN WHERE ID_TV = ?";

    @Override
    public void insert(ThanhVien entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getHoTen(), entity.getNgaySinh(), 
                                                entity.getGioiTinh(), entity.getSoDT(), entity.getEmail());
    }

    @Override
    public void update(ThanhVien entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getHoTen(), entity.getNgaySinh(), 
                                                entity.getGioiTinh(), entity.getSoDT(), entity.getEmail(), entity.getID());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public ThanhVien selectById(String value) throws Exception {
        List<ThanhVien> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThanhVien> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<ThanhVien> selectBySql(String sql, Object... args) throws Exception {
        List<ThanhVien> list = new ArrayList<ThanhVien>();
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
    public ThanhVien objectFromRs(ResultSet rs) throws Exception {
        return new ThanhVien(
        rs.getString("id_tv"),
        rs.getString("hoten"),
        rs.getDate("ngaysinh"),
        rs.getBoolean("gioitinh"),
        rs.getString("soDT"),
        rs.getString("email"),
        rs.getString("makh")
        );
    }
   
    public List<ThanhVien> selectbyKeyword(String keyword, String keyword1) throws Exception {
        String sql = "SELECT *FROM THANHVIEN WHERE HOTEN LIKE ? ";
        return this.selectBySql(sql, "%" + keyword + "%");
    }

    public List<ThanhVien> selectNotinCourse(int makh, String keyword) throws Exception {
        String sql = "SELECT *FROM THANHVIEN WHERE HOTEN LIKE ? AND ID_TV NOT IN "
                + "(SELECT ID_TV FROM THANHVIEN WHERE ID_TV = ?)";
        return this.selectBySql(sql, "%" + keyword + "%", makh);
    }
    
    
}

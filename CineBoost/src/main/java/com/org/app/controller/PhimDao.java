/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.NhanVien;
import com.org.app.entity.Phim;
import com.org.app.helper.JDBCHelper;
import com.org.app.util.ValueOfInQuerryBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author intfs
 */
public class PhimDao extends Dao<String, Phim> {
    private String SELECT_ALL = "SELECT * FROM PHIM";
    private String SELECT_BY_ID = "SELECT * FROM PHIM WHERE ID_PHIM = ?";
    private String INSERT = "INSERT INTO PHIM (TEN,POSTER,THOILUONG,THELOAI,NGONNGU,NGAY_KC,TOMTAT,TRANGTHAI,ID_NV) VALUES(?,?,?,?,?,?,?,?,?)";
    private String UPDATE = "UPDATE PHIM SET TEN=?, POSTER=?, THOILUONG=?, THELOAI=?, NGONNGU=?, NGAY_KC=?,TOMTAT=?,TRANGTHAI=?,ID_NV = ? WHERE ID_PHIM=?";
    private String DELETE = "DELETE FROM PHIM WHERE ID_PHIM=?";
    private String SELECT_BY_MUL_IDS = "SELECT * FROM PHIM WHERE ID_PHIM IN(?)";
    private String SELECT_BY_MUL_SC = " SELECT DISTINCT P.* FROM PHIM P JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM " +
                                        "WHERE SC.ID_SUAT IN (?) ";
    private String SELECT_AVAILABLE_PHIM = "SELECT * FROM PHIM WHERE TRANGTHAI = 1";

    @Override
    public void insert(Phim entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT,
                entity.getTen(), entity.getPoster(), entity.getThoiLuong(), entity.getTheloai(), entity.getNgonNgu(), entity.getNgayKhoiChieu(), entity.getTomTat(), entity.getTrangThai(), entity.getNv());
    }

    @Override
    public void update(Phim entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE,
                entity.getTen(), entity.getPoster(), entity.getThoiLuong(), entity.getTheloai(), entity.getNgonNgu(), entity.getNgayKhoiChieu(), entity.getTomTat(), entity.getTrangThai(), entity.getNv(), entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE, value);
    }

    @Override
    public Phim selectById(String value) throws Exception {
        List<Phim> l = selectBySql(SELECT_BY_ID, value);
        return l.size() == 0 ? null : l.get(0);
    }

    @Override
    public List<Phim> selectAll() throws Exception {
        return selectBySql(SELECT_ALL);
    }

    @Override
    public List<Phim> selectBySql(String sql, Object... args) throws Exception {
        List<Phim> list = new ArrayList<>();
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
    public Phim objectFromRs(ResultSet rs) throws Exception {
        return new Phim(
                rs.getString("ID_PHIM"),
                rs.getString("TEN"),
                rs.getString("POSTER"),
                rs.getInt("THOILUONG"),
                rs.getString("THELOAI"),
                rs.getString("NGONNGU"),
                rs.getDate("NGAY_KC"),
                rs.getString("TOMTAT"),
                rs.getBoolean("TRANGTHAI"),
                rs.getString("ID_NV")
        );
    }
    
    public List<Phim> selectByIds(String...args) throws Exception {
        String sql = ValueOfInQuerryBuilder.any(SELECT_BY_MUL_IDS, args.length);
        System.out.println("ids = " + sql);
        return selectBySql(sql, args);
    }
    
    public List<Phim> selectBySuatChieu(String...args) throws Exception {
        if(args.length == 0) return null;
        String sql = ValueOfInQuerryBuilder.any(SELECT_BY_MUL_SC, args.length);         
        return selectBySql(sql, args);
    }
    
    public List<Phim> selectAllAvailable() throws Exception {
        return selectBySql(SELECT_AVAILABLE_PHIM);
    }

}

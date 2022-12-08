/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.NhanVien;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author intfs
 */
public class NhanVienDao extends Dao<String, NhanVien> {

    private String selectAll = "SELECT * FROM NHANVIEN";
    private String selecById = "SELECT * FROM NHANVIEN WHERE ID_NV = ?";
    private String SELECT_BY_USER_NAME = "SELECT * FROM NHANVIEN WHERE TENTK = ?";
    private String INSERT = "INSERT INTO NHANVIEN (HOTEN,NGAYSINH,GIOITINH,SODT,EMAIL,TENTK,MATKHAU,ANH,VAITRO) VALUES(?,?,?,?,?,?,?,?,?)";
    private String UPDATE = "UPDATE NHANVIEN SET HOTEN=?, NGAYSINH=?, GIOITINH=?, SODT=?, EMAIL=?, TENTK=?,MATKHAU=?,ANH=?,VAITRO=? WHERE ID_NV=?";
    private String DELETE = "DELETE FROM NHANVIEN WHERE ID_NV=?";
    private String SELECT_BY_EMAIL = "SELECT * FROM NHANVIEN WHERE EMAIL = ?";
    
    @Override
    public void insert(NhanVien entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT, 
            entity.getHoten(),entity.getNgaysinh(),entity.getGioitinh(),entity.getSodt(),entity.getEmail(),entity.getTentk(), entity.getMatkhau(),entity.getAnh(),entity.getVaitro());
    }

    @Override
    public void update(NhanVien entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE, 
                entity.getHoten(),entity.getNgaysinh(),entity.getGioitinh(),entity.getSodt(),entity.getEmail(),entity.getTentk(), entity.getMatkhau(),entity.getAnh(),entity.getVaitro(),entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE, value);
    }

    @Override
    public NhanVien selectById(String value) throws Exception {
        List<NhanVien> l =  selectBySql(selecById, value);
        return l.size() == 0? null : l.get(0);
    }

    @Override
    public List<NhanVien> selectAll() throws Exception {
        return selectBySql(selectAll);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) throws Exception {
        List<NhanVien> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while(rs.next()) {
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
    public NhanVien objectFromRs(ResultSet rs) throws Exception {
        return new NhanVien(rs.getString("ID_NV"),
                        rs.getString("HOTEN"),
                        rs.getDate("NGAYSINH"),
                        rs.getBoolean("GIOITINH"),
                        rs.getString("SODT"),
                        rs.getString("EMAIL"),
                        rs.getBoolean("VAITRO"),
                        rs.getString("ANH"),
                        rs.getString("TENTK"),
                        rs.getString("MATKHAU")
                        );
    }
    
    public NhanVien selectByEmail(String value) throws Exception {
        List<NhanVien> l =  selectBySql(SELECT_BY_EMAIL, value);
        return l.size() == 0? null : l.get(0);
        
    }
    
     public NhanVien selectByUserName(String value) throws Exception {
        List<NhanVien> l =  selectBySql(SELECT_BY_USER_NAME, value);
        return l.size() == 0? null : l.get(0);
        
    }
}

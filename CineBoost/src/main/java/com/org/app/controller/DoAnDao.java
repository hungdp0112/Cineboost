/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.DoAn;
import com.org.app.entity.LoaiDoAn;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author intfs
 */
public class DoAnDao extends Dao<String, DoAn>{
    String INSERT_SQL = "INSERT INTO DOAN (TEN, ID_LOAI) VALUES (?, ?)";
    String UPDATE_SQL = "UPDATE DOAN SET TEN = ?, ID_LOAI = ? WHERE ID_DOAN = ?";
    String DELETE_SQL = "DELETE FROM DOAN WHERE ID_DOAN = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DOAN";
    String SELECT_BY_ID_SQL = "SELECT DA.*, LDA.TEN FROM DOAN DA INNER JOIN LOAIDOAN LDA ON DA.ID_LOAI = LDA.ID WHERE ID_DOAN = ?";
    String SELECT_TENDA = "SELECT TEN FROM DOAN GROUP BY TEN";
    String SELECT_ID_DOAN = "SELECT ID_DOAN FROM DOAN WHERE TEN LIKE ?";
    String SELECT_LDA_DOAN = "select LOAIDOAN.TEN from DOAN join LOAIDOAN on DOAN.ID_LOAI = LOAIDOAN.ID where ID_DOAN like ?";
    
    @Override
    public void insert(DoAn entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getTen(), entity.getLoaiDoAn());
    }
    
    @Override
    public void update(DoAn entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getTen(), entity.getLoaiDoAn(), entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public DoAn selectById(String value) throws Exception {
        List<DoAn> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DoAn> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<DoAn> selectBySql(String sql, Object... args) throws Exception {
        List<DoAn> list = new ArrayList<DoAn>();
        ResultSet rs = JDBCHelper.getResultSetByQuery(sql, args);
        try {
            list.add(objectFromRs(rs));
//            rs.close();
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
    public DoAn objectFromRs(ResultSet rs) throws Exception {
        return new DoAn(
                    rs.getString("ID_DOAN"),
                    rs.getString("TEN"),
                    createLoaiDoAn(rs.getString("ID_LOAI"), rs.getString("TEN"))
                );
    }
    
    public LoaiDoAn createLoaiDoAn(String id_loai, String ten){
        return new LoaiDoAn(id_loai, ten);
    }
    
//    public List<Object[]> getQLDonThanhToan_KhachHang(String loaiKH){
//        String sql = "{CALL sp_donThanhToan_KhachHang(?)}";
//        String[] cols = {"ID_DONTT", "SUATCHIEU", "NGAYDAT", "ID_TV", "TONG"};
//        return this.getListOfArray(sql, cols, ); 
//    }
    public void insert_SP(String tenDA, String id_loaiDA, String id_kichCo, double gia) throws Exception {
        JDBCHelper.excecuteStatement("{CALL sp_themDoAn(?,?,?,?)}", tenDA, id_loaiDA, id_kichCo, gia);
    }
    
    public void update_SP(String tenDA, String id_loaiDA, String id_kichCo, double gia, String id_DoAn, int id_KCDA) throws Exception {
        JDBCHelper.excecuteStatement("{CALL sp_suaDoAn(?,?,?,?,?,?)}", tenDA, id_loaiDA, id_kichCo, gia, id_DoAn, id_KCDA);
    }
    
    public List<String> getTenDA() throws SQLException{
        List<String> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.getResultSetByQuery(SELECT_TENDA);
        while (rs.next()) {            
            String tenDA = rs.getString("TEN");
            list.add(tenDA);
        }
        
        return list;
    }
    
    public String getIdDoAn(String value){
        try {
            ResultSet rs = JDBCHelper.getResultSetByQuery(SELECT_ID_DOAN, value); 
            String id_DA = null;
            while (rs.next()) {                
                id_DA = rs.getString("ID_DOAN");
            }
            rs.getStatement().getConnection().close();
            return id_DA;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }   
    }
    
    public String getLoaiDoAn(String value){
        try {
            ResultSet rs = JDBCHelper.getResultSetByQuery(SELECT_LDA_DOAN, value); 
            String loaiDA = null;
            while (rs.next()) {                
                loaiDA = rs.getString("TEN");
            }
            rs.getStatement().getConnection().close();
            return loaiDA;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }   
    }
}

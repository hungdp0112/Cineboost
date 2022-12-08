/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.DonDoAn;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author intfs
 */
public class DonDoAnDao extends Dao<String, DonDoAn>{
    String INSERT_SQL = "INSERT INTO DONDOAN (ID_DONDA) VALUES (?)";
    String UPDATE_SQL = "UPDATE DONDOAN SET ID_DONDA WHERE ID_DONDA = ?";
    String DELETE_SQL = "DELETE FROM DONDOAN WHERE ID_DONDA = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DONDOAN";
    String SELECT_BY_ID_SQL = "SELECT * FROM DONDOAN WHERE ID_DONDA = ?";
    String SELECT_BY_SL_DU = "select count(ID_LOAI) as SOLUONGDU from DONTHANHTOAN DTT join DONDOAN DDA on DTT.ID_DONDA = DDA.ID_DONDA " +
                            "join DOANCT DACT on DDA.ID_DONDA = DACT.ID_DONDA " +
                            "join KICHCODOAN KCDA on KCDA.ID_KCDA = DACT.ID_KCDA " +
                            "join DOAN DA on DA.ID_DOAN = KCDA.ID_DOAN " +
                            "where ID_DONTT like ? and ID_LOAI like 'DU'";
    String SELECT_BY_SL_TA = "select count(ID_LOAI) as SOLUONGTA from DONTHANHTOAN DTT join DONDOAN DDA on DTT.ID_DONDA = DDA.ID_DONDA " +
                            "join DOANCT DACT on DDA.ID_DONDA = DACT.ID_DONDA " +
                            "join KICHCODOAN KCDA on KCDA.ID_KCDA = DACT.ID_KCDA " +
                            "join DOAN DA on DA.ID_DOAN = KCDA.ID_DOAN " +
                            "where ID_DONTT like ? and ID_LOAI like 'TA'";
    
    @Override
    public void insert(DonDoAn entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getId());
    }

    @Override
    public void update(DonDoAn entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getId(), entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public DonDoAn selectById(String value) throws Exception {
        List<DonDoAn> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonDoAn> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<DonDoAn> selectBySql(String sql, Object... args) throws SQLException  {
        List<DonDoAn> list = new ArrayList<DonDoAn>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            list.add(objectFromRs(rs));
//            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
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
    public DonDoAn objectFromRs(ResultSet rs) throws Exception {
        return new DonDoAn(
                    rs.getString("ID_DONDA"),
                    rs.getInt("SOLUONG"),
                    rs.getDouble("TAMTINH")
                );
    }
    
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args) throws SQLException{
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
//            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi truy suất dữ liệu");
        } finally {
             if(rs != null) {
                rs.getStatement().getConnection().close();
                if(!rs.isClosed()) {
                   rs.getStatement().close();
                   rs.close();
               }
            } 
        }
 
    }
    
    public List<Object[]> getThongTinDonDA(int id_dontt) throws SQLException{
        String sql = "{CALL sp_donDoAn(?)}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "TONGTIEN"};
        return this.getListOfArray(sql, cols, id_dontt);
    }
    
    public int getSoLuongDUBySQL(Integer value) throws SQLException{
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_BY_SL_DU, value); 
            int soLuongDU = 0;
            while (rs.next()) {                
                soLuongDU = rs.getInt("SOLUONGDU");
            }
//            rs.getStatement().getConnection().close();
            return soLuongDU;
        } catch (SQLException ex) {
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
    
    public int getSoLuongTABySQL(Integer value) throws SQLException{
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_BY_SL_TA, value); 
            int soLuongTA = 0;
            while (rs.next()) {                
                soLuongTA = rs.getInt("SOLUONGTA");
            }
//            rs.getStatement().getConnection().close();
            return soLuongTA;
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    
}

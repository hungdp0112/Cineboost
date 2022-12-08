/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.DoAn;
import com.org.app.entity.KichCo;
import com.org.app.entity.KichCoDoAn;
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
public class KichCoDoAnDao extends Dao<Integer, KichCoDoAn>{
    String INSERT_SQL = "INSERT INTO KICHCODOAN (ID_DOAN, ID_KICHCO, GIA) VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE KICHCODOAN SET ID_DOAN = ?, ID_KICHCO = ?, GIA=? WHERE ID_KCDA = ?";
    String DELETE_SQL = "DELETE FROM KICHCODOAN WHERE ID_KCDA = ?";
    String SELECT_ALL_SQL = "SELECT * FROM KICHCODOAN";
    String SELECT_BY_ID_SQL = "select ID_KCDA , DA.ID_DOAN, DA.TEN, ID_LOAI, LDA.TEN, ID_KICHCO, GIA " +
                                "from DOAN DA join KICHCODOAN KCDA on DA.ID_DOAN = KCDA.ID_DOAN join LOAIDOAN LDA on LDA.ID = DA.ID_LOAI " +
                                "where ID_KCDA = ?";
    String SELECT_GET_ID_SQL = "select ID_KCDA , DA.ID_DOAN, DA.TEN, ID_LOAI, LDA.TEN, ID_KICHCO, GIA " +
                                "from DOAN DA join KICHCODOAN KCDA on DA.ID_DOAN = KCDA.ID_DOAN join LOAIDOAN LDA on LDA.ID = DA.ID_LOAI " +
                                "where DA.TEN like ? and ID_KICHCO like ?";
    
    String SELECT_DOAN_SQL = "SELECT ID_KCDA, DOAN.ID_DOAN, DOAN.TEN, DOAN.ID_LOAI, LDA.TEN, ID_KICHCO, GIA FROM KICHCODOAN KCDA JOIN DOAN DOAN ON KCDA.ID_DOAN = DOAN.ID_DOAN JOIN LOAIDOAN LDA ON DOAN.ID_LOAI = LDA.ID ORDER BY ID_DOAN, ID_KICHCO DESC";
    
    String SELECT_DOAN_DU_SQL = "SELECT ID_KCDA, DOAN.ID_DOAN, DOAN.TEN, DOAN.ID_LOAI, LDA.TEN, ID_KICHCO, GIA " +
                                "FROM KICHCODOAN KCDA JOIN DOAN DOAN ON KCDA.ID_DOAN = DOAN.ID_DOAN " +
                                "					 JOIN LOAIDOAN LDA ON DOAN.ID_LOAI = LDA.ID " +
                                "WHERE ID_LOAI like 'DU' ORDER BY ID_DOAN, ID_KICHCO DESC";
    
    String SELECT_DOAN_TA_SQL = "SELECT ID_KCDA, DOAN.ID_DOAN, DOAN.TEN, DOAN.ID_LOAI, LDA.TEN, ID_KICHCO, GIA " +
                                "FROM KICHCODOAN KCDA JOIN DOAN DOAN ON KCDA.ID_DOAN = DOAN.ID_DOAN " +
                                "					 JOIN LOAIDOAN LDA ON DOAN.ID_LOAI = LDA.ID " +
                                "WHERE ID_LOAI like 'TA' ORDER BY ID_DOAN, ID_KICHCO DESC";
    
    String SELECT_MENU = "select ID_KCDA , DA.ID_DOAN, DA.TEN, ID_LOAI, LDA.TEN, ID_KICHCO, GIA " +
                            "from DOAN DA join KICHCODOAN KCDA on DA.ID_DOAN = KCDA.ID_DOAN join LOAIDOAN LDA on LDA.ID = DA.ID_LOAI " +
                            "where ID_LOAI like ?";
        
    @Override
    public void insert(KichCoDoAn entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL,entity.getDoAn(), entity.getKichco(), entity.getGia());
    }
    
    @Override
    public void update(KichCoDoAn entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL,entity.getDoAn(), entity.getKichco(), entity.getGia(), entity.getId());
    }
    
    @Override
    public void delete(Integer value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }
    
    @Override
    public KichCoDoAn selectById(Integer value) throws Exception {
        List<KichCoDoAn> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public KichCoDoAn selectByTenDA_KC(String tenDA, String kichCo) throws Exception {
        List<KichCoDoAn> list = this.selectBySql(SELECT_GET_ID_SQL, tenDA, kichCo);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KichCoDoAn> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }
    
    public List<KichCoDoAn> selectDoAnAll() throws Exception {
        return this.selectBySql(SELECT_DOAN_SQL);
    }
    
    public List<KichCoDoAn> selectMenu(String loaiDA) throws Exception {
        return this.selectBySql(SELECT_MENU, loaiDA);
    }
    
    public List<KichCoDoAn> selectDoAn_DU() throws Exception {
        return this.selectBySql(SELECT_DOAN_DU_SQL);
    }
    
    public List<KichCoDoAn> selectDoAn_TA() throws Exception {
        return this.selectBySql(SELECT_DOAN_TA_SQL);
    }
    
    @Override
    public List<KichCoDoAn> selectBySql(String sql, Object... args) throws SQLException, Exception {
        List<KichCoDoAn> list = new ArrayList<>();
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
    public KichCoDoAn objectFromRs(ResultSet rs) throws Exception {
        return new KichCoDoAn(
                rs.getInt("ID_KCDA"),
                createDoAn(rs.getString("ID_DOAN"), rs.getString("TEN"), 
                        new LoaiDoAn(rs.getString("ID_LOAI"), rs.getString("TEN"))),
                        new KichCo(rs.getString("ID_KICHCO")), 
                rs.getDouble("GIA")
        );
    }

    public DoAn createDoAn(String idDoAn, String ten, LoaiDoAn loaiDoAn){
        return new DoAn(idDoAn, ten, loaiDoAn);
    }
        
}

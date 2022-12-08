/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.DoAnChiTiet;
import com.org.app.entity.DonDoAn;
import com.org.app.entity.KichCo;
import com.org.app.entity.KichCoDoAn;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author intfs
 */
public class DoAnChiTietDao extends Dao<Integer, DoAnChiTiet>{
    String INSERT_SQL = "INSERT INTO DOANCT (ID_KCDA, ID_DONDA, SOLUONG) VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE DOANCT SET ID_KCDA = ?, ID_DONDA = ?, SOLUONG = ? WHERE ID_DOANCT = ?";
    String DELETE_SQL = "DELETE FROM DOANCT WHERE ID_DOANCT = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DOANCT";
    String SELECT_BY_ID_SQL = "SELECT DCT.*, DDA.SOLUONG, DDA.TAMTINH, KCDA.ID_KICHCO, KCDA.GIA FROM DOANCT DCT INNER JOIN DONDOAN "
            + "DDA ON DCT.ID_DONDA = DDA.ID_DONDA" +
"INNER JOIN KICHCODOAN KCDA ON KCDA.ID_KCDA = DCT.ID_KCDA WHERE ID_DOANCT = ?";
    
    @Override
    public void insert(DoAnChiTiet entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getDoAn(), entity.getDonDA(), entity.getSoLuong());
    }

    @Override
    public void update(DoAnChiTiet entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getDoAn(), entity.getDonDA(), entity.getSoLuong(), entity.getId());
    }

    @Override
    public void delete(Integer value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public DoAnChiTiet selectById(Integer value) throws Exception {
        List<DoAnChiTiet> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DoAnChiTiet> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<DoAnChiTiet> selectBySql(String sql, Object... args) throws Exception {
        List<DoAnChiTiet> list = new ArrayList<DoAnChiTiet>();
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            list.add(objectFromRs(rs));
//            rs.close();
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
    public DoAnChiTiet objectFromRs(ResultSet rs) throws Exception {
        return null;
    }
    
    /*
        List<DoAnChiTiet> getListDoAnChiTiet(){}
        List<DoAnChiTiet> list = new Arr
        dung keySet
            for(Integer k : selectedDoAn){
                list.add(new DoAnChiTiet(kcda_id, soluong));
            }
    
        public double tamTinh(){
            
        }
    */
    public DonDoAn createDonDoAn(String id_DonDA, int soLuong, double tamTinh){
        return new DonDoAn(id_DonDA, soLuong, tamTinh);
    }
    
    public KichCoDoAn createKichCoDoAn(int id_KichCoDoAn, String id_KichCo, double gia){
        return new KichCoDoAn(id_KichCoDoAn, createKichCo(id_KichCo), gia);
    }
    
    public KichCo createKichCo(String id){
        return new KichCo(id);
    }
}

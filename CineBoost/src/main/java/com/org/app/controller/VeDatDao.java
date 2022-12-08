/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.VeDat;
import com.org.app.helper.DateHelper;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intfs
 */
public class VeDatDao extends Dao<Integer, VeDat> {

    String INSERT_SQL = "INSERT INTO VEDAT (ID_LOAIVE, ID_GHE, ID_DONVE, ID_SUAT) VALUES (?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE VEDAT SET ID_LOAIVE = ?, ID_GHE = ?, ID_DONVE = ?, ID_SUAT = ? WHERE ID_VEDAT = ?";
    String DELETE_SQL = "DELETE FROM VEDAT WHERE ID_VEDAT = ?";
    String SELECT_ALL_SQL = "SELECT * FROM VEDAT";
    String SELECT_BY_ID_SQL = "SELECT * FROM VEDAT WHERE ID_VEDAT = ?";
    String SELECT_LUONG_VE_SUAT = "{CALL  SP_LUONGVEBANCUASUAT (?)}";
//    String SELECT_LUONG_VE_BAN_NGAY = "SELECT COUNT(ID_VEDAT) FROM DONTHANHTOAN dtt JOIN DONVE dv " +
//                                        "ON dv.ID_DONVE = dtt.ID_DONVE JOIN VEDAT vd " +
//                                        "ON vd.ID_DONVE = dv.ID_DONVE WHERE CAST(NGAYDAT AS DATE) = ?";
    String SELECT_LUONG_VE_BAN_NGAY = "SELECT count(ID_VEDAT)\n" +
                                        "FROM DONTHANHTOAN dtt JOIN DONVE dv" +
                                        " ON dv.ID_DONVE = dtt.ID_DONVE " +
                                        "JOIN VEDAT vd ON vd.ID_DONVE = dv.ID_DONVE " +
                                        "JOIN SUATCHIEU SC ON vd.ID_SUAT = SC.ID_SUAT " +
                                        "WHERE cast(ngaydat as date) =  ? or sc.NGAYCHIEU = ?";

    @Override
    public void insert(VeDat entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getLoaiVe(), entity.getGhe(), entity.getDonVe(),
                entity.getSuatChieu());
    }

    @Override
    public void update(VeDat entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getLoaiVe(), entity.getGhe(),
                entity.getDonVe(), entity.getSuatChieu());
    }

    @Override
    public void delete(Integer value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);

    }

    @Override
    public VeDat selectById(Integer value) throws Exception {
        List<VeDat> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<VeDat> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);

    }

    @Override
    public List<VeDat> selectBySql(String sql, Object... args) throws Exception {
        List<VeDat> list = new ArrayList<VeDat>();
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
/*[ID_VEDAT],[ID_LOAIVE],[ID_GHE],[ID_DONVE],[ID_SUAT]*/
    @Override
    public VeDat objectFromRs(ResultSet rs) throws Exception {
        return new VeDat(rs.getInt("ID_VEDAT"),
                         rs.getString("ID_LOAIVE"),
                         rs.getString("ID_GHE"),
                         rs.getString("ID_DONVE"),
                         rs.getString("ID_SUAT"));
    }

    public int getLuongVeOf(String id_suat) throws Exception {
        ResultSet rs = null;
        try{
            rs = JDBCHelper.getResultSetByQuery(SELECT_LUONG_VE_SUAT, id_suat);
            rs.next();
            return rs.getInt("LUONGVE");
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
    
    public int getLuongVeBanNgay(LocalDate date) throws Exception {
        Integer luongVe = 0;
        ResultSet rs = null;
        try {
            java.util.Date d = DateHelper.convertToDate(date);
            String df = DateHelper.toString(d, DateHelper.YYYYMMDD_FORMAT);
            System.out.println("df = "+ df);
            rs = JDBCHelper.getResultSetByQuery(SELECT_LUONG_VE_BAN_NGAY, df,df);
            while (rs.next()){
                luongVe =  rs.getInt(1);
            }
//            rs.close();
//            rs.getStatement().close();
            return luongVe == null? 0 : luongVe;    
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
}

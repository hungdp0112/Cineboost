/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.Ghe;
import com.org.app.entity.LoaiGhe;
import com.org.app.entity.PhongChieu;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
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
public class GheDao extends Dao<String, Ghe>{
    String INSERT_SQL = "INSERT INTO GHE(VITRIDAY, VITRICOT, ID_PHONGCHIEU, ID_LOAIGHE) VALUES (?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE GHE SET VITRIDAY = ?, VITRICOT = ?, ID_PHONGCHIEU=?, ID_LOAIGHE=?  WHERE ID_GHE = ?";
    String DELETE_SQL = "DELETE FROM GHE WHERE ID_GHE = ?";
    String SELECT_ALL_SQL = "SELECT * FROM GHE G JOIN PHONGCHIEU ON G.ID_PHONGCHIEU = PHONGCHIEU.ID_PHONG " +
                            "JOIN LOAIGHE LG ON G.ID_LOAIGHE = LG.ID_LOAIGHE";
    String SELECT_BY_ID_SQL = "SELECT * FROM GHE WHERE ID_GHE = ?";
    String SELECT_GHEPHONG_SQL ="SELECT * FROM GHE JOIN PHONGCHIEU ON GHE.ID_PHONGCHIEU = PHONGCHIEU.ID_PHONG";
    
    private String selectGheBySuat ="SELECT g.* FROM GHE g JOIN SUATCHIEU s ON s.ID_PHONG = g.ID_PHONGCHIEU = ?";
    private String selectByPhong = "SELECT * FROM GHE G JOIN PHONGCHIEU ON G.ID_PHONGCHIEU = PHONGCHIEU.ID_PHONG " +
                            "JOIN LOAIGHE LG ON G.ID_LOAIGHE = LG.ID_LOAIGHE WHERE ID_PHONGCHIEU = ?" ;
    
    private String SELECTED_BOOKED_GHE = "SELECT G.*, PC.*, LG.* FROM VEDAT " +
                                        "LEFT JOIN GHE G ON VEDAT.ID_GHE = G.ID_GHE " +
                                        "LEFT JOIN LOAIGHE LG ON LG.ID_LOAIGHE = G.ID_LOAIGHE " +
                                        "LEFT JOIN SUATCHIEU SC ON VEDAT.ID_SUAT = SC.ID_SUAT " +
                                        "LEFT JOIN PHONGCHIEU PC ON PC.ID_PHONG = SC.ID_PHONG WHERE SC.ID_SUAT = ?";
    //private String selectReserved = "SELECT * FROM ";

    @Override
    public void insert(Ghe entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getViTriDay(), entity.getViTriCot(), entity.getPhong(), entity.getLoaiGhe());
    }

    @Override
    public void update(Ghe entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getViTriDay(), entity.getViTriCot(), entity.getPhong(), entity.getLoaiGhe(), entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public Ghe selectById(String value) throws Exception {
        List<Ghe> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Ghe> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Ghe> selectBySql(String sql, Object... args) throws Exception {
        List<Ghe> list = new ArrayList<>();
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
    public Ghe objectFromRs(ResultSet rs) throws Exception {
        return new Ghe(rs.getString("ID_GHE"),
                rs.getString("VITRIDAY"),
                rs.getString("VITRICOT"),
                createPhongChieu(rs.getString("ID_PHONGCHIEU"), rs.getInt("SOLUONGDAY"), rs.getInt("SOLUONGCOT")),
                createLoaiGhe(rs.getString("ID_LOAIGHE"), rs.getDouble("PHUTHU"), rs.getString("TENLOAI"))
        );
    }
    
    public PhongChieu createPhongChieu(String id, int slDay, int slCot){
        return new PhongChieu(id, slDay, slCot);
    }
    
    public LoaiGhe createLoaiGhe(String id, double gia, String tenLoai){
        return new LoaiGhe(id, gia, tenLoai);
    }
    
    public List<Ghe> selectGheOfPhong(String idPhong) throws Exception {
        return selectBySql(selectByPhong, idPhong);
    }
    
    public Map<String, Ghe> getMapGheOfPhong(String idPhong) throws Exception {
        List<Ghe> ghes = selectGheOfPhong(idPhong);
        LinkedHashMap<String, Ghe> map = new LinkedHashMap<>();
        for(Ghe p : ghes) {
            map.put(p.getId(), p);
        }
        return map;
    }
    
    public List<Ghe> selectGheDaDat(String idSuat) throws Exception {
        return selectBySql(SELECTED_BOOKED_GHE, idSuat);
        
    }
}

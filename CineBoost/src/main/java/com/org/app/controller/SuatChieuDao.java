/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.entity.SuatChieu;
import com.org.app.helper.DateHelper;
import com.org.app.helper.JDBCHelper;
import com.org.app.helper.TimeHelper;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intfs
 */
public class SuatChieuDao extends Dao<String, SuatChieu> {

    private String INSERT_SQL = "INSERT INTO SUATCHIEU (NGAYCHIEU, ID_PHONG, ID_PHIM, GIOBATDAU, GIOKETTHUC, ID_NV) VALUES (?, ?, ?, ?, ?,?)";
    private String UPDATE_SQL = "UPDATE SUATCHIEU SET NGAYCHIEU = ?, ID_PHONG = ?, ID_PHIM = ?, GIOBATDAU = ?, GIOKETTHUC = ?, ID_NV = ? WHERE ID_SUAT = ?";
    private String DELETE_SQL = "DELETE FROM SUATCHIEU WHERE ID_SUAT = ?";
    private String SELECT_ALL_SQL = "SELECT * FROM SUATCHIEU ORDER BY NGAYCHIEU, GIOBATDAU";
    private String SELECT_BY_ID_SQL = "SELECT * FROM SUATCHIEU WHERE ID_SUAT = ?";
    private String SELECT_WITH_DATETIME_AFTER = "SELECT * FROM SUATCHIEU WHERE NGAYCHIEU = ? AND GIOBATDAU > ? ORDER BY NGAYCHIEU, GIOBATDAU";
    private String SELECT_WITH_DATETIME_BEFORE = "SELECT * FROM SUATCHIEU WHERE NGAYCHIEU = ? AND GIOBATDAU < ? ORDER BY NGAYCHIEU, GIOBATDAU";
    private String SELECT_WITH_DATE = "SELECT * FROM SUATCHIEU WHERE NGAYCHIEU = ? ORDER BY GIOBATDAU";
    private String SELECT_TTDATVE = "{CALL SP_LICHCHIEUNGAY(?,?,?,?)}";
    private String SELECT_SUAT_BY_ID_PHIM = " SELECT COUNT(ID_SUAT) AS LUONGVE FROM SUATCHIEU WHERE ID_PHIM = ?";
    
    private String SELECT_BY_NGAY_PHONG = "SELECT * FROM SUATCHIEU WHERE NGAYCHIEU = ? AND ID_PHONG = ? ORDER BY GIOBATDAU";
    private String  SELECT_SUAT_TRONG_KHUNG = "{CALL SP_FINDTIMESLOT (?,?,?,?)}";
    private String  SELECT_SUAT_TRONG_NGAY_PHIM = "{CALL SP_LICHCHIEUNGAY (?,?,?,?)}";
    private String SELECT_SUAT_ALL = "{CALL SP_SELECTLICH (?,?,?,?,?)}";
    private String SELECT_SUAT_TRANG_THAI = "{CALL SP_SELECTLICHBYTRANGTHAI (?,?,?)}";
    private String SELECT_PHONG_SUAT_TOP3 = "{CALL SP_SELECTLICHPHONG (?,?,?)}";
    private String SELECT_SUAT_DA_CHIEU_PHONG = "{CALL SP_SUATDACHIEUCUAPHONG (?,?,?)}";
    private String SELECT_LUONG_VE_SUAT = "select sc.ID_SUAT, count(ID_VEDAT) luongve from  SUATCHIEU sc " +
                                            "left join VEDAT vd  on vd.ID_SUAT = sc.ID_SUAT " +
                                            "group by sc.ID_SUAT " +
                                            "having count(ID_VEDAT) > 0";
    
    
    @Override
    public void insert(SuatChieu entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getNgayChieu(), entity.getPhong(),
                entity.getPhim(), entity.getGioBatDau(), entity.getGioKetThuc(), entity.getNv());
    }

    @Override
    public void update(SuatChieu entity) throws Exception {
        JDBCHelper.excecuteStatement(UPDATE_SQL, entity.getNgayChieu(), entity.getPhong(),
                entity.getPhim(), entity.getGioBatDau(), entity.getGioKetThuc(), entity.getNv(),entity.getId());
    }

    @Override
    public void delete(String value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    @Override
    public SuatChieu selectById(String value) throws Exception {
        List<SuatChieu> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<SuatChieu> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<SuatChieu> selectBySql(String sql, Object... args) throws Exception {
        List<SuatChieu> list = new ArrayList<SuatChieu>();
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
    public SuatChieu objectFromRs(ResultSet rs) throws Exception {
        return new SuatChieu(rs.getString("ID_SUAT"),
                             rs.getDate("NGAYCHIEU"),
                             rs.getString("ID_PHONG"),
                             rs.getString("ID_PHIM"),
                             rs.getTime("GIOBATDAU"),
                             rs.getTime("GIOKETTHUC"),
                             rs.getString("ID_NV"));

    }
    
    public List<SuatChieu> selectBy(Date ngay, Time t) throws Exception {
        String n = DateHelper.toStringForQuery(ngay);
        String g = t.toString();
        return selectBySql(n, g);
    }
    
    public List<SuatChieu> selectInComingSuatChieus(java.time.LocalDateTime now) throws Exception {
        try {
            String date = DateHelper.getDatePart(now).toString();
            String time = TimeHelper.toString(now);
            System.out.println("date = "+date +" time = "+time);
            return selectBySql(SELECT_WITH_DATETIME_AFTER,date,time);
        }catch(Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Loi ngay thang");
        }
    }
    
    public List<SuatChieu> selectByDateOnly(java.sql.Date date) throws Exception{
        return selectBySql(SELECT_WITH_DATE, date.toString());
    }
    
    // condition :  1 lon hon @ngay | 0 bang @ngay | -1 nho hon ngay
    public List<SuatChieu> selectTTDatVeByDate(java.sql.Date date, java.sql.Time time, int condition) throws Exception{
        //{ID_PHIM, TEN, ID_SUAT, ID_PHONG, NGAYCHIEU, GIOBATDAU}
        List<SuatChieu> l = new ArrayList<>();
        ResultSet rs =  null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_TTDATVE, date, time, condition, null);
            while(rs.next()) {

                l.add(objectFromRs(rs));
            }
            return l;
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
    
    public List<SuatChieu> selectByPhimNgay(java.sql.Date date, String id_Phim) throws SQLException, Exception {
        List<SuatChieu> l = new ArrayList<>();
        
        Time t = null;
        String[] para = {"NGAY","PHIM","CONDITION","GIO"};
        ResultSet rs = null;
        try {
            
            rs = JDBCHelper.getRSOfProc(SELECT_SUAT_TRONG_NGAY_PHIM, para, date,id_Phim, 0, t);

            while(rs.next()) l.add(objectFromRs(rs));

            return l;
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
    public List<SuatChieu> selectSuatChieuTrongKhoang(Date ngay, String phong, Time gioBatDau, Time gioKetThuc) throws Exception {
        return selectBySql(SELECT_SUAT_TRONG_KHUNG, ngay, phong, gioBatDau, gioKetThuc);
    }
    
    public List<SuatChieu> selectByNgayPhong(Date ngay, String phong) throws Exception {
        return selectBySql(SELECT_BY_NGAY_PHONG, ngay, phong);
    }
    
    
    //ID_SUAT, ngaychieu, p.TEN TENPHIM, ID_PHONG PHONG, THOILUONG , GIOBATDAU, GIOKETTHUC
    public List<Vector<Object>> selectByAll(Object...args) throws Exception {
//        System.out.println("object " + java.util.Arrays.toString(args));
        ResultSet rs = null;
        try {
           rs = JDBCHelper.getRSOfProc(SELECT_SUAT_ALL, new String[]{"ID_PHIM","NGAYBD", "NGAYKT", "PHONG", "ISCHUACHIEU"}, args);
            List<Vector<Object>> list = new ArrayList<>();
            int count = 0;
            while(rs.next()) {
                Vector<Object> v = new Vector<>();
                v.addAll(List.of(rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getTime(6),
                        rs.getTime(7),
                        rs.getString(1))
                );
                list.add(v);
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
    
    public Integer[] selectSuatDaChieuCuaPhong(String id_phong, java.sql.Date date, java.sql.Time time) throws SQLException, Exception {
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_SUAT_DA_CHIEU_PHONG,  id_phong, date, time);
            Integer[] a = new Integer[2];
            while(rs.next()) {
                a[0] = rs.getInt(1);
                a[1] = rs.getInt(2);
            }
            return a;
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
    
    public List<Object[]> selectTopSuatCuaPhong(String id_phong, java.sql.Date date, java.sql.Time time) throws SQLException, Exception  {
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_PHONG_SUAT_TOP3, id_phong, date, time);
            List<Object[]> list = new ArrayList<>();
            //pc.ID_PHONG, TEN, GIOBATDAU, GIOKETTHUC 
            while(rs.next()) {
                list.add(new Object[] {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getTime(3),
                    rs.getTime(4)
                });
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
    
    public Map<String, Integer> selectLuongVeOfSuat() throws SQLException, Exception  {
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_LUONG_VE_SUAT);
            Map<String, Integer> map = new HashMap<>();
            while(rs.next()) {
                map.put(rs.getString(1), rs.getInt(2));
            }
            return map;
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
    
    public int getLuongSuatOfPhim(String id) throws Exception {
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_SUAT_BY_ID_PHIM, id);
            Integer luong = 0;
            while(rs.next()) {
                Integer r =rs.getInt("Luongve");
                luong = r == null? 0 : r;
            }
            return luong;
        }catch(Exception ex) {
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

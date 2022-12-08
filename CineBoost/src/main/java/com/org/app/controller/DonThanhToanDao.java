/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;
import com.org.app.entity.DonDoAn;
import com.org.app.entity.DonThanhToan;
import com.org.app.entity.DonVe;
import com.org.app.entity.NhanVien;
import com.org.app.entity.ThanhVien;
import com.org.app.helper.DateHelper;
import com.org.app.helper.JDBCHelper;
import com.org.app.util.DinhDangTienTe;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author intfs
 */
public class DonThanhToanDao extends Dao<Integer, DonThanhToan>{
    String INSERT_SQL = "INSERT INTO DONTHANHTOAN (ID_DONDA, ID_TV, ID_NV, ID_DONVE, NGAYDAT, TONG) VALUES (?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE DONTHANHTOAN SET ID_DONDA = ?, ID_TV = ?, ID_NV = ?, ID_DONVE = ?, NGAYDAT = ?, TONG = ? WHERE ID_DONTT = ?";
    String DELETE_SQL = "DELETE FROM DONTHANHTOAN WHERE ID_DONTT = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DONTHANHTOAN";
    String SELECT_BY_ID_SQL = "SELECT DTT.*, DV.TONGGHE, DV.TAMTINH, TV.HOTEN, TV.NGAYSINH, TV.GIOITINH, TV.SODT, TV.EMAIL FROM DONTHANHTOAN DTT INNER JOIN DONVE DV ON DTT.ID_DONVE = DV.ID_DONVE INNER JOIN THANHVIEN TV ON TV.ID_TV = DTT.ID_TV WHERE ID_DONTT = ?";
    String ADD_DONTT = "{CALL SP_THEMDONTT (?,?,?)}";
    String UPDATE_TONGTIEN = "UPDATE DONTHANHTOAN SET TONG = ? WHERE ID_DONTT = ?";
    String UPDATE_TONGTIEN_NO_GIAM = "{CALL SP_UPDATETONGDON (?)}";

    String GET_DOANH_THU = "select sum(tong) from DONTHANHTOAN where cast(NGAYDAT as date) = ?";
    String SELECT_YEAR = "select YEAR(NGAYDAT) as NAM from DONTHANHTOAN group by YEAR(NGAYDAT) order by NAM";
    String SELECT_DONTT_ALL = "{CALL SP_DONTHANHTOAN (?,?,?,?,?,?)}";
    String SELECT_DON_SDT_NAME = "select ID_DONTT, NGAYDAT, TV.ID_TV, TONG from DONTHANHTOAN DTT JOIN THANHVIEN TV ON DTT.ID_TV = TV.ID_TV where SODT like ? or HOTEN like ?";
    

    @Override
    public void insert(DonThanhToan entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getDonDoAn(), entity.getTv(), entity.getNv(), 
                                                entity.getDonVe(), entity.getNgayDat(), entity.getTong());
    }

    @Override
    public void update(DonThanhToan entity) throws Exception {
        JDBCHelper.excecuteStatement(INSERT_SQL, entity.getDonDoAn(), entity.getTv(), entity.getNv(), 
                                                entity.getDonVe(), entity.getNgayDat(), entity.getTong(), entity.getId());
    }

    public void delete(Integer value) throws Exception {
        JDBCHelper.excecuteStatement(DELETE_SQL, value);
    }

    public DonThanhToan selectById(Integer value) throws Exception {
        List<DonThanhToan> list = this.selectBySql(SELECT_BY_ID_SQL, value);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DonThanhToan> selectAll() throws Exception {
        return this.selectBySql(SELECT_ALL_SQL);
    }
    
    @Override
    public List<DonThanhToan> selectBySql(String sql, Object... args) throws Exception {
        List<DonThanhToan> list = new ArrayList<DonThanhToan>();
        ResultSet rs = JDBCHelper.getResultSetByQuery(sql, args);
        try {
            list.add(objectFromRs(rs));
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DonThanhToan objectFromRs(ResultSet rs) throws Exception {
//        return new DonThanhToan(
//                        rs.getInt("ID_DONTT"),
//                        createDonDoAn(rs.getString("ID_DONDA"), rs.getInt("SOLUONG"), rs.getDouble("TAMTINH")),
//                        createDonVe(rs.getString("ID_DONVE"), rs.getInt("TONGGHE"), rs.getDouble("TAMTINH")),
//                        rs.getDouble("TONG"),
//                        rs.getString("ID_NV"),
//                        createThanhVien(rs.getString("ID_TV")),     
//                        rs.getDate("NGAYDAT")                  
//                    );
        return null;
    }
    
    public DonDoAn createDonDoAn(String id_DonDA, int soLuong, double tamTinh){
        return new DonDoAn(id_DonDA, soLuong, tamTinh);
    }
    
    public DonVe createDonVe(String id_DonVe, int tongGhe, double tamTinh){
        return new DonVe(id_DonVe, tongGhe, tamTinh);
    }
    
    public ThanhVien createThanhVien(String id_ThanhVien){
        return new ThanhVien(id_ThanhVien);
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
            return list;
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
    
//    public List<Object[]> getQLDonThanhToan(){
//        String sql = "{CALL sp_qldonthanhtoan}";
//        String[] cols = {"ID_DONTT", "SUATCHIEU", "NGAYDAT", "ID_TV", "TONG"};
//        return this.getListOfArray(sql, cols);
//    }
//    
//    public List<Object[]> getQLDonThanhToan_NgayMua(Date ngayMua){
//        String sql = "{CALL sp_donThanhToan_NgayMua(?)}";
//        String[] cols = {"ID_DONTT", "SUATCHIEU", "NGAYDAT", "ID_TV", "TONG"};
//        return this.getListOfArray(sql, cols, ngayMua); 
//    }
//    
//    public List<Object[]> getQLDonThanhToan_KhachHang(String loaiKH){
//        String sql = "{CALL sp_donThanhToan_KhachHang(?)}";
//        String[] cols = {"ID_DONTT", "SUATCHIEU", "NGAYDAT", "ID_TV", "TONG"};
//        return this.getListOfArray(sql, cols, loaiKH); 
//    }
    
    //loai don 0 la ve, 1 la doan , con lai la ca 2
    public DonThanhToan addDonThanhToan(int loaiDon, String tv, String nv) throws SQLException {
        ResultSet rs = null;
        try {
            DonThanhToan don = new DonThanhToan(nv, tv);
            rs = JDBCHelper.getResultSetByQuery(ADD_DONTT, loaiDon, tv, nv);
            //ID_DTT, ID_DA, ID_VE
            while(rs.next()) {
                don.setId(rs.getInt(1));
                don.setDonDoAn(rs.getString(2));
                don.setDonVe(rs.getString(3));
            }            
            return don;
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
    
    public void updateTongTien(int id_don, double tong) {
        JDBCHelper.excecuteStatement(UPDATE_TONGTIEN, tong, id_don);
    }
    
    public void updateTongTien(int id_don) {
        JDBCHelper.excecuteStatement(UPDATE_TONGTIEN_NO_GIAM, id_don);
    }
    
    
    public double getDoanhThuNgay(LocalDate date) throws SQLException  {
        ResultSet rs = null;
        try {
            java.util.Date d = DateHelper.convertToDate(date);
            String df = DateHelper.toString(d, DateHelper.YYYYMMDD_FORMAT);
            rs = JDBCHelper.getResultSetByQuery(GET_DOANH_THU, df);
            Double dt = 0.0;
            while (rs.next()){dt =  rs.getDouble(1);};
            
            return dt==null? 0.0 : dt;
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
//    public Double getTamTinh(int id_don) throws SQLException {
//        ResultSet rs = JDBCHelper.getResultSetByQuery(SELECT_TONGTIEN, id_don);
//        Double tong = 0.0;
//        while(rs.next()) {
//            tong = rs.getDouble(1);
//        }
//        return tong;   
//    }
    public List<Integer> getNamThanhToan() throws SQLException{
        
        List<Integer> list = new ArrayList<>();
        ResultSet rs = JDBCHelper.getResultSetByQuery(SELECT_YEAR);
        while (rs.next()) {            
            int nam = rs.getInt("NAM");
//            int nam = DateHelper.getYearPart(rs.getDate("NAM"));
            list.add(nam);
        }
        
        return list;
    }
    
    public List<Vector<Object>> selectByAll(Object...args) throws Exception {
        ResultSet rs = JDBCHelper.getRSOfProc(SELECT_DONTT_ALL, new String[]{"NGAYBD","NGAYKT", "THANG", "NAM", "ISKHACHHANG", "KEYWORD"}, args);
       
        List<Vector<Object>> list = new ArrayList<>();
        while(rs.next()) {
            Vector<Object> v = new Vector<>();
            v.addAll(List.of(
                    rs.getInt("ID_DONTT"),
                    rs.getDate("NGAYDAT"),
                    rs.getString("ID_TV") == null ?"Tự do": rs.getString("ID_TV"),
                    DinhDangTienTe.chuyenThanhTienVN(rs.getDouble("TONG")))
            );
            list.add(v);            
        }
        return list;
    }
    
}

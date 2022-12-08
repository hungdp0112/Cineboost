/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;

import com.org.app.helper.JDBCHelper;
import com.org.app.helper.TimeHelper;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class ThongKeDAO {
    String TAO_KHUNG_GIO_SQL = "SELECT * FROM DBO.FKHUNGGIO(?,?)";
    
    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) throws Exception {
        ResultSet rs = null;
        List<Object[]> list = null;
        try {
             list = new ArrayList<>();
            rs = JDBCHelper.getResultSetByQuery(sql, args);
            while (rs.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
                
            }            
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
        return list;
    }

    // DOANH THU PHÒNG VÉ
    public List<Object[]> getDoanhThuPhongVe() throws Exception {
        String sql = "{CALL sp_doanhthuphongve}";
        String[] cols = {"thang", "tongsosuatchieu", "sovedaban", "doanhthu"};
        return this.getListOfArray(sql, cols);
    }

      // DOANH THU PHÒNG VÉ
    public List<Object[]> getDoanhThuPhongVe_SapXep() throws Exception {
        String sql = "{CALL sp_doanhthuphongve_sapxep}";
        String[] cols = {"thang", "tongsosuatchieu", "sovedaban", "doanhthu"};
        return this.getListOfArray(sql, cols);
    }
    
    // ;ỌC DOANH THU PHÒNG VÉ THEO NGÀY
    public List<Object[]> getDoanhThuPhongVe_TheoNgay(Date ngay) throws Exception {
        String sql = "{CALL sp_doanhthuphongve_theongay(?)}";
        String[] cols = {"thang", "tongsosuatchieu", "sovedaban", "doanhthu"};
        return this.getListOfArray(sql, cols, ngay);
    }

    // LỌC DOANH THU PHÒNG VÉ THEO THÁNG
    public List<Object[]> getDoanhThuPhongVe_TheoThang(int thang, int nam) throws Exception {
        String sql = "{CALL sp_doanhthuphongve_theothangnam(?,?)}";
        String[] cols = {"thang", "tongsosuatchieu", "sovedaban", "doanhthu"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    // LỌC DOANH THU PHÒNG VÉ THEO THÁNG
    public List<Object[]> getDoanhThuPhongVe_TheoNam(int nam) throws Exception {
        String sql = "{CALL sp_doanhthuphongve_theonam(?)}";
        String[] cols = {"thang", "tongsosuatchieu", "sovedaban", "doanhthu"};
        return this.getListOfArray(sql, cols, nam);
    }

    // DOANH THU PHIM
    public List<Object[]> getDoanhThuPhim() throws Exception {
        String sql = "{CALL sp_doanhthuphim}";
        String[] cols = {"TEN", "sosuatchieu", "sovedat", "tonggia"};
        return this.getListOfArray(sql, cols);
    }

     // DOANH THU PHIM
    public List<Object[]> getDoanhThuPhim_SapXep() throws Exception {
        String sql = "{CALL sp_doanhthuphim_sapxep}";
        String[] cols = {"TEN", "sosuatchieu", "sovedat", "tonggia"};
        return this.getListOfArray(sql, cols);
    }
    
     // DOANH THU PHIM
    public List<Object[]> getDoanhThuPhim_Top() throws Exception {
        String sql = "{CALL sp_doanhthuphim_top}";
        String[] cols = {"TEN", "sosuatchieu", "sovedat", "tonggia"};
        return this.getListOfArray(sql, cols);
    }
    
    // LỌC DOANH THU PHIM THEO NGÀY
    public List<Object[]> getDoanhThuPhim_TheoNgay(Date ngay) throws Exception {
        String sql = "{CALL sp_doanhthuphim_theongay(?)}";
        String[] cols = {"TEN", "sosuatchieu", "sovedat", "tonggia"};
        return this.getListOfArray(sql, cols, ngay);
    }

    // LỌC DOANH THU PHIM THEO THÁNG
    public List<Object[]> getDoanhThuPhim_TheoThang(int thang, int nam) throws Exception {
        String sql = "{CALL sp_doanhthuphim_theothangnam(?,?)}";
        String[] cols = {"TEN", "sosuatchieu", "sovedat", "tonggia"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    // LỌC DOANH THU PHIM THEO THÁNG
    public List<Object[]> getDoanhThuPhim_TheoNam(int nam) throws Exception {
        String sql = "{CALL sp_doanhthuphim_theonam(?)}";
        String[] cols = {"TEN", "sosuatchieu", "sovedat", "tonggia"};
        return this.getListOfArray(sql, cols, nam);
    }

    // LƯỢNG VÉ ĐÃ BÁN
    public List<Object[]> getLuongVeDB() throws Exception {
        String sql = "{CALL sp_luongvedaban}";
        String[] cols = {"NGAYCHIEU", "sovedaban", "tongsuatchieu"};
        return this.getListOfArray(sql, cols);
    }

     // SẮP XẾP LƯỢNG VÉ ĐÃ BÁN
    public List<Object[]> getLuongVeDB_SapXep() throws Exception {
        String sql = "{CALL sp_luongvedaban_sapxep}";
        String[] cols = {"NGAYCHIEU", "sovedaban", "tongsuatchieu"};
        return this.getListOfArray(sql, cols);
    }
    // LỌC LƯỢNG VÉ ĐÃ BÁN THEO NGÀY
    public List<Object[]> getLuongVeDB_TheoNgay(Date ngay) throws Exception {
        String sql = "{CALL sp_luongvedaban_theongay(?)}";
        String[] cols = {"NGAYCHIEU", "sovedaban", "tongsuatchieu"};
        return this.getListOfArray(sql, cols, ngay);
    }

    // LỌC LƯỢNG VÉ ĐÃ BÁN THEO THÁNG
    public List<Object[]> getLuongVeDB_TheoThang(int thang, int nam) throws Exception {
        String sql = "{CALL sp_luongvedaban_theothangnam(?,?)}";
        String[] cols = {"NGAYCHIEU", "sovedaban", "tongsuatchieu"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    // LỌC LƯỢNG VÉ ĐÃ BÁN THEO NĂM
    public List<Object[]> getLuongVeDB_TheoNam(int nam) throws Exception {
        String sql = "{CALL sp_luongvedaban_theonam(?)}";
        String[] cols = {"NGAYCHIEU", "sovedaban", "tongsuatchieu"};
        return this.getListOfArray(sql, cols, nam);
    }

    // DOANH THU ĐỒ ĂN
    public List<Object[]> getDTDoAN() throws Exception {
        String sql = "{CALL sp_doanhthudoan}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols);
    }

    // LỌC DOANH THU ĐỒ ĂN THEO NGÀY
    public List<Object[]> getDTDoAN_TheoNgay(Date ngay) throws Exception {
        String sql = "{CALL sp_doanhthudoan_theongay(?)}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols, ngay);
    }

    // LỌC DOANH THU ĐỒ ĂN THEO THÁNG
    public List<Object[]> getDTDoAN_TheoThang(int thang, int nam) throws Exception {
        String sql = "{CALL sp_doanhthudoan_theothangnam(?,?)}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols, thang, nam);
    }

    // LỌC DOANH THU ĐỒ ĂN THEO NĂM
    public List<Object[]> getDTDoAN_TheoNam(int nam) throws Exception {
        String sql = "{CALL sp_doanhthudoan_theonam(?)}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols, nam);
    }

    // LỌC DOANH THU ĐỒ ĂN THEO TÊN
    public List<Object[]> getDTDoAN_TheoTen(String ten) throws Exception {
        String sql = "{CALL sp_doanhthudoan_theoten(?)}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols, ten);
    }

    // LỌC DOANH THU ĐỒ ĂN THEO SIZE
    public List<Object[]> getDTDoAN_TheoSize(String size) throws Exception {
        String sql = "{CALL sp_doanhthudoan_theosize(?)}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols, size);
    }
    
    // SẮP XẾP DOANH THU ĐỒ ĂN
      public List<Object[]> getDTDoAN_SapXep() throws Exception {
        String sql = "{CALL sp_sapxepdoan}";
        String[] cols = {"TEN", "SOLUONG", "ID_KICHCO", "THANHTIEN"};
        return this.getListOfArray(sql, cols);
    }
      
    // DOANH THU TỔNG
    public List<Object[]> getDoanhThuTong() throws Exception {
        String sql = "{CALL sp_doanhthutong}";
        String[] cols = {"DTPV", "DTDA", "tongDoanhThu"};
        return this.getListOfArray(sql, cols);
    }
    
    // DOANH THU TỔNG THEO NGÀY
    public List<Object[]> getDoanhThuTong_TheoNgay(Date ngay) throws Exception {
        String sql = "{CALL sp_doanhthutong_ngay(?)}";
        String[] cols = {"DTPV", "DTDA", "tongDoanhThu"};
        return this.getListOfArray(sql, cols, ngay);
    }
    
    // DOANH THU TỔNG THEO THÁNG
    public List<Object[]> getDoanhThuTong_TheoThang(int thang, int nam) throws Exception {
        String sql = "{CALL sp_doanhthutong_thang(?,?)}";
        String[] cols = {"DTPV", "DTDA", "tongDoanhThu"};
        return this.getListOfArray(sql, cols, thang, nam);
    }
    
    // DOANH THU TỔNG THEO NĂM
    public List<Object[]> getDoanhThuTong_TheoNam(int nam) throws Exception {
        String sql = "{CALL sp_doanhthutong_nam(?)}";
        String[] cols = {"DTPV", "DTDA", "tongDoanhThu"};
        return this.getListOfArray(sql, cols, nam);
    }
    
    // THONG KE THEO KHUNG GIO    
    public List<Object[]> getThongKeKG(int khoangCachKG, Date ngay) throws Exception {
        String sql = "{CALL sp_thongke_khunggio(?,?)}";
        String[] cols = {"KHUNGGIO","SOSUATCHIEU", "SOVEDAT"};
        return this.getListOfArray(sql, cols, khoangCachKG, ngay);
    }
}

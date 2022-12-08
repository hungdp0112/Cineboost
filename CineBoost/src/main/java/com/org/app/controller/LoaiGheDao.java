/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.controller;
import com.org.app.entity.Ghe;
import com.org.app.entity.LoaiGhe;
import com.org.app.helper.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author intfs
 */
public class LoaiGheDao extends Dao<String, LoaiGhe>{
    private String selectAll = "SELECT * FROM LOAIGHE";
    private String selecById = "SELECT * FROM LOAIGHE WHERE ID_LOAIGHE = ?";
    private String SELECT_SOGHEVP_SQL = "select count(*) as SOGHEVIP\n" +
                                "from DONTHANHTOAN DTT join DONVE DV on DTT.ID_DONVE = DV.ID_DONVE " +
                                "					  join VEDAT VD on VD.ID_DONVE = DV.ID_DONVE " +
                                "					  join GHE G on VD.ID_GHE = G.ID_GHE " +
                                "			          join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE " +
                                "where ID_DONTT = ? and G.ID_LOAIGHE like 'VP'";
    private String INSERT = "";
    private String UPDATE = "";
    private String DELETE = "";
    
    @Override
    public void insert(LoaiGhe entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(LoaiGhe entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String value) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LoaiGhe selectById(String value) throws Exception {
        List<LoaiGhe> l =  selectBySql(selecById, value);
        return l.size() == 0? null : l.get(0);
    }

    @Override
    public List<LoaiGhe> selectAll() throws Exception {
        return selectBySql(selectAll);
    }

    @Override
    public List<LoaiGhe> selectBySql(String sql, Object... args) throws Exception {
        List<LoaiGhe> list = new ArrayList<>();
        ResultSet rs =  null;
        
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
    public LoaiGhe objectFromRs(ResultSet rs) throws Exception {
        return  new LoaiGhe(
                    rs.getString("ID_LOAIGHE"),
                    Integer.parseInt(rs.getDouble("PHUTHU")+""),
                    rs.getString("TENLOAI")
                );
        
      }
    
    public int getSoGheVPBySQL(Integer value) throws RuntimeException, SQLException{
        ResultSet rs = null;
        try {
            rs = JDBCHelper.getResultSetByQuery(SELECT_SOGHEVP_SQL, value); 
            int soGheVP = 0;
            while (rs.next()) {                
                soGheVP = rs.getInt("SOGHEVIP");
            }
//            rs.getStatement().getConnection().close();
            return soGheVP;
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

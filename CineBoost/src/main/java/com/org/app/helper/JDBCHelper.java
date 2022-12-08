/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.app.helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author intfs
 */
public class JDBCHelper {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://localhost;databaseName=CINEBOOST";
    private static String username = "sa";
    private static String password = "12345";
    private static String errorMess = JDBCHelper.class.getName();

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(errorMess + ": " + "Lỗi Driver");
        }
    }

    public static PreparedStatement prepareStatement(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        return pstmt;
    }
    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            if(args[i] == null) {
                pstmt.setNull(i+1, java.sql.Types.NVARCHAR);
            }
            else pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }
    
    public static CallableStatement getCallStamentOfSP(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(dburl, username, password);
        CallableStatement pstmt = null;
        pstmt = connection.prepareCall(sql);
        return pstmt;
    }
    
    public static CallableStatement executeCall(String sql, String[] para, Object...args) throws SQLException {
        CallableStatement cstm = getCallStamentOfSP(sql);
        for (int i = 0; i < args.length; i++) {
            cstm.setObject("@"+para[i], args[i]);
        }
        return cstm;

    }
    
    public static ResultSet getRSOfProc(String sql, String[] para, Object...args) throws SQLException {
        try {
            CallableStatement stmt = executeCall(sql, para, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(errorMess + ": " + "Lỗi execute querry SQL");
        }
    } 

    public static int excecuteStatement(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(errorMess + ": " + "Lỗi execute querry SQL");
        }

    }

    public static ResultSet getResultSetByQuery(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(errorMess + ": " + "Lỗi execute querry SQL");
        }
    }
    public static void main(String[] args) {
        JDBCHelper.getResultSetByQuery("SELECT * FROM NHANVIEN");

    }

}

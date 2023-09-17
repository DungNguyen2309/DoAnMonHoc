/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;

/**
 *
 * @author nguye
 */
public class SQLprovider {
        private Connection connection;
    
    public void open(){
    String URL = "jdbc:sqlserver://localhost:1433;databaseName=QUANLY_VPP;encrypt=true;trustServerCertificate=true;";
        String USER = "sa";
        String PASSWORD = "1";
        String DRIVER_CLASS = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {

            Class.forName(DRIVER_CLASS);


            connection = DriverManager.getConnection(URL,USER,PASSWORD);
           
            if(connection != null){
                System.out.println("Kết Nối Thành công");
            }
        } catch (ClassNotFoundException e) {
//            System.out.println("khong the load");
            e.printStackTrace();
        } catch (SQLException ex) {
           // System.out.println("khong the ket noi csdl");
           System.out.println("Lỗi: " + ex.getMessage());
        }
    
    }
    public void close(){
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLprovider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet excuteQuery(String sql){
        Statement stmStatement=null;
        ResultSet rs=null;
        try {
             stmStatement=connection.createStatement();
              rs =stmStatement.executeQuery(sql);             
            System.out.println("hien thi thanh cong");
        } catch (SQLException ex) {
            System.out.println("Hien thi that bai");
        }
        return rs;
    
    }
    public int excuteUpdate(String sql){
        int n=-1;
        try {
            
            Statement stm=connection.createStatement();
            
            n=stm.executeUpdate(sql);
            
        } catch (Exception e) {
            n=-1;
        }
        return n;
    
    }
     public int checkLogin(String username, String password) {
        int code = 0;
        try {       
            // Gọi thủ tục
            String sql = "{call loginSP (?, ?)}";
            CallableStatement cstmt = connection.prepareCall(sql);
            cstmt.setString(1, username);
            cstmt.setString(2, password);
            ResultSet rs = cstmt.executeQuery();

            // Lấy giá trị trả về từ thủ tục
            if (rs.next()) {
                code = rs.getInt("code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }
}

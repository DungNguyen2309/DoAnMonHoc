/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.KhachHang;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class KhachHangDAO {
    static  SQLprovider provider= new SQLprovider();
    public static ArrayList<KhachHang> layDSKH(){
    ArrayList<KhachHang> ds=new ArrayList<>();
  
    provider.open();
        try {
            String sql= "select * from KHACHHANG";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                KhachHang KH= new KhachHang();
                KH.setMaKH(rs.getInt("MAKH"));
                KH.setTenKH(rs.getString("TENKH"));
                KH.setSDT(rs.getString("SDT"));
                KH.setGioiTinh(rs.getString("GIOITINH"));
                ds.add(KH);
            }
            provider.close();
        } catch (Exception e) {
            System.out.println("lay ds that bai");
        }
        return ds;
    }

  
    public int layIdKH(String TenKh){
        String sql="Select MAKH from KHACHHANG where TENKH=N'"+TenKh+",";
       ResultSet s= provider.excuteQuery(sql);
       int i=-1;
        try {
            while (s.next()){
                i=s.getInt("MAKH");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    
    
    }
    public static int themKH(String TenKh, String SDT, String GT){
        provider.open();
        String sql= "insert into KHACHHANG(TENKH,SDT,GIOITINH) values(N'"+TenKh+"','"+SDT+"','"+GT+"');";
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
        
    }
}

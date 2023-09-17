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
import pojo.LoaiSP;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class LoaiSPDAO {
    public static SQLprovider provider= new SQLprovider();
    public static ArrayList<LoaiSP> layDSLSP(){
    ArrayList<LoaiSP> ds=new ArrayList<>();
    SQLprovider provider= new SQLprovider();
    provider.open();
        try {
            String sql= "select * from LoaiSP";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                LoaiSP lSp= new LoaiSP();
                lSp.setMaLoai(rs.getInt("MALOAISP"));
                lSp.setTenLoai(rs.getString("TENLOAISP"));
               
                ds.add(lSp);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
    public static int layMaSPByTen(String tenSP){
        provider.open();
        int i=-1;
            String sql= "select * from LoaiSP where TENLOAISP=N'"+tenSP+"'";
            ResultSet rs= provider.excuteQuery(sql);
        try {
            if (rs.next()) {
                i=rs.getInt("MALOAISP");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoaiSPDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            provider.close();
            return i;
    }
    public static int themLoaiSP(String ten){
        String sql="insert into LOAISP(TENLOAISP) values(N'"+ten+"')";
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    }
    public static int suaLoaiSP(int maloai,String Ten){
        String sql="update LOAISP set TENLOAISP=N'"+Ten+"' where MALOAISP="+maloai;
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    
    }
}

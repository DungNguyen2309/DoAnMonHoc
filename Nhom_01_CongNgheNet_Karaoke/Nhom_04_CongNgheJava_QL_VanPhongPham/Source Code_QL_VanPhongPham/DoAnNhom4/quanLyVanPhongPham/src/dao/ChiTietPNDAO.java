/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.ChiTietPhieuNhap;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class ChiTietPNDAO {
    static SQLprovider provider= new SQLprovider();
    public static ArrayList<ChiTietPhieuNhap> layDSCTPN(){
    ArrayList<ChiTietPhieuNhap> ds=new ArrayList<>();
    
    provider.open();
        try {
            String sql= "select * from CHITIETPHIEUNHAP";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                ChiTietPhieuNhap ctPn= new ChiTietPhieuNhap();
                ctPn.setMaCTPN(rs.getInt("MACTPM"));
                ctPn.setMaPN(rs.getInt("MAPN"));
                ctPn.setMaSP(rs.getInt("MASP"));
                ctPn.setSoLuong(rs.getInt("SOLUONG"));
                ctPn.setThanhTien(rs.getFloat("THANHTIEN"));
                ds.add(ctPn);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
    public static int themCtPN(int maPN,int MaSP,int soLuong){
        String sql="insert into CHITIETPHIEUNHAP(MAPN,MASP,SOLUONG) values("+maPN+","+MaSP+","+soLuong+");";
        
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
              
    
    }
    public static int xoaCTPN(int MAPN){
        String sql="delete from CHITIETPHIEUNHAP where MAPN="+MAPN;
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    
    }
}

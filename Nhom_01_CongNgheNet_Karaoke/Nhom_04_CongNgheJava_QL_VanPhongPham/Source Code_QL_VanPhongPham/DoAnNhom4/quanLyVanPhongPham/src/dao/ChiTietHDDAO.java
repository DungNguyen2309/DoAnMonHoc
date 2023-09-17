/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.ChiTietHoaDon;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class ChiTietHDDAO {
    static SQLprovider provider= new SQLprovider();
    
     public static ArrayList<ChiTietHoaDon> getAllChiTietHD(){
        ArrayList<ChiTietHoaDon> chiTietHDList = new ArrayList<>();
        
        provider.open();
        try {
            String sql= "select * from CHITIETHD";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                int maCTHD = rs.getInt("MACTHD");
            int maHD = rs.getInt("MAHD");
            int maSP = rs.getInt("MASP");
            int soLuong = rs.getInt("SOLUONG");
            float thanhTien = rs.getFloat("THANHTIEN");
            ChiTietHoaDon chiTietHD = new ChiTietHoaDon(maCTHD, maHD, maSP, soLuong, thanhTien);
            chiTietHDList.add(chiTietHD);
            }
            provider.close();
        } catch (SQLException e) {
        }
        return chiTietHDList;
    }
     
     public static ArrayList<ChiTietHoaDon> getAllChiTietHDByHDID(int mahd){
        ArrayList<ChiTietHoaDon> chiTietHDList = new ArrayList<>();
        
        provider.open();
        try {
            String sql= "select * from CHITIETHD where MAHD="+mahd;
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                int maCTHD = rs.getInt("MACTHD");
            int maHD = rs.getInt("MAHD");
            int maSP = rs.getInt("MASP");
            int soLuong = rs.getInt("SOLUONG");
            float thanhTien = rs.getFloat("THANHTIEN");
            ChiTietHoaDon chiTietHD = new ChiTietHoaDon(maCTHD, maHD, maSP, soLuong, thanhTien);
            chiTietHDList.add(chiTietHD);
            }
            provider.close();
        } catch (Exception e) {
        }
        return chiTietHDList;
    }
    public static int themCTHD(int mahd, int masp, int soluong){
        provider.open();
        String sql="insert into CHITIETHD(MAHD,MASP,SOLUONG) values("+mahd+","+masp+","+soluong+");";
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    }
    public static int xoaCTHD(int maHD){
        String sql="delete from CHITIETHD where MAHD="+maHD;
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    }
}

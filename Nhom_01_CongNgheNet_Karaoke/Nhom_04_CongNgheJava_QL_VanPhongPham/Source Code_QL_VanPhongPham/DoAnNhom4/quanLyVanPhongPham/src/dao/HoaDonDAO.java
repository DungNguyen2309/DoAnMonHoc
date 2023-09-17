/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import pojo.HoaDon;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class HoaDonDAO {
    static  SQLprovider provider= new SQLprovider();
    
      public static ArrayList<HoaDon> layDSHD(){
    ArrayList<HoaDon> ds=new ArrayList<>();
    provider.open();
        try {
            String sql= "select * from Hoadon";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                HoaDon hd= new HoaDon();
                hd.setMaHD(rs.getInt("MAHD"));
                hd.setMaKH(rs.getInt("MAKH"));
                hd.setMaNV(rs.getInt("MANV"));
                hd.setNgayLap(rs.getDate("NGAYLAP"));
                hd.setGiamGia(rs.getFloat("GIAMGIA"));
                hd.setTongTien(rs.getFloat("TONGTIEN"));
                ds.add(hd);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
        public static ArrayList<HoaDon> layDSHDTheoNgay(String dateBD,String dateKT){
    ArrayList<HoaDon> ds=new ArrayList<>();
    provider.open();
        try {
            String sql= "set dateformat DMY\n" +"exec usp_LayHoaDonTrongKhoangThoiGian '"+dateBD+"','"+dateKT+"'";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                HoaDon hd= new HoaDon();
                hd.setMaHD(rs.getInt("MAHD"));
                hd.setMaKH(rs.getInt("MAKH"));
                hd.setMaNV(rs.getInt("MANV"));
                hd.setNgayLap(rs.getDate("NGAYLAP"));
                hd.setGiamGia(rs.getFloat("GIAMGIA"));
                hd.setTongTien(rs.getFloat("TONGTIEN"));
                ds.add(hd);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
      public static int layMaHdMoiNhat(){
          int i=0;
          try {
           provider.open();
            ResultSet rs=provider.excuteQuery("SELECT TOP 1 MAHD FROM HOADON ORDER BY MAHD DESC");
            while (rs.next()){
                i=rs.getInt("MAHD");
            }
              System.out.println("lay thanh cong");
           // provider.close();
        } catch (SQLException ex) {
            i=-1;
              System.out.println("lay that bai");
              ex.printStackTrace();
        }
        return i;
        
      }
      public static int themHD(int makh, int manv,String ngaylap,float giamGia){
          String sql= "set dateformat DMY insert into HoaDon(MAKH,MANV,NGAYLAP,GIAMGIA) values("+makh+","+manv+",'"+ngaylap+"',"+giamGia+")";
          
          provider.open();
          int i= provider.excuteUpdate(sql);
          provider.close();
          return i;
      
      }
      public static int xoaHD(int mahd){
          String sql="delete from HOADON where MAHD="+mahd;
          
          provider.open();
          int i= provider.excuteUpdate(sql);
          provider.close();
          return i;
                  
          
      }
      public static int capNhatGiamGia(int ma,float giamGia){
          String sql="update HOADON set GIAMGIA="+giamGia+" where MAHD="+ma;
          provider.open();
          int i= provider.excuteUpdate(sql);
          provider.close();
          return i;
      
      }
}

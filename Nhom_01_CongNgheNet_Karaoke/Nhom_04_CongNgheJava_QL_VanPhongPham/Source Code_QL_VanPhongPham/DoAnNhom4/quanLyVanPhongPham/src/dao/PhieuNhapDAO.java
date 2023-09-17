/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static dao.HoaDonDAO.provider;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojo.PhieuNhap;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class PhieuNhapDAO {
    static  SQLprovider provider= new SQLprovider();
    public static ArrayList<PhieuNhap> layDSPN(){
    ArrayList<PhieuNhap> ds=new ArrayList<>();
    
    provider.open();
        try {
            String sql= "select * from PHIEUNHAP";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                PhieuNhap pn= new  PhieuNhap();
                pn.setMaPN(rs.getInt("MAPN"));
                pn.setMaNCC(rs.getInt("MANCC"));
                pn.setMaNV(rs.getInt("MANV"));
                pn.setNgayLap(rs.getDate("NGAYLAP"));
                pn.setTongTien(rs.getFloat("TONGTIEN"));
                ds.add(pn);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
    public static int themPN(int MaNCC,int MANV,String NgayLap){
        String sql="set dateformat DMY insert into PHIEUNHAP(MANCC,MANV,NGAYLAP) values("+MaNCC+","+MANV+","+NgayLap+");";
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    }
    public static int xoaPn(int ma){
        String sql="delete from PHIEUNHAP where MAPN="+ma;
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    
    }
     public static int layMaPNMoiNhat(){
          int i=0;
          try {
           provider.open();
            ResultSet rs=provider.excuteQuery("SELECT TOP 1 MAPN FROM PHIEUNHAP ORDER BY MAPN DESC");
            while (rs.next()){
                i=rs.getInt("MAPN");
            }
          
            provider.close();
        } catch (SQLException ex) {
            i=-1;
              System.out.println("lay that bai");
              ex.printStackTrace();
        }
        return i;
        
      }
    
}

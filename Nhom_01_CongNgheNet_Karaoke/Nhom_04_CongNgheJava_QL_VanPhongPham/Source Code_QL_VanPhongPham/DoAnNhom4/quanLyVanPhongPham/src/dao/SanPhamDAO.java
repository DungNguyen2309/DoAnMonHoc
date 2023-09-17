/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.SanPham;
/**
 *
 * @author nguye
 */
public class SanPhamDAO {
    
     public static SQLprovider provider= new SQLprovider();
    public static ArrayList<SanPham> layDSSP(){
    ArrayList<SanPham> ds=new ArrayList<>();
    SQLprovider provider= new SQLprovider();
    provider.open();
        try {
            String sql= "select * from SanPham";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                SanPham sp= new SanPham();
                sp.setMaSP(rs.getInt("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setMaLoai(rs.getInt("MALOAI"));
                sp.setDonGua(rs.getFloat("DONGIA"));
                sp.setSoLuongTon(rs.getInt("SOLUONGTON"));
                ds.add(sp);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
    public static int themSP(String tenSP, int maLoai, float dongia){
        String sql="insert into SANPHAM(TENSP,MALOAI,DONGIA,SOLUONGTON) values(N'"+tenSP+"',"+maLoai+","+dongia+",0);";
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    }
    public static int xoaSP(int maSP){
        String sql="delete from SANPHAM where MASP="+maSP;
        
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
    }
    public static int suaSP(int MaSP,String tenSP, int maLoai, float dongia){
        String sql="UPDATE SANPHAM set TENSP=N'"+tenSP+"',MALOAI="+maLoai+",DONGIA="+dongia+"where MASP="+MaSP;
        provider.open();
        int i= provider.excuteUpdate(sql);
        provider.close();
        return i;
                
    }
    public static ArrayList<SanPham> TimKiem(String key){
    ArrayList<SanPham> ds=new ArrayList<>();
    SQLprovider provider= new SQLprovider();
    provider.open();
        try {
            String sql= "EXEC SearchProduct N'"+key+"'";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                SanPham sp= new SanPham();
                sp.setMaSP(rs.getInt("MASP"));
                sp.setTenSP(rs.getString("TENSP"));
                sp.setMaLoai(rs.getInt("MALOAI"));
                sp.setDonGua(rs.getFloat("DONGIA"));
                sp.setSoLuongTon(rs.getInt("SOLUONGTON"));
                ds.add(sp);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.NhanVien;
import pojo.SanPham;
import java.sql.SQLException;
/**
 *
 * @author nguye
 */
public class NhanVienDAO {
    static SQLprovider provider= new SQLprovider();
    public static ArrayList<NhanVien> layDSNV(){
    ArrayList<NhanVien> ds=new ArrayList<>();
    
    provider.open();
        try {
            String sql= "select * from NHANVIEN";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
                NhanVien nv= new NhanVien();
                nv.setMaNV(rs.getInt("MANV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setGioiTinh(rs.getString("GIOITINH"));
                nv.setSDT(rs.getString("SDT"));
                nv.setDiaChi(rs.getString("DIACHI"));
                nv.setChucVu(rs.getInt("CHUCVU"));
                nv.setTenDN(rs.getString("TENDN"));
                nv.setMatKhau(rs.getString("MK"));
                ds.add(nv);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }

    public static int themNV(String ten, String gioitinh, String Sdt, String Diachi, int chucvu,String tenDN, String MK){
        int i= -1;
        
        provider.open();
        String query = "INSERT INTO NHANVIEN (TenNV, GioiTinh, DiaChi, SDT, TenDN, ChucVu, MK) VALUES (N'" + ten + "', '" + gioitinh + "', '" + Diachi + "', '" + Sdt + "', '" + tenDN + "', "
                + chucvu + ", '" + MK + "')";
        i = provider.excuteUpdate(query);
        provider.close();
        return i;
    }
    public static int xoaNV(int maNV){
        int i=-1;
        provider.open();
        
        String query="delete from NhanVien where MANV="+maNV;
        i=provider.excuteUpdate(query);
        
        provider.close();
        return i;
    }
        public static int suaNV(int maNV ,String ten, String gioitinh, String Sdt, String Diachi, int chucvu,String tenDN, String MK){
        int i= -1;
        
        provider.open();
        String query ="update NHANVIEN set TENNV=N'"+ten+"', GIOITINH='"+gioitinh+"',DIACHI='"+Diachi+"',SDT='"+Sdt+"',CHUCVU="+chucvu+",TENDN='"+tenDN+"',MK='"+MK+"' where MANV="+maNV;
        i = provider.excuteUpdate(query);
        provider.close();
        return i;
    }
}

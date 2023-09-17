/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import pojo.NhaCungCap;
import pojo.SanPham;

/**
 *
 * @author nguye
 */
public class NhaCCDAO {
    public static ArrayList<NhaCungCap> layDSNCC(){
    ArrayList<NhaCungCap> ds=new ArrayList<>();
    SQLprovider provider= new SQLprovider();
    provider.open();
        try {
            String sql= "select * from NHACUNGCAP";
            ResultSet rs= provider.excuteQuery(sql);
            while (rs.next()) {
            int maNCC = rs.getInt("MANCC");
            String tenNCC = rs.getString("TENNCC");
            String sdt = rs.getString("SDT");
            String email = rs.getString("EMAIL");
            String diaChi = rs.getString("DIACHI");
            NhaCungCap nhaCungCap = new NhaCungCap(maNCC, tenNCC, sdt, email, diaChi);
            ds.add(nhaCungCap);
            }
            provider.close();
        } catch (Exception e) {
        }
        return ds;
    }
}

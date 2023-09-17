/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author nguye
 */
public class KhachHang {
    private int maKH;
    private String tenKH;
    private String SDT;
    private String gioiTinh;

    public int getMaKH() {
        return maKH;
    }
    public KhachHang(KhachHang kh){
        this.maKH=kh.maKH;
        this.tenKH=kh.tenKH;
        this.SDT=kh.SDT;
        this.gioiTinh=kh.gioiTinh;
    
    }
    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public KhachHang(int maKH, String tenKH, String SDT, String gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.SDT = SDT;
        this.gioiTinh = gioiTinh;
    }

    public KhachHang() {
    }
        @Override
    public String toString() {
        return tenKH;
    }
}

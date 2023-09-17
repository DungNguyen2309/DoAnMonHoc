/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author nguye
 */
public class SanPham {
    private int maSP;
    private String tenSP;
    private int maLoai;
    private float donGua;
    private int soLuongTon;

    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, int maLoai, float donGua, int soLuongTon) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maLoai = maLoai;
        this.donGua = donGua;
        this.soLuongTon = soLuongTon;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public float getDonGua() {
        return donGua;
    }

    public void setDonGua(float donGua) {
        this.donGua = donGua;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }
    
    
}

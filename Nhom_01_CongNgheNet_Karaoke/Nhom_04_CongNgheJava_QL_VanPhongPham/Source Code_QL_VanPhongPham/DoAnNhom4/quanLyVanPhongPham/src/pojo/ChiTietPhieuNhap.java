/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author nguye
 */
public class ChiTietPhieuNhap {
    private int maCTPN;
    private int maPN;
    private int maSP;
    private int soLuong;
    private float thanhTien;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(int maCTPN, int maPN, int maSP, int soLuong, float thanhTien) {
        this.maCTPN = maCTPN;
        this.maPN = maPN;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public int getMaCTPN() {
        return maCTPN;
    }

    public void setMaCTPN(int maCTPN) {
        this.maCTPN = maCTPN;
    }

    public int getMaPN() {
        return maPN;
    }

    public void setMaPN(int maPN) {
        this.maPN = maPN;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }
    
}

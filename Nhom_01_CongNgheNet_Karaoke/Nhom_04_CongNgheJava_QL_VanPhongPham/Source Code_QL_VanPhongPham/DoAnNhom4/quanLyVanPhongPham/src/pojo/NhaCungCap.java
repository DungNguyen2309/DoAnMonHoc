/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author nguye
 */
public class NhaCungCap {
    private int maNCC;
    private String tenNCC;
    private String SDT;
    private String email;
    private String diaChi;

    public NhaCungCap() {
    }

    public NhaCungCap(int maNCC, String tenNCC, String SDT, String email, String diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.SDT = SDT;
        this.email = email;
        this.diaChi = diaChi;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    
}

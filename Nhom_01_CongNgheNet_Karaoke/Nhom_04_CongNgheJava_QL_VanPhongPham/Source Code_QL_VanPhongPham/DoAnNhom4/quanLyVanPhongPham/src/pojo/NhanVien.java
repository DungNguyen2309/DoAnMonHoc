/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pojo;

/**
 *
 * @author nguye
 */
public class NhanVien {
    private int maNV;
    private String tenNV;
    private String gioiTinh;
    private String diaChi;
    private String SDT;
    private int chucVu;// 1 la admin 
    private String tenDN;
    private String matKhau;

    public NhanVien() {
    }

    public NhanVien(int maNV, String tenNV, String gioiTinh, String diaChi, String SDT, int chucVu, String tenDN, String matKhau) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.SDT = SDT;
        this.chucVu = chucVu;
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }
    public NhanVien(NhanVien nv){
    
        this.maNV = nv.maNV;
        this.tenNV = nv.tenNV;
        this.gioiTinh = nv.gioiTinh;
        this.diaChi = nv.diaChi;
        this.SDT = nv.SDT;
        this.chucVu = nv.chucVu;
        this.tenDN = nv.tenDN;
        this.matKhau = nv.matKhau;
    }
    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public int getChucVu() {
        return chucVu;
    }

    public void setChucVu(int chucVu) {
        this.chucVu = chucVu;
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
}

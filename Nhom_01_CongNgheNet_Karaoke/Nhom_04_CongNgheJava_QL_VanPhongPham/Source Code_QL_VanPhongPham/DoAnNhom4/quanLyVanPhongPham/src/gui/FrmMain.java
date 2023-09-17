/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import dao.ChiTietHDDAO;
import dao.HoaDonDAO;
import dao.SQLprovider;
import dao.SanPhamDAO;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pojo.KhachHang;
import pojo.SanPham;
import dao.KhachHangDAO;
import pojo.NhanVien;
import dao.NhanVienDAO;
import dao.KhachHangDAO;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import pojo.HoaDon;
import java.text.SimpleDateFormat;
import java.util.Date;
import pojo.ChiTietHoaDon;
import dao.ChiTietHDDAO;
import javax.swing.JFrame;
/**
 *
 * @author nguye
 */
public final class FrmMain extends javax.swing.JFrame {

    /**
     * Creates new form FrmMain
     */
    // object
    static NhanVien nhanVien=new NhanVien();
    static KhachHang khachHang= new KhachHang();
    static HoaDon indexHD= new HoaDon();
    static ArrayList<NhanVien> dsNV=NhanVienDAO.layDSNV();
    static ArrayList<KhachHang> DSKH=KhachHangDAO.layDSKH();
    static ArrayList<HoaDon> DSHD= HoaDonDAO.layDSHD();
    static String user;
    static int ChucVu;
    public static FrmMain frm;
    
    DefaultTableModel dtm= new DefaultTableModel();
    DefaultTableModel dtm2= new DefaultTableModel();
    
    public FrmMain(String user, int chucvu) {
        FrmMain.user=user;
        FrmMain.ChucVu=chucvu;
        frm=this;
        initComponents();
        if (chucvu==0) {
            menuQLNV.setVisible(false);
            menu_NhapHang.setVisible(false);
            menuQLSP.setVisible(false);
        }
        
        
        tbSanPham.setModel(dtm);
        String[] tieude={"MaSP","TenSP","Ma Loai","Don Gia","So Luong Ton Kho"};
        String[] tieude2={"id sản phẩm","Tên sản phẩm","Số Lượng"};
        dtm.setColumnIdentifiers(tieude);
        dtm2.setColumnIdentifiers(tieude2);
        tbGioHang.setModel(dtm2);
        this.setLocationRelativeTo(null);
        load();
        loadKH();
        nhanVien=LayNhanVien();
        khachHang= layKhachHang();
        lbTenNhanVien.setText(nhanVien.getTenNV());
        

    }

    private FrmMain() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public void load(){
        dtm.setRowCount(0);
        ArrayList<SanPham> dssp= SanPhamDAO.layDSSP();
        for(SanPham sp:dssp){
            Vector<Object> row= new Vector<Object>();
            row.add(sp.getMaSP());
            row.add(sp.getTenSP());
            row.add(sp.getMaLoai());
            row.add(sp.getDonGua());
            row.add(sp.getSoLuongTon());
            dtm.addRow(row);
        }
    }
    public void loadKH(){
        cboKhachHang.removeAllItems();
        ArrayList<KhachHang> dsKH= KhachHangDAO.layDSKH();
        for(KhachHang kh: dsKH){
            cboKhachHang.addItem(kh.getTenKH());
            
        }
        
        
    
    }
    public NhanVien LayNhanVien(){
        for (NhanVien Nv:dsNV) {
            if(user.equals(Nv.getTenDN())){
               return Nv;
            }
        }
        return null;
    }
    public KhachHang layKhachHang(){
        for(KhachHang kh:DSKH){
            String selectedKH= cboKhachHang.getSelectedItem().toString();
            if(selectedKH.equals(kh.getTenKH())){
                return kh;
            }
        }
        return null;
    
    }
    public HoaDon layHD(){
        ArrayList<HoaDon> ds= HoaDonDAO.layDSHD();
        for(HoaDon hd:ds){
            if(HoaDonDAO.layMaHdMoiNhat()==hd.getMaHD()){
            return hd;
            }
            
        }
        return null;
    }
    public void loadHD(){
        ArrayList<HoaDon> ds= HoaDonDAO.layDSHD();
        for(HoaDon hd: ds){
            if(HoaDonDAO.layMaHdMoiNhat()==hd.getMaHD()){
                txtMaHd.setText(String.valueOf(hd.getMaHD()));
                txtNVLap.setText(nhanVien.getTenNV());
                txtNgayLap.setText(String.valueOf(hd.getNgayLap()));
                txtGiamGia.setText(String.valueOf(hd.getGiamGia()));
                txtThanhTien.setText(String.valueOf(hd.getTongTien()));
            
            }
        
        }
    }
    
    // lấy danh sách chi tiêst hóa đơn của hóa đơn
    public ArrayList<ChiTietHoaDon> layDSCTHD(){
        
        ArrayList<ChiTietHoaDon> ds=ChiTietHDDAO.getAllChiTietHDByHDID(Integer.parseInt(txtMaHd.getText())) ;
        return ds;
    }
    public void printInvoiceToFile(HoaDon hoadon, ArrayList<ChiTietHoaDon> dsCTHD) {
    try {
        // Tạo đối tượng File và BufferedWriter
        File file = new File("hoadon.txt");
        String content = "CỬA HÀNG HƯNG THỊNH";
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        // ghi autosize
        bw.write("\t"+content);
        bw.newLine();
        // Ghi thông tin hóa đơn vào file
        bw.write("Mã hóa đơn: " + hoadon.getMaHD() + "\n");
        
        bw.write("Mã khách hàng: " + hoadon.getMaKH() + "\n");
        bw.write("Tên Khách Hàng:"+cboKhachHang.getSelectedItem().toString());
        bw.newLine();
        bw.write("=====================================");
        bw.newLine();
        bw.write("STT    MãSP    Số Lượng    Thành tiền\n");
        int stt = 1;
           for (ChiTietHoaDon sp : dsCTHD) {
            bw.write(stt + "\t" + sp.getMaSP()+ "\t  " + sp.getSoLuong()+ "\t " + sp.getThanhTien()+"đ");
            bw.newLine();
            stt++;
        }
        bw.newLine();
        bw.write("Tổng tiền: " + hoadon.getTongTien() + "\n");
        bw.newLine();
        bw.write("Ngày lập hóa đơn: " + hoadon.getNgayLap()+ "\n");
        
        bw.write("\tdesign by nhóm 4\n");
        bw.write("\tHotLine: 0359692045\n");
        bw.write("================end==================");
// Đóng BufferedWriter
        bw.close();

        // Mở file bằng ứng dụng văn bản mặc định của hệ thống
       if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
        desktop.open(file);
         }} 
    }catch (IOException e) {
        e.printStackTrace();
    }}
    public void cleartxt(){
        txtMaHd.setText("");
        txtNVLap.setText("");
        txtGiamGia.setText("");
        txtThanhTien.setText("");
        txtNgayLap.setText("");
    
    }
    public boolean ktrTrungMa(int id){
        for(int i=0; i<tbGioHang.getRowCount();i++ ){
            
                int id1=Integer.parseInt(tbGioHang.getValueAt(i,0).toString());
                
                if(id1==id){
                    return true;
                }
        }
        return false;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbGioHang = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        cboKhachHang = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnThemKHM = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbTenNhanVien = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaHd = new javax.swing.JTextField();
        txtNVLap = new javax.swing.JTextField();
        txtNgayLap = new javax.swing.JTextField();
        txtGiamGia = new javax.swing.JTextField();
        txtThanhTien = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuQLNV = new javax.swing.JMenuItem();
        menuQLSP = new javax.swing.JMenuItem();
        menu_NhapHang = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbSanPham);

        tbGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Tên Sản phẩm", "Id Sản phẩm", "Số Lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbGioHang);
        if (tbGioHang.getColumnModel().getColumnCount() > 0) {
            tbGioHang.getColumnModel().getColumn(0).setResizable(false);
            tbGioHang.getColumnModel().getColumn(1).setResizable(false);
        }

        btnThem.setText("Thêm vào giỏ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa khỏi giỏ");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setText("Tính Tiền");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jLabel1.setText("Khách Hàng");

        btnThemKHM.setText("Thêm");
        btnThemKHM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKHMActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Thông Tin Hóa Đơn");

        jLabel3.setText("Nhân Viên:");

        lbTenNhanVien.setText("jLabel4");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Mã Hóa Đơn");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Nhân Viên Lập");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Ngày Lập");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Giảm Giá");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Thành Tiền");

        jButton1.setText("Thanh Toán");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Tài Khoản");

        jMenuItem1.setText("Thông tin tài khoản");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Đăng Xuất");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Quản Lí");

        menuQLNV.setText("Quản lí nhân viên");
        menuQLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLNVActionPerformed(evt);
            }
        });
        jMenu2.add(menuQLNV);

        menuQLSP.setText("Quản lí sản phẩm");
        menuQLSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuQLSPActionPerformed(evt);
            }
        });
        jMenu2.add(menuQLSP);

        menu_NhapHang.setText("Nhập hàng");
        menu_NhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_NhapHangActionPerformed(evt);
            }
        });
        jMenu2.add(menu_NhapHang);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");

        jMenuItem4.setText("Thống kê doanh Thu");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(522, 522, 522)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThemKHM)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(btnThem)
                                        .addGap(60, 60, 60)
                                        .addComponent(btnXoa)
                                        .addGap(61, 61, 61)
                                        .addComponent(btnTaoHoaDon)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtNVLap)
                                        .addComponent(txtNgayLap)
                                        .addComponent(txtGiamGia)
                                        .addComponent(txtThanhTien, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
                                    .addComponent(jLabel2)
                                    .addComponent(txtMaHd, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51))))))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btnThemKHM))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnThem)
                                .addComponent(btnXoa)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbTenNhanVien))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaHd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNVLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        String soLuong = JOptionPane.showInputDialog("Nhập số lượng:");
        int index = tbSanPham.getSelectedRow();
        int soluongS= Integer.parseInt(soLuong);
        if(soluongS> Integer.parseInt(tbSanPham.getValueAt(index, 4).toString())){
           JOptionPane.showMessageDialog(rootPane, "Khong du so luong ton kho");
           return;}else if(ktrTrungMa(Integer.parseInt(tbSanPham.getValueAt(index, 0).toString()))){
                   
            for(int i=0; i<dtm2.getRowCount();i++ ){
                int id=Integer.parseInt(tbGioHang.getValueAt(i,0).toString());
                int idSPselected= Integer.parseInt(tbSanPham.getValueAt(index, 0).toString());
                if(idSPselected==id){
                    int soluongSP=soluongS+Integer.parseInt(tbGioHang.getValueAt(i,2 ).toString());
                    tbGioHang.setValueAt(soluongSP, i, 2);
                }
            }
           }else{
            Vector<Object> row= new Vector<>();
            row.add(tbSanPham.getValueAt(index,0));
            row.add(tbSanPham.getValueAt(index,1));
            row.add(soluongS);
            dtm2.addRow(row);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int indexGH= tbGioHang.getSelectedRow();
        dtm2.removeRow(indexGH);
        
    }//GEN-LAST:event_btnXoaActionPerformed
    
    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        // TODO add your handling code here:
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);
        //System.out.println(formattedDate);
        txtGiamGia.setText("0");
        HoaDonDAO.themHD(khachHang.getMaKH(), nhanVien.getMaNV(), formattedDate, Float.parseFloat(txtGiamGia.getText()));
        indexHD= layHD();
        for (int i = 0; i < dtm2.getRowCount(); i++) {
            int masp= Integer.parseInt(tbGioHang.getValueAt(i,0).toString());
            
            int soluong= Integer.parseInt(tbGioHang.getValueAt(i,2).toString());
             ChiTietHDDAO.themCTHD(indexHD.getMaHD(),masp , soluong);
        }
        dtm2.setRowCount(0);
        cleartxt();
        indexHD= layHD();
        loadHD();
        
        
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        HoaDonDAO.capNhatGiamGia(Integer.parseInt(txtMaHd.getText()),Float.parseFloat(txtGiamGia.getText()));
        indexHD=layHD();
        printInvoiceToFile(indexHD,layDSCTHD());
        
        loadHD();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        if(dialogResult == JOptionPane.YES_OPTION){
           int i=ChiTietHDDAO.xoaCTHD(Integer.parseInt(txtMaHd.getText()));
           int j= HoaDonDAO.xoaHD(Integer.parseInt(txtMaHd.getText()));
            if(i>0 && j>0){
                JOptionPane.showMessageDialog(rootPane, "Hủy đơn hàng thà1nh công");
                cleartxt();
                dtm.setRowCount(0);
            }
            
        
        }   
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnThemKHMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKHMActionPerformed
        // TODO add your handling code here:
        frmAddKH kh= new frmAddKH();
        kh.setVisible(true);
        kh.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
    }//GEN-LAST:event_btnThemKHMActionPerformed

    private void menuQLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLNVActionPerformed
        // TODO add your handling code here:
        frmNhanVien nv= new frmNhanVien();
        nv.setVisible(true);
        nv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_menuQLNVActionPerformed

    private void menuQLSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuQLSPActionPerformed
        // TODO add your handling code here:
        frmSanPham sp= new frmSanPham();
        sp.setVisible(true);
        sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_menuQLSPActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        LichSuGD LS= new LichSuGD();
        LS.setVisible(true);
        LS.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        frmLogin Login= new frmLogin();
        Login.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void menu_NhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_NhapHangActionPerformed
        // TODO add your handling code here:
        frmNhapHang PN=new frmNhapHang(LayNhanVien().getMaNV());
        PN.setVisible(true);
        PN.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_menu_NhapHangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemKHM;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbTenNhanVien;
    private javax.swing.JMenuItem menuQLNV;
    private javax.swing.JMenuItem menuQLSP;
    private javax.swing.JMenuItem menu_NhapHang;
    private javax.swing.JTable tbGioHang;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtMaHd;
    private javax.swing.JTextField txtNVLap;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtThanhTien;
    // End of variables declaration//GEN-END:variables
}

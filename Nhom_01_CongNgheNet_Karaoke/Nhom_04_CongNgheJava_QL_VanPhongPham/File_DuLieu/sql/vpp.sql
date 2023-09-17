CREATE DATABASE QUANLY_VPP
GO 
USE QUANLY_VPP
GO 



CREATE TABLE LOAISP
(
	MALOAISP INT IDENTITY,
	TENLOAISP NVARCHAR(200),
	CONSTRAINT PK_LOAISP PRIMARY KEY(MALOAISP)
)
go
CREATE TABLE SANPHAM
(
	MASP INT IDENTITY,
	TENSP NVARCHAR(200),
	MALOAI INT,
	DONGIA MONEY,
	SOLUONGTON INT,
	CONSTRAINT PK_SP PRIMARY KEY(MASP),
	CONSTRAINT FK_LOAI_SANPHAM FOREIGN KEY (MALOAI) REFERENCES LOAISP (MALOAISP)
)

CREATE TABLE NHACUNGCAP
(
	MANCC INT IDENTITY,
	TENNCC NVARCHAR(200),
	SDT VARCHAR(20),
	EMAIL VARCHAR(100),
	DIACHI NVARCHAR(300),
	CONSTRAINT PK_NCC PRIMARY KEY(MANCC),
)

CREATE TABLE NHANVIEN 
(
	MANV INT IDENTITY,
	TENNV NVARCHAR(100),
	GIOITINH NVARCHAR(10),
	DIACHI NVARCHAR(200),
	SDT VARCHAR(20),
	CHUCVU INT,
	TENDN VARCHAR(50) unique,
	MK VARCHAR(50),
	CONSTRAINT PK_NV PRIMARY KEY(MANV)
)

CREATE TABLE PHIEUNHAP
(
	MAPN INT IDENTITY,
	MANCC INT,
	MANV INT,
	NGAYLAP DATE,
	TONGTIEN MONEY,
	CONSTRAINT PK_PHIEUNHAP PRIMARY KEY(MAPN),
	CONSTRAINT FK_PHIEUNHAP_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN (MANV),
	CONSTRAINT FK_PHIEUNHAP_NHACUNGCAP FOREIGN KEY (MANCC) REFERENCES NHACUNGCAP (MANCC),
)
CREATE TABLE CHITIETPHIEUNHAP
(
	MACTPN INT IDENTITY,
	MAPN INT,
	MASP INT,
	SOLUONG INT,
	THANHTIEN MONEY,
	CONSTRAINT PK_CTPHIEUNHAP PRIMARY KEY(MACTPN),
	CONSTRAINT FK_PHIEUNHAP_CTPN FOREIGN KEY (MAPN) REFERENCES PHIEUNHAP (MAPN),
	CONSTRAINT FK_SP_CTPN FOREIGN KEY (MASP) REFERENCES SANPHAM (MASP),
)

CREATE TABLE KHACHHANG 
(
	MAKH INT IDENTITY,
	TENKH NVARCHAR(100),
	SDT VARCHAR(20),
	GIOITINH NVARCHAR(10),
	CONSTRAINT PK_KH PRIMARY KEY(MAKH),
)

CREATE TABLE HOADON 
(
	MAHD INT IDENTITY,
	MAKH INT,
	MANV INT,
	NGAYLAP DATE,
	GIAMGIA MONEY,
	TONGTIEN MONEY,
	CONSTRAINT PK_HD PRIMARY KEY (MAHD),
	CONSTRAINT FK_HOADON_KHACHHANG FOREIGN KEY (MAKH) REFERENCES KHACHHANG (MAKH),
	CONSTRAINT FK_HOADON_NHANVIEN FOREIGN KEY (MANV) REFERENCES NHANVIEN (MANV),
)
--ALTER TABLE HOADON
--  DROP COLUMN TONGTIEN;
--ALTER TABLE HOADON
--  ADD GIAMGIA MONEY,
--		TONGTIEN MONEY

CREATE TABLE CHITIETHD
(
		MACTHD INT IDENTITY,
		MAHD INT, 
		MASP INT,
		SOLUONG INT,
		THANHTIEN MONEY,
		CONSTRAINT PK_CHITIETHD PRIMARY KEY (MACTHD),
		CONSTRAINT FK_CHIIETHD_HOADON FOREIGN KEY (MAHD) REFERENCES HOADON (MAHD),
		CONSTRAINT FK_CHITIETHD_SANPHAM FOREIGN KEY (MASP) REFERENCES SANPHAM (MASP),
)

------------------------------------------------------------nhập dữ liệu---------------------------------------------------------------


select*from nhanvien
--thêm dữ liệu vào bảng loaisp
select*from sanpham
insert into LOAISP values 
(N'Bút viết'),
(N'Tập - sổ'),
(N'Máy tính'),
(N'Bảng'),
(N'Phấn'),
(N'Lau bảng'),
(N'Bấm kim'),
(N'Thước'),
(N'Dao kéo cắt giấy'),
(N'Bìa hồ sơ')

--thêm dữ liệu vào bảng sanpham 

insert into sanpham values 
(N'Bút chì bấm Pentel 05mm', 1, 55000, 9, N'butchikimbam05mm.jpg'),
(N'Bút chì 24 màu', 1, 55000, 12, N'butchi24mau.jpg'),
(N'Bút chì sáp 12 màu', 1, 75000, 10, N'butchisap12mau.jpg'),
(N'Bút chì Koh I Noor 2B', 1, 8000, 28, N'butchikohinoor2b.jpg'),
(N'Bút chì 12 màu', 1, 30000, 12, N'butchi12mau.jpg'),
(N'Bút bi TL xanh', 1, 3500, 50, N'butbitlxanh'),
(N'Bút bi TL đen', 1, 3500, 50, N'butbitlden.jpg'),
(N'Bút bi Ấn Độ xanh', 1, 3000, 50, N'butbiandoxanh.jpg'),
(N'Bút bi Ấn Độ đen', 1, 3000, 50, N'butbiandoden.jpg'),
(N'Bút bi Uniball Laknock đỏ', 2, 25000, 30, N'butbiuniballlaknockdo.jpg'),
(N'Tập Thuận Tiến 100 trang', 2, 10000, 100, N'tapthuantien100trang.jpg'),
(N'Vinabook 100 trang', 2, 10000, 120, N'vinabook100trang.jpg'),
(N'Vinabook 200 trang', 2, 15000, 150, N'vinabook200trang.jpg'),
(N'Sổ da Viaone', 2, 70000, 80, N'sodaviaone.jpg'),
(N'Sổ lò xo Lax A4', 2, 38000, 110, N'soloxolaxa4.jpg'),
(N'Casio FX-570VN', 3, 485000, 65, N'casiofx570vn.jpg'),
(N'Casio FX-500MS', 3, 280000, 48, N'casiofx500ms.jpg'),
(N'Vinacal 570-ESPlus', 3, 420000, 32, N'vinacal570esplus.jpg'),
(N'Bảng trắng 0.6x0.4', 4, 70000, 25, N'bangtran06x04.jpg'),
(N'Bảng nhung 0.6x0.4', 4, 120000, 25, N'bangnhung06x04.jpg'),
(N'Phấn trắng', 5, 7000, 80, N'phantrang.jpg'),
(N'Phấn màu', 5, 7000, 80, N'phanmau.jpg'),
(N'Khăn lau bảng', 6, 7000, 100, N'khanlaubang.jpg'),
(N'Mút xốp lau bảng', 6, 5000, 100, N'mutxoplaubang.jpg'),
(N'Lau bảng xukiva', 6, 12000, 100, N'laubangxukiva.jpg'),
(N'Bấm kim Kanex HD10 - 10 tờ', 7, 20000, 32, N'bamkim10to.jpg'),
(N'Bấm kim Kwtrio 5003 - 240 tờ', 7, 550000, 15, N'bamkim240to.jpg'),
(N'Thước dẻo WinQ 30cm', 8, 5000, 95, N'thuocdeo30cm.jpg'),
(N'Thước êke', 8, 5000, 88, N'thuoceke.jpg'),
(N'Thước đo độ', 8, 5000, 56, N'thuocdodo.jpg'),
(N'Thước Parabol WinQ QL-01', 8, 4000, 120, N'thuocparabol.jpg'),
(N'Thước vẽ kỹ thuật, Template Ruler C-2007', 8, 27000, 100, N'thuocvekythuat.jpg'),
(N'Kéo văn phòng Stacom F102 - 180mm', 9, 25000, 220, N'keovanphong.jpg'),
(N'Dao rọc giấy mini M&G 2243 hình củ cà rốt', 9, 14000, 190, N'daorocgiaycarot.jpg'),
(N'Dao rọc giấy mini M&G 2281', 9, 12000, 90, N'daorocmini.jpg'),
(N'Kéo nhỏ M&G 2229', 9, 12000, 70, N'keonho_mg2229.jpg'),
(N'Bìa còng KING JIM khổ F4 - 7F', 10, 50000, 40, N'biacongkingjimf4_7f.jpg'),
(N'Bìa cây A4 gáy lớn', 10, 6000, 100, N'biacaya4gaylon.jpg'),
(N'Bìa cây A4 gáy nhỏ', 10, 4000, 100, N'biacaya4gaynho.jpg'),
(N'Bìa nút khổ A5 không chữ', 10, 2500, 200, N'bianutkhoa5khongchu.jpg')
--(N'Bút chì bấm Pentel 05mm', 1, 55000, 9),
--(N'Bút chì 24 màu', 1, 55000, 12),
--(N'Bút chì sáp 12 màu', 1, 75000, 10),
--(N'Bút chì Koh I Noor 2B', 1, 8000, 28),
--(N'Bút chì 12 màu', 1, 30000, 12),
--(N'Bút bi TL xanh', 1, 3500, 50),
--(N'Bút bi TL đen', 1, 3500, 50),
--(N'Bút bi Ấn Độ xanh', 1, 3000, 50),
--(N'Bút bi Ấn Độ đen', 1, 3000, 50),
--(N'Bút bi Uniball Laknock đỏ', 2, 25000, 30),
--(N'Tập Thuận Tiến 100 trang', 2, 10000, 100),
--(N'Vinabook 100 trang', 2, 10000, 120),
--(N'Vinabook 200 trang', 2, 15000, 150),
--(N'Sổ da Viaone', 2, 70000, 80),
--(N'Sổ lò xo Lax A4', 2, 38000, 110),
--(N'Casio FX-570VN', 3, 485000, 65),
--(N'Casio FX-500MS', 3, 280000, 48),
--(N'Vinacal 570-ESPlus', 3, 42000, 32),
--(N'Bảng trắng 0.6x0.4', 4, 70000, 25),
--(N'Bảng nhung 0.6x0.4', 4, 120000, 25),
--(N'Phấn trắng', 5, 7000, 80),
--(N'Phấn màu', 5, 7000, 80),
--(N'Khăn lau bảng', 6, 7000, 100),
--(N'Mút xốp lau bảng', 6, 5000, 100),
--(N'Lau bảng xukiva', 6, 12000, 100),
--(N'Bấm kim Kanex HD10 - 10 tờ', 7, 20000, 32),
--(N'Bấm kim Kwtrio 5003 - 240 tờ', 7, 550000, 15),
--(N'Thước dẻo WinQ 30cm', 8, 5000, 95),
--(N'Thước êke', 8, 5000, 88),
--(N'Thước đo độ', 8, 5000, 56),
--(N'Thước Parabol WinQ QL-01', 8, 4000, 120),
--(N'Thước vẽ kỹ thuật, Template Ruler C-2007', 8, 27000, 100),
--(N'Kéo văn phòng Stacom F102 - 180mm', 9, 25000, 220),
--(N'Dao rọc giấy mini M&G 2243 hình củ cà rốt', 9, 14000, 190),
--(N'Dao rọc giấy mini M&G 2281', 9, 12000, 90),
--(N'Kéo nhỏ M&G 2229', 9, 12000, 70),
--(N'Bìa còng KING JIM khổ F4 - 7F', 10, 50000, 40),
--(N'Bìa cây A4 gáy lớn', 10, 6000, 100),
--(N'Bìa cây A4 gáy nhỏ', 10, 4000, 100),
--(N'Bìa nút khổ A5 không chữ', 10, 2500, 200)




select count(*) from NHANVIEN where TENTAIKHOAN='lqt' and MATKHAU='123'



--thêm dữ liệu vào bảng nhanvien 

insert into nhanvien values 
(N'Nguyễn Thành Văn', N'Nam', N'TP.HCM', '0383633081',  1, 'admin', '123'),
(N'Lư Quang Trực', N'Nam', N'TP.HCM', '0123321223',  1, 'lqt', '123'),
(N'Nguyễn Hứa Hiền Vinh', N'Nam', N'TP.HCM', '0883311233',  0, 'vinh', '123'),
(N'Trương Đình Văn', N'Nam', N'TP.HCM', '0798889992',  0, 'uservan', '123'),
(N'Nguyễn Tuấn Kiệt', N'Nam', N'TP.HCM', '0789632456', 0, 'userkiet', '123'),
(N'Lê Quang Tuấn', N'Nam', N'TPHCM', '0792666144', 0, 'usertuan', '123'),
(N'Trần Khánh Ngọc', N'Nữ', N'TPHCM', '0785568156', 0, 'userngoc', '123'),
(N'Nguyễn Nhật My', N'Nữ', N'TPHCM', '0125325635', 0, 'usermy', '123'),
(N'Nguyễn Huỳnh Ngọc Thư ', N'Nữ', N'TPHCM', '0987656565', 0, 'userthu', '123'),
(N'Bùi Thị Thu Linh', N'Nữ', N'TPHCM', '0123876598', 0, 'userlinh', '123')

--thêm dữ liệu vào bảng khachhang

insert into khachhang values 
(N'Nguyễn Trọng Nghĩa', '0911422428', N'Nam'),
(N'Huỳnh Anh Triệu', '0931748456', N'Nam'),
(N'Huỳnh Tấn Kiệt', '0343592659', N'Nam'),
(N'Nguyễn Nhật Hào', '0385120406', N'Nam'),
(N'Nguyễn Thị Thu Ngân', '0396219444', N'Nữ'),
(N'Đỗ Thị Hải Yến', '0707796677', N'Nữ'),
(N'Nguyễn Thị Quỳnh Như', '0783536600', N'Nữ'),
(N'Nguyễn Quốc Trung', '0793453322', 'Nam'),
(N'Nguyễn Nhật Trường', '078990077', N'Nam'),
(N'Nguyễn Đình Khả', '0703227711', N'Nam')

--thêm dữ liệu vào bảng nhacungcap

--	TENNCC NVARCHAR(200),
--	SDT VARCHAR(20),
--	EMAIL VARCHAR(100),
--	DIACHI NVARCHAR(300),

insert into nhacungcap values 
(N'VĂN PHÒNG PHẨM KHANG MINH', '0944697678', 'tbvpkhangminh@gmail.com', N'70/16 Hiệp Nhất, Phường 4, Quận Tân Bình, TP. Hồ Chí Minh'),
(N'VĂN PHÒNG PHẨM ANH ĐỨC', ' 0983481825', 'nguyenanhtuanhcmvn@gmail.com', N'490/41 Lê Văn Sỹ, Phường 14, Quận 3, TP Hồ Chí Minh'),
(N'VĂN PHÒNG PHẨM 24', '0985180684', ' minhanh@vanphongpham24.com', N'Số 218 Lô C5 Khu Đô Thị Đại Kim, Quận Hoàng Mai, TP Hà Nội'),
(N'VĂN PHÒNG PHẨM HÙNG THUẬN PHÁT', '0908150636', 'htp@hungthuanphat.vn', N'104/3 Mai Thị Lựu, Phường Đa Kao, Quận 1, TP Hồ Chí Minh'),
(N'VĂN PHÒNG PHẨM KHANG LÊ', '0935508693', 'vppkhangle@gmail.com', N'Số 60A, Đường Tam Đông 21, Xã Thới Tam Thôn, Huyện Hóc Môn, Tp Hồ Chí Minh')

--thêm dữ liệu vào bảng hoadon 

set dateformat dmy		
insert into hoadon values 
('HD001', '29/12/2020', NULL, 'KH001', 'NV001'),
('HD002', '01/02/2021', NULL, 'KH002', 'NV001'),
('HD003', '22/02/2021', NULL, 'KH003', 'NV002'),
('HD004', '29/03/2021', NULL, 'KH004', 'NV002'),
('HD005', '29/01/2021', NULL, 'KH005', 'NV003'),
('HD006', '16/05/2021', NULL, 'KH006', 'NV003'),
('HD007', '21/06/2021', NULL, 'KH007', 'NV004'),
('HD008', '08/03/2021', NULL, 'KH008', 'NV004'),
('HD009', '19/04/2021', NULL, 'KH009', 'NV005'),
('HD010', '22/07/2021', NULL, 'KH010', 'NV005')

--thêm dữ liệu vào bảng chitiethd 

insert into chitiethd values 
('HD001', 'SP001', 1, NULL, NULL),
('HD001', 'SP002', 1, NULL, NULL),
('HD001', 'SP003', 2, NULL, NULL),
('HD001', 'SP004', 3, NULL, NULL),
('HD001', 'SP005', 3, NULL, NULL),
('HD001', 'SP006', 2, NULL, NULL),
('HD002', 'SP007', 1, NULL, NULL),
('HD002', 'SP008', 1, NULL, NULL),
('HD002', 'SP009', 1, NULL, NULL),
('HD003', 'SP010', 1, NULL, NULL),
('HD003', 'SP011', 2, NULL, NULL),
('HD004', 'SP012', 1, NULL, NULL),
('HD004', 'SP013', 1, NULL, NULL),
('HD005', 'SP014', 1, NULL, NULL),
('HD006', 'SP015', 2, NULL, NULL),
('HD006', 'SP016', 1, NULL, NULL),
('HD007', 'SP017', 3, NULL, NULL),
('HD007', 'SP018', 1, NULL, NULL),
('HD009', 'SP019', 1, NULL, NULL),
('HD009', 'SP020', 1, NULL, NULL),
('HD008', 'SP021', 1, NULL, NULL),
('HD008', 'SP022', 3, NULL, NULL),
('HD010', 'SP023', 2, NULL, NULL),
('HD010', 'SP024', 1, NULL, NULL),
('HD005', 'SP025', 1, NULL, NULL),
('HD010', 'SP026', 2, NULL, NULL),
('HD009', 'SP027', 6, NULL, NULL),
('HD006', 'SP028', 3, NULL, NULL),
('HD005', 'SP029', 2, NULL, NULL),
('HD002', 'SP030', 1, NULL, NULL)

--------CẬP NHẬT CỘT GIABAN TRONG BẢNG CHITIETHD DỰA VÀO GIÁ BÁN CỦA SẢN PHẨM
declare cursorgiaban cursor 
for select masp, giatien from sanpham
open cursorgiaban
declare @masp char(20)
declare @giatien money
fetch next from cursorgiaban into @masp, @giatien
while (@@FETCH_STATUS = 0)
begin 
	update chitiethd set giaban = (select giatien from sanpham where chitiethd.masp = sanpham.masp) 
	where @masp = chitiethd.masp
	fetch next from cursorgiaban into @masp, @giatien
end 
close cursorgiaban
deallocate cursorgiaban

select * from chitiethd

--------CẬP NHẬT CỘT THÀNH TIỀN TRONG BẢNG CHITIETHD DỰA VÀO SOLUONG VÀ GIABAN
declare cursorthanhtien cursor 
for select soluong, giaban from CHITIETHD
open cursorthanhtien
declare @sluong int
declare @giaban money
fetch next from cursorthanhtien into @sluong, @giaban
while (@@FETCH_STATUS = 0)
begin 
		update chitiethd set thanhtien =  (@sluong * @giaban) where (@sluong = SOLUONG and @giaban = giaban)
		fetch next from cursorthanhtien into @sluong, @giaban
end 
close cursorthanhtien
deallocate cursorthanhtien

select * from CHITIETHD

--------CẬP NHẬT CỘT TIENBAN TRONG HOADON
declare cursortienban cursor 
for select MAHD from CHITIETHD
open cursortienban
declare @mahd char(20) 
fetch next from cursortienban into @mahd
while (@@FETCH_STATUS = 0)
begin 
	update HOADON set tongtien = (select sum(thanhtien) from CHITIETHD where HOADON.MAHD = CHITIETHD.MAHD)
	 where MAHD = @mahd 
	 fetch next from cursortienban into @mahd
end 
close cursortienban
deallocate cursortienban

select * from hoadon
select * from chitiethd






select hd.MAHD,nv.TENNV,kh.TENKH,hd.NGAYLAP,sp.TENSP,sp.DONGIA,cthd.SOLUONG,cthd.THANHTIEN,hd.GIAMGIA,hd.TONGTIEN  
                    from HOADON hd, CHITIETHD cthd, SANPHAM sp, KHACHHANG kh, NHANVIEN nv  
                    where hd.MAHD=cthd.MAHD  
                    and cthd.MASP=sp.MASP  
                    and hd.MAKH=kh.MAKH  
                    and nv.MANV=hd.MANV
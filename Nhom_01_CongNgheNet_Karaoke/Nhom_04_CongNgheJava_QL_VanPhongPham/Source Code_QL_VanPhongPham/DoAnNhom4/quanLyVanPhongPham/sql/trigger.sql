select * from CHITIETHD 
go
CREATE TRIGGER trg_AfterInsert_ChiTietHoaDon
ON ChiTietHD
AFTER INSERT
AS
BEGIN
    DECLARE @MaHoaDon VARCHAR(20)
    DECLARE @TongTien MONEY
    SELECT @MaHoaDon = MAHD FROM inserted
    SELECT @TongTien = SUM( SOLUONG* DonGia) FROM ChiTietHD WHERE MAHD = @MaHoaDon
    UPDATE HoaDon SET TongTien = @TongTien WHERE MaHoaDon = @MaHoaDon
END
go
CREATE TRIGGER trg_CapNhatThanhTien ON CHITIETHD
AFTER INSERT, UPDATE
AS
BEGIN
    UPDATE CHITIETHD SET THANHTIEN = CHITIETHD.SOLUONG * (SELECT DONGIA FROM SANPHAM WHERE MASP = inserted.MASP)
    FROM CHITIETHD
    INNER JOIN inserted ON CHITIETHD.MACTHD = inserted.MACTHD
END

----- ==================
go
alter TRIGGER trg_CapNhatThanhTienHoaDon
ON CHITIETHD
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    DECLARE @MAHD INT;
    SET @MAHD = (SELECT MAHD FROM inserted);

    UPDATE HOADON
    SET TONGTIEN = (SELECT SUM(THANHTIEN)-GIAMGIA FROM CHITIETHD WHERE MAHD = @MAHD)
    WHERE MAHD = @MAHD;
END;

UPDATE HOADON SET TONGTIEN = 0 WHERE TONGTIEN IS NULL;
UPDATE HOADON SET GIAMGIA = 0 WHERE GIAMGIA IS NULL;
select * from HOADON
select * from CHITIETHD

set dateformat DMY
insert into HOADON(MAKH,MANV,NGAYLAP) values (1,1,'23-08-2023')
update HOADON set GIAMGIA=10000 where MAHD=5
create trigger Tg_UpdateTTHD
on HoaDon
after update 
as

begin
	declare @mahd int ;
	set @mahd= (select mahd from inserted)
	update HOADON set TONGTIEN=TONGTIEN-GIAMGIA where MAHD=@mahd

end

DECLARE @MAHD INT;
SET @MAHD = SCOPE_IDENTITY();
SELECT @MAHD as MA;

SELECT TOP 1 MAHD FROM HOADON ORDER BY MAHD DESC
select * from KHACHHANG
insert into KHACHHANG(TENKH,SDT,GT) values(N'text','123123','Nam');
insert into KHACHHANG(TENKH,SDT,GIOITINH) values(N'Nguyễn Thế Dũng','123123','Nam');



----- kiểm tra login
go
create proc loginSP
@username nvarchar(10),
@pass nvarchar(10)
as
begin 
	 if exists (select * from NHANVIEN where TENDN = @Username and MK = @pass and CHUCVU = 1)
        select 1 as code
    else if exists (select * from NHANVIEN where TENDN = @Username and MK = @pass and CHUCVU= 0)
        select 0 as code
    else if exists(select * from NHANVIEN where TENDN = @Username and MK != @pass) 
        select 2 as code
    else select 3 as code
end
go

exec loginSP 'admin','123'

select * from NHANVIEN

-- cập nhật số lượng tồn kho khi thêm vào chi tiết hd
go
CREATE TRIGGER trg_CapNhatTonKho
ON CHITIETHD
AFTER INSERT, UPDATE
AS
BEGIN
    IF @@ROWCOUNT = 0
        RETURN

    DECLARE @MASP INT, @SOLUONG INT

    -- Lấy thông tin MASP và SOLUONG của bản ghi được thêm vào hoặc cập nhật
    SELECT @MASP = MASP, @SOLUONG = SOLUONG
    FROM inserted

    -- Tính toán số lượng tồn kho mới của sản phẩm
    DECLARE @TONKHO INT
    SELECT @TONKHO = SOLUONGTON - @SOLUONG
    FROM SANPHAM
    WHERE MASP = @MASP

    -- Cập nhật lại số lượng tồn kho trong bảng SANPHAM
    UPDATE SANPHAM
    SET SOLUONGTON = @TONKHO
    WHERE MASP = @MASP
END
-- cập nhật khi nhập hàng
go
CREATE TRIGGER trg_NhapHang
ON CHITIETPHIEUNHAP
AFTER INSERT
AS
BEGIN
    DECLARE @MASP INT, @SOLUONG INT

    SELECT @MASP = MASP, @SOLUONG = SOLUONG FROM INSERTED

    UPDATE SANPHAM SET SOLUONGTON = SOLUONGTON + @SOLUONG WHERE MASP = @MASP
END
-- cap nhat tong tien cua phieu nhap
go
CREATE TRIGGER trg_CapNhatTongTienPhieuNhap
ON CHITIETPHIEUNHAP
AFTER INSERT
AS
BEGIN
    -- Lấy mã phiếu nhập vừa được thêm mới
    DECLARE @MaPN INT
    SELECT @MaPN = MAPN FROM inserted
    
    -- Cập nhật tổng tiền của phiếu nhập
    UPDATE PHIEUNHAP
    SET TONGTIEN = (SELECT SUM(THANHTIEN) FROM CHITIETPHIEUNHAP WHERE MAPN = @MaPN)
    WHERE MAPN = @MaPN
END
-- trigger tim kiem san pham
alter PROCEDURE SearchProduct
    @keyword NVARCHAR(200)
AS
BEGIN
    SELECT MASP, TENSP, MALOAI, DONGIA, SOLUONGTON
    FROM SANPHAM
    WHERE TENSP LIKE '%'+@keyword+'%'
END

exec SearchProduct N'Bút'
 SELECT MASP, TENSP, MALOAI, DONGIA, SOLUONGTON
    FROM SANPHAM
    WHERE TENSP LIKE N'%Bút%'

--triger cap nhat ton kho khi huy chitiethd
CREATE TRIGGER TR_CHITIETHD_DEL
ON CHITIETHD
AFTER DELETE
AS
BEGIN
	DECLARE @MASP INT, @SOLUONG INT;
	SELECT @MASP = MASP, @SOLUONG = SOLUONG FROM deleted;
	UPDATE SANPHAM SET SOLUONGTON = SOLUONGTON + @SOLUONG WHERE MASP = @MASP;
END
--=====================
--proc lấy hóa đơn theo tgian
CREATE PROCEDURE usp_LayHoaDonTrongKhoangThoiGian
    @NgayBatDau DATE,
    @NgayKetThuc DATE
AS
BEGIN
    SELECT *
    FROM HOADON
    WHERE NGAYLAP BETWEEN @NgayBatDau AND @NgayKetThuc
END
select * from hoadon
set dateformat DMY
exec usp_LayHoaDonTrongKhoangThoiGian '7/6/2022','7/6/2022'
select * from NHANVIEN

delete from SANPHAM where MASP=

UPDATE SANPHAM set TENSP=N'',MALOAI=,DONGIA=,SOLUONGTON= where MASP=

select * from NHACUNGCAP
set dateformat DMY insert into PHIEUNHAP(MANCC,MANV,NGAYLAP) values
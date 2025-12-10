package entity;

import java.time.LocalDate;

public class LichLamViec {
//	private String maLich;
    private LocalDate ngayLamViec;
    private NhanVien nhanVien;
    private CaLamViec caLamViec;
    private boolean trangThai; 

    public LichLamViec() {
    }
    
    

	public LichLamViec(LocalDate ngayLamViec, NhanVien nhanVien, CaLamViec caLamViec,
			boolean trangThai) {
		super();
		this.ngayLamViec = ngayLamViec;
		this.nhanVien = nhanVien;
		this.caLamViec = caLamViec;
		this.trangThai = trangThai;
	}

	public CaLamViec getCaLamViec() {
        return caLamViec;
    }

    public LocalDate getNgayLamViec() {
        return ngayLamViec;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setCaLamViec(CaLamViec caLamViec) {
        this.caLamViec = caLamViec;
    }

    public void setNgayLamViec(LocalDate ngayLamViec) {
        this.ngayLamViec = ngayLamViec;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

	@Override
	public String toString() {
		return "LichLamViec [ngayLamViec=" + ngayLamViec + ", nhanVien=" + nhanVien
				+ ", caLamViec=" + caLamViec + ", trangThai=" + trangThai + "]";
	}
    
}

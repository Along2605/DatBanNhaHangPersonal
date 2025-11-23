package entity;

import java.time.LocalDateTime;

public class NhatKyThaoTac {
    private int maLog;
    private String maNV;
    private LoaiThaoTac loaiThaoTac;
    private String tenBang;
    private String maDoiTuong;
    private String noiDungCu;
    private String noiDungMoi;
    private LocalDateTime thoiGian;
    private String ghiChu;
    
    // Enum cho loại thao tác
    public enum LoaiThaoTac {
        THEM("THEM"),
        SUA("SUA"),
        XOA("XOA");
        
        private String value;
        LoaiThaoTac(String value) { this.value = value; }
        public String getValue() { return value; }
    }
    
    // Constructors
    public NhatKyThaoTac() {
        this.thoiGian = LocalDateTime.now();
    }
    
    public NhatKyThaoTac(String maNV, LoaiThaoTac loaiThaoTac, 
            String tenBang, String maDoiTuong) {
        this();
        this.maNV = maNV;
        this.loaiThaoTac = loaiThaoTac;
        this.tenBang = tenBang;
        this.maDoiTuong = maDoiTuong;
    }
    
    // Full constructor
    public NhatKyThaoTac(int maLog, String maNV, LoaiThaoTac loaiThaoTac,
            String tenBang, String maDoiTuong, String noiDungCu, 
            String noiDungMoi, LocalDateTime thoiGian, String ghiChu) {
        this.maLog = maLog;
        this.maNV = maNV;
        this.loaiThaoTac = loaiThaoTac;
        this.tenBang = tenBang;
        this.maDoiTuong = maDoiTuong;
        this.noiDungCu = noiDungCu;
        this.noiDungMoi = noiDungMoi;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
    }

    // Getters & Setters
    public int getMaLog() { return maLog; }
    public void setMaLog(int maLog) { this.maLog = maLog; }
    
    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }
    
    public LoaiThaoTac getLoaiThaoTac() { return loaiThaoTac; }
    public void setLoaiThaoTac(LoaiThaoTac loaiThaoTac) { this.loaiThaoTac = loaiThaoTac; }
    
    public String getTenBang() { return tenBang; }
    public void setTenBang(String tenBang) { this.tenBang = tenBang; }
    
    public String getMaDoiTuong() { return maDoiTuong; }
    public void setMaDoiTuong(String maDoiTuong) { this.maDoiTuong = maDoiTuong; }
    
    public String getNoiDungCu() { return noiDungCu; }
    public void setNoiDungCu(String noiDungCu) { this.noiDungCu = noiDungCu; }
    
    public String getNoiDungMoi() { return noiDungMoi; }
    public void setNoiDungMoi(String noiDungMoi) { this.noiDungMoi = noiDungMoi; }
    
    public LocalDateTime getThoiGian() { return thoiGian; }
    public void setThoiGian(LocalDateTime thoiGian) { this.thoiGian = thoiGian; }
    
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}
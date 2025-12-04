package gui;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.MonAnDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MonAn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TraCuuMonAn extends JPanel {
    private static final long serialVersionUID = 1L;

    private final MonAnDAO monAnDAO = new MonAnDAO();
    private final ChiTietHoaDonDAO cthdDAO = new ChiTietHoaDonDAO();
    private final HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private final DecimalFormat df = new DecimalFormat("#,##0 đ");

    private List<MonAn> danhSachMonAnHienTai;
    private List<MonAn> listGoc;
    private final Map<String, Integer> soLuongBanMap = new HashMap<>();

    private JPanel panelCenterCenter;

    public TraCuuMonAn() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize((int) screenSize.getWidth(), (int) (screenSize.getHeight() - 75));
        setLayout(null);
        setBackground(new Color(245, 245, 245));

        initUI();
        loadMonAnFromDB();
        tinhSoLuongBanCacMon();
    }

    private void initUI() {
        // Tiêu đề
        JLabel lblTieuDe = new JLabel("Danh sách món ăn");
        lblTieuDe.setBounds(550, 0, 455, 100);
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 26));
        add(lblTieuDe);

        // ==================== PANEL TRÁI - DANH MỤC ====================
        JPanel panelWest = new JPanel();
        panelWest.setBounds(10, 108, 246, 517);
        panelWest.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1),
                new EmptyBorder(15, 15, 15, 15)));
        panelWest.setLayout(null);
        add(panelWest);

        // Các button danh mục (giữ nguyên tọa độ cũ)
        JButton btnTatCaDanhMuc = createCleanButton("Tất cả danh mục", 39, 11, 170, 34, 17, true);
        JButton btnMonAnChinh = createCleanButton("Món ăn chính", 77, 49, 129, 30, 14, true);
        JButton btnTokbokki = createCleanButton("Tokbokki", 117, 82, 89, 23, 11, false);
        JButton btnCom = createCleanButton("Cơm", 117, 106, 89, 23, 11, false);
        JButton btnLau = createCleanButton("Lẩu", 117, 132, 89, 23, 11, false);
        JButton btnMi = createCleanButton("Mì", 117, 156, 89, 23, 11, false);
        JButton btnKhac = createCleanButton("Khác", 117, 180, 89, 23, 11, false);
        JButton btnDoUong = createCleanButton("Đồ uống", 77, 205, 129, 30, 14, true);
        JButton btnNuocSuoi = createCleanButton("Nước suối", 117, 237, 89, 23, 11, false);
        JButton btnTraSua = createCleanButton("Trà sữa", 117, 262, 89, 23, 11, false);
        JButton btnRuou = createCleanButton("Rượu", 117, 288, 89, 23, 11, false);
        JButton btnKhac2 = createCleanButton("Khác", 117, 317, 89, 23, 11, false);
        JButton btnMonAnKem = createCleanButton("Món ăn kèm", 77, 345, 129, 30, 14, true);
        JButton btnKimbab = createCleanButton("Kimbab", 117, 380, 89, 23, 11, false);
        JButton btnMandu = createCleanButton("Mandu", 117, 403, 89, 23, 11, false);
        JButton btnSalad = createCleanButton("Salad", 117, 427, 89, 23, 11, false);
        JButton btnKhac3 = createCleanButton("Khác", 117, 453, 89, 23, 11, false);

        panelWest.add(btnTatCaDanhMuc); panelWest.add(btnMonAnChinh); panelWest.add(btnTokbokki);
        panelWest.add(btnCom); panelWest.add(btnLau); panelWest.add(btnMi); panelWest.add(btnKhac);
        panelWest.add(btnDoUong); panelWest.add(btnNuocSuoi); panelWest.add(btnTraSua);
        panelWest.add(btnRuou); panelWest.add(btnKhac2); panelWest.add(btnMonAnKem);
        panelWest.add(btnKimbab); panelWest.add(btnMandu); panelWest.add(btnSalad); panelWest.add(btnKhac3);

        // Gắn sự kiện (giữ nguyên logic cũ)
        btnTatCaDanhMuc.addActionListener(e -> locTatCaDanhMuc());
        btnMonAnChinh.addActionListener(e -> locTheoMonAnChinh());
        btnTokbokki.addActionListener(e -> locTheoTuKhoa("Tokbokki"));
        btnCom.addActionListener(e -> locTheoTuKhoa("Cơm"));
        btnLau.addActionListener(e -> locTheoTuKhoa("Lẩu"));
        btnMi.addActionListener(e -> locTheoTuKhoa("Mì"));
        btnKhac.addActionListener(e -> locMonKhac("MC", List.of("Tokbokki", "Cơm", "Lẩu", "Mì")));
        btnDoUong.addActionListener(e -> locTheoDoUong());
        btnNuocSuoi.addActionListener(e -> locTheoTuKhoa("Nước suối"));
        btnTraSua.addActionListener(e -> locTheoTuKhoa("Trà sữa"));
        btnRuou.addActionListener(e -> locTheoTuKhoa("Rượu"));
        btnKhac2.addActionListener(e -> locMonKhac("DO", List.of("Nước suối", "Trà sữa", "Rượu")));
        btnMonAnKem.addActionListener(e -> locTheoMonAnKem());
        btnKimbab.addActionListener(e -> locTheoTuKhoa("Kimbab"));
        btnMandu.addActionListener(e -> locTheoTuKhoa("Mandu"));
        btnSalad.addActionListener(e -> locTheoTuKhoa("Salad"));
        btnKhac3.addActionListener(e -> locMonKhac("MK", List.of("Kimbab", "Mandu", "Salad")));

        // ==================== PANEL GIỮA ====================
        JPanel panelCenter = new JPanel();
        panelCenter.setBounds(266, 78, 1100, 457);
        panelCenter.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panelCenter.setLayout(null);
        add(panelCenter);

        JPanel panelCenterNorth = new JPanel();
        panelCenterNorth.setBounds(28, 11, 1044, 96);
        panelCenterNorth.setLayout(null);
        panelCenter.add(panelCenterNorth);

        JLabel lblSapXepTheo = new JLabel("Sắp xếp theo");
        lblSapXepTheo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSapXepTheo.setBounds(38, 34, 113, 22);
        panelCenterNorth.add(lblSapXepTheo);

        JButton btnPhoBien = createCleanButton("Phổ biến", 147, 25, 108, 43, 16, false);
        btnPhoBien.addActionListener(e -> sapXepTheoDoPhoBien());
        panelCenterNorth.add(btnPhoBien);

        JButton btnMoiNhat = createCleanButton("Mới nhất", 276, 26, 108, 43, 16, false);
        btnMoiNhat.addActionListener(e -> sapXepTheoMaMonGiamDan());
        panelCenterNorth.add(btnMoiNhat);

        String[] giaOptions = {"Giá", "Giá: Thấp đến cao", "Giá: Cao đến thấp"};
        JComboBox<String> comboBoxGia = new JComboBox<>(giaOptions);
        comboBoxGia.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        comboBoxGia.setBounds(410, 25, 215, 43);
        comboBoxGia.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1, true));
        comboBoxGia.addActionListener(e -> {
            String selected = (String) comboBoxGia.getSelectedItem();
            if ("Giá: Thấp đến cao".equals(selected)) sapXepTheoGiaTangDan();
            else if ("Giá: Cao đến thấp".equals(selected)) sapXepTheoGiaGiamDan();
        });
        panelCenterNorth.add(comboBoxGia);

        // Khu vực hiển thị món
        panelCenterCenter = new JPanel();
        panelCenterCenter.setLayout(new BoxLayout(panelCenterCenter, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(panelCenterCenter);
        scrollPane.setBounds(76, 118, 996, 379);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        panelCenter.add(scrollPane);

        // ==================== PANEL DƯỚI - TÌM KIẾM ====================
        JPanel panelBottom = new JPanel();
        panelBottom.setBounds(276, 546, 1090, 77);
        panelBottom.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200)),
                new EmptyBorder(15, 15, 15, 15)));
        panelBottom.setLayout(null);
        add(panelBottom);

        JTextArea textAreaTimKiem = new JTextArea();
        textAreaTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        textAreaTimKiem.setBounds(73, 27, 348, 28);
        textAreaTimKiem.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                new EmptyBorder(5, 10, 5, 10)));
        textAreaTimKiem.setLineWrap(true);
        textAreaTimKiem.setWrapStyleWord(true);
        panelBottom.add(textAreaTimKiem);

        JButton btnTimKiem = new JButton();
        btnTimKiem.setBounds(431, 27, 47, 28);
        btnTimKiem.setBorder(BorderFactory.createLineBorder(Color.black));
        btnTimKiem.setIcon(resizeIcon("img/search.png", 20, 20));
        btnTimKiem.setBorderPainted(false);
        btnTimKiem.setContentAreaFilled(false);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.addActionListener(e -> timKiemBangTuKhoa(textAreaTimKiem.getText()));
        panelBottom.add(btnTimKiem);

        JButton btnThemMonAn = createCleanButton("Thêm món ăn", 552, 28, 137, 28, 16, false);
        btnThemMonAn.addActionListener(e -> moPanelThemMonAn());
        panelBottom.add(btnThemMonAn);
    }

    // Button sạch sẽ, bo góc nhẹ
    private JButton createCleanButton(String text, int x, int y, int w, int h, int fontSize, boolean bold) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, fontSize));
        btn.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                new EmptyBorder(5, 10, 5, 10)));
        btn.setFocusPainted(false);
        return btn;
    }

    private ImageIcon resizeIcon(String path, int w, int h) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            return null;
        }
    }

    // Card món ăn đẹp, sạch, hiện đại
    private void themDuLieuVaoPanelCenterCenter(List<MonAn> listMonAn) {
        panelCenterCenter.removeAll();

        for (MonAn mon : listMonAn) {
            JPanel card = new JPanel(null);
            card.setPreferredSize(new Dimension(996, 160));
            card.setBorder(BorderFactory.createCompoundBorder(
                    new LineBorder(new Color(220, 220, 220), 1),
                    new EmptyBorder(15, 15, 15, 15)));

            // Hình ảnh
            JLabel lblHinh = new JLabel();
            lblHinh.setBounds(0, 0, 235, 130);
            lblHinh.setBorder(new LineBorder(new Color(200, 200, 200)));
            try {
                ImageIcon icon = new ImageIcon(mon.getHinhAnh());
                Image img = icon.getImage().getScaledInstance(235, 130, Image.SCALE_SMOOTH);
                lblHinh.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                lblHinh.setText("No Image");
                lblHinh.setHorizontalAlignment(SwingConstants.CENTER);
                lblHinh.setForeground(Color.GRAY);
            }
            card.add(lblHinh);

            // Thông tin
            JLabel lblMaMon = new JLabel(mon.getMaMon());
            lblMaMon.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            lblMaMon.setBounds(260, 10, 100, 20);
            card.add(lblMaMon);

            JLabel lblTenMon = new JLabel(mon.getTenMon());
            lblTenMon.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTenMon.setBounds(260, 35, 300, 30);
            card.add(lblTenMon);

            JLabel lblGia = new JLabel(df.format(mon.getGia()));
            lblGia.setFont(new Font("Segoe UI", Font.BOLD, 24));
            lblGia.setBounds(260, 70, 200, 35);
            card.add(lblGia);

            String tenLoai = monAnDAO.chuyenDoiMaLoaiSangTen(mon.getLoaiMon());
            JLabel lblLoai = new JLabel("Loại: " + tenLoai);
            lblLoai.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblLoai.setBounds(570, 15, 200, 25);
            card.add(lblLoai);

            if (mon.getSoLuong() > 0) {
                JLabel lblTon = new JLabel("Số lượng tồn: " + mon.getSoLuong());
                lblTon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                lblTon.setForeground(mon.getSoLuong() < 10 ? Color.RED : Color.BLACK);
                lblTon.setBounds(570, 40, 150, 25);
                card.add(lblTon);
            }

            String moTa = mon.getMoTa() != null && !mon.getMoTa().trim().isEmpty() ? mon.getMoTa() : "Không có mô tả";
            JLabel lblMoTa = new JLabel("<html><div width='380'>" + moTa + "</div></html>");
            lblMoTa.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblMoTa.setForeground(Color.GRAY);
            lblMoTa.setBounds(570, 70, 400, 60);
            card.add(lblMoTa);

            panelCenterCenter.add(card);
            panelCenterCenter.add(Box.createVerticalStrut(8));
        }

        panelCenterCenter.revalidate();
        panelCenterCenter.repaint();
    }

    // ==================== CÁC HÀM XỬ LÝ (GIỮ NGUYÊN 100%) ====================
    private void tinhSoLuongBanCacMon() {
        soLuongBanMap.clear();
        List<HoaDon> dsHoaDon = hoaDonDAO.timHoaDonTheoTrangThai("Đã thanh toán");
        for (HoaDon hd : dsHoaDon) {
            for (ChiTietHoaDon ct : cthdDAO.layDanhSachTheoHoaDon(hd.getMaHoaDon())) {
                String maMon = ct.getMonAn().getMaMon();
                soLuongBanMap.merge(maMon, ct.getSoLuong(), Integer::sum);
            }
        }
    }

    private void sapXepTheoDoPhoBien() {
        if (danhSachMonAnHienTai != null) {
            danhSachMonAnHienTai.sort((a, b) -> Integer.compare(
                    soLuongBanMap.getOrDefault(b.getMaMon(), 0),
                    soLuongBanMap.getOrDefault(a.getMaMon(), 0)));
            themDuLieuVaoPanelCenterCenter(danhSachMonAnHienTai);
        }
    }

    private void loadMonAnFromDB() {
        new Thread(() -> {
            List<MonAn> all = monAnDAO.getAllMonAn();
            listGoc = new ArrayList<>(all);
            danhSachMonAnHienTai = new ArrayList<>(all);
            SwingUtilities.invokeLater(() -> themDuLieuVaoPanelCenterCenter(danhSachMonAnHienTai));
        }).start();
    }

    private void timKiemBangTuKhoa(String keyword) {
        if (listGoc == null) return;
        List<MonAn> ketQua = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            ketQua.addAll(listGoc);
        } else {
            String tk = keyword.toLowerCase();
            for (MonAn mon : listGoc) {
                if (mon.getTenMon().toLowerCase().contains(tk)) ketQua.add(mon);
            }
        }
        danhSachMonAnHienTai = ketQua;
        themDuLieuVaoPanelCenterCenter(ketQua);
    }

    private void sapXepTheoGiaTangDan() {
        if (danhSachMonAnHienTai != null) {
            danhSachMonAnHienTai.sort((a, b) -> Double.compare(a.getGia(), b.getGia()));
            themDuLieuVaoPanelCenterCenter(danhSachMonAnHienTai);
        }
    }

    private void sapXepTheoGiaGiamDan() {
        if (danhSachMonAnHienTai != null) {
            danhSachMonAnHienTai.sort((a, b) -> Double.compare(b.getGia(), a.getGia()));
            themDuLieuVaoPanelCenterCenter(danhSachMonAnHienTai);
        }
    }

    private void sapXepTheoMaMonGiamDan() {
        if (danhSachMonAnHienTai != null) {
            danhSachMonAnHienTai.sort((a, b) -> b.getMaMon().compareTo(a.getMaMon()));
            themDuLieuVaoPanelCenterCenter(danhSachMonAnHienTai);
        }
    }

    private void locTatCaDanhMuc() { loadMonAnFromDB(); }
    private void locTheoMonAnChinh() { filterAndDisplay("loaimon", "MC"); }
    private void locTheoDoUong() { filterAndDisplay("loaimon", "DO"); }
    private void locTheoMonAnKem() { filterAndDisplay("loaimon", "MK"); }
    private void locTheoTuKhoa(String keyword) { filterAndDisplay("tukhoa", keyword); }

    private void filterAndDisplay(String loaiLoc, String giaTriLoc) {
        if (listGoc == null) return;
        List<MonAn> loc = new ArrayList<>();
        for (MonAn mon : listGoc) {
            boolean ok = switch (loaiLoc) {
                case "tatca" -> true;
                case "loaimon" -> mon.getLoaiMon().equalsIgnoreCase(giaTriLoc);
                case "tukhoa" -> mon.getTenMon().toLowerCase().contains(giaTriLoc.toLowerCase());
                default -> false;
            };
            if (ok) loc.add(mon);
        }
        danhSachMonAnHienTai = loc;
        themDuLieuVaoPanelCenterCenter(loc);
    }

    private void locMonKhac(String loai, List<String> tenDaCo) {
        if (listGoc == null) return;
        List<MonAn> ketQua = new ArrayList<>();
        for (MonAn mon : listGoc) {
            if (mon.getLoaiMon().equalsIgnoreCase(loai)) {
                boolean co = tenDaCo.stream().anyMatch(s -> mon.getTenMon().contains(s));
                if (!co) ketQua.add(mon);
            }
        }
        danhSachMonAnHienTai = ketQua;
        themDuLieuVaoPanelCenterCenter(ketQua);
    }

    private void moPanelThemMonAn() {
        ThemMonAn themMonAnPanel = new ThemMonAn();
        ManHinhChinhQuanLy container = new ManHinhChinhQuanLy();
        container.showPanel(themMonAnPanel);
    }
}
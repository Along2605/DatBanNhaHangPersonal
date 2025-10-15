package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import entity.BanAn;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class DialogChiTietHoaDon extends JDialog {
    
    private static final long serialVersionUID = 1L;
    private static final Color MAIN_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(76, 175, 80);
    private static final Color DANGER_COLOR = new Color(244, 67, 54);
    private static final Color WARNING_COLOR = new Color(255, 152, 0);
    
    private BanAn banAn;
    private JTable tableMonAn;
    private DefaultTableModel tableModel;
    
    // Labels để cập nhật giá trị
    private JLabel lblTongTienMonValue;
    private JLabel lblVATValue;
    private JLabel lblGiamGiaValue;
    private JLabel lblThanhToanValue;
    
    private JComboBox<String> cboKhuyenMai;
    private JButton btnThemMon;
    private JButton btnXoaMon;
    
    public DialogChiTietHoaDon(Frame parent, BanAn banAn) {
        super(parent, "Chi tiết hóa đơn", true);
        this.banAn = banAn;
        
        setSize(1000, 750);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        
        initComponents();
        loadThongTinHoaDon();
    }
    
    private void initComponents() {
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Tiêu đề
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        JLabel lblTitle = new JLabel("CHI TIẾT HÓA ĐƠN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(MAIN_COLOR);
        titlePanel.add(lblTitle, BorderLayout.CENTER);
        
        // Status badge
        JLabel lblStatus = new JLabel("ĐANG PHỤC VỤ");
        lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatus.setForeground(Color.WHITE);
        lblStatus.setBackground(SUCCESS_COLOR);
        lblStatus.setOpaque(true);
        lblStatus.setBorder(BorderFactory.createCompoundBorder(
            new EmptyBorder(0, 0, 0, 20),
            new EmptyBorder(5, 15, 5, 15)
        ));
        titlePanel.add(lblStatus, BorderLayout.EAST);
        
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        
        // Panel content
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Color.WHITE);
        
        // Phần thông tin hóa đơn
        contentPanel.add(createThongTinPanel(), BorderLayout.NORTH);
        
        // Phần danh sách món ăn
        contentPanel.add(createMonAnPanel(), BorderLayout.CENTER);
        
        // Phần tính tiền
        contentPanel.add(createTinhTienPanel(), BorderLayout.SOUTH);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Panel nút
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Tạo panel thông tin hóa đơn
     */
    private JPanel createThongTinPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                new LineBorder(MAIN_COLOR, 2),
                "Thông tin hóa đơn",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                MAIN_COLOR
            ),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Dòng 1
        JPanel row1 = new JPanel(new GridLayout(1, 5, 15, 0));
        row1.setBackground(Color.WHITE);
        
        row1.add(createInfoField("Mã hóa đơn:", "HD250112001"));
        row1.add(createInfoField("Ngày lập:", "15/12/2024 18:30"));
        row1.add(createInfoField("Nhân viên:", "NV001 - Minh Đức"));
        row1.add(createInfoField("Bàn:", banAn.getTenBan()));
        row1.add(createInfoField("Khu vực:", 
            banAn.getKhuVuc() != null ? banAn.getKhuVuc().getTenKhuVuc() : ""));
        
        // Dòng 2
        JPanel row2 = new JPanel(new GridLayout(1, 5, 15, 0));
        row2.setBackground(Color.WHITE);
        
        row2.add(createInfoField("Khách hàng:", "Nguyễn Văn A"));
        row2.add(createInfoField("SĐT:", "0912345678"));
        row2.add(createInfoField("Điểm tích lũy:", "150 điểm"));
        row2.add(createInfoField("Giờ vào:", "18:30"));
        row2.add(createInfoField("Thời gian:", "1h 15p"));
        
        panel.add(row1);
        panel.add(row2);
        
        return panel;
    }
    
    /**
     * Tạo panel danh sách món ăn
     */
    private JPanel createMonAnPanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                new LineBorder(MAIN_COLOR, 2),
                "Danh sách món ăn",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                MAIN_COLOR
            ),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Top panel với nút thêm/xóa món
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(Color.WHITE);
        
        btnThemMon = createSmallButton("Thêm món", SUCCESS_COLOR);
        btnThemMon.addActionListener(e -> themMon());
        
        btnXoaMon = createSmallButton("Xóa món", DANGER_COLOR);
        btnXoaMon.addActionListener(e -> xoaMon());
        
        topPanel.add(btnThemMon);
        topPanel.add(btnXoaMon);
        
        panel.add(topPanel, BorderLayout.NORTH);
        
        // Table món ăn
        String[] columns = {
            "STT", "Tên món", "Đơn giá", "Số lượng", 
            "Thành tiền", "Trạng thái", "Ghi chú"
        };
        
        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableMonAn = new JTable(tableModel);
        tableMonAn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableMonAn.setRowHeight(35);
        tableMonAn.setShowGrid(true);
        tableMonAn.setGridColor(new Color(230, 230, 230));
        tableMonAn.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Header style
        JTableHeader header = tableMonAn.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(MAIN_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Column widths
        tableMonAn.getColumnModel().getColumn(0).setPreferredWidth(50);   // STT
        tableMonAn.getColumnModel().getColumn(1).setPreferredWidth(220);  // Tên món
        tableMonAn.getColumnModel().getColumn(2).setPreferredWidth(100);  // Đơn giá
        tableMonAn.getColumnModel().getColumn(3).setPreferredWidth(80);   // Số lượng
        tableMonAn.getColumnModel().getColumn(4).setPreferredWidth(120);  // Thành tiền
        tableMonAn.getColumnModel().getColumn(5).setPreferredWidth(120);  // Trạng thái
        tableMonAn.getColumnModel().getColumn(6).setPreferredWidth(180);  // Ghi chú
        
        JScrollPane scrollPane = new JScrollPane(tableMonAn);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tạo panel tính tiền
     */
    private JPanel createTinhTienPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(15, 0));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        // Panel bên trái: Khuyến mãi và ghi chú
        JPanel leftPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        leftPanel.setBackground(Color.WHITE);
        
        // Khuyến mãi
        JPanel khuyenMaiPanel = new JPanel(new BorderLayout(10, 5));
        khuyenMaiPanel.setBackground(Color.WHITE);
        khuyenMaiPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(WARNING_COLOR, 2),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblKhuyenMai = new JLabel("🎁 Áp dụng khuyến mãi:");
        lblKhuyenMai.setFont(new Font("Segoe UI", Font.BOLD, 13));
        khuyenMaiPanel.add(lblKhuyenMai, BorderLayout.NORTH);
        
        cboKhuyenMai = new JComboBox<>(new String[]{
            "-- Không áp dụng --",
            "Giảm 10% - Khách hàng thân thiết",
            "Giảm 50,000đ - Hóa đơn trên 500k",
            "Giảm 20% - Sinh nhật"
        });
        cboKhuyenMai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboKhuyenMai.addActionListener(e -> tinhTongTien());
        khuyenMaiPanel.add(cboKhuyenMai, BorderLayout.CENTER);
        
        // Ghi chú
        JPanel ghiChuPanel = new JPanel(new BorderLayout(5, 5));
        ghiChuPanel.setBackground(Color.WHITE);
        ghiChuPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        JLabel lblGhiChu = new JLabel("Ghi chú:");
        lblGhiChu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        ghiChuPanel.add(lblGhiChu, BorderLayout.NORTH);
        
        JTextArea txtGhiChu = new JTextArea(2, 30);
        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 8, 5, 8)
        ));
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);
        scrollGhiChu.setBorder(null);
        ghiChuPanel.add(scrollGhiChu, BorderLayout.CENTER);
        
        leftPanel.add(khuyenMaiPanel);
        leftPanel.add(ghiChuPanel);
        
        // Panel bên phải: Tính tiền
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(245, 250, 255));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MAIN_COLOR, 3),
            new EmptyBorder(20, 25, 20, 25)
        ));
        rightPanel.setPreferredSize(new Dimension(400, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        
        // Tổng tiền món
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        JLabel lblTongTienMon = new JLabel("Tổng tiền món:");
        lblTongTienMon.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        rightPanel.add(lblTongTienMon, gbc);
        
        gbc.gridx = 1;
        lblTongTienMonValue = new JLabel("850,000đ");
        lblTongTienMonValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblTongTienMonValue.setHorizontalAlignment(JLabel.RIGHT);
        rightPanel.add(lblTongTienMonValue, gbc);
        
        // VAT (10%)
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblVAT = new JLabel("VAT (10%):");
        lblVAT.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        rightPanel.add(lblVAT, gbc);
        
        gbc.gridx = 1;
        lblVATValue = new JLabel("85,000đ");
        lblVATValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblVATValue.setHorizontalAlignment(JLabel.RIGHT);
        rightPanel.add(lblVATValue, gbc);
        
        // Giảm giá
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblGiamGia = new JLabel("Giảm giá:");
        lblGiamGia.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        lblGiamGia.setForeground(DANGER_COLOR);
        rightPanel.add(lblGiamGia, gbc);
        
        gbc.gridx = 1;
        lblGiamGiaValue = new JLabel("- 0đ");
        lblGiamGiaValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblGiamGiaValue.setHorizontalAlignment(JLabel.RIGHT);
        lblGiamGiaValue.setForeground(DANGER_COLOR);
        rightPanel.add(lblGiamGiaValue, gbc);
        
        // Separator
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(12, 0, 12, 0);
        JSeparator separator = new JSeparator();
        separator.setForeground(MAIN_COLOR);
        separator.setPreferredSize(new Dimension(0, 2));
        rightPanel.add(separator, gbc);
        
        // Thành tiền
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(8, 0, 8, 0);
        JLabel lblThanhToan = new JLabel("THÀNH TIỀN:");
        lblThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblThanhToan.setForeground(MAIN_COLOR);
        rightPanel.add(lblThanhToan, gbc);
        
        gbc.gridx = 1;
        lblThanhToanValue = new JLabel("935,000đ");
        lblThanhToanValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblThanhToanValue.setHorizontalAlignment(JLabel.RIGHT);
        lblThanhToanValue.setForeground(new Color(211, 47, 47));
        rightPanel.add(lblThanhToanValue, gbc);
        
       
        
        mainPanel.add(leftPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        
        return mainPanel;
    }
    
    /**
     * Tạo panel nút
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(Color.WHITE);
        
        JButton btnThanhToan = createButton("Thanh toán", SUCCESS_COLOR);
        btnThanhToan.setPreferredSize(new Dimension(180, 45));
        btnThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnThanhToan.addActionListener(e -> thanhToan());
        
        JButton btnInTamTinh = createButton("In tạm tính", new Color(103, 58, 183));
        btnInTamTinh.setPreferredSize(new Dimension(180, 45));
        btnInTamTinh.addActionListener(e -> inTamTinh());
        
        JButton btnChuyenBan = createButton("Chuyển bàn", WARNING_COLOR);
        btnChuyenBan.setPreferredSize(new Dimension(180, 45));
        btnChuyenBan.addActionListener(e -> chuyenBan());
        
        JButton btnDong = createButton("Đóng", new Color(100, 100, 100));
        btnDong.setPreferredSize(new Dimension(180, 45));
        btnDong.addActionListener(e -> dispose());
        
        panel.add(btnThanhToan);
        panel.add(btnInTamTinh);
        panel.add(btnChuyenBan);
        panel.add(btnDong);
        
        return panel;
    }
    
    /**
     * Tạo field thông tin
     */
    private JPanel createInfoField(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(Color.WHITE);
        
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblLabel.setForeground(Color.GRAY);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        panel.add(lblLabel, BorderLayout.NORTH);
        panel.add(lblValue, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tạo button
     */
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    /**
     * Tạo button nhỏ
     */
    private JButton createSmallButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 32));
        return button;
    }
    
    // ==================== XỬ LÝ CHỨC NĂNG ====================
    
    /**
     * Load thông tin hóa đơn
     * TODO: Load từ database
     */
    private void loadThongTinHoaDon() {
        tableModel.setRowCount(0);
        
        // TODO: Load từ database
        // String maHoaDon = hoaDonDAO.getMaHoaDonTheoBan(banAn.getMaBan());
        // HoaDon hoaDon = hoaDonDAO.getHoaDonTheoMa(maHoaDon);
        // List<ChiTietHoaDon> dsMonAn = chiTietHoaDonDAO.getMonAnTheoHoaDon(maHoaDon);
        
        // Dữ liệu demo
        Object[][] demoData = {
            {1, "Phở bò đặc biệt", "75,000đ", 2, "150,000đ", "Đã phục vụ", ""},
            {2, "Cơm rang dương châu", "65,000đ", 1, "65,000đ", "Đã phục vụ", ""},
            {3, "Gỏi cuốn tôm thịt", "45,000đ", 3, "135,000đ", "Đã phục vụ", "Thêm rau"},
            {4, "Bò lúc lắc", "120,000đ", 1, "120,000đ", "Đang nấu", "Chín kỹ"},
            {5, "Nước chanh tươi", "20,000đ", 4, "80,000đ", "Đã phục vụ", "Ít đá"},
            {6, "Chè ba màu", "25,000đ", 2, "50,000đ", "Chưa order", ""},
            {7, "Cà phê sữa đá", "25,000đ", 2, "50,000đ", "Đang pha chế", ""},
            {8, "Bánh flan", "20,000đ", 3, "60,000đ", "Đã phục vụ", ""},
            {9, "Salad trộn", "40,000đ", 1, "40,000đ", "Đã phục vụ", ""},
            {10, "Coca Cola", "15,000đ", 3, "45,000đ", "Đã phục vụ", "Chai"}
        };
        
        for (Object[] row : demoData) {
            tableModel.addRow(row);
        }
        
        tinhTongTien();
    }
    
    /**
     * Tính tổng tiền
     */
    private void tinhTongTien() {
        // TODO: Tính từ dữ liệu thực
        double tongTienMon = 850000;
        double vat = tongTienMon * 0.1;
        double giamGia = 0;
        
        // Tính giảm giá dựa trên khuyến mãi
        String khuyenMai = (String) cboKhuyenMai.getSelectedItem();
        if (khuyenMai.contains("10%")) {
            giamGia = tongTienMon * 0.1;
        } else if (khuyenMai.contains("20%")) {
            giamGia = tongTienMon * 0.2;
        } else if (khuyenMai.contains("50,000")) {
            giamGia = 50000;
        }
        
        double thanhTien = tongTienMon + vat - giamGia;
        
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        lblTongTienMonValue.setText(currencyFormat.format(tongTienMon) + "đ");
        lblVATValue.setText(currencyFormat.format(vat) + "đ");
        lblGiamGiaValue.setText("- " + currencyFormat.format(giamGia) + "đ");
        lblThanhToanValue.setText(currencyFormat.format(thanhTien) + "đ");
    }
    
    /**
     * Thêm món
     * TODO: Mở dialog chọn món
     */
    private void themMon() {
        // TODO: Mở dialog chọn món ăn
        // new DialogChonMonAn(this, maHoaDon, () -> loadThongTinHoaDon()).setVisible(true);
        
        JOptionPane.showMessageDialog(this,
            "Chức năng thêm món sẽ được cài đặt sau!",
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Xóa món
     * TODO: Xóa món đã chọn
     */
    private void xoaMon() {
        int selectedRow = tableMonAn.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn món cần xóa!",
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String tenMon = tableModel.getValueAt(selectedRow, 1).toString();
        String trangThai = tableModel.getValueAt(selectedRow, 5).toString();
        
        // Kiểm tra trạng thái
        if ("Đã phục vụ".equals(trangThai)) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Món '" + tenMon + "' đã được phục vụ.\n" +
                "Bạn có chắc chắn muốn xóa không?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
        }
        
        // TODO: Xóa từ database
        // chiTietHoaDonDAO.xoaMon(maHoaDon, maMon);
        
        JOptionPane.showMessageDialog(this,
            "Xóa món thành công!",
            "Thành công", JOptionPane.INFORMATION_MESSAGE);
        
        loadThongTinHoaDon();
    }
    
    /**
     * Thanh toán
     * TODO: Mở dialog thanh toán
     */
    
    /**
     * Thanh toán
     * TODO: Mở dialog thanh toán
     */
    private void thanhToan() {
        // Kiểm tra còn món chưa phục vụ
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String trangThai = tableModel.getValueAt(i, 5).toString();
            if (!"Đã phục vụ".equals(trangThai)) {
                int confirm = JOptionPane.showConfirmDialog(this,
                    "Vẫn còn món chưa được phục vụ!\n" +
                    "Bạn có muốn tiếp tục thanh toán không?",
                    "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }
                break;
            }
        }
        
        // Lấy số tiền cần thanh toán
        String thanhTienStr = lblThanhToanValue.getText().replace("đ", "").replace(".", "").replace(",", "").trim();
        double thanhTien = 0;
        try {
            thanhTien = Double.parseDouble(thanhTienStr);
        } catch (NumberFormatException e) {
            thanhTien = 935000; // Giá trị mặc định
        }
        
        // Tạo dialog thanh toán
        JDialog dialogThanhToan = new JDialog(this, "Thanh toán", true);
        dialogThanhToan.setSize(500, 400);
        dialogThanhToan.setLocationRelativeTo(this);
        dialogThanhToan.setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Title
        JLabel lblTitle = new JLabel("THANH TOÁN HÓA ĐƠN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(MAIN_COLOR);
        lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Tổng tiền
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.4;
        JLabel lblTongTienLabel = new JLabel("Tổng tiền:");
        lblTongTienLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        contentPanel.add(lblTongTienLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.6;
        JLabel lblTongTienVal = new JLabel(lblThanhToanValue.getText());
        lblTongTienVal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTienVal.setForeground(DANGER_COLOR);
        lblTongTienVal.setHorizontalAlignment(JLabel.RIGHT);
        contentPanel.add(lblTongTienVal, gbc);
        
        // Phương thức thanh toán
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblPhuongThucLabel = new JLabel("Phương thức:");
        lblPhuongThucLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        contentPanel.add(lblPhuongThucLabel, gbc);
        
        gbc.gridx = 1;
        JComboBox<String> cboPhuongThuc = new JComboBox<>(new String[]{
            "Tiền mặt", "Chuyển khoản", "Thẻ", "Ví điện tử"
        });
        cboPhuongThuc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contentPanel.add(cboPhuongThuc, gbc);
        
        // Tiền khách đưa
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblTienKhachLabel = new JLabel("Tiền khách đưa:");
        lblTienKhachLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        contentPanel.add(lblTienKhachLabel, gbc);
        
        gbc.gridx = 1;
        JTextField txtTienKhach = new JTextField();
        txtTienKhach.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtTienKhach.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(8, 10, 8, 10)
        ));
        contentPanel.add(txtTienKhach, gbc);
        
        // Tiền thừa
        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel lblTienThuaLabel = new JLabel("Tiền thừa:");
        lblTienThuaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        contentPanel.add(lblTienThuaLabel, gbc);
        
        gbc.gridx = 1;
        JLabel lblTienThuaVal = new JLabel("0đ");
        lblTienThuaVal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTienThuaVal.setForeground(SUCCESS_COLOR);
        lblTienThuaVal.setHorizontalAlignment(JLabel.RIGHT);
        contentPanel.add(lblTienThuaVal, gbc);
        
        // Tính tiền thừa khi nhập
        final double finalThanhTien = thanhTien;
        txtTienKhach.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    String input = txtTienKhach.getText().replace(",", "").replace(".", "").trim();
                    if (!input.isEmpty()) {
                        double tienKhach = Double.parseDouble(input);
                        double tienThua = tienKhach - finalThanhTien;
                        
                        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
                        if (tienThua >= 0) {
                            lblTienThuaVal.setText(currencyFormat.format(tienThua) + "đ");
                            lblTienThuaVal.setForeground(SUCCESS_COLOR);
                        } else {
                            lblTienThuaVal.setText(currencyFormat.format(Math.abs(tienThua)) + "đ (Thiếu)");
                            lblTienThuaVal.setForeground(DANGER_COLOR);
                        }
                    } else {
                        lblTienThuaVal.setText("0đ");
                        lblTienThuaVal.setForeground(SUCCESS_COLOR);
                    }
                } catch (NumberFormatException e) {
                    lblTienThuaVal.setText("Nhập sai!");
                    lblTienThuaVal.setForeground(DANGER_COLOR);
                }
            }
        });
        
        // Nút tiền nhanh
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JPanel quickMoneyPanel = new JPanel(new GridLayout(2, 3, 5, 5));
        quickMoneyPanel.setBackground(Color.WHITE);
        quickMoneyPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Tiền nhanh"),
            new EmptyBorder(5, 5, 5, 5)
        ));
        
        String[] quickAmounts = {"500,000", "1,000,000", "2,000,000", "5,000,000", "Đủ tiền", "Xóa"};
        for (String amount : quickAmounts) {
            JButton btnQuick = new JButton(amount);
            btnQuick.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btnQuick.setBackground(new Color(240, 240, 240));
            btnQuick.setFocusPainted(false);
            btnQuick.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            btnQuick.addActionListener(e -> {
                if ("Xóa".equals(amount)) {
                    txtTienKhach.setText("");
                    lblTienThuaVal.setText("0đ");
                    lblTienThuaVal.setForeground(SUCCESS_COLOR);
                } else if ("Đủ tiền".equals(amount)) {
                    NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
                    txtTienKhach.setText(currencyFormat.format(finalThanhTien));
                    txtTienKhach.postActionEvent(); // Trigger key listener
                } else {
                    txtTienKhach.setText(amount.replace(",", ""));
                    txtTienKhach.postActionEvent();
                }
            });
            
            quickMoneyPanel.add(btnQuick);
        }
        contentPanel.add(quickMoneyPanel, gbc);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton btnXacNhan = createButton("Xác nhận thanh toán", SUCCESS_COLOR);
        btnXacNhan.setPreferredSize(new Dimension(180, 40));
        btnXacNhan.addActionListener(e -> {
            // Validate
            String phuongThuc = (String) cboPhuongThuc.getSelectedItem();
            String tienKhachStr = txtTienKhach.getText().replace(",", "").replace(".", "").trim();
            
            if (tienKhachStr.isEmpty() && "Tiền mặt".equals(phuongThuc)) {
                JOptionPane.showMessageDialog(dialogThanhToan,
                    "Vui lòng nhập số tiền khách đưa!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Kiểm tra tiền đủ
            if ("Tiền mặt".equals(phuongThuc)) {
                try {
                    double tienKhach = Double.parseDouble(tienKhachStr);
                    if (tienKhach < finalThanhTien) {
                        JOptionPane.showMessageDialog(dialogThanhToan,
                            "Số tiền khách đưa chưa đủ!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialogThanhToan,
                        "Số tiền không hợp lệ!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Confirm
            int confirm = JOptionPane.showConfirmDialog(dialogThanhToan,
                "Xác nhận thanh toán?\n\n" +
                "Phương thức: " + phuongThuc + "\n" +
                "Tổng tiền: " + lblTongTienVal.getText() + "\n" +
                (lblTienThuaVal.getText().contains("Thiếu") ? "" : "Tiền thừa: " + lblTienThuaVal.getText()),
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // TODO: Cập nhật database
                // hoaDonDAO.thanhToanHoaDon(maHoaDon, phuongThuc, tienKhach);
                // banAnDAO.capNhatTrangThai(banAn.getMaBan(), "Trống");
                
                JOptionPane.showMessageDialog(dialogThanhToan,
                    "Thanh toán thành công!\n\n" +
                    "Cảm ơn quý khách và hẹn gặp lại!",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                
                dialogThanhToan.dispose();
                dispose(); // Đóng dialog hóa đơn
            }
        });
        
        JButton btnHuy = createButton("Hủy", new Color(100, 100, 100));
        btnHuy.setPreferredSize(new Dimension(100, 40));
        btnHuy.addActionListener(e -> dialogThanhToan.dispose());
        
        buttonPanel.add(btnXacNhan);
        buttonPanel.add(btnHuy);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialogThanhToan.add(mainPanel);
        dialogThanhToan.setVisible(true);
    }

    /**
     * In tạm tính
     * TODO: In hóa đơn tạm tính
     */
    private void inTamTinh() {
        JOptionPane.showMessageDialog(this,
            "Chức năng in tạm tính sẽ được cài đặt sau!\n\n" +
            "Sẽ in ra:\n" +
            "- Danh sách món ăn\n" +
            "- Tổng tiền tạm tính\n" +
            "- Chưa thanh toán",
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Chuyển bàn
     * TODO: Mở dialog chọn bàn mới
     */
    private void chuyenBan() {
        // Lấy danh sách bàn trống
        String[] banTrong = {"Bàn 3 - Tầng 1", "Bàn 5 - Tầng 2", "Bàn 7 - Sân thượng"};
        
        String banMoi = (String) JOptionPane.showInputDialog(
            this,
            "Chọn bàn muốn chuyển đến:",
            "Chuyển bàn",
            JOptionPane.QUESTION_MESSAGE,
            null,
            banTrong,
            banTrong[0]
        );
        
        if (banMoi != null) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Xác nhận chuyển từ " + banAn.getTenBan() + " sang " + banMoi + "?",
                "Xác nhận", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                // TODO: Cập nhật database
                // hoaDonDAO.chuyenBan(maHoaDon, maBanMoi);
                // banAnDAO.capNhatTrangThai(banAn.getMaBan(), "Trống");
                // banAnDAO.capNhatTrangThai(maBanMoi, "Đang sử dụng");
                
                JOptionPane.showMessageDialog(this,
                    "Chuyển bàn thành công!",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                
                dispose();
            }
        }
    }
}
package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import com.toedter.calendar.JDateChooser;

import dao.BanAnDAO;
import dao.HoaDonDAO;
import entity.BanAn;
import entity.HoaDon;

public class TraCuuHoaDon extends JPanel implements ActionListener, MouseListener {
    
    private static final long serialVersionUID = 1L;
    
    // Components tìm kiếm
    private JTextField txtMaHoaDon;
    private JTextField txtMaBan;
    private JTextField txtKhachHang;
    private JComboBox<String> cboTrangThai;
    private JDateChooser dateFrom;
    private JDateChooser dateTo;
    private JComboBox<String> cboLoaiHoaDon;
    
    private JTable tableHoaDon;
    private DefaultTableModel tableModel;
    
    private JButton btnTimKiem;
    private JButton btnLamMoi;
    private JButton btnXemChiTiet;
    private JButton btnInHoaDon;
    private JButton btnThongKe;
    
    private JLabel lblThongTin;
    private JLabel lblTongDoanhThu;
    
    private HoaDonDAO hoaDonDAO;
    
    // Colors
    private final Color MAIN_COLOR = new Color(214, 116, 76);
    private final Color HOVER_COLOR = new Color(234, 136, 96);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);
    private final Color SUCCESS_COLOR = new Color(76, 175, 80);
    private final Color WARNING_COLOR = new Color(255, 152, 0);
    private final Color DANGER_COLOR = new Color(244, 67, 54);
    
    // Formatters
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public TraCuuHoaDon() {
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        hoaDonDAO = new HoaDonDAO();
        
        // Add components
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createSearchPanel(), BorderLayout.WEST);
        add(createTablePanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        // Load initial data
        loadDanhSachHoaDon();
    }
    
    /**
     * Tạo panel tiêu đề
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setBackground(BACKGROUND_COLOR);
        
        JLabel lblTitle = new JLabel("TRA CỨU HÓA ĐƠN");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(MAIN_COLOR);
        panel.add(lblTitle);
        
        return panel;
    }
    
    /**
     * Tạo panel tìm kiếm bên trái
     */
    private JPanel createSearchPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MAIN_COLOR, 2, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        mainPanel.setPreferredSize(new Dimension(320, 0));
        
        // Title
        JLabel lblSearchTitle = new JLabel("Tiêu chí tìm kiếm");
        lblSearchTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSearchTitle.setForeground(MAIN_COLOR);
        lblSearchTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        mainPanel.add(lblSearchTitle, BorderLayout.NORTH);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        int row = 0;
        
        // Mã hóa đơn
        addFormField(formPanel, gbc, row++, "Mã hóa đơn:", txtMaHoaDon = createTextField());
        
        // Mã bàn
        addFormField(formPanel, gbc, row++, "Mã bàn:", txtMaBan = createTextField());
        
        // Khách hàng
        addFormField(formPanel, gbc, row++, "Khách hàng:", txtKhachHang = createTextField());
        
        // Trạng thái
        cboTrangThai = createComboBox(new String[]{
            "-- Tất cả --", "Chưa thanh toán", "Đã thanh toán"
        });
        addFormField(formPanel, gbc, row++, "Trạng thái:", cboTrangThai);
        
        // Loại hóa đơn
        cboLoaiHoaDon = createComboBox(new String[]{
            "-- Tất cả --", "Khách vãng lai", "Khách đặt trước"
        });
        addFormField(formPanel, gbc, row++, "Loại hóa đơn:", cboLoaiHoaDon);
        
        // Separator
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        JSeparator separator1 = new JSeparator();
        separator1.setBorder(new EmptyBorder(10, 0, 10, 0));
        formPanel.add(separator1, gbc);
        gbc.gridwidth = 1;
        
        // Label thời gian
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        JLabel lblTimeFilter = new JLabel("Lọc theo thời gian");
        lblTimeFilter.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTimeFilter.setForeground(MAIN_COLOR);
        formPanel.add(lblTimeFilter, gbc);
        gbc.gridwidth = 1;
        
        // Từ ngày - mặc định là ngày hôm nay
        dateFrom = new JDateChooser();
        dateFrom.setDateFormatString("dd/MM/yyyy");
        dateFrom.setDate(new Date()); // Set ngày hôm nay
        dateFrom.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dateFrom.setPreferredSize(new Dimension(0, 30));
        addFormField(formPanel, gbc, row++, "Từ ngày:", dateFrom);
        
        // Đến ngày - mặc định là ngày hôm nay
        dateTo = new JDateChooser();
        dateTo.setDateFormatString("dd/MM/yyyy");
        dateTo.setDate(new Date()); // Đã set ngày hôm nay
        dateTo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dateTo.setPreferredSize(new Dimension(0, 30));
        addFormField(formPanel, gbc, row++, "Đến ngày:", dateTo);
        
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(15, 0, 0, 0));
        
        btnTimKiem = createButton("Tìm kiếm", MAIN_COLOR);
        btnLamMoi = createButton("Làm mới", new Color(100, 100, 100));
        
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnLamMoi);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add action listeners
        btnTimKiem.addActionListener(this);
        btnLamMoi.addActionListener(this);
        
        return mainPanel;
    }
    
    /**
     * Tạo panel bảng dữ liệu
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(BACKGROUND_COLOR);
        
        // Top bar
        JPanel topBarPanel = new JPanel(new BorderLayout(10, 0));
        topBarPanel.setBackground(Color.WHITE);
        topBarPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        // Panel thống kê bên trái
        JPanel statsPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        statsPanel.setBackground(Color.WHITE);
        
        lblThongTin = new JLabel("Tổng số hóa đơn: 0");
        lblThongTin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        lblTongDoanhThu = new JLabel("Tổng doanh thu: 0đ");
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTongDoanhThu.setForeground(SUCCESS_COLOR);
        
        statsPanel.add(lblThongTin);
        statsPanel.add(lblTongDoanhThu);
        
        topBarPanel.add(statsPanel, BorderLayout.WEST);
        
        // Lọc nhanh bên phải
        JPanel quickFilterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        quickFilterPanel.setBackground(Color.WHITE);
        
//        JButton btnHomNay = new JButton("Hôm nay");
//        btnHomNay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
//        btnHomNay.setBackground(new Color(240, 240, 240));
//        btnHomNay.setFocusPainted(false);
//        btnHomNay.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        btnHomNay.addActionListener(e -> {
//            dateFrom.setDate(new Date());
//            dateTo.setDate(new Date());
//            timKiemHoaDon();
//        });
        
        JButton btnTuanNay = new JButton("Tuần này");
        btnTuanNay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnTuanNay.setBackground(new Color(240, 240, 240));
        btnTuanNay.setFocusPainted(false);
        btnTuanNay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTuanNay.addActionListener(e -> {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY);
            dateFrom.setDate(cal.getTime());
            dateTo.setDate(new Date());
            timKiemHoaDon();
        });
        
        JButton btnThangNay = new JButton("Tháng này");
        btnThangNay.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        btnThangNay.setBackground(new Color(240, 240, 240));
        btnThangNay.setFocusPainted(false);
        btnThangNay.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThangNay.addActionListener(e -> {
            java.util.Calendar cal = java.util.Calendar.getInstance();
            cal.set(java.util.Calendar.DAY_OF_MONTH, 1);
            dateFrom.setDate(cal.getTime());
            dateTo.setDate(new Date());
            timKiemHoaDon();
        });
        
//        quickFilterPanel.add(btnHomNay);
        quickFilterPanel.add(btnTuanNay);
        quickFilterPanel.add(btnThangNay);
        
        topBarPanel.add(quickFilterPanel, BorderLayout.EAST);
        
        panel.add(topBarPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {
            "Mã HĐ", "Loại", "Danh sách bàn", "Khách hàng", "Nhân viên",
            "Ngày lập", "Tiền cọc", "Tổng tiền", "Trạng thái"
        };
        
        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableHoaDon = new JTable(tableModel);
        tableHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableHoaDon.setRowHeight(35);
        tableHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHoaDon.setShowGrid(true);
        tableHoaDon.setGridColor(new Color(230, 230, 230));
        
        // Header style
        JTableHeader header = tableHoaDon.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(MAIN_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Column widths
        tableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(100);  // Mã HĐ
        tableHoaDon.getColumnModel().getColumn(1).setPreferredWidth(90);   // Loại
        tableHoaDon.getColumnModel().getColumn(2).setPreferredWidth(150);  // Danh sách bàn (tăng width)
        tableHoaDon.getColumnModel().getColumn(3).setPreferredWidth(140);  // Khách hàng
        tableHoaDon.getColumnModel().getColumn(4).setPreferredWidth(140);  // Nhân viên
        tableHoaDon.getColumnModel().getColumn(5).setPreferredWidth(130);  // Ngày lập
        tableHoaDon.getColumnModel().getColumn(6).setPreferredWidth(100);  // Tiền cọc
        tableHoaDon.getColumnModel().getColumn(7).setPreferredWidth(110);  // Tổng tiền
        tableHoaDon.getColumnModel().getColumn(8).setPreferredWidth(120);  // Trạng thái
        
        // Right align số tiền
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableHoaDon.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
        tableHoaDon.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
        
        JScrollPane scrollPane = new JScrollPane(tableHoaDon);
        tableHoaDon.addMouseListener(this);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tạo panel nút chức năng
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(BACKGROUND_COLOR);
        
        btnXemChiTiet = createButton("Xem chi tiết", MAIN_COLOR);
        btnXemChiTiet.setPreferredSize(new Dimension(150, 40));
        
        btnInHoaDon = createButton("In hóa đơn", new Color(103, 58, 183));
        btnInHoaDon.setPreferredSize(new Dimension(150, 40));
        
        btnThongKe = createButton("Thống kê", SUCCESS_COLOR);
        btnThongKe.setPreferredSize(new Dimension(150, 40));
        
        panel.add(btnXemChiTiet);
        panel.add(btnInHoaDon);
        panel.add(btnThongKe);
        
        btnXemChiTiet.addActionListener(this);
        btnInHoaDon.addActionListener(this);
        btnThongKe.addActionListener(this);
        
        return panel;
    }
    
    /**
     * Thêm field vào form
     */
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row,
                              String labelText, JComponent component) {
        // Label
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.35;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        panel.add(label, gbc);
        
        // Component
        gbc.gridx = 1;
        gbc.weightx = 0.65;
        panel.add(component, gbc);
    }
    
    /**
     * Tạo text field với style đồng nhất
     */
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        textField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 8, 5, 8)
        ));
        return textField;
    }
    
    /**
     * Tạo combo box với style đồng nhất
     */
    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        comboBox.setBackground(Color.WHITE);
        return comboBox;
    }
    
    /**
     * Tạo button với style đồng nhất
     */
    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(0, 35));
        
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
    
    private void loadDanhSachHoaDon() {
        try {
            tableModel.setRowCount(0);
            
            // ✅ Lấy hóa đơn của ngày hôm nay (từ 00:00:00 đến 23:59:59)
            LocalDate today = LocalDate.now();
            LocalDateTime startOfDay = today.atStartOfDay(); // 00:00:00
            LocalDateTime endOfDay = today.atTime(23, 59, 59); // 23:59:59
            
            // Lấy tất cả hóa đơn và lọc theo ngày hôm nay
            List<HoaDon> dsHoaDon = hoaDonDAO.findAll();
            
            if (dsHoaDon == null || dsHoaDon.isEmpty()) {
                capNhatThongTin();
                return;
            }
            
            // ✅ Lọc hóa đơn hôm nay
            List<HoaDon> dsHoaDonHomNay = dsHoaDon.stream()
                .filter(hd -> hd.getNgayLapHoaDon() != null && 
                             !hd.getNgayLapHoaDon().isBefore(startOfDay) &&
                             !hd.getNgayLapHoaDon().isAfter(endOfDay))
                .collect(Collectors.toList());
            
            for (HoaDon hd : dsHoaDonHomNay) {
                addHoaDonToTable(hd);
            }
            
            capNhatThongTin();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi tải dữ liệu: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Thêm hóa đơn vào bảng
     */
    private void addHoaDonToTable(HoaDon hd) {
        String loai = "";
        String banPhieu = "";
        
        if (hd.getPhieuDat() != null) {
            // Hóa đơn từ phiếu đặt - lấy danh sách bàn từ phiếu
            loai = "Đặt trước";
            dao.PhieuDatBanDAO phieuDAO = new dao.PhieuDatBanDAO();
            List<String> dsBan = phieuDAO.getDanhSachBanTheoPhieuDat(hd.getPhieuDat().getMaPhieuDat());
            if (dsBan != null && !dsBan.isEmpty()) {
                banPhieu = String.join(", ", dsBan);
            } else {
                banPhieu = "N/A";
            }
        } else if (hd.getBanAn() != null) {
            // Hóa đơn vãng lai - chỉ có 1 bàn
            loai = "Vãng lai";
            banPhieu = hd.getBanAn().getMaBan();
        } else {
            loai = "N/A";
            banPhieu = "N/A";
        }
        
        String tenKH = hd.getKhachHang() != null 
            ? hd.getKhachHang().getHoTen() 
            : "Khách vãng lai";
            
        String tenNV = hd.getNhanVien() != null 
            ? hd.getNhanVien().getHoTen() 
            : "N/A";
            
        String ngayLap = hd.getNgayLapHoaDon() != null 
            ? hd.getNgayLapHoaDon().format(dtf) 
            : "";
            
        String tienCoc = currencyFormat.format(hd.getTienCoc()) + "đ";
        String tongTien = currencyFormat.format(hd.getTongTien()) + "đ";
        
        tableModel.addRow(new Object[]{
            hd.getMaHoaDon(),
            loai,
            banPhieu,
            tenKH,
            tenNV,
            ngayLap,
            tienCoc,
            tongTien,
            hd.getTrangThai()
        });
    }
    
    /**
     * Tìm kiếm hóa đơn theo điều kiện
     */
    private void timKiemHoaDon() {
        try {
            String maHD = txtMaHoaDon.getText().trim();
            String maBan = txtMaBan.getText().trim();
            String khachHang = txtKhachHang.getText().trim();
            String trangThai = cboTrangThai.getSelectedItem().toString();
            String loaiHD = cboLoaiHoaDon.getSelectedItem().toString();
            
            Date fromDate = dateFrom.getDate();
            Date toDate = dateTo.getDate();
            
            LocalDateTime fromDateTime = null;
            LocalDateTime toDateTime = null;
            
            if (fromDate != null) {
                fromDateTime = new java.sql.Timestamp(fromDate.getTime()).toLocalDateTime();
            }
            if (toDate != null) {
                toDateTime = new java.sql.Timestamp(toDate.getTime()).toLocalDateTime().withHour(23).withMinute(59);
            }
            
            // Lấy danh sách hóa đơn
            List<HoaDon> dsHoaDon = hoaDonDAO.findAll();
            
            if (dsHoaDon == null) {
                dsHoaDon = new ArrayList<>();
            }
            
            // Lọc theo điều kiện
            final LocalDateTime finalFromDate = fromDateTime;
            final LocalDateTime finalToDate = toDateTime;
            
            List<HoaDon> ketQua = dsHoaDon.stream().filter(hd -> {
                boolean match = true;
                
                // Lọc mã hóa đơn
                if (!maHD.isEmpty()) {
                    match = match && hd.getMaHoaDon().toLowerCase().contains(maHD.toLowerCase());
                }
                
                // Lọc mã bàn (hỗ trợ cả vãng lai và đặt trước)
                if (!maBan.isEmpty()) {
                    boolean coMaBan = false;
                    
                    // Trường hợp 1: Hóa đơn vãng lai - kiểm tra maBan trực tiếp
                    if (hd.getBanAn() != null) {
                        coMaBan = hd.getBanAn().getMaBan().toLowerCase().contains(maBan.toLowerCase());
                    }
                    
                    // Trường hợp 2: Hóa đơn đặt trước - kiểm tra trong danh sách bàn của phiếu
                    if (!coMaBan && hd.getPhieuDat() != null) {
                        dao.PhieuDatBanDAO phieuDAO = new dao.PhieuDatBanDAO();
                        List<String> dsBan = phieuDAO.getDanhSachBanTheoPhieuDat(hd.getPhieuDat().getMaPhieuDat());
                        if (dsBan != null) {
                            for (String ban : dsBan) {
                                if (ban.toLowerCase().contains(maBan.toLowerCase())) {
                                    coMaBan = true;
                                    break;
                                }
                            }
                        }
                    }
                    
                    match = match && coMaBan;
                }
                
                // Lọc khách hàng
                if (!khachHang.isEmpty() && hd.getKhachHang() != null) {
                    match = match && hd.getKhachHang().getHoTen().toLowerCase()
                        .contains(khachHang.toLowerCase());
                }
                
                // Lọc trạng thái
                if (!trangThai.equals("-- Tất cả --")) {
                    match = match && hd.getTrangThai().equals(trangThai);
                }
                
                // Lọc loại hóa đơn
                if (!loaiHD.equals("-- Tất cả --")) {
                    if (loaiHD.equals("Khách vãng lai")) {
                        match = match && (hd.getBanAn() != null && hd.getPhieuDat() == null);
                    } else if (loaiHD.equals("Khách đặt trước")) {
                        match = match && (hd.getPhieuDat() != null);
                    }
                }
                
                // Lọc ngày
                if (finalFromDate != null && hd.getNgayLapHoaDon() != null) {
                    match = match && !hd.getNgayLapHoaDon().isBefore(finalFromDate);
                }
                if (finalToDate != null && hd.getNgayLapHoaDon() != null) {
                    match = match && !hd.getNgayLapHoaDon().isAfter(finalToDate);
                }
                
                return match;
            }).collect(Collectors.toList());
            
            // Cập nhật bảng
            tableModel.setRowCount(0);
            for (HoaDon hd : ketQua) {
                addHoaDonToTable(hd);
            }
            
            capNhatThongTin();
            
            if (ketQua.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Không tìm thấy hóa đơn phù hợp!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi tìm kiếm: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Làm mới form
     */
    private void lamMoiForm() {
        txtMaHoaDon.setText("");
        txtMaBan.setText("");
        txtKhachHang.setText("");
        cboTrangThai.setSelectedIndex(0);
        cboLoaiHoaDon.setSelectedIndex(0);
     // ✅ Reset về ngày hôm nay
        dateFrom.setDate(new Date());
        dateTo.setDate(new Date());
        
        loadDanhSachHoaDon();
    }
    
    /**
     * Xem chi tiết hóa đơn
     */
    private void xemChiTietHoaDon() {
        int selectedRow = tableHoaDon.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn một hóa đơn để xem chi tiết!",
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String maHD = tableModel.getValueAt(selectedRow, 0).toString();
        
        try {
            HoaDon hoaDon = hoaDonDAO.timHoaDonTheoMa(maHD);
            
            if (hoaDon == null) {
                JOptionPane.showMessageDialog(this,
                    "Không tìm thấy thông tin hóa đơn!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Lấy thông tin bàn để mở dialog
            BanAn banAn = hoaDon.getBanAn();
            
            if (banAn == null && hoaDon.getPhieuDat() != null) {
                // Trường hợp đặt trước, lấy bàn từ phiếu đặt
                dao.PhieuDatBanDAO phieuDAO = new dao.PhieuDatBanDAO();
                List<String> dsBan = phieuDAO.getDanhSachBanTheoPhieuDat(
                    hoaDon.getPhieuDat().getMaPhieuDat()
                );
                
                if (!dsBan.isEmpty()) {
                    BanAnDAO banDAO = new BanAnDAO();
                    banAn = banDAO.getBanTheoMa(dsBan.get(0));
                }
            }
            
            if (banAn == null) {
                JOptionPane.showMessageDialog(this,
                    "Không tìm thấy thông tin bàn!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Mở dialog chi tiết hóa đơn
            DialogChiTietHoaDon dialog = new DialogChiTietHoaDon(
                (Frame) SwingUtilities.getWindowAncestor(this),
                banAn
            );
            
            dialog.setVisible(true);
            
            // Refresh lại dữ liệu sau khi đóng dialog
            loadDanhSachHoaDon();
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi xem chi tiết: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * In hóa đơn
     */
    private void inHoaDon() {
        int selectedRow = tableHoaDon.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn hóa đơn cần in!",
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String maHD = tableModel.getValueAt(selectedRow, 0).toString();
        
        JOptionPane.showMessageDialog(this,
            "Chức năng in hóa đơn sẽ được triển khai sau!\n" +
            "Mã hóa đơn: " + maHD,
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Hiển thị thống kê
     */
    private void hienThiThongKe() {
        try {
            // Lấy dữ liệu từ bảng hiện tại (đã được lọc)
            int tongSoHD = tableModel.getRowCount();
            double tongDoanhThu = 0;
            int hdChuaTT = 0;
            int hdDaTT = 0;
            int hdVangLai = 0;
            int hdDatTruoc = 0;
            
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                // Tổng doanh thu
                String tongTienStr = tableModel.getValueAt(i, 7).toString()
                    .replace("đ", "").replace(",", "").replace(".", "").trim();
                tongDoanhThu += Double.parseDouble(tongTienStr);
                
                // Đếm trạng thái
                String trangThai = tableModel.getValueAt(i, 8).toString();
                if ("Chưa thanh toán".equals(trangThai)) {
                    hdChuaTT++;
                } else if ("Đã thanh toán".equals(trangThai)) {
                    hdDaTT++;
                }
                
                // Đếm loại
                String loai = tableModel.getValueAt(i, 1).toString();
                if ("Vãng lai".equals(loai)) {
                    hdVangLai++;
                } else if ("Đặt trước".equals(loai)) {
                    hdDatTruoc++;
                }
            }
            
            // Tính trung bình
            double trungBinh = tongSoHD > 0 ? tongDoanhThu / tongSoHD : 0;
            
            // Hiển thị dialog thống kê
            JDialog dialogThongKe = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                "Thống kê hóa đơn", true
            );
            dialogThongKe.setSize(500, 400);
            dialogThongKe.setLocationRelativeTo(this);
            dialogThongKe.setLayout(new BorderLayout(10, 10));
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            mainPanel.setBackground(Color.WHITE);
            
            // Title
            JLabel lblTitle = new JLabel("THỐNG KÊ HÓA ĐƠN", JLabel.CENTER);
            lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblTitle.setForeground(MAIN_COLOR);
            lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
            mainPanel.add(lblTitle, BorderLayout.NORTH);
            
            // Content
            JPanel contentPanel = new JPanel(new GridLayout(0, 2, 10, 15));
            contentPanel.setBackground(Color.WHITE);
            contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            
            // Thêm thông tin
            addStatRow(contentPanel, "Tổng số hóa đơn:", String.valueOf(tongSoHD));
            addStatRow(contentPanel, "Đã thanh toán:", String.valueOf(hdDaTT));
            addStatRow(contentPanel, "Chưa thanh toán:", String.valueOf(hdChuaTT));
            addStatRow(contentPanel, "Khách vãng lai:", String.valueOf(hdVangLai));
            addStatRow(contentPanel, "Khách đặt trước:", String.valueOf(hdDatTruoc));
            
            contentPanel.add(new JSeparator());
            contentPanel.add(new JSeparator());
            
            addStatRow(contentPanel, "Tổng doanh thu:", 
                currencyFormat.format(tongDoanhThu) + "đ", SUCCESS_COLOR);
            addStatRow(contentPanel, "Trung bình/HĐ:", 
                currencyFormat.format(trungBinh) + "đ", new Color(52, 152, 219));
            
            mainPanel.add(contentPanel, BorderLayout.CENTER);
            
            // Button
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            buttonPanel.setBackground(Color.WHITE);
            
            JButton btnDong = createButton("Đóng", new Color(100, 100, 100));
            btnDong.setPreferredSize(new Dimension(100, 35));
            btnDong.addActionListener(e -> dialogThongKe.dispose());
            
            buttonPanel.add(btnDong);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            dialogThongKe.add(mainPanel);
            dialogThongKe.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi hiển thị thống kê: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Thêm hàng thống kê vào panel
     */
    private void addStatRow(JPanel panel, String label, String value) {
        addStatRow(panel, label, value, Color.BLACK);
    }
    
    private void addStatRow(JPanel panel, String label, String value, Color valueColor) {
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblValue.setForeground(valueColor);
        lblValue.setHorizontalAlignment(JLabel.RIGHT);
        
        panel.add(lblLabel);
        panel.add(lblValue);
    }
    
    /**
     * Cập nhật thông tin thống kê
     */
    private void capNhatThongTin() {
        int tongSo = tableModel.getRowCount();
        double tongDoanhThu = 0;
        int chuaTT = 0;
        int daTT = 0;
        
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String trangThai = tableModel.getValueAt(i, 8).toString();
            
            if ("Chưa thanh toán".equals(trangThai)) {
                chuaTT++;
            } else if ("Đã thanh toán".equals(trangThai)) {
                daTT++;
                
                // Chỉ tính doanh thu cho hóa đơn đã thanh toán
                String tongTienStr = tableModel.getValueAt(i, 7).toString()
                    .replace("đ", "").replace(",", "").replace(".", "").trim();
                try {
                    tongDoanhThu += Double.parseDouble(tongTienStr);
                } catch (Exception e) {
                    // Bỏ qua lỗi parse
                }
            }
        }
        
        lblThongTin.setText(String.format(
            "Tổng số hóa đơn: %d  |  Đã thanh toán: %d  |  Chưa thanh toán: %d",
            tongSo, daTT, chuaTT
        ));
        
        lblTongDoanhThu.setText("Tổng doanh thu: " + currencyFormat.format(tongDoanhThu) + "đ");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        
        if (o == btnTimKiem) {
            timKiemHoaDon();
        } else if (o == btnLamMoi) {
            lamMoiForm();
        } else if (o == btnXemChiTiet) {
            xemChiTietHoaDon();
        } else if (o == btnInHoaDon) {
            inHoaDon();
        } else if (o == btnThongKe) {
            hienThiThongKe();
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && SwingUtilities.isLeftMouseButton(e)) {
            xemChiTietHoaDon();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
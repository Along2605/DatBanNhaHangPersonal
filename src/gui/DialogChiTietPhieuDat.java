package gui;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


import entity.BanAn;
import java.awt.*;

import java.text.NumberFormat;
import java.util.Locale;

public class DialogChiTietPhieuDat extends JDialog{
    
    private static final long serialVersionUID = 1L;
    private static final Color MAIN_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(76, 175, 80);
    private static final Color WARNING_COLOR = new Color(255, 152, 0);
    
    private BanAn banAn;
    private JTable tableMonAn;
    private DefaultTableModel tableModel;
    private JLabel lblTongTien;
    
    public DialogChiTietPhieuDat(Frame parent, BanAn banAn) {
        super(parent, "Chi tiết phiếu đặt bàn", true);
        this.banAn = banAn;
        
        setSize(960, 700);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));
        
        initComponents();
        loadThongTinPhieuDat();
    }
    
    private void initComponents() {
        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("CHI TIẾT PHIẾU ĐẶT BÀN", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitle.setForeground(MAIN_COLOR);
        lblTitle.setBorder(new EmptyBorder(0, 0, 15, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Panel content (chia 2 phần: thông tin + món ăn)
        JPanel contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBackground(Color.WHITE);
        
        // Phần thông tin phiếu đặt
        contentPanel.add(createThongTinPanel(), BorderLayout.NORTH);
        
        // Phần danh sách món ăn
        contentPanel.add(createMonAnPanel(), BorderLayout.CENTER);
        
        // Phần tổng tiền
        contentPanel.add(createTongTienPanel(), BorderLayout.SOUTH);
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Panel nút
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        
        add(mainPanel);
    }
    
    /**
     * Tạo panel thông tin phiếu đặt
     */
    private JPanel createThongTinPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                new LineBorder(MAIN_COLOR, 2),
                "Thông tin phiếu đặt",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                MAIN_COLOR
            ),
            new EmptyBorder(10, 10, 10, 10)
        ));
        
        // Dòng 1: Thông tin cơ bản
        JPanel row1 = new JPanel(new GridLayout(1, 4, 15, 0));
        row1.setBackground(Color.WHITE);
        
        row1.add(createInfoField("Mã phiếu đặt:", "PD250112001", false));
        row1.add(createInfoField("Ngày đặt:", "15/12/2024 18:00", false));
        row1.add(createInfoField("Số người:", "4 người", false));
        row1.add(createInfoField("Trạng thái:", "Đã đặt", true));
        
        // Dòng 2: Thông tin khách hàng & bàn
        JPanel row2 = new JPanel(new GridLayout(1, 4, 15, 0));
        row2.setBackground(Color.WHITE);
        
        row2.add(createInfoField("Khách hàng:", "Nguyễn Văn A", false));
        row2.add(createInfoField("SĐT:", "0912345678", false));
        row2.add(createInfoField("Bàn:", banAn.getTenBan(), false));
        row2.add(createInfoField("Khu vực:", 
            banAn.getKhuVuc() != null ? banAn.getKhuVuc().getTenKhuVuc() : "", false));
        
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
                "Danh sách món đã đặt trước",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 14),
                MAIN_COLOR
            ),
            new EmptyBorder(10, 10, 10, 10)
        ));
           
        // Table món ăn
        String[] columns = {"STT", "Tên món", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú"};
        
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
        
        // Header style
        JTableHeader header = tableMonAn.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(MAIN_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Column widths
        tableMonAn.getColumnModel().getColumn(0).setPreferredWidth(50);   // STT
        tableMonAn.getColumnModel().getColumn(1).setPreferredWidth(200);  // Tên món
        tableMonAn.getColumnModel().getColumn(2).setPreferredWidth(100);  // Đơn giá
        tableMonAn.getColumnModel().getColumn(3).setPreferredWidth(80);   // Số lượng
        tableMonAn.getColumnModel().getColumn(4).setPreferredWidth(120);  // Thành tiền
        tableMonAn.getColumnModel().getColumn(5).setPreferredWidth(200);  // Ghi chú
        
        JScrollPane scrollPane = new JScrollPane(tableMonAn);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        scrollPane.setPreferredSize(new Dimension(0, 250));
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    /**
     * Tạo panel tổng tiền
     */
    private JPanel createTongTienPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        // Panel bên trái: Ghi chú
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBackground(Color.WHITE);
        
        JLabel lblGhiChuTitle = new JLabel("Ghi chú:");
        lblGhiChuTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        leftPanel.add(lblGhiChuTitle, BorderLayout.NORTH);
        
        JTextArea txtGhiChu = new JTextArea(3, 30);
        txtGhiChu.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.setText("Khách yêu cầu bàn gần cửa sổ, view đẹp");
        txtGhiChu.setEditable(false);
        txtGhiChu.setBackground(new Color(245, 245, 245));
        txtGhiChu.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200)),
            new EmptyBorder(5, 8, 5, 8)
        ));
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);
        scrollGhiChu.setBorder(new LineBorder(new Color(200, 200, 200)));
        leftPanel.add(scrollGhiChu, BorderLayout.CENTER);
        
        // Panel bên phải: Tiền cọc và tổng tiền
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(new Color(245, 250, 255));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MAIN_COLOR, 2),
            new EmptyBorder(0, 20, 0, 20)
        ));
//        rightPanel.setPreferredSize(new Dimension(350, 0));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 0, 5, 0);
        
        // Tiền cọc
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        JLabel lblTienCocLabel = new JLabel("Tiền cọc:");
        lblTienCocLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rightPanel.add(lblTienCocLabel, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.5;
        JLabel lblTienCocValue = new JLabel("200,000đ");
        lblTienCocValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTienCocValue.setHorizontalAlignment(JLabel.RIGHT);
        lblTienCocValue.setForeground(WARNING_COLOR);
        rightPanel.add(lblTienCocValue, gbc);
        
        // Tổng tiền món
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblTongMonLabel = new JLabel("Tổng tiền món:");
        lblTongMonLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        rightPanel.add(lblTongMonLabel, gbc);
        
        gbc.gridx = 1;
        JLabel lblTongMonValue = new JLabel("450,000đ");
        lblTongMonValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTongMonValue.setHorizontalAlignment(JLabel.RIGHT);
        rightPanel.add(lblTongMonValue, gbc);
        
        // Separator
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JSeparator separator = new JSeparator();
        separator.setForeground(MAIN_COLOR);
        rightPanel.add(separator, gbc);
        
        // Tổng cộng
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        JLabel lblTongCongLabel = new JLabel("TỔNG CỘNG:");
        lblTongCongLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTongCongLabel.setForeground(MAIN_COLOR);
        rightPanel.add(lblTongCongLabel, gbc);
        
        gbc.gridx = 1;
        lblTongTien = new JLabel("650,000đ");
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTongTien.setHorizontalAlignment(JLabel.RIGHT);
        lblTongTien.setForeground(new Color(211, 47, 47));
        rightPanel.add(lblTongTien, gbc);
        
        // Note nhỏ
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JLabel lblNote = new JLabel("* Chưa bao gồm VAT và phí dịch vụ");
        lblNote.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblNote.setForeground(Color.GRAY);
        rightPanel.add(lblNote, gbc);
        
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Tạo panel nút
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(Color.WHITE);
        
        JButton btnXacNhan = createButton("Xác nhận phiếu", SUCCESS_COLOR);
        btnXacNhan.setPreferredSize(new Dimension(160, 40));
        btnXacNhan.addActionListener(e -> xacNhanPhieu());
        
        JButton btnChuyenBan = createButton("Chuyển bàn", new Color(52, 152, 219));
        btnChuyenBan.setPreferredSize(new Dimension(160, 40));
        btnChuyenBan.addActionListener(e-> chuyenBan());
              
        JButton btnHuyPhieu = createButton("Hủy phiếu", new Color(244, 67, 54));
        btnHuyPhieu.setPreferredSize(new Dimension(160, 40));
        btnHuyPhieu.addActionListener(e -> huyPhieu());
        
        JButton btnInPhieu = createButton("In phiếu", new Color(103, 58, 183));
        btnInPhieu.setPreferredSize(new Dimension(160, 40));
        btnInPhieu.addActionListener(e -> inPhieu());
        
        JButton btnDong = createButton("Đóng", new Color(100, 100, 100));
        btnDong.setPreferredSize(new Dimension(160, 40));
        btnDong.addActionListener(e -> dispose());
        
        panel.add(btnXacNhan);
        panel.add(btnChuyenBan);
        panel.add(btnHuyPhieu);
        panel.add(btnInPhieu);
        panel.add(btnDong);
        
        return panel;
    }
    
    private Object chuyenBan() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
     * Tạo field thông tin
     */
    private JPanel createInfoField(String label, String value, boolean isStatus) {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBackground(Color.WHITE);
        
        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblLabel.setForeground(Color.GRAY);
        
        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        if (isStatus) {
            lblValue.setOpaque(true);
            lblValue.setHorizontalAlignment(JLabel.CENTER);
            lblValue.setBorder(new EmptyBorder(5, 10, 5, 10));
            
            // Màu sắc theo trạng thái
            if ("Đã đặt".equals(value)) {
                lblValue.setBackground(WARNING_COLOR);
                lblValue.setForeground(Color.WHITE);
            } else if ("Đã xác nhận".equals(value)) {
                lblValue.setBackground(SUCCESS_COLOR);
                lblValue.setForeground(Color.WHITE);
            } else if ("Đã hủy".equals(value)) {
                lblValue.setBackground(new Color(158, 158, 158));
                lblValue.setForeground(Color.WHITE);
            }
        }
        
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
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
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
     * Load thông tin phiếu đặt
     * TODO: Load từ database
     */
    private void loadThongTinPhieuDat() {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // TODO: Load từ database
        // String maPhieuDat = ... (lấy mã phiếu đặt từ bàn)
        // PhieuDatBan phieuDat = phieuDatDAO.getPhieuDatTheoBan(banAn.getMaBan());
        // List<ChiTietPhieuDat> dsMonAn = chiTietPhieuDatDAO.getMonAnTheoPhieu(maPhieuDat);
        
        // Dữ liệu demo
        Object[][] demoData = {
            {1, "Phở bò đặc biệt", "75,000đ", 2, "150,000đ", "Ít hành"},
            {2, "Cơm rang dương châu", "65,000đ", 1, "65,000đ", ""},
            {3, "Gỏi cuốn tôm thịt", "45,000đ", 2, "90,000đ", "Thêm rau"},
            {4, "Nước chanh tươi", "20,000đ", 4, "80,000đ", "Ít đá"},
            {5, "Chè ba màu", "25,000đ", 2, "50,000đ", ""}
        };
        
        for (Object[] row : demoData) {
            tableModel.addRow(row);
        }
        
        // Cập nhật tổng tiền
        tinhTongTien();
    }
    
    /**
     * Tính tổng tiền
     */
    private void tinhTongTien() {
        // TODO: Tính từ dữ liệu thực
        double tongTienMon = 435000;
        double tienCoc = 200000;
        double tongCong = tongTienMon + tienCoc;
        
        NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
        lblTongTien.setText(currencyFormat.format(tongCong) + "đ");
    }
    
    /**
     * Xác nhận phiếu đặt
     * TODO: Cập nhật database
     */
    private void xacNhanPhieu() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Xác nhận phiếu đặt này?\n\n" +
            "Sau khi xác nhận, phiếu đặt sẽ được đảm bảo và không thể hủy!",
            "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Cập nhật database
            // phieuDatDAO.xacNhanPhieu(maPhieuDat);
            
            JOptionPane.showMessageDialog(this,
                "Xác nhận phiếu đặt thành công!",
                "Thành công", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
        }
    }
    
    /**
     * Hủy phiếu đặt
     * TODO: Cập nhật database
     */
    private void huyPhieu() {
        String lyDo = JOptionPane.showInputDialog(this,
            "Vui lòng nhập lý do hủy phiếu:",
            "Hủy phiếu đặt", JOptionPane.QUESTION_MESSAGE);
        
        if (lyDo == null || lyDo.trim().isEmpty()) {
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Xác nhận hủy phiếu đặt?\n\n" +
            "Lý do: " + lyDo + "\n\n" +
            "Tiền cọc sẽ được hoàn lại cho khách hàng!",
            "Xác nhận hủy", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            // TODO: Cập nhật database
            // phieuDatDAO.huyPhieu(maPhieuDat, lyDo);
            
            JOptionPane.showMessageDialog(this,
                "Hủy phiếu đặt thành công!",
                "Thành công", JOptionPane.INFORMATION_MESSAGE);
            
            dispose();
        }
    }
    
    /**
     * In phiếu đặt
     * TODO: Implement in phiếu
     */
    private void inPhieu() {
        JOptionPane.showMessageDialog(this,
            "Chức năng in phiếu đặt sẽ được cài đặt sau!",
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

	
}
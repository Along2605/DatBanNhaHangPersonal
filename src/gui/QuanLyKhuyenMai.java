package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;

import dao.KhuyenMaiDAO;
import entity.KhuyenMai;

import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuanLyKhuyenMai extends JPanel implements ActionListener, MouseListener {

    private static final long serialVersionUID = 1L;
    
    // ========== COMPONENTS ==========
    // Form nhập liệu
    private JTextField txtMaKhuyenMai;
    private JTextField txtTenKhuyenMai;
    private JTextField txtPhanTramGiam;
    private JTextField txtSoTienGiam;
    private JDateChooser dateNgayBatDau;
    private JDateChooser dateNgayKetThuc;
    private JComboBox<String> cboLoaiKhuyenMai;
    private JComboBox<String> cboTrangThai;
    
    // Tìm kiếm
    private JTextField txtTimKiem;
    private JComboBox<String> cboTimKiemLoai;
    private JComboBox<String> cboTimKiemTrangThai;
    
    // Bảng dữ liệu
    private JTable tableKhuyenMai;
    private DefaultTableModel tableModel;
    
    // Buttons
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JButton btnTimKiem;
    
    // ========== COLORS ==========
    private final Color MAIN_COLOR = new Color(41, 128, 185);
    private final Color HOVER_COLOR = new Color(52, 152, 219);
    private final Color SUCCESS_COLOR = new Color(46, 125, 50);
    private final Color WARNING_COLOR = new Color(255, 152, 0);
    private final Color DANGER_COLOR = new Color(211, 47, 47);
    private final Color BACKGROUND_COLOR = new Color(245, 245, 245);

    
    public QuanLyKhuyenMai() {
        setLayout(new BorderLayout(10, 10));
        setBackground(BACKGROUND_COLOR);
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Thêm các panel
        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createMainPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);
        
        // TODO: Load dữ liệu từ database
        // loadDanhSachKhuyenMai();
        
        // TODO: Tạo mã khuyến mãi tự động
        // taoMaKhuyenMaiTuDong();
    }

    
    /**
     * Tạo panel tiêu đề
     */
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BACKGROUND_COLOR);
        
        JLabel lblTitle = new JLabel("QUẢN LÝ KHUYẾN MÃI");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(MAIN_COLOR);
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        
        panel.add(lblTitle, BorderLayout.CENTER);
        panel.setBorder(new EmptyBorder(0, 0, 15, 0));
        
        return panel;
    }

    
    /**
     * Tạo panel chính (chứa form + bảng + tìm kiếm)
     */
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(BACKGROUND_COLOR);
        
        // Panel bên trái: Form + Tìm kiếm
        JPanel leftPanel = new JPanel(new BorderLayout(0, 10));
        leftPanel.setBackground(BACKGROUND_COLOR);
        leftPanel.setPreferredSize(new Dimension(380, 0));
        
        leftPanel.add(createFormPanel(), BorderLayout.CENTER);
        leftPanel.add(createSearchPanel(), BorderLayout.SOUTH);
        
        // Panel bên phải: Bảng dữ liệu
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(createTablePanel(), BorderLayout.CENTER);
        
        return panel;
    }

    /**
     * Tạo panel form nhập liệu
     */
    private JPanel createFormPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MAIN_COLOR, 2, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("Thông tin khuyến mãi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(MAIN_COLOR);
        lblTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        int row = 0;
        
        // Mã khuyến mãi (tự động)
        txtMaKhuyenMai = createTextField();
        txtMaKhuyenMai.setEditable(false);
        txtMaKhuyenMai.setBackground(new Color(240, 240, 240));
        addFormField(formPanel, gbc, row++, "Mã khuyến mãi:", txtMaKhuyenMai);
        
        // Tên khuyến mãi
        txtTenKhuyenMai = createTextField();
        addFormField(formPanel, gbc, row++, "Tên khuyến mãi: *", txtTenKhuyenMai);
        
        // Loại khuyến mãi
        cboLoaiKhuyenMai = createComboBox(new String[]{
            "Giảm theo phần trăm",
            "Giảm theo số tiền",
            "Combo",
            "Voucher",
            "Khác"
        });
        addFormField(formPanel, gbc, row++, "Loại khuyến mãi: *", cboLoaiKhuyenMai);
        
        // Phần trăm giảm
        txtPhanTramGiam = createTextField();
        txtPhanTramGiam.setText("0");
        addFormField(formPanel, gbc, row++, "Phần trăm giảm (%):", txtPhanTramGiam);
        
        // Số tiền giảm
        txtSoTienGiam = createTextField();
        txtSoTienGiam.setText("0");
        addFormField(formPanel, gbc, row++, "Số tiền giảm (đ):", txtSoTienGiam);
        
        // Ngày bắt đầu
        dateNgayBatDau = createDateChooser();
        addFormField(formPanel, gbc, row++, "Ngày bắt đầu: *", dateNgayBatDau);
        
        // Ngày kết thúc
        dateNgayKetThuc = createDateChooser();
        addFormField(formPanel, gbc, row++, "Ngày kết thúc: *", dateNgayKetThuc);
        
        // Trạng thái
        cboTrangThai = createComboBox(new String[]{
            "Đang áp dụng",
            "Chưa áp dụng",
            "Đã hết hạn"
        });
        addFormField(formPanel, gbc, row++, "Trạng thái:", cboTrangThai);
        
        // Scroll pane cho form
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        return mainPanel;
    }

    // ==================== SEARCH PANEL ====================
    /**
     * Tạo panel tìm kiếm
     */
    private JPanel createSearchPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MAIN_COLOR, 2, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        mainPanel.setPreferredSize(new Dimension(0, 220));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("Tìm kiếm");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(MAIN_COLOR);
        lblTitle.setBorder(new EmptyBorder(0, 0, 10, 0));
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        
        // Form tìm kiếm
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        int row = 0;
        
        // Từ khóa
        txtTimKiem = createTextField();
        addFormField(formPanel, gbc, row++, "Tìm theo tên:", txtTimKiem);
        
        // Loại khuyến mãi
        cboTimKiemLoai = createComboBox(new String[]{
            "-- Tất cả --",
            "Giảm theo phần trăm",
            "Giảm theo số tiền",
            "Combo",
            "Voucher",
            "Khác"
        });
        addFormField(formPanel, gbc, row++, "Loại:", cboTimKiemLoai);
        
        // Trạng thái
        cboTimKiemTrangThai = createComboBox(new String[]{
            "-- Tất cả --",
            "Đang áp dụng",
            "Chưa áp dụng",
            "Đã hết hạn"
        });
        addFormField(formPanel, gbc, row++, "Trạng thái:", cboTimKiemTrangThai);
        
        // Nút tìm kiếm
        btnTimKiem = createButton("Tìm kiếm", MAIN_COLOR);
        btnTimKiem.setPreferredSize(new Dimension(0, 35));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        formPanel.add(btnTimKiem, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Add action listener
        btnTimKiem.addActionListener(this);
        
        return mainPanel;
    }

    // ==================== TABLE PANEL ====================
    /**
     * Tạo panel bảng dữ liệu
     */
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(MAIN_COLOR, 2, true),
            new EmptyBorder(15, 15, 15, 15)
        ));
        
        // Tiêu đề
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        
        JLabel lblTitle = new JLabel("Danh sách khuyến mãi");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(MAIN_COLOR);
        titlePanel.add(lblTitle, BorderLayout.WEST);
        
        JLabel lblInfo = new JLabel("(Double-click để xem chi tiết)");
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfo.setForeground(Color.GRAY);
        titlePanel.add(lblInfo, BorderLayout.EAST);
        
        panel.add(titlePanel, BorderLayout.NORTH);
        
        // Bảng
        String[] columns = {
            "Mã KM", "Tên khuyến mãi", "Loại", "% Giảm", 
            "Tiền giảm", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
        };
        
        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableKhuyenMai = new JTable(tableModel);
        tableKhuyenMai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableKhuyenMai.setRowHeight(35);
        tableKhuyenMai.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableKhuyenMai.setShowGrid(true);
        tableKhuyenMai.setGridColor(new Color(230, 230, 230));
        
        // Header style
        JTableHeader header = tableKhuyenMai.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(MAIN_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Column widths
        tableKhuyenMai.getColumnModel().getColumn(0).setPreferredWidth(80);   // Mã
        tableKhuyenMai.getColumnModel().getColumn(1).setPreferredWidth(200);  // Tên
        tableKhuyenMai.getColumnModel().getColumn(2).setPreferredWidth(120);  // Loại
        tableKhuyenMai.getColumnModel().getColumn(3).setPreferredWidth(80);   // %
        tableKhuyenMai.getColumnModel().getColumn(4).setPreferredWidth(100);  // Tiền
        tableKhuyenMai.getColumnModel().getColumn(5).setPreferredWidth(120);  // Ngày BD
        tableKhuyenMai.getColumnModel().getColumn(6).setPreferredWidth(120);  // Ngày KT
        tableKhuyenMai.getColumnModel().getColumn(7).setPreferredWidth(120);  // Trạng thái
        
        JScrollPane scrollPane = new JScrollPane(tableKhuyenMai);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Info panel
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        infoPanel.setBackground(new Color(232, 245, 233));
        infoPanel.setBorder(new EmptyBorder(8, 10, 8, 10));
        
        JLabel lblTableInfo = new JLabel("Tổng số: 0 khuyến mãi");
        lblTableInfo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTableInfo.setForeground(new Color(46, 125, 50));
        infoPanel.add(lblTableInfo);
        
        panel.add(infoPanel, BorderLayout.SOUTH);
        
        // Add mouse listener
        tableKhuyenMai.addMouseListener(this);
        
        return panel;
    }

    // ==================== BUTTON PANEL ====================
    /**
     * Tạo panel chứa các nút chức năng
     */
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(BACKGROUND_COLOR);
        
        btnThem = createButton("Thêm", SUCCESS_COLOR);
        btnThem.setPreferredSize(new Dimension(120, 40));
        btnThem.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnSua = createButton("Sửa", WARNING_COLOR);
        btnSua.setPreferredSize(new Dimension(120, 40));
        btnSua.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnXoa = createButton("Xóa", DANGER_COLOR);
        btnXoa.setPreferredSize(new Dimension(120, 40));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnLamMoi = createButton("Làm mới", new Color(100, 100, 100));
        btnLamMoi.setPreferredSize(new Dimension(120, 40));
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        panel.add(btnThem);
        panel.add(btnSua);
        panel.add(btnXoa);
        panel.add(btnLamMoi);
        
        // Add action listeners
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLamMoi.addActionListener(this);
        
        return panel;
    }

    // ==================== HELPER METHODS ====================
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
        if (labelText.contains("*")) {
            label.setForeground(DANGER_COLOR);
        }
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
     * Tạo date chooser với style đồng nhất
     */
    private JDateChooser createDateChooser() {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dateChooser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        dateChooser.setPreferredSize(new Dimension(0, 30));
        return dateChooser;
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
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }

 
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == btnThem) {
            
            themKhuyenMai();
        } else if (source == btnSua) {
            
            suaKhuyenMai();
        } else if (source == btnXoa) {
            
            xoaKhuyenMai();
        } else if (source == btnLamMoi) {
            
            lamMoiForm();
        } else if (source == btnTimKiem) {
            
            timKiemKhuyenMai();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && e.getSource() == tableKhuyenMai) {
            // TODO: Double-click để xem chi tiết
            hienThiChiTiet();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

   
    
    /**
     * TODO: Thêm khuyến mãi mới
     */
    private void themKhuyenMai() {
        // 1. Validate dữ liệu nhập
        // 2. Tạo object KhuyenMai
        // 3. Gọi DAO để thêm vào database
        // 4. Refresh bảng
        // 5. Thông báo thành công
        
        JOptionPane.showMessageDialog(this,
            "Chức năng đang được phát triển!",
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * TODO: Sửa thông tin khuyến mãi
     */
    private void suaKhuyenMai() {
        // 1. Kiểm tra đã chọn dòng chưa
        // 2. Validate dữ liệu nhập
        // 3. Update object KhuyenMai
        // 4. Gọi DAO để cập nhật database
        // 5. Refresh bảng
        // 6. Thông báo thành công
        
        int selectedRow = tableKhuyenMai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn khuyến mãi cần sửa!",
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this,
            "Chức năng đang được phát triển!",
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * TODO: Xóa khuyến mãi
     */
    private void xoaKhuyenMai() {
        // 1. Kiểm tra đã chọn dòng chưa
        // 2. Xác nhận xóa
        // 3. Kiểm tra ràng buộc (có hóa đơn sử dụng không?)
        // 4. Gọi DAO để xóa
        // 5. Refresh bảng
        // 6. Thông báo thành công
        
        int selectedRow = tableKhuyenMai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn khuyến mãi cần xóa!",
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this,
            "Bạn có chắc chắn muốn xóa khuyến mãi này?",
            "Xác nhận", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this,
                "Chức năng đang được phát triển!",
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * TODO: Tìm kiếm khuyến mãi
     */
    private void timKiemKhuyenMai() {
        // 1. Lấy tiêu chí tìm kiếm
        // 2. Gọi DAO để tìm kiếm
        // 3. Hiển thị kết quả lên bảng
        
        JOptionPane.showMessageDialog(this,
            "Chức năng đang được phát triển!",
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * TODO: Hiển thị chi tiết khuyến mãi
     */
    private void hienThiChiTiet() {
        // 1. Lấy dòng được chọn
        // 2. Hiển thị thông tin lên form
        
        int selectedRow = tableKhuyenMai.getSelectedRow();
        if (selectedRow != -1) {
            String maKM = tableModel.getValueAt(selectedRow, 0).toString();
            
            JOptionPane.showMessageDialog(this,
                "Hiển thị chi tiết khuyến mãi: " + maKM,
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * TODO: Làm mới form
     */
    private void lamMoiForm() {
        // 1. Clear tất cả các trường nhập
        // 2. Tạo mã khuyến mãi mới
        // 3. Bỏ chọn bảng
        // 4. Load lại danh sách
        
        txtMaKhuyenMai.setText("");
        txtTenKhuyenMai.setText("");
        txtPhanTramGiam.setText("0");
        txtSoTienGiam.setText("0");
        dateNgayBatDau.setDate(null);
        dateNgayKetThuc.setDate(null);
        cboLoaiKhuyenMai.setSelectedIndex(0);
        cboTrangThai.setSelectedIndex(0);
        
        txtTimKiem.setText("");
        cboTimKiemLoai.setSelectedIndex(0);
        cboTimKiemTrangThai.setSelectedIndex(0);
        
        tableKhuyenMai.clearSelection();
        
        // TODO: Load lại danh sách
         loadDanhSachKhuyenMai();
    }
    
    
    
	private void loadDanhSachKhuyenMai() {
		// TODO Auto-generated method stub
		try {
			tableModel.setRowCount(0);
			KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAO();
			List<KhuyenMai> dsKhuyenMai = khuyenMaiDAO.getKhuyenMaiHopLe();
			if (dsKhuyenMai != null && !dsKhuyenMai.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				NumberFormat currencyFormat = NumberFormat.getInstance(new Locale("vi", "VN"));
				for (KhuyenMai km : dsKhuyenMai) {
					String ngayBD = km.getNgayBatDau() != null
							? sdf.format(Date.from(km.getNgayBatDau().atZone(ZoneId.systemDefault()).toInstant()))
							: "";

					String ngayKT = km.getNgayKetThuc() != null
							? sdf.format(Date.from(km.getNgayKetThuc().atZone(ZoneId.systemDefault()).toInstant()))
							: "";

					String phanTramGiam = String.format("%.2f", km.getPhanTramGiam());
					String soTienGiam = currencyFormat.format(km.getSoTienGiam());
					String trangThai = km.isTrangThai() ? "Hợp lệ": "Không hợp lệ";

					tableModel.addRow(new Object[] { km.getMaKhuyenMai(), km.getTenKhuyenMai(), km.getLoaiKhuyenMai(),
							phanTramGiam, soTienGiam, ngayBD, ngayKT, trangThai });					
					
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

//	private String xacDinhTrangThai(KhuyenMai km) {
//		
//         LocalDateTime now = LocalDateTime.now();
//         LocalDateTime ngayBD = km.getNgayBatDau();
//         LocalDateTime ngayKT = km.getNgayKetThuc();
//         
//         if (now.isBefore(ngayBD)) {
//             return "Chưa áp dụng";
//         } else if (now.isAfter(ngayKT)) {
//             return "Đã hết hạn";
//         } else {
//             return km.isTrangThai() ? "Đang áp dụng" : "Đã hết hạn";
//         }
//        
//        return "Đang áp dụng";
//	}
	
	
	private void capNhatLabelTongSo(int tongSo) {
        // Tìm label trong info panel và cập nhật
        Component[] components = ((JPanel)((JPanel)getComponent(1))
            .getComponent(1)).getComponents();
        
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel panel = (JPanel) comp;
                if (panel.getBorder() != null) {
                    Component[] panelComps = panel.getComponents();
                    for (Component panelComp : panelComps) {
                        if (panelComp instanceof JLabel) {
                            JLabel label = (JLabel) panelComp;
                            if (label.getText().contains("Tổng số")) {
                                label.setText("Tổng số: " + tongSo + " khuyến mãi");
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

	public static void main(String[] args) {
    	SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thông tin cá nhân");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 650);
            frame.setMinimumSize(new Dimension(800, 500));
            frame.setLocationRelativeTo(null);
            frame.add(new QuanLyKhuyenMai());
            frame.setVisible(true);
        });
	}

}
   
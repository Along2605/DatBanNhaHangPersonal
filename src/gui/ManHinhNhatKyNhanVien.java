package gui;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.toedter.calendar.JDateChooser;
import dao.NhanVienDAO;
import dao.NhatKyThaoTacDAO;
import entity.NhanVien;
import entity.NhatKyThaoTac;
import entity.NhatKyThaoTac.LoaiThaoTac;

public class ManHinhNhatKyNhanVien extends JPanel {
    private JComboBox<String> cbNhanVien;
    private JComboBox<String> cbLoaiThaoTac;
    private JComboBox<String> cbBang;
    private JDateChooser dateChooserTu;
    private JDateChooser dateChooserDen;
    private JTextField txtTimKiem;
    private JButton btnTimKiem;
    private JButton btnXemChiTiet;
    private JButton btnLamMoi;
    private JButton btnXuatBaoCao;
    private JTable table;
    private DefaultTableModel model;

    private NhatKyThaoTacDAO logDAO;
    private NhanVienDAO nhanVienDAO;

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public ManHinhNhatKyNhanVien() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#EAF1F9"));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        logDAO = NhatKyThaoTacDAO.getInstance();
        nhanVienDAO = new NhanVienDAO();

        // Title Panel
        JPanel pnTitle = createTitlePanel();
        add(pnTitle, BorderLayout.NORTH);

        // Center Panel (Filter + Table)
        JPanel pnCenter = new JPanel(new BorderLayout(0, 10));
        pnCenter.setBackground(Color.decode("#EAF1F9"));

        // Filter Panel
        JPanel pnFilter = createFilterPanel();
        pnCenter.add(pnFilter, BorderLayout.NORTH);

        // Table Panel
        JPanel pnTable = createTablePanel();
        pnCenter.add(pnTable, BorderLayout.CENTER);
        add(pnCenter, BorderLayout.CENTER);

        // Button Panel
        JPanel pnButtons = createButtonPanel();
        add(pnButtons, BorderLayout.SOUTH);

        // Load data
        loadAllLogs();
        loadNhanVienComboBox();

        addEventListeners();
    }

    private JPanel createTitlePanel() {
        JPanel pnTitle = new JPanel(new BorderLayout());
        pnTitle.setBackground(Color.decode("#EAF1F9"));

        JLabel lblTitle = new JLabel("NHẬT KÝ HOẠT ĐỘNG NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.decode("#333333"));
        lblTitle.setBorder(new EmptyBorder(5, 0, 10, 0));

        pnTitle.add(lblTitle, BorderLayout.CENTER);
        return pnTitle;
    }

    private JPanel createFilterPanel() {
        JPanel pnFilter = new JPanel();
        pnFilter.setLayout(new BoxLayout(pnFilter, BoxLayout.Y_AXIS));
        pnFilter.setBackground(Color.WHITE);
        pnFilter.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.decode("#CCCCCC")),
                "Bộ lọc tìm kiếm",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14)
            ),
            new EmptyBorder(10, 10, 10, 10)
        ));

        // Row 1: Nhân viên, Loại thao tác, Bảng
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row1.setBackground(Color.WHITE);

        JLabel lblNhanVien = new JLabel("Nhân viên:");
        lblNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
        cbNhanVien = new JComboBox<>();
        cbNhanVien.setPreferredSize(new Dimension(200, 30));
        cbNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblLoaiThaoTac = new JLabel("Loại thao tác:");
        lblLoaiThaoTac.setFont(new Font("Arial", Font.PLAIN, 14));
        cbLoaiThaoTac = new JComboBox<>(new String[]{"Tất cả", "Thêm", "Sửa", "Xóa"});
        cbLoaiThaoTac.setPreferredSize(new Dimension(150, 30));
        cbLoaiThaoTac.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblBang = new JLabel("Bảng:");
        lblBang.setFont(new Font("Arial", Font.PLAIN, 14));
        cbBang = new JComboBox<>(new String[]{
            "Tất cả", "HoaDon", "MonAn", "KhachHang", "NhanVien", "BanAn",
            "PhieuDatBan", "KhuyenMai", "ChiTietHoaDon"
        });
        cbBang.setPreferredSize(new Dimension(150, 30));
        cbBang.setFont(new Font("Arial", Font.PLAIN, 14));

        row1.add(lblNhanVien);
        row1.add(cbNhanVien);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(lblLoaiThaoTac);
        row1.add(cbLoaiThaoTac);
        row1.add(Box.createHorizontalStrut(10));
        row1.add(lblBang);
        row1.add(cbBang);

        // Row 2: Khoảng thời gian, Tìm kiếm
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row2.setBackground(Color.WHITE);

        JLabel lblTuNgay = new JLabel("Từ ngày:");
        lblTuNgay.setFont(new Font("Arial", Font.PLAIN, 14));
        dateChooserTu = new JDateChooser();
        dateChooserTu.setPreferredSize(new Dimension(150, 30));
        dateChooserTu.setDateFormatString("dd-MM-yyyy");
        dateChooserTu.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblDenNgay = new JLabel("Đến ngày:");
        lblDenNgay.setFont(new Font("Arial", Font.PLAIN, 14));
        dateChooserDen = new JDateChooser();
        dateChooserDen.setPreferredSize(new Dimension(150, 30));
        dateChooserDen.setDateFormatString("dd-MM-yyyy");
        dateChooserDen.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel lblTimKiem = new JLabel("Tìm mã đối tượng:");
        lblTimKiem.setFont(new Font("Arial", Font.PLAIN, 14));
        txtTimKiem = createTextField(true);
        txtTimKiem.setPreferredSize(new Dimension(180, 30));

        btnTimKiem = createButton("Tìm kiếm", "img/search.png");

        row2.add(lblTuNgay);
        row2.add(dateChooserTu);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(lblDenNgay);
        row2.add(dateChooserDen);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(lblTimKiem);
        row2.add(txtTimKiem);
        row2.add(Box.createHorizontalStrut(10));
        row2.add(btnTimKiem);

        pnFilter.add(row1);
        pnFilter.add(Box.createVerticalStrut(5));
        pnFilter.add(row2);
        return pnFilter;
    }

    private JPanel createTablePanel() {
        JPanel pnTable = new JPanel(new BorderLayout());
        pnTable.setBackground(Color.WHITE);

        String[] headers = {
            "Mã Log", "Nhân viên", "Loại thao tác", "Bảng",
            "Mã đối tượng", "Thời gian", "Ghi chú"
        };

        model = new DefaultTableModel(headers, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(Color.decode("#B3D9FF"));
        table.setSelectionForeground(Color.BLACK);
        table.setGridColor(Color.decode("#DDDDDD"));
        table.setShowGrid(true);
        table.setAutoCreateRowSorter(true);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.decode("#2196F3"));
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));

        int[] columnWidths = {80, 150, 120, 120, 120, 150, 200};
        for (int i = 0; i < columnWidths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        // HIỂN THỊ CHỮ TIẾNG VIỆT CHO LOẠI THAO TÁC
        table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(SwingConstants.CENTER);

                if (!isSelected) {
                    String loai = value.toString();
                    if (loai.equals("THEM")) {
                        setText("Thêm");
                        c.setForeground(Color.decode("#4CAF50"));
                    } else if (loai.equals("SUA")) {
                        setText("Sửa");
                        c.setForeground(Color.decode("#FF9800"));
                    } else if (loai.equals("XOA")) {
                        setText("Xóa");
                        c.setForeground(Color.decode("#F44336"));
                    }
                    ((JLabel) c).setFont(new Font("Arial", Font.BOLD, 13));
                }
                return c;
            }
        });

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (!isSelected) {
                    if (row % 2 == 0) {
                        c.setBackground(Color.WHITE);
                    } else {
                        c.setBackground(Color.decode("#F5F5F5"));
                    }
                    c.setForeground(Color.BLACK);
                }

                if (column == 0 || column == 2 || column == 3 || column == 4 || column == 5) {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.CENTER);
                } else {
                    ((JLabel) c).setHorizontalAlignment(SwingConstants.LEFT);
                }

                if (column == 2 && !isSelected) {
                    String loai = value.toString();
                    if (loai.equals("THEM")) {
                        setText("Thêm");
                        c.setForeground(Color.decode("#4CAF50"));
                    } else if (loai.equals("SUA")) {
                        setText("Sửa");
                        c.setForeground(Color.decode("#FF9800"));
                    } else if (loai.equals("XOA")) {
                        setText("Xóa");
                        c.setForeground(Color.decode("#F44336"));
                    }
                    ((JLabel) c).setFont(new Font("Arial", Font.BOLD, 13));
                }

                return c;
            }
        });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.decode("#CCCCCC")),
            "Danh sách nhật ký thao tác",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Arial", Font.PLAIN, 14)
        ));
        pnTable.add(scroll, BorderLayout.CENTER);
        return pnTable;
    }

    private JPanel createButtonPanel() {
        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        pnButtons.setBackground(Color.decode("#EAF1F9"));

        btnXemChiTiet = createButton("Xem chi tiết", "img/view.png");
        btnXuatBaoCao = createButton("Xuất báo cáo", "img/export.png");
        btnLamMoi = createButton("Làm mới", "img/refresh.png");

        pnButtons.add(btnXemChiTiet);
        pnButtons.add(btnLamMoi);
        // pnButtons.add(btnXuatBaoCao); // Nếu muốn bật lại thì bỏ comment

        return pnButtons;
    }

    private void loadNhanVienComboBox() {
        try {
            List<NhanVien> dsNhanVien = nhanVienDAO.layDanhSachNhanVien();
            cbNhanVien.addItem("Tất cả");
            for (NhanVien nv : dsNhanVien) {
                cbNhanVien.addItem(nv.getMaNV() + " - " + nv.getHoTen());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi load danh sách nhân viên: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadAllLogs() {
        model.setRowCount(0);
        try {
            List<NhatKyThaoTac> dsLog = logDAO.layTatCa();
            displayLogs(dsLog);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi load nhật ký: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayLogs(List<NhatKyThaoTac> dsLog) {
        model.setRowCount(0);
        for (NhatKyThaoTac log : dsLog) {
            String tenNV = log.getMaNV();
            try {
                NhanVien nv = nhanVienDAO.getNhanVienTheoMa(log.getMaNV());
                if (nv != null) {
                    tenNV = nv.getMaNV() + " - " + nv.getHoTen();
                }
            } catch (Exception e) { /* ignore */ }

            model.addRow(new Object[]{
                log.getMaLog(),
                tenNV,
                log.getLoaiThaoTac().getValue(), // Sẽ được render thành "Thêm"/"Sửa"/"Xóa" ở renderer
                log.getTenBang(),
                log.getMaDoiTuong(),
                log.getThoiGian().format(dateTimeFormatter),
                log.getGhiChu() != null ? log.getGhiChu() : ""
            });
        }
    }

    private void addEventListeners() {
        btnTimKiem.addActionListener(e -> timKiemLog());
        txtTimKiem.addActionListener(e -> timKiemLog());

        cbNhanVien.addActionListener(e -> {
            if (cbNhanVien.getSelectedIndex() > 0) timKiemLog();
        });

        btnLamMoi.addActionListener(e -> lamMoi());
        btnXemChiTiet.addActionListener(e -> xemChiTiet());
        btnXuatBaoCao.addActionListener(e -> xuatBaoCao());
    }

    private void timKiemLog() {
        try {
            List<NhatKyThaoTac> dsLog = logDAO.layTatCa();

            String selectedNV = cbNhanVien.getSelectedItem().toString();
            if (!selectedNV.equals("Tất cả")) {
                String maNV = selectedNV.split(" - ")[0];
                dsLog = dsLog.stream()
                    .filter(log -> log.getMaNV().equals(maNV))
                    .collect(Collectors.toList());
            }

            String loaiThaoTacStr = cbLoaiThaoTac.getSelectedItem().toString();
            if (!loaiThaoTacStr.equals("Tất cả")) {
                LoaiThaoTac loai = switch (loaiThaoTacStr) {
                    case "Thêm" -> LoaiThaoTac.THEM;
                    case "Sửa" -> LoaiThaoTac.SUA;
                    case "Xóa" -> LoaiThaoTac.XOA;
                    default -> null;
                };
                if (loai != null) {
                    dsLog = dsLog.stream()
                        .filter(log -> log.getLoaiThaoTac() == loai)
                        .collect(Collectors.toList());
                }
            }

            String bang = cbBang.getSelectedItem().toString();
            if (!bang.equals("Tất cả")) {
                dsLog = dsLog.stream()
                    .filter(log -> log.getTenBang().equals(bang))
                    .collect(Collectors.toList());
            }

            if (dateChooserTu.getDate() != null && dateChooserDen.getDate() != null) {
                LocalDate tuNgay = dateChooserTu.getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                LocalDate denNgay = dateChooserDen.getDate().toInstant()
                    .atZone(java.time.ZoneId.systemDefault()).toLocalDate();

                LocalDateTime tuNgayTime = tuNgay.atStartOfDay();
                LocalDateTime denNgayTime = denNgay.atTime(23, 59, 59);

                dsLog = dsLog.stream()
                    .filter(log -> !log.getThoiGian().isBefore(tuNgayTime) &&
                                   !log.getThoiGian().isAfter(denNgayTime))
                    .collect(Collectors.toList());
            }

            String maDoiTuong = txtTimKiem.getText().trim();
            if (!maDoiTuong.isEmpty()) {
                dsLog = dsLog.stream()
                    .filter(log -> log.getMaDoiTuong() != null &&
                                   log.getMaDoiTuong().toLowerCase().contains(maDoiTuong.toLowerCase()))
                    .collect(Collectors.toList());
            }

            displayLogs(dsLog);

            if (dsLog.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Không tìm thấy nhật ký nào phù hợp!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi tìm kiếm: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xemChiTiet() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn một bản ghi để xem chi tiết!",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int maLog = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
            List<NhatKyThaoTac> dsLog = logDAO.layTatCa();
            NhatKyThaoTac log = dsLog.stream()
                .filter(l -> l.getMaLog() == maLog)
                .findFirst()
                .orElse(null);

            if (log != null) {
                showDetailDialog(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Lỗi khi xem chi tiết: " + e.getMessage(),
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDetailDialog(NhatKyThaoTac log) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
            "Chi tiết nhật ký #" + log.getMaLog(), true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(700, 500);
        dialog.setLocationRelativeTo(this);

        JPanel pnContent = new JPanel();
        pnContent.setLayout(new BoxLayout(pnContent, BoxLayout.Y_AXIS));
        pnContent.setBorder(new EmptyBorder(15, 15, 15, 15));
        pnContent.setBackground(Color.WHITE);

        addDetailRow(pnContent, "Mã Log:", String.valueOf(log.getMaLog()));
        addDetailRow(pnContent, "Mã nhân viên:", log.getMaNV());
        addDetailRow(pnContent, "Loại thao tác:", switch (log.getLoaiThaoTac()) {
            case THEM -> "Thêm";
            case SUA -> "Sửa";
            case XOA -> "Xóa";
        });
        addDetailRow(pnContent, "Bảng:", log.getTenBang());
        addDetailRow(pnContent, "Mã đối tượng:", log.getMaDoiTuong());
        addDetailRow(pnContent, "Thời gian:", log.getThoiGian().format(dateTimeFormatter));
        addDetailRow(pnContent, "Ghi chú:", log.getGhiChu() != null ? log.getGhiChu() : "");

        if (log.getNoiDungCu() != null && !log.getNoiDungCu().isEmpty()) {
            pnContent.add(Box.createVerticalStrut(10));
            JLabel lblCu = new JLabel("Nội dung cũ:");
            lblCu.setFont(new Font("Arial", Font.BOLD, 14));
            pnContent.add(lblCu);

            JTextArea txtCu = new JTextArea(log.getNoiDungCu());
            txtCu.setEditable(false);
            txtCu.setLineWrap(true);
            txtCu.setWrapStyleWord(true);
            txtCu.setFont(new Font("Consolas", Font.PLAIN, 12));
            txtCu.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            JScrollPane scrollCu = new JScrollPane(txtCu);
            scrollCu.setPreferredSize(new Dimension(650, 100));
            pnContent.add(scrollCu);
        }

        if (log.getNoiDungMoi() != null && !log.getNoiDungMoi().isEmpty()) {
            pnContent.add(Box.createVerticalStrut(10));
            JLabel lblMoi = new JLabel("Nội dung mới:");
            lblMoi.setFont(new Font("Arial", Font.BOLD, 14));
            pnContent.add(lblMoi);

            JTextArea txtMoi = new JTextArea(log.getNoiDungMoi());
            txtMoi.setEditable(false);
            txtMoi.setLineWrap(true);
            txtMoi.setWrapStyleWord(true);
            txtMoi.setFont(new Font("Consolas", Font.PLAIN, 12));
            txtMoi.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            JScrollPane scrollMoi = new JScrollPane(txtMoi);
            scrollMoi.setPreferredSize(new Dimension(650, 100));
            pnContent.add(scrollMoi);
        }

        JScrollPane mainScroll = new JScrollPane(pnContent);
        dialog.add(mainScroll, BorderLayout.CENTER);

        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnButton.setBackground(Color.WHITE);
        JButton btnDong = createButton("Đóng", null);
        btnDong.addActionListener(e -> dialog.dispose());
        pnButton.add(btnDong);
        dialog.add(pnButton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void addDetailRow(JPanel panel, String label, String value) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        row.setBackground(Color.WHITE);

        JLabel lblLabel = new JLabel(label);
        lblLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblLabel.setPreferredSize(new Dimension(150, 25));

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Arial", Font.PLAIN, 14));

        row.add(lblLabel);
        row.add(lblValue);
        panel.add(row);
    }

    private void xuatBaoCao() {
        JOptionPane.showMessageDialog(this,
            "Chức năng xuất báo cáo đang được phát triển!",
            "Thông báo",
            JOptionPane.INFORMATION_MESSAGE);
    }

    private void lamMoi() {
        cbNhanVien.setSelectedIndex(0);
        cbLoaiThaoTac.setSelectedIndex(0);
        cbBang.setSelectedIndex(0);
        dateChooserTu.setDate(null);
        dateChooserDen.setDate(null);
        txtTimKiem.setText("");
        loadAllLogs();
    }

    private JTextField createTextField(boolean editable) {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setEditable(editable);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.decode("#CCCCCC")),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return textField;
    }

    private JButton createButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setPreferredSize(new Dimension(150, 38));
        button.setBackground(Color.decode("#2196F3"));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (iconPath != null) {
            try {
                ImageIcon icon = new ImageIcon(iconPath);
                Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(img));
            } catch (Exception e) {
                // ignore
            }
        }
        return button;
    }
}
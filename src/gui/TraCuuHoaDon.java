package gui;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.List;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import util.InHoaDonPDF;

public class TraCuuHoaDon extends JPanel implements MouseListener {

    private JTextField txtTimKiem;
    private JComboBox<String> cboTrangThai;
    private JButton btnTimKiem, btnLamMoi, btnXemChiTiet;

    private JTable tableHoaDon;
    private DefaultTableModel modelTable;

    private JLabel lblTongHoaDon, lblDaThanhToan, lblChuaThanhToan;

    private HoaDonDAO hoaDonDAO = new HoaDonDAO();
    private ChiTietHoaDonDAO chiTietDAO = new ChiTietHoaDonDAO();

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private DecimalFormat df = new DecimalFormat("#,##0 đ");

    public TraCuuHoaDon() {
        initComponents();
        loadDanhSachHoaDon();
    }

    private void initComponents() {
        setPreferredSize(new Dimension(1366, 768));
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setLayout(null);
        add(contentPane, BorderLayout.CENTER);

        // === TIÊU ĐỀ ===
        JLabel lblTieuDe = new JLabel("TRA CỨU HÓA ĐƠN", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 27));
        lblTieuDe.setBounds(460, 0, 470, 59);
        contentPane.add(lblTieuDe);

        // === PANEL LỌC ===
        JPanel panelLoc = new JPanel();
        panelLoc.setBounds(49, 57, 620, 80);
        panelLoc.setLayout(null);
        panelLoc.setBackground(new Color(220, 220, 220));
        panelLoc.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));
        contentPane.add(panelLoc);

        JLabel lblMaHD = new JLabel("Mã hóa đơn");
        lblMaHD.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblMaHD.setBounds(15, 15, 100, 25);
        panelLoc.add(lblMaHD);

        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTimKiem.setBounds(15, 42, 180, 28);
        panelLoc.add(txtTimKiem);

        JLabel lblTrangThai = new JLabel("Trạng thái");
        lblTrangThai.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTrangThai.setBounds(210, 15, 100, 25);
        panelLoc.add(lblTrangThai);

        cboTrangThai = new JComboBox<>(new String[]{
                "Tất cả", "Chưa thanh toán", "Đã thanh toán"
        });
        cboTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cboTrangThai.setBounds(210, 42, 160, 28);
        panelLoc.add(cboTrangThai);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnTimKiem.setBackground(new Color(52, 152, 219));
        btnTimKiem.setForeground(Color.WHITE);
        btnTimKiem.setBounds(390, 42, 105, 28);
        btnTimKiem.setFocusPainted(false);
        btnTimKiem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelLoc.add(btnTimKiem);

        btnLamMoi = new JButton("Làm mới");
        btnLamMoi.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLamMoi.setBackground(new Color(149, 165, 166));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.setBounds(505, 42, 100, 28);
        btnLamMoi.setFocusPainted(false);
        btnLamMoi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelLoc.add(btnLamMoi);

        // === PANEL THỐNG KÊ ===
        createStatPanel(contentPane, 700, "Tổng hóa đơn", "0", new Color(52, 152, 219), true);
        createStatPanel(contentPane, 920, "Đã thanh toán", "0", new Color(46, 204, 113), false);
        createStatPanel(contentPane, 1140, "Chưa thanh toán", "0", new Color(231, 76, 60), false);

        // === NÚT XEM CHI TIẾT (ĐÃ DỜI LÊN TRÊN) ===
        btnXemChiTiet = new JButton("Xem chi tiết hóa đơn");
        btnXemChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnXemChiTiet.setBackground(new Color(46, 204, 113));
        btnXemChiTiet.setForeground(Color.WHITE);
        btnXemChiTiet.setBounds(1099, 145, 190, 45); // Đưa lên trên ~100px so với bảng
        btnXemChiTiet.setFocusPainted(false);
        btnXemChiTiet.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXemChiTiet.addActionListener(e -> xemChiTietHoaDon());
        contentPane.add(btnXemChiTiet);

        // === BẢNG DỮ LIỆU ===
        JPanel panelTable = new JPanel();
        panelTable.setBounds(49, 205, 1240, 525); // Dời xuống để có chỗ cho nút
        panelTable.setLayout(null);
        panelTable.setBackground(new Color(220, 220, 220));
        panelTable.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 2));
        contentPane.add(panelTable);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 10, 1220, 505);
        panelTable.add(scrollPane);

        String[] cols = {"Mã HĐ", "Mã bàn", "Khách hàng", "Nhân viên",
                "Ngày lập", "Tổng tiền", "Trạng thái"};
        modelTable = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableHoaDon = new JTable(modelTable);
        tableHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableHoaDon.setRowHeight(30);
        tableHoaDon.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHoaDon.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        tableHoaDon.getTableHeader().setBackground(new Color(52, 152, 219));
        tableHoaDon.getTableHeader().setForeground(Color.WHITE);
        tableHoaDon.setSelectionBackground(new Color(189, 195, 199));
        tableHoaDon.addMouseListener(this);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableHoaDon.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableHoaDon.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableHoaDon.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
        tableHoaDon.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

        scrollPane.setViewportView(tableHoaDon);

        // Xử lý sự kiện
        xuLySuKien();
    }

    private void createStatPanel(JPanel parent, int x, String title, String value, Color color, boolean isTotal) {
        JPanel panel = new JPanel();
        panel.setBounds(x, 57, 200, 80);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblTitle.setBounds(10, 10, 180, 20);
        panel.add(lblTitle);

        JLabel lblValue = new JLabel(value);
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblValue.setForeground(color);
        lblValue.setBounds(10, 35, 180, 35);
        panel.add(lblValue);

        if (isTotal) lblTongHoaDon = lblValue;
        else if (title.contains("Đã")) lblDaThanhToan = lblValue;
        else lblChuaThanhToan = lblValue;

        parent.add(panel);
    }

    private void xuLySuKien() {
        btnTimKiem.addActionListener(e -> timKiemHoaDon());
        txtTimKiem.addActionListener(e -> timKiemHoaDon());
        cboTrangThai.addActionListener(e -> timKiemHoaDon());
        btnLamMoi.addActionListener(e -> lamMoi());
    }

    private void loadDanhSachHoaDon() {
        modelTable.setRowCount(0);
        List<HoaDon> ds = hoaDonDAO.findAll();
        capNhatBangVaThongKe(ds);
    }

    private void timKiemHoaDon() {
        modelTable.setRowCount(0);
        String maHD = txtTimKiem.getText().trim();
        String trangThai = cboTrangThai.getSelectedItem().toString();

        List<HoaDon> ds;
        if (!maHD.isEmpty()) {
            HoaDon hd = hoaDonDAO.timHoaDonTheoMa(maHD);
            ds = hd != null ? List.of(hd) : List.of();
        } else if (!trangThai.equals("Tất cả")) {
            ds = hoaDonDAO.timHoaDonTheoTrangThai(trangThai);
        } else {
            ds = hoaDonDAO.findAll();
        }
        capNhatBangVaThongKe(ds);
    }

    private void capNhatBangVaThongKe(List<HoaDon> ds) {
        int daThanhToan = 0, chuaThanhToan = 0;

        for (HoaDon hd : ds) {
            Object[] row = {
                    hd.getMaHoaDon(),
                    hd.getBanAn() != null ? hd.getBanAn().getMaBan() : "Mang đi",
                    hd.getKhachHang() != null ? hd.getKhachHang().getHoTen() : "Khách lẻ",
                    hd.getNhanVien() != null ? hd.getNhanVien().getHoTen() : "N/A",
                    hd.getNgayLapHoaDon() != null ? hd.getNgayLapHoaDon().format(dateFormatter) : "",
                    df.format(hd.getTongTien()),
                    hd.getTrangThai()
            };
            modelTable.addRow(row);

            if ("Đã thanh toán".equals(hd.getTrangThai())) daThanhToan++;
            else chuaThanhToan++;
        }

        lblTongHoaDon.setText(String.valueOf(ds.size()));
        lblDaThanhToan.setText(String.valueOf(daThanhToan));
        lblChuaThanhToan.setText(String.valueOf(chuaThanhToan));
    }

    private void lamMoi() {
        txtTimKiem.setText("");
        cboTrangThai.setSelectedIndex(0);
        loadDanhSachHoaDon();
    }

    // ===== XEM CHI TIẾT HÓA ĐƠN =====
    private void xemChiTietHoaDon() {
        int row = tableHoaDon.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String maHD = (String) modelTable.getValueAt(row, 0);
        HoaDon hd = hoaDonDAO.timHoaDonTheoMa(maHD);
        if (hd != null) {
            new DialogChiTietHoaDon_2(hd).setVisible(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            xemChiTietHoaDon();
        }
    }

    @Override public void mousePressed(MouseEvent e) {}
    @Override public void mouseReleased(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
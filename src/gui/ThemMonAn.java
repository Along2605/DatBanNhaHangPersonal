package gui;

import dao.MonAnDAO;
import entity.MonAn;
import util.Session;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ThemMonAn extends JPanel {
    private final MonAnDAO dao = new MonAnDAO();
    private List<MonAn> danhSachMonAn;
    private String selectedHinhAnhPath = null;
    private boolean dangTuDongPhatSinhMa = false;

    // UI
    private JTextField txtMaMA, txtTenMA, txtGia, txtSoLuong, txtTimKiem;
    private JComboBox<String> cbLoaiMA, cbTrangThai, cbDVT, cbTieuChiTimKiem;
    private JTextArea txtMota;
    private JLabel lblAnh;
    private JTable table;
    private DefaultTableModel model;
    private JButton btnThem, btnSua, btnXoa, btnLamMoi, btnThemLoaiMon, btnThemHinhAnh;

    // Regex tên món: chỉ chữ, số, khoảng trắng, gạch ngang
    private static final Pattern TEN_MON_PATTERN = Pattern.compile("^[\\p{L}0-9\\s\\-]+$");

    public ThemMonAn() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#EAF1F9"));

        add(taoTieuDe(), BorderLayout.NORTH);
        add(taoFormNhap(), BorderLayout.CENTER);
        add(taoBangVaTimKiem(), BorderLayout.EAST);
        add(taoNutChucNang(), BorderLayout.SOUTH);

        try {
            loadLoaiMonAn();
            loadDonViTinh();
            loadTable();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu: " + ex.getMessage());
        }

        // Events
        btnThem.addActionListener(e -> themMonAn());
        btnSua.addActionListener(e -> suaMonAn());
        btnXoa.addActionListener(e -> xoaMonAn());
        btnLamMoi.addActionListener(e -> lamMoiForm());
        btnThemLoaiMon.addActionListener(e -> themLoaiMonMoi());
        btnThemHinhAnh.addActionListener(e -> chonHinhAnh());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dayDuaLieuLenForm();
            }
        });

        // Tìm kiếm tự động
        cbTieuChiTimKiem.addActionListener(e -> timKiemTuDong());
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { timKiemTuDong(); }
            public void removeUpdate(DocumentEvent e) { timKiemTuDong(); }
            public void changedUpdate(DocumentEvent e) { timKiemTuDong(); }
        });

        // Tự động sinh mã khi chọn loại món
        cbLoaiMA.addActionListener(e -> {
            if (!dangTuDongPhatSinhMa && cbLoaiMA.getSelectedItem() != null) {
                String tenLoai = (String) cbLoaiMA.getSelectedItem();
                try {
                    String maLoai = dao.chuyenTenLoaiSangMa(tenLoai);
                    phatSinhMaMon(maLoai);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // ============================== GIAO DIỆN ==============================
    private JPanel taoTieuDe() {
        JPanel pn = new JPanel();
        pn.setBackground(Color.decode("#EAF1F9"));
        JLabel lbl = new JLabel("QUẢN LÝ MÓN ĂN");
        lbl.setFont(new Font("Arial", Font.BOLD, 26));
        lbl.setForeground(Color.decode("#2C3E50"));
        pn.add(lbl);
        return pn;
    }

    private JPanel taoFormNhap() {
        JPanel pnForm = new JPanel(new GridBagLayout());
        pnForm.setBackground(Color.decode("#EAF1F9"));
        pnForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                "Thông tin món ăn",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Arial", Font.BOLD, 14),
                Color.DARK_GRAY
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 12, 10, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Mã + Tên
        gbc.gridx = 0; gbc.gridy = row; pnForm.add(new JLabel("Mã món ăn:"), gbc);
        gbc.gridx = 1; txtMaMA = new JTextField(); txtMaMA.setEditable(false);
        pnForm.add(txtMaMA, gbc);
        gbc.gridx = 2; pnForm.add(new JLabel("Tên món ăn:"), gbc);
        gbc.gridx = 3; txtTenMA = new JTextField();
        pnForm.add(txtTenMA, gbc);

        row++;
        // Loại + Trạng thái
        gbc.gridx = 0; gbc.gridy = row; pnForm.add(new JLabel("Loại món:"), gbc);
        gbc.gridx = 1;
        cbLoaiMA = new JComboBox<>();
     // Thay đoạn code cũ bằng đoạn này:
        JPanel pnLoai = new JPanel(new BorderLayout());
        pnLoai.add(cbLoaiMA, BorderLayout.CENTER);

        // Tạo nút thêm loại món với ảnh
        btnThemLoaiMon = new JButton();
        btnThemLoaiMon.setToolTipText("Thêm loại món mới");
        btnThemLoaiMon.setPreferredSize(new Dimension(30, 26));
        btnThemLoaiMon.setContentAreaFilled(false);     // Bỏ nền mặc định
        btnThemLoaiMon.setBorderPainted(false);         // Bỏ viền
        btnThemLoaiMon.setFocusPainted(false);          // Bỏ viền focus
        btnThemLoaiMon.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Đặt ảnh làm icon, ảnh sẽ tự động co giãn vừa nút
        ImageIcon iconAdd = new ImageIcon("img/thucdon/add_icon.png");
        Image img = iconAdd.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btnThemLoaiMon.setIcon(new ImageIcon(img));

        // Hiệu ứng hover (tùy chọn - đẹp hơn)
        btnThemLoaiMon.addMouseListener(new java.awt.event.MouseAdapter() {
            private final Color originalBg = btnThemLoaiMon.getBackground();

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThemLoaiMon.setContentAreaFilled(true);
                btnThemLoaiMon.setBackground(new Color(200, 230, 255));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThemLoaiMon.setContentAreaFilled(false);
                btnThemLoaiMon.setBackground(originalBg);
            }
        });

        pnLoai.add(btnThemLoaiMon, BorderLayout.EAST);
        pnForm.add(pnLoai, gbc);

        gbc.gridx = 2; pnForm.add(new JLabel("Trạng thái:"), gbc);
        gbc.gridx = 3; cbTrangThai = new JComboBox<>(new String[]{"Còn phục vụ", "Hết phục vụ"});
        cbTrangThai.setSelectedIndex(0);
        pnForm.add(cbTrangThai, gbc);

        row++;
        // Số lượng + Đơn vị
        gbc.gridx = 0; gbc.gridy = row; pnForm.add(new JLabel("Số lượng:"), gbc);
        gbc.gridx = 1; txtSoLuong = new JTextField();
        pnForm.add(txtSoLuong, gbc);
        gbc.gridx = 2; pnForm.add(new JLabel("Đơn vị tính:"), gbc);
        gbc.gridx = 3; cbDVT = new JComboBox<>();
        pnForm.add(cbDVT, gbc);

        row++;
        // Giá
        gbc.gridx = 0; gbc.gridy = row; pnForm.add(new JLabel("Giá:"), gbc);
        gbc.gridx = 1; txtGia = new JTextField();
        pnForm.add(txtGia, gbc);

        row++;
        // Mô tả
        gbc.gridx = 0; gbc.gridy = row; pnForm.add(new JLabel("Mô tả:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtMota = new JTextArea(3, 35);
        txtMota.setLineWrap(true); txtMota.setWrapStyleWord(true);
        pnForm.add(new JScrollPane(txtMota), gbc);
        gbc.gridwidth = 1;

        row++;
        // Hình ảnh
        gbc.gridx = 0; gbc.gridy = row; pnForm.add(new JLabel("Hình ảnh:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        JPanel pnHinhAnh = new JPanel(new BorderLayout());
        pnHinhAnh.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        lblAnh = new JLabel("Chưa chọn hình");
        lblAnh.setPreferredSize(new Dimension(240, 240));
        lblAnh.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        lblAnh.setHorizontalAlignment(JLabel.CENTER);
        lblAnh.setBackground(Color.WHITE);
        lblAnh.setOpaque(true);
        pnHinhAnh.add(lblAnh, BorderLayout.CENTER);
        btnThemHinhAnh = new JButton("Chọn hình ảnh");
        btnThemHinhAnh.setPreferredSize(new Dimension(240, 30));
        pnHinhAnh.add(btnThemHinhAnh, BorderLayout.SOUTH);
        pnForm.add(pnHinhAnh, gbc);

        return pnForm;
    }

    private JPanel taoBangVaTimKiem() {
        JPanel pnEast = new JPanel(new BorderLayout());
        pnEast.setPreferredSize(new Dimension(750, 0));
        pnEast.setBorder(BorderFactory.createTitledBorder("Danh sách món ăn"));

        JPanel pnSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        pnSearch.add(new JLabel("Tìm theo:"));
        cbTieuChiTimKiem = new JComboBox<>(new String[]{
                "Tất cả", "Mã món", "Tên món", "Giá", "ĐVT", "Trạng thái", "SL", "Mô tả", "Loại"
        });
        pnSearch.add(cbTieuChiTimKiem);
        txtTimKiem = new JTextField(20);
        pnSearch.add(txtTimKiem);
        pnEast.add(pnSearch, BorderLayout.NORTH);

        String[] cols = {"Mã", "Tên món", "Giá", "DVT", "Trạng thái", "SL", "Mô tả", "Loại", "Ảnh"};
        model = new DefaultTableModel(cols, 0) { @Override public boolean isCellEditable(int r, int c) { return false; } };
        table = new JTable(model);
        table.setRowHeight(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(180);
        pnEast.add(new JScrollPane(table), BorderLayout.CENTER);
        return pnEast;
    }

    private JPanel taoNutChucNang() {
        JPanel pnSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        pnSouth.setBackground(Color.decode("#EAF1F9"));
        btnThem = taoNut("Thêm", "img/add.png");
        btnSua = taoNut("Sửa", "img/edit.png");
        btnXoa = taoNut("Xóa", "img/delete.png");
        btnLamMoi = taoNut("Làm mới", "img/refresh.png");
        pnSouth.add(btnThem); pnSouth.add(btnSua); pnSouth.add(btnXoa); pnSouth.add(btnLamMoi);
        return pnSouth;
    }

    private JButton taoNut(String text, String iconPath) {
        JButton btn = new JButton(text, scaleIcon(iconPath, 18, 18));
        btn.setPreferredSize(new Dimension(120, 35));
        btn.setFont(new Font("Arial", Font.BOLD, 12));
        return btn;
    }

    private ImageIcon scaleIcon(String path, int w, int h) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return new ImageIcon(img.getScaledInstance(w, h, Image.SCALE_SMOOTH));
        } catch (Exception e) {
            return new ImageIcon();
        }
    }

    // ============================== LOAD DỮ LIỆU ==============================
    private void loadLoaiMonAn() throws SQLException {
        cbLoaiMA.removeAllItems();
        for (String ten : dao.layRaLoaiMonAn()) cbLoaiMA.addItem(ten);
        if (cbLoaiMA.getItemCount() > 0) cbLoaiMA.setSelectedIndex(0);
    }

    private void loadDonViTinh() throws SQLException {
        cbDVT.removeAllItems();
        for (String dvt : dao.layRaDonViTinh()) cbDVT.addItem(dvt);
        if (cbDVT.getItemCount() > 0) cbDVT.setSelectedIndex(0);
    }

    private void loadTable() throws SQLException {
        danhSachMonAn = dao.getAllMonAn();
        capNhatBang(danhSachMonAn);
    }

    private void capNhatBang(List<MonAn> ds) {
        model.setRowCount(0);
        for (MonAn m : ds) {
            if (!m.isTrangThai()) continue;
            String tenLoai = "Không xác định";
            try { tenLoai = dao.chuyenMaLoaiSangTen(m.getLoaiMon()); } catch (SQLException ignored) {}

            model.addRow(new Object[]{
                    m.getMaMon(),
                    m.getTenMon(),
                    m.getGia(),
                    m.getDonViTinh() != null ? m.getDonViTinh() : "",
                    "Còn phục vụ",
                    m.getSoLuong(),
                    m.getMoTa() != null ? m.getMoTa() : "",
                    tenLoai,
                    m.getHinhAnh() != null ? m.getHinhAnh() : ""
            });
        }
    }

    private void phatSinhMaMon(String maLoai) {
        try {
            String maLonNhat = dao.layMaMonLonNhatTheoLoai(maLoai);
            int soThuTu = (maLonNhat == null) ? 1 : Integer.parseInt(maLonNhat.substring(2)) + 1;
            txtMaMA.setText(maLoai + String.format("%02d", soThuTu));
        } catch (Exception e) {
            txtMaMA.setText(maLoai + "01");
        }
    }

    // ============================== TÌM KIẾM ==============================
    private void timKiemTuDong() {
        String keyword = txtTimKiem.getText().trim().toLowerCase();
        String criteria = (String) cbTieuChiTimKiem.getSelectedItem();
        List<MonAn> result = new ArrayList<>();
        for (MonAn m : danhSachMonAn) {
            if (!m.isTrangThai()) continue;
            if (matches(m, keyword, criteria)) result.add(m);
        }
        capNhatBang(result);
    }

    private boolean matches(MonAn m, String k, String crit) {
        try {
            String tenLoai = dao.chuyenMaLoaiSangTen(m.getLoaiMon());
            return switch (crit) {
                case "Tất cả" -> contains(m.getMaMon(), k) || contains(m.getTenMon(), k) ||
                        String.valueOf(m.getGia()).contains(k) || contains(m.getDonViTinh(), k) ||
                        String.valueOf(m.getSoLuong()).contains(k) ||
                        contains(m.getMoTa(), k) || contains(tenLoai, k);
                case "Mã món" -> contains(m.getMaMon(), k);
                case "Tên món" -> contains(m.getTenMon(), k);
                case "Giá" -> String.valueOf(m.getGia()).contains(k);
                case "ĐVT" -> contains(m.getDonViTinh(), k);
                case "SL" -> String.valueOf(m.getSoLuong()).contains(k);
                case "Mô tả" -> contains(m.getMoTa(), k);
                case "Loại" -> contains(tenLoai, k);
                default -> false;
            };
        } catch (SQLException e) { return false; }
    }

    private boolean contains(String s, String k) {
        return s != null && s.toLowerCase().contains(k.toLowerCase());
    }

    // ============================== VALIDATE ==============================
    private boolean validateMonAn() {
        // 1. Tên món
        String tenMon = txtTenMA.getText().trim();
        if (tenMon.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên món ăn!");
            txtTenMA.requestFocus();
            return false;
        }
        if (!TEN_MON_PATTERN.matcher(tenMon).matches()) {
            JOptionPane.showMessageDialog(this, "Tên món chỉ được chứa chữ cái, số, khoảng trắng và dấu gạch ngang (-)");
            txtTenMA.requestFocus();
            return false;
        }

        // Kiểm tra trùng tên
        try {
            String maMonHienTai = txtMaMA.getText().trim();
            if (dao.kiemTraTenMonTrung(tenMon, maMonHienTai.isEmpty() ? null : maMonHienTai)) {
                JOptionPane.showMessageDialog(this, "Tên món '" + tenMon + "' đã tồn tại và đang được phục vụ!");
                txtTenMA.requestFocus();
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Lỗi kiểm tra tên món!");
            return false;
        }

        // 2. Giá
        String giaStr = txtGia.getText().trim();
        if (giaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá món ăn!");
            txtGia.requestFocus();
            return false;
        }
        try {
            double gia = Double.parseDouble(giaStr.replaceAll("[^0-9]", ""));
            if (gia <= 0) throw new Exception();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Giá phải là số lớn hơn 0!");
            txtGia.requestFocus();
            return false;
        }

        // 3. Số lượng (nếu nhập)
        String slStr = txtSoLuong.getText().trim();
        if (!slStr.isEmpty()) {
            try {
                int sl = Integer.parseInt(slStr);
                if (sl < 0) throw new Exception();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên không âm!");
                txtSoLuong.requestFocus();
                return false;
            }
        }

        return true;
    }

    // ============================== CRUD ==============================
    private void themMonAn() {
        if (!validateMonAn()) return;

        try {
            String maMon = txtMaMA.getText().trim();
            String tenMon = txtTenMA.getText().trim();
            double gia = Double.parseDouble(txtGia.getText().trim().replaceAll("[^0-9]", ""));

            String dvt = cbDVT.getSelectedItem() != null ? cbDVT.getSelectedItem().toString() : null;
            String moTa = txtMota.getText().trim();
            moTa = moTa.isEmpty() ? null : moTa;

            // soLuong: nếu để trống → 0 → DAO sẽ lưu NULL
            int soLuong = 0;
            String slStr = txtSoLuong.getText().trim();
            if (!slStr.isEmpty()) {
                soLuong = Integer.parseInt(slStr);
            }

            String hinhAnh = null;
            if (selectedHinhAnhPath != null && !selectedHinhAnhPath.isEmpty()) {
                File src = new File(selectedHinhAnhPath);
                String tenFile = src.getName();
                hinhAnh = "img/thucdon/" + tenFile;
                Files.copy(src.toPath(), new File(hinhAnh).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            String tenLoai = (String) cbLoaiMA.getSelectedItem();
            String maLoai = dao.chuyenTenLoaiSangMa(tenLoai);

            MonAn mon = new MonAn(maMon, tenMon, gia, dvt, true, hinhAnh, soLuong, moTa, maLoai);

            if (dao.themMonMoi(mon, Session.getMaNhanVienDangNhap())) {
                JOptionPane.showMessageDialog(this, "Thêm món ăn thành công!");
                lamMoiForm();
                loadTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi thêm món: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void suaMonAn() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món để sửa!");
            return;
        }
        if (!validateMonAn()) return;

        try {
            String maMon = txtMaMA.getText().trim();
            String tenMon = txtTenMA.getText().trim();
            double gia = Double.parseDouble(txtGia.getText().trim().replaceAll("[^0-9]", ""));

            String dvt = cbDVT.getSelectedItem() != null ? cbDVT.getSelectedItem().toString() : null;
            String moTa = txtMota.getText().trim();
            moTa = moTa.isEmpty() ? null : moTa;

            int soLuong = 0;
            String slStr = txtSoLuong.getText().trim();
            if (!slStr.isEmpty()) {
                soLuong = Integer.parseInt(slStr);
            }

            String hinhAnh = danhSachMonAn.get(row).getHinhAnh();
            if (selectedHinhAnhPath != null && !selectedHinhAnhPath.equals(hinhAnh)) {
                File src = new File(selectedHinhAnhPath);
                String tenFile = src.getName();
                hinhAnh = "img/thucdon/" + tenFile;
                Files.copy(src.toPath(), new File(hinhAnh).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            String tenLoai = (String) cbLoaiMA.getSelectedItem();
            String maLoai = dao.chuyenTenLoaiSangMa(tenLoai);

            MonAn mon = new MonAn(maMon, tenMon, gia, dvt, true, hinhAnh, soLuong, moTa, maLoai);

            if (dao.suaMonAn(mon, Session.getMaNhanVienDangNhap())) {
                JOptionPane.showMessageDialog(this, "Sửa món thành công!");
                lamMoiForm();
                loadTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi sửa món: " + ex.getMessage());
        }
    }

    private void xoaMonAn() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn món để ẩn!");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Ẩn món ăn này khỏi thực đơn?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String maMon = (String) table.getValueAt(row, 0);
            try {
                if (dao.anMonAn(maMon, Session.getMaNhanVienDangNhap())) {
                    JOptionPane.showMessageDialog(this, "Đã ẩn món ăn!");
                    loadTable();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        }
    }

    private void lamMoiForm() {
        txtMaMA.setText("");
        txtTenMA.setText("");
        txtGia.setText("");
        txtSoLuong.setText("");
        txtMota.setText("");
        lblAnh.setIcon(null);
        lblAnh.setText("Chưa chọn hình");
        selectedHinhAnhPath = null;
        table.clearSelection();
        if (cbLoaiMA.getItemCount() > 0) {
            cbLoaiMA.setSelectedIndex(0);
            try {
                String maLoai = dao.chuyenTenLoaiSangMa((String) cbLoaiMA.getSelectedItem());
                phatSinhMaMon(maLoai);
            } catch (Exception ignored) {}
        }
    }

    private void dayDuaLieuLenForm() {
        dangTuDongPhatSinhMa = true;
        int row = table.getSelectedRow();
        if (row < 0) return;

        txtMaMA.setText((String) model.getValueAt(row, 0));
        txtTenMA.setText((String) model.getValueAt(row, 1));
        txtGia.setText(model.getValueAt(row, 2).toString());
        cbDVT.setSelectedItem(model.getValueAt(row, 3));
        cbTrangThai.setSelectedItem(model.getValueAt(row, 4));

        Object sl = model.getValueAt(row, 5);
        txtSoLuong.setText((sl == null || sl.toString().equals("0")) ? "" : sl.toString());

        txtMota.setText((String) model.getValueAt(row, 6));

        String tenLoai = (String) model.getValueAt(row, 7);
        cbLoaiMA.setSelectedItem(tenLoai);

        String imgPath = (String) model.getValueAt(row, 8);
        if (imgPath != null && !imgPath.trim().isEmpty()) {
            selectedHinhAnhPath = imgPath;
            ImageIcon icon = new ImageIcon(imgPath);
            Image img = icon.getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(img));
            lblAnh.setText("");
        } else {
            lblAnh.setIcon(null);
            lblAnh.setText("Chưa chọn hình");
            selectedHinhAnhPath = null;
        }
        dangTuDongPhatSinhMa = false;
    }

    private void chonHinhAnh() {
        JFileChooser fc = new JFileChooser("img/thucdon");
        fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Hình ảnh", "jpg", "jpeg", "png", "gif"));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = fc.getSelectedFile();
            selectedHinhAnhPath = f.getAbsolutePath();
            ImageIcon icon = new ImageIcon(selectedHinhAnhPath);
            Image img = icon.getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(img));
            lblAnh.setText("");
        }
    }

    private void themLoaiMonMoi() {
        new DialogThemLoaiMon((Frame) SwingUtilities.getWindowAncestor(this)).setVisible(true);
        try { loadLoaiMonAn(); } catch (SQLException e) { e.printStackTrace(); }
    }
}
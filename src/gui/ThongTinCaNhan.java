package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import dao.TaiKhoanDAO;
import entity.NhanVien;

public class ThongTinCaNhan extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    // === Components ===
    private JTextField txtMaNV, txtTenNV, txtSdt, txtEmail;
    private JDateChooser dcrNgaySinh, dcrNgayVaoLam;
    private JComboBox<String> cboGioiTinh;
    private JLabel lblAnh;
    private JButton btnSua, btnLuu, btnHuy;

    // === DAO ===
    private NhanVienDAO nvDAO;
    private TaiKhoanDAO tkDAO;

    // === Default Image Path ===
    private static final String DEFAULT_IMG = "/img/user.jpg";

    public ThongTinCaNhan() {
        ConnectDB.getInstance().connect();
        nvDAO = new NhanVienDAO();
        tkDAO = new TaiKhoanDAO();

        setLayout(new BorderLayout());
        setBackground(Color.decode("#EAF1F9"));

        // === Tiêu đề ===
        JLabel lblTitle = new JLabel("THÔNG TIN CÁ NHÂN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(lblTitle, BorderLayout.NORTH);

        // === Nội dung chính (trong JScrollPane để responsive) ===
        JPanel mainPanel = createMainPanel();
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);

        // === Load dữ liệu ===
        loadThongTinCaNhan();
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // === Cột trái: Ảnh ===
        JPanel pnAnh = createImagePanel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.35;
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(pnAnh, gbc);

        // === Cột phải: Form ===
        JPanel pnForm = createFormPanel();
        gbc.gridx = 1;
        gbc.weightx = 0.65;
        panel.add(pnForm, gbc);

        return panel;
    }

    private JPanel createImagePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), "Ảnh đại diện"));

        lblAnh = new JLabel();
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnh.setPreferredSize(new Dimension(280, 280));
        lblAnh.setMinimumSize(new Dimension(200, 200));
        loadDefaultImage();

        panel.add(lblAnh);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)), "Thông tin cá nhân"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Mã NV
        addFormRow(panel, gbc, row++, "Mã nhân viên:", txtMaNV = createUneditableField());
        // Họ tên
        addFormRow(panel, gbc, row++, "Họ tên:", txtTenNV = createUneditableField());
        // Giới tính
        addFormRow(panel, gbc, row++, "Giới tính:", cboGioiTinh = createUneditableCombo());
        // Ngày sinh
        addFormRow(panel, gbc, row++, "Ngày sinh:", dcrNgaySinh = createUneditableDateChooser());
        // SĐT
        addFormRow(panel, gbc, row++, "Số điện thoại:", txtSdt = createUneditableField());
        // Email
        addFormRow(panel, gbc, row++, "Email:", txtEmail = createUneditableField());
        // Ngày vào làm
        addFormRow(panel, gbc, row++, "Ngày vào làm:", dcrNgayVaoLam = createUneditableDateChooser());

        // === Nút chức năng ===
        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnButtons.setOpaque(false);

        btnSua = createButton("Sửa", "/img/edit.png");
        btnLuu = createButton("Lưu", "/img/save.png");
        btnHuy = createButton("Hủy", "/img/cancel.png");

        pnButtons.add(btnSua);
        pnButtons.add(btnLuu);
        pnButtons.add(btnHuy);

        gbc.gridx = 1;
        gbc.gridy = row++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(pnButtons, gbc);

        // === Gán sự kiện ===
        btnSua.addActionListener(this);
        btnLuu.addActionListener(this);
        btnHuy.addActionListener(this);

        return panel;
    }

    // === Helper Methods ===

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String label, JComponent field) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        field.setPreferredSize(new Dimension(220, 35));
        panel.add(field, gbc);
    }

    private JTextField createUneditableField() {
        JTextField field = new JTextField(15);
        field.setEditable(false);
        return field;
    }

    private JComboBox<String> createUneditableCombo() {
        JComboBox<String> combo = new JComboBox<>(new String[]{"Nam", "Nữ"});
        combo.setEnabled(false);
        return combo;
    }

    private JDateChooser createUneditableDateChooser() {
        JDateChooser chooser = new JDateChooser();
        chooser.setDateFormatString("dd/MM/yyyy");
        chooser.setEnabled(false);
        return chooser;
    }

    private JButton createButton(String text, String iconPath) {
        JButton btn = new JButton(text);
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(16, 16, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Nếu không load được icon → vẫn hiển thị text
        }
        btn.setPreferredSize(new Dimension(100, 35));
        return btn;
    }

    // === Load & Display ===

    private void loadThongTinCaNhan() {
        NhanVien nv = util.Session.getNhanVienDangNhap();
        if (nv == null) {
            JOptionPane.showMessageDialog(this, "Chưa đăng nhập!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        txtMaNV.setText(nv.getMaNV());
        txtTenNV.setText(nv.getHoTen());
        cboGioiTinh.setSelectedItem(nv.isGioiTinh() ? "Nam" : "Nữ");
        txtSdt.setText(nv.getSoDienThoai());
        txtEmail.setText(nv.getEmail());

        setDate(dcrNgaySinh, nv.getNgaySinh());
        setDate(dcrNgayVaoLam, nv.getNgayVaoLam());

        loadAnhDaiDien(nv.getAnhDaiDien());
    }

    private void setDate(JDateChooser chooser, LocalDate date) {
        if (date != null) {
            chooser.setDate(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        }
    }

    private void loadAnhDaiDien(String path) {
        ImageIcon icon = null;
        if (path != null && !path.trim().isEmpty()) {
            try {
                File f = new File(path);
                if (f.exists()) {
                    icon = new ImageIcon(path);
                }
            } catch (Exception ignored) {}
        }

        // Nếu không có ảnh → dùng ảnh mặc định từ resources
        if (icon == null) {
            java.net.URL url = getClass().getResource(DEFAULT_IMG);
            if (url != null) {
                icon = new ImageIcon(url);
            }
        }

        if (icon != null) {
            Image img = icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(img));
        } else {
            lblAnh.setIcon(null);
            lblAnh.setText("<html><center>Không tải được ảnh<br>Ảnh mặc định lỗi</center></html>");
        }
    }

    private void loadDefaultImage() {
        java.net.URL url = getClass().getResource(DEFAULT_IMG);
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            Image img = icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(img));
        }
    }

    // === Action Events ===

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSua) {
            enableEditing(true);
        } else if (e.getSource() == btnLuu) {
            luuThongTin();
        } else if (e.getSource() == btnHuy) {
            enableEditing(false);
            loadThongTinCaNhan(); // Reset lại dữ liệu gốc
        }
    }

    private void enableEditing(boolean enable) {
        txtTenNV.setEditable(enable);
        cboGioiTinh.setEnabled(enable);
        dcrNgaySinh.setEnabled(enable);
        txtSdt.setEditable(enable);
        txtEmail.setEditable(enable);

        btnLuu.setEnabled(enable);
        btnHuy.setEnabled(enable);
        btnSua.setEnabled(!enable);
    }

    private void luuThongTin() {
        if (!validateInput()) return;

        try {
            NhanVien nv= new NhanVien();
            nv.setMaNV(txtMaNV.getText().trim());
            nv.setHoTen(txtTenNV.getText().trim());
            nv.setGioiTinh(cboGioiTinh.getSelectedItem().equals("Nam"));
            nv.setSoDienThoai(txtSdt.getText().trim());
            nv.setEmail(txtEmail.getText().trim());

            java.util.Date utilDate = dcrNgaySinh.getDate();
            if (utilDate != null) {
                nv.setNgaySinh(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            }

            boolean success = nvDAO.capNhatThongTinCaNhan(nv);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                util.Session.capNhatNhanVien(nv); // Cập nhật session
                enableEditing(false);
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInput() {
        if (txtTenNV.getText().trim().isEmpty()) {
            showError("Họ tên không được để trống!");
            return false;
        }
        if (!txtSdt.getText().trim().matches("\\d{10,11}")) {
            showError("Số điện thoại phải có 10-11 chữ số!");
            return false;
        }
        if (!txtEmail.getText().trim().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showError("Email không hợp lệ!");
            return false;
        }
        if (dcrNgaySinh.getDate() == null) {
            showError("Vui lòng chọn ngày sinh!");
            return false;
        }
        return true;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
    }

    // === Test ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Thông tin cá nhân");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 650);
            frame.setMinimumSize(new Dimension(800, 500));
            frame.setLocationRelativeTo(null);
            frame.add(new ThongTinCaNhan());
            frame.setVisible(true);
        });
    }
}
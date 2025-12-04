package gui;

import java.awt.BorderLayout;
import java.awt.Color;

// ============================== DIALOG THÔNG TIN NHÂN VIÊN ==============================

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

class DialogThongTinNhanVien extends JDialog {
    private JLabel lblAnh;
    private JTextField txtMaNV, txtHoTen, txtNgaySinh, txtEmail, txtSoDienThoai;
    private JTextField txtGioiTinh, txtChucVu, txtNgayVaoLam, txtTrangThai;
    private JTextField txtLuongCoBan, txtSoGioLam, txtSoHoaDon, txtTiLeNangXuat;
    
    public DialogThongTinNhanVien(String maNV, String hoTen, String chucVu, 
                                  String luongCoBan, String soGioLam, 
                                  String soHoaDon, String tiLeNangXuat) {
        setTitle("Thông tin chi tiết nhân viên - " + maNV);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout(0, 0));
        getContentPane().setBackground(Color.decode("#EAF1F9"));
        
        // Tiêu đề
        JLabel lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        add(lblTitle, BorderLayout.NORTH);
        
        // Main panel
        JPanel pnMain = new JPanel(new BorderLayout(20, 0));
        pnMain.setBackground(Color.decode("#EAF1F9"));
        pnMain.setBorder(new EmptyBorder(10, 20, 20, 20));
        add(pnMain, BorderLayout.CENTER);
        
        // Panel ảnh bên trái
        JPanel pnLeft = new JPanel();
        pnLeft.setBackground(Color.decode("#EAF1F9"));
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));
        
        lblAnh = new JLabel();
        lblAnh.setPreferredSize(new Dimension(180, 220));
        lblAnh.setMaximumSize(new Dimension(210, 220));
        lblAnh.setBorder(BorderFactory.createLineBorder(new Color(44, 62, 80), 2));
        lblAnh.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnh.setVerticalAlignment(SwingConstants.CENTER);
        lblAnh.setOpaque(true);
        lblAnh.setBackground(Color.WHITE);
        
        // Load ảnh nhân viên
        hienThiAnh(maNV);
        
        pnLeft.add(lblAnh);
        pnMain.add(pnLeft, BorderLayout.WEST);
        
        // Panel thông tin bên phải
        JPanel pnRight = new JPanel(new GridBagLayout());
        pnRight.setBackground(Color.decode("#EAF1F9"));
        pnRight.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(44, 62, 80), 1),
                "Thông tin chi tiết",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 16)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Khởi tạo các text field (không cho edit)
        txtMaNV = createTextField();
        txtHoTen = createTextField();
        txtNgaySinh = createTextField();
        txtEmail = createTextField();
        txtSoDienThoai = createTextField();
        txtGioiTinh = createTextField();
        txtChucVu = createTextField();
        txtNgayVaoLam = createTextField();
        txtTrangThai = createTextField();
        txtLuongCoBan = createTextField();
        txtSoGioLam = createTextField();
        txtSoHoaDon = createTextField();
        txtTiLeNangXuat = createTextField();
        
        // Set dữ liệu mẫu
        txtMaNV.setText(maNV);
        txtHoTen.setText(hoTen);
        txtNgaySinh.setText("15/03/1995"); // Dữ liệu mẫu
        txtEmail.setText(maNV.toLowerCase() + "@restaurant.com");
        txtSoDienThoai.setText("0" + (900000000 + (int)(Math.random() * 99999999)));
        txtGioiTinh.setText(Math.random() > 0.5 ? "Nam" : "Nữ");
        txtChucVu.setText(chucVu);
        txtNgayVaoLam.setText("01/01/2023"); // Dữ liệu mẫu
        txtTrangThai.setText("Đang làm việc");
        txtLuongCoBan.setText(luongCoBan);
        txtSoGioLam.setText(soGioLam);
        txtSoHoaDon.setText(soHoaDon);
        txtTiLeNangXuat.setText(tiLeNangXuat);
        
        // Row 0: Mã NV, Họ tên
        gbc.gridy = 0;
        addLabelAndField(pnRight, gbc, 0, "Mã nhân viên:", txtMaNV, 0.2, 0.3);
        addLabelAndField(pnRight, gbc, 2, "Họ tên:", txtHoTen, 0.2, 0.3);
        
        // Row 1: Ngày sinh, Email
        gbc.gridy = 1;
        addLabelAndField(pnRight, gbc, 0, "Ngày sinh:", txtNgaySinh, 0.2, 0.3);
        addLabelAndField(pnRight, gbc, 2, "Email:", txtEmail, 0.2, 0.3);
        
        // Row 2: SĐT, Giới tính
        gbc.gridy = 2;
        addLabelAndField(pnRight, gbc, 0, "Số điện thoại:", txtSoDienThoai, 0.2, 0.3);
        addLabelAndField(pnRight, gbc, 2, "Giới tính:", txtGioiTinh, 0.2, 0.3);
        
        // Row 3: Chức vụ, Ngày vào làm
        gbc.gridy = 3;
        addLabelAndField(pnRight, gbc, 0, "Chức vụ:", txtChucVu, 0.2, 0.3);
        addLabelAndField(pnRight, gbc, 2, "Ngày vào làm:", txtNgayVaoLam, 0.2, 0.3);
        
        // Row 4: Trạng thái, Lương cơ bản
        gbc.gridy = 4;
        addLabelAndField(pnRight, gbc, 0, "Trạng thái:", txtTrangThai, 0.2, 0.3);
        addLabelAndField(pnRight, gbc, 2, "Lương cơ bản:", txtLuongCoBan, 0.2, 0.3);
        
        // Row 5: Số giờ làm, Số hóa đơn
        gbc.gridy = 5;
        addLabelAndField(pnRight, gbc, 0, "Số giờ làm:", txtSoGioLam, 0.2, 0.3);
        addLabelAndField(pnRight, gbc, 2, "Số hóa đơn:", txtSoHoaDon, 0.2, 0.3);
        
        // Row 6: Tỉ lệ năng xuất
        gbc.gridy = 6;
        addLabelAndField(pnRight, gbc, 0, "Tỉ lệ năng xuất:", txtTiLeNangXuat, 0.2, 0.3);
        
        pnMain.add(pnRight, BorderLayout.CENTER);
        
        // Button panel
        JPanel pnButtons = new JPanel((LayoutManager) new FlowLayout(FlowLayout.CENTER, 15, 10));
        pnButtons.setBackground(Color.decode("#EAF1F9"));
        
        JButton btnDong = new JButton("Đóng");
        btnDong.setFont(new Font("Arial", Font.BOLD, 14));
        btnDong.setPreferredSize(new Dimension(120, 35));
        btnDong.setBackground(new Color(149, 165, 166));
        btnDong.setForeground(Color.WHITE);
        btnDong.setFocusPainted(false);
        btnDong.addActionListener(e -> dispose());
        
        pnButtons.add(btnDong);
        add(pnButtons, BorderLayout.SOUTH);
    }
    
    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setEditable(false);
        textField.setBackground(Color.decode("#EEEEEE"));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#CCCCCC")),
                BorderFactory.createEmptyBorder(5, 7, 5, 7)
        ));
        return textField;
    }
    
    private void addLabelAndField(JPanel panel, GridBagConstraints gbc, int col, 
                                 String labelText, Component field, 
                                 double labelWeight, double fieldWeight) {
        // Label
        gbc.gridx = col;
        gbc.gridwidth = 1;
        gbc.weightx = labelWeight;
        JLabel lbl = new JLabel(labelText);
        lbl.setFont(new Font("Arial", Font.BOLD, 13));
        lbl.setForeground(new Color(44, 62, 80));
        panel.add(lbl, gbc);
        
        // Field
        gbc.gridx = col + 1;
        gbc.weightx = fieldWeight;
        panel.add(field, gbc);
    }
    
    private void hienThiAnh(String maNV) {
        String duongDan = "img/employees/" + maNV + ".png";
        try {
            File imageFile = new File(duongDan);
            ImageIcon icon;
            
            if (imageFile.exists()) {
                icon = new ImageIcon(imageFile.getAbsolutePath());
            } else {
                // Thử các extension khác
                imageFile = new File("img/employees/" + maNV + ".jpg");
                if (imageFile.exists()) {
                    icon = new ImageIcon(imageFile.getAbsolutePath());
                } else {
                    // Load ảnh mặc định
                    imageFile = new File("img/employees/default-avatar.png");
                    if (imageFile.exists()) {
                        icon = new ImageIcon(imageFile.getAbsolutePath());
                    } else {
                        lblAnh.setText("Không có ảnh");
                        return;
                    }
                }
            }
            
            Image img = icon.getImage().getScaledInstance(180, 220, Image.SCALE_SMOOTH);
            lblAnh.setIcon(new ImageIcon(img));
            lblAnh.setText(null);
            
        } catch (Exception e) {
            lblAnh.setIcon(null);
            lblAnh.setText("Lỗi load ảnh");
            e.printStackTrace();
        }
    }
}
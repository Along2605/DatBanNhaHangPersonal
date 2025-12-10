package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhuVucDAO;
import entity.KhuVuc;

public class QuanLyKhuVuc extends JPanel implements ActionListener, MouseListener {
    private static final long serialVersionUID = 1L;
    
    // Màu sắc
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private static final Color WARNING_COLOR = new Color(243, 156, 18);
    private static final Color DANGER_COLOR = new Color(231, 76, 60);
    private static final Color BACKGROUND_COLOR = new Color(236, 240, 241);
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = new Color(44, 62, 80);
    
    // Components
    private JTextField txtMaKhuVuc;
    private JTextField txtTenKhuVuc;
    private JTextField txtViTri;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JTable tableKhuVuc;
    private DefaultTableModel modelKhuVuc;
    
    // DAO
    private KhuVucDAO khuVucDAO;
    
    public QuanLyKhuVuc() {
        ConnectDB.getInstance().connect();
        khuVucDAO = new KhuVucDAO();
        
        initComponents();
        loadData();
        addEvents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout(15, 15));
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Header
        add(createHeader(), BorderLayout.NORTH);
        
        // Form nhập liệu
        add(createFormPanel(), BorderLayout.CENTER);
        
        // Bảng dữ liệu
        add(createTablePanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel lblTitle = new JLabel("QUẢN LÝ KHU VỰC", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(PRIMARY_COLOR);
        
        headerPanel.add(lblTitle);
        
        return headerPanel;
    }
    
    private JPanel createFormPanel() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(PANEL_COLOR);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(25, 30, 25, 30)
        ));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Mã khu vực
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        JLabel lblMa = createLabel("Mã khu vực:");
        mainPanel.add(lblMa, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtMaKhuVuc = createTextField();
        txtMaKhuVuc.setText(khuVucDAO.taoMaKhuVucMoi());
        txtMaKhuVuc.setEditable(false);
        txtMaKhuVuc.setBackground(new Color(236, 240, 241));
        mainPanel.add(txtMaKhuVuc, gbc);
        
        // Tên khu vực
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel lblTen = createLabel("Tên khu vực:");
        mainPanel.add(lblTen, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtTenKhuVuc = createTextField();
        txtTenKhuVuc.requestFocus();
        mainPanel.add(txtTenKhuVuc, gbc);
        
        // Vị trí
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        JLabel lblViTri = createLabel("Vị trí:");
        mainPanel.add(lblViTri, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtViTri = createTextField();
        mainPanel.add(txtViTri, gbc);
        
        // Panel chứa các nút
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 10, 10, 10);
        mainPanel.add(createButtonPanel(), gbc);
        
        return mainPanel;
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setBackground(PANEL_COLOR);
        
        // Nút Thêm
        btnThem = createStyledButton("Thêm", "img/add.png", SUCCESS_COLOR);
        buttonPanel.add(btnThem);
        
        // Nút Sửa
        btnSua = createStyledButton("Sửa", "img/edit.png", WARNING_COLOR);
        buttonPanel.add(btnSua);
        
        // Nút Xóa
        btnXoa = createStyledButton("Xóa", "img/delete.png", DANGER_COLOR);
        buttonPanel.add(btnXoa);
        
        // Nút Làm mới
        btnLamMoi = createStyledButton("Làm mới", "img/refresh.png", PRIMARY_COLOR);
        buttonPanel.add(btnLamMoi);
        
        return buttonPanel;
    }
    
    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(BACKGROUND_COLOR);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        
        // Tạo bảng
        String[] columns = {"STT", "Mã khu vực", "Tên khu vực", "Vị trí"};
        modelKhuVuc = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableKhuVuc = new JTable(modelKhuVuc);
        customizeTable();
        
        JScrollPane scrollPane = new JScrollPane(tableKhuVuc);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                "Danh sách khu vực",
                0,
                0,
                new Font("Segoe UI", Font.BOLD, 14),
                PRIMARY_COLOR
            ),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        scrollPane.setPreferredSize(new Dimension(0, 250));
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        return tablePanel;
    }
    
    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT_COLOR);
        return label;
    }
    
    private JTextField createTextField() {
        JTextField textField = new JTextField(25);
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(0, 35));
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return textField;
    }
    
    private JButton createStyledButton(String text, String iconPath, Color bgColor) {
        JButton button = new JButton(text);
        
        try {
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            // Icon không tồn tại
        }
        
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(130, 40));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
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
    
    private void customizeTable() {
        tableKhuVuc.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableKhuVuc.setRowHeight(35);
        tableKhuVuc.setSelectionBackground(new Color(52, 152, 219, 100));
        tableKhuVuc.setSelectionForeground(TEXT_COLOR);
        tableKhuVuc.setGridColor(new Color(189, 195, 199));
        tableKhuVuc.setShowGrid(true);
        
        // Header
        JTableHeader header = tableKhuVuc.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 40));
        
        // Center align
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableKhuVuc.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableKhuVuc.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        // Column widths
        tableKhuVuc.getColumnModel().getColumn(0).setPreferredWidth(60);
        tableKhuVuc.getColumnModel().getColumn(1).setPreferredWidth(120);
        tableKhuVuc.getColumnModel().getColumn(2).setPreferredWidth(200);
        tableKhuVuc.getColumnModel().getColumn(3).setPreferredWidth(250);
    }
    
    private void addEvents() {
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLamMoi.addActionListener(this);
        tableKhuVuc.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == btnThem) {
            themKhuVuc();
        } else if (source == btnSua) {
            suaKhuVuc();
        } else if (source == btnXoa) {
            xoaKhuVuc();
        } else if (source == btnLamMoi) {
            lamMoi();
        }
    }
    
    private void loadData() {
        modelKhuVuc.setRowCount(0);
        List<KhuVuc> list = khuVucDAO.getAll();
        int stt = 1;
        
        for (KhuVuc khuVuc : list) {
            String viTri = (khuVuc.getViTri() != null && !khuVuc.getViTri().isEmpty()) 
                          ? khuVuc.getViTri() 
                          : "";
            
            modelKhuVuc.addRow(new Object[]{
                stt++,
                khuVuc.getMaKhuVuc(),
                khuVuc.getTenKhuVuc(),
                viTri
            });
        }
    }
    
    private boolean validateData() {
        String ten = txtTenKhuVuc.getText().trim();
        String viTri = txtViTri.getText().trim();
        
        // Validate tên khu vực
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tên khu vực không được để trống!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtTenKhuVuc.requestFocus();
            return false;
        }
        
        if (ten.length() < 3 || ten.length() > 50) {
            JOptionPane.showMessageDialog(this, 
                "Tên khu vực phải từ 3 đến 50 ký tự!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtTenKhuVuc.requestFocus();
            return false;
        }
        
        if (!ten.matches("^[A-Za-zÀ-ỹà-ỹ0-9 ]+$")) {
            JOptionPane.showMessageDialog(this, 
                "Tên khu vực chỉ chứa chữ cái, số và khoảng trắng!\nVí dụ: Tầng 1, Khu vực ngoài trời, Sân thượng", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtTenKhuVuc.requestFocus();
            return false;
        }
        
        // Validate vị trí (không bắt buộc)
        if (!viTri.isEmpty() && viTri.length() > 50) {
            JOptionPane.showMessageDialog(this, 
                "Vị trí không được quá 50 ký tự!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtViTri.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void themKhuVuc() {
        if (!validateData()) {
            return;
        }
        
        String ma = txtMaKhuVuc.getText().trim();
        String ten = txtTenKhuVuc.getText().trim();
        String viTri = txtViTri.getText().trim();
        
        KhuVuc khuVuc = new KhuVuc(ma, ten, viTri);
        
        try {
            if (khuVucDAO.themKhuVuc(khuVuc)) {
                JOptionPane.showMessageDialog(this, 
                    "Thêm khu vực thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Thêm khu vực thất bại!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void suaKhuVuc() {
        int row = tableKhuVuc.getSelectedRow();
        
        if (row < 0) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn khu vực cần sửa!", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateData()) {
            return;
        }
        
        String ma = txtMaKhuVuc.getText().trim();
        String ten = txtTenKhuVuc.getText().trim();
        String viTri = txtViTri.getText().trim();
        
        KhuVuc khuVuc = new KhuVuc(ma, ten, viTri);
        
        try {
            if (khuVucDAO.suaKhuVuc(khuVuc)) {
                JOptionPane.showMessageDialog(this, 
                    "Sửa khu vực thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Sửa khu vực thất bại!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Lỗi: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void xoaKhuVuc() {
        int row = tableKhuVuc.getSelectedRow();
        
        if (row < 0) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn khu vực cần xóa!", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String ma = tableKhuVuc.getValueAt(row, 1).toString();
        String ten = tableKhuVuc.getValueAt(row, 2).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa khu vực \"" + ten + "\" không?\n" +
            "Lưu ý: Xóa khu vực sẽ ảnh hưởng đến các bàn ăn thuộc khu vực này!", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (khuVucDAO.xoaKhuVuc(ma)) {
                    JOptionPane.showMessageDialog(this, 
                        "Xóa khu vực thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    lamMoi();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Xóa khu vực thất bại!\nCó thể khu vực này đang được sử dụng.", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, 
                    "Lỗi: " + e.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void lamMoi() {
        txtMaKhuVuc.setText(khuVucDAO.taoMaKhuVucMoi());
        txtTenKhuVuc.setText("");
        txtViTri.setText("");
        txtTenKhuVuc.requestFocus();
        tableKhuVuc.clearSelection();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableKhuVuc.getSelectedRow();
        if (row >= 0) {
            txtMaKhuVuc.setText(modelKhuVuc.getValueAt(row, 1).toString());
            txtTenKhuVuc.setText(modelKhuVuc.getValueAt(row, 2).toString());
            
            Object viTri = modelKhuVuc.getValueAt(row, 3);
            txtViTri.setText(viTri != null ? viTri.toString() : "");
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
    
    // Main method để test
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Quản lý Khu Vực");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(750, 650);
            frame.setLocationRelativeTo(null);
            
            QuanLyKhuVuc panel = new QuanLyKhuVuc();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
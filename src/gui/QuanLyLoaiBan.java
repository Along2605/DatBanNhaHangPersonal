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
import dao.LoaiBanDAO;
import entity.LoaiBan;

public class QuanLyLoaiBan extends JPanel implements ActionListener, MouseListener {
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
    private JTextField txtMaLoaiBan;
    private JTextField txtTenLoaiBan;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnLamMoi;
    private JTable tableLoaiBan;
    private DefaultTableModel modelLoaiBan;
    
    // DAO
    private LoaiBanDAO loaiBanDAO;
    
    public QuanLyLoaiBan() {
        ConnectDB.getInstance().connect();
        loaiBanDAO = new LoaiBanDAO();
        
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
        
        JLabel lblTitle = new JLabel("QUẢN LÝ LOẠI BÀN", SwingConstants.CENTER);
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
        
        // Mã loại bàn
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        JLabel lblMa = createLabel("Mã loại bàn:");
        mainPanel.add(lblMa, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtMaLoaiBan = createTextField();
        txtMaLoaiBan.setText(loaiBanDAO.taoMaLoaiBanMoi());
        txtMaLoaiBan.setEditable(false);
        txtMaLoaiBan.setBackground(new Color(236, 240, 241));
        mainPanel.add(txtMaLoaiBan, gbc);
        
        // Tên loại bàn
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel lblTen = createLabel("Tên loại bàn:");
        mainPanel.add(lblTen, gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        txtTenLoaiBan = createTextField();
        txtTenLoaiBan.requestFocus();
        mainPanel.add(txtTenLoaiBan, gbc);
        
        // Panel chứa các nút
        gbc.gridx = 0;
        gbc.gridy = 2;
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
        String[] columns = {"STT", "Mã loại bàn", "Tên loại bàn"};
        modelLoaiBan = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableLoaiBan = new JTable(modelLoaiBan);
        customizeTable();
        
        JScrollPane scrollPane = new JScrollPane(tableLoaiBan);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                "Danh sách loại bàn",
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
        tableLoaiBan.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tableLoaiBan.setRowHeight(35);
        tableLoaiBan.setSelectionBackground(new Color(52, 152, 219, 100));
        tableLoaiBan.setSelectionForeground(TEXT_COLOR);
        tableLoaiBan.setGridColor(new Color(189, 195, 199));
        tableLoaiBan.setShowGrid(true);
        
        // Header
        JTableHeader header = tableLoaiBan.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(PRIMARY_COLOR);
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(header.getWidth(), 35));
        
        // Center align
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        tableLoaiBan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableLoaiBan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        
        // Column widths
        tableLoaiBan.getColumnModel().getColumn(0).setPreferredWidth(80);
        tableLoaiBan.getColumnModel().getColumn(1).setPreferredWidth(150);
        tableLoaiBan.getColumnModel().getColumn(2).setPreferredWidth(300);
    }
    
    private void addEvents() {
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnLamMoi.addActionListener(this);
        tableLoaiBan.addMouseListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if (source == btnThem) {
            themLoaiBan();
        } else if (source == btnSua) {
            suaLoaiBan();
        } else if (source == btnXoa) {
            xoaLoaiBan();
        } else if (source == btnLamMoi) {
            lamMoi();
        }
    }
    
    private void loadData() {
        modelLoaiBan.setRowCount(0);
        List<LoaiBan> list = loaiBanDAO.getAllLoaiBan();
        int stt = 1;
        
        for (LoaiBan loaiBan : list) {
            modelLoaiBan.addRow(new Object[]{
                stt++,
                loaiBan.getMaLoaiBan(),
                loaiBan.getTenLoaiBan()
            });
        }
    }
    
    private boolean validateData() {
        String ten = txtTenLoaiBan.getText().trim();
        
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Tên loại bàn không được để trống!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtTenLoaiBan.requestFocus();
            return false;
        }
        
        if (ten.length() < 3 || ten.length() > 50) {
            JOptionPane.showMessageDialog(this, 
                "Tên loại bàn phải từ 3 đến 50 ký tự!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtTenLoaiBan.requestFocus();
            return false;
        }
        
        if (!ten.matches("^[A-Za-zÀ-ỹà-ỹ0-9 ]+$")) {
            JOptionPane.showMessageDialog(this, 
                "Tên loại bàn chỉ chứa chữ cái, số và khoảng trắng!\nVí dụ: Bàn VIP, Bàn thường, Bàn ngoài trời", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            txtTenLoaiBan.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void themLoaiBan() {
        if (!validateData()) {
            return;
        }
        
        String ma = txtMaLoaiBan.getText().trim();
        String ten = txtTenLoaiBan.getText().trim();
        
        LoaiBan loaiBan = new LoaiBan(ma, ten);
        
        try {
            if (loaiBanDAO.themLoaiBan(loaiBan)) {
                JOptionPane.showMessageDialog(this, 
                    "Thêm loại bàn thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Thêm loại bàn thất bại!", 
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
    
    private void suaLoaiBan() {
        int row = tableLoaiBan.getSelectedRow();
        
        if (row < 0) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn loại bàn cần sửa!", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (!validateData()) {
            return;
        }
        
        String ma = txtMaLoaiBan.getText().trim();
        String ten = txtTenLoaiBan.getText().trim();
        
        LoaiBan loaiBan = new LoaiBan(ma, ten);
        
        try {
            if (loaiBanDAO.suaLoaiBan(loaiBan)) {
                JOptionPane.showMessageDialog(this, 
                    "Sửa loại bàn thành công!", 
                    "Thành công", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadData();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Sửa loại bàn thất bại!", 
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
    
    private void xoaLoaiBan() {
        int row = tableLoaiBan.getSelectedRow();
        
        if (row < 0) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn loại bàn cần xóa!", 
                "Thông báo", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String ma = tableLoaiBan.getValueAt(row, 1).toString();
        String ten = tableLoaiBan.getValueAt(row, 2).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa loại bàn \"" + ten + "\" không?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (loaiBanDAO.xoaLoaiBan(ma)) {
                    JOptionPane.showMessageDialog(this, 
                        "Xóa loại bàn thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    lamMoi();
                } else {
                    JOptionPane.showMessageDialog(this, 
                        "Xóa loại bàn thất bại!\nCó thể loại bàn này đang được sử dụng.", 
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
        txtMaLoaiBan.setText(loaiBanDAO.taoMaLoaiBanMoi());
        txtTenLoaiBan.setText("");
        txtTenLoaiBan.requestFocus();
        tableLoaiBan.clearSelection();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int row = tableLoaiBan.getSelectedRow();
        if (row >= 0) {
            txtMaLoaiBan.setText(modelLoaiBan.getValueAt(row, 1).toString());
            txtTenLoaiBan.setText(modelLoaiBan.getValueAt(row, 2).toString());
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
            JFrame frame = new JFrame("Quản lý Loại Bàn");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 600);
            frame.setLocationRelativeTo(null);
            
            QuanLyLoaiBan panel = new QuanLyLoaiBan();
            frame.setContentPane(panel);
            frame.setVisible(true);
        });
    }
}
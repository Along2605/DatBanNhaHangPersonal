package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class DoiMatKhau extends JPanel {

    private JPasswordField txtMkCu;
    private JPasswordField txtMkMoi;
    private JPasswordField txtXacNhan;
    private JButton btnLuu;
    private JButton btnHuy;
	private JCheckBox cbHienThi;

    public DoiMatKhau() {
        // Panel chính: căn giữa pnForm
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#EAF1F9"));

        // --- TẤT CẢ NẰM TRONG pnForm ---
        JPanel pnForm = new JPanel();
        pnForm.setLayout(new BoxLayout(pnForm, BoxLayout.Y_AXIS));
        pnForm.setBackground(Color.WHITE);
        pnForm.setBorder(new EmptyBorder(30, 50, 50, 50));
        pnForm.setMaximumSize(new Dimension(700, 700));

        // 1. Tiêu đề
        JLabel lblTitle = new JLabel("ĐỔI MẬT KHẨU");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(lblTitle);

        pnForm.add(Box.createVerticalStrut(15));

        //Mk cũ
        JLabel lblMkCu = new JLabel("Mật khẩu cũ");
        lblMkCu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMkCu.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(lblMkCu);
        txtMkCu = new JPasswordField(20);
        txtMkCu.setPreferredSize(new Dimension(300, 35));
        txtMkCu.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(txtMkCu);

        pnForm.add(Box.createVerticalStrut(15));

        //mk mới
        JLabel lblMkMoi = new JLabel("Mật khẩu mới");
        lblMkMoi.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblMkMoi.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(lblMkMoi);
        txtMkMoi = new JPasswordField(20);
        txtMkMoi.setPreferredSize(new Dimension(300, 35));
        txtMkMoi.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(txtMkMoi);

        pnForm.add(Box.createVerticalStrut(15));

        //nhập lại
        JLabel lblXacNhan = new JLabel("Xác nhận lại mật khẩu");
        lblXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblXacNhan.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(lblXacNhan);
        txtXacNhan = new JPasswordField(20);
        txtXacNhan.setPreferredSize(new Dimension(300, 35));
        txtXacNhan.setMaximumSize(new Dimension(350, 28));
        txtXacNhan.setAlignmentX(LEFT_ALIGNMENT);
        pnForm.add(txtXacNhan);

        pnForm.add(Box.createVerticalStrut(15));
        
        //Hiển thị mật khẩu
        cbHienThi = new JCheckBox("Hiển thị mật khẩu?");
        cbHienThi.setOpaque(false);
        pnForm.add(cbHienThi);
        pnForm.add(Box.createVerticalStrut(30));
        
        //XỬ LÝ
        cbHienThi.addItemListener(e -> {
        	boolean hienThi = (e.getStateChange() == ItemEvent.SELECTED);
        	txtMkCu.setEchoChar(hienThi ? (char) 0 : '\u2022');
        	txtMkMoi.setEchoChar(hienThi ? (char) 0 : '\u2022');
        	txtXacNhan.setEchoChar(hienThi ? (char) 0 : '\u2022');
        });
        
        // 5. Panel nút
        JPanel pnButton = new JPanel(new GridLayout(1, 2, 10, 0));
        pnButton.setAlignmentX(LEFT_ALIGNMENT);

        btnLuu = new JButton("Đồng ý");
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnLuu.setPreferredSize(new Dimension(120, 35));

        btnHuy = new JButton("Hủy bỏ");
        btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnHuy.setPreferredSize(new Dimension(120, 35));

        pnButton.add(btnLuu);
        pnButton.add(btnHuy);
        pnForm.add(pnButton);

        // Thêm pnForm vào panel chính
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(pnForm, gbc);
    }
    

}
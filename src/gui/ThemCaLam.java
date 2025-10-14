package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.components.JSpinField;

public class ThemCaLam extends JPanel{
	private JSpinner gioBD;
	private JTextField txtMaCa;
	private JTextField txtTenCa;
	private JSpinner gioKT;
	private JTextArea txtGhiChu;
	private JCheckBox[] chkNgay;
	private JRadioButton rdHoatDong;
	private JRadioButton rdNgung;
	private JTable tableCaLam;
	private DefaultTableModel modelCaLam;
	private JButton btnLuu;
	private JButton btnHuy;
	private JDateChooser ngayBD;
	private JDateChooser ngayKT;
	private Component btnXoa;

	public ThemCaLam() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(10, 10));
        setBackground(Color.decode("#EAF1F9"));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        // Title
        JLabel lblTitle = new JLabel("QUẢN LÝ CA LÀM", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(Color.decode("#333333"));
        add(lblTitle, BorderLayout.NORTH);

        //Form nhập
        JPanel pnForm = new JPanel(new GridBagLayout());
        add(pnForm, BorderLayout.WEST); 
        pnForm.setBackground(Color.WHITE);
        pnForm.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            "Thêm ca làm",
            0, 0,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(25, 118, 210)
        ));
        
        int row = 0;
        //Input
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST; // Căn trái
        //mã ca
        txtMaCa = new JTextField(15);
        txtMaCa.setEditable(false);
        addFormField(pnForm, gbc, row++, "Mã ca làm *", txtMaCa);
        //giờ làm
        SpinnerDateModel modelBD = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        gioBD = new JSpinner(modelBD);
        gioBD.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gioBD.setEditor(new JSpinner.DateEditor(gioBD, "HH:mm"));
        addFormField(pnForm, gbc, row++, "Giờ bắt đầu *", gioBD);
        //tan ca
        SpinnerDateModel modelKT = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY);
        gioKT = new JSpinner(modelKT);
        gioKT.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        gioKT.setEditor(new JSpinner.DateEditor(gioKT, "HH:mm"));
        addFormField(pnForm, gbc, row++, "Giờ kết thúc *", gioKT);

        //từ ngày
        ngayBD = new JDateChooser();
        ngayBD.setDateFormatString("dd/MM/yyyy");
        ngayBD.setMinSelectableDate(new Date()); //chọn từ ngày hiện hành
        addFormField(pnForm, gbc, row++, "Ngày bắt đầu", ngayBD);
        
        //đến ngày
        ngayKT = new JDateChooser();
        ngayKT.setDateFormatString("dd/MM/yyyy");
        ngayKT.setMinSelectableDate(new Date()); //chọn từ ngày hiện hành
        addFormField(pnForm, gbc, row++, "Ngày kết thúc", ngayKT);
       
        //ghi chú
        txtGhiChu = new JTextArea(5, 15);
        JScrollPane scrollGhiChu = new JScrollPane(txtGhiChu);
        addFormField(pnForm, gbc, row++, "Ghi chú", scrollGhiChu);
        //trạng thái

        JPanel pnTrangThai = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rdHoatDong = new JRadioButton("Hoạt động", true);
        rdHoatDong.setOpaque(false);
        rdNgung = new JRadioButton("Ngừng hoạt động");
        rdNgung.setOpaque(false);
        ButtonGroup group = new ButtonGroup();
        group.add(rdHoatDong);
        group.add(rdNgung);
        pnTrangThai.add(rdHoatDong);
        pnTrangThai.add(rdNgung);
        pnTrangThai.setOpaque(false);
        addFormField(pnForm, gbc, row++, "Trạng thái", pnTrangThai);

     
        //các nút
        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        btnLuu = new JButton("Lưu");
        btnXoa = new JButton("Xóa");
        btnHuy = new JButton("Hủy");
        btnLuu.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoa.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnHuy.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pnButton.add(btnLuu);
        pnButton.add(btnXoa);
        pnButton.add(btnHuy);
        pnButton.setOpaque(false);
        gbc.gridy = row++;
        pnForm.add(pnButton, gbc);
        
        //bảng thông tin
        JPanel pnCaLam = new JPanel();
        pnCaLam.setBackground(Color.WHITE);
        pnCaLam.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            "Thông tin ca làm",
            0, 0,
            new Font("Segoe UI", Font.BOLD, 14),
            new Color(25, 118, 210)
        ));
        add(pnCaLam, BorderLayout.CENTER);
        String[] col = {"STT", "Mã ca làm", "Giờ vô ca", "Giờ tan ca", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"};
        modelCaLam = new DefaultTableModel(col, 0);
        tableCaLam = new JTable(modelCaLam);
        add(new JScrollPane(tableCaLam));
    }

    // Hàm tiện ích thêm 1 hàng label + component
    private void addFormField(JPanel panel, GridBagConstraints gbc, int row, 
                              String labelText, JComponent component) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        if (labelText.contains("*")) label.setForeground(new Color(183, 28, 28));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(component, gbc);
  
	}
}

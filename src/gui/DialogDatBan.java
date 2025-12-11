package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.BanAnDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import entity.BanAn;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import util.Session;

public class DialogDatBan extends JDialog {
	private JTextField txtMaHD;
	private HoaDonDAO hd_DAO;
	private JTextField txtSdt;
	private JTextField txtTenKH;
	private JPanel pnSDT;
	private JButton btnTimKH;
	private JButton btnDat;
	private JButton btnDong;
	private KhachHangDAO kh_DAO;
	private BanAn ban;
	private JRadioButton rdNam;
	private JRadioButton rdNu;
	private ButtonGroup grRadio;
	private JPanel pnGioiTinh;
	private Session session;
	private BanAnDAO ban_DAO;
	
	public DialogDatBan(Frame parent, BanAn ban) {
		super(parent, "Đặt bàn", true);
		this.ban = ban;

		hd_DAO = new HoaDonDAO();
		kh_DAO = new KhachHangDAO();
		ban_DAO = new BanAnDAO();

		setSize(400, 350);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout(10, 10));

		initUI();
	}

	private void initUI() {
		JPanel pnForm = new JPanel(new GridBagLayout());
		add(pnForm);
		pnForm.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		int row = 0;

		// MaHD
		gbc.gridx = 0;
		gbc.gridy = row++;
		pnForm.add(new JLabel("Mã hóa đơn: "), gbc);

		gbc.gridx = 1;
		txtMaHD = new JTextField(20);
		txtMaHD.setEditable(false);
		txtMaHD.setText(hd_DAO.taoMaHoaDonTuDong());
		pnForm.add(txtMaHD, gbc);

		// Số điện thoại
		gbc.gridx = 0;
		gbc.gridy = row++;
		pnForm.add(new JLabel("Số điện thoại: "), gbc);

		gbc.gridx = 1;
		pnSDT = new JPanel(new BorderLayout(5, 0));
		txtSdt = new JTextField();
		btnTimKH = new JButton("🔍");
		pnSDT.add(txtSdt, BorderLayout.CENTER);
		pnSDT.add(btnTimKH, BorderLayout.EAST);
		pnForm.add(pnSDT, gbc);

		// tên khách hàng
		gbc.gridx = 0;
		gbc.gridy = row++;
		pnForm.add(new JLabel("Họ tên: "), gbc);

		gbc.gridx = 1;
		txtTenKH = new JTextField(20);
		pnForm.add(txtTenKH, gbc);

		// Giới tính
		gbc.gridx = 0;
		gbc.gridy = row++;
		pnForm.add(new JLabel("Giới tính: "), gbc);

		gbc.gridx = 1;
		pnGioiTinh = new JPanel(new GridLayout(1, 2, 10, 0));
		rdNam = new JRadioButton("Nam");
		rdNam.setSelected(true);
		rdNu = new JRadioButton("Nữ");
		grRadio = new ButtonGroup();
		grRadio.add(rdNam);
		grRadio.add(rdNu);
		pnGioiTinh.add(rdNam);
		pnGioiTinh.add(rdNu);
		pnForm.add(pnGioiTinh, gbc);

		// Nút
		gbc.gridx = 0;
		gbc.gridy = row++;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(15, 5, 5, 5);

		JPanel pnButton = new JPanel(new GridLayout(1, 2, 10, 0));
		btnDat = new JButton("Đặt bàn");
		btnDong = new JButton("Đóng");
		pnButton.add(btnDat);
		pnButton.add(btnDong);

		pnForm.add(pnButton, gbc);

		btnDong.addActionListener(e -> {
			dispose();
		});

		btnTimKH.addActionListener(e -> {
			KhachHang kh = kh_DAO.timKhachHangTheoSDT(txtSdt.getText().toString().trim());
			if (kh != null) {
				JOptionPane.showMessageDialog(null, "Đã tìm thấy khách hàng " + kh.getHoTen() + "!");
				txtTenKH.setText(kh.getHoTen());
				if (kh.isGioiTinh()) {
					rdNam.setSelected(true);
				} else {
					rdNu.setSelected(true);
				}
				return;
			}
			JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng");
		});

		btnDat.addActionListener(e -> {
			System.out.println("MÃ bàn: " + ban.getMaBan());
			if (!validForm()) {
				return;
			}

			KhachHang kh = kh_DAO.timKhachHangTheoSDT(txtSdt.getText().trim());
			if (kh == null) {
				themKH();
				kh = kh_DAO.timKhachHangTheoSDT(txtSdt.getText().trim());
			}
			String maHD = txtMaHD.getText().trim();
			String maBan = ban.getMaBan();
			BanAn ba = new BanAn(maBan);
			String maKH = kh.getMaKH();
			KhachHang khach = new KhachHang(maKH);
			String maNV = session.getMaNhanVienDangNhap();
			NhanVien nv = new NhanVien(maNV);
			LocalDateTime ngayLap = LocalDateTime.now();
			double thueVAT = 0.1;
			double tongTien = 0;
			String trangThai = "Chưa thanh toán";
			double tienCoc = 0;
			
			HoaDon hd = new HoaDon(maHD, ban, khach, nv, ngayLap, thueVAT, tongTien, null, trangThai, null, tienCoc);
			boolean ok = hd_DAO.themHoaDon(hd, Session.getMaNhanVienDangNhap());

			if (ok) {
				JOptionPane.showMessageDialog(this, "Đặt bàn thành công!");
				ban_DAO.capNhatTrangThaiBan(ban.getMaBan(), "Đang sử dụng");
				dispose();
				
			} else {
				JOptionPane.showMessageDialog(this, "Đặt bàn thất bại!");
			}
		});
	}

	private boolean validForm() {
		String sdt = txtSdt.getText().toString().trim();
		String ten = txtTenKH.getText().toString().trim();

		if (sdt.isEmpty() || ten.isEmpty()) {
			JOptionPane.showMessageDialog(btnDat, "Vui lòng nhập đầy đủ các trường!");
			return false;
		}

		if (!sdt.matches("^(0[1-9][0-9]{8})$")) {
			JOptionPane.showMessageDialog(btnDat, "Số điện thoại không hợp lệ!");
			return false;
		}

		if (!ten.matches("^([A-ZÀ-Ỹa-zà-ỹ]+\\s?)+$")) {
			JOptionPane.showMessageDialog(btnDat, "Tên không hợp lệ!");
			return false;
		}

		return true;
	}

	private void themKH() {
		String maKH = kh_DAO.taoMaKhachHangTuDong();
		String ten = txtTenKH.getText().toString().trim();
		boolean gt = rdNam.isSelected() ? true : false;
		String sdt = txtSdt.getText().toString().trim();

		KhachHang kh = new KhachHang(maKH, ten, gt, sdt, 0, LocalDate.now(), true);
		kh_DAO.themKhachHang(kh, Session.getMaNhanVienDangNhap());
	}
}

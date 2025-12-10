package gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import connectDB.ConnectDB;
import dao.CaLamViecDAO;
import dao.LichLamViecDAO;
import dao.NhanVienDAO;
import entity.CaLamViec;
import entity.LichLamViec;
import entity.NhanVien;

public class PhanCaLam extends JPanel implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JDateChooser ngayLam;
	private JComboBox<String> cbCaLam;
	private JButton btnTimNVTrongCa, btnPhanCa, btnHuy, btnChonNV, btnXoaNV;
	private DefaultListModel<String> modelAll, modelDaChon;
	private DefaultTableModel modelLich;
	private JList<String> listNV, listChon;
	private CaLamViecDAO caLamDAO;
	private LichLamViecDAO lichLamDAO;
	private NhanVienDAO nhanVienDAO;
	private JTable tableLich;
	private JRadioButton rdLam;
	private JRadioButton rdNghi;
	private JButton btnSua;
	private JLabel lblLam;
	private JLabel lblNghi;

	public PhanCaLam() {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		caLamDAO = new CaLamViecDAO();
		lichLamDAO = new LichLamViecDAO();
		nhanVienDAO = new NhanVienDAO();

		setLayout(new BorderLayout(10, 10));
		setBackground(Color.decode("#EAF1F9"));
		setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title
		JLabel lblTitle = new JLabel("PHÂN CA LÀM", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setForeground(Color.decode("#333333"));
		add(lblTitle, BorderLayout.NORTH);

		// Form panel
		JPanel pnForm = new JPanel(new GridBagLayout());
		pnForm.setBackground(Color.WHITE);
		pnForm.setBorder(BorderFactory.createTitledBorder("Thông tin ca"));
		add(pnForm, BorderLayout.WEST);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 15, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		int row = 0;

		//ngày làm
		ngayLam = new JDateChooser();
		ngayLam.setDateFormatString("dd/MM/yyyy");
		ngayLam.setMinSelectableDate(new Date());
		addFormField(pnForm, gbc, row++, "Ngày làm: *", ngayLam);

		//chọn ca
		cbCaLam = new JComboBox<>();
		loadCaLamViec();
		addFormField(pnForm, gbc, row++, "Ca làm: *", cbCaLam);

		//Tìm nhân viên
		btnTimNVTrongCa = new JButton("Tìm nhân viên trống ca");
		addFormField(pnForm, gbc, row++, "Nhân viên", btnTimNVTrongCa);

		//Trạng thái
		rdLam = new JRadioButton("Làm");
		rdLam.setSelected(true);
		rdNghi = new JRadioButton("Nghỉ");
		rdLam.setBackground(Color.WHITE);
		rdNghi.setBackground(Color.WHITE);
		ButtonGroup groupTrangThai = new ButtonGroup();
		groupTrangThai.add(rdLam);
		groupTrangThai.add(rdNghi);
		JPanel pnTrangThai = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnTrangThai.add(rdLam);
		pnTrangThai.add(rdNghi);
		pnTrangThai.setOpaque(false);
		addFormField(pnForm, gbc, row++, "Trạng thái", pnTrangThai);

		//Buttons
		JPanel pnButton = new JPanel(new GridLayout(1, 2, 15, 0));
		btnPhanCa = new JButton("Phân ca");
		btnSua = new JButton("Sửa");
		btnHuy = new JButton("Hủy");
		btnPhanCa.setBackground(new Color(0, 123, 255));
		btnPhanCa.setForeground(Color.WHITE);
		btnHuy.setBackground(new Color(220, 53, 69));
		btnHuy.setForeground(Color.WHITE);
		pnButton.add(btnPhanCa);
		pnButton.add(btnSua);
		pnButton.add(btnHuy);
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.gridwidth = 2;
		pnForm.add(pnButton, gbc);

		// Nhân viên
		JPanel pnNV = new JPanel();
		pnNV.setLayout(new BoxLayout(pnNV, BoxLayout.Y_AXIS));
		pnNV.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnNV.setBackground(Color.WHITE);
		add(pnNV, BorderLayout.EAST);

		// Tất cả nhân viên
		JPanel pnNVR = new JPanel(new BorderLayout());
		pnNV.add(pnNVR);
		JLabel lblTieuDe = new JLabel("Danh sách nhân viên");
		pnNVR.add(lblTieuDe, BorderLayout.NORTH);
		modelAll = new DefaultListModel<>();
		loadNhanVien();
		listNV = new JList<>(modelAll);
		listNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollAll = new JScrollPane(listNV);
		scrollAll.setPreferredSize(new Dimension(200, 150));
		pnNVR.add(scrollAll, BorderLayout.CENTER);

		// Thêm/xóa nhân viên
		JPanel pnChon = new JPanel(new GridLayout(1, 2, 5, 5));
		btnChonNV = new JButton(">>");
		btnChonNV.setBackground(Color.GREEN);
		btnXoaNV = new JButton("<<");
		btnXoaNV.setBackground(Color.RED);
		pnChon.add(btnChonNV);
		pnChon.add(btnXoaNV);
		pnNV.add(pnChon);

		// Danh sách đã có ca làm
		JPanel pnNVC = new JPanel(new BorderLayout());
		pnNV.add(pnNVC);
		JLabel lblTieuDe1 = new JLabel("Nhân viên đã chọn");
		pnNVC.add(lblTieuDe1, BorderLayout.NORTH);
		modelDaChon = new DefaultListModel<>();
		listChon = new JList<>(modelDaChon);
		listChon.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		JScrollPane scrollChon = new JScrollPane(listChon);
		scrollChon.setPreferredSize(new Dimension(200, 150));
		pnNVC.add(scrollChon, BorderLayout.CENTER);

		// Lịch làm
		JPanel pnCenter = new JPanel(new BorderLayout());
		pnCenter.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pnCenter.setBackground(Color.white);
		add(pnCenter, BorderLayout.CENTER);
		JLabel lblLich = new JLabel("Lịch làm");
		pnCenter.add(lblLich, BorderLayout.NORTH);

		String[] col = new String[8];
		col[0] = "Ca làm";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE, dd/MM", new Locale("vi", "VN"));
		LocalDate today = LocalDate.now();
		for (int i = 0; i < 7; i++) {
			col[i + 1] = today.plusDays(i).format(format);
		}
		modelLich = new DefaultTableModel(new Object[0][8], col);
		tableLich = new JTable(modelLich);
		tableLich.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableLich.getColumnModel().getColumn(0).setPreferredWidth(150);
		tableLich.setRowSelectionAllowed(false); // không click vào hang
		tableLich.setColumnSelectionAllowed(true);
		tableLich.setCellSelectionEnabled(true);
		for (int i = 1; i < 8; i++) {
			tableLich.getColumnModel().getColumn(i).setPreferredWidth(100);
		}

		// đổi màu theo trạng thái
		tableLich.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				// Dùng JTextArea để tự động xuống dòng nếu nội dung dài
				JTextArea textArea = new JTextArea();
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(true);
				textArea.setEditable(false); // Không cho chỉnh sửa
				textArea.setOpaque(true);
				textArea.setFont(table.getFont());
				textArea.setText(value == null ? "" : value.toString());

				// Màu mặc định
				Color bgColor = Color.WHITE;
				Color fgColor = Color.BLACK;

				if (column > 0 && !textArea.getText().isEmpty()) {
					String tenCa = table.getValueAt(row, 0).toString();
					LocalDate ngay = LocalDate.now().plusDays(column - 1);

					// Tìm mã ca
					ArrayList<CaLamViec> ca = caLamDAO.timCaLamViecTheoTen(tenCa);
					if (ca != null && !ca.isEmpty()) {
						String maCa = ca.get(0).getMaCa();
						List<LichLamViec> dsLich = lichLamDAO.layLichLamViecTheoCaVaNgay(maCa, ngay);

						// Nếu có lịch làm thì đổi màu theo trạng thái
						if (dsLich != null && !dsLich.isEmpty()) {
							boolean coNghi = dsLich.stream().anyMatch(lich -> !lich.isTrangThai());
							bgColor = coNghi ? new Color(255, 204, 204) : new Color(204, 255, 204);
							// đỏ nhạt = nghỉ, xanh nhạt = làm
						}
					}
				}

				// Áp dụng màu
				textArea.setBackground(bgColor);
				textArea.setForeground(fgColor);

				// Căn giữa nội dung
				textArea.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
				textArea.setAlignmentY(JTextArea.CENTER_ALIGNMENT);

				// Tự điều chỉnh chiều cao dòng
				int height = textArea.getPreferredSize().height + 8;
				if (table.getRowHeight(row) < height) {
					table.setRowHeight(row, height);
				}

				return textArea;
			}
		});

		// load lịch làm
		loadLichLamTheoTuan();
		JScrollPane scrollLich = new JScrollPane(tableLich);
		pnCenter.add(scrollLich, BorderLayout.CENTER);

		// Note trạng thái
		JPanel pnNote = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		pnCenter.add(pnNote, BorderLayout.SOUTH);
		pnNote.setOpaque(false);

		JPanel pnLam = new JPanel();
		pnNote.add(pnLam);
		pnLam.setBackground(new Color(204, 255, 204));
		pnLam.setPreferredSize(new Dimension(40, 20));
		pnNote.add(lblLam = new JLabel("Làm"));

		JPanel pnNghi = new JPanel();
		pnNote.add(pnNghi);
		pnNghi.setBackground(new Color(255, 204, 204));
		pnNghi.setPreferredSize(new Dimension(40, 20));
		pnNote.add(lblNghi = new JLabel("Nghỉ"));

		// Xử lý sự kiện
		btnPhanCa.addActionListener(this);
		btnTimNVTrongCa.addActionListener(this);
		btnSua.addActionListener(this);
		btnHuy.addActionListener(this);
		btnChonNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		tableLich.addMouseListener(this);

	}

	private void addFormField(JPanel panel, GridBagConstraints gbc, int row, String lblText, JComponent component) {
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.weightx = 0.3;
		JLabel lbl = new JLabel(lblText);
		panel.add(lbl, gbc);
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		panel.add(component, gbc);
	}
	
	//Load lên combobox
	private void loadCaLamViec() {
		List<CaLamViec> dsCaLam = caLamDAO.layDanhSachCaLamViec();
		cbCaLam.removeAllItems();
		for (CaLamViec ca : dsCaLam) {
			if (ca.isTrangThai()) {
				// Thêm mã ca vào đầu để dễ lấy
				String item = ca.getMaCa() + " - " + ca.getTenCa() + " (" + ca.getGioVaoLam() + "-" + ca.getGioTanLam()
						+ ")";
				cbCaLam.addItem(item);
			}
		}
	}

	// Load nhân viên lên bảng tất cả nhân viên
	private void loadNhanVien() {
		List<NhanVien> dsNhanVien = nhanVienDAO.layDanhSachNhanVien();
		modelAll.clear();
		for (NhanVien nv : dsNhanVien) {
			modelAll.addElement(nv.getMaNV() + " - " + nv.getHoTen());
		}
	}
	
	//Sự kiện
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnTimNVTrongCa)) {
			timNhanVienTrongCa();
		} else if (o.equals(btnPhanCa)) {
			phanCaNhanVien();
		} else if (o.equals(btnHuy)) {
			huy();
		} else if (o.equals(btnChonNV)) {
			chonNhanVien();
		} else if (o.equals(btnXoaNV)) {
			xoaNhanVien();
		} else if (o.equals(btnSua)) {
			doiTrangThaiCa();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableLich.getSelectedRow();
		int col = tableLich.getSelectedColumn();

		if (col == 0 || row < 0)
			return;

		hienThiThongTinClick(row, col);

	}
	@Override
	public void mousePressed(MouseEvent e) {

	}
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}
	//
	
	//Reload
	private void huy() {
		ngayLam.setDate(null);
		cbCaLam.setSelectedIndex(-1);
		rdLam.setSelected(true);
		modelDaChon.clear();
		loadNhanVien();
		loadCaLamViec();
	}
	// Tìm các nhân ca trống ca đã chọn
	private void timNhanVienTrongCa() {
		if (ngayLam.getDate() == null) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		String maCa = cbCaLam.getSelectedItem().toString().split(" - ")[0];
		LocalDate ngayLamDate = ngayLam.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

		// Tất cả nhân viên
		List<NhanVien> allNV = nhanVienDAO.layDanhSachNhanVien();
		//
		List<LichLamViec> nvDaPhanCa = lichLamDAO.layLichLamViecTheoCaVaNgay(maCa, ngayLamDate);

		modelAll.clear();
		for (NhanVien nv : allNV) {
			boolean daPhanCa = false;

			if (nvDaPhanCa != null) {
				for (LichLamViec lich : nvDaPhanCa) {
					if (lich.getNhanVien() != null) {
						// Debug log để kiểm tra
						if (nv.getMaNV().equals(lich.getNhanVien().getMaNV())) {
							daPhanCa = true;
							break;
						}
					}
				}
			}

			if (!daPhanCa) {
				modelAll.addElement(nv.getMaNV() + " - " + nv.getHoTen());
			}
		}

		if (modelAll.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Không có nhân viên trống cho ngày này!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void phanCaNhanVien() {
		if (ngayLam.getDate() == null || cbCaLam.getSelectedItem() == null || modelDaChon.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày, ca và nhân viên!", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		String maCa = cbCaLam.getSelectedItem().toString().split(" - ")[0];
		LocalDate ngayLamDate = ngayLam.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		boolean allSuccess = true;

		for (int i = 0; i < modelDaChon.size(); i++) {
			String item = modelDaChon.getElementAt(i);
			String maNV = item.split(" - ")[0].trim();

			LichLamViec lich = new LichLamViec();
			lich.setCaLamViec(caLamDAO.timCaLamViecTheoMa(maCa));
			lich.setNhanVien(nhanVienDAO.getNhanVienTheoMa(maNV));
			lich.setNgayLamViec(ngayLamDate);
			lich.setTrangThai(rdLam.isSelected());

			if (!lichLamDAO.themLichLamViec(lich)) {
				allSuccess = false;
			}
		}

		// ✅ Chỉ hiển thị 1 thông báo cuối cùng
		if (allSuccess) {
			loadLichLamTheoTuan();
			huy();
			JOptionPane.showMessageDialog(this, "Phân ca thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Phân ca thất bại! Vui lòng thử lại.", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void chonNhanVien() {
		List<String> selected = listNV.getSelectedValuesList();
		if (selected.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một nhân viên!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		for (String tenNV : selected) {
			if (!modelDaChon.contains(tenNV)) {
				modelDaChon.addElement(tenNV);
				modelAll.removeElement(tenNV);
			}
		}
		listNV.clearSelection();
	}

	private void xoaNhanVien() {
		List<String> selected = listChon.getSelectedValuesList();
		if (selected.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một nhân viên để xóa!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		for (String tenNV : selected) {
			modelDaChon.removeElement(tenNV);
			if (!modelAll.contains(tenNV)) {
				modelAll.addElement(tenNV);
			}
		}
		listChon.clearSelection();
	}

	private void loadLichLamTheoTuan() {
		DefaultTableModel model = (DefaultTableModel) tableLich.getModel();
		model.setRowCount(0);
		// Ca làm
		List<CaLamViec> dsCa = caLamDAO.layDanhSachCaLamViec();
		LocalDate ngayBatDauTuan = LocalDate.now();

		// Chỉ hiển thị 7 ngày trong tuần
		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE, dd/MM", new Locale("vi", "VN"));
		String[] colNames = new String[8];
		colNames[0] = "Ca làm";
		for (int i = 0; i < 7; i++) {
			colNames[i + 1] = ngayBatDauTuan.plusDays(i).format(format);
		}
		model.setColumnIdentifiers(colNames); // Cập nhật tên cột

		for (CaLamViec ca : dsCa) {
			Vector<Object> row = new Vector<>();
			row.add(ca.getTenCa());
			// Duyệt 7 ngày trong tuần
			for (int i = 0; i < 7; i++) {
				LocalDate ngay = ngayBatDauTuan.plusDays(i);
				List<LichLamViec> dsLich = lichLamDAO.layLichLamViecTheoCaVaNgay(ca.getMaCa(), ngay);

				if (dsLich.isEmpty()) {
					row.add(""); // Nếu không có nhân viên làm
				} else {
					StringBuilder sb = new StringBuilder();

					for (LichLamViec lich : dsLich) {
						if (lich.getNhanVien() != null) {
							NhanVien nv = lich.getNhanVien();
							if (nv != null) {
								sb.append(nv.getMaNV()).append(" - ").append(nv.getHoTen()).append("\n");

							}
						}
					}

					String danhSachNV = sb.toString().trim();
					row.add(danhSachNV.isEmpty() ? "" : danhSachNV);
				}
			}
			model.addRow(row);
		}

	}

	private void doiTrangThaiCa() {
		DefaultTableModel model = (DefaultTableModel) tableLich.getModel();
		int row = tableLich.getSelectedRow();
		String tenCa = model.getValueAt(row, 0).toString();
		Date date = ngayLam.getDate();
		LocalDate ngay = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		try {
			// Lấy mã ca từ tên ca
			ArrayList<CaLamViec> ca = caLamDAO.timCaLamViecTheoTen(tenCa);
			String maCa = ca.get(0).getMaCa();

			// Lấy danh sách LichLamViec tương ứng
			List<LichLamViec> dsLich = lichLamDAO.layLichLamViecTheoCaVaNgay(maCa, ngay);
			if (dsLich == null || dsLich.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Chưa có nhân viên phân ca!", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			// Xác nhận thay đổi
			int xacNhan = JOptionPane.showConfirmDialog(this,
					"Bạn có muốn thay đổi trạng thái làm việc của nhân viên trong ca này không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (xacNhan != JOptionPane.YES_OPTION)
				return;

			// Đảo trạng thái từng nhân viên
			boolean allSuccess = true;
			for (LichLamViec lich : dsLich) {
				lich.setTrangThai(!lich.isTrangThai());
				if (!lichLamDAO.capNhatTrangThai(lich)) {
					allSuccess = false;
				}
			}

			// Reload lại bảng
			loadLichLamTheoTuan();

			if (allSuccess) {
				JOptionPane.showMessageDialog(this, "Đổi trạng thái thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi đổi trạng thái!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void hienThiThongTinClick(int row, int col) {
		try {
			DefaultTableModel model = (DefaultTableModel) tableLich.getModel();

			// Lấy tên ca
			String tenCa = model.getValueAt(row, 0).toString();

			LocalDate ngayBatDauTuan = LocalDate.now();
			LocalDate ngay = ngayBatDauTuan.plusDays(col - 1);
			ngayLam.setDate(Date.from(ngay.atStartOfDay(ZoneId.systemDefault()).toInstant()));

			// Chọn ca làm trong combobox
			for (int i = 0; i < cbCaLam.getItemCount(); i++) {
				String item = cbCaLam.getItemAt(i);
				if (item.contains(tenCa)) {
					cbCaLam.setSelectedIndex(i);
					break;
				}
			}

			// Lấy danh sách LichLamViec thực tế từ DB
			ArrayList<CaLamViec> ca = caLamDAO.timCaLamViecTheoTen(tenCa);
			String maCa = ca.get(0).getMaCa();
			List<LichLamViec> dsLich = lichLamDAO.layLichLamViecTheoCaVaNgay(maCa, ngay);

			// Hiển thị danh sách nhân viên
			modelDaChon.clear();

			// Làm mới danh sách nhân viên còn lại
			List<NhanVien> allNV = nhanVienDAO.layDanhSachNhanVien();
			modelAll.clear();
			for (NhanVien nv : allNV) {
				boolean daChon = false;
				for (int i = 0; i < modelDaChon.size(); i++) {
					if (modelDaChon.getElementAt(i).contains(nv.getMaNV())) {
						daChon = true;
						break;
					}
				}
				if (!daChon) {
					modelAll.addElement(nv.getMaNV() + " - " + nv.getHoTen());
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Test - Phân ca làm");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 700);
			frame.setLocationRelativeTo(null);
			frame.add(new PhanCaLam());
			frame.setVisible(true);
		});
	}
}
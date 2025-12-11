package gui;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.CaLamViecDAO;
import dao.LichLamViecDAO;
import entity.CaLamViec;
import entity.LichLamViec;
import entity.NhanVien;

public class XemLichLam extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelLich;
	private CaLamViecDAO caLamDAO;
	private LichLamViecDAO lichLamDAO;
	private JTable tableLich;
	private JLabel lblLam;
	private JLabel lblNghi;

	public XemLichLam() {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		caLamDAO = new CaLamViecDAO();
		lichLamDAO = new LichLamViecDAO();

		setLayout(new BorderLayout(10, 10));
		setBackground(Color.decode("#EAF1F9"));
		setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title
		JLabel lblTitle = new JLabel("Lịch làm", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setForeground(Color.decode("#333333"));
		add(lblTitle, BorderLayout.NORTH);

		// Lịch làm
		String[] col = new String[8];
		col[0] = "Ca làm";
		DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE, dd/MM", new Locale("vi", "VN"));
		LocalDate today = LocalDate.now();
		for (int i = 0; i < 7; i++) {
			col[i + 1] = today.plusDays(i).format(format);
		}
		modelLich = new DefaultTableModel(new Object[0][8], col);
		tableLich = new JTable(modelLich);

		tableLich.setRowSelectionAllowed(false); // không click vào hang
		tableLich.setColumnSelectionAllowed(true);
		tableLich.setCellSelectionEnabled(true);
		tableLich.setFillsViewportHeight(true);
		// Đổi màu tiêu đề
		JTableHeader header = tableLich.getTableHeader();
		header.setBackground(Color.decode("#4CAF50"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Segoe UI", Font.BOLD, 14));

		tableLich.setSelectionBackground(new Color(51, 153, 255));
		tableLich.setSelectionForeground(Color.WHITE);
		
		
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
					List<CaLamViec> ca = caLamDAO.timCaLamViecTheoTen(tenCa);
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
		scrollLich.setBorder(BorderFactory.createEmptyBorder());
		scrollLich.getViewport().setBackground(Color.WHITE);
		add(scrollLich, BorderLayout.CENTER);

		// Tự động giãn cột để full chiều ngang panel
		tableLich.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		// Cho phép bảng lấp đầy vùng hiển thị
		tableLich.setFillsViewportHeight(true);

		SwingUtilities.invokeLater(() -> {
			// Tính chiều rộng tổng trừ cột đầu tiên
			int totalWidth = scrollLich.getViewport().getWidth();
			if (totalWidth == 1)
				totalWidth = getWidth();

			// Cột đầu tiên chiếm
			int firstColWidth = (int) (totalWidth * 0.05);
			tableLich.getColumnModel().getColumn(0).setPreferredWidth(firstColWidth);

			// Các cột còn lại chia đều 80%
			int otherWidth = (int) ((totalWidth * 0.95) / 7);
			for (int i = 0; i < tableLich.getColumnCount(); i++) {
				tableLich.getColumnModel().getColumn(i).setPreferredWidth(otherWidth);
			}
		});

		// Note trạng thái
		JPanel pnNote = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
		add(pnNote, BorderLayout.SOUTH);
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

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Test - Xem lịch làm");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 700);
			frame.setLocationRelativeTo(null);
			frame.add(new XemLichLam());
			frame.setVisible(true);
		});
	}
}
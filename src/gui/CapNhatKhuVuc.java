package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import connectDB.ConnectDB;
import dao.KhuVucDAO;
import entity.KhuVuc;

public class CapNhatKhuVuc extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KhuVucDAO kv_dao;
	private JTextField txtMaKV;
	private JTextField txtTenKV;
	private JTextField txtViTri;
	private JButton btnHuy;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnThem;
	private JComboBox<String> cboTenKV;
	private JComboBox<String> cboViTri;
	private JScrollPane scrollKV;
	private DefaultTableModel modelKV;
	private JLabel lblViTri;
	private JLabel lblTenKV;
	private JTable tableKV;
	private JButton btnTim;

	public CapNhatKhuVuc() {
		ConnectDB.getInstance().connect();
		kv_dao = new KhuVucDAO();

		setLayout(new BorderLayout(10, 10));
		setBackground(Color.decode("#EAF1F9"));
		setBorder(new EmptyBorder(10, 10, 10, 10));

		// Title
		JLabel lblTitle = new JLabel("CẬP NHẬT KHU VỰC", SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setForeground(Color.decode("#333333"));
		add(lblTitle, BorderLayout.NORTH);

		// Form panel
		JPanel pnForm = new JPanel(new GridBagLayout());
		pnForm.setBackground(Color.WHITE);
		pnForm.setBorder(BorderFactory.createTitledBorder("Thêm khu vực"));
		add(pnForm, BorderLayout.WEST);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 15, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		int row = 0;

		// Nhập mã
		txtMaKV = new JTextField(20);
		txtMaKV.setEditable(false);
		txtMaKV.setText(kv_dao.taoMaKhuVucMoi());
		addFormField(pnForm, gbc, row++, "Mã khu vực: ", txtMaKV);

		// Nhập mã
		txtTenKV = new JTextField(20);
		addFormField(pnForm, gbc, row++, "Tên khu vực: ", txtTenKV);

		// Vị trí
		txtViTri = new JTextField();
		addFormField(pnForm, gbc, row++, "Vị trí: ", txtViTri);

		// Buttons
		JPanel pnButton = new JPanel(new GridLayout(1, 4, 10, 0));
		btnThem = createButton("Thêm", "img/add.png");
		btnXoa = createButton("Xóa", "img/delete.png");
		btnSua = createButton("Sửa", "img/edit.png");
		pnButton.add(btnThem);
		pnButton.add(btnXoa);
		pnButton.add(btnSua);
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.gridwidth = 2;
		pnForm.add(pnButton, gbc);

		// bảng
		JPanel pnCenter = new JPanel(new BorderLayout());
		add(pnCenter, BorderLayout.CENTER);

		// Tìm
		JPanel pnTim = new JPanel(new FlowLayout());
		pnCenter.add(pnTim, BorderLayout.NORTH);
		pnTim.add(lblViTri = new JLabel("Vị trí: "));
		cboViTri = new JComboBox<String>();
		cboViTri.addItem("Tất cả");
		ArrayList<String> dsViTri = kv_dao.layViTri();
		for (String viTri : dsViTri) {
			boolean tonTai = false;
			for (int i = 0; i < cboViTri.getItemCount(); i++) {
				if (cboViTri.getItemAt(i).equals(viTri)) {
					tonTai = true;
					break;
				}
			}
			if (!tonTai) {
				cboViTri.addItem(viTri);
			}
		}
		pnTim.add(cboViTri);

		pnTim.add(lblTenKV = new JLabel("Khu vực: "));
		cboTenKV = new JComboBox<String>();
		cboTenKV.addItem("Tất cả");
		ArrayList<String> dsKV = kv_dao.layTenKhuVuc();
		for (String kv : dsKV) {
			boolean tonTai = false;
			for (int i = 0; i < cboTenKV.getItemCount(); i++) {
				if (cboTenKV.getItemAt(i).equals(kv)) {
					tonTai = true;
					break;
				}
			}
			if (!tonTai) {
				cboTenKV.addItem(kv);
			}
		}
		pnTim.add(cboTenKV);

		btnTim = createButton("Tìm kiếm", "img/search.png");
		btnHuy = createButton("Làm mới", "img/refresh.png");
		pnTim.add(btnTim);
		pnTim.add(btnHuy);

		// table
		String[] tieuDe = { "STT", "Mã khu vực", "Tên khu vực", "Vị trí" };
		modelKV = new DefaultTableModel(tieuDe, 0);
		tableKV = new JTable(modelKV);
		// Đổi màu tiêu đề
		JTableHeader header = tableKV.getTableHeader();
		header.setBackground(Color.decode("#4CAF50"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Segoe UI", Font.BOLD, 14));

		tableKV.setSelectionBackground(new Color(51, 153, 255));
		tableKV.setSelectionForeground(Color.WHITE);

		tableKV.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				if (isSelected) {
					c.setBackground(new Color(51, 153, 255));
					c.setForeground(Color.WHITE);
				} else {
					if (row % 2 == 0) {
						c.setBackground(new Color(240, 240, 240));
					} else {
						c.setBackground(Color.WHITE);
					}
					c.setForeground(Color.BLACK);
				}

				return c;
			}
		});

		docDuLieuLenBang();
		scrollKV = new JScrollPane(tableKV);
		pnCenter.add(scrollKV, BorderLayout.CENTER);

		// Sư kiện
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnTim.addActionListener(this);
		btnTim.addActionListener(this);
		btnHuy.addActionListener(this);
		tableKV.addMouseListener(this);
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

	private void docDuLieuLenBang() {
		List<KhuVuc> ds = kv_dao.getAll();
		int stt = 1;
		for (KhuVuc kv : ds) {
			modelKV.addRow(new Object[] { stt++, kv.getMaKhuVuc(), kv.getTenKhuVuc(), kv.getViTri() });
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableKV.getSelectedRow();
		txtMaKV.setText(modelKV.getValueAt(row, 1).toString());
		txtTenKV.setText(modelKV.getValueAt(row, 2).toString());
		txtViTri.setText(modelKV.getValueAt(row, 3).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			them();
		}
		if (o.equals(btnXoa)) {
			xoa();
		}
		if (o.equals(btnSua)) {
			sua();
		}
		if (o.equals(btnHuy)) {
			System.out.println("ok");
			huy();
		}
		if (o.equals(btnTim)) {
			tim();
		}
		if (o.equals(btnHuy)) {
			refresh();
		}
	}

	private void refresh() {
		cboTenKV.setSelectedIndex(0);
		cboViTri.setSelectedIndex(0);
		List<KhuVuc> dsKV = kv_dao.getAll();
		huy();
		capNhatTable(dsKV);
	}

	private boolean kiemTraCuPhap() {
		String ten = txtTenKV.getText().trim();
		String viTri = txtViTri.getText().trim();

		if (!(ten.length() > 0 && ten.matches("^[A-ZÀ-Ỹa-zà-ỹ0-9\\s]+$"))) {
			JOptionPane.showMessageDialog(this, "Tên khu vực không hợp lệ!");
			return false;
		}
		if (!(viTri.length() > 0 && viTri.matches("^[A-ZÀ-Ỹa-zà-ỹ0-9\\s]+$"))) {
			JOptionPane.showMessageDialog(this, "Vị trí không hợp lệ!");
			return false;
		}
		return true;
	}

	private void them() {
		if (!kiemTraCuPhap()) {
			return;
		}
		String ma = txtMaKV.getText().trim();
		String ten = txtTenKV.getText().trim();
		String viTri = txtViTri.getText().trim();

		KhuVuc kv = new KhuVuc(ma, ten, viTri);
		;
		modelKV.insertRow(0, new Object[] { 0, kv.getMaKhuVuc(), kv.getTenKhuVuc(), kv.getViTri() });
		try {
			if (kv_dao.themKhuVuc(kv)) {
				JOptionPane.showMessageDialog(this, "Thêm ca làm thành công!");
				docDuLieuLenBang();
				huy();
			} else {
				JOptionPane.showConfirmDialog(this, "Thêm ca làm thất bại!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void tim() {
		String viTri = null;
		String tenKV = null;

		if (cboViTri.getSelectedIndex() > 0) {
			viTri = cboViTri.getSelectedItem().toString().trim();
		}
		if (cboTenKV.getSelectedIndex() > 0) {
			tenKV = cboTenKV.getSelectedItem().toString().trim();
		}

		List<KhuVuc> dsKV;

		if (viTri == null && tenKV == null) {
			dsKV = kv_dao.getAll();
		} else {
			dsKV = kv_dao.loc(tenKV, viTri);
			if (dsKV == null || dsKV.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
				dsKV = kv_dao.getAll();
			}

		}
		capNhatTable(dsKV);
	}

	private void sua() {
		int row = tableKV.getSelectedRow();

		if (row < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn khu vực cần cập nhật lại thông tin!");
			return;
		}

		if (!kiemTraCuPhap()) {// kiểm tra cú pháp
			return;
		}

		String ma = txtMaKV.getText().trim();
		String ten = txtTenKV.getText().trim();
		String viTri = txtViTri.getText().trim();

		KhuVuc khuVuc = new KhuVuc(ma, ten, viTri);
		if (kv_dao.suaKhuVuc(khuVuc)) {
			JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
			tableKV.setValueAt(ten, row, 2);
			tableKV.setValueAt(viTri, row, 3);
		} else {
			JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
		}
		huy();
	}

	private void xoa() {
		int row = tableKV.getSelectedRow();
		if (row >= 0) {
			String ma = (String) tableKV.getValueAt(row, 1);
			int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khu vực có mã " + ma + " không?", "Xóa",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (chon == JOptionPane.YES_OPTION) {
				if (kv_dao.xoaKhuVuc(ma)) {
					modelKV.removeRow(row);
					JOptionPane.showMessageDialog(this, "Xóa thành công!");
					huy();
				}
			} else {
				JOptionPane.showMessageDialog(this, "Xóa thất bại!");
			}
		}
	}

	private void huy() {
		txtMaKV.setText(kv_dao.taoMaKhuVucMoi());
		txtTenKV.setText("");
		txtViTri.setText("");
		tableKV.clearSelection();
	}

	private void capNhatTable(List<KhuVuc> danhSach) {
		modelKV.setRowCount(0); // Xóa tất cả các hàng hiện có trong bảng
		int stt = 1;
		for (KhuVuc kv : danhSach) {
			modelKV.addRow(new Object[] { stt++, kv.getMaKhuVuc(), kv.getTenKhuVuc(), kv.getViTri() });
		}
	}

	private JButton createButton(String text, String iconPath) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 14));
		button.setPreferredSize(new Dimension(130, 38));
		button.setBackground(Color.decode("#4CAF50"));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));

		if (iconPath != null) {
			try {
				ImageIcon icon = new ImageIcon(iconPath);
				Image img = icon.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
				button.setIcon(new ImageIcon(img));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return button;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Test - Cập nhật khu vực");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 700);
			frame.setLocationRelativeTo(null);
			frame.add(new CapNhatKhuVuc());
			frame.setVisible(true);
		});
	}

}

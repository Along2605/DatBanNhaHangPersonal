package gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import connectDB.ConnectDB;
import dao.BanAnDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.KhuVucDAO;
import dao.LoaiBanDAO;
import entity.BanAn;
import entity.LoaiBan;
import util.Session;

public class SoDoBanAn extends JPanel {

	private JComboBox<String> cbLoaiBan;
	private JComboBox<String> cbViTri;
	private JComboBox<String> cbTrangThai;

	private BanAnDAO ban_DAO;
	private KhuVucDAO khu_DAO;
	private LoaiBanDAO loaiBan_DAO;
	private ChiTietHoaDonDAO ctHD_DAO;
	private HoaDonDAO hoaDon_DAO;

	private JPanel pnDanhSachBan; // panel chính chứa sơ đồ bàn
	private BanAn banCanChuyen = null;
	private JLabel lblTitle;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnLamMoi;

	public SoDoBanAn() {
		try {
			ConnectDB.getInstance().connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Không thể kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		ban_DAO = new BanAnDAO();
		loaiBan_DAO = new LoaiBanDAO();
		khu_DAO = new KhuVucDAO();
		hoaDon_DAO = new HoaDonDAO();
		ctHD_DAO = new ChiTietHoaDonDAO();

		setLayout(new BorderLayout());

		initTopPanel();
		initDanhSachBan();
	}

	private void initTopPanel() {
		JPanel pnTop = new JPanel();
		pnTop.setLayout(new BoxLayout(pnTop, BoxLayout.Y_AXIS));
		add(pnTop, BorderLayout.NORTH);

		lblTitle = new JLabel("SƠ ĐỒ BÀN ĂN", JLabel.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
		lblTitle.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pnTop.add(lblTitle);

		JPanel pnLoc = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
		pnTop.add(pnLoc);

		// Loại bàn
		List<LoaiBan> dsLoaiBan = loaiBan_DAO.getAllLoaiBan();
		LinkedHashSet<String> tenLoaiBanSet = new LinkedHashSet<>();
		tenLoaiBanSet.add("--Tất cả--");
		for (LoaiBan loai : dsLoaiBan) {
			tenLoaiBanSet.add(loai.getTenLoaiBan());
		}
		cbLoaiBan = new JComboBox<>(tenLoaiBanSet.toArray(new String[0]));
		pnLoc.add(new JLabel("Loại bàn:"));
		pnLoc.add(cbLoaiBan);

		// Vị trí
		ArrayList<String> dsKhuVuc = khu_DAO.layViTri();
		List<String> dsKhuVucArr = new ArrayList<>();
		dsKhuVucArr.add("--Tất cả--");
		dsKhuVucArr.addAll(new LinkedHashSet<>(dsKhuVuc));
		cbViTri = new JComboBox<>(dsKhuVucArr.toArray(new String[0]));
		pnLoc.add(new JLabel("Vị trí:"));
		pnLoc.add(cbViTri);

		// Trạng thái
		List<BanAn> dsBan = ban_DAO.getAllBanAn();
		LinkedHashSet<String> setStatus = new LinkedHashSet<>();
		setStatus.add("--Tất cả--");
		for (BanAn b : dsBan) {
			setStatus.add(b.getTrangThai());
		}
		cbTrangThai = new JComboBox<>(setStatus.toArray(new String[0]));
		pnLoc.add(new JLabel("Trạng thái:"));
		pnLoc.add(cbTrangThai);

		// Text nhập
		JLabel lblTim = new JLabel("Nhập");
		txtTim = new JTextField(10);
		btnTim = new JButton("Tìm");
		btnLamMoi = new JButton("Làm mới");
		pnLoc.add(new JLabel("Nhập"));
		pnLoc.add(txtTim);
		pnLoc.add(btnTim);
		pnLoc.add(btnLamMoi);
		
		btnTim.addActionListener(e -> {
			String loaiBan = (String) cbLoaiBan.getSelectedItem();
			String viTri = (String) cbViTri.getSelectedItem();
			String trangThai = (String) cbTrangThai.getSelectedItem();
			String tim = txtTim.getText().trim();

			List<BanAn> dsBanAn = ban_DAO.getAllBanAn();
			List<BanAn> ketQua = new ArrayList<BanAn>();

			for (BanAn b : dsBanAn) {
				boolean ok = true;
				if (!"--Tất cả--".equals(loaiBan) && !b.getLoaiBan().getTenLoaiBan().equals(loaiBan)) {
					ok = false;
				}
				if (!"--Tất cả--".equals(viTri) && !b.getKhuVuc().getViTri().equals(viTri)) {
					ok = false;
				}
				if (!"--Tất cả--".equals(trangThai) && !b.getTrangThai().equals(trangThai)) {
					ok = false;
				}
				if (!tim.isEmpty()
						&& !(b.getMaBan().toLowerCase().contains(tim) || b.getTenBan().toLowerCase().contains(tim))) {
					ok = false;
				}
				if (ok)
					ketQua.add(b);
			}

			pnDanhSachBan.removeAll();
			Map<String, List<BanAn>> kVBanAn = new LinkedHashMap<>();
			for (BanAn b : ketQua) {
				kVBanAn.computeIfAbsent(b.getKhuVuc().getViTri(), k -> new ArrayList<>()).add(b);
			}

			List<String> sortedKeys = new ArrayList<>(kVBanAn.keySet());
			sortedKeys.sort((a, b1) -> {
				try {
					int numA = Integer.parseInt(a.replaceAll("\\D+", ""));
					int numB = Integer.parseInt(b1.replaceAll("\\D+", ""));
					return Integer.compare(numA, numB);
				} catch (Exception ex) {
					return a.compareTo(b1);
				}
			});

//			for (String viTriKey : sortedKeys) {
//				JPanel pnViTri = new JPanel(new GridLayout(0, 3, 10, 10));
//				pnViTri.setBorder(BorderFactory.createTitledBorder(viTriKey));
//				for (BanAn b : kVBanAn.get(viTriKey)) {
//					pnViTri.add(taoBan(b.getKhuVuc().getTenKhuVuc(), b.getTenBan(), b.getTrangThai(),
//							b.getLoaiBan().getTenLoaiBan(), b));
//				}
//				pnDanhSachBan.add(pnViTri);
//				pnDanhSachBan.add(Box.createRigidArea(new Dimension(0, 10)));
//			}

			pnDanhSachBan.revalidate();
			pnDanhSachBan.repaint();
		});
	
		btnLamMoi.addActionListener(e -> {
			cbLoaiBan.setSelectedIndex(0);
			cbTrangThai.setSelectedIndex(0);
			cbViTri.setSelectedIndex(0);
			txtTim.setText("");
			loadBanAn();
		});
	}

	private void initDanhSachBan() {
		pnDanhSachBan = new JPanel();
		pnDanhSachBan.setLayout(new BoxLayout(pnDanhSachBan, BoxLayout.Y_AXIS));
		JScrollPane scroll = new JScrollPane(pnDanhSachBan);
		add(scroll, BorderLayout.CENTER);

		loadBanAn();
	}

	public void loadBanAn() {
		pnDanhSachBan.removeAll();

		List<BanAn> dsBan = ban_DAO.getAllBanAn();
		Map<String, List<BanAn>> kVBanAn = new LinkedHashMap<>();
		for (BanAn b : dsBan) {
			kVBanAn.computeIfAbsent(b.getKhuVuc().getViTri(), k -> new ArrayList<>()).add(b);
		}

		List<String> sortedKeys = new ArrayList<>(kVBanAn.keySet());
		sortedKeys.sort((a, b) -> {
			try {
				int numA = Integer.parseInt(a.replaceAll("\\D+", ""));
				int numB = Integer.parseInt(b.replaceAll("\\D+", ""));
				return Integer.compare(numA, numB);
			} catch (Exception e) {
				return a.compareTo(b);
			}
		});

		for (String viTri : sortedKeys) {
			JPanel pnViTri = new JPanel(new GridLayout(0, 3, 10, 10));
			pnViTri.setBorder(BorderFactory.createTitledBorder(viTri));
			for (BanAn b : kVBanAn.get(viTri)) {
				pnViTri.add(taoBan(b.getKhuVuc().getTenKhuVuc(), b.getTenBan(), b.getTrangThai(),
						b.getLoaiBan().getTenLoaiBan(), b));
			}
			pnDanhSachBan.add(pnViTri);
			pnDanhSachBan.add(Box.createRigidArea(new Dimension(0, 10)));
		}

		pnDanhSachBan.revalidate();
		pnDanhSachBan.repaint();
	}

	private JPanel taoBan(String viTri, String tenBan, String trangThai, String loaiBan, BanAn ban) {
		JPanel pnBan = new JPanel(new BorderLayout());
		Dimension size = new Dimension(200, 160); // kích thước chuẩn
	    pnBan.setPreferredSize(size);
	    pnBan.setMaximumSize(size);
	    pnBan.setMinimumSize(size);
//		pnBan.setPreferredSize(new Dimension(200, 160));
		pnBan.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		pnBan.setBackground(Color.WHITE);

		JLabel lblTenBan = new JLabel(tenBan + " • " + viTri);
		lblTenBan.setFont(new Font("Segoe UI", Font.BOLD, 16));
		JLabel lblTrangThai = new JLabel(trangThai);
		lblTrangThai.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JPanel pnTop = new JPanel(new BorderLayout());
		pnTop.setOpaque(false);
		pnTop.add(lblTenBan, BorderLayout.WEST);
		pnTop.add(lblTrangThai, BorderLayout.EAST);

		JLabel lblLoai = new JLabel("Loại: " + loaiBan);
		lblLoai.setFont(new Font("Segoe UI", Font.BOLD, 13));
		JPanel pnBottom = new JPanel();
		pnBottom.setOpaque(false);
		pnBottom.add(lblLoai);

		pnBan.add(pnTop, BorderLayout.NORTH);
		pnBan.add(pnBottom, BorderLayout.SOUTH);

		Color bg = switch (trangThai) {
		case "Trống" -> new Color(255, 240, 240);
		case "Đang phục vụ" -> new Color(235, 255, 235);
		case "Đã đặt" -> new Color(240, 240, 255);
		case "Bảo trì" -> new Color(255, 250, 230);
		default -> new Color(240, 240, 240);
		};
		pnBan.setBackground(bg);

		pnBan.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        if (banCanChuyen == null) {
		            // Chưa chọn bàn gốc → mở dialog bình thường hoặc cho chọn làm bàn gốc
		            new DialogChiTietBanAn((Frame) SwingUtilities.getWindowAncestor(pnBan), ban, selectedBan -> {
		                banCanChuyen = selectedBan;
		                lblTitle.setText("CHUYỂN/GỘP BÀN - Đã chọn: " + banCanChuyen.getTenBan() + " → Chọn bàn đích");
		                loadBanAn(); // highlight bàn đang chọn
		            }).setVisible(true);

		        } else {
		            // Đã chọn bàn gốc → giờ click vào bàn khác
		            if (ban.equals(banCanChuyen)) {
		                banCanChuyen = null;
		                lblTitle.setText("SƠ ĐỒ BÀN ĂN");
		                loadBanAn();
		                return;
		            }

		            if ("Trống".equals(ban.getTrangThai())) {
		                // Chuyển sang bàn trống
		                int confirm = JOptionPane.showConfirmDialog(pnBan,
		                    "Chuyển từ bàn " + banCanChuyen.getTenBan() + " sang bàn " + ban.getTenBan() + "?",
		                    "Xác nhận chuyển bàn", JOptionPane.YES_NO_OPTION);
		                if (confirm == JOptionPane.YES_OPTION) {
		                    if (ban_DAO.chuyenBan(banCanChuyen.getMaBan(), ban.getMaBan())) {
		                        JOptionPane.showMessageDialog(pnBan, "Chuyển bàn thành công!");
		                    } else {
		                        JOptionPane.showMessageDialog(pnBan, "Chuyển bàn thất bại!");
		                    }
		                    banCanChuyen = null;
		                    lblTitle.setText("SƠ ĐỒ BÀN ĂN");
		                    loadBanAn();
		                }
		            } 
		            else if ("Đang sử dụng".equals(ban.getTrangThai())) {
		                // Gộp bàn: bàn gốc (banCanChuyen) → gộp vào bàn đích (ban)
		                int confirm = JOptionPane.showConfirmDialog(pnBan,
		                    "Gộp bàn " + banCanChuyen.getTenBan() + " vào bàn " + ban.getTenBan() + "?\n" +
		                    "→ Tất cả món ăn sẽ được chuyển sang bàn " + ban.getTenBan() + "\n" +
		                    "→ Bàn " + banCanChuyen.getTenBan() + " sẽ trống",
		                    "Xác nhận gộp bàn", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		                if (confirm == JOptionPane.YES_OPTION) {
		                    String maBanCu = banCanChuyen.getMaBan();
		                    String maBanMoi = ban.getMaBan();

		                    try {
		                        // 1. Lấy mã hóa đơn của 2 bàn
		                        String maHDCu = ban_DAO.layMaHDTuBan(maBanCu);
		                        String maHDDich = ban_DAO.layMaHDTuBan(maBanMoi);

		                        if (maHDCu == null) {
		                            JOptionPane.showMessageDialog(pnBan, "Bàn gốc không có hóa đơn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }
		                        if (maHDDich == null) {
		                            JOptionPane.showMessageDialog(pnBan, "Bàn đích không có hóa đơn để gộp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        // 2. Gộp chi tiết hóa đơn từ bàn cũ → bàn mới
		                        boolean gopThanhCong = ctHD_DAO.gopHoaDon(maHDCu, maHDDich, maBanCu, maBanMoi, Session.getMaNhanVienDangNhap());
		                        if (!gopThanhCong) {
		                            JOptionPane.showMessageDialog(pnBan, "Gộp chi tiết hóa đơn thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		                            return;
		                        }

		                        // 3. Xóa hóa đơn cũ
		                        hoaDon_DAO.xoaHoaDon(maHDCu, Session.getMaNhanVienDangNhap());

		                        // 4. Cập nhật trạng thái bàn
		                        ban_DAO.capNhatTrangThaiBan(maBanCu, "Trống");
		                        ban_DAO.capNhatTrangThaiBan(maBanMoi, "Đang sử dụng");

		                        // cap nhat lai ma ban trong hoa don đích
		                        // hoaDon_DAO.capNhatMaBanHoaDon(maHDDich, maBanMoi);

		                        JOptionPane.showMessageDialog(pnBan,
		                            "Gộp bàn thành công!\n" +
		                            "→ Bàn " + banCanChuyen.getTenBan() + " đã trống\n" +
		                            "→ Tất cả món đã chuyển sang bàn " + ban.getTenBan(),
		                            "Thành công", JOptionPane.INFORMATION_MESSAGE);

		                        // Reset trạng thái chọn bàn
		                        banCanChuyen = null;
		                        lblTitle.setText("SƠ ĐỒ BÀN ĂN");

		                        // Cập nhật lại giao diện
		                        loadBanAn();

		                    } catch (Exception ex) {
		                        ex.printStackTrace();
		                        JOptionPane.showMessageDialog(pnBan, "Lỗi khi gộp bàn: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		            }
		        }
		    }
		});

		return pnBan;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Sơ đồ bàn ăn");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(1200, 700);
			frame.setLocationRelativeTo(null);
			frame.add(new SoDoBanAn());
			frame.setVisible(true);
		});
	}
}

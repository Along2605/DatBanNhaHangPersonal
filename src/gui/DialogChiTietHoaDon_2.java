package gui;

import entity.HoaDon;
import entity.ChiTietHoaDon;
import dao.ChiTietHoaDonDAO;
import util.InHoaDonPDF;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DialogChiTietHoaDon_2 extends JDialog {

    private HoaDon hoaDon;
    private ChiTietHoaDonDAO chiTietDAO = new ChiTietHoaDonDAO();
    private DecimalFormat df = new DecimalFormat("#,##0 đ");
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public DialogChiTietHoaDon_2(HoaDon hd) {
        this.hoaDon = hd;
        setTitle("Chi tiết hóa đơn - " + hd.getMaHoaDon());
        setSize(900, 650);
        setLocationRelativeTo(null);
        setModal(true);
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(Color.WHITE);
        add(panel, BorderLayout.CENTER);

        // Thông tin hóa đơn
        JPanel panelInfo = new JPanel(new GridLayout(7, 2, 10, 8));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        panelInfo.add(new JLabel("Mã hóa đơn:"));
        panelInfo.add(new JLabel(hoaDon.getMaHoaDon()));

        panelInfo.add(new JLabel("Bàn:"));
        panelInfo.add(new JLabel(hoaDon.getBanAn() != null ? hoaDon.getBanAn().getMaBan() : "Mang đi"));

        panelInfo.add(new JLabel("Khách hàng:"));
        panelInfo.add(new JLabel(hoaDon.getKhachHang() != null ? hoaDon.getKhachHang().getHoTen() : "Khách lẻ"));

        panelInfo.add(new JLabel("Nhân viên:"));
        panelInfo.add(new JLabel(hoaDon.getNhanVien() != null ? hoaDon.getNhanVien().getHoTen() : "N/A"));

        panelInfo.add(new JLabel("Ngày lập:"));
        panelInfo.add(new JLabel(hoaDon.getNgayLapHoaDon().format(dtf)));

        panelInfo.add(new JLabel("Trạng thái:"));
        JLabel lblTrangThai = new JLabel(hoaDon.getTrangThai());
        lblTrangThai.setForeground("Đã thanh toán".equals(hoaDon.getTrangThai()) ? Color.BLUE : Color.RED);
        lblTrangThai.setFont(lblTrangThai.getFont().deriveFont(Font.BOLD));
        panelInfo.add(lblTrangThai);

        panelInfo.add(new JLabel("Tổng tiền:"));
        JLabel lblTongTien = new JLabel(df.format(hoaDon.getTongTien()));
        lblTongTien.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTongTien.setForeground(new Color(231, 76, 60));
        panelInfo.add(lblTongTien);

        panel.add(panelInfo, BorderLayout.NORTH);

        // Bảng chi tiết món
        String[] cols = {"STT", "Tên món", "SL", "Đơn giá", "Thành tiền"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.getTableHeader().setBackground(new Color(52, 152, 219));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));

        List<ChiTietHoaDon> dsCT = chiTietDAO.layDanhSachTheoHoaDon(hoaDon.getMaHoaDon());
        int stt = 1;
        for (ChiTietHoaDon ct : dsCT) {
            model.addRow(new Object[]{
                    stt++,
                    ct.getMonAn().getTenMon(),
                    ct.getSoLuong(),
                    df.format(ct.getDonGia()),
                    df.format(ct.getThanhTien())
            });
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(850, 300));
        panel.add(scroll, BorderLayout.CENTER);

        // Nút in hóa đơn
        JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnIn = new JButton("In hóa đơn");
        btnIn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIn.setBackground(new Color(46, 204, 113));
        btnIn.setForeground(Color.WHITE);
        btnIn.setPreferredSize(new Dimension(140, 40));
        btnIn.addActionListener(e -> InHoaDonPDF.xuatHoaDon(hoaDon));
        panelBtn.add(btnIn);

        JButton btnDong = new JButton("Đóng");
        btnDong.addActionListener(e -> dispose());
        panelBtn.add(btnDong);

        add(panelBtn, BorderLayout.SOUTH);
    }
}
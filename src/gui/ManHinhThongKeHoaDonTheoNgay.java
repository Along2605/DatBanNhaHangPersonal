package gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import dao.HoaDonDAO;
import entity.HoaDon;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import util.InHoaDonPDF; // Class in hóa đơn riêng

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManHinhThongKeHoaDonTheoNgay extends JPanel {
    private static final long serialVersionUID = 1L;

    private JLabel lblDoanhThu_TongTien, lblHoaDonTaiBan_SoLuong, lblHoaDonMangDi_SoLuong;
    private JTextField textFieldTongSoHoaDon, textFieldTongTienHoaDon;
    private DefaultTableModel model;
    private JTable table;
    private JPanel panelBieuDo;
    private DatePicker datePicker;
    private LocalDate ngayChon = LocalDate.now();
    private Map<String, double[]> mapHienTai;

    public ManHinhThongKeHoaDonTheoNgay() {
        setPreferredSize(new Dimension(1366, 768));
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setLayout(null);
        add(contentPane, BorderLayout.CENTER);

        // === TIÊU ĐỀ ===
        JLabel lblTieuDe = new JLabel("THỐNG KÊ HÓA ĐƠN THEO NGÀY", SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 27));
        lblTieuDe.setBounds(460, 0, 470, 59);
        contentPane.add(lblTieuDe);

        // === PANEL LỌC THEO NGÀY ===
        JPanel panelLocNgay = new JPanel();
        panelLocNgay.setBounds(49, 57, 281, 95);
        panelLocNgay.setLayout(null);
        contentPane.add(panelLocNgay);

        JLabel lblNgay = new JLabel("Chọn ngày");
        lblNgay.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNgay.setBounds(10, 10, 100, 21);
        panelLocNgay.add(lblNgay);

        DatePickerSettings st = new DatePickerSettings();
        st.setFormatForDatesCommonEra("dd/MM/yyyy");
        datePicker = new DatePicker(st);

        LocalDate ngayNhieuHDNhat = HoaDonDAO.layNgayCoNhieuHoaDonNhat();
        if (ngayNhieuHDNhat != null) {
            datePicker.setDate(ngayNhieuHDNhat);
        } else {
            datePicker.setDateToToday();
        }
        datePicker.setBounds(10, 40, 215, 25);
        panelLocNgay.add(datePicker);

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnThongKe.setBounds(150, 70, 100, 25);
        panelLocNgay.add(btnThongKe);

        // === TẠO CÁC PANEL THỐNG KÊ ===
        lblDoanhThu_TongTien = createStatPanel(contentPane, 373, "Doanh thu", "Theo ngày", "VNĐ");
        lblHoaDonTaiBan_SoLuong = createStatPanel(contentPane, 678, "Hóa đơn tại bàn", "Theo ngày", "đơn");
        lblHoaDonMangDi_SoLuong = createStatPanel(contentPane, 983, "Hóa đơn mang đi", "Theo ngày", "đơn");

        // === BIỂU ĐỒ ===
        panelBieuDo = new JPanel(new BorderLayout());
        panelBieuDo.setBounds(49, 174, 620, 357);
        contentPane.add(panelBieuDo);

        // === NÚT ===
        JButton btnXemChiTiet = createButton("Xem chi tiết", 129, 542, e -> xemChiTiet());
        contentPane.add(btnXemChiTiet);

        JButton btnXuatExcel = createButton("Xuất file Excel", 373, 542, e -> xuatExcel());
        btnXuatExcel.setBackground(new Color(46, 204, 113));
        btnXuatExcel.setForeground(Color.WHITE);
        btnXuatExcel.setFocusPainted(false);
        contentPane.add(btnXuatExcel);

        // NÚT IN HÓA ĐƠN MỚI – DÙNG CLASS RIÊNG
        JButton btnInHoaDon = createButton("In hóa đơn", 617, 542, e -> inHoaDonPDF());
        btnInHoaDon.setBackground(new Color(231, 76, 60));
        btnInHoaDon.setForeground(Color.WHITE);
        contentPane.add(btnInHoaDon);

        // === TỔNG HỢP ===
        JLabel lblTongHD = new JLabel("Tổng số hóa đơn");
        lblTongHD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTongHD.setBounds(718, 174, 115, 20);
        contentPane.add(lblTongHD);

        JLabel lblTongTien = new JLabel("Tổng tiền hóa đơn");
        lblTongTien.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTongTien.setBounds(718, 205, 115, 20);
        contentPane.add(lblTongTien);

        textFieldTongSoHoaDon = createTextField(863, 174);
        textFieldTongTienHoaDon = createTextField(863, 205);
        contentPane.add(textFieldTongSoHoaDon);
        contentPane.add(textFieldTongTienHoaDon);

        // === BẢNG ===
        JPanel panelTable = new JPanel();
        panelTable.setBounds(688, 236, 631, 357);
        panelTable.setLayout(null);
        contentPane.add(panelTable);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 5, 611, 341);
        panelTable.add(scrollPane);

        String[] cols = {"Khung giờ", "Tổng số HĐ", "Tổng tiền HĐ"};
        model = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(model);
        scrollPane.setViewportView(table);

        // === SỰ KIỆN NÚT THỐNG KÊ ===
        btnThongKe.addActionListener(e -> {
            LocalDate selected = datePicker.getDate();
            if (selected == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.ngayChon = selected;
            capNhatToanBo(selected);
        });

        // Khởi tạo mặc định
        capNhatToanBo(LocalDate.now());
    }

    // ============================== IN HÓA ĐƠN ==============================
    private void inHoaDonPDF() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khung giờ trong bảng!", "Chưa chọn", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String khungGio = (String) model.getValueAt(row, 0);
        List<HoaDon> dsHD = HoaDonDAO.layDanhSachHoaDonTheoNgay(ngayChon)
                .stream()
                .filter(hd -> layKhungGio(hd.getNgayLapHoaDon().getHour()).equals(khungGio))
                .toList();

        if (dsHD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn nào trong khung giờ này!");
            return;
        }

        for (HoaDon hd : dsHD) {
            InHoaDonPDF.xuatHoaDon(hd);
        }

        JOptionPane.showMessageDialog(this, "Đã in thành công " + dsHD.size() + " hóa đơn!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    private String layKhungGio(int hour) {
        if (hour >= 8 && hour < 12) return "8h-11h";
        if (hour >= 12 && hour < 16) return "12h-15h";
        if (hour >= 16 && hour < 20) return "16h-19h";
        if (hour >= 20 && hour < 23) return "20h-22h";
        return null;
    }

    // ============================== CÁC HÀM CŨ (GIỮ NGUYÊN) ==============================
    private JLabel createStatPanel(JPanel parent, int x, String title, String sub, String unit) {
        JPanel panel = new JPanel();
        panel.setBounds(x, 57, 281, 95);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitle.setBounds(10, 11, 150, 21);
        panel.add(lblTitle);

        JLabel lblValue = new JLabel("0");
        lblValue.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblValue.setBounds(10, 35, 150, 21);
        panel.add(lblValue);

        JLabel lblSub = new JLabel(sub);
        lblSub.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblSub.setBounds(218, 77, 63, 14);
        panel.add(lblSub);

        JLabel lblUnit = new JLabel(unit);
        lblUnit.setForeground(new Color(0, 0, 0, 150));
        lblUnit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUnit.setBounds(10, 65, 63, 14);
        panel.add(lblUnit);

        parent.add(panel);
        return lblValue;
    }

    private JButton createButton(String text, int x, int y, java.awt.event.ActionListener listener) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBounds(x, y, 179, 49);
        btn.setFocusPainted(false);
        btn.addActionListener(listener);
        return btn;
    }

    private JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setEditable(false);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tf.setBounds(x, y, 150, 20);
        return tf;
    }

    private void capNhatToanBo(LocalDate ngay) {
        List<HoaDon> ds = HoaDonDAO.layDanhSachHoaDonTheoNgay(ngay);
        Map<String, double[]> ketQua = ds.isEmpty() ? new HashMap<>() : tinhToanTheoKhungGio(ds);
        this.mapHienTai = ketQua;
        capNhatThongKe(ds, ketQua);
        capNhatBieuDo(ketQua, ngay);
        capNhatBang(ketQua);
    }

    private Map<String, double[]> tinhToanTheoKhungGio(List<HoaDon> ds) {
        Map<String, double[]> map = new HashMap<>();
        String[] khung = {"8h-11h", "12h-15h", "16h-19h", "20h-22h"};
        for (String k : khung) map.put(k, new double[]{0, 0});
        for (HoaDon hd : ds) {
            int hour = hd.getNgayLapHoaDon().getHour();
            String khungGio = layKhungGio(hour);
            if (khungGio != null) {
                double[] v = map.get(khungGio);
                v[0]++;
                v[1] += hd.getTongTien();
            }
        }
        return map;
    }

    private void capNhatThongKe(List<HoaDon> ds, Map<String, double[]> map) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
        double tongTien = ds.stream().mapToDouble(HoaDon::getTongTien).sum();
        int mangDi = (int) ds.stream().filter(hd -> hd.getKhachHang() == null).count();
        int taiBan = ds.size() - mangDi;
        lblDoanhThu_TongTien.setText(df.format(tongTien));
        lblHoaDonTaiBan_SoLuong.setText(String.valueOf(taiBan));
        lblHoaDonMangDi_SoLuong.setText(String.valueOf(mangDi));
        textFieldTongSoHoaDon.setText(String.valueOf(ds.size()));
        textFieldTongTienHoaDon.setText(df.format(tongTien) + " VNĐ");
    }

    private void capNhatBieuDo(Map<String, double[]> map, LocalDate ngay) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] thuTuDung = {"8h-11h", "12h-15h", "16h-19h", "20h-22h"};
        for (String khung : thuTuDung) {
            double[] v = map.getOrDefault(khung, new double[]{0, 0});
            double doanhThuTrieu = v[1] / 1_000_000.0;
            dataset.addValue(doanhThuTrieu, "Doanh thu", khung);
        }
        JFreeChart chart = ChartFactory.createBarChart(
                "DOANH THU THEO KHUNG GIỜ - " + ngay.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                "Khung giờ", "Doanh thu (triệu VNĐ)", dataset, PlotOrientation.VERTICAL, true, true, false);
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 12));
        chart.getCategoryPlot().getDomainAxis().setCategoryMargin(0.2);

        ChartPanel chartPanel = new ChartPanel(chart);
        panelBieuDo.removeAll();
        panelBieuDo.add(chartPanel, BorderLayout.CENTER);
        panelBieuDo.revalidate();
        panelBieuDo.repaint();
    }

    private void capNhatBang(Map<String, double[]> map) {
        model.setRowCount(0);
        java.text.DecimalFormat df = new java.text.DecimalFormat("#,###");
        String[] order = {"8h-11h", "12h-15h", "16h-19h", "20h-22h"};
        for (String k : order) {
            double[] v = map.getOrDefault(k, new double[]{0, 0});
            model.addRow(new Object[]{k, (int) v[0], df.format(v[1]) + " VNĐ"});
        }
    }

    private void xemChiTiet() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khung giờ!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String khungGio = (String) model.getValueAt(row, 0);
        List<HoaDon> ds = HoaDonDAO.layDanhSachHoaDonTheoNgay(ngayChon)
                .stream()
                .filter(hd -> layKhungGio(hd.getNgayLapHoaDon().getHour()).equals(khungGio))
                .toList();
        if (ds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có hóa đơn trong khung giờ này!", "Thông tin", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        new DialogChiTietHoaDonTheoNgay(ds).setVisible(true);
    }

    private void xuatExcel() {
        // Hàm xuất Excel giữ nguyên như bạn đã có
        if (mapHienTai == null || mapHienTai.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu để xuất Excel!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JFileChooser fc = new JFileChooser();
        String fileName = "ThongKe_HoaDon_Ngay_" + ngayChon.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ".xlsx";
        fc.setSelectedFile(new File(fileName));
        if (fc.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;

        File file = fc.getSelectedFile();
        if (!file.getName().endsWith(".xlsx")) {
            file = new File(file.getAbsolutePath() + ".xlsx");
        }

        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Thong ke");

            XSSFFont titleFont = (XSSFFont) wb.createFont();
            titleFont.setFontName("Segoe UI Semibold"); titleFont.setFontHeightInPoints((short) 16); titleFont.setBold(true);

            CellStyle titleStyle = wb.createCellStyle();
            titleStyle.setFont(titleFont); titleStyle.setAlignment(HorizontalAlignment.CENTER);

            Row row0 = sheet.createRow(0);
            Cell cell0 = row0.createCell(0);
            cell0.setCellValue("BÁO CÁO DOANH THU THEO KHUNG GIỜ - " + ngayChon.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            cell0.setCellStyle(titleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));

            Row header = sheet.createRow(2);
            String[] cols = {"Khung giờ", "Số HĐ", "Doanh thu"};
            for (int i = 0; i < cols.length; i++) {
                Cell c = header.createCell(i);
                c.setCellValue(cols[i]);
                c.setCellStyle(wb.createCellStyle());
            }

            int r = 3;
            String[] order = {"8h-11h", "12h-15h", "16h-19h", "20h-22h"};
            for (String k : order) {
                double[] v = mapHienTai.getOrDefault(k, new double[]{0, 0});
                Row row = sheet.createRow(r++);
                row.createCell(0).setCellValue(k);
                row.createCell(1).setCellValue((int) v[0]);
                row.createCell(2).setCellValue(v[1]);
            }

            double tong = mapHienTai.values().stream().mapToDouble(v -> v[1]).sum();
            Row totalRow = sheet.createRow(r);
            totalRow.createCell(1).setCellValue("TỔNG CỘNG");
            totalRow.createCell(2).setCellValue(tong);

            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
                sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 1000);
            }

            try (FileOutputStream out = new FileOutputStream(file)) {
                wb.write(out);
            }
            JOptionPane.showMessageDialog(this, "Xuất Excel thành công!\n" + file.getAbsolutePath());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xuất Excel: " + ex.getMessage());
        }
    }
}
package util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import dao.ChiTietHoaDonDAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;

public class InHoaDonPDF {

    private static final ChiTietHoaDonDAO chiTietDAO = new ChiTietHoaDonDAO();
    private static final DecimalFormat df = new DecimalFormat("#,##0");
    private static final DateTimeFormatter dtfNgay = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter dtfGio = DateTimeFormatter.ofPattern("HH:mm");

    public static void xuatHoaDon(HoaDon hoaDon) {
        if (hoaDon == null) {
            JOptionPane.showMessageDialog(null, "Hóa đơn không hợp lệ!");
            return;
        }

        JFileChooser fc = new JFileChooser("note");
        fc.setSelectedFile(new File("HoaDon_" + hoaDon.getMaHoaDon() + ".pdf"));
        if (fc.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = fc.getSelectedFile();
        if (!file.getName().toLowerCase().endsWith(".pdf")) {
            file = new File(file.getAbsolutePath() + ".pdf");
        }

        Document document = new Document(PageSize.A4, 36, 36, 50, 50);
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            // Font tiếng Việt (dùng Arial có sẵn trên Windows → không cần file ttf)
            BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font titleFont = new Font(bf, 18, Font.BOLD);
            Font headerFont = new Font(bf, 13, Font.BOLD);
            Font normalFont = new Font(bf, 11);
            Font bigBold = new Font(bf, 14, Font.BOLD);

            // Tiêu đề
            Paragraph title = new Paragraph(" SEOUL CUISINE\nHÓA ĐƠN THANH TOÁN", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(8);
            document.add(title);

            Paragraph sub = new Paragraph("Nhà hàng ẩm thực Hàn Quốc", normalFont);
            sub.setAlignment(Element.ALIGN_CENTER);
            document.add(sub);

            document.add(new Paragraph("Địa chỉ: 12 Nguyễn Văn Bảo, P. Hạnh Thông, Q. Gò Vấp, TP. Hồ Chí Minh", normalFont));
            document.add(new Paragraph("Điện thoại: (028) 3888 9999      Email: contact@seoulcuisine.vn", normalFont));
            document.add(new Paragraph("Mã số Thuế: 0303888999", normalFont));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Mã hóa đơn: " + hoaDon.getMaHoaDon(), headerFont));
            document.add(new Paragraph("Ngày: " + hoaDon.getNgayLapHoaDon().format(dtfNgay) +
                    "     Giờ: " + hoaDon.getNgayLapHoaDon().format(dtfGio), normalFont));
            document.add(new Paragraph("Bàn: " + (hoaDon.getBanAn() != null ? hoaDon.getBanAn().getMaBan() : "Mang đi"), normalFont));
            document.add(new Paragraph("Nhân viên: " +
                    (hoaDon.getNhanVien() != null ? hoaDon.getNhanVien().getHoTen() + " (" + hoaDon.getNhanVien().getMaNV() + ")" : "Không xác định"),
                    normalFont));
            document.add(new Paragraph(" "));

            // Bảng món ăn
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{10, 45, 10, 18, 17});

            String[] headers = {"STT", "Tên món", "SL", "Đơn giá", "Thành tiền"};
            for (String h : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(new Color(220, 220, 220));
                cell.setPadding(5);
                table.addCell(cell);
            }

            List<ChiTietHoaDon> dsCT = chiTietDAO.layDanhSachTheoHoaDon(hoaDon.getMaHoaDon());
            int stt = 1;
            for (ChiTietHoaDon ct : dsCT) {
                table.addCell(new Phrase(String.valueOf(stt++), normalFont));
                table.addCell(new Phrase(ct.getMonAn().getTenMon(), normalFont));
                table.addCell(new Phrase(String.valueOf(ct.getSoLuong()), normalFont));
                table.addCell(new Phrase(df.format(ct.getDonGia()), normalFont));
                table.addCell(new Phrase(df.format(ct.getThanhTien()), normalFont));
            }
            document.add(table);

            document.add(new Paragraph(" "));
            double chuaThue = hoaDon.getTongTien() / 1.1;
            document.add(new Paragraph("Tổng cộng (Chưa Thuế):          " + df.format(chuaThue) + " đ", normalFont));
            document.add(new Paragraph("Thuế GTGT 10%:                        " + df.format(hoaDon.getTongTien() * 0.1) + " đ", normalFont));
            document.add(new Paragraph("TỔNG CỘNG:                                 " + df.format(hoaDon.getTongTien()) + " đ", bigBold));
            document.add(new Paragraph(" "));

          
            Paragraph loiCamOn = new Paragraph(
                "\nCảm ơn quý khách đã ghé thăm Seoul Cuisine!\n" +
                "Vui lòng kiểm tra hóa đơn trước khi rời khỏi quầy.\n" +
                "Hóa đơn không có giá trị quy đổi thành tiền mặt.", headerFont);
            loiCamOn.setAlignment(Element.ALIGN_CENTER);
            document.add(loiCamOn);

            document.close();
            JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!\n" + file.getAbsolutePath(), "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi xuất PDF: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
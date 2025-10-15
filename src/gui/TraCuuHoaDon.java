package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class TraCuuHoaDon extends JPanel{
	private JTextField txtTim;
	private JButton btnTim;
	private JDateChooser ngayBD;
	private JDateChooser ngayKT;
	private JTable tableHoaDon;
	private DefaultTableModel modelHoaDon;

	public TraCuuHoaDon() {
		setLayout(new BorderLayout(10,10));
        setBackground(Color.decode("#EAF1F9"));
        
        
        //Top
        JPanel pnTop = new JPanel(new BorderLayout());
        pnTop.setOpaque(false);
        add(pnTop, BorderLayout.NORTH);
        //Tiêu đề
        JLabel lblTitle = new JLabel("TRA CỨU HÓA ĐƠN", SwingConstants.CENTER);
        pnTop.add(lblTitle, BorderLayout.NORTH);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(16,0,8,0));
        //Bao cao
        JButton btnXuatHD = new JButton("Xuất Excel");
        pnTop.add(btnXuatHD, BorderLayout.EAST);
        //Lọc
        JPanel pnLoc = new JPanel();
        pnLoc.setOpaque(false);
        pnTop.add(pnLoc, BorderLayout.CENTER);
        //Tim theo ma
        JLabel lblTim = new JLabel("Nhập mã hóa đơn: ");
        pnLoc.add(lblTim);
        txtTim = new JTextField(10);
        pnLoc.add(txtTim);
        btnTim = new JButton("Tìm");
        pnLoc.add(btnTim);
        pnLoc.add(Box.createRigidArea(new Dimension(30, 0))); //tạo khoảng cách theo chiều ngày
        //Tim theo ngay: ngay bat dau
        JLabel lblngayBD = new JLabel("Từ ngày: ");
        pnLoc.add(lblngayBD);
        ngayBD = new JDateChooser();
        ngayBD.setDateFormatString("dd/MM/yyyy");
        pnLoc.add(ngayBD);
        //Ngay ket thuc
        JLabel lblNgayKT = new JLabel("Đến ngày: ");
        pnLoc.add(lblNgayKT);
        ngayKT = new JDateChooser();
        ngayKT.setDateFormatString("dd/MM/yyyy");
        pnLoc.add(ngayKT);
        
        
        //Bảng thông tin ca làm
        JPanel pnInfo = new JPanel(new BorderLayout());
        add(pnInfo, BorderLayout.CENTER);
        
        String[] col = {"STT", "Mã hóa đơn", "Ngày lập", "Mã bàn ăn", "Tên khách hàng", "Mã nhân viên", "Trạng thái", "Tổng tiền"};
        modelHoaDon= new DefaultTableModel(col, 0);
        tableHoaDon = new JTable(modelHoaDon);
        JScrollPane scroll = new JScrollPane(tableHoaDon);
        pnInfo.add(scroll, BorderLayout.CENTER);
	}
}

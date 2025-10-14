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

public class TraCuuCaLam extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTim;
	private JDateChooser ngayKT;
	private JDateChooser ngayBD;
	private JButton btnTim;
	private DefaultTableModel modelCaLam;
	private JTable tableCaLam;

	public TraCuuCaLam() {
		setLayout(new BorderLayout(10,10));
        setBackground(Color.decode("#EAF1F9"));
        //Top
        JPanel pnTop = new JPanel(new BorderLayout());
        pnTop.setOpaque(false);
        add(pnTop, BorderLayout.NORTH);
        //Tiêu đề
        JLabel lblTitle = new JLabel("TRA CỨU CA LÀM", SwingConstants.CENTER);
        pnTop.add(lblTitle, BorderLayout.NORTH);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(44, 62, 80));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(16,0,8,0));
        
        //Lọc
        JPanel pnLoc = new JPanel();
        pnLoc.setOpaque(false);
        pnTop.add(pnLoc, BorderLayout.CENTER);
        //Tim theo ma
        JLabel lblTim = new JLabel("Nhập mã ca: ");
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
        
        String[] col = {"STT", "Mã ca làm", "Giờ vô làm", "Giờ tan ca", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái", "Mô tả"};
        modelCaLam = new DefaultTableModel(col, 0);
        tableCaLam = new JTable(modelCaLam);
        JScrollPane scroll = new JScrollPane(tableCaLam);
        pnInfo.add(scroll);
	}
}

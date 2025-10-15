package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

public class PhanCaLam extends JPanel {
	private JDateChooser ngayLam;
	private JComboBox<String> cbCaLam;
	private JTextArea txtGhiChu;

	public PhanCaLam() {
		setLayout(new BorderLayout(10,10));
        setBackground(Color.decode("#EAF1F9"));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        //Tieu de
        JLabel lblTitle = new JLabel("PHÂN CA LÀM", SwingConstants.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(Color.decode("#333333"));
        
        JPanel pnForm = new JPanel(new GridBagLayout());
        add(pnForm, BorderLayout.WEST);
        pnForm.setBackground(Color.WHITE);
        pnForm.setBorder(BorderFactory.createTitledBorder(
        		BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
        		"Thông tin ca"
        ));
        
        int row = 0;
        
        //Form nhap
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,  10,  15,  10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        //Ngay lam
        ngayLam = new JDateChooser();
        ngayLam.setDateFormatString("dd/MM/yyyy");
        ngayLam.setMinSelectableDate(new Date());
        addFormField(pnForm, gbc, row++, "Ngày làm: *", ngayLam);
        
        //Ca làm
        String[] caLam = {"CA001 - (07:00-12:00)","CA002 - (12:00-16:00)", "CA001 - (17:00-21:00)"};
        cbCaLam = new JComboBox<String>(caLam);
        addFormField(pnForm, gbc, row++, "Ca làm", cbCaLam);
        
        //Ghi chu
        txtGhiChu = new JTextArea(5, 10);
        JScrollPane scroll = new JScrollPane(txtGhiChu);
        addFormField(pnForm, gbc, row++, "Ghi chú", scroll);
        
        
        //Chon nhan vien
        JPanel pnNV = new JPanel(new BorderLayout());
        add(pnNV, BorderLayout.EAST);
        pnNV.setLayout(new BoxLayout(pnNV, BoxLayout.Y_AXIS));
        pnNV.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnNV.setBackground(Color.WHITE);
        
        //Danh sach nhan vien
        JPanel pnNVR = new JPanel(new BorderLayout());
        pnNV.add(pnNVR, BorderLayout.NORTH);
        
        JLabel lblTieuDe = new JLabel("Danh sách nhân viên");
        pnNVR.add(lblTieuDe, BorderLayout.NORTH);
        //danh sach cac nhan vien
        String [] nv = {
        		"Nguyễn Văn A",
        		"Nguyễn Văn B",
        		"Nguyễn Văn C",
        		"Nguyễn Văn D",
        }; //test
        DefaultListModel<String> modelAll = new DefaultListModel<>();
        for(String emp : nv) {
        	modelAll.addElement(emp);
        }
        JList<String> listNV = new JList<>(modelAll);
        listNV.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollAll = new JScrollPane(listNV);
        pnNVR.add(scrollAll, BorderLayout.CENTER);
        
        //Danh sach nhan vien duoc chon
        JPanel pnNVC = new JPanel(new BorderLayout());
        pnNV.add(pnNVC, BorderLayout.CENTER);
        
        JLabel lblTieuDe1= new JLabel("Danh sách nhân viên");
        pnNVC.add(lblTieuDe1, BorderLayout.NORTH);
        //danh sach cac nhan vien da chon
        DefaultListModel<String> modelDaChon = new DefaultListModel();
        JList<String> listChon = new JList<>(modelDaChon);
        listChon.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollChon = new JScrollPane(listChon);
        pnNVC.add(scrollChon, BorderLayout.CENTER);
        
        //lich lam
        JPanel pnCenter = new JPanel(new BorderLayout());
        add(pnCenter, BorderLayout.CENTER);
        pnCenter.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        JLabel lblLich = new JLabel("Lịch làm");
        pnCenter.add(lblLich, BorderLayout.NORTH);
        
        //Dw lieu bang
        String[] col = new String[8]; //7 ngay trong tuan
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE, dd/MM", new Locale("vi", "VN"));
        
        LocalDate today = LocalDate.now(); //Lay tu ngay hom nay
        for(int i = 0; i < 7; i++) {
        	LocalDate date = today.plusDays(i);
        	String ngayStr = date.format(format);
        	
        	col[i + 1] = ngayStr;
        }
        
        //Du lieu ao
        Object[][] data = {
        	    {"CA001 - (07:00-12:00)", "Nguyễn Văn A", "", "", "", "", "", ""},
        	    {"CA002 - (12:00-16:00)", "", "Trần Thị B", "", "", "", "", ""},
        	    {"CA003 - (17:00-21:00)", "", "", "Lê Văn C", "", "", "", ""}
        	};
        DefaultTableModel modelLich = new DefaultTableModel(data, col);
        JTable tableLich = new JTable(modelLich);
        JScrollPane scrollLich = new JScrollPane(tableLich);
        pnCenter.add(scrollLich);
        
	}
	
	private void addFormField (JPanel panel, GridBagConstraints gbc, int row, String lblText, JComponent component) {
		gbc.gridx = 0;
		gbc.gridy = row;
		gbc.weightx = 0.3;
		JLabel lbl = new JLabel(lblText);
		lbl.setFont(new Font("Segeo UI", Font.PLAIN, 13));
		if(lblText.contains("*")) {
			lbl.setForeground(new Color(183, 28, 28)); 
		}
		panel.add(lbl, gbc); //them label vao cot 0
		
		gbc.gridx = 1;
		gbc.weightx = 0.7;
		panel.add(component, gbc);
	}
}

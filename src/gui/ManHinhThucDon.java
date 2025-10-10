package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import javax.swing.border.MatteBorder;

public class ManHinhThucDon extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JPanel panelCenter;
    private JPanel panelEast;
    private JPanel panelCenterNorth;
    private JPanel panelCenterSouth;
    private JPanel panelCenterCenter;
    private JButton btnMonAnChinh;
    private JButton btnDoUong;
    private JButton btnNewButton_2;
    private JPanel panelMonAn_1, panelMonAn_2, panelMonAn_3, panelMonAn_4, panelMonAn_5, panelMonAn_6;
    private JPanel panelMonAn_7, panelMonAn_8, panelMonAn_9, panelMonAn_10, panelMonAn_11, panelMonAn_12;
    private JPanel panelMonAn_13, panelMonAn_14, panelMonAn_15;
    private JLabel lblHinhMonAn_1, lblHinhMonAn_2, lblHinhMonAn_3, lblHinhMonAn_4, lblHinhMonAn_5, lblHinhMonAn_6;
    private JLabel lblHinhMonAn_7, lblHinhMonAn_8, lblHinhMonAn_9, lblHinhMonAn_10, lblHinhMonAn_11, lblHinhMonAn_12;
    private JLabel lblHinhMonAn_13, lblHinhMonAn_14, lblHinhMonAn_15;
    private JPanel panelMonAn_1_Right, panelMonAn_2_Right, panelMonAn_3_Right, panelMonAn_4_Right, panelMonAn_5_Right, panelMonAn_6_Right;
    private JPanel panelMonAn_7_Right, panelMonAn_8_Right, panelMonAn_9_Right, panelMonAn_10_Right, panelMonAn_11_Right, panelMonAn_12_Right;
    private JPanel panelMonAn_13_Right, panelMonAn_14_Right, panelMonAn_15_Right;
    private JPanel panel_MonAn_1_Right_North, panel_MonAn_2_Right_North, panel_MonAn_3_Right_North, panel_MonAn_4_Right_North, panel_MonAn_5_Right_North, panel_MonAn_6_Right_North;
    private JPanel panel_MonAn_7_Right_North, panel_MonAn_8_Right_North, panel_MonAn_9_Right_North, panel_MonAn_10_Right_North, panel_MonAn_11_Right_North, panel_MonAn_12_Right_North;
    private JPanel panel_MonAn_13_Right_North, panel_MonAn_14_Right_North, panel_MonAn_15_Right_North;
    private JPanel panel_MonAn_1_Right_South, panel_MonAn_2_Right_South, panel_MonAn_3_Right_South, panel_MonAn_4_Right_South, panel_MonAn_5_Right_South, panel_MonAn_6_Right_South;
    private JPanel panel_MonAn_7_Right_South, panel_MonAn_8_Right_South, panel_MonAn_9_Right_South, panel_MonAn_10_Right_South, panel_MonAn_11_Right_South, panel_MonAn_12_Right_South;
    private JPanel panel_MonAn_13_Right_South, panel_MonAn_14_Right_South, panel_MonAn_15_Right_South;
    private JButton btnMonAn_1_them, btnMonAn_2_them, btnMonAn_3_them, btnMonAn_4_them, btnMonAn_5_them, btnMonAn_6_them;
    private JButton btnMonAn_7_them, btnMonAn_8_them, btnMonAn_9_them, btnMonAn_10_them, btnMonAn_11_them, btnMonAn_12_them;
    private JButton btnMonAn_13_them, btnMonAn_14_them, btnMonAn_15_them;
    private JLabel lblMonAn_1_tieuDe, lblMonAn_2_tieuDe, lblMonAn_3_tieuDe, lblMonAn_4_tieuDe, lblMonAn_5_tieuDe, lblMonAn_6_tieuDe;
    private JLabel lblMonAn_7_tieuDe, lblMonAn_8_tieuDe, lblMonAn_9_tieuDe, lblMonAn_10_tieuDe, lblMonAn_11_tieuDe, lblMonAn_12_tieuDe;
    private JLabel lblMonAn_13_tieuDe, lblMonAn_14_tieuDe, lblMonAn_15_tieuDe;
    private JLabel lblMonAn_1_gia, lblMonAn_2_gia, lblMonAn_3_gia, lblMonAn_4_gia, lblMonAn_5_gia, lblMonAn_6_gia;
    private JLabel lblMonAn_7_gia, lblMonAn_8_gia, lblMonAn_9_gia, lblMonAn_10_gia, lblMonAn_11_gia, lblMonAn_12_gia;
    private JLabel lblMonAn_13_gia, lblMonAn_14_gia, lblMonAn_15_gia;
    private JLabel lblMonAn_1_tenHanQuoc, lblMonAn_2_tenHanQuoc, lblMonAn_3_tenHanQuoc, lblMonAn_4_tenHanQuoc, lblMonAn_5_tenHanQuoc, lblMonAn_6_tenHanQuoc;
    private JLabel lblMonAn_7_tenHanQuoc, lblMonAn_8_tenHanQuoc, lblMonAn_9_tenHanQuoc, lblMonAn_10_tenHanQuoc, lblMonAn_11_tenHanQuoc, lblMonAn_12_tenHanQuoc;
    private JLabel lblMonAn_13_tenHanQuoc, lblMonAn_14_tenHanQuoc, lblMonAn_15_tenHanQuoc;
    private JLabel lblMonAn_1_thanhPhan, lblMonAn_2_thanhPhan, lblMonAn_3_thanhPhan, lblMonAn_4_thanhPhan, lblMonAn_5_thanhPhan, lblMonAn_6_thanhPhan;
    private JLabel lblMonAn_7_thanhPhan, lblMonAn_8_thanhPhan, lblMonAn_9_thanhPhan, lblMonAn_10_thanhPhan, lblMonAn_11_thanhPhan, lblMonAn_12_thanhPhan;
    private JLabel lblMonAn_13_thanhPhan, lblMonAn_14_thanhPhan, lblMonAn_15_thanhPhan;
    private JPanel panelCenterCenter_line1, panelCenterCenter_line2, panelCenterCenter_line3, panelCenterCenter_line4, panelCenterCenter_line5;
    private JPanel panel;
    private JLabel lblGiaTongTienSanPham;
    private JLabel lblGiamGia;
    private JTextField textFieldGiamGia;
    private JButton btnThanhToan;
    private JPanel panelEastTop;
    private JLabel lblDanhSachDatMon;
    private JRadioButton rdbtMangDi;
    private JTable tableDatMon;
    private DefaultTableModel tableModel;
    private JPanel panelEastCenter;
    private JPanel panelEastCenter_Line1;
    private JLabel lblDanhSachDatMon_Mon1_TieuDe;
    private JLabel lblDanhSachDatMon_Mon1_Gia;
    private JButton btnDanhSachMonAn_Mon1_Cong;
    private JButton btnNewButton_1;
    private JLabel lblDanhSachDatMon_Mon1_GiaTong;
    private JLabel lblImgThungRacIcon;
	private JButton btnImgThungRacIcon;
	private JLabel lblNewLabel_2;
	private JButton btnXuatHoaDon;
	private JButton btnXoaTatCaMon;
	private JButton btnLuuHoaDon;
	private JButton btnDanhSachMonAn_Mon1_Tru;
	private JPanel panelEastCenter_Line2, panelEastCenter_Line3, panelEastCenter_Line4, panelEastCenter_Line5, panelEastCenter_Line6, panelEastCenter_Line7;
	private JLabel lblDanhSachDatMon_Mon2_TieuDe, lblDanhSachDatMon_Mon3_TieuDe, lblDanhSachDatMon_Mon4_TieuDe, lblDanhSachDatMon_Mon5_TieuDe, lblDanhSachDatMon_Mon6_TieuDe, lblDanhSachDatMon_Mon7_TieuDe;
	private JLabel lblDanhSachDatMon_Mon2_Gia, lblDanhSachDatMon_Mon3_Gia, lblDanhSachDatMon_Mon4_Gia, lblDanhSachDatMon_Mon5_Gia, lblDanhSachDatMon_Mon6_Gia, lblDanhSachDatMon_Mon7_Gia;
	private JLabel lblDanhSachMonAn_Mon2_SoLuong, lblDanhSachMonAn_Mon3_SoLuong, lblDanhSachMonAn_Mon4_SoLuong, lblDanhSachMonAn_Mon5_SoLuong, lblDanhSachMonAn_Mon6_SoLuong, lblDanhSachMonAn_Mon7_SoLuong;
	private JButton btnDanhSachMonAn_Mon2_Cong, btnDanhSachMonAn_Mon3_Cong, btnDanhSachMonAn_Mon4_Cong, btnDanhSachMonAn_Mon5_Cong, btnDanhSachMonAn_Mon6_Cong, btnDanhSachMonAn_Mon7_Cong;
	private JButton btnDanhSachMonAn_Mon2_Tru, btnDanhSachMonAn_Mon3_Tru, btnDanhSachMonAn_Mon4_Tru, btnDanhSachMonAn_Mon5_Tru, btnDanhSachMonAn_Mon6_Tru, btnDanhSachMonAn_Mon7_Tru;
	private JLabel lblDanhSachDatMon_Mon2_GiaTong, lblDanhSachDatMon_Mon3_GiaTong, lblDanhSachDatMon_Mon4_GiaTong, lblDanhSachDatMon_Mon5_GiaTong, lblDanhSachDatMon_Mon6_GiaTong, lblDanhSachDatMon_Mon7_GiaTong;
	private JButton btnImgThungRacIcon_Mon2, btnImgThungRacIcon_Mon3, btnImgThungRacIcon_Mon4, btnImgThungRacIcon_Mon5, btnImgThungRacIcon_Mon6, btnImgThungRacIcon_Mon7;
	private JButton btnDanhSachMonAn_MonAnChinh;
	private JButton btnDanhSachMonAn_DoUong;
	private JButton btnDanhSachMonAn_MonAnKem;
    private JPanel panelDoUong;
    private JPanel panelDoUong_line1, panelDoUong_line2;
    private JPanel panelDoUong_1, panelDoUong_2, panelDoUong_3, panelDoUong_4, panelDoUong_5, panelDoUong_6;
    private JLabel lblHinhDoUong_1, lblHinhDoUong_2, lblHinhDoUong_3, lblHinhDoUong_4, lblHinhDoUong_5, lblHinhDoUong_6;
    private JPanel panelDoUong_1_Right, panelDoUong_2_Right, panelDoUong_3_Right, panelDoUong_4_Right, panelDoUong_5_Right, panelDoUong_6_Right;
    private JPanel panel_DoUong_1_Right_North, panel_DoUong_2_Right_North, panel_DoUong_3_Right_North, panel_DoUong_4_Right_North, panel_DoUong_5_Right_North, panel_DoUong_6_Right_North;
    private JPanel panel_DoUong_1_Right_South, panel_DoUong_2_Right_South, panel_DoUong_3_Right_South, panel_DoUong_4_Right_South, panel_DoUong_5_Right_South, panel_DoUong_6_Right_South;
    private JButton btnDoUong_1_them, btnDoUong_2_them, btnDoUong_3_them, btnDoUong_4_them, btnDoUong_5_them, btnDoUong_6_them;
    private JLabel lblDoUong_1_tieuDe, lblDoUong_2_tieuDe, lblDoUong_3_tieuDe, lblDoUong_4_tieuDe, lblDoUong_5_tieuDe, lblDoUong_6_tieuDe;
    private JLabel lblDoUong_1_gia, lblDoUong_2_gia, lblDoUong_3_gia, lblDoUong_4_gia, lblDoUong_5_gia, lblDoUong_6_gia;
    private JLabel lblDoUong_1_tenHanQuoc, lblDoUong_2_tenHanQuoc, lblDoUong_3_tenHanQuoc, lblDoUong_4_tenHanQuoc, lblDoUong_5_tenHanQuoc, lblDoUong_6_tenHanQuoc;
    private JLabel lblDoUong_1_thanhPhan, lblDoUong_2_thanhPhan, lblDoUong_3_thanhPhan, lblDoUong_4_thanhPhan, lblDoUong_5_thanhPhan, lblDoUong_6_thanhPhan;

    private JPanel panelMonAnKem;
    private JPanel panelMonAnKem_line1, panelMonAnKem_line2;
    private JPanel panelMonAnKem_1, panelMonAnKem_2, panelMonAnKem_3, panelMonAnKem_4, panelMonAnKem_5, panelMonAnKem_6;
    private JLabel lblHinhMonAnKem_1, lblHinhMonAnKem_2, lblHinhMonAnKem_3, lblHinhMonAnKem_4, lblHinhMonAnKem_5, lblHinhMonAnKem_6;
    private JPanel panelMonAnKem_1_Right, panelMonAnKem_2_Right, panelMonAnKem_3_Right, panelMonAnKem_4_Right, panelMonAnKem_5_Right, panelMonAnKem_6_Right;
    private JPanel panel_MonAnKem_1_Right_North, panel_MonAnKem_2_Right_North, panel_MonAnKem_3_Right_North, panel_MonAnKem_4_Right_North, panel_MonAnKem_5_Right_North, panel_MonAnKem_6_Right_North;
    private JPanel panel_MonAnKem_1_Right_South, panel_MonAnKem_2_Right_South, panel_MonAnKem_3_Right_South, panel_MonAnKem_4_Right_South, panel_MonAnKem_5_Right_South, panel_MonAnKem_6_Right_South;
    private JButton btnMonAnKem_1_them, btnMonAnKem_2_them, btnMonAnKem_3_them, btnMonAnKem_4_them, btnMonAnKem_5_them, btnMonAnKem_6_them;
    private JLabel lblMonAnKem_1_tieuDe, lblMonAnKem_2_tieuDe, lblMonAnKem_3_tieuDe, lblMonAnKem_4_tieuDe, lblMonAnKem_5_tieuDe, lblMonAnKem_6_tieuDe;
    private JLabel lblMonAnKem_1_gia, lblMonAnKem_2_gia, lblMonAnKem_3_gia, lblMonAnKem_4_gia, lblMonAnKem_5_gia, lblMonAnKem_6_gia;
    private JLabel lblMonAnKem_1_tenHanQuoc, lblMonAnKem_2_tenHanQuoc, lblMonAnKem_3_tenHanQuoc, lblMonAnKem_4_tenHanQuoc, lblMonAnKem_5_tenHanQuoc, lblMonAnKem_6_tenHanQuoc;
    private JLabel lblMonAnKem_1_thanhPhan, lblMonAnKem_2_thanhPhan, lblMonAnKem_3_thanhPhan, lblMonAnKem_4_thanhPhan, lblMonAnKem_5_thanhPhan, lblMonAnKem_6_thanhPhan;
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ManHinhThucDon frame = new ManHinhThucDon();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ManHinhThucDon() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 10, 1200, 700);
        setTitle("Quản Lý Nhà Hàng");

        // Set BorderLayout for the entire frame
        getContentPane().setLayout(new BorderLayout());

        // Initialize JMenuBar
        menuBar = new JMenuBar();
        menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
        menuBar.setBackground(new Color(214, 116, 76, 255));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        setJMenuBar(menuBar);

        // Add logo
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setSize(new Dimension(60, 60));
        lblNewLabel.setBackground(new Color(214, 116, 76, 180));
        lblNewLabel.setOpaque(true);
        ImageIcon icon = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\logo_nhahang.png");
        Image img = icon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(img));
        menuBar.add(lblNewLabel);
        menuBar.add(Box.createHorizontalStrut(5));

        // Use MenuBuilder to create menus
        MenuBuilderQuanLy menuBuilder = new MenuBuilderQuanLy();
        menuBar.add(menuBuilder.createHeThongMenu());
        menuBar.add(menuBuilder.createThucDonMenu());
        menuBar.add(menuBuilder.createKhuVucMenu());
        menuBar.add(menuBuilder.createCaLamMenu());
        menuBar.add(menuBuilder.createNhanVienMenu());
        menuBar.add(menuBuilder.createDoanhThuMenu());

        // Initialize contentPane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(245, 245, 245));
        contentPane.setLayout(new BorderLayout());
        getContentPane().add(contentPane, BorderLayout.CENTER);

        panelCenter = new JPanel();
        panelCenter.setPreferredSize(new Dimension(800, 600));
        contentPane.add(panelCenter, BorderLayout.WEST);
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

        panelCenterNorth = new JPanel();
        panelCenterNorth.setMaximumSize(new Dimension(32767, 100));
        panelCenterNorth.setPreferredSize(new Dimension(800, 100));
        panelCenterNorth.setBackground(new Color(245, 245, 245)); // Light gray for a clean look
        panelCenter.add(panelCenterNorth);
        panelCenterNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15)); // Increased gaps for better spacing

        // ======= CLASS BO GOC =======
        class RoundedButton extends JButton {
            private int radius;

            public RoundedButton(String text, int radius) {
                super(text);
                this.radius = radius;
                setFocusPainted(false);
                setContentAreaFilled(false);
                setBorderPainted(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2.setColor(new Color(39, 174, 96)); // Border color
                g2.setStroke(new BasicStroke(2));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
                g2.dispose();
                super.paintComponent(g);
            }
        }
        // ==============================

        // ======= BUTTON MÓN ĂN CHÍNH =======
        btnDanhSachMonAn_MonAnChinh = new RoundedButton("MÓN ĂN CHÍNH", 20);
        btnDanhSachMonAn_MonAnChinh.setPreferredSize(new Dimension(200, 60));
        btnDanhSachMonAn_MonAnChinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDanhSachMonAn_MonAnChinh.setForeground(new Color(255, 255, 255));
        btnDanhSachMonAn_MonAnChinh.setBackground(new Color(46, 204, 113));
        ImageIcon iconMain = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\main_course_icon.png");
        Image imgMain = iconMain.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnDanhSachMonAn_MonAnChinh.setIcon(new ImageIcon(imgMain));
        btnDanhSachMonAn_MonAnChinh.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDanhSachMonAn_MonAnChinh.setIconTextGap(10);
        btnDanhSachMonAn_MonAnChinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDanhSachMonAn_MonAnChinh.setBackground(new Color(39, 174, 96));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnDanhSachMonAn_MonAnChinh.setBackground(new Color(46, 204, 113));
            }
        });
        btnDanhSachMonAn_MonAnChinh.setToolTipText("Món ăn chính");
        panelCenterNorth.add(btnDanhSachMonAn_MonAnChinh);

        // ======= BUTTON ĐỒ UỐNG =======
        btnDanhSachMonAn_DoUong = new RoundedButton("ĐỒ UỐNG", 20);
        btnDanhSachMonAn_DoUong.setPreferredSize(new Dimension(200, 60));
        btnDanhSachMonAn_DoUong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDanhSachMonAn_DoUong.setForeground(new Color(255, 255, 255));
        btnDanhSachMonAn_DoUong.setBackground(new Color(46, 204, 113));
        ImageIcon iconDrink = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\soju_icon.png");
        Image imgDrink = iconDrink.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnDanhSachMonAn_DoUong.setIcon(new ImageIcon(imgDrink));
        btnDanhSachMonAn_DoUong.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDanhSachMonAn_DoUong.setIconTextGap(10);
        btnDanhSachMonAn_DoUong.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDanhSachMonAn_DoUong.setBackground(new Color(39, 174, 96));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnDanhSachMonAn_DoUong.setBackground(new Color(46, 204, 113));
            }
        });
        btnDanhSachMonAn_DoUong.setToolTipText("Đồ uống");
        btnDanhSachMonAn_DoUong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Existing action listener logic
            }
        });
        panelCenterNorth.add(btnDanhSachMonAn_DoUong);

        // ======= BUTTON MÓN ĂN KÈM =======
        btnDanhSachMonAn_MonAnKem = new RoundedButton("MÓN ĂN KÈM", 20);
        btnDanhSachMonAn_MonAnKem.setPreferredSize(new Dimension(200, 60));
        btnDanhSachMonAn_MonAnKem.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDanhSachMonAn_MonAnKem.setForeground(new Color(255, 255, 255));
        btnDanhSachMonAn_MonAnKem.setBackground(new Color(46, 204, 113));
        ImageIcon iconSide = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\kim_chi_icon.png");
        Image imgSide = iconSide.getImage().getScaledInstance(24, 24, Image.SCALE_SMOOTH);
        btnDanhSachMonAn_MonAnKem.setIcon(new ImageIcon(imgSide));
        btnDanhSachMonAn_MonAnKem.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDanhSachMonAn_MonAnKem.setIconTextGap(10);
        btnDanhSachMonAn_MonAnKem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btnDanhSachMonAn_MonAnKem.setBackground(new Color(39, 174, 96));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btnDanhSachMonAn_MonAnKem.setBackground(new Color(46, 204, 113));
            }
        });
        btnDanhSachMonAn_MonAnKem.setToolTipText("Món ăn kèm");
        btnDanhSachMonAn_MonAnKem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Existing action listener logic
            }
        });
        panelCenterNorth.add(btnDanhSachMonAn_MonAnKem);


        panelCenterSouth = new JPanel();
        panelCenterSouth.setBackground(new Color(0, 255, 255));
        panelCenter.add(panelCenterSouth);
        panelCenterSouth.setLayout(new BoxLayout(panelCenterSouth, BoxLayout.Y_AXIS));

        panelCenterCenter = new JPanel();
        panelCenterCenter.setPreferredSize(new Dimension(780, 800)); // Increased height for 5 rows
        panelCenterCenter.setLayout(new BoxLayout(panelCenterCenter, BoxLayout.Y_AXIS));
        panelCenterCenter.setBackground(new Color(255, 255, 255));
        JScrollPane scrollPane = new JScrollPane(panelCenterCenter);
        scrollPane.setPreferredSize(new Dimension(800, 400));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panelCenter.add(scrollPane);

        // Line 1: Three dishes
        panelCenterCenter_line1 = new JPanel();
        panelCenterCenter_line1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_line1 = (FlowLayout) panelCenterCenter_line1.getLayout();
        flowLayout_line1.setHgap(10);
        panelCenterCenter_line1.setBackground(new Color(255, 255, 255));
        panelCenterCenter.add(panelCenterCenter_line1);
        panelCenterCenter.add(Box.createVerticalStrut(10));

        // Dish 1: Tokbokki truyền thống
        panelMonAn_1 = new JPanel();
        panelMonAn_1.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line1.add(panelMonAn_1);
        panelMonAn_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        ImageIcon icon1 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\tokbokki_truyen_thong.PNG");
        Image img1 = icon1.getImage().getScaledInstance(125, 110, Image.SCALE_SMOOTH);

        lblHinhMonAn_1 = new JLabel("");
        lblHinhMonAn_1.setIcon(new ImageIcon(img1));
        lblHinhMonAn_1.setPreferredSize(new Dimension(125, 110));
        panelMonAn_1.add(lblHinhMonAn_1);

        panelMonAn_1_Right = new JPanel();
        panelMonAn_1_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_1.add(panelMonAn_1_Right);
        panelMonAn_1_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_1_Right_North = new JPanel();
        panel_MonAn_1_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_1_Right.add(panel_MonAn_1_Right_North, BorderLayout.NORTH);
        panel_MonAn_1_Right_North.setLayout(new BoxLayout(panel_MonAn_1_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_1_tieuDe = new JLabel("Tokbokki truyền thống");
        lblMonAn_1_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_1_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_1_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_1_Right_North.add(lblMonAn_1_tieuDe);

        lblMonAn_1_tenHanQuoc = new JLabel("떡볶이");
        lblMonAn_1_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_1_Right_North.add(lblMonAn_1_tenHanQuoc);

        lblMonAn_1_thanhPhan = new JLabel("Bánh gạo, Rau củ, Trứng");
        lblMonAn_1_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_1_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_1_Right_North.add(lblMonAn_1_thanhPhan);

        panel_MonAn_1_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_1_Right_South = (FlowLayout) panel_MonAn_1_Right_South.getLayout();
        fl_panel_MonAn_1_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_1_Right.add(panel_MonAn_1_Right_South, BorderLayout.SOUTH);

        btnMonAn_1_them = new JButton("Thêm");
        btnMonAn_1_them.setForeground(new Color(64, 0, 0));
        btnMonAn_1_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_1_them.setBackground(new Color(255, 128, 64));
        btnMonAn_1_them.setContentAreaFilled(true);
        btnMonAn_1_them.setOpaque(true);
        btnMonAn_1_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_1_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_1_Right_South.add(btnMonAn_1_them);

        lblMonAn_1_gia = new JLabel("43,000 ");
        lblMonAn_1_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_1_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_1_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_1_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_1_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_1_Right.add(lblMonAn_1_gia, BorderLayout.CENTER);

        // Dish 2: Kimbap chiên
        panelMonAn_2 = new JPanel();
        panelMonAn_2.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line1.add(panelMonAn_2);
        panelMonAn_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_2 = new JLabel("");
        lblHinhMonAn_2.setIcon(new ImageIcon(img1));
        lblHinhMonAn_2.setPreferredSize(new Dimension(125, 110));
        panelMonAn_2.add(lblHinhMonAn_2);

        panelMonAn_2_Right = new JPanel();
        panelMonAn_2_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_2.add(panelMonAn_2_Right);
        panelMonAn_2_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_2_Right_North = new JPanel();
        panel_MonAn_2_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_2_Right.add(panel_MonAn_2_Right_North, BorderLayout.NORTH);
        panel_MonAn_2_Right_North.setLayout(new BoxLayout(panel_MonAn_2_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_2_tieuDe = new JLabel("Kimbap chiên giòn");
        lblMonAn_2_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_2_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_2_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_2_Right_North.add(lblMonAn_2_tieuDe);

        lblMonAn_2_tenHanQuoc = new JLabel("김밥");
        lblMonAn_2_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_2_Right_North.add(lblMonAn_2_tenHanQuoc);

        lblMonAn_2_thanhPhan = new JLabel("Cơm, Rong biển, Thịt");
        lblMonAn_2_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_2_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_2_Right_North.add(lblMonAn_2_thanhPhan);

        panel_MonAn_2_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_2_Right_South = (FlowLayout) panel_MonAn_2_Right_South.getLayout();
        fl_panel_MonAn_2_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_2_Right.add(panel_MonAn_2_Right_South, BorderLayout.SOUTH);

        btnMonAn_2_them = new JButton("Thêm");
        btnMonAn_2_them.setForeground(new Color(64, 0, 0));
        btnMonAn_2_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_2_them.setBackground(new Color(255, 128, 64));
        btnMonAn_2_them.setContentAreaFilled(true);
        btnMonAn_2_them.setOpaque(true);
        btnMonAn_2_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_2_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_2_Right_South.add(btnMonAn_2_them);

        lblMonAn_2_gia = new JLabel("35,000 VNĐ");
        lblMonAn_2_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_2_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_2_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_2_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_2_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_2_Right.add(lblMonAn_2_gia, BorderLayout.CENTER);

        // Dish 3: Mì tương đen
        panelMonAn_3 = new JPanel();
        panelMonAn_3.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line1.add(panelMonAn_3);
        panelMonAn_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_3 = new JLabel("");
        lblHinhMonAn_3.setIcon(new ImageIcon(img1));
        lblHinhMonAn_3.setPreferredSize(new Dimension(125, 110));
        panelMonAn_3.add(lblHinhMonAn_3);

        panelMonAn_3_Right = new JPanel();
        panelMonAn_3_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_3.add(panelMonAn_3_Right);
        panelMonAn_3_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_3_Right_North = new JPanel();
        panel_MonAn_3_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_3_Right.add(panel_MonAn_3_Right_North, BorderLayout.NORTH);
        panel_MonAn_3_Right_North.setLayout(new BoxLayout(panel_MonAn_3_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_3_tieuDe = new JLabel("Mì tương đen đặc biệt");
        lblMonAn_3_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_3_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_3_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_3_Right_North.add(lblMonAn_3_tieuDe);

        lblMonAn_3_tenHanQuoc = new JLabel("짜장면");
        lblMonAn_3_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_3_Right_North.add(lblMonAn_3_tenHanQuoc);

        lblMonAn_3_thanhPhan = new JLabel("Mì, Tương đen, Rau củ");
        lblMonAn_3_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_3_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_3_Right_North.add(lblMonAn_3_thanhPhan);

        panel_MonAn_3_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_3_Right_South = (FlowLayout) panel_MonAn_3_Right_South.getLayout();
        fl_panel_MonAn_3_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_3_Right.add(panel_MonAn_3_Right_South, BorderLayout.SOUTH);

        btnMonAn_3_them = new JButton("Thêm");
        btnMonAn_3_them.setForeground(new Color(64, 0, 0));
        btnMonAn_3_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_3_them.setBackground(new Color(255, 128, 64));
        btnMonAn_3_them.setContentAreaFilled(true);
        btnMonAn_3_them.setOpaque(true);
        btnMonAn_3_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_3_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_3_Right_South.add(btnMonAn_3_them);

        lblMonAn_3_gia = new JLabel("50,000 VNĐ");
        lblMonAn_3_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_3_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_3_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_3_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_3_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_3_Right.add(lblMonAn_3_gia, BorderLayout.CENTER);

        // Line 2: Three dishes
        panelCenterCenter_line2 = new JPanel();
        panelCenterCenter_line2.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_line2 = (FlowLayout) panelCenterCenter_line2.getLayout();
        flowLayout_line2.setHgap(10);
        panelCenterCenter_line2.setBackground(new Color(255, 255, 255));
        panelCenterCenter.add(panelCenterCenter_line2);
        panelCenterCenter.add(Box.createVerticalStrut(10));

        // Dish 4: Bibimbap
        panelMonAn_4 = new JPanel();
        panelMonAn_4.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line2.add(panelMonAn_4);
        panelMonAn_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_4 = new JLabel("");
        lblHinhMonAn_4.setIcon(new ImageIcon(img1));
        lblHinhMonAn_4.setPreferredSize(new Dimension(125, 110));
        panelMonAn_4.add(lblHinhMonAn_4);

        panelMonAn_4_Right = new JPanel();
        panelMonAn_4_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_4.add(panelMonAn_4_Right);
        panelMonAn_4_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_4_Right_North = new JPanel();
        panel_MonAn_4_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_4_Right.add(panel_MonAn_4_Right_North, BorderLayout.NORTH);
        panel_MonAn_4_Right_North.setLayout(new BoxLayout(panel_MonAn_4_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_4_tieuDe = new JLabel("Bibimbap hỗn hợp");
        lblMonAn_4_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_4_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_4_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_4_Right_North.add(lblMonAn_4_tieuDe);

        lblMonAn_4_tenHanQuoc = new JLabel("비빔밥");
        lblMonAn_4_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_4_Right_North.add(lblMonAn_4_tenHanQuoc);

        lblMonAn_4_thanhPhan = new JLabel("Cơm, Rau củ, Thịt bò");
        lblMonAn_4_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_4_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_4_Right_North.add(lblMonAn_4_thanhPhan);

        panel_MonAn_4_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_4_Right_South = (FlowLayout) panel_MonAn_4_Right_South.getLayout();
        fl_panel_MonAn_4_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_4_Right.add(panel_MonAn_4_Right_South, BorderLayout.SOUTH);

        btnMonAn_4_them = new JButton("Thêm");
        btnMonAn_4_them.setForeground(new Color(64, 0, 0));
        btnMonAn_4_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_4_them.setBackground(new Color(255, 128, 64));
        btnMonAn_4_them.setContentAreaFilled(true);
        btnMonAn_4_them.setOpaque(true);
        btnMonAn_4_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_4_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_4_Right_South.add(btnMonAn_4_them);

        lblMonAn_4_gia = new JLabel("55,000");
        lblMonAn_4_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_4_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_4_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_4_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_4_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_4_Right.add(lblMonAn_4_gia, BorderLayout.CENTER);

        // Dish 5: Samgyeopsal
        panelMonAn_5 = new JPanel();
        panelMonAn_5.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line2.add(panelMonAn_5);
        panelMonAn_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_5 = new JLabel("");
        lblHinhMonAn_5.setIcon(new ImageIcon(img1));
        lblHinhMonAn_5.setPreferredSize(new Dimension(125, 110));
        panelMonAn_5.add(lblHinhMonAn_5);

        panelMonAn_5_Right = new JPanel();
        panelMonAn_5_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_5.add(panelMonAn_5_Right);
        panelMonAn_5_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_5_Right_North = new JPanel();
        panel_MonAn_5_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_5_Right.add(panel_MonAn_5_Right_North, BorderLayout.NORTH);
        panel_MonAn_5_Right_North.setLayout(new BoxLayout(panel_MonAn_5_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_5_tieuDe = new JLabel("Samgyeopsal nướng");
        lblMonAn_5_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_5_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_5_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_5_Right_North.add(lblMonAn_5_tieuDe);

        lblMonAn_5_tenHanQuoc = new JLabel("삼겹살");
        lblMonAn_5_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_5_Right_North.add(lblMonAn_5_tenHanQuoc);

        lblMonAn_5_thanhPhan = new JLabel("Thịt heo, Rau sống, Tương ớt");
        lblMonAn_5_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_5_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_5_Right_North.add(lblMonAn_5_thanhPhan);

        panel_MonAn_5_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_5_Right_South = (FlowLayout) panel_MonAn_5_Right_South.getLayout();
        fl_panel_MonAn_5_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_5_Right.add(panel_MonAn_5_Right_South, BorderLayout.SOUTH);

        btnMonAn_5_them = new JButton("Thêm");
        btnMonAn_5_them.setForeground(new Color(64, 0, 0));
        btnMonAn_5_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_5_them.setBackground(new Color(255, 128, 64));
        btnMonAn_5_them.setContentAreaFilled(true);
        btnMonAn_5_them.setOpaque(true);
        btnMonAn_5_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_5_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_5_Right_South.add(btnMonAn_5_them);

        lblMonAn_5_gia = new JLabel("60,000 VNĐ");
        lblMonAn_5_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_5_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_5_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_5_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_5_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_5_Right.add(lblMonAn_5_gia, BorderLayout.CENTER);

        // Dish 6: Kimchi jjigae
        panelMonAn_6 = new JPanel();
        panelMonAn_6.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line2.add(panelMonAn_6);
        panelMonAn_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_6 = new JLabel("");
        lblHinhMonAn_6.setIcon(new ImageIcon(img1));
        lblHinhMonAn_6.setPreferredSize(new Dimension(125, 110));
        panelMonAn_6.add(lblHinhMonAn_6);

        panelMonAn_6_Right = new JPanel();
        panelMonAn_6_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_6.add(panelMonAn_6_Right);
        panelMonAn_6_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_6_Right_North = new JPanel();
        panel_MonAn_6_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_6_Right.add(panel_MonAn_6_Right_North, BorderLayout.NORTH);
        panel_MonAn_6_Right_North.setLayout(new BoxLayout(panel_MonAn_6_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_6_tieuDe = new JLabel("Kimchi jjigae cay");
        lblMonAn_6_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_6_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_6_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_6_Right_North.add(lblMonAn_6_tieuDe);

        lblMonAn_6_tenHanQuoc = new JLabel("김치찌개");
        lblMonAn_6_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_6_Right_North.add(lblMonAn_6_tenHanQuoc);

        lblMonAn_6_thanhPhan = new JLabel("Kimchi, Đậu phụ, Thịt");
        lblMonAn_6_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_6_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_6_Right_North.add(lblMonAn_6_thanhPhan);

        panel_MonAn_6_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_6_Right_South = (FlowLayout) panel_MonAn_6_Right_South.getLayout();
        fl_panel_MonAn_6_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_6_Right.add(panel_MonAn_6_Right_South, BorderLayout.SOUTH);

        btnMonAn_6_them = new JButton("Thêm");
        btnMonAn_6_them.setForeground(new Color(64, 0, 0));
        btnMonAn_6_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_6_them.setBackground(new Color(255, 128, 64));
        btnMonAn_6_them.setContentAreaFilled(true);
        btnMonAn_6_them.setOpaque(true);
        btnMonAn_6_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_6_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_6_Right_South.add(btnMonAn_6_them);

        lblMonAn_6_gia = new JLabel("45,000 VNĐ");
        lblMonAn_6_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_6_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_6_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_6_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_6_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_6_Right.add(lblMonAn_6_gia, BorderLayout.CENTER);

        // Line 3: Three dishes
        panelCenterCenter_line3 = new JPanel();
        panelCenterCenter_line3.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_line3 = (FlowLayout) panelCenterCenter_line3.getLayout();
        flowLayout_line3.setHgap(10);
        panelCenterCenter_line3.setBackground((panelCenterCenter_line2.getBackground()));
        panelCenterCenter.add(panelCenterCenter_line3);
        panelCenterCenter.add(Box.createVerticalStrut(10));

        // Dish 7: Japchae
        panelMonAn_7 = new JPanel();
        panelMonAn_7.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line3.add(panelMonAn_7);
        panelMonAn_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_7 = new JLabel("");
        lblHinhMonAn_7.setIcon(new ImageIcon(img1));
        lblHinhMonAn_7.setPreferredSize(new Dimension(125, 110));
        panelMonAn_7.add(lblHinhMonAn_7);

        panelMonAn_7_Right = new JPanel();
        panelMonAn_7_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_7.add(panelMonAn_7_Right);
        panelMonAn_7_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_7_Right_North = new JPanel();
        panel_MonAn_7_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_7_Right.add(panel_MonAn_7_Right_North, BorderLayout.NORTH);
        panel_MonAn_7_Right_North.setLayout(new BoxLayout(panel_MonAn_7_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_7_tieuDe = new JLabel("Japchae xào rau củ");
        lblMonAn_7_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_7_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_7_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_7_Right_North.add(lblMonAn_7_tieuDe);

        lblMonAn_7_tenHanQuoc = new JLabel("잡채");
        lblMonAn_7_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_7_Right_North.add(lblMonAn_7_tenHanQuoc);

        lblMonAn_7_thanhPhan = new JLabel("Mì kính, Rau củ, Thịt");
        lblMonAn_7_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_7_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_7_Right_North.add(lblMonAn_7_thanhPhan);

        panel_MonAn_7_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_7_Right_South = (FlowLayout) panel_MonAn_7_Right_South.getLayout();
        fl_panel_MonAn_7_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_7_Right.add(panel_MonAn_7_Right_South, BorderLayout.SOUTH);

        btnMonAn_7_them = new JButton("Thêm");
        btnMonAn_7_them.setForeground(new Color(64, 0, 0));
        btnMonAn_7_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_7_them.setBackground(new Color(255, 128, 64));
        btnMonAn_7_them.setContentAreaFilled(true);
        btnMonAn_7_them.setOpaque(true);
        btnMonAn_7_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_7_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_7_Right_South.add(btnMonAn_7_them);

        lblMonAn_7_gia = new JLabel("50,000đ");
        lblMonAn_7_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_7_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_7_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_7_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_7_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_7_Right.add(lblMonAn_7_gia, BorderLayout.CENTER);

        // Dish 8: Tteokguk
        panelMonAn_8 = new JPanel();
        panelMonAn_8.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line3.add(panelMonAn_8);
        panelMonAn_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_8 = new JLabel("");
        lblHinhMonAn_8.setIcon(new ImageIcon(img1));
        lblHinhMonAn_8.setPreferredSize(new Dimension(125, 110));
        panelMonAn_8.add(lblHinhMonAn_8);

        panelMonAn_8_Right = new JPanel();
        panelMonAn_8_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_8.add(panelMonAn_8_Right);
        panelMonAn_8_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_8_Right_North = new JPanel();
        panel_MonAn_8_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_8_Right.add(panel_MonAn_8_Right_North, BorderLayout.NORTH);
        panel_MonAn_8_Right_North.setLayout(new BoxLayout(panel_MonAn_8_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_8_tieuDe = new JLabel("Tteokguk canh năm mới");
        lblMonAn_8_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_8_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_8_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_8_Right_North.add(lblMonAn_8_tieuDe);

        lblMonAn_8_tenHanQuoc = new JLabel("떡국");
        lblMonAn_8_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_8_Right_North.add(lblMonAn_8_tenHanQuoc);

        lblMonAn_8_thanhPhan = new JLabel("Bánh gạo, Nước dùng, Trứng");
        lblMonAn_8_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_8_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_8_Right_North.add(lblMonAn_8_thanhPhan);

        panel_MonAn_8_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_8_Right_South = (FlowLayout) panel_MonAn_8_Right_South.getLayout();
        fl_panel_MonAn_8_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_8_Right.add(panel_MonAn_8_Right_South, BorderLayout.SOUTH);

        btnMonAn_8_them = new JButton("Thêm");
        btnMonAn_8_them.setForeground(new Color(64, 0, 0));
        btnMonAn_8_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_8_them.setBackground(new Color(255, 128, 64));
        btnMonAn_8_them.setContentAreaFilled(true);
        btnMonAn_8_them.setOpaque(true);
        btnMonAn_8_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_8_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_8_Right_South.add(btnMonAn_8_them);

        lblMonAn_8_gia = new JLabel("40,000đ");
        lblMonAn_8_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_8_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_8_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_8_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_8_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_8_Right.add(lblMonAn_8_gia, BorderLayout.CENTER);

        // Dish 9: Sundubu jjigae
        panelMonAn_9 = new JPanel();
        panelMonAn_9.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line3.add(panelMonAn_9);
        panelMonAn_9.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_9 = new JLabel("");
        lblHinhMonAn_9.setIcon(new ImageIcon(img1));
        lblHinhMonAn_9.setPreferredSize(new Dimension(125, 110));
        panelMonAn_9.add(lblHinhMonAn_9);

        panelMonAn_9_Right = new JPanel();
        panelMonAn_9_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_9.add(panelMonAn_9_Right);
        panelMonAn_9_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_9_Right_North = new JPanel();
        panel_MonAn_9_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_9_Right.add(panel_MonAn_9_Right_North, BorderLayout.NORTH);
        panel_MonAn_9_Right_North.setLayout(new BoxLayout(panel_MonAn_9_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_9_tieuDe = new JLabel("Sundubu jjigae hải sản");
        lblMonAn_9_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_9_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_9_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_9_Right_North.add(lblMonAn_9_tieuDe);

        lblMonAn_9_tenHanQuoc = new JLabel("순두부찌개");
        lblMonAn_9_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_9_Right_North.add(lblMonAn_9_tenHanQuoc);

        lblMonAn_9_thanhPhan = new JLabel("Đậu phụ mềm, Hải sản, Rau củ");
        lblMonAn_9_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_9_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_9_Right_North.add(lblMonAn_9_thanhPhan);

        panel_MonAn_9_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_9_Right_South = (FlowLayout) panel_MonAn_9_Right_South.getLayout();
        fl_panel_MonAn_9_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_9_Right.add(panel_MonAn_9_Right_South, BorderLayout.SOUTH);

        btnMonAn_9_them = new JButton("Thêm");
        btnMonAn_9_them.setForeground(new Color(64, 0, 0));
        btnMonAn_9_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_9_them.setBackground(new Color(255, 128, 64));
        btnMonAn_9_them.setContentAreaFilled(true);
        btnMonAn_9_them.setOpaque(true);
        btnMonAn_9_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_9_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_9_Right_South.add(btnMonAn_9_them);

        lblMonAn_9_gia = new JLabel("48,000đ");
        lblMonAn_9_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_9_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_9_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_9_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_9_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_9_Right.add(lblMonAn_9_gia, BorderLayout.CENTER);

        // Line 4: Three new dishes
        panelCenterCenter_line4 = new JPanel();
        FlowLayout flowLayout_line4 = (FlowLayout) panelCenterCenter_line4.getLayout();
        flowLayout_line4.setHgap(10);
        panelCenterCenter_line4.setBackground(panelCenterCenter_line2.getBackground());
        panelCenterCenter.add(panelCenterCenter_line4);
        panelCenterCenter.add(Box.createVerticalStrut(10));

        // Dish 10: Bulgogi
        panelMonAn_10 = new JPanel();
        panelMonAn_10.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line4.add(panelMonAn_10);
        panelMonAn_10.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_10 = new JLabel("");
        lblHinhMonAn_10.setIcon(new ImageIcon(img1));
        lblHinhMonAn_10.setPreferredSize(new Dimension(125, 110));
        panelMonAn_10.add(lblHinhMonAn_10);

        panelMonAn_10_Right = new JPanel();
        panelMonAn_10_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_10.add(panelMonAn_10_Right);
        panelMonAn_10_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_10_Right_North = new JPanel();
        panel_MonAn_10_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_10_Right.add(panel_MonAn_10_Right_North, BorderLayout.NORTH);
        panel_MonAn_10_Right_North.setLayout(new BoxLayout(panel_MonAn_10_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_10_tieuDe = new JLabel("Bulgogi thịt bò nướng");
        lblMonAn_10_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_10_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_10_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_10_Right_North.add(lblMonAn_10_tieuDe);

        lblMonAn_10_tenHanQuoc = new JLabel("불고기");
        lblMonAn_10_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_10_Right_North.add(lblMonAn_10_tenHanQuoc);

        lblMonAn_10_thanhPhan = new JLabel("Thịt bò, Nước sốt, Rau củ");
        lblMonAn_10_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_10_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_10_Right_North.add(lblMonAn_10_thanhPhan);

        panel_MonAn_10_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_10_Right_South = (FlowLayout) panel_MonAn_10_Right_South.getLayout();
        fl_panel_MonAn_10_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_10_Right.add(panel_MonAn_10_Right_South, BorderLayout.SOUTH);

        btnMonAn_10_them = new JButton("Thêm");
        btnMonAn_10_them.setForeground(new Color(64, 0, 0));
        btnMonAn_10_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_10_them.setBackground(new Color(255, 128, 64));
        btnMonAn_10_them.setContentAreaFilled(true);
        btnMonAn_10_them.setOpaque(true);
        btnMonAn_10_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_10_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_10_Right_South.add(btnMonAn_10_them);

        lblMonAn_10_gia = new JLabel("65,000đ");
        lblMonAn_10_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_10_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_10_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_10_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_10_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_10_Right.add(lblMonAn_10_gia, BorderLayout.CENTER);

        // Dish 11: Haemul Pajeon
        panelMonAn_11 = new JPanel();
        panelMonAn_11.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line4.add(panelMonAn_11);
        panelMonAn_11.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_11 = new JLabel("");
        lblHinhMonAn_11.setIcon(new ImageIcon(img1));
        lblHinhMonAn_11.setPreferredSize(new Dimension(125, 110));
        panelMonAn_11.add(lblHinhMonAn_11);

        panelMonAn_11_Right = new JPanel();
        panelMonAn_11_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_11.add(panelMonAn_11_Right);
        panelMonAn_11_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_11_Right_North = new JPanel();
        panel_MonAn_11_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_11_Right.add(panel_MonAn_11_Right_North, BorderLayout.NORTH);
        panel_MonAn_11_Right_North.setLayout(new BoxLayout(panel_MonAn_11_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_11_tieuDe = new JLabel("Haemul Pajeon hải sản");
        lblMonAn_11_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_11_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_11_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_11_Right_North.add(lblMonAn_11_tieuDe);

        lblMonAn_11_tenHanQuoc = new JLabel("해물파전");
        lblMonAn_11_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_11_Right_North.add(lblMonAn_11_tenHanQuoc);

        lblMonAn_11_thanhPhan = new JLabel("Hải sản, Hành lá, Bột mì");
        lblMonAn_11_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_11_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_11_Right_North.add(lblMonAn_11_thanhPhan);

        panel_MonAn_11_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_11_Right_South = (FlowLayout) panel_MonAn_11_Right_South.getLayout();
        fl_panel_MonAn_11_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_11_Right.add(panel_MonAn_11_Right_South, BorderLayout.SOUTH);

        btnMonAn_11_them = new JButton("Thêm");
        btnMonAn_11_them.setForeground(new Color(64, 0, 0));
        btnMonAn_11_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_11_them.setBackground(new Color(255, 128, 64));
        btnMonAn_11_them.setContentAreaFilled(true);
        btnMonAn_11_them.setOpaque(true);
        btnMonAn_11_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_11_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_11_Right_South.add(btnMonAn_11_them);

        lblMonAn_11_gia = new JLabel("42,000đ");
        lblMonAn_11_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_11_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_11_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_11_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_11_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_11_Right.add(lblMonAn_11_gia, BorderLayout.CENTER);

        // Dish 12: Jeyuk Bokkeum
        panelMonAn_12 = new JPanel();
        panelMonAn_12.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line4.add(panelMonAn_12);
        panelMonAn_12.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_12 = new JLabel("");
        lblHinhMonAn_12.setIcon(new ImageIcon(img1));
        lblHinhMonAn_12.setPreferredSize(new Dimension(125, 110));
        panelMonAn_12.add(lblHinhMonAn_12);

        panelMonAn_12_Right = new JPanel();
        panelMonAn_12_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_12.add(panelMonAn_12_Right);
        panelMonAn_12_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_12_Right_North = new JPanel();
        panel_MonAn_12_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_12_Right.add(panel_MonAn_12_Right_North, BorderLayout.NORTH);
        panel_MonAn_12_Right_North.setLayout(new BoxLayout(panel_MonAn_12_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_12_tieuDe = new JLabel("Jeyuk Bokkeum cay");
        lblMonAn_12_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_12_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_12_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_12_Right_North.add(lblMonAn_12_tieuDe);

        lblMonAn_12_tenHanQuoc = new JLabel("제육볶음");
        lblMonAn_12_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_12_Right_North.add(lblMonAn_12_tenHanQuoc);

        lblMonAn_12_thanhPhan = new JLabel("Thịt heo, Tương ớt, Rau củ");
        lblMonAn_12_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_12_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_12_Right_North.add(lblMonAn_12_thanhPhan);

        panel_MonAn_12_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_12_Right_South = (FlowLayout) panel_MonAn_12_Right_South.getLayout();
        fl_panel_MonAn_12_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_12_Right.add(panel_MonAn_12_Right_South, BorderLayout.SOUTH);

        btnMonAn_12_them = new JButton("Thêm");
        btnMonAn_12_them.setForeground(new Color(64, 0, 0));
        btnMonAn_12_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_12_them.setBackground(new Color(255, 128, 64));
        btnMonAn_12_them.setContentAreaFilled(true);
        btnMonAn_12_them.setOpaque(true);
        btnMonAn_12_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_12_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_12_Right_South.add(btnMonAn_12_them);

        lblMonAn_12_gia = new JLabel("55,000đ");
        lblMonAn_12_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_12_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_12_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_12_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_12_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_12_Right.add(lblMonAn_12_gia, BorderLayout.CENTER);

        // Line 5: Three new dishes
        panelCenterCenter_line5 = new JPanel();
        FlowLayout flowLayout_line5 = (FlowLayout) panelCenterCenter_line5.getLayout();
        flowLayout_line5.setHgap(10);
        panelCenterCenter_line5.setBackground(panelCenterCenter_line2.getBackground());
        panelCenterCenter.add(panelCenterCenter_line5);
        panelCenterCenter.add(Box.createVerticalStrut(10));

        // Dish 13: Doenjang Jjigae
        panelMonAn_13 = new JPanel();
        panelMonAn_13.setPreferredSize(new Dimension(250, 130));
        panelCenterCenter_line5.add(panelMonAn_13);
        panelMonAn_13.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAn_13 = new JLabel("");
        lblHinhMonAn_13.setIcon(new ImageIcon(img1));
        lblHinhMonAn_13.setPreferredSize(new Dimension(125, 110));
        panelMonAn_13.add(lblHinhMonAn_13);

        panelMonAn_13_Right = new JPanel();
        panelMonAn_13_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAn_13.add(panelMonAn_13_Right);
        panelMonAn_13_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAn_13_Right_North = new JPanel();
        panel_MonAn_13_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAn_13_Right.add(panel_MonAn_13_Right_North, BorderLayout.NORTH);
        panel_MonAn_13_Right_North.setLayout(new BoxLayout(panel_MonAn_13_Right_North, BoxLayout.Y_AXIS));

        lblMonAn_13_tieuDe = new JLabel("Doenjang Jjigae đậu tương");
        lblMonAn_13_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAn_13_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_13_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_13_Right_North.add(lblMonAn_13_tieuDe);

        lblMonAn_13_tenHanQuoc = new JLabel("된장찌개");
        lblMonAn_13_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_13_Right_North.add(lblMonAn_13_tenHanQuoc);

        lblMonAn_13_thanhPhan = new JLabel("Tương đậu, Đậu phụ, Rau củ");
        lblMonAn_13_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAn_13_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAn_13_Right_North.add(lblMonAn_13_thanhPhan);

        panel_MonAn_13_Right_South = new JPanel();
        FlowLayout fl_panel_MonAn_13_Right_South = (FlowLayout) panel_MonAn_13_Right_South.getLayout();
        fl_panel_MonAn_13_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAn_13_Right.add(panel_MonAn_13_Right_South, BorderLayout.SOUTH);

        btnMonAn_13_them = new JButton("Thêm");
        btnMonAn_13_them.setForeground(new Color(64, 0, 0));
        btnMonAn_13_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAn_13_them.setBackground(new Color(255, 128, 64));
        btnMonAn_13_them.setContentAreaFilled(true);
        btnMonAn_13_them.setOpaque(true);
        btnMonAn_13_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAn_13_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAn_13_Right_South.add(btnMonAn_13_them);

        lblMonAn_13_gia = new JLabel("45,000đ");
        lblMonAn_13_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAn_13_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAn_13_gia.setBackground(new Color(64, 0, 0));
        lblMonAn_13_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAn_13_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAn_13_Right.add(lblMonAn_13_gia, BorderLayout.CENTER);
        

        // Panel for Đồ Uống
        panelDoUong = new JPanel();
        panelDoUong.setPreferredSize(new Dimension(780, 300));
        panelDoUong.setLayout(new BoxLayout(panelDoUong, BoxLayout.Y_AXIS));
        panelDoUong.setBackground(new Color(255, 255, 255));
        
        ImageIcon iconDoUong = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\cocacola_icon.png");
        Image imgDoUong = iconDoUong.getImage().getScaledInstance(125, 110, Image.SCALE_SMOOTH);

        // Line 1 for Đồ Uống
        panelDoUong_line1 = new JPanel();
        panelDoUong_line1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_du_line1 = (FlowLayout) panelDoUong_line1.getLayout();
        flowLayout_du_line1.setHgap(10);
        panelDoUong_line1.setBackground(new Color(255, 255, 255));
        panelDoUong.add(panelDoUong_line1);
        panelDoUong.add(Box.createVerticalStrut(10));

        // Đồ Uống 1: Soju
        panelDoUong_1 = new JPanel();
        panelDoUong_1.setPreferredSize(new Dimension(250, 130));
        panelDoUong_line1.add(panelDoUong_1);
        panelDoUong_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

   
        lblHinhDoUong_1 = new JLabel("");
        lblHinhDoUong_1.setIcon(new ImageIcon(imgDoUong));
        lblHinhDoUong_1.setPreferredSize(new Dimension(125, 110));
        panelDoUong_1.add(lblHinhDoUong_1);

        panelDoUong_1_Right = new JPanel();
        panelDoUong_1_Right.setPreferredSize(new Dimension(110, 110));
        panelDoUong_1.add(panelDoUong_1_Right);
        panelDoUong_1_Right.setLayout(new BorderLayout(0, 0));

        panel_DoUong_1_Right_North = new JPanel();
        panel_DoUong_1_Right_North.setPreferredSize(new Dimension(10, 50));
        panelDoUong_1_Right.add(panel_DoUong_1_Right_North, BorderLayout.NORTH);
        panel_DoUong_1_Right_North.setLayout(new BoxLayout(panel_DoUong_1_Right_North, BoxLayout.Y_AXIS));

        lblDoUong_1_tieuDe = new JLabel("Soju truyền thống");
        lblDoUong_1_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblDoUong_1_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_1_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_1_Right_North.add(lblDoUong_1_tieuDe);

        lblDoUong_1_tenHanQuoc = new JLabel("소주");
        lblDoUong_1_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_1_Right_North.add(lblDoUong_1_tenHanQuoc);

        lblDoUong_1_thanhPhan = new JLabel("Rượu gạo, Nước");
        lblDoUong_1_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblDoUong_1_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_1_Right_North.add(lblDoUong_1_thanhPhan);

        panel_DoUong_1_Right_South = new JPanel();
        FlowLayout fl_panel_DoUong_1_Right_South = (FlowLayout) panel_DoUong_1_Right_South.getLayout();
        fl_panel_DoUong_1_Right_South.setAlignment(FlowLayout.RIGHT);
        panelDoUong_1_Right.add(panel_DoUong_1_Right_South, BorderLayout.SOUTH);

        btnDoUong_1_them = new JButton("Thêm");
        btnDoUong_1_them.setForeground(new Color(64, 0, 0));
        btnDoUong_1_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDoUong_1_them.setBackground(new Color(255, 128, 64));
        btnDoUong_1_them.setContentAreaFilled(true);
        btnDoUong_1_them.setOpaque(true);
        btnDoUong_1_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnDoUong_1_them.setPreferredSize(new Dimension(60, 23));
        panel_DoUong_1_Right_South.add(btnDoUong_1_them);

        lblDoUong_1_gia = new JLabel("20,000 VNĐ");
        lblDoUong_1_gia.setPreferredSize(new Dimension(40, 30));
        lblDoUong_1_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblDoUong_1_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_1_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDoUong_1_Right.add(lblDoUong_1_gia, BorderLayout.CENTER);

        // Đồ Uống 2: Makgeolli
        panelDoUong_2 = new JPanel();
        panelDoUong_2.setPreferredSize(new Dimension(250, 130));
        panelDoUong_line1.add(panelDoUong_2);
        panelDoUong_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhDoUong_2 = new JLabel("");
        lblHinhDoUong_2.setIcon(new ImageIcon(imgDoUong));
        lblHinhDoUong_2.setPreferredSize(new Dimension(125, 110));
        panelDoUong_2.add(lblHinhDoUong_2);

        panelDoUong_2_Right = new JPanel();
        panelDoUong_2_Right.setPreferredSize(new Dimension(110, 110));
        panelDoUong_2.add(panelDoUong_2_Right);
        panelDoUong_2_Right.setLayout(new BorderLayout(0, 0));

        panel_DoUong_2_Right_North = new JPanel();
        panel_DoUong_2_Right_North.setPreferredSize(new Dimension(10, 50));
        panelDoUong_2_Right.add(panel_DoUong_2_Right_North, BorderLayout.NORTH);
        panel_DoUong_2_Right_North.setLayout(new BoxLayout(panel_DoUong_2_Right_North, BoxLayout.Y_AXIS));

        lblDoUong_2_tieuDe = new JLabel("Makgeolli sữa gạo");
        lblDoUong_2_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblDoUong_2_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_2_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_2_Right_North.add(lblDoUong_2_tieuDe);

        lblDoUong_2_tenHanQuoc = new JLabel("막걸리");
        lblDoUong_2_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_2_Right_North.add(lblDoUong_2_tenHanQuoc);

        lblDoUong_2_thanhPhan = new JLabel("Rượu gạo, Sữa");
        lblDoUong_2_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblDoUong_2_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_2_Right_North.add(lblDoUong_2_thanhPhan);

        panel_DoUong_2_Right_South = new JPanel();
        FlowLayout fl_panel_DoUong_2_Right_South = (FlowLayout) panel_DoUong_2_Right_South.getLayout();
        fl_panel_DoUong_2_Right_South.setAlignment(FlowLayout.RIGHT);
        panelDoUong_2_Right.add(panel_DoUong_2_Right_South, BorderLayout.SOUTH);

        btnDoUong_2_them = new JButton("Thêm");
        btnDoUong_2_them.setForeground(new Color(64, 0, 0));
        btnDoUong_2_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDoUong_2_them.setBackground(new Color(255, 128, 64));
        btnDoUong_2_them.setContentAreaFilled(true);
        btnDoUong_2_them.setOpaque(true);
        btnDoUong_2_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnDoUong_2_them.setPreferredSize(new Dimension(60, 23));
        panel_DoUong_2_Right_South.add(btnDoUong_2_them);

        lblDoUong_2_gia = new JLabel("25,000 VNĐ");
        lblDoUong_2_gia.setPreferredSize(new Dimension(40, 30));
        lblDoUong_2_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblDoUong_2_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_2_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDoUong_2_Right.add(lblDoUong_2_gia, BorderLayout.CENTER);

        // Đồ Uống 3: Beer
        panelDoUong_3 = new JPanel();
        panelDoUong_3.setPreferredSize(new Dimension(250, 130));
        panelDoUong_line1.add(panelDoUong_3);
        panelDoUong_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhDoUong_3 = new JLabel("");
        lblHinhDoUong_3.setIcon(new ImageIcon(imgDoUong));
        lblHinhDoUong_3.setPreferredSize(new Dimension(125, 110));
        panelDoUong_3.add(lblHinhDoUong_3);

        panelDoUong_3_Right = new JPanel();
        panelDoUong_3_Right.setPreferredSize(new Dimension(110, 110));
        panelDoUong_3.add(panelDoUong_3_Right);
        panelDoUong_3_Right.setLayout(new BorderLayout(0, 0));

        panel_DoUong_3_Right_North = new JPanel();
        panel_DoUong_3_Right_North.setPreferredSize(new Dimension(10, 50));
        panelDoUong_3_Right.add(panel_DoUong_3_Right_North, BorderLayout.NORTH);
        panel_DoUong_3_Right_North.setLayout(new BoxLayout(panel_DoUong_3_Right_North, BoxLayout.Y_AXIS));

        lblDoUong_3_tieuDe = new JLabel("Bia Hàn Quốc");
        lblDoUong_3_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblDoUong_3_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_3_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_3_Right_North.add(lblDoUong_3_tieuDe);

        lblDoUong_3_tenHanQuoc = new JLabel("맥주");
        lblDoUong_3_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_3_Right_North.add(lblDoUong_3_tenHanQuoc);

        lblDoUong_3_thanhPhan = new JLabel("Bia, Lúa mạch");
        lblDoUong_3_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblDoUong_3_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_3_Right_North.add(lblDoUong_3_thanhPhan);

        panel_DoUong_3_Right_South = new JPanel();
        FlowLayout fl_panel_DoUong_3_Right_South = (FlowLayout) panel_DoUong_3_Right_South.getLayout();
        fl_panel_DoUong_3_Right_South.setAlignment(FlowLayout.RIGHT);
        panelDoUong_3_Right.add(panel_DoUong_3_Right_South, BorderLayout.SOUTH);

        btnDoUong_3_them = new JButton("Thêm");
        btnDoUong_3_them.setForeground(new Color(64, 0, 0));
        btnDoUong_3_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDoUong_3_them.setBackground(new Color(255, 128, 64));
        btnDoUong_3_them.setContentAreaFilled(true);
        btnDoUong_3_them.setOpaque(true);
        btnDoUong_3_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnDoUong_3_them.setPreferredSize(new Dimension(60, 23));
        panel_DoUong_3_Right_South.add(btnDoUong_3_them);

        lblDoUong_3_gia = new JLabel("30,000 VNĐ");
        lblDoUong_3_gia.setPreferredSize(new Dimension(40, 30));
        lblDoUong_3_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblDoUong_3_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_3_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDoUong_3_Right.add(lblDoUong_3_gia, BorderLayout.CENTER);

        // Line 2 for Đồ Uống
        panelDoUong_line2 = new JPanel();
        panelDoUong_line2.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_du_line2 = (FlowLayout) panelDoUong_line2.getLayout();
        flowLayout_du_line2.setHgap(10);
        panelDoUong_line2.setBackground(new Color(255, 255, 255));
        panelDoUong.add(panelDoUong_line2);
        panelDoUong.add(Box.createVerticalStrut(10));

        // Đồ Uống 4: Green Tea
        panelDoUong_4 = new JPanel();
        panelDoUong_4.setPreferredSize(new Dimension(250, 130));
        panelDoUong_line2.add(panelDoUong_4);
        panelDoUong_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhDoUong_4 = new JLabel("");
        lblHinhDoUong_4.setIcon(new ImageIcon(imgDoUong));
        lblHinhDoUong_4.setPreferredSize(new Dimension(125, 110));
        panelDoUong_4.add(lblHinhDoUong_4);

        panelDoUong_4_Right = new JPanel();
        panelDoUong_4_Right.setPreferredSize(new Dimension(110, 110));
        panelDoUong_4.add(panelDoUong_4_Right);
        panelDoUong_4_Right.setLayout(new BorderLayout(0, 0));

        panel_DoUong_4_Right_North = new JPanel();
        panel_DoUong_4_Right_North.setPreferredSize(new Dimension(10, 50));
        panelDoUong_4_Right.add(panel_DoUong_4_Right_North, BorderLayout.NORTH);
        panel_DoUong_4_Right_North.setLayout(new BoxLayout(panel_DoUong_4_Right_North, BoxLayout.Y_AXIS));

        lblDoUong_4_tieuDe = new JLabel("Trà xanh");
        lblDoUong_4_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblDoUong_4_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_4_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_4_Right_North.add(lblDoUong_4_tieuDe);

        lblDoUong_4_tenHanQuoc = new JLabel("녹차");
        lblDoUong_4_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_4_Right_North.add(lblDoUong_4_tenHanQuoc);

        lblDoUong_4_thanhPhan = new JLabel("Lá trà, Nước");
        lblDoUong_4_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblDoUong_4_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_4_Right_North.add(lblDoUong_4_thanhPhan);

        panel_DoUong_4_Right_South = new JPanel();
        FlowLayout fl_panel_DoUong_4_Right_South = (FlowLayout) panel_DoUong_4_Right_South.getLayout();
        fl_panel_DoUong_4_Right_South.setAlignment(FlowLayout.RIGHT);
        panelDoUong_4_Right.add(panel_DoUong_4_Right_South, BorderLayout.SOUTH);

        btnDoUong_4_them = new JButton("Thêm");
        btnDoUong_4_them.setForeground(new Color(64, 0, 0));
        btnDoUong_4_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDoUong_4_them.setBackground(new Color(255, 128, 64));
        btnDoUong_4_them.setContentAreaFilled(true);
        btnDoUong_4_them.setOpaque(true);
        btnDoUong_4_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnDoUong_4_them.setPreferredSize(new Dimension(60, 23));
        panel_DoUong_4_Right_South.add(btnDoUong_4_them);

        lblDoUong_4_gia = new JLabel("15,000 VNĐ");
        lblDoUong_4_gia.setPreferredSize(new Dimension(40, 30));
        lblDoUong_4_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblDoUong_4_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_4_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDoUong_4_Right.add(lblDoUong_4_gia, BorderLayout.CENTER);

        // Đồ Uống 5: Banana Milk
        panelDoUong_5 = new JPanel();
        panelDoUong_5.setPreferredSize(new Dimension(250, 130));
        panelDoUong_line2.add(panelDoUong_5);
        panelDoUong_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhDoUong_5 = new JLabel("");
        lblHinhDoUong_5.setIcon(new ImageIcon(imgDoUong));
        lblHinhDoUong_5.setPreferredSize(new Dimension(125, 110));
        panelDoUong_5.add(lblHinhDoUong_5);

        panelDoUong_5_Right = new JPanel();
        panelDoUong_5_Right.setPreferredSize(new Dimension(110, 110));
        panelDoUong_5.add(panelDoUong_5_Right);
        panelDoUong_5_Right.setLayout(new BorderLayout(0, 0));

        panel_DoUong_5_Right_North = new JPanel();
        panel_DoUong_5_Right_North.setPreferredSize(new Dimension(10, 50));
        panelDoUong_5_Right.add(panel_DoUong_5_Right_North, BorderLayout.NORTH);
        panel_DoUong_5_Right_North.setLayout(new BoxLayout(panel_DoUong_5_Right_North, BoxLayout.Y_AXIS));

        lblDoUong_5_tieuDe = new JLabel("Sữa chuối");
        lblDoUong_5_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblDoUong_5_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_5_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_5_Right_North.add(lblDoUong_5_tieuDe);

        lblDoUong_5_tenHanQuoc = new JLabel("바나나 우유");
        lblDoUong_5_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_5_Right_North.add(lblDoUong_5_tenHanQuoc);

        lblDoUong_5_thanhPhan = new JLabel("Sữa, Chuối");
        lblDoUong_5_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblDoUong_5_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_5_Right_North.add(lblDoUong_5_thanhPhan);

        panel_DoUong_5_Right_South = new JPanel();
        FlowLayout fl_panel_DoUong_5_Right_South = (FlowLayout) panel_DoUong_5_Right_South.getLayout();
        fl_panel_DoUong_5_Right_South.setAlignment(FlowLayout.RIGHT);
        panelDoUong_5_Right.add(panel_DoUong_5_Right_South, BorderLayout.SOUTH);

        btnDoUong_5_them = new JButton("Thêm");
        btnDoUong_5_them.setForeground(new Color(64, 0, 0));
        btnDoUong_5_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDoUong_5_them.setBackground(new Color(255, 128, 64));
        btnDoUong_5_them.setContentAreaFilled(true);
        btnDoUong_5_them.setOpaque(true);
        btnDoUong_5_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnDoUong_5_them.setPreferredSize(new Dimension(60, 23));
        panel_DoUong_5_Right_South.add(btnDoUong_5_them);

        lblDoUong_5_gia = new JLabel("10,000 VNĐ");
        lblDoUong_5_gia.setPreferredSize(new Dimension(40, 30));
        lblDoUong_5_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblDoUong_5_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_5_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDoUong_5_Right.add(lblDoUong_5_gia, BorderLayout.CENTER);

        // Đồ Uống 6: Sikhye
        panelDoUong_6 = new JPanel();
        panelDoUong_6.setPreferredSize(new Dimension(250, 130));
        panelDoUong_line2.add(panelDoUong_6);
        panelDoUong_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhDoUong_6 = new JLabel("");
        lblHinhDoUong_6.setIcon(new ImageIcon(imgDoUong));
        lblHinhDoUong_6.setPreferredSize(new Dimension(125, 110));
        panelDoUong_6.add(lblHinhDoUong_6);

        panelDoUong_6_Right = new JPanel();
        panelDoUong_6_Right.setPreferredSize(new Dimension(110, 110));
        panelDoUong_6.add(panelDoUong_6_Right);
        panelDoUong_6_Right.setLayout(new BorderLayout(0, 0));

        panel_DoUong_6_Right_North = new JPanel();
        panel_DoUong_6_Right_North.setPreferredSize(new Dimension(10, 50));
        panelDoUong_6_Right.add(panel_DoUong_6_Right_North, BorderLayout.NORTH);
        panel_DoUong_6_Right_North.setLayout(new BoxLayout(panel_DoUong_6_Right_North, BoxLayout.Y_AXIS));

        lblDoUong_6_tieuDe = new JLabel("Nước gạo ngọt");
        lblDoUong_6_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblDoUong_6_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_6_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_6_Right_North.add(lblDoUong_6_tieuDe);

        lblDoUong_6_tenHanQuoc = new JLabel("식혜");
        lblDoUong_6_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_6_Right_North.add(lblDoUong_6_tenHanQuoc);

        lblDoUong_6_thanhPhan = new JLabel("Gạo, Đường");
        lblDoUong_6_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblDoUong_6_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_DoUong_6_Right_North.add(lblDoUong_6_thanhPhan);

        panel_DoUong_6_Right_South = new JPanel();
        FlowLayout fl_panel_DoUong_6_Right_South = (FlowLayout) panel_DoUong_6_Right_South.getLayout();
        fl_panel_DoUong_6_Right_South.setAlignment(FlowLayout.RIGHT);
        panelDoUong_6_Right.add(panel_DoUong_6_Right_South, BorderLayout.SOUTH);

        btnDoUong_6_them = new JButton("Thêm");
        btnDoUong_6_them.setForeground(new Color(64, 0, 0));
        btnDoUong_6_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnDoUong_6_them.setBackground(new Color(255, 128, 64));
        btnDoUong_6_them.setContentAreaFilled(true);
        btnDoUong_6_them.setOpaque(true);
        btnDoUong_6_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnDoUong_6_them.setPreferredSize(new Dimension(60, 23));
        panel_DoUong_6_Right_South.add(btnDoUong_6_them);

        lblDoUong_6_gia = new JLabel("10,000 VNĐ");
        lblDoUong_6_gia.setPreferredSize(new Dimension(40, 30));
        lblDoUong_6_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblDoUong_6_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDoUong_6_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelDoUong_6_Right.add(lblDoUong_6_gia, BorderLayout.CENTER);

        // Panel for Món Ăn Kèm
        panelMonAnKem = new JPanel();
        panelMonAnKem.setPreferredSize(new Dimension(780, 300));
        panelMonAnKem.setLayout(new BoxLayout(panelMonAnKem, BoxLayout.Y_AXIS));
        panelMonAnKem.setBackground(new Color(255, 255, 255));

        // Line 1 for Món Ăn Kèm
        panelMonAnKem_line1 = new JPanel();
        panelMonAnKem_line1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_kem_line1 = (FlowLayout) panelMonAnKem_line1.getLayout();
        flowLayout_kem_line1.setHgap(10);
        panelMonAnKem_line1.setBackground(new Color(255, 255, 255));
        panelMonAnKem.add(panelMonAnKem_line1);
        panelMonAnKem.add(Box.createVerticalStrut(10));

        // Món Ăn Kèm 1: Kimchi
        panelMonAnKem_1 = new JPanel();
        panelMonAnKem_1.setPreferredSize(new Dimension(250, 130));
        panelMonAnKem_line1.add(panelMonAnKem_1);
        panelMonAnKem_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        ImageIcon iconSaLad = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\salad_icon.png");
        Image imgSalad = iconSaLad.getImage().getScaledInstance(125, 110, Image.SCALE_SMOOTH);
        lblHinhMonAnKem_1 = new JLabel("");
        lblHinhMonAnKem_1.setIcon(new ImageIcon(imgSalad));
        lblHinhMonAnKem_1.setPreferredSize(new Dimension(125, 110));
        panelMonAnKem_1.add(lblHinhMonAnKem_1);

        panelMonAnKem_1_Right = new JPanel();
        panelMonAnKem_1_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAnKem_1.add(panelMonAnKem_1_Right);
        panelMonAnKem_1_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAnKem_1_Right_North = new JPanel();
        panel_MonAnKem_1_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAnKem_1_Right.add(panel_MonAnKem_1_Right_North, BorderLayout.NORTH);
        panel_MonAnKem_1_Right_North.setLayout(new BoxLayout(panel_MonAnKem_1_Right_North, BoxLayout.Y_AXIS));

        lblMonAnKem_1_tieuDe = new JLabel("Kimchi bắp cải");
        lblMonAnKem_1_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAnKem_1_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_1_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_1_Right_North.add(lblMonAnKem_1_tieuDe);

        lblMonAnKem_1_tenHanQuoc = new JLabel("김치");
        lblMonAnKem_1_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_1_Right_North.add(lblMonAnKem_1_tenHanQuoc);

        lblMonAnKem_1_thanhPhan = new JLabel("Bắp cải, Ớt bột");
        lblMonAnKem_1_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAnKem_1_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_1_Right_North.add(lblMonAnKem_1_thanhPhan);

        panel_MonAnKem_1_Right_South = new JPanel();
        FlowLayout fl_panel_MonAnKem_1_Right_South = (FlowLayout) panel_MonAnKem_1_Right_South.getLayout();
        fl_panel_MonAnKem_1_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAnKem_1_Right.add(panel_MonAnKem_1_Right_South, BorderLayout.SOUTH);

        btnMonAnKem_1_them = new JButton("Thêm");
        btnMonAnKem_1_them.setForeground(new Color(64, 0, 0));
        btnMonAnKem_1_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAnKem_1_them.setBackground(new Color(255, 128, 64));
        btnMonAnKem_1_them.setContentAreaFilled(true);
        btnMonAnKem_1_them.setOpaque(true);
        btnMonAnKem_1_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAnKem_1_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAnKem_1_Right_South.add(btnMonAnKem_1_them);

        lblMonAnKem_1_gia = new JLabel("10,000 VNĐ");
        lblMonAnKem_1_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAnKem_1_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAnKem_1_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_1_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAnKem_1_Right.add(lblMonAnKem_1_gia, BorderLayout.CENTER);

        // Món Ăn Kèm 2: Namul
        panelMonAnKem_2 = new JPanel();
        panelMonAnKem_2.setPreferredSize(new Dimension(250, 130));
        panelMonAnKem_line1.add(panelMonAnKem_2);
        panelMonAnKem_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAnKem_2 = new JLabel("");
        lblHinhMonAnKem_2.setIcon(new ImageIcon(imgSalad));
        lblHinhMonAnKem_2.setPreferredSize(new Dimension(125, 110));
        panelMonAnKem_2.add(lblHinhMonAnKem_2);

        panelMonAnKem_2_Right = new JPanel();
        panelMonAnKem_2_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAnKem_2.add(panelMonAnKem_2_Right);
        panelMonAnKem_2_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAnKem_2_Right_North = new JPanel();
        panel_MonAnKem_2_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAnKem_2_Right.add(panel_MonAnKem_2_Right_North, BorderLayout.NORTH);
        panel_MonAnKem_2_Right_North.setLayout(new BoxLayout(panel_MonAnKem_2_Right_North, BoxLayout.Y_AXIS));

        lblMonAnKem_2_tieuDe = new JLabel("Rau trộn");
        lblMonAnKem_2_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAnKem_2_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_2_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_2_Right_North.add(lblMonAnKem_2_tieuDe);

        lblMonAnKem_2_tenHanQuoc = new JLabel("나물");
        lblMonAnKem_2_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_2_Right_North.add(lblMonAnKem_2_tenHanQuoc);

        lblMonAnKem_2_thanhPhan = new JLabel("Rau củ, Gia vị");
        lblMonAnKem_2_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAnKem_2_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_2_Right_North.add(lblMonAnKem_2_thanhPhan);

        panel_MonAnKem_2_Right_South = new JPanel();
        FlowLayout fl_panel_MonAnKem_2_Right_South = (FlowLayout) panel_MonAnKem_2_Right_South.getLayout();
        fl_panel_MonAnKem_2_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAnKem_2_Right.add(panel_MonAnKem_2_Right_South, BorderLayout.SOUTH);

        btnMonAnKem_2_them = new JButton("Thêm");
        btnMonAnKem_2_them.setForeground(new Color(64, 0, 0));
        btnMonAnKem_2_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAnKem_2_them.setBackground(new Color(255, 128, 64));
        btnMonAnKem_2_them.setContentAreaFilled(true);
        btnMonAnKem_2_them.setOpaque(true);
        btnMonAnKem_2_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAnKem_2_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAnKem_2_Right_South.add(btnMonAnKem_2_them);

        lblMonAnKem_2_gia = new JLabel("8,000 VNĐ");
        lblMonAnKem_2_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAnKem_2_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAnKem_2_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_2_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAnKem_2_Right.add(lblMonAnKem_2_gia, BorderLayout.CENTER);

        // Món Ăn Kèm 3: Japchae
        panelMonAnKem_3 = new JPanel();
        panelMonAnKem_3.setPreferredSize(new Dimension(250, 130));
        panelMonAnKem_line1.add(panelMonAnKem_3);
        panelMonAnKem_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAnKem_3 = new JLabel("");
        lblHinhMonAnKem_3.setIcon(new ImageIcon(imgSalad));
        lblHinhMonAnKem_3.setPreferredSize(new Dimension(125, 110));
        panelMonAnKem_3.add(lblHinhMonAnKem_3);

        panelMonAnKem_3_Right = new JPanel();
        panelMonAnKem_3_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAnKem_3.add(panelMonAnKem_3_Right);
        panelMonAnKem_3_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAnKem_3_Right_North = new JPanel();
        panel_MonAnKem_3_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAnKem_3_Right.add(panel_MonAnKem_3_Right_North, BorderLayout.NORTH);
        panel_MonAnKem_3_Right_North.setLayout(new BoxLayout(panel_MonAnKem_3_Right_North, BoxLayout.Y_AXIS));

        lblMonAnKem_3_tieuDe = new JLabel("Miến trộn");
        lblMonAnKem_3_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAnKem_3_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_3_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_3_Right_North.add(lblMonAnKem_3_tieuDe);

        lblMonAnKem_3_tenHanQuoc = new JLabel("잡채");
        lblMonAnKem_3_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_3_Right_North.add(lblMonAnKem_3_tenHanQuoc);

        lblMonAnKem_3_thanhPhan = new JLabel("Miến, Rau củ");
        lblMonAnKem_3_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAnKem_3_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_3_Right_North.add(lblMonAnKem_3_thanhPhan);

        panel_MonAnKem_3_Right_South = new JPanel();
        FlowLayout fl_panel_MonAnKem_3_Right_South = (FlowLayout) panel_MonAnKem_3_Right_South.getLayout();
        fl_panel_MonAnKem_3_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAnKem_3_Right.add(panel_MonAnKem_3_Right_South, BorderLayout.SOUTH);

        btnMonAnKem_3_them = new JButton("Thêm");
        btnMonAnKem_3_them.setForeground(new Color(64, 0, 0));
        btnMonAnKem_3_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAnKem_3_them.setBackground(new Color(255, 128, 64));
        btnMonAnKem_3_them.setContentAreaFilled(true);
        btnMonAnKem_3_them.setOpaque(true);
        btnMonAnKem_3_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAnKem_3_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAnKem_3_Right_South.add(btnMonAnKem_3_them);

        lblMonAnKem_3_gia = new JLabel("15,000 VNĐ");
        lblMonAnKem_3_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAnKem_3_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAnKem_3_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_3_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAnKem_3_Right.add(lblMonAnKem_3_gia, BorderLayout.CENTER);

        // Line 2 for Món Ăn Kèm
        panelMonAnKem_line2 = new JPanel();
        panelMonAnKem_line2.setBorder(new LineBorder(new Color(0, 0, 0), 0));
        FlowLayout flowLayout_kem_line2 = (FlowLayout) panelMonAnKem_line2.getLayout();
        flowLayout_kem_line2.setHgap(10);
        panelMonAnKem_line2.setBackground(new Color(255, 255, 255));
        panelMonAnKem.add(panelMonAnKem_line2);
        panelMonAnKem.add(Box.createVerticalStrut(10));

        // Món Ăn Kèm 4: Gyeran Jjim
        panelMonAnKem_4 = new JPanel();
        panelMonAnKem_4.setPreferredSize(new Dimension(250, 130));
        panelMonAnKem_line2.add(panelMonAnKem_4);
        panelMonAnKem_4.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAnKem_4 = new JLabel("");
        lblHinhMonAnKem_4.setIcon(new ImageIcon(imgSalad));
        lblHinhMonAnKem_4.setPreferredSize(new Dimension(125, 110));
        panelMonAnKem_4.add(lblHinhMonAnKem_4);

        panelMonAnKem_4_Right = new JPanel();
        panelMonAnKem_4_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAnKem_4.add(panelMonAnKem_4_Right);
        panelMonAnKem_4_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAnKem_4_Right_North = new JPanel();
        panel_MonAnKem_4_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAnKem_4_Right.add(panel_MonAnKem_4_Right_North, BorderLayout.NORTH);
        panel_MonAnKem_4_Right_North.setLayout(new BoxLayout(panel_MonAnKem_4_Right_North, BoxLayout.Y_AXIS));

        lblMonAnKem_4_tieuDe = new JLabel("Trứng hấp");
        lblMonAnKem_4_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAnKem_4_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_4_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_4_Right_North.add(lblMonAnKem_4_tieuDe);

        lblMonAnKem_4_tenHanQuoc = new JLabel("계란찜");
        lblMonAnKem_4_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_4_Right_North.add(lblMonAnKem_4_tenHanQuoc);

        lblMonAnKem_4_thanhPhan = new JLabel("Trứng, Hành lá");
        lblMonAnKem_4_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAnKem_4_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_4_Right_North.add(lblMonAnKem_4_thanhPhan);

        panel_MonAnKem_4_Right_South = new JPanel();
        FlowLayout fl_panel_MonAnKem_4_Right_South = (FlowLayout) panel_MonAnKem_4_Right_South.getLayout();
        fl_panel_MonAnKem_4_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAnKem_4_Right.add(panel_MonAnKem_4_Right_South, BorderLayout.SOUTH);

        btnMonAnKem_4_them = new JButton("Thêm");
        btnMonAnKem_4_them.setForeground(new Color(64, 0, 0));
        btnMonAnKem_4_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAnKem_4_them.setBackground(new Color(255, 128, 64));
        btnMonAnKem_4_them.setContentAreaFilled(true);
        btnMonAnKem_4_them.setOpaque(true);
        btnMonAnKem_4_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAnKem_4_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAnKem_4_Right_South.add(btnMonAnKem_4_them);

        lblMonAnKem_4_gia = new JLabel("12,000 VNĐ");
        lblMonAnKem_4_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAnKem_4_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAnKem_4_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_4_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAnKem_4_Right.add(lblMonAnKem_4_gia, BorderLayout.CENTER);

        // Món Ăn Kèm 5: Dubu Jorim
        panelMonAnKem_5 = new JPanel();
        panelMonAnKem_5.setPreferredSize(new Dimension(250, 130));
        panelMonAnKem_line2.add(panelMonAnKem_5);
        panelMonAnKem_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAnKem_5 = new JLabel("");
        lblHinhMonAnKem_5.setIcon(new ImageIcon(imgSalad));
        lblHinhMonAnKem_5.setPreferredSize(new Dimension(125, 110));
        panelMonAnKem_5.add(lblHinhMonAnKem_5);

        panelMonAnKem_5_Right = new JPanel();
        panelMonAnKem_5_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAnKem_5.add(panelMonAnKem_5_Right);
        panelMonAnKem_5_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAnKem_5_Right_North = new JPanel();
        panel_MonAnKem_5_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAnKem_5_Right.add(panel_MonAnKem_5_Right_North, BorderLayout.NORTH);
        panel_MonAnKem_5_Right_North.setLayout(new BoxLayout(panel_MonAnKem_5_Right_North, BoxLayout.Y_AXIS));

        lblMonAnKem_5_tieuDe = new JLabel("Đậu hũ kho");
        lblMonAnKem_5_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAnKem_5_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_5_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_5_Right_North.add(lblMonAnKem_5_tieuDe);

        lblMonAnKem_5_tenHanQuoc = new JLabel("두부조림");
        lblMonAnKem_5_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_5_Right_North.add(lblMonAnKem_5_tenHanQuoc);

        lblMonAnKem_5_thanhPhan = new JLabel("Đậu hũ, Nước tương");
        lblMonAnKem_5_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAnKem_5_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_5_Right_North.add(lblMonAnKem_5_thanhPhan);

        panel_MonAnKem_5_Right_South = new JPanel();
        FlowLayout fl_panel_MonAnKem_5_Right_South = (FlowLayout) panel_MonAnKem_5_Right_South.getLayout();
        fl_panel_MonAnKem_5_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAnKem_5_Right.add(panel_MonAnKem_5_Right_South, BorderLayout.SOUTH);

        btnMonAnKem_5_them = new JButton("Thêm");
        btnMonAnKem_5_them.setForeground(new Color(64, 0, 0));
        btnMonAnKem_5_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAnKem_5_them.setBackground(new Color(255, 128, 64));
        btnMonAnKem_5_them.setContentAreaFilled(true);
        btnMonAnKem_5_them.setOpaque(true);
        btnMonAnKem_5_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAnKem_5_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAnKem_5_Right_South.add(btnMonAnKem_5_them);

        lblMonAnKem_5_gia = new JLabel("10,000 VNĐ");
        lblMonAnKem_5_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAnKem_5_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAnKem_5_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_5_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAnKem_5_Right.add(lblMonAnKem_5_gia, BorderLayout.CENTER);

        // Món Ăn Kèm 6: Oi Muchim
        panelMonAnKem_6 = new JPanel();
        panelMonAnKem_6.setPreferredSize(new Dimension(250, 130));
        panelMonAnKem_line2.add(panelMonAnKem_6);
        panelMonAnKem_6.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        lblHinhMonAnKem_6 = new JLabel("");
        lblHinhMonAnKem_6.setIcon(new ImageIcon(imgSalad));
        lblHinhMonAnKem_6.setPreferredSize(new Dimension(125, 110));
        panelMonAnKem_6.add(lblHinhMonAnKem_6);

        panelMonAnKem_6_Right = new JPanel();
        panelMonAnKem_6_Right.setPreferredSize(new Dimension(110, 110));
        panelMonAnKem_6.add(panelMonAnKem_6_Right);
        panelMonAnKem_6_Right.setLayout(new BorderLayout(0, 0));

        panel_MonAnKem_6_Right_North = new JPanel();
        panel_MonAnKem_6_Right_North.setPreferredSize(new Dimension(10, 50));
        panelMonAnKem_6_Right.add(panel_MonAnKem_6_Right_North, BorderLayout.NORTH);
        panel_MonAnKem_6_Right_North.setLayout(new BoxLayout(panel_MonAnKem_6_Right_North, BoxLayout.Y_AXIS));

        lblMonAnKem_6_tieuDe = new JLabel("Dưa chuột trộn");
        lblMonAnKem_6_tieuDe.setPreferredSize(new Dimension(100, 48));
        lblMonAnKem_6_tieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_6_tieuDe.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_6_Right_North.add(lblMonAnKem_6_tieuDe);

        lblMonAnKem_6_tenHanQuoc = new JLabel("오이무침");
        lblMonAnKem_6_tenHanQuoc.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_6_Right_North.add(lblMonAnKem_6_tenHanQuoc);

        lblMonAnKem_6_thanhPhan = new JLabel("Dưa chuột, Gia vị");
        lblMonAnKem_6_thanhPhan.setFont(new Font("Segoe UI", Font.ITALIC, 8));
        lblMonAnKem_6_thanhPhan.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel_MonAnKem_6_Right_North.add(lblMonAnKem_6_thanhPhan);

        panel_MonAnKem_6_Right_South = new JPanel();
        FlowLayout fl_panel_MonAnKem_6_Right_South = (FlowLayout) panel_MonAnKem_6_Right_South.getLayout();
        fl_panel_MonAnKem_6_Right_South.setAlignment(FlowLayout.RIGHT);
        panelMonAnKem_6_Right.add(panel_MonAnKem_6_Right_South, BorderLayout.SOUTH);

        btnMonAnKem_6_them = new JButton("Thêm");
        btnMonAnKem_6_them.setForeground(new Color(64, 0, 0));
        btnMonAnKem_6_them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnMonAnKem_6_them.setBackground(new Color(255, 128, 64));
        btnMonAnKem_6_them.setContentAreaFilled(true);
        btnMonAnKem_6_them.setOpaque(true);
        btnMonAnKem_6_them.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        btnMonAnKem_6_them.setPreferredSize(new Dimension(60, 23));
        panel_MonAnKem_6_Right_South.add(btnMonAnKem_6_them);

        lblMonAnKem_6_gia = new JLabel("8,000 VNĐ");
        lblMonAnKem_6_gia.setPreferredSize(new Dimension(40, 30));
        lblMonAnKem_6_gia.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(0, 0, 0, 80)));
        lblMonAnKem_6_gia.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblMonAnKem_6_gia.setHorizontalAlignment(SwingConstants.CENTER);
        panelMonAnKem_6_Right.add(lblMonAnKem_6_gia, BorderLayout.CENTER);

        // Thêm actionListener cho các nút
        btnDanhSachMonAn_MonAnChinh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(panelCenterCenter);
            }
        });

        btnDanhSachMonAn_DoUong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(panelDoUong);
            }
        });

        btnDanhSachMonAn_MonAnKem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scrollPane.setViewportView(panelMonAnKem);
            }
        });

        
        panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBackground(new Color(255, 255, 255));
        panel.setPreferredSize(new Dimension(10, 150));
        panelCenter.add(panel);
        panel.setLayout(null);
        
        JLabel lblTongTienSanPham = new JLabel("Tổng tiền sản phẩm:");
        lblTongTienSanPham.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTongTienSanPham.setPreferredSize(new Dimension(120, 50));
        lblTongTienSanPham.setBounds(44, 25, 198, 30);
        panel.add(lblTongTienSanPham);
        
        lblGiaTongTienSanPham = new JLabel("826,000 VNĐ");
        lblGiaTongTienSanPham.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGiaTongTienSanPham.setBounds(253, 28, 126, 22);
        panel.add(lblGiaTongTienSanPham);
        
        lblGiamGia = new JLabel("Giảm giá:");
        lblGiamGia.setPreferredSize(new Dimension(120, 50));
        lblGiamGia.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblGiamGia.setBounds(44, 78, 198, 30);
        panel.add(lblGiamGia);
        
        textFieldGiamGia = new JTextField();
        textFieldGiamGia.setFont(new Font("Segoe UI", Font.PLAIN, 17));
        textFieldGiamGia.setText("0%");
        textFieldGiamGia.setBounds(234, 66, 145, 35);
        panel.add(textFieldGiamGia);
        textFieldGiamGia.setColumns(10);
        textFieldGiamGia.setEditable(false);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel_1.setBounds(10, 11, 417, 115);
        panel.add(panel_1);
        
        JLabel lblTongCong = new JLabel("Tổng cộng:");
        lblTongCong.setPreferredSize(new Dimension(120, 50));
        lblTongCong.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTongCong.setBounds(466, 25, 110, 30);
        panel.add(lblTongCong);
        
        JLabel lblGiaTongTienSanPham_1 = new JLabel("826,000 VNĐ");
        lblGiaTongTienSanPham_1.setForeground(new Color(255, 0, 0));
        lblGiaTongTienSanPham_1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblGiaTongTienSanPham_1.setBounds(600, 28, 126, 22);
        panel.add(lblGiaTongTienSanPham_1);
        
        JButton btHuy = new JButton("Hủy");
        btHuy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btHuy.setForeground(Color.WHITE);
        btHuy.setBackground(Color.BLACK);
        btHuy.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btHuy.setBounds(476, 69, 96, 49);
        panel.add(btHuy);
        
        btnThanhToan = new JButton("Thanh toán");
        btnThanhToan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnThanhToan.setBackground(Color.RED);
        btnThanhToan.setForeground(new Color(255, 255, 255));
        btnThanhToan.setMaximumSize(new Dimension(51, 23));
        btnThanhToan.setPreferredSize(new Dimension(51, 23));
        btnThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnThanhToan.setBounds(588, 65, 130, 56);
        panel.add(btnThanhToan);

        panelEast = new JPanel();
        panelEast.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEast.setBackground(Color.WHITE);
        contentPane.add(panelEast, BorderLayout.CENTER);
        panelEast.setLayout(null);
        
        panelEastTop = new JPanel();
        panelEastTop.setBounds(35, 11, 299, 90);
        panelEast.add(panelEastTop);
        panelEastTop.setLayout(null);
        
        lblDanhSachDatMon = new JLabel("Danh sách đặt món");
        lblDanhSachDatMon.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblDanhSachDatMon.setBounds(10, 37, 180, 25);
        panelEastTop.add(lblDanhSachDatMon);
        
        JComboBox<String> comboBan = new JComboBox<>();
        for (int i = 1; i <= 9; i++) {
            comboBan.addItem("Bàn số " + i);
        }

        comboBan.setBackground(Color.WHITE);
        comboBan.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0,0,0,80))); 

        comboBan.setBounds(187, 37, 102, 30);
        panelEastTop.add(comboBan);
        
     // Tạo nhóm
        ButtonGroup group = new ButtonGroup();

        JRadioButton rdbtnAnTaiBan = new JRadioButton("Ăn tại bàn");
        rdbtnAnTaiBan.setFont(new Font("Segoe UI", Font.PLAIN, 11)); // font nhỏ hơn
        rdbtnAnTaiBan.setBounds(138, 7, 80, 20);
        panelEastTop.add(rdbtnAnTaiBan);
        group.add(rdbtnAnTaiBan);

        JRadioButton rdbtMangDi = new JRadioButton("Mang đi");
        rdbtMangDi.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        rdbtMangDi.setBounds(220, 7, 80, 20);
        panelEastTop.add(rdbtMangDi);
        group.add(rdbtMangDi);
        
     // Initialize panelEastCenter
        panelEastCenter = new JPanel();
        panelEastCenter.setBorder(null);
        panelEastCenter.setBackground(Color.WHITE);
        panelEastCenter.setLayout(null); // Keep null layout for precise positioning
        panelEastCenter.setPreferredSize(new Dimension(354, 66 * 7)); // Adjust height based on number of lines (e.g., 7 lines * 66px per line)

        // Create JScrollPane for panelEastCenter
        JScrollPane eastCenterScrollPane = new JScrollPane(panelEastCenter);
        eastCenterScrollPane.setBounds(10, 104, 364, 317); // Match original bounds
        eastCenterScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        eastCenterScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add JScrollPane to panelEast instead of panelEastCenter directly
        panelEast.add(eastCenterScrollPane);
        
        panelEastCenter_Line1 = new JPanel();
        panelEastCenter_Line1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0,120)));
        panelEastCenter_Line1.setBounds(0, 11, 344, 66);
        panelEastCenter.add(panelEastCenter_Line1);
        panelEastCenter_Line1.setLayout(null);
        
        lblDanhSachDatMon_Mon1_TieuDe = new JLabel("Kimbap chiên giòn");
        lblDanhSachDatMon_Mon1_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon1_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon1_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon1_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line1.add(lblDanhSachDatMon_Mon1_TieuDe);
        
        lblDanhSachDatMon_Mon1_Gia = new JLabel("35,000");
        lblDanhSachDatMon_Mon1_Gia.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon1_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon1_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon1_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon1_Gia.setBounds(49, 37, 62, 18);
        lblDanhSachDatMon_Mon1_Gia.setForeground(new Color(0,0,0,180));
        panelEastCenter_Line1.add(lblDanhSachDatMon_Mon1_Gia);
        
        JLabel lblDanhSachMonAn_Mon1_SoLuong = new JLabel("2");
        lblDanhSachMonAn_Mon1_SoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachMonAn_Mon1_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0)));
        lblDanhSachMonAn_Mon1_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon1_SoLuong.setBounds(122, 11, 30, 44);
        panelEastCenter_Line1.add(lblDanhSachMonAn_Mon1_SoLuong);
        
        btnDanhSachMonAn_Mon1_Cong = new JButton("");
        btnDanhSachMonAn_Mon1_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon1_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon1_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon1_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon1_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line1.add(btnDanhSachMonAn_Mon1_Cong);
        ImageIcon iconAdd = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4 = iconAdd.getImage().getScaledInstance(btnDanhSachMonAn_Mon1_Cong.getWidth(), btnDanhSachMonAn_Mon1_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon1_Cong.setIcon(new ImageIcon(img4));
        
        
        btnDanhSachMonAn_Mon1_Tru = new JButton("");
        btnDanhSachMonAn_Mon1_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon1_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon1_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon1_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon1_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line1.add(btnDanhSachMonAn_Mon1_Tru);

        ImageIcon iconMinus = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5 = iconMinus.getImage().getScaledInstance(btnDanhSachMonAn_Mon1_Tru.getWidth(), btnDanhSachMonAn_Mon1_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon1_Tru.setIcon(new ImageIcon(img5));

        
        lblDanhSachDatMon_Mon1_GiaTong = new JLabel("70,000 VNĐ");
        lblDanhSachDatMon_Mon1_GiaTong.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon1_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon1_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon1_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon1_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon1_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line1.add(lblDanhSachDatMon_Mon1_GiaTong);
        
        btnImgThungRacIcon = new JButton("");
        btnImgThungRacIcon.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon.setOpaque(true);
        ImageIcon iconThungRac = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3 = iconThungRac.getImage().getScaledInstance(btnImgThungRacIcon.getWidth(), btnImgThungRacIcon.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon.setIcon(new ImageIcon(img3));
        
        panelEastCenter_Line1.add(btnImgThungRacIcon);
        
        // Line 2: Tokbokki truyền thống
        panelEastCenter_Line2 = new JPanel();
        panelEastCenter_Line2.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 120)));
        panelEastCenter_Line2.setBounds(0, 77, 344, 66);
        panelEastCenter.add(panelEastCenter_Line2);
        panelEastCenter_Line2.setLayout(null);

        lblDanhSachDatMon_Mon2_TieuDe = new JLabel("Tokbokki truyền thống");
        lblDanhSachDatMon_Mon2_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon2_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon2_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon2_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line2.add(lblDanhSachDatMon_Mon2_TieuDe);

        lblDanhSachDatMon_Mon2_Gia = new JLabel("43,000");
        lblDanhSachDatMon_Mon2_Gia.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon2_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon2_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon2_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon2_Gia.setForeground(new Color(0, 0, 0, 180));
        lblDanhSachDatMon_Mon2_Gia.setBounds(49, 37, 62, 18);
        panelEastCenter_Line2.add(lblDanhSachDatMon_Mon2_Gia);

        lblDanhSachMonAn_Mon2_SoLuong = new JLabel("1");
        lblDanhSachMonAn_Mon2_SoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachMonAn_Mon2_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblDanhSachMonAn_Mon2_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon2_SoLuong.setBounds(122, 11, 30, 44);
        panelEastCenter_Line2.add(lblDanhSachMonAn_Mon2_SoLuong);

        btnDanhSachMonAn_Mon2_Cong = new JButton("");
        btnDanhSachMonAn_Mon2_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon2_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon2_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon2_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon2_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line2.add(btnDanhSachMonAn_Mon2_Cong);
        ImageIcon iconAdd2 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4_2 = iconAdd2.getImage().getScaledInstance(btnDanhSachMonAn_Mon2_Cong.getWidth(), btnDanhSachMonAn_Mon2_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon2_Cong.setIcon(new ImageIcon(img4_2));

        btnDanhSachMonAn_Mon2_Tru = new JButton("");
        btnDanhSachMonAn_Mon2_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon2_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon2_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon2_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon2_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line2.add(btnDanhSachMonAn_Mon2_Tru);
        ImageIcon iconMinus2 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5_2 = iconMinus2.getImage().getScaledInstance(btnDanhSachMonAn_Mon2_Tru.getWidth(), btnDanhSachMonAn_Mon2_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon2_Tru.setIcon(new ImageIcon(img5_2));

        lblDanhSachDatMon_Mon2_GiaTong = new JLabel("43,000 VNĐ");
        lblDanhSachDatMon_Mon2_GiaTong.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon2_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon2_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon2_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon2_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon2_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line2.add(lblDanhSachDatMon_Mon2_GiaTong);

        btnImgThungRacIcon_Mon2 = new JButton("");
        btnImgThungRacIcon_Mon2.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon_Mon2.setOpaque(true);
        ImageIcon iconThungRac2 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3_2 = iconThungRac2.getImage().getScaledInstance(btnImgThungRacIcon_Mon2.getWidth(), btnImgThungRacIcon_Mon2.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon_Mon2.setIcon(new ImageIcon(img3_2));
        panelEastCenter_Line2.add(btnImgThungRacIcon_Mon2);

        // Line 3: Mì tương đen đặc biệt
        panelEastCenter_Line3 = new JPanel();
        panelEastCenter_Line3.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 120)));
        panelEastCenter_Line3.setBounds(0, 143, 344, 66);
        panelEastCenter.add(panelEastCenter_Line3);
        panelEastCenter_Line3.setLayout(null);

        lblDanhSachDatMon_Mon3_TieuDe = new JLabel("Mì tương đen đặc biệt");
        lblDanhSachDatMon_Mon3_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon3_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon3_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon3_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line3.add(lblDanhSachDatMon_Mon3_TieuDe);

        lblDanhSachDatMon_Mon3_Gia = new JLabel("50,000");
        lblDanhSachDatMon_Mon3_Gia.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon3_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon3_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon3_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon3_Gia.setForeground(new Color(0, 0, 0, 180));
        lblDanhSachDatMon_Mon3_Gia.setBounds(49, 37, 62, 18);
        panelEastCenter_Line3.add(lblDanhSachDatMon_Mon3_Gia);

        lblDanhSachMonAn_Mon3_SoLuong = new JLabel("2");
        lblDanhSachMonAn_Mon3_SoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachMonAn_Mon3_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblDanhSachMonAn_Mon3_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon3_SoLuong.setBounds(122, 11, 30, 44);
        panelEastCenter_Line3.add(lblDanhSachMonAn_Mon3_SoLuong);

        btnDanhSachMonAn_Mon3_Cong = new JButton("");
        btnDanhSachMonAn_Mon3_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon3_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon3_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon3_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon3_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line3.add(btnDanhSachMonAn_Mon3_Cong);
        ImageIcon iconAdd3 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4_3 = iconAdd3.getImage().getScaledInstance(btnDanhSachMonAn_Mon3_Cong.getWidth(), btnDanhSachMonAn_Mon3_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon3_Cong.setIcon(new ImageIcon(img4_3));

        btnDanhSachMonAn_Mon3_Tru = new JButton("");
        btnDanhSachMonAn_Mon3_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon3_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon3_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon3_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon3_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line3.add(btnDanhSachMonAn_Mon3_Tru);
        ImageIcon iconMinus3 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5_3 = iconMinus3.getImage().getScaledInstance(btnDanhSachMonAn_Mon3_Tru.getWidth(), btnDanhSachMonAn_Mon3_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon3_Tru.setIcon(new ImageIcon(img5_3));

        lblDanhSachDatMon_Mon3_GiaTong = new JLabel("100,000 VNĐ");
        lblDanhSachDatMon_Mon3_GiaTong.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon3_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon3_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon3_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon3_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon3_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line3.add(lblDanhSachDatMon_Mon3_GiaTong);

        btnImgThungRacIcon_Mon3 = new JButton("");
        btnImgThungRacIcon_Mon3.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon_Mon3.setOpaque(true);
        ImageIcon iconThungRac3 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3_3 = iconThungRac3.getImage().getScaledInstance(btnImgThungRacIcon_Mon3.getWidth(), btnImgThungRacIcon_Mon3.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon_Mon3.setIcon(new ImageIcon(img3_3));
        panelEastCenter_Line3.add(btnImgThungRacIcon_Mon3);

        // Line 4: Bibimbap hỗn hợp
        panelEastCenter_Line4 = new JPanel();
        panelEastCenter_Line4.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 120)));
        panelEastCenter_Line4.setBounds(0, 209, 344, 66);
        panelEastCenter.add(panelEastCenter_Line4);
        panelEastCenter_Line4.setLayout(null);

        lblDanhSachDatMon_Mon4_TieuDe = new JLabel("Bibimbap hỗn hợp");
        lblDanhSachDatMon_Mon4_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon4_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon4_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon4_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line4.add(lblDanhSachDatMon_Mon4_TieuDe);

        lblDanhSachDatMon_Mon4_Gia = new JLabel("55,000");
        lblDanhSachDatMon_Mon4_Gia.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon4_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon4_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon4_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon4_Gia.setForeground(new Color(0, 0, 0, 180));
        lblDanhSachDatMon_Mon4_Gia.setBounds(49, 37, 62, 18);
        panelEastCenter_Line4.add(lblDanhSachDatMon_Mon4_Gia);

        lblDanhSachMonAn_Mon4_SoLuong = new JLabel("1");
        lblDanhSachMonAn_Mon4_SoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachMonAn_Mon4_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblDanhSachMonAn_Mon4_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon4_SoLuong.setBounds(122, 11, 30, 44);
        panelEastCenter_Line4.add(lblDanhSachMonAn_Mon4_SoLuong);

        btnDanhSachMonAn_Mon4_Cong = new JButton("");
        btnDanhSachMonAn_Mon4_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon4_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon4_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon4_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon4_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line4.add(btnDanhSachMonAn_Mon4_Cong);
        ImageIcon iconAdd4 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4_4 = iconAdd4.getImage().getScaledInstance(btnDanhSachMonAn_Mon4_Cong.getWidth(), btnDanhSachMonAn_Mon4_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon4_Cong.setIcon(new ImageIcon(img4_4));

        btnDanhSachMonAn_Mon4_Tru = new JButton("");
        btnDanhSachMonAn_Mon4_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon4_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon4_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon4_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon4_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line4.add(btnDanhSachMonAn_Mon4_Tru);
        ImageIcon iconMinus4 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5_4 = iconMinus4.getImage().getScaledInstance(btnDanhSachMonAn_Mon4_Tru.getWidth(), btnDanhSachMonAn_Mon4_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon4_Tru.setIcon(new ImageIcon(img5_4));

        lblDanhSachDatMon_Mon4_GiaTong = new JLabel("55,000 VNĐ");
        lblDanhSachDatMon_Mon4_GiaTong.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon4_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon4_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon4_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon4_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon4_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line4.add(lblDanhSachDatMon_Mon4_GiaTong);

        btnImgThungRacIcon_Mon4 = new JButton("");
        btnImgThungRacIcon_Mon4.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon_Mon4.setOpaque(true);
        ImageIcon iconThungRac4 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3_4 = iconThungRac4.getImage().getScaledInstance(btnImgThungRacIcon_Mon4.getWidth(), btnImgThungRacIcon_Mon4.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon_Mon4.setIcon(new ImageIcon(img3_4));
        panelEastCenter_Line4.add(btnImgThungRacIcon_Mon4);

        // Line 5: Samgyeopsal nướng
        panelEastCenter_Line5 = new JPanel();
        panelEastCenter_Line5.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 120)));
        panelEastCenter_Line5.setBounds(0, 275, 344, 66);
        panelEastCenter.add(panelEastCenter_Line5);
        panelEastCenter_Line5.setLayout(null);

        lblDanhSachDatMon_Mon5_TieuDe = new JLabel("Samgyeopsal nướng");
        lblDanhSachDatMon_Mon5_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon5_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon5_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon5_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line5.add(lblDanhSachDatMon_Mon5_TieuDe);

        lblDanhSachDatMon_Mon5_Gia = new JLabel("60,000");
        lblDanhSachDatMon_Mon5_Gia.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon5_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon5_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon5_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon5_Gia.setForeground(new Color(0, 0, 0, 180));
        lblDanhSachDatMon_Mon5_Gia.setBounds(49, 37, 62, 18);
        panelEastCenter_Line5.add(lblDanhSachDatMon_Mon5_Gia);

        lblDanhSachMonAn_Mon5_SoLuong = new JLabel("2");
        lblDanhSachMonAn_Mon5_SoLuong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachMonAn_Mon5_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblDanhSachMonAn_Mon5_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon5_SoLuong.setBounds(122, 11, 30, 44);
        panelEastCenter_Line5.add(lblDanhSachMonAn_Mon5_SoLuong);

        btnDanhSachMonAn_Mon5_Cong = new JButton("");
        btnDanhSachMonAn_Mon5_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon5_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon5_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon5_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon5_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line5.add(btnDanhSachMonAn_Mon5_Cong);
        ImageIcon iconAdd5 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4_5 = iconAdd5.getImage().getScaledInstance(btnDanhSachMonAn_Mon5_Cong.getWidth(), btnDanhSachMonAn_Mon5_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon5_Cong.setIcon(new ImageIcon(img4_5));

        btnDanhSachMonAn_Mon5_Tru = new JButton("");
        btnDanhSachMonAn_Mon5_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon5_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon5_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon5_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon5_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line5.add(btnDanhSachMonAn_Mon5_Tru);
        ImageIcon iconMinus5 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5_5 = iconMinus5.getImage().getScaledInstance(btnDanhSachMonAn_Mon5_Tru.getWidth(), btnDanhSachMonAn_Mon5_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon5_Tru.setIcon(new ImageIcon(img5_5));

        lblDanhSachDatMon_Mon5_GiaTong = new JLabel("120,000 VNĐ");
        lblDanhSachDatMon_Mon5_GiaTong.setPreferredSize(new Dimension(41, 27));
        lblDanhSachDatMon_Mon5_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon5_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon5_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon5_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon5_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line5.add(lblDanhSachDatMon_Mon5_GiaTong);

        btnImgThungRacIcon_Mon5 = new JButton("");
        btnImgThungRacIcon_Mon5.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon_Mon5.setOpaque(true);
        ImageIcon iconThungRac5 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3_5 = iconThungRac5.getImage().getScaledInstance(btnImgThungRacIcon_Mon5.getWidth(), btnImgThungRacIcon_Mon5.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon_Mon5.setIcon(new ImageIcon(img3_5));
        panelEastCenter_Line5.add(btnImgThungRacIcon_Mon5);

        // Line 6: Kimchi jjigae cay
        panelEastCenter_Line6 = new JPanel();
        panelEastCenter_Line6.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 120)));
        panelEastCenter_Line6.setBounds(0, 341, 344, 66);
        panelEastCenter.add(panelEastCenter_Line6);
        panelEastCenter_Line6.setLayout(null);

        lblDanhSachDatMon_Mon6_TieuDe = new JLabel("Kimchi jjigae cay");
        lblDanhSachDatMon_Mon6_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon6_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon6_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon6_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line6.add(lblDanhSachDatMon_Mon6_TieuDe);

        lblDanhSachDatMon_Mon6_Gia = new JLabel("45,000");
        lblDanhSachDatMon_Mon6_Gia.setPreferredSize(new Dimension(41, 27));
        lblDanhSachDatMon_Mon6_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon6_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon6_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon6_Gia.setForeground(new Color(0, 0, 0, 180));
        lblDanhSachDatMon_Mon6_Gia.setBounds(49, 37, 62, 18);
        panelEastCenter_Line6.add(lblDanhSachDatMon_Mon6_Gia);

        lblDanhSachMonAn_Mon6_SoLuong = new JLabel("1");
        lblDanhSachMonAn_Mon6_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblDanhSachMonAn_Mon6_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon6_SoLuong.setBounds(122, 10, 30, 45);
        panelEastCenter_Line6.add(lblDanhSachMonAn_Mon6_SoLuong);

        btnDanhSachMonAn_Mon6_Cong = new JButton("");
        btnDanhSachMonAn_Mon6_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon6_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon6_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon6_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon6_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line6.add(btnDanhSachMonAn_Mon6_Cong);
        ImageIcon iconAdd6 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4_6 = iconAdd6.getImage().getScaledInstance(btnDanhSachMonAn_Mon6_Cong.getWidth(), btnDanhSachMonAn_Mon6_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon6_Cong.setIcon(new ImageIcon(img4_6));

        btnDanhSachMonAn_Mon6_Tru = new JButton("");
        btnDanhSachMonAn_Mon6_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon6_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon6_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon6_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon6_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line6.add(btnDanhSachMonAn_Mon6_Tru);
        ImageIcon iconMinus6 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5_6 = iconMinus6.getImage().getScaledInstance(btnDanhSachMonAn_Mon6_Tru.getWidth(), btnDanhSachMonAn_Mon6_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon6_Tru.setIcon(new ImageIcon(img5_6));

        lblDanhSachDatMon_Mon6_GiaTong = new JLabel("45,000 VNĐ");
        lblDanhSachDatMon_Mon6_GiaTong.setPreferredSize(new Dimension(41, 27));
        lblDanhSachDatMon_Mon6_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon6_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon6_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon6_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon6_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line6.add(lblDanhSachDatMon_Mon6_GiaTong);

        btnImgThungRacIcon_Mon6 = new JButton("");
        btnImgThungRacIcon_Mon6.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon_Mon6.setOpaque(true);
        ImageIcon iconThungRac6 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3_6 = iconThungRac6.getImage().getScaledInstance(btnImgThungRacIcon_Mon6.getWidth(), btnImgThungRacIcon_Mon6.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon_Mon6.setIcon(new ImageIcon(img3_6));
        panelEastCenter_Line6.add(btnImgThungRacIcon_Mon6);

        // Line 7: Japchae xào rau củ
        panelEastCenter_Line7 = new JPanel();
        panelEastCenter_Line7.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 120)));
        panelEastCenter_Line7.setBounds(0, 407, 344, 66);
        panelEastCenter.add(panelEastCenter_Line7);
        panelEastCenter_Line7.setLayout(null);

        lblDanhSachDatMon_Mon7_TieuDe = new JLabel("Japchae xào rau củ");
        lblDanhSachDatMon_Mon7_TieuDe.setPreferredSize(new Dimension(100, 48));
        lblDanhSachDatMon_Mon7_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblDanhSachDatMon_Mon7_TieuDe.setAlignmentX(0.0f);
        lblDanhSachDatMon_Mon7_TieuDe.setBounds(0, 11, 102, 25);
        panelEastCenter_Line7.add(lblDanhSachDatMon_Mon7_TieuDe);

        lblDanhSachDatMon_Mon7_Gia = new JLabel("50,000");
        lblDanhSachDatMon_Mon7_Gia.setPreferredSize(new Dimension(41, 27));
        lblDanhSachDatMon_Mon7_Gia.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon7_Gia.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDanhSachDatMon_Mon7_Gia.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon7_Gia.setForeground(new Color(0, 0, 0, 180));
        lblDanhSachDatMon_Mon7_Gia.setBounds(49, 37, 62, 18);
        panelEastCenter_Line7.add(lblDanhSachDatMon_Mon7_Gia);

        lblDanhSachMonAn_Mon7_SoLuong = new JLabel("1");
        lblDanhSachMonAn_Mon7_SoLuong.setBorder(new LineBorder(new Color(0, 0, 0, 80)));
        lblDanhSachMonAn_Mon7_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblDanhSachMonAn_Mon7_SoLuong.setBounds(122, 10, 30, 45);
        panelEastCenter_Line7.add(lblDanhSachMonAn_Mon7_SoLuong);

        btnDanhSachMonAn_Mon7_Cong = new JButton("");
        btnDanhSachMonAn_Mon7_Cong.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon7_Cong.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon7_Cong.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon7_Cong.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon7_Cong.setBounds(149, 11, 30, 25);
        panelEastCenter_Line7.add(btnDanhSachMonAn_Mon7_Cong);
        ImageIcon iconAdd7 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\add_icon.png");
        Image img4_7 = iconAdd7.getImage().getScaledInstance(btnDanhSachMonAn_Mon7_Cong.getWidth(), btnDanhSachMonAn_Mon7_Cong.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon7_Cong.setIcon(new ImageIcon(img4_7));

        btnDanhSachMonAn_Mon7_Tru = new JButton("");
        btnDanhSachMonAn_Mon7_Tru.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnDanhSachMonAn_Mon7_Tru.setMinimumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon7_Tru.setMaximumSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon7_Tru.setPreferredSize(new Dimension(41, 27));
        btnDanhSachMonAn_Mon7_Tru.setBounds(149, 36, 30, 19);
        panelEastCenter_Line7.add(btnDanhSachMonAn_Mon7_Tru);
        ImageIcon iconMinus7 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\minus_icon.png");
        Image img5_7 = iconMinus7.getImage().getScaledInstance(btnDanhSachMonAn_Mon7_Tru.getWidth(), btnDanhSachMonAn_Mon7_Tru.getHeight(), Image.SCALE_SMOOTH);
        btnDanhSachMonAn_Mon7_Tru.setIcon(new ImageIcon(img5_7));

        lblDanhSachDatMon_Mon7_GiaTong = new JLabel("50,000 VNĐ");
        lblDanhSachDatMon_Mon7_GiaTong.setPreferredSize(new Dimension(40, 30));
        lblDanhSachDatMon_Mon7_GiaTong.setHorizontalAlignment(SwingConstants.CENTER);
        lblDanhSachDatMon_Mon7_GiaTong.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDanhSachDatMon_Mon7_GiaTong.setBorder(null);
        lblDanhSachDatMon_Mon7_GiaTong.setBackground(new Color(64, 0, 0));
        lblDanhSachDatMon_Mon7_GiaTong.setBounds(211, 20, 91, 27);
        panelEastCenter_Line7.add(lblDanhSachDatMon_Mon7_GiaTong);

        btnImgThungRacIcon_Mon7 = new JButton("");
        btnImgThungRacIcon_Mon7.setBounds(304, 15, 30, 33);
        btnImgThungRacIcon_Mon7.setOpaque(true);
        ImageIcon iconThungRac7 = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\thung_rac_icon.png");
        Image img3_7 = iconThungRac7.getImage().getScaledInstance(btnImgThungRacIcon_Mon7.getWidth(), btnImgThungRacIcon_Mon7.getHeight(), Image.SCALE_SMOOTH);
        btnImgThungRacIcon_Mon7.setIcon(new ImageIcon(img3_7));
        panelEastCenter_Line7.add(btnImgThungRacIcon_Mon7);
        
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 432, 354, 157);
        panelEast.add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnInBillChoBep = new JButton("In bill cho bếp");
        btnInBillChoBep.setBackground(new Color(255, 255, 255));
        btnInBillChoBep.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnInBillChoBep.setVerticalAlignment(SwingConstants.BOTTOM);

        // Gắn icon
        ImageIcon iconDauBep = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\dau_bep_icon.png");
        Image imgDauBep = iconDauBep.getImage().getScaledInstance(50, 40, Image.SCALE_SMOOTH);
        btnInBillChoBep.setIcon(new ImageIcon(imgDauBep));

        // Đặt vị trí icon và text
        btnInBillChoBep.setHorizontalTextPosition(SwingConstants.CENTER); // chữ nằm giữa theo chiều ngang
        btnInBillChoBep.setVerticalTextPosition(SwingConstants.BOTTOM);   // chữ nằm dưới icon

        btnInBillChoBep.setBounds(43, 11, 131, 75);
        panel_2.add(btnInBillChoBep);
        
        btnXuatHoaDon = new JButton("Xuất hóa đơn");
        btnXuatHoaDon.setBackground(new Color(255, 255, 255));
        btnXuatHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXuatHoaDon.setVerticalAlignment(SwingConstants.BOTTOM);

        // Thêm icon (máy in hóa đơn)
        ImageIcon iconXuatHoaDon = new ImageIcon("C:\\S1(2025-2016)\\PTUD\\DatBanNhaHang\\img\\thucdon\\printer_icon.png");
        Image imgXuatHoaDon = iconXuatHoaDon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        btnXuatHoaDon.setIcon(new ImageIcon(imgXuatHoaDon));

        // Căn vị trí icon và chữ
        btnXuatHoaDon.setHorizontalTextPosition(SwingConstants.CENTER); // chữ giữa theo ngang
        btnXuatHoaDon.setVerticalTextPosition(SwingConstants.BOTTOM);   // chữ dưới icon
        btnXuatHoaDon.setIconTextGap(5); // khoảng cách giữa icon và chữ

        btnXuatHoaDon.setBounds(197, 11, 131, 75); // tăng nhẹ chiều cao để hiển thị đủ
        panel_2.add(btnXuatHoaDon);
        
        btnXoaTatCaMon = new JButton("Xóa tất cả món");
        btnXoaTatCaMon.setBackground(new Color(192, 192, 192));
        btnXoaTatCaMon.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXoaTatCaMon.setBounds(43, 97, 131, 38);
        panel_2.add(btnXoaTatCaMon);
        
        btnLuuHoaDon = new JButton("Lưu hóa đơn");
        btnLuuHoaDon.setBackground(new Color(0, 128, 255));
        btnLuuHoaDon.setForeground(new Color(255, 255, 255));
        btnLuuHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLuuHoaDon.setBounds(197, 97, 131, 38);
        panel_2.add(btnLuuHoaDon);

        
        

    }

    // MenuBuilder class to create menus and menu items consistently
    private static class MenuBuilderQuanLy {
        private final Font menuFont = new Font("Segoe UI", Font.BOLD, 14);
        private final Font menuItemFont = new Font("Segoe UI", Font.PLAIN, 13);
        private final Dimension menuItemSize = new Dimension(200, 30);
        private final Color menuForeground = Color.WHITE;
        private final Color menuItemBackground = new Color(220, 220, 220);

        private JMenu createMenu(String title) {
            JMenu menu = new JMenu(title);
            menu.setFont(menuFont);
            menu.setForeground(menuForeground);
            menu.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            return menu;
        }

        private JMenuItem createMenuItem(String title, ActionListener actionListener) {
            JMenuItem menuItem = new JMenuItem(title);
            menuItem.setFont(menuItemFont);
            menuItem.setPreferredSize(menuItemSize);
            menuItem.setBackground(menuItemBackground);
            if (actionListener != null) {
                menuItem.addActionListener(actionListener);
            }
            return menuItem;
        }

        public JMenu createHeThongMenu() {
            JMenu menu = createMenu("Hệ thống");
            menu.add(createMenuItem("Màn hình chính", null));
            menu.add(createMenuItem("Thoát", e -> System.exit(0)));
            return menu;
        }

        public JMenu createThucDonMenu() {
            JMenu menu = createMenu("Thực đơn");
            menu.add(createMenuItem("Thêm món ăn", null));
            menu.add(createMenuItem("Cập nhật", null));
            menu.add(createMenuItem("Tra cứu", null));
            menu.add(createMenuItem("Thống kê", null));
            return menu;
        }

        public JMenu createKhuVucMenu() {
            JMenu menu = createMenu("Khu vực");
            menu.add(createMenuItem("Xem danh sách", null));
            menu.add(createMenuItem("Thêm khu vực mới", null));
            menu.add(createMenuItem("Cập nhật", null));
            return menu;
        }

        public JMenu createCaLamMenu() {
            JMenu menu = createMenu("Ca làm");
            menu.add(createMenuItem("Tra cứu", null));
            menu.add(createMenuItem("Phân ca", null));
            menu.add(createMenuItem("Thêm ca mới", null));
            return menu;
        }

        public JMenu createNhanVienMenu() {
            JMenu menu = createMenu("Nhân viên");
            menu.add(createMenuItem("Xem danh sách", null));
            menu.add(createMenuItem("Cập nhật", null));
            menu.add(createMenuItem("Phân quyền", null));
            menu.add(createMenuItem("Thống kê", null));
            return menu;
        }

        public JMenu createDoanhThuMenu() {
            JMenu menu = createMenu("Doanh thu");
            menu.add(createMenuItem("Thống kê trong ngày", null));
            menu.add(createMenuItem("Thống kê theo tuần", null));
            return menu;
        }
    }
}
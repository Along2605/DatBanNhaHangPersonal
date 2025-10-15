package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;

import java.awt.event.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleInsets;

import java.util.Random;

public class ManHinhThongKeHoaDonTheoNam extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
    
    private static JPanel mainPanelRef;
    private JTextField textFieldTongSoHoaDon;
    private JTextField textFieldTongTienHoaDon;
    private DefaultTableModel model;
    private JTable table;
    
    public static void showPanel(JPanel panel) {
        Component[] comps = mainPanelRef.getComponents();
        for (Component c : comps) {
            if (c instanceof JPanel p) {
                p.removeAll();
                p.setVisible(false);
            }
        }
        mainPanelRef.removeAll();
        System.gc();
        
        mainPanelRef.add(panel, BorderLayout.CENTER);
        mainPanelRef.revalidate();
        mainPanelRef.repaint();
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JFrame frame = new JFrame("Quản Lý Nhà Hàng");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setBounds(0, 0, screenSize.width, screenSize.height);
                frame.setLocationRelativeTo(null);
                
                ManHinhThongKeHoaDonTheoNam panel = new ManHinhThongKeHoaDonTheoNam();
                frame.add(panel, BorderLayout.CENTER);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ManHinhThongKeHoaDonTheoNam() {
        setLayout(new BorderLayout());

        addHierarchyListener(e -> {
            if ((e.getChangeFlags() & HierarchyEvent.PARENT_CHANGED) != 0) {
                Component parent = SwingUtilities.getRoot(this);
                if (parent instanceof JFrame frame) {
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            int confirm = JOptionPane.showConfirmDialog(
                                ManHinhThongKeHoaDonTheoNam.this,
                                "Bạn có chắc muốn thoát không?",
                                "Xác nhận",
                                JOptionPane.YES_NO_OPTION
                            );

                            if (confirm == JOptionPane.YES_OPTION) {
                                ConnectDB.getInstance().close();
                                System.exit(0);
                            }
                        }
                    });
                }
            }
        });

        menuBar = new JMenuBar();
        menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));
        menuBar.setBackground(new Color(214, 116, 76, 255));
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(menuBar, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setSize(new Dimension(60, 60));
        lblNewLabel.setBackground(new Color(214, 116, 76, 180));
        lblNewLabel.setOpaque(true);
        ImageIcon icon = new ImageIcon("img\\logo_nhahang.png");
        Image img = icon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(img));
        menuBar.add(lblNewLabel);
        menuBar.add(Box.createHorizontalStrut(5));

        MenuBuilderQuanLy menuBuilder = new MenuBuilderQuanLy();
        menuBar.add(menuBuilder.createHeThongMenu());
        menuBar.add(menuBuilder.createMonAnMenu());
        menuBar.add(menuBuilder.createKhuVucMenu());
        menuBar.add(menuBuilder.createCaLamMenu());
        menuBar.add(menuBuilder.createNhanVienMenu());
        menuBar.add(menuBuilder.createDoanhThuMenu());

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(245, 245, 245));
        add(contentPane, BorderLayout.CENTER);
        
        mainPanelRef = contentPane;

        contentPane.setLayout(null);
        
        JLabel lblTieuDe = new JLabel("THỐNG KÊ HÓA ĐƠN THEO NĂM");
        lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
        lblTieuDe.setFont(new Font("Segoe UI", Font.BOLD, 27));
        lblTieuDe.setBounds(425,0, 450, 59);
        contentPane.add(lblTieuDe);
        
        JPanel panelDoanhThu = new JPanel();
        panelDoanhThu.setBounds(49, 57, 281, 95);
        contentPane.add(panelDoanhThu);
        panelDoanhThu.setLayout(null);
        
        JLabel lblDoanhThu_TieuDe = new JLabel("Doanh thu");
        lblDoanhThu_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblDoanhThu_TieuDe.setBounds(10, 11, 91, 21);
        panelDoanhThu.add(lblDoanhThu_TieuDe);
        
        JLabel lblDoanhThu_TongTien = new JLabel("1,450,000,000");
        lblDoanhThu_TongTien.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblDoanhThu_TongTien.setBounds(10, 35, 150, 21);
        panelDoanhThu.add(lblDoanhThu_TongTien);
        
        JLabel lblDoanhThu_TheoNam = new JLabel("Theo năm");
        lblDoanhThu_TheoNam.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblDoanhThu_TheoNam.setBounds(218, 77, 63, 14);
        panelDoanhThu.add(lblDoanhThu_TheoNam);
        
        JLabel labelDoanhThu_VND = new JLabel("Vnđ");
        labelDoanhThu_VND.setForeground(new Color(0, 0, 0, 150));
        labelDoanhThu_VND.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelDoanhThu_VND.setBounds(10, 65, 46, 14);
        panelDoanhThu.add(labelDoanhThu_VND);
        
        JPanel panelHoaDonTaiBan = new JPanel();
        panelHoaDonTaiBan.setLayout(null);
        panelHoaDonTaiBan.setBounds(373, 57, 281, 95);
        contentPane.add(panelHoaDonTaiBan);
        
        JLabel lblHoaDonTaiBan_TieuDe = new JLabel("Hóa đơn tại bàn");
        lblHoaDonTaiBan_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHoaDonTaiBan_TieuDe.setBounds(10, 11, 123, 21);
        panelHoaDonTaiBan.add(lblHoaDonTaiBan_TieuDe);
        
        JLabel lblHoaDonTaiBan_SoLuong = new JLabel("9,500");
        lblHoaDonTaiBan_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHoaDonTaiBan_SoLuong.setBounds(10, 35, 112, 21);
        panelHoaDonTaiBan.add(lblHoaDonTaiBan_SoLuong);
        
        JLabel lblDoanhThu_TheoNam_1 = new JLabel("Theo năm");
        lblDoanhThu_TheoNam_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblDoanhThu_TheoNam_1.setBounds(218, 76, 63, 14);
        panelHoaDonTaiBan.add(lblDoanhThu_TheoNam_1);
        
        JLabel labelHoaDonTaiBan_Don = new JLabel("đơn");
        labelHoaDonTaiBan_Don.setForeground(new Color(0, 0, 0, 150));
        labelHoaDonTaiBan_Don.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelHoaDonTaiBan_Don.setBounds(10, 65, 63, 14);
        panelHoaDonTaiBan.add(labelHoaDonTaiBan_Don);
        
        JPanel panelHoaDonMangDi = new JPanel();
        panelHoaDonMangDi.setLayout(null);
        panelHoaDonMangDi.setBounds(678, 57, 281, 95);
        contentPane.add(panelHoaDonMangDi);
        
        JLabel lblHoaDoMangDi_TieuDe = new JLabel("Hóa đơn mang đi");
        lblHoaDoMangDi_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblHoaDoMangDi_TieuDe.setBounds(10, 11, 123, 21);
        panelHoaDonMangDi.add(lblHoaDoMangDi_TieuDe);
        
        JLabel lblHoaDonMangDi_SoLuong = new JLabel("1,500");
        lblHoaDonMangDi_SoLuong.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblHoaDonMangDi_SoLuong.setBounds(10, 35, 112, 21);
        panelHoaDonMangDi.add(lblHoaDonMangDi_SoLuong);
        
        JLabel lblDoanhThu_TheoNam_1_1 = new JLabel("Theo năm");
        lblDoanhThu_TheoNam_1_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblDoanhThu_TheoNam_1_1.setBounds(218, 76, 63, 14);
        panelHoaDonMangDi.add(lblDoanhThu_TheoNam_1_1);
        
        JLabel labelHoaDonMangDi_Don = new JLabel("đơn");
        labelHoaDonMangDi_Don.setForeground(new Color(0, 0, 0, 150));
        labelHoaDonMangDi_Don.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelHoaDonMangDi_Don.setBounds(10, 65, 63, 14);
        panelHoaDonMangDi.add(labelHoaDonMangDi_Don);
        
        JPanel panelChiPhiKhac = new JPanel();
        panelChiPhiKhac.setLayout(null);
        panelChiPhiKhac.setBounds(1017, 57, 281, 95);
        contentPane.add(panelChiPhiKhac);
        
        JLabel lbl_ChiPhiKhac_TieuDe = new JLabel("Chi phí khác");
        lbl_ChiPhiKhac_TieuDe.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl_ChiPhiKhac_TieuDe.setBounds(10, 11, 123, 21);
        panelChiPhiKhac.add(lbl_ChiPhiKhac_TieuDe);
        
        JLabel lblChiPhiKhac_TongTien = new JLabel("145,000,000");
        lblChiPhiKhac_TongTien.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblChiPhiKhac_TongTien.setBounds(10, 35, 150, 21);
        panelChiPhiKhac.add(lblChiPhiKhac_TongTien);
        
        JLabel lblDoanhThu_TheoNam_1_1_1 = new JLabel("Theo năm");
        lblDoanhThu_TheoNam_1_1_1.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblDoanhThu_TheoNam_1_1_1.setBounds(208, 76, 63, 14);
        panelChiPhiKhac.add(lblDoanhThu_TheoNam_1_1_1);
        
        JLabel labelChiPhiKhac_VND = new JLabel("Vnđ");
        labelChiPhiKhac_VND.setForeground(new Color(0, 0, 0, 150));
        labelChiPhiKhac_VND.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        labelChiPhiKhac_VND.setBounds(10, 65, 63, 14);
        panelChiPhiKhac.add(labelChiPhiKhac_VND);
        
        JPanel panelBieuDo = new JPanel();
        panelBieuDo.setBounds(49, 174, 620, 357);
        contentPane.add(panelBieuDo);
        panelBieuDo.setLayout(new BorderLayout());
        
        DefaultCategoryDataset dataset = createRevenueDataset();
        
        JFreeChart barChart = ChartFactory.createBarChart(
            "BIỂU ĐỒ THỐNG KÊ DOANH THU THEO NĂM (2015-2025)",
            "Năm",
            "Doanh thu (tỷ VND)",
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );
        
        var domainAxis = barChart.getCategoryPlot().getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            org.jfree.chart.axis.CategoryLabelPositions.STANDARD
        );
        domainAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 10));
        domainAxis.setMaximumCategoryLabelWidthRatio(1.0f);
        domainAxis.setTickLabelsVisible(true);
        
        var rangeAxis = barChart.getCategoryPlot().getRangeAxis();
        rangeAxis.setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 10));
        rangeAxis.setTickLabelsVisible(true);
        rangeAxis.setLabelInsets(new RectangleInsets(5, 5, 5, 5));

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(620, 343));
        panelBieuDo.add(chartPanel, BorderLayout.CENTER);
        
        JButton btnXemChiTiet = new JButton("Xem chi tiết");
        btnXemChiTiet.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXemChiTiet.setBounds(129, 542, 179, 49);
        contentPane.add(btnXemChiTiet);
        
        JButton btnXuatFileExcel = new JButton("Xuất file excel");
        btnXuatFileExcel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnXuatFileExcel.setBounds(373, 542, 179, 49);
        contentPane.add(btnXuatFileExcel);
        
        JLabel lblTongSoHoaDon = new JLabel("Tổng số hóa đơn");
        lblTongSoHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTongSoHoaDon.setBounds(718, 174, 115, 20);
        contentPane.add(lblTongSoHoaDon);
        
        JLabel lblTongTienHoaDon_1 = new JLabel("Tổng tiền hóa đơn");
        lblTongTienHoaDon_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblTongTienHoaDon_1.setBounds(718, 205, 115, 20);
        contentPane.add(lblTongTienHoaDon_1);
        
        textFieldTongSoHoaDon = new JTextField();
        textFieldTongSoHoaDon.setText("11,000");
        textFieldTongSoHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textFieldTongSoHoaDon.setBounds(863, 174, 150, 20);
        contentPane.add(textFieldTongSoHoaDon);
        textFieldTongSoHoaDon.setColumns(10);
        
        textFieldTongTienHoaDon = new JTextField();
        textFieldTongTienHoaDon.setText("1,450,000,000 VNĐ");
        textFieldTongTienHoaDon.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textFieldTongTienHoaDon.setColumns(10);
        textFieldTongTienHoaDon.setBounds(863, 205, 150, 20);
        contentPane.add(textFieldTongTienHoaDon);
        
        JPanel panelTable = new JPanel();
        panelTable.setBounds(688, 236, 631, 357);
        contentPane.add(panelTable);
        panelTable.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 5, 611, 341);
        panelTable.add(scrollPane);
        
        String tableHeader[] = {"Năm", "Tổng số HĐ", "Tổng tiền HĐ"};
        model = new DefaultTableModel(tableHeader, 0);
        table = new JTable(model);
        scrollPane.setViewportView(table);
        
        for (Object[] row : new Object[][]{
            {"2015", 7500, "750,000,000 VNĐ"},
            {"2016", 7800, "780,000,000 VNĐ"},
            {"2017", 8000, "800,000,000 VNĐ"},
            {"2018", 8200, "820,000,000 VNĐ"},
            {"2019", 8500, "850,000,000 VNĐ"},
            {"2020", 8700, "870,000,000 VNĐ"},
            {"2021", 9000, "900,000,000 VNĐ"},
            {"2022", 9200, "920,000,000 VNĐ"},
            {"2023", 9500, "950,000,000 VNĐ"},
            {"2024", 9800, "980,000,000 VNĐ"},
            {"2025", 11000, "1,450,000,000 VNĐ"}
        }) {
            model.addRow(row);
        }
    }
    
    private DefaultCategoryDataset createRevenueDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(0.75, "Doanh thu", "2015");
        dataset.addValue(0.78, "Doanh thu", "2016");
        dataset.addValue(0.80, "Doanh thu", "2017");
        dataset.addValue(0.82, "Doanh thu", "2018");
        dataset.addValue(0.85, "Doanh thu", "2019");
        dataset.addValue(0.87, "Doanh thu", "2020");
        dataset.addValue(0.90, "Doanh thu", "2021");
        dataset.addValue(0.92, "Doanh thu", "2022");
        dataset.addValue(0.95, "Doanh thu", "2023");
        dataset.addValue(0.98, "Doanh thu", "2024");
        dataset.addValue(1.45, "Doanh thu", "2025");
        
        return dataset;
    }

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
            menu.add(createMenuItem("Đăng xuất", e -> {
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(mainPanelRef);
                if (topFrame != null) {
                    topFrame.dispose();
                }
            }));
            menu.add(createMenuItem("Thoát", e -> System.exit(0)));
            return menu;
        }

        public JMenu createMonAnMenu() {
            JMenu menu = createMenu("Món ăn");
            menu.add(createMenuItem("Thống kê", null));
            return menu;
        }

        public JMenu createKhuVucMenu() {
            JMenu menu = createMenu("Khu vực");
            menu.add(createMenuItem("Xem danh sách bàn ăn", null));
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
            menu.add(createMenuItem("Phân quyền", null));
            menu.add(createMenuItem("Thống kê", null));
            return menu;
        }

        public JMenu createDoanhThuMenu() {
            JMenu menu = createMenu("Doanh thu");
            menu.add(createMenuItem("Thống kê trong ngày", null));
            menu.add(createMenuItem("Thống kê theo tuần", null));
            menu.add(createMenuItem("Thống kê theo tháng", null));
            menu.add(createMenuItem("Thống kê theo năm", null));
            return menu;
        }
    }
}
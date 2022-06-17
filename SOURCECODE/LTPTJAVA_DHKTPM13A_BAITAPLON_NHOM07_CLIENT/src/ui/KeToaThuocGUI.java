package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import entities.ChiTietToaThuoc;

public class KeToaThuocGUI extends JFrame {
	Date nowDay = new Date();
	JLabel lblAuthor;
	
	JMenuBar menuBar;
	JMenu meHuongDan, meThoat;
	
	JLabel lblTD, lblMaToa, lblNgayNhap, lblTimMaKH, lblTenKH, lblTuoi, lblSDT, lblChuanDoan, lblMaBS,
			lblTenThuoc, lblTPThuoc, lblSoLuong, lblDonVT, lblCachDung, DonVT;
	JTextField txtMaToa, txtTimMaKH, txtMaKH, txtTuoi, txtSDT, txtMaBS, txtSoLuong, txtTenThuoc;
	JTextArea txtChuanDoan, txtCachDung;
	JComboBox<String> cboDonVT;
	JButton btnTim, btnThem, btnSua, btnXemToaThuoc, btnLuu, btnHuy;
	JDateChooser dcNgayNhap;
	DefaultTableModel model;
	JTable tabCTToaThuoc;
	JScrollPane scrollTimKiem, scrTimKH;
	JList<String> dsTenThuoc, listTenKH;
	DefaultListModel<String> modList, modTimKH;
	
	JPopupMenu poMenu = new JPopupMenu();
	JMenuItem mXoa = new JMenuItem("Xóa", new ImageIcon("hinhanh/xoa.png"));
	
//	ToaThuocControl dsToaThuoc = new ToaThuocControl();
	ArrayList<ChiTietToaThuoc> arrCTToaThuoc = new ArrayList<ChiTietToaThuoc>();
	int soToaThuoc = 0;
	
	
	public KeToaThuocGUI() {
		setTitle("Phần mềm quản lý quầy thuốc");
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("hinhanh/title.png"));
		setSize(ManHinhChinhGUI.width, ManHinhChinhGUI.height);
		setLocationRelativeTo(null);
		setResizable(false);
		
		createFrameKeToaThuoc();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void createFrameKeToaThuoc() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setPreferredSize(new Dimension(0, 35));
		
		menuBar.add(meThoat = new JMenu("Thoát"));
		if(ManHinhChinhGUI.user != 0) {
			int length = ManHinhChinhGUI.tenDN.length() * 7;
			menuBar.add(Box.createHorizontalStrut(ManHinhChinhGUI.width - 162 - length + 100));
			Box b = Box.createHorizontalBox();
			b.add(lblAuthor = new JLabel(ManHinhChinhGUI.tenDN));	// khi đăng nhập sẽ hiện tên lên đây
			b.add(Box.createHorizontalStrut(5));
			b.add(new JLabel(new ImageIcon("hinhanh/user.png")));
			menuBar.add(b);
		}
		
		poMenu.add(mXoa);
		poMenu.setPreferredSize(new Dimension(100, 30));

		// ------------------ Ở trên ------------------ //
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		pNorth.add(lblTD = new JLabel("KÊ TOA THUỐC"));
		lblTD.setFont(new Font("Calibri Light", Font.BOLD, 25));
		add(pNorth, BorderLayout.NORTH);
		
		// ------------ Ở giữa ------------ //
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		
		JPanel pCenNor = new JPanel();
		pCenNor.setLayout(new BorderLayout());
		pCenNor.setPreferredSize(new Dimension(898,82));
		JPanel pBox1 = new JPanel();
		pBox1.setLayout(null);
		pBox1.add(lblTimMaKH = new JLabel("Tìm khách hàng"));
		pBox1.add(txtTimMaKH = new JTextField());
		pBox1.add(btnTim = new JButton(new ImageIcon("hinhanh/search.png")));
		lblTimMaKH.setBounds(20, 5, 100, 23);
		txtTimMaKH.setBounds(120, 5, 200, 25);
		btnTim.setBounds(343, 5, 35, 25);
		pBox1.add(scrTimKH = new JScrollPane());
		scrTimKH.setBounds(120, 32, 200, 50);
		modTimKH = new DefaultListModel<>();
		listTenKH = new JList<>();
		listTenKH.setModel(modTimKH);
		listTenKH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrTimKH.setViewportView(listTenKH);
		scrTimKH.setVisible(false);
		
		pCenNor.add(pBox1, BorderLayout.CENTER);
		pCenter.add(pCenNor, BorderLayout.NORTH);
		
		JPanel pMid = new JPanel();
		pMid.setLayout(new BorderLayout());
		JPanel pAboveMid = new JPanel();
		pAboveMid.setLayout(new GridLayout(0, 2));
		pAboveMid.setPreferredSize(new Dimension(898,200));
		
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBorder(BorderFactory.createTitledBorder("Toa thuốc"));
		p1.add(lblMaToa = new JLabel("Mã toa thuốc"));
		p1.add(txtMaToa = new JTextField());
		lblMaToa.setBounds(10, 20, 100, 23);
		txtMaToa.setBounds(105, 20, 150, 25);
		p1.add(lblNgayNhap = new JLabel("Ngày lập"));
		p1.add(dcNgayNhap = new JDateChooser());
		dcNgayNhap.setDateFormatString("yyyy-MM-dd");
		dcNgayNhap.setDate(nowDay);
		lblNgayNhap.setBounds(270, 20, 60, 23);
		dcNgayNhap.setBounds(330, 20, 105, 25);
		
		p1.add(lblTenKH = new JLabel("Mã khách hàng"));
		p1.add(txtMaKH = new JTextField());
		lblTenKH.setBounds(10, 60, 100, 23);
		txtMaKH.setBounds(105, 60, 150, 25);
		p1.add(lblTuoi = new JLabel("Tuổi"));
		p1.add(txtTuoi = new JTextField());
		lblTuoi.setBounds(270, 60, 60, 23);
		txtTuoi.setBounds(330, 60, 105, 25);
		
		p1.add(lblSDT = new JLabel("Điện thoại"));
		p1.add(txtSDT = new JTextField());
		lblSDT.setBounds(10, 100, 100, 23);
		txtSDT.setBounds(105, 100, 150, 25);
		p1.add(lblMaBS = new JLabel("Bác sĩ"));
		p1.add(txtMaBS = new JTextField());
		lblMaBS.setBounds(270, 100, 60, 23);
		txtMaBS.setBounds(330, 100, 105, 25);
		p1.add(lblChuanDoan = new JLabel("Chuẩn đoán"));
		JScrollPane scp1 = new JScrollPane(txtChuanDoan = new JTextArea());
		p1.add(scp1);
		lblChuanDoan.setBounds(10, 140, 100, 23);
		scp1.setBounds(105, 140, 328, 50);
		pAboveMid.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBorder(BorderFactory.createTitledBorder("Chi tiết toa thuốc"));
		p2.add(lblTenThuoc = new JLabel("Tên thuốc"));
		p2.add(txtTenThuoc = new JTextField());
		lblTenThuoc.setBounds(10, 20, 80, 23);
		txtTenThuoc.setBounds(80, 20, 355, 25);
		p2.add(scrollTimKiem = new JScrollPane());
		scrollTimKiem.setBounds(80, 45, 355, 105);
		modList = new DefaultListModel<>();
		dsTenThuoc = new JList<>();
		dsTenThuoc.setModel(modList);
		dsTenThuoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollTimKiem.setViewportView(dsTenThuoc);
		scrollTimKiem.setVisible(false);
		
		p2.add(lblSoLuong = new JLabel("Số Lượng"));
		p2.add(txtSoLuong = new JTextField());
		lblSoLuong.setBounds(10, 60, 80, 23);
		txtSoLuong.setBounds(80, 60, 130, 25);
		p2.add(lblDonVT = new JLabel("Đơn vị tính"));
		p2.add(cboDonVT = new JComboBox<String>());
		lblDonVT.setBounds(225, 60, 75, 23);
		cboDonVT.setBounds(300, 60, 135, 25);
		cboDonVT.addItem("Viên");
		cboDonVT.addItem("Vỉ");
		cboDonVT.addItem("Hộp");
		cboDonVT.addItem("Lọ");
		p2.add(lblCachDung = new JLabel("Cách dùng"));
		JScrollPane scp2 = new JScrollPane(txtCachDung = new JTextArea());
		p2.add(scp2);
		lblCachDung.setBounds(10, 100, 100, 23);
		scp2.setBounds(80, 100, 355, 50);
		p2.add(btnThem = new JButton("Thêm", new ImageIcon("hinhanh/add.png")));
		p2.add(btnSua = new JButton("  Sửa  ", new ImageIcon("hinhanh/edit.png")));
		btnThem.setBounds(125, 165, 100, 30);
		btnSua.setBounds(245, 165, 100, 30);
		
		pAboveMid.add(p2);
		pMid.add(pAboveMid, BorderLayout.NORTH);
		
		String[] a = {"STT", "Tên thuốc", "Số lượng", "Đơn vị tính", "Cách dùng"};
		model = new DefaultTableModel(a, 0);
		tabCTToaThuoc = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;   //Disallow the editing of any cell
			}
			// Chỉnh màu cho body table
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component c = super.prepareRenderer(renderer, row, col);
				if(row % 2 == 0 && !isCellSelected(row,col))
					c.setBackground(Color.decode("#F1F1F1"));
				else
					if(!isCellSelected(row,col))
						c.setBackground(Color.decode("#D7F1FF"));
					else
						c.setBackground(Color.decode("#25C883"));
				return c;
			}
		};
// Chỉnh màu cho tiêu đề table
		JTableHeader header = tabCTToaThuoc.getTableHeader();
		header.setBackground(Color.decode("#007ECA"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Calibri Light", Font.ITALIC, 16));
		header.setOpaque(false);
		
		tabCTToaThuoc.setRowHeight(25);
		tabCTToaThuoc.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabCTToaThuoc.getColumnModel().getColumn(1).setPreferredWidth(ManHinhChinhGUI.width / 9 + 144);
		tabCTToaThuoc.getColumnModel().getColumn(2).setPreferredWidth(ManHinhChinhGUI.width / 9 + 50);
		tabCTToaThuoc.getColumnModel().getColumn(3).setPreferredWidth(ManHinhChinhGUI.width / 9 + 50);
		tabCTToaThuoc.getColumnModel().getColumn(4).setPreferredWidth(ManHinhChinhGUI.width / 9 + 200);
		tabCTToaThuoc.setAutoCreateRowSorter(true);			// sắp xếp
		tabCTToaThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabCTToaThuoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // thiết lập chỉ cho chọn 1 hàng trong bảng
		JScrollPane scoll = new JScrollPane(tabCTToaThuoc);
		scoll.setBorder(BorderFactory.createTitledBorder("Chi tiết toa thuốc"));
		pMid.add(scoll, BorderLayout.CENTER);
		pCenter.add(pMid, BorderLayout.CENTER);
		add(pCenter);
		
		JPanel pSouth = new JPanel();
		Box bSou = Box.createHorizontalBox();
		bSou.add(Box.createVerticalStrut(7));
		bSou.add(btnLuu = new JButton("Lưu", new ImageIcon("hinhanh/save.png")));
		bSou.add(Box.createHorizontalStrut(30));
		bSou.add(btnHuy = new JButton("Hủy", new ImageIcon("hinhanh/cancal.png")));
		bSou.add(Box.createHorizontalStrut(400));
		bSou.add(btnXemToaThuoc = new JButton("Xem các toa thuốc đã kê", new ImageIcon("hinhanh/xemDS.png")));
		btnLuu.setPreferredSize(new Dimension(100, 33));
		btnHuy.setPreferredSize(new Dimension(100, 33));
		btnXemToaThuoc.setPreferredSize(new Dimension(230, 33));
		bSou.add(Box.createVerticalStrut(7));
		pSouth.add(bSou, BorderLayout.EAST);
		add(pSouth, BorderLayout.SOUTH);
	}
}


package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import javax.swing.JOptionPane;
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

import com.google.gson.Gson;
import com.toedter.calendar.JDateChooser;

import entities.BenhNhan;
import entities.ChiTietToaThuoc;
import entities.NhanVien;
import entities.Thuoc;
import entities.ToaThuoc;
import services.BNServiceImpl;
import services.BNServices;
import services.BenhNhanServices;
import services.ChiTietToaThuocServices;
import services.NhanVienServices;
import services.ThuocServices;
import services.ToaThuocServices;
import services.impls.BenhNhanServiceImpl;
import services.impls.ChiTietToaThuocServiceImpl;
import services.impls.NhanVienServiceImpl;
import services.impls.ThuocServiceImpl;
import services.impls.ToaThuocServiceImpl;
import services.BNServices;
import services.ChiTietToaThuocServices;
import services.NhanVienServices;
import services.ThuocServices;
import services.ToaThuocServices;

// import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class KhamBenh_GUI extends JFrame implements ActionListener, MouseListener {

//	JLabel lblTD, lblMaToa, lblNgayKe, lblMaBN, lblTenBN ;
//	JTextField txtMaToa, txtMaBN, txtTenBN, txtTenThuoc, txtNhomThuoc, txtSL, txtDonVT, dcNgayKe;
//	JDateChooser dcNgayKe;
	JButton btnGoiKham;
	
	Date nowDay = new Date();
	JLabel lblAuthor;
	
	JMenuBar menuBar;
	JMenu meHuongDan, meThoat;
	
	JLabel lblTD, lblMaToa, lblNgayNhap, lblTimMaKH, lblTenKH, lblTuoi, lblSDT, lblChuanDoan, lblMaBS,
			lblTenThuoc, lblTPThuoc, lblSoLuong, lblDonVT, lblCachDung, DonVT;
	JTextField txtMaToa, txtTimMaKH, txtMaKH, txtTuoi, txtSDT, txtMaBS, txtSoLuong, txtTenThuoc;
	JTextArea txtChuanDoan, txtCachDung;
	JComboBox<String> cboDonVT, cboTenThuoc;
	JButton btnTim, btnThem, btnSua, btnXemToaThuoc, btnLuu, btnHuy;
	JDateChooser dcNgayNhap;
	DefaultTableModel model;
	JTable tabCTToaThuoc;

	JList<String> listTenKH;
	DefaultListModel<String> modList, modTimKH;
	
	JPopupMenu poMenu = new JPopupMenu();
	JMenuItem mXoa = new JMenuItem("X??a", new ImageIcon("hinhanh/xoa.png"));
	
//	ToaThuocControl dsToaThuoc = new ToaThuocControl();
	ArrayList<ChiTietToaThuoc> arrCTToaThuoc = new ArrayList<ChiTietToaThuoc>();
	int soToaThuoc = 0;
	
	protected BenhNhanServices benhNhanServices;
	protected ChiTietToaThuocServices chiTietToaThuocServices;
	protected NhanVienServices nhanVienServices;
	protected ThuocServices thuocServices;
	protected ToaThuocServices toaThuocServices;
	private BNServices bnServices;
	
	public static DefaultListModel<String> modelKham = new DefaultListModel<>();
	JList<String> listChoKham;
	
	public KhamBenh_GUI() throws RemoteException {
		bnServices= new BNServiceImpl(this);
		
		setTitle("Ph???n m???m qu???n l?? qu???y thu???c");
		setType(Type.POPUP);
		//		setIconImage(Toolkit.getDefaultToolkit().getImage("hinhanh/title.png"));
		setSize(900, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		try{
			SecurityManager securityManager = System.getSecurityManager();
			if(securityManager == null) {
				System.setProperty("java.security.policy","policy\\policyFile.policy");
				securityManager = new SecurityManager();
			}

			benhNhanServices = (BenhNhanServices) Naming.lookup("rmi://localhost:1999/bn_service");
		// goi dangkyp???t k d?????c
			// import project server vo// oong import ddi
			// ong biet co import pro je
			Random rd=new Random();
			int port = 0000+rd.nextInt(100);
			benhNhanServices.dangKyPort(port); 
			this.startServer(port);
			nhanVienServices = (NhanVienServices) Naming.lookup("rmi://localhost:1999/nv_service");
			thuocServices = (ThuocServices) Naming.lookup("rmi://localhost:1999/thuoc_service");
			toaThuocServices = (ToaThuocServices) Naming.lookup("rmi://localhost:1999/toathuoc_service");
			chiTietToaThuocServices = (ChiTietToaThuocServices) Naming.lookup("rmi://localhost:1999/chitiet_service");

		}catch(Exception ex){
			ex.printStackTrace();
		}

		createFrame();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createFrame() throws RemoteException {
		// ------------------ North ------------------ //
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		pNorth.add(lblTD = new JLabel("KH??M B???NH"));
		lblTD.setFont(new Font("Calibri Light", Font.BOLD, 25));
		getContentPane().add(pNorth, BorderLayout.NORTH);
		
		// ------------------ West ------------------ //
		JPanel pnWest = new JPanel();
		Box box = Box.createVerticalBox();
		pnWest.setBorder(BorderFactory.createTitledBorder("Danh s??ch b???nh nh??n ch??? kh??m"));
		JScrollPane scroll = new JScrollPane(listChoKham = new JList<>(modelKham));
		box.add(scroll);
		box.add(Box.createVerticalStrut(5));
		JPanel panelGoiKham = new JPanel();
		panelGoiKham.setLayout(new BorderLayout());
		panelGoiKham.add(btnGoiKham = new JButton("                G???i kh??m...                "));
		box.add(panelGoiKham);
		scroll.setPreferredSize(new Dimension(220, 550));
		btnGoiKham.setPreferredSize(new Dimension(220, 50));
		pnWest.add(box);
		getContentPane().add(pnWest, BorderLayout.WEST);
		
		// ------------------ Center ------------------ //
//		JPanel pCenter = new JPanel();
//		pCenter.setLayout(new BorderLayout());
//		
//		JPanel pCenNor = new JPanel();
//		pCenNor.setLayout(new BorderLayout());
//		Box boxHD = Box.createVerticalBox();
//		boxHD.setBorder(BorderFactory.createTitledBorder("H??a ????n"));
//		Box b1, b2;
//		boxHD.add(b1 = Box.createHorizontalBox());
//		b1.add(Box.createHorizontalStrut(15));
//		b1.add(lblMaToa = new JLabel("M?? toa thu???c  "));
//		b1.add(txtMaToa = new JTextField());
//		b1.add(Box.createHorizontalStrut(15));
//		
//		b1.add(lblNgayKe = new JLabel("Ng??y k??"));
//		b1.add(dcNgayKe = new JTextField());
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		
		JPanel pMid = new JPanel();
		pMid.setLayout(new BorderLayout());
		JPanel pAboveMid = new JPanel();
		pAboveMid.setLayout(new GridLayout(2, 0));
		pAboveMid.setPreferredSize(new Dimension(898,300));
		
		JPanel p1 = new JPanel();
		p1.setLayout(null);
		p1.setBorder(BorderFactory.createTitledBorder("Toa thu???c"));
		p1.add(lblMaToa = new JLabel("M?? toa thu???c"));
		p1.add(txtMaToa = new JTextField());
		lblMaToa.setBounds(10, 20, 100, 23);
		txtMaToa.setBounds(126, 18, 211, 25);
		p1.add(lblNgayNhap = new JLabel("Ng??y l???p"));
		p1.add(dcNgayNhap = new JDateChooser());
		dcNgayNhap.setDateFormatString("yyyy-MM-dd");
		dcNgayNhap.setDate(nowDay);
		lblNgayNhap.setBounds(373, 20, 60, 23);
		dcNgayNhap.setBounds(441, 18, 211, 25);
		
		p1.add(lblTenKH = new JLabel("M?? benh nhan"));
		p1.add(txtMaKH = new JTextField());
		lblTenKH.setBounds(10, 60, 100, 23);
		txtMaKH.setBounds(126, 60, 211, 25);
		p1.add(lblTuoi = new JLabel("Tu???i"));
		p1.add(txtTuoi = new JTextField());
		lblTuoi.setBounds(373, 60, 60, 23);
		txtTuoi.setBounds(441, 58, 211, 25);
		
		p1.add(lblSDT = new JLabel("??i???n tho???i"));
		p1.add(txtSDT = new JTextField());
		lblSDT.setBounds(10, 100, 100, 23);
		txtSDT.setBounds(126, 98, 211, 25);
		p1.add(lblMaBS = new JLabel("B??c s??"));
		p1.add(txtMaBS = new JTextField());
		lblMaBS.setBounds(373, 100, 60, 23);
		txtMaBS.setBounds(441, 98, 211, 25);
		
		pAboveMid.add(p1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(null);
		p2.setBorder(BorderFactory.createTitledBorder("Chi ti???t toa thu???c"));
		p2.add(lblTenThuoc = new JLabel("T??n thu???c"));
		p2.add( cboTenThuoc = new JComboBox<>());
		lblTenThuoc.setBounds(10, 20, 80, 23);
		cboTenThuoc.setBounds(125, 20, 211, 25);
		
		List<Thuoc> list = thuocServices.getThuocs();
		
		for (Thuoc thuoc : list) {
			cboTenThuoc.addItem(thuoc.getTenThuoc());
		}
		
		
		p2.add(lblSoLuong = new JLabel("S??? L?????ng"));
		p2.add(txtSoLuong = new JTextField());
		lblSoLuong.setBounds(10, 60, 80, 23);
		txtSoLuong.setBounds(125, 60, 211, 25);
		p2.add(lblDonVT = new JLabel("????n v??? t??nh"));
		p2.add(cboDonVT = new JComboBox<String>());
		lblDonVT.setBounds(353, 60, 75, 23);
		cboDonVT.setBounds(440, 60, 211, 25);
		cboDonVT.addItem("Vi??n");
		cboDonVT.addItem("V???");
		cboDonVT.addItem("H???p");
		cboDonVT.addItem("L???");
		
		p2.add(btnThem = new JButton("Th??m", new ImageIcon("hinhanh/add.png")));
		p2.add(btnSua = new JButton("  S???a  ", new ImageIcon("hinhanh/edit.png")));
		btnThem.setBounds(125, 97, 100, 30);
		btnSua.setBounds(236, 97, 100, 30);
		
		JPanel pSouth = new JPanel();
		pMid.add(pSouth, BorderLayout.SOUTH);
		Box bSou = Box.createHorizontalBox();
		bSou.add(Box.createVerticalStrut(7));
		bSou.add(btnLuu = new JButton("L??u", new ImageIcon("hinhanh/save.png")));
		bSou.add(Box.createHorizontalStrut(30));
		bSou.add(btnHuy = new JButton("H???y", new ImageIcon("hinhanh/cancal.png")));
		bSou.add(Box.createHorizontalStrut(100));
		bSou.add(btnXemToaThuoc = new JButton("Xem c??c toa thu???c ???? k??", new ImageIcon("hinhanh/xemDS.png")));
		btnLuu.setPreferredSize(new Dimension(100, 33));
		btnHuy.setPreferredSize(new Dimension(100, 33));
		btnXemToaThuoc.setPreferredSize(new Dimension(230, 33));
		bSou.add(Box.createVerticalStrut(7));
		pSouth.add(bSou, BorderLayout.EAST);
		
		pAboveMid.add(p2);
		pMid.add(pAboveMid, BorderLayout.NORTH);
		
		String[] a = {"STT", "T??n thu???c", "S??? l?????ng", "????n v??? t??nh", "C??ch d??ng"};
		model = new DefaultTableModel(a, 0);
		tabCTToaThuoc = new JTable(model) {
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;   //Disallow the editing of any cell
			}
			// Ch???nh m??u cho body table
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
// Ch???nh m??u cho ti??u ????? table
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
		tabCTToaThuoc.setAutoCreateRowSorter(true);			// s???p x???p
		tabCTToaThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabCTToaThuoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // thi???t l???p ch??? cho ch???n 1 h??ng trong b???ng
		JScrollPane scoll = new JScrollPane(tabCTToaThuoc);
		scoll.setBorder(BorderFactory.createTitledBorder("Chi ti???t toa thu???c"));
		pMid.add(scoll, BorderLayout.CENTER);
		pCenter.add(pMid, BorderLayout.CENTER);
		getContentPane().add(pCenter);
//		b1.add(dcNgayKe = new JDateChooser());
//		dcNgayKe.setDateFormatString("yyyy-MM-dd");
//		Date day = new Date();
//		dcNgayKe.setDate(day);
		
		
//		b1.add(Box.createHorizontalStrut(200));
//		boxHD.add(Box.createVerticalStrut(10));
//		boxHD.add(b2 = Box.createHorizontalBox());
//		b2.add(Box.createHorizontalStrut(15));
//		
//		b2.add(lblMaBN = new JLabel("M?? b???nh nh??n     "));
//		b2.add(txtMaBN = new JTextField("Kh??ch v??ng lai"));
//		
//		b2.add(Box.createHorizontalStrut(15));
//		b2.add(lblTenBN = new JLabel("H??? t??n  "));
//		b2.add(txtTenBN = new JTextField("Kh??ng k?? ????n"));			// kh??ng c?? ????n c???a b??c s??
//		b2.add(Box.createHorizontalStrut(200));
//		boxHD.add(Box.createVerticalStrut(10));
//		lblNgayKe.setPreferredSize(lblMaBN.getPreferredSize());
//		lblMaToa.setPreferredSize(lblMaBN.getPreferredSize());
//		txtMaToa.setPreferredSize(new Dimension(150, 22));
//		dcNgayKe.setPreferredSize(new Dimension(100, 23));
//		txtMaBN.setPreferredSize(txtMaToa.getPreferredSize());
//		txtTenBN.setPreferredSize(txtMaToa.getPreferredSize());
//		pCenNor.add(boxHD, BorderLayout.NORTH);
		
		
		getContentPane().add(pCenter, BorderLayout.CENTER);
		
		batsukien();
	}
	
	public void batsukien() {
		btnGoiKham.addActionListener(this);
	}

	public static void main(String[] args) throws RemoteException {
		new KhamBenh_GUI().setVisible(true);
	}
	
	/**
	 * b???t s??? ki???n button
	 */
	public void eventAction() {
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
		btnLuu.addActionListener(this);
		btnHuy.addActionListener(this);
		btnXemToaThuoc.addActionListener(this);
		
		mXoa.addActionListener(this);
	}
	/**
	 * b???t s??? ki???n k??ch chu???t
	 */
	public void eventClick() {
		this.addMouseListener(this);
		tabCTToaThuoc.addMouseListener(this);
		meThoat.addMouseListener(this);
//		dsTenThuoc.addMouseListener(this);
		listTenKH.addMouseListener(this);
	}
	/**
	 * b???t s??? ki???n nh???n ph??m
	 */
	
	
	public void hiddenTextFieldToaThuoc(boolean b) {
		txtMaToa.setEditable(b);
		dcNgayNhap.setEnabled(b);
//		((JTextField)dcNgayNhap.getDateEditor().getUiComponent()).setEditable(b);
		txtMaKH.setEditable(b);
		txtTuoi.setEditable(b);
		txtSDT.setEditable(b);
		txtMaBS.setEditable(b);
	}
	public void hienTextFielCTToaThuoc(boolean b) {
		txtTenThuoc.setEditable(b);
		txtSoLuong.setEditable(b);
		txtCachDung.setEnabled(b);
		txtChuanDoan.setEnabled(b);
		cboDonVT.setEnabled(b);
	}	
	
	/**
	 * ????a d??? li???u chi ti???t toa thu???c l??n text field
	 */
	public void ctToaThuocDuaThongTinVaoTextField(int row) {
		txtTenThuoc.setText(tabCTToaThuoc.getValueAt(row, 1).toString());
		txtSoLuong.setText(tabCTToaThuoc.getValueAt(row, 2).toString());
		cboDonVT.setSelectedItem(tabCTToaThuoc.getValueAt(row, 3).toString());
		txtCachDung.setText(tabCTToaThuoc.getValueAt(row, 4).toString());
	}
	
	/**
	 * ????a d??? li???u kh??ch h??ng l??n text field
	 */
	@SuppressWarnings("deprecation")
	public void khachhangDuaThongTinVaoTextField(BenhNhan kh) {
		txtMaKH.setText(kh.getMaBN());
//		txtTuoi.setText((nowDay.getYear() - kh.getNgaySinh().getYear()) + " - " + kh.getGt());
		txtSDT.setText(kh.getSodt());
	}
	
	/**
	 * X??a d??? li???u tr??n textfield chi ti???t toa thu???c
	 */
	public void ctToaThuocXoaTrang() {
		txtTenThuoc.setText("");
		txtSoLuong.setText("");
		txtCachDung.setText("");
	}
	
	/**
	 * X??a d??? li???u textfield toa thuoc
	 */
	public void toathuocXoaTrang() {
		txtMaKH.setText("");
		txtTuoi.setText("");
		txtSDT.setText("");
		txtChuanDoan.setText("");
	}
	
	/**
	 * ????a t??n thu???c v??o JList
	 * @param dsTen
	 */
//	public void duaTenThuocVaoJList(ArrayList<Thuoc> dsTen) {
//		for(int i = 0; i < dsTen.size(); i++) {
//			modList.addElement(dsTen.get(i).getTenThuoc());
//			dsTenThuoc.setSelectedIndex(0);
//		}
//	}
	
	/**
	 * t??m t??n thu???c c?? trong l??
	 * @param ten
	 * @return
	 */
//	public boolean timTenThuoc(String ten) {
//		ArrayList<Thuoc> arrThuoc = new ArrayList<Thuoc>();
//		ThuocControl thuocCon = new ThuocControl();
//		arrThuoc = thuocCon.timThuoc(1, txtTenThuoc.getText());
//		if(arrThuoc.size() != 0) {
//			modList.removeAllElements();
//			duaTenThuocVaoJList(arrThuoc);
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * ????a t??n kh??ch h??ng v??o JList
	 * @param dsTen
	 */
//	public void duaTenKhachHangVaoJList(ArrayList<BenhNhan> dsTen) {
//		for(int i = 0; i < dsTen.size(); i++) {
//			modTimKH.addElement(dsTen.get(i).getTenKH());
//			listTenKH.setSelectedIndex(0);
//		}
//	}
	
	/**
	 * t??m t??n kh??ch h??ng
	 * @param ten
	 * @return
	 */
//	public boolean timTenKhachHang(String ten) {
//		ArrayList<KhachHang> arrKH = new ArrayList<KhachHang>();
//		QLKhachHangControl kh = new QLKhachHangControl();
//		arrKH = kh.timKhachHang(1, ten);
//		if(arrKH.size() != 0) {
//			modTimKH.removeAllElements();
//			duaTenKhachHangVaoJList(arrKH);
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * ki???m tra c??c d??? li???u nh???p v??o
	 * @return
	 */
	public boolean ktNhapVao() {
		if(txtTenThuoc.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "T??n thu???c kh??ng ???????c r???ng");
			txtTenThuoc.requestFocus();
			return false;
		}
		
		if(txtSoLuong.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "S??? l?????ng kh??ng ???????c r???ng");
			txtSoLuong.requestFocus();
			return false;
		}
		else {
			if(!txtSoLuong.getText().matches("(-\\d+)|(\\d+)")) {
				JOptionPane.showMessageDialog(this, "Sai ?????nh d???ng. Ph???i nh???p s???");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
				return false;
			}
			else if(Integer.parseInt(txtSoLuong.getText()) < 0) {
				JOptionPane.showMessageDialog(this, "S??? l?????ng ph???i l???n h??n 0");
				txtSoLuong.selectAll();
				txtSoLuong.requestFocus();
				return false;
			}
		}
		
		if(txtCachDung.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "C??ch d??ng kh??ng ???????c r???ng");
			txtCachDung.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * ????a th??ng tin v??o ?????i t????ng chi ti???t toa thu???c
	 * @return
	 */
	public ChiTietToaThuoc layTTChiTietToaThuoc() {
		if(ktNhapVao()) {
			int maToaThuoc = Integer.parseInt(txtMaToa.getText());
			String tenThuoc = txtTenThuoc.getText();
			int soLuong = Integer.parseInt(txtSoLuong.getText());
			String donVT = cboDonVT.getSelectedItem().toString();
//			String cachDung = txtCachDung.getText();
			ToaThuoc toaThuoc = new ToaThuoc();
			toaThuoc.setMaToa(maToaThuoc);
			Thuoc thuoc = new Thuoc();
			ChiTietToaThuoc ctToaThuoc = new ChiTietToaThuoc(toaThuoc, thuoc, soLuong, donVT, "");
//					ChiTietToaThuoc(maToaThuoc, tenThuoc, soLuong, donVT, cachDung);
			return ctToaThuoc;
		}
		return null;
	}

	/**
	 * Th??m chi ti???t toa thu???c v??o b???ng
	 */
	public ArrayList<ChiTietToaThuoc> themChiTietToaThuoc(ChiTietToaThuoc ctToaThuoc) {
		if(ctToaThuoc != null) {
//			ThuocControl t = new ThuocControl();
//			if(t.kiemtraTenThuoc(ctToaThuoc.getTenThuoc())) {
				if(!arrCTToaThuoc.contains(ctToaThuoc)) {
					arrCTToaThuoc.add(ctToaThuoc);
					String[] a = {Integer.toString(tabCTToaThuoc.getRowCount() + 1), ctToaThuoc.getThuoc().getTenThuoc(),
								  Integer.toString(ctToaThuoc.getSoluong()), ctToaThuoc.getDonVT(), ctToaThuoc.getCachdung()};
					model.addRow(a);
					ctToaThuocXoaTrang();
					txtTenThuoc.requestFocus();
				}
				else
					JOptionPane.showMessageDialog(this, "Thu???c n??y ???? c?? trong chi ti???t toa thu???c");
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "Qu???y thu???c ch??a c?? thu???c n??y");
//				txtTenThuoc.selectAll();
//				txtTenThuoc.requestFocus();
//			}
		}
		return arrCTToaThuoc;
	}
	
	/**
	 * S???a l???i th??ng tin chi ti???t toa thu???c
	 */
	public void suaThongTinCTToaThuoc(ChiTietToaThuoc ctToaThuoc) {
		int row = tabCTToaThuoc.getSelectedRow();
//		if(ctToaThuoc != null) {
//			ThuocControl t = new ThuocControl();
//			if(t.kiemtraTenThuoc(ctToaThuoc.getTenThuoc())) {
//				model.setValueAt(ctToaThuoc.getTenThuoc(), row, 1);
//				model.setValueAt(Integer.toString(ctToaThuoc.getSoLuong()), row, 2);
//				model.setValueAt(ctToaThuoc.getDonVT(), row, 3);
//				model.setValueAt(ctToaThuoc.getCachDung(), row, 4);
//				JOptionPane.showMessageDialog(this, "Th??ng tin ???????c s???a th??nh c??ng");
//				ctToaThuocXoaTrang();
//				btnSua.setEnabled(false);
//			}
//			else {
//				JOptionPane.showMessageDialog(this, "Qu???y thu???c ch??a c?? thu???c n??y");
//				txtTenThuoc.requestFocus();
//			}
//		}
	}
	
	/**
	 * X??a chi ti???t h??a ????n
	 */
	public void xoaChiTietToaThuoc(int row) {
		if(row != -1) {
			if(JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n x??a kh??ng?",
					"C???nh b??o", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				arrCTToaThuoc.remove(row);
				model.setNumRows(0);
				for(int i = 0; i < arrCTToaThuoc.size(); i++) {
					ChiTietToaThuoc ctToaThuoc = arrCTToaThuoc.get(i);
					String[] a = {Integer.toString(tabCTToaThuoc.getRowCount() + 1), ctToaThuoc.getThuoc().getTenThuoc(),
							  Integer.toString(ctToaThuoc.getSoluong()), ctToaThuoc.getDonVT(), ctToaThuoc.getCachdung()};
					model.addRow(a);
				}
			}
		}
		else
			JOptionPane.showMessageDialog(this, "B???n ph???i ch???n chi ti???t toa thu???c c???n x??a");
	}
	
	/**
	 * ????a th??ng tin v??o ?????i t????ng toa thu???c
	 * @return
	 */
	public ToaThuoc layTTToaThuoc() {
		int maToaThuoc = Integer.parseInt(txtMaToa.getText());
		Date ngayLap = dcNgayNhap.getDate();
		String tenKH = txtTimMaKH.getText();
		String chuanDoan = txtChuanDoan.getText();
		String maBS = txtMaBS.getText();
		
		ToaThuoc tt = new ToaThuoc(maToaThuoc, LocalDate.now(), "");
		return tt;
	}
	
	/**
	 * H???y toa thu???c
	 */
	public void huyToaThuoc() {
		hienTextFielCTToaThuoc(false);
		txtTimMaKH.setText("");
		arrCTToaThuoc = new ArrayList<>();
		model.setNumRows(0);
		toathuocXoaTrang();
		btnThem.setEnabled(false);
	}
	
	/**
	 * L??u toa thu???c v??o c?? s??? d??? li???u
	 * @param tt
	 * @throws RemoteException 
	 */
	public void luuToaThuoc(ToaThuoc tt) throws RemoteException {
		if(tt != null) {
				if(arrCTToaThuoc.size() != 0) {
					if(toaThuocServices.themToaThuoc(tt)) {
						for(int i = 0; i < arrCTToaThuoc.size(); i++) {
							chiTietToaThuocServices.themChiTietToaThuoc(arrCTToaThuoc.get(i));
						}
						JOptionPane.showMessageDialog(this, "Toa thu???c ???????c th??m th??nh c??ng");
						huyToaThuoc();
						btnHuy.setEnabled(false);
						txtMaToa.setText(Integer.toString(++soToaThuoc));
					}
				}
				else
					JOptionPane.showMessageDialog(this, "Chi ti???t toa thu???c ch??a c?? n??n kh??ng th??? l??u");
		}
	}
	
	/**
	 * T??m th??ng tin kh??ch h??ng
	 */
	public void timThongTinKH(String maKH) {
//		QLKhachHangControl qlKhachHang = new QLKhachHangControl();
//		ArrayList<KhachHang> kh = qlKhachHang.timKhachHang(0, maKH);
//		if(kh.size() != 0) {
//			khachhangDuaThongTinVaoTextField(kh.get(0));
//			btnThem.setEnabled(true);
//			hienTextFielCTToaThuoc(true);
//			btnHuy.setEnabled(true);
//			txtChuanDoan.requestFocus();
//		}
//		else {
//			JOptionPane.showMessageDialog(this, "Th??ng tin kh??ch h??ng n??y ch??a c??.");
//			txtTimMaKH.selectAll();
//			txtTimMaKH.requestFocus();
//		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == btnTim) {
			String maKH = txtTimMaKH.getText();
			if(maKH.equals("")) {
				JOptionPane.showMessageDialog(this, "B???n ch??a nh???p th??ng tin mu???n t??m");
				txtTimMaKH.requestFocus();
				return;
			}
//			timThongTinKH(maKH);
//			scrTimKH.setVisible(false);
		}
		
		else if(ob == btnThem) {
			ChiTietToaThuoc ctToa = layTTChiTietToaThuoc();
			themChiTietToaThuoc(ctToa);
		}
		else if(ob == btnGoiKham) {
			int index = listChoKham.getSelectedIndex();
			modelKham.remove(index);
		}
		
		else if(ob == btnSua) {
			ChiTietToaThuoc ctToa = layTTChiTietToaThuoc();
			suaThongTinCTToaThuoc(ctToa);
		}
		
		else if(ob == btnLuu) {
			ToaThuoc tt = layTTToaThuoc();
			try {
				luuToaThuoc(tt);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(ob == mXoa) {
			int row = tabCTToaThuoc.getSelectedRow();
			xoaChiTietToaThuoc(row);
		}
		
		else if(ob == btnXemToaThuoc) {
//			this.dispose();
//			new XemDSBacSiKeToaGUI().setVisible(true);
		}		
		
		else if(ob == btnHuy)
			huyToaThuoc();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object ob = e.getSource();
//		scrollTimKiem.setVisible(false);
//		scrTimKH.setVisible(false);
//		if(ob == tabCTToaThuoc) {
//			int row = tabCTToaThuoc.getSelectedRow();
//			if(row != -1) {
//				ctToaThuocDuaThongTinVaoTextField(row);
//				btnSua.setEnabled(true);
//			}
//		}
//		
//		else if(ob == dsTenThuoc) {
//			int row = dsTenThuoc.getSelectedIndex();
//			if(row != -1) {
//				String ten = dsTenThuoc.getSelectedValue();
//				txtTenThuoc.setText(ten);
//				scrollTimKiem.setVisible(false);
//				txtSoLuong.requestFocus();
//			}
//		}
		
		if(ob == listTenKH) {
			int row = listTenKH.getSelectedIndex();
			if(row != -1) {
				txtTimMaKH.setText(listTenKH.getSelectedValue());
			}
		}
		
		else if(ob == meThoat)
			if(JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n x??a kh??ng?",
					"C???nh b??o", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				if(ManHinhChinhGUI.user == 1) {
					ManHinhChinhGUI.user = 0;
					ManHinhChinhGUI.tenDN = "";
					ManHinhChinhGUI.maDN = "";
					new ManHinhChinhGUI().setVisible(true);
					this.dispose();
				}
			}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger())
			poMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	public void themBN(BenhNhan bn) {
		// theem ben nahn vo maay cai table cho nay
		modelKham.addElement(bn.getMaBN()+"--"+bn.getTenBN());
		System.out.println("Them vo giao dien");
//		JOptionPane.showMessageDialog(null, bn.toString());
		System.out.println(bn);
		
	}
	private void startServer(int port) throws RemoteException, MalformedURLException {
		SecurityManager securityManager = System.getSecurityManager();
		if(securityManager == null) {
			System.setProperty("java.security.policy","policy\\policyFile.policy");
			securityManager = new SecurityManager();
		}

	
		LocateRegistry.createRegistry(port);
		
//		Naming.rebind("rmi://h73m21:1999/sv_service", sinhvienServices);
//		Naming.rebind("rmi://h73m21:1999/lh_service", lophocServices);

		Naming.rebind("rmi://localhost:"+port+"/bn_service", bnServices);
		
		System.out.println("Server is ready ... ");
	}

}

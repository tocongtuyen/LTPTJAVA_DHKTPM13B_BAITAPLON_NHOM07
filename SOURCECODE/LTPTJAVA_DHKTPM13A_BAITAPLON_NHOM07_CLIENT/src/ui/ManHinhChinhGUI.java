package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import services.BNServices;
import services.BenhNhanServices;
import services.ChiTietToaThuocServices;
import services.NhanVienServices;
import services.ThuocServices;
import services.ToaThuocServices;


/**
 * 
 * @author Thanh Duy
 * 
 * MÃ n hÃ¬nh chÃ­nh cá»§a á»©ng dá»¥ng
 *
 */
@SuppressWarnings("serial")
public class ManHinhChinhGUI extends JFrame implements ActionListener, MouseListener {

	public static int user = 0;
	public static String tenDN = "";		// lưu lại tên đăng nhập
	public static String maDN = "";
	public static int width = 900, height = 600;
	protected BenhNhanServices benhNhanServices;
	protected ChiTietToaThuocServices chiTietToaThuocServices;
	protected NhanVienServices nhanVienServices;
	protected ThuocServices thuocServices;
	protected ToaThuocServices toaThuocServices;

	JMenuBar menuBar;
	JMenu meTrangChu, meDanhMuc, meNhanSu, meTuyChon;
	JMenuItem dmBenhNhan, dmThuoc;
	JMenuItem nsBacSi, nsNhanVien;
	JMenuItem tcLogin, tcThoat;

	JPanel pCenter = new JPanel();;
	JLabel lblAuthor;
	JButton btnDN;
	private JLabel imgbutton;

	public ManHinhChinhGUI() {
		setTitle("Phần mềm quản lý quầy thuốc");
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("hinhanh/title.png"));
		setSize(width, height);
		setLocationRelativeTo(null);
		setResizable(false);
		
		createFrame();
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				try{
					SecurityManager securityManager = System.getSecurityManager();
					if(securityManager == null) {
						System.setProperty("java.security.policy","policy\\policyFile.policy");
						securityManager = new SecurityManager();
					}

					benhNhanServices = (BenhNhanServices) Naming.lookup("rmi://localhost:1999/bn_service"); // chỗ này client 
					nhanVienServices = (NhanVienServices) Naming.lookup("rmi://localhost:1999/nv_service");
					thuocServices = (ThuocServices) Naming.lookup("rmi://localhost:1999/thuoc_service");
					toaThuocServices = (ToaThuocServices) Naming.lookup("rmi://localhost:1999/toathuoc_service");
					chiTietToaThuocServices = (ChiTietToaThuocServices) Naming.lookup("rmi://localhost:1999/chitiet_service");

				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createFrame() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.setPreferredSize(new Dimension(0, 35));

		menuBar.add(meTrangChu = new JMenu("Trang chủ"));
		menuBar.add(meDanhMuc = new JMenu("Danh mục"));
		menuBar.add(meNhanSu = new JMenu("Quản lý nhân sự"));
		menuBar.add(meTuyChon = new JMenu("Tùy chọn"));
		meDanhMuc.setPreferredSize(new Dimension(90, 30));
		meNhanSu.setPreferredSize(new Dimension(150, 30));
		meTuyChon.setPreferredSize(new Dimension(200, 30));

		// ------------------ Danh mục ------------------ //		
		meDanhMuc.add(dmThuoc = new JMenuItem("Thuốc"));
		meDanhMuc.add(dmBenhNhan = new JMenuItem("Bệnh nhân"));

		dmBenhNhan.setPreferredSize(new Dimension(160, 30));
		dmThuoc.setPreferredSize(dmBenhNhan.getPreferredSize());

		// ------------------ Nhân sự ------------------ //
		meNhanSu.add(nsBacSi = new JMenuItem("Bác sĩ"));
		meNhanSu.add(nsNhanVien = new JMenuItem("Nhân viên"));
		nsNhanVien.setPreferredSize(new Dimension(150, 30));
		nsBacSi.setPreferredSize(nsNhanVien.getPreferredSize());

		// ------------------ Tùy chọn ------------------ //
		meTuyChon.add(tcLogin = new JMenuItem("Đăng nhập"));
		meTuyChon.add(tcThoat = new JMenuItem("Thoát"));
		tcLogin.setPreferredSize(new Dimension(150, 30));
		tcThoat.setPreferredSize(tcLogin.getPreferredSize());


		pCenter.setLayout(new BorderLayout());
		pCenter.add(panelCenter());
		add(pCenter, BorderLayout.CENTER);
		
		if(user == 0) {
			hiddenMenu(false);
		}
		else {
			meTuyChon.setText(tenDN);
			tcLogin.setText("Đăng xuất");
			hiddenMenu(true);
		}

		meTrangChu.setEnabled(false);

		eventActions();
		eventClickMouse();
	}

	public JPanel panelCenter() {
		pCenter.setBackground(Color.CYAN);
		JLabel lblDT, lblNhom, lblTVN;

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Color.CYAN);

		Font f1 = new Font("Calibri Light", Font.BOLD, 25);
		Font f2 = new Font("Calibri Light", Font.BOLD, 22);
		Font f3 = new Font("Time new roman", Font.BOLD, 14);

		Box b1 = Box.createVerticalBox();
		b1.add(Box.createVerticalStrut(35));
		JPanel pTop = new JPanel();
		pTop.setBackground(Color.CYAN);
		pTop.add(lblDT = new JLabel("Đề tài: Quản lý thông tin quầy thuốc của một bệnh viện"));
		lblDT.setFont(f1);
		b1.add(pTop);
		panel.add(b1, BorderLayout.NORTH);

		JPanel pCen = new JPanel();
		pCen.setLayout(new BorderLayout());
		pCen.setBackground(Color.CYAN);

		Box b2 = Box.createVerticalBox();
		b2.add(Box.createVerticalStrut(15));
		JPanel p2 = new JPanel();
		p2.add(lblNhom = new JLabel("Nhóm: 08"));
		p2.setBackground(Color.CYAN);
		lblNhom.setFont(f1);
		b2.add(p2);
		b2.add(Box.createVerticalStrut(25));
		pCen.add(b2, BorderLayout.NORTH);

		JPanel pLeft = new JPanel();
		pLeft.setBackground(Color.CYAN);
		imgbutton = new JLabel(new ImageIcon("hinhanh/quaythuoc.jpg"));

		pLeft.add(imgbutton);
		imgbutton.addMouseListener(this);
		pCen.add(pLeft, BorderLayout.WEST);

		JPanel pCenMid = new JPanel();
		pCenMid.setBackground(Color.CYAN);
		JLabel lblTen1, lblTen2;
		Box bMid = Box.createVerticalBox();
		bMid.add(Box.createVerticalStrut(30));
		bMid.add(lblTVN = new JLabel("Thành viên nhóm:"));
		bMid.add(Box.createVerticalStrut(15));
		bMid.add(lblTen1 = new JLabel("          Phạm Thanh Duy                              16043751"));
		bMid.add(Box.createVerticalStrut(15));
		bMid.add(lblTen2 = new JLabel("          Tô Công Tuyển                                 16013611"));
		bMid.add(Box.createVerticalStrut(15));
		lblTVN.setFont(f2);
		lblTen1.setFont(f3);
		lblTen2.setFont(f3);
		pCenMid.add(bMid);
		pCen.add(pCenMid);
		panel.add(pCen, BorderLayout.CENTER);

		if(user == 0) {
			JPanel pBot = new JPanel();
			pBot.add(btnDN = new JButton("Đăng nhập"));
			btnDN.setPreferredSize(new Dimension(150, 40));
			pBot.add(Box.createVerticalStrut(80));
			pBot.setBackground(Color.CYAN);
			btnDN.addActionListener(this);
			panel.add(pBot,BorderLayout.SOUTH);
		}

		return panel;
	}

	public void hiddenMenu(boolean b) {
		meTrangChu.setEnabled(b);

		dmThuoc.setEnabled(b);
		dmBenhNhan.setEnabled(b);

		nsNhanVien.setEnabled(b);
		nsBacSi.setEnabled(b);
	}

	public void eventActions() {
		// --------- sự kiện danh mục ------------ //
		dmBenhNhan.addActionListener(this);
		dmThuoc.addActionListener(this);

		// --------- sự kiện hóa đơn ------------ //
		nsNhanVien.addActionListener(this);
		nsBacSi.addActionListener(this);
		
		tcLogin.addActionListener(this);
		tcThoat.addActionListener(this);
	}

	public void eventClickMouse() {
		meTrangChu.addMouseListener(this);
		meTuyChon.addMouseListener(this);
	}

	public void callNewPanel() {
		pCenter.setBackground(null);
		pCenter.removeAll();
		pCenter.repaint();
		pCenter.revalidate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if(ob == btnDN) {
			try {
				new DangNhapGUI(nhanVienServices).setVisible(true);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		}		
		else {
			hiddenMenu(true);
			callNewPanel();
			if(ob == dmThuoc) {
				try {
					pCenter.add(new QLThongTinThuocGUI(thuocServices));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dmThuoc.setEnabled(false);
			}
			else if(ob == dmBenhNhan) {
				try {
					pCenter.add(new QLThongTinBenhNhanGUI(benhNhanServices));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dmBenhNhan.setEnabled(false);
			}
			else if(ob == nsNhanVien) {
				try {
					pCenter.add(new QLThongTinNhanVienGUI(nhanVienServices));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nsNhanVien.setEnabled(false);
			}
//			else if(ob == nsBacSi) {
//				pCenter.add(new QLThongTinBacSiGUI());
//				nsBacSi.setEnabled(false);
//			}
			else if(ob == tcLogin) {
				if (tcLogin.getText().equalsIgnoreCase("Đăng xuất")) {
					if(user != 0) {
						user = 0;
						tenDN = "";
						maDN = "";
						new ManHinhChinhGUI().setVisible(true);
						this.dispose();
					}
				}
				else {
					try {
						new DangNhapGUI(nhanVienServices).setVisible(true);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.dispose();
				}
			}
			else if(ob == tcThoat) {
				if(JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa không?",
						"Cảnh báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object ob = e.getSource();
		if (ob == imgbutton) {
			JOptionPane.showMessageDialog(this,"ssssss");
		}
		hiddenMenu(true);
		if(ob == meTrangChu) {
			callNewPanel();
			pCenter.add(panelCenter());
			meTrangChu.setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
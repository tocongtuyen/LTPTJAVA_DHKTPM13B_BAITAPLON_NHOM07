package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import com.toedter.calendar.JDateChooser;

import entities.BenhNhan;
import services.BenhNhanServices;
import services.impls.BenhNhanServiceImpl;

/**
 * 
 * @author Thanh Duy
 * 
 * Quản lý thông tin khách hàng
 *
 */
@SuppressWarnings("serial")
public class QLThongTinBenhNhanGUI extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	JLabel lblTD, lblMaBN, lblTenBN, lblGT, lblNS, lblSDT, lblDC, lblTB;
	JTextField txtMaBN, txtTenBN, txtSDT, txtTimTT;
	JTextArea txtDC;
	JDateChooser dchNgay;
	JRadioButton radNu, radNam;
	ButtonGroup bgrGioiTinh = new ButtonGroup();
	JComboBox<String> cboTim;
	JButton btnThem, btnSua, btnTroVe, btnXoa, btnTim, btnGoiKham;
	DefaultTableModel model;
	JTable tabBenhNhan;
	
	JPopupMenu poMenu = new JPopupMenu();
	JMenuItem mTaiLai = new JMenuItem("Tải lại", new ImageIcon("hinhanh/capnhat.png"));
	
	BenhNhanServices bnControl; 
	List<BenhNhan> dsBenhNhan = new ArrayList<>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date nowDay = new Date();
	
	public QLThongTinBenhNhanGUI() throws RemoteException {
		bnControl = new BenhNhanServiceImpl();
		poMenu.add(mTaiLai);
		poMenu.setPreferredSize(new Dimension(100, 30));
		
		setLayout(new BorderLayout());
		
		// ------------------ Nội dung ------------------ //
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		pNorth.add(lblTD = new JLabel("QUẢN LÝ THÔNG TIN BỆNH NHÂN"));
		lblTD.setFont(new Font("Calibri Light", Font.BOLD, 25));
		add(pNorth, BorderLayout.NORTH);

		// ------------ Ở giữa ------------ //
		JPanel pCenter = new JPanel();
		pCenter.add(Box.createVerticalStrut(20));
		pCenter.setLayout(new BorderLayout());
		
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		Box bTim;
		box.add(bTim = Box.createHorizontalBox());
		bTim.add(Box.createHorizontalStrut(70));
		bTim.add(txtTimTT = new JTextField());
		bTim.add(Box.createHorizontalStrut(15));
		bTim.add(cboTim = new JComboBox<>());
		cboTim.addItem("Mã");
		cboTim.addItem("Họ tên");
		cboTim.setSelectedIndex(1);
		cboTim.setPreferredSize(new Dimension(120,25));
		bTim.add(Box.createHorizontalStrut(15));
		bTim.add(btnTim = new JButton(new ImageIcon("hinhanh/search.png")));
		bTim.add(Box.createHorizontalStrut(15));
		bTim.add(btnGoiKham = new JButton("Gọi khám"));
		bTim.add(Box.createHorizontalStrut(445));
		txtTimTT.setPreferredSize(new Dimension(200, 25));
		btnTim.setPreferredSize(new Dimension(35, 25));
		box.add(Box.createVerticalStrut(5));
		pCenter.add(box, BorderLayout.NORTH);
		
		JPanel pWest = new JPanel();
		pWest.setLayout(new BorderLayout());
		pWest.setPreferredSize(new Dimension(270,700));
		
		JPanel pContaint = new JPanel();
		pContaint.setLayout(null);
		pContaint.setBorder(BorderFactory.createTitledBorder("Thông tin bệnh nhân"));
		pContaint.add(lblMaBN = new JLabel("Mã"));
		pContaint.add(txtMaBN = new JTextField());
		lblMaBN.setBounds(10, 30, 120, 23);
		txtMaBN.setBounds(75, 30, 170, 25);
		pContaint.add(lblTenBN = new JLabel("Họ và tên"));
		pContaint.add(txtTenBN = new JTextField());
		lblTenBN.setBounds(10, 70, 120, 23);
		txtTenBN.setBounds(75, 70, 170, 25);
		pContaint.add(lblNS = new JLabel("Ngày sinh"));
		pContaint.add(dchNgay = new JDateChooser());
		dchNgay.setDateFormatString("yyyy-MM-dd");
		lblNS.setBounds(10, 110, 120, 23);
		dchNgay.setBounds(75, 110, 170, 25);
		pContaint.add(lblGT = new JLabel("Giới tính"));
		pContaint.add(lblGT = new JLabel("Giới tính"));
		pContaint.add(radNam = new JRadioButton("Nam"));
		pContaint.add(radNu = new JRadioButton("Nữ"));
		lblGT.setBounds(10, 150, 120, 23);
		radNam.setBounds(75, 150, 65, 23);
		radNu.setBounds(160, 150, 50, 23);
		bgrGioiTinh.add(radNam);
		bgrGioiTinh.add(radNu);
		radNam.setSelected(true);
		
		pContaint.add(lblSDT = new JLabel("Điện thoại"));
		pContaint.add(txtSDT = new JTextField());
		lblSDT.setBounds(10, 190, 120, 23);
		txtSDT.setBounds(75, 190, 170, 25);
		
		pContaint.add(lblDC = new JLabel("Địa chỉ"));
		JScrollPane scp = new JScrollPane(txtDC = new JTextArea());
		pContaint.add(scp);
		lblDC.setBounds(10, 230, 120, 23);
		scp.setBounds(75, 230, 170, 70);
		
		pContaint.add(btnThem = new JButton("Thêm", new ImageIcon("hinhanh/add.png")));
		pContaint.add(btnSua = new JButton("  Sửa  ", new ImageIcon("hinhanh/edit.png")));
		btnThem.setBounds(25, 370, 100, 30);
		btnSua.setBounds(145, 370, 100, 30);
		pContaint.add(lblTB = new JLabel());
		lblTB.setBounds(50, 415, 150, 23);
		
		pWest.add(pContaint, BorderLayout.CENTER);
		pCenter.add(pWest, BorderLayout.WEST);
		String[] a = {"STT", "Mã", "Họ và tên", "Ngày sinh", "Giới tính", "Điện thoại", "Địa chỉ"};
		model = new DefaultTableModel(a, 0);
		tabBenhNhan = new JTable(model) {
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
		JTableHeader header = tabBenhNhan.getTableHeader();
		header.setBackground(Color.decode("#007ECA"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Calibri Light", Font.ITALIC, 16));
		header.setOpaque(false);
		
		tabBenhNhan.setRowHeight(25);
		tabBenhNhan.getColumnModel().getColumn(0).setPreferredWidth(40);
		tabBenhNhan.getColumnModel().getColumn(1).setPreferredWidth(ManHinhChinhGUI.width / 9 - 10);
		tabBenhNhan.getColumnModel().getColumn(2).setPreferredWidth(ManHinhChinhGUI.width / 9 + 100);
		tabBenhNhan.getColumnModel().getColumn(3).setPreferredWidth(ManHinhChinhGUI.width / 9 + 20);
		tabBenhNhan.getColumnModel().getColumn(4).setPreferredWidth(ManHinhChinhGUI.width / 9 - 0);
		tabBenhNhan.getColumnModel().getColumn(5).setPreferredWidth(ManHinhChinhGUI.width / 9 + 35);
		tabBenhNhan.getColumnModel().getColumn(6).setPreferredWidth(ManHinhChinhGUI.width / 9 + 150);
		tabBenhNhan.setAutoCreateRowSorter(true);					// sắp xếp
//		tabBenhNhan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	// hiện thanh trượt ngang ở dưới
		tabBenhNhan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // thiết lập chỉ cho chọn 1 hàng trong bảng
		tabBenhNhan.setAutoscrolls(true);
		JScrollPane scoll = new JScrollPane(tabBenhNhan);
		scoll.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));
		pCenter.add(scoll, BorderLayout.CENTER);
		add(pCenter);
		
		dsBenhNhan = bnControl.getBenhNhans();			// Lấy dữ liệu khách hàng trong CSDL
		docDuLieuKhachHang(dsBenhNhan);

		hiddenTextField(false);
		eventAction();
		eventClick();
		eventKey();
		
		txtMaBN.setEditable(false);
	}
	
	/**
	 * bắt sự kiện button
	 */
	public void eventAction() {
		mTaiLai.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnTim.addActionListener(this);
	}
	/**
	 * bắt sự kiện kích chuột
	 */
	public void eventClick() {
		this.addMouseListener(this);
		tabBenhNhan.addMouseListener(this);
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
	}
	/**
	 * bắt sự kiện nhấn phím
	 */
	public void eventKey() {
		txtTimTT.addKeyListener(this);
		txtTenBN.addKeyListener(this);
		txtSDT.addKeyListener(this);
	}
	
	public void hiddenTextField(boolean b) {
		txtTenBN.setEditable(b);
		((JTextField)dchNgay.getDateEditor().getUiComponent()).setEditable(b);
		txtSDT.setEditable(b);
		txtDC.setEditable(b);
		radNu.setEnabled(b);
		radNam.setEnabled(b);
	}
	
	/**
	 * Đọc dữ liệu khách hàng vào bảng
	 * @param ds
	 */
	public void docDuLieuKhachHang(List<BenhNhan> ds) {
		for(int i = 0; i < ds.size(); i++) {
			BenhNhan kh = ds.get(i);
			String[] a = {Integer.toString(i+1),kh.getMaBN(),kh.getTenBN(),kh.getNgaysinh().toString(),
							kh.getGioiTinh(),kh.getSodt(),kh.getDiaChi()};
			model.addRow(a);
		}
	}
	
	/**
	 * Đưa dữ liệu lên text field
	 */
	public void duaThongTinVaoTextField(int row) {
		tabBenhNhan.setRowSelectionInterval(row, row);
		txtMaBN.setText(tabBenhNhan.getValueAt(row, 1).toString());
		txtTenBN.setText(tabBenhNhan.getValueAt(row, 2).toString());
		try {
			dchNgay.setDate(sdf.parse((tabBenhNhan.getValueAt(row, 3).toString())));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(tabBenhNhan.getValueAt(row, 4).toString().equalsIgnoreCase("Nam"))
			radNam.setSelected(true);
		else
			radNu.setSelected(true);
		txtSDT.setText(tabBenhNhan.getValueAt(row, 5).toString());
		txtDC.setText(tabBenhNhan.getValueAt(row, 6).toString());
	}
	
	/**
	 * Xóa dữ liệu trên textfield
	 */
	public void xoaTrangTextField() {
		txtMaBN.setText("");
		txtTenBN.setText("");
		((JTextField)dchNgay.getDateEditor().getUiComponent()).setText("");
		txtSDT.setText("");
		txtDC.setText("");
	}
	
	/**
	 * kiểm tra các dữ liệu nhập vào
	 * @return
	 */
	public boolean ktNhapVao() {
		if(txtTenBN.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Họ và tên không được rỗng");
			txtTenBN.requestFocus();
			return false;
		}
		String ns = ((JTextField)dchNgay.getDateEditor().getUiComponent()).getText();
		if(ns.equals("")) {
			JOptionPane.showMessageDialog(this, "Ngày sinh không được rỗng");
			((JTextField)dchNgay.getDateEditor().getUiComponent()).requestFocus();
			return false;
		}
		else {
			if(!ns.matches("(\\d{4})-(\\d{1,2})-(\\d{1,2})")) {
				JOptionPane.showMessageDialog(this, "Ngày không hợp lệ (yyyy-MM-dd)");
				((JTextField)dchNgay.getDateEditor().getUiComponent()).selectAll();;
				((JTextField)dchNgay.getDateEditor().getUiComponent()).requestFocus();
				return false;
			}

			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(nowDay);
			c2.setTime(dchNgay.getDate());
			long time = (c1.getTime().getTime() - c2.getTime().getTime()) / (24 * 3600 * 1000);
			if(!(time > 365)) {
				JOptionPane.showMessageDialog(this, "Bệnh nhân phải trên 1 tuổi");
				((JTextField)dchNgay.getDateEditor().getUiComponent()).selectAll();
				return false;
			}
		}
		
		if(!txtSDT.getText().equals("") && !txtSDT.getText().matches("^0\\d{9}$")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu từ 0 và có độ dài là 10 số");
			txtSDT.selectAll();
			txtSDT.requestFocus();
			return false;
		}
		if(txtDC.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được rỗng");
			txtDC.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * Đưa thông tin vào đối tương
	 * @return
	 */
	public BenhNhan layTTBenhNhan() {
		if(ktNhapVao()) {
			String maBN = txtMaBN.getText();
			String tenBN = txtTenBN.getText();
			String gt = "Nam";
			LocalDate ngaySinh = dchNgay.getDate().toInstant()
				      .atZone(ZoneId.systemDefault())
				      .toLocalDate();
			String soDT = txtSDT.getText();
			String diaChi = txtDC.getText();

			if(radNu.isSelected())
				gt = "Nữ";
			
			BenhNhan kh = new BenhNhan(maBN, tenBN, gt, ngaySinh, soDT, diaChi);
			return kh;
		}
		return null;
	}
	
	/**
	 * Thêm khách hàng mới vào cơ sở dữ liệu
	 * @throws RemoteException 
	 * @throws HeadlessException 
	 */
	public void themBenhNhanMoi(BenhNhan bn) throws HeadlessException, RemoteException {
		if(bn != null) {
			if(!dsBenhNhan.contains(bn)) {
				if(bnControl.themBenhNhan(bn)) {
					String[] a = {Integer.toString(tabBenhNhan.getRowCount() + 1),bn.getMaBN(),bn.getTenBN(),bn.getNgaysinh()+"",
								bn.getGioiTinh(),bn.getSodt(),bn.getDiaChi()};					
					dsBenhNhan.add(bn);
					model.addRow(a);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					xoaTrangTextField();
					hiddenTextField(false);
					btnThem.setText("Thêm");
					btnSua.setEnabled(true);
					duaThongTinVaoTextField(tabBenhNhan.getRowCount() - 1);
					tabBenhNhan.setEnabled(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Bệnh nhân này đã có");
				txtMaBN.selectAll();
				txtMaBN.requestFocus();
				return;
			}
		}
	}
	
	/**
	 * Sửa lại thông tin bệnh nhân
	 * @throws RemoteException 
	 * @throws HeadlessException 
	 */
	public void suaThongTinBN(BenhNhan bn) throws HeadlessException, RemoteException {
		int row = tabBenhNhan.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Bạn phải chọn bệnh nhân cần sửa");
		else {
			if(bn != null && bnControl.suaThongTinBN(bn)) {				
				model.setValueAt(bn.getTenBN(), row, 2);
				model.setValueAt(bn.getNgaysinh(), row, 3);
				model.setValueAt(bn.getGioiTinh(), row, 4);
				model.setValueAt(bn.getSodt(), row, 5);
				model.setValueAt(bn.getDiaChi(), row, 6);
				JOptionPane.showMessageDialog(this, "Thông tin được sửa thành công");
				hiddenTextField(false);
				btnSua.setText("  Sửa  ");
				btnThem.setEnabled(true);
				tabBenhNhan.setEnabled(true);
			}
		}
	}
	
	/**
	 * Tìm thông tin bệnh nhân
	 * @throws RemoteException 
	 */
	public boolean timThongTinKH(String ttTim) throws RemoteException {
		List<BenhNhan> arrKH = new ArrayList<BenhNhan>();
		int truongTim = cboTim.getSelectedIndex();
		arrKH = bnControl.getBenhNhanTheoTen(ttTim);
		if(arrKH.size() != 0) {
			model.setNumRows(0);
			docDuLieuKhachHang(arrKH);
			return true;
		}
		return false;
	}
	
	/**
	 * Tải lại thông tin bệnh nhân
	 * @throws RemoteException 
	 */
	public void tailaiBenhNhan() throws RemoteException {
		model.setNumRows(0);
		dsBenhNhan = new ArrayList<>();
		dsBenhNhan = bnControl.getBenhNhans();
		docDuLieuKhachHang(dsBenhNhan);
	}
	
	/**
	 * thực hiện các chức năng
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == btnThem) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				tabBenhNhan.clearSelection();
				xoaTrangTextField();
				hiddenTextField(true);		// Hiện textfield.
				txtTenBN.requestFocus(true);

				int dem = 1;
				
				if(dsBenhNhan.size() > 0)
					dem = tabBenhNhan.getRowCount() + 1;
				
				if(dem < 10)
					txtMaBN.setText("BN000" + dem);
				else if(dem < 100)
					txtMaBN.setText("BN00" + dem);
				else if(dem < 1000)
					txtMaBN.setText("BN0" + dem);
				else
					txtMaBN.setText("BN" + dem);
					
				btnThem.setText(" Lưu ");
				btnSua.setEnabled(false);
				tabBenhNhan.setEnabled(false);
			}
			else if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
				BenhNhan bn = layTTBenhNhan();
				try {
					themBenhNhanMoi(bn);
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		else if(ob == btnSua) {
			if(tabBenhNhan.getSelectedRow() != -1) {
				if(btnSua.getText().equalsIgnoreCase("  Sửa  ")) {
					hiddenTextField(true);
					btnSua.setText(" Lưu ");
					txtTenBN.requestFocus();
					btnThem.setEnabled(false);
					tabBenhNhan.setEnabled(false);
				}
				else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
					BenhNhan bn = layTTBenhNhan();
					try {
						suaThongTinBN(bn);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Bạn phải chọn bệnh nhân cần sửa");
		}
		
		else if(ob == mTaiLai)
			try {
				tailaiBenhNhan();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if(ob == btnTim) {
			String ttTim = txtTimTT.getText();
			if(ttTim.equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin muốn tìm");
				txtTimTT.requestFocus();
			}
			else {
				try {
					if(!timThongTinKH(ttTim)) {
						JOptionPane.showMessageDialog(this, "thông tin tìm không có");
						txtTimTT.selectAll();
						txtTimTT.requestFocus();
					}
					else
						txtTimTT.setText("");
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * bắt sự kiện chuột
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tabBenhNhan.getSelectedRow();
		if(row != -1)
			duaThongTinVaoTextField(row);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object ob = e.getSource();
		
		if(ob == btnThem || ob == btnSua)
			if(btnThem.getText().equalsIgnoreCase(" Lưu ") || btnSua.getText().equalsIgnoreCase(" Lưu "))
				lblTB.setText("Nhấn ESC để hủy thao tác");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lblTB.setText("");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * bắt sự kiện nhấn chuột phải
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger())
			poMenu.show(e.getComponent(), e.getX(), e.getY());
	}
	
	/**
	 * bắt sự kiện nhấn phím
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!txtTimTT.getText().equals("")) {
				String ttTim = txtTimTT.getText();
				try {
					timThongTinKH(ttTim);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtTimTT.setText("");
			}
			else {
				if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
					BenhNhan bn = layTTBenhNhan();
					try {
						themBenhNhanMoi(bn);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
					BenhNhan bn = layTTBenhNhan();
					try {
						suaThongTinBN(bn);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
				txtTenBN.requestFocus(false);
				hiddenTextField(false);
				tabBenhNhan.setEnabled(true);
				xoaTrangTextField();
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				if(tabBenhNhan.getSelectedRow() != -1)
					duaThongTinVaoTextField(tabBenhNhan.getSelectedRow());
			}
			else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
				hiddenTextField(false);
				btnSua.setText("  Sửa  ");
				btnThem.setEnabled(true);
				duaThongTinVaoTextField(tabBenhNhan.getSelectedRow());
			}
			tabBenhNhan.setEnabled(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Object ob = e.getSource();
		
		if(ob == txtTimTT) {
			if(!txtTimTT.getText().equals("")) {
				int n = cboTim.getSelectedIndex();
				if(n == 1) {
					String ttTim = txtTimTT.getText();
					try {
						timThongTinKH(ttTim);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}							
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				if(txtTimTT.getText().equals(""))
					try {
						tailaiBenhNhan();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
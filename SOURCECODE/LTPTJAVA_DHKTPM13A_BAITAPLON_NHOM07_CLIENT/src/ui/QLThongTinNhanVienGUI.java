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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

import entities.NhanVien;
import entities.TaiKhoan;
import services.NhanVienServices;
import services.impls.NhanVienServiceImpl;

/**
 * 
 * @author Thanh Duy
 *
 * Quản lý thông tin nhân viên
 *
 */
@SuppressWarnings("serial")
public class QLThongTinNhanVienGUI extends JPanel implements ActionListener, MouseListener, KeyListener {
	JLabel lblTD, lblMaNV, lblTenNV, lblLoaiNV, lblSDT, lblDC, lblMatKhau, lblTB;
	JTextField txtMaNV, txtTenNV, txtSDT, txtTimTT, txtMatKhau;
	JTextArea txtDC;
	JComboBox<String> cboTim, cboLoaiNV;
	JButton btnThem, btnSua, btnTim;
	DefaultTableModel model;
	JTable tabNV;
	
	JPopupMenu poMenu = new JPopupMenu();
	JMenuItem mTaiLai = new JMenuItem("Tải lại", new ImageIcon("hinhanh/capnhat.png"));
	
	NhanVienServices nvControl;
	
	
	List<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	public QLThongTinNhanVienGUI(NhanVienServices nvControl) throws RemoteException {
		this.nvControl = nvControl;
		poMenu.add(mTaiLai);
		poMenu.setPreferredSize(new Dimension(100, 30));
		
		setLayout(new BorderLayout());
		
		// ------------------ Nội dung ------------------ //
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		pNorth.add(lblTD = new JLabel("QUẢN LÝ THÔNG TIN NHÂN VIÊN"));
		lblTD.setFont(new Font("Calibri Light", Font.BOLD, 25));
		add(pNorth, BorderLayout.NORTH);

		// ------------ Ở giữa ------------ //
		JPanel pCenter = new JPanel();
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
		bTim.add(Box.createHorizontalStrut(457));
		txtTimTT.setPreferredSize(new Dimension(200, 25));
		btnTim.setPreferredSize(new Dimension(35, 25));
		box.add(Box.createVerticalStrut(5));
		pCenter.add(box, BorderLayout.NORTH);
		
		JPanel pWest = new JPanel();
		pWest.setLayout(new BorderLayout());
		pWest.setPreferredSize(new Dimension(270,700));
		
		JPanel pContain = new JPanel();
		pContain.setLayout(null);
		pContain.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));
		pContain.add(lblMaNV = new JLabel("Mã"));
		pContain.add(txtMaNV = new JTextField());
		lblMaNV.setBounds(10, 20, 120, 23);
		txtMaNV.setBounds(75, 20, 170, 25);
		pContain.add(lblTenNV = new JLabel("Họ và tên"));
		pContain.add(txtTenNV = new JTextField());
		lblTenNV.setBounds(10, 55, 120, 23);
		txtTenNV.setBounds(75, 55, 170, 25);
		
		pContain.add(lblLoaiNV = new JLabel("Loại tài khoản"));
		
		
		pContain.add(cboLoaiNV = new JComboBox<>());
		cboLoaiNV.addItem("Nhân viên nhân bệnh");
		cboLoaiNV.addItem("Nhân viên phát thuốc");
		cboLoaiNV.addItem("Bác sĩ");
		cboLoaiNV.setSelectedIndex(1);
		lblLoaiNV.setBounds(10, 92, 120, 23);
		cboLoaiNV.setBounds(75, 92, 170, 25);
		
		pContain.add(lblSDT = new JLabel("Điện thoại"));
		pContain.add(txtSDT = new JTextField());
		lblSDT.setBounds(10, 128, 120, 23);
		txtSDT.setBounds(75, 128, 170, 23);
		
		pContain.add(lblMatKhau = new JLabel("Mật khẩu"));
		pContain.add(txtMatKhau = new JTextField());
		lblMatKhau.setBounds(10, 162, 120, 23);
		txtMatKhau.setBounds(75, 162, 170, 25);
	
		pContain.add(lblDC = new JLabel("Địa chỉ"));
		JScrollPane scp = new JScrollPane(txtDC = new JTextArea());
		pContain.add(scp);
		lblDC.setBounds(10, 238, 120, 23);
		scp.setBounds(75, 238, 170, 50);
		
		pContain.add(btnThem = new JButton("Thêm", new ImageIcon("hinhanh/add.png")));
		pContain.add(btnSua = new JButton("  Sửa  ", new ImageIcon("hinhanh/edit.png")));
		btnThem.setBounds(25, 345, 100, 30);
		btnSua.setBounds(145, 345, 100, 30);
		pContain.add(lblTB = new JLabel());
		lblTB.setBounds(25, 380, 150, 23);
		pWest.add(pContain, BorderLayout.CENTER);
		pCenter.add(pWest, BorderLayout.WEST);
		
		String[] a = {"STT", "Mã", "Họ và tên", "Loại nhân viên", "Điện thoại", "Địa chỉ", "Mật khẩu"};
		model = new DefaultTableModel(a, 0);
		tabNV = new JTable(model) {
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
		JTableHeader header = tabNV.getTableHeader();
		header.setBackground(Color.decode("#007ECA"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Calibri Light", Font.ITALIC, 16));
		header.setOpaque(false);
		
		tabNV.setRowHeight(25);
		tabNV.getColumnModel().getColumn(0).setPreferredWidth(35);
		tabNV.getColumnModel().getColumn(1).setPreferredWidth(ManHinhChinhGUI.width / 9 - 30);
		tabNV.getColumnModel().getColumn(2).setPreferredWidth(ManHinhChinhGUI.width / 9 + 50);
		tabNV.getColumnModel().getColumn(3).setPreferredWidth(ManHinhChinhGUI.width / 9 + 30);
		tabNV.getColumnModel().getColumn(4).setPreferredWidth(ManHinhChinhGUI.width / 9 + 0);
		tabNV.getColumnModel().getColumn(5).setPreferredWidth(ManHinhChinhGUI.width / 9 + 100);
		tabNV.getColumnModel().getColumn(6).setPreferredWidth(ManHinhChinhGUI.width / 9 + 70);
		tabNV.setAutoCreateRowSorter(true);					// sắp xếp
		tabNV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);	// hiện thanh trượt ngang ở dưới
		tabNV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // thiết lập chỉ cho chọn 1 hàng trong bảng
		JScrollPane scoll = new JScrollPane(tabNV);
		scoll.setBorder(BorderFactory.createTitledBorder("Danh sách nhân viên"));
		pCenter.add(scoll, BorderLayout.CENTER);

		add(pCenter);		
		
		try {
			dsNhanVien = nvControl.getNhanViens();			// Lấy dữ liệu nhân viên trong CSDL
			docDuLieuNhanVien(dsNhanVien);
			
		} catch (Exception e) {
			System.out.println("error");
		}
	
		
		hiddenTextField(false);
		eventAction();
		eventClick();
		eventKey();
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
		tabNV.addMouseListener(this);
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
	}
	/**
	 * bắt sự kiện nhấn phím
	 */
	public void eventKey() {
		txtTimTT.addKeyListener(this);
		txtMatKhau.addKeyListener(this);
		txtTenNV.addKeyListener(this);
		txtSDT.addKeyListener(this);
		txtDC.addKeyListener(this);
	}
	
	public void hiddenTextField(boolean b) {
		txtMaNV.setEditable(b);
		txtTenNV.setEditable(b);
		txtSDT.setEditable(b);
		txtDC.setEditable(b);
		txtMatKhau.setEditable(b);
	}
	
	/**
	 * Đọc dữ liệu nhân viên vào bảng
	 * @param ds
	 */
	public void docDuLieuNhanVien(List<NhanVien> ds) {
		for(int i = 0; i < ds.size(); i++) {
			NhanVien nv = ds.get(i);
			
			String loainv = "";
			if(nv.getLoaiNV() == 1) {
				loainv = "Nhân viên nhân bệnh";
			}else if(nv.getLoaiNV() == 2) {
				loainv = "Nhân viên phát thuốc";
			}else if(nv.getLoaiNV() == 3) {
				loainv = "Bác sĩ";
			}
			
			String[] a = {Integer.toString(i+1),nv.getMaNV(),nv.getTenNV(),loainv,
							nv.getSodt(),nv.getDiaChi(),nv.getTaikhoan().getMatkhau()};
			model.addRow(a);
		}
	}
	
	/**
	 * Đưa dữ liệu lên text field
	 */
	public void duaThongTinVaoTextField(int row) {
		tabNV.setRowSelectionInterval(row, row);
		txtMaNV.setText(tabNV.getValueAt(row, 1).toString());
		txtTenNV.setText(tabNV.getValueAt(row, 2).toString());
		
		cboLoaiNV.setSelectedItem(tabNV.getValueAt(row, 3).toString());
		
		txtSDT.setText(tabNV.getValueAt(row, 4).toString());
		txtDC.setText(tabNV.getValueAt(row, 5).toString());
		txtMatKhau.setText(tabNV.getValueAt(row, 6).toString());
	}
	
	/**
	 * Xóa dữ liệu trên textfield
	 */
	public void xoaTrangTextField() {
		txtMaNV.setText("");
		txtTenNV.setText("");
		txtSDT.setText("");
		txtDC.setText("");
		txtMatKhau.setText("");
	}
	
	/**
	 * kiểm tra các dữ liệu nhập vào
	 * @return
	 */
	public boolean ktNhapVao() {
		if(txtTenNV.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Họ và tên không được rỗng");
			txtTenNV.requestFocus();
			return false;
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
		
		if(!txtMatKhau.getText().equals("") && !txtMatKhau.getText().matches("(?=.*\\d)(?=.*[a-z]).{6,20}")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cần tối thiểu 6 và tối đa 20 ký tự cả chữ lẫn số");
			txtMatKhau.selectAll();
			txtMatKhau.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * Đưa thông tin vào đối tương
	 * @return
	 */
	public NhanVien layTTNhanVien() {
		if(ktNhapVao()) {
			String maNV = txtMaNV.getText();
			String tenNV = txtTenNV.getText();
			int loaiNV = cboLoaiNV.getSelectedIndex();
			String sodt = txtSDT.getText();
			String diaChi = txtDC.getText();
			String matKhau = txtMatKhau.getText();			
			
//			List<NhanVien> nv = nvControl.timTaiKhoan(maNV, matKhau);
			TaiKhoan taikhoan;
//			if (nv == null) {
				taikhoan = new TaiKhoan(maNV, matKhau, loaiNV+1);
				NhanVien nv1 = new NhanVien(maNV, tenNV, loaiNV+1, sodt, diaChi, taikhoan);
				return nv1;
//			}		
		}
		return null;
	}
	
	/**
	 * Thêm nhân viên mới vào cơ sở dữ liệu
	 * @throws RemoteException 
	 * @throws HeadlessException 
	 * */
	public void themNhanVienMoi(NhanVien nv) throws HeadlessException, RemoteException {
		System.out.println("ko vao");
		if(nv != null) {
			System.out.println("dis");
			if(!dsNhanVien.contains(nv)) {
				if(nvControl.themNhanVien(nv)) {
					String loainv = "";
					if(nv.getLoaiNV() == 1) {
						loainv = "Nhân viên nhân bệnh";
					}else if(nv.getLoaiNV() == 2) {
						loainv = "Nhân viên phát thuốc";
					}else if(nv.getLoaiNV() == 3) {
						loainv = "Bác sĩ";
					}
					
					String[] a = {Integer.toString(tabNV.getRowCount() + 1),nv.getMaNV(),
								  nv.getTenNV(),loainv,
								  nv.getSodt(),nv.getDiaChi(),nv.getTaikhoan().getMatkhau()};					
					dsNhanVien.add(nv);
					model.addRow(a);
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					xoaTrangTextField();
					hiddenTextField(false);
					btnThem.setText("Thêm");
					btnSua.setEnabled(true);
					duaThongTinVaoTextField(tabNV.getRowCount() - 1);
					tabNV.setEnabled(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Mã nhân viên đã có");
				txtMaNV.selectAll();
				txtMaNV.requestFocus();
				return;
			}
		}
	}
	
	/**
	 * Sửa lại thông tin nhân viên
	 * @throws RemoteException 
	 * @throws HeadlessException 
	 */
	public void suaThongTinNV(NhanVien nv) throws HeadlessException, RemoteException {
		int row = tabNV.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Bạn phải chọn nhân viên cần sửa");
		else {
			if(nv!= null && nvControl.suaThongTinNV(nv)) {
				String loainv = "";
				if(nv.getLoaiNV() == 1) {
					loainv = "Nhân viên nhân bệnh";
				}else if(nv.getLoaiNV() == 2) {
					loainv = "Nhân viên phát thuốc";
				}else if(nv.getLoaiNV() == 3) {
					loainv = "Bác sĩ";
				}
				model.setValueAt(nv.getTenNV(), row, 2);
				model.setValueAt(loainv, row, 3);
				model.setValueAt(nv.getSodt(), row, 4);
				model.setValueAt(nv.getDiaChi(), row, 5);
				model.setValueAt(nv.getTaikhoan().getMatkhau(), row, 6);
				JOptionPane.showMessageDialog(this, "Thông tin được sửa thành công");
				hiddenTextField(false);
				btnSua.setText("  Sửa  ");
				btnThem.setEnabled(true);
				tabNV.setEnabled(true);
			}
		}
	}
	
	/**
	 * Tìm thông tin nhân viên
	 * @throws RemoteException 
	 */
	public boolean timThongTinNV(String ttTim) throws RemoteException {		
		List<NhanVien> arrNV = new ArrayList<NhanVien>();
		int truongTim = cboTim.getSelectedIndex();
		arrNV = nvControl.getNhanVienTheoTen(ttTim);
		if(arrNV.size() != 0) {
			model.setNumRows(0);
			docDuLieuNhanVien(arrNV);
			return true;
		}
		return false;
	}
	
	/**
	 * Tải lại thông tin nhân viên
	 * @throws RemoteException 
	 */
	public void tailaiNhanVien() throws RemoteException {
		model.setNumRows(0);
		dsNhanVien = new ArrayList<>();
		dsNhanVien = nvControl.getNhanViens();
		docDuLieuNhanVien(dsNhanVien);
	}
	
	/**
	 * thực hiện các chức năng
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == btnThem) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				tabNV.clearSelection();
				xoaTrangTextField();
				hiddenTextField(true);		// Hiện textfield.
				txtTenNV.requestFocus(true);
				txtMaNV.setEditable(false);
				
				int dem = 1;
				
				if(dsNhanVien.size() > 0)
					dem = tabNV.getRowCount() + 1;
				
				if(dem < 10)
					txtMaNV.setText("NV000" + dem);
				else if(dem < 100)
					txtMaNV.setText("NV00" + dem);
				else if(dem < 1000)
					txtMaNV.setText("NV0" + dem);
				else
					txtMaNV.setText("NV" + dem);
					
				btnThem.setText(" Lưu ");
				btnSua.setEnabled(false);
				tabNV.setEnabled(false);
			}
			else if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
				NhanVien nv = layTTNhanVien();
				try {
					themNhanVienMoi(nv);
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		else if(ob == btnSua) {
			if(tabNV.getSelectedRow() != -1) {
				if(btnSua.getText().equalsIgnoreCase("  Sửa  ")) {
					hiddenTextField(true);
					txtMaNV.setEditable(false);
					btnSua.setText(" Lưu ");
					btnThem.setEnabled(false);
					txtTenNV.requestFocus();
					tabNV.setEnabled(false);
				}
				else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
					NhanVien nv = layTTNhanVien();
					try {
						suaThongTinNV(nv);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Bạn phải chọn nhân viên cần sửa");
		}
		
		else if(ob == mTaiLai)
			try {
				tailaiNhanVien();
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
					if(!timThongTinNV(ttTim)) {
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
		int row = tabNV.getSelectedRow();
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
					if(!timThongTinNV(ttTim)) {
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
			else {
				if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
					NhanVien nv = layTTNhanVien();
					try {
						themNhanVienMoi(nv);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
					NhanVien nv = layTTNhanVien();
					try {
						suaThongTinNV(nv);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
				txtTenNV.requestFocus(false);
				hiddenTextField(false);
				tabNV.setEnabled(true);
				xoaTrangTextField();
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				if(tabNV.getSelectedRow() != -1)
					duaThongTinVaoTextField(tabNV.getSelectedRow());
			}
			else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
				hiddenTextField(false);
				btnSua.setText("  Sửa  ");
				btnThem.setEnabled(true);
				duaThongTinVaoTextField(tabNV.getSelectedRow());
			}
			tabNV.setEnabled(true);
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
						timThongTinNV(ttTim);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}							
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				if(txtTimTT.getText().equals(""))
					try {
						tailaiNhanVien();
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
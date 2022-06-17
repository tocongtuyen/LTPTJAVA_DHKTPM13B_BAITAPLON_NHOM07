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

import entities.Thuoc;
import services.ThuocServices;
import services.impls.ThuocServiceImpl;

/**
 * 
 * @author Thanh Duy
 * 
 * 	Quản lý thông tin thuốc
 *
 */
@SuppressWarnings("serial")
public class QLThongTinThuocGUI extends JPanel implements ActionListener, MouseListener, KeyListener {

	JLabel lblTD, lblMaThuoc, lblTenThuoc, lblNhomThuoc, lblTPThuoc, lblTB;
	JTextField txtMaThuoc, txtTenThuoc, txtTimTT;
	JTextArea txtTPThuoc;
	JComboBox<String> cboNhomThuoc,cboTim;
	JButton btnThem, btnSua, btnTroVe, btnXoa, btnTim;
	DefaultTableModel model;
	JTable tabThuoc;
	
	JPopupMenu poMenu = new JPopupMenu();
	JMenuItem  mTaiLai = new JMenuItem("Tải lại", new ImageIcon("hinhanh/capnhat.png"));
	
	ThuocServices thuocControl;
	
	List<Thuoc> listThuoc = new ArrayList<>();

	public QLThongTinThuocGUI() throws RemoteException {
		thuocControl = new ThuocServiceImpl();
		poMenu.add(mTaiLai);
		poMenu.setPreferredSize(new Dimension(100, 30));
		
		setLayout(new BorderLayout());

		// ------------------ Ở trên ------------------ //
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		pNorth.add(lblTD = new JLabel("QUẢN LÝ THÔNG TIN THUỐC"));
		lblTD.setFont(new Font("Calibri Light", Font.BOLD, 25));
		add(pNorth, BorderLayout.NORTH);

		// ------------ Ở giữa ------------ //
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		Box box = Box.createVerticalBox();
		box.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		Box bTim;
		box.add(bTim = Box.createHorizontalBox());
		bTim.add(Box.createHorizontalStrut(115));
		bTim.add(txtTimTT = new JTextField());
		bTim.add(Box.createHorizontalStrut(15));
		bTim.add(cboTim = new JComboBox<>());
		cboTim.addItem("Mã thuốc");
		cboTim.addItem("Tên thuốc");
		cboTim.setSelectedIndex(1);
		cboTim.setPreferredSize(new Dimension(120,25));
		bTim.add(Box.createHorizontalStrut(15));
		bTim.add(btnTim = new JButton(new ImageIcon("hinhanh/search.png")));
		bTim.add(Box.createHorizontalStrut(380));
		txtTimTT.setPreferredSize(new Dimension(200, 25));
		btnTim.setPreferredSize(new Dimension(35, 25));
		box.add(Box.createVerticalStrut(5));
		pCenter.add(box, BorderLayout.NORTH);
		
		JPanel pWest = new JPanel();
		pWest.setLayout(new BorderLayout());
		pWest.setPreferredSize(new Dimension(330,700));
		
		JPanel pContaint = new JPanel();
		pContaint.setLayout(null);
		pContaint.setBorder(BorderFactory.createTitledBorder("Thông tin thuốc"));
		pContaint.add(lblMaThuoc = new JLabel("Mã thuốc:"));
		pContaint.add(txtMaThuoc = new JTextField());
		lblMaThuoc.setBounds(10, 40, 120, 23);
		txtMaThuoc.setBounds(120, 40, 200, 25);
		pContaint.add(lblTenThuoc = new JLabel("Tên thuốc:"));
		pContaint.add(txtTenThuoc = new JTextField());
		lblTenThuoc.setBounds(10, 90, 120, 23);
		txtTenThuoc.setBounds(120, 90, 200, 25);
		pContaint.add(lblNhomThuoc = new JLabel("Nhóm thuốc:"));
		pContaint.add(cboNhomThuoc = new JComboBox<String>());
		lblNhomThuoc.setBounds(10, 140, 120, 23);
		cboNhomThuoc.setBounds(120, 140, 200, 25);
		pContaint.add(lblTPThuoc = new JLabel("Thành phần thuốc:  "));
		JScrollPane scp = new JScrollPane(txtTPThuoc = new JTextArea());
		pContaint.add(scp);
		lblTPThuoc.setBounds(10, 190, 120, 23);
		scp.setBounds(120, 190, 200, 80);
		txtTPThuoc.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		pContaint.add(btnThem = new JButton("Thêm", new ImageIcon("hinhanh/add.png")));
		pContaint.add(btnSua = new JButton("  Sửa  ", new ImageIcon("hinhanh/edit.png")));
		btnThem.setBounds(50, 350, 100, 30);
		btnSua.setBounds(180, 350, 100, 30);
		pContaint.add(lblTB = new JLabel());
		lblTB.setBounds(50, 410, 150, 23);
		
		pWest.add(pContaint, BorderLayout.CENTER);
		pCenter.add(pWest, BorderLayout.WEST);
		
		String[] a = {"STT", "Mã thuốc", "Tên thuốc", "Nhóm thuốc", "Thành phần thuốc"};
		model = new DefaultTableModel(a, 0); 
		tabThuoc = new JTable(model) {
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
		JTableHeader header = tabThuoc.getTableHeader();
		header.setBackground(Color.decode("#007ECA"));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Calibri Light", Font.ITALIC, 16));
		header.setOpaque(false);
		
		tabThuoc.setRowHeight(25);
		tabThuoc.getColumnModel().getColumn(0).setPreferredWidth(50);
		tabThuoc.getColumnModel().getColumn(1).setPreferredWidth(ManHinhChinhGUI.width / 9 + 40);
		tabThuoc.getColumnModel().getColumn(2).setPreferredWidth(ManHinhChinhGUI.width / 9 + 120);
		tabThuoc.getColumnModel().getColumn(3).setPreferredWidth(ManHinhChinhGUI.width / 9 + 120);
		tabThuoc.getColumnModel().getColumn(4).setPreferredWidth(ManHinhChinhGUI.width / 9 + 180);
		tabThuoc.setAutoCreateRowSorter(true);			// sắp xếp
//		tabThuoc.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabThuoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // thiết lập chỉ cho chọn 1 hàng trong bảng
		JScrollPane scoll = new JScrollPane(tabThuoc);
		scoll.setBorder(BorderFactory.createTitledBorder("Danh sách thuốc"));
		pCenter.add(scoll, BorderLayout.CENTER);
		add(pCenter);

		comboBoxThemTenNhomThuoc();					// đưa tên nhóm thuốc vào comboBox
		listThuoc = thuocControl.getThuocs();
		
		docDuLieuThuoc(listThuoc);
		
		
		hiddenTextField(false);
		txtMaThuoc.setEditable(false);
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
		tabThuoc.addMouseListener(this);
		btnThem.addMouseListener(this);
		btnSua.addMouseListener(this);
	}
	/**
	 * bắt sự kiện nhấn phím
	 */
	public void eventKey() {
		txtTimTT.addKeyListener(this);
		txtTenThuoc.addKeyListener(this);
	}
	
	public void hiddenTextField(boolean b) {
		txtTenThuoc.setEditable(b);
		txtTPThuoc.setEditable(b);
	}
	
	/**
	 * Đưa tên nhóm thuốc vào comboBox
	 */
	public void comboBoxThemTenNhomThuoc() {
		cboNhomThuoc.addItem("Thuốc giảm đau loại opi");
		cboNhomThuoc.addItem("Trị cảm, cúm");
		cboNhomThuoc.addItem("Dị ứng");
		cboNhomThuoc.addItem("Vitamin");
		cboNhomThuoc.addItem("Thuốc gây tê, mê");
		cboNhomThuoc.addItem("Thuốc chống dị ứng");
		cboNhomThuoc.addItem("Thuốc giải độc");
	}
	
	/**
	 * Đọc dữ liệu thuốc vào bảng
	 * @param ds
	 */
	public void docDuLieuThuoc(List<Thuoc> ds) {
		for(int i = 0; i < ds.size(); i++) {
			Thuoc t = ds.get(i);
			String[] a = {Integer.toString(tabThuoc.getRowCount() + 1), t.getMaThuoc(),
						  t.getTenThuoc(), t.getNhomThuoc(), t.getThanhPhan()};
			model.addRow(a);
		}
	}

	/**
	 * Đưa dữ liệu lên text field
	 */
	public void duaThongTinVaoTextField(int row) {
		tabThuoc.setRowSelectionInterval(row, row);
		txtMaThuoc.setText(tabThuoc.getValueAt(row, 1).toString());
		txtTenThuoc.setText(tabThuoc.getValueAt(row, 2).toString());
		cboNhomThuoc.setSelectedItem(tabThuoc.getValueAt(row, 3).toString());
		txtTPThuoc.setText(tabThuoc.getValueAt(row, 4).toString());
	}
	
	/**
	 * Xóa dữ liệu trên textfield
	 */
	public void xoaTrangTextField() {
		txtMaThuoc.setText("");
		txtTenThuoc.setText("");
		txtTPThuoc.setText("");
	}
	
	/**
	 * kiểm tra các dữ liệu nhập vào
	 * @return
	 */
	public boolean ktNhapVao() {
		if(txtTenThuoc.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên thuốc không được rỗng");
			txtTenThuoc.requestFocus();
			return false;
		}
		return true;
	}
	
	/**
	 * Đưa thông tin vào đối tương
	 * @return
	 */
	public Thuoc layThongTinThuoc() {
		if(ktNhapVao()) {
			String maThuoc = txtMaThuoc.getText();
			String tenThuoc = txtTenThuoc.getText();
			String nhomThuoc = cboNhomThuoc.getSelectedItem().toString();
			String thanhPhanThuoc = txtTPThuoc.getText();
			
			Thuoc t = new Thuoc(maThuoc, tenThuoc, nhomThuoc, thanhPhanThuoc);
			return t;
		}
		return null;
	}
	
	/**
	 * Thêm thuốc mới vào cơ sở dữ liệu
	 * @throws RemoteException 
	 * @throws HeadlessException 
	 * */
	public void themThuocMoi(Thuoc t) throws HeadlessException, RemoteException {
		if(t != null) {
			if(!listThuoc.contains(t)) {
				if(thuocControl.themThuoc(t)) {
					String[] a = {Integer.toString(tabThuoc.getRowCount() + 1), t.getMaThuoc(),
							  	  t.getTenThuoc(), t.getNhomThuoc(), t.getThanhPhan()};
					JOptionPane.showMessageDialog(this, "Thêm thành công");
					
					listThuoc.add(t);
					model.addRow(a);
					xoaTrangTextField();
					hiddenTextField(false);
					btnThem.setText("Thêm");
					btnSua.setEnabled(true);
					duaThongTinVaoTextField(tabThuoc.getRowCount() - 1);
					tabThuoc.setEnabled(true);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Thuốc này đã có");
				txtTenThuoc.selectAll();
				txtTenThuoc.requestFocus();
				return;
			}
		}
	}
	
	/**
	 * Sửa lại thông tin thuốc
	 * @throws RemoteException 
	 * @throws HeadlessException 
	 */
	public void suaThongTinThuoc(Thuoc t) throws HeadlessException, RemoteException {
		int row = tabThuoc.getSelectedRow();
		if(t != null && thuocControl.suaThongTinT(t)) {
			JOptionPane.showMessageDialog(this, "Thông tin được sửa thành công");
			model.setValueAt(t.getTenThuoc(), row, 2);
			model.setValueAt(t.getNhomThuoc(), row, 3);
			model.setValueAt(t.getThanhPhan(), row, 4);
			hiddenTextField(false);
			btnSua.setText("  Sửa  ");
			btnThem.setEnabled(true);
			tabThuoc.setEnabled(true);
		}
	}
	
	/**
	 * Tìm thông tin thuốc
	 * @throws RemoteException 
	 */
	public boolean timThongTinThuoc(String ttTim) throws RemoteException {
		List<Thuoc> arrThuoc = new ArrayList<Thuoc>();
		int truongTim = cboTim.getSelectedIndex();
		arrThuoc = thuocControl.getThuocTheoTen(ttTim);
		if(arrThuoc.size() != 0) {
			model.setNumRows(0);
			docDuLieuThuoc(arrThuoc);
			return true;
		}
		return false;
	}
	
	/**
	 * Tải lại dữ liệu
	 * @throws RemoteException 
	 */
	public void tailaiThuoc() throws RemoteException {
		model.setNumRows(0);
		listThuoc = new ArrayList<>();
		listThuoc = thuocControl.getThuocs();
		docDuLieuThuoc(listThuoc);
	}
	
	/**
	 * thực hiện các chức năng
	 */	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == btnThem) {
			if(btnThem.getText().equalsIgnoreCase("Thêm")) {
				tabThuoc.clearSelection();
				xoaTrangTextField();
				hiddenTextField(true);		// Hiện textfield.
				txtTenThuoc.requestFocus(true);
				int dem = 100001;
				
				if (listThuoc.size() > 0)
					dem = Integer.parseInt(tabThuoc.getValueAt(tabThuoc.getRowCount() - 1, 1).toString()) + 1;
				txtMaThuoc.setText(Integer.toString(dem));
					
				btnThem.setText(" Lưu ");
				btnSua.setEnabled(false);
				tabThuoc.setEnabled(false);
			}
			else if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
				Thuoc t = layThongTinThuoc();
				try {
					themThuocMoi(t);
				} catch (HeadlessException | RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		else if(ob == btnSua) {
			if(tabThuoc.getSelectedRow() != -1) {
				if(btnSua.getText().equalsIgnoreCase("  Sửa  ")) {
					hiddenTextField(true);
					btnSua.setText(" Lưu ");
					btnThem.setEnabled(false);
					txtTenThuoc.requestFocus();
					tabThuoc.setEnabled(false);
				}
				else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
					Thuoc t = layThongTinThuoc();
					try {
						suaThongTinThuoc(t);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Bạn phải chọn thuốc cần sửa");
		}
		
		else if(ob == btnTim) {
			String ttTim = txtTimTT.getText();
			if(ttTim.equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn chưa nhập thông tin muốn tìm");
				txtTimTT.requestFocus();
			}
			else {
				try {
					if(!timThongTinThuoc(ttTim)) {
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
		
		else if(ob == mTaiLai)
			try {
				tailaiThuoc();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	/**
	 * bắt sự kiện chuột
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tabThuoc.getSelectedRow();
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

	}

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
					if(!timThongTinThuoc(ttTim)) {
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
					Thuoc t = layThongTinThuoc();
					try {
						themThuocMoi(t);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
					Thuoc t = layThongTinThuoc();
					try {
						suaThongTinThuoc(t);
					} catch (HeadlessException | RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if(btnThem.getText().equalsIgnoreCase(" Lưu ")) {
				txtTenThuoc.requestFocus(false);
				hiddenTextField(false);
				tabThuoc.setEnabled(true);
				xoaTrangTextField();
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				if(tabThuoc.getSelectedRow() != -1)
					duaThongTinVaoTextField(tabThuoc.getSelectedRow());
			}
			else if(btnSua.getText().equalsIgnoreCase(" Lưu ")) {
				hiddenTextField(false);
				btnSua.setText("  Sửa  ");
				btnThem.setEnabled(true);
				duaThongTinVaoTextField(tabThuoc.getSelectedRow());
			}
			tabThuoc.setEnabled(true);
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
						timThongTinThuoc(ttTim);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}							
			}
			if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
				if(txtTimTT.getText().equals(""))
					try {
						tailaiThuoc();
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}

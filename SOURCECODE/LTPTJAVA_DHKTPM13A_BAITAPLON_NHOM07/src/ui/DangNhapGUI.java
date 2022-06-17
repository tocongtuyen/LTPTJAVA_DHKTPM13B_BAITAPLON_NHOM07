package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entities.NhanVien;
import services.NhanVienServices;
import services.impls.NhanVienServiceImpl;


/**
 * @author Thanh Duy
 * 
 * Đăng nhập vào hệ thống
 *
 */
@SuppressWarnings("serial")
public class DangNhapGUI extends JFrame implements ActionListener, KeyListener {
	
	JLabel lblUse, lblPass, lblHead;
	JTextField txtUse;
	JPasswordField txtPass;
	JButton btnDN, btnExit;
	
	NhanVienServices tkCon; 
	
	public DangNhapGUI() throws RemoteException{
		tkCon = new NhanVienServiceImpl() ;
		setTitle("Đăng nhập");
		setType(Type.POPUP);
		setSize(400, 280);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JPanel pNorth = new JPanel();
		pNorth.add(lblHead = new JLabel("ĐĂNG NHẬP"));
		lblHead.setFont(new Font("Calibri Light", Font.BOLD, 25));
		pNorth.setBackground(Color.CYAN);
		add(pNorth, BorderLayout.NORTH);
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		pCenter.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		JPanel pContaint = new JPanel();
		pContaint.setLayout(null);
		pContaint.add(lblUse = new JLabel("Tài khoản:"));
		pContaint.add(txtUse = new JTextField());
		pContaint.add(lblPass = new JLabel("Mật khẩu:"));
		pContaint.add(txtPass = new JPasswordField());
		lblUse.setBounds(35, 50, 100, 23);
		lblPass.setBounds(35, 100, 100, 23);
		txtUse.setBounds(120, 50, 220, 25);
		txtPass.setBounds(120, 100, 220, 25);
		pCenter.add(pContaint, BorderLayout.CENTER);

		add(pCenter);
		
		JPanel pSouth = new JPanel();
//		pSouth.setBackground(Color.CYAN);
		Box b3 = Box.createHorizontalBox();
		b3.add(btnDN = new JButton("Đăng nhập"));
		b3.add(Box.createHorizontalStrut(20));
		b3.add(btnExit = new JButton("    Thoát    "));
		btnExit.setPreferredSize(btnDN.getPreferredSize());
		pSouth.add(b3);
		add(pSouth, BorderLayout.SOUTH);
		
		event();
	}
	
	public void event() {
		// --------- button event --------- //
		btnDN.addActionListener(this);
		btnExit.addActionListener(this);
		// -------- keyboard event -------- //
		txtPass.addKeyListener(this);
		txtUse.addKeyListener(this);
	}
	
	@SuppressWarnings("deprecation")
	public boolean ktNhapVao() {
		
		String tk = txtUse.getText();
		String mk = txtPass.getText();
		
		if(tk.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên đăng nhập không được rỗng");
			txtUse.requestFocus();
			return false;
		}
		else if(!tk.matches("^[A-Z]{2}\\w{4}")) {
			JOptionPane.showMessageDialog(this, "Tài khoản phải bắt đầu bằng 2 chữ hoa và phải đủ 6 ký tự");
			txtUse.selectAll();
			txtUse.requestFocus();
			return false;
		}
		
		if(mk.equals("")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được rỗng");
			txtPass.requestFocus();
			return false;
		}
		else if(!mk.matches("(?=.*\\d)(?=.*[a-z]).{6,20}")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cần tối thiểu 6 và tối đa 20 ký tự cả chữ lẫn số");
			txtPass.selectAll();
			txtPass.requestFocus();
			return false;
		}
		return true;
	}
	public boolean ktTaiKhoanDN(String tk, String mk) throws RemoteException {
		NhanVien nhanVien = tkCon.timTaiKhoan(tk, mk).get(0);
		
		String username = nhanVien.getTaikhoan().getMaDN(), password = nhanVien.getTaikhoan().getMatkhau();
		
		if(tk.equals(username) && mk.equals(password)) {
			ManHinhChinhGUI.maDN = username;
			ManHinhChinhGUI.user = 1;
			int loaiTK = nhanVien.getTaikhoan().getLoaiTK();
			if(loaiTK == 2) {
				ManHinhChinhGUI.tenDN = "Admin";				
				new ManHinhChinhGUI().setVisible(true);
			}
			else {
				if(loaiTK == 0) {
					ManHinhChinhGUI.tenDN = tkCon.timTaiKhoan(tk, mk).get(0).getTenNV();				
				}
				else if(loaiTK == 1) {
					ManHinhChinhGUI.tenDN = "BS. " + tkCon.timTaiKhoan(tk, mk).get(0).getTenNV();
//					new KeToaThuocGUI().setVisible(true);
				}
			}
			return true;
		}
		return false;
	}
	
	public void ktDangNhap(String tk, String mk) throws HeadlessException, RemoteException {
		if(ktNhapVao()) {
			if(ktTaiKhoanDN(tk, mk)) {
				new ManHinhChinhGUI().setVisible(true);
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(this, "Đăng nhập thất bại");
				txtUse.selectAll();
				txtUse.requestFocus();
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		if(ob == btnDN)
			try {
				ktDangNhap(txtUse.getText(), txtPass.getText());
			} catch (HeadlessException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if(ob == btnExit)
			System.exit(0);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			try {
				ktDangNhap(txtUse.getText(), txtPass.getText());
			} catch (HeadlessException | RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			this.dispose();
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String[] args) {
//		new DangNhapGUI().setVisible(true);
//
//	}
}
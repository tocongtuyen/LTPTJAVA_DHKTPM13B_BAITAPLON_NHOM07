package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

// import com.toedter.calendar.JDateChooser;


@SuppressWarnings("serial")
public class KhamBenh_GUI extends JFrame {

	JLabel lblTD, lblMaToa, lblNgayKe, lblMaBN, lblTenBN ;
	JTextField txtMaToa, txtMaBN, txtTenBN, txtTenThuoc, txtNhomThuoc, txtSL, txtDonVT, dcNgayKe;
//	JDateChooser dcNgayKe;
	JButton btnGoiKham;
	
	
	public static DefaultListModel<String> model = new DefaultListModel<>();
	JList<String> listChoKham;
	
	public KhamBenh_GUI() {
		setTitle("Phần mềm quản lý quầy thuốc");
		setType(Type.POPUP);
		//		setIconImage(Toolkit.getDefaultToolkit().getImage("hinhanh/title.png"));
		setSize(900, 700);
		setLocationRelativeTo(null);
		setResizable(false);

		createFrame();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void createFrame() {
		// ------------------ North ------------------ //
		JPanel pNorth = new JPanel();
		pNorth.setBackground(Color.CYAN);
		pNorth.add(lblTD = new JLabel("KHÁM BỆNH"));
		lblTD.setFont(new Font("Calibri Light", Font.BOLD, 25));
		add(pNorth, BorderLayout.NORTH);
		
		// ------------------ West ------------------ //
		JPanel pnWest = new JPanel();
		Box box = Box.createVerticalBox();
		pnWest.setBorder(BorderFactory.createTitledBorder("Danh sách bệnh nhân chờ khám"));
		JScrollPane scroll = new JScrollPane(listChoKham = new JList<>(model));
		box.add(scroll);
		box.add(Box.createVerticalStrut(5));
		JPanel panelGoiKham = new JPanel();
		panelGoiKham.setLayout(new BorderLayout());
		panelGoiKham.add(btnGoiKham = new JButton("                Gọi khám...                "));
		box.add(panelGoiKham);
		scroll.setPreferredSize(new Dimension(220, 550));
		btnGoiKham.setPreferredSize(new Dimension(220, 50));
		pnWest.add(box);
		add(pnWest, BorderLayout.WEST);
		
		// ------------------ Center ------------------ //
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		
		JPanel pCenNor = new JPanel();
		pCenNor.setLayout(new BorderLayout());
		Box boxHD = Box.createVerticalBox();
		boxHD.setBorder(BorderFactory.createTitledBorder("Hóa đơn"));
		Box b1, b2;
		boxHD.add(b1 = Box.createHorizontalBox());
		b1.add(Box.createHorizontalStrut(15));
		b1.add(lblMaToa = new JLabel("Mã toa thuốc  "));
		b1.add(txtMaToa = new JTextField());
		b1.add(Box.createHorizontalStrut(15));
		
		b1.add(lblNgayKe = new JLabel("Ngày kê"));
		b1.add(dcNgayKe = new JTextField());
//		b1.add(dcNgayKe = new JDateChooser());
//		dcNgayKe.setDateFormatString("yyyy-MM-dd");
//		Date day = new Date();
//		dcNgayKe.setDate(day);
		
		
		b1.add(Box.createHorizontalStrut(200));
		boxHD.add(Box.createVerticalStrut(10));
		boxHD.add(b2 = Box.createHorizontalBox());
		b2.add(Box.createHorizontalStrut(15));
		
		b2.add(lblMaBN = new JLabel("Mã bệnh nhân     "));
		b2.add(txtMaBN = new JTextField("Khách vãng lai"));
		
		b2.add(Box.createHorizontalStrut(15));
		b2.add(lblTenBN = new JLabel("Họ tên  "));
		b2.add(txtTenBN = new JTextField("Không kê đơn"));			// không có đơn của bác sĩ
		b2.add(Box.createHorizontalStrut(200));
		boxHD.add(Box.createVerticalStrut(10));
		lblNgayKe.setPreferredSize(lblMaBN.getPreferredSize());
		lblMaToa.setPreferredSize(lblMaBN.getPreferredSize());
		txtMaToa.setPreferredSize(new Dimension(150, 22));
		dcNgayKe.setPreferredSize(new Dimension(100, 23));
		txtMaBN.setPreferredSize(txtMaToa.getPreferredSize());
		txtTenBN.setPreferredSize(txtMaToa.getPreferredSize());
		pCenNor.add(boxHD, BorderLayout.NORTH);
		
		
		pCenter.add(pCenNor, BorderLayout.NORTH);
		add(pCenter, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		new KhamBenh_GUI().setVisible(true);
	}

}

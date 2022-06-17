package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import daos.BenhNhanDAO;
import daos.NhanVienDAO;
import daos.ThuocDAO;
import entities.BenhNhan;
import entities.NhanVien;
import entities.TaiKhoan;
import entities.Thuoc;
import ui.ManHinhChinhGUI;


public class Main {
	public static void main(String[] args) throws ParseException {
//		
//		TaiKhoan tk = new TaiKhoan("tctuyen", "123", 1);
//		NhanVien nv = new NhanVien("01", "To Cong Tuyen", 1, "0987730043", "go vap", tk);
//		NhanVienDAO nvd = new NhanVienDAO();
//		nvd.themNhanVien(nv);
//		System.out.println("seccess");
		
//		BenhNhan benhNhan = new BenhNhan("01", "Nguyen Van A", "Nam",LocalDate.now(), "0123456789", "go vap");
//		BenhNhanDAO benhNhanDAO = new BenhNhanDAO();
//		benhNhanDAO.themBenhNhan(benhNhan);
//		benhNhanDAO.getBenhNhans();

		new ManHinhChinhGUI().setVisible(true); 
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
//		List<NhanVien> dsnv = (ArrayList<NhanVien>) nhanVienDAO.getNhanViens();
//		dsnv.forEach(x->System.out.println(x));
		System.out.println(nhanVienDAO.timTaiKhoan("tctuyen", "123"));
//		Thuoc t = new Thuoc("01", "Thuoc la", "thuoc hut", "heroin");
//		ThuocDAO td = new ThuocDAO();
//		td.themThuoc(t);
	}
}

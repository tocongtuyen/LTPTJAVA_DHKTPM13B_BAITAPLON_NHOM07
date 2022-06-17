package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.TaiKhoan;

public class TaiKhoanDAO{
private EntityManager em;
	
	public TaiKhoanDAO(EntityManager em) {
		this.em = em;
	}

	public boolean themTaiKhoan(TaiKhoan tk) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(tk);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean xoaTaiKhoan(TaiKhoan tk) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(TaiKhoan.class, tk.getMaDN()));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean suaThongTinTK(TaiKhoan tk) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(tk);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public TaiKhoan getTaiKhoan(String matk) {
		return em.find(TaiKhoan.class, matk);
	}

	public List<TaiKhoan> getTaiKhoans(int max) {
		return em.createNamedQuery("getTaiKhoans", TaiKhoan.class)
				.setMaxResults(max)
				.getResultList();
	}

	public List<TaiKhoan> getTaiKhoanTheoTen(String ten) {
		return em.createQuery("from TaiKhoan tk where tk.ten=:x", TaiKhoan.class)
				.setParameter("x", ten)
				.getResultList();
	}

	public boolean ktTaiKhoanDN(String tk, String mk) {
//		TaiKhoan taikhoan = layTaiKhoan(tk, mk);		
//		String username = taikhoan.getMaDN(), password = taikhoan.getMatkhau();
//		
//		if(tk.equals(username) && mk.equals(password)) {
//			ManHinhChinhGUI.maDN = username;
//			ManHinhChinhGUI.user = 1;
//			int loaiTK = taikhoan.getLoaiTK();
//			if(loaiTK == 2) {
//				ManHinhChinhGUI.tenDN = "Admin";				
//				new ManHinhChinhGUI().setVisible(true);
//			}
//			else {
//				if(loaiTK == 0) {
//					ManHinhChinhGUI.tenDN = layTenNguoiDung(username, loaiTK);					
//				}
//				else if(loaiTK == 1) {
//					ManHinhChinhGUI.tenDN = "BS. " + layTenNguoiDung(username, loaiTK);
////					new KeToaThuocGUI().setVisible(true);
//				}
//			}
//			return true;
//		}
		return false;
	}

}

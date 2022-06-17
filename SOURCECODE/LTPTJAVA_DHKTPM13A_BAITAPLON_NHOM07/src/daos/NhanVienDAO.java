package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import entities.NhanVien;

public class NhanVienDAO{
private EntityManager em;
	
	public NhanVienDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	public boolean themNhanVien(NhanVien nv) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(nv);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean xoaNhanVien(NhanVien nv) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(NhanVien.class, nv.getMaNV()));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean suaThongTinNV(NhanVien nv) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(nv);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public NhanVien getNhanVien(String manv) {
		return em.find(NhanVien.class, manv);
	}

	public List<NhanVien> getNhanViens() {
		return em.createNamedQuery("getNhanViens", NhanVien.class)
				.getResultList();
	}

	public List<NhanVien> getNhanVienTheoTen(String ten) {
		return em.createQuery("from NhanVien nv where nv.tenNV=:x", NhanVien.class)
				.setParameter("x", ten)
				.getResultList();
	}
	
	public List<NhanVien> timTaiKhoan(String maDN, String matKhau) {
		return em.createQuery("from NhanVien nv where nv.taikhoan.maDN=:maDN and nv.taikhoan.matkhau=:matkhau", NhanVien.class)
				.setParameter("maDN", maDN)
				.setParameter("matkhau", matKhau)
				.getResultList();
	}
}

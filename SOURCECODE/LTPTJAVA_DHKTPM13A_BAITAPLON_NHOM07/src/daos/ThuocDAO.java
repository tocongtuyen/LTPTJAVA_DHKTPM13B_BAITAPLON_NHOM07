package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.Thuoc;

public class ThuocDAO{
private EntityManager em;
	
	public ThuocDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	public boolean themThuoc(Thuoc t) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(t);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean xoaThuoc(Thuoc t) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(Thuoc.class, t.getMaThuoc()));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean suaThongTinT(Thuoc t) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(t);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public Thuoc getThuoc(String mat) {
		return em.find(Thuoc.class, mat);
	}

	public List<Thuoc> getThuocs() {
		return em.createNamedQuery("getThuocs", Thuoc.class)
				.getResultList();
	}

	public List<Thuoc> getThuocTheoTen(String ten) {
		return em.createQuery("from Thuoc t where t.tenThuoc=:x", Thuoc.class)
				.setParameter("x", ten)
				.getResultList();
	}

}

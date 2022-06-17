package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.ToaThuoc;

public class ToaThuocDAO {
	
	private EntityManager em;
	
	public ToaThuocDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	public boolean themToaThuoc(ToaThuoc tt) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(tt);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean xoaToaThuoc(ToaThuoc tt) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(ToaThuoc.class, tt.getMaToa()));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean suaThongTinTT(ToaThuoc tt) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(tt);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public ToaThuoc getToaThuoc(String matt) {
		return em.find(ToaThuoc.class, matt);
	}

	public List<ToaThuoc> getToaThuocs() {
		return em.createNamedQuery("getToaThuocs", ToaThuoc.class)
				.getResultList();
	}

	public List<ToaThuoc> getToaThuocTheoTen(String ten) {
		return em.createQuery("from ToaThuoc tt where tt.ten=:x", ToaThuoc.class)
				.setParameter("x", ten)
				.getResultList();
	}

}

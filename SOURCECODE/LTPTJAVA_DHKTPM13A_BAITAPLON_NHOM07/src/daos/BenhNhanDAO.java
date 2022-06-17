package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.BenhNhan;

public class BenhNhanDAO {
	private EntityManager em;
	
	public BenhNhanDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	public boolean themBenhNhan(BenhNhan bn) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(bn);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean xoaBenhNhan(BenhNhan bn) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(BenhNhan.class, bn.getMaBN()));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public boolean suaThongTinBN(BenhNhan bn) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(bn);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	public BenhNhan getBenhNhan(String mabn) {
		return em.find(BenhNhan.class, mabn);
	}

	public List<BenhNhan> getBenhNhans() {
		return em.createNamedQuery("getBenhNhans", BenhNhan.class)
				.getResultList();
	}

	public List<BenhNhan> getBenhNhanTheoTen(String ten) {
		return em.createQuery("from BenhNhan bn where bn.tenBN=:x", BenhNhan.class)
				.setParameter("x", ten)
				.getResultList(); 
	}
}

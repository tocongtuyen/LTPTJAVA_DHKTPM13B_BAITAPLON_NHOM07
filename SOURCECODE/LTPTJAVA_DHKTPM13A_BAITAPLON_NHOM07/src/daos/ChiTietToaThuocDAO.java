package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entities.ChiTietToaThuoc;
import services.ChiTietToaThuocServices;

public class ChiTietToaThuocDAO implements ChiTietToaThuocServices{

	private EntityManager em;
	
	public ChiTietToaThuocDAO() {
		em = MyEntityManager.getInstance().getEntityManager();
	}

	@Override
	public boolean themChiTietToaThuoc(ChiTietToaThuoc ct) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.persist(ct);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	@Override
	public boolean xoaChiTietToaThuoc(ChiTietToaThuoc ct) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.remove(em.find(ChiTietToaThuoc.class, ct.getClass()));///?????
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	@Override
	public boolean suaThongTinCT(ChiTietToaThuoc ct) {
		EntityTransaction tr = em.getTransaction();
		try {
			tr.begin();
			em.merge(ct);
			tr.commit();
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			tr.rollback();
			return false;
		}
	}

	@Override
	public ChiTietToaThuoc getChiTietToaThuoc(String mact) {
		return em.find(ChiTietToaThuoc.class, mact);
	}

	@Override
	public List<ChiTietToaThuoc> getChiTietToaThuocs() {
		return em.createNamedQuery("getChiTietToaThuocs", ChiTietToaThuoc.class)
				.getResultList();
	}

	@Override
	public List<ChiTietToaThuoc> getChiTietToaThuocTheoTen(String ten) {
		return em.createQuery("from ChiTietToaThuoc ct where ct.ten=:x", ChiTietToaThuoc.class)
				.setParameter("x", ten)
				.getResultList();
	}

}

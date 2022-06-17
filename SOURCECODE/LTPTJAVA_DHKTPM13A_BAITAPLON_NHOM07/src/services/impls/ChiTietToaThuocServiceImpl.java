package services.impls;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import daos.ChiTietToaThuocDAO;
import entities.ChiTietToaThuoc;
import services.ChiTietToaThuocServices;

public class ChiTietToaThuocServiceImpl extends UnicastRemoteObject implements ChiTietToaThuocServices{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ChiTietToaThuocDAO chiTietDAO;
	
	public ChiTietToaThuocServiceImpl() throws RemoteException {
		chiTietDAO = new ChiTietToaThuocDAO();
	}

	@Override
	public boolean themChiTietToaThuoc(ChiTietToaThuoc ct) {
		return chiTietDAO.themChiTietToaThuoc(ct);
	}

	@Override
	public boolean xoaChiTietToaThuoc(ChiTietToaThuoc ct) {
		return chiTietDAO.xoaChiTietToaThuoc(ct);
	}

	@Override
	public boolean suaThongTinCT(ChiTietToaThuoc ct) {
		return chiTietDAO.suaThongTinCT(ct);
	}

	@Override
	public ChiTietToaThuoc getChiTietToaThuoc(String ct) {
		return chiTietDAO.getChiTietToaThuoc(ct);
	}

	@Override
	public List<ChiTietToaThuoc> getChiTietToaThuocs() {
		return chiTietDAO.getChiTietToaThuocs();
	}

	@Override
	public List<ChiTietToaThuoc> getChiTietToaThuocTheoTen(String ten) {
		// TODO Auto-generated method stub
		return null;
	}

}

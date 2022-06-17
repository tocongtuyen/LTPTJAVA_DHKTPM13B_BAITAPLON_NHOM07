package services.impls;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import daos.ToaThuocDAO;
import entities.ToaThuoc;
import services.ToaThuocServices;

public class ToaThuocServiceImpl extends UnicastRemoteObject implements ToaThuocServices {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ToaThuocDAO toaThuocDAO;
	
	
	public ToaThuocServiceImpl() throws RemoteException {
		toaThuocDAO = new ToaThuocDAO();
	}

	@Override
	public boolean themToaThuoc(ToaThuoc tt) {
		return toaThuocDAO.themToaThuoc(tt);
	}

	@Override
	public boolean xoaToaThuoc(ToaThuoc tt) {
		return toaThuocDAO.xoaToaThuoc(tt);
	}

	@Override
	public boolean suaThongTinTT(ToaThuoc tt) {
		return toaThuocDAO.suaThongTinTT(tt);
	}

	@Override
	public ToaThuoc getToaThuoc(String tt) {
		return toaThuocDAO.getToaThuoc(tt);
	}

	@Override
	public List<ToaThuoc> getToaThuocs() {
		return toaThuocDAO.getToaThuocs();
	}

	@Override
	public List<ToaThuoc> getToaThuocTheoTen(String ten) {
		return null;
	}

}

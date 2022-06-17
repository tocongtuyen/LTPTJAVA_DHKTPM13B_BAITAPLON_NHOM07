package services.impls;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import daos.ThuocDAO;
import entities.Thuoc;
import services.ThuocServices;

public class ThuocServiceImpl extends UnicastRemoteObject implements ThuocServices{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ThuocDAO thuocDAO;
	
	
	public ThuocServiceImpl() throws RemoteException {
		thuocDAO = new ThuocDAO();
	}

	@Override
	public boolean themThuoc(Thuoc t) {
		return thuocDAO.themThuoc(t);
	}

	@Override
	public boolean xoaThuoc(Thuoc t) {
		return thuocDAO.xoaThuoc(t);
	}

	@Override
	public boolean suaThongTinT(Thuoc t) {
		return thuocDAO.suaThongTinT(t);
	}

	@Override
	public Thuoc getThuoc(String t) {
		return thuocDAO.getThuoc(t);
	}

	@Override
	public List<Thuoc> getThuocs() {
		return thuocDAO.getThuocs();
	}

	@Override
	public List<Thuoc> getThuocTheoTen(String ten) {
		return thuocDAO.getThuocTheoTen(ten);
	}

}

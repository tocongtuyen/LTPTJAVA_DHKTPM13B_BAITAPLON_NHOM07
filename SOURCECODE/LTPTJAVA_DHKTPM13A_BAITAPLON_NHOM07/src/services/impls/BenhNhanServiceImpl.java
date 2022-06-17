package services.impls;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import daos.BenhNhanDAO;
import entities.BenhNhan;
import services.BenhNhanServices;

public class BenhNhanServiceImpl extends UnicastRemoteObject implements BenhNhanServices{
	private Integer portBS;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BenhNhanDAO benhNhanDAO;
	
	
	public BenhNhanServiceImpl() throws RemoteException{
		benhNhanDAO = new BenhNhanDAO();
	}

	@Override
	public boolean themBenhNhan(BenhNhan bn) {
		return benhNhanDAO.themBenhNhan(bn);
	}

	@Override
	public boolean xoaBenhNhan(BenhNhan bn) {
		return benhNhanDAO.xoaBenhNhan(bn);
	}

	@Override
	public boolean suaThongTinBN(BenhNhan bn) {
		return benhNhanDAO.suaThongTinBN(bn);
	}

	@Override
	public BenhNhan getBenhNhan(String bn) {
		return benhNhanDAO.getBenhNhan(bn);
	}

	@Override
	public List<BenhNhan> getBenhNhans() {
		return benhNhanDAO.getBenhNhans();
	}

	@Override
	public List<BenhNhan> getBenhNhanTheoTen(String ten) {
		return benhNhanDAO.getBenhNhanTheoTen(ten);
	}

	@Override
	public void dangKyPort(int port) throws RemoteException {
		this.portBS = port; 
	}

	@Override
	public Integer layPort() throws RemoteException {
		return portBS;
	}

}

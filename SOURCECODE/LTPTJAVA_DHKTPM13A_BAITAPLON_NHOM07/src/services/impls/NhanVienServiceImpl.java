package services.impls;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import daos.NhanVienDAO;
import entities.NhanVien;
import services.NhanVienServices;

public class NhanVienServiceImpl extends UnicastRemoteObject implements NhanVienServices{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private NhanVienDAO nhanVienDAO;
		
	public NhanVienServiceImpl() throws RemoteException {
		nhanVienDAO = new NhanVienDAO();
	}

	@Override
	public boolean themNhanVien(NhanVien nv) {
		return nhanVienDAO.themNhanVien(nv);
	}

	@Override
	public boolean xoaNhanVien(NhanVien nv) {
		return nhanVienDAO.xoaNhanVien(nv);
	}

	@Override
	public boolean suaThongTinNV(NhanVien nv) {
		return nhanVienDAO.suaThongTinNV(nv);
	}

	@Override
	public NhanVien getNhanVien(String nv) {
		return nhanVienDAO.getNhanVien(nv);
	}

	@Override
	public List<NhanVien> getNhanViens() {
		return nhanVienDAO.getNhanViens();
	}

	@Override
	public List<NhanVien> getNhanVienTheoTen(String ten) {
		return nhanVienDAO.getNhanVienTheoTen(ten);
	}

	@Override
	public List<NhanVien> timTaiKhoan(String maDN, String matKhau) {
		return nhanVienDAO.timTaiKhoan(maDN, matKhau);
	}

}

package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.NhanVien;

public interface NhanVienServices extends Remote{
	public boolean themNhanVien(NhanVien nv) throws RemoteException;
	public boolean xoaNhanVien(NhanVien nv) throws RemoteException;
	public boolean suaThongTinNV(NhanVien nv) throws RemoteException;
	public NhanVien getNhanVien(String nv) throws RemoteException;
	public List<NhanVien> getNhanViens() throws RemoteException;
	public List<NhanVien> getNhanVienTheoTen(String ten) throws RemoteException;
	public List<NhanVien> timTaiKhoan(String maDN, String matKhau) throws RemoteException;
}

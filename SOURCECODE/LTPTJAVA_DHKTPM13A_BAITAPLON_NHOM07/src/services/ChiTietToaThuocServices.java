package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.ChiTietToaThuoc;

public interface ChiTietToaThuocServices extends Remote{
	public boolean themChiTietToaThuoc(ChiTietToaThuoc ct) throws RemoteException;
	public boolean xoaChiTietToaThuoc(ChiTietToaThuoc ct) throws RemoteException;
	public boolean suaThongTinCT(ChiTietToaThuoc ct) throws RemoteException;
	public ChiTietToaThuoc getChiTietToaThuoc(String ct) throws RemoteException;
	public List<ChiTietToaThuoc> getChiTietToaThuocs() throws RemoteException;
	public List<ChiTietToaThuoc> getChiTietToaThuocTheoTen(String ten) throws RemoteException;
}

package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.Thuoc;

public interface ThuocServices extends Remote{
	public boolean themThuoc(Thuoc t) throws RemoteException;
	public boolean xoaThuoc(Thuoc t) throws RemoteException;
	public boolean suaThongTinT(Thuoc t) throws RemoteException;
	public Thuoc getThuoc(String t) throws RemoteException;
	public List<Thuoc> getThuocs() throws RemoteException;
	public List<Thuoc> getThuocTheoTen(String ten) throws RemoteException;
}

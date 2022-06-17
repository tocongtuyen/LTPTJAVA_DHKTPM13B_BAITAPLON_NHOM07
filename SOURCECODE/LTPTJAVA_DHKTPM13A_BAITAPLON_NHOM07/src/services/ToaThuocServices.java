package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.ToaThuoc;

public interface ToaThuocServices extends Remote{
	public boolean themToaThuoc(ToaThuoc tt) throws RemoteException;
	public boolean xoaToaThuoc(ToaThuoc tt) throws RemoteException;
	public boolean suaThongTinTT(ToaThuoc tt) throws RemoteException;
	public ToaThuoc getToaThuoc(String tt) throws RemoteException;
	public List<ToaThuoc> getToaThuocs() throws RemoteException;
	public List<ToaThuoc> getToaThuocTheoTen(String ten) throws RemoteException;
}

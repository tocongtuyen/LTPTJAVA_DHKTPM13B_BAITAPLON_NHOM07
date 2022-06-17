package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.BenhNhan;

public interface BenhNhanServices extends Remote{
	public boolean themBenhNhan(BenhNhan bn) throws RemoteException;
	public boolean xoaBenhNhan(BenhNhan bn) throws RemoteException;
	public boolean suaThongTinBN(BenhNhan bn) throws RemoteException;
	public BenhNhan getBenhNhan(String bn) throws RemoteException;
	public List<BenhNhan> getBenhNhans() throws RemoteException;
	public List<BenhNhan> getBenhNhanTheoTen(String ten) throws RemoteException;
	void dangKyPort(int port) throws RemoteException;
	Integer layPort()throws RemoteException;
}

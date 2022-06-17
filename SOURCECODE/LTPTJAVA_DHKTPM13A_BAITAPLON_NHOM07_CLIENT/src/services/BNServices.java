package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import entities.BenhNhan;

public interface BNServices extends Remote{
	void themBenhNhan(BenhNhan bn) throws RemoteException;
}

package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import daos.BenhNhanDAO;
import entities.BenhNhan;
import services.BNServices;
import ui.KhamBenh_GUI;

public class BNServiceImpl extends UnicastRemoteObject implements BNServices{
	KhamBenh_GUI kb_gui;

	public BNServiceImpl(KhamBenh_GUI kb_gui) throws RemoteException{
		this.kb_gui = kb_gui;
	}

	@Override
	public void themBenhNhan(BenhNhan bn) {
		this.kb_gui.themBN(bn);
	}

}

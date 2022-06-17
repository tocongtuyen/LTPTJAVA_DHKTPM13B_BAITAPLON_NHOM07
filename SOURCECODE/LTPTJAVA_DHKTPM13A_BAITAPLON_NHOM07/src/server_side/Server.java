package server_side;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import services.BenhNhanServices;
import services.ChiTietToaThuocServices;
import services.NhanVienServices;
import services.ThuocServices;
import services.ToaThuocServices;
import services.impls.BenhNhanServiceImpl;
import services.impls.ChiTietToaThuocServiceImpl;
import services.impls.NhanVienServiceImpl;
import services.impls.ThuocServiceImpl;
import services.impls.ToaThuocServiceImpl;

public class Server {
	public static void main(String[] args)throws RemoteException {
		try {
			SecurityManager securityManager = System.getSecurityManager();
			if(securityManager == null) {
				System.setProperty("java.security.policy","policy\\policyFile.policy");
				securityManager = new SecurityManager();
			}

			BenhNhanServices benhNhanServices = new BenhNhanServiceImpl();
			NhanVienServices nhanVienServices = new NhanVienServiceImpl();
			ThuocServices thuocServices = new ThuocServiceImpl();
			ToaThuocServices toaThuocServices = new ToaThuocServiceImpl();
			ChiTietToaThuocServices chiTietToaThuocServices = new ChiTietToaThuocServiceImpl();
			LocateRegistry.createRegistry(1999);
			
//			Naming.rebind("rmi://h73m21:1999/sv_service", sinhvienServices);
//			Naming.rebind("rmi://h73m21:1999/lh_service", lophocServices);

			Naming.rebind("rmi://localhost:1999/bn_service", benhNhanServices);
			Naming.rebind("rmi://localhost:1999/nv_service", nhanVienServices);
			Naming.rebind("rmi://localhost:1999/thuoc_service", thuocServices);
			Naming.rebind("rmi://localhost:1999/toathuoc_service", toaThuocServices);
			Naming.rebind("rmi://localhost:1999/chitiet_service", chiTietToaThuocServices);
			System.out.println("Server is ready ... ");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

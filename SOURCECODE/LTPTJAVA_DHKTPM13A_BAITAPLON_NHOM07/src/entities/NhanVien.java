package entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: NhanVien
 *
 */
@Entity
@Table(name = "nhanviens")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getNhanViens", query = "db.nhanviens.find({})", resultClass = NhanVien.class)
})
public class NhanVien implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String maNV;
	private String tenNV;
	private int loaiNV;
	private String sodt;
	private String diaChi;
	
	@Embedded
	private TaiKhoan taikhoan;
	
	public String getMaNV() {
		return maNV;
	}


	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}


	public String getTenNV() {
		return tenNV;
	}


	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}


	public int getLoaiNV() {
		return loaiNV;
	}


	public void setLoaiNV(int loaiNV) {
		this.loaiNV = loaiNV;
	}


	public String getSodt() {
		return sodt;
	}


	public void setSodt(String sodt) {
		this.sodt = sodt;
	}


	public String getDiaChi() {
		return diaChi;
	}


	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}


	public TaiKhoan getTaikhoan() {
		return taikhoan;
	}


	public void setTaikhoan(TaiKhoan taikhoan) {
		this.taikhoan = taikhoan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}


	public NhanVien() {
		super();
	}


	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}


	public NhanVien(String maNV, String tenNV, int loaiNV, String sodt, String diaChi, TaiKhoan taikhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.loaiNV = loaiNV;
		this.sodt = sodt;
		this.diaChi = diaChi;
		this.taikhoan = taikhoan;
	}


	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", loaiNV=" + loaiNV + ", sodt=" + sodt + ", diaChi="
				+ diaChi + ", taikhoan=" + taikhoan + "]";
	}   
}

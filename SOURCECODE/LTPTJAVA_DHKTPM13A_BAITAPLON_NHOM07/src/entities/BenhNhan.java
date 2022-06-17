package entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: BenhNhan
 *
 */
@Entity
@Table(name = "benhnhans")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getBenhNhans", query = "db.benhnhans.find({})", resultClass = BenhNhan.class)
})
public class BenhNhan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1568244852601289181L;
	
	@Id
	private String maBN;
	private String tenBN;
	private String gioiTinh;
	private LocalDate ngaysinh;
	private String sodt;
	private String diaChi;
	
	
	public BenhNhan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BenhNhan(String maBN, String tenBN, String gioiTinh, LocalDate ngaysinh, String sodt, String diaChi) {
		super();
		this.maBN = maBN;
		this.tenBN = tenBN;
		this.gioiTinh = gioiTinh;
		this.ngaysinh = ngaysinh;
		this.sodt = sodt;
		this.diaChi = diaChi;
	}


	public String getMaBN() {
		return maBN;
	}


	public void setMaBN(String maBN) {
		this.maBN = maBN;
	}


	public String getTenBN() {
		return tenBN;
	}


	public void setTenBN(String tenBN) {
		this.tenBN = tenBN;
	}


	public String getGioiTinh() {
		return gioiTinh;
	}


	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}


	public LocalDate getNgaysinh() {
		return ngaysinh;
	}


	public void setNgaysinh(LocalDate ngaysinh) {
		this.ngaysinh = ngaysinh;
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


	@Override
	public String toString() {
		return "BenhNhan [maBN=" + maBN + ", tenBN=" + tenBN + ", gioiTinh=" + gioiTinh + ", ngaysinh=" + ngaysinh
				+ ", sodt=" + sodt + ", diaChi=" + diaChi + "]";
	}

}

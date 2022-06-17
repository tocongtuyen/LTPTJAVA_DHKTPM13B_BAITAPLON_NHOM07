package entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: ToaThuoc
 *
 */
@Entity
@Table(name = "toathuocs")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getToaThuocs", query = "db.toathuocs.find({})", resultClass = ToaThuoc.class)
})
public class ToaThuoc implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 647044173561471429L;
	
	@Id
	private int maToa;
	private LocalDate ngayke;
	private String ghichu;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maNV")
	private NhanVien nhanVien;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "maBN")
	private BenhNhan benhNhan;

	public ToaThuoc() {

	}

	
	public ToaThuoc(int maToa, LocalDate ngayke, String ghichu) {
		super();
		this.maToa = maToa;
		this.ngayke = ngayke;
		this.ghichu = ghichu;
	}


	public int getMaToa() {
		return maToa;
	}


	public void setMaToa(int maToa) {
		this.maToa = maToa;
	}


	public LocalDate getNgayke() {
		return ngayke;
	}


	public void setNgayke(LocalDate ngayke) {
		this.ngayke = ngayke;
	}


	public String getGhichu() {
		return ghichu;
	}


	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}


	public NhanVien getNhanVien() {
		return nhanVien;
	}


	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}


	public BenhNhan getBenhNhan() {
		return benhNhan;
	}


	public void setBenhNhan(BenhNhan benhNhan) {
		this.benhNhan = benhNhan;
	}


	@Override
	public String toString() {
		return "ToaThuoc [maToa=" + maToa + ", ngayke=" + ngayke + ", ghichu=" + ghichu + ", nhanVien=" + nhanVien
				+ ", benhNhan=" + benhNhan + "]";
	}

	
}

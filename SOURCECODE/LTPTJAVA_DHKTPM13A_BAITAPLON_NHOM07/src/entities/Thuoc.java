package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Thuoc
 *
 */
@Entity
@Table(name = "thuocs")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getThuocs", query = "db.thuocs.find({})", resultClass = Thuoc.class)
})
public class Thuoc implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String maThuoc;
	private String tenThuoc;
	private String nhomThuoc;
	private String thanhPhan;

	public String getMaThuoc() {
		return maThuoc;
	}


	public void setMaThuoc(String maThuoc) {
		this.maThuoc = maThuoc;
	}


	public String getTenThuoc() {
		return tenThuoc;
	}


	public void setTenThuoc(String tenThuoc) {
		this.tenThuoc = tenThuoc;
	}

	
	public String getNhomThuoc() {
		return nhomThuoc;
	}


	public void setNhomThuoc(String nhomThuoc) {
		this.nhomThuoc = nhomThuoc;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}


	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maThuoc == null) ? 0 : maThuoc.hashCode());
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
		Thuoc other = (Thuoc) obj;
		if (maThuoc == null) {
			if (other.maThuoc != null)
				return false;
		} else if (!maThuoc.equals(other.maThuoc))
			return false;
		return true;
	}


	public Thuoc() {
		super();
	}


	public Thuoc(String maThuoc, String tenThuoc, String nhomThuoc, String thanhPhan) {
		super();
		this.maThuoc = maThuoc;
		this.tenThuoc = tenThuoc;
		this.nhomThuoc = nhomThuoc;
		this.thanhPhan = thanhPhan;
	}


	@Override
	public String toString() {
		return "Thuoc [maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", nhomThuoc=" + nhomThuoc + ", thanhPhan="
				+ thanhPhan + "]";
	}
}

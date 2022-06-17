package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ChiTietToaThuoc
 *
 */
@Entity
@IdClass(PK_CTTT.class)
@Table(name = "chitiettoathuocs")
@NamedNativeQueries({
	@NamedNativeQuery(name = "getChiTietToaThuocs", query = "db.chitiettoathuocs.find({})", resultClass = ChiTietToaThuoc.class)
})
public class ChiTietToaThuoc implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
	@JoinColumn(name="maToa")
	private ToaThuoc toathuoc;
	
	@Id
	@ManyToOne
	@JoinColumn(name="maThuoc")
	private Thuoc thuoc;
	
	private int soluong;
	private String donVT;
	private String cachdung;

	public ToaThuoc getToathuoc() {
		return toathuoc;
	}
	
	public void setToathuoc(ToaThuoc toathuoc) {
		this.toathuoc = toathuoc;
	}
	
	public Thuoc getThuoc() {
		return thuoc;
	}

	public void setThuoc(Thuoc thuoc) {
		this.thuoc = thuoc;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public String getDonVT() {
		return donVT;
	}

	public void setDonVT(String donVT) {
		this.donVT = donVT;
	}

	public String getCachdung() {
		return cachdung;
	}

	public void setCachdung(String cachdung) {
		this.cachdung = cachdung;
	}

	public ChiTietToaThuoc() {
		super();
	}

	public ChiTietToaThuoc(ToaThuoc toathuoc, Thuoc thuoc, int soluong, String donVT, String cachdung) {
		super();
		this.toathuoc = toathuoc;
		this.thuoc = thuoc;
		this.soluong = soluong;
		this.donVT = donVT;
		this.cachdung = cachdung;
	}

	@Override
	public String toString() {
		return "ChiTietToaThuoc [toathuoc=" + toathuoc + ", thuoc=" + thuoc + ", soluong=" + soluong + ", donVT="
				+ donVT + ", cachdung=" + cachdung + "]";
	}
}

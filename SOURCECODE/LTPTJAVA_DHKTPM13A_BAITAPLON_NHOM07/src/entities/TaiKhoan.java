package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Entity implementation class for Entity: TaiKhoan
 *
 */
@Embeddable
public class TaiKhoan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String maDN;
	private String matkhau;
	private int loaiTK;

	public String getMaDN() {
		return maDN;
	}

	public void setMaDN(String maDN) {
		this.maDN = maDN;
	}

	public String getMatkhau() {
		return matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public int getLoaiTK() {
		return loaiTK;
	}

	public void setLoaiTK(int loaiTK) {
		this.loaiTK = loaiTK;
	}


	public TaiKhoan() {
		super();
	}

	public TaiKhoan(String maDN, String matkhau, int loaiTK) {
		super();
		this.maDN = maDN;
		this.matkhau = matkhau;
		this.loaiTK = loaiTK;
	}

	@Override
	public String toString() {
		return "TaiKhoan [maDN=" + maDN + ", matkhau=" + matkhau + ", loaiTK=" + loaiTK + "]";
	}   
}

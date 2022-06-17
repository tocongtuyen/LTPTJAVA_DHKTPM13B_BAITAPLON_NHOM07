package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PK_CTTT implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 60946272966975586L;

	private Integer toathuoc;
	private String thuoc;
	
	public PK_CTTT() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PK_CTTT(Integer toathuoc, String thuoc) {
		super();
		this.toathuoc = toathuoc;
		this.thuoc = thuoc;
	}

	public Integer getToathuoc() {
		return toathuoc;
	}

	public void setToathuoc(Integer toathuoc) {
		this.toathuoc = toathuoc;
	}

	public String getThuoc() {
		return thuoc;
	}

	public void setThuoc(String thuoc) {
		this.thuoc = thuoc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((thuoc == null) ? 0 : thuoc.hashCode());
		result = prime * result + ((toathuoc == null) ? 0 : toathuoc.hashCode());
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
		PK_CTTT other = (PK_CTTT) obj;
		if (thuoc == null) {
			if (other.thuoc != null)
				return false;
		} else if (!thuoc.equals(other.thuoc))
			return false;
		if (toathuoc == null) {
			if (other.toathuoc != null)
				return false;
		} else if (!toathuoc.equals(other.toathuoc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PK_CTTT [toathuoc=" + toathuoc + ", thuoc=" + thuoc + "]";
	}
	
	
}

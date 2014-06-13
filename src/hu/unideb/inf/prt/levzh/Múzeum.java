package hu.unideb.inf.prt.levzh;

import java.util.List;

public class Múzeum {
	protected String id;
	@AtLeastTenChars
	protected String név;
	protected String cím;
	protected String zárás;
	List<Kiállítás> kiállítások;
	
	public Múzeum(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNév() {
		return név;
	}

	public void setNév(String név) {
		this.név = név;
	}

	public String getCím() {
		return cím;
	}

	public void setCím(String cím) {
		this.cím = cím;
	}

	public String getZárás() {
		return zárás;
	}

	public void setZárás(String zárás) {
		this.zárás = zárás;
	}

	public List<Kiállítás> getKiállítások() {
		return kiállítások;
	}

	public void setKiállítások(List<Kiállítás> kiállítások) {
		this.kiállítások = kiállítások;
	}

	@Override
	public String toString() {
		return "Múzeum [id=" + id + ", név=" + név + ", cím=" + cím
				+ ", zárás=" + zárás + ", kiállítások=" + kiállítások + "]";
	}

}
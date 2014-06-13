package hu.unideb.inf.prt.levzh;

public class Kiállítás {
	protected String id;
	protected String cím;
	protected int megtekintés;
	
	public Kiállítás(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCím() {
		return cím;
	}

	public void setCím(String cím) {
		this.cím = cím;
	}

	public int getMegtekintés() {
		return megtekintés;
	}

	public void setMegtekintés(int megtekintés) {
		this.megtekintés = megtekintés;
	}

	@Override
	public String toString() {
		return "Kiállítás [id=" + id + ", cím=" + cím + ", megtekintés="
				+ megtekintés + "]";
	}
	
	
}

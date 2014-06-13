package hu.unideb.inf.prt.levzh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Személy {

	public enum Státusz {
		FELNŐTT, DIÁK, NYUGDÍJAS, GYERMEK
	}

	@AtLeastTenChars
	protected String id;
	protected String vezetéknév;
	protected String keresztnév;
	protected Integer életkor;
	protected String cím;
	protected Státusz státusz;

	public Személy(String id) {
		super();
		this.id = id;
	}

	public void setÉletkor(Integer életkor) {
		this.életkor = életkor;
	}

	public String getVezetéknév() {
		return vezetéknév;
	}

	public void setVezetéknév(String vezetéknév) {
		this.vezetéknév = vezetéknév;
	}

	public String getKeresztnév() {
		return keresztnév;
	}

	public void setKeresztnév(String keresztnév) {
		this.keresztnév = keresztnév;
	}

	public String getCím() {
		return cím;
	}

	public void setCím(String cím) {
		this.cím = cím;
	}

	public String getId() {
		return id;
	}

	public Személy(String id, String vezetéknév, String keresztnév,
			Integer életkor, String cím, Státusz státusz) {
		this(id);
		this.vezetéknév = vezetéknév;
		this.keresztnév = keresztnév;
		this.életkor = életkor;
		this.cím = cím;
		this.státusz = státusz;
	}

	public Személy(String id, String vezetéknév, String keresztnév,
			String születésiDátum, String cím, String diákigazolványszám,
			Státusz státusz) throws ParseException {
		this(id);
		this.vezetéknév = vezetéknév;
		this.keresztnév = keresztnév;
		this.életkor = meghatározÉletkort(születésiDátum);
		this.cím = cím;
		this.státusz = státusz;
	}

	/**
	 * Egy yyyy.MM.dd formátummal megadott napon született személyről
	 * meghatározza, hogy a rendszeridő által meghatározott napon hány éves.
	 * Pl. 2014.06.06-án: 
	 * meghatározÉletkort("2010.02.12") -> 4 
	 * meghatározÉletkort("2010.06.06") -> 4 
	 * meghatározÉletkort("2010.06.07") -> 3 
	 * meghatározÉletkort("2010.11.19") -> 3 
	 * 
	 * @param születésiDátum a születési dátum
	 * @return az évek száma Integer-be csomagolva
	 * @throws ParseException
	 *             ha a paraméterként kapott sztring nem yyyy.MM.dd formátumú
	 *             dátumot tartalmaz
	 */
	private Integer meghatározÉletkort(String születésiDátum)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		Calendar date = Calendar.getInstance(); // Set this to date to check
		date.setTime(sdf.parse(születésiDátum));
		Calendar today = Calendar.getInstance();
		int curYear = today.get(Calendar.YEAR);
		int curMonth = today.get(Calendar.MONTH);
		int curDay = today.get(Calendar.DAY_OF_MONTH);

		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH);
		int day = date.get(Calendar.DAY_OF_MONTH);

		int age = curYear - year;
		if (curMonth < month || (month == curMonth && curDay < day)) {
			age--;
		}
		return new Integer(age);
	}

	public Integer getÉletkor() {
		return életkor;
	}

	@Override
	public String toString() {
		return "Személy [id=" + id + ", vezetéknév=" + vezetéknév
				+ ", keresztnév=" + keresztnév + ", életkor=" + életkor
				+ ", cím=" + cím + ", státusz=" + státusz + "]";
	}

}
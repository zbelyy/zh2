package hu.unideb.inf.prt.levzh;

import java.util.List;

public interface MúzeumDAO {
	/**
	 * Visszaadja az adott nevű kiállítást. java.util.NoSuchElementException-t
	 * dob, ha az adott névvel nincs kiállítás az adatforrásban. Feltételezzük,
	 * a kiállításnevek egyediek.
	 * 
	 * @param kiállításnév
	 *            azonosító
	 * @return kiállítás
	 * @throws java.util.NoSuchElementException
	 *             ha adott id-vel nincs kiállítás az adatforráson.
	 */
	Kiállítás getKiállításByNév(String kiállításnév);

	/**
	 * Visszaadja az adott múzeum összes kiállítását.
	 * 
	 * @param múzeumnév
	 * @return kiállítások listája
	 */
	List<Kiállítás> getÖsszesKiállításAdottMúzeumból(String múzeumnév);
}
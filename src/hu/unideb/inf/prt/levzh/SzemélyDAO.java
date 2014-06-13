package hu.unideb.inf.prt.levzh;

import java.util.List;

public interface SzemélyDAO {
	/**
	 * Visszaadja az adott azonosítójú személyt.
	 * java.util.NoSuchElementException-t dob, ha az adott azonosítóval nincs
	 * személy az adatforrásban.
	 * 
	 * @param id
	 *            azonosító
	 * @return a személy
	 * @throws java.util.NoSuchElementException
	 *             ha adott id-vel nincs személy az adatforráson.
	 */
	Személy getSzemélyById(String id);

	/**
	 * Visszaadja az adott címen lakó összes személyt.
	 * 
	 * @param cím
	 * @return személyek listája
	 */
	List<Személy> getÖsszesSzemélyAdottStátusszal(String státusz);
}
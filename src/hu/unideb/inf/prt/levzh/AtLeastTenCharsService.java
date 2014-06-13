package hu.unideb.inf.prt.levzh;

import java.lang.reflect.Field;
import java.util.List;

import javax.management.ReflectionException;

public interface AtLeastTenCharsService {
	/**
	 * Meghatározza, hogy melyek egy objektum azon adattagjai, amelyek megsértik
	 * az AtLeastTenChars megszorítást és az objektum futásidejű osztályában kerültek
	 * deklarálásra.
	 * 
	 * @param o
	 *            az objektum
	 * @return a megszorítást sértő adattagok listája (lehet üres is!)
	 * @throws ReflectionException
	 *             ha a reflektív elérés miatt valamilyen kivétel kiváltódik
	 */
	List<Field> getAtLeastTenCharstMegsértőAdattagok(Object o);

	/**
	 * Az o objektum AtLeastTenChars megszorítást megsértő adattagjainak értékét
	 * többszörözze úgy meg, hogy a megszorítás a továbbiakban már ne sérüljön!
	 * Példa: ha a Múzeum osztály név nevű, String típusú adattagjára elő van írva
	 * a megszorítás, és e metódus egy olyan Múzeum osztálybeli objektumpéldányt kap,
	 * amely példány esetén a név attribútum értéke „Modem”, akkor állítsa be a név
	 * attribútum értékét „ModemModem”-re, mivel ez a legkisebb olyan többszörözés,
	 * amellyel a megszorítás már nem sérül. Ha a paraméterként kapott példány név
	 * attribútumának értéke „SzM”, akkor állítsa „SzMSzMSzMSzM”-re.
	 * 
	 * @param f
	 *            String típusú adattag
	 * @param o
	 *            az objektum
	 * @throws ReflectionException
	 *             ha a reflektív elérés miatt valamilyen kivétel kiváltódik
	 */
	void correctAtLeastTenCharsMegsértések(Object o);
}
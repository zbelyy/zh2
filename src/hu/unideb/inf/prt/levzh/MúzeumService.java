package hu.unideb.inf.prt.levzh;

import java.util.List;

public interface MúzeumService {
	/**
	 * Meghatározza a Személy által fizetendő jegyárat, figyelembe véve a
	 * Személy jellemzőit (címét, életkorát, diák ill. nyugdíjas voltát, hogy
	 * azok alapján jogosult-e valamilyen kedvezményre). Egy személyt akkor
	 * tekintünk diáknak illetve nyugdíjasnak, ha státusza nyugdíjas, illetve
	 * diák. A jegyár kiszámítását a feladatsor tetején adott táblázat és a
	 * közvetlenül alatta elhelyezkedő két bekezdésben leírtak alapján végezze!
	 * 
	 * @param k
	 *            kiállítás
	 * @param sz
	 *            személy
	 * @return jegyár
	 */
	int getJegyár(Kiállítás k, Személy sz);

	/**
	 * Meghatározza az adott kiállítás megtekintési ideje, az adott múzeum
	 * zárási ideje, valamint a paraméterként kapott belépési idő alapján, hogy
	 * van-e még ideje a látogatónak arra, hogy ezt a kiállítást megnézze. A
	 * metódus akkor adjon igaz értéket, ha a belépési idő és a zárási idő
	 * között legalább a megtekintési idő háromnegyede rendelkezésre áll
	 * (kerekítve, ld. példa), különben adjon hamisat! A belépés időpontja MI:SS
	 * formátumban (perc:másodperc, mindkettő két karakteren, pl. 07:05 vagy
	 * 16:02) van megadva.
	 * 
	 * Példa: egy kiállítás megtekintési ideje 90 perc, ennek a háromnegyede
	 * 67,5 perc, kerekítve 68 perc. Ha a múzeum 18:00-kor zár, akkor a metódus
	 * akkor és csak akkor adjon igazat, ha a belépési idő legkésőbb 16:52
	 * (ekkor van ugyanis pont 68 perc zárásig).
	 * 
	 * @param m
	 *            Múzeum
	 * @param k
	 *            Kiállítás
	 * @param belépésiIdő
	 *            String
	 */
	boolean vanIdőAKiállításra(Múzeum m, Kiállítás k, String belépésiIdő);

	/**
	 * Meghatározza, hogy a paraméterként megadott kiállítás megadott időpontban
	 * történő megtekintése után az adott m múzeumban még milyen más kiállítások
	 * megtekintésére lenne idő a b. pont feladatában leírt értelemben,
	 * feltételezve, hogy a k kiállítás megtekintése a megtekintés
	 * példányváltozó értékében megadott számú percet vesz igénybe.
	 * 
	 * @param m
	 *            Múzeum
	 * @param k
	 *            Kiállítás
	 * @param belépésiIdő
	 *            String
	 */
	List<Kiállítás> továbbiKiállítások(Múzeum m, Kiállítás k, String belépésiIdő);
}
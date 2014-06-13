package hu.unideb.inf.prt.levzh;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adattagokra megadható annotációtípusként az adott adattagra megszorítást ír
 * elő. Ha az adattag nem String típusú, akkor megadásának nincs hatása. String
 * típusú adattagokra vonatkozóan megadva azt jelenti, hogy az adattag értékének
 * legalább 10 karakter hosszúságúnak kell(ene) lennie.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AtLeastTenChars {
}

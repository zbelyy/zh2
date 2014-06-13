package hu.unideb.inf.prt.levzh;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class AtLeastTenCharsServiceImpl implements AtLeastTenCharsService {

    @Override
    public List<Field> getAtLeastTenCharstMegsértőAdattagok(Object o) {

        //üres lista a visszatérési értéknek
        List<Field> returnList = Collections.emptyList();

        //lekérjük az összes deklarált mezőt az osztályból
        Field[] fieldList = o.getClass().getDeclaredFields();

        for (Field field : fieldList) {
            //ha a mező típusa string és van hozzá AtLeastTenChars annotáció, akkor
            if (field.getType() == String.class && field.getAnnotation(AtLeastTenChars.class) != null) {
                //megpróbáljuk az értékét kivenni, és
                try {
                    String value = (String) field.get(field);
                    //ha rövidebb mint 10 karakter hozzáfűzzük a visszatérési listához
                    if (value.length() < 10) {
                        returnList.add(field);
                    }
                    //ha meg exception váltódik ki, eldobjuk a saját kivételtípusunkat
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    throw new ReflectionException(e.getCause());
                }
            }
        }
        //visszaadjuk a listát
        return returnList;
    }

    @Override
    public void correctAtLeastTenCharsMegsértések(Object o) {
        // használjuk az előzőleg létrehozott függvényt, azoknak a listázására amivel itt dolgozni kell
        List<Field> fieldList = getAtLeastTenCharstMegsértőAdattagok(o);

        for (Field field : fieldList) {
            try {
                String value = (String) field.get(field);
                int length = value.length();
                //megnézzük hányszor nagyobb kell hogy legyen, hogy 10től hosszabb legyen
                int mult = Math.floorDiv(10, length);
                for (int i = 0; i < mult; i++) {
                    value += value;
                }
                //beállítjuk a mező értékét erre
                field.set(field, value);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                throw new ReflectionException(e.getCause());
            }
        }
    }

}

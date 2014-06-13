package hu.unideb.inf.prt.levzh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MúzeumServiceImpl implements MúzeumService {

    @Override
    public int getJegyár(Kiállítás k, Személy sz) {
        int jegyár = 0;

        if (k.cím == null) {
            throw new IllegalArgumentException("k.cím = null");
        }

        if (k.cím.equals("Tér mint TÉR")) {
            if (sz.életkor < 6) {
                jegyár = 250;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 1000;
            } else {
                jegyár = 2000;
            }

            if (sz.cím.equals("Budapest")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Tolouse-Lautrec világa")) {
            if (sz.életkor < 6) {
                jegyár = 250;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 1300;
            } else {
                jegyár = 2600;
            }
            if (sz.cím.equals("Budapest")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Európai szobrászat")) {
            if (sz.életkor < 6) {
                jegyár = 250;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 900;
            } else {
                jegyár = 1800;
            }

            if (sz.cím.equals("Budapest")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Szabadkéz")) {
            if (sz.életkor < 6) {
                jegyár = 0;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 600;
            } else {
                jegyár = 1000;
            }
            if (sz.cím.equals("Debrecen")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Alföld")) {
            if (sz.életkor < 6) {
                jegyár = 0;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 750;
            } else {
                jegyár = 1200;
            }
            if (sz.cím.equals("Debrecen")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Antal-Lusztig gyűjtemény")) {
            if (sz.életkor < 6) {
                jegyár = 0;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 500;
            } else {
                jegyár = 1000;
            }
            if (sz.cím.equals("Debrecen")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Micimackó hátsó fele")) {
            if (sz.életkor < 6) {
                jegyár = 0;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 250;
            } else {
                jegyár = 500;
            }
            if (sz.cím.equals("Százholdas Pagony")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("Féltve őrzött répagyűjtemény")) {
            if (sz.életkor < 6) {
                jegyár = 0;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 250;
            } else {
                jegyár = 500;
            }
            if (sz.cím.equals("Százholdas Pagony")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        } else if (k.cím.equals("South Park")) {
            if (sz.életkor < 6) {
                jegyár = 0;
            } else if (sz.státusz == Személy.Státusz.DIÁK || sz.státusz == Személy.Státusz.NYUGDÍJAS) {
                jegyár = 250;
            } else {
                jegyár = 500;
            }
            if (sz.cím.equals("Százholdas Pagony")) {
                jegyár = 5 * (Math.round((jegyár * (2 / 3)) / 5));
            }
        }

        return jegyár;
    }

    @Override
    public boolean vanIdőAKiállításra(Múzeum m, Kiállítás k, String belépésiIdő) {
        boolean ret = false;

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date inDate = null;
        Date endDate = null;
        try {
            inDate = sdf.parse(belépésiIdő);
            endDate = sdf.parse(m.zárás);
        } catch (ParseException ex) {
            Logger.getLogger(MúzeumServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        cal.setTime(inDate);
        int in = cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE);
        cal.setTime(endDate);
        int end = cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE);
        int must = k.megtekintés * (3 / 4);

        if (Math.round((end - in)) > must) {
            ret = true;
        }

        return ret;
    }

    @Override
    public List<Kiállítás> továbbiKiállítások(Múzeum m, Kiállítás k, String belépésiIdő) {
        List<Kiállítás> ret = Collections.emptyList();

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date inDate = null;
        try {
            inDate = sdf.parse(belépésiIdő);
        } catch (ParseException ex) {
            Logger.getLogger(MúzeumServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        cal.setTime(inDate);
        int in = cal.get(Calendar.HOUR) + cal.get(Calendar.MINUTE);
        int anotherIn = in + k.megtekintés;
        // másodperc stringgé
        int hour = anotherIn / 60;
        int min = anotherIn % 60;
        for (Kiállítás kiállítás : m.kiállítások) {
            if (vanIdőAKiállításra(m, kiállítás, hour + ":" + min)) {
                ret.add(kiállítás);
            }
        }
        return ret;
    }
}

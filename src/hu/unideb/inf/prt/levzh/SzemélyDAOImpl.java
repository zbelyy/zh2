package hu.unideb.inf.prt.levzh;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import static java.util.Calendar.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SzemélyDAOImpl implements SzemélyDAO {

    String filename = "people.xml";

    public SzemélyDAOImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public Személy getSzemélyById(String id) {
        try {
            // xml fájl beolvasása Document elembe
            File fXmlFile = new File(this.filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            //lekérjük a személy tag-ek listáját
            NodeList nList = doc.getElementsByTagName("személy");

            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                String value = element.getAttribute("id");
                if (value.equals(id)) {
                    Személy személy = new Személy(value);
                    NodeList list = element.getChildNodes();
                    for (int j = 0; j < list.getLength(); j++) {
                        Node lmnt = list.item(j);
                        switch (lmnt.getNodeName()) {
                            case "cím":
                                személy.cím = lmnt.getTextContent();
                                break;
                            case "keresztnév":
                                személy.keresztnév = lmnt.getTextContent();
                                break;
                            case "vezetéknév":
                                személy.vezetéknév = lmnt.getTextContent();
                                break;
                            case "születésidátum":
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                                Date now = new Date();
                                Date birth = sdf.parse(lmnt.getTextContent());
                                Calendar a = Calendar.getInstance();
                                a.setTime(birth);

                                Calendar b = Calendar.getInstance();
                                b.setTime(now);

                                int életkor = b.get(YEAR) - a.get(YEAR);
                                if (a.get(MONTH) > b.get(MONTH)
                                        || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
                                    életkor--;
                                }

                                személy.életkor = életkor;
                                if (személy.életkor < 6) {
                                    személy.státusz = Személy.Státusz.GYERMEK;
                                } else {
                                    személy.státusz = Személy.Státusz.FELNŐTT;
                                }
                                break;
                            case "diákigazolvány":
                                személy.státusz = Személy.Státusz.DIÁK;
                                break;
                            case "nyugdíjasigazolvány":
                                személy.státusz = Személy.Státusz.NYUGDÍJAS;
                                break;
                        }
                    }
                    if (személy.cím != null) {
                        return személy;
                    }
                }

            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(SzemélyDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new NoSuchElementException();
    }

    @Override
    public List<Személy> getÖsszesSzemélyAdottStátusszal(String státusz
    ) {
        List<Személy> returnList = Collections.emptyList();

        try {

            File fXmlFile = new File(this.filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("személy");

            for (int i = 0; i < nList.getLength(); i++) {

                Element element = (Element) nList.item(i);
                String value = element.getAttribute("id");
                Személy személy = new Személy(value);

                NodeList list = element.getChildNodes();
                for (int j = 0; j < list.getLength(); j++) {
                    Element lmnt = (Element) list.item(i);
                    switch (lmnt.getNodeName()) {
                        case "cím":
                            személy.cím = lmnt.getTextContent();
                            break;
                        case "keresztnév":
                            személy.keresztnév = lmnt.getTextContent();
                            break;
                        case "vezetéknév":
                            személy.vezetéknév = lmnt.getTextContent();
                            break;
                        case "születésidátum":
                            Date now = new Date();

                            Long életkor = Date.parse(now.toString()) - Date.parse(lmnt.getTextContent());
                            személy.életkor = életkor.intValue();
                            if (személy.életkor < 6) {
                                személy.státusz = Személy.Státusz.GYERMEK;
                            } else {
                                személy.státusz = Személy.Státusz.FELNŐTT;
                            }
                            break;
                        case "diákigazolvány":
                            személy.státusz = Személy.Státusz.DIÁK;
                            break;
                        case "nyugdíjasigazolvány":
                            személy.státusz = Személy.Státusz.NYUGDÍJAS;
                            break;
                    }
                }

                if (személy.státusz.toString().equals(státusz)) {
                    returnList.add(személy);
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        }

        return returnList;
    }

}

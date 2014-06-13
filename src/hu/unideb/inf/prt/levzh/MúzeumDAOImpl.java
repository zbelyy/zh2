/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.unideb.inf.prt.levzh;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author hallgato
 */
public class MúzeumDAOImpl implements MúzeumDAO {
    
    String filename = "museums.xml";
    
    public MúzeumDAOImpl(String filename) {
        this.filename = filename;
    }
    
    @Override
    public Kiállítás getKiállításByNév(String kiállításnév) {
        try {
            File fXmlFile = new File(this.filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("kiállítás");
            // kiállítások
            for (int i = 0; i < nList.getLength(); i++) {
                Element element = (Element) nList.item(i);
                String id = element.getAttribute("id");
                Kiállítás kiall = new Kiállítás(id);
                NodeList list = element.getChildNodes();
                for (int j = 0; j < list.getLength(); j++) {
                    Node lmnt = list.item(j);
                    if (lmnt.getNodeName().equals("cím")) {
                        if (lmnt.getTextContent().equals(kiállításnév)) {
                            kiall.cím = lmnt.getTextContent();
                        }
                    } else if (lmnt.getNodeName().equals("megtekintés")) {
                        String megtekintés = lmnt.getTextContent();
                        kiall.megtekintés = Integer.parseInt(megtekintés);
                    }
                }
                if (kiall.cím != null) {
                    return kiall;
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        }
        throw new NoSuchElementException();
    }
    
    @Override
    public List<Kiállítás> getÖsszesKiállításAdottMúzeumból(String múzeumnév
    ) {
        List<Kiállítás> retList = Collections.emptyList();
        
        try {
            File fXmlFile = new File(this.filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("múzeum");
            
            for (int i = 0; i < nList.getLength(); i++) {
                
                Element element = (Element) nList.item(i);
                
                String value = element.getAttribute("id");
                Kiállítás kiall = new Kiállítás(null);
                NodeList list = element.getChildNodes();
                for (int j = 0; j < list.getLength(); j++) {
                    Element lmnt = (Element) list.item(i);
                    if (lmnt.getNodeName().equals("név")) {
                        if (!lmnt.getTextContent().equals(múzeumnév)) {
                            break;
                        }
                    } else if (lmnt.getNodeName().equals("kiállítás")) {
                        NodeList nlist = lmnt.getChildNodes();
                        String nvalue = lmnt.getAttribute("id");
                        kiall.id = nvalue;
                        for (int k = 0; k < nlist.getLength(); k++) {
                            Element lmnt2 = (Element) nlist.item(k);
                            if (lmnt2.getNodeName().equals("cím")) {
                                kiall.cím = lmnt2.getTextContent();
                            } else if (lmnt2.getNodeName().equals("megtekintés")) {
                                kiall.megtekintés = Integer.parseInt(lmnt2.getTextContent());
                            }
                        }
                        retList.add(kiall);
                    }
                }
            }
        } catch (IOException | NumberFormatException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        }
        
        return retList;
    }
    
}

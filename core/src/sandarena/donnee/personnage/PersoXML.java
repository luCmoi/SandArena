package sandarena.donnee.personnage;

import com.badlogic.gdx.Gdx;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by lucmo on 17/09/2015.
 */
public class PersoXML {

    public static void parsePersoXML() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        //domFactory.setValidating(true);
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(Gdx.files.internal("XML/personnage.xml").read());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element racine = doc.getDocumentElement();
        NodeList racineNoeuds = racine.getChildNodes();
        for (int i = 0; i < racineNoeuds.getLength(); i++) {
            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                parsePersonnage((Element) racineNoeuds.item(i));
            }
        }
    }

    private static void parsePersonnage(Element racine) {
        int id = parseIntStatic(racine.getAttribute("id"));
        String nom = racine.getElementsByTagName("nom").item(0).getTextContent();
        String description = racine.getElementsByTagName("description").item(0).getTextContent();
        String chemin = racine.getElementsByTagName("image").item(0).getTextContent();
        Element caract = (Element)racine.getElementsByTagName("caract").item(0);
        int vie = parseIntStatic(caract.getAttribute("vie"));
        int vitesse = parseIntStatic(caract.getAttribute("vitesse"));
        int force = parseIntStatic(caract.getAttribute("force"));
        int agilite = parseIntStatic(caract.getAttribute("agilite"));
        int magie = parseIntStatic(caract.getAttribute("magie"));
        ArrayList<Byte> affinite = new ArrayList<Byte>();
        NodeList tmp = racine.getElementsByTagName("affinite");
        for (int i = 0; i < tmp.getLength(); i++) {
            if (tmp.item(i).getNodeType() == Node.ELEMENT_NODE) {
                affinite.add((byte)parseIntStatic(tmp.item(i).getTextContent()));
            }
        }
        ArrayList<String> surnom = new ArrayList<String>();
        tmp = racine.getElementsByTagName("surnom");
        for (int i = 0; i < tmp.getLength(); i++) {
            if (tmp.item(i).getNodeType() == Node.ELEMENT_NODE) {
                surnom.add((tmp.item(i).getTextContent()));
            }
        }
        sandarena.donnee.personnage.BanquePersonnage.banque.add(new sandarena.donnee.personnage.BanquePersonnage.DonneePersonnage(id, nom, description, chemin, vie,vitesse,force,agilite,magie, affinite));
    }

    private static int parseIntStatic(String mess) {
        if (mess.equals("affforce")) return 0;
        else if (mess.equals("afftribal")) return 1;
        else if (mess.equals("affagilite")) return 2;
        else if (mess.equals("affpoison")) return 3;
        else if (mess.equals("affmagie"))return 4;
        else {
            return Integer.parseInt(mess);
        }
    }
}

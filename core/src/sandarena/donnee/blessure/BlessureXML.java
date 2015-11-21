package sandarena.donnee.blessure;

import com.badlogic.gdx.Gdx;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import sandarena.joueur.blessure.Blessure;

/**
 * Created by lucmo on 20/11/2015.
 */
public class BlessureXML {

    public static void parseBlessureXML() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        //domFactory.setValidating(true);
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(Gdx.files.internal("XML/blessure.xml").read());
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
                parseBlessure((Element) racineNoeuds.item(i));
            }
        }
    }

    private static void parseBlessure(Element racine) {
        int id = parseIntStatic(racine.getAttribute("id"));
        int force = parseIntStatic(racine.getAttribute("force"));
        int agilite = parseIntStatic(racine.getAttribute("agilite"));
        int magie = parseIntStatic(racine.getAttribute("magie"));
        int vitesse = parseIntStatic(racine.getAttribute("vitesse"));
        int vie = parseIntStatic(racine.getAttribute("vie"));
        int competence = parseIntStatic(racine.getAttribute("competence"));
        String nom = racine.getElementsByTagName("nom").item(0).getTextContent();
        String description = null;
        //String description = racine.getElementsByTagName("description").item(0).getTextContent();
        String chemin = racine.getElementsByTagName("image").item(0).getTextContent();
        Blessure blessure = null;
        sandarena.donnee.blessure.BanqueBlessure.banque.add(new sandarena.donnee.blessure.BanqueBlessure.DonneeBlessure(id, nom, description, chemin, force, agilite, magie, vie, vitesse, competence));
    }

    private static int parseIntStatic(String mess) {
        return Integer.parseInt(mess);
    }
}


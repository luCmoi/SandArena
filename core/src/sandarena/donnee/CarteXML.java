package sandarena.donnee;

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
 * Created by lucmo on 21/09/2015.
 */
public class CarteXML {
    public static void parseCarteXML() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        //domFactory.setValidating(true);
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(Gdx.files.internal("XML/carte.xml").read());
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
                parseCarte((Element) racineNoeuds.item(i));
            }
        }
    }

    private static void parseCarte(Element racine) {
        int id = parseIntStatic(racine.getAttribute("id"));
        int type = parseIntStatic(racine.getAttribute("type"));
        int taillex = parseIntStatic(racine.getAttribute("taillex"));
        int tailley = parseIntStatic(racine.getAttribute("tailley"));
        int nbperso = parseIntStatic(racine.getAttribute("nbperso"));
        //String nom = racine.getElementsByTagName("nom").item(0).getTextContent();
        //String description = racine.getElementsByTagName("description").item(0).getTextContent();
        //String chemin = racine.getElementsByTagName("image").item(0).getTextContent();
        ArrayList<CaseSpeciale> emplacementJoueurActif = new ArrayList<CaseSpeciale>();
        ArrayList<CaseSpeciale> emplacementJoueurAutre = new ArrayList<CaseSpeciale>();
        ArrayList<CaseSpeciale> special = new ArrayList<CaseSpeciale>();
        NodeList tmp = racine.getElementsByTagName("casestatic");
        for (int i = 0; i < tmp.getLength(); i++) {
            if (tmp.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element tmp2 = (Element)tmp.item(i);
                special.add(new CaseSpeciale(false, parseIntStatic(tmp2.getAttribute("type")),parseIntStatic(tmp2.getAttribute("x")), parseIntStatic(tmp2.getAttribute("y"))));
            }
        }
        tmp = racine.getElementsByTagName("caseemplacement");
        for (int i = 0; i < tmp.getLength(); i++) {
            if (tmp.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element tmp2 = (Element)tmp.item(i);
                if (parseIntStatic(tmp2.getAttribute("joueur")) == 0){
                    emplacementJoueurActif.add(new CaseSpeciale(true, -1,parseIntStatic(tmp2.getAttribute("x")), parseIntStatic(tmp2.getAttribute("y"))));
                }else{
                    emplacementJoueurAutre.add(new CaseSpeciale(true, -1,parseIntStatic(tmp2.getAttribute("x")), parseIntStatic(tmp2.getAttribute("y"))));
                }
            }
        }
        BanqueCarte.banque.add(new BanqueCarte.DonneCarte(id,null,null,null,type,taillex,tailley,nbperso,special,emplacementJoueurActif,emplacementJoueurAutre));
    }

    public static int parseIntStatic(String mess) {
        if (mess.equals("sable")) return 0;
        else {
            return Integer.parseInt(mess);
        }
    }

}

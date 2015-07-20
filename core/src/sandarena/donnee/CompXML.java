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

import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.passive.CompetenceBuff;

/**
 * Created by Guillaume on 20/07/2015.
 */
public class CompXML {

    public static void parseCompXML() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setValidating(true);
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(Gdx.files.internal("XML/competence.xml").file());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Element racine = doc.getDocumentElement();
        NodeList racineNoeuds = racine.getChildNodes();
        for (int i = 0; i<racineNoeuds.getLength(); i++) {
            if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                parseCompetence((Element)racineNoeuds.item(i));
            }
        }
    }

    private static void parseCompetence(Element racine) {
        String nom =racine.getElementsByTagName("nom").item(0).getTextContent();
        String description = racine.getElementsByTagName("description").item(0).getTextContent();
        String chemin = racine.getElementsByTagName("image").item(0).getTextContent();
        Competence competence = null;
        Element comp = null;
        //compbuff
        if (racine.getElementsByTagName("compbuff").getLength()!=0){
            comp =(Element) racine.getElementsByTagName("compbuff").item(0);
            int type = Integer.parseInt(comp.getAttribute("type"));
            int valeur = Integer.parseInt(comp.getAttribute("valeur"));
            competence = new CompetenceBuff(type, valeur);
        }
        //compdeclencheureffet
        if (racine.getElementsByTagName("compdeclencheureffet").getLength()!=0){
            comp =(Element) racine.getElementsByTagName("compdeclencheureffet").item(0);
            int type = Integer.parseInt(comp.getAttribute("type"));
            int valeur = Integer.parseInt(comp.getAttribute("valeur"));
            competence = new CompetenceBuff(type, valeur);
        }
        //compattaque
        //compbuffactif
        //compdispel
        Competence tmpSucc = competence;
        while (comp.getElementsByTagName("compbuff").getLength()!=0){
            comp = (Element)comp.getElementsByTagName("nom").item(0);
            int type = Integer.parseInt(comp.getAttribute("type"));
            int valeur = Integer.parseInt(comp.getAttribute("valeur"));
            tmpSucc.setSucc(new CompetenceBuff(type, valeur));
            tmpSucc = tmpSucc.getSucc();
        }
        ArrayList<Integer>affinite = new ArrayList<Integer>();
        Element listeAffi = (Element)racine.getElementsByTagName("affinite").item(0);
        for (int i = 0; i < listeAffi.getChildNodes().getLength(); i++) {
            Node tmp = listeAffi.getChildNodes().item(i);
            if (tmp.getNodeType() == Node.ELEMENT_NODE){
                affinite.add(Integer.parseInt(tmp.getTextContent()));
            }
        }
        BanqueCompetence.banque.add(new BanqueCompetence.EntreeCompetence(nom,description,chemin,competence,affinite));
    }
}

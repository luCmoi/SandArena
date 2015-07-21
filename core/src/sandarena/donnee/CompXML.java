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
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.active.CompetenceDispel;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;

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
        for (int i = 0; i < racineNoeuds.getLength(); i++) {
            if (racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                parseCompetence((Element) racineNoeuds.item(i));
            }
        }
    }

    private static void parseCompetence(Element racine) {
        String nom = racine.getElementsByTagName("nom").item(0).getTextContent();
        String description = racine.getElementsByTagName("description").item(0).getTextContent();
        String chemin = racine.getElementsByTagName("image").item(0).getTextContent();
        Competence competence = null;
        Element comp = null;
        if (racine.getElementsByTagName("compdeclencheureffet").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compdeclencheureffet").item(0);
            competence = compDeclencheurEffet(comp);
            if (comp.getElementsByTagName("compbuff").getLength() != 0) {
                comp  = (Element) comp.getElementsByTagName("compbuff").item(0);
            } else {
                comp  = (Element) comp.getElementsByTagName("compattaque").item(0);
            }
        }
        else if (racine.getElementsByTagName("compbuffactif").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compbuffactif").item(0);
            competence = competenceBuffActif(comp);
        }
        else if (racine.getElementsByTagName("compdispel").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compdispel").item(0);
            competence = compDispel(comp);
        }
        else if (racine.getElementsByTagName("compattaque").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compattaque").item(0);
            competence = compAttaque(comp);
        }
        else if (racine.getElementsByTagName("compbuff").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compbuff").item(0);
            competence = compBuff(comp);
        }
        Competence tmpSucc = competence;
        while (comp.getElementsByTagName("compbuff").getLength() != 0) {
            comp = (Element) comp.getElementsByTagName("compbuff").item(0);
            tmpSucc.setSucc(compBuff(comp));
            tmpSucc = tmpSucc.getSucc();
        }
        ArrayList<Integer> affinite = new ArrayList<Integer>();
        Element listeAffi = (Element) racine.getElementsByTagName("affinite").item(0);
        for (int i = 0; i < listeAffi.getChildNodes().getLength(); i++) {
            Node tmp = listeAffi.getChildNodes().item(i);
            if (tmp.getNodeType() == Node.ELEMENT_NODE) {
                affinite.add(Integer.parseInt(tmp.getTextContent()));
            }
        }
        BanqueCompetence.banque.add(new BanqueCompetence.EntreeCompetence(nom, description, chemin, competence, affinite));
    }

    private static CompetenceBuff compBuff(Element comp) {
        int type = Integer.parseInt(comp.getAttribute("type"));
        int valeur = Integer.parseInt(comp.getAttribute("valeur"));
        int condduree=-1;
        int condtype=-1;
        if (comp.getElementsByTagName("condtype").getLength() != 0) {
            condtype = Integer.parseInt(comp.getElementsByTagName("condtype").item(0).getTextContent());
        }
        if (comp.getElementsByTagName("condduree").getLength() != 0) {
            condduree = Integer.parseInt(comp.getElementsByTagName("condduree").item(0).getTextContent());
        }
        return new CompetenceBuff(type, valeur,condtype,condduree);
    }

    private static CompetenceDeclencheurEffet compDeclencheurEffet(Element comp) {
        int typeDeclencheur = Integer.parseInt(comp.getAttribute("typedeclencheur"));
        int cible = Integer.parseInt(comp.getAttribute("cible"));
        Competence succ = null;
        if (comp.getElementsByTagName("compbuff").getLength() != 0) {
            succ = compBuff((Element) comp.getElementsByTagName("compbuff").item(0));
        } else {
            succ = compAttaque((Element) comp.getElementsByTagName("compattaque").item(0));
        }
        return new CompetenceDeclencheurEffet(typeDeclencheur, cible, succ);
    }

    private static CompetenceAttaque compAttaque(Element comp) {
        CompLance attaque = compLance((Element) comp.getElementsByTagName("complance").item(0));
        int bonus = Integer.parseInt(comp.getAttribute("bonus"));
        return new CompetenceAttaque(attaque, bonus);
    }

    private static CompetenceBuffActif competenceBuffActif(Element comp) {
        CompLance attaque = compLance((Element) comp.getElementsByTagName("complance").item(0));
        return new CompetenceBuffActif(attaque);
    }

    private static CompetenceDispel compDispel(Element comp) {
        CompLance attaque = compLance((Element) comp.getElementsByTagName("complance").item(0));
        boolean cible = Boolean.parseBoolean(comp.getAttribute("cible"));
        int nombre = Integer.parseInt(comp.getAttribute("nombre"));
        return new CompetenceDispel(attaque, cible, nombre);
    }

    private static CompLance compLance(Element lance) {
        return new CompLance(Integer.parseInt(lance.getAttribute("recharge")),
                Integer.parseInt(lance.getAttribute("utilisation")),
                Integer.parseInt(lance.getAttribute("porte")),
                Integer.parseInt(lance.getAttribute("portemin")),
                Integer.parseInt(lance.getAttribute("zone")),
                Integer.parseInt(lance.getAttribute("caract")));
    }

    public static class CompLance {
        public int recharge;
        public int utilisation;
        public int porte;
        public int portemin;
        public int zone;
        public int caract;

        public CompLance(int recharge, int utilisation, int porte, int portemin, int zone, int caract) {
            this.recharge = recharge;
            this.utilisation = utilisation;
            this.porte = porte;
            this.portemin = portemin;
            this.zone = zone;
            this.caract = caract;
        }
    }
}

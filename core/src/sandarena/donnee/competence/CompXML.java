package sandarena.donnee.competence;

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
        //domFactory.setValidating(true);
        DocumentBuilder builder = null;
        Document doc = null;
        try {
            builder = domFactory.newDocumentBuilder();
            doc = builder.parse(Gdx.files.internal("XML/competence.xml").read());
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
        int id = parseIntStatic(racine.getAttribute("id"));
        String nom = racine.getElementsByTagName("nom").item(0).getTextContent();
        String description = racine.getElementsByTagName("description").item(0).getTextContent();
        String chemin = racine.getElementsByTagName("image").item(0).getTextContent();
        Competence competence = null;
        Element comp = null;
        if (racine.getElementsByTagName("compdeclencheureffet").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compdeclencheureffet").item(0);
            competence = compDeclencheurEffet(comp);
            if (comp.getElementsByTagName("compbuff").getLength() != 0) {
                comp = (Element) comp.getElementsByTagName("compbuff").item(0);
            } else {
                comp = (Element) comp.getElementsByTagName("compattaque").item(0);
            }
        } else if (racine.getElementsByTagName("compbuffactif").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compbuffactif").item(0);
            competence = competenceBuffActif(comp);
        } else if (racine.getElementsByTagName("compdispel").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compdispel").item(0);
            competence = compDispel(comp);
        } else if (racine.getElementsByTagName("compattaque").getLength() != 0) {
            comp = (Element) racine.getElementsByTagName("compattaque").item(0);
            competence = compAttaque(comp);
        } else if (racine.getElementsByTagName("compbuff").getLength() != 0) {
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
                affinite.add(parseIntStatic(tmp.getTextContent()));
            }
        }
        sandarena.donnee.competence.BanqueCompetence.banque.add(new sandarena.donnee.competence.BanqueCompetence.EntreeCompetence(id, nom, description, chemin, competence, affinite));
    }

    private static CompetenceBuff compBuff(Element comp) {
        int type = parseIntStatic(comp.getAttribute("type"));
        int valeur = parseIntStatic(comp.getAttribute("valeur"));
        int condduree = -1;
        int condtype = -1;
        if (comp.getElementsByTagName("condtype").getLength() != 0) {
            condtype = parseIntStatic(comp.getElementsByTagName("condtype").item(0).getTextContent());
        }
        if (comp.getElementsByTagName("condduree").getLength() != 0) {
            condduree = Integer.parseInt(comp.getElementsByTagName("condduree").item(0).getTextContent());
        }
        return new CompetenceBuff((byte)type, (byte)valeur, (byte)condtype,(byte) condduree);
    }

    private static CompetenceDeclencheurEffet compDeclencheurEffet(Element comp) {
        int typeDeclencheur = parseIntStatic(comp.getAttribute("typedeclencheur"));
        int cible = parseIntStatic(comp.getAttribute("cible"));
        Competence succ = null;
        if (comp.getElementsByTagName("compbuff").getLength() != 0) {
            succ = compBuff((Element) comp.getElementsByTagName("compbuff").item(0));
        } else {
            succ = compAttaque((Element) comp.getElementsByTagName("compattaque").item(0));
        }
        return new CompetenceDeclencheurEffet((byte)typeDeclencheur,(byte) cible, succ);
    }

    private static CompetenceAttaque compAttaque(Element comp) {
        CompLance attaque = compLance((Element) comp.getElementsByTagName("complance").item(0));
        int bonus = Integer.parseInt(comp.getAttribute("bonus"));
        return new CompetenceAttaque(attaque,(byte) bonus);
    }

    private static CompetenceBuffActif competenceBuffActif(Element comp) {
        CompLance attaque = compLance((Element) comp.getElementsByTagName("complance").item(0));
        return new CompetenceBuffActif(attaque);
    }

    private static CompetenceDispel compDispel(Element comp) {
        CompLance attaque = compLance((Element) comp.getElementsByTagName("complance").item(0));
        boolean cible = Boolean.parseBoolean(comp.getAttribute("cible"));
        int nombre = Integer.parseInt(comp.getAttribute("nombre"));
        return new CompetenceDispel(attaque, cible, (byte)nombre);
    }

    private static CompLance compLance(Element lance) {
        return new CompLance(Integer.parseInt(lance.getAttribute("recharge")),
                Integer.parseInt(lance.getAttribute("utilisation")),
                Integer.parseInt(lance.getAttribute("porte")),
                Integer.parseInt(lance.getAttribute("portemin")),
                Integer.parseInt(lance.getAttribute("zone")),
                parseIntStatic(lance.getAttribute("caract")));
    }

    public static class CompLance {
        public byte recharge;
        public byte utilisation;
        public byte porte;
        public byte portemin;
        public byte zone;
        public byte caract;

        public CompLance(int recharge, int utilisation, int porte, int portemin, int zone, int caract) {
            this.recharge = (byte)recharge;
            this.utilisation = (byte)utilisation;
            this.porte = (byte)porte;
            this.portemin = (byte)portemin;
            this.zone = (byte)zone;
            this.caract = (byte)caract;
        }
    }

    public static int parseIntStatic(String mess) {
        if (mess.equals("affforce")) return 0;
        else if (mess.equals("afftribal")) return 1;
        else if (mess.equals("affagilite")) return 2;
        else if (mess.equals("affpoison")) return 3;
        else if (mess.equals("affmagie"))return 4;
        else if (mess.equals("attaquebase")) return -1;

        else if (mess.equals("bufftypeattaque")) return 0;
        else if (mess.equals("bufftypedefense")) return 1;
        else if (mess.equals("buffdot")) return 4;
        else if (mess.equals("buffvalvitesse")) return 5;
        else if (mess.equals("buffvalattaque")) return 6;
        else if (mess.equals("buffdegat")) return 7;
        else if (mess.equals("buffstun")) return 8;
        else if (mess.equals("buffvaldefense")) return 9;
        else if (mess.equals("buffval")) return 10;

        else if (mess.equals("caractforce")) return 0;
        else if (mess.equals("caractagilite")) return 1;
        else if (mess.equals("caractmagie")) return 2;

        else if (mess.equals("declenchedegatrecu")) return 0;

        else if (mess.equals("ciblesoi")) return 0;
        else if (mess.equals("cibledeclenche"))return 1;

        else {
            return Integer.parseInt(mess);
        }
    }
}

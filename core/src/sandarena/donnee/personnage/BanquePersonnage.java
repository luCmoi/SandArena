package sandarena.donnee.personnage;

import java.util.ArrayList;
import java.util.Collections;

import sandarena.donnee.banque.Banque;
import sandarena.donnee.banque.Entree;

/**
 * Banque de donnée qui stock les personnages
 *
 * @author Guillaume
 */
public class BanquePersonnage extends Banque {

    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }

    /**
     * Retourne un nom propre aléatoire en fonction du personnage passer en
     * argument
     *
     * @param nom
     * @return
     */
    public static String getNom(String nom) {
        return "";
    }

    public static DonneePersonnage getPersonnage() {
        ArrayList<Entree> tmp = (ArrayList<Entree>)banque.clone();
        Collections.shuffle(tmp);
        DonneePersonnage retour = (DonneePersonnage)tmp.get(0);
        tmp.clear();
        tmp=null;
        return retour;
    }

    public static class DonneePersonnage extends Entree{

        public byte vie;
        public byte vitesse;
        public byte force;
        public byte magie;
        public byte agilite;
        public ArrayList<Byte> affinite;

        public DonneePersonnage(int id, String nom, String description, String chemin, int vie, int vitesse, int force, int agilite, int magie, ArrayList<Byte> affinite) {
            super(id, nom, description, chemin);
            this.vie = (byte)vie;
            this.vitesse = (byte)vitesse;
            this.agilite = (byte)agilite;
            this.magie = (byte)magie;
            this.force = (byte)force;
            this.affinite = affinite;
        }
    }
}

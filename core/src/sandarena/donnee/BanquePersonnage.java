package sandarena.donnee;

import java.util.ArrayList;

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
     * Initialise tous les personnages du jeu
     */
    public static void init() {
        banque.add(new DonneePersonnage("Barbare des Sables",
                "Ces hommes vivaient dans le desert avant que les Arènes ne s'y établissent, certains racontent qu'ils sont nés sur ces terres attiré par la soif de combats",
                "Image/Personnage/BarbareDesSables.png",
                15, 4, 5, 2, 2, Affinite.FORCE,Affinite.TRIBAL));
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

    public static class DonneePersonnage extends Entree {

        public int vie;
        public int vitesse;
        public int force;
        public int magie;
        public int agilite;
        public int[] affinite;

        public DonneePersonnage(String nom, String description, String chemin, int vie, int vitesse, int force, int magie, int agilite, int... affinite) {
            super(nom, description, chemin);
            this.vie = vie;
            this.vitesse = vitesse;
            this.agilite = agilite;
            this.magie = magie;
            this.force = force;
            this.affinite = affinite;
        }
    }
}

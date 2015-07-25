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
                "Ces hommes vivaient dans le desert avant que les Arènes ne s'y établissent, certains racontent qu'ils sont à l'origine des premières arènes du désert",
                "Image/Personnage/BarbareDesSables.png",
                15, 3, 5, 2, 2, Affinite.FORCE,Affinite.TRIBAL));
        banque.add(new DonneePersonnage("Sauvageon des Sables",
                "Personne ne sait s'il sont de la même espèce que les barbares qu'ils semblent servire où s'ils sont entrés en symbiose avec leur communautée",
                "Image/Personnage/SauvageonDesSables.png",
                10, 4, 1, 6, 3,Affinite.AGILITE, Affinite.POISON, Affinite.TRIBAL));
        banque.add(new DonneePersonnage("Guetteur",
                "Créature des profondeurs trouvées dans des cavernes abandonnées il faut de nombreux traqueur expérimentés pour en ramener un dans l'arène",
                "Image/Personnage/Guetteur.png",
                13, 4, 2, 2, 5, Affinite.POISON/*Affinite.MAGIE*/));
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

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
        banque.add(new DonneePersonnage(1001,"Barbare des Sables",
                "Ces hommes vivaient dans le desert avant que les Arènes ne s'y établissent, certains racontent qu'ils sont à l'origine des premières arènes du désert",
                "Image/Personnage/BarbareDesSables.png",
                15, 3, 5, 2, 2, Affinite.FORCE,Affinite.TRIBAL));
        banque.add(new DonneePersonnage(1002,"Sauvageon des Sables",
                "Personne ne sait s'il sont de la même espèce que les barbares qu'ils semblent servire où s'ils sont entrés en symbiose avec leur communautée",
                "Image/Personnage/SauvageonDesSables.png",
                10, 4, 1, 6, 3,Affinite.AGILITE, Affinite.POISON, Affinite.TRIBAL));
        banque.add(new DonneePersonnage(1003,"Guetteur",
                "Créature des profondeurs trouvées dans des cavernes abandonnées il faut de nombreux traqueur expérimentés pour en ramener un dans l'arène",
                "Image/Personnage/Guetteur.png",
                13, 4, 2, 2, 5, Affinite.POISON,Affinite.MAGIE));
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

    public static class DonneePersonnage extends Entree{

        public byte vie;
        public byte vitesse;
        public byte force;
        public byte magie;
        public byte agilite;
        public byte[] affinite;

        public DonneePersonnage(int id, String nom, String description, String chemin, int vie, int vitesse, int force, int agilite, int magie, byte... affinite) {
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

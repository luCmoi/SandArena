package sandarena.donnee;

import java.util.ArrayList;

/**
 * Banque de donnée qui stock les sols
 *
 * @author Guillaume
 */
public class BanqueSol extends Banque {

    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }

    /**
     * Initialise tous les sols du jeu
     */
    public static void init() {
        banque.add(new EntreeSol("Sable",
                "Le sol d'arène le plus classique possible.",
                "Image/Sol/Sable.png"));
    }

    public static class EntreeSol extends Entree {

        public EntreeSol(String nom, String description, String chemin) {
            super(nom, description, chemin);
        }
    }
}

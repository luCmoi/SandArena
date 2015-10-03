package sandarena.donnee.sol;

import java.util.ArrayList;

/**
 * Banque de donnée qui stock les sols
 *
 * @author Guillaume
 */
public class BanqueSol extends sandarena.donnee.banque.Banque {

    public static ArrayList<sandarena.donnee.banque.Entree> banque = new ArrayList<sandarena.donnee.banque.Entree>();

    public static ArrayList<sandarena.donnee.banque.Entree> getBanque() {
        return banque;
    }

    /**
     * Initialise tous les sols du jeu
     */
    public static void init() {
        banque.add(new EntreeSol(4001,"Sable",
                "Le sol d'arène le plus classique possible.",
                "Image/Sol/Sable.png"));
    }

    public static class EntreeSol extends sandarena.donnee.banque.Entree {

        public EntreeSol(int id, String nom, String description, String chemin) {
            super(id, nom, description, chemin);
        }
    }
}


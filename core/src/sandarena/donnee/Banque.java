package sandarena.donnee;

import java.util.ArrayList;

/**
 * La Banque est une classe abstraite qui sert aux differentes banques de donnees
 * afin de gerer les differentes fonctions de base.
 *
 * @author Guillaume
 */
public abstract class Banque {

    /**
     * Retourne une entree a l'aide de son nom et previent l'entree qu'elle possede une instance
     * supplémentaire.
     *
     * @param banque
     * @param nom
     * @return Entree
     */
    public static Entree getEntree(ArrayList<Entree> banque, String nom) {
        for (Entree e : banque) {
            if (e.nom.equals(nom)) {
                e.incremente();
                return e;
            }
        }
        return null;
    }

    public static Entree getEntree(ArrayList<Entree> banque, int id) {
        for (Entree e : banque) {
            if (e.getId() == id) {
                e.incremente();
                return e;
            }
        }
        return null;
    }

    /**
     * Previent l'entree qu'on lui retire une instance
     *
     * @param banque
     * @param nom
     */
    public static void giveEntree(ArrayList<Entree> banque, String nom) {
        for (Entree e : banque) {
            if (e.nom.equals(nom)) {
                e.decremente();
            }
        }
    }

    public static void dispose(ArrayList<Entree> banque) {
        for (Entree e : banque) {
            e.dispose();
            banque.remove(e);
        }
    }
}
    
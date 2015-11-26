package sandarena.donnee.banque;

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
                return e;
            }
        }
        return null;
    }

    public static Entree getEntree(ArrayList<Entree> banque, int id) {
        for (Entree e : banque) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public static void dispose(ArrayList<Entree> banque) {
        for (Entree e : banque) {
            e.dispose();
            banque.remove(e);
        }
    }
}
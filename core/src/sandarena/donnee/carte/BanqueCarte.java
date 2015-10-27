package sandarena.donnee.carte;

import java.util.ArrayList;
import java.util.Collections;

import sandarena.donnee.banque.Banque;
import sandarena.donnee.banque.Entree;

/**
 * Created by lucmo on 21/09/2015.
 */
public class BanqueCarte extends Banque {

    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }

    public static int getRandom() {
        ArrayList<Entree> tmp = (ArrayList<Entree>)banque.clone();
        Collections.shuffle(tmp);
        int retour = ((DonneCarte)tmp.get(0)).getId();
        tmp.clear();
        tmp=null;
        return retour;
    }


    public static class DonneCarte extends Entree{

        public byte sol;
        public ArrayList<CaseSpeciale> special;
        public ArrayList<CaseSpeciale> zoneDepartActif;
        public ArrayList<CaseSpeciale> zoneDepartAutre;
        public byte nbPerso;
        public byte x;
        public byte y;


        public DonneCarte(int id, String nom, String description, String chemin, int sol,int x, int y,int nbPerso, ArrayList<CaseSpeciale> special,ArrayList<CaseSpeciale> zoneDepartActif,ArrayList<CaseSpeciale> zoneDepartAutre ) {
            super(id, nom, description, chemin);
            this.sol = (byte)sol;
            this.x = (byte)x;
            this.y = (byte)y;
            this.nbPerso = (byte)nbPerso;
            this.special = special;
            this.zoneDepartActif = zoneDepartActif;
            this.zoneDepartAutre = zoneDepartAutre;
        }
    }
}

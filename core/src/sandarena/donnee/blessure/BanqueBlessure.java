package sandarena.donnee.blessure;

import java.util.ArrayList;
import java.util.Collections;

import sandarena.donnee.banque.Banque;
import sandarena.donnee.banque.Entree;
import sandarena.joueur.blessure.Blessure;

/**
 * Created by lucmo on 19/11/2015.
 */
public class BanqueBlessure extends Banque {
    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }

    public static DonneeBlessure getBlessure(Blessure[] dejaAcquise) {
        ArrayList<Entree> tmpBanque = (ArrayList<Entree>) banque.clone();
        Collections.shuffle(tmpBanque);
        for (Blessure c : dejaAcquise) {
            if (c != null) {
                tmpBanque.remove(c.donnee);
            }
        }
        tmpBanque.add(null);
        for (Entree e : tmpBanque) {
            tmpBanque.clear();
            return (DonneeBlessure) e;
        }
        return null;
    }

public static class DonneeBlessure extends Entree {
    public int force;
    public int agilite;
    public int magie;
    public int vitesse;
    public int vie;
    public int comp;

    public DonneeBlessure(int id, String nom, String description, String chemin, int force, int agilite, int magie, int vie, int vitesse, int comp) {
        super(id, nom, description, chemin);
        this.force = force;
        this.agilite = agilite;
        this.magie = magie;
        this.vie = vie;
        this.vitesse = vitesse;
        this.comp = comp;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
}

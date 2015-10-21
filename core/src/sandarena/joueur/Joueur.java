package sandarena.joueur;

import java.util.ArrayList;

public class Joueur {
    private ArrayList<Personnage> personnages = new ArrayList();
    private int numero;
    private int or;

    public Joueur(int numero) {
        this.numero = numero;

    }

    public int getNumero() {
        return numero;
    }

    public void dispose() {
        for (Personnage perso : personnages) {
            perso.dispose();
        }
        personnages.clear();
        personnages = null;
    }

    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }
}

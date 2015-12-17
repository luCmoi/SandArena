package sandarena.joueur.blessure;

import java.util.ArrayList;

import sandarena.donnee.blessure.BanqueBlessure;

/**
 * Created by lucmo on 19/11/2015.
 */
public class Blessure {
    public BanqueBlessure.DonneeBlessure donnee;

    public Blessure(BanqueBlessure.DonneeBlessure donnee) {
        this.donnee = donnee;
    }

    public String toString() {
        return null;
    }

    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        if (donnee.vie != 0) {
            retour.add("Reduit la vie maximum de ".concat(String.valueOf(donnee.vie)));
        }
        if (donnee.vitesse != 0) {
            retour.add("Reduit la vitesse maximum de ".concat(String.valueOf(donnee.vitesse)));
        }
        if (donnee.force != 0) {
            retour.add("Reduit la force de ".concat(String.valueOf(donnee.force)));
        }
        if (donnee.agilite != 0) {
            retour.add("Reduit l'agilité de ".concat(String.valueOf(donnee.agilite)));
        }
        if (donnee.magie != 0) {
            retour.add("Reduit la magie de ".concat(String.valueOf(donnee.magie)));
        }
        return retour;
    }

    public void checkString(String[] texte) {
        if (donnee.vie != 0) {
            texte[1] = texte[1].concat(String.valueOf(donnee.vie));
        }
        if (donnee.vitesse != 0) {
            texte[2] = texte[2].concat(String.valueOf(donnee.vitesse));
        }
        if (donnee.force != 0) {
            texte[3] = texte[3].concat(String.valueOf(donnee.force));
        }
        if (donnee.agilite != 0) {
            texte[4] = texte[4].concat(String.valueOf(donnee.agilite));
        }
        if (donnee.magie != 0) {
            texte[5] = texte[5].concat(String.valueOf(donnee.magie));
        }
    }


}

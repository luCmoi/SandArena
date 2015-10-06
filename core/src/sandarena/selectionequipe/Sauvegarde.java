package sandarena.selectionequipe;

import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 06/10/2015.
 */
public class Sauvegarde {
    public static String toSnapshotName(int i) {
        switch (i){
            case 0:
                return "snapSand-0";
            case 3:
                return "snapSand-3";
            case 2:
                return "snapSand-2";
        }
        return null;
    }

    public static String toData(Joueur equipe) {
        String retour = "v0001";
        for (Personnage perso : equipe.getPersonnages()){
            retour.concat(String.valueOf(perso.getId()));
            for (int i = 0; i < 4; i++) {
                retour.concat(String.valueOf(perso.getCompetences()[i].getId()));
            }
        }
        return retour;
    }
}

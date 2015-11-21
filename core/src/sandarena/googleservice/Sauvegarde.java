package sandarena.googleservice;

import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 06/10/2015.
 */
public class Sauvegarde {
    public static String toSnapshotName(int i) {
        switch (i) {
            case 0:
                return "snapSand-0";
            case 1:
                return "snapSand-1";
            case 2:
                return "snapSand-2";
        }
        return null;
    }

    public static String toData(Joueur equipe) {
        String retour = "v005";
        if (equipe != null) {
            for (Personnage perso : equipe.getPersonnages()) {
                retour = retour.concat(String.valueOf(perso.getId()));
                for (int i = 0; i < 4; i++) {
                    retour = retour.concat(String.valueOf(perso.getCompetences()[i].getId()));
                }
                for (int i = 0; i < 4; i++) {
                    if (perso.getBlessures()[i]!=null){
                        retour = retour.concat(String.valueOf(perso.getBlessures()[i].donnee.getId()));
                    }
                }
            }
            retour = retour.concat("o");
            retour = retour.concat(toOr(String.valueOf(equipe.getOr())));
        }
        return retour;
    }

    private static String toOr(String s) {
        while(s.length() < 4){
            s = "0".concat(s);
        }
        return s;
    }
}

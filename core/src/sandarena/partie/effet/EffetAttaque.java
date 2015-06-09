package sandarena.partie.effet;

import sandarena.donnee.Caract;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.partie.Case;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.PersonnageIG;

/**
 * @author Guillaume
 */
public class EffetAttaque {
    public int type;
    public double mul = 1;

    public EffetAttaque(int type) {
        this.type = type;
    }

    public EffetAttaque(CompetenceAttaque comp) {
        this.type = comp.getCaract();
        this.mul = comp.getMultiAttaque();
    }

    public void lance(Case attaquant, Case defenseur) {
       /*Rajouter les check de tous les effets sur la case receuveuse et celle qui envoi
        ainsi que les check de buff de personnage qui pourront modifier leffet ou autre
        */
        if (defenseur.getPresence() != null) {
            int att = 0;
            int def = 0;
            switch (type) {
                case (Caract.FORCE):
                    att=attaquant.getPresence().getDonnee().commun.force;
                    def=defenseur.getPresence().getDonnee().commun.force;
                    break;
                case (Caract.AGILITE):
                    att=attaquant.getPresence().getDonnee().commun.agilite;
                    def=defenseur.getPresence().getDonnee().commun.agilite;
                    break;
                case (Caract.MAGIE):
                    att=attaquant.getPresence().getDonnee().commun.magie;
                    def=defenseur.getPresence().getDonnee().commun.magie;
                    break;
            }
            att = attaquant.getPresence().modifAttaque(att,type);
            def = defenseur.getPresence().modifDefense(def,type);
            defenseur.getPresence().inflige(degat(att,def));
        }
    }

    public int degat(int caractA, int caractD) {
        return 100;
        //defenseur.getPresence().setVieActuelle(
    }
}

package sandarena.partie.compcase;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;
import sandarena.partie.AlgorithmePathfinding;
import sandarena.partie.Case;
import sandarena.partie.effet.EffetAttaque;
import sandarena.partie.effet.EffetBuf;

/**
 * Une instance de compétence en Partie
 *
 * @author Guillaume
 */
public class CompetenceIG {
    public EntreeCompetence info;
    private PersonnageIG container;
    private int recharge = 0;
    private int utilisationRestante = -1;
    private boolean active = true;

    CompetenceIG(PersonnageIG perso, EntreeCompetence c) {
        container = perso;
        info = c;
        if (c.competence instanceof CompetenceBuff) {
            perso.addBuf(new EffetBuf(((CompetenceBuff) c.competence).getTypeBuff(), ((CompetenceBuff) c.competence).getVal(), ((CompetenceBuff) c.competence).getDonnee()));
        } else if (c.competence instanceof CompetenceDeclencheurEffet) {
            perso.addDeclencheur(((CompetenceDeclencheurEffet) c.competence).enJeu());
        }
        if (c.competence instanceof CompetenceActive){
            if (((CompetenceActive)c.competence).getUtilisation()!=0){
                this.utilisationRestante = ((CompetenceActive)c.competence).getUtilisation();
            }
        }
    }

    public void dispose() {
        this.info = null;
        this.container = null;
    }

    public void select(Case[][] plateau) {
        AlgorithmePathfinding.calculCaseTouchable(((CompetenceActive) info.competence).getPortemin(), ((CompetenceActive) info.competence).getPorte(), this.container.getContainer(), plateau);
    }

    public void agit(Case aCase) {
        if (aCase.getPresence() != null) {
            if (info.competence instanceof CompetenceAttaque) {
                new EffetAttaque((CompetenceAttaque) this.info.competence).lance(this.container.getContainer(), aCase);
            } else if (info.competence instanceof CompetenceBuffActif) {
                aCase.getPresence().addBuf(new EffetBuf(((CompetenceBuffActif) info.competence).getTypeBuff(), ((CompetenceBuffActif) info.competence).getVal(), ((CompetenceBuffActif) info.competence).getDonnee()));
            }
            if (((CompetenceActive)info.competence).getUtilisation()!=0){
                this.utilisationRestante = this.getUtilisationRestante() - 1;
                if (this.getUtilisationRestante() == 0){
                    active = false;
                }
            }
            if (((CompetenceActive)info.competence).getRechargement()!=0){
                this.recharge = ((CompetenceActive)info.competence).getRechargement();
                active = false;
            }
            this.container.setAAgi(true);
        }
    }

    public String[] toStrings() {
        Object[] tmp = info.competence.toStrings().toArray();
        String[] retour = new String[tmp.length + 1];
        retour[0] = info.nom;
        for (int i = 1; i < retour.length; i++) {
            retour[i] = (String) (tmp[i - 1]);
        }
        return retour;
    }

    public void tour(){
        if (getRecharge() != 0){
            recharge = getRecharge() - 1;
            if (getRecharge() == 0){
                active = true;
            }
        }
    }

    public int getRecharge() {
        return recharge;
    }

    public int getUtilisationRestante() {
        return utilisationRestante;
    }

    public boolean isActive() {
        return active;
    }
}

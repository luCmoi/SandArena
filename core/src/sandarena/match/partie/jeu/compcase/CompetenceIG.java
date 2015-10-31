package sandarena.match.partie.jeu.compcase;

import sandarena.donnee.competence.BanqueCompetence.EntreeCompetence;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.active.CompetenceDispel;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;
import sandarena.match.partie.jeu.Case;
import sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet;
import sandarena.match.partie.jeu.compcase.effet.EffetAttaque;
import sandarena.match.partie.jeu.compcase.effet.EffetDispel;

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
    private int place;

    CompetenceIG(PersonnageIG perso, EntreeCompetence c, int place) {
        container = perso;
        info = c;
        this.place = place;
        if (c.competence instanceof CompetenceBuff) {
            perso.addBuf(CompetenceToEffet.toEffet(this), false);
        } else if (c.competence instanceof CompetenceDeclencheurEffet) {
            perso.addDeclencheur(CompetenceToEffet.toDeclencheur(this));
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
        sandarena.match.partie.AlgorithmePathfinding.calculCaseTouchable(((CompetenceActive) info.competence).getPortemin(), ((CompetenceActive) info.competence).getPorte(), this.getContainer().getContainer(), plateau);
    }

    public void agit(Case aCase) {
        if (aCase.getPresence() != null) {
            if (info.competence instanceof CompetenceAttaque) {
                new EffetAttaque((CompetenceAttaque)this.info.competence, CompetenceToEffet.toEffet(this)).lance(this.getContainer().getContainer(), aCase,true);
            } else if (info.competence instanceof CompetenceDispel) {
                new EffetDispel((CompetenceDispel) this.info.competence, CompetenceToEffet.toEffet(this)).lance(this.getContainer().getContainer(), aCase);
            }else if (info.competence instanceof CompetenceBuffActif) {
                aCase.getPresence().addBuf(CompetenceToEffet.toEffet(this), true);
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
            this.getContainer().setAAgi(true, false);
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

    private PersonnageIG getContainer() {
        return container;
    }

    public int getPlace() {
        return place;
    }

}

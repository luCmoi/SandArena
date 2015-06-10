package sandarena.partie.compcase;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.joueur.competence.active.CompetenceAttaque;
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

    CompetenceIG(PersonnageIG perso, EntreeCompetence c) {
        container = perso;
        info = c;
        if (c.competence instanceof CompetenceBuff) {
            //todo a remplir
            perso.addBuf(new EffetBuf(0, 0));
        } else if (c.competence instanceof CompetenceDeclencheurEffet) {
            perso.addDeclencheur(((CompetenceDeclencheurEffet) c.competence).enJeu());
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
        if (info.competence instanceof CompetenceAttaque) {
            new EffetAttaque((CompetenceAttaque) this.info.competence).lance(this.container.getContainer(), aCase);
        }
        this.container.setAAgi(true);
    }

    public void init() {
        if (info.competence instanceof CompetenceBuff) {

        } else if (info.competence instanceof CompetenceDeclencheurEffet) {

        }
    }
}

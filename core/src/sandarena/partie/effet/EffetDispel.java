package sandarena.partie.effet;

import sandarena.joueur.competence.active.CompetenceDispel;
import sandarena.partie.Case;

/**
 * Created by Guillaume on 18/06/2015.
 */
public class EffetDispel extends Effet {
    protected boolean cible;
    protected int nombre;
    protected EffetBuf suite;

    public EffetDispel(CompetenceDispel competence, EffetBuf suite) {
        this.cible = competence.isCible();
        this.nombre = competence.getNombre();
        this.suite = suite;
    }

    public void lance(Case lanceur, Case receveur) {
        if (cible){
            receveur.getPresence().removeBuffBenefique();
        }else{
            receveur.getPresence().removeBuffMauvais();
        }
        if (suite != null){
            receveur.getPresence().addBuf(suite, true);
        }
    }
}

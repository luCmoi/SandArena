package sandarena.joueur.competence.active;

import sandarena.joueur.competence.CompetenceActive;

/**
 *
 * @author Guillaume
 */
public class CompetenceAttaque extends CompetenceActive {

    protected int caract;
    protected double multiAttaque;
    protected double multiDefense;

    public CompetenceAttaque(int type, int recharge, int utilisation, int porte, int caract, double multiAttaque, double multiDefense) {
        super(type, recharge, utilisation, porte);
        this.caract = caract;
        this.multiAttaque = multiAttaque;
        this.multiDefense = multiDefense;
    }
}

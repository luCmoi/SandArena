package sandarena.joueur.competence.active;

import sandarena.joueur.competence.CompetenceActive;

/**
 * @author Guillaume
 */
public class CompetenceBuffActif extends CompetenceActive {

    protected int caract;
    protected double multi;

    public CompetenceBuffActif(int type, int recharge, int utilisation, int porte, int portemin, int zone, int caract, double multi) {
        super(type, recharge, utilisation, porte, portemin, zone);
        this.setCaract(caract);
        this.setMulti(multi);
    }

    public int getCaract() {
        return caract;
    }

    public void setCaract(int caract) {
        this.caract = caract;
    }

    public double getMulti() {
        return multi;
    }

    public void setMulti(double multi) {
        this.multi = multi;
    }
}

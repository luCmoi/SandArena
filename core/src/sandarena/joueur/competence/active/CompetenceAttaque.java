package sandarena.joueur.competence.active;

import sandarena.joueur.competence.CompetenceActive;

/**
 *
 * @author Guillaume
 */
public class CompetenceAttaque extends CompetenceActive {

    protected int caract;
    protected double multiAttaque;

    public CompetenceAttaque(int type, int recharge, int utilisation, int porte,int portemin,int zone, int caract, double multiAttaque ) {
        super(type, recharge, utilisation, porte,portemin, zone);
        this.setCaract(caract);
        this.setMultiAttaque(multiAttaque);
    }

    public int getCaract() {
        return caract;
    }

    public void setCaract(int caract) {
        this.caract = caract;
    }

    public double getMultiAttaque() {
        return multiAttaque;
    }

    public void setMultiAttaque(double multiAttaque) {
        this.multiAttaque = multiAttaque;
    }

}

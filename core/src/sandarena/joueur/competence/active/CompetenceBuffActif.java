package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetenceActive;

/**
 * @author Guillaume
 */
public class CompetenceBuffActif extends CompetenceActive {
    protected  String toStrings;
    protected int caract;
    //
    protected int typeBuff;
    protected int val;
    protected  double[] donnee;

    public CompetenceBuffActif(String toStrings, int type, int recharge, int utilisation, int porte, int portemin, int zone, int caract, int typeBuff, int val, double... donnee) {
        super(type, recharge, utilisation, porte, portemin, zone);
        this.setCaract(caract);
        this.toStrings=toStrings;
        this.typeBuff = typeBuff;
        this.val = val;
        this.donnee = donnee;
    }

    public int getCaract() {
        return caract;
    }

    public void setCaract(int caract) {
        this.caract = caract;
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add(this.toStrings);
        retour.addAll(super.toStrings());
        return retour;
    }
}

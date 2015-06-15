package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetenceActive;

public class CompetenceBuffActif extends CompetenceActive {
    protected  String toStrings;
    private int caract;
    //todo on gere tout d'ici
    private int typeBuff;
    private int val;
    private double[] donnee;

    public CompetenceBuffActif(String toStrings, int type, int recharge, int utilisation, int porte, int portemin, int zone, int caract, int typeBuff, int val, double... donnee) {
        super(type, recharge, utilisation, porte, portemin, zone);
        this.setCaract(caract);
        this.toStrings=toStrings;
        this.typeBuff = typeBuff;
        this.val = val;
        this.donnee = donnee;
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

    public int getTypeBuff() {
        return typeBuff;
    }

    public int getVal() {
        return val;
    }

    public double[] getDonnee() {
        return donnee;
    }
}

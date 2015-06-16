package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.effet.CompetenceToEffet;

public class CompetenceBuffActif extends CompetenceActive {
    private int caract;
    //todo on gere tout d'ici
    private int typeBuff;
    private int val;
    private double[] donnee;

    public CompetenceBuffActif(int type, int recharge, int utilisation, int porte, int portemin, int zone, int caract, int typeBuff, int val, double... donnee) {
        super(type, recharge, utilisation, porte, portemin, zone);
        this.setCaract(caract);
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
        retour.addAll(CompetenceToEffet.toStrings(this));
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

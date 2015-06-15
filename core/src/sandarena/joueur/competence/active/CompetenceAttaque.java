package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.donnee.Caract;
import sandarena.joueur.competence.CompetenceActive;

public class CompetenceAttaque extends CompetenceActive {

    protected int caract;
    protected double multiAttaque;
    protected double[] effetSpeciaux;

    public CompetenceAttaque(int type, int recharge, int utilisation, int porte, int portemin, int zone, int caract, double multiAttaque, double... effetSpeciaux) {
        super(type, recharge, utilisation, porte, portemin, zone);
        this.setCaract(caract);
        this.setMultiAttaque(multiAttaque);
        this.effetSpeciaux = effetSpeciaux;
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

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        String car="";
        switch (caract){
            case (Caract.FORCE):
                car = "Force";
                break;
            case (Caract.AGILITE):
                car = "Agilité";
                break;
            case (Caract.MAGIE):
                car = "Magie";
                break;
        }
        retour.add("Attaque de type "+car+" de puissance "+multiAttaque);
        retour.addAll(super.toStrings());
        return retour;
    }
}

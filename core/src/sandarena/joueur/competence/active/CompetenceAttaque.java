package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.donnee.Caract;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.effet.CompetenceToEffet;

public class CompetenceAttaque extends CompetenceActive {

    protected int caract;
    protected int multiAttaque;
    protected int[] donnee;

    public CompetenceAttaque(int type, int recharge, int utilisation, int porte, int portemin, int zone, int caract, int multiAttaque, int... donnee) {
        super(type, recharge, utilisation, porte, portemin, zone);
        this.setCaract(caract);
        this.setMultiAttaque(multiAttaque);
        if (donnee.length > 0) {
            this.donnee = donnee;
        }
    }

    public int getCaract() {
        return caract;
    }

    public void setCaract(int caract) {
        this.caract = caract;
    }

    public int getMultiAttaque() {
        return multiAttaque;
    }

    public void setMultiAttaque(int multiAttaque) {
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
        retour.add("Attaque de type " + car);
        retour.add(" De puissance " + multiAttaque);
        if (donnee != null){
            int[] donneetmp = null;
            if (donnee.length > 3) {
                donneetmp = new int[donnee.length-3];
                for (int i = 0; i < donnee.length-3; i++) {
                    donneetmp[i]=donnee[i+3];
                }
            }
            retour.addAll(CompetenceToEffet.switchTypeBuff(donnee[1], donnee[2],donneetmp));
        }
        retour.addAll(super.toStrings());
        return retour;
    }

    public int[] getDonnee() {
        return donnee;
    }
}

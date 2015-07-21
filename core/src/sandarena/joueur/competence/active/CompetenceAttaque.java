package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.donnee.Caract;
import sandarena.donnee.CompXML;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.effet.CompetenceToEffet;

public class CompetenceAttaque extends CompetenceActive {

    protected int caract;
    protected int multiAttaque;

    public CompetenceAttaque(CompXML.CompLance attaque, int bonus) {
        super(attaque);
        this.multiAttaque = bonus;
    }

    public int getCaract() {
        return caract;
    }

    public int getMultiAttaque() {
        return multiAttaque;
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
        if (succ != null){
            retour.addAll(CompetenceToEffet.toStrings(succ));
        }
        retour.addAll(super.toStrings());
        return retour;
    }

}

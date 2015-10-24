package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.donnee.donneestatic.Caract;
import sandarena.donnee.competence.CompXML;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet;

public class CompetenceAttaque extends CompetenceActive {

    protected byte caract;
    protected byte multiAttaque;

    public CompetenceAttaque(CompXML.CompLance attaque, byte bonus) {
        super(attaque);
        this.caract = attaque.caract;
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
                car = "Agilit�";
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

package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.donnee.CompXML;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.effet.CompetenceToEffet;

/**
 * Created by Guillaume on 18/06/2015.
 */
public class CompetenceDispel extends CompetenceActive {
    private boolean cible;
    private int nombre;
    protected int[] donnee = null;

    public CompetenceDispel( int recharge, int utilisation, int porte, int portemin, int zone,boolean cible, int nombre, int... donnee) {
        super( recharge, utilisation, porte, portemin, zone);
        this.cible = cible;
        this.nombre = nombre;
        if (donnee.length > 0) {
            this.donnee = donnee;
        }
    }

    public CompetenceDispel(CompXML.CompLance attaque, boolean cible, int nombre){
        super(attaque);
        this.cible = cible;
        this.nombre = nombre;
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        String tmp = null;
        if (isCible()){
            tmp = "bénéfique";
        }else{
            tmp = "maléfique";
        }
        retour.add("Dissipie "+ getNombre() +" état "+tmp);
        if (donnee != null){
            int[] donneetmp = null;
            if (donnee.length > 3) {
                donneetmp = new int[donnee.length-3];
                for (int i = 0; i < donnee.length-3; i++) {
                    donneetmp[i]=donnee[i+3];
                }
            }
            retour.addAll(CompetenceToEffet.switchTypeBuff(donnee[1], donnee[2], donneetmp));
        }
        retour.addAll(super.toStrings());
        return retour;
    }

    public int[] getDonnee() {
        return donnee;
    }

    public boolean isCible() {
        return cible;
    }

    public int getNombre() {
        return nombre;
    }
}

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
    private byte nombre;

    public CompetenceDispel(CompXML.CompLance attaque, boolean cible, byte nombre) {
        super(attaque);
        this.cible = cible;
        this.nombre = nombre;
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        String tmp = null;
        if (isCible()) {
            tmp = "b�n�fique";
        } else {
            tmp = "mal�fique";
        }
        retour.add("Dissipie " + getNombre() + " �tat " + tmp);
        if (succ != null) {
            retour.addAll(CompetenceToEffet.toStrings(succ));
        }
        retour.addAll(super.toStrings());
        return retour;
    }


    public boolean isCible() {
        return cible;
    }

    public int getNombre() {
        return nombre;
    }
}

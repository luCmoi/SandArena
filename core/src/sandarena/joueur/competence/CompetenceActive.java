package sandarena.joueur.competence;

import java.util.ArrayList;

import sandarena.donnee.CompXML;
import sandarena.joueur.competence.active.CompetenceAttaque;

public abstract class CompetenceActive extends Competence {
    protected int rechargement;
    protected int utilisation;
    protected int porte;
    protected int portemin;
    private int zone;

    public CompetenceActive( int recharge, int utilisation, int porte, int portemin, int zone) {
        this.rechargement=recharge;
        this.utilisation=utilisation;
        this.porte=porte;
        this.portemin=portemin;
        this.zone=zone;
    }

    public CompetenceActive(CompXML.CompLance compLance){
        this.rechargement=compLance.recharge;
        this.utilisation=compLance.utilisation;
        this.porte=compLance.porte;
        this.portemin=compLance.portemin;
        this.zone=compLance.zone;
    }

    public int getRechargement() {
        return rechargement;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public int getPorte() {
        return porte;
    }

    public int getPortemin() {
        return portemin;
    }

    public int getZone() {
        return zone;
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        if (rechargement != 0) {
            retour.add("Rechargement : " + rechargement);
        }
        if (porte != 0) {
            retour.add("Portée : " + porte);
        }else {
            retour.add("Portée : soi");
        }
        if (portemin != 1 && portemin != 0) {
            retour.add("Portée minimale : " + portemin);
        }
        if (zone != 1) {
            retour.add("Portée de zone : " + portemin);
        } if (utilisation != 0){
            retour.add("Utilisation : "+utilisation);
        }
        return retour;
    }
}

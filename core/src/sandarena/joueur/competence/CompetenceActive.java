package sandarena.joueur.competence;

import java.util.ArrayList;

import sandarena.donnee.competence.CompXML;

public abstract class CompetenceActive extends Competence {
    private byte rechargement;
    private byte utilisation;
    private byte porte;
    private byte portemin;
    private byte zone;

    protected CompetenceActive(byte recharge, byte utilisation, byte porte, byte portemin, byte zone) {
        this.rechargement=recharge;
        this.utilisation=utilisation;
        this.porte=porte;
        this.portemin=portemin;
        this.zone=zone;
    }

    protected CompetenceActive(CompXML.CompLance compLance){
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
            retour.add("Port�e : " + porte);
        }else {
            retour.add("Port�e : soi");
        }
        if (portemin != 1 && portemin != 0) {
            retour.add("Port�e minimale : " + portemin);
        }
        if (zone != 1) {
            retour.add("Port�e de zone : " + portemin);
        } if (utilisation != 0){
            retour.add("Utilisation : "+utilisation);
        }
        return retour;
    }
}

package sandarena.joueur.competence;

import java.util.ArrayList;

public abstract class CompetenceActive extends Competence {
    protected int rechargement;
    protected int utilisation;
    protected int porte;
    protected int portemin;
    private int zone;

    public CompetenceActive(int type, int recharge, int utilisation, int porte, int portemin, int zone) {
        super(type);
        this.rechargement=recharge;
        this.utilisation=utilisation;
        this.porte=porte;
        this.portemin=portemin;
        this.zone=zone;
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

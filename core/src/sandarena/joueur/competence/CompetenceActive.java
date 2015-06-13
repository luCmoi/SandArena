package sandarena.joueur.competence;

import java.util.ArrayList;

/**
 * @author Guillaume
 */
public abstract class CompetenceActive extends Competence {
    protected int rechargement;
    protected int utilisation;
    protected int porte;
    protected int portemin;
    private int zone;

    public CompetenceActive(int type, int recharge, int utilisation, int porte, int portemin, int zone) {
        super(type);
        this.setRechargement(recharge);
        this.setUtilisation(utilisation);
        this.setPorte(porte);
        this.setPortemin(portemin);
        this.setZone(zone);
    }

    public int getRechargement() {
        return rechargement;
    }

    public void setRechargement(int rechargement) {
        this.rechargement = rechargement;
    }

    public int getUtilisation() {
        return utilisation;
    }

    public void setUtilisation(int utilisation) {
        this.utilisation = utilisation;
    }

    public int getPorte() {
        return porte;
    }

    public void setPorte(int porte) {
        this.porte = porte;
    }

    public int getPortemin() {
        return portemin;
    }

    public void setPortemin(int portemin) {
        this.portemin = portemin;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    @Override
    public ArrayList<String> toStrings() {
        ArrayList<String> retour = new ArrayList<String>();
        if (rechargement != 0) {
            retour.add("Rechargement : " + rechargement);
        }
        if (porte != 0) {
            retour.add("Portée : " + porte);
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

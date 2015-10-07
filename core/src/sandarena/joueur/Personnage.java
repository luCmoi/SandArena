package sandarena.joueur;

import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.competence.BanqueCompetence.EntreeCompetence;
import sandarena.donnee.personnage.BanquePersonnage;
import sandarena.donnee.personnage.BanquePersonnage.DonneePersonnage;

/**
 * Represente les donnee fixes d'un personnage
 *
 */
public class Personnage{

    public DonneePersonnage commun;
    private String nom;
    private EntreeCompetence[] competences = new EntreeCompetence[4];


    public Personnage(int id){
        this.commun = (DonneePersonnage) BanquePersonnage.getEntree(BanquePersonnage.banque, id);
        commun.incremente();
        this.nom = BanquePersonnage.getNom(commun.nom);
    }

    public Personnage(String nom) {
        this.commun = (DonneePersonnage) BanquePersonnage.getEntree(BanquePersonnage.banque, nom);
        this.nom = BanquePersonnage.getNom(nom);
        for (int i = 0; i < 4; i++) {
            int tmp = (int)(Math.random()*commun.affinite.size());
            this.competences[i] = BanqueCompetence.getCompetence(commun.affinite.get(tmp), competences);
            if (this.competences[i]!=null) {
                this.competences[i].incremente();
            }
        }
    }

    public Personnage() {
        this.commun = BanquePersonnage.getPersonnage();
        this.nom = BanquePersonnage.getNom(commun.nom);
        for (int i = 0; i < 4; i++) {
            int tmp = (int)(Math.random()*commun.affinite.size());
            this.competences[i] = BanqueCompetence.getCompetence(commun.affinite.get(tmp), competences);
            if (this.competences[i]!=null) {
                this.competences[i].incremente();
            }
        }
    }

    public int getId(){
        return commun.getId();
    }

    public void dispose() {
        commun.decremente();
        this.commun = null;
        for (EntreeCompetence c : getCompetences()) {
            if (c != null) {
                c.decremente();
                c = null;
            }
        }
        competences = null;
        nom = null;
    }

    public String getNom() {
        return "Fred";
        //todo return nom;
    }

    public EntreeCompetence[] getCompetences() {
        return competences;
    }

    public void addCompetence(EntreeCompetence compTmp) {
        for (int i = 0; i < 4; i++) {
            if (competences[i]== null){
                competences[i] = compTmp;
                break;
            }
        }
    }
}

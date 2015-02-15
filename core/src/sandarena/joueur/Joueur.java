package sandarena.joueur;

import java.util.ArrayList;
import sandarena.partie.compcase.PersonnageIG;

public class Joueur {
    private ArrayList<Personnage> personnages = new ArrayList();
    private ArrayList<PersonnageIG> personnagesIG = new ArrayList();
    
    public Joueur(){
        
    }
    
    public void setEnJeu(ArrayList<Personnage> selection){
        for(Personnage select : selection){
            personnagesIG.add(new PersonnageIG(select));
        }
    }
    
    public void setFinJeu(){
        for(PersonnageIG perso : personnagesIG){
            perso.dispose();
        }
        personnagesIG.clear();
    }
    
    public void dispose(){
        for(Personnage perso : personnages){
            perso.dispose();
        }
        personnages.clear();
    }
    
    public ArrayList<Personnage> getPersonnages() {
        return personnages;
    }

    public void setPersonnages(ArrayList<Personnage> personnages) {
        this.personnages = personnages;
    }

    public ArrayList<PersonnageIG> getPersonnagesIG() {
        return personnagesIG;
    }

    public void setPersonnagesIG(ArrayList<PersonnageIG> personnagesIG) {
        this.personnagesIG = personnagesIG;
    }
}

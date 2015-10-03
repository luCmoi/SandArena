package sandarena.partie.jeu.compcase.effet.effetdeclencheur;

import sandarena.partie.jeu.compcase.effet.Effet;
import sandarena.partie.jeu.compcase.effet.EffetDeclencheur;

/**
 * Created by Guillaume on 17/06/2015.
 */
//Todo conditions
public class EffetDeclencheurDegatRecu extends EffetDeclencheur {
    private int minimum;

    public EffetDeclencheurDegatRecu(int cible, Effet chaine) {
        super(cible, chaine);
    }

    public void check(int degat){
        launch(null);
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}

package sandarena.match.partie.jeu.compcase.effet.effetdeclencheur;

/**
 * Created by Guillaume on 17/06/2015.
 */
//Todo conditions
public class EffetDeclencheurDegatRecu extends sandarena.match.partie.jeu.compcase.effet.EffetDeclencheur {
    private int minimum;

    public EffetDeclencheurDegatRecu(int cible, sandarena.match.partie.jeu.compcase.effet.Effet chaine) {
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

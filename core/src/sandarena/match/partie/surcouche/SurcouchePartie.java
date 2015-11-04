package sandarena.match.partie.surcouche;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.match.commun.Surcouche;
import sandarena.match.partie.ScreenPartie;

/**
 * Created by lucmo on 02/11/2015.
 */
public class SurcouchePartie extends Surcouche {
    private Victoire victoire;
    private Defaite defaite;

    public SurcouchePartie(ScreenPartie container, FillViewport fillViewport, Batch batch) {
        super(container, fillViewport, batch);
        victoire = new Victoire(this);
        defaite = new Defaite(this);
        this.addActor(victoire);
        this.addActor(defaite);
    }

    public void victoire() {
        this.setVisible(true);
        victoire.setVisible(true);
    }

    public void defaite() {
        this.setVisible(true);
        defaite.setVisible(true);
    }

    public void dispose(){
        super.dispose();
        victoire.dispose();
        defaite.dispose();
        victoire = null;
        defaite = null;
    }
}

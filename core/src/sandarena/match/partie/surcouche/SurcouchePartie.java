package sandarena.match.partie.surcouche;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.match.commun.Surcouche;
import sandarena.match.partie.ScreenPartie;

/**
 * Created by lucmo on 02/11/2015.
 */
public class SurcouchePartie extends Surcouche {
    private Fin fin;

    public SurcouchePartie(ScreenPartie container, FillViewport fillViewport, Batch batch) {
        super(container, fillViewport, batch);
    }

    public void victoire() {
        this.setVisible(true);
        fin = new Fin(this,true);
        this.addActor(fin);
        fin.setVisible(true);
    }

    public void defaite() {
        this.setVisible(true);
        fin = new Fin(this,false);
        this.addActor(fin);
        fin.setVisible(true);
    }

    public void dispose(){
        super.dispose();
        if (fin!= null) {
            fin.dispose();
            fin = null;
        }
    }
}

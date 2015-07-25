package sandarena.preparematch;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sandarena.joueur.Joueur;


/**
 * Created by Guillaume on 23/07/2015.
 */
public class StageBarre extends Stage {
    private final StagePrincipalScreenPrepa principal;
    private final Joueur joueur;
    private CameraBarre camera;
    private float widthTailleTotale;

    public StageBarre(StagePrincipalScreenPrepa principal, Joueur joueur, ExtendViewport extendViewport, Batch batch) {
        super(extendViewport,batch);
        this.principal = principal;
        this.joueur = joueur;
        this.getViewport().setCamera(new CameraBarre(this));
        this.camera = (CameraBarre) (this.getViewport().getCamera());
    }

    public float getWidthTailleTotale() {
        return widthTailleTotale;
    }

    public void gauche() {
        this.camera.gauche();
    }

    public void droite() {
        this.camera.droite();
    }
}

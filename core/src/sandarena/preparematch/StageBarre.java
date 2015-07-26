package sandarena.preparematch;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;


/**
 * Created by Guillaume on 23/07/2015.
 */
public class StageBarre extends Stage {
    private final StagePrincipalScreenPrepa principal;
    private final Joueur joueur;
    private CameraBarre camera;
    private float widthTailleTotale;
    private ArrayList<UnitBarre> persos;


    public StageBarre(StagePrincipalScreenPrepa principal, Joueur joueur, ExtendViewport extendViewport, Batch batch) {
        super(extendViewport,batch);
        this.principal = principal;
        this.joueur = joueur;
        this.getViewport().setCamera(new CameraBarre(this));
        this.camera = (CameraBarre) (this.getViewport().getCamera());
        this.widthTailleTotale = Resolution.differenceBas*joueur.getPersonnages().size();
        int x = 0;
        persos = new ArrayList<UnitBarre>();
        for (Personnage perso : joueur.getPersonnages()){
            persos.add(new UnitBarre(this, x ,perso));
            this.addActor(persos.get(x));
            x ++;
        }
    }

    public void augmenteWidthTailleTotale(int place){
        this.widthTailleTotale = this.widthTailleTotale+Resolution.differenceBas;
        for (int i = place+1; i < persos.size(); i++) {
            persos.get(i).decaleDroite();
        }
    }

    public void diminueWidthTailleTotale(int place){
        this.widthTailleTotale = this.widthTailleTotale-Resolution.differenceBas;
        for (int i = place+1; i < persos.size(); i++) {
            persos.get(i).decaleGauche();
        }
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

    public StagePrincipalScreenPrepa getPrincipal() {
        return principal;
    }

}

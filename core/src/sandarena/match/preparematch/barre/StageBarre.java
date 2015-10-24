package sandarena.match.preparematch.barre;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.match.preparematch.barre.emplacement.EmplacementBarre;
import sandarena.match.preparematch.stageprincipal.StagePrincipalScreenPrepa;


/**
 * Created by Guillaume on 23/07/2015.
 */
public class StageBarre extends Stage {
    private final StagePrincipalScreenPrepa principal;
    private final Joueur joueur;
    private CameraBarre camera;
    private float widthTailleTotale;
    private ArrayList<EmplacementBarre> persos;
    private sandarena.match.preparematch.barre.Fond fond;


    public StageBarre(StagePrincipalScreenPrepa principal, Joueur joueur, ExtendViewport extendViewport, Batch batch) {
        super(extendViewport, batch);
        this.principal = principal;
        this.joueur = joueur;
        this.getViewport().setCamera(new CameraBarre(this));
        this.camera = (CameraBarre) (this.getViewport().getCamera());
        this.widthTailleTotale = Resolution.differenceBas * joueur.getPersonnages().size();
        int x = 0;
        fond = new sandarena.match.preparematch.barre.Fond(this);
        this.addActor(fond);
        persos = new ArrayList<EmplacementBarre>();
        for (Personnage perso : joueur.getPersonnages()) {
            persos.add(new EmplacementBarre(this, x, perso));
            this.addActor(persos.get(x));
            x++;
        }

    }

    public void augmenteWidthTailleTotale(int place) {
        this.widthTailleTotale = this.widthTailleTotale + Resolution.differenceBas;
        for (int i = place + 1; i < persos.size(); i++) {
            persos.get(i).decaleDroite();
        }
    }

    public void diminueWidthTailleTotale(int place, boolean baisse) {
        this.widthTailleTotale = this.widthTailleTotale - Resolution.differenceBas;
        for (int i = place + 1; i < persos.size(); i++) {
            persos.get(i).decaleGauche();
            if (baisse) {
                persos.get(i).setPlace(persos.get(i).getPlace() - 1);
            }
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

    public void stop() {
        this.camera.stop();
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        stageX = stageX + camera.getX();
        return super.hit(stageX, stageY, touchable);
    }

    public ArrayList<EmplacementBarre> getPersos() {
        return persos;
    }

    public void select(EmplacementBarre selected) {
        if (!principal.getBloquand()) {
            Personnage tmp = selected.getPerso();
            this.diminueWidthTailleTotale(selected.getPlace(), true);
            selected.dispose();
            principal.ajoutGauche(tmp);
        }
    }

    @Override
    public void draw() {
        super.draw();
    }
}
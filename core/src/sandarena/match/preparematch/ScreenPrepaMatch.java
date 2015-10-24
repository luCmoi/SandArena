package sandarena.match.preparematch;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.ArrayList;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.googleservice.IGoogleService;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.match.commun.Surcouche;
import sandarena.match.preparematch.stageprincipal.StagePrincipalScreenPrepa;


/**
 * Created by Guillaume on 20/07/2015.
 */
public class ScreenPrepaMatch implements Screen {
    private Batch batch;
    private SandArena container;
    private sandarena.match.preparematch.barre.StageBarre barre;
    private StagePrincipalScreenPrepa principal;
    private Surcouche surcouche;
    private Joueur joueur;
    private Personnage check = null;
    private int map;

    public ScreenPrepaMatch(SandArena conteneur, int map, Joueur equipe) {
        this.container = conteneur;
        this.map = map;
        this.batch = conteneur.getBatch();
        joueur = equipe;
    }

    @Override
    public void render(float delta) {
        if (!IGoogleService.data.justLeft) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glViewport(Resolution.differenceBas / 2, 0, Resolution.width - Resolution.differenceBas, Resolution.differenceBas);
            if (check != null) {
                check.commun.incremente();
                check.getCompetences()[0].incremente();
                check.getCompetences()[1].incremente();
                check.getCompetences()[2].incremente();
                check.getCompetences()[3].incremente();
                setCheck(null);
                if (principal.getCommence() && principal.testFin()) {
                    finPrepare();
                }
            }
            this.getBarre().draw();
            Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
            this.getPrincipal().draw();
            this.getSurcouche().draw();
        } else {
            container.lanceGestionEquipe(this.getJoueur());
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        boolean commence = IGoogleService.data.commence;
        setSurcouche(new Surcouche(this,new FillViewport(Resolution.width, Resolution.height), batch));
        this.setPrincipal(new StagePrincipalScreenPrepa(this, joueur, commence, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch));
        this.setBarre(new sandarena.match.preparematch.barre.StageBarre(this.getPrincipal(), joueur, new ExtendViewport(Resolution.width - Resolution.differenceBas, Resolution.differenceBas, Resolution.width - Resolution.differenceBas, Resolution.differenceBas), batch));
        getPrincipal().setBarre(this.getBarre());
        Gdx.input.setInputProcessor(this.getPrincipal());
        IGoogleService.data.lancePartie = false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public sandarena.match.preparematch.barre.StageBarre getBarre() {
        return barre;
    }

    public void setBarre(sandarena.match.preparematch.barre.StageBarre barre) {
        this.barre = barre;
    }

    public StagePrincipalScreenPrepa getPrincipal() {
        return principal;
    }

    public void setPrincipal(StagePrincipalScreenPrepa principal) {
        this.principal = principal;
    }

    public Surcouche getSurcouche() {
        return surcouche;
    }

    public void setSurcouche(Surcouche surcouche) {
        this.surcouche = surcouche;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void finPrepare() {
        ArrayList<Personnage> personnagesActif = principal.getPersonnagesActif();
        ArrayList<Personnage> personnagesAutre = principal.getPersonnagesAutre();
        IGoogleService.data.time = null;
        container.lancePartie(joueur, personnagesActif, personnagesAutre, !(principal.getCommence()), map);
    }

    public void setCheck(Personnage check) {
        this.check = check;
    }

    public Personnage getCheck() {
        return check;
    }

    public void backKeyPressed() {

    }
}
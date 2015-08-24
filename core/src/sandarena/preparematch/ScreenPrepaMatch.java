package sandarena.preparematch;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.ArrayList;

import sandarena.ConnexionMatch;
import sandarena.Resolution;
import sandarena.SandArena;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.preparematch.barre.StageBarre;
import sandarena.preparematch.stageprincipal.StagePrincipalScreenPrepa;


/**
 * Created by Guillaume on 20/07/2015.
 */
public class ScreenPrepaMatch implements Screen {
    private Batch batch;
    private SandArena container;
    private StageBarre barre;
    private StagePrincipalScreenPrepa principal;
    private Stage surcouche;
    private Joueur joueur;


    public ScreenPrepaMatch(SandArena conteneur) {
        this.container = conteneur;
        this.batch = conteneur.getBatch();
        //Temp joueur
        joueur = new Joueur();
        joueur.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur.getPersonnages().add(new Personnage("Sauvageon des Sables"));
        joueur.getPersonnages().add(new Personnage("Guetteur"));
        joueur.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur.getPersonnages().add(new Personnage("Sauvageon des Sables"));
        joueur.getPersonnages().add(new Personnage("Guetteur"));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(Resolution.differenceBas / 2, 0, Resolution.width - Resolution.differenceBas, Resolution.differenceBas);
        this.getBarre().draw();
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        this.getPrincipal().draw();
        this.getSurcouche().draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        boolean alea = (ConnexionMatch.serverSocket==null);
        this.setPrincipal(new StagePrincipalScreenPrepa(this,joueur,alea, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch));
        this.setBarre(new StageBarre(this.getPrincipal(), joueur, new ExtendViewport(Resolution.width - Resolution.differenceBas, Resolution.differenceBas, Resolution.width - Resolution.differenceBas, Resolution.differenceBas), batch));
        setSurcouche(new Stage(new FillViewport(Resolution.width, Resolution.height), batch));
        getPrincipal().setBarre(this.getBarre());
        Gdx.input.setInputProcessor(this.getPrincipal());
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

    public StageBarre getBarre() {
        return barre;
    }

    public void setBarre(StageBarre barre) {
        this.barre = barre;
    }

    public StagePrincipalScreenPrepa getPrincipal() {
        return principal;
    }

    public void setPrincipal(StagePrincipalScreenPrepa principal) {
        this.principal = principal;
    }

    public Stage getSurcouche() {
        return surcouche;
    }

    public void setSurcouche(Stage surcouche) {
        this.surcouche = surcouche;
    }

    public Joueur getJoueur(){
        return joueur;
    }

    public void finPrepare() {
        ArrayList<Personnage> personnagesActif = principal.getPersonnagesActif();
        ArrayList<Personnage> personnagesAutre = principal.getPersonnagesAutre();
        container.lancePartie(joueur, personnagesActif, personnagesAutre, !(principal.getCommence()));
    }
}

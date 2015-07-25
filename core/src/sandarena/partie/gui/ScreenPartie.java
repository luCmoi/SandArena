package sandarena.partie.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import sandarena.Resolution;
import sandarena.SandArena;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.partie.Partie;
import sandarena.partie.gui.interfacep.StageInterface;

/**
 * Screen permetant d'afficher la partie
 *
 */
public class ScreenPartie implements Screen {

    private Batch batch;
    private SandArena conteneur;
    private Partie partie;
    private StageInterface interfaceS;
    //Temporaire
    private Joueur joueur1;
    private Joueur joueur2;
    private Stage surcouche;

    public ScreenPartie(SandArena conteneur) {
        this.conteneur = conteneur;
        this.batch = conteneur.getBatch();
        //Temporaire
        joueur1 = new Joueur();
        joueur2 = new Joueur();
        joueur1.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur1.getPersonnages().add(new Personnage("Sauvageon des Sables"));
        joueur2.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur2.getPersonnages().add(new Personnage("Sauvageon des Sables"));
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.partie.getViewport().update();
        this.partie.draw();
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.differenceBas);
        this.interfaceS.draw();
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        this.surcouche.draw();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
        this.interfaceS = new sandarena.partie.gui.interfacep.StageInterface(new ExtendViewport(Resolution.width, Resolution.differenceBas, Resolution.width, Resolution.differenceBas), batch);
        this.partie = new Partie(this, joueur1, joueur2, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch);
        surcouche = new Stage(new FillViewport(Resolution.width,Resolution.height),batch);
        interfaceS.setPartie(partie);
        Gdx.input.setInputProcessor(this.partie);
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
        if (getBatch() != null) {
            getBatch().dispose();
            this.batch = null;
        }
        conteneur = null;
        partie.dispose();
        partie = null;
        interfaceS.dispose();
        interfaceS = null;
        surcouche.dispose();
        surcouche = null;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public SandArena getConteneur() {
        return conteneur;
    }

    public Partie getPartie() {
        return this.partie;
    }

    public sandarena.partie.gui.interfacep.StageInterface getStageInterface() {
        return this.interfaceS;
    }

    public Stage getSurcouche() {
        return surcouche;
    }
}

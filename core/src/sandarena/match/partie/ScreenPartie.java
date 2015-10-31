package sandarena.match.partie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.ArrayList;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.googleservice.IGoogleService;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.match.commun.Surcouche;
import sandarena.match.partie.gui.interfacep.StageInterface;
import sandarena.match.partie.jeu.Partie;

/**
 * Screen permetant d'afficher la partie
 */
public class ScreenPartie implements Screen {

    private Batch batch;
    private SandArena container;
    private Partie partie;
    private StageInterface interfaceS;
    //Temporaire
    private Joueur joueurActif;
    private Joueur joueurAutre;
    private Surcouche surcouche;
    private int map;

    public ScreenPartie(SandArena container, Joueur joueur, ArrayList<Personnage> personnagesActif, ArrayList<Personnage> personnagesAutre, boolean commence, int map) {
        this.container = container;
        this.map = map;
        this.batch = container.getBatch();
        //Temporaire
        joueurActif = joueur;
        joueurAutre = new Joueur(0);
        surcouche = new Surcouche(this, new FillViewport(Resolution.width, Resolution.height), batch);
        this.interfaceS = new StageInterface(new ExtendViewport(Resolution.width, Resolution.differenceBas, Resolution.width, Resolution.differenceBas), batch);
        this.partie = new Partie(this, joueurActif, personnagesActif, joueurAutre, personnagesAutre, commence, surcouche, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch);
        interfaceS.setPartie(partie);
        Gdx.input.setInputProcessor(this.partie);
        if (Son.actuelle != null){
            Son.actuelle.stop();
        }
        Son.nomads.setLooping(true);
        Son.nomads.setVolume(0.4f);
        Son.nomads.play();
        Son.actuelle = Son.nomads;
    }

    @Override
    public void render(float f) {
        if (!IGoogleService.data.justLeft) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            this.partie.getViewport().update();
            this.partie.draw();
            Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.differenceBas);
            this.interfaceS.draw();
            Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
            this.surcouche.draw();
        } else {
            partie.victoire();
            Son.nomads.stop();
        }
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
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
        container = null;
        partie.dispose();
        partie = null;
        interfaceS.dispose();
        interfaceS = null;
        surcouche.dispose();
        surcouche = null;
    }

    private Batch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public SandArena getContainer() {
        return container;
    }

    public Partie getPartie() {
        return this.partie;
    }

    public StageInterface getStageInterface() {
        return this.interfaceS;
    }

    public Surcouche getSurcouche() {
        return surcouche;
    }

    public int getMap() {
        return map;
    }

    public Joueur getJoueurActif() {
        return joueurActif;
    }
}


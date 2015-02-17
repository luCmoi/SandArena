package sandarena.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import sandarena.Resolution;
import sandarena.SandArena;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.partie.Partie;

/**
 * Screen permetant d'afficher la partie
 *
 * @author Guillaume
 */
public class ScreenPartie implements Screen {

    private Batch batch;
    private SandArena conteneur;
    private Camera camera;
    private Partie partie;
    private int differenceBas;
    private int xHautGauche;
    private int yHautGauche;
    private StageInterface interfaceS;
    //Temporaire
    private Joueur joueur1;
    private Joueur joueur2;

    public ScreenPartie(SandArena conteneur) {
        this.conteneur = conteneur;
        this.batch = conteneur.getBatch();
        this.differenceBas = Resolution.height / 4;
        //Temporaire
        joueur1 = new Joueur();
        joueur2 = new Joueur();
        joueur1.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur2.getPersonnages().add(new Personnage("Barbare des Sables"));
    }

    @Override
    public void render(float f) {
        //getCamera().updateExt();
        //xHautGauche = (int) (getCamera().position.x - Resolution.width / 2);
        //yHautGauche = (int) (partie.getDimMax() - Resolution.height - (getCamera().position.y - Resolution.height / 2));
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //getCamera().update();
        //getBatch().begin();
        //this.partie.render(batch);
        this.partie.draw();
        //getBatch().setProjectionMatrix(getCamera().combined);
        //getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
        setBatch(new SpriteBatch());
        //setCamera(new Camera(this));
        this.partie = new Partie(this, joueur1, joueur2,new ExtendViewport (Resolution.width, Resolution.height - this.differenceBas,Resolution.width, Resolution.height - this.differenceBas),batch);
        this.interfaceS = new StageInterface(new ExtendViewport(Resolution.width,this.differenceBas,Resolution.width,this.differenceBas),batch);
        Gdx.input.setInputProcessor(new InputMultiplexer(this.partie,this.interfaceS));
        //
        //xHautGauche = (int) (getCamera().position.x - Resolution.width / 2);
        //yHautGauche = (int) (partie.getDimMax() - Resolution.height - (getCamera().position.y - Resolution.height / 2));
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
        }
        camera.dispose();
        camera = null;
        conteneur = null;
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

    public void setConteneur(SandArena conteneur) {
        this.conteneur = conteneur;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getDifferenceBas() {
        return differenceBas;
    }

    public void setDifferenceBas(int differenceBas) {
        this.differenceBas = differenceBas;
    }

}

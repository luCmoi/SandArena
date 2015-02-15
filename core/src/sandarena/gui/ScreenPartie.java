package sandarena.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private SpriteBatch batch;
    private SandArena conteneur;
    private Camera camera;
    private Partie partie;
    private int widthTailleTotale;
    private int heightTailleTotale;
    private int differenceBas;
    private int xHautGauche;
    private int yHautGauche;
    //Temporaire
    private Joueur joueur1;
    private Joueur joueur2;

    public ScreenPartie(SandArena conteneur) {
        this.conteneur = conteneur;
        this.batch = conteneur.getBatch();
        this.differenceBas = Resolution.height / 4;
        Gdx.input.setInputProcessor(new ScreenPartieListener(this));
        //Temporaire
        joueur1 = new Joueur();
        joueur2 = new Joueur();
        joueur1.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur2.getPersonnages().add(new Personnage("Barbare des Sables"));
        joueur1.setEnJeu(joueur1.getPersonnages());
        joueur2.setEnJeu(joueur2.getPersonnages());
    }

    @Override
    public void render(float f) {
        getCamera().updateExt();
        xHautGauche = (int) (getCamera().position.x - Resolution.width / 2);
        yHautGauche = (int) (partie.getDimMax() - Resolution.height - (getCamera().position.y - Resolution.height / 2));
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getCamera().update();
        getBatch().begin();
        this.partie.render(batch);
        getBatch().setProjectionMatrix(getCamera().combined);
        getBatch().end();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
        setBatch(new SpriteBatch());
        setCamera(new Camera(this));
        this.partie = new Partie(this, joueur1, joueur2);
        widthTailleTotale = partie.getPlateau().length * Resolution.widthCase;
        heightTailleTotale = partie.getPlateau()[0].length * Resolution.heightCase;
        xHautGauche = (int) (getCamera().position.x - Resolution.width / 2);
        yHautGauche = (int) (partie.getDimMax() - Resolution.height - (getCamera().position.y - Resolution.height / 2));
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

    public SpriteBatch getBatch() {
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

    public int getWidthTailleTotale() {
        return widthTailleTotale;
    }

    public int getHeightTailleTotale() {
        return heightTailleTotale;
    }
}

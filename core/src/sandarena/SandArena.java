package sandarena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;

import sandarena.donnee.sol.BanqueSol;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.partie.ScreenPartie;

import static sandarena.donnee.carte.CarteXML.parseCarteXML;
import static sandarena.donnee.competence.CompXML.parseCompXML;
import static sandarena.donnee.personnage.PersoXML.parsePersoXML;

/**
 * Class principale de l'application SandArena, un applicationAdapter
 *
 * @author Guillaume
 */
public class SandArena extends Game {

    private SpriteBatch batch;
    private ScreenPartie screenPartie;

    /**
     * Lancement de l'application, calcul de résolution et des differents ecrans
     */
    @Override
    public void create() {
        BanqueSol.init();
        batch = new SpriteBatch();
        try {
            sandarena.donnee.donneestatic.Resolution.calculResolution();
        } catch (IOException ex) {
            System.err.println("error : File");
        }
        parseCompXML();
        parsePersoXML();
        parseCarteXML();
        this.setScreen(new Screen() {
            @Override
            public void render(float delta) {

            }

            @Override
            public void resize(int width, int height) {

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

            }
        });
        new sandarena.connexion.ConnexionServeur(this);
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public void lancePartie(Joueur joueur, ArrayList<Personnage> personnagesActif, ArrayList<Personnage> personnagesAutre, boolean commence, int map){
        this.screenPartie = new ScreenPartie(this,joueur,personnagesActif,personnagesAutre,commence, map);
        this.setScreen(screenPartie);
    }

}

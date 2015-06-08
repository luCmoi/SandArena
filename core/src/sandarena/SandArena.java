package sandarena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.io.IOException;
import sandarena.donnee.*;
import sandarena.partie.gui.ScreenPartie;
import sandarena.joueur.Personnage;

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
        BanquePersonnage.init();
        BanqueCompetence.init();
        batch = new SpriteBatch();
        try {
            Resolution.calculResolution();
        } catch (IOException ex) {
            System.err.println("error : File");
        }
        new Personnage("Barbare des Sables");
        this.screenPartie = new ScreenPartie(this);
        this.setScreen(screenPartie);
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }
}

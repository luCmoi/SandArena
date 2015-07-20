package sandarena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;

import sandarena.donnee.BanqueCompetence;
import sandarena.donnee.BanquePersonnage;
import sandarena.donnee.BanqueSol;
import sandarena.partie.gui.ScreenPartie;

import static sandarena.donnee.CompXML.parseCompXML;

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
        //
        parseCompXML();
        //
        this.screenPartie = new ScreenPartie(this);
        this.setScreen(screenPartie);
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

}

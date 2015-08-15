package sandarena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;

import sandarena.donnee.BanqueCompetence;
import sandarena.donnee.BanquePersonnage;
import sandarena.donnee.BanqueSol;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.partie.gui.ScreenPartie;
import sandarena.preparematch.ScreenPrepaMatch;

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
        batch = new SpriteBatch();
        try {
            Resolution.calculResolution();
        } catch (IOException ex) {
            System.err.println("error : File");
        }
        parseCompXML();
        this.setScreen(new ScreenPrepaMatch(this));
        //
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public void lancePartie(Joueur joueur, ArrayList<Personnage> personnagesActif, ArrayList<Personnage> personnagesAutre, boolean commence){
        this.screenPartie = new ScreenPartie(this,joueur,personnagesActif,personnagesAutre,commence);
        this.setScreen(screenPartie);
    }
}

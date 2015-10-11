package sandarena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;

import sandarena.donnee.sol.BanqueSol;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.lancement.ScreenLancement;
import sandarena.partie.ScreenPartie;

/**
 * Class principale de l'application SandArena, un applicationAdapter
 *
 * @author Guillaume
 */
public class SandArena extends Game {

    private SpriteBatch batch;
    private ScreenPartie screenPartie;
    public static sandarena.googleservice.IGoogleService googleService;

    public SandArena(sandarena.googleservice.IGoogleService googleService)
    {
        super();
        this.googleService = googleService;
    }
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
        this.setScreen(new ScreenLancement(this));
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public void lancePartie(Joueur joueur, ArrayList<Personnage> personnagesActif, ArrayList<Personnage> personnagesAutre, boolean commence, int map){
        this.screenPartie = new ScreenPartie(this,joueur,personnagesActif,personnagesAutre,commence, map);
        this.setScreen(screenPartie);
    }

}

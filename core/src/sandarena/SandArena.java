package sandarena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.IOException;
import java.util.ArrayList;

import sandarena.donnee.carte.BanqueCarte;
import sandarena.donnee.sol.BanqueSol;
import sandarena.gestionequipe.ScreenGestionEquipe;
import sandarena.googleservice.ConnexionMatch;
import sandarena.googleservice.IGoogleService;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.lancement.ScreenLancement;
import sandarena.match.partie.ScreenPartie;
import sandarena.match.preparematch.ScreenPrepaMatch;
import sandarena.selectionequipe.ScreenSelectionEquipe;

/**
 * Class principale de l'application SandArena, un applicationAdapter
 *
 * @author Guillaume
 */
public class SandArena extends Game {

    private SpriteBatch batch;
    private ScreenPartie screenPartie;
    public static IGoogleService googleService;
    private ScreenSelectionEquipe screenSelectionEquipe = null;
    private ScreenGestionEquipe screenGestionEquipe = null;
    private ScreenPrepaMatch screenPrepaMatch;

    public SandArena(IGoogleService googleService)
    {
        super();
        SandArena.googleService = googleService;
    }

    public SandArena() {
        super();
    }

    /**
     * Lancement de l'application, calcul de résolution et des differents ecrans
     */
    @Override
    public void create() {
        BanqueSol.init();
        batch = new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
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
        this.setScreen(new ScreenPartie(this,joueur,personnagesActif,personnagesAutre,commence, map));
    }

    public void lanceSelect(Joueur[] equipe) {
        this.screenSelectionEquipe = new ScreenSelectionEquipe(this,equipe);
        this.setScreen(screenSelectionEquipe);
    }

    public void resumeSelect(Joueur equipe){
        this.screenSelectionEquipe.checkJoueur(equipe);
        this.setScreen(screenSelectionEquipe);
    }

    public void lanceGestionEquipe(Joueur equipe) {
        if (screenGestionEquipe == null){
            this.screenGestionEquipe = new ScreenGestionEquipe(this, equipe);
        }else{
            screenGestionEquipe.checkEquipe(equipe);
        }
        setScreen(screenGestionEquipe);
    }

    public void lancePrepaMatch(Joueur equipe) {
        int map = 0;
        if (IGoogleService.data.commence){
            map = BanqueCarte.getRandom();
            ConnexionMatch.envoiMap(map);
        }else{
            map = ConnexionMatch.recoitMap();
        }
        setScreen(new ScreenPrepaMatch(this,map, equipe));
    }
}

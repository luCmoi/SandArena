package sandarena.gestionequipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.gestionequipe.barre.StageBarreGestionEquipe;
import sandarena.gestionequipe.stagepersos.StagePersonnageGestionEquipe;
import sandarena.gestionequipe.surcouche.SurcoucheGestionEquipe;
import sandarena.googleservice.IGoogleService;
import sandarena.googleservice.Sauvegarde;
import sandarena.interfaceutil.stage.ScreenSurcouche;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 16/10/2015.
 */
public class ScreenGestionEquipe extends ScreenSurcouche {
    private Joueur equipe;
    private final SandArena container;
    private final Batch batch;
    private StagePersonnageGestionEquipe persos;
    private StageBarreGestionEquipe barre;

    public ScreenGestionEquipe(SandArena container, Joueur equipe) {
        this.equipe = equipe;
        this.container = container;
        this.batch = container.getBatch();
        this.persos = new StagePersonnageGestionEquipe(this, equipe, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch);
        this.barre = new StageBarreGestionEquipe(this, equipe, new ExtendViewport(Resolution.width, Resolution.differenceBas, Resolution.width, Resolution.differenceBas), batch);
        this.surcouche = new SurcoucheGestionEquipe(this, new FillViewport(Resolution.width, Resolution.height), batch);
        Gdx.input.setInputProcessor(this.persos);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.differenceBas);
        this.barre.draw();
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        this.persos.draw();
        this.surcouche.draw();
        if (IGoogleService.data.lancePartie) {
            container.lancePrepaMatch(equipe);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        IGoogleService.data.justLeft = false;
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

    @Override
    public void finInputSurcouche() {
        Gdx.input.setInputProcessor(persos);
    }

    public StageBarreGestionEquipe getBarre() {
        return barre;
    }

    public void launch() {
        SandArena.googleService.startQuickGame();
        ((SurcoucheGestionEquipe)surcouche).activateAttente();
    }

    public void achatAleat(byte place) {
        Personnage tmp = new Personnage();
        equipe.getPersonnages().add(tmp);
        equipe.setOr(equipe.getOr() - 1000);
        persos.add(tmp, place);
        SandArena.googleService.savedGamesUpdate(Sauvegarde.toSnapshotName(equipe.getNumero()), Sauvegarde.toData(equipe));
    }

    public SandArena getContainer() {
        return container;
    }

    public void backKeyPressed() {
        Son.menuSelect.play();
        if (surcouche.isVisible()) {
            surcouche.setVisible(false);
        } else {
            container.resumeSelect(equipe);
        }
    }

    public void checkEquipe(Joueur equipe) {
        this.equipe = equipe;
        barre.setEquipe(equipe);
        persos.setEquipe(equipe);
        surcouche.setVisible(false);
        Gdx.input.setInputProcessor(persos);
        if (Son.actuelle != null && Son.actuelle != Son.sadStrings){
            Son.actuelle.stop();
        }
        if (Son.actuelle != Son.sadStrings) {
            Son.sadStrings.setLooping(true);
            Son.sadStrings.setVolume(0.5f);
            Son.sadStrings.play();
            Son.actuelle = Son.sadStrings;
        }
    }

    public Joueur getEquipe() {
        return equipe;
    }
}

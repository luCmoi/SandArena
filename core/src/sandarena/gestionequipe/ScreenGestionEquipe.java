package sandarena.gestionequipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.gestionequipe.surcouche.Surcouche;
import sandarena.gestionequipe.barre.StageBarreGestionEquipe;
import sandarena.gestionequipe.stagepersos.StagePersonnageGestionEquipe;
import sandarena.googleservice.IGoogleService;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.preparematch.ScreenPrepaMatch;

/**
 * Created by lucmo on 16/10/2015.
 */
public class ScreenGestionEquipe implements Screen {
    private final Joueur equipe;
    private final SandArena container;
    private final Batch batch;
    private StagePersonnageGestionEquipe persos;
    private StageBarreGestionEquipe barre;
    private Surcouche surcouche;

    public ScreenGestionEquipe(SandArena container, Joueur equipe){
        this.equipe = equipe;
        this.container =container;
        this.batch = container.getBatch();
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
        if (IGoogleService.data.lancePartie){
            container.setScreen(new ScreenPrepaMatch(container, 3001, equipe));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        this.persos = new StagePersonnageGestionEquipe(this, equipe, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch);
        this.barre = new StageBarreGestionEquipe(this, equipe, new ExtendViewport(Resolution.width, Resolution.differenceBas, Resolution.width, Resolution.differenceBas), batch);
        this.surcouche = new Surcouche(this, new FillViewport(Resolution.width, Resolution.height), batch);
        Gdx.input.setInputProcessor(this.persos);
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

    public StageBarreGestionEquipe getBarre() {
        return barre;
    }

    public void launch() {
        SandArena.googleService.startQuickGame();
        surcouche.activateAttente();
    }

    public StagePersonnageGestionEquipe getPersos() {
        return persos;
    }

    public Surcouche getSurcouche() {
        return surcouche;
    }

    public void achatAleat(byte place) {
        Personnage tmp = new Personnage();
        equipe.getPersonnages().add(tmp);
        persos.add(tmp,place);
    }
}

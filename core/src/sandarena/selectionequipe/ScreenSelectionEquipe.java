package sandarena.selectionequipe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.selectionequipe.Surcouche.Surcouche;

/**
 * Created by lucmo on 05/10/2015.
 */
public class ScreenSelectionEquipe implements Screen {
    private Batch batch;
    private SandArena container;
    private StageSelectionEquipe stage;
    private Surcouche surcouche;

    public ScreenSelectionEquipe(SandArena container, Joueur[] equipe){
        this.container = container;
        this.batch = container.getBatch();
        this.surcouche = new Surcouche(this,new FillViewport(Resolution.width,Resolution.height),batch);
        setStage(new StageSelectionEquipe(this,batch,equipe));
        Gdx.input.setInputProcessor(this.getStage());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        stage.draw();
        surcouche.draw();
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

    public StageSelectionEquipe getStage() {
        return stage;
    }

    public void setStage(StageSelectionEquipe stage) {
        this.stage = stage;
    }

    public SandArena getContainer() {
        return container;
    }

    public void setTouchableDisabled() {
        Gdx.input.setInputProcessor(null);
    }

    public void confirme(int panel) {
        stage.suppr(panel);
    }

    public void confSuppr(int panel) {
        surcouche.activateConfirmeSuppr(panel);
    }

    public void launch(int place) {
        SandArena.googleService.startQuickGame();
        stage.setEquipe(place);
        surcouche.activateAttente(place);
    }
}
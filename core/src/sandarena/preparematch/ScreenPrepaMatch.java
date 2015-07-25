package sandarena.preparematch;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import sandarena.Resolution;
import sandarena.SandArena;
import sandarena.joueur.Joueur;


/**
 * Created by Guillaume on 20/07/2015.
 */
public class ScreenPrepaMatch implements Screen {
    private Batch batch;
    private SandArena conteneur;
    private StageBarre barre;
    private StagePrincipalScreenPrepa principal;
    private Stage surcouche;
    private Joueur joueur;


    public ScreenPrepaMatch(SandArena conteneur) {
        this.conteneur = conteneur;
        this.batch = conteneur.getBatch();
        this.joueur=joueur;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        this.getPrincipal().draw();
        Gdx.gl.glViewport(Resolution.differenceBas/2, 0, Resolution.width-Resolution.differenceBas, Resolution.differenceBas);
        this.getBarre().draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        this.setPrincipal(new StagePrincipalScreenPrepa(this,joueur, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch));
        this.setBarre(new StageBarre(this.getPrincipal(),joueur, new ExtendViewport(Resolution.width-Resolution.differenceBas, Resolution.differenceBas, Resolution.width-Resolution.differenceBas, Resolution.differenceBas), batch));
        setSurcouche(new Stage(new FillViewport(Resolution.width,Resolution.height),batch));
        getPrincipal().setBarre(this.getBarre());
        Gdx.input.setInputProcessor(this.getPrincipal());
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

    public StageBarre getBarre() {
        return barre;
    }

    public void setBarre(StageBarre barre) {
        this.barre = barre;
    }

    public StagePrincipalScreenPrepa getPrincipal() {
        return principal;
    }

    public void setPrincipal(StagePrincipalScreenPrepa principal) {
        this.principal = principal;
    }

    public Stage getSurcouche() {
        return surcouche;
    }

    public void setSurcouche(Stage surcouche) {
        this.surcouche = surcouche;
    }
}

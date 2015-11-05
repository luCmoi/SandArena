package sandarena.selectionequipe;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.selectionequipe.contenupanneau.PanneauEquipe;

public class StageSelectionEquipe extends Stage{
    private final ScreenSelectionEquipe container;

    public StageSelectionEquipe(ScreenSelectionEquipe container, Batch batch, Joueur[] equipe) {
        super(new FillViewport(Resolution.width,Resolution.height), batch);
        this.container = container;
        this.addActor(new PanneauEquipe(this,0,equipe[0]));
        this.addActor(new PanneauEquipe(this,1,equipe[1]));
        this.addActor(new PanneauEquipe(this,2,equipe[2]));
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX, stageY, touchable);
    }

    @Override
    public void draw() {
        super.draw();
    }

    public ScreenSelectionEquipe getContainer() {
        return container;
    }

    public void suppr(int panel) {
        ((PanneauEquipe)getActors().get(panel)).suppr();
    }

    public void checkJoueur(Joueur equipe) {
        ((PanneauEquipe)getActors().get(equipe.getNumero())).setEquipe(equipe);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK){
            container.backKeyPressed();
        }
        return super.keyDown(keyCode);
    }

    public void addEquipe(int panel, Joueur equipe){
        ((PanneauEquipe)getActors().get(panel)).setEquipe(equipe);
    }
}

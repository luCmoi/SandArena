package sandarena.selectionequipe;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.googleservice.IGoogleService;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.preparematch.ScreenPrepaMatch;
import sandarena.selectionequipe.contenupanneau.PanneauEquipe;

public class StageSelectionEquipe extends Stage{
    private final ScreenSelectionEquipe container;
    private final Joueur[] equipe;
    private int equipeSelect;

    public StageSelectionEquipe(ScreenSelectionEquipe container, Batch batch, Joueur[] equipe) {
        super(new FillViewport(Resolution.width,Resolution.height), batch);
        this.container = container;
        this.equipe=equipe;
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
        if (IGoogleService.data.lancePartie){
            container.getContainer().setScreen(new ScreenPrepaMatch(container.getContainer(), 3001, equipe[equipeSelect]));
        }
    }

    public ScreenSelectionEquipe getContainer() {
        return container;
    }

    public void suppr(int panel) {
        ((PanneauEquipe)getActors().get(panel)).suppr();
    }
}

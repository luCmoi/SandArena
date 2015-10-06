package sandarena.selectionequipe;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;

/**
 * Created by lucmo on 05/10/2015.
 */
public class StageSelectionEquipe extends Stage{
    private final ScreenSelectionEquipe container;
    private final FondSelection fond;
    private final Joueur[] equipe;

    public StageSelectionEquipe(ScreenSelectionEquipe container, Batch batch, Joueur[] equipe) {
        super(new FillViewport(Resolution.width,Resolution.height), batch);
        this.container = container;
        this.equipe=equipe;
        fond = new FondSelection(this);
        this.addActor(fond);
        this.addActor(new PanneauEquipe(this,0,equipe[0]));
        this.addActor(new PanneauEquipe(this,1,equipe[1]));
        this.addActor(new PanneauEquipe(this,2,equipe[2]));
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX, stageY, touchable);
    }

    public ScreenSelectionEquipe getContainer() {
        return container;
    }
}

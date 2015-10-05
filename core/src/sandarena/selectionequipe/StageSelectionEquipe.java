package sandarena.selectionequipe;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.donnee.donneestatic.Resolution;

/**
 * Created by lucmo on 05/10/2015.
 */
public class StageSelectionEquipe extends Stage{
    private final ScreenSelectionEquipe container;
    private final FondSelection fond;

    public StageSelectionEquipe(ScreenSelectionEquipe container, Batch batch) {
        super(new FillViewport(Resolution.width,Resolution.height), batch);
        this.container = container;
        fond = new FondSelection(this);
        this.addActor(fond);
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        System.err.println("Hit");
        return super.hit(stageX, stageY, touchable);
    }
}

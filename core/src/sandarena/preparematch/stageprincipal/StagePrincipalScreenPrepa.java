package sandarena.preparematch.stageprincipal;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import sandarena.Resolution;
import sandarena.joueur.Joueur;
import sandarena.preparematch.ScreenPrepaMatch;
import sandarena.preparematch.barre.EmplacementBarre;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class StagePrincipalScreenPrepa extends Stage {

    private final ScreenPrepaMatch container;
    private final Joueur joueur;
    private final FlecheListe gauche;
    private final FlecheListe droite;
    private sandarena.preparematch.barre.StageBarre barre;
    private PanelScreenPrepaMatch panelGauche;
    private PanelScreenPrepaMatch panelDroit;

    public StagePrincipalScreenPrepa(ScreenPrepaMatch screenPrepaMatch, Joueur joueur, ScalingViewport scalingViewport, Batch batch) {
        super(scalingViewport,batch);
        this.container = screenPrepaMatch;
        this.joueur = joueur;
        this.panelGauche = new PanelScreenPrepaMatch(0,this);
        this.panelDroit = new PanelScreenPrepaMatch(1,this);
        this.addActor(panelGauche);
        this.addActor(panelDroit);
        gauche = new FlecheListe(this,true);
        droite = new FlecheListe(this,false);
        this.addActor(gauche);
        this.addActor(droite);
    }

    public void setBarre(sandarena.preparematch.barre.StageBarre barre){
        this.barre=barre;
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        Vector2 vScreen = stageToScreenCoordinates(new Vector2(stageX, stageY));
        if (Resolution.height - vScreen.y < Resolution.differenceBas && Resolution.width - vScreen.x > Resolution.differenceBas/2 && Resolution.width - vScreen.x < Resolution.width - (Resolution.differenceBas/2)) {
            return barre.hit(vScreen.x - (Resolution.differenceBas/2), Resolution.height - vScreen.y, touchable);
        } else {
            return super.hit(stageX, stageY, touchable);
        }
    }

    public sandarena.preparematch.barre.StageBarre getBarre() {
        return barre;
    }

    public boolean relacheUnit(float x, float y, EmplacementBarre container) {
        return panelGauche.relacheUnit(x, y, container);
    }
}

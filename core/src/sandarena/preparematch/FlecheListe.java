package sandarena.preparematch;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.Utili;

/**
 * Created by Guillaume on 25/07/2015.
 */
public class FlecheListe extends Actor {

    private final StagePrincipalScreenPrepa container;
    private final boolean gauche;

    public FlecheListe(StagePrincipalScreenPrepa container, boolean gauche) {
        this.container = container;
        this.setTouchable(Touchable.enabled);
        this.gauche = gauche;
        if (gauche) {
            setBounds(0, 0, Resolution.differenceBas/2, Resolution.differenceBas);
        } else {
            setBounds(Resolution.width - (Resolution.differenceBas/2), 0, Resolution.differenceBas/2, Resolution.differenceBas);
        }
        this.addListener(new FlecheListeListener(this));
    }

    public boolean isGauche(){
        return this.gauche;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }


    public StagePrincipalScreenPrepa getContainer() {
        return container;
    }
}

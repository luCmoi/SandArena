package sandarena.match.preparematch.stageprincipal;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by Guillaume on 25/07/2015.
 */
class FlecheListe extends Actor {

    private  StagePrincipalScreenPrepa container;
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
        if (gauche){
            batch.draw(Utili.fleche, getX(), getY(), getWidth(), getHeight());
        }else{
            batch.draw(Utili.fleche, getX(), getY(), getWidth(), getHeight(), 0, 0, Utili.fleche.getWidth(), Utili.fleche.getHeight(), true, false);
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }


    public StagePrincipalScreenPrepa getContainer() {
        return container;
    }

    public void dispose() {

        ((FlecheListeListener) (getListeners().get(0))).dispose();
        getListeners().clear();this.container = null;
    }
}

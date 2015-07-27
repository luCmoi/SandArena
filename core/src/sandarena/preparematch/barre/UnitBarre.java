package sandarena.preparematch.barre;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 26/07/2015.
 */
public class UnitBarre extends Group {
    private StageBarre container;
    private final int place;
    private Personnage perso;
    private boolean ouvert;
    private boolean dragged;
    private float ancienX;

    public UnitBarre(StageBarre container, int place, Personnage perso) {
        this.container = container;
        this.place = place;
        this.perso = perso;
        this.setBounds(Resolution.differenceBas * place, 0, Resolution.differenceBas, Resolution.differenceBas);
        this.setTouchable(Touchable.enabled);
        this.addListener(new UnitBarreListener(this));
        ouvert = false;
        setDragged(false);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(perso.commun.image, getX(), getY(), Resolution.differenceBas, getHeight());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public void clique() {
        if (!ouvert) {
            this.getContainer().augmenteWidthTailleTotale(this.place);
            this.setWidth(getWidth() + Resolution.differenceBas);
            ouvert = true;
        } else {
            this.getContainer().diminueWidthTailleTotale(this.place);
            this.setWidth(getWidth() - Resolution.differenceBas);
            ouvert = false;
        }
    }

    public void decaleGauche() {
        this.setX(getX() - Resolution.differenceBas);
    }

    public void decaleDroite() {
        this.setX(getX() + Resolution.differenceBas);
    }

    public synchronized void dragged() {
        if (!isDragged()) {
            if (ouvert) {
                this.getContainer().diminueWidthTailleTotale(this.place);
                this.setWidth(getWidth() - Resolution.differenceBas);
                ouvert = false;
            }
            this.remove();
            getContainer().getPrincipal().addActor(this);
            setDragged(true);
            ancienX = getX();
        }
        if (Gdx.input.isTouched()) {
            this.setX(Gdx.input.getX(0)- (Resolution.differenceBas/2));
            this.setY(Resolution.height - Gdx.input.getY(0) - (Resolution.differenceBas/2));
        } else {
            this.remove();
            this.getContainer().addActor(this);
            setDragged(false);
            this.setX(ancienX);
            this.setY(0);
        }
    }

    public boolean isDragged() {
        return dragged;
    }

    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }

    public StageBarre getContainer() {
        return container;
    }

    public int getPlace() {
        return place;
    }

    public void dispose() {
        container = null;
        perso = null;
        ((UnitBarreListener)(getListeners().get(0))).dispose();
        getListeners().clear();
        remove();
    }

    public Personnage getPerso() {
        return perso;
    }
}

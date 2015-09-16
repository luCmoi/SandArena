package sandarena.infowindow;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.Resolution;
import sandarena.donnee.Utili;


/**
 * Created by Guillaume on 11/06/2015.
 */
public abstract class InfoWindow extends Group {
    protected Icone icone;
    protected Info info;
    protected static final byte DIFF = 100;
    protected static final byte TAILLE_EFFET = 20;
    protected boolean affiche = false;

    public InfoWindow(Object emplacement) {
    }

    protected void checkPlace(){
        if (getX()+ getWidth() > Resolution.width){
            setX(Resolution.width-getWidth());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (affiche) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
            super.draw(batch, parentAlpha);
        }
    }

    public void dispose(){
        clear();
        remove();
        if (icone != null) {
            this.icone.dispose();
        }
        this.info.dispose();
        icone = null;
        info = null;
    }

}

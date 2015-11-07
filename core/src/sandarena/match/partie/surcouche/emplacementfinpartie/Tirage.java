package sandarena.match.partie.surcouche.emplacementfinpartie;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 06/11/2015.
 */
public class Tirage extends Actor {
    private final EmplacementFinPartie container;

    public Tirage(EmplacementFinPartie emplacementFinPartie) {
        container = emplacementFinPartie;
        this.setBounds(0,0,container.getWidth()/3*2,container.getWidth()/3*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour,getX(),getY(),getWidth(),getHeight());
        super.draw(batch, parentAlpha);
    }
}

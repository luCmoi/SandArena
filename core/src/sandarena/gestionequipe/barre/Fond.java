package sandarena.gestionequipe.barre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 16/10/2015.
 */
public class Fond extends Actor {
    private final StageBarreGestionEquipe container;

    public Fond(StageBarreGestionEquipe stageBarre) {
        this.container = stageBarre;
        this.setBounds(0,0,stageBarre.getWidth(),stageBarre.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.fond,getX(),getY(), getWidth(), getHeight());
    }
}
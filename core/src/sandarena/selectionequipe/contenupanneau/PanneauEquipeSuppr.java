package sandarena.selectionequipe.contenupanneau;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 09/10/2015.
 */
public class PanneauEquipeSuppr extends Actor{
    private PanneauEquipe container;

    public PanneauEquipeSuppr(PanneauEquipe panneauEquipe) {
        this.container = panneauEquipe;
        this.setBounds(0,0,container.getWidth(),container.getWidth()/6);
        this.setTouchable(Touchable.enabled);
        this.addListener(new PanneauEquipeSupprListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setColor(Color.BLACK);
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        Font.font.draw(batch,"Supprimer",getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 9 / 2),getHeight() -(getHeight()/10));
    }

    public void clique() {
        Son.menuSelect.play();
        container.setSupprimer(true);
        container.getContainer().getContainer().confSuppr(container.getPlace());
    }

    public void dispose(){
        this.container = null;
        ((PanneauEquipeSupprListener)this.getListeners().get(0)).dispose();
        this.clear();
    }
}

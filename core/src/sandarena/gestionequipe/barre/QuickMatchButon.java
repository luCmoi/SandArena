package sandarena.gestionequipe.barre;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 16/10/2015.
 */
class QuickMatchButon extends Actor {
    private final StageBarreGestionEquipe container;

    public QuickMatchButon(StageBarreGestionEquipe stageBarreGestionEquipe) {
        this.container = stageBarreGestionEquipe;
        this.setTouchable(Touchable.enabled);
        this.setBounds(0, 0, container.getWidth() / 4, container.getHeight());
        this.addListener(new QuickMatchButonListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setColor(Color.BLACK);
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        Font.font.draw(batch, "Match", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 5/2), getY() + getHeight() - getHeight() / 6);
        Font.font.draw(batch,"Rapide", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth()*6/2),getY()+getHeight()-getHeight()/6 - Font.font.getLineHeight());
    }

    public void clique(){
        Son.MenuSelect.play();
        container.getContainer().launch();
    }
}

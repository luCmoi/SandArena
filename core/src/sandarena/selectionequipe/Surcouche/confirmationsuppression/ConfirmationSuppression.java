package sandarena.selectionequipe.Surcouche.confirmationsuppression;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.selectionequipe.Surcouche.Surcouche;

/**
 * Created by lucmo on 13/10/2015.
 */
public class ConfirmationSuppression extends Group {
    private final Surcouche container;
    private int panel;

    public ConfirmationSuppression(Surcouche surcouche) {
        super();
        container = surcouche;
        setBounds(Resolution.width/4,Resolution.height/4,Resolution.width/2,Resolution.height/2);
        this.addActor(new BouttonConf(this));
        this.addActor(new BouttonAnnu(this));
        setVisible(false);
    }

    public Surcouche getContainer() {
        return container;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.passive,0,0,Resolution.width,Resolution.height);
        batch.draw(Utili.fond,getX(),getY(),getWidth(),getHeight());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setColor(Color.BLACK);
        Font.font.setScale(Resolution.ratioWidth * 6, Resolution.ratioHeight * 6);
        Font.font.draw(batch, "Supprimer ?", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 11 / 2), getY() + getHeight() - getHeight() / 6);
        super.draw(batch, parentAlpha);
        Font.font.setColor(Color.WHITE);
    }

    public void setVisible(boolean visible,int panel) {
        super.setVisible(visible);
        this.panel = panel;
    }

    public int getPanel() {
        return panel;
    }

}

package sandarena.gestionequipe.stagepersos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 22/10/2015.
 */
public class Fond extends Actor {
    private final StagePersonnageGestionEquipe container;

    public Fond(StagePersonnageGestionEquipe container) {
        this.container = container;
        this.setBounds(0, Resolution.differenceBas,container.getWidth(),container.getHeight() - Resolution.differenceBas - container.getWidth()/8*3);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Font.font.setColor(Color.YELLOW);
        Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
        String strTmp = String.valueOf(container.getEquipe().getOr());
        Font.font.draw(batch, strTmp , getWidth() - Font.font.getSpaceWidth() * strTmp.length()-getHeight(), getY()+Font.font.getLineHeight());
        batch.draw(Utili.pieces,getWidth() - getHeight(), getY(),getHeight(),getHeight());

    }
}

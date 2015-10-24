package sandarena.lancement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 11/10/2015.
 */
public class EmplacementTexte extends Actor{
    private StageLancement container;
    private String texte = "";
    private static  float ECARTFOND;

    public EmplacementTexte(StageLancement stageLancement) {
        this.container = stageLancement;
        this.setBounds(container.getWidth() / 6, container.getHeight() / 6, Resolution.width, 0);

    }

    public void setTexte(String texte) {
        this.texte = texte;
        Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
        this.setX((Resolution.width/2)-(Font.font.getSpaceWidth()*texte.length()/2));
        setHeight(Font.font.getLineHeight());
        ECARTFOND = Font.font.getLineHeight()/2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Font.font.setColor(Color.WHITE);
        Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
        batch.draw(Utili.fondTexte,0,getY()-(2*ECARTFOND),getWidth(),getHeight()+(ECARTFOND));
        Font.font.draw(batch, texte, getX(), getY());
    }

    public void dispose() {
        texte = null;
        container = null;
    }
}

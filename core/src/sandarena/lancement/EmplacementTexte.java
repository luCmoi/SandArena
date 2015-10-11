package sandarena.lancement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;

/**
 * Created by lucmo on 11/10/2015.
 */
public class EmplacementTexte extends Actor{
    private StageLancement container;
    private String texte = "";

    public EmplacementTexte(StageLancement stageLancement) {
        this.container = stageLancement;
        this.setBounds(container.getWidth()/6,container.getHeight()/6,0,0);
        Font.font.setColor(Color.WHITE);
        Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Font.font.draw(batch, texte, getX(), getY());
    }

    public void dispose() {
        texte = null;
        container = null;
    }
}

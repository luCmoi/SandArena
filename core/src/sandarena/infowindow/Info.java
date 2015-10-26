package sandarena.infowindow;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Font;


/**
 * Created by Guillaume on 12/06/2015.
 */
public abstract class Info extends Actor {
    protected String[] texte = new String[0];
    protected static final byte PARLIGNECOMP = 35;
    protected static final byte PARLIGNEEFFET = 50;

    protected Info(InfoWindow container) {
        super();
        setBounds(0, 0, container.getHeight(), container.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        Font.font.setColor(Color.BLACK);
    }

    public void dispose() {
        for (String s : texte) {
            s = null;
        }
        this.clear();
    }

    public String[] getTexte() {
        return texte;
    }
}


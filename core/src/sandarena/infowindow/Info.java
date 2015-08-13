package sandarena.infowindow;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.donnee.Font;
import sandarena.donnee.RegexTexte;
import sandarena.donnee.Utili;
import sandarena.partie.effet.EffetBuf;


/**
 * Created by Guillaume on 12/06/2015.
 */
public abstract class Info extends Actor {
    protected String[] texte = new String[0];
    protected static final int PARLIGNECOMP = 35;
    protected static final int PARLIGNEEFFET = 50;

    public Info(InfoWindow container) {
        super();
        setBounds(0, 0, container.getHeight(), container.getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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


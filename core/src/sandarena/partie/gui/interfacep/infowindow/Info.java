package sandarena.partie.gui.interfacep.infowindow;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.Resolution;
import sandarena.donnee.Font;
import sandarena.donnee.RegexTexte;


/**
 * Created by Guillaume on 12/06/2015.
 */
public class Info extends Actor {
    private String[] texte;
    private boolean perso;
    private static final int PARLIGNE = 40;

    public Info(InfoWindow container) {
        super();
        setBounds(0, 0, container.getHeight(), container.getHeight());
        if (container.getPerso() != null) {
            texte = container.getPerso().toStrings();
            perso = true;
        } else if (container.getComp() != null) {
            texte = container.getComp().toStrings();
            perso = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso) {
            Font.font.setScale(Resolution.ratioWidth * 3, Resolution.ratioHeight * 3);
            Font.font.draw(batch, texte[0], getX() + getHeight(), getY() + getHeight());
            Font.font.setScale((float)(Resolution.ratioWidth * 1.5), Resolution.ratioHeight * 2);
            Font.font.draw(batch, texte[1], getX() + getHeight(), getY() + getHeight() - Font.font.getLineHeight() - (Resolution.ratioHeight * 5));
            Font.font.draw(batch, texte[2], getX() + getHeight() + (getHeight() / 2), getY() + getHeight() - Font.font.getLineHeight() - (Resolution.ratioHeight * 5));
            Font.font.draw(batch, texte[3], getX() + getHeight(), getY() + getHeight() - (Font.font.getLineHeight() * 2) - (Resolution.ratioHeight * 10));
            Font.font.draw(batch, texte[4], getX() + getHeight() + (getHeight() / 2), getY() + getHeight() - (Font.font.getLineHeight() * 2) - (Resolution.ratioHeight * 10));
            Font.font.draw(batch, texte[5], getX() + getHeight(), getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 15));
        }
        else {
            Font.font.setScale(Resolution.ratioWidth * 3, Resolution.ratioHeight * 3);
            Font.font.draw(batch, texte[0], getX() + getHeight(), getY() + getHeight());
            Font.font.setScale((float)(Resolution.ratioWidth * 1.5), Resolution.ratioHeight * 2);
            int h = 1;
            for (int i = 1; i < texte.length; i++) {
                RegexTexte.CHARACTERS_PER_FRAME = PARLIGNE;
                String[] tmp = RegexTexte.parse_string(texte[i]);
                for (int j = 0; j<tmp.length;j++){
                    Font.font.draw(batch, tmp[j], getX() + getHeight(), getY() + getHeight() - ((Font.font.getLineHeight()/2)*(1+h)) - (Resolution.ratioHeight * 5*h));
                    h++;
                }
            }
        }
    }

    public void dispose() {
        for (String s : texte){
            s = null;
        }
        this.clear();
    }
}


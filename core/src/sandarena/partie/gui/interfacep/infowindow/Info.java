package sandarena.partie.gui.interfacep.infowindow;

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
public class Info extends Actor {
    private String[] texte = new String[0];
    private boolean perso;
    private boolean comp;
    private static final int PARLIGNECOMP = 35;
    private static final int PARLIGNEEFFET = 50;

    public Info(InfoWindow container) {
        super();
        setBounds(0, 0, container.getHeight(), container.getHeight());
        if (container.getPerso() != null) {
            texte = container.getPerso().toStrings();
            perso = true;
            comp = false;
        } else if (container.getComp() != null) {
            texte = container.getComp().toStrings();
            perso = false;
            comp = true;
        } else if (container.getEffet() != null) {
            ArrayList<String> tmp = new ArrayList<String>();
            tmp.add(" ");
            for (EffetBuf buff : container.getEffet()) {
                tmp.addAll(buff.toStrings());
                tmp.add(" ");
            }
            texte = tmp.toArray(texte);
            perso = false;
            comp = false;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso) {
            RegexTexte.changeCharacter(PARLIGNECOMP);
            Font.font.setScale((float) (Resolution.ratioWidth * 3.25), (float) (Resolution.ratioHeight * 3.25));
            Font.font.draw(batch, texte[0], getX() + getHeight(), getY() + getHeight());
            Font.font.setScale((float) (Resolution.ratioWidth * 2), (float) (Resolution.ratioHeight * 2.5));

            batch.draw(Utili.vie, getX() + getHeight(),
                    getY() + getHeight() - (2 * Font.font.getLineHeight()) - (Resolution.ratioHeight * 5),
                    Font.font.getLineHeight(), Font.font.getLineHeight());
            Font.font.draw(batch, texte[1], getX() + getHeight() + Font.font.getLineHeight(),
                    getY() + getHeight() - Font.font.getLineHeight() - (Resolution.ratioHeight * 5));

            batch.draw(Utili.vitesse, getX() + getHeight() + (getHeight() / 2) + Font.font.getLineHeight(),
                    getY() + getHeight() - (2 * Font.font.getLineHeight()) - (Resolution.ratioHeight * 5),
                    Font.font.getLineHeight(), Font.font.getLineHeight());
            Font.font.draw(batch, texte[2], getX() + getHeight() + (getHeight() / 2) + (Font.font.getLineHeight() * 2),
                    getY() + getHeight() - Font.font.getLineHeight() - (Resolution.ratioHeight * 5));

            batch.draw(Utili.force, getX() + getHeight(),
                    getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 10),
                    Font.font.getLineHeight(), Font.font.getLineHeight());
            Font.font.draw(batch, texte[3], getX() + getHeight() + Font.font.getLineHeight(),
                    getY() + getHeight() - (Font.font.getLineHeight() * 2) - (Resolution.ratioHeight * 10));

            batch.draw(Utili.agilite, getX() + getHeight() + (getHeight() / 2) + Font.font.getLineHeight(),
                    getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 10),
                    Font.font.getLineHeight(), Font.font.getLineHeight());
            Font.font.draw(batch, texte[4], getX() + getHeight() + (getHeight() / 2) + (Font.font.getLineHeight() * 2),
                    getY() + getHeight() - (Font.font.getLineHeight() * 2) - (Resolution.ratioHeight * 10));

            batch.draw(Utili.magie, getX() + getHeight(),
                    getY() + getHeight() - (Font.font.getLineHeight() * 4) - (Resolution.ratioHeight * 15),
                    Font.font.getLineHeight(), Font.font.getLineHeight());
            Font.font.draw(batch, texte[5], getX() + getHeight() + Font.font.getLineHeight(),
                    getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 15));

        } else if (comp) {
            RegexTexte.changeCharacter(PARLIGNECOMP);
            Font.font.setScale((float) (Resolution.ratioWidth * 3.25), (float) (Resolution.ratioHeight * 3.25));
            Font.font.draw(batch, texte[0], getX() + getHeight(), getY() + getHeight());
            Font.font.setScale((float) (Resolution.ratioWidth * 2), (float) (Resolution.ratioHeight * 2.5));
            int h = 1;
            for (int i = 1; i < texte.length; i++) {
                String[] tmp = RegexTexte.parse_string(texte[i]);
                for (int j = 0; j < tmp.length; j++) {
                    Font.font.draw(batch, tmp[j], getX() + getHeight(), getY() + getHeight() - ((Font.font.getLineHeight() / 2) * (1 + h)) - (Resolution.ratioHeight * 5 * h));
                    h++;
                }
            }
        } else {
            RegexTexte.changeCharacter(PARLIGNEEFFET);
            Font.font.setScale((float) (Resolution.ratioWidth * 2), (float) (Resolution.ratioHeight * 2.5));
            float tSize = Font.font.getLineHeight();
            Font.font.setScale((float) (Resolution.ratioWidth * 1.5), (float) (Resolution.ratioHeight * 2));
            float hSize = Font.font.getLineHeight();
            int t = 0;
            int h = 0;
            for (int i = 0; i < texte.length; i++) {
                if (texte[i] == " ") {
                    i++;
                    //Test couleur
                    if (i < texte.length) {
                        Font.font.setScale((float) (Resolution.ratioWidth * 2), (float) (Resolution.ratioHeight * 2.5));
                        Font.font.draw(batch, texte[i], getX(), getY() + getHeight() - (tSize / 2 * t) - (hSize / 2 * h) - (Resolution.ratioHeight * 5 * (h+t)));
                        t++;
                    }
                } else {
                    Font.font.setScale((float) (Resolution.ratioWidth * 1), (float) (Resolution.ratioHeight * 1.5));
                    String[] tmp = RegexTexte.parse_string(texte[i]);
                    for (int j = 0; j < tmp.length; j++) {
                        Font.font.draw(batch, tmp[j], getX(), getY() + getHeight()   - (tSize / 2 * t) - (hSize / 2 * h) - (Resolution.ratioHeight * 5 * (h+t)));
                        h++;
                    }
                }
            }
        }
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


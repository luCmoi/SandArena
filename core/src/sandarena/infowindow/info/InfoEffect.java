package sandarena.infowindow.info;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.RegexTexte;
import sandarena.infowindow.Info;
import sandarena.infowindow.windows.InfoWindowEffect;
import sandarena.match.partie.jeu.compcase.effet.EffetBuf;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoEffect extends Info {

    public InfoEffect(InfoWindowEffect container) {
        super(container);
        ArrayList<String> tmp = new ArrayList<String>();
        tmp.add(" ");
        for (EffetBuf buff : container.getEffet()) {
            tmp.addAll(buff.toStrings());
            tmp.add(" ");
        }
        texte = tmp.toArray(texte);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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
                    Font.font.draw(batch, texte[i], getX(), getY() + getHeight() - (tSize / 2 * t) - (hSize / 2 * h) - (Resolution.ratioHeight * 5 * (h + t)));
                    t++;
                }
            } else {
                Font.font.setScale((float) (Resolution.ratioWidth * 1), (float) (Resolution.ratioHeight * 1.5));
                String[] tmp = RegexTexte.parse_string(texte[i]);
                for (int j = 0; j < tmp.length; j++) {
                    Font.font.draw(batch, tmp[j], getX(), getY() + getHeight() - (tSize / 2 * t) - (hSize / 2 * h) - (Resolution.ratioHeight * 5 * (h + t)));
                    h++;
                }
            }
        }
    }

}

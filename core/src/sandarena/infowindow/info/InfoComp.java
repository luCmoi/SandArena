package sandarena.infowindow.info;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.RegexTexte;
import sandarena.infowindow.Info;
import sandarena.infowindow.windows.InfoWindowComp;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoComp extends Info {
    public InfoComp(InfoWindowComp container) {
        super(container);
        Object[] tmp = container.getComp().competence.toStrings().toArray();
        texte = new String[tmp.length + 1];
        texte[0] = container.getComp().nom;
        for (int i = 1; i < texte.length; i++) {
            texte[i] = (String) (tmp[i - 1]);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
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
    }
}

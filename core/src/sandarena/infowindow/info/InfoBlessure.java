package sandarena.infowindow.info;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.RegexTexte;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.Info;
import sandarena.infowindow.windows.InfoWindowBlessure;

/**
 * Created by lucmo on 20/11/2015.
 */
public class InfoBlessure extends Info {
    public InfoBlessure(InfoWindowBlessure container) {
        super(container);
        Object[] tmp = container.getBlessure().toStrings().toArray();
        texte = new String[tmp.length + 1];
        texte[0] = container.getBlessure().donnee.nom;
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
        Font.font.setScale(Resolution.ratioWidth * 2, (float) (Resolution.ratioHeight * 2.5));
        int h = 1;
        for (int i = 1; i < texte.length; i++) {
            String[] tmp = RegexTexte.parse_string(texte[i]);
            for (String aTmp : tmp) {
                Font.font.draw(batch, aTmp, getX() + getHeight(), getY() + getHeight() - ((Font.font.getLineHeight() / 2) * (1 + h)) - (Resolution.ratioHeight * 5 * h));
                h++;
            }
        }
    }
}

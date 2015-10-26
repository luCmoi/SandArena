package sandarena.infowindow.info;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.RegexTexte;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.Info;
import sandarena.infowindow.windows.InfoWindowPersoIG;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoPersoIG extends Info {
    public InfoPersoIG(InfoWindowPersoIG container) {
        super(container);
        texte = container.getPersoIG().toStrings();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        RegexTexte.changeCharacter(PARLIGNECOMP);
        Font.font.setScale((float) (Resolution.ratioWidth * 3.25), (float) (Resolution.ratioHeight * 3.25));
        Font.font.draw(batch, texte[0], getX() + getHeight(), getY() + getHeight());
        Font.font.setScale(Resolution.ratioWidth * 2, (float) (Resolution.ratioHeight * 2.5));

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

    }
}

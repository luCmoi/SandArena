package sandarena.infowindow.info;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.Resolution;
import sandarena.donnee.Font;
import sandarena.donnee.RegexTexte;
import sandarena.donnee.Utili;
import sandarena.infowindow.Info;
import sandarena.infowindow.windows.InfoWindowCaseIG;
import sandarena.partie.effet.CompetenceToEffet;

/**
 * Created by lucmo on 14/09/2015.
 */
public class InfoCaseIG extends Info {

    private final InfoWindowCaseIG container;
    private int[] tabModif;

    public InfoCaseIG(InfoWindowCaseIG container) {
        super(container);
        this.container = container;
        texte = container.getPresence().toStrings();
        tabModif = new int[8];
        tabModif = CompetenceToEffet.toStringsCase(container.getComp().info.competence, container.getPresence(), container.getLanceur(), tabModif);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        RegexTexte.changeCharacter(PARLIGNECOMP);
        Font.font.setScale((float) (Resolution.ratioWidth * 3.25), (float) (Resolution.ratioHeight * 3.25));
        Font.font.draw(batch, texte[0], getX() + getHeight(), getY() + getHeight());
        Font.font.setScale((float) (Resolution.ratioWidth * 2), (float) (Resolution.ratioHeight * 2.5));

        batch.draw(Utili.vie, getX() + getHeight(),
                getY() + getHeight() - (2 * Font.font.getLineHeight()) - (Resolution.ratioHeight * 5),
                Font.font.getLineHeight(), Font.font.getLineHeight());
        Font.font.draw(batch, ajout(texte[1], stringFrom(tabModif[0]),true), getX() + getHeight() + Font.font.getLineHeight(),
                getY() + getHeight() - Font.font.getLineHeight() - (Resolution.ratioHeight * 5));

        batch.draw(Utili.vitesse, getX() + getHeight() + (getHeight() / 2) + Font.font.getLineHeight(),
                getY() + getHeight() - (2 * Font.font.getLineHeight()) - (Resolution.ratioHeight * 5),
                Font.font.getLineHeight(), Font.font.getLineHeight());
        Font.font.draw(batch, ajout(texte[2], stringFrom(tabModif[1]),true), getX() + getHeight() + (getHeight() / 2) + (Font.font.getLineHeight() * 2),
                getY() + getHeight() - Font.font.getLineHeight() - (Resolution.ratioHeight * 5));

        batch.draw(Utili.force, getX() + getHeight(),
                getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 10),
                Font.font.getLineHeight(), Font.font.getLineHeight());
        Font.font.draw(batch,  ajout(ajout(texte[3],stringFrom(tabModif[2]),true),stringFrom(tabModif[3]),false), getX() + getHeight() + Font.font.getLineHeight(),
                getY() + getHeight() - (Font.font.getLineHeight() * 2) - (Resolution.ratioHeight * 10));

        batch.draw(Utili.agilite, getX() + getHeight() + (getHeight() / 2) + Font.font.getLineHeight(),
                getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 10),
                Font.font.getLineHeight(), Font.font.getLineHeight());
        Font.font.draw(batch,  ajout(ajout(texte[4],stringFrom(tabModif[4]),true),stringFrom(tabModif[5]),false), getX() + getHeight() + (getHeight() / 2) + (Font.font.getLineHeight() * 2),
                getY() + getHeight() - (Font.font.getLineHeight() * 2) - (Resolution.ratioHeight * 10));

        batch.draw(Utili.magie, getX() + getHeight(),
                getY() + getHeight() - (Font.font.getLineHeight() * 4) - (Resolution.ratioHeight * 15),
                Font.font.getLineHeight(), Font.font.getLineHeight());
        Font.font.draw(batch, ajout(ajout(texte[5],stringFrom(tabModif[6]),true),stringFrom(tabModif[7]),false), getX() + getHeight() + Font.font.getLineHeight(),
                getY() + getHeight() - (Font.font.getLineHeight() * 3) - (Resolution.ratioHeight * 15));

    }

    private String ajout(String s, String s1, boolean premier) {
        if (premier) {
            String[] tmp = s.split("/");
            return tmp[0].concat(s1).concat("/").concat(tmp[1]);
        }else{
            return s.concat(s1);
        }
    }

    private String stringFrom(int i) {
        if (i != 0) {
            String a = Integer.toString(i);
            if (i > 0) {
                a = "+".concat(a);
            }
            return a;
        }else {
            return "";
        }
    }
}

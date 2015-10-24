package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.Icone;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoCaseIG;
import sandarena.match.partie.jeu.compcase.CompetenceIG;
import sandarena.match.partie.jeu.compcase.PersonnageIG;

/**
 * Created by lucmo on 14/09/2015.
 */
public class InfoWindowCaseIG extends InfoWindow {
    private CompetenceIG comp;
    private PersonnageIG presence;
    private PersonnageIG lanceur;

    public InfoWindowCaseIG(PersonnageIG presence,PersonnageIG lanceur, CompetenceIG competenceActive) {
        super(presence);
        if (presence != null) {
            this.presence = presence;
            this.comp = competenceActive;
            this.lanceur = lanceur;
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 2.5), (float) ((Resolution.height / 4)));
            icone = new Icone(this, presence.getDonnee().commun.image);
            info = new InfoCaseIG(this);
            this.addActor(icone);
            this.addActor(info);
            affiche = true;
            checkPlace();
        }
    }

    public CompetenceIG getComp() {
        return comp;
    }

    public PersonnageIG getPresence() {
        return presence;
    }

    public PersonnageIG getLanceur() {
        return lanceur;
    }
}

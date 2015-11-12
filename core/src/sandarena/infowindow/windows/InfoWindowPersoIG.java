package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.Icone;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoPersoIG;
import sandarena.match.partie.jeu.compcase.PersonnageIG;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoWindowPersoIG extends InfoWindow {


    private PersonnageIG persoIG;

    public InfoWindowPersoIG(PersonnageIG emplacement) {
        super(emplacement);
        if (emplacement != null) {
            persoIG = emplacement;
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 2.5), (float) ((Resolution.height / 4)));
            icone = new Icone(this, persoIG.getDonnee().commun.image);
            info = new InfoPersoIG(this);
            this.addActor(icone);
            this.addActor(info);
            checkPlace();
            affiche = true;
        }
    }

    public PersonnageIG getPersoIG() {
        return persoIG;
    }
}

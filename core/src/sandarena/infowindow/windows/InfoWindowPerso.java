package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.Icone;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoPerso;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoWindowPerso extends InfoWindow {
    private  Personnage perso;

    public InfoWindowPerso(Personnage emplacement) {
        super(emplacement);
        if (emplacement != null) {
            this.perso = emplacement;
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 2.5), (float) ((Resolution.height / 4)));
            icone = new Icone(this, getPerso().commun.image);
            info = new InfoPerso(this);
            this.addActor(icone);
            this.addActor(info);
            affiche = true;
            checkPlace();
        }
    }

    public Personnage getPerso() {
        return perso;
    }
}

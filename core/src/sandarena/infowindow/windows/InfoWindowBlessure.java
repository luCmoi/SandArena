package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.Icone;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoBlessure;
import sandarena.joueur.blessure.Blessure;

/**
 * Created by lucmo on 20/11/2015.
 */
public class InfoWindowBlessure extends InfoWindow {
    private Blessure blessure;

    public InfoWindowBlessure(Blessure emplacement) {
        super(emplacement);
        if (emplacement != null) {
            this.blessure = emplacement;
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 3.5), (float) ((Resolution.height / 4)));
            icone = new Icone(this, getBlessure().donnee.image);
            info = new InfoBlessure(this);
            this.addActor(icone);
            this.addActor(info);
            affiche = true;
            checkPlace();
        }
    }

    public Blessure getBlessure() {
        return blessure;
    }
}

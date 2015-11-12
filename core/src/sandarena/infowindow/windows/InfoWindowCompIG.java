package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.Icone;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoCompIG;
import sandarena.match.partie.jeu.compcase.CompetenceIG;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoWindowCompIG extends InfoWindow {

    private  CompetenceIG compIG;

    public InfoWindowCompIG(CompetenceIG emplacement) {
        super(emplacement);
        if (emplacement != null) {
            this.compIG =  emplacement;
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 3.5), (float) ((Resolution.height / 4)));
            icone = new Icone(this, compIG.info.image);
            info = new InfoCompIG(this);
            this.addActor(icone);
            this.addActor(info);
            affiche = true;
            checkPlace();
        }
    }

    public CompetenceIG getCompIG() {
        return compIG;
    }
}
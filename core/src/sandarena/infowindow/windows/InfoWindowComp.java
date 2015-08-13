package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import sandarena.Resolution;
import sandarena.donnee.BanqueCompetence;
import sandarena.infowindow.Icone;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoComp;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoWindowComp extends InfoWindow {
    private BanqueCompetence.EntreeCompetence comp;

    public InfoWindowComp(BanqueCompetence.EntreeCompetence emplacement) {
        super(emplacement);
        if (emplacement != null) {
            this.comp = emplacement;
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 3.5), (float) ((Resolution.height / 4)));
            icone = new Icone(this, comp.image);
            info = new InfoComp(this);
            this.addActor(icone);
            this.addActor(info);
            affiche = true;
            checkPlace();
        }
    }
}

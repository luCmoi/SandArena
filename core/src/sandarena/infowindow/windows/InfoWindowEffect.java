package sandarena.infowindow.windows;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.infowindow.InfoWindow;
import sandarena.infowindow.info.InfoEffect;
import sandarena.partie.jeu.compcase.effet.EffetBuf;
import sandarena.partie.gui.interfacep.empinterface.EmplacementEffet;

/**
 * Created by Guillaume on 13/08/2015.
 */
public class InfoWindowEffect extends InfoWindow {
    private  ArrayList<EffetBuf> effet;

    public InfoWindowEffect(EmplacementEffet emplacement) {
        super(emplacement);
        if (emplacement != null) {
            this.effet = emplacement.getEffets();
            info = new InfoEffect(this);
            this.addActor(info);
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 1.5), TAILLE_EFFET * info.getTexte().length);
            this.getChildren().get(0).setBounds(0, 0, getWidth(), getHeight());
            affiche = true;
            checkPlace();
        }
    }

    public ArrayList<EffetBuf> getEffet() {
        return effet;
    }
}

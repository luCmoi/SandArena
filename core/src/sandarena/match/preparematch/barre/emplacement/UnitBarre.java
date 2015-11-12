package sandarena.match.preparematch.barre.emplacement;

import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.interfaceutil.emplacement.EmplacementPerso;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 13/08/2015.
 */
class UnitBarre extends EmplacementPerso {

    public UnitBarre(EmplacementBarre container, Personnage perso) {
        super(container);
        this.perso = perso;
        this.addListenerBasique();
        this.setTouchable(Touchable.enabled);
    }


    public void pression() {
        super.pression();
        if (perso != null) {
            ((EmplacementBarre)container).fenetre();
        }
    }

}


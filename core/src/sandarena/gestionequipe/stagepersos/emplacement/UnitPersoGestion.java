package sandarena.gestionequipe.stagepersos.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;
import sandarena.gestionequipe.surcouche.SurcoucheGestionEquipe;
import sandarena.interfaceutil.emplacement.EmplacementPerso;

/**
 * Created by Guillaume on 23/07/2015.
 */
class UnitPersoGestion extends EmplacementPerso {

    public UnitPersoGestion(EmplacementPersoGestion container) {
        super(container);
        addListenerBasique();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso == null) {
            batch.draw(Utili.plus, getX(), getY(), getWidth(),getHeight());
        }
    }


    public void clique(){
        Son.menuSelect.play();
        if (perso == null){
            ((SurcoucheGestionEquipe)((EmplacementPersoGestion)container).getContainer().getContainer().getSurcouche()).activateAchatPerso(container.getPlace());
        }
    }
}

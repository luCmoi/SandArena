package sandarena.gestionequipe.stagepersos.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowPerso;
import sandarena.joueur.Personnage;

/**
 * Created by Guillaume on 23/07/2015.
 */
class UnitPersoGestion extends Actor {
    private EmplacementPersoGestion container;
    private Personnage perso;
    private InfoWindowPerso info;

    public UnitPersoGestion(EmplacementPersoGestion container) {
        this.container = container;
        this.setBounds(0, 0, container.getHeight(), container.getHeight());
        this.addListener(new UnitPersoGestionListener(this));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso != null) {
            batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
            ((UnitPersoGestionListener) (getListeners().get(0))).update();
        }else{
            batch.draw(Utili.plus, getX(), getY(), getWidth(),getHeight());
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public Personnage getPerso() {
        return perso;
    }

    public EmplacementPersoGestion getContainer() {
        return container;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    public void pression() {
        if (perso != null) {
            this.info = new InfoWindowPerso(perso);
            container.getContainer().getContainer().getSurcouche().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }

    public void clique(){
        Son.menuSelect.play();
        if (perso == null){
            container.getContainer().getContainer().getSurcouche().activateAchatPerso(container.getPlace());
        }
    }
}

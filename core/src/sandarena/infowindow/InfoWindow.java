package sandarena.infowindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.joueur.Personnage;
import sandarena.joueur.competence.Competence;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.gui.interfacep.empinterface.EmplacementComp;
import sandarena.partie.gui.interfacep.empinterface.EmplacementEffet;
import sandarena.partie.gui.interfacep.empinterface.EmplacementPerso;


/**
 * Created by Guillaume on 11/06/2015.
 */
public abstract class InfoWindow extends Group {
    protected Icone icone;
    protected Info info;
    protected static final int DIFF = 100;
    protected static final int TAILLE_EFFET = 20;
    protected boolean affiche = false;

    public InfoWindow(Object emplacement) {
    }

    protected void checkPlace(){
        if (getX()+ getWidth() > Resolution.width){
            setX(Resolution.width-getWidth());
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (affiche) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
            super.draw(batch, parentAlpha);
        }
    }

    public void dispose(){
        clear();
        remove();
        if (icone != null) {
            this.icone.dispose();
        }
        this.info.dispose();
        icone = null;
        info = null;
    }

}

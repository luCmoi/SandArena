package sandarena.partie.gui.interfacep.infowindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.gui.interfacep.EmplacementComp;
import sandarena.partie.gui.interfacep.EmplacementInterface;
import sandarena.partie.gui.interfacep.EmplacementPerso;


/**
 * Created by Guillaume on 11/06/2015.
 */
public class InfoWindow extends Group {
    private PersonnageIG perso;
    private CompetenceIG comp;
    private Icone icone;
    private Info info;

    public InfoWindow(EmplacementInterface emplacement) {
        if (emplacement instanceof  EmplacementPerso){
            this.perso = ((EmplacementPerso)emplacement).getPerso();
            setBounds(Gdx.input.getX(), ((Resolution.height / 4 * 4) - Gdx.input.getY()), (Resolution.height / 4) * 2, Resolution.height / 4);
        } else if (emplacement instanceof  EmplacementComp){
            this.comp = ((EmplacementComp)emplacement).getCompetenceIG();
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()), (Resolution.height/4) *3 ,(float)((Resolution.height/4)/1.5));
        }
        icone = new Icone(this);
        info = new Info(this);
        this.addActor(icone);
        this.addActor(info);
        if (this.perso == null && this.comp == null){
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.perso != null || this.comp != null) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
            super.draw(batch, parentAlpha);
        }
    }

    public void dispose(){
        perso = null;
        comp = null;
        clear();
        remove();
        this.icone.dispose();
        this.info.dispose();
        icone = null;
        info = null;
    }

    public PersonnageIG getPerso() {
        return perso;
    }

    public CompetenceIG getComp() {
        return comp;
    }
}

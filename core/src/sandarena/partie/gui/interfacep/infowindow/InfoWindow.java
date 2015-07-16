package sandarena.partie.gui.interfacep.infowindow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.gui.interfacep.empinterface.EmplacementComp;
import sandarena.partie.gui.interfacep.empinterface.EmplacementEffet;
import sandarena.partie.gui.interfacep.empinterface.EmplacementInterface;
import sandarena.partie.gui.interfacep.empinterface.EmplacementPerso;


/**
 * Created by Guillaume on 11/06/2015.
 */
public class InfoWindow extends Group {
    private PersonnageIG perso;
    private CompetenceIG comp;
    private ArrayList<EffetBuf> effet;
    private Icone icone;
    private Info info;
    private static final int DIFF = 100;
    private static final int TAILLE_EFFET = 20;

    public InfoWindow(EmplacementInterface emplacement) {
        if (emplacement instanceof  EmplacementPerso){
            this.perso = ((EmplacementPerso)emplacement).getPerso();
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY())+ DIFF, (float)((Resolution.height/4) *2.5) ,(float)((Resolution.height/4)));
            icone = new Icone(this);
            info = new Info(this);
            this.addActor(icone);
            this.addActor(info);
        } else if (emplacement instanceof  EmplacementComp){
            this.comp = ((EmplacementComp)emplacement).getCompetenceIG();
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float)((Resolution.height/4) *3.5) ,(float)((Resolution.height/4)));
            icone = new Icone(this);
            info = new Info(this);
            this.addActor(icone);
            this.addActor(info);
        }else if (emplacement instanceof EmplacementEffet){
            this.effet = ((EmplacementEffet)emplacement).getEffets();
            info = new Info(this);
            this.addActor(info);
            setBounds(Gdx.input.getX(), ((Resolution.height) - Gdx.input.getY()) + DIFF, (float) ((Resolution.height / 4) * 1.5), TAILLE_EFFET * info.getTexte().length);
            this.getChildren().get(0).setBounds(0, 0, getWidth(), getHeight());
        }
        if (getX()+ getWidth() > Resolution.width){
            setX(Resolution.width-getWidth());
        }
        if (this.perso == null && this.comp == null && this.effet == null){
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (this.perso != null || this.comp != null || this.getEffet() != null) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
            super.draw(batch, parentAlpha);
        }
    }

    public void dispose(){
        perso = null;
        comp = null;
        effet =null;
        clear();
        remove();
        if (icone != null) {
            this.icone.dispose();
        }
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

    public ArrayList<EffetBuf> getEffet() {
        return effet;
    }
}

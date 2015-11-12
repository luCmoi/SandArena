package sandarena.interfaceutil.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Utili;
import sandarena.interfaceutil.stage.ScreenSurcouche;
import sandarena.joueur.Personnage;
import sandarena.match.partie.jeu.compcase.PersonnageIG;

/**
 * Created by lucmo on 12/11/2015.
 */
public abstract class Emplacement extends Group {
    protected ScreenSurcouche screen;
    protected byte place;
    protected EmplacementPerso perso;
    protected EmplacementCompetence[] comp = new EmplacementCompetence[4];

    public Emplacement(ScreenSurcouche container, int place) {
        this.screen = container;
        this.place = (byte)place;
    }

    protected void linkEmplacementPerso(){
        this.perso = new EmplacementPerso(this);
        this.perso.addListenerBasique();
        this.addActor(perso);
    }

    protected void linkEmplacementCompetence(){
        for (int i = 0; i < 4; i++) {
            comp[i] = new EmplacementCompetence(this, i);
            comp[i].addListenerBasique();
            this.addActor(comp[i]);
        }
    }

    public Personnage getPerso() {
        return perso.getPerso();
    }

    public void setPerso(Personnage perso){
        if (perso != null) {
            this.perso.setPerso(perso);
            for (int i = 0; i < 4; i++) {
                comp[i].setComp(perso.getCompetences()[i]);
            }
        }else{
            this.perso.setPerso(null);
            for (int i = 0; i < 4; i++) {
                comp[i].setComp(null);
            }
        }
    }

    public void setPersoIG(PersonnageIG perso) {
        if (perso != null) {
            this.perso.setPersoIG(perso);
            for (int i = 0; i < 4; i++) {
                comp[i].setCompIG(perso.getCompetence()[i]);
            }
        }else{
            this.perso.setPersoIG(null);
            for (int i = 0; i < 4; i++) {
                comp[i].setCompIG(null);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.fond,getX(),getY(),getWidth(),getHeight());
        super.draw(batch, parentAlpha);
    }

    public ScreenSurcouche getScreen() {
        return screen;
    }

    public byte getPlace() {
        return place;
    }

    public void dispose() {
        screen = null;
        if (perso != null){
            perso.dispose();
            perso = null;
        }
        if (comp != null){
            for (int i = 0; i < 4; i++) {
                if (comp[i] != null){
                    comp[i].dispose();
                }
            }
            comp = null;
        }
        clear();
        remove();
    }
}

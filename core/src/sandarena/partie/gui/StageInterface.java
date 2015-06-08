package sandarena.partie.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.partie.Partie;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.PersonnageIG;

/**
 * @author Guillaume
 */
public class StageInterface extends Stage {
    private Partie partie;
    private PersonnageIG personnageActif;
    private ArrayList<EmplacementComp> emplacementComps;

    public StageInterface(Viewport viewport, Batch batch) {
        super(viewport, batch);
        emplacementComps = new ArrayList<EmplacementComp>();
        emplacementComps.add(new EmplacementComp(0, this));
        emplacementComps.add(new EmplacementComp(1, this));
        emplacementComps.add(new EmplacementComp(2, this));
        emplacementComps.add(new EmplacementComp(3, this));
        for (EmplacementComp emp : emplacementComps) {
            this.addActor(emp);
        }
    }

    @Override
    public void draw() {
        super.draw();

    }

    public void setPersonnageActif(PersonnageIG perso) {
        this.personnageActif = perso;
        for (EmplacementComp emp : emplacementComps) {
            emp.setCompetenceIG(perso.getCompetence()[emp.getPlace()]);
        }
    }
    public void setPartie(Partie partie){
        this.partie=partie;
    }

    public Partie getPartie(){
        return this.partie;
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX,stageY,touchable);
    }
}

package sandarena.partie.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.partie.Case;
import sandarena.partie.Partie;
import sandarena.partie.compcase.PersonnageIG;

/**
 * @author Guillaume
 */
public class StageInterface extends Stage {
    private Partie partie;
    private PersonnageIG personnageActif;
    private Case caseSelect;
    private ArrayList<EmplacementComp> emplacementCompsActif;
    private ArrayList<EmplacementComp> emplacementCompsSelect;
    private EmplacementPerso emplacementPersoActif;
    private EmplacementPerso emplacementPersoSelect;
    protected int tailleCoteHeight;
    protected int tailleCoteWidth;

    public StageInterface(Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.tailleCoteHeight =  (int)this.getHeight();
        //this.tailleCoteWidth = (int)(this.getHeight()* Resolution.ratioHeight);
        this.tailleCoteWidth = (int)(this.getWidth()/8);
        emplacementPersoActif = new EmplacementPerso(0, this);
        emplacementPersoSelect = new EmplacementPerso(1, this);
        this.addActor(emplacementPersoActif);
        this.addActor(emplacementPersoSelect);
        emplacementCompsActif = new ArrayList<EmplacementComp>();
        emplacementCompsActif.add(new EmplacementComp(0, this));
        emplacementCompsActif.add(new EmplacementComp(1, this));
        emplacementCompsActif.add(new EmplacementComp(2, this));
        emplacementCompsActif.add(new EmplacementComp(3, this));
        for (EmplacementComp emp : emplacementCompsActif) {
            this.addActor(emp);
        }
        emplacementCompsSelect = new ArrayList<EmplacementComp>();
        emplacementCompsSelect.add(new EmplacementComp(4, this));
        emplacementCompsSelect.add(new EmplacementComp(5, this));
        emplacementCompsSelect.add(new EmplacementComp(6, this));
        emplacementCompsSelect.add(new EmplacementComp(7, this));
        for (EmplacementComp emp : emplacementCompsSelect) {
            this.addActor(emp);
        }
    }

    @Override
    public void draw() {
        super.draw();

    }

    public void setPersonnageActif(PersonnageIG perso) {
        this.personnageActif = perso;
        emplacementPersoActif.perso = perso;
        for (EmplacementComp emp : emplacementCompsActif) {
            emp.setCompetenceIG(perso.getCompetence()[emp.getPlace()]);
        }
    }

    public Partie getPartie() {
        return this.partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX, stageY, touchable);
    }

    public Case getCaseSelect() {
        return caseSelect;
    }

    public void setCaseSelect(Case caseSelect) {
        this.caseSelect = caseSelect;
        if (caseSelect.getPresence() != null) {
            emplacementPersoSelect.perso = caseSelect.getPresence();
        } else {
            emplacementPersoSelect.perso = null;
        }
        for (EmplacementComp emp : emplacementCompsSelect) {
            if (caseSelect.getPresence() != null) {
                emp.setCompetenceIG(caseSelect.getPresence().getCompetence()[emp.getPlace() - 4]);
            } else {
                emp.setCompetenceIG(null);
            }
        }
    }
}

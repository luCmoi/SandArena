package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.partie.Case;
import sandarena.partie.Partie;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.gui.interfacep.empinterface.EmplacementComp;
import sandarena.partie.gui.interfacep.empinterface.EmplacementEffet;
import sandarena.partie.gui.interfacep.empinterface.EmplacementPerso;

public class StageInterface extends Stage {
    private Partie partie;
    private ArrayList<EmplacementComp> emplacementCompsActif;
    private ArrayList<EmplacementComp> emplacementCompsSelect;
    private ArrayList<EmplacementEffet> emplacementEffetsActif;
    private ArrayList<EmplacementEffet> emplacementEffetsSelect;
    private EmplacementPerso emplacementPersoActif;
    private EmplacementPerso emplacementPersoSelect;
    public int tailleCoteHeight;
    public int tailleCoteWidth;

    public StageInterface(Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.tailleCoteHeight =  (int)this.getHeight();
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
        emplacementEffetsActif = new ArrayList<EmplacementEffet>();
        emplacementEffetsActif.add(new EmplacementEffet(0, this));
        emplacementEffetsActif.add(new EmplacementEffet(1, this));
        emplacementEffetsActif.add(new EmplacementEffet(2, this));
        emplacementEffetsActif.add(new EmplacementEffet(3, this));
        emplacementEffetsActif.add(new EmplacementEffet(4, this));
        emplacementEffetsActif.add(new EmplacementEffet(5, this));
        emplacementEffetsActif.add(new EmplacementEffet(6, this));
        emplacementEffetsActif.add(new EmplacementEffet(7, this));

        for (EmplacementComp emp : emplacementCompsActif) {
            this.addActor(emp);
        }
        for (EmplacementEffet emp : emplacementEffetsActif) {
            this.addActor(emp);
        }
        emplacementCompsSelect = new ArrayList<EmplacementComp>();
        emplacementCompsSelect.add(new EmplacementComp(4, this));
        emplacementCompsSelect.add(new EmplacementComp(5, this));
        emplacementCompsSelect.add(new EmplacementComp(6, this));
        emplacementCompsSelect.add(new EmplacementComp(7, this));
        emplacementEffetsSelect = new ArrayList<EmplacementEffet>();
        emplacementEffetsSelect.add(new EmplacementEffet(8, this));
        emplacementEffetsSelect.add(new EmplacementEffet(9, this));
        emplacementEffetsSelect.add(new EmplacementEffet(10, this));
        emplacementEffetsSelect.add(new EmplacementEffet(11, this));
        emplacementEffetsSelect.add(new EmplacementEffet(12, this));
        emplacementEffetsSelect.add(new EmplacementEffet(13, this));
        emplacementEffetsSelect.add(new EmplacementEffet(14, this));
        emplacementEffetsSelect.add(new EmplacementEffet(15, this));
        for (EmplacementComp emp : emplacementCompsSelect) {
            this.addActor(emp);
        }
        for (EmplacementEffet emp : emplacementEffetsSelect) {
            this.addActor(emp);
        }
    }

    @Override
    public void draw() {
        super.draw();

    }

    public void dispose(){
        super.dispose();
        if (emplacementPersoActif != null) {
            emplacementPersoActif.dispose();
            emplacementPersoActif = null;
        }
        if (emplacementPersoSelect != null) {
            emplacementPersoSelect.dispose();
            emplacementPersoSelect = null;
        }
        for(EmplacementComp c : emplacementCompsActif){
            if (c != null){
                c.dispose();
                c = null;
            }
        }
        for(EmplacementComp c : emplacementCompsSelect){
            if (c != null){
                c.dispose();
                c = null;
            }
        }
        for(EmplacementEffet c : emplacementEffetsSelect){
            if (c != null){
                c.dispose();
                c = null;
            }
        }
        for(EmplacementEffet c : emplacementEffetsActif){
            if (c != null){
                c.dispose();
                c = null;
            }
        }
        emplacementEffetsActif.clear();
        emplacementEffetsActif = null;
        emplacementEffetsSelect.clear();
        emplacementEffetsSelect = null;
        emplacementCompsActif.clear();
        emplacementCompsActif = null;
        emplacementCompsSelect.clear();
        emplacementCompsSelect = null;
        clear();
    }

    public void setPersonnageActif(PersonnageIG perso) {
        emplacementPersoActif.setPerso(perso);
        for (EmplacementComp emp : emplacementCompsActif) {
            emp.setCompetenceIG(perso.getCompetence()[emp.getPlace()]);
        }
        for (EmplacementEffet emp : emplacementEffetsActif) {
            emp.setEffet(perso);
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

    public void setCaseSelect(Case caseSelect) {
        if (caseSelect.getPresence() != null) {
            emplacementPersoSelect.setPerso(caseSelect.getPresence());
        } else {
            emplacementPersoSelect.setPerso(null);
        }
        for (EmplacementComp emp : emplacementCompsSelect) {
            if (caseSelect.getPresence() != null) {
                emp.setCompetenceIG(caseSelect.getPresence().getCompetence()[emp.getPlace() - 4]);
            } else {
                emp.setCompetenceIG(null);
            }
        }
        for (EmplacementEffet emp : emplacementEffetsSelect) {
            if (caseSelect.getPresence() != null) {
                emp.setEffet(caseSelect.getPresence());
            } else {
                emp.setEffet(null);
            }
        }
    }
}

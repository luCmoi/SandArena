package sandarena.partie.gui.interfacep;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.partie.Case;
import sandarena.partie.Partie;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.gui.interfacep.empinterface.EmplacementPerso;

public class StageInterface extends Stage {
    private Partie partie;
    private ArrayList<sandarena.partie.gui.interfacep.empinterface.EmplacementComp> emplacementCompsActif;
    private ArrayList<sandarena.partie.gui.interfacep.empinterface.EmplacementComp> emplacementCompsSelect;
    private sandarena.partie.gui.interfacep.empinterface.EmplacementPerso emplacementPersoActif;
    private sandarena.partie.gui.interfacep.empinterface.EmplacementPerso emplacementPersoSelect;
    public int tailleCoteHeight;
    public int tailleCoteWidth;

    public StageInterface(Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.tailleCoteHeight =  (int)this.getHeight();
        this.tailleCoteWidth = (int)(this.getWidth()/8);
        emplacementPersoActif = new sandarena.partie.gui.interfacep.empinterface.EmplacementPerso(0, this);
        emplacementPersoSelect = new EmplacementPerso(1, this);
        this.addActor(emplacementPersoActif);
        this.addActor(emplacementPersoSelect);
        emplacementCompsActif = new ArrayList<sandarena.partie.gui.interfacep.empinterface.EmplacementComp>();
        emplacementCompsActif.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(0, this));
        emplacementCompsActif.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(1, this));
        emplacementCompsActif.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(2, this));
        emplacementCompsActif.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(3, this));
        for (sandarena.partie.gui.interfacep.empinterface.EmplacementComp emp : emplacementCompsActif) {
            this.addActor(emp);
        }
        emplacementCompsSelect = new ArrayList<sandarena.partie.gui.interfacep.empinterface.EmplacementComp>();
        emplacementCompsSelect.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(4, this));
        emplacementCompsSelect.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(5, this));
        emplacementCompsSelect.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(6, this));
        emplacementCompsSelect.add(new sandarena.partie.gui.interfacep.empinterface.EmplacementComp(7, this));
        for (sandarena.partie.gui.interfacep.empinterface.EmplacementComp emp : emplacementCompsSelect) {
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
        for(sandarena.partie.gui.interfacep.empinterface.EmplacementComp c : emplacementCompsActif){
            if (c != null){
                c.dispose();
                c = null;
            }
        }
        for(sandarena.partie.gui.interfacep.empinterface.EmplacementComp c : emplacementCompsSelect){
            if (c != null){
                c.dispose();
                c = null;
            }
        }
        emplacementCompsActif.clear();
        emplacementCompsActif = null;
        emplacementCompsSelect.clear();
        emplacementCompsSelect = null;
        clear();
    }

    public void setPersonnageActif(PersonnageIG perso) {
        emplacementPersoActif.setPerso(perso);
        for (sandarena.partie.gui.interfacep.empinterface.EmplacementComp emp : emplacementCompsActif) {
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

    public void setCaseSelect(Case caseSelect) {
        if (caseSelect.getPresence() != null) {
            emplacementPersoActif.setPerso(caseSelect.getPresence());
        } else {
            emplacementPersoSelect.setPerso(null);
        }
        for (sandarena.partie.gui.interfacep.empinterface.EmplacementComp emp : emplacementCompsSelect) {
            if (caseSelect.getPresence() != null) {
                emp.setCompetenceIG(caseSelect.getPresence().getCompetence()[emp.getPlace() - 4]);
            } else {
                emp.setCompetenceIG(null);
            }
        }
    }
}

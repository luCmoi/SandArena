package sandarena.match.partie.gui.interfacep;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp;
import sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet;
import sandarena.match.partie.jeu.Case;
import sandarena.match.partie.jeu.Partie;

public class StageInterface extends Stage {
    private sandarena.match.partie.jeu.Partie partie;
    private ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp> emplacementCompsActif;
    private ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp> emplacementCompsSelect;
    private ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet> emplacementEffetsActif;
    private ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet> emplacementEffetsSelect;
    private ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton> emplacementBoutons;
    private sandarena.match.partie.gui.interfacep.empinterface.EmplacementPerso emplacementPersoActif;
    private sandarena.match.partie.gui.interfacep.empinterface.EmplacementPerso emplacementPersoSelect;
    public int tailleCoteHeight;
    private int tailleCoteWidth;

    public StageInterface(Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.tailleCoteHeight = (int) this.getHeight();
        this.tailleCoteWidth = (int) (this.getWidth() / 8);
        this.addActor(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementFond(this));
        emplacementPersoActif = new sandarena.match.partie.gui.interfacep.empinterface.EmplacementPerso(0, this);
        emplacementPersoSelect = new sandarena.match.partie.gui.interfacep.empinterface.EmplacementPerso(1, this);
        this.addActor(emplacementPersoActif);
        this.addActor(emplacementPersoSelect);
        emplacementCompsActif = new ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp>();
        emplacementCompsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(0, this));
        emplacementCompsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(1, this));
        emplacementCompsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(2, this));
        emplacementCompsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(3, this));
        emplacementEffetsActif = new ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet>();
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(0, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(1, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(2, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(3, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(4, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(5, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(6, this));
        emplacementEffetsActif.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(7, this));

        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp emp : emplacementCompsActif) {
            this.addActor(emp);
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet emp : emplacementEffetsActif) {
            this.addActor(emp);
        }
        emplacementCompsSelect = new ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp>();
        emplacementCompsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(4, this));
        emplacementCompsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(5, this));
        emplacementCompsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(6, this));
        emplacementCompsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp(7, this));
        emplacementEffetsSelect = new ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet>();
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(8, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(9, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(10, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(11, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(12, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(13, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(14, this));
        emplacementEffetsSelect.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet(15, this));
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp emp : emplacementCompsSelect) {
            this.addActor(emp);
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet emp : emplacementEffetsSelect) {
            this.addActor(emp);
        }
        emplacementBoutons = new ArrayList<sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton>();
        emplacementBoutons.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton(0, this));
        emplacementBoutons.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton(1, this));
        emplacementBoutons.add(new sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton(2, this));
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton emp : emplacementBoutons) {
            this.addActor(emp);
        }
    }

    public void dispose() {
        super.dispose();
        if (emplacementPersoActif != null) {
            emplacementPersoActif.dispose();
            emplacementPersoActif = null;
        }
        if (emplacementPersoSelect != null) {
            emplacementPersoSelect.dispose();
            emplacementPersoSelect = null;
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp c : emplacementCompsActif) {
            if (c != null) {
                c.dispose();
                c = null;
            }
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementComp c : emplacementCompsSelect) {
            if (c != null) {
                c.dispose();
                c = null;
            }
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet c : emplacementEffetsSelect) {
            if (c != null) {
                c.dispose();
                c = null;
            }
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementEffet c : emplacementEffetsActif) {
            if (c != null) {
                c.dispose();
                c = null;
            }
        }
        for (sandarena.match.partie.gui.interfacep.empinterface.EmplacementBouton c : emplacementBoutons) {
            if (c != null) {
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
        emplacementBoutons.clear();
        emplacementBoutons = null;
        clear();
    }

    public void setPersonnageActif(sandarena.match.partie.jeu.compcase.PersonnageIG perso) {
        emplacementPersoActif.setPerso(perso);
        for (EmplacementComp emp : emplacementCompsActif) {
            if (perso != null) {
                emp.setCompetenceIG(perso.getCompetence()[emp.getPlace()]);
            } else {
                emp.setCompetenceIG(null);
            }
        }
        for (EmplacementEffet emp : emplacementEffetsActif) {
            emp.setEffet(perso);
        }
    }

    public Partie getPartie() {
        return this.partie;
    }

    public void setPartie(sandarena.match.partie.jeu.Partie partie) {
        this.partie = partie;
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        return super.hit(stageX, stageY, touchable);
    }

    public void setCaseSelect(Case caseSelect) {
        if (caseSelect != null) {
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

    public void recharge() {
        if (emplacementPersoSelect.getPerso() != null) {
            setCaseSelect(emplacementPersoSelect.getPerso().getContainer());
        }
    }
}

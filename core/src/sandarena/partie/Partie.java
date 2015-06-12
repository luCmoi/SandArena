package sandarena.partie;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.joueur.Joueur;
import sandarena.partie.compcase.CompetenceIG;
import sandarena.partie.compcase.JoueurIG;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.gui.Camera;
import sandarena.partie.gui.PartieListener;
import sandarena.partie.gui.ScreenPartie;
import sandarena.partie.gui.interfacep.StageInterface;

/**
 * Stock toutes les donnees relatives à une instance de partie
 *
 * @author Guillaume
 */
public class Partie extends Stage {

    private final int widthTailleTotale;
    private final int heightTailleTotale;
    private Case[][] plateau;
    private ScreenPartie container;
    private JoueurIG joueur1;
    private JoueurIG joueur2;
    private PersonnageIG personnageActif;
    private Group groupeCase;
    private Camera camera;
    private ArrayList<Case> chemin;
    private StageInterface stageInterface;
    private CompetenceIG competenceActive;
    private Case caseSelect = null;

    /**
     * Permet de créer une nouvelle partie a partir de son conteneur, et plus
     * tard de donne de la carte ainsi que des deux joueurs
     *
     * @param container
     * @param joueur1
     * @param joueur2
     * @param viewport
     * @param batch
     */
    public Partie(ScreenPartie container, Joueur joueur1, Joueur joueur2, Viewport viewport, Batch batch) {
        super(viewport, batch);
        this.container = container;
        this.stageInterface = this.container.getStageInterface();
        this.getViewport().setCamera(new Camera(this));

        this.camera = (Camera) (this.getViewport().getCamera());
        this.joueur1 = new JoueurIG(joueur1);
        this.joueur2 = new JoueurIG(joueur2);
        //Changera lorsqu'on saura a partir de quoit creer la partie
        int coteTmp = 25;
        plateau = new Case[coteTmp][coteTmp];
        this.groupeCase = new Group();
        for (int x = 0; x < coteTmp; x++) {
            for (int y = 0; y < coteTmp; y++) {
                plateau[x][y] = new Case(x, y, this);
                this.groupeCase.addActor(plateau[x][y]);
            }
        }
        this.addActor(groupeCase);
        widthTailleTotale = this.plateau.length * Resolution.widthCase;
        heightTailleTotale = this.plateau[0].length * Resolution.heightCase;
        this.addCaptureListener(new PartieListener(this));
        chemin = new ArrayList<Case>();
        lancement();
    }

    @Override
    public void draw() {
        ((Camera) getViewport().getCamera()).updateExt();
        super.draw();
    }

    private void lancement() {
        /* Placement des unitées
         */
        getPlateau()[5][5].entrePresence(getJoueur1().getPersonnages().get(0));
        getPlateau()[7][5].entrePresence(getJoueur2().getPersonnages().get(0));
        tour();
    }

    private boolean victoire() {
        return false;
    }

    private void tour() {
        for (PersonnageIG perso : joueur1.getPersonnages()) {
            perso.setAAgi(false);
        }
        for (PersonnageIG perso : joueur2.getPersonnages()) {
            perso.setAAgi(false);
        }
        phase(getJoueur1());
    }


    private void phase(JoueurIG joueur) {
        for (PersonnageIG perso : joueur.getPersonnages()) {
            if (!perso.isAAgi()) {
                this.setPersonnageActif(perso);
                return;
            }
        }
        if (joueur.equals(getJoueur1())) {
            phase(getJoueur2());
        } else {
            tour();
        }
    }


    public void finPerso() {
        phase(personnageActif.getPossesseur());
    }

    /**
     * Appelle la supression du plateau de jeu avant de supprimer cet element
     */
    @Override
    public void dispose() {
        super.dispose();
        setContainer(null);
        groupeCase.remove();
        for (Case[] tabC : getPlateau()) {
            for (Case c : tabC) {
                groupeCase.removeActor(c);
                c.dispose();
            }
        }
        groupeCase = null;
        setPlateau(null);
        dispose();
        camera.dispose();
        camera = null;
        setViewport(null);
        joueur1 = null;
        joueur2 = null;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }

    public ScreenPartie getContainer() {
        return container;
    }

    public void setContainer(ScreenPartie container) {
        this.container = container;
    }

    @Override
    public Camera getCamera() {
        return this.camera;
    }

    public JoueurIG getJoueur1() {
        return joueur1;
    }

    public JoueurIG getJoueur2() {
        return joueur2;
    }

    public PersonnageIG getPersonnageActif() {
        return personnageActif;
    }

    public void setPersonnageActif(PersonnageIG personnageActif) {
        if (personnageActif != null) {
            this.personnageActif = personnageActif;
            AlgorithmePathfinding.calculCaseAccessible(personnageActif.getVitesseRestante(), personnageActif.getContainer(), plateau);
            this.stageInterface.setPersonnageActif(personnageActif);
        }
    }

    public int getWidthTailleTotale() {
        return widthTailleTotale;
    }

    public int getHeightTailleTotale() {
        return heightTailleTotale;
    }

    public void selectChemin(Case caseC) {
        videChemin();
        caseC.setChemin(true);
        caseC.setCible(true);
        chemin.add(caseC);
        while (caseC.getPredecesseur() != personnageActif.getContainer()) {
            caseC = caseC.getPredecesseur();
            caseC.setChemin(true);
            chemin.add(caseC);
        }
    }

    public void videChemin() {
        for (Case c : chemin) {
            c.setCible(false);
            c.setChemin(false);
        }
        chemin.clear();
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        Vector2 vScreen = stageToScreenCoordinates(new Vector2(stageX, stageY));
        if (Resolution.height - vScreen.y < this.container.getDifferenceBas()) {
            return stageInterface.hit(vScreen.x, Resolution.height - vScreen.y, touchable);
        } else {
            return super.hit(stageX, stageY, touchable);
        }
    }

    void deplacement() {
        for (int i = 1; i <= chemin.size(); i++) {
            personnageActif.mouvement(chemin.get(chemin.size() - i));
        }
        videChemin();
        AlgorithmePathfinding.calculCaseAccessible(personnageActif.getVitesseRestante(), personnageActif.getContainer(), plateau);
    }

    public CompetenceIG getCompetenceActive() {
        return competenceActive;
    }

    public void setCompetenceActive(CompetenceIG competenceActive) {
        this.competenceActive = competenceActive;
        if (this.competenceActive != null) {
            this.competenceActive.select(plateau);
        } else {
            for (Case[] c : plateau) {
                for (Case cc : c) {
                    cc.setCompetenceable(false);
                }
            }
        }
    }

    public void setCaseSelect(Case caseSelect) {
        if (this.caseSelect != null) {
            this.caseSelect.setSelect(false);
        }
        this.caseSelect = caseSelect;
        stageInterface.setCaseSelect(caseSelect);
    }
}

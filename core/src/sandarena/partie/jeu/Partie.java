package sandarena.partie.jeu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import sandarena.SandArena;
import sandarena.googleservice.ConnexionMatch;
import sandarena.donnee.carte.BanqueCarte;
import sandarena.donnee.carte.CaseSpeciale;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.joueur.competence.CompetenceActive;
import sandarena.partie.AlgorithmePathfinding;
import sandarena.partie.ScreenPartie;
import sandarena.partie.gui.Camera;
import sandarena.partie.gui.PartieListener;
import sandarena.partie.gui.interfacep.StageInterface;
import sandarena.partie.jeu.compcase.CompetenceIG;
import sandarena.partie.jeu.compcase.JoueurIG;
import sandarena.partie.jeu.compcase.PersonnageIG;

/**
 * Stock toutes les donnees relatives à une instance de partie
 *
 * @author Guillaume
 */
public class Partie extends Stage {

    private final int widthTailleTotale;
    private final int heightTailleTotale;
    private final boolean commence;
    private Case[][] plateau;
    private ScreenPartie container;
    private JoueurIG joueurActif;
    private JoueurIG joueurAutre;
    private PersonnageIG personnageActif;
    private Group groupeCase;
    private Camera camera;
    private ArrayList<Case> chemin;
    private StageInterface stageInterface;
    private CompetenceIG competenceActive;
    private Case caseSelect = null;
    private boolean bloquand = false;
    private boolean spawn;

    /**
     * Permet de créer une nouvelle partie a partir de son conteneur, et plus
     * tard de donne de la carte ainsi que des deux joueurs
     *
     * @param container
     * @param joueur1
     * @param personnagesActif
     * @param joueur2
     * @param personnagesAutre
     * @param commence
     * @param viewport
     * @param batch
     */
    public Partie(ScreenPartie container, Joueur joueur1, ArrayList<Personnage> personnagesActif, Joueur joueur2, ArrayList<Personnage> personnagesAutre, boolean commence, Viewport viewport, Batch batch) {
        super(viewport, batch);
        SandArena.googleService.printError(String.valueOf(commence));
        this.container = container;
        this.stageInterface = this.container.getStageInterface();
        this.getViewport().setCamera(new Camera(this));
        this.commence = commence;
        this.camera = (Camera) (this.getViewport().getCamera());
        this.joueurActif = new JoueurIG(joueur1, personnagesActif);
        this.joueurAutre = new JoueurIG(joueur2, personnagesAutre);
        BanqueCarte.DonneCarte carte = (BanqueCarte.DonneCarte) BanqueCarte.getEntree(BanqueCarte.getBanque(), container.getMap());
        plateau = new Case[carte.x][carte.y];
        this.groupeCase = new Group();
        for (int x = 0; x < carte.x; x++) {
            for (int y = 0; y < carte.y; y++) {
                plateau[x][y] = new Case(x, y, this, carte);
                this.groupeCase.addActor(plateau[x][y]);
            }
        }
        this.addActor(groupeCase);
        widthTailleTotale = this.plateau.length * Resolution.widthCase;
        heightTailleTotale = this.plateau[0].length * Resolution.heightCase;
        this.addCaptureListener(new PartieListener(this));
        chemin = new ArrayList<Case>();

        ConnexionMatch.ecouteMatch(this);

        lancement(carte.nbPerso, carte.zoneDepartActif, carte.zoneDepartAutre);
    }

    @Override
    public void draw() {
        ((Camera) getViewport().getCamera()).updateExt();
        super.draw();
    }

    private void lancement(byte nbPerso, ArrayList<CaseSpeciale> zoneDepartActif, ArrayList<CaseSpeciale> zoneDepartAutre) {
        if (commence) {
            for (int a = 0; a < nbPerso; a++) {
                getPlateau()[zoneDepartActif.get(a).getX()][zoneDepartActif.get(a).getY()].entrePresence(getJoueurActif().getPersonnages().get(a));
            }
            for (int a = 0; a < nbPerso; a++) {
                getPlateau()[zoneDepartAutre.get(a).getX()][zoneDepartAutre.get(a).getY()].entrePresence(getJoueurAutre().getPersonnages().get(a));
            }
            for (int a = 0; a < zoneDepartActif.size(); a++) {
                getPlateau()[zoneDepartActif.get(a).getX()][zoneDepartActif.get(a).getY()].setSpawnable(0);
            }
            for (int a = 0; a < zoneDepartAutre.size(); a++) {
                getPlateau()[zoneDepartAutre.get(a).getX()][zoneDepartAutre.get(a).getY()].setSpawnable(1);
            }
        } else {
            for (int a = 0; a < nbPerso; a++) {
                getPlateau()[zoneDepartAutre.get(a).getX()][zoneDepartAutre.get(a).getY()].entrePresence(getJoueurActif().getPersonnages().get(a));
            }
            for (int a = 0; a < nbPerso; a++) {
                getPlateau()[zoneDepartActif.get(a).getX()][zoneDepartActif.get(a).getY()].entrePresence(getJoueurAutre().getPersonnages().get(a));
            }
            for (int a = 0; a < zoneDepartAutre.size(); a++) {
                getPlateau()[zoneDepartAutre.get(a).getX()][zoneDepartAutre.get(a).getY()].setSpawnable(0);
            }
            for (int a = 0; a < zoneDepartActif.size(); a++) {
                getPlateau()[zoneDepartActif.get(a).getX()][zoneDepartActif.get(a).getY()].setSpawnable(1);
            }
        }
        spawn = true;
        if (commence) {
            setBloquand(false);
        } else {
            setBloquand(true);
        }
        setPersonnageActif(null);
    }

    private boolean victoire() {
        return false;
    }

    private void tour() {
        if (commence) {
            for (PersonnageIG perso : joueurActif.getPersonnages()) {
                perso.setAAgi(false);
                perso.tourBuff();
                perso.setVitesseRestante(perso.getDonnee().commun.vitesse);
                perso.infligeDot();
                perso.modifCaract();
            }
        }
        for (PersonnageIG perso : joueurAutre.getPersonnages()) {
            perso.setAAgi(false);
            perso.tourBuff();
            perso.setVitesseRestante(perso.getDonnee().commun.vitesse);
            perso.infligeDot();
            perso.modifCaract();
        }
        if (!commence) {
            for (PersonnageIG perso : joueurActif.getPersonnages()) {
                perso.setAAgi(false);
                perso.tourBuff();
                perso.setVitesseRestante(perso.getDonnee().commun.vitesse);
                perso.infligeDot();
                perso.modifCaract();
            }
            phase(getJoueurAutre());
        } else {
            phase(getJoueurActif());
        }
    }


    private void phase(JoueurIG joueur) {
        if (joueur.equals(getJoueurAutre())) {
            setBloquand(true);
        } else {
            setBloquand(false);
        }
        for (PersonnageIG perso : joueur.getPersonnages()) {
            if (!perso.isAAgi()) {
                this.setPersonnageActif(perso);
                return;
            }
        }
        SandArena.googleService.printError("Fin tour");
        if (joueur.equals(getJoueurActif())) {
            if (commence) {
                phase(getJoueurAutre());
            } else {
                tour();
            }
        } else {
            SandArena.googleService.printError("tour autre");
            if (commence) {
                tour();
            } else {
                phase(getJoueurActif());
            }
        }
    }

    public void finPhase() {
        if (spawn) {
            if (commence) {
                if (!isBloquand()) {
                    setBloquand(true);
                } else {
                    spawn = false;
                    tour();
                }
            } else {
                if (isBloquand()) {
                    setBloquand(false);
                } else {
                    spawn = false;
                    tour();
                }
            }
        } else {
            if (personnageActif.getPossesseur().equals(getJoueurActif())) {
                if (commence) {
                    for (PersonnageIG perso : getJoueurActif().getPersonnages()) {
                        perso.setAAgi(true);
                    }
                    phase(getJoueurAutre());
                } else {
                    for (PersonnageIG perso : getJoueurActif().getPersonnages()) {
                        perso.setAAgi(true);
                    }
                    tour();
                }
            } else {
                if (commence) {
                    for (PersonnageIG perso : getJoueurAutre().getPersonnages()) {
                        perso.setAAgi(true);
                    }
                    tour();
                } else {
                    for (PersonnageIG perso : getJoueurAutre().getPersonnages()) {
                        perso.setAAgi(true);
                    }
                    phase(getJoueurActif());
                }
            }
        }
    }


    public void finPerso() {
        stageInterface.recharge();
        phase(personnageActif.getPossesseur());
    }

    /**
     * Appelle la supression du plateau de jeu avant de supprimer cet element
     */
    @Override
    public void dispose() {
        super.dispose();
        setContainer(null);
        groupeCase.clear();
        for (Case[] tabC : getPlateau()) {
            for (Case c : tabC) {
                c.dispose();
            }
        }
        groupeCase = null;
        setPlateau(null);
        camera.dispose();
        camera = null;
        getViewport().setCamera(null);
        setViewport(null);
        personnageActif = null;
        competenceActive = null;
        caseSelect = null;
        stageInterface = null;
        chemin.clear();
        chemin = null;
        joueurActif = null;
        joueurAutre = null;
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

    public JoueurIG getJoueurActif() {
        return joueurActif;
    }

    public JoueurIG getJoueurAutre() {
        return joueurAutre;
    }

    public PersonnageIG getPersonnageActif() {
        return personnageActif;
    }

    public void setPersonnageActif(PersonnageIG personnageActif) {
        if (personnageActif != null) {
            videChemin();
            this.personnageActif = personnageActif;
            if (!spawn) {
                AlgorithmePathfinding.calculCaseAccessible(personnageActif.getVitesseRestante(), personnageActif.getContainer(), plateau);
            }
            this.stageInterface.setPersonnageActif(personnageActif);
        } else {
            if (this.personnageActif != null) {
                videChemin();
            }
            this.personnageActif = personnageActif;
            this.stageInterface.setPersonnageActif(personnageActif);
        }
    }

    public void setPersonnageActif(int perso) {
        if (personnageActif != joueurAutre.getPersonnages().get(perso)) {
            videChemin();
            this.personnageActif = joueurAutre.getPersonnages().get(perso);
            if (!isSpawn()) {
                AlgorithmePathfinding.calculCaseAccessible(personnageActif.getVitesseRestante(), personnageActif.getContainer(), plateau);
            }
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
        if (Resolution.height - vScreen.y < Resolution.differenceBas) {
            return stageInterface.hit(vScreen.x, Resolution.height - vScreen.y, touchable);
        } else {
            return super.hit(stageX, stageY, touchable);
        }
    }

    public void deplacement() {
        getContainer().getStageInterface().recharge();
        if (!bloquand) {
            ConnexionMatch.partieEnvoiDeplacement(getJoueurActif().getPersonnages().indexOf(personnageActif), chemin.get(0).getPlaceX(), chemin.get(0).getPlaceY());
        }
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
        if (this.competenceActive != competenceActive) {
            this.competenceActive = competenceActive;
            if (this.competenceActive != null) {
                if (this.competenceActive.info.competence instanceof CompetenceActive) {
                    if (((CompetenceActive) this.competenceActive.info.competence).getPorte() == 0) {
                        for (Case[] c : plateau) {
                            for (Case cc : c) {
                                cc.setCompetenceable(false);
                            }
                        }
                        personnageActif.getContainer().setCompetenceable(true);
                    } else {
                        this.competenceActive.select(plateau);
                    }
                } else {
                    this.competenceActive.select(plateau);
                }
            } else {
                for (Case[] c : plateau) {
                    for (Case cc : c) {
                        cc.setCompetenceable(false);
                    }
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

    public boolean isBloquand() {
        return bloquand;
    }

    public void setBloquand(boolean bloquand) {
        this.bloquand = bloquand;
    }

    public boolean isSpawn() {
        return spawn;
    }

    public void testfin() {
        boolean victoire = true;
        for (PersonnageIG perso : joueurAutre.getPersonnages()){
            if (!perso.isMort()){
                victoire = false;
                break;
            }
        }
        if (victoire){
            //joueur actif win
        }
        else {
            for (PersonnageIG perso : joueurActif.getPersonnages()) {
                if (!perso.isMort()){
                    victoire = true;
                    break;
                }
            }
            if (!victoire){
                //joueur autre win
            }
        }
    }
}

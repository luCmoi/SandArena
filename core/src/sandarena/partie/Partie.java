package sandarena.partie;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import sandarena.Resolution;
import sandarena.gui.Camera;
import sandarena.gui.ScreenPartie;
import sandarena.gui.ScreenPartieListener;
import sandarena.joueur.Joueur;
import sandarena.partie.compcase.JoueurIG;
import sandarena.partie.compcase.PersonnageIG;

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
        this.getViewport().setCamera(new Camera(this));
        this.camera = (Camera)(this.getViewport().getCamera());
        this.joueur1 = new JoueurIG(joueur1);
        this.joueur2 = new JoueurIG(joueur2);
        //Changera lorsqu'on saura a partir de quoit creer la partie
        int coteTmp = 25;
        plateau = new Case[coteTmp][coteTmp];
        this.groupeCase = new Group();
        for (int x = 0; x < coteTmp; x++) {
            for (int y = 0; y < coteTmp; y++) {
                plateau[x][y] = new Case(x, y);
                this.groupeCase.addActor(plateau[x][y]);
            }
        }
        this.addActor(groupeCase);
        widthTailleTotale = this.plateau.length * Resolution.widthCase;
        heightTailleTotale = this.plateau[0].length * Resolution.heightCase;
        this.addCaptureListener(new ScreenPartieListener(this));
        lancement();
    }
    
    @Override
    public void draw () {
        ((Camera)getViewport().getCamera()).updateExt();
        super.draw();
    }

    private void lancement() {
        /* Placement des unitées
         */
        getPlateau()[5][5].setPresence(getJoueur1().getPersonnages().get(0));
        getPlateau()[7][5].setPresence(getJoueur2().getPersonnages().get(0));
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
        phase(getJoueur2());
    }

    private void phase(JoueurIG joueur) {
        selection(joueur.getPersonnages().get(0));
    }

    private void selection(PersonnageIG perso) {
        if (!perso.isAAgi()) {
            this.setPersonnageActif(perso);
        }
    }
    /**
     * Appelle la supression du plateau de jeu avant de supprimer cet element
     *
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
        groupeCase=null;
        setPlateau(null);
        dispose();
        camera.dispose();
        camera=null;
        setViewport(null);
        joueur1=null;
        joueur2=null;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public ScreenPartie getContainer() {
        return container;
    }

    @Override
    public Camera getCamera(){
        return this.camera;
    }
    
    public void setPlateau(Case[][] plateau) {
        this.plateau = plateau;
    }

    public void setContainer(ScreenPartie container) {
        this.container = container;
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
        this.personnageActif = personnageActif;
        AlgorithmePathfinding.calculCaseAccessible(personnageActif, plateau);
    }

    public int getWidthTailleTotale() {
        return widthTailleTotale;
    }

    public int getHeightTailleTotale() {
        return heightTailleTotale;
    }
}

package sandarena.partie;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sandarena.Resolution;
import sandarena.gui.ScreenPartie;
import sandarena.joueur.Joueur;
import sandarena.partie.compcase.JoueurIG;
import sandarena.partie.compcase.PersonnageIG;

/**
 * Stock toutes les donnees relatives à une instance de partie
 *
 * @author Guillaume
 */
public class Partie {

    private int dimMax;
    private Case[][] plateau;
    private ScreenPartie container;
    private JoueurIG joueur1;
    private JoueurIG joueur2;
    private PersonnageIG personnageActif;

    /**
     * Permet de créer une nouvelle partie a partir de son conteneur, et plus
     * tard de donne de la carte ainsi que des deux joueurs
     *
     * @param container
     * @param joueur1
     * @param joueur2
     */
    public Partie(ScreenPartie container, Joueur joueur1, Joueur joueur2) {
        this.container = container;
        this.joueur1 = new JoueurIG(joueur1);
        this.joueur2 = new JoueurIG(joueur2);
        //Changera lorsqu'on saura a partir de quoit creer la partie
        int coteTmp = 25;
        plateau = new Case[coteTmp][coteTmp];
        for (int x = 0; x < coteTmp; x++) {
            for (int y = 0; y < coteTmp; y++) {
                plateau[x][y] = new Case(x, y);
            }
        }
        dimMax = coteTmp * Resolution.heightCase;
        lancement();
    }

    public void cliquer(){
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

    public void render(SpriteBatch batch) {
        for (Case[] tabC : getPlateau()) {
            for (Case c : tabC) {
                c.render(batch);
            }
        }
    }

    /**
     * Appelle la supression du plateau de jeu avant de supprimer cet element
     *
     */
    public void dispose() {
        setContainer(null);
        for (Case[] tabC : getPlateau()) {
            for (Case c : tabC) {
                c.dispose();
            }
        }
        setPlateau(null);
    }

    public int getDimMax() {
        return dimMax;
    }

    public Case[][] getPlateau() {
        return plateau;
    }

    public ScreenPartie getContainer() {
        return container;
    }

    public void setDimMax(int dimMax) {
        this.dimMax = dimMax;
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
}

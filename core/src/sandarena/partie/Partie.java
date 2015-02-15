package sandarena.partie;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sandarena.Resolution;
import sandarena.gui.ScreenPartie;
import sandarena.joueur.Joueur;

/**
 * Stock toutes les donnees relatives à une instance de partie
 *
 * @author Guillaume
 */
public class Partie {

    private int dimMax;
    private Case[][] plateau;
    private ScreenPartie container;
    private Joueur joueur1;
    private Joueur joueur2;
    
    /**
     * Permet de créer une nouvelle partie a partir de son conteneur, et plus
     * tard de donne de la carte ainsi que des deux joueurs
     *
     * @param container
     * @param joueur1
     * @param joueur2
     */
    public Partie(ScreenPartie container,Joueur joueur1,Joueur joueur2) {
        this.container = container;
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        //Changera lorsqu'on saura a partir de quoit creer la partie
        int coteTmp = 25;
        plateau = new Case[coteTmp][coteTmp];
        for (int x=0; x<coteTmp; x++) {
            for (int y=0; y<coteTmp; y++ ) {
                plateau[x][y]=new Case(x,y);
            }
        }
        dimMax = coteTmp * Resolution.heightCase;
        lancement();
    }
    
    private void lancement(){
        /* Placement des unitées
        */
        plateau[0][0].setPresence(joueur1.getPersonnagesIG().get(0));
        plateau[24][0].setPresence(joueur2.getPersonnagesIG().get(0));
        while(!victoire()){
            tour();
        }
    }

    private boolean victoire(){
        return true;
    }
    
    private void tour(){
        //J1 joue
        //J2 joue
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

    public Joueur getJoueur1() {
        return joueur1;
    }

    public void setJoueur1(Joueur joueur1) {
        this.joueur1 = joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }

    public void setJoueur2(Joueur joueur2) {
        this.joueur2 = joueur2;
    }
}

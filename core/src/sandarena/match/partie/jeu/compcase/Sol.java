package sandarena.match.partie.jeu.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.banque.Entree;
import sandarena.donnee.sol.BanqueSol;
import sandarena.donnee.sol.BanqueSol.EntreeSol;
import sandarena.match.partie.jeu.Case;

public class Sol {
    private Case container;
    private EntreeSol donnee;
    private int anime = 0;
    private long lastTimer;
    private boolean sableGauche;
    private boolean sableDroit;
    private boolean sableHaut;
    private boolean sableBas;

    public Sol(String type, Case container) {
        this.container = container;
        this.donnee = (EntreeSol) BanqueSol.getEntree(BanqueSol.banque, type);
    }

    public Sol(int sol, Case container) {
        this.container = container;
        this.donnee = (EntreeSol) BanqueSol.getEntree(BanqueSol.banque, sol);
        donnee.incremente();
        lastTimer = System.currentTimeMillis();
    }

    public void render(Batch batch) {
        if (donnee.isAnime()) {
            batch.draw(donnee.getImage()[anime], container.getX(), container.getY(), container.getWidth(), container.getHeight());
            if (System.currentTimeMillis() - lastTimer > 100) {
                anime++;
                if (anime >= donnee.getImage().length) {
                    anime = 0;
                }
                lastTimer = System.currentTimeMillis();
            }
            if (sableBas){
                batch.draw(BanqueSol.bordSableBas, container.getX(), container.getY()-1, container.getWidth(), container.getHeight()/4);
            }if(sableGauche){
                batch.draw(BanqueSol.bordSableGauche, container.getX()-1, container.getY(), container.getWidth()/4, container.getHeight());
            }if (sableHaut){
                batch.draw(BanqueSol.bordSableBas, container.getX(), 1+container.getY()+container.getHeight()-container.getHeight()/4, container.getWidth(), container.getHeight()/4,0, 0, BanqueSol.bordSableBas.getWidth(), BanqueSol.bordSableBas.getHeight(), true, true);
            }if (sableDroit){
                batch.draw(BanqueSol.bordSableGauche, 1+container.getX()+container.getWidth()-container.getWidth()/4, container.getY(), container.getWidth()/4, container.getHeight(),0, 0, BanqueSol.bordSableGauche.getWidth(), BanqueSol.bordSableGauche.getHeight(), true, true);
            }
        } else {
            batch.draw(((Entree) donnee).image, container.getX(), container.getY(), container.getWidth(), container.getHeight());
        }
    }

    public void dispose() {
        donnee.decremente();
        donnee = null;
        container = null;
    }

    public boolean isTraversable() {
        return donnee.isTraversable();
    }

    public void checkVoisin() {
        if (donnee.isAnime()) {
            if (container.getPlaceX() != 0) {
                if (!container.getContainer().getPlateau()[container.getPlaceX()-1][container.getPlaceY()].getSol().isAnime()){
                    this.sableGauche = true;
                }
            }
            if (container.getPlaceX() < container.getContainer().getPlateau().length - 1) {
                if (!container.getContainer().getPlateau()[container.getPlaceX()+1][container.getPlaceY()].getSol().isAnime()){
                    this.sableDroit = true;
                }
            }
            if (container.getPlaceY() != 0) {
                if (!container.getContainer().getPlateau()[container.getPlaceX()][container.getPlaceY()-1].getSol().isAnime()){
                    this.sableBas = true;
                }
            }
            if (container.getPlaceY() < container.getContainer().getPlateau()[0].length - 1) {
                if (!container.getContainer().getPlateau()[container.getPlaceX()][container.getPlaceY()+1].getSol().isAnime()){
                    this.sableHaut = true;
                }
            }
        }
    }

    public boolean isAnime() {
        return donnee.isAnime();
    }
}

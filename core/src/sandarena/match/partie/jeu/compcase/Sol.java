package sandarena.match.partie.jeu.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.SandArena;
import sandarena.donnee.banque.Entree;
import sandarena.donnee.sol.BanqueSol;
import sandarena.donnee.sol.BanqueSol.EntreeSol;
import sandarena.match.partie.jeu.Case;

public class Sol {
    private Case container;
    private EntreeSol donnee;
    private int anime = 0;
    private long lastTimer;

    public Sol(String type, Case container) {
        this.container = container;
        this.donnee = (EntreeSol) BanqueSol.getEntree(BanqueSol.banque, type);
    }

    public Sol(int sol, Case container) {
        this.container = container;
        SandArena.googleService.printError(String.valueOf(sol));
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
}

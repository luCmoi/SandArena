package sandarena.match.partie.jeu.compcase;

import com.badlogic.gdx.graphics.g2d.Batch;

import sandarena.donnee.sol.BanqueSol;
import sandarena.donnee.sol.BanqueSol.EntreeSol;
import sandarena.match.partie.jeu.Case;

public class Sol {
    private Case container;
    private EntreeSol donnee;

    public Sol(String type, Case container) {
        this.container = container;
        this.donnee = (EntreeSol) BanqueSol.getEntree(BanqueSol.banque, type);
    }

    public void render(Batch batch) {
        batch.draw(donnee.image, container.getX(), container.getY(), container.getWidth(), container.getHeight());
    }

    public void dispose() {
        donnee.decremente();
        donnee = null;
        container = null;
    }
}

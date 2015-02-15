package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sandarena.donnee.BanqueSol;
import sandarena.donnee.BanqueSol.*;
import sandarena.partie.Case;

public class Sol {
    private Case container;
    private EntreeSol donnee;

    public Sol(String type,Case container) {
        this.container=container;
        this.donnee = (EntreeSol)BanqueSol.getEntree(BanqueSol.banque, type);
    }

    public void render(SpriteBatch batch) {
        batch.draw(donnee.image, container.getPosition().x, container.getPosition().y, container.getPosition().width, container.getPosition().height);
    }

    public void dispose() {
        donnee.decremente();
        donnee = null;
        container=null;
    }
}

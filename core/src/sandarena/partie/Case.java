package sandarena.partie;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sandarena.Resolution;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.compcase.Sol;

/**
 *
 * @author Guillaume
 */
public class Case {

    private int placeX;
    private int placeY;
    private Rectangle position;
    private Sol sol;
    private PersonnageIG presence;
    private boolean accessible;
    private Case predecesseur;
    private boolean chemin;

    public Case(int x, int y){
        placeX = x;
        placeY = y;
        position = new Rectangle(x * Resolution.widthCase, y * Resolution.heightCase, Resolution.widthCase, Resolution.heightCase);
        this.sol = new Sol("Sable", this);
        this.presence = null;
        this.accessible = false;
        this.predecesseur = null;
        this.chemin = false;
    }

    public boolean isTraversable() {
        return (this.presence == null);
    }

    public void render(SpriteBatch batch) {
        if (!accessible) {
            getSol().render(batch);
        }
        if (presence != null) {
            getPresence().render(batch);
        }
    }

    public void dispose() {
        this.getSol().dispose();
        this.setSol(null);
        if (this.presence != null) {
            this.presence.dispose();
            this.presence = null;
        }
    }

    public Rectangle getPosition() {
        return position;
    }

    public void setPosition(Rectangle position) {
        this.position = position;
    }

    public Sol getSol() {
        return sol;
    }

    public void setSol(Sol sol) {
        this.sol = sol;
    }

    public int getPlaceX() {
        return placeX;
    }

    public void setPlaceX(int placeX) {
        this.placeX = placeX;
    }

    public int getPlaceY() {
        return placeY;
    }

    public void setPlaceY(int placeY) {
        this.placeY = placeY;
    }

    public PersonnageIG getPresence() {
        return presence;
    }

    public void setPresence(PersonnageIG presence) {
        this.presence = presence;
        this.presence.setContainer(this);
    }

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public Case getPredecesseur() {
        return predecesseur;
    }

    public void setPredecesseur(Case predecesseur) {
        this.predecesseur = predecesseur;
    }

    public boolean isChemin() {
        return chemin;
    }

    public void setChemin(boolean chemin) {
        this.chemin = chemin;
    }
}

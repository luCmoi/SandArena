package sandarena.partie;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.compcase.Sol;

/**
 * @author Guillaume
 */
public class Case extends Actor {

    private final Partie container;
    private int placeX;
    private int placeY;
    private Rectangle position;
    private Sol sol;
    private PersonnageIG presence;
    private boolean accessible;
    private Case predecesseur;
    private boolean chemin;
    private boolean cible;
    private boolean competenceable;
    private boolean select;

    public Case(int x, int y, Partie container) {
        placeX = x;
        placeY = y;
        this.setTouchable(Touchable.enabled);
        this.setBounds(x * Resolution.widthCase, y * Resolution.heightCase, Resolution.widthCase, Resolution.heightCase);
        this.sol = new Sol("Sable", this);
        this.presence = null;
        this.accessible = false;
        this.predecesseur = null;
        this.chemin = false;
        this.container = container;
        this.addListener(new CaseListener(this));
        this.cible = false;
    }

    public boolean isTraversable() {
        return (this.presence == null);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        getSol().render(batch);
        if (accessible) {
            batch.draw(Utili.accessible, getX(), getY(), getWidth(), getHeight());
        }
        if (chemin) {
            batch.draw(Utili.chemin, getX(), getY(), getWidth(), getHeight());
        }
        if (competenceable) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
        }
        if (presence != null) {
            if (container.getPersonnageActif().equals(presence)) {
                batch.draw(Utili.caseActive, getX(), getY(), getWidth(), getHeight());
            }
            else if (select) {
                batch.draw(Utili.caseSelect, getX(), getY(), getWidth(), getHeight());
            }
            getPresence().render(batch);
        } else if (select) {
            batch.draw(Utili.caseSelect, getX(), getY(), getWidth(), getHeight());
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

    public void entrePresence(PersonnageIG presence) {
        this.presence = presence;
        this.presence.setContainer(this);
    }

    public void sortiePresence() {
        this.presence = null;
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

    void clique() {
        container.setCaseSelect(this);
        this.select = true;
        if (getContainer().getCompetenceActive() == null) {
            if (isAccessible() && !cible) {
                getContainer().selectChemin(this);
            } else if (cible) {
                getContainer().deplacement();
            }
        } else if (this.competenceable) {
            getContainer().getCompetenceActive().agit(this);
        }
    }


    public void setCible(boolean val) {
        this.cible = val;
    }

    public void setCompetenceable(boolean competenceable) {
        this.competenceable = competenceable;
    }

    public Partie getContainer() {
        return container;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}

package sandarena.match.partie.jeu;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.googleservice.ConnexionMatch;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.carte.BanqueCarte;
import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowCaseIG;

/**
 * @author Guillaume
 */
public class Case extends Actor {

    private Partie container;
    private int placeX;
    private int placeY;
    private sandarena.match.partie.jeu.compcase.Sol sol;
    private sandarena.match.partie.jeu.compcase.PersonnageIG presence;
    private boolean accessible;
    private Case predecesseur;
    private boolean chemin;
    private boolean cible;
    private boolean competenceable;
    private boolean select;
    private int spawnable = -1;
    private InfoWindowCaseIG info;

    public Case(int x, int y, Partie container, BanqueCarte.DonneCarte carte) {
        placeX = x;
        placeY = y;
        this.setTouchable(Touchable.enabled);
        this.setBounds(x * Resolution.widthCase, y * Resolution.heightCase, Resolution.widthCase, Resolution.heightCase);
        if (carte.sol != -1) {
            this.sol = new sandarena.match.partie.jeu.compcase.Sol("Sable", this);
        }
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
        ((CaseListener) getListeners().get(0)).update();
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
        if (spawnable == 0 && container.isSpawn()) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
        }
        if (spawnable == 1 && container.isSpawn()) {
            batch.draw(Utili.competenceable, getX(), getY(), getWidth(), getHeight());
        }
        if (presence != null) {
            if (container.getPersonnageActif() != null && container.getPersonnageActif().equals(presence)) {
                batch.draw(Utili.caseActive, getX(), getY(), getWidth(), getHeight());
            } else if (select) {
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
        container = null;
        predecesseur = null;
        ((CaseListener) getListeners().get(0)).dispose();
        clear();
    }

    public sandarena.match.partie.jeu.compcase.Sol getSol() {
        return sol;
    }

    public void setSol(sandarena.match.partie.jeu.compcase.Sol sol) {
        this.sol = sol;
    }

    public int getPlaceX() {
        return placeX;
    }

    public int getPlaceY() {
        return placeY;
    }

    public sandarena.match.partie.jeu.compcase.PersonnageIG getPresence() {
        return presence;
    }

    public void entrePresence(sandarena.match.partie.jeu.compcase.PersonnageIG presence) {
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

    public void setChemin(boolean chemin) {
        this.chemin = chemin;
    }

    void clique() {
        container.setCaseSelect(this);
        this.select = true;
        if (!container.isBloquand()) {
            if (!container.isSpawn()) {
                if (getContainer().getCompetenceActive() == null) {
                    if (isAccessible() && !cible) {
                        getContainer().selectChemin(this);
                    } else if (cible) {
                        getContainer().deplacement();
                    } else if (this.getPresence() != null && !this.getPresence().isAAgi() && this.getPresence().getPossesseur() == getContainer().getPersonnageActif().getPossesseur()) {
                        getContainer().setPersonnageActif(this.getPresence());
                        ConnexionMatch.partieEnvoiPersoActif(getContainer().getJoueurActif().getPersonnages().indexOf(this.getPresence()));
                    }
                } else if (this.competenceable) {
                    ConnexionMatch.partieEnvoiUtiliseCompetence(getContainer().getJoueurActif().getPersonnages().indexOf(getContainer().getPersonnageActif()), getContainer().getCompetenceActive().getPlace(), this.getPlaceX(), this.getPlaceY());
                    getContainer().getCompetenceActive().agit(this);
                    getContainer().getContainer().getStageInterface().recharge();
                }
            } else {
                if (spawnable == 0) {
                    if (container.getPersonnageActif() == null && this.getPresence() != null) {
                        container.setPersonnageActif(this.getPresence());
                        ConnexionMatch.partieEnvoiPersoActif(getContainer().getJoueurActif().getPersonnages().indexOf(this.getPresence()));
                    } else if (container.getPersonnageActif() != null) {
                        ConnexionMatch.partieEnvoiEchange(getContainer().getJoueurActif().getPersonnages().indexOf(container.getPersonnageActif()),placeX, placeY);
                        this.echange();
                    }
                }
            }
        }
    }

    public void echange() {
        sandarena.match.partie.jeu.compcase.PersonnageIG tmp = this.getPresence();
        this.presence = container.getPersonnageActif();
        if (tmp != null) {
            container.getPersonnageActif().getContainer().setPresence(tmp);
            tmp.setContainer(container.getPersonnageActif().getContainer());
        }
        this.presence.setContainer(this);
        container.setPersonnageActif(null);
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

    public void pression() {
        if (this.getPresence() != null && this.competenceable && !container.isBloquand()) {
            this.info = new InfoWindowCaseIG(this.getPresence(), container.getPersonnageActif(), container.getCompetenceActive());
            container.getContainer().getSurcouche().addActor(info);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }
    }

    public void setPresence(sandarena.match.partie.jeu.compcase.PersonnageIG presence) {
        this.presence = presence;
    }

    public void setSpawnable(int spawnable) {
        this.spawnable = spawnable;
    }
}

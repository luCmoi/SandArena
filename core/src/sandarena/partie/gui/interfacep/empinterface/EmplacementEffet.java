package sandarena.partie.gui.interfacep.empinterface;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

import sandarena.donnee.Utili;
import sandarena.partie.compcase.PersonnageIG;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.gui.interfacep.StageInterface;
import sandarena.partie.gui.interfacep.infowindow.InfoWindow;

/**
 * Created by Guillaume on 26/06/2015.
 */
public class EmplacementEffet extends EmplacementInterface {
    private ArrayList<EffetBuf> effets = new ArrayList<EffetBuf>();
    private static final int EFFETATTAQUE = 3;
    private static final int EFFETDEFENSE = 7;
    private static final int EFFETVITESSE = 2;
    private static final int EFFETVIE = 6;
    private static final int EFFETDOT = 1;
    private static final int EFFETSTUN = 5;

    public EmplacementEffet(int place, StageInterface container) {
        super(place, container);
        int difX = 0;
        if (getPlace() % 8 >= 4) difX = 1;
        this.setBounds((container.tailleCoteHeight * (8 + (difX) + (10 * (getPlace() / 8))) / 4), 0 + ((container.tailleCoteHeight * (getPlace() % 4) / 4)), container.tailleCoteHeight / 4, container.tailleCoteHeight / 4);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
            switch (place % 8) {
                case EFFETATTAQUE:
                    batch.draw(Utili.attaque, getX(), getY(), getWidth(), getHeight());
                    break;
                case EFFETDEFENSE:
                    batch.draw(Utili.defense, getX(), getY(), getWidth(), getHeight());
                    break;
                case EFFETVIE:
                    batch.draw(Utili.vie, getX(), getY(), getWidth(), getHeight());
                    break;
                case EFFETVITESSE:
                    batch.draw(Utili.vitesse, getX(), getY(), getWidth(), getHeight());
                    break;
                case EFFETDOT:
                    batch.draw(Utili.dot, getX(), getY(), getWidth(), getHeight());
                    break;
                case EFFETSTUN:
                    batch.draw(Utili.stun, getX(), getY(), getWidth(), getHeight());
                    break;
            }
        if (getEffets().isEmpty()){
            batch.draw(Utili.passive,getX(), getY(), getWidth(), getHeight());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        getEffets().clear();
        setEffets(null);
    }

    @Override
    public void clique() {
    }

    @Override
    public void pression() {
        if (!this.getEffets().isEmpty()) {
            this.info = new InfoWindow(this);
            container.getPartie().getContainer().getSurcouche().addActor(info);
        }
    }

    public void setEffet(PersonnageIG perso) {
        this.getEffets().clear();
        if (perso != null) {
            switch (place % 8) {
                case EFFETATTAQUE:
                    this.getEffets().addAll(perso.getChangeAtt());
                    this.getEffets().addAll(perso.getChangeTypeAtt());
                    break;
                case EFFETDEFENSE:
                    this.getEffets().addAll(perso.getChangeDef());
                    this.getEffets().addAll(perso.getChangeTypeDef());
                    break;
                case EFFETVIE:
                    this.getEffets().addAll(perso.getChangeVie());
                    break;
                case EFFETVITESSE:
                    this.getEffets().addAll(perso.getChangeVitesse());
                    break;
                case EFFETDOT:
                    this.getEffets().addAll(perso.getDot());
                    break;
                case EFFETSTUN:
                    this.getEffets().addAll(perso.getStun());
                    break;
            }
        }
    }

    public ArrayList<EffetBuf> getEffets() {
        return effets;
    }

    public void setEffets(ArrayList<EffetBuf> effets) {
        this.effets = effets;
    }
}

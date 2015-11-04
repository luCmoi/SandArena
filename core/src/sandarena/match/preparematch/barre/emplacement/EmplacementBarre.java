package sandarena.match.preparematch.barre.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Personnage;
import sandarena.match.preparematch.barre.StageBarre;

/**
 * Created by Guillaume on 26/07/2015.
 */
public class EmplacementBarre extends Group {
    private StageBarre container;
    private byte place;
    private Personnage perso;
    private boolean ouvert;
    private UnitBarre unit;
    private AccepteBarre accepte;
    private CompetenceBarre[] enfants = new CompetenceBarre[4];

    public EmplacementBarre(sandarena.match.preparematch.barre.StageBarre container, int place, Personnage perso) {
        this.container = container;
        this.place = (byte) place;
        this.perso = perso;
        this.setBounds(Resolution.differenceBas * place, 0, Resolution.differenceBas, Resolution.differenceBas);
        this.setTouchable(Touchable.enabled);
        this.addListener(new EmplacementBarreListener(this));
        ouvert = false;
        unit = new UnitBarre(this, perso);
        addActor(unit);
        if (perso.getCompetences()[0] != null)
            enfants[0] = new CompetenceBarre(this, 0, perso.getCompetences()[0]);
        if (perso.getCompetences()[1] != null)
            enfants[1] = new CompetenceBarre(this, 1, perso.getCompetences()[1]);
        if (perso.getCompetences()[2] != null)
            enfants[2] = new CompetenceBarre(this, 2, perso.getCompetences()[2]);
        if (perso.getCompetences()[3] != null)
            enfants[3] = new CompetenceBarre(this, 3, perso.getCompetences()[3]);
        for (CompetenceBarre comp : enfants) {
            if (comp != null) addActor(comp);
        }
        accepte = new AccepteBarre(this);
        this.addActor(accepte);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void clique() {
        if (!ouvert) {
            this.getContainer().augmenteWidthTailleTotale(this.place);
            this.setWidth(getWidth() + Resolution.differenceBas);
            ouvert = true;
            for (CompetenceBarre comp : enfants) {
                if (comp != null) {
                    comp.setTouchable(Touchable.enabled);
                }
            }
                accepte.setTouchable(Touchable.enabled);
        } else {
            this.getContainer().diminueWidthTailleTotale(this.place, false);
            this.setWidth(getWidth() - Resolution.differenceBas);
            for (CompetenceBarre comp : enfants) {
                if (comp != null) {
                    comp.setTouchable(Touchable.disabled);
                }
            }
            accepte.setTouchable(Touchable.disabled);
            ouvert = false;
        }
    }

    public void decaleGauche() {
        this.setX(getX() - Resolution.differenceBas);
    }

    public void decaleDroite() {
        this.setX(getX() + Resolution.differenceBas);
    }


    public StageBarre getContainer() {
        return container;
    }

    public int getPlace() {
        return place;
    }

    public void dispose(boolean disposeMain) {
        ((EmplacementBarreListener) (getListeners().get(0))).dispose();
        getListeners().clear();
        if (!disposeMain) {
            container.getPersos().remove(place);
        }container = null;
        perso = null;
        for (CompetenceBarre comp : enfants) {
            if (comp != null) {
                comp.dispose();
            }
        }
        enfants = null;
        unit.dispose();
        unit = null;
        accepte.dispose();
        accepte =null;
        remove();
        clear();
    }

    public Personnage getPerso() {
        return perso;
    }

    public void setPlace(int place) {
        this.place = (byte) place;
    }

    public void fenetre() {
        ((EmplacementBarreListener) (getListeners().get(0))).fenetre();
    }
}

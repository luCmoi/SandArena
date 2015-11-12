package sandarena.match.preparematch.barre.emplacement;

import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.interfaceutil.emplacement.Emplacement;
import sandarena.interfaceutil.emplacement.EmplacementCompetence;
import sandarena.joueur.Personnage;
import sandarena.match.preparematch.barre.StageBarre;

/**
 * Created by Guillaume on 26/07/2015.
 */
public class EmplacementBarre extends Emplacement {
    private StageBarre container;
    private Personnage personnage;
    private boolean ouvert;
    private AccepteBarre accepte;

    public EmplacementBarre(StageBarre container, int place, Personnage personnage) {
        super(container.getPrincipal().getContainer(),place);
        this.container = container;
        this.personnage = personnage;
        this.setBounds(Resolution.differenceBas * place, 0, Resolution.differenceBas, Resolution.differenceBas);
        this.setTouchable(Touchable.enabled);
        this.addListener(new EmplacementBarreListener(this));
        ouvert = false;
        perso = new UnitBarre(this, personnage);
        addActor(perso);
        if (personnage.getCompetences() != null) {
            comp[0] = new CompetenceBarre(this, 0, personnage.getCompetences()[0]);
            comp[1] = new CompetenceBarre(this, 1, personnage.getCompetences()[1]);
            comp[2] = new CompetenceBarre(this, 2, personnage.getCompetences()[2]);
            comp[3] = new CompetenceBarre(this, 3, personnage.getCompetences()[3]);
        }
        for (EmplacementCompetence compe : comp) {
            if (compe != null) addActor(compe);
        }
        accepte = new AccepteBarre(this);
        this.addActor(accepte);
    }

    public void clique() {
        if (!ouvert) {
            this.getContainer().augmenteWidthTailleTotale(this.place);
            this.setWidth(getWidth() + Resolution.differenceBas);
            ouvert = true;
            for (EmplacementCompetence compe : comp) {
                if (compe != null) {
                    compe.setTouchable(Touchable.enabled);
                }
            }
                accepte.setTouchable(Touchable.enabled);
        } else {
            this.getContainer().diminueWidthTailleTotale(this.place, false);
            this.setWidth(getWidth() - Resolution.differenceBas);
            for (EmplacementCompetence compe : comp) {
                if (compe != null) {
                    compe.setTouchable(Touchable.disabled);
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

    public void dispose(boolean disposeMain) {
        super.dispose();
        if (!disposeMain) {
            container.getPersos().remove(place);
        }
        container = null;
        personnage = null;
        accepte.dispose();
        accepte =null;
    }

    public Personnage getPerso() {
        return personnage;
    }

    public void fenetre() {
        ((EmplacementBarreListener) (getListeners().get(0))).fenetre();
    }

    public void setPlace(int place) {
        this.place = (byte)place;
    }
}

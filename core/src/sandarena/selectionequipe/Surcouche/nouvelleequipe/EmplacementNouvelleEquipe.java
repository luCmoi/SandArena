package sandarena.selectionequipe.Surcouche.nouvelleequipe;

import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.interfaceutil.emplacement.EmplacementCompetence;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 04/11/2015.
 */
public class EmplacementNouvelleEquipe extends sandarena.interfaceutil.emplacement.Emplacement {
    private PanneauEquipe container;

    public EmplacementNouvelleEquipe(PanneauEquipe container, int place, Personnage personnage) {
        super(container.getContainer().getContainer(),place);
        this.container = container;
        this.setBounds((container.getWidth() / 4) * (place % 3)+container.getWidth() / 8, container.getHeight() - (container.getWidth() / 8) * (2 + place / 3), container.getWidth() / 4, container.getWidth() / 8);
        this.linkEmplacementPerso();
        this.linkEmplacementCompetence();
        this.setPerso(personnage);
        this.setVisible(false);
        if (place < 8){
            this.setVisible(true);
        }
    }

    public PanneauEquipe getContainer() {
        return container;
    }

    public byte getPlace() {
        return place;
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
        perso.setVisible(visible);
        for (EmplacementCompetence cmp : comp) {
            cmp.setVisible(visible);
        }
        if (visible) {
            perso.setTouchable(Touchable.enabled);
            for (EmplacementCompetence cmp : comp) {
                cmp.setTouchable(Touchable.enabled);
            }
        } else {
            perso.setTouchable(Touchable.disabled);
            for (EmplacementCompetence cmp : comp) {
                cmp.setTouchable(Touchable.disabled);
            }
        }
    }

    public void dispose() {

    }
}

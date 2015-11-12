package sandarena.gestionequipe.stagepersos.emplacement;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.gestionequipe.stagepersos.StagePersonnageGestionEquipe;
import sandarena.interfaceutil.emplacement.Emplacement;

/**
 * Created by lucmo on 23/09/2015.
 */
public class EmplacementPersoGestion extends Emplacement{

    private final StagePersonnageGestionEquipe container;

    public EmplacementPersoGestion(StagePersonnageGestionEquipe container, int place) {
        super(container.getContainer(),place);
        this.container = container;
        this.setBounds((container.getWidth()/4)* (place % 4), Resolution.height - (container.getWidth()/8) * (1+ place / 4),container.getWidth()/4,container.getWidth()/8);
        this.perso = new UnitPersoGestion(this);
        this.addActor(perso);
        this.linkEmplacementCompetence();
    }

    public StagePersonnageGestionEquipe getContainer() {
        return container;
    }

}

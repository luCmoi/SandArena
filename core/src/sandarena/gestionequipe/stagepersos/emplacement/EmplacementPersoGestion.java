package sandarena.gestionequipe.stagepersos.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.gestionequipe.stagepersos.StagePersonnageGestionEquipe;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 23/09/2015.
 */
public class EmplacementPersoGestion extends Group{
    private StagePersonnageGestionEquipe container;
    private byte place;
    UnitPersoGestion unit;
    CompPersoGestion[] comp = new CompPersoGestion[4];

    public EmplacementPersoGestion(StagePersonnageGestionEquipe container, int place) {
        this.container = container;
        this.place = (byte)place;
        this.setBounds((container.getWidth()/4)* (place % 4), Resolution.height - (container.getWidth()/8) * (1+ place / 4),container.getWidth()/4,container.getWidth()/8);
        this.unit = new UnitPersoGestion(this);
        this.addActor(unit);
        for (int i = 0; i < 4; i++) {
            comp[i] = new CompPersoGestion(this, i);
            this.addActor(comp[i]);
        }
    }

    public Personnage getPerso() {
        return unit.getPerso();
    }

    public void setPerso(Personnage perso) {
        if (perso != null) {
            unit.setPerso(perso);
            for (int i = 0; i < 4; i++) {
                comp[i].setComp(perso.getCompetences()[i]);
            }
        }else{
            unit.setPerso(null);
            for (int i = 0; i < 4; i++) {
                comp[i].setComp(null);
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.fond,getX(),getY(),getWidth(),getHeight());
        super.draw(batch, parentAlpha);
    }

    public StagePersonnageGestionEquipe getContainer() {
        return container;
    }

    public byte getPlace() {
        return place;
    }

}

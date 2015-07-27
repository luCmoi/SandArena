package sandarena.preparematch.stageprincipal;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.Resolution;
import sandarena.donnee.Utili;
import sandarena.joueur.Personnage;
import sandarena.preparematch.barre.UnitBarre;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class UnitPanelScreenPrepaMatch extends Actor {
    private PanelScreenPrepaMatch container;
    private boolean actif;
    private int place;
    private Personnage perso;

    public UnitPanelScreenPrepaMatch(PanelScreenPrepaMatch container, boolean b, int i) {
        this.container = container;
        this.actif = b;
        this.place = i;
        if (actif) {
            this.setBounds(container.coteMoyen * (place / 2),Resolution.differenceBas+ container.coteMoyen * (place % 2),container.coteMoyen, container.coteMoyen);
        } else {
            this.setBounds(container.getWidth() - (container.coteMoyen * (1 + (place / 2))),Resolution.differenceBas+ container.coteMoyen * (place % 2),container.coteMoyen, container.coteMoyen);
        }
        this.setTouchable(Touchable.enabled);
        this.addListener(new UnitPanelScreenPrepaMatchListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (perso != null){
            batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
        }
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public boolean contient(float x, float y, UnitBarre container) {
        System.out.println(getX() + " "+ getY()+ " "+ x +" "+ y);
        if (getX()<x && getY()>y&&(getX() +getWidth())>x && (getY() - getHeight())<y){
            perso = container.getPerso();
            return true;
        }
        return false;
    }
}

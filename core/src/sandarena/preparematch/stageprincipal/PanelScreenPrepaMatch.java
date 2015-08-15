package sandarena.preparematch.stageprincipal;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

import sandarena.Resolution;
import sandarena.joueur.Personnage;
import sandarena.partie.gui.interfacep.empinterface.EmplacementPerso;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class PanelScreenPrepaMatch extends Group {
    private final StagePrincipalScreenPrepa container;
    private ArrayList<UnitPanelScreenPrepaMatch> places;
    public float coteMoyen;

    public PanelScreenPrepaMatch(int position, sandarena.preparematch.stageprincipal.StagePrincipalScreenPrepa container) {
        this.container = container;
        places = new ArrayList<sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch>();
        this.setBounds(0,0,Resolution.width / 2, Resolution.height - Resolution.differenceBas);
        if (this.getHeight() > this.getWidth()){
            coteMoyen = getWidth()/2;
        }else {
            coteMoyen = getHeight()/2;
        }
        if (position == 0) {
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, true, 0));
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, true, 1));
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, true, 2));
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, true, 3));
        } else {
            this.setX(Resolution.width / 2);
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, false, 0));
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, false, 1));
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, false, 2));
            places.add(new sandarena.preparematch.stageprincipal.UnitPanelScreenPrepaMatch(this, false, 3));
        }
        /* Nombre Pourra varier */
        for (UnitPanelScreenPrepaMatch unit : places){
            this.addActor(unit);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public StagePrincipalScreenPrepa getContainer() {
        return container;
    }

    public void ajout(Personnage tmp) {
        for (UnitPanelScreenPrepaMatch place : places){
            if (place.getPerso() == null){
                place.setPerso(tmp);
                break;
            }
        }
    }

    public boolean testFin() {
        for (UnitPanelScreenPrepaMatch place : places){
            if (place.getPerso() == null){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Personnage> getPersonnages() {
        ArrayList<Personnage> retour = new ArrayList<Personnage>();
        for (UnitPanelScreenPrepaMatch perso : places){
            retour.add(perso.getPerso());
        }
        return retour;
    }
}

package sandarena.match.preparematch.stageprincipal;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.joueur.Personnage;
import sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class PanelScreenPrepaMatch extends Group {
    private  StagePrincipalScreenPrepa container;
    private ArrayList<EmplacementPanelScreenPrepaMatch> places;
    private float coteMoyen;

    public PanelScreenPrepaMatch(boolean position, StagePrincipalScreenPrepa container) {
        this.container = container;
        places = new ArrayList<EmplacementPanelScreenPrepaMatch>();
        this.setBounds(0,0,Resolution.width / 2, Resolution.height - Resolution.differenceBas);
        if (this.getHeight() > this.getWidth()){
            coteMoyen = getWidth()/2;
        }else {
            coteMoyen = getHeight()/2;
        }
        if (position) {
            places.add(new EmplacementPanelScreenPrepaMatch(this, true, 0));
            places.add(new EmplacementPanelScreenPrepaMatch(this, true, 1));
            places.add(new EmplacementPanelScreenPrepaMatch(this, true, 2));
            places.add(new EmplacementPanelScreenPrepaMatch(this, true, 3));
        } else {
            this.setX(Resolution.width / 2);
            places.add(new sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch(this, false, 0));
            places.add(new sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch(this, false, 1));
            places.add(new sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch(this, false, 2));
            places.add(new sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch(this, false, 3));
        }
        /* Nombre Pourra varier */
        for (sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch unit : places){
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
        for (sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch place : places){
            if (place.getPerso() == null){
                place.setPerso(tmp);
                Son.persoSelect.play();
                break;
            }
        }
    }

    public boolean testFin() {
        for (sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch place : places){
            if (place.getPerso() == null){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Personnage> getPersonnages() {
        ArrayList<Personnage> retour = new ArrayList<Personnage>();
        for (sandarena.match.preparematch.stageprincipal.emplacement.EmplacementPanelScreenPrepaMatch perso : places){
            retour.add(perso.getPerso());
        }
        return retour;
    }

    public void dispose() {
        container = null;
        for (EmplacementPanelScreenPrepaMatch persos:places) {
         persos.dispose();
        }
        places.clear();
        places = null;
    }
}

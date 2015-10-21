package sandarena.gestionequipe.stagepersos;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.gestionequipe.ScreenGestionEquipe;
import sandarena.gestionequipe.stagepersos.emplacement.EmplacementPersoGestion;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 16/10/2015.
 */
public class StagePersonnageGestionEquipe extends Stage {
    private final ScreenGestionEquipe container;
    private Joueur equipe;

    public StagePersonnageGestionEquipe(ScreenGestionEquipe screenGestionEquipe, Joueur equipe, ScalingViewport scalingViewport, Batch batch) {
        super(scalingViewport,batch);
        container =screenGestionEquipe;
        this.equipe = equipe;
        for (int i = 0; i < 12; i++) {
            this.addActor(new EmplacementPersoGestion(this,i));
        }
        int i = 0;
        for (Personnage perso :equipe.getPersonnages())
        {
            ((EmplacementPersoGestion)this.getActors().get(i)).setPerso(perso);
            i++;
        }
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        Vector2 vScreen = stageToScreenCoordinates(new Vector2(stageX, stageY));
        if (Resolution.height - vScreen.y < Resolution.differenceBas && Resolution.width - vScreen.x > Resolution.differenceBas / 2 && Resolution.width - vScreen.x < Resolution.width - (Resolution.differenceBas / 2)) {
            return container.getBarre().hit(vScreen.x - (Resolution.differenceBas / 2), Resolution.height - vScreen.y, touchable);
        } else {
            return super.hit(stageX, stageY, touchable);
        }
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK){
            container.backKeyPressed();
        }
        return super.keyDown(keyCode);
    }

    public ScreenGestionEquipe getContainer() {
        return container;
    }

    public void add(Personnage tmp, byte place) {
        ((EmplacementPersoGestion)this.getActors().get(place)).setPerso(tmp);
    }

    public void setEquipe(Joueur equipe) {
        this.equipe = equipe;
        for (int i = 0; i <12 ; i++) {
            if (i < equipe.getPersonnages().size()) {
                ((EmplacementPersoGestion) this.getActors().get(i)).setPerso(equipe.getPersonnages().get(i));
            }else{
                ((EmplacementPersoGestion) this.getActors().get(i)).setPerso(null);
            }
        }
    }
}

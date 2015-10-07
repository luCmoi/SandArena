package sandarena.selectionequipe;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;

/**
 * Created by lucmo on 06/10/2015.
 */
public class PanneauEquipe extends Actor {
    private final int place;
    private StageSelectionEquipe container;
    private Joueur equipe;

    public PanneauEquipe(StageSelectionEquipe stageSelectionEquipe, int place, Joueur equipe) {
        this.container = stageSelectionEquipe;
        this.place = place;
        this.equipe =equipe;
        this.setTouchable(Touchable.enabled);
        this.setBounds((Resolution.width / 3) * place, 0, Resolution.width / 3, Resolution.height);
        this.addListener(new PanneauEquipeListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour,getX(),getY(),getWidth(),getHeight());
        if (equipe != null){
            for (int i = 0; i < equipe.getPersonnages().size(); i++) {
                batch.draw(equipe.getPersonnages().get(i).commun.image,getX()+ (getWidth()/3)*(i%3),getHeight()-(getWidth()/3)*(1+(i/3)),getWidth()/3,getWidth()/3);
            }
        }
    }

    public void clique() {
        if (equipe != null){
            SandArena.googleService.printError("Equipe Find");
            //SandArena.googleService.startQuickGame();
            new sandarena.connexion.ConnexionServeur(container.getContainer().getContainer(),equipe);
        }else{
            equipe = new Joueur();
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            SandArena.googleService.savedGamesUpdate(Sauvegarde.toSnapshotName(place),Sauvegarde.toData(equipe));
        }
    }
}

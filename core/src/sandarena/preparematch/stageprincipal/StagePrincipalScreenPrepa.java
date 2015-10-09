package sandarena.preparematch.stageprincipal;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.ArrayList;

import sandarena.SandArena;
import sandarena.googleservice.ConnexionMatch;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.preparematch.ScreenPrepaMatch;
import sandarena.preparematch.barre.StageBarre;

/**
 * Created by Guillaume on 23/07/2015.
 */
public class StagePrincipalScreenPrepa extends Stage {

    private final ScreenPrepaMatch container;
    private final Joueur joueur;
    private final FlecheListe gauche;
    private final FlecheListe droite;
    private StageBarre barre;
    private PanelScreenPrepaMatch panelGauche;
    private PanelScreenPrepaMatch panelDroit;
    private boolean commence;
    private boolean bloquand = false;

    public StagePrincipalScreenPrepa(ScreenPrepaMatch screenPrepaMatch, Joueur joueur, boolean commence, ScalingViewport scalingViewport, Batch batch) {
        super(scalingViewport, batch);
        this.container = screenPrepaMatch;
        this.joueur = joueur;
        this.commence = commence;
        this.panelGauche = new PanelScreenPrepaMatch(true, this);
        this.panelDroit = new PanelScreenPrepaMatch(false, this);
        this.addActor(panelGauche);
        this.addActor(panelDroit);
        gauche = new FlecheListe(this, true);
        droite = new FlecheListe(this, false);
        this.addActor(gauche);
        this.addActor(droite);
        if (!commence) {
            recoitPersonnageAutre();
        }
    }

    public void setBarre(StageBarre barre) {
        this.barre = barre;
    }

    @Override
    public Actor hit(float stageX, float stageY, boolean touchable) {
        Vector2 vScreen = stageToScreenCoordinates(new Vector2(stageX, stageY));
        if (Resolution.height - vScreen.y < Resolution.differenceBas && Resolution.width - vScreen.x > Resolution.differenceBas / 2 && Resolution.width - vScreen.x < Resolution.width - (Resolution.differenceBas / 2)) {
            return barre.hit(vScreen.x - (Resolution.differenceBas / 2), Resolution.height - vScreen.y, touchable);
        } else {
            return super.hit(stageX, stageY, touchable);
        }
    }

    public StageBarre getBarre() {
        return barre;
    }

    public ScreenPrepaMatch getContainer() {
        return container;
    }

    public void ajoutGauche(final Personnage tmp) {
        SandArena.googleService.printError("Envoi mess");
        new Thread() {
            @Override
            public void run() {
                ConnexionMatch.prepareMatchEnvoiPerso(tmp);
            }
        }.start();
        panelGauche.ajout(tmp);
        if (!commence && testFin()) {
            container.finPrepare();
        } else {
            recoitPersonnageAutre();
        }
    }

    public void recoitPersonnageAutre() {
        SandArena.googleService.printError("Recoi mess");
        bloquand = true;
        new Thread() {
            @Override
            public void run() {
                Personnage recu = ConnexionMatch.prepareMatchRecoitPerso();
                container.setCheck(recu);
                panelDroit.ajout(recu);
                bloquand = false;
            }
        }.start();
    }

    public boolean testFin() {
        if (panelGauche.testFin() && panelDroit.testFin()) {
            return true;
        }
        return false;
    }

    public ArrayList<Personnage> getPersonnagesActif() {
        return panelGauche.getPersonnages();
    }

    public ArrayList<Personnage> getPersonnagesAutre() {
        return panelDroit.getPersonnages();
    }

    public boolean getCommence() {
        return commence;
    }

    public boolean getBloquand() {
        return bloquand;
    }


}

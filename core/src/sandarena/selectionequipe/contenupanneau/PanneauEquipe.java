package sandarena.selectionequipe.contenupanneau;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.googleservice.Sauvegarde;
import sandarena.selectionequipe.StageSelectionEquipe;

/**
 * Created by lucmo on 06/10/2015.
 */
public class PanneauEquipe extends Group {
    private final int place;
    private StageSelectionEquipe container;
    private Joueur equipe;
    private PanneauEquipeSuppr suppr;
    private boolean supprimer;

    public PanneauEquipe(StageSelectionEquipe stageSelectionEquipe, int place, Joueur equipe) {
        this.container = stageSelectionEquipe;
        this.place = place;
        this.equipe = equipe;
        this.setTouchable(Touchable.enabled);
        this.setBounds((Resolution.width / 3) * place, 0, Resolution.width / 3, Resolution.height);
        this.addListener(new PanneauEquipeListener(this));
        if (equipe != null) {
            suppr = new PanneauEquipeSuppr(this);
            this.addActor(suppr);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        if (equipe != null) {
            for (int i = 0; i < equipe.getPersonnages().size(); i++) {
                batch.draw(equipe.getPersonnages().get(i).commun.image, getX() + (getWidth() / 3) * (i % 3), getHeight() - (getWidth() / 3) * (1 + (i / 3)), getWidth() / 3, getWidth() / 3);
            }
            Font.font.setColor(Color.YELLOW);
            Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
            String strTmp = String.valueOf(equipe.getOr());
            Font.font.draw(batch, strTmp , getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * strTmp.length() / 2), getHeight()/6);
        }else {
            Font.font.setColor(Color.BLACK);
            Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
            Font.font.draw(batch, "Nouvelle",getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 8 / 2), getHeight() - (getHeight() / 3));
            Font.font.draw(batch, "Equipe", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 6 / 2), getHeight() - (getHeight() / 3) - Font.font.getLineHeight());
        }


    }

    public void clique() {
        if (equipe != null) {
            container.getContainer().getContainer().lanceGestionEquipe(equipe);
        } else {
            equipe = new Joueur(place);
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.getPersonnages().add(new Personnage());
            equipe.setOr(2000);
            SandArena.googleService.savedGamesUpdate(Sauvegarde.toSnapshotName(place), Sauvegarde.toData(equipe));
            suppr = new PanneauEquipeSuppr(this);
            this.addActor(suppr);
        }
    }

    public void suppr() {
        SandArena.googleService.savedGamesUpdate(Sauvegarde.toSnapshotName(place), Sauvegarde.toData(null));
        this.equipe.dispose();
        this.suppr.dispose();
        this.removeActor(suppr);
        this.suppr = null;
        this.equipe = null;
    }

    public void setSupprimer(boolean supprimer) {
        this.supprimer = supprimer;
    }

    public boolean getSupprimer() {
        return supprimer;
    }

    public StageSelectionEquipe getContainer() {
        return container;
    }

    public int getPlace() {
        return place;
    }

    public void setEquipe(Joueur equipe) {
        this.equipe = equipe;
    }
}

package sandarena.selectionequipe.Surcouche.nouvelleequipe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.googleservice.Sauvegarde;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.selectionequipe.Surcouche.Surcouche;
import sandarena.selectionequipe.Surcouche.nouvelleequipe.emplacement.Emplacement;


/**
 * Created by lucmo on 04/11/2015.
 */
public class PanneauEquipe extends Group {
    private  BoutonAccepte boutonAccepte;
    private  Surcouche container;
    private  FlechePanneau flecheGauche;
    private  FlechePanneau flecheDroite;
    private final int place;
    private int panel;
    private Emplacement[][] listePerso = new Emplacement[3][8];

    public PanneauEquipe(Surcouche container, int place) {
        this.setVisible(true);
        this.container = container;
        this.place = place;
        setBounds(Resolution.width / 10, Resolution.height / 10, Resolution.width / 10 * 8, Resolution.height / 10 * 8);
        this.flecheGauche = new FlechePanneau(this, true);
        this.flecheDroite = new FlechePanneau(this, false);
        this.addActor(flecheDroite);
        this.addActor(flecheGauche);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                listePerso[i][j] = new Emplacement(this, j, new Personnage());
                this.addActor(listePerso[i][j]);
                if (i != 0) {
                    listePerso[i][j].setVisible(false);
                }
            }
        }
        panel = 0;
        this.boutonAccepte = new BoutonAccepte(this);
        this.addActor(boutonAccepte);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.passive, 0, 0, Resolution.width, Resolution.height);
        batch.draw(Utili.fond, getX(), getY(), getWidth(), getHeight());
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        Font.font.setColor(Color.BLACK);
        Font.font.setScale(Resolution.ratioWidth * 5, Resolution.ratioHeight * 5);
        Font.font.draw(batch, "Nouvelle Equipe", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 15 / 2), getY()+getHeight() - (getHeight() / 16));
        super.draw(batch, parentAlpha);
    }

    public Surcouche getContainer() {
        return container;
    }

    public void changePanel(boolean gauche) {
        for (Emplacement emp : listePerso[panel]) {
            emp.setVisible(false);
        }
        if (gauche) {
            panel--;
        } else {
            panel++;
        }
        if (panel < 0) panel = 2;
        if (panel > 2) panel = 0;
        for (Emplacement emp : listePerso[panel]) {
            emp.setVisible(true);
        }
    }

    public void select() {
        Joueur equipe = new Joueur(place);
        for (int i = 0; i < 8; i++) {
            equipe.getPersonnages().add(listePerso[panel][i].getPerso());
        }
        equipe.setOr(2000);
        SandArena.googleService.savedGamesUpdate(Sauvegarde.toSnapshotName(place), Sauvegarde.toData(equipe));
        container.getContainer().getStage().addEquipe(panel, equipe);
        container.setVisible(false);
    }

    public void dispose() {
        container = null;
        flecheGauche.dispose();
        flecheDroite.dispose();
        flecheGauche = null;
        flecheDroite = null;
        boutonAccepte.dispose();
        boutonAccepte = null;
        for (Emplacement[] tab : listePerso) {
            for (Emplacement emp:tab) {
                emp.dispose();
            }
        }
        listePerso = null;
        remove();
        clear();
    }
}

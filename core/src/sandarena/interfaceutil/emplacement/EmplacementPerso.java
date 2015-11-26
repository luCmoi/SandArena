package sandarena.interfaceutil.emplacement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Utili;
import sandarena.infowindow.windows.InfoWindowPerso;
import sandarena.infowindow.windows.InfoWindowPersoIG;
import sandarena.joueur.Personnage;
import sandarena.match.partie.jeu.compcase.PersonnageIG;

/**
 * Created by lucmo on 12/11/2015.
 */
public class EmplacementPerso extends Group {
    protected Emplacement container;
    protected Personnage perso;
    protected InfoWindowPerso info;
    protected PersonnageIG persoIG;
    protected InfoWindowPersoIG infoIG;
    protected EmplacementBlessure[] blessures = new EmplacementBlessure[4];

    public EmplacementPerso(Emplacement container) {
        this.container = container;
        this.setBounds(0, 0, container.getHeight(), container.getHeight());
        for (int i = 0; i < 4; i++) {
            blessures[i] = new EmplacementBlessure(this,i);
            this.addActor(blessures[i]);
        }
    }

    public void addListenerBasique(){
        this.addListener(new EmplacementPersoListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (perso != null) {
            batch.draw(perso.commun.image, getX(), getY(), getWidth(), getHeight());
            if (getListeners().size > 0) {
                ((EmplacementPersoListener) (getListeners().get(0))).update();
            }
        }else if (persoIG != null){
            batch.draw(persoIG.getDonnee().commun.image, getX(), getY(), getWidth(), getHeight());
            if (getListeners().size > 0) {
                ((EmplacementPersoListener) (getListeners().get(0))).update();
            }
        }
        super.draw(batch, parentAlpha);
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
    }

    public Personnage getPerso() {
        return perso;
    }

    public PersonnageIG getPersoIG() {
        return persoIG;
    }

    public Emplacement getContainer() {
        return container;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
        for (EmplacementBlessure bless : blessures){
            bless.setPerso(perso);
        }
    }

    public void setPersoIG(PersonnageIG perso) {
        this.persoIG = perso;
        for (EmplacementBlessure bless : blessures){
            bless.setPerso(perso.getDonnee());
        }
    }

    public void pression() {
        if (perso != null) {
            this.info = new InfoWindowPerso(perso);
            container.getScreen().getSurcouche().addActor(info);
        } else if (persoIG != null){
            this.infoIG = new InfoWindowPersoIG(persoIG);
            container.getScreen().getSurcouche().addActor(infoIG);
        }
    }

    public void finPression() {
        if (info != null) {
            this.info.dispose();
            info = null;
        }else if (infoIG!= null){
            this.infoIG.dispose();
            infoIG =  null;
        }
    }

    public void clique(){
    }

    public void dispose() {
        container = null;
        perso = null;
        persoIG = null;
        if (info != null){
            info.dispose();
            info = null;
        }
        if (infoIG != null){
            infoIG.dispose();
            infoIG = null;
        }
        for (EmplacementBlessure bless : blessures){
            bless.dispose();
        }
        blessures = null;
        clear();
        remove();
    }
}

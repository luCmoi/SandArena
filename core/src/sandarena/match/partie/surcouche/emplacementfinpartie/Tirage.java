package sandarena.match.partie.surcouche.emplacementfinpartie;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.blessure.BanqueBlessure;
import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.donneestatic.Utili;
import sandarena.joueur.blessure.Blessure;

/**
 * Created by lucmo on 06/11/2015.
 */
public class Tirage extends Actor {
    private final EmplacementFinPartie container;
    private BanqueCompetence.EntreeCompetence cmp;
    private Blessure bls;
    private boolean test;
    private int replace;

    public Tirage(EmplacementFinPartie emplacementFinPartie) {
        container = emplacementFinPartie;
        this.setBounds(0, 0, container.getWidth() / 3 * 2, container.getWidth() / 3 * 2);
        if (container.getPerso().isMort()) {
            bls = container.getPerso().getBlessure();
        } else {
            cmp = BanqueCompetence.getCompetence(container.getPerso().getDonnee().commun.affinite.get((int) (Math.random() * container.getPerso().getDonnee().commun.affinite.size())),
                    container.getPerso().getDonnee().getCompetences());
            test = true;
        }
        replace = -1;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Utili.contour, getX(), getY(), getWidth(), getHeight());
        super.draw(batch, parentAlpha);
        if (cmp != null){
            batch.draw(cmp.image,getX(),getY(),getWidth(),getHeight());
        }else{
            batch.draw(bls.donnee.image,getX(),getY(),getWidth(),getHeight());
        }
    }

    public void retire(){
        if (cmp != null){
            cmp = BanqueCompetence.getCompetence(container.getPerso().getDonnee().commun.affinite.get((int) (Math.random() * container.getPerso().getDonnee().commun.affinite.size())),
                    container.getPerso().getDonnee().getCompetences());
        }else{
            bls = new Blessure(BanqueBlessure.getBlessure(container.getPerso().getDonnee().getBlessures()));
        }
    }

    public Blessure getBls() {
        return bls;
    }

    public BanqueCompetence.EntreeCompetence getCmp() {
        return cmp;
    }

    public int getReplace() {
        return replace;
    }
}

package sandarena.selectionequipe.Surcouche.nouvelleequipe;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;


/**
 * Created by lucmo on 04/11/2015.
 */
public class FlechePanneau extends Actor {
    private final PanneauEquipe container;
    private final boolean gauche;

    public FlechePanneau(PanneauEquipe panneauEquipe, boolean b) {
        this.container = panneauEquipe;
        this.gauche = b;
        if (gauche){
            setBounds(container.getWidth()/16,container.getHeight()/14,container.getWidth()/8,container.getWidth()/8);
        }else{
            setBounds(container.getWidth()-container.getWidth()/16-container.getWidth()/8,container.getHeight()/14,container.getWidth()/8,container.getWidth()/8);
        }
        this.addListener(new FlechePanneauListener(this));
        this.setTouchable(Touchable.enabled);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (gauche){
            batch.draw(Utili.fleche, getX(), getY(), getWidth(), getHeight());
        }else{
            batch.draw(Utili.fleche, getX(), getY(), getWidth(), getHeight(), 0, 0, Utili.fleche.getWidth(), Utili.fleche.getHeight(), true, false);
        }
        super.draw(batch, parentAlpha);
    }

    public void clique(){
        Son.menuSelect.play();
        container.changePanel(gauche);
    }

    public void dispose() {

    }
}

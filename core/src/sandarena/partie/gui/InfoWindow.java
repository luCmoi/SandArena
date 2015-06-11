package sandarena.partie.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.Resolution;
import sandarena.donnee.Utili;


/**
 * Created by Guillaume on 11/06/2015.
 */
public class InfoWindow extends Group {
    EmplacementPerso perso;
    EmplacementComp comp;

    public InfoWindow(EmplacementInterface emplacement) {
        if (emplacement instanceof  EmplacementPerso){
            this.perso = (EmplacementPerso)emplacement;
            //setBounds(Gdx.input.getX(), ((Resolution.height/4*3) - Gdx.input.getY()), (Resolution.height/4) *2 ,Resolution.height/4);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //todo probleme de reglage
        super.draw(batch, parentAlpha);
        batch.draw(Utili.competenceable, getX(), getY(), getWidth(), 200);
    }

    public void dispose(){
        perso = null;
        comp = null;
        clear();
        remove();
    }

}

package sandarena.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import sandarena.Resolution;
import sandarena.partie.Case;
import sandarena.partie.Partie;

/**
 *
 * @author Guillaume
 */
public class StageInterface extends Stage {

    public Texture image;

    public StageInterface(Viewport viewport, Batch batch) {
        super(viewport, batch);
        image = new Texture(Gdx.files.internal("Image/bleu.jpg"));

    }

    public void draw() {
        super.draw();
        /*Batch batch = this.getSpriteBatch();
        if (batch != null) {
            batch.begin();
            batch.draw(image, 0, 0, Resolution.width, Resolution.height / 4);
            batch.end();
        }*/

    }

    public void hopPartie(Partie p){
        this.addActor(new Case(0,0, p ));
        this.addActor(new Case(3,3,p));
    }
}

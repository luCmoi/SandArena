package sandarena.infowindow;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Guillaume on 12/06/2015.
 */
public class Icone extends Actor {
    private Texture img;

    public Icone(InfoWindow container, Texture img){
        super();
        setBounds(0,0,container.getHeight(),container.getHeight());
        this.img = img;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(img, getX(), getY(), getWidth(), getHeight());
    }

    public void dispose() {
        img = null;
        clear();
    }
}

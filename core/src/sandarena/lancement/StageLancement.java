package sandarena.lancement;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;

import sandarena.donnee.donneestatic.Resolution;

/**
 * Created by lucmo on 11/10/2015.
 */
public class StageLancement extends Stage {
    private ScreenLancement container;
    private EmplacementTexte texte;
    private Fond fond;

    public StageLancement(ScreenLancement screenLancement, SpriteBatch batch) {
        super(new FillViewport(Resolution.width, Resolution.height), batch);
        texte = new EmplacementTexte(this);
        this.container = screenLancement;
        this.fond = new Fond(this);
        this.addActor(fond);
        this.addActor(texte);
    }

    @Override
    public void draw() {
        super.draw();
    }

    public void setTexte(String texte) {
        this.texte.setTexte(texte);
    }

    @Override
    public void dispose() {
        container = null;
        fond.dispose();
        texte.dispose();
        fond = null;
        texte = null;
        super.dispose();
    }
}

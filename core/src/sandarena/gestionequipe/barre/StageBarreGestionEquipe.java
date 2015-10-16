package sandarena.gestionequipe.barre;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import sandarena.gestionequipe.ScreenGestionEquipe;
import sandarena.joueur.Joueur;

/**
 * Created by lucmo on 16/10/2015.
 */
public class StageBarreGestionEquipe extends Stage {
    private final ScreenGestionEquipe container;
    private final Joueur equipe;
    private final Fond fond;
    private QuickMatchButon boutonMatchRapide;

    public StageBarreGestionEquipe(ScreenGestionEquipe screenGestionEquipe, Joueur equipe, ExtendViewport extendViewport, Batch batch) {
        super(extendViewport,batch);
        this.container = screenGestionEquipe;
        this.equipe = equipe;
        fond = new Fond(this);
        boutonMatchRapide = new QuickMatchButon(this);
        this.addActor(fond);
        this.addActor(boutonMatchRapide);
    }

    @Override
    public void draw() {
        super.draw();
    }

    public ScreenGestionEquipe getContainer() {
        return container;
    }
}

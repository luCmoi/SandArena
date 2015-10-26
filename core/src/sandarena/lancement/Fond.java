package sandarena.lancement;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.googleservice.IGoogleService;

/**
 * Created by lucmo on 11/10/2015.
 */
class Fond extends Actor {
    private StageLancement container;

    public Fond(StageLancement stageLancement) {
        this.container = stageLancement;
        this.setBounds(0,0,Resolution.width,Resolution.height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utili.desertChargement, 0, 0, Resolution.width, Resolution.height);
        if (IGoogleService.data.versionName != null){
            Font.font.setColor(Color.WHITE);
            Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
            Font.font.draw(batch, IGoogleService.data.versionName, Resolution.width-Font.font.getSpaceWidth()*IGoogleService.data.versionName.length(), Font.font.getLineHeight());
        }
    }

    public void dispose() {
        container = null;
    }
}

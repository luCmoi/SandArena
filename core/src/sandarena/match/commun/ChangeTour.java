package sandarena.match.commun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.donnee.donneestatic.Utili;

/**
 * Created by lucmo on 28/10/2015.
 */
public class ChangeTour extends Group {
    private SurcoucheMatchCommun container;
    private boolean actif;
    private long timer;
    private long debut;

    public ChangeTour(SurcoucheMatchCommun surcoucheMatchCommun) {
        super();
        container = surcoucheMatchCommun;
        setBounds(Resolution.width / 2 - Resolution.height / 6, Resolution.height / 2, Resolution.height / 6 * 2, Resolution.height / 6);
        setVisible(false);
    }

    public void setVisible(boolean actif, boolean visible) {
        super.setVisible(visible);
        if (visible) {
            Son.nouveauTour.play();
            this.actif = actif;
            this.timer = System.currentTimeMillis();
            this.debut = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (isVisible()) {
            if (System.currentTimeMillis() - debut < 2000) {
                batch.draw(Utili.passive, 0, 0, Resolution.width, Resolution.height);
                if (getWidth() < Resolution.width / 3 + getHeight() * 2) {
                    long addition = (long) (2*((Resolution.width / 3) * ((System.currentTimeMillis() - timer) / 1000.0)));
                    setWidth(Math.min(Resolution.width / 3 + getHeight() * 2, getWidth() + addition));
                    setX(Math.min(Resolution.width - (Resolution.width / 3 + getHeight() * 2), getX() - addition / 2));
                    timer = System.currentTimeMillis();
                }
                batch.draw(Utili.fleche2, getX(), getY(), getHeight(), getHeight());
                batch.draw(Utili.fleche2, getX() + getWidth() - getHeight(), getY(), getHeight(), getHeight(),0,0,Utili.fleche2.getWidth(),Utili.fleche2.getHeight(),true,false);
                batch.draw(Utili.fond, getX() + getHeight(), getY(), getWidth() - getHeight() * 2, getHeight());
                batch.draw(Utili.contour, getX() + getHeight(), getY(), getWidth() - getHeight() * 2, getHeight());
                Font.font.setColor(Color.BLACK);
                Font.font.setScale(Resolution.ratioWidth * 4, Resolution.ratioHeight * 4);
                if (getWidth() > Font.font.getSpaceWidth() * 12 + 2 * getHeight()) {
                    if (actif) {
                        Font.font.draw(batch, "Votre Tour", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 10 / 2), getY() + getHeight() - getHeight() / 4);
                    } else {
                        Font.font.draw(batch, "Tour Adverse", getX() + (getWidth() / 2) - (Font.font.getSpaceWidth() * 12 / 2), getY() + getHeight() - getHeight() / 4);
                    }
                }
                super.draw(batch, parentAlpha);
            } else {
                container.setVisible(false);
                setX(Resolution.width / 2 - Resolution.height / 6);
                setWidth(Resolution.height / 6 * 2);
                timer = -1;
                debut = -1;
            }
        }
    }

    public SurcoucheMatchCommun getContainer() {
        return container;
    }

    public boolean getActif() {
        return actif;
    }

    public void dispose() {
        container = null;
    }
}

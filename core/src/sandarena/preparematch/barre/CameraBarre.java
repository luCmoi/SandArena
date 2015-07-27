package sandarena.preparematch.barre;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sandarena.Resolution;

/**
 * Created by Guillaume on 25/07/2015.
 */
public class CameraBarre extends OrthographicCamera {
    private final sandarena.preparematch.barre.StageBarre container;
    private static final int VITESSECAM = 10;
    private boolean gauche;
    private boolean droite;

    public CameraBarre(sandarena.preparematch.barre.StageBarre stageBarre) {
        super();
        this.container = stageBarre;
        this.setToOrtho(false, Resolution.width - Resolution.differenceBas, Resolution.differenceBas);
        this.position.set(container.getWidth() / 2, container.getHeight() / 2, 0);
        gauche= false;
        droite= false;
    }

    public void gauche(){
        gauche = true;
    }

    public void droite(){
        droite = true;
    }

    @Override
    public void update() {
        super.update();
        if (gauche){
            if (position.x > container.getViewport().getWorldWidth() / 2) {
                translate(-(int) (VITESSECAM * Resolution.ratioWidth), 0, 0);
                if (position.x < container.getViewport().getWorldWidth() / 2) {
                    position.x = container.getViewport().getWorldWidth() / 2;
                }
            }
        }else if (droite){
            if (position.x < container.getWidthTailleTotale() - container.getViewport().getWorldWidth() / 2) {
                translate((int) (VITESSECAM * Resolution.ratioWidth), 0, 0);
                if (position.x > container.getWidthTailleTotale() - container.getViewport().getWorldWidth() / 2) {
                    position.x = container.getWidthTailleTotale() - container.getViewport().getWorldWidth() / 2;
                }
            }
        }
    }

    public void stop() {
        gauche= false;
        droite = false;
    }
}

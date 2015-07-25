package sandarena.preparematch;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sandarena.Resolution;

/**
 * Created by Guillaume on 25/07/2015.
 */
public class CameraBarre extends OrthographicCamera {
    private final StageBarre container;
    private static final int VITESSECAM = 10;

    public CameraBarre(StageBarre stageBarre) {
        super();
        this.container = stageBarre;
        this.setToOrtho(false, Resolution.width - Resolution.differenceBas, Resolution.differenceBas);
        this.position.set(container.getWidth() / 2, container.getHeight() / 2, 0);
    }

    public void gauche(){
        System.out.println("Gauche");
        if (position.x > container.getViewport().getWorldWidth() / 2) {
            translate(-(int) (VITESSECAM * Resolution.ratioWidth), 0, 0);
            if (position.x < container.getViewport().getWorldWidth() / 2) {
                position.x = container.getViewport().getWorldWidth() / 2;
            }
        }
    }

    public void droite(){
        System.out.println("Droite");
        if (position.x < container.getWidthTailleTotale() - container.getViewport().getWorldWidth() / 2) {
            translate((int) (VITESSECAM * Resolution.ratioWidth), 0, 0);
            if (position.x > container.getWidthTailleTotale() - container.getViewport().getWorldWidth() / 2) {
                position.x = container.getWidthTailleTotale() - container.getViewport().getWorldWidth() / 2;
            }
        }
    }
}

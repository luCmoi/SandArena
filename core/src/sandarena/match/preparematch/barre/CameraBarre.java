package sandarena.match.preparematch.barre;

import com.badlogic.gdx.graphics.OrthographicCamera;

import sandarena.donnee.donneestatic.Resolution;

/**
 * Created by Guillaume on 25/07/2015.
 */
class CameraBarre extends OrthographicCamera {
    private StageBarre container;
    private static final int VITESSECAM = 10;
    private boolean gauche;
    private boolean droite;
    private float x;

    public CameraBarre(StageBarre stageBarre) {
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

    public float getX() {
        return position.x-(container.getWidth()/2);
    }

    public void dispose() {
        container = null;
    }
}

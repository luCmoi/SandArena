package sandarena.match.commun;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import sandarena.donnee.donneestatic.Font;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Utili;
import sandarena.googleservice.ConnexionMatch;

/**
 * Created by lucmo on 24/10/2015.
 */
public class Timer extends Actor {
    int valeur;
    long dernierTemp;
    public static int TEMPSPREPAS = 40;
    public static int TEMPSTOUR = 180;
    private boolean prepa;

    public Timer(boolean prepa) {
        super();
        this.prepa = prepa;
        setBounds(Resolution.width / 2 - Resolution.width / 16, Resolution.height - Resolution.width / 16, Resolution.width / 8, Resolution.width / 16);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        long tempTmp = System.currentTimeMillis();
        if (tempTmp-dernierTemp > 1000){
            valeur = valeur - (int)((tempTmp - dernierTemp)/1000);
            dernierTemp = tempTmp - ((tempTmp-dernierTemp)%1000);

        }
        batch.draw(Utili.timer, getX(), getY(), getWidth(), getHeight());
        Font.font.setScale(Resolution.ratioWidth * 3, Resolution.ratioHeight * 3);
        if ((prepa && valeur > 10) || (!prepa && valeur > 30)){
            Font.font.setColor(Color.BLACK);
        }else{
            Font.font.setColor(Color.RED);
        }
        Font.font.draw(batch,String.valueOf(valeur),getX()+(getWidth() / 2) - (Font.font.getSpaceWidth() * String.valueOf(valeur).length()/ 2),getY()+ getHeight() - (getHeight() / 4));
    }

    public void reset(){
        dernierTemp = System.currentTimeMillis();
        if (prepa) {
            valeur = TEMPSPREPAS;
        }else{
            valeur = TEMPSTOUR;
        }
        ConnexionMatch.envoiTimer(valeur);
    }

    public void newTime(int temps){
        dernierTemp = System.currentTimeMillis();
        valeur = temps;
    }
}

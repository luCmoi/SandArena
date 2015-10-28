package sandarena.donnee.donneestatic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by lucmo on 25/10/2015.
 */
public class Son {
    public final static Music nomads= Gdx.audio.newMusic(Gdx.files.internal("Son/Nomads.mp3"));
    public final static Music sadStrings = Gdx.audio.newMusic(Gdx.files.internal("Son/SadStrings.mp3"));
    public final static Music ambiancePrepare = Gdx.audio.newMusic(Gdx.files.internal("Son/AmbiencePrepare.mp3"));
    public final static Music nouveauTour = Gdx.audio.newMusic(Gdx.files.internal("Son/AdversaireTrouve.mp3"));
    public final static Music persoSelect = Gdx.audio.newMusic(Gdx.files.internal("Son/PersoSelect.mp3"));
    public final static Music menuSelect = Gdx.audio.newMusic(Gdx.files.internal("Son/MenuSelect0.mp3"));
    public final static Music victoire = Gdx.audio.newMusic(Gdx.files.internal("Son/Victoire.mp3"));
    public final static Music defaite = Gdx.audio.newMusic(Gdx.files.internal("Son/Defaite.mp3"));
    public static Music actuelle;
}

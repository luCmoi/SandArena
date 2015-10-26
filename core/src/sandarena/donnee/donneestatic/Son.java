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
    public final static Music adversaireTrouve = Gdx.audio.newMusic(Gdx.files.internal("Son/AdversaireTrouve.mp3"));
    public final static Music PersoSelect = Gdx.audio.newMusic(Gdx.files.internal("Son/PersoSelect.mp3"));
    public final static Music MenuSelect = Gdx.audio.newMusic(Gdx.files.internal("Son/MenuSelect0.mp3"));
    public static Music actuelle;
}

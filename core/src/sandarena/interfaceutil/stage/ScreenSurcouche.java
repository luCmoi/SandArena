package sandarena.interfaceutil.stage;

import com.badlogic.gdx.Screen;

/**
 * Created by lucmo on 12/11/2015.
 */
public abstract class ScreenSurcouche implements Screen {
    protected Surcouche surcouche;


    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public Surcouche getSurcouche() {
        return surcouche;
    }

    public abstract void finInputSurcouche();

    public abstract void backKeyPressed();
}

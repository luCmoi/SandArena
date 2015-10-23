package sandarena.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import sandarena.SandArena;

/**
 * Created by lucmo on 23/10/2015.
 */
public class GameFragment extends AndroidFragmentApplication {

    public GameFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initializeForView(new SandArena());
    }


    @Override
    public void exit() {
    }
}

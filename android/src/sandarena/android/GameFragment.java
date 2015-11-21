package sandarena.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import sandarena.SandArena;

/**
 * Created by lucmo on 23/10/2015.
 */
public class GameFragment extends AndroidFragmentApplication {

    public GameFragment(){
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AdView adView = createAdView();
        adView.setLayoutParams(createParams());
        container.addView(adView);
        adView.loadAd(createAdRequest());
        container.refreshDrawableState();
        return initializeForView(new SandArena());
    }
    public AdRequest createAdRequest() {
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("abc")
                .build();
        return request;
    }
    public AdView createAdView() {
        AdView adView = new AdView(getContext());
        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        adView.setAdSize(AdSize.FULL_BANNER);
        return adView;
    }

    public RelativeLayout.LayoutParams createParams() {
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        return params1;
    }


    @Override
    public void exit() {
    }

}

package sandarena.android;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;

/**
 * Created by lucmo on 07/10/2015.
 */
public class AndroidRealTimeMessageReceivedListener implements RealTimeMessageReceivedListener {
    private final AndroidLauncher container;

    public AndroidRealTimeMessageReceivedListener(AndroidLauncher androidLauncher) {
        this.container = androidLauncher;
    }

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {

    }
}

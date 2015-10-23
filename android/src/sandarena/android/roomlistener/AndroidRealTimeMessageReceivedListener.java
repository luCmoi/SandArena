package sandarena.android.roomlistener;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;

import sandarena.SandArena;
import sandarena.android.GameHelperFragment;
import sandarena.googleservice.IGoogleService;

/**
 * Created by lucmo on 07/10/2015.
 */
public class AndroidRealTimeMessageReceivedListener implements RealTimeMessageReceivedListener {
    private final GameHelperFragment container;
    /*private final AndroidLauncher container;

    public AndroidRealTimeMessageReceivedListener(AndroidLauncher androidLauncher) {
        this.container = androidLauncher;
    }*/

    public AndroidRealTimeMessageReceivedListener(GameHelperFragment gameHelperFragment) {
        this.container = gameHelperFragment;
    }

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        SandArena.googleService.printError("Received : " + realTimeMessage.getSenderParticipantId() + " " + realTimeMessage.getMessageData());
        IGoogleService.data.mess = realTimeMessage.getMessageData();
    }
}

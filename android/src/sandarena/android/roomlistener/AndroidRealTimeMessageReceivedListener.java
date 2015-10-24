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


    public AndroidRealTimeMessageReceivedListener(GameHelperFragment gameHelperFragment) {
        this.container = gameHelperFragment;
    }

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
        SandArena.googleService.printError("Received : " + realTimeMessage.getSenderParticipantId() + " " + realTimeMessage.getMessageData());
        if (realTimeMessage.getMessageData().length < 4) {
            IGoogleService.data.time = realTimeMessage.getMessageData();
        } else {
            IGoogleService.data.mess = realTimeMessage.getMessageData();
        }
    }
}

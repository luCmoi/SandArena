package sandarena.android.roomlistener;

import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;

import sandarena.android.AndroidLauncher;
import sandarena.googleservice.IGoogleService;

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
        container.printError("Received : "+realTimeMessage.getSenderParticipantId()+" "+realTimeMessage.getMessageData());
        IGoogleService.data.mess = realTimeMessage.getMessageData();
    }
}

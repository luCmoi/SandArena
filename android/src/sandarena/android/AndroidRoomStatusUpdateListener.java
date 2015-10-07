package sandarena.android;

import android.view.WindowManager;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;

import java.util.List;

/**
 * Created by lucmo on 07/10/2015.
 */
public class AndroidRoomStatusUpdateListener implements RoomStatusUpdateListener{
    private final AndroidLauncher container;
    boolean playing = false;
    final static int NB_PLAYER = 2;

    public AndroidRoomStatusUpdateListener(AndroidLauncher androidLauncher) {
        this.container =androidLauncher;
    }

    boolean shouldStartGame(Room room) {
        int connectedPlayers = 0;
        for (Participant p : room.getParticipants()) {
            if (p.isConnectedToRoom()) ++connectedPlayers;
        }
        return connectedPlayers == NB_PLAYER;
    }

    @Override
    public void onPeersConnected(Room room, List<String> peers) {
        if (playing) {
            System.err.println("Game allready launched");
        } else if (shouldStartGame(room)) {
            System.err.println("Game Start here");
        }
    }

    @Override
    public void onPeersDisconnected(Room room, List<String> peers) {
        if (playing) {
            System.err.println("Player left when in game");
        } else {
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), null, room.getCreatorId());
            //container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void onP2PConnected(String s) {

    }

    @Override
    public void onP2PDisconnected(String s) {

    }

    @Override
    public void onRoomConnecting(Room room) {

    }

    @Override
    public void onRoomAutoMatching(Room room) {

    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> list) {

    }

    @Override
    public void onPeerDeclined(Room room, List<String> list) {

    }

    @Override
    public void onPeerJoined(Room room, List<String> list) {
    }

    @Override
    public void onPeerLeft(Room room, List<String> peers) {
        // peer left -- see if game should be canceled
        if (playing) {
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), null, room.getCreatorId());
            container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    @Override
    public void onConnectedToRoom(Room room) {
    }

    @Override
    public void onDisconnectedFromRoom(Room room) {
    }

}

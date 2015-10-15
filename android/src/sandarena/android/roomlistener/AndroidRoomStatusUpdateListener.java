package sandarena.android.roomlistener;

import android.view.WindowManager;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;

import java.util.List;

import sandarena.android.AndroidLauncher;
import sandarena.googleservice.IGoogleService;

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
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
        if (playing) {
            System.err.println("Game allready launched");
        } else if (shouldStartGame(room)) {
            for (String id : room.getParticipantIds()){
                if (id != container.getMyId()){
                    container.setEnnemyId(id);
                }
            }
            if (room.getParticipantIds().get(0)==container.getMyId()){
                IGoogleService.data.commence = true;
            }
            container.setRoomId(room.getRoomId());
            IGoogleService.data.lancePartie = true;
        }
    }

    @Override
    public void onPeersDisconnected(Room room, List<String> peers) {
        if (playing) {
            System.err.println("Player left when in game");
        } else {
            container.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
            IGoogleService.data.justLeft = true;
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
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onRoomAutoMatching(Room room) {
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> list) {
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onPeerDeclined(Room room, List<String> list) {
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onPeerJoined(Room room, List<String> list) {
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onPeerLeft(Room room, List<String> peers) {
        if (playing) {
            container.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
            IGoogleService.data.justLeft = true;
        }
    }

    @Override
    public void onConnectedToRoom(Room room) {
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onDisconnectedFromRoom(Room room) {
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

}

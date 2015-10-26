package sandarena.android.roomlistener;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;

import java.util.List;

import sandarena.android.GameHelperFragment;
import sandarena.googleservice.IGoogleService;

/**
 * Created by lucmo on 07/10/2015.
 */
public class AndroidRoomStatusUpdateListener implements RoomStatusUpdateListener {
    private final GameHelperFragment container;
    private boolean playing = false;
    private final static int NB_PLAYER = 2;


    public AndroidRoomStatusUpdateListener(GameHelperFragment gameHelperFragment) {
        this.container = gameHelperFragment;
    }

    private boolean shouldStartGame(Room room) {
        int connectedPlayers = 0;
        for (Participant p : room.getParticipants()) {
            if (p.isConnectedToRoom()) ++connectedPlayers;
        }
        return connectedPlayers == NB_PLAYER;
    }

    @Override
    public void onPeersConnected(Room room, List<String> peers) {
        System.err.println("onPeerConected");
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
        if (playing) {
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
            container.setRoomId(null);
            IGoogleService.data.justLeft = true;
        } else if (shouldStartGame(room)) {
            for (String id : room.getParticipantIds()) {
                if (id != container.getMyId()) {
                    container.setEnnemyId(id);
                }
            }
            if (room.getParticipantIds().get(0) == container.getMyId()) {
                IGoogleService.data.commence = true;
            }
            container.setRoomId(room.getRoomId());
            IGoogleService.data.lancePartie = true;
        }
    }

    @Override
    public void onPeersDisconnected(Room room, List<String> peers) {
        System.err.println("onPeerDisconnected");
        Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
        container.setRoomId(null);
    }

    @Override
    public void onP2PConnected(String s) {

    }

    @Override
    public void onP2PDisconnected(String s) {

    }

    @Override
    public void onRoomConnecting(Room room) {
        System.err.println("onRoomConnecting");
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onRoomAutoMatching(Room room) {
        System.err.println("onRoomAutoMatching");
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
        Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
        container.setRoomId(null);
        IGoogleService.data.justLeft = true;
    }

    @Override
    public void onPeerJoined(Room room, List<String> list) {
        System.err.println("onPeerJoined");
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onPeerLeft(Room room, List<String> peers) {
        System.err.println("onPeerLeft");
        Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
        container.setRoomId(null);
    }

    @Override
    public void onConnectedToRoom(Room room) {
        System.err.println("onConnectedToRoom");
        container.setRoomId(room.getRoomId());
        container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
    }

    @Override
    public void onDisconnectedFromRoom(Room room) {
        System.err.println("onDisconnectedFromRoom");
        Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
        container.setRoomId(null);
        IGoogleService.data.justLeft = true;
    }

}

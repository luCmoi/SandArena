package sandarena.android.roomlistener;

import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;

import sandarena.android.GameHelperFragment;
import sandarena.googleservice.IGoogleService;

/**
 * Created by lucmo on 07/10/2015.
 */
public class AndroidRoomUpdateListener implements RoomUpdateListener {
    private final GameHelperFragment container;

    public AndroidRoomUpdateListener(GameHelperFragment gameHelperFragment) {
        this.container = gameHelperFragment;
    }

    @Override
    public void onRoomCreated(int statusCode, Room room) {
        System.err.println("onRoomCreated");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            if (room.getRoomId() != null) {
                Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
            }
            container.setRoomId(null);
            IGoogleService.data.justLeft = true;
            System.err.println("Error when creating the room");
        } else {
            container.setRoomId(room.getRoomId());
            container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
        }
    }

    @Override
    public void onJoinedRoom(int statusCode, Room room) {
        System.err.println("onJoinedRoom");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
            container.setRoomId(null);
            IGoogleService.data.justLeft = true;
            System.err.println("Error when Joining the room");
        } else {
            container.setRoomId(room.getRoomId());
            container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
        }
    }

    @Override
    public void onLeftRoom(int i, String s) {
    }

    @Override
    public void onRoomConnected(int statusCode, Room room) {
        System.err.println("onRoomConnected");
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            Games.RealTimeMultiplayer.leave(container.get_gameHelper().getApiClient(), container.getUpdateListener(), room.getRoomId());
            container.setRoomId(null);
            IGoogleService.data.justLeft = true;
            System.err.println("Error when Connecting the room");
        } else {
            container.setRoomId(room.getRoomId());
            container.setMyId(room.getParticipantId(Games.Players.getCurrentPlayerId(container.get_gameHelper().getApiClient())));
        }
    }
}


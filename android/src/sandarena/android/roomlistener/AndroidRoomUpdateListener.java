package sandarena.android.roomlistener;

import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;

import sandarena.android.AndroidLauncher;

/**
 * Created by lucmo on 07/10/2015.
 */
public class AndroidRoomUpdateListener implements RoomUpdateListener {
    private final AndroidLauncher container;

    public AndroidRoomUpdateListener(AndroidLauncher androidLauncher) {
        this.container = androidLauncher;
    }

    @Override
    public void onRoomCreated(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            System.err.println("Error when creating the room");
        }
    }

    @Override
    public void onJoinedRoom(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            System.err.println("Error when Joining the room");
        }
    }

    @Override
    public void onLeftRoom(int i, String s) {

    }

    @Override
    public void onRoomConnected(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            System.err.println("Error when Connecting the room");
        }
    }

}

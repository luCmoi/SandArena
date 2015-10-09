package sandarena.android.roomlistener;

import android.view.WindowManager;

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
            container.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
        }
        System.err.println("Error when creating the room");
    }

    @Override
    public void onJoinedRoom(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            container.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
        }
        System.err.println("Error when Joining the room");
    }

    @Override
    public void onLeftRoom(int i, String s) {
        container.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
    }

    @Override
    public void onRoomConnected(int statusCode, Room room) {
        if (statusCode != GamesStatusCodes.STATUS_OK) {
            container.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    container.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                }
            });
        }
        System.err.println("Error when Connecting the room");
    }
}


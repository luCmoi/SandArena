package sandarena.android;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.plus.Plus;

import sandarena.SandArena;
import sandarena.android.roomlistener.AndroidRealTimeMessageReceivedListener;
import sandarena.android.roomlistener.AndroidRoomStatusUpdateListener;
import sandarena.android.roomlistener.AndroidRoomUpdateListener;

/**
 * Created by lucmo on 23/10/2015.
 */
public class GameHelperFragment extends Fragment {
    private GameHelper _gameHelper;
    private AndroidLauncher mActivity = null;
    private AndroidRoomUpdateListener updateListener;
    private String roomId;
    private String myId;
    private String ennemyId;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (AndroidLauncher) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        _gameHelper = new GameHelper(mActivity, GameHelper.CLIENT_GAMES);
        _gameHelper.createApiClientBuilder().addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
                .addApi(Games.API).addScope(Games.SCOPE_GAMES)
                .addApi(Drive.API).addScope(Drive.SCOPE_APPFOLDER);
        _gameHelper.enableDebugLog(false);
        GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
            @Override
            public void onSignInSucceeded() {
            }
            @Override
            public void onSignInFailed() {
            }
        };
        _gameHelper.setup(gameHelperListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }
    public GameHelper get_gameHelper() {
        return _gameHelper;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        _gameHelper.onStop();
    }

    public void startQuickGame() {
        Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0);
        updateListener = new AndroidRoomUpdateListener(this);
        RoomConfig.Builder roomConfigBuilder = RoomConfig.builder(updateListener)
                .setRoomStatusUpdateListener(new AndroidRoomStatusUpdateListener(this))
                .setMessageReceivedListener(new AndroidRealTimeMessageReceivedListener(this));
        roomConfigBuilder.setAutoMatchCriteria(am);
        RoomConfig roomConfig = roomConfigBuilder.build();
        Games.RealTimeMultiplayer.create(_gameHelper.getApiClient(), roomConfig);
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
    }


    public void quitQuickGame(boolean waitId) {
        System.err.println("Trying on quit");
        if (roomId != null) {
            System.err.println("on quit");
            Games.RealTimeMultiplayer.leave(get_gameHelper().getApiClient(), updateListener, roomId);
            roomId = null;
        }else if (waitId){
            long time = System.currentTimeMillis();
            while(roomId == null){
                if (System.currentTimeMillis() - time> 100){
                    time = System.currentTimeMillis();
                }
            }
            System.err.println("on quit");
            Games.RealTimeMultiplayer.leave(get_gameHelper().getApiClient(), updateListener, roomId);
            roomId = null;
        }
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    public String getMyId() {
        return myId;
    }

    public void setEnnemyId(String ennemyId) {
        this.ennemyId = ennemyId;
    }

    public AndroidRoomUpdateListener getUpdateListener() {
        return updateListener;
    }

    public String getRoomId() {
        return roomId;
    }

    public Activity getmActivity() {
        return mActivity;
    }

    public void sendOtherPlayer(String mess) {
        if (roomId != null) {
            SandArena.googleService.printError("Send : " + mess);
            byte[] message = mess.getBytes();
            SandArena.googleService.printError("Send : " + message);
            SandArena.googleService.printError("From : " + myId + " To :" + ennemyId + " In : " + roomId);
            Games.RealTimeMultiplayer.sendReliableMessage(get_gameHelper().getApiClient(), null, message, roomId, ennemyId);
        }
    }
}

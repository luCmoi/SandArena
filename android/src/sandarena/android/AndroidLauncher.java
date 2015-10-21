package sandarena.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.plus.Plus;

import java.io.IOException;

import sandarena.SandArena;
import sandarena.android.roomlistener.AndroidRealTimeMessageReceivedListener;
import sandarena.android.roomlistener.AndroidRoomStatusUpdateListener;
import sandarena.android.roomlistener.AndroidRoomUpdateListener;
import sandarena.googleservice.IGoogleService;

public class AndroidLauncher extends AndroidApplication implements IGoogleService {
    private GameHelper _gameHelper;
    // Request code used to invoke Snapshot selection UI.
    private static final int RC_SELECT_SNAPSHOT = 9002;
    private String roomId;
    private String myId;
    private String ennemyId;
    private AndroidRoomUpdateListener updateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
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
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new SandArena(this), config);
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    _gameHelper.beginUserInitiatedSignIn();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
        }

    }

    @Override
    public void signOut() {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    _gameHelper.signOut();
                }
            });
        } catch (Exception e) {
            Gdx.app.log("MainActivity", "Log out failed: " + e.getMessage() + ".");
        }
    }

    @Override
    public void rateGame() {

    }

    @Override
    public void submitScore(long score) {
    }

    @Override
    public void showScores() {

    }

    @Override
    public boolean isSignedIn() {
        return _gameHelper.isSignedIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        _gameHelper.onStart(this);
    }

    @Override
    protected void onStop() {
        _gameHelper.onStop();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        _gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    private String displaySnapshotMetadata(SnapshotMetadata metadata) {
        if (metadata == null) {
            return "";
        }
        String metadataStr = "Source: Saved Games" + '\n'
                + "Description: " + metadata.getDescription() + '\n'
                + "Name: " + metadata.getUniqueName() + '\n'
                + "Last Modified: " + String.valueOf(metadata.getLastModifiedTimestamp()) + '\n'
                + "Played Time: " + String.valueOf(metadata.getPlayedTime()) + '\n'
                + "Cover Image URL: " + metadata.getCoverImageUrl();
        return metadataStr;
    }

    public void savedGamesLoad(String snapshotName, final int place) {
        PendingResult<Snapshots.OpenSnapshotResult> pendingResult = Games.Snapshots.open(_gameHelper.getApiClient(), snapshotName, false);
        ResultCallback<Snapshots.OpenSnapshotResult> callback = new ResultCallback<Snapshots.OpenSnapshotResult>() {
            @Override
            public void onResult(Snapshots.OpenSnapshotResult openSnapshotResult) {
                if (openSnapshotResult.getStatus().isSuccess()) {
                    System.err.println("Saved game load success");
                    byte[] data = new byte[0];
                    try {
                        data = openSnapshotResult.getSnapshot().getSnapshotContents().readFully();
                    } catch (IOException e) {
                        System.err.println("Exception reading snapshot");
                    }
                    String donnee = new String(data);
                    String metaDonnee = displaySnapshotMetadata(openSnapshotResult.getSnapshot().getMetadata());
                    System.err.println(donnee);
                    System.err.println(metaDonnee);
                    if (!donnee.startsWith("v004")) {
                        System.err.println("Passe pas");
                        IGoogleService.data.save[place] = null;
                        IGoogleService.data.meta[place] = null;
                    } else {
                        System.err.println("Passe");
                        IGoogleService.data.save[place] = donnee;
                        IGoogleService.data.meta[place] = metaDonnee;
                    }
                } else {
                    System.err.println("Saved game load failure");
                    IGoogleService.data.save[place] = null;
                    IGoogleService.data.meta[place] = null;
                }
                IGoogleService.data.chargementSaveLoad++;
            }
        };
        pendingResult.setResultCallback(callback);
    }

    public void savedGamesUpdate(final String snapshotName, String dataStr) {
        final boolean createIfMissing = true;
        final byte[] data = dataStr.getBytes();
        AsyncTask<Void, Void, Boolean> updateTask = new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                Snapshots.OpenSnapshotResult open = Games.Snapshots.open(_gameHelper.getApiClient(), snapshotName, createIfMissing).await();
                if (!open.getStatus().isSuccess()) {
                    System.err.println("Could not open Snapshot for update.");
                    return false;
                }
                // Change data but leave existing metadata
                Snapshot snapshot = open.getSnapshot();
                snapshot.getSnapshotContents().writeBytes(data);
                Snapshots.CommitSnapshotResult commit = Games.Snapshots.commitAndClose(_gameHelper.getApiClient(), snapshot, SnapshotMetadataChange.EMPTY_CHANGE).await();
                if (!commit.getStatus().isSuccess()) {
                    System.err.println("Failed to commit Snapshot.");
                    return false;
                }
                return true;
            }

            @Override
            protected void onPostExecute(Boolean result) {
                if (result) {
                    System.err.println("Saved game update success");
                } else {
                    System.err.println("Saved game update failure");
                }
            }
        };
        updateTask.execute();
    }

    @Override
    public void savedGamesLoadAll() {
        SandArena.googleService.savedGamesLoad("snapSand-0", 0);
        SandArena.googleService.savedGamesLoad("snapSand-1", 1);
        SandArena.googleService.savedGamesLoad("snapSand-2", 2);
    }

    @Override
    public void printError(String print) {
        System.err.println(print);
    }

    @Override
    public void startQuickGame() {
        Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0);
        updateListener = new AndroidRoomUpdateListener(this);
        RoomConfig.Builder roomConfigBuilder = RoomConfig.builder(updateListener)
                .setRoomStatusUpdateListener(new AndroidRoomStatusUpdateListener(this))
                .setMessageReceivedListener(new AndroidRealTimeMessageReceivedListener(this));
        roomConfigBuilder.setAutoMatchCriteria(am);
        RoomConfig roomConfig = roomConfigBuilder.build();
        Games.RealTimeMultiplayer.create(_gameHelper.getApiClient(), roomConfig);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });

    }

    public GameHelper get_gameHelper() {
        return _gameHelper;
    }

    public void sendOtherPlayer(String mess) {
        printError("Send : "+mess);
        byte[] message = mess.getBytes();
        printError("Send : " + message);
        printError("From : "+myId+" To :"+ennemyId+" In : "+roomId);
        Games.RealTimeMultiplayer.sendReliableMessage(_gameHelper.getApiClient(), null, message, roomId, ennemyId);
    }

    @Override
    public void quitQuickGame() {
        if (roomId != null) {
            Games.RealTimeMultiplayer.leave(_gameHelper.getApiClient(), updateListener, roomId);
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
}

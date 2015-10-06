package sandarena.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.plus.Plus;

import java.io.IOException;

import sandarena.IGoogleService;
import sandarena.SandArena;

public class AndroidLauncher extends AndroidApplication implements IGoogleService {
    private static final String TAG = "Err";
    private GameHelper _gameHelper;
    // Request code used to invoke Snapshot selection UI.
    private static final int RC_SELECT_SNAPSHOT = 9002;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create the GameHelper.
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

    public void savedGame() {
        /*int maxNumberOfSavedGamesToShow = 5;
        Intent savedGamesIntent = Games.Snapshots.getSelectSnapshotIntent(_gameHelper.getApiClient(), "See My Saves", true, true, maxNumberOfSavedGamesToShow);
        startActivityForResult(savedGamesIntent, RC_SAVED_GAMES);*/
        AsyncTask<Void, Void, Integer> task = new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                // Open the saved game using its name.
                Snapshots.OpenSnapshotResult result = Games.Snapshots.open(_gameHelper.getApiClient(),
                        "Premier", true).await();
                // Check the result of the open operation
                if (result.getStatus().isSuccess()) {
                    Snapshot snapshot = result.getSnapshot();
                    // Read the byte content of the saved game.
                    try {
                        byte[] mSaveGameData = snapshot.getSnapshotContents().readFully();
                    } catch (IOException e) {
                        Log.e(TAG, "Error while reading Snapshot.", e);
                    }
                } else {
                    Log.e(TAG, "Error while loading: " + result.getStatus().getStatusCode());
                }

                return result.getStatus().getStatusCode();
            }

            @Override
            protected void onPostExecute(Integer status) {
                // Dismiss progress dialog and reflect the changes in the UI.
                // ...
            }
        };

        task.execute();

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

    /**
     * Display metadata about Snapshot save data.
     *
     * @param metadata the SnapshotMetadata associated with the saved game.
     */
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

    /**
     * Load a Snapshot from the Saved Games service based on its unique name.  After load, the UI
     * will update to display the Snapshot data and SnapshotMetadata.
     *
     * @param snapshotName the unique name of the Snapshot.
     * @param place
     */
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
                    if (!donnee.startsWith("v001")){
                        IGoogleService.data.save[place]=null;
                        IGoogleService.data.meta[place]=null;
                    }else{
                        IGoogleService.data.save[place]=donnee;
                        IGoogleService.data.meta[place]=metaDonnee;
                    }
                } else {
                    System.err.println("Saved game load failure");
                    IGoogleService.data.save[place]=null;
                    IGoogleService.data.meta[place]=null;
                }
                IGoogleService.data.chargementSaveLoad++;
            }
        };
        pendingResult.setResultCallback(callback);
    }


    /**
     * Launch the UI to select a Snapshot from the user's Saved Games.  The result of this
     * selection will be returned to onActivityResult.
     */
    public void savedGamesSelect() {
        final boolean allowAddButton = false;
        final boolean allowDelete = false;
        Intent intent = Games.Snapshots.getSelectSnapshotIntent(_gameHelper.getApiClient(), "Saved Games", allowAddButton, allowDelete, Snapshots.DISPLAY_LIMIT_NONE);
        startActivityForResult(intent, RC_SELECT_SNAPSHOT);
    }


    /**
     * Update the Snapshot in the Saved Games service with new data.  Metadata is not affected,
     * however for your own application you will likely want to update metadata such as cover image,
     * played time, and description with each Snapshot update.  After update, the UI will
     * be cleared.
     */
    public void savedGamesUpdate(final String snapshotName, String dataStr) {
        final boolean createIfMissing = true;
        final byte[] data = dataStr.getBytes();
        AsyncTask<Void, Void, Boolean> updateTask = new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... params) {
                Snapshots.OpenSnapshotResult open = Games.Snapshots.open(_gameHelper.getApiClient(), snapshotName, createIfMissing).await();
                if (!open.getStatus().isSuccess()) {
                    Log.w(TAG, "Could not open Snapshot for update.");
                    return false;
                }
                // Change data but leave existing metadata
                Snapshot snapshot = open.getSnapshot();
                snapshot.getSnapshotContents().writeBytes(data);
                Snapshots.CommitSnapshotResult commit = Games.Snapshots.commitAndClose(_gameHelper.getApiClient(), snapshot, SnapshotMetadataChange.EMPTY_CHANGE).await();
                if (!commit.getStatus().isSuccess()) {
                    Log.w(TAG, "Failed to commit Snapshot.");
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
        }; updateTask.execute();
    }

    @Override
    public void savedGamesLoadAll() {
        SandArena.googleService.savedGamesLoad("snapSand-0",0);
        SandArena.googleService.savedGamesLoad("snapSand-1",1);
        SandArena.googleService.savedGamesLoad("snapSand-2",2);
    }

}

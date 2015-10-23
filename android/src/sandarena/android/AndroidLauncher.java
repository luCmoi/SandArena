package sandarena.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshots;

import java.io.IOException;

import sandarena.SandArena;
import sandarena.googleservice.IGoogleService;

public class AndroidLauncher extends FragmentActivity implements IGoogleService, AndroidFragmentApplication.Callbacks {


    private static final int RC_SELECT_SNAPSHOT = 9002;
    private GameFragment gameFragment;
    private GameHelperFragment gameHelperFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        gameFragment = new GameFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(android.R.id.content, gameFragment);
        trans.commit();
        SandArena.googleService = this;

        gameHelperFragment = (GameHelperFragment) getSupportFragmentManager().findFragmentByTag("GameHelperFragment");
        if (gameHelperFragment == null) {
            gameHelperFragment = new GameHelperFragment();
            getSupportFragmentManager().beginTransaction().add(gameHelperFragment, "GameHelperFragment").commit();
        }
    }

    @Override
    public void signIn() {
        try {
            runOnUiThread(new Runnable() {
                //@Override
                public void run() {
                    gameHelperFragment.get_gameHelper().beginUserInitiatedSignIn();
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
                    gameHelperFragment.get_gameHelper().signOut();
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
        return gameHelperFragment.get_gameHelper().isSignedIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gameHelperFragment.get_gameHelper().onStart(this);
    }

    @Override
    protected void onStop() {
        //gameHelperFragment.get_gameHelper().onStop();
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        gameHelperFragment.get_gameHelper().onActivityResult(requestCode, resultCode, data);
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
        PendingResult<Snapshots.OpenSnapshotResult> pendingResult = Games.Snapshots.open(gameHelperFragment.get_gameHelper().getApiClient(), snapshotName, false);
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
                Snapshots.OpenSnapshotResult open = Games.Snapshots.open(gameHelperFragment.get_gameHelper().getApiClient(), snapshotName, createIfMissing).await();
                if (!open.getStatus().isSuccess()) {
                    System.err.println("Could not open Snapshot for update.");
                    return false;
                }
                // Change data but leave existing metadata
                Snapshot snapshot = open.getSnapshot();
                snapshot.getSnapshotContents().writeBytes(data);
                Snapshots.CommitSnapshotResult commit = Games.Snapshots.commitAndClose(gameHelperFragment.get_gameHelper().getApiClient(), snapshot, SnapshotMetadataChange.EMPTY_CHANGE).await();
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
        gameHelperFragment.startQuickGame();
    }

    public GameHelper get_gameHelper() {
        return gameHelperFragment.get_gameHelper();
    }

    public void sendOtherPlayer(String mess) {
        gameHelperFragment.sendOtherPlayer(mess);

    }

    @Override
    public void quitQuickGame() {
        gameHelperFragment.quitQuickGame();
    }

    @Override
    public void exit() {

    }
}

package sandarena.googleservice;

public interface IGoogleService {
     GoogleData data = new GoogleData();

     void signIn();

     void signOut();

     void rateGame();

     void submitScore(long score);

     void showScores();

     boolean isSignedIn();

     void savedGamesLoad(String snapshotName, int place);

     void savedGamesUpdate(final String snapshotName, String dataStr);

     void savedGamesLoadAll();

     void printError(String print);

     void startQuickGame();

     void sendOtherPlayer(String mess);

     void quitQuickGame(boolean waitId);
}
package sandarena;

public interface IGoogleService {
    public GoogleData data = new GoogleData();

    public void signIn();

    public void signOut();

    public void rateGame();

    public void submitScore(long score);

    public void showScores();

    public boolean isSignedIn();

    public void savedGamesLoad(String snapshotName, int place);

    public void savedGamesSelect();

    public void savedGamesUpdate(final String snapshotName, String dataStr);

    public void savedGamesLoadAll();
}
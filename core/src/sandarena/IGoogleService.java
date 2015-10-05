package sandarena;

public interface IGoogleService
{
    public void signIn();
    public void signOut();
    public void rateGame();
    public void submitScore(long score);
    public void showScores();
    public boolean isSignedIn();
    public void savedGame();
}
package sandarena;

/**
 * Created by lucmo on 04/10/2015.
 */
public class DesktopGoogleService implements IGoogleService
{
    @Override
    public void signIn()
    {
        System.out.println("DesktopGoogleServies: signIn()");
    }

    @Override
    public void signOut()
    {
        System.out.println("DesktopGoogleServies: signOut()");
    }

    @Override
    public void rateGame()
    {
        System.out.println("DesktopGoogleServices: rateGame()");
    }

    @Override
    public void submitScore(long score)
    {
        System.out.println("DesktopGoogleServies: submitScore(" + score + ")");
    }

    @Override
    public void showScores()
    {
        System.out.println("DesktopGoogleServies: showScores()");
    }

    @Override
    public boolean isSignedIn()
    {
        System.out.println("DesktopGoogleServies: isSignedIn()");
        return false;
    }

    @Override
    public void savedGamesLoad(String snapshotName, int place) {

    }

    @Override
    public void savedGamesSelect() {

    }

    @Override
    public void savedGamesUpdate(String snapshotName, String dataStr) {

    }

    @Override
    public void savedGamesLoadAll() {

    }

}
package sandarena.googleservice;

/**
 * Created by lucmo on 04/10/2015.
 */
public class DesktopGoogleService implements IGoogleService
{
    public void initLayout() {

    }

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
        return true;
    }

    @Override
    public void savedGamesLoad(String snapshotName, int place) {

    }


    @Override
    public void savedGamesUpdate(String snapshotName, String dataStr) {

    }

    @Override
    public void savedGamesLoadAll() {
        data.save[0] = null;
        data.save[1] = null;
        data.save[2] = null;
        data.chargementSaveLoad=3;
    }

    @Override
    public void printError(String print) {

    }

    @Override
    public void startQuickGame() {

    }

    @Override
    public void sendOtherPlayer(String mess) {

    }

    @Override
    public void quitQuickGame(boolean waitId) {

    }

    @Override
    public void bringAdFront() {

    }

    @Override
    public void hideAd() {

    }


}
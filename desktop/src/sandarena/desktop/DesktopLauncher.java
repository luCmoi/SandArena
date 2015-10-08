package sandarena.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import sandarena.googleservice.DesktopGoogleService;
import sandarena.SandArena;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        config.height = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        new LwjglApplication(new SandArena(new DesktopGoogleService()), config);
    }
}

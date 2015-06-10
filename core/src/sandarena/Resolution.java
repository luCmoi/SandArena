package sandarena;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Class au contenu static permettant les calculs de résolution ainsi que le
 * stockage en mode Desktop des préférence de réglage.
 */
public class Resolution {

    public static int width = 0;
    public static int widthCase;
    public static int height = 0;
    public static int heightCase;
    public static float ratioWidth;
    public static float ratioHeight;
    public static Boolean fullScreen;

    /**
     * Calcul la résolution en utilisant le fichier de réglage en mode Desktop
     *
     * @throws java.io.FileNotFoundException
     */
    public static void calculResolution() throws IOException {
        if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
            File fichier = Gdx.files.internal("Fichiers/option.txt").file();
            if (fichier.exists()) {
                DataInputStream input;
                input = new DataInputStream(new BufferedInputStream(new FileInputStream(fichier)));
                width = input.readInt();
                height = input.readInt();
                fullScreen = input.readBoolean();
                input.close();
            }
        }
        if (width == 0 && height == 0) {
            width = Gdx.graphics.getWidth();
            height = Gdx.graphics.getHeight();
            fullScreen = true;
            if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
                //nouvelleResolution(width, height, true);
            }
        }
        Gdx.graphics.setDisplayMode(width, height, fullScreen);
        ratioWidth = (float) (width / 1920.0);
        ratioHeight = (float) (height / 1080.0);
        widthCase = (int) (128 * ratioWidth);
        heightCase = (int) (128 * ratioHeight);
    }

    /**
     * Permet d'enregistrer une résolution
     */
    public static void nouvelleResolution(int width, int height, boolean fullScreen) throws IOException {
        File fichier = Gdx.files.internal("Fichiers/option.txt").file();
        if (!fichier.exists()) {
            fichier.createNewFile();
        }
        DataOutputStream output;
        output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fichier)));
        output.writeInt(width);
        output.writeInt(height);
        output.writeBoolean(fullScreen);
        output.close();
    }
}

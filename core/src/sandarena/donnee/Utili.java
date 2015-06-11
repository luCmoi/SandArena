package sandarena.donnee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Guillaume on 09/06/2015.
 */
public class Utili {
    public static Texture chemin = new Texture(Gdx.files.internal("Image/Util/Chemin.png"));
    public static Texture caseActive = new Texture(Gdx.files.internal("Image/Util/SelectVert.png"));
    public static Texture caseSelect = new Texture(Gdx.files.internal("Image/Util/SelectRouge.png"));
    public static Texture competenceable = new Texture(Gdx.files.internal("Image/Util/Competence.png"));
    public static Texture accessible = new Texture(Gdx.files.internal("Image/Util/Traversable.png"));
    public static Texture contour = new Texture(Gdx.files.internal("Image/Util/Contour.png"));
}

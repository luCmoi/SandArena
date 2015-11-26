package sandarena.donnee.banque;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Une entree sert de type de base a toutes les banques de données
 *
 * @author Guillaume
 */
public abstract class Entree {

    private int id;
    public String nom;
    private String description;
    public Texture image;

    public Entree(int id, String nom, String description, String chemin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        if (chemin != null) {
            this.image = new Texture(Gdx.files.internal(chemin));
        }
    }

    public int getId() {
        return id;
    }

    public void dispose() {
        this.nom = null;
        this.description = null;
        if (image != null) {
            image.dispose();
            image = null;
        }
    }
}

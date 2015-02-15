package sandarena.donnee;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Une entree sert de type de base a toutes les banques de données
 * @author Guillaume
 */
public abstract class Entree {

    public String nom;
    public String description;
    public int instance = 0;
    public String cheminImage;
    public Texture image;

    public Entree(String nom, String description, String chemin) {
        this.nom = nom;
        this.description = description;
        this.cheminImage = chemin;
    }

    /**
     * Prévient l'entree qu'elle possede une instance de plus et charge l'image si nécessaire
     */
    public void incremente() {
        if (instance == 0) {
            image = new Texture(Gdx.files.internal(cheminImage));
        }
        instance++;
    }

    /**
     * Prévient l'entree qu'elle possede une instance de moins et libere l'éspace de l'image si necessaire
     */
    public void decremente() {
        instance--;
        if (instance == 0) {
            image.dispose();
            image = null;
        }
    }

    public void dispose() {
        this.nom = null;
        this.description = null;
        this.cheminImage = null;
        if (image != null) {
            image.dispose();
            image = null;
        }
    }
}

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
    protected int instance = 0;
    protected String cheminImage;
    public Texture image;

    public Entree(int id, String nom, String description, String chemin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.cheminImage = chemin;
    }

    /**
     * Prévient l'entree qu'elle possede une instance de plus et charge l'image si nécessaire
     */
    public void incremente() {
        if (instance == 0) {
            this.image = new Texture(Gdx.files.internal(cheminImage));
        }
        instance++;
    }

    public int getId(){
        return id;
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

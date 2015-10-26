package sandarena.donnee.sol;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Banque de donnée qui stock les sols
 *
 * @author Guillaume
 */
public class BanqueSol extends sandarena.donnee.banque.Banque {

    public static ArrayList<sandarena.donnee.banque.Entree> banque = new ArrayList<sandarena.donnee.banque.Entree>();

    public static ArrayList<sandarena.donnee.banque.Entree> getBanque() {
        return banque;
    }

    /**
     * Initialise tous les sols du jeu
     */
    public static void init() {
        banque.add(new EntreeSol(4001, "Sable",
                "Le sol d'arène le plus classique possible.",
                "Image/Sol/Sable2.png", true, false));
        banque.add(new EntreeSol(4002, "Lave", "Il ne faut pas essayer de s'y baigner", "Image/Sol/Lave/", false, true));
    }

    public static class EntreeSol extends sandarena.donnee.banque.Entree {
        private boolean anime;
        private boolean traversable;
        private Texture[] image;

        public EntreeSol(int id, String nom, String description, String chemin, boolean traversable, boolean anime) {
            super(id, nom, description, chemin);
            this.traversable = traversable;
            this.anime = anime;
        }

        @Override
        public void incremente() {
            if (anime) {
                if (instance == 0) {
                    image = new Texture[45];
                    for (int i = 0; i < 45; i++) {
                        image[i] = new Texture(Gdx.files.internal(cheminImage.concat(String.valueOf(i+1)).concat(".png")));
                    }
                }
                instance++;
            } else {
                super.incremente();
            }
        }

        public boolean isAnime() {
            return anime;
        }

        public Texture[] getImage() {
            return image;
        }

        public boolean isTraversable() {
            return traversable;
        }
    }
}


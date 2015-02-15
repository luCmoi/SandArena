package sandarena.partie.compcase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sandarena.donnee.BanqueCompetence.EntreeCompetence;
import sandarena.joueur.Personnage;
import sandarena.partie.Case;

/**
 * Une instance de Personnage en partie
 * @author Guillaume
 */
public class PersonnageIG {

    private Personnage donnee;
    private int vieActuelle;
    private int vitesseRestante;
    private final CompetenceIG[] competence = new CompetenceIG[4];
    private Case container;
    
    public PersonnageIG(Personnage donnee) {
        this.donnee = donnee;
        this.vieActuelle = donnee.info.vie;
        this.vitesseRestante = donnee.info.vitesse;
        int i = 0;
        for (EntreeCompetence c : donnee.competences) {
            if (c != null) {
                competence[i] = new CompetenceIG(c);
                i++;
            }
        }
    }

    public void dispose() {
        int i=0;
        for (CompetenceIG c : competence) {
            if (c != null) {
                competence[i].dispose();
                i++;
            }
        }
        this.donnee=null;
        this.setContainer(null);
    }
    
    public void render(SpriteBatch batch){
        batch.draw(donnee.info.image,  container.getPosition().x, container.getPosition().y, container.getPosition().width, container.getPosition().height);
    }

    public void setContainer(Case container) {
        this.container = container;
    }
}

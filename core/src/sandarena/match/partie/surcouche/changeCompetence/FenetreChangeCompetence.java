package sandarena.match.partie.surcouche.changeCompetence;

import com.badlogic.gdx.scenes.scene2d.Group;

import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Personnage;
import sandarena.joueur.competence.Competence;
import sandarena.match.partie.surcouche.SurcouchePartie;

/**
 * Created by lucmo on 28/11/2015.
 */
public class FenetreChangeCompetence extends Group{

    private  SurcouchePartie container;
    private  Personnage perso;
    private  Competence comp;
    private  EmplacementChangeCompetence emp;
    private BoutonAccepte accepte;

    public FenetreChangeCompetence(SurcouchePartie container, Personnage perso, Competence comp){
        this.container = container;
        this.perso = perso;
        this.comp = comp;
        setBounds(Resolution.width / 8, Resolution.height / 32, Resolution.width / 8 * 6, (Resolution.height / 8) * 6);
        this.accepte = new BoutonAccepte(this);
        this.emp = new EmplacementChangeCompetence(container.getContainer(),0);
        this.addActor(emp);
        this.addActor(accepte);
    }
}

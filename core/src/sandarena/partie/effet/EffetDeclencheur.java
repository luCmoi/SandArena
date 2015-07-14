package sandarena.partie.effet;


import sandarena.partie.compcase.PersonnageIG;

/**
 * Created by Guillaume on 09/06/2015.
 */
public class EffetDeclencheur extends Effet{
    protected int cible;
    private Effet chaine;
    protected PersonnageIG container;

    public EffetDeclencheur(int cible, Effet chaine) {
        this.cible = cible;
        this.chaine = chaine;
    }

    public void setContainer(PersonnageIG container) {
        this.container = container;
    }

    public void launch(PersonnageIG personnage) {
        if (chaine instanceof EffetBuf){
            if (cible == CompetenceToEffet.SOI) {
                container.addBuf((EffetBuf)chaine, true);
            } else {
                personnage.addBuf((EffetBuf)chaine, true);
            }
        }
    }
}

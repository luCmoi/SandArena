package sandarena.match.partie.jeu.compcase.effet;


import sandarena.match.partie.jeu.compcase.PersonnageIG;

/**
 * Created by Guillaume on 09/06/2015.
 */
public class EffetDeclencheur extends Effet {
    private int cible;
    private Effet chaine;
    private PersonnageIG container;

    protected EffetDeclencheur(int cible, Effet chaine) {
        this.cible = cible;
        this.chaine = chaine;
    }

    public void setContainer(PersonnageIG container) {
        this.container = container;
    }

    protected void launch(PersonnageIG personnage) {
        System.out.println(chaine.getClass());
        if (chaine instanceof sandarena.match.partie.jeu.compcase.effet.EffetBuf){
            if (cible == sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet.SOI) {
                container.addBuf((sandarena.match.partie.jeu.compcase.effet.EffetBuf)chaine, true);
            } else {
                //--// TODO: 21/08/2015  Declencheur recoit lattaquand quand on l'a 
                //personnage.addBuf((EffetBuf)chaine, true);
            }
        }else if (chaine instanceof EffetAttaque){
            if (cible == sandarena.match.partie.jeu.compcase.effet.CompetenceToEffet.SOI) {
                ((EffetAttaque) chaine).lance(container.getContainer(), container.getContainer(), true);
            } else {
                ((EffetAttaque) chaine).lance(container.getContainer(),personnage.getContainer(),true);
            }
        }
    }
}

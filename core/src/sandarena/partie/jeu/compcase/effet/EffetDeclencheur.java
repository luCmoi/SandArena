package sandarena.partie.jeu.compcase.effet;


import sandarena.partie.jeu.compcase.PersonnageIG;

/**
 * Created by Guillaume on 09/06/2015.
 */
public class EffetDeclencheur extends Effet {
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
        System.out.println(chaine.getClass());
        if (chaine instanceof EffetBuf){
            if (cible == CompetenceToEffet.SOI) {
                container.addBuf((EffetBuf)chaine, true);
            } else {
                //--// TODO: 21/08/2015  Declencheur recoit lattaquand quand on l'a 
                //personnage.addBuf((EffetBuf)chaine, true);
            }
        }else if (chaine instanceof sandarena.partie.jeu.compcase.effet.EffetAttaque){
            if (cible == CompetenceToEffet.SOI) {
                ((sandarena.partie.jeu.compcase.effet.EffetAttaque) chaine).lance(container.getContainer(), container.getContainer(), true);
            } else {
                ((sandarena.partie.jeu.compcase.effet.EffetAttaque) chaine).lance(container.getContainer(),personnage.getContainer(),true);
            }
        }
    }
}

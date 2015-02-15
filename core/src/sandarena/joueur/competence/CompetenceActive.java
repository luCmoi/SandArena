package sandarena.joueur.competence;

/**
 *
 * @author Guillaume
 */
public class CompetenceActive extends Competence{
    protected int rechargement;
    protected int utilisation;
    protected int porte;
    
    public CompetenceActive(int type, int recharge, int utilisation, int porte){
        super(type);
        this.rechargement=recharge;
        this.utilisation=utilisation;
        this.porte=porte;
    }
}

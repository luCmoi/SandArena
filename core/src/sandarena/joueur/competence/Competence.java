package sandarena.joueur.competence;

/**
 * @author Guillaume
 */
public abstract class Competence {
    protected int type;

    public Competence(int type) {
        this.type = type;
    }

    public void dispose() {
    }

}

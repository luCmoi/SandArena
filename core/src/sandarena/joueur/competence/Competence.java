package sandarena.joueur.competence;

import java.util.ArrayList;

import sandarena.donnee.Caract;

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

    public abstract ArrayList<String> toStrings();
}

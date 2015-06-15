
package sandarena.joueur.competence;

import java.util.ArrayList;

public abstract class CompetencePassive extends Competence {

    public CompetencePassive(int type) {
        super(type);
    }

    @Override
    public ArrayList<String> toStrings() {
        return null;
    }
}

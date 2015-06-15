
package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetenceActive;

public class CompetenceInvocation extends CompetenceActive {

    public CompetenceInvocation(int type, int recharge, int utilisation, int porte, int portemin, int zone) {
        super(type, recharge, utilisation, porte, portemin, zone);
    }

    @Override
    public ArrayList<String> toStrings() {
        return super.toStrings();
    }
}

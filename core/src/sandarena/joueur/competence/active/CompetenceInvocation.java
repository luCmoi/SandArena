
package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetenceActive;

public class CompetenceInvocation extends CompetenceActive {

    public CompetenceInvocation( byte recharge, byte utilisation, byte porte, byte portemin, byte zone) {
        super( recharge, utilisation, porte, portemin, zone);
    }

    @Override
    public ArrayList<String> toStrings() {
        return super.toStrings();
    }


}

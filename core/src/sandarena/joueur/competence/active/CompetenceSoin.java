
package sandarena.joueur.competence.active;

import java.util.ArrayList;

import sandarena.joueur.competence.CompetenceActive;


public class CompetenceSoin extends CompetenceActive {

    public CompetenceSoin( int recharge, int utilisation, int porte, int portemin, int zone) {
        super( recharge, utilisation, porte, portemin, zone);
    }

    @Override
    public ArrayList<String> toStrings() {
        return super.toStrings();
    }
}

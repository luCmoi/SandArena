package sandarena.partie.compcase;

import sandarena.donnee.BanqueCompetence.EntreeCompetence;

/**
 * Une instance de compétence en Partie
 * @author Guillaume
 */
public class CompetenceIG {
    public EntreeCompetence info;
    
    CompetenceIG(EntreeCompetence c) {
        info=c;
    }

    void dispose() {
        this.info=null;
    }
    
}

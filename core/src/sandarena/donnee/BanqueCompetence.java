package sandarena.donnee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import sandarena.joueur.competence.Competence;
import sandarena.joueur.competence.active.CompetenceAttaque;
import sandarena.joueur.competence.active.CompetenceBuffActif;
import sandarena.joueur.competence.active.CompetenceDispel;
import sandarena.joueur.competence.passive.CompetenceBuff;
import sandarena.joueur.competence.passive.CompetenceDeclencheurEffet;
import sandarena.partie.effet.CompetenceToEffet;
import sandarena.partie.effet.EffetAttaque;
import sandarena.partie.effet.EffetBuf;
import sandarena.partie.effet.EffetDeclencheur;


/**
 * Banque de donnée qui stock les compétences
 *
 * @author Guillaume
 */
public class BanqueCompetence extends Banque {
    public static final int FIN = 9999;
    public static ArrayList<Entree> banque = new ArrayList<Entree>();

    public static ArrayList<Entree> getBanque() {
        return banque;
    }

    /**
     * Initialise toutes les compétences du jeu
     */
    public static void init() {
        //FORCE
        banque.add(new EntreeCompetence("Bourre-pif",
                "Coup de poing puissant au visage, de préférence dans le nez.",
                "Image/Competence/Bourrepif.png",
                new CompetenceAttaque(Affinite.FORCE, 0, 0, 1, 1, 1, Caract.FORCE, 1),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        banque.add(new EntreeCompetence("Berzerk",
                "Plus le personnage est blessé plus sa folie est grande.",
                "Image/Competence/Berzerk.png",
                new CompetenceDeclencheurEffet(Affinite.FORCE, CompetenceToEffet.DEGATRECU, CompetenceToEffet.SOI,
                        CompetenceToEffet.CONDITIONBUFF, CompetenceToEffet.VALATTAQUE, 1,
                        CompetenceToEffet.CONDITIONTYPE, Caract.FORCE),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        banque.add(new EntreeCompetence("Ecrase-Face",
                "Un tel coup peut ferait s'évanouir n'importe quel être vivant",
                "Image/Competence/EcraseFace.png",
                new CompetenceAttaque(Affinite.FORCE, 3, 0, 1, 1, 1, Caract.FORCE, -1,
                        CompetenceToEffet.CONDITIONBUFF, CompetenceToEffet.STUN, 1),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        banque.add(new EntreeCompetence("Leg Day",
                "Le personnage à travaillé ses jambes tous les jours",
                "Image/Competence/LegDay.png",
                new CompetenceBuff(Affinite.TRIBAL, CompetenceToEffet.VALVITESSE, 4),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        banque.add(new EntreeCompetence("Contraction Musculaire",
                "Des muscles puissants suffisent à arreter un coup mortel",
                "Image/Competence/ContractionMusculaire.png",
                new CompetenceBuffActif(Affinite.FORCE, 3, 0, 0, 0, 1, Caract.FORCE, CompetenceToEffet.VALDEFENSE, 3,
                        CompetenceToEffet.CONDITIONTYPE, Caract.FORCE,
                        CompetenceToEffet.CONDITIONDUREE, 2),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        //TRIBAL
        banque.add(new EntreeCompetence("Lance de Pai",
                "On dit que l'originale lancée par Pai fendit une montagne",
                "Image/Competence/LanceDePai.png",
                new CompetenceAttaque(Affinite.TRIBAL, 2, 0, 5, 2, 1, Caract.MAGIE, 0),
                new ArrayList(Arrays.asList(Affinite.FORCE))));
        banque.add(new EntreeCompetence("Rituel de sang",
                "Parfoit il faut sacrifier une partie de soit pour en renforcer une autre",
                "Image/Competence/RituelDeSang.png",
                new CompetenceBuffActif(Affinite.TRIBAL,3,0,0,0,1,Caract.MAGIE, CompetenceToEffet.DEGAT, 4,
                        CompetenceToEffet.CONDITIONBUFF,CompetenceToEffet.VALATTAQUE, 1),
                new ArrayList<Integer>(Arrays.asList(Affinite.TRIBAL))));
        banque.add(new EntreeCompetence("Poupée Vaudou",
                "L'ame d'un homme peut être lié a celui d'un objet afin que leurs souffrances soit communes",
                "Image/Competence/PoupeeVaudou.png",
                new CompetenceBuffActif(Affinite.TRIBAL, 0, 1, 15, 1, 1, Caract.MAGIE, CompetenceToEffet.DOT, 1),
                new ArrayList(Arrays.asList(Affinite.TRIBAL))));
        banque.add(new EntreeCompetence("Tiki de To´a-Hiti",
                "Ce tiki puise dans la force pour defendre l'esprit",
                "Image/Competence/TikiDeToahiti.png",
                new CompetenceBuff(Affinite.TRIBAL, CompetenceToEffet.TYPEDEFENSE, Caract.FORCE,
                        CompetenceToEffet.CONDITIONTYPE, Caract.MAGIE),
                new ArrayList(Arrays.asList(Affinite.TRIBAL))));
        banque.add(new EntreeCompetence("Tiki de Tu",
                "Ce tiki donne a l'esprit la force des muscles",
                "Image/Competence/TikiDeTu.png",
                new CompetenceBuff(Affinite.TRIBAL, CompetenceToEffet.TYPEATTAQUE, Caract.FORCE,
                        CompetenceToEffet.CONDITIONTYPE, Caract.MAGIE),
                new ArrayList(Arrays.asList(Affinite.TRIBAL))));
        banque.add(new EntreeCompetence("Perle de Purification",
                "Purifie le corps de ses afflictions",
                "Image/Competence/PerleDePurification.png",
                new CompetenceDispel(Affinite.TRIBAL,4,3,4,0,1, false, 1),
                new ArrayList(Arrays.asList(Affinite.TRIBAL))));
    }

    /**
     * Retourne une compétence aléatoire de l'affinité passée en argument
     *
     * @param affinite
     * @return
     */
    public static EntreeCompetence getCompetence(int affinite, Competence... dejaAcquise) {
        Collections.shuffle(banque);
        for (Entree e : banque) {
            if (((EntreeCompetence) e).affinite.contains(affinite)) {
                return (EntreeCompetence) e;
            }
        }
        return null;
    }

    /**
     * Une Entree de Competence
     */
    public static class EntreeCompetence extends Entree {

        public Competence competence;
        private ArrayList<Integer> affinite;

        public EntreeCompetence(String nom, String description, String chemin, Competence competence, ArrayList<Integer> affinite) {
            super(nom, description, chemin);
            this.competence = competence;
            this.affinite = affinite;
        }

        @Override
        public void dispose() {
            super.dispose();
            competence.dispose();
            competence = null;
            affinite.clear();
            affinite = null;
        }
    }
}

package sandarena.partie.effet;

import sandarena.partie.compcase.PersonnageIG;

/**
 * Created by Guillaume on 09/06/2015.
 */
public class EffetDeclencheur {
    //type déclenche
    public static final int DEGATRECU = 0;
    //type action
    public static final int BUFF = 0;
    //type cible
    public static final int SOI = 0;
    public static final int AUTRE = 1;


    private int type;
    private int cible;
    private Double[] infos;
    private int typeEffet;
    private double val;
    private Double[] infosEffet;

    //todo a completer petit a petit
    public EffetDeclencheur(int type, int cible, Double... infos) {
        this.type = type;
        this.cible = cible;
        this.infos = infos;
    }

    public void addEffet(int typeE, Double... infosE) {
        this.typeEffet = typeE;
        this.infosEffet = infosE;
    }

    public void check(int type, PersonnageIG personnageDeclenche, PersonnageIG personnageDeclencheur, double... infosCheck) {
        //todo a completer et rajouter les conditions (infos)
        boolean check = false;
        if (this.type == type) {
            switch (this.type) {
                case (DEGATRECU):
                    if (infos.length == 0) {
                        check = true;
                    }
                    break;
            }
        }
        if (check) {
            if (cible == SOI) {
                launch(personnageDeclenche);
            } else {
                launch(personnageDeclencheur);
            }
        }
    }

    public void launch(PersonnageIG personnage) {
        switch (typeEffet) {
            case (BUFF):
                EffetBuf effet = EffetBuf.genereEffetBuf(infosEffet);
                personnage.addBuf(effet);
                break;
        }
    }
}

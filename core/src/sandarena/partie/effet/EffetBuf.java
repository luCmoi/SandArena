package sandarena.partie.effet;


import sandarena.donnee.IntegerNew;
import sandarena.partie.compcase.PersonnageIG;

public class EffetBuf {
    //type buff
    public static final int TYPEATTAQUE = 0;
    public static final int TYPEDEFENSE = 1;
    public static final int MULATTAQUE = 2;
    public static final int MULDEFENSE = 3;
    //todo a complete fur et a mesure
    public static final int DOT = 4;
    public static final int VALVITESSE = 5;
    public static final int VALATTAQUE = 6;
    public static final int DEGAT = 7;

    //
    public static final int CONDITIONBUFF = 0;
    public static final int CONDITIONDUREE = 1;

    private int changement;
    private double multiVal;
    private int val;
    private int duree;
    private int typeNouveau;
    private double[][] condition;

    public EffetBuf(int type, double val, double... conditions) {
        //todo ne prend pas en compte attaque en def et def en attaque
        changement = type;
        this.duree = -1;
        if (getChangement() == TYPEATTAQUE || getChangement() == TYPEDEFENSE) {
            typeNouveau = (int) val;
        } else if (getChangement() == MULATTAQUE || getChangement() == MULDEFENSE) {
            multiVal = val;
        } else if (getChangement() == VALATTAQUE || getChangement() == VALVITESSE || getChangement() == DEGAT) {
            this.val = (int) val;
        } else if (getChangement() == DOT) {
            this.val = (int) val;
        }
        if (conditions != null) {

        } else {
            condition = null;
        }
    }

    public static EffetBuf genereEffetBuf(Double... infos) {
        int type = (int) (double) infos[0];
        double val = infos[1];
        double conditions[] = new double[infos.length - 2];
        for (int i = 2; i < infos.length; i++) {
            conditions[i - 2] = infos[i];
        }
        return new EffetBuf(type, val, conditions);
    }

    public void modif(IntegerNew val, IntegerNew type) {
        //todo ajouter les conditions
        switch (getChangement()) {
            case (TYPEATTAQUE):
            case (TYPEDEFENSE):
                type.anInt = typeNouveau;
                break;
            case (MULATTAQUE):
            case (MULDEFENSE):
                val.aDouble = val.aDouble * multiVal;
                break;
            case (VALATTAQUE):
                val.aDouble = val.aDouble + this.val;
                break;
            case (VALVITESSE):
                val.aDouble = val.aDouble + this.val;
        }
    }

    public void inflige(PersonnageIG cible) {
        switch (getChangement()) {
            case (DEGAT):
            case (DOT):
                cible.inflige(this.val);
                break;
        }
    }

    public int getChangement() {
        return changement;
    }
}

package sandarena.partie.effet;


import sandarena.donnee.IntegerNew;

public class EffetBuf {
    //type buff
    public static final int TYPEATTAQUE = 0;
    public static final int TYPEDEFENSE = 1;
    public static final int VALATTAQUE = 2;
    public static final int VALDEFENSE = 3;
    //todo a complete fur et a mesure

    private int changement;
    private double multiVal;
    private int typeNouveau;
    private double[][] condition;

    public EffetBuf(int type, double val, double... conditions) {
        //todo ne prend pas en compte attaque en def et def en attaque
        changement = type;
        if (getChangement() == TYPEATTAQUE || getChangement() == TYPEDEFENSE) {
            typeNouveau = (int) val;
        } else if (getChangement() == VALATTAQUE || getChangement() == VALDEFENSE) {
            multiVal = val;
        }
        if (conditions != null) {
            condition = new double[conditions.length / 2][2];
            for (int i = 0; i < conditions.length; i = i + 2) {
                condition[i][0] = conditions[i];
                condition[i][1] = conditions[i + 1];
            }
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
            case (VALATTAQUE):
            case (VALDEFENSE):
                val.aDouble = val.aDouble * multiVal;
                break;
        }
    }

    public int getChangement() {
        return changement;
    }
}

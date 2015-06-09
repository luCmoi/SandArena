package sandarena.partie.effet;


import java.util.ArrayList;

public class EffetBuf {
    //type buff
    public static final int TYPEATTAQUE = 0;
    public static final int TYPEDEFENSE = 1;
    public static final int VALATTAQUE = 2;
    public static final int VALDEFENSE = 3;

    //todo a completer petit a petit


    private int changement;
    private double multiVal;
    private int typeNouveau;
    private int[][] declencheur;

    public EffetBuf(int type, double val, int... declencheurs) {
        //todo ne prend pas en compte attaque en def et def en attaque
        changement = type;
        if (changement == TYPEATTAQUE || changement == TYPEDEFENSE) {
            typeNouveau = (int) val;
        } else if (changement == VALATTAQUE || changement == VALDEFENSE) {
            multiVal = val;
        }
        if (declencheurs != null) {
            declencheur = new int[declencheurs.length / 2][2];
            for (int i = 0; i < declencheurs.length; i = i + 2) {
                declencheur[i][0] = declencheurs[i];
                declencheur[i][1] = declencheurs[i + 1];
            }
        }else{
            declencheur = null;
        }
    }

    public void modif(int val, int type){
        //todo gerer mieu declencheur et faire l'effet
    }
}

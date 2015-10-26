package sandarena.donnee.carte;

/**
 * Created by lucmo on 21/09/2015.
 */
public class CaseSpeciale {
    private boolean depart;
    private int sol;
    private byte x;
    private byte y;

    public CaseSpeciale(boolean depart, int sol, int x,int y){
        this.depart = depart;
        this.sol = sol;
        this.x = (byte)x;
        this.y = (byte)y;
    }

    public boolean isDepart() {
        return depart;
    }

    public int getSol() {
        return sol;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }
}

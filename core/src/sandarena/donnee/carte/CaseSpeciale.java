package sandarena.donnee.carte;

/**
 * Created by lucmo on 21/09/2015.
 */
public class CaseSpeciale {
    private boolean depart;
    private byte sol;
    private byte x;
    private byte y;

    public CaseSpeciale(boolean depart, int sol, int x,int y){
        this.depart = depart;
        this.sol = (byte)sol;
        this.x = (byte)x;
        this.y = (byte)y;
    }

    public boolean isDepart() {
        return depart;
    }

    public byte getSol() {
        return sol;
    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }
}

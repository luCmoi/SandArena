package sandarena.joueur.competence;

import java.util.ArrayList;

import sandarena.joueur.competence.passive.CompetenceBuff;

public abstract class Competence{
    protected CompetenceBuff succ;

    Competence() {
    }

    public void dispose() {
    }

    public void setSucc(CompetenceBuff succ){
        this.succ = succ;
    }

    public CompetenceBuff getSucc(){
        return succ;
    }

    public abstract ArrayList<String> toStrings();
}

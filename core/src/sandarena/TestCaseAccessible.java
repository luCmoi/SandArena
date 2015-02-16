package sandarena;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Class de test des cases accessibles
 * Algo fait a partir d'un tableau byte [][]
 * où :
 *      - 1 represente le joueur
 *      - 0 les cases sol normal
 *      - -1 les rochers, ennemis, allié,... (les case ou on ne peus pas se posé ou traverser)
 *
 *    0   1   2   3   4   5   6   7   8
 * 0| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 * 1| 0 | 0 | 0 | 0 |-1 | 0 | 0 | 0 | 0 |
 * 2| 0 | 0 | 0 | 0 |-1 | 0 |-1 | 0 | 0 |
 * 3| 0 | 0 |-1 |-1 | 0 |-1 | 0 | 0 | 0 |
 * 4| 0 | 0 | 0 |-1 | 1 | 0 | 0 |-1 | 0 |
 * 5| 0 |-1 | 0 | 0 | 0 | 0 |-1 | 0 | 0 |
 * 6| 0 | 0 | 0 | 0 |-1 | 0 | 0 | 0 | 0 |
 * 7| 0 | 0 | 0 | 0 | 0 |-1 | 0 | 0 | 0 |
 * 8| 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 0 |
 *
 * reste a faire la transormation entre plateau de jeu -> byte[][] et byte[][] -> plateau de jeu
 *
 *
 * Le paleau des un carré de 9*9 car pour le teste le deplacement max est 4
 * Les autres case du plateau ne son pas utile ^^
 *
 * Result deplacement 4 cases
 * X rocher
 * J joueur
 * ' ' case ok
 * - case trop loin
 *   0 1 2 3 4 5 6 7 8
 * 0|-|-|-|-|-|-|-|-|-|
 * 1|-|-|-|-|X| |-|-|-|
 * 2|-|-|-|-|X| |X|-|-|
 * 3|-|-|X|X|   |X|-|-|
 * 4|-|-| |X|J    |X|-|
 * 5|-|X|       |X|-|-|
 * 6|-|-|   |X|   |-|-|
 * 7|-|-|-| |-|X|-|-|-|
 * 8|-|-|-|-|-|-|-|-|-|



 */


public class TestCaseAccessible {
    static class Sommet {
        public ArrayDeque<Sommet> file = new ArrayDeque<Sommet>();
        private int i,j;
        static byte cmp = 0;
        private boolean couleur;
        private int distance;
        private ArrayList<Sommet> listFrere;
        public ArrayList<Sommet> pere;

        public Sommet() {
            this.i = (cmp-(cmp%9))/9;
            this.j = cmp%9;
            cmp++;
            this.couleur = true;
            this.distance = 0;
            listFrere = new ArrayList<Sommet>();
            this.pere = new ArrayList<Sommet>();

        }

        public String toString(){
            return "i:"+i+" j:"+j;
        }

        public void ajouterConnexion(Sommet s) {
            this.listFrere.add(s);
            s.getListFrere().add(this);
        }

        public ArrayList<Sommet> getListFrere() {
            return listFrere;
        }

        public ArrayList<Sommet> parcourLargeur(int dist) {
            ArrayList<Sommet> list = new ArrayList<Sommet>();
            this.couleur = false;
            file.add(this);
            Sommet tmp = null;
            while (!file.isEmpty()) {
                tmp = file.pop();
                for (Sommet s : tmp.getListFrere()) {
                    if (s.couleur) {
                        s.pere.addAll(tmp.pere);
                        s.pere.add(tmp);
                        s.distance = tmp.distance + 1;
                        s.couleur = false;
                        if(s.distance <= dist){
                            list.add(s);
                            file.add(s);
                        }

                    }
                }
            }
            return list;
        }
    }
    static class Graph {

        public Sommet[] listeSommets;

        public Graph(int taille) {
            listeSommets = new Sommet[taille];
            for (int i = 0; i < taille; i++) {
                listeSommets[i] = new Sommet();
            }
        }

        public void addSommet(int i, int j, int i2, int j2) {
            listeSommets[i *9+ j].ajouterConnexion(listeSommets[i2 * 9 + j2]);

        }
    }
    public static Sommet tabToGraph(byte[][] tab) {
        Graph graph = new Graph(tab[0].length * tab.length);
        Sommet joueur = null;
        for (int i = 0; i < tab.length - 1; i++) {
            for (int j = 0; j < tab[0].length; j++) {
                if (tab[i][j] == 1) {
                    joueur = graph.listeSommets[i * tab.length +  j];
                }
                if (tab[i][j] != -1 && tab[i+1][j] != -1)
                    graph.addSommet(i, j, i + 1, j);
                if (j < tab[0].length - 1) {
                    if (tab[i][j] != -1 && tab[i][j+1] != -1)
                        graph.addSommet(i, j, i, j + 1);
                }
            }
        }
        return joueur;
    }

    public static void main(String[] args){
        byte[][] tab = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0,-1, 0, 0, 0, 0},
                {0, 0, 0, 0,-1, 0,-1, 0, 0},
                {0, 0,-1,-1, 0, 0,-1, 0, 0},
                {0, 0, 0,-1, 1, 0, 0,-1, 0},
                {0,-1, 0, 0, 0, 0,-1, 0, 0},
                {0, 0, 0, 0,-1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0,-1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        Sommet s = tabToGraph(tab);
        System.out.println("JOUEUR : "+s+"\n");
        ArrayList<Sommet> list = s.parcourLargeur(4);
        System.out.println("Cases Accessibles : "+s+"");

        for (Sommet ss : list){
            System.out.println(ss);
        }
        Sommet t = list.get(list.size()-1);
        System.out.println("\n\nCHEMIN de : \n"+s+"\nà\n"+t+"\n---------");
        for (Sommet tt : t.pere){
            System.out.println(tt);
        }



    }
}
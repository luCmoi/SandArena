package sandarena.partie;

import java.util.ArrayDeque;
import java.util.ArrayList;
import sandarena.partie.compcase.PersonnageIG;

/**
 *
 * @author Guillaume
 */
public class AlgorithmePathfinding {

    /*
     * Parcour en largeur sur le plateau
     */
    static public class Sommet {

        public int x;
        public int y;
        boolean marque = true;
        public ArrayList<Sommet> listFrere;
        public Sommet pere;
        int distance;

        public Sommet(int x, int y) {
            this.x = x;
            this.y = y;
            this.listFrere = new ArrayList<Sommet>();
        }

        public void ajouterConnexion(Sommet s) {
            this.listFrere.add(s);
            s.listFrere.add(this);
        }

        public ArrayList<Sommet> parcourLargeur(int dist) {
            ArrayDeque<Sommet> file = new ArrayDeque<Sommet>();
            ArrayList<Sommet> list = new ArrayList<Sommet>();
            this.marque = false;
            file.add(this);
            Sommet tmp = null;
            while (!file.isEmpty()) {
                tmp = file.pop();
                for (Sommet s : tmp.listFrere) {
                    if (s.marque) {
                        s.pere = tmp;
                        s.distance = tmp.distance + 1;
                        s.marque = false;
                        if (s.distance <= dist) {
                            list.add(s);
                            file.add(s);
                        }
                    }
                }
            }
            return list;
        }
    }

    public static void calculCaseAccessible(PersonnageIG personnage, Case[][] plateau) {
        int distance = personnage.getVitesseRestante();
        Sommet s = tabToGraph(personnage.getContainer(), plateau);
        System.out.println(s.listFrere.size());
        ArrayList<Sommet> liste = s.parcourLargeur(distance);
        for (Sommet som : liste) {
            plateau[som.x][som.y].setAccessible(true);
            plateau[som.x][som.y].setPredecesseur(plateau[som.pere.x][som.pere.y]);
        }
    }

    static class Graph {

        public Sommet[] listeSommets;

        public Graph(int xTaille, int yTaille) {
            listeSommets = new Sommet[xTaille * yTaille];
            for (int x = 0; x < xTaille; x++) {
                for (int y = 0; y < yTaille; y++) {
                    listeSommets[(x * yTaille) + y] = new Sommet(x, y);
                }
            }
        }

        public void addSommet(int x, int y, int i, int j, int yTaille) {
            listeSommets[(x * yTaille) + y].ajouterConnexion(listeSommets[(i * yTaille) + j]);
        }
    }

    public static Sommet tabToGraph(Case depart, Case[][] plateau) {
        Graph graph = new Graph(plateau.length, plateau[0].length);
        Sommet joueur = null;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                plateau[i][j].setAccessible(false);
                plateau[i][j].setPredecesseur(null);
                if (plateau[i][j] == depart) {
                    joueur = graph.listeSommets[(i * plateau[0].length) + j];
                }
                if (i < plateau.length - 1) {
                    if ((plateau[i][j].isTraversable() || plateau[i][j] == depart) && (plateau[i + 1][j].isTraversable()|| plateau[i+1][j] == depart)) {
                        graph.addSommet(i, j, i + 1, j, plateau[0].length);
                    }
                }
                if (j < plateau[0].length - 1) {
                    if ((plateau[i][j].isTraversable() || plateau[i][j] == depart) && (plateau[i][j + 1].isTraversable()|| plateau[i][j+1] == depart)) {
                        graph.addSommet(i, j, i, j + 1, plateau[0].length);
                    }
                }
            }
        }
        return joueur;
    }

}

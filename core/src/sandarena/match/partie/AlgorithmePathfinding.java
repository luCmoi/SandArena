package sandarena.match.partie;

import java.util.ArrayDeque;
import java.util.ArrayList;

import sandarena.match.partie.jeu.Case;

/**
 * @author Guillaume
 */
public class AlgorithmePathfinding {
    private static Graph graph;

    public static void calculCaseAccessible(int vitesseRestante, Case caseDepart, Case[][] plateau) {
        Sommet s = tabToGraph(caseDepart, plateau);
        if (s !=null) {
            ArrayList<Sommet> liste = s.parcourLargeur(vitesseRestante);
            for (Sommet som : liste) {
                plateau[som.x][som.y].setAccessible(true);
                plateau[som.x][som.y].setPredecesseur(plateau[som.pere.x][som.pere.y]);
            }
            liste.clear();
        }
        graph.dispose();
        graph = null;
    }

    public static void calculCaseTouchable(int portemin, int porte, Case caseDepart, Case[][] plateau) {
        Sommet s = tabToGraph2(caseDepart, plateau);
        if (s != null) {
            ArrayList<Sommet> liste = s.parcourLargeur(porte);
            for (Sommet som : liste) {
                if (som.distance >= portemin) {
                    plateau[som.x][som.y].setCompetenceable(true);
                }
            }
            liste.clear();
        }
    }

    private static Sommet tabToGraph(Case depart, Case[][] plateau) {
        graph = new Graph(plateau.length, plateau[0].length);
        Sommet joueur = null;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                plateau[i][j].setAccessible(false);
                plateau[i][j].setPredecesseur(null);
                if (plateau[i][j] == depart) {
                    joueur = graph.listeSommets[(i * plateau[0].length) + j];
                }
                if (i < plateau.length - 1) {
                    if ((plateau[i][j].isTraversable() || plateau[i][j] == depart) && (plateau[i + 1][j].isTraversable() || plateau[i + 1][j] == depart)) {
                        graph.addSommet(i, j, i + 1, j, plateau[0].length);
                    }
                }
                if (j < plateau[0].length - 1) {
                    if ((plateau[i][j].isTraversable() || plateau[i][j] == depart) && (plateau[i][j + 1].isTraversable() || plateau[i][j + 1] == depart)) {
                        graph.addSommet(i, j, i, j + 1, plateau[0].length);
                    }
                }
            }
        }
        return joueur;
    }

    private static Sommet tabToGraph2(Case depart, Case[][] plateau) {
        Graph graph = new Graph(plateau.length, plateau[0].length);
        Sommet joueur = null;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[0].length; j++) {
                plateau[i][j].setCompetenceable(false);
                if (plateau[i][j] == depart) {
                    joueur = graph.listeSommets[(i * plateau[0].length) + j];
                }
                if (i < plateau.length - 1) {
                    graph.addSommet(i, j, i + 1, j, plateau[0].length);
                }
                if (j < plateau[0].length - 1) {
                    graph.addSommet(i, j, i, j + 1, plateau[0].length);
                }
            }
        }
        return joueur;
    }

    /*
         * Parcour en largeur sur le plateau
         */
    static public class Sommet {

        public int x;
        public int y;
        public ArrayList<Sommet> listFrere;
        public Sommet pere;
        boolean marque = true;
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
            Sommet tmp;
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
            file.clear();
            return list;
        }

        public void dispose() {
            listFrere.clear();
            listFrere = null;
            pere = null;
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

        public void dispose() {
            for (Sommet s : listeSommets) {
                s.dispose();
            }
            listeSommets = null;
        }
    }


}

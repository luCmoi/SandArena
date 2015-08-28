package sandarena;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import sandarena.donnee.BanqueCompetence;
import sandarena.joueur.Personnage;
import sandarena.partie.Partie;

/**
 * Created by lucmo on 23/08/2015.
 */
public class ConnexionMatch {
    private static final String PERSOACTIF = "APIS";
    private static final String DEPLACEMENT = "EDJO";
    private static final String COMPETENCE = "NOUT";
    private static final String UTILISECOMPETENCE = "SETH";
    private static final String FINPHASE = "LETO";
    public static ServerSocket serverSocket;
    public static Socket socketLien;
    public static PrintWriter pw;
    public static BufferedReader br;

    public static void init() {
        try {
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketLien.getOutputStream())));
            br = new BufferedReader(new InputStreamReader(socketLien.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Personnage prepareMatchRecoitPerso() {
        try {
            Personnage retour = null;
            while (retour == null) {
                retour = new Personnage(Integer.parseInt(br.readLine()));
            }
            for (int i = 0; i < 4; i++) {
                BanqueCompetence.EntreeCompetence compTmp = null;
                while (compTmp == null) {
                    compTmp = (BanqueCompetence.EntreeCompetence) BanqueCompetence.getEntree(BanqueCompetence.getBanque(), Integer.parseInt(br.readLine()));
                    retour.addCompetence(compTmp);
                }
            }
            return retour;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void prepareMatchEnvoiPerso(Personnage tmp) {
        pw.println(tmp.getId());
        pw.println(tmp.getCompetences()[0].getId());
        pw.println(tmp.getCompetences()[1].getId());
        pw.println(tmp.getCompetences()[2].getId());
        pw.println(tmp.getCompetences()[3].getId());
        pw.flush();
    }

    public static void ecouteMatch(final Partie partie) {
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String mess = null;
                        while (mess == null) {
                            mess = br.readLine();
                        }
                        if (mess.equals(PERSOACTIF)) {
                            partieRecoitPersoActif(partie);
                        } else if (mess.equals(DEPLACEMENT)) {
                            partieRecoitDeplacement(partie);
                        } else if (mess.equals(COMPETENCE)) {
                            partieRecoitCompetence(partie);
                        } else if (mess.equals(UTILISECOMPETENCE)) {
                            partieRecoitUtiliseCompetence(partie);
                        }else if (mess.equals(FINPHASE)){
                            partieRecoitFinPhase(partie);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public static void partieRecoitPersoActif(Partie partie) throws IOException {
        int perso = Integer.parseInt(br.readLine());
        partie.setPersonnageActif(perso);
    }

    public static void partieEnvoiPersoActif(int persoActif) {
        pw.println(PERSOACTIF);
        pw.println(persoActif);
        pw.flush();
    }

    public static void partieRecoitDeplacement(Partie partie) throws IOException {
        partieRecoitPersoActif(partie);
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        partie.selectChemin(partie.getPlateau()[x][y]);
        partie.deplacement();
    }

    public static void partieEnvoiDeplacement(int perso, int coordX, int coordY) {
        pw.println(DEPLACEMENT);
        pw.println(perso);
        pw.println(coordX);
        pw.println(coordY);
        pw.flush();
    }

    public static void partieRecoitCompetence(Partie partie) throws IOException {
        partieRecoitPersoActif(partie);
        int i = Integer.parseInt(br.readLine());
        if (i != -1) {
            partie.setCompetenceActive(partie.getPersonnageActif().getCompetence()[i]);
        }else {
            partie.setCompetenceActive(null);
        }
    }

    public static void partieEnvoiCompetence(int perso, int comp) {
        pw.println(COMPETENCE);
        pw.println(perso);
        pw.println(comp);
        pw.flush();
    }

    public static void partieRecoitUtiliseCompetence(Partie partie) throws IOException {
        partieRecoitCompetence(partie);
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        partie.getCompetenceActive().agit(partie.getPlateau()[x][y]);
    }

    public static void partieEnvoiUtiliseCompetence(int perso, int comp, int coordX, int coordY) {
        pw.println(UTILISECOMPETENCE);
        pw.println(perso);
        pw.println(comp);
        pw.println(coordX);
        pw.println(coordY);
        pw.flush();
    }

    public static void partieRecoitFinPhase(Partie partie){
        partie.finPhase();
    }
    
    public static void partieEnvoiFinPhase() {
        pw.println(FINPHASE);
        pw.flush();
    }
}

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

/**
 * Created by lucmo on 23/08/2015.
 */
public class ConnexionMatch {
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
            System.out.println(retour.getId());
            for (int i = 0; i < 4; i++) {
                BanqueCompetence.EntreeCompetence compTmp = null;
                while (compTmp == null) {
                    compTmp = (BanqueCompetence.EntreeCompetence) BanqueCompetence.getEntree(BanqueCompetence.getBanque(), Integer.parseInt(br.readLine()));
                }
                System.out.println(compTmp.getId());
                retour.addCompetence(compTmp);
            }
            return retour;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void prepareMatchEnvoiPerso(Personnage tmp) {
        pw.println(tmp.getId());
        pw.flush();
        pw.println(tmp.getCompetences()[0].getId());
        pw.flush();
        pw.println(tmp.getCompetences()[1].getId());
        pw.flush();
        pw.println(tmp.getCompetences()[2].getId());
        pw.flush();
        pw.println(tmp.getCompetences()[3].getId());
        pw.flush();
    }
}

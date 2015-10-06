package sandarena.connexion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import sandarena.*;
import sandarena.joueur.Joueur;
import sandarena.preparematch.ScreenPrepaMatch;

/**
 * Created by lucmo on 22/08/2015.
 */
public class ConnexionServeur {
    private String serverMessage;
    //public static final String SERVERIP = "90.24.237.150";
    public static final String SERVERIP = "192.168.1.15";
    //public static final String SERVERIP = "0.0.0.0";
    public static final short SERVERPORT = 5333;
    PrintWriter pw;
    BufferedReader br;
    private static final String BEGINMESS = "THOR";
    private static String CONECTMESS = "LOKI";
    private static final String FIRST = "ZEUS";
    private static final String ENDCONV = "HERA";
    private static final String ETAPEOK = "THOT";
    private static final String TESTREC = "EOLE";
    private static final String TESTENV = "RHEA";
    private SandArena container;



    public ConnexionServeur(SandArena container, Joueur equipe) {
        this.container = container;
        try {
            InetAddress serverAddr = InetAddress.getByName(SERVERIP);
            Socket socket = new Socket(serverAddr, SERVERPORT);
            try {
                pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw.println(BEGINMESS);
                pw.flush();
                while (serverMessage == null) {
                    serverMessage = br.readLine();
                    if (serverMessage != null && serverMessage.startsWith(TESTREC)) {
                        pw.println(TESTENV);
                        pw.flush();
                        serverMessage = null;
                    }
                }
                if (serverMessage.startsWith(CONECTMESS)) {
                    if (serverMessage.endsWith(FIRST)) {
                        ConnexionMatch.first = true;
                    } else {
                        ConnexionMatch.first = false;
                    }
                    ConnexionMatch.socketLien = socket;
                    ConnexionMatch.br = br;
                    ConnexionMatch.pw = pw;
                    pw.println(ENDCONV);
                    pw.flush();
                    dispose();
                    //ici changera
                    container.setScreen(new ScreenPrepaMatch(container,3001,equipe));
                } else {
                    dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void dispose() {
        serverMessage = null;
    }
}


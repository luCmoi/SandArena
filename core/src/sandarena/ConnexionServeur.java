package sandarena;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import sandarena.preparematch.ScreenPrepaMatch;

/**
 * Created by lucmo on 22/08/2015.
 */
public class ConnexionServeur {
    private String serverMessage;
    public static final String SERVERIP = "90.24.237.150";
    public static final int SERVERPORT = 5333;
    PrintWriter pw;
    BufferedReader br;
    private static final String BEGINMESS = "THOR";
    private static String CONECTMESS = "LOKI";
    private static final String SERVEURMESS = "ZEUS";
    private static final String ENDCONV = "HERA";
    private static final String SERVEURETABLIT = "THOT";
    private static final String TESTREC = "EOLE";
    private static final String TESTENV = "RHEA";
    private SandArena container;



    public ConnexionServeur(SandArena container) {
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
                System.out.println(serverMessage);
                if (serverMessage.startsWith(CONECTMESS)) {
                    if (serverMessage.endsWith(SERVEURMESS)) {
                        System.out.println("Serveur");
                        ConnexionMatch.serverSocket = new ServerSocket(4233);
                        System.out.println("Socket pret");
                        pw.println(SERVEURETABLIT);
                        pw.flush();
                        ConnexionMatch.socketLien = ConnexionMatch.serverSocket.accept();
                        System.out.println("Socket accepte");
                    } else {
                        serverMessage = null;
                        while (serverMessage == null) {
                            serverMessage = br.readLine();
                        }
                        System.out.println(serverMessage);
                        serverMessage = (serverMessage.split("/"))[1];
                        System.out.println(serverMessage);
                        ConnexionMatch.socketLien = new Socket(InetAddress.getByName(serverMessage), 4233);
                    }
                    pw.println(ENDCONV);
                    pw.flush();
                    dispose();
                    ConnexionMatch.init();
                    container.setScreen(new ScreenPrepaMatch(container));
                } else {
                    System.out.println("Bad Message");
                    dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void dispose() {
        try {
            br.close();
            pw.close();
            serverMessage = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


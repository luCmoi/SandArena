package sandarena.googleservice;

import java.io.IOException;

import sandarena.SandArena;
import sandarena.donnee.blessure.BanqueBlessure;
import sandarena.donnee.competence.BanqueCompetence;
import sandarena.joueur.Personnage;
import sandarena.joueur.blessure.Blessure;
import sandarena.match.partie.jeu.Partie;
import sandarena.match.commun.Timer;

/**
 * Created by lucmo on 23/08/2015.
 */
public class ConnexionMatch {
    private static final String PERSOACTIF = "APIS";
    private static final String DEPLACEMENT = "EDJO";
    private static final String COMPETENCE = "NOUT";
    private static final String UTILISECOMPETENCE = "SETH";
    private static final String FINPHASE = "LETO";
    private static final String ECHANGE = "RHEA";
    public static boolean finTimer = false;
    public static boolean finRecoit = false;

    public static void envoiTimer(int valeur) {
        String mess = String.valueOf(valeur);
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    public static void recoiTimer(final Timer timer) {
        new Thread() {
            @Override
            public void run() {
                finTimer = false;
                String mess = null;
                SandArena.googleService.printError("En attente");
                while (mess == null && !IGoogleService.data.justLeft) {
                    if (finTimer) {
                        finTimer = false;
                        return;
                    }
                    if (IGoogleService.data.time != null) {
                        byte[] tmp = IGoogleService.data.time;
                        IGoogleService.data.time = null;
                        mess = new String(tmp);
                    }
                }
                if (!IGoogleService.data.justLeft) {
                    SandArena.googleService.printError("mess Recu : " + mess);
                    timer.newTime(Integer.parseInt(mess));
                }
            }
        }.start();
    }

    public static Personnage prepareMatchRecoitPerso() {
        finRecoit = false;
        String mess = null;
        SandArena.googleService.printError("En attente");
        while (mess == null && !IGoogleService.data.justLeft) {
            if (finRecoit) {
                finRecoit = false;
                return null;
            }
            if (IGoogleService.data.mess != null) {
                mess = new String(IGoogleService.data.mess);
                IGoogleService.data.mess = null;
            }
        }
        if (!IGoogleService.data.justLeft) {
            SandArena.googleService.printError("mess Recu : " + mess);
            Personnage retour = new Personnage(Integer.parseInt(mess.substring(0, 4)), false);
            for (int i = 0; i < 4; i++) {
                BanqueCompetence.EntreeCompetence compTmp = null;
                while (compTmp == null) {
                    compTmp = (BanqueCompetence.EntreeCompetence) BanqueCompetence.getEntree(BanqueCompetence.getBanque(), Integer.parseInt(mess.substring(4 * (i + 1), 4 * (i + 2))));
                    retour.addCompetence(compTmp);
                }
            }
            for (int i = 0; i < 4; i++) {
                Blessure blessureTmp = null;
                String lecture = null;
                while (lecture == null) {
                    lecture = mess.substring(4 * (i + 5), 4 * (i + 6));
                    SandArena.googleService.printError(lecture);
                    if (!lecture.startsWith("0")) {
                        SandArena.googleService.printError(lecture);
                        blessureTmp = new Blessure((BanqueBlessure.DonneeBlessure) BanqueBlessure.getEntree(BanqueBlessure.getBanque(), Integer.parseInt(lecture)));
                        retour.addBlessure(blessureTmp);
                    }
                }
            }
            return retour;
        } else {
            return null;
        }
    }

    public static void prepareMatchEnvoiPerso(Personnage tmp) {
        String mess = String.valueOf(tmp.getId());
        mess = mess.concat(String.valueOf(tmp.getCompetences()[0].getId()));
        mess = mess.concat(String.valueOf(tmp.getCompetences()[1].getId()));
        mess = mess.concat(String.valueOf(tmp.getCompetences()[2].getId()));
        mess = mess.concat(String.valueOf(tmp.getCompetences()[3].getId()));
        mess = mess.concat(tmp.getBlessures()[0] != null ? String.valueOf(tmp.getBlessures()[0].donnee.getId()) : "0000");
        mess = mess.concat(tmp.getBlessures()[1] != null ? String.valueOf(tmp.getBlessures()[1].donnee.getId()) : "0000");
        mess = mess.concat(tmp.getBlessures()[2] != null ? String.valueOf(tmp.getBlessures()[2].donnee.getId()) : "0000");
        mess = mess.concat(tmp.getBlessures()[3] != null ? String.valueOf(tmp.getBlessures()[3].donnee.getId()) : "0000");
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    public static void ecouteMatch(final Partie partie) {
        new Thread() {
            @Override
            public void run() {
                try {
                    finRecoit = false;
                    String mess = null;
                    SandArena.googleService.printError("En attente");
                    while (true) {
                        if (finRecoit) {
                            finRecoit = false;
                            return;
                        }
                        while (mess == null) {
                            if (IGoogleService.data.mess != null) {
                                mess = new String(IGoogleService.data.mess);
                                IGoogleService.data.mess = null;
                            }
                        }
                        SandArena.googleService.printError("Message reçus : " + mess);
                        if (mess.startsWith(PERSOACTIF)) {
                            partieRecoitPersoActif(partie, mess.substring(4));
                        } else if (mess.startsWith(DEPLACEMENT)) {
                            partieRecoitDeplacement(partie, mess.substring(4));
                        } else if (mess.startsWith(COMPETENCE)) {
                            partieRecoitCompetence(partie, mess.substring(4));
                        } else if (mess.startsWith(UTILISECOMPETENCE)) {
                            partieRecoitUtiliseCompetence(partie, mess.substring(4));
                        } else if (mess.startsWith(FINPHASE)) {
                            partieRecoitFinPhase(partie);
                        } else if (mess.startsWith(ECHANGE)) {
                            partieRecoitEchange(partie, mess.substring(4));
                        }
                        mess = null;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void partieRecoitPersoActif(Partie partie, String mess) {
        int perso = Integer.parseInt(mess.substring(0, 4));
        partie.setPersonnageActif(perso);
    }

    public static void partieEnvoiPersoActif(int persoActif) {
        String mess = PERSOACTIF;
        mess = mess.concat(string4(persoActif));
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    private static void partieRecoitDeplacement(Partie partie, String mess) throws IOException {
        partieRecoitPersoActif(partie, mess);
        int x = Integer.parseInt(mess.substring(4, 8));
        int y = Integer.parseInt(mess.substring(8, 12));
        partie.selectChemin(partie.getPlateau()[x][y]);
        partie.deplacement();
    }

    public static void partieEnvoiDeplacement(int perso, int coordX, int coordY) {
        String mess = DEPLACEMENT;
        mess = mess.concat(string4(perso));
        mess = mess.concat(string4(coordX));
        mess = mess.concat(string4(coordY));
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    private static void partieRecoitCompetence(Partie partie, String mess) throws IOException {
        partieRecoitPersoActif(partie, mess);
        int i = Integer.parseInt(mess.substring(4, 8));
        if (i != 9999) {
            partie.setCompetenceActive(partie.getPersonnageActif().getCompetence()[i]);
        } else {
            partie.setCompetenceActive(null);
        }
    }

    public static void partieEnvoiCompetence(int perso, int comp) {
        String mess = COMPETENCE;
        mess = mess.concat(string4(perso));
        mess = mess.concat(string4(comp));
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    private static void partieRecoitUtiliseCompetence(Partie partie, String mess) throws IOException {
        partieRecoitCompetence(partie, mess);
        int x = Integer.parseInt(mess.substring(8, 12));
        int y = Integer.parseInt(mess.substring(12, 16));
        partie.getCompetenceActive().agit(partie.getPlateau()[x][y]);
        partie.getContainer().getStageInterface().recharge();
    }

    public static void partieEnvoiUtiliseCompetence(int perso, int comp, int coordX, int coordY) {
        String mess = UTILISECOMPETENCE;
        mess = mess.concat(string4(perso));
        mess = mess.concat(string4(comp));
        mess = mess.concat(string4(coordX));
        mess = mess.concat(string4(coordY));
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    private static void partieRecoitFinPhase(Partie partie) {
        partie.finPhase();
        partie.getContainer().getStageInterface().recharge();
    }

    public static void partieEnvoiFinPhase() {
        String mess = FINPHASE;
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    public static void partieEnvoiEchange(int perso, int coordX, int coordY) {
        String mess = ECHANGE;
        mess = mess.concat(string4(perso));
        mess = mess.concat(string4(coordX));
        mess = mess.concat(string4(coordY));
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    private static void partieRecoitEchange(Partie partie, String mess) throws IOException {
        partieRecoitPersoActif(partie, mess);
        int x = Integer.parseInt(mess.substring(4, 8));
        int y = Integer.parseInt(mess.substring(8, 12));
        partie.getPlateau()[x][y].echange();
    }

    private static String string4(int entre) {
        if (entre != -1) {
            String retour = String.valueOf(entre);
            while (retour.length() < 4) {
                retour = "0".concat(retour);
            }
            return retour;
        } else {
            return "9999";
        }
    }


    public static void envoiMap(int map) {
        String mess = String.valueOf(map);
        SandArena.googleService.printError("Envoi mess : " + mess);
        SandArena.googleService.sendOtherPlayer(mess);
    }

    public static int recoitMap() {
        String mess = null;
        SandArena.googleService.printError("En attente");
        while (mess == null && !IGoogleService.data.justLeft) {
            if (IGoogleService.data.mess != null) {
                mess = new String(IGoogleService.data.mess);
                IGoogleService.data.mess = null;
            }
        }
        if (!IGoogleService.data.justLeft) {
            SandArena.googleService.printError("mess Recu : " + mess);
            return Integer.valueOf(mess);
        }
        return 0;
    }
}

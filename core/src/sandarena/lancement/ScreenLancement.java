package sandarena.lancement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import sandarena.SandArena;
import sandarena.donnee.blessure.BanqueBlessure;
import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.googleservice.IGoogleService;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.joueur.blessure.Blessure;

import static sandarena.donnee.blessure.BlessureXML.parseBlessureXML;
import static sandarena.donnee.carte.CarteXML.parseCarteXML;
import static sandarena.donnee.competence.CompXML.parseCompXML;
import static sandarena.donnee.personnage.PersoXML.parsePersoXML;

/**
 * Created by lucmo on 06/10/2015.
 */
public class ScreenLancement implements Screen {
    private SandArena container;
    private Joueur[] equipe = new Joueur[3];
    private boolean loaded = false;
    private StageLancement stageLancement;
    private boolean loadBase = false;

    public ScreenLancement(SandArena sandArena) {
        this.container = sandArena;
        this.stageLancement = new StageLancement(this,container.getBatch());
        stageLancement.setTexte("Chargement des ressources");
        Son.sadStrings.setLooping(true);
        Son.sadStrings.setVolume(0.5f);
        Son.sadStrings.play();
        Son.actuelle = Son.sadStrings;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        stageLancement.draw();
        if (!loadBase) {
            loadBase = true;
            parseCompXML();
            parsePersoXML();
            parseCarteXML();
            parseBlessureXML();
            stageLancement.setTexte("Connexion");
        }
        if (SandArena.googleService.isSignedIn() && !loaded) {
            stageLancement.setTexte("Chargement des sauvegardes");
            loaded = true;
            SandArena.googleService.savedGamesLoadAll();
        }
        if (IGoogleService.data.chargementSaveLoad == 3){
            stageLancement.setTexte("Lecture des sauvegardes");
            IGoogleService.data.chargementSaveLoad = -1;
            for (int i = 0; i < 3; i++) {
                if (IGoogleService.data.save[i]!=null){
                    equipe[i] = parseEquipe(IGoogleService.data.save[i],i);
                }else{
                    equipe[i] = null;
                }
            }
            container.lanceSelect(equipe);
            this.dispose();
        }
    }

    private Joueur parseEquipe(String data,int num) {
        Joueur retour = new Joueur(num);
        Personnage tmpPers = null;
        for (int i = 0; i < data.length(); i = i+4) {
            String tmpStr = data.substring(i,i+4);
            if (tmpStr.startsWith("v")){
                SandArena.googleService.printError("version : " + tmpStr);
            }else if (tmpStr.startsWith("1")){
                if (tmpPers != null){
                    retour.getPersonnages().add(tmpPers);
                }
                tmpPers = new Personnage(Integer.parseInt(tmpStr),true);
            }else if (tmpStr.startsWith("2")){
                BanqueCompetence.EntreeCompetence tmp = (BanqueCompetence.EntreeCompetence)BanqueCompetence.getEntree(BanqueCompetence.banque,Integer.parseInt(tmpStr));
                tmpPers.addCompetence(tmp);
            }else if (tmpStr.startsWith("5")) {
                BanqueBlessure.DonneeBlessure tmp =(BanqueBlessure.DonneeBlessure)BanqueBlessure.getEntree(BanqueBlessure.banque,Integer.parseInt(tmpStr));
                tmpPers.addBlessure(new Blessure(tmp));
            }else if (tmpStr.startsWith("o")){
                retour.setOr(Integer.parseInt(data.substring(i+1)));
                i = data.length();
            }else{
                SandArena.googleService.printError(tmpStr);
            }
        }
        if (tmpPers != null){
            retour.getPersonnages().add(tmpPers);
        }
        if (retour.getPersonnages().isEmpty()){
            return null;
        }
        return retour;
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        container = null;
        equipe = null;
        stageLancement.dispose();
    }
}

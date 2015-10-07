package sandarena.lancement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import sandarena.IGoogleService;
import sandarena.SandArena;
import sandarena.donnee.competence.BanqueCompetence;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.selectionequipe.ScreenSelectionEquipe;

/**
 * Created by lucmo on 06/10/2015.
 */
public class ScreenLancement implements Screen {
    private final SandArena container;
    private Joueur[] equipe = new Joueur[3];
    private boolean loaded = false;

    public ScreenLancement(SandArena sandArena) {
        this.container = sandArena;

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
        if (SandArena.googleService.isSignedIn() && !loaded) {
            loaded = true;
            SandArena.googleService.savedGamesLoadAll();
            SandArena.googleService.printError("Load Finish");
        }
        if (IGoogleService.data.chargementSaveLoad == 3){
            SandArena.googleService.printError("Decrypt");
            IGoogleService.data.chargementSaveLoad = -1;
            for (int i = 0; i < 3; i++) {
                if (IGoogleService.data.save[i]!=null){
                    SandArena.googleService.printError("Save");
                    equipe[i] = parseEquipe(IGoogleService.data.save[i]);
                }else{
                    SandArena.googleService.printError("No save");
                    equipe[i] = null;
                }
            }
            container.setScreen(new ScreenSelectionEquipe(container, equipe));
        }
    }

    private Joueur parseEquipe(String data) {
        Joueur retour = new Joueur();
        Personnage tmpPers = null;
        for (int i = 0; i < data.length(); i = i+4) {
            String tmpStr = data.substring(i,i+4);
            if (tmpStr.startsWith("v")){
                SandArena.googleService.printError("version : " + tmpStr);
            }else if (tmpStr.startsWith("1")){
                SandArena.googleService.printError("personnage : " + tmpStr);
                if (tmpPers != null){
                    retour.getPersonnages().add(tmpPers);
                }
                tmpPers = new Personnage(Integer.parseInt(tmpStr));
            }else if (tmpStr.startsWith("2")){
                SandArena.googleService.printError("competence : " + tmpStr);
                BanqueCompetence.EntreeCompetence tmp = (BanqueCompetence.EntreeCompetence)BanqueCompetence.getEntree(BanqueCompetence.banque,Integer.parseInt(tmpStr));
                tmp.incremente();
                tmpPers.addCompetence(tmp);
            }else{
                SandArena.googleService.printError(tmpStr);
            }
        }
        if (tmpPers != null){
            retour.getPersonnages().add(tmpPers);
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

    }
}

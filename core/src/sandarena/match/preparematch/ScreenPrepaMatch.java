package sandarena.match.preparematch;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import java.util.ArrayList;

import sandarena.SandArena;
import sandarena.donnee.donneestatic.Resolution;
import sandarena.donnee.donneestatic.Son;
import sandarena.googleservice.ConnexionMatch;
import sandarena.googleservice.IGoogleService;
import sandarena.interfaceutil.stage.ScreenSurcouche;
import sandarena.joueur.Joueur;
import sandarena.joueur.Personnage;
import sandarena.match.commun.SurcoucheMatchCommun;
import sandarena.match.preparematch.barre.StageBarre;
import sandarena.match.preparematch.stageprincipal.StagePrincipalScreenPrepa;


/**
 * Created by Guillaume on 20/07/2015.
 */
public class ScreenPrepaMatch extends ScreenSurcouche {
    private Batch batch;
    private SandArena container;
    private StageBarre barre;
    private StagePrincipalScreenPrepa principal;
    private Joueur joueur;
    private Personnage check = null;
    private int map;

    public ScreenPrepaMatch(SandArena conteneur, int i, Joueur equipe) {
        this.container = conteneur;
        this.map = i;
        this.batch = conteneur.getBatch();
        joueur = equipe;
        if (Son.actuelle != null) {
            Son.actuelle.stop();
        }
        Son.ambiancePrepare.setLooping(true);
        Son.ambiancePrepare.setVolume(0.8f);
        Son.ambiancePrepare.play();
        Son.actuelle = Son.ambiancePrepare;
    }

    @Override
    public void render(float delta) {
        if (!IGoogleService.data.justLeft) {
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glViewport(Resolution.differenceBas / 2, 0, Resolution.width - Resolution.differenceBas, Resolution.differenceBas);
            if (check != null) {
                setCheck(null);
                if (principal.getCommence() && principal.testFin()) {
                    finPrepare();
                }
            }
            this.getBarre().draw();
            Gdx.gl.glViewport(0, 0, Resolution.width, Resolution.height);
            this.getPrincipal().draw();
            this.getSurcoucheMatchCommun().draw();
        } else {
            IGoogleService.data.justLeft = false;
            Son.ambiancePrepare.stop();
            container.lanceGestionEquipe(this.getJoueur());
            this.dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        boolean commence = IGoogleService.data.commence;
        setSurcoucheMatchCommun(new SurcoucheMatchCommun(this, new FillViewport(Resolution.width, Resolution.height), batch));
        this.setPrincipal(new StagePrincipalScreenPrepa(this, joueur, commence, new ScalingViewport(Scaling.none, Resolution.width, Resolution.height), batch));
        this.setBarre(new sandarena.match.preparematch.barre.StageBarre(this.getPrincipal(), joueur, new ExtendViewport(Resolution.width - Resolution.differenceBas, Resolution.differenceBas, Resolution.width - Resolution.differenceBas, Resolution.differenceBas), batch));
        getPrincipal().setBarre(this.getBarre());
        Gdx.input.setInputProcessor(this.getPrincipal());
        IGoogleService.data.lancePartie = false;
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
        barre.dispose();
        barre.clear();
        principal.dispose();
        principal.clear();
        surcouche.dispose();
        surcouche.clear();
        ConnexionMatch.finRecoit = true;
        check = null;
        joueur = null;
        batch = null;
    }

    @Override
    public void finInputSurcouche() {
        Gdx.input.setInputProcessor(principal);
    }

    private sandarena.match.preparematch.barre.StageBarre getBarre() {
        return barre;
    }

    private void setBarre(sandarena.match.preparematch.barre.StageBarre barre) {
        this.barre = barre;
    }

    public StagePrincipalScreenPrepa getPrincipal() {
        return principal;
    }

    private void setPrincipal(StagePrincipalScreenPrepa principal) {
        this.principal = principal;
    }

    public SurcoucheMatchCommun getSurcoucheMatchCommun() {
        return (SurcoucheMatchCommun)surcouche;
    }

    private void setSurcoucheMatchCommun(SurcoucheMatchCommun surcoucheMatchCommun) {
        this.surcouche = surcoucheMatchCommun;
    }

    private Joueur getJoueur() {
        return joueur;
    }

    public void finPrepare() {
        ArrayList<Personnage> personnagesActif = principal.getPersonnagesActif();
        ArrayList<Personnage> personnagesAutre = principal.getPersonnagesAutre();
        IGoogleService.data.time = null;
        container.lancePartie(joueur, personnagesActif, personnagesAutre, !(principal.getCommence()), map);
    }

    public void setCheck(Personnage check) {
        this.check = check;
    }

    public Personnage getCheck() {
        return check;
    }

    public void backKeyPressed() {
    }

}
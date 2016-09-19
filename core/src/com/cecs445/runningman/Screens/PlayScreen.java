package com.cecs445.runningman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Scenes.Hud;

/**
 * Created by Kevin on 9/14/2016.
 */
public class PlayScreen implements Screen{
    private RunningMan game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    public PlayScreen(RunningMan game){
        this.game = game;
        //create came used to follow man through cam world
        gamecam = new OrthographicCamera();
        //create fitviewport to maintain virtual aspect ratio
        gamePort = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, gamecam);
        //create our game hud for scores timers etc
        hud = new Hud(game.batch);
        //load map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level 1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);


    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched())
            gamecam.position.x += 100 * dt; //temporary
    }

    public void update(float dt) {
        handleInput(dt);

        gamecam.update(); //updates cam after every user input
        renderer.setView(gamecam); //renders what our gamecam can see
    }

    @Override
    public void render(float delta) {
        update(delta);

        // clears game screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(); //draws tile map

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

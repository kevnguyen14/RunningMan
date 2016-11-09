package com.cecs445.runningman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Scenes.Hud;


/**
 * Created by Christian on 10/3/2016.
 */
public class TitleScreen implements Screen{
    private RunningMan game;
    public Viewport viewport;
    public Stage stage;
    private Texture bg;
    private boolean playPressed, connectPressed, exitPressed;
    public TitleScreen(RunningMan game) {
        this.game = game;
        Table table = new Table();
        viewport = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        //making background
        bg = new Texture("graphics\\bg\\bgresized.jpg");

        //making banner
        Image banner = new Image(new Texture("graphics\\banner\\banner.png"));
//        banner.setSize(30, 30);
        banner.setScaling(Scaling.stretch);

        table.top();
        table.setFillParent(true);
        table.padRight(10).padLeft(10).padTop(10).padBottom(10);
//        table.setTouchable(Touchable.enabled);
        table.add(banner);

        //making play
        Image play = new Image(new Texture("graphics\\buttons\\playbutton.png"));
        banner.setScaling(Scaling.fit);
        play.setTouchable(Touchable.enabled);
        play.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playPressed = false;
            }
        });
        table.row();
        table.center();
        table.setFillParent(true);
        table.add(play).padBottom(5);

        //making connect
        Image connect = new Image(new Texture("graphics\\buttons\\connectbutton.png"));
        banner.setScaling(Scaling.fit);
        connect.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                connectPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                connectPressed = false;
            }
        });
        table.row();
        table.center();
        table.setFillParent(true);
        table.add(connect).padBottom(5);

        //making exit
        Image exit = new Image(new Texture("graphics\\buttons\\exitbutton.png"));
        banner.setScaling(Scaling.fit);
        exit.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                exitPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                exitPressed = false;
            }
        });
        table.row();
        table.center();
        table.setFillParent(true);
        table.add(exit).padBottom(5);

        stage.addActor(table);
    }

    public void handleInput(float dt){
//        if(Gdx.input.isTouched()){
//            float y = Gdx.input.getY();
//            if (y < (viewport.getScreenHeight() - 2*(viewport.getScreenHeight()/3))){
//                game.setScreen(new PlayScreen(this.game));
//            }
//            if (y > (viewport.getScreenHeight() - 2*(viewport.getScreenHeight()/3)) && y < (viewport.getScreenHeight() - (viewport.getScreenHeight()/3))){
////                game.setScreen(new PlayScreen(this.game));
//            }
//            if (y > (viewport.getScreenHeight() - (viewport.getScreenHeight()/3))){
//                System.exit(0);
//            }
//        }

        //play button
        if(isPlayPressed())
            game.setScreen(new PlayScreen(this.game));
        //connect button
        /**
         * Hilario code for sign in api
         */
        //exit button
        if(isExitPressed())
            System.exit(0);

    }

    public void update(float dt) {
        handleInput(dt);


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(stage.getCamera().view);
        stage.act(delta);
        stage.getBatch().begin();
        stage.getBatch().draw(bg, 0, 0, RunningMan.V_WIDTH, RunningMan.V_HEIGHT);
        stage.getBatch().end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    public boolean isPlayPressed(){
        return playPressed;
    }

    public boolean isExitPressed(){
        return exitPressed;
    }

    public boolean isConnectPressed(){
        return connectPressed;
    }
}

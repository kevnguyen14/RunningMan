package com.cecs445.runningman.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;

/**
 * Created by Steven on 10/12/2016.
 */

public class LevelScreen implements Screen{

    private RunningMan game;
    public Stage myStage;
    private Viewport views;
    public OrthographicCamera myCam;

    private boolean onePress, twoPress, backPress;

    public LevelScreen (RunningMan game) {
        this.game = game;
        myCam = new OrthographicCamera();
        views = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, myCam);
        myStage = new Stage(views);
        Gdx.input.setInputProcessor(myStage);

        Image levelOneImage = new Image(new Texture("levelOne.jpg"));
        levelOneImage.setSize(100,50);
        levelOneImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onePress = true;
//                game.setScreen(new LevelScreen(this.game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onePress = false;
            }
        });

        Image levelTwoImage = new Image(new Texture("levelTwo.png"));
        levelTwoImage.setSize(100,50);
        levelTwoImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                twoPress = true;
//                game.setScreen(new LevelScreen(this.game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                twoPress = false;
            }
        });

        Image backImage = new Image(new Texture("backButton.jpg"));
        backImage.setSize(100,50);
        backImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                backPress = true;
//                game.setScreen(new LevelScreen(this.game));
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                backPress = false;
            }
        });

        Table myTable = new Table();
        myTable.top().padBottom(165);
        myTable.setFillParent(true);
        myTable.add(levelOneImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myStage.addActor(myTable);

        Table myTable2 = new Table();
        myTable2.top();
        myTable2.setFillParent(true);
        myTable2.add(levelTwoImage).size(levelTwoImage.getWidth(),levelTwoImage.getHeight()).padTop(60);
        myStage.addActor(myTable2);

        Table myTable3 = new Table();
        myTable3.center().padTop(100);
        myTable3.setFillParent(true);
        myTable3.add(backImage).size(backImage.getWidth(), backImage.getHeight()).padBottom(20);
        myStage.addActor(myTable3);

}

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(myStage.getCamera().view);
        myStage.draw();
    }

    public void update(float dt) {
        handleInput(dt);

    }

    public void handleInput(float dt) {
        if (backPress == true) {
            game.setScreen(new TitleScreen(this.game));
        }

        if (onePress) {
            game.setScreen(new PlayScreen(this.game));
        }

        if(twoPress == true) {
            game.setScreen(new PlayScreen(this.game));
        }

        //Crashes the game when this button is pressed
        //Tested with other image buttons, still crashes
        //Tested onepress and twopress to go to titlescreen and it worked

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

    public boolean isOnePress() {

        return onePress;
    }

    public boolean isTwoPress() {

        return twoPress;

    }

    public boolean isBackPress() {
        return backPress;
    }
}


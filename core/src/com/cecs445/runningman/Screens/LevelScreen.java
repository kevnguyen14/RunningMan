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
    private Texture bg;
    private static int level;

    private boolean onePress, twoPress,threePress, fourPress, fivePress, sixPress, exitPress;

    public LevelScreen (RunningMan game) {
        this.game = game;
        //making background
        bg = new Texture("graphics\\bg\\bgresized.jpg");
        myCam = new OrthographicCamera();
        views = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, myCam);
        myStage = new Stage(views);
        level = 0;
        Gdx.input.setInputProcessor(myStage);

        Image levelOneImage = new Image(new Texture("1.png"));
        levelOneImage.setSize(100,50);
        levelOneImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onePress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onePress = false;
            }
        });

        Image levelTwoImage = new Image(new Texture("2.png"));
        levelTwoImage.setSize(100,50);
        levelTwoImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                twoPress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                twoPress = false;
            }
        });

        Image levelThreeImage = new Image(new Texture("3.png"));
        levelOneImage.setSize(100,50);
        levelOneImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onePress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onePress = false;
            }
        });
        Image levelFourImage = new Image(new Texture("4.png"));
        levelOneImage.setSize(100,50);
        levelOneImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onePress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onePress = false;
            }
        });

        Image levelFiveImage = new Image(new Texture("5.png"));
        levelOneImage.setSize(100,50);
        levelOneImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onePress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onePress = false;
            }
        });

        Image levelSixImage = new Image(new Texture("6.png"));
        levelOneImage.setSize(100,50);
        levelOneImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onePress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onePress = false;
            }
        });

        Image exitImage = new Image(new Texture("exitbutton.png"));
        exitImage.setSize(100,50);
        exitImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                exitPress = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                exitPress = false;
            }
        });

        Table myTable = new Table();
        myTable.top();
        myTable.setFillParent(true);
        myTable.add(levelOneImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myTable.add(levelTwoImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myTable.add(levelThreeImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myTable.row();
        myTable.add(levelFourImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myTable.add(levelFiveImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myTable.add(levelSixImage).size(levelOneImage.getWidth(), levelOneImage.getHeight());
        myStage.addActor(myTable);

        Table myTable2 = new Table();
        myTable2.bottom().left().pad(25);
        myTable2.add(exitImage).size(levelTwoImage.getWidth(),levelTwoImage.getHeight());
        myStage.addActor(myTable2);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(myStage.getCamera().view);
        myStage.act(delta);
        myStage.getBatch().begin();
        myStage.getBatch().draw(bg, 0, 0, RunningMan.V_WIDTH, RunningMan.V_HEIGHT);
        myStage.getBatch().end();
        myStage.draw();
        setInput();
    }

    public void update(float dt) {
        handleInput(dt);

    }

    public void handleInput(float dt) {
        if (exitPress) {
            game.setScreen(new TitleScreen(this.game));
            RunningMan.buttonselect.play();
        }

        else if (onePress) {
            level = 1;
            game.setScreen(new PlayScreen(this.game,level));
            RunningMan.buttonselect.play();
        }

        else if(twoPress) {
            level = 2;
            game.setScreen(new PlayScreen(this.game,level));
            RunningMan.buttonselect.play();
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
    public boolean isThreePress() {

        return onePress;
    }

    public boolean isFourPress() {

        return twoPress;

    }   public boolean isFivePress() {

        return onePress;
    }

    public boolean isSixPress() {

        return twoPress;

    }
    public boolean isBackPress() {

        return exitPress;
    }

    public static int getLevel() {
        return level;
    }

    public void setInput() {
        Gdx.input.setInputProcessor(myStage);
    }
}


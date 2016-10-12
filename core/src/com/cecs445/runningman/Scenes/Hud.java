package com.cecs445.runningman.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;


/**
 * Created by Kevin on 9/14/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    public OrthographicCamera hudCam;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private boolean rightPressed, leftPressed, jumpPressed;

    Label countdownLabel, scoreLabel, timeLabel, levelLabel, currentLevelLabel, manLabel, blankLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        hudCam = new OrthographicCamera();
        viewport = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, hudCam);
        stage = new Stage(viewport, sb);
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.top(); //top of screen
        table.setFillParent(true); //size of our stage

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        currentLevelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manLabel = new Label("RUNNING MAN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        blankLabel = new Label("                                ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //add labels to table
        table.add(manLabel).expandX().padTop(10);
        table.add().expandX();
        table.add(timeLabel).expandX().padTop(10);
        table.add().expandX();
        table.add(levelLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add().expandX();
        table.add(countdownLabel).expandX();
        table.add().expandX();
        table.add(currentLevelLabel).expandX();

        //add table to stage
        stage.addActor(table);

        //making controller
        Image leftImage = new Image(new Texture("flatDark23.png"));
        leftImage.setSize(30, 30);
        leftImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                leftPressed = false;
            }
        });

        Image rightImage = new Image(new Texture("flatDark24.png"));
        rightImage.setSize(30, 30);
        rightImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                rightPressed = false;
            }
        });

        Image aImage = new Image(new Texture("a.png"));
        aImage.setSize(30, 30);
        aImage.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                jumpPressed = false;
            }
        });
        Table table2 = new Table();
        table2.bottom().left().padLeft(10).padBottom(20); //bottom left of screen
        table2.setFillParent(true); //size of our stage
        table2.add(leftImage).size(leftImage.getWidth(), leftImage.getHeight()).padRight(20);
        table2.add(rightImage).size(rightImage.getWidth(), rightImage.getHeight());
        stage.addActor(table2);
        Table table3 = new Table();
        table3.bottom().right().padBottom(22); //bottom right of screen
        table3.setFillParent(true); //size of our stage
        table3.add(aImage).size(aImage.getWidth(), aImage.getHeight()).padRight(10);
        stage.addActor(table3);
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isJumpPressed() {
        return jumpPressed;
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }
}

package com.cecs445.runningman.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Screens.LevelScreen;
import com.cecs445.runningman.Sprites.Man;


/**
 * Created by Kevin on 9/14/2016.
 */
public class EndHud {
    public Stage stage;
    private Viewport viewport;
    public OrthographicCamera hudCam;

    Label gratsLabel, scoreLabel, timeLabel, exitLabel;

    public EndHud(SpriteBatch sb){

        hudCam = new OrthographicCamera();
        viewport = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, hudCam);
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.center(); //top of screen
        table.setFillParent(true); //size of our stage

        gratsLabel = new Label("YOU LOSE ON LEVEL " + LevelScreen.getLevel() + "!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        exitLabel = new Label("Click anywhere on screen to exit.", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //add labels to table
        table.add(gratsLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX();
        table.row();
        table.add(timeLabel).expandX();
        table.row();
        table.add(exitLabel).expandX();

        //add table to stage
        stage.addActor(table);

    }

    public void update(float dt){
    }

    public void resize(int width, int height){
        viewport.update(width, height);
    }

    public void setEndHudInput() {
        Gdx.input.setInputProcessor(stage);
    }

    public void destroy() {
        stage.dispose();
    }
}

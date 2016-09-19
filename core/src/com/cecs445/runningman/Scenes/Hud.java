package com.cecs445.runningman.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;

/**
 * Created by Kevin on 9/14/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel, scoreLabel, timeLabel, levelLabel, currentLevelLabel, manLabel;

    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top(); //top of screen
        table.setFillParent(true); //size of our stage

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        currentLevelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        manLabel = new Label("RUNNING MAN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //add labels to table
        table.add(manLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(levelLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(countdownLabel).expandX();
        table.add(currentLevelLabel).expandX();

        //add table to stage
        stage.addActor(table);
    }

}

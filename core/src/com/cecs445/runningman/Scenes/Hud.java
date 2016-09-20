package com.cecs445.runningman.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;

import java.awt.Image;

/**
 * Created by Kevin on 9/14/2016.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private Skin touchpadSkin;
    private Touchpad touchpad, touchpad2;
    private Touchpad.TouchpadStyle touchpadStyle, touchpadStyle2;

    private Drawable dPad, a;
    Texture dPadButton, aButton;

    Label countdownLabel, scoreLabel, timeLabel, levelLabel, currentLevelLabel, manLabel;

    public Hud(SpriteBatch sb){
        dPadButton = new Texture("dPad.png");
        aButton = new Texture("a.png");

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

        //making dPad
        //Create a touchpad skin
        touchpadSkin = new Skin();
        //Set background image
        touchpadSkin.add("dPad", dPadButton);
        //Set knob image
        touchpadSkin.add("a", aButton);
        //Create TouchPad Style
        touchpadStyle = new Touchpad.TouchpadStyle();
        //Create Drawable's from TouchPad skin
        dPad = touchpadSkin.getDrawable("dPad");
        a = touchpadSkin.getDrawable("a");
        //Apply the Drawables to the TouchPad Style
        //touchpadStyle.background = dPad;
        touchpadStyle.knob = dPad;
        //Create new TouchPad with the created style
        touchpad = new Touchpad(10, touchpadStyle);
        //setBounds(x,y,width,height)
        touchpad.setPosition(55, 45);
        stage.addActor(touchpad);
        touchpadStyle2 = new Touchpad.TouchpadStyle();
        touchpadStyle2.knob = a;
        touchpad2 = new Touchpad(10, touchpadStyle2);
        touchpad2.setPosition(355, 45);
        stage.addActor(touchpad2);
    }

}

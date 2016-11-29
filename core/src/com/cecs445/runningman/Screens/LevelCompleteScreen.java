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
import com.cecs445.runningman.Sprites.Man;


/**
 * Created by Kevin  
 */
public class LevelCompleteScreen implements Screen{
    private RunningMan game;
    public Viewport viewport;
    public Stage stage;
    private Texture bg;
    private Label gratsLabel, scoreLabel, timeLabel, exitLabel;

    public LevelCompleteScreen(RunningMan game) {
        this.game = game;
        Table table = new Table();
        viewport = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        //making background
        bg = new Texture("graphics\\bg\\bgresized.jpg");

        table.center(); //top of screen
        table.setFillParent(true); //size of our stage

        gratsLabel = new Label("Congratulations on beating Level " + LevelScreen.getLevel(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("Remaining Health: %02d", Man.getPlayerHealth()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label("Remaining Time: " + Hud.getWorldTimer(), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
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

    public void handleInput(float dt){
        if(Gdx.input.justTouched())
            game.setScreen(new TitleScreen(this.game));
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
}

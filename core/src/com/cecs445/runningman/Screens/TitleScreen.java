package com.cecs445.runningman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    Texture texture;
    public boolean playTouch = false;

    public Viewport viewport;
    public Label exitLabel;
    public Image playLabel;
    private Hud hud;
    public Stage stage;
    public TitleScreen(RunningMan game) {
        this.game = game;

        //texture = new Texture("badlogic.jpg");


        playLabel = new Image(new Texture("TitleScreen.jpg"));
        playLabel.setScaling(Scaling.stretch);
        viewport = new FitViewport(RunningMan.V_WIDTH, RunningMan.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        table.setTouchable(Touchable.enabled);

        //PlayLabel = new Label("Play", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        //exitLabel = new Label("Exit", new Label.LabelStyle(new BitmapFont(),Color.WHITE));
        table.top();
        table.add(playLabel);

//        Table table2 = new Table();
//        table2.bottom();
//        table2.setFillParent(true);
//        table2.add(exitLabel).expandX().padBottom(10);
//        stage.addActor(table);
//        stage.addActor(table2);
        stage.addActor(table);
    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            float y = Gdx.input.getY();
            if (y < (viewport.getScreenHeight() - 2*(viewport.getScreenHeight()/3))){
                game.setScreen(new PlayScreen(this.game));
            }
            if (y > (viewport.getScreenHeight() - 2*(viewport.getScreenHeight()/3)) && y < (viewport.getScreenHeight() - (viewport.getScreenHeight()/3))){
//                game.setScreen(new PlayScreen(this.game));
            }
            if (y > (viewport.getScreenHeight() - (viewport.getScreenHeight()/3))){
                System.exit(0);
            }
        }
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
        stage.draw();
//        Gdx.gl.glClearColor(1,0,0,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        game.batch.begin();
//        game.batch.draw(texture,0,0);
//        game.batch.end();

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

package com.cecs445.runningman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Scenes.EndHud;
import com.cecs445.runningman.Scenes.Hud;
import com.cecs445.runningman.Sprites.Man;
import com.cecs445.runningman.Sprites.Warp;
import com.cecs445.runningman.Tools.BoxWorldCreator;
import com.cecs445.runningman.Tools.worldContactListener;

/**
 * Created by Kevin on 9/14/2016.
 */
public class PlayScreen implements Screen{
    private RunningMan game;
    private TextureAtlas atlas;

    //game world camera
    private OrthographicCamera gamecam;

    //gameworld view
    private Viewport gamePort;

    //creating hud
    private Hud hud;
    private EndHud endHud;

    //creating man
    private Man man;

    //tiled mape variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr; //shows whats going on in box2d

    public PlayScreen(RunningMan game, int level){
        this.game = game;

        atlas = new TextureAtlas("Man.pack");

        Gdx.input.setCatchBackKey(true);
        //create cam used to follow man through cam world
        gamecam = new OrthographicCamera();

        //create fitviewport to maintain virtual aspect ratio
        gamePort = new FitViewport(RunningMan.V_WIDTH/RunningMan.PPM, RunningMan.V_HEIGHT/RunningMan.PPM, gamecam);

        //create our game hud for scores timers etc
        hud = new Hud(game.batch);
        endHud = new EndHud(game.batch);

        //load map and setup our map renderer
        mapLoader = new TmxMapLoader();

        RunningMan.backgroundMusic.play();

        switch(level){
            case 1:
                map = mapLoader.load("level1.tmx");
                break;
            case 2:
                map = mapLoader.load("level2.tmx");
                break;
            case 6:
                map = mapLoader.load("level6.tmx");
                break;
        }

        renderer = new OrthogonalTiledMapRenderer(map, 1/RunningMan.PPM);

        //initially set our gamecam to be center correctly at the start of the game
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);

        //creating the world
        world = new World(new Vector2(0, -10), true); //sleep objects at rest true, 0,0 no gravity

        //creating the line Box2D renderer for debugging
        b2dr = new Box2DDebugRenderer();

        //creating the running man
        man = new Man(world, this);
        hud.setHealth(man.playerHealth);
        //intitalizing box2d body, bdef and fdef
        BoxWorldCreator boxWorldCreator = new BoxWorldCreator(world, map, man, hud, this.game);

//        //creating pipe bodies/fixtures
//        for(MapObject object: map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2)/RunningMan.PPM, (rect.getY() + rect.getHeight() / 2)/RunningMan.PPM);
//
//            body = world.createBody(bdef);
//
//            shape.setAsBox(rect.getWidth() / 2/RunningMan.PPM, rect.getHeight()/2/RunningMan.PPM);
//            fdef.shape = shape;
//            body.createFixture(fdef);
//        }

        //creating ground body/fixtures




        world.setContactListener(new worldContactListener());

    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    @Override
    public void show() {

    }

    public void handleInput(float dt){
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && man.b2body.getLinearVelocity().y == 0) {
            man.b2body.applyLinearImpulse(new Vector2(0, 3.5f), man.b2body.getWorldCenter(), true); //applying vertical force in y direction, force applied on the bodys center
            RunningMan.jump.play();
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && man.b2body.getLinearVelocity().x <= 2)
            man.b2body.applyLinearImpulse(new Vector2(man.movementSpeed, 0), man.b2body.getWorldCenter(), true);
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && man.b2body.getLinearVelocity().x >= -2)
            man.b2body.applyLinearImpulse(new Vector2(-man.movementSpeed, 0), man.b2body.getWorldCenter(), true);


        //controller inputs
        if(hud.isRightPressed()) {
            man.b2body.applyLinearImpulse(new Vector2(man.movementSpeed, 0), man.b2body.getWorldCenter(), true);
            if(hud.isJumpPressed() && man.b2body.getLinearVelocity().y == 0 && Gdx.input.justTouched()){
                man.b2body.applyLinearImpulse(new Vector2(0, man.jumpPower), man.b2body.getWorldCenter(), true);
                RunningMan.jump.play();
            }
        }
        else if(hud.isLeftPressed()) {
            man.b2body.applyLinearImpulse(new Vector2(-man.movementSpeed, 0), man.b2body.getWorldCenter(), true);
            if(hud.isJumpPressed() && man.b2body.getLinearVelocity().y == 0 && Gdx.input.justTouched()){
                man.b2body.applyLinearImpulse(new Vector2(0, man.jumpPower), man.b2body.getWorldCenter(), true);
                RunningMan.jump.play();
            }
        }

        else if(man.b2body.getLinearVelocity().y == 0 && hud.isJumpPressed()) {
            man.b2body.applyLinearImpulse(new Vector2(0, man.jumpPower), man.b2body.getWorldCenter(), true);
            RunningMan.jump.play();
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new TitleScreen(this.game));
            RunningMan.backgroundMusic.stop();
        }
    }

    public void deathHandler() {
        if(Gdx.input.justTouched()) {
            game.setScreen(new TitleScreen(this.game));
        }
    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        man.update(dt);
        hud.update(dt);
        hud.setHealth(man.playerHealth);
        // death beneath tile floors
        if(man.getY() < 0 || hud.health <= 0 || hud.worldTimer <= 0) {
            hud.destroyHud();
            deathHandler();
            RunningMan.backgroundMusic.stop();
        }

        System.out.println(man.b2body.getPosition().x + " " + man.b2body.getPosition().y);
        //level one warp
        if(LevelScreen.getLevel() == 1)
            levelOneWarps();

        //level six warps
        if(LevelScreen.getLevel() == 6)
            levelSixWarps();

        //everytime man moves, track with game cam only on x axis
        gamecam.position.x = man.b2body.getPosition().x;
        if(LevelScreen.getLevel() == 6)
            gamecam.position.y = man.b2body.getPosition().y;
        gamecam.update(); //updates cam after every user input
        renderer.setView(gamecam); //renders what our gamecam can see
    }

    public void levelOneWarps(){
        //level one warp
        if(man.b2body.getPosition().x > 24.5 && man.b2body.getPosition().x <24.8 && man.b2body.getPosition().y > 0.8 && man.b2body.getPosition().y < 0.9)
            man.manReset();
    }

    public void levelSixWarps(){
        //level six side warps
        if(man.b2body.getPosition().x < 0) {
            man.b2body.setTransform((16*20)/RunningMan.PPM, man.b2body.getPosition().y, 0);
        }

        if(man.b2body.getPosition().x > 16*20/RunningMan.PPM) {
            man.b2body.setTransform(0 , man.b2body.getPosition().y, 0);
        }

        if(man.b2body.getPosition().x > 1.5279071 && man.b2body.getPosition().x < 1.6845737 && man.b2body.getPosition().y >= 3.4249995 && man.b2body.getPosition().y < 4)
            man.manReset();

        if(man.b2body.getPosition().x > 2.9793448 && man.b2body.getPosition().x < 3.1226783 && man.b2body.getPosition().y >= 6.4 && man.b2body.getPosition().y < 7)
            man.manReset();

    }

    @Override
    public void render(float delta) {

        //separate our update logic from render
        update(delta);

        // clears game screen with black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render game map
        renderer.render(); //draws tile map

        //renderer our Box2DDebugLines
//        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        man.draw(game.batch);
        game.batch.end();

        //set our batch to now draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        hud.setInput();

        if(man.getY() < 0 || hud.health <= 0 || hud.worldTimer <= 0) {
            game.batch.setProjectionMatrix(endHud.stage.getCamera().combined);
            endHud.stage.draw();
            endHud.setEndHudInput();
            RunningMan.backgroundMusic.stop();
        }
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);
        hud.resize(width, height);
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

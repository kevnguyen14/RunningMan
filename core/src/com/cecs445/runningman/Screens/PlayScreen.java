package com.cecs445.runningman.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.cecs445.runningman.Scenes.Hud;
import com.cecs445.runningman.Sprites.Man;
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

    //creating man
    private Man man;

    //tiled mape variables
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr; //shows whats going on in box2d

    public PlayScreen(RunningMan game){
        this.game = game;
        atlas = new TextureAtlas("Runner.pack");

        Gdx.input.setCatchBackKey(true);
        //create cam used to follow man through cam world
        gamecam = new OrthographicCamera();

        //create fitviewport to maintain virtual aspect ratio
        gamePort = new FitViewport(RunningMan.V_WIDTH/RunningMan.PPM, RunningMan.V_HEIGHT/RunningMan.PPM, gamecam);

        //create our game hud for scores timers etc
        hud = new Hud(game.batch);

        //load map and setup our map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
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
        BoxWorldCreator boxWorldCreator = new BoxWorldCreator(world, map, man, hud);

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
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && man.b2body.getLinearVelocity().y == 0)
            man.b2body.applyLinearImpulse(new Vector2(0, 3.5f), man.b2body.getWorldCenter(), true); //applying vertical force in y direction, force applied on the bodys center
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && man.b2body.getLinearVelocity().x <= 2)
            man.b2body.applyLinearImpulse(new Vector2(man.movementSpeed, 0), man.b2body.getWorldCenter(), true);
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && man.b2body.getLinearVelocity().x >= -2)
            man.b2body.applyLinearImpulse(new Vector2(-man.movementSpeed, 0), man.b2body.getWorldCenter(), true);


        //controller inputs
        if(hud.isRightPressed())
            man.b2body.applyLinearImpulse(new Vector2(man.movementSpeed, 0), man.b2body.getWorldCenter(), true);
        if(hud.isLeftPressed())
            man.b2body.applyLinearImpulse(new Vector2(-man.movementSpeed, 0), man.b2body.getWorldCenter(), true);
        if(hud.isJumpPressed() && man.b2body.getLinearVelocity().y == 0) {
            man.b2body.applyLinearImpulse(new Vector2(0, 3.5f), man.b2body.getWorldCenter(), true);
            man.damageTrigger();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.BACK)){
            game.setScreen(new TitleScreen(this.game));
        }
//        if(Gdx.input.isTouched(1) && man.b2body.getLinearVelocity().y == 0) {
//            //jump
//            if(Gdx.input.getX(1) >=1621  && Gdx.input.getX(1) <= 1815 && Gdx.input.getY(1) >= 740  && Gdx.input.getY(1) <=  935) {
//                man.b2body.applyLinearImpulse(new Vector2(0, 4f), man.b2body.getWorldCenter(), true);}
//        }
//
//        if(Gdx.input.isTouched(0)) {
//            gamecam.unproject(touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0));
//            Gdx.app.log("Touch pos X: ", touchPos.x + " " + "Touch pos Y: " + touchPos.y + " ");
//            //moving right touchscreen
//            if(Gdx.input.getX(0) >= 310 && Gdx.input.getX(0) <= 445 && Gdx.input.getY(0) >= 770  && Gdx.input.getY(0) <= 870 && man.b2body.getLinearVelocity().x <= 2 )
//                man.b2body.applyLinearImpulse(new Vector2(0.1f, 0), man.b2body.getWorldCenter(), true);
//            //moving left touchscreen
//            if(Gdx.input.getX(0) >= 70 && Gdx.input.getX(0) <= 220 && Gdx.input.getY(0) >= 770  && Gdx.input.getY(0) <= 870 && man.b2body.getLinearVelocity().x >= -2)
//                man.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), man.b2body.getWorldCenter(), true);
//            if(Gdx.input.getX(0) >=1621  && Gdx.input.getX(0) <= 1815 && Gdx.input.getY(0) >= 740  && Gdx.input.getY(0) <=  935 && man.b2body.getLinearVelocity().y == 0) {
//                man.b2body.applyLinearImpulse(new Vector2(0, 4f), man.b2body.getWorldCenter(), true);}
//        }

    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        man.update(dt);
        hud.update(dt);
        hud.setHealth(man.playerHealth);
        // death beneath tile floors
        if(man.getY() < 0 || hud.health <= 0 || hud.worldTimer <= 0) {
            //set to game over screen
            game.setScreen(new TitleScreen(this.game));
        }

        //everytime man moves, track with game cam only on x axis
        gamecam.position.x = man.b2body.getPosition().x;
        gamecam.update(); //updates cam after every user input
        renderer.setView(gamecam); //renders what our gamecam can see
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
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        man.draw(game.batch);
        game.batch.end();

        //set our batch to now draw what the Hud camera sees
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
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

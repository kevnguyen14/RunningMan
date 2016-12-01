package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Screens.PlayScreen;

/**
 * Created by Kevin on 9/19/2016.
 */
public class Man extends Sprite{
    public World world;
    public static Body b2body;
    public static int playerHealth;
    public float movementSpeed = 0.1f;
    public float jumpPower = 3.5f;
    public boolean isSlowed = false;
    private TextureRegion runnerStand;
    private boolean onFire = false;
    private float timer = 0;
    public boolean hasKey = false;
    public enum State {JUMPING, STANDING, RUNNING};
    public State currentState;
    public State previousState;
    private Animation manRun, manJump;
    private float stateTimer;
    private boolean runningRight;

    public Man(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("man"));
        this.world = world;

        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 0; i < 3; i++)
            frames.add(new TextureRegion(getTexture(), i * 26, 0 ,26, 39));
        manRun = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 4; i < 5; i++)
            frames.add(new TextureRegion(getTexture(), i * 26, 0 ,26, 39));
        manJump = new Animation(0.1f, frames);
        frames.clear();

        defineMan();
        runnerStand = new TextureRegion(getTexture(), 130, 1, 26, 39); //77, 38
        setBounds(0,0,16/RunningMan.PPM, 16 * (114/49) /RunningMan.PPM);
        setRegion(runnerStand);


    }

    public void update(float dt){
        timer += dt;
        setPosition(b2body.getPosition().x - getWidth()/2,(b2body.getPosition().y)- getHeight()/4);

        setRegion(getFrame(dt));

        if (onFire && timer > 1){
            playerHealth--;
            timer = 0;
        }
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState){
            case JUMPING:
                region = manJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = manRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = runnerStand;
                break;
        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight = true;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState(){
        if(b2body.getLinearVelocity().y != 0)
            return State.JUMPING;
        else if(b2body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }

    public void defineMan(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / RunningMan.PPM, 32/ RunningMan.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        playerHealth = 50;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ RunningMan.PPM);

        fdef.filter.categoryBits = RunningMan.ManBit;
        fdef.filter.maskBits = RunningMan.DefaultBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("player");
        // create head
        PolygonShape pshape = new PolygonShape();
        pshape.setAsBox(4 / RunningMan.PPM, 13 / RunningMan.PPM, new Vector2(0, 8/ RunningMan.PPM), 0);
        fdef.shape = pshape;
        b2body.createFixture(fdef).setUserData("foot");

    }

    public void slowTrigger(){
        if (!isSlowed){
            movementSpeed /= 2;
        }else{
            movementSpeed *= 2;
        }
        isSlowed = !isSlowed;
    }

//        public void slowTrigger(){
//        if (isSlowed){
//            movementSpeed = 0.05f;
//        }else{
//            movementSpeed = 0.1f;
//        }
//    }

    public void manReset(){
//        world.destroyBody(b2body);
//        defineMan();
        b2body.setTransform(32 / RunningMan.PPM, 32/ RunningMan.PPM, 0);
    }

    public void SpeedPower(){}

    public void JumpPower(){}

    public void HealthPower(){}

    public void damageTrigger(){onFire = !onFire;}

    public static int getPlayerHealth() {
        return playerHealth;
    }
}

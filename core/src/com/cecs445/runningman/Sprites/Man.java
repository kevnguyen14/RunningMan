package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Screens.PlayScreen;

/**
 * Created by Kevin on 9/19/2016.
 */
public class Man extends Sprite{
    public World world;
    public static Body b2body;
    public int playerHealth;
    public float movementSpeed = 0.1f;
    public float jumpPower = 3.5f;
    public boolean isSlowed = false;
    private TextureRegion runnerStand;
    private boolean onFire = false;
    private float timer = 0;
    public boolean hasKey = false;


    public Man(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("runner-frame"));
        this.world = world;
        defineMan();
        runnerStand = new TextureRegion(getTexture(), 103, 77, 27, 38); //77, 38
        setBounds(0,0,16/RunningMan.PPM, 16 * (114/49) /RunningMan.PPM);
        setRegion(runnerStand);


    }

    public void update(float dt){
        timer += dt;
        setPosition(b2body.getPosition().x - getWidth()/2,(b2body.getPosition().y)- getHeight()/4);
        if (onFire && timer > 1){
            playerHealth--;
            timer = 0;
        }
    }

    public void defineMan(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / RunningMan.PPM, 32/ RunningMan.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        playerHealth = 50;

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        //shape.setAsBox(6/RunningMan.PPM, 8/RunningMan.PPM);
        shape.setRadius(5/ RunningMan.PPM);
//        CircleShape shape = new CircleShape();
//        shape.setRadius(5 / RunningMan.PPM);
        fdef.filter.categoryBits = RunningMan.ManBit;
        fdef.filter.maskBits = RunningMan.DefaultBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData("player");

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

        b2body.setTransform(new Vector2(32 / RunningMan.PPM, 32/ RunningMan.PPM), b2body.getAngle());

    }

    public void SpeedPower(){}

    public void JumpPower(){}

    public void HealthPower(){}

    public void damageTrigger(){onFire = !onFire;}
}

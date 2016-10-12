package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public Body b2body;
    private TextureRegion runnerStand;

    public Man(World world, PlayScreen screen) {
        super(screen.getAtlas().findRegion("runner-frame"));
        this.world = world;
        defineMan();
        runnerStand = new TextureRegion(getTexture(), 103, 77, 27, 38); //77, 38
        setBounds(0,0,16/RunningMan.PPM, 16 * (114/49) /RunningMan.PPM);
        setRegion(runnerStand);

    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth()/2,(b2body.getPosition().y)- getHeight()/4);
    }

    public void defineMan(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / RunningMan.PPM, 32/ RunningMan.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(6/RunningMan.PPM, 16/RunningMan.PPM);
//        CircleShape shape = new CircleShape();
//        shape.setRadius(5 / RunningMan.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);

    }
}

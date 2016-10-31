package com.cecs445.runningman.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Scenes.Hud;
import com.cecs445.runningman.Sprites.Fire;
import com.cecs445.runningman.Sprites.Man;
import com.cecs445.runningman.Sprites.Swamp;
import com.cecs445.runningman.Sprites.Warp;

/**
 * Created by Christian on 10/31/2016.
 */
public class BoxWorldCreator {
    public BoxWorldCreator(World world, TiledMap map, Man man, Hud hud){
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;




        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / RunningMan.PPM, (rect.getY() + rect.getHeight() / 2) / RunningMan.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / RunningMan.PPM, rect.getHeight() / 2 / RunningMan.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for (MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Fire(world, map, rect, man);
        }

        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Swamp(world, map, rect, hud);
        }

//        for (MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect = ((RectangleMapObject) object).getRectangle();
//
//            new Swamp(world, map, rect);
//        }

        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            new Warp(world, map, rect);
        }

    }
}

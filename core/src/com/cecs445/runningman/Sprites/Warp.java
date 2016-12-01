package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Screens.PlayScreen;

/**
 * Created by Christian on 10/31/2016.
 */
public class Warp extends InteractiveTileObject {
    public Man man;
    public static boolean portal;
    public Warp(World world, TiledMap map, Rectangle bounds, Man man) {
        super(world, map, bounds);
        fixture.setUserData(this);
        fixture.setSensor(true);
        this.man = man;
    }

    @Override
    public void onPlayerContact() {
        RunningMan.portal.play();
    }

    @Override
    public void onContactEnd() {

    }
}

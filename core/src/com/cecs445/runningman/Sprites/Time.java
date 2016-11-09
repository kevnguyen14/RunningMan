package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Scenes.Hud;

/**
 * Created by Christian on 11/9/2016.
 */

public class Time extends InteractiveTileObject {
    public Hud hud;
    public Time(World world, TiledMap map, Rectangle bounds, Hud hud) {
        super(world, map, bounds);
        fixture.setUserData(this);
        this.hud = hud;
    }

    @Override
    public void onPlayerContact() {

        hud.worldTimer += 5;
        setCategoryFilter(RunningMan.DestroyedBit);
        getCell().setTile(null);

    }

    @Override
    public void onContactEnd() {

    }
}

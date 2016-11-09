package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.cecs445.runningman.RunningMan;

/**
 * Created by Christian on 11/9/2016.
 */

public class Key extends InteractiveTileObject {
    public Man man;
    public Key(World world, TiledMap map, Rectangle bounds, Man man) {
        super(world, map, bounds);
        fixture.setUserData(this);
        this.man = man;
    }

    @Override
    public void onPlayerContact() {

        man.hasKey = true;
        setCategoryFilter(RunningMan.DestroyedBit);
        getCell().setTile(null);

    }

    @Override
    public void onContactEnd() {

    }
}

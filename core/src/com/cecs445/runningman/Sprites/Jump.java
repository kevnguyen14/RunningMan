package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Christian on 11/9/2016.
 */

public class Jump extends InteractiveTileObject {
    public Man man;
    public Jump(World world, TiledMap map, Rectangle bounds, Man man) {
        super(world, map, bounds);
        this.man = man;
    }

    @Override
    public void onPlayerContact() {
        getCell().setTile(null);

    }

    @Override
    public void onContactEnd() {

    }
}

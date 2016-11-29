package com.cecs445.runningman.Sprites;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.cecs445.runningman.RunningMan;
import com.cecs445.runningman.Screens.LevelCompleteScreen;
import com.cecs445.runningman.Screens.TitleScreen;

/**
 * Created by Christian on 11/9/2016.
 */

public class Door extends InteractiveTileObject{
    public Man man;
    public RunningMan game;

    public Door(World world, TiledMap map, Rectangle bounds, Man man, RunningMan game) {
        super(world, map, bounds);
        fixture.setUserData(this);
        this.man = man;
        this.game = game;
    }

    @Override
    public void onPlayerContact() {
        if (man.hasKey){
            game.setScreen(new LevelCompleteScreen(this.game));
        }
    }

    @Override
    public void onContactEnd() {

    }
}

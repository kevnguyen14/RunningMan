package com.cecs445.runningman.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.cecs445.runningman.Screens.TitleScreen;
import com.cecs445.runningman.Sprites.InteractiveTileObject;

/**
 * Created by Christian on 10/31/2016.
 */
public class worldContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() instanceof InteractiveTileObject){
            ((InteractiveTileObject) fixA.getUserData()).onPlayerContact();
        }
        if(fixB.getUserData() instanceof InteractiveTileObject){
            ((InteractiveTileObject) fixB.getUserData()).onPlayerContact();
        }


    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() instanceof InteractiveTileObject){
            ((InteractiveTileObject) fixA.getUserData()).onContactEnd();
        }
        if(fixB.getUserData() instanceof InteractiveTileObject){
            ((InteractiveTileObject) fixB.getUserData()).onContactEnd();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}

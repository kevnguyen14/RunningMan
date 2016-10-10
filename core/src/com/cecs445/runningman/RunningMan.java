package com.cecs445.runningman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cecs445.runningman.Screens.PlayScreen;
import com.cecs445.runningman.Screens.TitleScreen;

public class RunningMan extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100; //pixels per meter

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new TitleScreen(this)); //setting the playScreen
	}

	@Override
	public void render () {
		super.render();
	}
}

package com.cecs445.runningman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cecs445.runningman.Screens.PlayScreen;
import com.cecs445.runningman.Screens.TitleScreen;

public class RunningMan extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100; //pixels per meter

	public static final short DefaultBit = 1;
	public static final short ManBit = 2;
	public static final short DestroyedBit = 4;

	//creating sound variables
	public static Sound collision, death, jump, backgroundMusic, portal, fire, time, feather, heart, buttonselect, sand, door, key;

	public SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		collision = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/collision.mp3"));
		jump = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/jump.mp3"));
		death = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/death.mp3"));
		backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/bg_music.mp3"));
		portal = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/portal.mp3"));
		fire = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/fire.mp3"));
		time = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/time.mp3"));
		feather = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/feather.mp3"));
		heart = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/heart.mp3"));
		buttonselect = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/buttonselect.mp3"));
		sand = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/sand.mp3"));
		key = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/key.mp3"));
		door = Gdx.audio.newSound(Gdx.files.internal("graphics/sound/door.mp3"));
		setScreen(new TitleScreen(this)); //setting the playScreen
	}

	@Override
	public void render () {
		super.render();
	}
}

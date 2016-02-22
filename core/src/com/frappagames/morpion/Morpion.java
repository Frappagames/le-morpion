package com.frappagames.morpion;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.frappagames.morpion.Screens.MenuScreen;

public class Morpion extends Game {
	public static int width = 720;
	public static int height = 1280;
	public static final String TITLE = "Le Morpion";
	public SpriteBatch batch;
	public TextureAtlas atlas;
	public char whoIsPlaying;
	
	@Override
	public void create () {
		atlas = new TextureAtlas(Gdx.files.internal("LeMorpion.pack"));
		batch = new SpriteBatch();

		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		atlas.dispose();
	}
}

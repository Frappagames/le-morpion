package com.frappagames.morpion.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.frappagames.morpion.Morpion;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Morpion.height / 2;
		config.width = Morpion.width / 2;
		config.title = Morpion.TITLE;
		new LwjglApplication(new Morpion(), config);
	}
}

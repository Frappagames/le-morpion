package com.frappagames.morpion.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.frappagames.morpion.Morpion;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(400, 640);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Morpion();
        }
}
package com.frappagames.morpion.Tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.morpion.Morpion;
import com.frappagames.morpion.Screens.PlayScreen;

/**
 * Cell object of the gameboard
 * Created by jmoreau on 22/02/16.
 */
public class BoxActor extends ImageButton {
    private TextureRegionDrawable cross;
    private TextureRegionDrawable circle;
    public ChangeListener listener;

    public BoxActor(Drawable imageUp, final int number, final Morpion game, final PlayScreen playScreen) {
        super(imageUp);

        cross    = new TextureRegionDrawable(game.atlas.findRegion("cross"));
        circle   = new TextureRegionDrawable(game.atlas.findRegion("circle"));
        listener = new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                play(game.whoIsPlaying);
                playScreen.playerPlay(number - 1);
            }
        };

        this.addListener(listener);
    }

    public void play(int player) {
        if (player == 1) {
            setStyle(new ImageButtonStyle(null, null, null, cross, null, null));
        } else {
            setStyle(new ImageButtonStyle(null, null, null, circle, null, null));
        }
        removeListener(listener);
    }
}

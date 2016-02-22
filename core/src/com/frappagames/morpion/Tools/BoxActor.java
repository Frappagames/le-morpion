package com.frappagames.morpion.Tools;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.morpion.Morpion;
import com.frappagames.morpion.Screens.PlayScreen;

/**
 * Created by jmoreau on 22/02/16.
 */
public class BoxActor extends ImageButton {
    private TextureRegionDrawable cross;
    private TextureRegionDrawable circle;
    private int value = 0;

    public BoxActor(Drawable imageUp, final int number, final Morpion game, final PlayScreen playScreen) {
        super(imageUp);

        cross       = new TextureRegionDrawable(game.atlas.findRegion("cross"));
        circle      = new TextureRegionDrawable(game.atlas.findRegion("circle"));

        this.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                if (game.whoIsPlaying == 1) {
                    setStyle(new ImageButtonStyle(null, null, null, cross, null, null));
                    value = 1;
                } else {
                    setStyle(new ImageButtonStyle(null, null, null, circle, null, null));
                    value = 2;
                }
                playScreen.playerPlay(number - 1);
                removeListener(this);
            }
        });
    }

    public int getValue() {
        return value;
    }
}

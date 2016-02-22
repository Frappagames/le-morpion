package com.frappagames.morpion.Screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.frappagames.morpion.Morpion;

/**
 * Created by jmoreau on 11/01/16.
 */
public class MenuScreen extends com.frappagames.morpion.Tools.GameScreen {
    private Image newLbl;
    private ImageButton onePlayerBtn;
    private ImageButton twoPlayerBtn;
    private Image titleImg;
    private Image decosImg;

    public MenuScreen(final Morpion game) {
        super(game);

        titleImg     = new Image(new TextureRegionDrawable(game.atlas.findRegion("title")));
        newLbl       = new Image(new TextureRegionDrawable(game.atlas.findRegion("newLbl")));
        onePlayerBtn = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("onePlayerBtn")));
        twoPlayerBtn = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("twoPlayerBtn")));
        decosImg     = new Image(new TextureRegionDrawable(game.atlas.findRegion("decos")));

        game.whoIsPlaying = 0;

        VerticalGroup vg = new VerticalGroup();
        vg.addActor(newLbl);
        vg.addActor(onePlayerBtn);
        vg.addActor(twoPlayerBtn);

        table.add(titleImg).top().padTop(169).row();
        table.add(vg).expandY().row();
        table.add(decosImg).bottom().padBottom(50);

        onePlayerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new DifficultyScreen(game));
            }
        });
        twoPlayerBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, 2, 0));
            }
        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}

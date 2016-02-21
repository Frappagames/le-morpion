package com.frappagames.morpion.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.frappagames.morpion.Morpion;

/**
 * Created by jmoreau on 11/01/16.
 */
public class PlayScreen extends GameScreen {
    private Image titleImg;
    private Image gridImg;
    private Image scoreLbl;
    private ImageButton menuBtn;
    private ImageButton newBtn;

    private Image xName;
    private Image oName;
    private Image player1Lbl;
    private Image player2Lbl;
    private Image separator;

    private BitmapFont font;
    private Label score1Lbl;
    private Label score2Lbl;

    public PlayScreen(Morpion game, int nbPlayers, int difficulty) {
        super(game);

        titleImg   = new Image(new TextureRegionDrawable(game.atlas.findRegion("title")));
        gridImg    = new Image(new TextureRegionDrawable(game.atlas.findRegion("grid")));
        scoreLbl   = new Image(new TextureRegionDrawable(game.atlas.findRegion("scoreLbl")));
        menuBtn    = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("menuBtn")));
        newBtn     = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("newBtn")));
        xName      = new Image(new TextureRegionDrawable(game.atlas.findRegion("crossSmall")));
        oName      = new Image(new TextureRegionDrawable(game.atlas.findRegion("circleSmall")));
        player1Lbl = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerOneName")));
        player2Lbl = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerTwoName")));
        separator = new Image(new TextureRegionDrawable(game.atlas.findRegion("scoreSeparator")));

        font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        score1Lbl = new Label("0", labelStyle);
        score1Lbl.setSize(85, 96);
        score1Lbl.setAlignment(Align.center);

        score2Lbl = new Label("0", labelStyle);
        score2Lbl.setSize(85, 96);
        score2Lbl.setAlignment(Align.center);

        table.debug();

        HorizontalGroup hgTop = new HorizontalGroup();
        hgTop.fill();
        hgTop.addActor(menuBtn);
        hgTop.addActor(newBtn);

        Table tableBot    = new Table();
        tableBot.debug();

        VerticalGroup vgXName    = new VerticalGroup();
        vgXName.addActor(xName);
        vgXName.addActor(player1Lbl);
        tableBot.add(vgXName);

        HorizontalGroup hgScores = new HorizontalGroup();
        hgScores.addActor(score1Lbl);
        hgScores.addActor(separator);
        hgScores.addActor(score2Lbl);
        tableBot.add(hgScores).expandX().width(200).top();

        VerticalGroup vgOName    = new VerticalGroup();
        vgOName.addActor(oName);
        vgOName.addActor(player2Lbl);
        tableBot.add(vgOName);


        table.add(titleImg).top().padTop(100).row();
        table.add(hgTop).row();
        table.add(gridImg).row();
        table.add(scoreLbl).padTop(30).row();
        table.add(tableBot).padBottom(70);

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

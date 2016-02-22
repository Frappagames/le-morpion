package com.frappagames.morpion.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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
    private Image player1Name;
    private Image player2Name;
    private Image player1Txt;
    private Image player2Txt;
    private Image separator;
    private Image playTxt;
    private Image winTxt;

    private BitmapFont font;
    private Label score1Lbl;
    private Label score2Lbl;


    public PlayScreen(final Morpion game, final int nbPlayers, final int difficulty) {
        super(game);

        if (game.whoIsOlaying == 1) {
            game.whoIsOlaying = 2;
        } else {
            game.whoIsOlaying = 1;
        }

        titleImg    = new Image(new TextureRegionDrawable(game.atlas.findRegion("title")));
        gridImg     = new Image(new TextureRegionDrawable(game.atlas.findRegion("grid")));
        scoreLbl    = new Image(new TextureRegionDrawable(game.atlas.findRegion("scoreLbl")));
        menuBtn     = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("menuBtn")));
        newBtn      = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("newBtn")));
        xName       = new Image(new TextureRegionDrawable(game.atlas.findRegion("crossSmall")));
        oName       = new Image(new TextureRegionDrawable(game.atlas.findRegion("circleSmall")));
        separator   = new Image(new TextureRegionDrawable(game.atlas.findRegion("scoreSeparator")));
        playTxt     = new Image(new TextureRegionDrawable(game.atlas.findRegion("playLbl")));
        winTxt      = new Image(new TextureRegionDrawable(game.atlas.findRegion("winLbl")));

        if (nbPlayers == 1) {
            player1Txt  = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerLbl")));
            player1Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerName")));

            if (difficulty == 1) {
                player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaEasyName")));
                player2Txt  = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaEasyLbl")));
            } else if (difficulty == 2) {
                player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaNormalName")));
                player2Txt  = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaNormalLbl")));
            } else {
                player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaHardName")));
                player2Txt  = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaHardLbl")));
            }
        } else {
            player1Txt  = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerOneLbl")));
            player1Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerOneName")));
            player2Txt  = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerTwoLbl")));
            player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerTwoName")));
        }

        font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        score1Lbl = new Label("0", labelStyle);
        score1Lbl.setAlignment(Align.center);

        score2Lbl = new Label("0", labelStyle);
        score2Lbl.setAlignment(Align.center);

        VerticalGroup vgXName = new VerticalGroup();
        vgXName.addActor(xName);
        vgXName.addActor(player1Name);

        VerticalGroup vgOName = new VerticalGroup();
        vgOName.addActor(oName);
        vgOName.addActor(player2Name);

        VerticalGroup vgText = new VerticalGroup();
        if (game.whoIsOlaying == 1) {
            vgText.addActor(player1Txt);
        } else {
            vgText.addActor(player2Txt);
        }
        vgText.addActor(playTxt);


        Table hgTop = new Table();
        hgTop.add(menuBtn).width(160);
        hgTop.add(vgText);
        hgTop.add(newBtn).width(160);

        Table tableBot = new Table();
        tableBot.add(vgXName);
        tableBot.add(score1Lbl).width(85).top().padTop(15);
        tableBot.add(separator).top();
        tableBot.add(score2Lbl).width(85).top().padTop(15);
        tableBot.add(vgOName);

        table.add(titleImg).top().padTop(75).row();
        table.add(hgTop).row();
        table.add(gridImg).padTop(25).padBottom(25).row();
        table.add(scoreLbl).row();
        table.add(tableBot).padBottom(75);

        menuBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });

        newBtn.addListener(new ChangeListener() {
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game, nbPlayers, difficulty));
            }
        });


        Table gameBoard = new Table();
        gameBoard.setFillParent(true);
        gameBoard.padTop(35);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("circle")))).pad(25, 30, 25, 30);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("cross")))).pad(25, 30, 25, 30);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("cross")))).pad(25, 30, 25, 30);
        gameBoard.row();
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("cross")))).pad(25, 30, 25, 30);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("circle")))).pad(25, 30, 25, 30);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("cross")))).pad(25, 30, 25, 30);
        gameBoard.row();
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("circle")))).pad(25, 30, 25, 30);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("cross")))).pad(25, 30, 25, 30);
        gameBoard.add(new Image(new TextureRegionDrawable(game.atlas.findRegion("circle")))).pad(25, 30, 25, 30);
        gameBoard.row();

        stage.addActor(gameBoard);
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

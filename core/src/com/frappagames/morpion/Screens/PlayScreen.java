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
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.frappagames.morpion.Morpion;
import com.frappagames.morpion.Tools.BoxActor;
import com.frappagames.morpion.Tools.IA;

/**
 * Play screen and board
 * Created by jmoreau on 11/01/16.
 */
public class PlayScreen extends com.frappagames.morpion.Tools.GameScreen {

    private TextureRegionDrawable player1Txt;
    private TextureRegionDrawable player2Txt;
    private TextureRegionDrawable playTxt;
    private TextureRegionDrawable winTxt;
    private Image playOrWin;
    private Image playerTxt;
    private final Image drawTxt;
    private Label score1Lbl;
    private Label score2Lbl;
    private int[] board;
    private BoxActor[] cells;
    private VerticalGroup vgText;
    private int nbPlayers;
    private int gameDifficulty;

    public boolean canPlay;

    public PlayScreen(final Morpion game, final int nbPlayers, final int difficulty) {
        super(game);
        this.nbPlayers = nbPlayers;
        this.gameDifficulty = difficulty;

        Image titleImg      = new Image(new TextureRegionDrawable(game.atlas.findRegion("title")));
        Image gridImg       = new Image(new TextureRegionDrawable(game.atlas.findRegion("grid")));
        Image scoreLbl      = new Image(new TextureRegionDrawable(game.atlas.findRegion("scoreLbl")));
        ImageButton menuBtn = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("menuBtn")));
        ImageButton newBtn  = new ImageButton(new TextureRegionDrawable(game.atlas.findRegion("newBtn")));
        Image xName         = new Image(new TextureRegionDrawable(game.atlas.findRegion("crossSmall")));
        Image oName         = new Image(new TextureRegionDrawable(game.atlas.findRegion("circleSmall")));
        Image separator     = new Image(new TextureRegionDrawable(game.atlas.findRegion("scoreSeparator")));
        Image player1Name;
        Image player2Name;

        canPlay     = true;
        board       = new int[9];
        cells       = new BoxActor[9];
        playTxt     = new TextureRegionDrawable(game.atlas.findRegion("playLbl"));
        winTxt      = new TextureRegionDrawable(game.atlas.findRegion("winLbl"));
        drawTxt     = new Image(new TextureRegionDrawable(game.atlas.findRegion("drawLbl")));
        playerTxt   = new Image();
        playOrWin   = new Image(playTxt);
        vgText      = new VerticalGroup();

        if (nbPlayers == 1) {
            player1Txt  = new TextureRegionDrawable(game.atlas.findRegion("playerLbl"));
            player1Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerName")));

            if (gameDifficulty == 1) {
                player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaEasyName")));
                player2Txt  = new TextureRegionDrawable(game.atlas.findRegion("iaEasyLbl"));
            } else if (gameDifficulty == 2) {
                player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaNormalName")));
                player2Txt  = new TextureRegionDrawable(game.atlas.findRegion("iaNormalLbl"));
            } else {
                player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("iaHardName")));
                player2Txt  = new TextureRegionDrawable(game.atlas.findRegion("iaHardLbl"));
            }
        } else {
            player1Txt  = new TextureRegionDrawable(game.atlas.findRegion("playerOneLbl"));
            player1Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerOneName")));
            player2Txt  = new TextureRegionDrawable(game.atlas.findRegion("playerTwoLbl"));
            player2Name = new Image(new TextureRegionDrawable(game.atlas.findRegion("playerTwoName")));
        }

        BitmapFont font = new BitmapFont(Gdx.files.internal("font.fnt"), false);
        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);

        score1Lbl = new Label(Integer.toString(game.score1), labelStyle);
        score1Lbl.setAlignment(Align.center);

        score2Lbl = new Label(Integer.toString(game.score2), labelStyle);
        score2Lbl.setAlignment(Align.center);

        VerticalGroup vgXName = new VerticalGroup();
        vgXName.addActor(xName);
        vgXName.addActor(player1Name);

        VerticalGroup vgOName = new VerticalGroup();
        vgOName.addActor(oName);
        vgOName.addActor(player2Name);

        vgText.addActor(playerTxt);
        vgText.addActor(playOrWin);


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
                game.setScreen(new PlayScreen(game, nbPlayers, gameDifficulty));
            }
        });

        BaseDrawable drawable = new BaseDrawable();
        drawable.setMinWidth(150);
        drawable.setMinHeight(150);


        Table gameBoard = new Table();
        gameBoard.setFillParent(true);
        gameBoard.padTop(35);

        for (int i = 1; i <= 9; i++) {
            BoxActor button = new BoxActor(drawable, i, game, this);
            gameBoard.add(button).pad(25, 30, 25, 30);
            cells[i - 1] = button;
            if (i % 3 == 0) gameBoard.row();
        }

        stage.addActor(gameBoard);

        if (game.startPlayer == 2) {
            game.startPlayer  = 1;
            game.whoIsPlaying = 2;
            playerTxt.setDrawable(player2Txt);

            if (nbPlayers == 1) {
                iaPlay();
            }
        } else {
            game.startPlayer  = 2;
            game.whoIsPlaying = 1;
            playerTxt.setDrawable(player1Txt);
        }
    }

    public void changePlayer() {
        if (game.whoIsPlaying == 1) {
            game.whoIsPlaying = 2;
            playerTxt.setDrawable(player2Txt);

            if (nbPlayers == 1) {
                iaPlay();
            }
        } else {
            game.whoIsPlaying = 1;
            playerTxt.setDrawable(player1Txt);
        }
    }

    private void iaPlay() {
        canPlay = false;

        IA computerIA = new IA(game.whoIsPlaying, gameDifficulty);
        int cell = computerIA.getMove(board);

        cells[cell].play(game.whoIsPlaying);

        canPlay = true;

        playerPlay(cell);
    }

    public void playerPlay(int cell) {
        board[cell] = game.whoIsPlaying;
        if (hasPlayerWin()) {
            playOrWin.setDrawable(winTxt);

            if (game.whoIsPlaying == 1) game.score1++;
            else                        game.score2++;

            score1Lbl.setText(Integer.toString(game.score1));
            score2Lbl.setText(Integer.toString(game.score2));
            canPlay = false;
        } else if(gameIsDraw()) {
            vgText.clear();
            vgText.addActor(drawTxt);
            canPlay = false;
        } else {
            playOrWin.setDrawable(playTxt);
            changePlayer();
        }

        if (!canPlay) {
            for (int i = 0; i < 9; i++) {
                cells[i].removeListener(cells[i].listener);
            }
        }
    }

    private boolean gameIsDraw() {
        for (int i = 0; i < 9; i++) {
            if (board[i] == 0) return false;
        }
        return true;
    }

    private boolean hasPlayerWin() {
        for (int i = 0; i < 3; i++) {
            // test lines
            if (board[i * 3] == game.whoIsPlaying
                    && board[i * 3 + 1] == game.whoIsPlaying
                    && board[i * 3 + 2] == game.whoIsPlaying) {
                showWin("line", i);
                return true;
            }

            // test columns
            if (board[i] == game.whoIsPlaying
                    && board[i + 3] == game.whoIsPlaying
                    && board[i + 6] == game.whoIsPlaying) {
                showWin("colomn", i);
                return true;
            }
        }
        // test diagonal DESC
        if (board[0] == game.whoIsPlaying
                && board[4] == game.whoIsPlaying
                && board[8] == game.whoIsPlaying) {
            showWin("diagonal", 0);
            return true;
        }
        // test diagonal ASC
        if (board[2] == game.whoIsPlaying
                && board[4] == game.whoIsPlaying
                && board[6] == game.whoIsPlaying) {
            showWin("diagonal", 1);
            return true;
        }

        return false;
    }

    private void showWin(String line, int number) {
        Image lineImg;

        if (line.equals("line")) {
            lineImg = new Image(new TextureRegionDrawable(game.atlas.findRegion("horizontalBar")));
            lineImg.setPosition((stage.getWidth() - 610) / 2, 812 - (200 * number));
        } else if (line.equals("colomn")) {
            lineImg = new Image(new TextureRegionDrawable(game.atlas.findRegion("verticalBar")));
            lineImg.setPosition(((stage.getWidth() - 610) / 2) + (210 * number) + 85, 320);
        } else {
            if (number == 0) {
                lineImg = new Image(new TextureRegionDrawable(game.atlas.findRegion("oblicBarDesc")));
            } else {
                lineImg = new Image(new TextureRegionDrawable(game.atlas.findRegion("oblicBarAsc")));
            }
            lineImg.setPosition((stage.getWidth() - 610) / 2, 315);
        }

        stage.addActor(lineImg);
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

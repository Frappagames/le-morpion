package com.frappagames.morpion.Tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * IA class for computer move
 * Created by jmoreau on 24/02/16.
 */
public class IA {

    protected int[] gameState;
    protected int difficultyDepth;
    protected int player;
    protected int opponent;

    /**
     * Constructor for IA
     * @param player        Number of current player: 1 or 2
     * @param difficulty    Game difficulty: 1, 2 or 3
     */
    public IA(int player, int difficulty) {
        this.player    = player;
        this.opponent  = (player == 1) ? 2 : 1;
        this.gameState = new int[9];

        switch (difficulty) {
            case 1 :
                difficultyDepth = 0;
                break;
            case 3 :
                difficultyDepth = 2;
                break;
            default :
                difficultyDepth = 1;
        }
    }

    public int getMove(int[] gameState) {
        this.gameState = gameState;
        // If difficulty is easy, play fully random
        if (difficultyDepth == 0) {
            List<Integer> emptyCells = getEmptyCells();
            Random rand = new Random();
            return emptyCells.get(rand.nextInt(emptyCells.size()));

        // else evaluate board and try to win
        } else {
            return alphabeta(difficultyDepth, player, Integer.MIN_VALUE, Integer.MAX_VALUE)[1];
        }
    }

    private int[] alphabeta(int depth, int currentPlayer, int alpha, int beta) {
        List<Integer> emptyCells = getEmptyCells();
        int score;
        int bestMove = -1;

        if (emptyCells.isEmpty() || depth == 0) {
            score = evaluateBoard(difficultyDepth - depth);
            return new int[] {score, bestMove};
        } else {
            for (int move : emptyCells) {
                // try this move for the current "player"
                gameState[move] = currentPlayer;

                // If player is the IA, try to maximize the score
                if (currentPlayer == player) {
                    score = alphabeta(depth - 1, opponent, alpha, beta)[0];
                    if (score > alpha) {
                        alpha    = score;
                        bestMove = move;
                    }

                // If player is the opponent, try to minimize the score
                } else {
                    score = alphabeta(depth - 1, player, alpha, beta)[0];
                    if (score < beta) {
                        beta     = score;
                        bestMove = move;
                    }
                }

                // undo move
                gameState[move] = 0;

                // Alpha-Beta cut-off
                if (alpha >= beta) break;
            }
            return new int[] {(currentPlayer == player) ? alpha : beta, bestMove};
        }
    }

    public int evaluateBoard(int detphModifier) {
        if (hasWin(player))         return 1000 - detphModifier;
        else if (hasWin(opponent))  return detphModifier - 1000;
        else if (isDraw())          return 0;
        else {
            int score = 0;
            // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
            score += evaluateLine(new int[] {0, 1, 2});  // row 0
            score += evaluateLine(new int[] {3, 4, 5});  // row 1
            score += evaluateLine(new int[] {6, 7, 8});  // row 2
            score += evaluateLine(new int[] {0, 3, 6});  // col 0
            score += evaluateLine(new int[] {1, 4, 7});  // col 1
            score += evaluateLine(new int[] {2, 5, 8});  // col 2
            score += evaluateLine(new int[] {0, 4, 8});  // diagonal
            score += evaluateLine(new int[] {6, 4, 2});  // alternate diagonal
            return score;
        }
    }

    /**
     * The heuristic evaluation function for the given line of 3 cells
     *
     * @param cells     The 3 cells to evaluate
     * @return          The score of the line +100, +10 for 2-, 1-in-a-line for computer.
     *                  -100, -10 for 2-, 1-in-a-line for opponent.
     *                  0 otherwise
     */
    private int evaluateLine(int[] cells) {
        int playerPoints    = 0;
        int opponnentPoints = 0;

        for (int cell : cells) {
            if (gameState[cell] == player) {
                playerPoints++;
            } else if (gameState[cell] == opponent) {
                opponnentPoints++;
            }
        }

        if (playerPoints == 1 && opponnentPoints == 1)  return 0;
        if (playerPoints == 1 && opponnentPoints == 0)  return 10;
        if (playerPoints == 2 && opponnentPoints == 0)  return 100;
        if (opponnentPoints == 1 && playerPoints == 0)  return -10;
        if (opponnentPoints == 2 && playerPoints == 0)  return -100;

        return 0;
    }

    /**
     * Check empty cells
     * @return List<Integer> Array of empty cells
     */
    private List<Integer> getEmptyCells() {
        List<Integer> emptyCells = new ArrayList<Integer>();

        for (int i = 0; i < this.gameState.length; i++) {
            if (this.gameState[i] == 0) emptyCells.add(i);
        }
        return emptyCells;
    }

    private boolean hasWin(int player) {
        return ((gameState[0] == player) && (gameState[1] == player) && (gameState[2] == player)
                || (gameState[3] == player) && (gameState[4] == player) && (gameState[5] == player)
                || (gameState[6] == player) && (gameState[7] == player) && (gameState[8] == player)
                || (gameState[0] == player) && (gameState[3] == player) && (gameState[6] == player)
                || (gameState[1] == player) && (gameState[4] == player) && (gameState[7] == player)
                || (gameState[2] == player) && (gameState[5] == player) && (gameState[8] == player)
                || (gameState[0] == player) && (gameState[4] == player) && (gameState[8] == player)
                || (gameState[6] == player) && (gameState[4] == player) && (gameState[2] == player));
    }

    private boolean isDraw() {
        for (int i = 0; i < 9; i++) {
            if (gameState[i] == 0) return false;
        }
        return true;
    }
}

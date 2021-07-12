package com.webcheckers.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Game model class containing the logic of a standard game of Checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class Game {

    // The Red Player.
    private final Player redPlayer;

    // The White Player.
    private final Player whitePlayer;

    // The checkers board.
    private final Board board;

    private boolean isGameOver;

    /**
     * Constructor for Game that contains the Players.
     *
     * @param redPlayer
     *     The red player who makes the first move.
     *
     * @param whitePlayer
     *     The white player who makes a move after the red player.
     */
    public Game(Player redPlayer, Player whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        board = new Board();
    }

    /**
     * A getter method for the red player.
     *
     * @return
     *     The red player.
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * A getter method for the white player.
     *
     * @return
     *     The white player.
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * A getter method for the board.
     *
     * @return
     *     The white player.
     */
    public Board getBoard() {
        return board;
    }

    public void executeMoves(List<Move> moveList)
    {
        board.makeMove((ArrayList<Move>) moveList);

    }

    public void setGameOver(boolean gameOver)
    {
        isGameOver = gameOver;
    }

    public boolean isGameOver()
    {
        return isGameOver;
    }
}

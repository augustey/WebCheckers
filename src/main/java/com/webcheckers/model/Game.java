package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.GameWin;

/**
 * The Game class is responsible for managing the logic of a standard game of Checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class Game {
    //Id index
    private static int idIndex = 0;
    // The Red Player.
    private final Player redPlayer;

    // The White Player.
    private final Player whitePlayer;

    // The checkers board.
    private final Board board;

    // The GameWin object for handling win conditions.
    private GameWin gameWin;

    // The boolean flag for if this game has ended.
    private boolean isGameOver;

    private final String id;

    /**
     * Constructor for Game that contains the Players.
     *
     * @param redPlayer
     *         The red player who makes the first move.
     * @param whitePlayer
     *         The white player who makes a move after the red player.
     */
    public Game(Player redPlayer, Player whitePlayer, GameCenter gameCenter) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.gameWin = new GameWin(gameCenter, this);
        this.board = BoardConfig.newBoard(this.gameWin, redPlayer.getName());
        this.id = "Game" + (++idIndex);
    }

    /**
     * A getter method for the red player.
     *
     * @return The red player.
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * A getter method for the white player.
     *
     * @return The white player.
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * A getter method for the board.
     *
     * @return The white player.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * A getter method for the game win object.
     *
     * @return The game win object.
     */
    public GameWin getGameWin() {
        return gameWin;
    }

    /**
     * A getter for the game id.
     *
     * @return The game id.
     */
    public String getId() {return id;}

    /**
     * A setter method for if the game is over.
     *
     * @param gameOver
     *         A boolean flag for if the game is over.
     */
    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    /**
     * A getter method for the boolean flag if the game is over.
     *
     * @return The boolean flag for it the game is over.
     */
    public boolean isGameOver() {
        return isGameOver;
    }
}
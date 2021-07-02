package com.webcheckers.model;

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

    private final Board board;
    /*
    TODO: Add board class
     */

    /**
     * Constructor for Game that contains the Players.
     *  @param redPlayer
     *     The red player who makes the first move.
     *
     * @param whitePlayer
     * @param board
     */
    public Game(Player redPlayer, Player whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
        this.board = new Board();
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
}

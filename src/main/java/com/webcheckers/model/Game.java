package com.webcheckers.model;

/**
 * Game model class containing the logic of a standard game of Checkers
 * @author Yaqim Auguste (yaa6681@rit.edu)
 */
public class Game {
    private final Player redPlayer;
    private final Player whitePlayer;

    /*
    TODO: Add board class
     */

    /**
     * Constructor for game.
     * @param redPlayer the red player who starts first
     * @param whitePlayer the white player who goes second
     */
    public Game(Player redPlayer, Player whitePlayer) {
        this.redPlayer = redPlayer;
        this.whitePlayer = whitePlayer;
    }

    /**
     * Getter for redPlayer8
     * @return redPlayer
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * getter for whitePlayer
     * @return whitePlayer
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }
}

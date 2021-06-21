package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

/**
 * This class manages a specific game of checkers
 * and offers services to access the game.
 */
public class PlayerService {
    private final Player redPlayer;
    private final Player whitePlayer;
    private final Game game;

    /**
     * Constructor for PlayerService
     * @param game the game of checkers to provide services for.
     */
    public PlayerService(Game game) {
        this.redPlayer = game.getRedPlayer();
        this.whitePlayer = game.getWhitePlayer();
        this.game = game;
    }

    /**
     * getter for redPlayer
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

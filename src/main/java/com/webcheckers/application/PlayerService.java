package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

/**
 * This class manages a specific game of checkers
 * and offers services to access the game.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class PlayerService {

    // The Red Player.
    private final Player redPlayer;

    // The White Player.
    private final Player whitePlayer;

    // The single game between the two players.
    private final Game game;

    /**
     * Constructor for PlayerService.
     *
     * @param game
     *     The game of checkers to provide services for.
     */
    public PlayerService(Game game) {
        this.redPlayer = game.getRedPlayer();
        this.whitePlayer = game.getWhitePlayer();
        this.game = game;
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
     * @return
     *     The white player.
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * A getter method for a board.
     * @return
     *     A board.
     */
    public Board getBoard() {
        //TEMPORARY
        return new Board();
    }

    /**
     * A getter method for the flipped version of the board.
     *
     * @return
     *     A flipped board.
     */
    public Board getBoardFlipped() {
        //TEMPORARY
        Board board = new Board();
        board.flip();
        return board;
    }
}

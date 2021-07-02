package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.model.Row;
import com.webcheckers.model.BoardView;

import java.util.Iterator;

/**
 * This class manages a specific game of checkers
 * and offers services to access the game.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class PlayerService {
    // Active player
    private final Player player;
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
    public PlayerService(Player player, Game game) {
        this.player = player;
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
     * A getter method for the main player.
     * @return
     *     The main player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * A getter method for a board.
     * @return
     *     A board.
     */
    public synchronized BoardView getBoardView() {
        Board board = game.getBoard();
        Iterator<Row> boardView;

        if(player.equals(redPlayer))
            boardView = board.iterator();
        else {
            board.flip();
            boardView = board.iterator();
            board.flip();
        }

        return new BoardView(boardView);
    }
}

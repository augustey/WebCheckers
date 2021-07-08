package com.webcheckers.application;

import com.webcheckers.model.*;

import java.util.*;

/**
 * This class manages a specific game of checkers
 * and offers services to access the game.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
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

    // The moves made by the player during a turn
    private final List<Move> turnMoves;

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
        turnMoves = new ArrayList<>();
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
     * Get the active player color
     *
     * @return
     *      the active color
     */
    public Piece.Color getActivePlayerColor()
    {
        return game.getBoard().getActivePlayerColor();
    }

    /**
     * Get the game
     *
     * @return
     *      the game
     */
    public Game getGame()
    {
        return game;
    }

    /**
     * A getter method for the game
     *
     * @return
     *      the list of moves during the turn
     */
    public List<Move> getTurnMoves()
    {
        return turnMoves;
    }

    /**
     * A getter method for a board.
     * @return
     *     A board.
     */
    public synchronized BoardView getBoardView() {
        Board board = new Board(game.getBoard());
        Iterator<Row> boardView;

        if(player.equals(redPlayer) && getActivePlayerColor() == Piece.Color.RED) {
            boardView = board.iterator();
        }
        else if(player.equals(redPlayer) && getActivePlayerColor() != Piece.Color.RED) {
            board.flip();
            boardView = board.iterator();
        }
        else if(player.equals(whitePlayer) && getActivePlayerColor() == Piece.Color.WHITE) {
            boardView = board.iterator();
        }
        else {
            board.flip();
            boardView = board.iterator();
        }

        return new BoardView(boardView);
    }

    /**
     * Adds a move to the list of moves in the current turn
     *
     * @param move
     *      move that is being made
     */
    public synchronized void addMove(Move move) {
        turnMoves.add(move);
    }

    /**
     * Removes the last made move from the list of moves
     *
     * @return
     *      move that was removed
     */
    public synchronized Move removeMove() {
        if(!turnMoves.isEmpty()) {
            int i = turnMoves.size() - 1;
            return turnMoves.remove(i);
        }
        return null;
    }

    public synchronized void clearMoves() {
        turnMoves.clear();
    }
}

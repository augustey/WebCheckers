package com.webcheckers.application;

import com.webcheckers.model.*;

import java.util.*;

/**
 * The PlayerService class is responsible for managing a player's specific game of checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */
public class PlayerService {

    // The Active player.
    private final Player player;

    // The Red Player.
    private final Player redPlayer;

    // The White Player.
    private final Player whitePlayer;

    // The single game between the two players.
    private final Game game;

    // The PlayerService's turn.
    private final Turn turn;

    /**
     * Constructor for PlayerService.
     *
     * @param player
     *         The player this service belongs to.
     * @param game
     *         The game of checkers to provide services for.
     */
    public PlayerService(Player player, Game game) {
        this.player = player;
        this.redPlayer = game.getRedPlayer();
        this.whitePlayer = game.getWhitePlayer();
        this.game = game;
        this.turn = new Turn(game.getBoard().getMoveType());
    }

    /**
     * A getter method for the red player in the player's game.
     *
     * @return The red player.
     */
    public Player getRedPlayer() {
        return redPlayer;
    }

    /**
     * A getter method for the white player in the player's game.
     *
     * @return The white player.
     */
    public Player getWhitePlayer() {
        return whitePlayer;
    }

    /**
     * A getter method for the main player.
     *
     * @return The main player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * A getter method for the active player color.
     *
     * @return The active player color.
     */
    public Piece.Color getActivePlayerColor() {
        return game.getBoard().getActivePlayerColor();
    }

    /**
     * A getter method for the game.
     *
     * @return The game.
     */
    public Game getGame() {
        return game;
    }

    /**
     * A getter method for list of moves in the turn.
     *
     * @return The list of moves during the turn.
     */
    public List<Move> getTurnMoves() {
        return turn.getMoves();
    }

    /**
     * A getter method for the turn object.
     *
     * @return The Turn.
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * A getter method for the board view.
     *
     * @return A board view object.
     */
    public synchronized BoardView getBoardView() {
        Board board = new Board(game.getBoard());
        Iterator<Row> boardView;

        if (player.equals(redPlayer) && getActivePlayerColor() == Piece.Color.RED) {
            boardView = board.iterator();
        }
        else if (player.equals(redPlayer) && getActivePlayerColor() != Piece.Color.RED) {
            board.flip();
            boardView = board.iterator();
        }
        else if (player.equals(whitePlayer) && getActivePlayerColor() == Piece.Color.WHITE) {
            boardView = board.iterator();
        }
        else {
            board.flip();
            boardView = board.iterator();
        }

        return new BoardView(boardView);
    }

    /**
     * Adds a move to the turn.
     *
     * @param move
     *         A move that is to be made.
     */
    public synchronized boolean addMove(Move move) {
        return turn.addMove(move);
    }

    /**
     * Removes the last move in the turn.
     *
     * @return A move that was removed.
     */
    public synchronized Move removeMove() {
        return turn.removeMove();
    }

    /**
     * A getter method for the id of the game.
     *
     * @return The id of the game.
     */
    public String getGameId() {
        return game.getId();
    }

    /**
     * Clears the list of moves for the turn.
     */
    public synchronized void clearMoves() {
        turn.clearTurnMoves();
    }
}
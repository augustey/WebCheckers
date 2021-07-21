package com.webcheckers.application;

import com.webcheckers.model.*;

import java.util.*;

/**
 * The PlayerService class is responsible for managing a player's specific game of checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
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

    // The list of moves made by a player.
    private final List<Move> turnMoves;

    //Represents the current turn
    private Turn turn;

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
        this.turnMoves = new ArrayList<>();
        this.turn = new Turn(game.getBoard().getMoveType());//TODO make this not shit
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
     * A helper getter method for the active player color.
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
     * A getter method for list of moves during the player's turn.
     *
     * @return The list of moves during the player's turn.
     */
    public List<Move> getTurnMoves() {
//        return turnMoves;
        return turn.getMoves();
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
     * Adds a move to the list of moves in the player's turn.
     *
     * @param move
     *         A move that is to be made.
     */
    public synchronized boolean addMove(Move move) {
        //TODO call addMove
        //System.out.println("turnMoves: " + turnMoves);
        if (turn.addMove(move)) {
            turnMoves.add(move);
            return true;
        }
        return false;
    }

    /**
     * Removes the last made move from the list of moves.
     *
     * @return A move that was removed.
     */
    public synchronized Move removeMove() {
        if (!turnMoves.isEmpty()) {
            //TODO call removeMove
            turn.removeMove();
            int i = turnMoves.size() - 1;
            return turnMoves.remove(i);
        }
        return null;
    }

    /**
     * Clears the list of moves at the end of a player's turn.
     */
    public synchronized void clearMoves() {//TODO have it create a new TURN instead
        turn = new Turn(game.getBoard().getMoveType());//TODO make this not shit
        turnMoves.clear();
    }
}
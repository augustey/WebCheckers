package com.webcheckers.application;

import com.webcheckers.model.*;

import java.util.*;

/**
 * Logs turns for games
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class TurnLogger
{
    private Map<String, List<Board>> turns;
    private Map<Player, Game> reviewing;

    /**
     * Constructor for TurnLogger that initializes the turns Map
     */
    public TurnLogger() {
        turns = new HashMap<>();
        reviewing = new HashMap<>();
    }

    /**
     * Logs a json string of the board
     *
     * @param game
     *          The game whose turns are being logged
     */
    public void logTurn(Game game) {
        Board board = game.getBoard();
        String id = game.getId();

        if(!turns.containsKey(id)) {
            LinkedList<Board> list = new LinkedList<>();
            list.add(new Board());
            turns.put(id, list);
        }

        List<Board> list = turns.get(id);
        Board board1 = list.get(list.size() - 1);
        board1.flip();

        if(!board.toString().equals(board1.toString())) { //No duplicate consecutive turns
            turns.get(id).add(new Board(board));
        }

        board1.flip();
    }

    /**
     * Logs a json string of the board
     *
     * @param game
     *     The game whose turns are being logged
     *
     * @return List of turns
     */
    public List<Board> getTurns(Game game) {
        String id = game.getId();
        return turns.get(id);
    }

    /**
     * A getter method for the board view.
     *
     * @return A board view object.
     */
    public synchronized BoardView getBoardView(Board board) {
        Iterator<Row> boardView;

        if(board.getActivePlayerColor() == Piece.Color.WHITE) board.flip();
        boardView = board.iterator();

        return new BoardView(boardView);
    }

    public synchronized void startReview(Player player, Game game) {
        reviewing.put(player, game);
    }

    public synchronized boolean isReviewing(Player player) {
        return reviewing.containsKey(player);
    }

    public synchronized Game getGame(Player player) {
        return reviewing.get(player);
    }

    public synchronized boolean stopReview(Player player, Game game) {
        return reviewing.remove(player, game);
    }
}

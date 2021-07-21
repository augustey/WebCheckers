package com.webcheckers.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    private Set<Player> reviewing;

    /**
     * Constructor for TurnLogger that initializes the turns Map
     */
    public TurnLogger() {
        turns = new HashMap<>();
        reviewing = new HashSet<>();
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

        turns.get(id).add(new Board(board));
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

        boardView = board.iterator();

        return new BoardView(boardView);
    }

    public synchronized void startReview(Player player) {
        reviewing.add(player);
    }

    public synchronized boolean isReviewing(Player player) {
        return reviewing.contains(player);
    }

    public synchronized boolean stopReview(Player player) {
        return reviewing.remove(player);
    }
}

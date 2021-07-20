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
    private Map<String, List<String>> turns;

    /**
     * Constructor for TurnLogger that initializes the turns Map
     */
    public TurnLogger() {
        turns = new HashMap<>();
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

        Gson gson = new GsonBuilder().create();

        turns.computeIfAbsent(id, k -> new LinkedList<>());

        String boardJSON = gson.toJson(board);
        turns.get(id).add(boardJSON);
    }

    /**
     * Logs a json string of the board
     *
     * @param game
     *     The game whose turns are being logged
     *
     * @return List of turns
     */
    public List<String> getTurns(Game game) {
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
}

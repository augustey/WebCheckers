package com.webcheckers.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Space;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

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
     * @param playerService
     */
    public void logTurn(PlayerService playerService) {
        Game game = playerService.getGame();
        Space[][] board = game.getBoard().getBoard();
        String id = game.getId();

        Gson gson = new GsonBuilder().create();

        turns.computeIfAbsent(id, k -> new LinkedList<>());

        String boardJSON = gson.toJson(board);
        turns.get(id).add(boardJSON);
    }

    public List<String> getTurns(PlayerService playerService) {
        String id = playerService.getGameId();
        return turns.get(id);
    }
}

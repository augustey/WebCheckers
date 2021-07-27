package com.webcheckers.application;

import com.webcheckers.model.*;

import java.util.*;

/**
 * The TurnLogger class is responsible for logging turns during a game.
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class TurnLogger {

    // Map of Turns.
    private Map<String, List<String>> turns;

    // Map of Games for replay.
    private Map<Player, Game> replay;

    /**
     * Constructor for TurnLogger that initializes the Map of turns and games.
     */
    public TurnLogger() {
        this.turns = new HashMap<>();
        this.replay = new HashMap<>();
    }

    /**
     * Log a json string of the board.
     *
     * @param game
     *         The game whose turns are being logged
     */
    public void logTurn(Game game) {
        // Create a copy of the board
        Board board = new Board(game.getBoard());
        String id = game.getId();
        if (!turns.containsKey(id) || turns.get(id) == null) {
            LinkedList<String> list = new LinkedList<>();
            turns.put(id, list);
        }
        List<String> list = turns.get(id);
        list.add(board.toString());
    }

    /**
     * Log a json string of the board.
     *
     * @param game
     *         The game whose turns are being logged.
     *
     * @return The list of turns.
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
        if (board.getActivePlayerColor() == Piece.Color.WHITE) {
            board.flip();
        }
        boardView = board.iterator();
        return new BoardView(boardView);
    }

    /**
     * Adds a player to the reviewing map.
     *
     * @param player
     *         The player that is added to the map.
     * @param game
     *         The game that is added to the map.
     */
    public synchronized void startReplay(Player player, Game game) {
        replay.put(player, game);
    }

    /**
     * Determines if player is currently reviewing a game.
     *
     * @param player
     *         The player that is being checked.
     *
     * @return True if the player is reviewing, else, false.
     */
    public synchronized boolean isReplaying(Player player) {
        return replay.containsKey(player);
    }

    /**
     * A getter method for the game based on the player.
     *
     * @param player
     *         The player that is reviewing the game.
     *
     * @return The game that is being reviewed.
     */
    public synchronized Game getGame(Player player) {
        return replay.get(player);
    }

    /**
     * Removes the player and game from review.
     *
     * @param player
     *         The player that is being removed.
     * @param game
     *         The game that is being removed.
     *
     * @return True if player was successfully removed, else, false.
     */
    public synchronized boolean stopReplay(Player player, Game game) {
        return replay.remove(player, game);
    }

    /**
     * A getter for replay map of games.
     *
     * @return The map of games to replay.
     */
    public Map<Player, Game> getReplay() {
        return replay;
    }

    /**
     * A getter method for turns map.
     *
     * @return The map of turns.
     */
    public Map<String, List<String>> getTurns() {
        return turns;
    }
}
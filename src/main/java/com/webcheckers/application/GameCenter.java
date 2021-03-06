package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The GameCenter class is responsible for managing of games of checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class GameCenter {

    // Error messages.
    public static final Message PLAYER_NULL_MSG = Message.error("That player does not exist.");
    public static final Message PLAYER_IN_GAME_MSG = Message.error("That player is already in a game.");
    public static final Message PLAYER_REVIEWING_GAME_MSG = Message.error("That player is reviewing a game.");

    // Information messages.
    public static final Message CREATE_GAME_SUCCESS = Message.info("New game was created.");

    /**
     * A Map of the active games in the server.
     */
    private final Map<Player, Game> activeGames;

    private final Map<String, Game> completedGames;

    /**
     * Constructor for GameCenter that initializes the active games.
     */
    public GameCenter() {
        this.activeGames = new HashMap<>();
        this.completedGames = new HashMap<>();
    }

    /**
     * Check if player is currently in a game of checkers.
     *
     * @param player
     *         The player to search for.
     *
     * @return True if the specified player is in a game of checkers, else, false.
     */
    public boolean isInGame(Player player) {
        return activeGames.containsKey(player);
    }

    /**
     * Create a new game containing the two specified players. Both players must not be in a game for the game center to
     * create it.
     *
     * @param player
     *         The requesting player.
     * @param opponent
     *         The opponent the requesting player selected.
     * @param turnLogger
     *         The turn logger for the game
     *
     * @return A message detailing the result of the action.
     */
    public Message requestNewGame(Player player, Player opponent, TurnLogger turnLogger) {
        if (opponent == null) {
            return PLAYER_NULL_MSG;
        }
        if (isInGame(opponent)) {
            return PLAYER_IN_GAME_MSG;
        }
        if (turnLogger.isReplaying(opponent)) {
            return PLAYER_REVIEWING_GAME_MSG;
        }

        Game newGame = new Game(player, opponent, this);

        activeGames.put(player, newGame);
        activeGames.put(opponent, newGame);

        turnLogger.logTurn(newGame); //log starting board

        return CREATE_GAME_SUCCESS;
    }

    /**
     * A getter method for the PlayerService object representing the users active game if it exists.
     *
     * @param player
     *         The requesting player.
     *
     * @return A PlayerService object containing the player's game if it exists.
     */
    public PlayerService getPlayerService(Player player) {
        if (!isInGame(player)) {
            return null;
        }

        Game game = activeGames.get(player);
        return new PlayerService(player, game);
    }

    /**
     * Remove the game for each player in active games.
     *
     * @param game
     *         The finished game to get the two players.
     *
     * @return True if the games were removed when both players were in a game, else, false.
     */
    public boolean removeGame(Game game) {
        Player player1 = game.getRedPlayer();
        Player player2 = game.getWhitePlayer();

        if (isInGame(player1) && isInGame(player2)) {
            activeGames.remove(player1);
            activeGames.remove(player2);
            completedGames.put(game.getId(), game);
            return true;
        }
        return false;
    }

    /**
     * A getter method for a completed game.
     *
     * @param id
     *         The Game id as a String to get.
     *
     * @return The requested game.
     */
    public Game getCompletedGame(String id) {
        return completedGames.get(id);
    }

    /**
     * A getter method for the list of games.
     *
     * @return The list of completed games.
     */
    public List<Game> getCompletedGames() {
        List<Game> games = new LinkedList<>();
        for (String key : completedGames.keySet()) {
            games.add(completedGames.get(key));
        }
        return games;
    }

    /**
     * A getter method for the map of completed games.
     *
     * @return The map of completed games.
     */
    public Map<String, Game> getCompletedGamesMap() {
        return completedGames;
    }
}
package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Central class for creating and monitoring games of checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class GameCenter {
    /**
     * A Map of the active games in the server.
     */
    private final Map<Player, Game> activeGames;

    /**
     * Constructor for GameCenter that initializes the active games.
     */
    public GameCenter() {
        this.activeGames = new HashMap<>();
    }

    /**
     * Check if player is currently in a game of checkers.
     *
     * @param player
     *     The player to search for.
     *
     * @return
     *     Whether the specified player is in a game of checkers or not
     */
    public boolean isInGame(Player player) {
        return activeGames.containsKey(player);
    }

    /**
     * Create a new game containing the two specified players.
     * Both players must not be in a game for the game center to create it.
     *
     * @param player
     *     The requesting player.
     *
     * @param opponent
     *     The opponent the requesting player selected.
     *
     * @return
     *     The service object containing the newly created game.
     */
    public PlayerService requestNewGame(Player player, Player opponent) {
        if(isInGame(player) || isInGame(opponent)) {
            return null;
        }
        Board board = new Board();

        Game newGame = new Game(player, opponent, board);

        activeGames.put(player, newGame);
        activeGames.put(opponent, newGame);

        return new PlayerService(newGame);
    }

    /**
     * Get a PlayerService object representing the users active game
     * if it exists.
     *
     * @param player
     *     The requesting player.
     *
     * @return
     *     PlayerService object containing the players game if it exists.
     */
    public PlayerService getPlayerService(Player player) {
        if(!isInGame(player)) {
            return null;
        }

        Game game = activeGames.get(player);
        return new PlayerService(game);
    }
}

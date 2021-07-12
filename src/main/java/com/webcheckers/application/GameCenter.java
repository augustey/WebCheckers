package com.webcheckers.application;

import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Central class for creating and monitoring games of checkers.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class GameCenter {
    // Error messages
    public static final Message PLAYER_NULL_MSG = Message.error("That player does not exist.");
    public static final Message PLAYER_IN_GAME_MSG = Message.error("That player is already in a game.");
    public static final Message CREATE_GAME_SUCCESS = Message.info("New game was created.");

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
    public Message requestNewGame(Player player, Player opponent) {
        if(opponent == null) {
            return PLAYER_NULL_MSG;
        }
        if(isInGame(opponent)) {
            return PLAYER_IN_GAME_MSG;
        }

        Game newGame = new Game(player, opponent, this);

        activeGames.put(player, newGame);
        activeGames.put(opponent, newGame);

        return CREATE_GAME_SUCCESS;
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
        return new PlayerService(player, game);
    }

    public boolean removeGame(Game game) {
        Player player1 = game.getRedPlayer();
        Player player2 = game.getWhitePlayer();

        if(isInGame(player1) && isInGame(player2)) {
            activeGames.remove(player1);
            activeGames.remove(player2);
            return true;
        }
        return false;
    }
}

package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Central class for creating and monitoring games of checkers.
 * @author Yaqim Auguste (yaa6681)
 */
public class GameCenter {
    //Keep track of users and the games they're in.
    private final Map<Player, Game> activeGames;

    /**
     * Constructor for GameCenter
     */
    public GameCenter() {
        this.activeGames = new HashMap<>();
    }

    public boolean isInGame(Player player) {
        return activeGames.containsKey(player);
    }

    public PlayerService requestNewGame(Player player, Player opponent) {
        //If any of the players are in a game, don't create a new game
        //and return null.
        if(isInGame(player) || isInGame(opponent)) {
            return null;
        }

        //Create a new game with the requesting player as red
        //and the other player as white.
        Game newGame = new Game(player, opponent);

        //Add the two players to the map of games.
        activeGames.put(player, newGame);
        activeGames.put(opponent, newGame);

        //Return a service object representing the game.
        return new PlayerService(newGame);
    }

    /**
     * Get a PlayerService object representing the users active game
     * if it exists.
     * @param player the requesting player
     * @return PlayerService object containing the players game if it exists.
     */
    public PlayerService getPlayerService(Player player) {
        //If the requesting player is not in the game, return null.
        if(!isInGame(player)) {
            return null;
        }

        //Get the game the player is in and return a service object representing it.
        Game game = activeGames.get(player);
        return new PlayerService(game);
    }
}

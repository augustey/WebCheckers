package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * The PlayerLobby class is responsible for holding sign-in information
 * and verifying unique and alphanumeric player names.
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class PlayerLobby {

    /**
     * Enum that keeps track of the specific sign in result.
     */
    public enum SignInResult {NON_UNIQUE, NON_ALPHANUMERIC, SUCCESS}

    /**
     * Set of unique Players.
     */
    private final Set<Player> playerSet;

    /**
     * Constructor for PlayerLobby that initializes the player set.
     */
    public PlayerLobby() {
        this.playerSet = new HashSet<>();
    }

    /**
     * Given a name, signs the player in and returns the proper
     * SignInResult enum response.
     *
     * @param player The player that was entered.
     * @return Whether the player sign-in was a success, or if the player entered
     * a non-unique or non alphanumeric name.
     */
    public synchronized SignInResult signIn(Player player) {
        if (!verifyAlphanumeric(player.getName())) {
            return SignInResult.NON_ALPHANUMERIC;
        }
        if (playerSet.contains(player)) {
            return SignInResult.NON_UNIQUE;
        }
        playerSet.add(player);

        return SignInResult.SUCCESS;
    }

    /**
     * Removes the player from the set of signed in users.
     *
     * @param player The player that is signing out.
     */
    public synchronized void signOut(Player player) {
        playerSet.remove(player);
    }

    /**
     * A getter method for the set of unique players.
     *
     * @return The playerSet.
     */
    public synchronized Set<Player> getPlayerSet() {
        return playerSet;
    }

    /**
     * Verifies if a given string is alphanumeric.
     *
     * @param str String that is being checked.
     * @return True if the string contains a letter or a digit.
     */
    private boolean verifyAlphanumeric(String str) {
        char[] a = str.toCharArray();
        if (a.length == 0) {
            return false;
        }
        boolean containsAlphaNum = false;
        for (Character c : a) {
            if (Character.isLetterOrDigit(c)) {
                containsAlphaNum = true;
            }
            if (!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
                return false;
            }
            if (!containsAlphaNum && Character.isSpaceChar(c)) {
                return false;
            }
        }
        return containsAlphaNum;
    }

    /**
     * Get a specific player from the player lobby by name.
     *
     * @param name The name of the Player to find.
     * @return The Player object corresponding with name.
     */
    public Player getPlayer(String name) {
        for (Player player : playerSet) {
            if (player.getName().equals(name))
                return player;
        }
        return null;
    }
}
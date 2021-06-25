package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Holds sign-in information and verifies unique names and alphanumeric names
 * @author <a href = 'mailto:jrl9984@rit.edu'> Jim Logan</a>
 */
public class PlayerLobby {
    /**
     * Enum that keeps track of the specific sign in result
     */
    public enum SignInResult {NON_UNIQUE, NON_ALPHANUMERIC, SUCCESS}

    private final Set<Player> playerSet; //Set that holds unique Players

    /**
     * Creates a new PlayerLobby that initializes the player set
     */
    public PlayerLobby() {
        playerSet = new HashSet<>();
    }

    /**
     * Given a name, signs the player in and returns the proper
     * SignInResult enum response
     * @param player - player that was entered
     * @return the signin result
     */
    public synchronized SignInResult signIn(Player player) {
        if(!verifyAlphanumeric(player.getName())) {
            return SignInResult.NON_ALPHANUMERIC;
        }
        if(playerSet.contains(player)) {
            return SignInResult.NON_UNIQUE;
        }
        playerSet.add(player);

        return SignInResult.SUCCESS;
    }

    /**
     * Removes the player from the set of signed in users
     * @param player - player that is signing out
     */
    public synchronized void signOut(Player player) {
        if(playerSet.contains(player)) {
            playerSet.remove(player);
        }
    }

    /**
     * getter for the playerSet
     * @return playerSet
     */
    public synchronized Set<Player> getPlayerSet() {
        return playerSet;
    }

    /**
     * Verifies if a given string is aplhanumeric
     * @param str - String that is being checked
     * @return true if it contains a letter of digit
     */
    private boolean verifyAlphanumeric(String str) {
        char[] a = str.toCharArray();
        if(a.length == 0) {
            return false;
        }

        boolean containsAplhaNum = false;
        for(Character c : a) {
            if(Character.isLetterOrDigit(c)) {
                containsAplhaNum = true;
            }
            if(!Character.isLetterOrDigit(c) && !Character.isSpaceChar(c)) {
                return false;
            }
            if(!containsAplhaNum && Character.isSpaceChar(c)) {
                return false;
            }
        }
        return containsAplhaNum;
    }

    /**
     * Get a specific player from the player lobby by name.
     * @param name the name of the Player to find.
     * @return the Player object corresponding with name.
     */
    public Player getPlayer(String name) {
        for(Player player: playerSet) {
            if(player.getName().equals(name))
                return player;
        }

        return null;
    }
}

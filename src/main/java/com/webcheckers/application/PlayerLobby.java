package com.webcheckers.application;

import com.webcheckers.model.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * Holds sign-in information and verifies unique names and alphanumeric names
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'> Jim Logan</a>
 */
public class PlayerLobby
{
    /**
     * Enum that keeps track of the specific sign in result
     */
    public enum SignInResult {NON_UNIQUE, NON_ALPHANUMERIC, SUCCESS}

    private final Set<Player> playerSet; //Set that holds unique Players

    /**
     * Creates a new PlayerLobby that initializes the player set
     */
    public PlayerLobby()
    {
        playerSet = new HashSet<>();
    }

    /**
     * Given a name, signs the player in and returnes the proper
     * SignInResult enum response
     * @param name - name that was entered
     * @return the signin result
     */
    public SignInResult signIn(String name)
    {
        if(verifyAlphanumeric(name))
        {
            return SignInResult.NON_ALPHANUMERIC;
        }

        Player player = new Player(name);

        if(playerSet.contains(player))
        {
            return SignInResult.NON_UNIQUE;
        }

        playerSet.add(player);

        return SignInResult.SUCCESS;
    }

    /**
     * Verifies if a given string is aplhanumeric
     * @param str - String that is being checked
     * @return true if it contains a letter of digit
     */
    private boolean verifyAlphanumeric(String str)
    {
        char[] a = str.toCharArray();
        for(Character c : a)
        {
            if(Character.isLetterOrDigit(c))
            {
                return true;
            }
        }
        return false;
    }
}

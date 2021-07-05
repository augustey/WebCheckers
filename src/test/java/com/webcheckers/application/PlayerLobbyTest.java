package com.webcheckers.application;

import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * The testing suite for the {@link PlayerLobby} component.
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("Application-tier")
public class PlayerLobbyTest
{
    //Component under Test
    private PlayerLobby CuT;

    private static final String NAME = "name";
    private static final String NAME_2 = "name2";
    private static final String SYMBOL = "@$#%$^";
    private static final String MIXED = "abc2$";
    private static final String SPACE = "   ";
    private static final String SPACE_MIX = "Hello There";
    private static final String EMPTY = "";

    //friendly objects
    private Player valid;
    private Player valid2;
    private Player symbol;
    private Player mixed;
    private Player space;
    private Player spaceMix;
    private Player empty;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void setUp()
    {
        valid = new Player(NAME);
        valid2 = new Player(NAME_2);
        symbol = new Player(SYMBOL);
        mixed = new Player(MIXED);
        space = new Player(SPACE);
        spaceMix = new Player(SPACE_MIX);
        empty = new Player(EMPTY);

        CuT = new PlayerLobby();
    }

    /**
     * Test for getPlayerSet()
     */
    @Test
    public void test_getPlayerSet()
    {
        Set<Player> expected = new HashSet<>();

        Set<Player> actual = CuT.getPlayerSet();

        assertEquals(expected, actual);
    }

    /**
     * Test for a valid sign in
     */
    @Test
    public void test_signIn_valid()
    {
        int expectedSize = 1;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.SUCCESS;

        PlayerLobby.SignInResult actual = CuT.signIn(valid);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * Test for multiple valid sign ins
     */
    @Test
    public void test_signIn_multiple()
    {
        int expectedSize = 2;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.SUCCESS;
        CuT.signIn(valid);

        PlayerLobby.SignInResult actual = CuT.signIn(valid2);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * Test for non unique sign ins
     */
    @Test
    public void test_signIn_non_unique()
    {
        int expectedSize = 1;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.NON_UNIQUE;
        CuT.signIn(valid);

        PlayerLobby.SignInResult actual = CuT.signIn(valid);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * Test for non alphanumeric sign ins
     */
    @Test
    public void test_signIn_symbols()
    {
        int expectedSize = 0;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.NON_ALPHANUMERIC;

        PlayerLobby.SignInResult actual = CuT.signIn(symbol);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * test for mixed names
     */
    @Test
    public void test_signIn_symbols_mixed()
    {
        int expectedSize = 0;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.NON_ALPHANUMERIC;

        PlayerLobby.SignInResult actual = CuT.signIn(mixed);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * test for names that are just spaces
     */
    @Test
    public void test_signIn_space()
    {
        int expectedSize = 0;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.NON_ALPHANUMERIC;

        PlayerLobby.SignInResult actual = CuT.signIn(space);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * test for empty sign ins
     */
    @Test
    public void test_signIn_empty()
    {
        int expectedSize = 0;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.NON_ALPHANUMERIC;

        PlayerLobby.SignInResult actual = CuT.signIn(empty);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * test for spaces in valid names
     */
    @Test
    public void test_signIn_space_mixed()
    {
        int expectedSize = 1;
        PlayerLobby.SignInResult expected = PlayerLobby.SignInResult.SUCCESS;

        PlayerLobby.SignInResult actual = CuT.signIn(spaceMix);

        int actualSize = CuT.getPlayerSet().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
    }

    /**
     * test for signing out
     */
    @Test
    public void test_signOut()
    {
        int expectedSize = 0;
        CuT.signIn(spaceMix);

        CuT.signOut(spaceMix);

        int actualSize = CuT.getPlayerSet().size();
        assertSame(expectedSize, actualSize);
    }

    /**
     * test for multiple sign outs
     */
    @Test
    public void test_signOut_multiple()
    {
        int expectedSize = 1;
        CuT.signIn(spaceMix);
        CuT.signIn(valid);

        CuT.signOut(spaceMix);

        int actualSize = CuT.getPlayerSet().size();
        assertSame(expectedSize, actualSize);
    }

    /**
     * Test for getPlayer
     */
    @Test
    public void test_getPlayer()
    {
        CuT.signIn(valid);

        Player actual = CuT.getPlayer("name");

        assertEquals(valid, actual);
    }


}

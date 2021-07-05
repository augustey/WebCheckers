package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The testing suite for the {@link Player} component.
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */

@Tag("Model-tier")
public class PlayerTest
{
    //component under test
    private Player CuT;

    private final static String NAME = "name"; //name

    /**
     * Setup for all tests
     */
    @BeforeEach
    public void setUp()
    {
        CuT = new Player(NAME);
    }

    /**
     * Test for getName()
     */
    @Test
    public void test_getName()
    {
        String expected = "name";

        String actual = CuT.getName();

        assertEquals(expected, actual);
    }

    /**
     * Test for eqivilancy
     */
    @Test
    public void test_equals()
    {
        Player player2 = new Player(NAME);

        boolean result = CuT.equals(player2);

        assertTrue(result);
    }

    /**
     * Test for unequivlancy
     */
    @Test
    public void test_not_equal()
    {
        Player player2 = new Player("name1");
        Player player = null;
        Game game = new Game(CuT, player);

        boolean result = CuT.equals(player2);
        boolean result2 = CuT.equals(player);
        boolean result3 = CuT.equals(game);

        assertFalse(result);
        assertFalse(result2);
        assertFalse(result3);
    }

    /**
     * Test for hashCode
     */
    @Test
    public void test_hash()
    {
        int expected = Objects.hash(NAME);

        int actual = CuT.hashCode();

        assertEquals(expected, actual);
    }

    /**
     * Test for toString
     */
    @Test
    public void test_toString()
    {
        String expected = NAME;

        String actual = CuT.toString();

        assertEquals(expected, actual);
    }

}

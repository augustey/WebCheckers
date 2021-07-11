package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The testing suite for the {@link GameWin} component.
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("Application-tier")
public class GameWinTest
{
    private GameWin CuT;

    //friendly objects
    private GameCenter gameCenter;
    private Player player1;
    private Player player2;
    private PlayerService playerService;
    private Game game;

    /**
     * Setup for tests
     */
    @BeforeEach
    public void setUp()
    {
        player1 = new Player("1");
        player2 = new Player("2");
        gameCenter = new GameCenter();
        gameCenter.requestNewGame(player1, player2);
        playerService = gameCenter.getPlayerService(player1);
        game = playerService.getGame();

        CuT = new GameWin(gameCenter, game);
    }

    /**
     * Test for isGameOver
     */
    @Test
    public void test_isGameOver()
    {
        boolean result = CuT.isGameOver();

        assertFalse(result);
        assertFalse(game.isGameOver());
    }

    /**
     * Test for getGameMessage
     */
    @Test
    public void test_getGameOverMessage()
    {
        String expected = GameWin.NOT_OVER;

        String actual = CuT.getGameOverMessage();

        assertEquals(expected, actual);
    }

    /**
     * Test to trigger an end to a game
     */
    @Test
    public void test_triggerGameOver()
    {
        String expected = GameWin.NO_PIECES;

        boolean result = CuT.triggerGameOver(GameWin.NO_PIECES);

        assertTrue(game.isGameOver());
        assertTrue(CuT.isGameOver());
        assertEquals(expected, CuT.getGameOverMessage());
        assertTrue(result);
    }


}

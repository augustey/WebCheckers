package com.webcheckers.model;

import com.webcheckers.application.GameCenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Testing suite for Game
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
@Tag("Model-tier")
public class GameTest {
    private Game CuT;
    private Player player;
    private Player opponent;
    private GameCenter gameCenter;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void testSetup() {
        player = new Player("Player");
        opponent = new Player("Opponent");
        gameCenter = new GameCenter();
    }

    /**
     * Test the id for each game
     */
    @Test
    public void test_game_id() {
        int start = Integer.parseInt((new Game(player, opponent, gameCenter)).getId().substring(4));

        for(int i = start; i < start + 5; i++) {
            CuT = new Game(player, opponent, gameCenter);

            assertEquals("Game" + (i + 1), CuT.getId());
        }
    }

    /**
     * Test getters returns right player when player is red
     */
    @Test
    public void test_get_red_player() {
        CuT = new Game(player, opponent, gameCenter);
        Player red = CuT.getRedPlayer();
        Player white = CuT.getWhitePlayer();

        assertEquals(player, red);
        assertEquals(opponent, white);
    }

    /**
     * Test getters returns right player when opponent is red
     */
    @Test
    public void test_get_white_player() {
        CuT = new Game(opponent, player, gameCenter);
        Player red = CuT.getRedPlayer();
        Player white = CuT.getWhitePlayer();

        assertEquals(opponent, red);
        assertEquals(player, white);
    }

    /**
     * Test if board is configured correctly at beginning.
     */
    @Test
    public void test_board_pieces() {
        CuT = new Game(player, opponent, gameCenter);
        assertEquals(12, CuT.getGameWin().checkPieceCount(CuT.getBoard(), Piece.Color.RED));
        assertEquals(12, CuT.getGameWin().checkPieceCount(CuT.getBoard(), Piece.Color.WHITE));
    }

    /**
     * Test if game over works properly
     */
    @Test
    public void test_game_over() {
        CuT = new Game(player, opponent, gameCenter);

        assertFalse(CuT.isGameOver());
        CuT.getGameWin().triggerGameOver();
        assertTrue(CuT.isGameOver());
    }
}

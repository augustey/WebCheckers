package com.webcheckers.application;

import com.webcheckers.model.*;
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
@Tag("feature-win")
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
        String actual = CuT.getGameOverMessage();

        assertNull(actual);
    }

    /**
     * Test to trigger an end to a game
     */
    @Test
    public void test_triggerGameOver()
    {
        String expected = "over";

        boolean result = CuT.triggerGameOver("over");

        assertTrue(game.isGameOver());
        assertTrue(CuT.isGameOver());
        assertEquals(expected, CuT.getGameOverMessage());
        assertTrue(result);
    }

    /**
     * Test for checkPieceCount
     */
    @Test
    public void test_checkPieceCount()
    {
        int expected = 12;

        int actual = CuT.checkPieceCount(game.getBoard(), Piece.Color.RED);

        assertSame(expected, actual);
    }

    /**
     * test for if checkPieceGameOver is false
     */
    @Test
    public void test_checkPieceGameOver_false()
    {
        assertFalse(CuT.checkPieceGameOver(game.getBoard(), Piece.Color.RED));
    }

    /**
     * Test for checkPieceGameOver if the player is red
     */
    @Test
    public void test_checkPieceGameOver_red()
    {
        String expected = "2 has captured all pieces!";
        clearColor(game.getBoard(), Piece.Color.RED);

        boolean result = CuT.checkPieceGameOver(game.getBoard(), Piece.Color.RED);
        String actual = CuT.getGameOverMessage();

        assertTrue(result);
        assertEquals(expected, actual);
    }

    /**
     * Test for checkPieceGameOver if the player is white
     */
    @Test
    public void test_checkPieceGameOver_white()
    {
        String expected = "1 has captured all pieces!";
        clearColor(game.getBoard(), Piece.Color.WHITE);

        boolean result = CuT.checkPieceGameOver(game.getBoard(), Piece.Color.WHITE);
        String actual = CuT.getGameOverMessage();

        assertTrue(result);
        assertEquals(expected, actual);
    }

    /**
     * Test for checkBlockedGameOver if the player is red
     */
    @Test
    public void test_checkBlockedGameOver_red()
    {
        String expected = "1 has all of their pieces blocked!";

        boolean result = CuT.checkBlockedGameOver(Piece.Color.RED);
        String actual = CuT.getGameOverMessage();

        assertTrue(result);
        assertEquals(expected, actual);
    }

    /**
     * Test for checkBlockedGameOver if the player is white
     */
    @Test
    public void test_checkBlockedGameOver_white()
    {
        String expected = "2 has all of their pieces blocked!";

        boolean result = CuT.checkBlockedGameOver(Piece.Color.WHITE);
        String actual = CuT.getGameOverMessage();

        assertTrue(result);
        assertEquals(expected, actual);
    }

    /**
     * Test for checkResignGameOver
     */
    @Test
    public void test_checkResignGameOver() {
        String expected = "1 has resigned.";

        boolean result = CuT.checkResignGameOver(player1);
        String actual = CuT.getGameOverMessage();

        assertTrue(result);
        assertEquals(expected, actual);
    }


    /**
     * Helper method to clear board of a color
     * @param board
     * @param color
     */
    private void clearColor(Board board, Piece.Color color) {
        for(int i = 0; i < board.BOARD_DIM; i++) {
            for(int j = 0; j < board.BOARD_DIM; j++) {
                Piece piece = board.getBoard()[i][j].getPiece();
                try {
                    if(piece.getColor() == color) {
                        board.getBoard()[i][j].setPiece(null);
                    }
                } catch (NullPointerException npe) {
                    continue;
                }
            }
        }
    }
}

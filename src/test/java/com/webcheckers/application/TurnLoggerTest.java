package com.webcheckers.application;

import com.webcheckers.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing suite for TurnLogger
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("Application-tier")
@Tag("replay")
public class TurnLoggerTest
{
    private TurnLogger CuT;

    //friendly objects
    private Player player;
    private Player opponent;
    private GameCenter gameCenter;
    private PlayerService playerService;
    private Game game;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void SetUp() {

        gameCenter = new GameCenter();
        player = new Player("Player");
        opponent = new Player("Opponent");

        CuT = new TurnLogger();

        gameCenter.requestNewGame(player, opponent, CuT);
        playerService = gameCenter.getPlayerService(player);
        game = playerService.getGame();
    }

    /**
     * Test for when getTurns is null
     */
    @Test
    public void test_getTurns_null() {
        for(String s : CuT.getTurns().keySet()) {
            if(CuT.getTurns().get(s) != null) {
                CuT.getTurns().get(s).clear();
            }
            CuT.getTurns().put(s, null);
        }

        List<String> turns = CuT.getTurns(game);

        assertNull(turns);
    }

    /**
     * Test for getting turns
     */
    @Test
    public void test_getTurns() {
        List<Board> expected = new LinkedList<>();
        expected.add(game.getBoard());
        expected.add(game.getBoard());

        List<String> actual = CuT.getTurns(game);

        for(int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).toString(), actual.get(i).toString());
        }
    }

    /**
     * Test for logTurn
     */
    @Test
    public void test_logTurn() {
        int expectedSize = 2;

        CuT.logTurn(game);

        int actualSize = CuT.getTurns(game).size();

        assertEquals(expectedSize, actualSize);
    }

    /**
     * Test for getBoardView
     */
    @Test
    public void test_getBoardView() {
        BoardView expected = new BoardView(game.getBoard().iterator());

        BoardView actual = playerService.getBoardView();

        assertEquals(expected, actual);
    }

    /**
     * Test for getBoardView when the board needs to be flipped
     */
    @Test
    public void test_getBoardView_flip() {
        game.getBoard().flip();
        game.getBoard().setActivePlayerColor(Piece.Color.WHITE);
        BoardView expected = playerService.getBoardView();

        BoardView actual = CuT.getBoardView(game.getBoard());

        assertEquals(expected, actual);
    }

    /**
     * Test for startReview
     */
    @Test
    public void test_startReview() {
        int expectedSize = 1;

        CuT.startReplay(player, game);

        int actualSize = CuT.getReplay().size();

        assertEquals(actualSize, expectedSize);
    }

    /**
     * Test for isReviewing
     */
    @Test
    public void test_isReviwing() {
        CuT.startReplay(player, game);

        boolean result1 = CuT.isReplaying(player);
        boolean result2 = CuT.isReplaying(opponent);

        assertTrue(result1);
        assertFalse(result2);
    }

    /**
     * Test for stopReview
     */
    @Test
    public void test_stopReview() {
        int expectedSize = 0;
        CuT.startReplay(player, game);

        boolean result = CuT.stopReplay(player, game);

        int actualSize = CuT.getReplay().size();

        assertEquals(actualSize, expectedSize);
        assertTrue(result);
    }
}

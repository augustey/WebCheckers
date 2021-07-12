package com.webcheckers.application;

import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Testing suite for GameCenter
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
@Tag("Application-tier")
public class GameCenterTest {
    private GameCenter CuT;
    private Player player;
    private Player opponent;
    private Player otherPlayer;

    /**
     * Setup for each test
     */
    @BeforeEach
    public void testSetup() {
        CuT = new GameCenter();
        player = new Player("Player");
        opponent = new Player("Opponent");
        otherPlayer = new Player("OtherPlayer");
    }

    /**
     * Test message upon successful creation of a game.
     */
    @Test
    public void test_create_game_message1() {
        Message result = CuT.requestNewGame(player, opponent);

        assertSame(result, GameCenter.CREATE_GAME_SUCCESS);
    }

    /**
     * Test message when attempting to start a game with a player
     * that's already in a game.
     */
    @Test
    public void test_create_game_message2() {
        CuT.requestNewGame(player, opponent);
        Message result = CuT.requestNewGame(otherPlayer, player);

        assertSame(result, GameCenter.PLAYER_IN_GAME_MSG);
    }

    /**
     * Test message when trying to start a game with a null player
     */
    @Test
    public void test_create_game_message3() {
        Message result = CuT.requestNewGame(player, null);

        assertSame(result, GameCenter.PLAYER_NULL_MSG);
    }

    /**
     * Test if isGame behaves as expected.
     */
    @Test
    public void test_inGame() {
        CuT.requestNewGame(player, opponent);

        assertTrue(CuT.isInGame(player));
        assertTrue(CuT.isInGame(opponent));
        assertFalse(CuT.isInGame(otherPlayer));
        assertFalse(CuT.isInGame(null));
    }

    /**
     * Test if the PlayerService objects are created with the correct values
     */
    @Test
    public void test_player_service() {
        CuT.requestNewGame(player, opponent);
        PlayerService playerSvcP = CuT.getPlayerService(player);
        PlayerService playerSvcO = CuT.getPlayerService(opponent);
        PlayerService playerSvcN = CuT.getPlayerService(otherPlayer);
        PlayerService playerSvcW = CuT.getPlayerService(null);

        assertSame(player, playerSvcP.getPlayer());
        assertSame(player, playerSvcP.getRedPlayer());
        assertSame(opponent, playerSvcO.getPlayer());
        assertSame(opponent, playerSvcP.getWhitePlayer());
        assertNull(playerSvcW);
        assertNull(playerSvcN);
    }

    /**
     * Test to remove games from active players
     */
    @Test
    public void test_remove_game() {
        CuT.requestNewGame(player, opponent);
        Game game = new Game(otherPlayer, opponent, CuT);
        Game game2 = new Game(opponent, otherPlayer, CuT);
        Game game3 = new Game(opponent, new Player("f"), CuT);
        PlayerService playerService = CuT.getPlayerService(player);

        assertTrue(CuT.removeGame(playerService.getGame()));
        assertFalse(CuT.removeGame(game));
        assertFalse(CuT.removeGame(game2));
        assertFalse(CuT.removeGame(game3));
    }
}

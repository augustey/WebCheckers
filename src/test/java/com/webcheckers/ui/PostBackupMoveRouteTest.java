package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PostBackupMoveRouteTest
{
    private PostBackupMoveRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private Gson gson;

    //friendly objects
    private PlayerService playerService;
    private Game game;
    private Player player;
    private Player opponent;
    private Move move;
    //Position

    /**
     * Setup for each test
     */
    @BeforeEach
    public void setup()
    {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);

        player = new Player("Player");
        opponent = new Player("Opponent");
        game = new Game(player, opponent);
        playerService = new PlayerService(player, game);

        gson = new GsonBuilder().create();

        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);

        CuT = new PostBackupMoveRoute();
    }

    /**
     * Test for valid move
     */
    @Test
    public void test_valid()
    {
        move = new Move(new Position(5, 0), new Position(4, 1));
        playerService.addMove(move);
        String expected = "{\"text\":\"Move was reversed successfully!\",\"type\":\"INFO\"}";
        int expectedSize = 0;

        String actual = (String) CuT.handle(request, response);

        int actualSize = playerService.getTurnMoves().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
        assertThrows(IndexOutOfBoundsException.class, () -> {playerService.getTurnMoves().get(0);});
    }

    /**
     * Test for valid move
     */
    @Test
    public void test_valid_multiple()
    {
        move = new Move(new Position(5, 0), new Position(4, 1));
        playerService.addMove(move);
        Move move1 = new Move(new Position(5, 2), new Position(4, 3));
        playerService.addMove(move1);
        String expected = "{\"text\":\"Move was reversed successfully!\",\"type\":\"INFO\"}";
        int expectedSize = 1;

        String actual = (String) CuT.handle(request, response);

        int actualSize = playerService.getTurnMoves().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
        assertEquals(move, playerService.getTurnMoves().get(0));
    }

    /**
     * Test for invalid move
     */
    @Test
    public void test_invalid()
    {
        String expected = "{\"text\":\"Move was unable to be reversed!\",\"type\":\"ERROR\"}";
        int expectedSize = 0;

        String actual = (String) CuT.handle(request, response);

        int actualSize = playerService.getTurnMoves().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
        assertThrows(IndexOutOfBoundsException.class, () -> {playerService.getTurnMoves().get(0);});
    }
}

package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testing suite for PostValidateMoveRoute
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */

@Tag("UI-tier")
public class PostValidateMoveRouteTest
{
    private PostValidateMoveRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private Gson gson;

    //friendly objects
    private PlayerService playerService;
    private GameCenter gameCenter;
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
        gameCenter = new GameCenter();
        game = new Game(player, opponent, gameCenter);
        playerService = new PlayerService(player, game);

        gson = new GsonBuilder().create();

        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);

        CuT = new PostValidateMoveRoute();
    }

    /**
     * Test for valid move
     */
    @Test
    public void test_valid()
    {
        move = new Move(new Position(5, 0), new Position(4, 1));
        when(request.queryParams("actionData")).thenReturn(gson.toJson(move));
        String expected = "{\"text\":\"Move was made successfully!\",\"type\":\"INFO\"}";
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
        move = new Move(new Position(5, 0), new Position(4, 0));
        when(request.queryParams("actionData")).thenReturn(gson.toJson(move));
        String expected = "{\"text\":\"Move was unable to be made!\",\"type\":\"ERROR\"}";
        int expectedSize = 0;

        String actual = (String) CuT.handle(request, response);

        int actualSize = playerService.getTurnMoves().size();
        assertEquals(expected, actual);
        assertSame(expectedSize, actualSize);
        assertThrows(IndexOutOfBoundsException.class, () -> {playerService.getTurnMoves().get(0);});
    }
}

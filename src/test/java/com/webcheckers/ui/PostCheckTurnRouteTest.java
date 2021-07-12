package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.GameWin;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.*;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testing suite for PostCheckTurnRoute
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("UI-tier")
public class PostCheckTurnRouteTest
{
    //Component under Test
    private PostCheckTurnRoute CuT;

    //Mock Objects
    private Request request;
    private Session session;
    private Response response;
    private Gson gson;

    //friendly objects
    private GameCenter gameCenter;
    private PlayerService playerService;
    private Game game;
    private Player player;
    private Player opponent;
    //Position, Move

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

        gameCenter = new GameCenter();
        player = new Player("Player");
        opponent = new Player("Opponent");
        gameCenter.requestNewGame(player, opponent);
        playerService = gameCenter.getPlayerService(player);
        game = playerService.getGame();

        gson = new GsonBuilder().create();

        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);

        CuT = new PostCheckTurnRoute();
    }

    /**
     * Test for when the active player is red
     */
    @Test
    public void test_active_player_red()
    {
        Message message = Message.info("true");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }

    /**
     * Test for when the active player is white
     */
    @Test
    public void test_active_player_white()
    {
        playerService = new PlayerService(opponent, game);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);
        Board board = game.getBoard();
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(new SingleMove(new Position(5, 0), new Position(4, 1)));
        board.makeMove(moves);
        Message message = Message.info("true");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }

    /**
     * Test for when the active player is white but the player is red
     */
    @Test
    public void test_active_player_white_player_red()
    {
        Board board = game.getBoard();
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(new SingleMove(new Position(5, 0), new Position(4, 1)));
        board.makeMove(moves);
        Message message = Message.info("false");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }

    /**
     * Test for when the active player is red but the player is white
     */
    @Test
    public void test_active_player_red_player_white()
    {
        playerService = new PlayerService(opponent, game);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);
        Message message = Message.info("false");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }

    /**
     * Test for when the active player is not the current color
     */
    @Test
    public void test_inactive_player()
    {
        playerService = new PlayerService(opponent, game);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);
        Message message = Message.info("false");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }
}

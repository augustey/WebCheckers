package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.GameWin;
import com.webcheckers.application.PlayerService;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Testing suite for PostResignGameRoute
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("UI-tier")
@Tag("feature-ui")
public class PostResignGameRouteTest
{
    private PostResignGameRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private Gson gson;

    //friendly objects
    private GameCenter gameCenter;
    private TurnLogger turnLogger;
    private PlayerService playerService;
    private Game game;
    private GameWin gameWin;
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

        gameCenter = new GameCenter();
        turnLogger = new TurnLogger();
        player = new Player("Player");
        opponent = new Player("Opponent");
        gameCenter.requestNewGame(player, opponent, turnLogger);
        playerService = gameCenter.getPlayerService(player);
        game = playerService.getGame();
        gameWin = game.getGameWin();

        gson = new GsonBuilder().create();

        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);

        CuT = new PostResignGameRoute();
    }

    /**
     * Test for a valid resignation
     */
    @Test
    public void test_valid() {
        Message expected = Message.info("Player has resigned. Opponent is the winner!");

        String actual = (String) CuT.handle(request, response);

        assertEquals(gson.toJson(expected), actual);
    }

    /**
     * Test for an invalid resignation
     */
    @Test
    public void test_invalid() {
        Message expected = Message.error("Game was unable to be ended.");
        Player player3 = new Player("Hi");
        PlayerService playerService = new PlayerService(player3, new Game(player3, player, gameCenter));
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);

        String actual = (String) CuT.handle(request, response);

        assertEquals(gson.toJson(expected), actual);
    }
}

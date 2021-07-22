package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerService;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


/**
 * Testing suite for PostReplayPreviousTurnRoute
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("UI-tier")
@Tag("replay")
public class PostReplayPreviousTurnRouteTest
{
    private PostReplayPreviousTurnRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;

    private String redirectURL;
    private GameCenter gameCenter;
    private TurnLogger turnLogger;
    private PlayerService playerService;
    private Player player;
    private Player opponenet;
    private Game game;
    private Gson gson;

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
        engine = mock(TemplateEngine.class);

        doAnswer(inv -> redirectURL = inv.getArgument(0)).when(response).redirect(anyString());

        gameCenter = new GameCenter();
        turnLogger = new TurnLogger();

        gson = new GsonBuilder().create();

        player = new Player("player");
        opponenet = new Player("opponent");
        gameCenter.requestNewGame(player, opponenet, turnLogger);
        playerService = gameCenter.getPlayerService(player);
        game = playerService.getGame();
        turnLogger.startReview(player, game);

        CuT = new PostReplayPreviousTurnRoute();
    }

    /**
     * Test for standard session
     */
    @Test
    public void test_session() {
        when(session.attribute(PostReplayNextTurnRoute.TURNID_PARAM)).thenReturn(5);
        Message message = Message.info("true");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }

    /**
     * Test for when the session fails
     */
    @Test
    public void test_session_fail() {
        Message message = Message.error("An error has occured getting the turn index");
        String expected = gson.toJson(message);

        String actual = (String) CuT.handle(request, response);

        assertEquals(expected, actual);
    }
}

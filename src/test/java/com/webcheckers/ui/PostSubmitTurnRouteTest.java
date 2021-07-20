package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
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
 * Testing suite for PostSubmitTurnRoute
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */

@Tag("UI-tier")
@Tag("feature-ui")
public class PostSubmitTurnRouteTest
{
    private PostSubmitTurnRoute CuT;

    private Request request;
    private Session session;
    private Response response;
    private Gson gson;

    //friendly objects
    private GameCenter gameCenter;
    private TurnLogger turnLogger;
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

        gameCenter = new GameCenter();
        turnLogger = new TurnLogger();
        player = new Player("Player");
        opponent = new Player("Opponent");
        gameCenter.requestNewGame(player, opponent, turnLogger);
        playerService = gameCenter.getPlayerService(player);
        game = playerService.getGame();

        gson = new GsonBuilder().create();

        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);

        CuT = new PostSubmitTurnRoute(turnLogger);
    }

    /**
     * Test for a submission
     */
    @Test
    public void test_handle()
    {
        Message expected = Message.info("Move is valid!");
        playerService.addMove(new Move(new Position(5, 0), new Position(4, 1)));

        String actual = (String) CuT.handle(request, response);

        assertEquals(gson.toJson(expected), actual);
    }
}

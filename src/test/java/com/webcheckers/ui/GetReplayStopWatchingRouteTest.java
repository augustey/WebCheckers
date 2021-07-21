package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerService;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Tag("UI-tier")
@Tag("replay")
public class GetReplayStopWatchingRouteTest
{
    private GetReplayStopWatchingRoute CuT;

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

        player = new Player("player");
        opponenet = new Player("opponent");
        gameCenter.requestNewGame(player, opponenet, turnLogger);
        playerService = gameCenter.getPlayerService(player);
        game = playerService.getGame();
        turnLogger.startReview(player, game);

        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);

        CuT = new GetReplayStopWatchingRoute(turnLogger);
    }

    @Test
    public void test_session() {
        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.HOME_URL);
        }
    }

    @Test
    public void test_session_invalid() {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(null);
        Message message = Message.error("Unable to stop watching");

        Message actual = (Message) CuT.handle(request, response);

        assertEquals(message.getText(), actual.getText());
        assertEquals(message.getType(), actual.getType());
    }
}

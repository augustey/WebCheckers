package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerService;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Tag("UI-tier")
@Tag("replay")
public class GetReplayPageRouteTest
{
    private GetReplayPageRoute CuT;

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

        CuT = new GetReplayPageRoute(engine, gameCenter, turnLogger);
    }

    /**
     * Test that CuT shows the Replay page when the page is loaded and there is no player
     */
    @Test
    public void new_session() {
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignInRoute.TITLE_ATTR, "Replay");
        testHelper.assertViewModelAttribute("gameList", gameCenter.getCompletedGames());
        testHelper.assertViewModelAttribute(PLAYER_KEY, null);
    }

    /**
     * Test that CuT shows the Replay page when the page is loaded
     */
    @Test
    public void existing_session() {
        when(session.attribute(PLAYER_KEY)).thenReturn(player);
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetSignInRoute.TITLE_ATTR, "Replay");
        testHelper.assertViewModelAttribute("gameList", gameCenter.getCompletedGames());
        testHelper.assertViewModelAttribute(PLAYER_KEY, player);
    }

    /**
     * Test that CuT shows the Replay page when the page is loaded and they are already in a replay
     */
    @Test
    public void existing_session_replay() {
        when(session.attribute(PLAYER_KEY)).thenReturn(player);
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        turnLogger.startReview(player, game);
        // Invoke the test
        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.REPLAY_GAME_URL + "?gameID=" + turnLogger.getGame(player).getId());
        }
    }
}

package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerService;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.HashMap;
import java.util.Map;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

/**
 * Testing suite for GetReplayGameRoute
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
@Tag("UI-tier")
@Tag("replay")
public class GetReplayGameRouteTest
{
    private GetReplayGameRoute CuT;

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

        turnLogger.logTurn(game);

        gameCenter.getCompletedGamesMap().put(game.getId(), game);

        when(request.queryParams(GetReplayGameRoute.GAMEID_PARAM)).thenReturn(game.getId());
        when(session.attribute(PLAYER_KEY)).thenReturn(player);
        when(session.attribute(GetReplayGameRoute.TURNID_PARAM)).thenReturn(0);

        CuT = new GetReplayGameRoute(engine, gameCenter, turnLogger);
    }

    /**
     * Test for when the player is null
     */
    @Test
    public void test_player_null() {
        when(session.attribute(PLAYER_KEY)).thenReturn(null);
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.HOME_URL);
        }
    }

    /**
     * Test for when the gameID is null
     */
    @Test
    public void test_gameid_null() {
        when(request.queryParams(GetReplayGameRoute.GAMEID_PARAM)).thenReturn(null);
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.HOME_URL);
        }
    }

    /**
     * Test for when the index is null
     */
    @Test
    public void test_index_null() {
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(GetReplayGameRoute.TURNID_PARAM)).thenReturn(null);


        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetGameRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute(GetGameRoute.USER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.WHITE_PLAYER_ATTR, opponenet);
        testHelper.assertViewModelAttribute(GetGameRoute.ACTIVE_COLOR_ATTR, Piece.Color.RED);
        testHelper.assertViewModelAttribute(GetReplayGameRoute.VIEW_MODE_ATTR, GetReplayGameRoute.VIEW_MODE);

        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    /**
     * Test for a standard session when there is a next turn
     */
    @Test
    public void test_session() {
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        Gson gson = new GsonBuilder().create();
        Map<String, Object> modeOptions = new HashMap<>();

        modeOptions.put("hasNext", true);
        modeOptions.put("hasPrevious", false);


        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetGameRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute(GetGameRoute.USER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.WHITE_PLAYER_ATTR, opponenet);
        testHelper.assertViewModelAttribute(GetGameRoute.ACTIVE_COLOR_ATTR, Piece.Color.RED);
        testHelper.assertViewModelAttribute(GetReplayGameRoute.VIEW_MODE_ATTR, GetReplayGameRoute.VIEW_MODE);
        testHelper.assertViewModelAttribute(GetReplayGameRoute.MODE_OPTS_ATTR, gson.toJson(modeOptions));

        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    /**
     * Test for a standard session when there is a previous turn
     */
    @Test
    public void test_session_2() {
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        when(session.attribute(GetReplayGameRoute.TURNID_PARAM)).thenReturn(1);
        Gson gson = new GsonBuilder().create();
        Map<String, Object> modeOptions = new HashMap<>();

        modeOptions.put("hasNext", false);
        modeOptions.put("hasPrevious", true);


        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetGameRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute(GetGameRoute.USER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.WHITE_PLAYER_ATTR, opponenet);
        testHelper.assertViewModelAttribute(GetGameRoute.ACTIVE_COLOR_ATTR, Piece.Color.RED);
        testHelper.assertViewModelAttribute(GetReplayGameRoute.VIEW_MODE_ATTR, GetReplayGameRoute.VIEW_MODE);
        testHelper.assertViewModelAttribute(GetReplayGameRoute.MODE_OPTS_ATTR, gson.toJson(modeOptions));

        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }
}

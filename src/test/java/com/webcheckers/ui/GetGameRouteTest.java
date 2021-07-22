package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import static org.junit.jupiter.api.Assertions.*;

import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The unit test suite for the GetGameRoute component.
 *
 * @author <a href='mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
@Tag("UI-tier")
public class GetGameRouteTest {

    private GetGameRoute CuT;

    //Friendly classes
    private GameCenter gameCenter;
    private Player player;
    private Player opponent;
    private Player otherPlayer;
    private String redirectURL;

    private Request request;
    private Session session;
    private Response response;
    private PlayerLobby playerLobby;
    private TurnLogger turnLogger;
    private TemplateEngine engine;

    /**
     * Setup for tests
     */
    @BeforeEach
    public void setup() {
        player = new Player("Player");
        opponent = new Player("Opponent");
        otherPlayer = new Player("OtherPlayer");

        request = mock(Request.class);

        session = mock(Session.class);
        when(request.session()).thenReturn(session);

        response = mock(Response.class);
        doAnswer(inv -> redirectURL = inv.getArgument(0)).when(response).redirect(anyString());

        playerLobby = mock(PlayerLobby.class);

        Set<Player> playerSet = new HashSet<>();
        playerSet.add(player);
        playerSet.add(opponent);
        playerSet.add(otherPlayer);

        for (Player p : playerSet) {
            when(playerLobby.getPlayer(p.getName())).thenReturn(p);
        }

        engine = mock(TemplateEngine.class);

        gameCenter = new GameCenter();
        turnLogger = new TurnLogger();

        CuT = new GetGameRoute(playerLobby, gameCenter, turnLogger, engine);
    }

    /**
     * Test if the happy route where a game is created between two players if they are both
     * not in a game.
     */
    @Test
    public void test_new_game() {
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(null);
        when(request.queryParams(GetGameRoute.OPPONENT_PARAM)).thenReturn(opponent.getName());

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        verify(session).attribute(eq(GetGameRoute.PLAYER_SERVICE_KEY), any(PlayerService.class));

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetGameRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute(GetGameRoute.USER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.WHITE_PLAYER_ATTR, opponent);
        testHelper.assertViewModelAttribute(GetGameRoute.ACTIVE_COLOR_ATTR, Piece.Color.RED);

        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }

    /**
     * Test if the page redirects if the requested opponent is already
     * in a game.
     */
    @Test
    public void test_opponent_in_game() {
        gameCenter.requestNewGame(otherPlayer, opponent, turnLogger);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(null);
        when(request.queryParams(GetGameRoute.OPPONENT_PARAM)).thenReturn(opponent.getName());

        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.HOME_URL);
        }

        verify(session, times(0)).attribute(eq(GetGameRoute.PLAYER_SERVICE_KEY), any(PlayerService.class));
    }

    /**
     * Test if the page redirects if the requested opponent is null.
     */
    @Test
    public void test_opponent_null() {
        gameCenter.requestNewGame(otherPlayer, opponent, turnLogger);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(null);
        when(request.queryParams(GetGameRoute.OPPONENT_PARAM)).thenReturn("doesnotexist");

        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.HOME_URL);
        }

        verify(session, times(0)).attribute(eq(GetGameRoute.PLAYER_SERVICE_KEY), any(PlayerService.class));
    }

    /**
     * Test if the page redirects to sign in if player object is null.
     */
    @Test
    public void test_player_null() {
        gameCenter.requestNewGame(otherPlayer, opponent, turnLogger);
        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(null);

        try {
            CuT.handle(request, response);
            fail("Route did not halt");
        } catch (HaltException e) {
            assertEquals(redirectURL, WebServer.SIGNIN_URL);
        }

        verify(session, times(0)).attribute(eq(GetGameRoute.PLAYER_SERVICE_KEY), any(PlayerService.class));
    }

    /**
     * Test if the page loads correctly if the player is already in a game
     */
    @Test
    public void test_player_in_game() {
        gameCenter.requestNewGame(player, opponent, turnLogger);
        PlayerService playerService = gameCenter.getPlayerService(player);

        when(session.attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player);
        when(session.attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);
        when(request.queryParams(GetGameRoute.OPPONENT_PARAM)).thenReturn(opponent.getName());

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        CuT.handle(request, response);

        verify(session, times(0)).attribute(eq(GetGameRoute.PLAYER_SERVICE_KEY), any(PlayerService.class));

        testHelper.assertViewModelAttribute(GetGameRoute.TITLE_ATTR, GetGameRoute.TITLE);
        testHelper.assertViewModelAttribute(GetGameRoute.USER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.RED_PLAYER_ATTR, player);
        testHelper.assertViewModelAttribute(GetGameRoute.WHITE_PLAYER_ATTR, opponent);
        testHelper.assertViewModelAttribute(GetGameRoute.ACTIVE_COLOR_ATTR, Piece.Color.RED);

        testHelper.assertViewName(GetGameRoute.VIEW_NAME);
    }
}
package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Player;

import com.webcheckers.util.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link GetHomeRoute} component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("UI-tier")

public class GetHomeRouteTest {

    // Constants for testing.
    private final String PLAYER_NAME_1 = "UnitTester1";
    private final String PLAYER_NAME_2 = "UnitTester2";
    private final String MESSAGE = "Test Message";

    // Friendly Objects
    private PlayerLobby playerLobby;
    private TurnLogger turnLogger;
    private GameCenter gameCenter;
    private Player player1;
    private Player player2;

    // Mock Objects
    private TemplateEngine engine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * The component-under-test (CuT).
     */
    private GetHomeRoute CuT;

    /**
     * Setup the new mock objects for the unit tests.
     */
    @BeforeEach
    public void testSetup() {
        // Build the mocks.
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        // Build the friendly objects.
        player1 = new Player(PLAYER_NAME_1);
        player2 = new Player(PLAYER_NAME_2);
        playerLobby = new PlayerLobby();
        gameCenter = new GameCenter();
        turnLogger = new TurnLogger();
        // Creating the CuT.
        CuT = new GetHomeRoute(playerLobby, gameCenter, engine);
    }

    /**
     * Test that CuT shows the Home view when the page is first loaded.
     */
    @Test
    public void new_session() {
        // Arrange the test scenario: Creating a new session home page
        when(request.session().attribute(GetHomeRoute.MESSAGE_KEY)).thenReturn(null);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        testHelper.assertViewModelAttribute(GetHomeRoute.ONLINE_COUNT_ATTR, 0);
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, GetHomeRoute.WELCOME_MSG);
    }

    /**
     * Test the route to see if the proper home page is shown for a signed in player.
     */
    @Test
    public void test_player_home_page() {
        // Arrange the test scenario: There is a player signed into the web app.
        playerLobby.signIn(player1);
        when(request.session().attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player1);
        when(request.session().attribute(GetHomeRoute.MESSAGE_KEY)).thenReturn(null);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYER_KEY, player1);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYERSET_KEY, playerLobby.getPlayerSet());
        testHelper.assertViewModelAttribute(GetHomeRoute.ONLINE_COUNT_ATTR, 1);
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, GetHomeRoute.WELCOME_MSG);
    }

    /**
     * Test the route to see if the proper home page is shown for a player when a game has ended.
     */
    @Test
    public void test_player_home_game_not_ended() {
        // Arrange the test scenario: There is a player signed into the web app.
        playerLobby.signIn(player1);
        gameCenter.requestNewGame(player1, player2, turnLogger);
        PlayerService playerService = gameCenter.getPlayerService(player1);
        playerService.getGame().setGameOver(false);
        Message message = Message.info(MESSAGE);

        when(request.session().attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player1);
        when(request.session().attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);
        when(request.session().attribute(GetHomeRoute.MESSAGE_KEY)).thenReturn(message);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYER_KEY, player1);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYERSET_KEY, playerLobby.getPlayerSet());
        testHelper.assertViewModelAttribute(GetHomeRoute.ONLINE_COUNT_ATTR, 1);
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, message);
    }

    /**
     * Test the route to see if the proper home page is shown for a player when a game has ended.
     */
    @Test
    public void test_player_home_game_ended() {
        // Arrange the test scenario: There is a player signed into the web app.
        playerLobby.signIn(player1);
        gameCenter.requestNewGame(player1, player2, turnLogger);
        PlayerService playerService = gameCenter.getPlayerService(player1);
        playerService.getGame().setGameOver(true);
        Message message = Message.info(MESSAGE);

        when(request.session().attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player1);
        when(request.session().attribute(GetGameRoute.PLAYER_SERVICE_KEY)).thenReturn(playerService);
        when(request.session().attribute(GetHomeRoute.MESSAGE_KEY)).thenReturn(message);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYER_KEY, player1);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYERSET_KEY, playerLobby.getPlayerSet());
        testHelper.assertViewModelAttribute(GetHomeRoute.ONLINE_COUNT_ATTR, 1);
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, message);
    }
}
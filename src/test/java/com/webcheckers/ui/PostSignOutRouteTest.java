package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The unit test suite for the {@link PostSignOutRoute} component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("UI-tier")

public class PostSignOutRouteTest {

    // Constants for testing.
    private final String PLAYER_NAME_1 = "UnitTester1";
    private final String PLAYER_NAME_2 = "UnitTester2";

    // Friendly Objects
    private PlayerLobby playerLobby;
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
    private PostSignOutRoute CuT;

    /**
     * Setup the new mock objects for the unit tests.
     */
    @BeforeEach
    public void testSetup() {
        // Build the mocks for Request, Session, and Response.
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);

        // Build the mocks for initializing the CuT.
        engine = mock(TemplateEngine.class);

        // Initialize the friendly objects
        playerLobby = new PlayerLobby();
        player1 = new Player(PLAYER_NAME_1);
        player2 = new Player(PLAYER_NAME_2);
        playerLobby.signIn(player1);
        playerLobby.signIn(player2);

        // Creating the CuT.
        CuT = new PostSignOutRoute(playerLobby, engine);
    }

    /**
     * Test the route with a valid username.
     */
    @Test
    public void test_sign_out() {
        // Arrange the test scenario: The username is valid.
        when(request.session().attribute(GetHomeRoute.PLAYER_KEY)).thenReturn(player1);
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test.
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(GetHomeRoute.TITLE_ATTR, GetHomeRoute.TITLE);
        testHelper.assertViewModelAttribute(GetHomeRoute.ONLINE_COUNT_ATTR, playerLobby.getPlayerSet().size());
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, GetHomeRoute.WELCOME_MSG);
    }
}

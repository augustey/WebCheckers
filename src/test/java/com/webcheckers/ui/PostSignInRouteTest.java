package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerLobby.SignInResult;
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
 * The unit test suite for the {@link PostSignInRoute} component.
 *
 * @author <a href = 'mailto:nmr3095@rit.edu'>Neel Raj</a>
 */

@Tag("UI-tier")

public class PostSignInRouteTest {

    // Constant Test Username Strings.
    private final String VALID_ALPHANUMERIC = "UnitTester1";
    private final String INVALID_NONALPHANUMERIC = "-*#@$%&^+=";
    private final String INVALID_EMPTY = "";
    private final String INVALID_SPACES = "  ";

    // Friendly Object
    private Player player;

    // Mock Objects
    private PlayerLobby playerLobby;
    private TemplateEngine engine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * The component-under-test (CuT).
     */
    private PostSignInRoute CuT;

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
        playerLobby = mock(PlayerLobby.class);
        engine = mock(TemplateEngine.class);

        // Creating the CuT.
        CuT = new PostSignInRoute(playerLobby, engine);
    }

    /**
     * Test the route with a valid username.
     */
    @Test
    public void test_valid_username() {
        // Arrange the test scenario: The username is valid.
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(VALID_ALPHANUMERIC);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Initialize a player with the valid username.
        player = new Player(VALID_ALPHANUMERIC);
        // Setup the mock PlayerLobby functionality.
        when(playerLobby.signIn(player)).thenReturn(SignInResult.SUCCESS);
        // Invoke the test.
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYER_KEY, player);
        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, false);
    }

    /**
     * Test the route with an invalid username composed of symbols.
     */
    @Test
    public void test_invalid_username_symbols() {
        // Arrange the test scenario: The username is invalid and composed of symbols.
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(INVALID_NONALPHANUMERIC);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Initialize a player with the invalid username of symbols.
        player = new Player(INVALID_NONALPHANUMERIC);
        // Setup the mock PlayerLobby functionality.
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_ALPHANUMERIC);
        // Invoke the test.
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_ALPHANUMERIC_USERNAME);
    }

    /**
     * Test the route with an invalid username that is an empty string.
     */
    @Test
    public void test_invalid_username_empty() {
        // Arrange the test scenario: The username is invalid and an empty string.
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(INVALID_EMPTY);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Initialize a player with the invalid username, an empty string.
        player = new Player(INVALID_EMPTY);
        // Setup the mock PlayerLobby functionality.
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_ALPHANUMERIC);

        // Invoke the test.
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_ALPHANUMERIC_USERNAME);
    }

    /**
     * Test the route with an invalid username that is spaces.
     */
    @Test
    public void test_invalid_username_spaces() {
        // Arrange the test scenario: The username is invalid and composed of spaces.
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(INVALID_SPACES);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Initialize a player with the invalid username of spaces.
        player = new Player(INVALID_SPACES);
        // Setup the mock PlayerLobby functionality.
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_ALPHANUMERIC);

        // Invoke the test.
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_ALPHANUMERIC_USERNAME);
    }

    /**
     * Test the route with a non-unique username.
     */
    @Test
    public void test_invalid_username_non_unique() {
        // Arrange the test scenario: The username is invalid and non-unique.
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(VALID_ALPHANUMERIC);

        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine.
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Initialize a player with the non-unique invalid username.
        player = new Player(VALID_ALPHANUMERIC);
        // Setup the mock PlayerLobby functionality.
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_UNIQUE);

        // Invoke the test.
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map.
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data.
        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_UNIQUE_USERNAME);
    }
}
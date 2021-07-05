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

    // Friendly Object
    private Player player;

    /**
     * The component-under-test (CuT).
     */
    private PostSignInRoute CuT;

    // Mock Objects
    private PlayerLobby playerLobby;
    private TemplateEngine engine;
    private Request request;
    private Session session;
    private Response response;

    /**
     * Setup the new mock objects for the unit tests.
     */
    @BeforeEach
    public void testSetup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        playerLobby = mock(PlayerLobby.class);
        response = mock(Response.class);
        engine = mock(TemplateEngine.class);

        CuT = new PostSignInRoute(playerLobby, engine);
    }

    /**
     * Test the route with a valid username.
     */
    @Test
    public void test_valid_username() {
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(VALID_ALPHANUMERIC);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        player = new Player(VALID_ALPHANUMERIC);
        when(playerLobby.signIn(player)).thenReturn(SignInResult.SUCCESS);
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYER_KEY, player);
        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, false);
    }

    /**
     * Test the route with an invalid username composed of symbols.
     */
    @Test
    public void test_invalid_username_symbols() {
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(INVALID_NONALPHANUMERIC);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        player = new Player(INVALID_NONALPHANUMERIC);
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_ALPHANUMERIC);
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_ALPHANUMERIC_USERNAME);
    }

    /**
     * Test the route with an invalid username that is empty.
     */
    @Test
    public void test_invalid_username_empty() {
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(INVALID_EMPTY);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        player = new Player(INVALID_EMPTY);
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_ALPHANUMERIC);
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_ALPHANUMERIC_USERNAME);
    }

    /**
     * Test the route with a non-unique username.
     */
    @Test
    public void test_invalid_username_non_unique() {
        when(request.queryParams(eq(PostSignInRoute.USERNAME_PARAM))).thenReturn(VALID_ALPHANUMERIC);

        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());
        player = new Player(VALID_ALPHANUMERIC);
        when(playerLobby.signIn(player)).thenReturn(SignInResult.NON_UNIQUE);
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();

        testHelper.assertViewModelAttribute(PostSignInRoute.SIGNED_IN, false);
        testHelper.assertViewModelAttribute(PostSignInRoute.NOT_VALID_USERNAME, true);
        testHelper.assertViewModelAttribute(PostSignInRoute.INVALID_MESSAGE, PostSignInRoute.NON_UNIQUE_USERNAME);
    }
}
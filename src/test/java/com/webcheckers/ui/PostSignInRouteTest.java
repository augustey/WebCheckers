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
    private final String VALID_ALPHANUMERIC = "UnitTester1";
    private final String INVALID_NONALPHANUMERIC = "-*#@$%&^+=";
    private final String INVALID_EMPTY = "";
    private Player player;
    private PostSignInRoute CuT;

    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private PlayerLobby playerLobby;

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
package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * Testing suite for GetSignInRoute
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */
@Tag("UI-Tier")
public class GetHomeRouteTest {
    private GetHomeRoute CuT;

    // friendly objects
    private GameCenter gameCenter;
    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    // mock objects
//    private PlayerLobby playerLobby= new PlayerLobby();
    private PlayerLobby player;
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;

    @BeforeEach
    public void setup() {

//        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
//        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
//        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");

        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        gameCenter = mock(GameCenter.class);
        playerLobby = mock(PlayerLobby.class);
        engine = mock(TemplateEngine.class);

        CuT = new GetHomeRoute(playerLobby, gameCenter, engine);
    }
    @Test
    public void new_session() {
        // To analyze what the Route created in the View-Model map you need
        // to be able to extract the argument to the TemplateEngine.render method.
        // Mock up the 'render' method by supplying a Mockito 'Answer' object
        // that captures the ModelAndView data passed to the template engine
        final TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        // Analyze the results:
        //   * model is a non-null Map
        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        //   * model contains all necessary View-Model data
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, GetHomeRoute.TITLE);


//        testHelper.assertViewModelAttribute(GetHomeRoute.NEW_PLAYER_ATTR, Boolean.TRUE);
        //   * test view name
        testHelper.assertViewName(GetHomeRoute.PLAYER_KEY);
        //   * verify that a player service object and the session timeout watchdog are stored
        //   * in the session.
        verify(session).attribute(eq(GetHomeRoute.PLAYER_KEY), any(PlayerService.class));
//        verify(session).attribute(eq(GetHomeRoute.TIMEOUT_SESSION_KEY),
//                any(SessionTimeoutWatchdog.class));
    }

}
package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import spark.*;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

/**
 * Testing suite for GetHomeRoute
 *
 * @author <a href = 'mailto:whd8254@g.rit.edu'>William Dabney</a>
 */
@Tag("UI-Tier")
public class GetHomeRouteTest {
    private GetHomeRoute CuT;

    // friendly objects
    private GameCenter gameCenter;
//    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    // mock objects
//    private PlayerLobby playerLobby= new PlayerLobby();
    private PlayerLobby playerLobby;
    private Request request;
    private Session session;
    private TemplateEngine engine;
    private Response response;
    private Player player;

    @BeforeEach
    public void setup() {

        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        response = mock(Response.class);
        gameCenter = mock(GameCenter.class);
        playerLobby = mock(PlayerLobby.class);
        engine = mock(TemplateEngine.class);
        player = mock(Player.class);

        CuT = new GetHomeRoute(playerLobby, gameCenter, engine);
    }
    @Test
    public void new_session() {

        TemplateEngineTester testHelper = new TemplateEngineTester();
        when(engine.render(any(ModelAndView.class))).thenAnswer(testHelper.makeAnswer());

        // Invoke the test
        CuT.handle(request, response);

        testHelper.assertViewModelExists();
        testHelper.assertViewModelIsaMap();
        testHelper.assertViewModelAttribute(GetHomeRoute.MESSAGE_KEY, GetHomeRoute.WELCOME_MSG);
        testHelper.assertViewModelAttribute(GetHomeRoute.PLAYER_KEY, null);
        String viewName = "home.ftl";
        testHelper.assertViewName(viewName);


    }

}
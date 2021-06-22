package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Route for signing out the player
 *
 * @author <a href="mailto:jrl9984@rit.edu">Jim Logan</a>
 */
public class PostSignOutRoute implements Route
{
    //Attributes
    private static final String TITLE_ATTR = "title";

    //State
    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code POST /signout} HTTP requests.
     *
     * @param playerLobby
     *   the server wide lobby keeping track of all players
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public PostSignOutRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine)
    {
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers Signout page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Signout page
     */
    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_KEY);
        playerLobby.signOut(player);
        httpSession.removeAttribute(GetHomeRoute.PLAYER_KEY);

        vm.put(TITLE_ATTR, GetHomeRoute.TITLE);

        int count = playerLobby.getPlayerSet().size();
        vm.put(GetHomeRoute.ONLINE_COUNT_ATTR, count);

        vm.put("message", GetHomeRoute.WELCOME_MSG);

        response.redirect("/");

        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}

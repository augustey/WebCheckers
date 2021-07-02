package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Player;
import spark.*;

import com.webcheckers.util.Message;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    public static final Message WELCOME_MSG = Message.info("Welcome to the world of Online Checkers.");

    public static final String MESSAGE_KEY = "message";
    public static final String PLAYER_KEY = "currentUser";
    public static final String ONLINE_COUNT_ATTR = "count";

    public static final String TITLE = "Welcome!";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /} HTTP requests.
     *
     * @param templateEngine
     *   The HTML template rendering engine.
     */
    public GetHomeRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *   The HTTP request.
     *
     * @param response
     *   The HTTP response.
     *
     * @return
     *   The rendered HTML for the Home page.
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetHomeRoute is invoked.");
        //
        final Session httpSession = request.session();
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Welcome!");

        if(httpSession.attribute(PLAYER_KEY) != null)
        {
            Player player = httpSession.attribute(PLAYER_KEY);
            vm.put("currentUser", player);
            vm.put("playerSet", playerLobby.getPlayerSet());

            PlayerService playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

            if(playerService == null) {
                playerService = gameCenter.getPlayerService(player);

                if(playerService != null) {
                    httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY, playerService);
                    response.redirect(WebServer.GAME_URL);
                    halt();
                    return null;
                }
            }
        }

        int count = playerLobby.getPlayerSet().size();
        vm.put(ONLINE_COUNT_ATTR, count);

        Message message = httpSession.attribute(MESSAGE_KEY);
        message = (message == null) ? WELCOME_MSG : message;

        httpSession.removeAttribute(MESSAGE_KEY);

        // display a user message in the Home page
        vm.put("message", message);

        // render the View
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}

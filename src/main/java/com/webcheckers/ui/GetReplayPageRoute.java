package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;
import static spark.Spark.halt;

/**
 * The UI Controller to GET the Replay page.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class GetReplayPageRoute implements Route {
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;
    private final TurnLogger turnLogger;

    private static final String TITLE_ATTR = "title";
    private static final String GAMELIST_ATTR = "gameList";

    private static final String TITLE = "Replay";

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /replay} HTTP requests.
     *
     * @param templateEngine
     *          The HTML template rendering engine.
     *
     * @param gameCenter
     *          The GameCenter that stores active games
     *
     * @param turnLogger
     *          The turnLogger used to store turns of a game
     */
    public GetReplayPageRoute(final TemplateEngine templateEngine, final GameCenter gameCenter, final TurnLogger turnLogger) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.turnLogger = turnLogger;
    }

    /**
     * Render the WebCheckers Home page.
     *
     * @param request
     *         The HTTP request.
     * @param response
     *         The HTTP response.
     *
     * @return The rendered HTML for the Replay page.
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();

        Player player = httpSession.attribute(PLAYER_KEY);

        if(turnLogger.isReviewing(player)) {
            response.redirect(WebServer.REPLAY_GAME_URL + "?gameID=" + turnLogger.getGame(player).getId());
        }

        vm.put(TITLE_ATTR, TITLE);
        vm.put(PLAYER_KEY, player);

        List<Game> list = gameCenter.getCompletedGames();

        vm.put(GAMELIST_ATTR, list);

        return templateEngine.render(new ModelAndView(vm, "replay.ftl"));
    }
}

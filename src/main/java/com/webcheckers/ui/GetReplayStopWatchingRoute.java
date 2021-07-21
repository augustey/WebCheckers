package com.webcheckers.ui;

import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;

/**
 * The UI Controller to GET the ReplayStopWatching page.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class GetReplayStopWatchingRoute implements Route
{
    public static final String TURNID_PARAM = "turn";

    private TurnLogger turnLogger;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /replay/stopWatching} HTTP requests.
     *
     * @param turnLogger
     *         The turnLogger used to store turns of a game
     */
    public GetReplayStopWatchingRoute(TurnLogger turnLogger) {
        this.turnLogger = turnLogger;
    }

    /**
     * Removes players from reviewing a game and redirects to home screen
     *
     * @param request
     *         The HTTP request.
     *
     * @param response
     *         The HTTP response.
     *
     * @return null
     */
    @Override
    public Object handle(Request request, Response response)
    {
        Session httpSession = request.session();

        try {
            Player player = httpSession.attribute(PLAYER_KEY);
            Game game = turnLogger.getGame(player);

            httpSession.removeAttribute(TURNID_PARAM);

            if(turnLogger.stopReview(player, game)) {
                response.redirect(WebServer.HOME_URL);
            }
        } catch (Exception e) {
            return Message.error("Unable to stop watching");
        }

        return null;
    }
}

package com.webcheckers.ui;

import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;

public class GetReplayStopWatchingRoute implements Route
{
    public static final String TURNID_PARAM = "turn";

    private TurnLogger turnLogger;

    public GetReplayStopWatchingRoute(TurnLogger turnLogger) {
        this.turnLogger = turnLogger;
    }


    @Override
    public Object handle(Request request, Response response)
    {
        Session httpSession = request.session();

        Player player = httpSession.attribute(PLAYER_KEY);

        httpSession.removeAttribute(TURNID_PARAM);
        turnLogger.stopReview(player);

        response.redirect("/");

        return null;
    }
}

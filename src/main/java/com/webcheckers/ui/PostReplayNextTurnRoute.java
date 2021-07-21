package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * The UI Controller to POST the ReplayNextTurn page.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class PostReplayNextTurnRoute implements Route
{
    public static final String TURNID_PARAM = "turn";

    /**
     * Constructor for PostReplayNextTurnRoute
     */
    public PostReplayNextTurnRoute() {

    }

    /**
     * Increments the turns for the game being reviewed.
     *
     * @param request
     *         The HTTP request.
     * @param response
     *         The HTTP response.
     *
     * @return JSON message for success
     */
    @Override
    public Object handle(Request request, Response response)
    {
        Session httpSession = request.session();

        Message message;
        message = Message.info("true");
        try {
            int i = httpSession.attribute(TURNID_PARAM);

            i++;

            httpSession.attribute(TURNID_PARAM, i);
        } catch (Exception e) {
            message = Message.error("An error has occured getting the turn index");
        }

        Gson gson = new GsonBuilder().create();

        return gson.toJson(message);
    }
}

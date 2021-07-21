package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostReplayPreviousTurnRoute implements Route
{
    public static final String TURNID_PARAM = "turn";

    public PostReplayPreviousTurnRoute() {

    }

    @Override
    public Object handle(Request request, Response response)
    {
        Session httpSession = request.session();

        Message message;
        try {
            int i = httpSession.attribute(TURNID_PARAM);

            i--;

            httpSession.attribute(TURNID_PARAM, i);
        } catch (Exception e) {
            message = Message.error("An error has occured getting the turn index");
        }

        Gson gson = new GsonBuilder().create();

        message = Message.info("true");
        return gson.toJson(message);
    }
}

package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostResignGameRoute implements Route
{
    private PlayerService playerService;

    public PostResignGameRoute()
    {
    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        final Map<String, Object> modeOp = new HashMap<>();
        Gson gson = new GsonBuilder().create();

        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

        String gameOverMessage = playerService.getPlayer() + " has resigned.";

        //trigger game over

        Message message;

        message = Message.info(gameOverMessage);

        return gson.toJson(message);
    }
}
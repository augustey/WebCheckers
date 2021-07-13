package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostBackupMoveRoute implements Route {

    private PlayerService playerService;

    public PostBackupMoveRoute() {
    }


    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

        Gson gson = new GsonBuilder().create();

        Message message;

        if (playerService.removeMove() != null) {
            message = Message.info("Move was reversed successfully!");
        }
        else {
            message = Message.error("Move was unable to be reversed!");
        }

        return gson.toJson(message);
    }
}
package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.GameWin;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Game;
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
        Gson gson = new GsonBuilder().create();

        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);
        Game game = playerService.getGame();
        GameWin gameWin = game.getGameWin();

        String gameOverMessage = playerService.getPlayer() + " has resigned.";
        Message message;

        if(gameWin.triggerGameOver(gameOverMessage))
        {
            message = Message.info(gameOverMessage);
        } else {
            message = Message.error("Game was unable to be ended.");
        }

        return gson.toJson(message);
    }
}
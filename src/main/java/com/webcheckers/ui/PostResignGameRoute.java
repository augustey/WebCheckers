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
    private GameCenter gameCenter;
    private PlayerService playerService;

    public PostResignGameRoute(final GameCenter gameCenter)
    {
        this.gameCenter = gameCenter;
    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        Gson gson = new GsonBuilder().create();

        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);
        Game game = playerService.getGame();
        GameWin gameWin = new GameWin(gameCenter, game);

        String gameOverMessage = playerService.getPlayer() + " has resigned.";
        if(gameWin.triggerGameOver(gameOverMessage))
        {
            Message message;
            message = Message.info(gameOverMessage);
        }

        Message message;
        message = Message.info(gameOverMessage);

        return gson.toJson(message);
    }
}
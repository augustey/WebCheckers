package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Game;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.model.SingleMovePosition;
import com.webcheckers.util.Message;
import spark.*;

public class PostValidateMoveRoute implements Route
{
    //PlayerService for the Player
    private PlayerService playerService;


    public PostValidateMoveRoute()
    {
        playerService = null;
    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();

        //TODO Store move for game
        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

        String JSONMove = request.queryParams("actionData");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Move move = gson.fromJson(JSONMove, Move.class);


        Message valid;
        if(move.validateMove())
        {
            valid = Message.info("Valid");
            playerService.addMove(move);
        } else
        {
            valid = Message.error("Invalid");
        }

        return gson.toJson(valid);
    }
}

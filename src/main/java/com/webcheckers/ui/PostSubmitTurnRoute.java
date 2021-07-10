package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.*;

import java.util.List;

public class PostSubmitTurnRoute implements Route
{
    private PlayerService playerService;

    public PostSubmitTurnRoute()
    {

    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

        //Look for other possible moves, and if a jump move is found, send an error

        //If no other moves are found, have game perform moves in the turnMoves list
        List<Move> moves = playerService.getTurnMoves();
        playerService.getGame().executeMoves(moves);

        Gson gson = new GsonBuilder().create();

        return gson.toJson(Message.info("Valid"));
    }
}
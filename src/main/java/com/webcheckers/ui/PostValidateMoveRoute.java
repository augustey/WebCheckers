package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.model.SingleMovePosition;
import com.webcheckers.util.Message;
import spark.*;

public class PostValidateMoveRoute implements Route
{
    public PostValidateMoveRoute()
    {

    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();

        //TODO Store move for game

        String JSONMove = request.queryParams("actionData");
        System.out.println(JSONMove);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Move move = gson.fromJson(JSONMove, Move.class);

        System.out.println(move);

        Message valid;
        if(move.validateMove())
        {
            valid = Message.info("Valid");
        } else
        {
            valid = Message.error("Invalid");
        }

        return gson.toJson(valid);
    }
}

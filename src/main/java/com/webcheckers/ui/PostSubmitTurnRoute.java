package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostSubmitTurnRoute implements Route
{
    public PostSubmitTurnRoute()
    {

    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();

        //Look for other possible moves, and if a jump move is found, send an error

        //If no other moves are found, have game perform moves in the turnMoves list

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return "{}";
    }
}
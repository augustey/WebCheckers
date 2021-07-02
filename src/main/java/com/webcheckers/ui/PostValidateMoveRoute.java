package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

        SingleMovePosition move = moveFromJson(JSONMove);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Message valid;
        if(true)
        {
            valid = Message.info("Valid");
        } else
        {
            valid = Message.error("Invalid");
        }

        return gson.toJson(valid);
    }

    private SingleMovePosition moveFromJson(String json)
    {
        char[] jsonChar = json.toCharArray();
        int[] coords = new int[4];
        int i = 0;

        for(char c : jsonChar)
        {
            if(Character.isDigit(c))
            {
                coords[i] = Integer.parseInt("" + c);
            }
        }

        Position start = new Position(coords[0], coords[1]);
        Position end = new Position(coords[2], coords[3]);
        SingleMovePosition move = new SingleMovePosition(start, end);

        return move;
    }
}

package com.webcheckers.ui;

import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.model.SingleMove;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostValidateMoveRoute implements Route
{
    public PostValidateMoveRoute()
    {

    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();

        String JSONMove = request.queryParams("actionData");

        Move move = moveFromJson(JSONMove);

        //if Move is valid
            //return Message.info("Move Valid");
        //else
            //return Message.error("Move Invalid");

        return Message.info("Valid");
    }

    private Move moveFromJson(String json)
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
        Move move = new Move(start, end);

        return move;
    }
}

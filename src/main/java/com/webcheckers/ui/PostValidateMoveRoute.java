package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import com.webcheckers.util.Message;
import spark.*;

/**
 * UI component that is called whenever a move is made on the board
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class PostValidateMoveRoute implements Route
{
    //PlayerService for the Player
    private PlayerService playerService;


    /**
     * Creates a new PostValidateMoveRoute
     */
    public PostValidateMoveRoute()
    {
        playerService = null;
    }


    /**
     * Grabs the player's PlayerService and the move from the server, and then
     * validates the move
     * @param request
     *     The HTTP request.
     *
     * @param response
     *     The HTTP response.
     *
     * @return
     *      JSON formatted message determing if the move was valid for not
     */
    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();

        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

        String JSONMove = request.queryParams("actionData");

        Gson gson = new GsonBuilder().create();

        Move move1 = gson.fromJson(JSONMove, Move.class);
        Move move2 = toJSON(JSONMove);

        Board board = playerService.getGame().getBoard();
        System.out.println(move1);
        System.out.println(move2);
        Message valid;
        if(board.getPossibleMoves().contains(move1))
        {
            valid = Message.info("Move was made successfully!");
            playerService.addMove(move1);
        }
        else {
            valid = Message.error("Move was unable to be made!");
        }

        System.out.println(gson.toJson(valid));

        return gson.toJson(valid);
    }

    private Move toJSON(String json) {
        int[] coords = new int[4];
        int i = 0;

        for(Character c : json.toCharArray()) {
            if(Character.isDigit(c)) {
                coords[i] = Integer.parseInt(Character.toString(c));
                i++;
            }
        }

        return new Move(new Position(coords[0], coords[1]), new Position(coords[2], coords[3]));
    }
}

package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Board;
import com.webcheckers.model.JumpMove;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.*;

/**
 * The UI component that is called whenever a move is made on the board
 *
 * @author <a href = 'mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class PostValidateMoveRoute implements Route {

    // PlayerService for the Player
    private PlayerService playerService;

    /**
     * Creates a new PostValidateMoveRoute.
     */
    public PostValidateMoveRoute() {
        playerService = null;
    }


    /**
     * Render the WebCheckers validate move message after validating a move.
     *
     * @param request
     *         The HTTP request.
     * @param response
     *         The HTTP response.
     *
     * @return The valid move message.
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);
        String JSONMove = request.queryParams("actionData");
        Gson gson = new GsonBuilder().create();
        Move move = gson.fromJson(JSONMove, Move.class);
        Board board = playerService.getGame().getBoard();
        playerService.getTurn().setMoveType(board.getMoveType());
        Message valid;
        if (board.isPossibleMove(move)) {
            if (playerService.addMove(move)) {
                valid = Message.info("Move was made successfully!");
            }
            else {
                valid = Message.error("Move was unable to be made!");
            }
        }
        else {
            valid = Message.error("Move was unable to be made!");
        }
        return gson.toJson(valid);
    }
}
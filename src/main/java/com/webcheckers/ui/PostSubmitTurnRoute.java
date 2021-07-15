package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.util.Message;
import spark.*;

import java.util.ArrayList;

public class PostSubmitTurnRoute implements Route {

    // The player service.
    private PlayerService playerService;

    /**
     * Constructor for PostSubmitTurnRoute
     */
    public PostSubmitTurnRoute() {
    }

    /**
     * Render the WebCheckers submit turn message and make the user's moves.
     *
     * @param request
     *         The HTTP request.
     * @param response
     *         The HTTP response.
     *
     * @return The backup message.
     */
    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);

        Board board = playerService.getGame().getBoard();
        ArrayList<Move> moves = (ArrayList<Move>) playerService.getTurnMoves();
        Message message = board.makeMove(moves);
        playerService.clearMoves();

        Gson gson = new GsonBuilder().create();

        return gson.toJson(message);
    }
}
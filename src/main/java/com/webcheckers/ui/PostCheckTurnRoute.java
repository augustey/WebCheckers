package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.util.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

public class PostCheckTurnRoute implements Route {
    private PlayerService playerService;

    public PostCheckTurnRoute() {

    }

    @Override
    public Object handle(Request request, Response response) {
        final Session httpSession = request.session();
        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);
        Board board = playerService.getGame().getBoard();

        Gson gson = new GsonBuilder().create();

        Message message;

        if (playerService.getPlayer().equals(playerService.getRedPlayer()) && board.getActivePlayerColor() == Piece.Color.RED) {
            message = Message.info("true");
        }
        else if (playerService.getPlayer().equals(playerService.getWhitePlayer()) && board.getActivePlayerColor() == Piece.Color.WHITE) {
            message = Message.info("true");
        }
        else {
            message = Message.info("false");
        }

        return gson.toJson(message);
    }
}

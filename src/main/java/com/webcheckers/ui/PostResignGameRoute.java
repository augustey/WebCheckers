package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameWin;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Game;
import com.webcheckers.util.Message;
import spark.*;

/**
 * The UI Controller to POST the resign message.
 *
 * @author <a href="mailto:jrl9984@rit.edu">Jim Logan</a>
 */
public class PostResignGameRoute implements Route {

    // The player service
    private PlayerService playerService;

    /**
     * Constructor for ostCheckTurnRoute
     */
    public PostResignGameRoute() {
    }

    /**
     * Render the WebCheckers check turn message.
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
        Gson gson = new GsonBuilder().create();

        playerService = httpSession.attribute(GetGameRoute.PLAYER_SERVICE_KEY);
        Game game = playerService.getGame();
        GameWin gameWin = game.getGameWin();

        Message message;

        if (gameWin.checkResignGameOver(playerService.getPlayer())) {
            message = Message.info(gameWin.getGameOverMessage());
        }
        else {
            message = Message.error("Game was unable to be ended.");
        }

        return gson.toJson(message);
    }
}
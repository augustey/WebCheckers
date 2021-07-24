package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.PlayerService;
import com.webcheckers.util.Message;
import spark.*;

/**
 * The UI Controller to POST the backup move message.
 *
 * @author <a href="mailto:jrl9984@rit.edu">Jim Logan</a>
 */
public class PostBackupMoveRoute implements Route {

    // The player service.
    private PlayerService playerService;

    /**
     * Constructor for PostBackupMoveRoute
     */
    public PostBackupMoveRoute() {
    }

    /**
     * Render the WebCheckers Backup message.
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
        Gson gson = new GsonBuilder().create();
        Message message;
        if (playerService.removeMove() != null) {
            message = Message.info("Move was reversed successfully!");
        }
        else {
            message = Message.error("Move was unable to be reversed!");
        }
        return gson.toJson(message);
    }
}
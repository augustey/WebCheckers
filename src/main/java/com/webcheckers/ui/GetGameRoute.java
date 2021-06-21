package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * UI controller to get the game route.
 * @author Yaqim Auguste (yaa6681@rit.edu)
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    //Used to send opponents name when selected in the list
    private final String OPPONENT_PARAM = "opponent";

    //Used to store and access player service in session object
    public static final String PLAYER_SERVICE_KEY = "PlayerService";

    //Attributes for the freemarker template
    private final String TITLE_ATTR = "title";
    private final String USER_ATTR = "currentUser";
    private final String VIEW_MODE_ATTR = "viewMode";
    private final String RED_PLAYER_ATTR = "redPlayer";
    private final String WHITE_PLAYER_ATTR = "whitePlayer";
    private final String ACTIVE_COLOR_ATTR = "activeColor";
    private final String BOARD_VIEW_ATTR = "board";

    //Freemarker values
    private final String TITLE = "Checkers";
    private final String VIEW_MODE = "PLAY"; //TODO: Add enumeration
    private final String ACTIVE_COLOR = "RED"; //TODO: Add enumeration

    /**
     * Constructor for GetGameRoute. Used to handle requests sent to "/game".
     * @param templateEngine Template engine used to render views.
     */
    public GetGameRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Game page.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");

        final Session httpSession = request.session();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_KEY);

        //If player object does not exist, redirect them to the sign in page.
        if(player == null) {
            response.redirect(WebServer.SIGNIN_URL);
            halt();
            return null;
        }

        //Attempt to retrieve playerService object
        PlayerService playerService = httpSession.attribute(PLAYER_SERVICE_KEY);

        //If player service does not exist, attempt to create new game with opponent
        if(playerService == null) {
            //Get the name of the opponent selected from the online list
            String opponentName = request.queryParams(OPPONENT_PARAM);

            //Retrieve the corresponding player object
            Player opponent = playerLobby.getPlayer(opponentName);

            //If the opponent is in the game then redirect them home
            //with an error message
            //TODO: Add error message functionality
            if (opponent == null || gameCenter.isInGame(opponent)) {
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            }

            //Create a new game and get a player service for it
            playerService = gameCenter.requestNewGame(player, opponent);

            httpSession.attribute(PLAYER_SERVICE_KEY, playerService);
        }

        Map<String, Object> vm = new HashMap<>();

        //Add template variables
        vm.put(TITLE_ATTR, TITLE);
        vm.put(USER_ATTR, player);
        vm.put(RED_PLAYER_ATTR, playerService.getRedPlayer());
        vm.put(WHITE_PLAYER_ATTR, playerService.getWhitePlayer());
        vm.put(VIEW_MODE_ATTR, VIEW_MODE); //TODO: Add enumeration
        vm.put(ACTIVE_COLOR_ATTR, ACTIVE_COLOR); //TODO: Add enumeration
        //TODO: Add board view attribute

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}

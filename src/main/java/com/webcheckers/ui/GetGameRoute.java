package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.application.PlayerLobby;
import com.webcheckers.application.PlayerService;
import com.webcheckers.model.Board;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import static spark.Spark.halt;

/**
 * UI controller to get the game route.
 *
 * @author <a href = 'mailto:yaa6681@rit.edu'>Yaqim Auguste</a>
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;
    private final PlayerLobby playerLobby;
    private final GameCenter gameCenter;

    // Used to send opponents name when selected in the list.
    public static final String OPPONENT_PARAM = "opponent";

    // Used to store and access player service in session object.
    public static final String PLAYER_SERVICE_KEY = "PlayerService";

    // Attributes for the freemarker template.
    public static final String TITLE_ATTR = "title";
    public static final String USER_ATTR = "currentUser";
    public static final String VIEW_MODE_ATTR = "viewMode";
    public static final String RED_PLAYER_ATTR = "redPlayer";
    public static final String WHITE_PLAYER_ATTR = "whitePlayer";
    public static final String ACTIVE_COLOR_ATTR = "activeColor";
    public static final String BOARD_VIEW_ATTR = "board";

    // Freemarker values.
    public static final String TITLE = "Checkers";
    public static final String VIEW_MODE = "PLAY"; //TODO: Add enumeration

    public static final String VIEW_NAME = "game.ftl";

    /**
     * Constructor for GetGameRoute. Used to handle requests sent to "/game".
     *
     * @param templateEngine
     *     Template engine used to render views.
     */
    public GetGameRoute(final PlayerLobby playerLobby, final GameCenter gameCenter, final TemplateEngine templateEngine) {
        this.playerLobby = Objects.requireNonNull(playerLobby, "playerLobby is required");
        this.gameCenter = Objects.requireNonNull(gameCenter, "gameCenter is required");
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetGameRoute is initialized.");
    }
    /**
     * Render the WebCheckers Game page.
     *
     * @param request
     *     The HTTP request.
     *
     * @param response
     *     The HTTP response.
     *
     * @return
     *     The rendered HTML for the Home page.
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");

        final Session httpSession = request.session();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_KEY);

        if(player == null) {
            response.redirect(WebServer.SIGNIN_URL);
            halt();
            return null;
        }

        PlayerService playerService = httpSession.attribute(PLAYER_SERVICE_KEY);

        if(playerService == null) {
            String opponentName = request.queryParams(OPPONENT_PARAM);
            Player opponent = playerLobby.getPlayer(opponentName);

            Message result  = gameCenter.requestNewGame(player, opponent);

            if(result.getType() == Message.Type.INFO) {
                playerService = gameCenter.getPlayerService(player);
                httpSession.attribute(PLAYER_SERVICE_KEY, playerService);
            }
            else {
                httpSession.attribute(GetHomeRoute.MESSAGE_KEY, result);
                response.redirect(WebServer.HOME_URL);
                halt();
                return null;
            }
        }

        Map<String, Object> vm = new HashMap<>();

        vm.put(TITLE_ATTR, TITLE);
        vm.put(USER_ATTR, player);
        vm.put(RED_PLAYER_ATTR, playerService.getRedPlayer());
        vm.put(WHITE_PLAYER_ATTR, playerService.getWhitePlayer());
        vm.put(VIEW_MODE_ATTR, VIEW_MODE); //TODO: Add enumeration
        vm.put(ACTIVE_COLOR_ATTR, playerService.getActivePlayerColor());
        vm.put(BOARD_VIEW_ATTR, playerService.getBoardView());

        return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
    }
}

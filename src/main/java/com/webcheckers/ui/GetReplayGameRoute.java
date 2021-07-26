package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import com.webcheckers.util.Message;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.webcheckers.ui.GetHomeRoute.PLAYER_KEY;
import static spark.Spark.halt;

/**
 * The UI Controller to GET the Replay Game page.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class GetReplayGameRoute implements Route {

    private final TemplateEngine templateEngine;

    // The application attributes.
    private final GameCenter gameCenter;
    private final TurnLogger turnLogger;

    // Used to send opponents name when selected in the list.
    public static final String GAMEID_PARAM = "gameID";
    public static final String TURNID_PARAM = "turn";

    // Attributes for the freemarker template.
    public static final String TITLE_ATTR = "title";
    public static final String USER_ATTR = "currentUser";
    public static final String VIEW_MODE_ATTR = "viewMode";
    public static final String RED_PLAYER_ATTR = "redPlayer";
    public static final String WHITE_PLAYER_ATTR = "whitePlayer";
    public static final String ACTIVE_COLOR_ATTR = "activeColor";
    public static final String BOARD_VIEW_ATTR = "board";
    public static final String MODE_OPTS_ATTR = "modeOptionsAsJSON";
    private static final String NEXT = "hasNext";
    private static final String PREV = "hasPrevious";

    // Freemarker values.
    public static final String TITLE = "Checkers";
    public static final String VIEW_MODE = "REPLAY";
    public static final String VIEW_NAME = "game.ftl";

    /**
     * Constructor for GetReplayGameRoute
     *
     * @param templateEngine
     *         The HTML template rendering engine.
     * @param gameCenter
     *         The GameCenter that stores active games
     * @param turnLogger
     *         The turnLogger used to store turns of a game
     */
    public GetReplayGameRoute(TemplateEngine templateEngine, GameCenter gameCenter, TurnLogger turnLogger) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.turnLogger = turnLogger;
    }

    /**
     * Render the WebCheckers Replay Game page.
     *
     * @param request
     *         The HTTP request.
     * @param response
     *         The HTTP response.
     *
     * @return The rendered HTML for the Replay Game page.
     */
    @Override
    public Object handle(Request request, Response response) {
        Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();
        final Map<String, Object> modeOptions = new HashMap<>();

        String gameID = request.queryParams(GAMEID_PARAM);
        Game game = gameCenter.getCompletedGame(gameID);

        Player player = httpSession.attribute(PLAYER_KEY);
        if (player == null || gameID == null) {
            response.redirect(WebServer.HOME_URL);
            halt();
        }
        if (!turnLogger.isReplaying(player)) {
            turnLogger.startReplay(player, game);
        }

        int i;
        if (httpSession.attribute(TURNID_PARAM) == null) {
            i = 0;
            httpSession.attribute(TURNID_PARAM, i);
        }
        else {
            i = httpSession.attribute(TURNID_PARAM);
        }

        List<String> turns = turnLogger.getTurns(game);

        System.out.println("size:" + turns.get(0));

        Board board = Board.fromString(turns.get(i));
        if (i % 2 != 0) {
            board.flip();
        }

        //Determine if there are turns before or after the current turn
        modeOptions.put(NEXT, true);
        modeOptions.put(PREV, true);
        if (i == 0) {
            modeOptions.put(PREV, false);
        }
        if (i == turns.size() - 1) {
            modeOptions.put(NEXT, false);
            vm.put("message", Message.info("You've reached the end of the game.\n" + game.getWinner() + " was victorious"));
        }

        Gson gson = new GsonBuilder().create();

        vm.put(TITLE_ATTR, TITLE);
        vm.put(USER_ATTR, player);
        vm.put(RED_PLAYER_ATTR, game.getRedPlayer());
        vm.put(WHITE_PLAYER_ATTR, game.getWhitePlayer());
        vm.put(VIEW_MODE_ATTR, VIEW_MODE);
        vm.put(ACTIVE_COLOR_ATTR, board.getActivePlayerColor());
        vm.put(BOARD_VIEW_ATTR, turnLogger.getBoardView(board));
        vm.put(MODE_OPTS_ATTR, gson.toJson(modeOptions));

        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
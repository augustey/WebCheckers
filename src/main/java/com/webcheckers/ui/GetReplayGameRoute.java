package com.webcheckers.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webcheckers.application.GameCenter;
import com.webcheckers.application.TurnLogger;
import com.webcheckers.model.Board;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetReplayGameRoute implements Route {

    private final TemplateEngine templateEngine;
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

    // Freemarker values.
    public static final String TITLE = "Checkers";
    public static final String VIEW_MODE = "REPLAY"; //TODO: Add enumeration

    public static final String VIEW_NAME = "game.ftl";


    public GetReplayGameRoute(TemplateEngine templateEngine, GameCenter gameCenter, TurnLogger turnLogger) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
        this.turnLogger = turnLogger;
    }

    @Override
    public Object handle(Request request, Response response) {
        Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_KEY);

        String gameID = request.queryParams(GAMEID_PARAM);
        Game game = gameCenter.getCompletedGame(gameID);

        int i = Integer.parseInt(request.queryParams(TURNID_PARAM));
        List<Board> turns = turnLogger.getTurns(game);

        vm.put(TITLE_ATTR, TITLE);
        vm.put(USER_ATTR, player);
        vm.put(RED_PLAYER_ATTR, game.getRedPlayer());
        vm.put(WHITE_PLAYER_ATTR, game.getWhitePlayer());
        vm.put(VIEW_MODE_ATTR, VIEW_MODE);
        vm.put(ACTIVE_COLOR_ATTR, Piece.Color.RED);
        vm.put(BOARD_VIEW_ATTR, turnLogger.getBoardView(turns.get(i)));

        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
}

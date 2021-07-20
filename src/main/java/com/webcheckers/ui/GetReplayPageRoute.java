package com.webcheckers.ui;

import com.webcheckers.application.GameCenter;
import com.webcheckers.model.Game;
import spark.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The UI Controller to GET the Replay page.
 *
 * @author <a href='mailto:jrl9984@rit.edu'>Jim Logan</a>
 */
public class GetReplayPageRoute implements Route {
    private final TemplateEngine templateEngine;
    private final GameCenter gameCenter;

    private final String TITLE_ATTR = "title";
    private final String GAMELIST_ATTR = "gameList";

    private final String TITLE = "Replay";

    public GetReplayPageRoute(final TemplateEngine templateEngine, final GameCenter gameCenter) {
        this.templateEngine = templateEngine;
        this.gameCenter = gameCenter;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        final Map<String, Object> vm = new HashMap<>();

        vm.put(TITLE_ATTR, TITLE);

        List<Game> list = gameCenter.getCompletedGames();

        vm.put(GAMELIST_ATTR, list);

        return templateEngine.render(new ModelAndView(vm, "replay.ftl"));
    }
}

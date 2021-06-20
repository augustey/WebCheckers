package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostSignOutRoute implements Route
{
    private static final String TITLE_ATTR = "title";


    private static final String TITLE = "Sign Out";

    private final PlayerLobby playerLobby;
    private final TemplateEngine templateEngine;

    public PostSignOutRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine)
    {
        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();

        Player player = httpSession.attribute(GetHomeRoute.PLAYER_KEY);
        playerLobby.signOut(player);
        httpSession.removeAttribute(GetHomeRoute.PLAYER_KEY);

        vm.put(TITLE_ATTR, GetHomeRoute.TITLE);

        int count = playerLobby.getPlayerSet().size();
        vm.put(GetHomeRoute.ONLINE_COUNT_ATTR, count);

        vm.put("message", GetHomeRoute.WELCOME_MSG);

        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}

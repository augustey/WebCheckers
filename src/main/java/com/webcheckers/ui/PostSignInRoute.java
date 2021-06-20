package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import com.webcheckers.model.Player;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PostSignInRoute implements Route
{
    public static final String USERNAME_PARAM = "username";
    public static final String NOT_VALID_USERNAME = "notValid";

    public static final String NON_UNIQUE_USERNAME_ATTR = "The name you entered is already in the system";
    public static final String NON_ALPHANUMERIC_USERNAME_ATTR = "The name you entered does not contain a letter or a number";

    private PlayerLobby playerLobby;
    private TemplateEngine templateEngine;

    public PostSignInRoute(final PlayerLobby playerLobby, final TemplateEngine templateEngine)
    {
        Objects.requireNonNull(playerLobby, "playerLobby must not be null");
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.playerLobby = playerLobby;
        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception
    {
        final Session httpSession = request.session();

        final Map<String, Object> vm = new HashMap<>();

        final String nameString = request.queryParams(USERNAME_PARAM);
        Player player = new Player(nameString);

        vm.put(GetSignInRoute.TITLE_ATTR, GetSignInRoute.TITLE);

        switch (playerLobby.signIn(player))
        {
            case NON_UNIQUE:
                vm.put("notValid", true);
                vm.put("invalidMessage", NON_UNIQUE_USERNAME_ATTR);
                break;
            case NON_ALPHANUMERIC:
                vm.put("notValid", true);
                vm.put("invalidMessage", NON_ALPHANUMERIC_USERNAME_ATTR);
                break;
            case SUCCESS:
                httpSession.attribute(GetHomeRoute.PLAYER_KEY, player);
                vm.put("notValid", false);
                break;
        }

        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}

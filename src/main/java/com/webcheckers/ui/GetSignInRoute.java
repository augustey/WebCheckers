package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GetSignInRoute implements Route
{
    public static final String TITLE_ATTR = "title";

    public static final String TITLE = "Sign In";

    private final TemplateEngine templateEngine;

    public GetSignInRoute(final TemplateEngine templateEngine)
    {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
    }

    @Override
    public Object handle(Request request, Response response)
    {
        final Map<String, Object> vm = new HashMap<>();

        vm.put(TITLE_ATTR, TITLE);

        vm.put(PostSignInRoute.NOT_VALID_USERNAME, false);

        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}

package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;

public class PostResignGameRoute implements Route
{
    private TemplateEngine templateEngine;

    public PostResignGameRoute(final TemplateEngine templateEngine)
    {
        this.templateEngine = templateEngine;
    }


    @Override
    public Object handle(Request request, Response response)
    {
        final Session httpSession = request.session();
        final Map<String, Object> vm = new HashMap<>();

        return templateEngine.render(new ModelAndView(vm , "game.ftl"));
    }
}
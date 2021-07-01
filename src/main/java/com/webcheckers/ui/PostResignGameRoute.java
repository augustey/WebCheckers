package com.webcheckers.ui;

import com.webcheckers.util.Message;
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

        return Message.info("Placeholder");
    }
}
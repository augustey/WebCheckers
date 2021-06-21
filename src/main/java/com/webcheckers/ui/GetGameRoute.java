package com.webcheckers.ui;

import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * UI controller to get the game route.
 * @author Yaqim Auguste (yaa6681@rit.edu)
 */
public class GetGameRoute implements Route {
    private static final Logger LOG = Logger.getLogger(GetGameRoute.class.getName());
    private final TemplateEngine templateEngine;

    //Used to send opponents name when selected in the list
    private final String OPPONENT_PARAM = "opponent";

    /**
     * Constructor for GetGameRoute. Used to handle requests sent to "/game".
     * @param templateEngine Template engine used to render views.
     */
    public GetGameRoute(final TemplateEngine templateEngine) {
        this.templateEngine = Objects.requireNonNull(templateEngine, "templateEngine is required");
        LOG.config("GetGameRoute is initialized.");
    }

    /**
     * Render the WebCheckers Home page.
     * @param request the HTTP request
     * @param response the HTTP response
     * @return the rendered HTML for the Home page
     */
    @Override
    public Object handle(Request request, Response response) {
        LOG.finer("GetGameRoute is invoked.");

        Map<String, Object> vm = new HashMap<>();

        response.redirect(WebServer.HOME_URL);//templateEngine.render(new ModelAndView(vm , "game.ftl"));
        return null;
    }
}

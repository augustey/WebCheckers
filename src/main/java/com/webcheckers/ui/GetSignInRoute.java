package com.webcheckers.ui;

import com.webcheckers.application.PlayerLobby;
import spark.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The UI Controller to GET the Signin page.
 * @author <a href="mailto:jrl9984@rit.edu">Jim Logan</a>
 */
public class GetSignInRoute implements Route
{
    //Attributes
    public static final String TITLE_ATTR = "title";

    //Text
    public static final String TITLE = "Sign In";

    //State
    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) to handle all {@code GET /signin} HTTP requests.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSignInRoute(final TemplateEngine templateEngine)
    {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");

        this.templateEngine = templateEngine;
    }

    /**
     * Render the WebCheckers SignIn page.
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   the rendered HTML for the Signin page
     */
    @Override
    public Object handle(Request request, Response response)
    {
        final Map<String, Object> vm = new HashMap<>();

        vm.put(TITLE_ATTR, TITLE);

        vm.put(PostSignInRoute.NOT_VALID_USERNAME, false);
        vm.put(PostSignInRoute.SIGNED_IN, false);

        return templateEngine.render(new ModelAndView(vm , "signin.ftl"));
    }
}
